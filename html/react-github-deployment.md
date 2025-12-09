# React 앱을 GitHub에 배포하는 방법

## 개요
React 앱을 GitHub에 배포하는 방법은 여러 가지가 있습니다. 가장 일반적인 방법은 **GitHub Pages**를 사용하는 것입니다.

## 사전 준비사항

1. **GitHub 계정** 및 저장소 생성
2. **React 프로젝트** 준비 완료
3. **Git** 설치 및 설정 완료

---

## 방법 1: GitHub Pages를 사용한 배포

### 1단계: React 프로젝트 설정

#### Create React App 사용 시

```bash
# gh-pages 패키지 설치
npm install --save-dev gh-pages
```

#### package.json 수정

```json
{
  "name": "my-react-app",
  "version": "0.1.0",
  "homepage": "https://[사용자명].github.io/[저장소명]",
  "scripts": {
    "predeploy": "npm run build",
    "deploy": "gh-pages -d build",
    "start": "react-scripts start",
    "build": "react-scripts build"
  }
}
```

**중요**: `homepage` 필드에 GitHub Pages URL을 정확히 입력해야 합니다.

### 2단계: GitHub 저장소 생성 및 연결

```bash
# Git 초기화 (아직 안 했다면)
git init

# GitHub 저장소와 연결
git remote add origin https://github.com/[사용자명]/[저장소명].git

# 파일 추가 및 커밋
git add .
git commit -m "Initial commit"

# 메인 브랜치에 푸시
git branch -M main
git push -u origin main
```

### 3단계: 배포 실행

```bash
# 배포 명령어 실행
npm run deploy
```

이 명령어는 자동으로:
1. `npm run build`를 실행하여 프로덕션 빌드 생성
2. `gh-pages` 브랜치에 빌드 파일 업로드
3. GitHub Pages에 자동 배포

### 4단계: GitHub Pages 설정 확인

1. GitHub 저장소로 이동
2. **Settings** → **Pages** 메뉴 클릭
3. **Source**에서 `gh-pages` 브랜치 선택
4. 저장 후 몇 분 후 `https://[사용자명].github.io/[저장소명]`에서 확인

---

## 방법 2: GitHub Actions를 사용한 자동 배포

### 1단계: GitHub Actions 워크플로우 파일 생성

프로젝트 루트에 `.github/workflows/deploy.yml` 파일 생성:

```yaml
name: Deploy to GitHub Pages

on:
  push:
    branches:
      - main

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest
    
    steps:
      - name: Checkout
        uses: actions/checkout@v3
      
      - name: Setup Node.js
        uses: actions/setup-node@v3
        with:
          node-version: '18'
          cache: 'npm'
      
      - name: Install dependencies
        run: npm ci
      
      - name: Build
        run: npm run build
      
      - name: Deploy to GitHub Pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: ./build
```

### 2단계: GitHub 저장소 설정

1. 저장소의 **Settings** → **Actions** → **General**
2. **Workflow permissions**에서 **Read and write permissions** 선택
3. 저장

### 3단계: 자동 배포 확인

`main` 브랜치에 푸시하면 자동으로 배포가 시작됩니다:
- **Actions** 탭에서 배포 진행 상황 확인
- 배포 완료 후 GitHub Pages URL에서 확인

---

## 방법 3: Vite를 사용한 React 프로젝트 배포

### 1단계: vite.config.js 설정

```javascript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  base: '/[저장소명]/'  // GitHub Pages 서브 경로용
})
```

### 2단계: package.json 설정

```json
{
  "scripts": {
    "build": "vite build",
    "preview": "vite preview",
    "deploy": "npm run build && gh-pages -d dist"
  },
  "homepage": "https://[사용자명].github.io/[저장소명]"
}
```

### 3단계: 배포

```bash
npm install --save-dev gh-pages
npm run deploy
```

---

## 주의사항 및 문제 해결

### 1. 라우팅 문제 (React Router 사용 시)

GitHub Pages는 SPA(Single Page Application)를 완벽히 지원하지 않습니다. 
`404.html` 파일을 생성하여 해결:

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>React App</title>
    <script>
      var path = window.location.pathname;
      if (path !== '/' && path.slice(-1) !== '/') {
        window.location = path + '/';
      }
    </script>
  </head>
  <body>
    <script>
      var redirect = sessionStorage.redirect;
      delete sessionStorage.redirect;
      if (redirect && redirect !== location.href) {
        history.replaceState(null, null, redirect);
      }
    </script>
    <div id="root"></div>
    <script src="/[저장소명]/static/js/bundle.js"></script>
  </body>
</html>
```

또는 `HashRouter` 사용:

```javascript
import { HashRouter } from 'react-router-dom';

function App() {
  return (
    <HashRouter>
      {/* 라우트 설정 */}
    </HashRouter>
  );
}
```

### 2. 환경 변수 설정

`.env.production` 파일 생성:

```
REACT_APP_API_URL=https://api.example.com
```

### 3. 이미지 경로 문제

`public` 폴더의 이미지는 절대 경로로 참조:

```jsx
<img src="/[저장소명]/images/logo.png" alt="Logo" />
```

---

## 배포 후 확인 사항

- [ ] GitHub Pages URL에서 앱이 정상적으로 로드되는지 확인
- [ ] 모든 라우트가 정상 작동하는지 확인
- [ ] 이미지 및 정적 파일이 올바르게 로드되는지 확인
- [ ] 모바일 반응형이 정상 작동하는지 확인
- [ ] 콘솔에 에러가 없는지 확인

---

## 추가 리소스

- [GitHub Pages 공식 문서](https://docs.github.com/en/pages)
- [gh-pages 패키지](https://github.com/tschaub/gh-pages)
- [React 공식 문서 - 배포](https://react.dev/learn/start-a-new-react-project#production-builds)

---

## 요약

1. **gh-pages 패키지 설치** 및 `package.json` 설정
2. **GitHub 저장소 생성** 및 코드 푸시
3. **npm run deploy** 실행
4. **GitHub Pages 설정** 확인
5. 배포 완료 후 **URL에서 확인**

이 과정을 통해 React 앱을 무료로 GitHub Pages에 배포할 수 있습니다!



