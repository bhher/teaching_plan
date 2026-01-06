# 프로젝트 스타일 가이드

이 문서는 프로젝트의 코딩 표준과 규칙을 정의합니다.

## 목차
1. [HTML 스타일 가이드](#html-스타일-가이드)
2. [CSS 스타일 가이드](#css-스타일-가이드)
3. [JavaScript 스타일 가이드](#javascript-스타일-가이드)
4. [파일 구조 규칙](#파일-구조-규칙)
5. [네이밍 규칙](#네이밍-규칙)
6. [커밋 규칙](#커밋-규칙)

---

## HTML 스타일 가이드

### 들여쓰기
- **2칸 공백** 사용 (탭 사용 금지)
- 일관된 들여쓰기 유지

```html
<div class="container">
  <header class="header">
    <h1>제목</h1>
  </header>
</div>
```

### 속성 순서
1. 클래스
2. ID
3. 데이터 속성
4. 일반 속성
5. 이벤트 핸들러

```html
<a 
  class="link"
  id="main-link"
  data-id="123"
  href="#"
  target="_blank"
  onclick="handleClick()"
>
  링크
</a>
```

### 시맨틱 태그 사용
- 의미 있는 HTML5 시맨틱 태그 사용
- div 남용 지양

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

### 속성 따옴표
- 모든 속성에 **쌍따옴표** 사용

```html
<!-- 좋은 예 -->
<img src="image.jpg" alt="이미지">

<!-- 나쁜 예 -->
<img src='image.jpg' alt=이미지>
```

### 불리언 속성
- 불리언 속성은 값 생략

```html
<!-- 좋은 예 -->
<input type="checkbox" checked>
<button disabled>버튼</button>

<!-- 나쁜 예 -->
<input type="checkbox" checked="checked">
<button disabled="disabled">버튼</button>
```

---

## CSS 스타일 가이드

### 들여쓰기
- **2칸 공백** 사용

### 네이밍 규칙
- **BEM 방법론** 사용
- 소문자와 하이픈(-) 사용

```css
/* Block */
.card { }

/* Element */
.card__title { }
.card__body { }

/* Modifier */
.card--highlighted { }
.card__button--primary { }
```

### 속성 순서
1. 위치 (position, top, left, z-index)
2. 박스 모델 (display, width, height, padding, margin)
3. 시각 (background, color, border)
4. 타이포그래피 (font, line-height, text-align)
5. 기타 (transition, animation, cursor)

```css
.element {
    /* 위치 */
    position: relative;
    top: 0;
    left: 0;
    z-index: 1;
    
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
    text-align: center;
    
    /* 기타 */
    transition: all 0.3s;
    cursor: pointer;
}
```

### 주석 규칙
- 섹션 구분: `/* ===== */`
- 컴포넌트: `/* Component: Button */`
- 설명: `/* 설명 */`

```css
/* ========================================
   섹션: 헤더
   ======================================== */

/* Component: Navigation */
.nav { }

/* Modifier: Active state */
.nav__link--active { }
```

### CSS 변수 사용
- 전역 변수는 `:root`에 정의
- 의미 있는 이름 사용

```css
:root {
    --primary-color: #3498db;
    --spacing-unit: 8px;
    --font-size-base: 16px;
}
```

### 미디어 쿼리
- 모바일 우선 설계
- 브레이크포인트는 변수로 관리

```css
/* 모바일 (기본) */
.container {
    width: 100%;
}

/* 태블릿 */
@media (min-width: 768px) {
    .container {
        width: 750px;
    }
}
```

---

## JavaScript 스타일 가이드

### 들여쓰기
- **2칸 공백** 사용

### 변수 선언
- `const` 우선 사용
- 재할당 필요한 경우만 `let`
- `var` 사용 금지

```javascript
// 좋은 예
const name = 'John';
let age = 25;

// 나쁜 예
var name = 'John';
```

### 함수 선언
- 함수 표현식 또는 화살표 함수 사용

```javascript
// 좋은 예
const handleClick = () => {
    console.log('clicked');
};

// 또는
function handleClick() {
    console.log('clicked');
}
```

### 네이밍 규칙
- 변수/함수: camelCase
- 상수: UPPER_SNAKE_CASE
- 클래스: PascalCase

```javascript
const userName = 'John';
const MAX_COUNT = 100;

function getUserName() { }
class UserProfile { }
```

---

## 파일 구조 규칙

### HTML 파일
- 소문자 사용
- 하이픈(-)으로 단어 구분
- 예: `about-us.html`, `contact-page.html`

### CSS 파일
- 소문자 사용
- 하이픈(-)으로 단어 구분
- 예: `main.css`, `button-component.css`

### JavaScript 파일
- camelCase 사용
- 예: `main.js`, `apiUtils.js`

### 폴더 구조
```
assets/
├── css/
│   ├── base/
│   ├── components/
│   ├── layouts/
│   └── utilities/
├── js/
│   └── modules/
└── images/
```

---

## 네이밍 규칙

### 클래스 이름
- BEM 방법론 사용
- 소문자와 하이픈(-)
- 예: `.card__title--large`

### ID 이름
- camelCase 사용
- 예: `mainContent`, `headerNav`

### 파일 이름
- 소문자 사용
- 하이픈(-)으로 단어 구분
- 예: `about-us.html`, `main-style.css`

---

## 커밋 규칙

### 커밋 메시지 형식
```
타입(범위): 간단한 설명

상세 설명 (선택사항)
```

### 타입
- `feat`: 새로운 기능
- `fix`: 버그 수정
- `docs`: 문서 수정
- `style`: 코드 포맷팅
- `refactor`: 리팩토링
- `test`: 테스트 추가
- `chore`: 빌드/설정 변경

### 예시
```
feat(header): Add responsive navigation menu

- Add mobile hamburger menu
- Add desktop horizontal menu
- Add smooth transitions

fix(button): Fix hover state color

docs: Update README with installation guide

style: Format code with Prettier
```

---

## 코드 리뷰 체크리스트

- [ ] 스타일 가이드를 따르고 있는가?
- [ ] 중복 코드가 없는가?
- [ ] 의미 있는 변수명을 사용하는가?
- [ ] 주석이 명확한가?
- [ ] 접근성을 고려했는가?
- [ ] 성능을 고려했는가?
- [ ] 반응형 디자인을 적용했는가?

---

## 참고 자료

- [BEM 방법론](http://getbem.com/)
- [Google HTML/CSS 스타일 가이드](https://google.github.io/styleguide/htmlcssguide.html)
- [Airbnb JavaScript 스타일 가이드](https://github.com/airbnb/javascript)


