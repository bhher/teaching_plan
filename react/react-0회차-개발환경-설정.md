# React 0회차 — 개발환경 설정

## 학습 목표
- Node.js를 설치하고 npm을 사용할 수 있다
- VSCode를 React 개발에 최적화하여 설정할 수 있다
- Vite를 사용하여 React 프로젝트를 생성하고 실행할 수 있다
- 필요한 의존성 패키지를 설치하고 관리할 수 있다
- 개발 환경을 완전히 구축할 수 있다

---

## 1. Node.js 설치

### 1.1 Node.js란?

Node.js는 JavaScript를 서버 사이드에서 실행할 수 있게 해주는 런타임 환경입니다.

**주요 특징:**
- JavaScript로 서버 개발 가능
- npm (Node Package Manager) 포함
- 크로스 플랫폼 (Windows, macOS, Linux)

### 1.2 Node.js 설치 방법

#### Windows

1. **공식 웹사이트에서 다운로드**
   - https://nodejs.org 접속
   - LTS (Long Term Support) 버전 다운로드 권장
   - 현재 권장 버전: v20.x.x 이상

2. **설치 프로그램 실행**
   - 다운로드한 `.msi` 파일 실행
   - "Next" 클릭하여 설치 진행
   - 기본 설정으로 설치 권장

3. **설치 확인**
   ```bash
   node --version
   npm --version
   ```

#### macOS

1. **Homebrew 사용 (권장)**
   ```bash
   brew install node
   ```

2. **또는 공식 웹사이트에서 다운로드**
   - https://nodejs.org 접속
   - macOS용 설치 파일 다운로드
   - `.pkg` 파일 실행하여 설치

3. **설치 확인**
   ```bash
   node --version
   npm --version
   ```

#### Linux (Ubuntu/Debian)

```bash
# NodeSource 저장소 추가
curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash -

# Node.js 설치
sudo apt-get install -y nodejs

# 설치 확인
node --version
npm --version
```

### 1.3 설치 확인

터미널(또는 명령 프롬프트)에서 다음 명령어 실행:

```bash
# Node.js 버전 확인
node --version
# 예상 출력: v20.x.x

# npm 버전 확인
npm --version
# 예상 출력: 10.x.x
```

### 1.4 npm 업데이트

```bash
# npm 최신 버전으로 업데이트
npm install -g npm@latest

# 업데이트 확인
npm --version
```

---

## 2. VSCode 설치 및 설정

### 2.1 VSCode 설치

1. **공식 웹사이트에서 다운로드**
   - https://code.visualstudio.com 접속
   - 운영체제에 맞는 버전 다운로드

2. **설치**
   - Windows: `.exe` 파일 실행
   - macOS: `.zip` 파일 압축 해제 후 Applications 폴더로 이동
   - Linux: `.deb` 또는 `.rpm` 파일 설치

### 2.2 필수 확장 프로그램

#### 필수 확장 프로그램 목록

1. **ES7+ React/Redux/React-Native snippets**
   - 확장 ID: `dsznajder.es7-react-js-snippets`
   - React 코드 스니펫 제공

2. **Prettier - Code formatter**
   - 확장 ID: `esbenp.prettier-vscode`
   - 코드 자동 포맷팅

3. **ESLint**
   - 확장 ID: `dbaeumer.vscode-eslint`
   - 코드 린팅

4. **Auto Rename Tag**
   - 확장 ID: `formulahendry.auto-rename-tag`
   - 태그 자동 이름 변경

5. **Bracket Pair Colorizer 2** (또는 기본 기능 사용)
   - 확장 ID: `coenraads.bracket-pair-colorizer-2`
   - 괄호 색상 구분

6. **GitLens**
   - 확장 ID: `eamodio.gitlens`
   - Git 기능 강화

7. **Live Server** (선택사항)
   - 확장 ID: `ritwickdey.liveserver`
   - 로컬 서버 실행

#### 확장 프로그램 설치 방법

1. **VSCode에서 설치**
   - `Ctrl+Shift+X` (Windows/Linux) 또는 `Cmd+Shift+X` (macOS)
   - 검색창에 확장 프로그램 이름 입력
   - "Install" 클릭

2. **명령어로 설치 (선택사항)**
   ```bash
   code --install-extension dsznajder.es7-react-js-snippets
   code --install-extension esbenp.prettier-vscode
   code --install-extension dbaeumer.vscode-eslint
   ```

### 2.3 VSCode 설정

#### settings.json 설정

`Ctrl+Shift+P` (또는 `Cmd+Shift+P`) → "Preferences: Open Settings (JSON)" 선택

```json
{
  // 파일 저장 시 자동 포맷팅
  "editor.formatOnSave": true,
  "editor.defaultFormatter": "esbenp.prettier-vscode",
  
  // 탭 크기
  "editor.tabSize": 2,
  "editor.insertSpaces": true,
  
  // 자동 저장
  "files.autoSave": "afterDelay",
  "files.autoSaveDelay": 1000,
  
  // 파일 인코딩
  "files.encoding": "utf8",
  
  // 줄 끝 문자
  "files.eol": "\n",
  
  // ESLint 설정
  "eslint.validate": [
    "javascript",
    "javascriptreact",
    "typescript",
    "typescriptreact"
  ],
  
  // Emmet 설정
  "emmet.includeLanguages": {
    "javascript": "javascriptreact"
  },
  
  // 파일 제외
  "files.exclude": {
    "**/.git": true,
    "**/.DS_Store": true,
    "**/node_modules": true
  },
  
  // 검색 제외
  "search.exclude": {
    "**/node_modules": true,
    "**/dist": true,
    "**/build": true
  }
}
```

### 2.4 유용한 단축키

#### 일반 단축키
- `Ctrl+P` (Windows/Linux) / `Cmd+P` (macOS): 파일 빠른 열기
- `Ctrl+Shift+P` / `Cmd+Shift+P`: 명령 팔레트
- `Ctrl+\` / `Cmd+\`: 편집기 분할
- `Ctrl+B` / `Cmd+B`: 사이드바 토글

#### 편집 단축키
- `Ctrl+D` / `Cmd+D`: 다음 일치 항목 선택
- `Alt+↑/↓` / `Option+↑/↓`: 줄 이동
- `Shift+Alt+↑/↓` / `Shift+Option+↑/↓`: 줄 복사
- `Ctrl+/` / `Cmd+/`: 주석 토글

#### React 개발 단축키
- `rafce`: 함수 컴포넌트 스니펫 (ES7+ React snippets)
- `rafc`: 클래스 컴포넌트 스니펫
- `useState`: useState 훅 스니펫
- `useEffect`: useEffect 훅 스니펫

---

## 3. Vite 사용법

### 3.1 Vite란?

Vite는 빠른 개발 서버와 빌드 도구를 제공하는 프론트엔드 빌드 도구입니다.

**주요 특징:**
- 매우 빠른 개발 서버
- 즉시 Hot Module Replacement (HMR)
- 최적화된 프로덕션 빌드
- TypeScript 지원

### 3.2 Vite로 React 프로젝트 생성

#### 방법 1: npm create (권장)

```bash
# React 프로젝트 생성
npm create vite@latest my-react-app -- --template react

# 프로젝트 폴더로 이동
cd my-react-app

# 의존성 설치
npm install

# 개발 서버 실행
npm run dev
```

#### 방법 2: yarn 사용

```bash
# yarn 설치 (선택사항)
npm install -g yarn

# React 프로젝트 생성
yarn create vite my-react-app --template react

# 의존성 설치
cd my-react-app
yarn install

# 개발 서버 실행
yarn dev
```

#### 방법 3: pnpm 사용

```bash
# pnpm 설치 (선택사항)
npm install -g pnpm

# React 프로젝트 생성
pnpm create vite my-react-app --template react

# 의존성 설치
cd my-react-app
pnpm install

# 개발 서버 실행
pnpm dev
```

### 3.3 프로젝트 구조

Vite로 생성된 React 프로젝트 구조:

```
my-react-app/
├── node_modules/          # 의존성 패키지
├── public/                 # 정적 파일
│   └── vite.svg
├── src/                    # 소스 코드
│   ├── assets/            # 이미지, 폰트 등
│   ├── App.jsx            # 메인 컴포넌트
│   ├── App.css            # App 스타일
│   ├── index.css          # 전역 스타일
│   └── main.jsx           # 진입점
├── .gitignore             # Git 제외 파일
├── index.html             # HTML 템플릿
├── package.json           # 프로젝트 설정
├── vite.config.js         # Vite 설정 파일
└── README.md              # 프로젝트 설명
```

### 3.4 Vite 명령어

```bash
# 개발 서버 실행
npm run dev

# 프로덕션 빌드
npm run build

# 빌드 미리보기
npm run preview

# 린트 실행 (설정 시)
npm run lint
```

### 3.5 Vite 설정 (vite.config.js)

```javascript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,        // 개발 서버 포트
    open: true,        // 브라우저 자동 열기
  },
  build: {
    outDir: 'dist',    // 빌드 출력 디렉토리
    sourcemap: true,   // 소스맵 생성
  },
})
```

---

## 4. React 기본 사용법

### 4.1 React 컴포넌트

#### 함수 컴포넌트 (권장)

```jsx
// App.jsx
import './App.css'

function App() {
  return (
    <div className="App">
      <h1>Hello React!</h1>
    </div>
  )
}

export default App
```

#### JSX 문법

```jsx
function App() {
  const name = "React"
  const isLoggedIn = true
  
  return (
    <div>
      {/* 주석 */}
      <h1>Hello {name}!</h1>
      {isLoggedIn && <p>로그인되었습니다</p>}
      {isLoggedIn ? <p>환영합니다</p> : <p>로그인하세요</p>}
    </div>
  )
}
```

### 4.2 React Hooks

#### useState

```jsx
import { useState } from 'react'

function Counter() {
  const [count, setCount] = useState(0)
  
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>
        증가
      </button>
    </div>
  )
}
```

#### useEffect

```jsx
import { useState, useEffect } from 'react'

function App() {
  const [data, setData] = useState(null)
  
  useEffect(() => {
    // 컴포넌트 마운트 시 실행
    fetch('/api/data')
      .then(res => res.json())
      .then(data => setData(data))
    
    // 클린업 함수 (선택사항)
    return () => {
      console.log('컴포넌트 언마운트')
    }
  }, []) // 빈 배열 = 마운트 시 한 번만 실행
  
  return <div>{data && <p>{data.message}</p>}</div>
}
```

### 4.3 컴포넌트 Props

```jsx
// 부모 컴포넌트
function App() {
  return (
    <div>
      <Greeting name="React" age={10} />
    </div>
  )
}

// 자식 컴포넌트
function Greeting({ name, age }) {
  return (
    <div>
      <h1>Hello, {name}!</h1>
      <p>나이: {age}</p>
    </div>
  )
}
```

### 4.4 이벤트 처리

```jsx
function Button() {
  const handleClick = () => {
    alert('버튼 클릭!')
  }
  
  const handleChange = (e) => {
    console.log(e.target.value)
  }
  
  return (
    <div>
      <button onClick={handleClick}>클릭</button>
      <input onChange={handleChange} />
    </div>
  )
}
```

---

## 5. 필요한 의존성 패키지

### 5.1 필수 패키지

Vite로 생성된 프로젝트에는 이미 포함되어 있습니다:

```json
{
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0"
  },
  "devDependencies": {
    "@types/react": "^18.2.0",
    "@types/react-dom": "^18.2.0",
    "@vitejs/plugin-react": "^4.0.0",
    "vite": "^4.4.0"
  }
}
```

### 5.2 유용한 추가 패키지

#### 라우팅
```bash
npm install react-router-dom
```

#### 상태 관리
```bash
# Redux
npm install @reduxjs/toolkit react-redux

# Zustand (가벼운 상태 관리)
npm install zustand
```

#### HTTP 클라이언트
```bash
# Axios
npm install axios

# 또는 Fetch API 사용 (내장)
```

#### 스타일링
```bash
# CSS Modules (내장)
# styled-components
npm install styled-components

# Tailwind CSS
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p
```

#### 폼 관리
```bash
# React Hook Form
npm install react-hook-form

# Formik
npm install formik
```

#### 유틸리티
```bash
# 날짜 처리
npm install date-fns

# 아이콘
npm install react-icons

# 애니메이션
npm install framer-motion
```

### 5.3 개발 도구

```bash
# ESLint 설정
npm install -D eslint eslint-plugin-react eslint-plugin-react-hooks

# Prettier 설정
npm install -D prettier eslint-config-prettier eslint-plugin-prettier

# TypeScript (선택사항)
npm install -D typescript @types/node
```

### 5.4 package.json 예시

```json
{
  "name": "my-react-app",
  "private": true,
  "version": "0.0.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview",
    "lint": "eslint . --ext js,jsx --report-unused-disable-directives --max-warnings 0"
  },
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-router-dom": "^6.20.0",
    "axios": "^1.6.0"
  },
  "devDependencies": {
    "@types/react": "^18.2.0",
    "@types/react-dom": "^18.2.0",
    "@vitejs/plugin-react": "^4.0.0",
    "eslint": "^8.53.0",
    "eslint-plugin-react": "^7.33.2",
    "eslint-plugin-react-hooks": "^4.6.0",
    "vite": "^4.4.0"
  }
}
```

---

## 6. 프로젝트 설정

### 6.1 ESLint 설정

`.eslintrc.cjs` 파일 생성:

```javascript
module.exports = {
  root: true,
  env: { browser: true, es2020: true },
  extends: [
    'eslint:recommended',
    'plugin:react/recommended',
    'plugin:react/jsx-runtime',
    'plugin:react-hooks/recommended',
  ],
  ignorePatterns: ['dist', '.eslintrc.cjs'],
  parserOptions: { ecmaVersion: 'latest', sourceType: 'module' },
  settings: { react: { version: '18.2' } },
  plugins: ['react-refresh'],
  rules: {
    'react-refresh/only-export-components': [
      'warn',
      { allowConstantExport: true },
    ],
  },
}
```

### 6.2 Prettier 설정

`.prettierrc` 파일 생성:

```json
{
  "semi": false,
  "singleQuote": true,
  "tabWidth": 2,
  "trailingComma": "es5",
  "printWidth": 80,
  "arrowParens": "avoid"
}
```

### 6.3 .gitignore 설정

```
# Logs
logs
*.log
npm-debug.log*
yarn-debug.log*
yarn-error.log*
pnpm-debug.log*
lerna-debug.log*

node_modules
dist
dist-ssr
*.local

# Editor directories and files
.vscode/*
!.vscode/extensions.json
.idea
.DS_Store
*.suo
*.ntvs*
*.njsproj
*.sln
*.sw?
```

---

## 7. 개발 워크플로우

### 7.1 프로젝트 생성부터 실행까지

```bash
# 1. 프로젝트 생성
npm create vite@latest my-app -- --template react

# 2. 프로젝트 폴더로 이동
cd my-app

# 3. 의존성 설치
npm install

# 4. 개발 서버 실행
npm run dev

# 5. 브라우저에서 http://localhost:5173 접속
```

### 7.2 개발 중

```bash
# 개발 서버 실행 (터미널 1)
npm run dev

# 다른 터미널에서 추가 작업 가능
# 예: 패키지 설치
npm install axios
```

### 7.3 빌드 및 배포

```bash
# 프로덕션 빌드
npm run build

# 빌드 결과 미리보기
npm run preview

# dist 폴더가 생성됨 (배포할 파일)
```

---

## 8. 문제 해결

### 8.1 일반적인 문제

#### 포트가 이미 사용 중
```bash
# vite.config.js에서 포트 변경
server: {
  port: 3001
}
```

#### node_modules 오류
```bash
# node_modules 삭제 후 재설치
rm -rf node_modules
npm install
```

#### 캐시 문제
```bash
# npm 캐시 삭제
npm cache clean --force

# Vite 캐시 삭제
rm -rf node_modules/.vite
```

### 8.2 버전 호환성

- Node.js: v18 이상 권장
- npm: v9 이상 권장
- React: v18.2 이상

---

## 9. 실습 체크리스트

### 설치 확인
- [ ] Node.js가 설치되었는가?
- [ ] npm이 설치되었는가?
- [ ] VSCode가 설치되었는가?

### VSCode 설정
- [ ] 필수 확장 프로그램이 설치되었는가?
- [ ] settings.json이 설정되었는가?
- [ ] 단축키를 익혔는가?

### 프로젝트 생성
- [ ] Vite로 프로젝트를 생성했는가?
- [ ] 개발 서버가 실행되는가?
- [ ] 브라우저에서 접속되는가?

### 개발 환경
- [ ] 필요한 패키지를 설치했는가?
- [ ] ESLint가 작동하는가?
- [ ] Prettier가 작동하는가?

---

## 다음 단계
- React 컴포넌트 기초
- Props와 State
- React Hooks
- 이벤트 처리
- 조건부 렌더링


