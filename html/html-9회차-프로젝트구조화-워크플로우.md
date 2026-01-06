# 9회차 — 프로젝트 구조화 및 워크플로우

## 학습 목표
- 효율적인 프로젝트 폴더 구조를 설계할 수 있다
- HTML, CSS, 자산 파일을 적절히 분리할 수 있다
- npm scripts를 사용하여 빌드 자동화를 설정할 수 있다
- Git을 사용하여 버전 관리와 협업을 할 수 있다
- 코드 리팩토링과 스타일 가이드를 만들 수 있다

---

## 1. 프로젝트 구조 설계

### 1.1 좋은 프로젝트 구조의 원칙

**원칙:**
- 명확성: 구조만 봐도 프로젝트 이해 가능
- 확장성: 기능 추가 시 구조 변경 최소화
- 유지보수성: 파일 찾기 쉬움
- 일관성: 일관된 구조 유지

### 1.2 기본 프로젝트 구조

```
my-project/
├── index.html
├── about.html
├── contact.html
├── css/
│   ├── main.css
│   ├── components.css
│   └── utilities.css
├── js/
│   ├── main.js
│   └── utils.js
├── images/
│   ├── logo.png
│   └── hero.jpg
├── fonts/
│   └── custom-font.woff2
└── README.md
```

### 1.3 확장된 프로젝트 구조

```
my-project/
├── index.html
├── pages/
│   ├── about.html
│   ├── contact.html
│   └── blog/
│       └── post.html
├── assets/
│   ├── css/
│   │   ├── base/
│   │   │   ├── reset.css
│   │   │   └── typography.css
│   │   ├── components/
│   │   │   ├── button.css
│   │   │   ├── card.css
│   │   │   └── nav.css
│   │   ├── layouts/
│   │   │   ├── header.css
│   │   │   ├── footer.css
│   │   │   └── grid.css
│   │   └── main.css
│   ├── js/
│   │   ├── modules/
│   │   │   ├── api.js
│   │   │   └── utils.js
│   │   └── main.js
│   ├── images/
│   │   ├── icons/
│   │   ├── photos/
│   │   └── logos/
│   └── fonts/
│       └── custom-font.woff2
├── docs/
│   └── style-guide.md
├── .gitignore
├── package.json
└── README.md
```

### 1.4 구조 선택 기준

**작은 프로젝트 (1-5페이지):**
- 간단한 구조
- 파일 수 적음
- 빠른 개발

**중간 프로젝트 (5-20페이지):**
- 폴더 분리
- 컴포넌트별 CSS
- 모듈화

**큰 프로젝트 (20페이지 이상):**
- 세밀한 폴더 구조
- 컴포넌트 기반
- 빌드 도구 필요

---

## 2. HTML/CSS/자산 분리

### 2.1 HTML 구조

**기본 HTML 파일:**
```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>페이지 제목</title>
    <link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
    <!-- 콘텐츠 -->
    <script src="assets/js/main.js"></script>
</body>
</html>
```

### 2.2 CSS 분리 전략

#### 방법 1: 기능별 분리
```
css/
├── base.css        /* 기본 스타일 */
├── layout.css      /* 레이아웃 */
├── components.css  /* 컴포넌트 */
└── utilities.css   /* 유틸리티 */
```

#### 방법 2: 컴포넌트별 분리
```
css/
├── button.css
├── card.css
├── nav.css
└── main.css        /* 통합 */
```

#### 방법 3: BEM 기반 분리
```
css/
├── blocks/
│   ├── card.css
│   └── button.css
├── elements/
└── modifiers/
```

### 2.3 CSS 통합 방법

**main.css에서 통합:**
```css
/* main.css */
@import url('base/reset.css');
@import url('base/typography.css');
@import url('components/button.css');
@import url('components/card.css');
@import url('layouts/header.css');
```

**또는 HTML에서 직접:**
```html
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/components.css">
<link rel="stylesheet" href="css/layout.css">
```

### 2.4 자산 파일 관리

**이미지:**
```
images/
├── icons/          /* 아이콘 */
├── photos/         /* 사진 */
├── logos/          /* 로고 */
└── backgrounds/    /* 배경 이미지 */
```

**폰트:**
```
fonts/
├── custom-font.woff2
├── custom-font.woff
└── custom-font.ttf
```

**기타:**
```
assets/
├── videos/
├── documents/
└── data/
```

---

## 3. 빌드 및 자동화

### 3.1 npm 기본 설정

**package.json 생성:**
```json
{
  "name": "my-project",
  "version": "1.0.0",
  "description": "프로젝트 설명",
  "scripts": {
    "start": "live-server",
    "build": "npm run build:css && npm run build:js",
    "build:css": "postcss css/main.css -o dist/css/main.css",
    "build:js": "webpack"
  },
  "devDependencies": {
    "live-server": "^1.2.2"
  }
}
```

### 3.2 기본 npm Scripts

**개발 서버:**
```json
{
  "scripts": {
    "dev": "live-server --port=3000",
    "start": "npm run dev"
  }
}
```

**CSS 처리:**
```json
{
  "scripts": {
    "css:watch": "watch 'npm run css:build' css/",
    "css:build": "postcss css/main.css -o dist/css/main.css"
  }
}
```

**빌드:**
```json
{
  "scripts": {
    "build": "npm run clean && npm run build:css && npm run build:js",
    "clean": "rm -rf dist"
  }
}
```

### 3.3 간단한 빌드 도구

#### Live Server
```bash
npm install --save-dev live-server
```

```json
{
  "scripts": {
    "dev": "live-server"
  }
}
```

#### PostCSS (CSS 처리)
```bash
npm install --save-dev postcss-cli autoprefixer
```

```json
{
  "scripts": {
    "css:build": "postcss css/main.css -o dist/css/main.css --use autoprefixer"
  }
}
```

### 3.4 빌드 프로세스

**개발:**
1. 파일 수정
2. 자동 새로고침 (live-server)
3. 즉시 확인

**빌드:**
1. CSS 압축/최적화
2. JavaScript 번들링
3. 이미지 최적화
4. 배포 준비

---

## 4. Git 버전 관리

### 4.1 Git 기본 설정

**초기화:**
```bash
git init
git config user.name "Your Name"
git config user.email "your.email@example.com"
```

**.gitignore 파일:**
```
# 의존성
node_modules/
package-lock.json

# 빌드 결과
dist/
build/

# 운영체제
.DS_Store
Thumbs.db

# 에디터
.vscode/
.idea/
*.swp
```

### 4.2 기본 Git 워크플로우

**초기 커밋:**
```bash
git add .
git commit -m "Initial commit"
```

**변경사항 커밋:**
```bash
git add .
git commit -m "Add header component"
```

**상태 확인:**
```bash
git status
git log
```

### 4.3 브랜치 전략

#### 기본 브랜치
- `main` 또는 `master`: 프로덕션 코드
- `develop`: 개발 브랜치

#### 기능 브랜치
```bash
# 기능 브랜치 생성
git checkout -b feature/header-component

# 작업 후 커밋
git add .
git commit -m "Add header component"

# 메인 브랜치로 병합
git checkout main
git merge feature/header-component
```

#### 브랜치 네이밍
- `feature/`: 새로운 기능
- `fix/`: 버그 수정
- `hotfix/`: 긴급 수정
- `refactor/`: 리팩토링

### 4.4 커밋 규칙

#### 커밋 메시지 형식
```
타입(범위): 간단한 설명

상세 설명 (선택사항)
```

**타입:**
- `feat`: 새로운 기능
- `fix`: 버그 수정
- `docs`: 문서 수정
- `style`: 코드 포맷팅
- `refactor`: 리팩토링
- `test`: 테스트 추가
- `chore`: 빌드/설정 변경

**예시:**
```
feat(header): Add responsive navigation menu

- Add mobile hamburger menu
- Add desktop horizontal menu
- Add smooth transitions
```

### 4.5 협업 워크플로우

**원격 저장소 연결:**
```bash
git remote add origin https://github.com/user/repo.git
git push -u origin main
```

**협업 흐름:**
1. 원격 저장소에서 최신 코드 가져오기
   ```bash
   git pull origin main
   ```

2. 기능 브랜치 생성
   ```bash
   git checkout -b feature/new-feature
   ```

3. 작업 후 커밋
   ```bash
   git add .
   git commit -m "feat: Add new feature"
   ```

4. 원격 저장소에 푸시
   ```bash
   git push origin feature/new-feature
   ```

5. Pull Request 생성 (GitHub/GitLab)

6. 코드 리뷰 후 병합

---

## 5. 코드 리팩토링

### 5.1 리팩토링이란?

리팩토링은 코드의 기능은 유지하면서 구조를 개선하는 작업입니다.

**목적:**
- 코드 가독성 향상
- 유지보수성 향상
- 버그 감소
- 성능 최적화

### 5.2 리팩토링 시점

**리팩토링이 필요한 경우:**
- 중복 코드 발견
- 긴 함수/클래스
- 복잡한 조건문
- 매직 넘버/문자열
- 명확하지 않은 변수명

### 5.3 리팩토링 기법

#### 중복 코드 제거
```css
/* 리팩토링 전 */
.button-primary {
    padding: 10px 20px;
    border-radius: 5px;
    background-color: blue;
}

.button-secondary {
    padding: 10px 20px;
    border-radius: 5px;
    background-color: gray;
}
```

```css
/* 리팩토링 후 */
.button {
    padding: 10px 20px;
    border-radius: 5px;
}

.button--primary {
    background-color: blue;
}

.button--secondary {
    background-color: gray;
}
```

#### CSS 변수 활용
```css
/* 리패토링 전 */
.card {
    padding: 20px;
    margin: 20px;
    border-radius: 8px;
}

.button {
    padding: 20px;
    margin: 20px;
    border-radius: 8px;
}
```

```css
/* 리팩토링 후 */
:root {
    --spacing: 20px;
    --border-radius: 8px;
}

.card {
    padding: var(--spacing);
    margin: var(--spacing);
    border-radius: var(--border-radius);
}

.button {
    padding: var(--spacing);
    margin: var(--spacing);
    border-radius: var(--border-radius);
}
```

#### 컴포넌트 분리
```css
/* 리팩토링 전: 모든 스타일이 한 파일 */
/* main.css - 1000줄 */
```

```css
/* 리팩토링 후: 컴포넌트별 분리 */
/* components/button.css */
/* components/card.css */
/* components/nav.css */
```

### 5.4 리팩토링 체크리스트

- [ ] 중복 코드 제거
- [ ] 의미 있는 변수명 사용
- [ ] 함수/클래스 길이 적절한가?
- [ ] 주석이 명확한가?
- [ ] 일관된 코딩 스타일
- [ ] 불필요한 코드 제거
- [ ] 성능 최적화

---

## 6. 스타일 가이드

### 6.1 스타일 가이드란?

스타일 가이드는 프로젝트의 코딩 표준과 규칙을 정의한 문서입니다.

**목적:**
- 일관된 코드 스타일
- 협업 효율성 향상
- 유지보수 용이
- 온보딩 시간 단축

### 6.2 HTML 스타일 가이드

#### 들여쓰기
```html
<!-- 들여쓰기: 2칸 또는 4칸 -->
<div class="container">
  <header class="header">
    <h1>제목</h1>
  </header>
</div>
```

#### 속성 순서
```html
<!-- 권장 순서 -->
<a 
  class="link"
  id="main-link"
  href="#"
  target="_blank"
  rel="noopener"
  aria-label="링크 설명"
>
  링크
</a>
```

#### 시맨틱 태그 사용
```html
<!-- 좋은 예 -->
<header>
  <nav>
    <ul>
      <li><a href="#">메뉴</a></li>
    </ul>
  </nav>
</header>

<!-- 나쁜 예 -->
<div class="header">
  <div class="nav">
    <div class="menu">메뉴</div>
  </div>
</div>
```

### 6.3 CSS 스타일 가이드

#### 네이밍 규칙
```css
/* BEM 방법론 사용 */
.block { }
.block__element { }
.block--modifier { }
```

#### 속성 순서
```css
/* 권장 순서 */
.element {
    /* 위치 */
    position: relative;
    top: 0;
    left: 0;
    
    /* 박스 모델 */
    display: block;
    width: 100%;
    padding: 20px;
    margin: 10px;
    
    /* 시각 */
    background-color: #fff;
    color: #333;
    border: 1px solid #ddd;
    
    /* 타이포그래피 */
    font-size: 16px;
    line-height: 1.5;
    
    /* 기타 */
    transition: all 0.3s;
}
```

#### 주석 규칙
```css
/* ========================================
   섹션: 헤더
   ======================================== */

/* 컴포넌트: 버튼 */
.button { }

/* Modifier: Primary 버튼 */
.button--primary { }
```

### 6.4 파일 구조 규칙

```
프로젝트/
├── assets/
│   ├── css/
│   │   ├── base/        /* 기본 스타일 */
│   │   ├── components/  /* 컴포넌트 */
│   │   ├── layouts/     /* 레이아웃 */
│   │   └── utilities/   /* 유틸리티 */
│   └── js/
│       ├── modules/     /* 모듈 */
│       └── main.js      /* 진입점 */
```

### 6.5 스타일 가이드 문서 예시

```markdown
# 프로젝트 스타일 가이드

## HTML 규칙
- 들여쓰기: 2칸
- 속성 따옴표: 쌍따옴표
- 시맨틱 태그 사용

## CSS 규칙
- 네이밍: BEM 방법론
- 속성 순서: 위치 → 박스 → 시각 → 타이포 → 기타
- 들여쓰기: 2칸

## 파일 구조
- CSS: 컴포넌트별 분리
- JS: 모듈별 분리
```

---

## 7. 실전 워크플로우

### 7.1 개발 워크플로우

1. **프로젝트 초기화**
   ```bash
   mkdir my-project
   cd my-project
   git init
   npm init -y
   ```

2. **폴더 구조 생성**
   ```bash
   mkdir -p assets/{css,js,images}
   ```

3. **기본 파일 생성**
   - index.html
   - assets/css/main.css
   - assets/js/main.js
   - README.md
   - .gitignore

4. **개발 시작**
   ```bash
   npm run dev
   ```

5. **커밋**
   ```bash
   git add .
   git commit -m "feat: Initial project setup"
   ```

### 7.2 협업 워크플로우

1. **원격 저장소 클론**
   ```bash
   git clone https://github.com/user/repo.git
   cd repo
   ```

2. **기능 브랜치 생성**
   ```bash
   git checkout -b feature/new-feature
   ```

3. **개발 및 커밋**
   ```bash
   # 작업
   git add .
   git commit -m "feat: Add new feature"
   ```

4. **원격 저장소에 푸시**
   ```bash
   git push origin feature/new-feature
   ```

5. **Pull Request 생성**

6. **코드 리뷰 및 병합**

### 7.3 배포 워크플로우

1. **빌드**
   ```bash
   npm run build
   ```

2. **테스트**
   - 로컬 테스트
   - 스테이징 환경 테스트

3. **배포**
   - 프로덕션 환경에 배포
   - 모니터링

---

## 8. 실습 체크리스트

### 프로젝트 구조
- [ ] 명확한 폴더 구조를 설계했는가?
- [ ] HTML, CSS, 자산 파일을 적절히 분리했는가?
- [ ] 확장 가능한 구조인가?

### 빌드 및 자동화
- [ ] package.json을 설정했는가?
- [ ] npm scripts를 정의했는가?
- [ ] 개발 서버를 설정했는가?

### Git 버전 관리
- [ ] Git 저장소를 초기화했는가?
- [ ] .gitignore 파일을 설정했는가?
- [ ] 커밋 규칙을 따르고 있는가?
- [ ] 브랜치 전략을 이해하고 있는가?

### 코드 품질
- [ ] 코드 리팩토링을 수행했는가?
- [ ] 스타일 가이드를 작성했는가?
- [ ] 일관된 코딩 스타일을 유지하고 있는가?

---

## 다음 단계
- CSS 프레임워크 (Bootstrap, Tailwind)
- CSS 전처리기 (Sass, Less)
- 모던 빌드 도구 (Webpack, Vite)
- 실전 프로젝트


