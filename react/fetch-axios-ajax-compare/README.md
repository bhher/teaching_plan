# fetch · axios · ajax 비교 예제 (Vite + React)

Vite로 만든 수업용 예제입니다. 같은 API를 **fetch / axios / ajax(XHR)** 세 방식으로 호출합니다.

## 실행

```bash
cd react/fetch-axios-ajax-compare
npm install
npm run dev
```

## 설명 문서

자세한 비교·코드 해설은 아래 문서를 보세요.

→ **[fetch-axios-ajax-비교-설명.md](./fetch-axios-ajax-비교-설명.md)**

## 학습 포인트

| 방식 | 핵심 |
|------|------|
| **fetch** | 브라우저 내장, `res.json()`, `res.ok` 직접 체크 |
| **axios** | `npm i axios`, 자동 JSON·에러 처리, `params` |
| **ajax (XHR)** | `XMLHttpRequest` — 고전 AJAX / jQuery `$.ajax` 기반 |

## 폴더

```text
src/api/
  config.js       # API URL, POST 샘플
  withFetch.js
  withAxios.js
  withAjax.js
src/components/
  CompareCard.jsx
  CompareTable.jsx
```

## API

[JSONPlaceholder](https://jsonplaceholder.typicode.com/posts) — 키 없이 GET/POST 연습  
(POST는 실제 저장되지 않고 가짜 응답만 반환)
