# 프로젝트 구조 예제

이 문서는 다양한 규모의 프로젝트에 대한 권장 폴더 구조를 보여줍니다.

## 1. 작은 프로젝트 구조 (1-5페이지)

간단한 웹사이트나 랜딩 페이지에 적합한 구조입니다.

```
my-website/
├── index.html
├── about.html
├── contact.html
├── css/
│   └── style.css
├── js/
│   └── main.js
├── images/
│   ├── logo.png
│   └── hero.jpg
└── README.md
```

**특징:**
- 간단하고 직관적
- 파일 수가 적어 관리 쉬움
- 빠른 개발 가능

## 2. 중간 프로젝트 구조 (5-20페이지)

일반적인 웹사이트에 적합한 구조입니다.

```
my-project/
├── index.html
├── pages/
│   ├── about.html
│   ├── services.html
│   ├── portfolio.html
│   └── contact.html
├── assets/
│   ├── css/
│   │   ├── base.css
│   │   ├── components.css
│   │   ├── layout.css
│   │   └── utilities.css
│   ├── js/
│   │   ├── main.js
│   │   └── utils.js
│   ├── images/
│   │   ├── icons/
│   │   ├── photos/
│   │   └── logos/
│   └── fonts/
│       └── custom-font.woff2
├── .gitignore
├── package.json
└── README.md
```

**특징:**
- HTML과 자산 파일 분리
- CSS를 기능별로 분리
- 확장 가능한 구조

## 3. 큰 프로젝트 구조 (20페이지 이상)

복잡한 웹 애플리케이션에 적합한 구조입니다.

```
my-app/
├── index.html
├── pages/
│   ├── about/
│   │   └── index.html
│   ├── services/
│   │   ├── index.html
│   │   └── detail.html
│   └── blog/
│       ├── index.html
│       └── post.html
├── assets/
│   ├── css/
│   │   ├── base/
│   │   │   ├── reset.css
│   │   │   ├── typography.css
│   │   │   └── variables.css
│   │   ├── components/
│   │   │   ├── button.css
│   │   │   ├── card.css
│   │   │   ├── nav.css
│   │   │   └── form.css
│   │   ├── layouts/
│   │   │   ├── header.css
│   │   │   ├── footer.css
│   │   │   └── grid.css
│   │   ├── utilities/
│   │   │   └── helpers.css
│   │   └── main.css
│   ├── js/
│   │   ├── modules/
│   │   │   ├── api.js
│   │   │   ├── utils.js
│   │   │   └── validation.js
│   │   └── main.js
│   ├── images/
│   │   ├── icons/
│   │   ├── photos/
│   │   ├── logos/
│   │   └── backgrounds/
│   ├── fonts/
│   │   └── custom-font.woff2
│   └── videos/
├── docs/
│   ├── style-guide.md
│   └── api.md
├── .gitignore
├── package.json
└── README.md
```

**특징:**
- 세밀한 폴더 구조
- 컴포넌트 기반 설계
- 문서화 포함

## 4. CSS 통합 방법

### 방법 1: main.css에서 @import 사용

```css
/* assets/css/main.css */
@import url('base/reset.css');
@import url('base/typography.css');
@import url('base/variables.css');
@import url('components/button.css');
@import url('components/card.css');
@import url('layouts/header.css');
@import url('layouts/footer.css');
@import url('utilities/helpers.css');
```

**HTML에서 사용:**
```html
<link rel="stylesheet" href="assets/css/main.css">
```

### 방법 2: HTML에서 직접 링크

```html
<link rel="stylesheet" href="assets/css/base/reset.css">
<link rel="stylesheet" href="assets/css/base/typography.css">
<link rel="stylesheet" href="assets/css/components/button.css">
<link rel="stylesheet" href="assets/css/components/card.css">
<link rel="stylesheet" href="assets/css/layouts/header.css">
```

## 5. 파일 네이밍 규칙

### HTML 파일
- 소문자 사용
- 하이픈(-)으로 단어 구분
- 예: `about-us.html`, `contact-page.html`

### CSS 파일
- 소문자 사용
- 하이픈(-)으로 단어 구분
- 예: `main.css`, `button-component.css`

### JavaScript 파일
- camelCase 또는 kebab-case
- 예: `main.js`, `apiUtils.js` 또는 `api-utils.js`

### 이미지 파일
- 소문자 사용
- 하이픈(-)으로 단어 구분
- 예: `hero-image.jpg`, `logo-icon.png`

## 6. 폴더 구조 선택 가이드

### 프로젝트 규모별 권장 구조

| 프로젝트 규모 | 권장 구조 | 특징 |
|------------|---------|------|
| 작은 (1-5페이지) | 기본 구조 | 간단하고 직관적 |
| 중간 (5-20페이지) | 확장 구조 | 폴더 분리, 컴포넌트화 |
| 큰 (20페이지 이상) | 세밀한 구조 | 모듈화, 문서화 |

### 프로젝트 유형별 권장 구조

- **정적 웹사이트**: 기본 또는 확장 구조
- **웹 애플리케이션**: 세밀한 구조
- **랜딩 페이지**: 기본 구조
- **포트폴리오**: 확장 구조

## 7. 실전 예제

### 예제 1: 블로그 사이트

```
blog/
├── index.html
├── posts/
│   ├── post-1.html
│   └── post-2.html
├── assets/
│   ├── css/
│   │   ├── base.css
│   │   ├── article.css
│   │   └── sidebar.css
│   └── images/
│       └── posts/
└── README.md
```

### 예제 2: 포트폴리오 사이트

```
portfolio/
├── index.html
├── about.html
├── portfolio.html
├── contact.html
├── assets/
│   ├── css/
│   │   ├── base.css
│   │   ├── gallery.css
│   │   └── contact-form.css
│   └── images/
│       ├── portfolio/
│       └── profile/
└── README.md
```

## 8. 구조 설계 체크리스트

- [ ] 프로젝트 규모에 맞는 구조를 선택했는가?
- [ ] HTML, CSS, 자산 파일이 적절히 분리되었는가?
- [ ] 파일 이름이 일관된 규칙을 따르는가?
- [ ] 확장 가능한 구조인가?
- [ ] 다른 개발자가 이해하기 쉬운가?
- [ ] 필요한 문서가 포함되어 있는가?


