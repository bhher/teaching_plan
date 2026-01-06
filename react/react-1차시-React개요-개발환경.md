# 1차시. React 개요 & 개발 환경

## 학습 목표
- React가 무엇인지 이해하고 SPA 개념을 설명할 수 있다
- React와 전통적인 HTML/CSS/JS의 차이점을 이해할 수 있다
- Node.js와 npm의 개념을 이해하고 사용할 수 있다
- Vite와 CRA의 차이점을 이해할 수 있다
- React 프로젝트를 생성하고 실행할 수 있다
- 프로젝트 폴더 구조를 이해할 수 있다

---

## 1. React란 무엇인가?

### 1.1 React의 정의

**React**는 사용자 인터페이스(UI)를 구축하기 위한 JavaScript 라이브러리입니다.

**주요 특징:**
- ✅ **컴포넌트 기반**: 재사용 가능한 UI 조각
- ✅ **선언적**: 원하는 UI 상태를 선언하면 React가 처리
- ✅ **가상 DOM**: 빠른 렌더링 성능
- ✅ **단방향 데이터 흐름**: 데이터 흐름이 명확함

### 1.2 SPA (Single Page Application) 개념

**전통적인 웹사이트 (MPA - Multi Page Application):**
```
사용자 클릭
    ↓
서버에 요청
    ↓
새로운 HTML 페이지 받음
    ↓
전체 페이지 새로고침
```

**SPA (Single Page Application):**
```
사용자 클릭
    ↓
JavaScript가 화면만 변경
    ↓
페이지 새로고침 없음
    ↓
빠르고 부드러운 사용자 경험
```

**SPA의 장점:**
- ✅ 빠른 페이지 전환
- ✅ 부드러운 사용자 경험
- ✅ 서버 부하 감소
- ✅ 모바일 앱과 유사한 경험

**SPA의 단점:**
- ❌ 초기 로딩 시간이 길 수 있음
- ❌ SEO 최적화가 어려울 수 있음 (해결 방법 있음)

### 1.3 React의 역사

- **2013년**: Facebook에서 개발 및 공개
- **현재**: 가장 인기 있는 프론트엔드 라이브러리
- **사용처**: Facebook, Instagram, Netflix, Airbnb 등

---

## 2. React vs HTML/CSS/JS 차이

### 2.1 전통적인 웹 개발 방식

**HTML 파일:**
```html
<!DOCTYPE html>
<html>
<head>
    <title>카운터</title>
</head>
<body>
    <h1>카운터: <span id="count">0</span></h1>
    <button id="increment">증가</button>
    
    <script>
        let count = 0;
        document.getElementById('increment').addEventListener('click', function() {
            count++;
            document.getElementById('count').textContent = count;
        });
    </script>
</body>
</html>
```

**문제점:**
- ❌ HTML, CSS, JS가 분리되어 있어 관리 어려움
- ❌ DOM 조작이 복잡함
- ❌ 상태 관리가 어려움
- ❌ 재사용이 어려움

### 2.2 React 방식

**React 컴포넌트:**
```jsx
function Counter() {
    const [count, setCount] = useState(0);
    
    return (
        <div>
            <h1>카운터: {count}</h1>
            <button onClick={() => setCount(count + 1)}>증가</button>
        </div>
    );
}
```

**장점:**
- ✅ HTML, CSS, JS가 하나의 컴포넌트에 모임
- ✅ 선언적 코드 (어떻게 보일지 선언)
- ✅ 상태 관리가 쉬움
- ✅ 재사용 가능한 컴포넌트

### 2.3 비교표

| 항목 | 전통적인 방식 | React |
|------|--------------|-------|
| 코드 구조 | 분리됨 | 통합됨 |
| DOM 조작 | 직접 조작 | 자동 업데이트 |
| 상태 관리 | 어려움 | 쉬움 |
| 재사용성 | 낮음 | 높음 |
| 학습 곡선 | 낮음 | 중간 |

---

## 3. Node.js / npm 개념

### 3.1 Node.js란?

**Node.js**는 JavaScript를 서버에서 실행할 수 있게 해주는 런타임 환경입니다.

**주요 용도:**
- 서버 개발
- 빌드 도구 실행
- 패키지 관리

**React 개발에서의 역할:**
- 개발 서버 실행
- 빌드 도구 실행
- 패키지 설치 및 관리

### 3.2 npm이란?

**npm** (Node Package Manager)은 Node.js 패키지 관리자입니다.

**주요 기능:**
- 패키지 설치
- 프로젝트 의존성 관리
- 스크립트 실행

**npm 명령어:**
```bash
# 패키지 설치
npm install 패키지명

# 개발 의존성 설치
npm install -D 패키지명

# 패키지 제거
npm uninstall 패키지명

# 패키지 목록 확인
npm list
```

### 3.3 package.json

프로젝트의 의존성과 스크립트를 관리하는 파일입니다.

```json
{
  "name": "my-react-app",
  "version": "1.0.0",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview"
  },
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0"
  },
  "devDependencies": {
    "vite": "^4.4.5"
  }
}
```

---

## 4. Vite vs CRA (Create React App)

### 4.1 CRA (Create React App)

**특징:**
- Facebook에서 공식 제공
- 설정이 자동화됨
- Webpack 기반
- 비교적 느린 빌드 속도

**생성 방법:**
```bash
npx create-react-app my-app
cd my-app
npm start
```

### 4.2 Vite

**특징:**
- 빠른 개발 서버
- 빠른 빌드 속도
- ES 모듈 기반
- 최신 도구 사용

**생성 방법:**
```bash
npm create vite@latest my-app -- --template react
cd my-app
npm install
npm run dev
```

### 4.3 비교표

| 항목 | CRA | Vite |
|------|-----|------|
| 개발 서버 속도 | 보통 | 매우 빠름 |
| 빌드 속도 | 느림 | 빠름 |
| 설정 복잡도 | 간단 | 간단 |
| 최신 기술 | 보통 | 최신 |
| 권장 | ❌ (유지보수 모드) | ✅ |

**결론: Vite를 사용하는 것을 권장합니다!**

---

## 5. 프로젝트 생성 실습

### 5.1 사전 준비

**필수 설치:**
- Node.js (v18 이상 권장)
- VSCode
- Git

**설치 확인:**
```bash
node --version
npm --version
```

### 5.2 Vite로 React 프로젝트 생성

**1단계: 프로젝트 생성**
```bash
npm create vite@latest my-react-app -- --template react
```

**프롬프트 선택:**
- Project name: `my-react-app` (또는 원하는 이름)
- Select a framework: `React`
- Select a variant: `JavaScript` (또는 TypeScript)

**2단계: 프로젝트 폴더로 이동**
```bash
cd my-react-app
```

**3단계: 의존성 설치**
```bash
npm install
```

**4단계: 개발 서버 실행**
```bash
npm run dev
```

**결과:**
```
  VITE v4.4.5  ready in 500 ms

  ➜  Local:   http://localhost:5173/
  ➜  Network: use --host to expose
```

브라우저에서 `http://localhost:5173/` 접속하면 React 앱이 실행됩니다!

### 5.3 프로젝트 폴더 구조 이해

```
my-react-app/
├── node_modules/          # 설치된 패키지들
├── public/               # 정적 파일 (이미지, 아이콘 등)
│   └── vite.svg
├── src/                 # 소스 코드
│   ├── App.jsx          # 메인 컴포넌트
│   ├── App.css          # App 컴포넌트 스타일
│   ├── index.css        # 전역 스타일
│   ├── main.jsx         # 진입점 (Entry Point)
│   └── assets/          # 이미지, 폰트 등
│       └── react.svg
├── .gitignore           # Git 제외 파일 목록
├── index.html           # HTML 템플릿
├── package.json         # 프로젝트 설정 및 의존성
├── vite.config.js       # Vite 설정 파일
└── README.md            # 프로젝트 설명
```

### 5.4 주요 파일 설명

#### `index.html`
```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>React App</title>
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.jsx"></script>
  </body>
</html>
```

#### `src/main.jsx`
```jsx
import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
```

#### `src/App.jsx`
```jsx
import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <h1>React App</h1>
      <button onClick={() => setCount(count + 1)}>
        count is {count}
      </button>
    </div>
  )
}

export default App
```

---

## 6. 실습 과제

### 실습 1: 프로젝트 생성 및 실행

1. **새로운 React 프로젝트 생성**
   ```bash
   npm create vite@latest my-first-app -- --template react
   ```

2. **의존성 설치 및 실행**
   ```bash
   cd my-first-app
   npm install
   npm run dev
   ```

3. **브라우저에서 확인**
   - `http://localhost:5173/` 접속
   - 화면이 정상적으로 보이는지 확인

### 실습 2: 폴더 구조 탐색

1. **VSCode에서 프로젝트 열기**
   - File → Open Folder → `my-first-app` 선택

2. **각 폴더와 파일 확인**
   - `src/App.jsx` 파일 열어보기
   - `package.json` 파일 확인하기
   - `index.html` 파일 확인하기

3. **파일 수정해보기**
   - `src/App.jsx`에서 텍스트 변경
   - 저장하면 자동으로 화면이 업데이트되는지 확인

### 실습 3: 개발 서버 종료 및 재시작

1. **서버 종료**
   - 터미널에서 `Ctrl + C` 누르기

2. **서버 재시작**
   ```bash
   npm run dev
   ```

---

## 7. 자주 발생하는 문제 해결

### 문제 1: `npm: command not found`

**원인:** Node.js가 설치되지 않음

**해결:**
1. Node.js 공식 사이트에서 다운로드
2. 설치 후 터미널 재시작
3. `node --version`으로 확인

### 문제 2: 포트가 이미 사용 중

**에러 메시지:**
```
Port 5173 is already in use
```

**해결:**
```bash
# 다른 포트 사용
npm run dev -- --port 3000
```

### 문제 3: `npm install` 실패

**해결:**
```bash
# 캐시 삭제 후 재설치
npm cache clean --force
npm install
```

---

## 8. 다음 차시 예고

다음 차시에서는 **JSX 문법**을 배웁니다:
- JSX 기본 문법
- JavaScript 표현식 사용
- JSX와 HTML의 차이점

---

## 요약

### 핵심 개념

1. **React**: UI 구축을 위한 JavaScript 라이브러리
2. **SPA**: 페이지 새로고침 없이 화면만 변경
3. **Node.js**: JavaScript 실행 환경
4. **npm**: 패키지 관리자
5. **Vite**: 빠른 빌드 도구

### 필수 명령어

```bash
# 프로젝트 생성
npm create vite@latest 프로젝트명 -- --template react

# 의존성 설치
npm install

# 개발 서버 실행
npm run dev

# 빌드
npm run build
```

### 체크리스트

- [ ] React의 개념 이해
- [ ] SPA 개념 이해
- [ ] Node.js 및 npm 설치 확인
- [ ] React 프로젝트 생성 성공
- [ ] 개발 서버 실행 성공
- [ ] 폴더 구조 이해

---

**다음 차시에서 만나요! 🚀**

