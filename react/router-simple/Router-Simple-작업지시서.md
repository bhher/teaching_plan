# 작업지시서 — Router Simple 유사 미니 사이트

**참고 완성본:** `react/router-simple/`  
**목표:** Layout + Outlet 중첩 라우트로 홈 · 소개 · 문의 · 404 사이트를 스스로 만들기

---

## 제출 조건

| 항목 | 내용 |
|------|------|
| 프로젝트명 | `router-simple-hw` (또는 자유) |
| 도구 | Vite + React + `react-router-dom` |
| 제출 | 소스 폴더 또는 Git · 실행 화면 캡처 |

```bash
npm create vite@latest router-simple-hw -- --template react
cd router-simple-hw
npm install
npm install react-router-dom
npm run dev
```

---

## 최종 화면 구성 (완성 기준)

```text
┌─────────────────────────────────────┐
│  [브랜드]     홈 | 소개 | 문의        │  ← 모든 페이지 공통 (Layout)
├─────────────────────────────────────┤
│                                     │
│         (여기만 URL마다 바뀜)         │  ← Outlet
│                                     │
├─────────────────────────────────────┤
│  footer 문구                         │  ← 공통
└─────────────────────────────────────┘
```

| URL | 화면 |
|-----|------|
| `/` | 홈 — 사이트 소개 문단 + 메뉴 안내 목록 |
| `/about` | 소개 — 라우팅 설명 + **경로 표** |
| `/contact` | 문의 — 이름·메시지 폼, 제출 시 데모 안내 |
| `/없는주소` | 404 — 안내 + **홈으로** 링크 |
| (공통) | 헤더 NavLink 활성 스타일, 푸터 |

---

## 작업 순서 (단계별)

### 1단계 — 설치 · 폴더 ⭐

1. Vite React 프로젝트 생성, `react-router-dom` 설치  
2. 아래 폴더/파일을 만든다.

```text
src/
  main.jsx
  App.jsx
  index.css          (간단 스타일 OK)
  components/
    Layout.jsx
  pages/
    Home.jsx
    About.jsx
    Contact.jsx
    NotFound.jsx
```

**확인:** `npm run dev` 로 빈 화면이라도 실행된다.

---

### 2단계 — BrowserRouter ⭐

`main.jsx`에서 앱을 `BrowserRouter`로 감싼다.

```jsx
import { BrowserRouter } from 'react-router-dom';

// ...
<BrowserRouter>
  <App />
</BrowserRouter>
```

**확인:** 콘솔에 Router 관련 에러가 없다.

---

### 3단계 — Layout (뼈대) ⭐⭐

`Layout.jsx` 요구사항:

1. 헤더에 브랜드 링크 → `/`  
2. 메뉴 3개: 홈(`/`), 소개(`/about`), 문의(`/contact`)  
3. **반드시 `NavLink` 사용** (`<a href>` 금지)  
4. 홈 링크에는 `end` prop 사용  
5. `<main>` 안에 **`<Outlet />`**  
6. 푸터에 한 줄 문구 (예: `React Router · 중첩 라우트`)

활성 메뉴 예시:

```jsx
const navLinkClass = ({ isActive }) =>
  isActive ? 'nav-link nav-link--active' : 'nav-link';
```

**확인:** 아직 Routes가 없어도 Layout만 화면에 그릴 수 있으면 OK. 다음은 App 연결.

---

### 4단계 — App.jsx 중첩 Routes ⭐⭐

`App.jsx`에 다음 구조를 **그대로 개념에 맞게** 작성한다.

```jsx
<Routes>
  <Route path="/" element={<Layout />}>
    <Route index element={<Home />} />
    <Route path="about" element={<About />} />
    <Route path="contact" element={<Contact />} />
    <Route path="*" element={<NotFound />} />
  </Route>
</Routes>
```

주의:

- 자식 path는 `about`처럼 **상대 경로** (앞에 `/` 없이)  
- 홈은 `index`  
- 없는 주소는 `path="*"`

**확인:** 메뉴 클릭 시 URL이 바뀌고, 헤더/푸터는 유지된 채 가운데만 바뀐다.

---

### 5단계 — 페이지 내용 ⭐⭐

#### Home.jsx

- 제목: `홈`
- `react-router-dom` / `Outlet` 등을 언급하는 짧은 설명 문단 1개
- 소개·문의 메뉴를 안내하는 `<ul>` 목록

#### About.jsx

- 제목: `소개`
- Layout + Outlet 역할을 2~3문장으로 설명
- **표**로 URL ↔ 컴포넌트 정리  

| URL | 컴포넌트 |
|-----|----------|
| `/` | Home |
| `/about` | About |
| `/contact` | Contact |
| `/*` | NotFound |

#### Contact.jsx

- 제목: `문의`
- 이름 input, 메시지 textarea, 제출 버튼
- `useState`로 제출 여부 관리
- `onSubmit`에서 `preventDefault()` 후 “데모: 제출된 것처럼 표시” 문구 보이기
- **실제 서버 전송 없음**

#### NotFound.jsx

- `404` 제목 + 안내 문구
- `Link`로 `to="/"` 홈 이동 버튼

**확인:** `/contact`에서 보내기 → 안내 문구. 주소창에 `/abc` → 404 → 홈으로.

---

### 6단계 — 스타일 (최소) ⭐

`index.css`에 최소 요구만 적용 (디자인 자유):

- [ ] 헤더·본문·푸터 레이아웃이 구분된다  
- [ ] `.nav-link--active` (또는 동등)로 **현재 메뉴**가 눈에 띈다  
- [ ] 폼 label / input 간격이 읽기 편하다  
- [ ] 모바일에서도 메뉴가 크게 깨지지 않는다 (줄바꿈 OK)

---

## 서술 문제 (선택 · 퀴즈)

1. `<a href="/about">` 대신 `NavLink` / `Link`를 쓰는 이유는?  
2. `Outlet`이 없으면 어떤 문제가 생기는가?  
3. 홈 `NavLink`에 `end`를 넣는 이유는?  
4. `path="*"` Route는 왜 Layout의 **자식**으로 두는가?

---

## 보너스 (선택) ⭐⭐⭐

아래 중 **하나 이상** 추가하면 가산점.

- [ ] 메뉴에 **Portfolio** (`/portfolio`) 페이지 추가 (카드 2~3개)  
- [ ] Contact를 controlled input (`value` + `onChange`)으로 바꾸기  
- [ ] 404가 Layout 밖이 아니라, 지시서처럼 Layout 안에서 나오게 유지했는지 설명 1줄  
- [ ] `router-project`처럼 Home에 간단한 슬라이드 넣기  

---

## 채점 체크리스트

| No | 항목 | 배점 가이드 |
|----|------|-------------|
| 1 | BrowserRouter + Routes/Route 동작 | 필수 |
| 2 | Layout에 NavLink · Outlet · footer | 필수 |
| 3 | index / about / contact / * 연결 | 필수 |
| 4 | Contact 데모 제출 (preventDefault + state) | 필수 |
| 5 | NotFound + Link 홈 | 필수 |
| 6 | NavLink 활성 스타일 + end | 권장 |
| 7 | About 경로 표 | 권장 |
| 8 | 보너스 | 가산 |

---

## 금지 사항

- 페이지 이동을 `<a href="...">`만으로 처리하기 (전체 새로고침 방식)
- Contact에서 실제 API/메일 전송 구현을 필수로 요구하지 않음 (데모만)
- 완성본 `router-simple` 코드를 **그대로 복붙**만 하고 구조 설명을 못하는 경우 → 감점 가능  
  → 파일은 참고하되, **본인 프로젝트에 직접 타이핑**할 것

---

## 완료 후 자가 테스트

1. `/` → 홈 내용, 홈 메뉴 active  
2. `/about` → 표 보임, 소개 메뉴 active, 홈은 active 아님 (`end`)  
3. `/contact` → 폼 제출 → 데모 메시지  
4. `/zzz` → 404 → 홈으로 → `/`  
5. 메뉴 이동 시 **헤더/푸터가 깜빡이며 사라지지 않음** (SPA)

---

## 한 줄 목표

> **공통 Layout + Outlet**, **NavLink로 메뉴**, **없는 주소는 404** 인 미니 사이트 완성하기.
