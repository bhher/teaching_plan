# fetch · axios · ajax 비교 설명

예제 앱: `react/fetch-axios-ajax-compare` (**Vite + React**)

```bash
cd react/fetch-axios-ajax-compare
npm install
npm run dev
```

브라우저에서 세 카드의 **GET / POST** 버튼을 눌러 같은 API 응답을 비교합니다.

---

## 0. Vite로 만든 이유

| 항목 | 내용 |
|------|------|
| 빌드 도구 | Vite |
| UI | React 18 |
| HTTP 라이브러리 | axios (fetch·XHR은 브라우저 내장) |
| 연습 API | [JSONPlaceholder](https://jsonplaceholder.typicode.com/posts) |

Vite는 `npm run dev`로 바로 화면을 띄우고, `import`로 axios·컴포넌트를 깔끔하게 나눌 수 있어 수업 예제에 적합합니다.

```text
브라우저 버튼 클릭
   → App.jsx 의 run()
   → withFetch / withAxios / withAjax
   → JSONPlaceholder
   → 결과를 카드에 JSON으로 표시
```

---

## 1. 세 가지가 무엇인가?

모두 **서버에 HTTP 요청을 보내는 방법**입니다.  
차이는 “누가 제공하느냐”, “응답을 어떻게 다루느냐”입니다.

| | fetch | axios | ajax (XHR) |
|--|-------|-------|------------|
| 정식 이름 | Fetch API | axios 라이브러리 | XMLHttpRequest (고전 AJAX) |
| 제공 | 브라우저 내장 | `npm i axios` | 브라우저 내장 |
| 스타일 | Promise + async/await | Promise + async/await | 원래 콜백 → Promise로 감싸 사용 |
| 요즘 사용 | 매우 흔함 | 실무 API 레이어에서 흔함 | 레거시·개념 이해용 |

> **AJAX**란?  
> “페이지 새로고침 없이 서버와 데이터를 주고받는 기법”의 이름입니다.  
> 예전에는 주로 `XMLHttpRequest`로 구현했고, jQuery의 `$.ajax()`도 그 위를 감싼 것입니다.  
> 지금 말하는 fetch·axios도 넓게 보면 AJAX 방식입니다.

---

## 2. 프로젝트 구조

```text
fetch-axios-ajax-compare/
├── package.json          # vite, react, axios
├── vite.config.js
├── index.html
├── README.md
├── fetch-axios-ajax-비교-설명.md   ← 이 문서
└── src/
    ├── main.jsx
    ├── App.jsx                 # 세 방식 실행·상태 관리
    ├── index.css
    ├── api/
    │   ├── config.js           # API 주소, POST 샘플 body
    │   ├── withFetch.js
    │   ├── withAxios.js
    │   └── withAjax.js
    └── components/
        ├── CompareCard.jsx     # 카드 UI
        └── CompareTable.jsx    # 비교 표
```

---

## 3. fetch — 브라우저 내장

### 3.1 GET

```js
const res = await fetch(`${API}?_limit=3`);
if (!res.ok) throw new Error(`HTTP ${res.status}`);
return res.json();
```

### 3.2 POST

```js
const res = await fetch(API, {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify(SAMPLE_POST),
});
if (!res.ok) throw new Error(`HTTP ${res.status}`);
return res.json();
```

### 3.3 꼭 기억할 점

1. **설치 불필요**
2. 응답은 `Response` 객체 → **`res.json()`을 직접** 호출해야 데이터가 나옴
3. **404, 500이어도 Promise는 성공(resolve)** 할 수 있음  
   → 반드시 `if (!res.ok)` 로 HTTP 에러를 직접 처리
4. body는 문자열이므로 객체는 `JSON.stringify`

예제 파일: `src/api/withFetch.js`

---

## 4. axios — npm 라이브러리

### 4.1 설치

```bash
npm i axios
```

### 4.2 GET / POST

```js
import axios from 'axios';

const { data } = await axios.get(API, { params: { _limit: 3 } });
const { data } = await axios.post(API, SAMPLE_POST);
```

### 4.3 꼭 기억할 점

1. 응답에서 **`data`가 이미 파싱된 JSON**
2. **4xx/5xx면 자동으로 에러(throw)** → try/catch로 잡기 쉬움
3. 쿼리는 `params` 객체로 전달 (`?_limit=3` 직접 안 붙여도 됨)
4. 공통 헤더·토큰은 **인터셉터**로 한곳에 모을 수 있음 (확장)

예제 파일: `src/api/withAxios.js`

---

## 5. ajax — XMLHttpRequest (고전)

### 5.1 흐름

```text
xhr.open(method, url)
xhr.onload / xhr.onerror 등록
xhr.send(body?)
```

### 5.2 예제에서는 Promise로 감쌈

현대 코드와 나란히 비교하려고 XHR을 Promise로 감쌌습니다.

```js
function xhrRequest({ method, url, body }) {
  return new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.responseType = 'json';

    xhr.onload = () => {
      if (xhr.status >= 200 && xhr.status < 300) {
        resolve(xhr.response);
      } else {
        reject(new Error(`HTTP ${xhr.status}`));
      }
    };
    xhr.onerror = () => reject(new Error('네트워크 오류'));

    if (body) {
      xhr.setRequestHeader('Content-Type', 'application/json');
      xhr.send(JSON.stringify(body));
    } else {
      xhr.send();
    }
  });
}
```

### 5.3 꼭 기억할 점

1. 원래는 **콜백** 스타일 (`onload`)
2. 상태 코드·JSON 파싱을 **직접** 처리
3. jQuery `$.ajax({ url, method, success, error })` 도 내부적으로 이런 XHR을 사용
4. 새 프로젝트에서는 fetch/axios를 쓰고, XHR은 **레거시 이해**용으로 알아 두면 충분

예제 파일: `src/api/withAjax.js`

---

## 6. 한눈에 비교표

| 항목 | fetch | axios | ajax (XHR) |
|------|-------|-------|------------|
| 설치 | 불필요 | `npm i axios` | 불필요 |
| 반환 | `Response` | `{ data, status, ... }` | `xhr.response` |
| JSON | `await res.json()` | 자동 (`data`) | `responseType='json'` 등 |
| HTTP 에러 | 직접 `res.ok` | 자동 reject | `status` 직접 체크 |
| 쿼리 파라미터 | URL에 직접 또는 URLSearchParams | `params: {}` | URL에 직접 |
| 요청 취소 | `AbortController` | AbortController / CancelToken | `xhr.abort()` |
| 인터셉터 | 없음 (직접 래핑) | 있음 | 없음 |
| 수업·실무 | 기본기로 필수 | API 모듈에 자주 사용 | 개념·레거시 |

---

## 7. App.jsx에서 공통으로 실행하는 이유

세 함수의 **입출력 형태를 비슷하게** 맞춰 두었습니다.

```js
async function run(key, fn) {
  // loading / error / result 상태 갱신
  const result = await fn();  // 모두 Promise → JSON 데이터
}
```

그래서 UI는 하나이고, **바꾸는 것은 api 파일뿐**입니다.  
“같은 일을 다른 도구로” 비교할 때 좋은 패턴입니다.

---

## 8. 화면에서 확인하는 방법

1. `npm run dev` 실행
2. **fetch** 카드 → GET → 게시글 3개 JSON
3. **axios** 카드 → GET → 거의 같은 데이터
4. **ajax** 카드 → GET → 역시 같은 API 결과
5. 각 카드 **POST** → `id`가 붙은 가짜 생성 응답 (JSONPlaceholder는 DB에 안 남김)

결과가 비슷해 보여도, **콘솔·코드 경로가 다름**을 `src/api/`에서 확인하는 것이 목표입니다.

---

## 9. 언제 무엇을 쓸까? (수업용 가이드)

| 상황 | 추천 |
|------|------|
| 의존성 없이 빠르게 | **fetch** |
| 토큰·에러·로딩을 API 레이어로 통일 | **axios** |
| 예전 jQuery 코드 읽기 / AJAX 역사 | **XHR / $.ajax** 개념 |

초급 수업 순서 제안:

1. fetch GET → `res.ok` + `res.json()`
2. fetch POST → headers + JSON.stringify
3. axios로 같은 것 다시 쓰기 → 코드가 짧아지는 느낌
4. XHR 코드 읽고 “예전엔 이렇게” 한 번 보기

---

## 10. 자주 하는 실수

| 실수 | 설명 |
|------|------|
| fetch에서 `res.ok` 안 봄 | 404인데도 then으로 들어가 버림 |
| fetch에서 `res.json()` 깜빡임 | Response 객체만 화면에 나옴 |
| axios에서 `response` 전체를 state에 넣음 | 보통은 `response.data`만 사용 |
| XHR에서 `send()` 전에 콜백 안 붙임 | 응답을 못 받음 |
| POST body에 객체를 그대로 넣음 (fetch) | 문자열로 `JSON.stringify` 필요 |

---

## 11. 관련 자료

- 예제 README: 같은 폴더 `README.md`
- 이론 보강: `react/react-14차시-fetch-axios.md`
