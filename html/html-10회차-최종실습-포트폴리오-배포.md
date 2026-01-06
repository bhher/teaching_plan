# 10회차 — 최종 실습(포트폴리오 페이지 제작) 및 배포

## 학습 목표
- 기획부터 배포까지 전체 웹 개발 프로세스를 경험할 수 있다
- HTML, CSS, JavaScript를 종합적으로 활용할 수 있다
- 반응형 웹 디자인을 완성도 있게 구현할 수 있다
- 폼 유효성 검사와 시각적 피드백을 구현할 수 있다
- 정적 사이트를 배포할 수 있다
- 유지보수 체크리스트를 이해하고 적용할 수 있다

---

## 1. 프로젝트 기획

### 1.1 목표 설정

**포트폴리오 사이트 목표:**
- 나를 소개하는 개인 포트폴리오
- 프로젝트를 보여주는 갤러리
- 연락할 수 있는 연락처 폼
- 반응형 디자인 적용
- 접근성 고려

### 1.2 사이트 구조 설계

**페이지 구성:**
```
포트폴리오 사이트
├── 홈 (index.html)
│   ├── 히어로 섹션
│   ├── 소개 섹션
│   ├── 스킬 섹션
│   └── 프로젝트 미리보기
├── 프로젝트 (projects.html)
│   └── 프로젝트 갤러리
└── 연락처 (contact.html)
    └── 연락 폼
```

### 1.3 디자인 컨셉

**고려사항:**
- 색상 팔레트 선택
- 타이포그래피 선택
- 레이아웃 스타일
- 인터랙션 요소

**디자인 도구:**
- 펜과 종이 (스케치)
- Figma, Adobe XD
- 브라우저 개발자 도구

### 1.4 콘텐츠 준비

**필요한 콘텐츠:**
- 자기소개 텍스트
- 프로젝트 설명 및 이미지
- 스킬 목록
- 연락처 정보
- 프로필 사진

---

## 2. 마크업 (HTML)

### 2.1 기본 구조

**index.html 기본 구조:**
```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="포트폴리오 사이트 설명">
    <title>포트폴리오 | 홈</title>
    <link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
    <!-- 헤더 -->
    <header class="header">
        <nav class="nav">
            <!-- 네비게이션 -->
        </nav>
    </header>

    <!-- 메인 콘텐츠 -->
    <main>
        <!-- 히어로 섹션 -->
        <section class="hero">
            <!-- 히어로 콘텐츠 -->
        </section>

        <!-- 소개 섹션 -->
        <section class="about">
            <!-- 소개 콘텐츠 -->
        </section>

        <!-- 스킬 섹션 -->
        <section class="skills">
            <!-- 스킬 목록 -->
        </section>

        <!-- 프로젝트 섹션 -->
        <section class="projects">
            <!-- 프로젝트 미리보기 -->
        </section>
    </main>

    <!-- 푸터 -->
    <footer class="footer">
        <!-- 푸터 콘텐츠 -->
    </footer>

    <script src="assets/js/main.js"></script>
</body>
</html>
```

### 2.2 시맨틱 HTML 사용

**시맨틱 태그 활용:**
```html
<header>     <!-- 헤더 -->
<nav>        <!-- 네비게이션 -->
<main>       <!-- 메인 콘텐츠 -->
<section>    <!-- 섹션 -->
<article>    <!-- 독립적인 콘텐츠 -->
<aside>      <!-- 사이드바 -->
<footer>     <!-- 푸터 -->
```

### 2.3 접근성 고려

**접근성 요소:**
- alt 속성 (이미지)
- aria-label (아이콘 버튼)
- 시맨틱 태그
- 적절한 제목 구조 (h1-h6)
- 스킵 링크

```html
<!-- 스킵 링크 -->
<a href="#main-content" class="skip-link">메인 콘텐츠로 건너뛰기</a>

<!-- 이미지 alt -->
<img src="profile.jpg" alt="프로필 사진">

<!-- 아이콘 버튼 -->
<button aria-label="메뉴 열기">
    <span class="icon-menu"></span>
</button>
```

---

## 3. 스타일링 (CSS)

### 3.1 CSS 구조

**파일 구조:**
```
assets/css/
├── base/
│   ├── reset.css
│   ├── typography.css
│   └── variables.css
├── components/
│   ├── button.css
│   ├── card.css
│   └── form.css
├── layouts/
│   ├── header.css
│   ├── footer.css
│   └── grid.css
└── main.css
```

### 3.2 CSS 변수 활용

```css
:root {
    /* 색상 */
    --primary-color: #667eea;
    --secondary-color: #764ba2;
    --text-color: #2c3e50;
    --bg-color: #ffffff;
    --light-bg: #f5f5f5;
    
    /* 간격 */
    --spacing-unit: 8px;
    --spacing-small: calc(var(--spacing-unit) * 1);
    --spacing-medium: calc(var(--spacing-unit) * 2);
    --spacing-large: calc(var(--spacing-unit) * 4);
    
    /* 타이포그래피 */
    --font-size-base: 16px;
    --font-size-h1: 3em;
    --font-size-h2: 2em;
    
    /* 그림자 */
    --shadow-small: 0 2px 5px rgba(0,0,0,0.1);
    --shadow-medium: 0 5px 15px rgba(0,0,0,0.2);
}
```

### 3.3 BEM 네이밍

```css
/* Block */
.header { }
.card { }
.button { }

/* Element */
.header__logo { }
.card__title { }
.button__icon { }

/* Modifier */
.button--primary { }
.card--highlighted { }
```

### 3.4 애니메이션 적용

```css
@keyframes fadeInUp {
    from {
        opacity: 0;
        transform: translateY(30px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.hero__title {
    animation: fadeInUp 1s ease-out;
}
```

---

## 4. 반응형 보완

### 4.1 모바일 우선 설계

```css
/* 기본: 모바일 */
.container {
    width: 100%;
    padding: var(--spacing-medium);
}

/* 태블릿 */
@media (min-width: 768px) {
    .container {
        width: 750px;
        margin: 0 auto;
        padding: var(--spacing-large);
    }
}

/* 데스크톱 */
@media (min-width: 1024px) {
    .container {
        width: 1200px;
    }
}
```

### 4.2 반응형 네비게이션

```css
/* 모바일: 햄버거 메뉴 */
.nav__menu {
    display: none;
}

.nav__toggle {
    display: block;
}

/* 태블릿 이상: 가로 메뉴 */
@media (min-width: 768px) {
    .nav__menu {
        display: flex;
    }
    
    .nav__toggle {
        display: none;
    }
}
```

### 4.3 반응형 그리드

```css
.project-grid {
    display: grid;
    grid-template-columns: 1fr;
    gap: var(--spacing-medium);
}

@media (min-width: 768px) {
    .project-grid {
        grid-template-columns: repeat(2, 1fr);
    }
}

@media (min-width: 1024px) {
    .project-grid {
        grid-template-columns: repeat(3, 1fr);
    }
}
```

### 4.4 반응형 이미지

```css
img {
    max-width: 100%;
    height: auto;
}

.hero__image {
    width: 100%;
    height: auto;
    object-fit: cover;
}
```

---

## 5. 폼과 상호작용

### 5.1 폼 기본 구조

```html
<form class="contact-form" id="contactForm">
    <div class="form-group">
        <label for="name">이름</label>
        <input 
            type="text" 
            id="name" 
            name="name" 
            required
            aria-required="true"
        >
        <span class="error-message" id="nameError"></span>
    </div>
    
    <div class="form-group">
        <label for="email">이메일</label>
        <input 
            type="email" 
            id="email" 
            name="email" 
            required
            aria-required="true"
        >
        <span class="error-message" id="emailError"></span>
    </div>
    
    <div class="form-group">
        <label for="message">메시지</label>
        <textarea 
            id="message" 
            name="message" 
            rows="5"
            required
            aria-required="true"
        ></textarea>
        <span class="error-message" id="messageError"></span>
    </div>
    
    <button type="submit" class="button button--primary">
        전송
    </button>
</form>
```

### 5.2 유효성 검사 (JavaScript)

```javascript
// 폼 유효성 검사
const form = document.getElementById('contactForm');
const nameInput = document.getElementById('name');
const emailInput = document.getElementById('email');
const messageInput = document.getElementById('message');

// 실시간 유효성 검사
nameInput.addEventListener('blur', () => {
    validateName();
});

emailInput.addEventListener('blur', () => {
    validateEmail();
});

messageInput.addEventListener('blur', () => {
    validateMessage();
});

// 제출 시 검사
form.addEventListener('submit', (e) => {
    e.preventDefault();
    
    if (validateForm()) {
        // 폼 제출
        submitForm();
    }
});

// 유효성 검사 함수
function validateName() {
    const name = nameInput.value.trim();
    const errorElement = document.getElementById('nameError');
    
    if (name === '') {
        showError('nameError', '이름을 입력해주세요.');
        return false;
    } else if (name.length < 2) {
        showError('nameError', '이름은 2자 이상 입력해주세요.');
        return false;
    } else {
        hideError('nameError');
        nameInput.classList.add('valid');
        return true;
    }
}

function validateEmail() {
    const email = emailInput.value.trim();
    const errorElement = document.getElementById('emailError');
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    
    if (email === '') {
        showError('emailError', '이메일을 입력해주세요.');
        return false;
    } else if (!emailRegex.test(email)) {
        showError('emailError', '올바른 이메일 형식이 아닙니다.');
        return false;
    } else {
        hideError('emailError');
        emailInput.classList.add('valid');
        return true;
    }
}

function validateMessage() {
    const message = messageInput.value.trim();
    
    if (message === '') {
        showError('messageError', '메시지를 입력해주세요.');
        return false;
    } else if (message.length < 10) {
        showError('messageError', '메시지는 10자 이상 입력해주세요.');
        return false;
    } else {
        hideError('messageError');
        messageInput.classList.add('valid');
        return true;
    }
}

function validateForm() {
    const isNameValid = validateName();
    const isEmailValid = validateEmail();
    const isMessageValid = validateMessage();
    
    return isNameValid && isEmailValid && isMessageValid;
}

// 에러 표시/숨김
function showError(errorId, message) {
    const errorElement = document.getElementById(errorId);
    errorElement.textContent = message;
    errorElement.style.display = 'block';
    
    const input = errorElement.previousElementSibling;
    input.classList.add('error');
    input.classList.remove('valid');
}

function hideError(errorId) {
    const errorElement = document.getElementById(errorId);
    errorElement.style.display = 'none';
    
    const input = errorElement.previousElementSibling;
    input.classList.remove('error');
}

// 폼 제출
function submitForm() {
    // 폼 데이터 수집
    const formData = {
        name: nameInput.value.trim(),
        email: emailInput.value.trim(),
        message: messageInput.value.trim()
    };
    
    // 성공 메시지
    showSuccessMessage('메시지가 성공적으로 전송되었습니다!');
    
    // 폼 리셋
    form.reset();
    
    // 실제로는 서버로 전송
    // fetch('/api/contact', {
    //     method: 'POST',
    //     body: JSON.stringify(formData)
    // });
}

function showSuccessMessage(message) {
    const successDiv = document.createElement('div');
    successDiv.className = 'success-message';
    successDiv.textContent = message;
    form.insertBefore(successDiv, form.firstChild);
    
    setTimeout(() => {
        successDiv.remove();
    }, 5000);
}
```

### 5.3 시각적 피드백 (CSS)

```css
/* 입력 필드 기본 스타일 */
.form-group input,
.form-group textarea {
    width: 100%;
    padding: var(--spacing-small);
    border: 2px solid #ddd;
    border-radius: var(--border-radius);
    font-size: var(--font-size-base);
    transition: border-color 0.3s, box-shadow 0.3s;
}

/* 포커스 상태 */
.form-group input:focus,
.form-group textarea:focus {
    outline: none;
    border-color: var(--primary-color);
    box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

/* 에러 상태 */
.form-group input.error,
.form-group textarea.error {
    border-color: #e74c3c;
    box-shadow: 0 0 0 3px rgba(231, 76, 60, 0.1);
}

/* 유효 상태 */
.form-group input.valid,
.form-group textarea.valid {
    border-color: #27ae60;
    box-shadow: 0 0 0 3px rgba(39, 174, 96, 0.1);
}

/* 에러 메시지 */
.error-message {
    display: none;
    color: #e74c3c;
    font-size: 0.9em;
    margin-top: 5px;
}

.error-message.show {
    display: block;
}

/* 성공 메시지 */
.success-message {
    background-color: #27ae60;
    color: white;
    padding: var(--spacing-medium);
    border-radius: var(--border-radius);
    margin-bottom: var(--spacing-medium);
    animation: slideDown 0.3s ease-out;
}

@keyframes slideDown {
    from {
        opacity: 0;
        transform: translateY(-10px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}
```

---

## 6. 정적 사이트 배포

### 6.1 GitHub Pages 배포

#### 방법 1: 저장소 설정

1. **GitHub 저장소 생성**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/username/portfolio.git
   git push -u origin main
   ```

2. **GitHub Pages 활성화**
   - 저장소 Settings → Pages
   - Source: main 브랜치 선택
   - Save 클릭

3. **접속 URL**
   - `https://username.github.io/portfolio/`

#### 방법 2: gh-pages 브랜치

```bash
# gh-pages 브랜치 생성
git checkout -b gh-pages

# 파일 푸시
git push origin gh-pages
```

### 6.2 Vercel 배포

#### 방법 1: Vercel CLI

1. **Vercel CLI 설치**
   ```bash
   npm install -g vercel
   ```

2. **배포**
   ```bash
   vercel
   ```

3. **프로덕션 배포**
   ```bash
   vercel --prod
   ```

#### 방법 2: GitHub 연동

1. **Vercel 웹사이트 접속**
   - https://vercel.com

2. **GitHub 연동**
   - GitHub 계정으로 로그인
   - 저장소 선택

3. **자동 배포**
   - main 브랜치에 푸시하면 자동 배포

### 6.3 Netlify 배포

1. **Netlify 웹사이트 접속**
   - https://www.netlify.com

2. **드래그 앤 드롭**
   - 빌드된 파일을 드래그 앤 드롭

3. **Git 연동**
   - GitHub 저장소 연동
   - 자동 배포 설정

### 6.4 배포 전 체크리스트

- [ ] 모든 파일이 올바른 경로에 있는가?
- [ ] 이미지 경로가 상대 경로인가?
- [ ] CSS/JS 파일 경로가 올바른가?
- [ ] 모든 링크가 작동하는가?
- [ ] 반응형이 모든 기기에서 작동하는가?
- [ ] 폼이 정상 작동하는가?

---

## 7. 유지보수 체크리스트

### 7.1 접근성 체크리스트

- [ ] 색상 대비가 충분한가? (최소 4.5:1)
- [ ] 포커스 스타일이 명확한가?
- [ ] 키보드만으로 모든 기능 사용 가능한가?
- [ ] 스크린 리더가 이해할 수 있는가?
- [ ] 이미지에 alt 속성이 있는가?
- [ ] 시맨틱 HTML을 사용하는가?
- [ ] 스킵 링크가 있는가?

**도구:**
- WAVE (Web Accessibility Evaluation Tool)
- axe DevTools
- Lighthouse (접근성 점수)

### 7.2 크로스 브라우저 체크리스트

- [ ] Chrome에서 테스트
- [ ] Firefox에서 테스트
- [ ] Safari에서 테스트
- [ ] Edge에서 테스트
- [ ] 모바일 브라우저에서 테스트

**테스트 도구:**
- BrowserStack
- CrossBrowserTesting
- 실제 기기 테스트

**호환성 고려사항:**
- CSS Grid (IE 미지원)
- Flexbox (IE 10+)
- CSS 변수 (IE 미지원)
- ES6+ JavaScript

### 7.3 성능 체크리스트

- [ ] 이미지 최적화 (WebP, 압축)
- [ ] CSS/JS 파일 최소화
- [ ] 불필요한 코드 제거
- [ ] 폰트 최적화 (woff2 사용)
- [ ] 레이지 로딩 적용
- [ ] 캐싱 설정

**성능 측정 도구:**
- Lighthouse
- PageSpeed Insights
- WebPageTest

**목표:**
- First Contentful Paint (FCP): < 1.8초
- Largest Contentful Paint (LCP): < 2.5초
- Time to Interactive (TTI): < 3.8초

### 7.4 SEO 체크리스트

- [ ] 메타 태그 설정 (title, description)
- [ ] Open Graph 태그
- [ ] 구조화된 데이터 (Schema.org)
- [ ] 시맨틱 HTML 사용
- [ ] 적절한 제목 구조 (h1-h6)
- [ ] alt 속성 (이미지)
- [ ] 사이트맵 (sitemap.xml)
- [ ] robots.txt

### 7.5 보안 체크리스트

- [ ] HTTPS 사용
- [ ] 폼 CSRF 보호
- [ ] XSS 방지
- [ ] 민감한 정보 노출 방지
- [ ] 의존성 보안 업데이트

---

## 8. 실습 프로세스

### 8.1 단계별 진행

1. **기획 (1일)**
   - 목표 설정
   - 사이트 구조 설계
   - 디자인 컨셉 결정
   - 콘텐츠 준비

2. **마크업 (2일)**
   - HTML 구조 작성
   - 시맨틱 태그 사용
   - 접근성 고려

3. **스타일링 (3일)**
   - CSS 변수 설정
   - 컴포넌트 스타일링
   - 레이아웃 구성
   - 애니메이션 추가

4. **반응형 (2일)**
   - 모바일 우선 설계
   - 미디어 쿼리 적용
   - 다양한 기기 테스트

5. **상호작용 (2일)**
   - 폼 유효성 검사
   - 시각적 피드백
   - JavaScript 기능 추가

6. **배포 및 테스트 (1일)**
   - 배포 준비
   - 배포 실행
   - 체크리스트 확인

### 8.2 개발 팁

**점진적 개발:**
- 기본 구조부터 시작
- 기능을 하나씩 추가
- 각 단계마다 테스트

**버전 관리:**
- Git으로 버전 관리
- 기능별 커밋
- 브랜치 활용

**테스트:**
- 자주 테스트하기
- 다양한 기기에서 테스트
- 다른 사람에게 피드백 받기

---

## 9. 실습 체크리스트

### 프로젝트 완성도
- [ ] 모든 페이지가 완성되었는가?
- [ ] 디자인이 일관된가?
- [ ] 모든 링크가 작동하는가?
- [ ] 이미지가 모두 로드되는가?

### 기능
- [ ] 네비게이션이 작동하는가?
- [ ] 폼 유효성 검사가 작동하는가?
- [ ] 반응형이 모든 기기에서 작동하는가?
- [ ] 애니메이션이 부드러운가?

### 접근성
- [ ] 색상 대비가 충분한가?
- [ ] 포커스 스타일이 명확한가?
- [ ] 키보드만으로 사용 가능한가?
- [ ] 스크린 리더 호환인가?

### 성능
- [ ] 페이지 로딩이 빠른가?
- [ ] 이미지가 최적화되었는가?
- [ ] 불필요한 코드가 없는가?

### 배포
- [ ] 배포가 완료되었는가?
- [ ] 모든 기능이 배포 환경에서 작동하는가?
- [ ] URL이 올바른가?

---

## 다음 단계
- 백엔드 연동 (폼 제출)
- 데이터베이스 연동
- 사용자 인증
- 고급 애니메이션
- PWA (Progressive Web App)


