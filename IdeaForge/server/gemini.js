import { GoogleGenerativeAI } from '@google/generative-ai';
import { env } from './env.js';

function client() {
  if (!env.geminiKey) {
    const err = new Error('GEMINI_API_KEY missing');
    err.code = 'SERVER_MISCONFIG';
    err.status = 500;
    throw err;
  }
  return new GoogleGenerativeAI(env.geminiKey);
}

async function generateText(prompt, modelName = env.geminiModel) {
  const genAI = client();
  const model = genAI.getGenerativeModel({ model: modelName });
  const result = await model.generateContent(prompt);
  return result.response.text();
}

function extractJson(text) {
  const fenced = text.match(/```(?:json)?\s*([\s\S]*?)```/);
  const raw = fenced ? fenced[1] : text;
  const start = raw.indexOf('{');
  const end = raw.lastIndexOf('}');
  const arrStart = raw.indexOf('[');
  const arrEnd = raw.lastIndexOf(']');
  let slice = raw;
  if (start !== -1 && end !== -1 && (arrStart === -1 || start < arrStart)) {
    slice = raw.slice(start, end + 1);
  } else if (arrStart !== -1 && arrEnd !== -1) {
    slice = raw.slice(arrStart, arrEnd + 1);
  }
  return JSON.parse(slice);
}

async function withModelFallback(run) {
  const models = [env.geminiModel, 'gemini-flash-latest', 'gemini-2.5-flash', 'gemini-2.5-flash-lite'];
  const tried = new Set();
  let lastErr;
  for (const model of models) {
    if (!model || tried.has(model)) continue;
    tried.add(model);
    try {
      return await run(model);
    } catch (e) {
      lastErr = e;
    }
  }
  const err = new Error('LLM_FAIL');
  err.code = 'LLM_FAIL';
  err.status = 502;
  err.cause = lastErr;
  throw err;
}

export async function generateTitles({ keywords, audience, postType, tone }) {
  const prompt = `당신은 네이버 블로그 SEO·클릭을 잘하는 한국어 카피라이터입니다.
조건:
- 키워드: ${keywords.join(', ')}
- 독자층: ${audience || '일반'}
- 글 유형: ${postType || '정보성'}
- 톤: ${tone || '친근'}
요구:
- 서로 다른 추천 제목 정확히 5개
- 과장·허위·자극적 낚시 제목 금지
- JSON만 출력: {"titles":["...","...","...","...","..."]}`;

  return withModelFallback(async (model) => {
    const text = await generateText(prompt, model);
    const data = extractJson(text);
    const titles = (data.titles || []).map((t) => String(t).trim()).filter(Boolean);
    if (titles.length < 5) throw new Error('bad titles');
    return titles.slice(0, 5);
  });
}

export async function generateOutline({ keywords, audience, postType, tone, title }) {
  const prompt = `네이버 블로그용 글 구성을 JSON으로만 작성하세요.
제목: ${title}
키워드: ${keywords.join(', ')}
독자층: ${audience || '일반'}
글 유형: ${postType || '정보성'}
톤: ${tone || '친근'}

스키마:
{
  "search_intent": "문자열",
  "intro": "도입부 문단",
  "sections": [
    {"heading":"소제목","points":["핵심1","핵심2"],"example":"예시"}
  ],
  "outro": "마무리",
  "hashtags": ["#태그1","#태그2"],
  "image_ideas": ["이미지 아이디어1"],
  "notes": ["유의점1"]
}
sections는 3~5개. 한국어.`;

  return withModelFallback(async (model) => {
    const text = await generateText(prompt, model);
    const data = extractJson(text);
    if (!data.search_intent || !data.intro || !Array.isArray(data.sections)) {
      throw new Error('bad outline');
    }
    return {
      search_intent: String(data.search_intent),
      intro: String(data.intro),
      sections: data.sections.map((s) => ({
        heading: String(s.heading || ''),
        points: Array.isArray(s.points) ? s.points.map(String) : [],
        example: String(s.example || ''),
      })),
      outro: String(data.outro || ''),
      hashtags: Array.isArray(data.hashtags) ? data.hashtags.map(String) : [],
      image_ideas: Array.isArray(data.image_ideas) ? data.image_ideas.map(String) : [],
      notes: Array.isArray(data.notes) ? data.notes.map(String) : [],
    };
  });
}
