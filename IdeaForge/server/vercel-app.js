/**
 * Express 앱을 Vercel serverless로 감싸기 위한 placeholder.
 * 현재 로컬 개발은 server/index.js listen 방식을 사용합니다.
 * GitHub/Vercel 연결 단계에서 통합합니다.
 */
export default async function handler(req, res) {
  res.statusCode = 501;
  res.setHeader('Content-Type', 'application/json');
  res.end(JSON.stringify({ error: 'NOT_WIRED', message: 'Vercel API wiring pending' }));
}
