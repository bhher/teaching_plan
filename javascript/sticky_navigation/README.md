# 스크롤 고정 네비게이션

스크롤 시 네비게이션이 상단에 고정되고 스타일이 변경되는 기능을 구현한 프로젝트입니다.

## 📋 목차
1. [프로젝트 개요](#프로젝트-개요)
2. [주요 기능](#주요-기능)
3. [코드 설명](#코드-설명)
4. [사용 방법](#사용-방법)
5. [커스터마이징](#커스터마이징)

---

## 프로젝트 개요

이 프로젝트는 jQuery를 사용하여 스크롤 위치에 따라 네비게이션의 스타일을 동적으로 변경하는 기능을 구현합니다.

### 핵심 개념
- **고정 네비게이션**: 스크롤해도 항상 상단에 고정
- **동적 스타일 변경**: 스크롤 위치에 따라 `.active` 클래스 추가/제거
- **그림자 효과**: 활성화 시 그림자로 깊이감 표현
- **반응형 디자인**: 모바일, 태블릿, 데스크톱 모두 지원

---

## 주요 기능

### 1. 스크롤 감지
- `$(window).scroll()` 이벤트로 스크롤 감지
- 스크롤 위치에 따라 네비게이션 상태 변경

### 2. 동적 클래스 추가
- 스크롤 100px 이상: `.active` 클래스 추가
- 스크롤 100px 미만: `.active` 클래스 제거

### 3. 시각적 효과
- 활성화 시 그림자 효과 (`box-shadow`)
- 배경색 변경
- 패딩 조정

### 4. 부드러운 스크롤
- 메뉴 클릭 시 해당 섹션으로 부드럽게 이동
- 애니메이션 효과 적용

### 5. 모바일 메뉴
- 햄버거 메뉴 버튼
- 반응형 네비게이션

---

## 코드 설명

### HTML 구조

```html
<!-- 네비게이션 -->
<nav class="navbar" id="navbar">
  <div class="nav-container">
    <div class="nav-logo">로고</div>
    <ul class="nav-menu">
      <li><a href="#home">홈</a></li>
      <!-- 메뉴 항목들 -->
    </ul>
  </div>
</nav>
```

**설명:**
- `navbar`: 네비게이션 컨테이너
- `nav-container`: 내부 컨텐츠를 감싸는 컨테이너
- `nav-menu`: 메뉴 리스트

### CSS 스타일

#### 기본 네비게이션 스타일
```css
.navbar {
  position: fixed;        /* 고정 위치 */
  top: 0;                 /* 상단에 고정 */
  width: 100%;           /* 전체 너비 */
  background-color: rgba(255, 255, 255, 0.95);  /* 반투명 배경 */
  transition: all 0.3s ease;  /* 부드러운 전환 효과 */
  box-shadow: none;      /* 초기 상태: 그림자 없음 */
}
```

#### 활성화된 네비게이션 스타일
```css
.navbar.active {
  background-color: #fff;     /* 불투명 배경 */
  padding: 10px 0;            /* 패딩 감소 */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);  /* 그림자 효과 */
}
```

**설명:**
- `position: fixed`: 스크롤해도 위치 고정
- `transition`: 스타일 변경 시 부드러운 애니메이션
- `box-shadow`: 그림자 효과로 깊이감 표현

### JavaScript 코드

#### 1. 스크롤 이벤트 핸들러

```javascript
$(window).scroll(function() {
  var scrollPosition = $(window).scrollTop();
  
  if (scrollPosition > scrollThreshold) {
    $navbar.addClass('active');
  } else {
    $navbar.removeClass('active');
  }
});
```

**단계별 설명:**

1. **`$(window).scroll(function() {...})`**
   - 브라우저 창이 스크롤될 때마다 실행되는 이벤트 핸들러

2. **`$(window).scrollTop()`**
   - 현재 스크롤 위치를 픽셀 단위로 반환
   - 예: 0px (맨 위), 500px (500px 아래)

3. **조건문**
   - `scrollPosition > scrollThreshold`: 스크롤이 100px 이상이면
   - `.addClass('active')`: active 클래스 추가
   - `.removeClass('active')`: active 클래스 제거

#### 2. 변수 선언

```javascript
var $navbar = $('#navbar');
var scrollThreshold = 100;
```

**설명:**
- `$navbar`: 네비게이션 요소를 변수에 저장 (성능 최적화)
- `scrollThreshold`: 활성화되는 스크롤 위치 (100px)

#### 3. 부드러운 스크롤

```javascript
$('.nav-menu a').click(function(e) {
  e.preventDefault();
  var targetId = $(this).attr('href');
  var targetPosition = $(targetId).offset().top - 70;
  
  $('html, body').animate({
    scrollTop: targetPosition
  }, 800);
});
```

**설명:**
- `e.preventDefault()`: 기본 링크 동작 방지
- `$(targetId).offset().top`: 대상 요소의 위치 계산
- `- 70`: 네비게이션 높이만큼 빼기
- `.animate()`: 부드러운 스크롤 애니메이션

---

## 사용 방법

### 1. 파일 구조
```
sticky_navigation/
├── index.html
├── css/
│   └── style.css
├── js/
│   └── main.js
└── README.md
```

### 2. 실행 방법
1. `index.html` 파일을 브라우저에서 열기
2. 페이지를 스크롤하면 네비게이션 스타일 변경 확인
3. 메뉴 클릭 시 해당 섹션으로 이동 확인

### 3. 필요한 라이브러리
- jQuery 3.1.1 이상 (CDN 사용)

---

## 커스터마이징

### 스크롤 임계값 변경

```javascript
// js/main.js 파일에서
var scrollThreshold = 200;  // 100px → 200px로 변경
```

### 그림자 효과 조정

```css
/* css/style.css 파일에서 */
.navbar.active {
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);  /* 더 진한 그림자 */
}
```

### 배경색 변경

```css
.navbar.active {
  background-color: #f0f0f0;  /* 원하는 색상으로 변경 */
}
```

### 전환 속도 조정

```css
.navbar {
  transition: all 0.5s ease;  /* 0.3s → 0.5s로 변경 */
}
```

---

## 주요 개념 설명

### 1. $(window).scroll()
- 브라우저 창이 스크롤될 때마다 실행되는 이벤트
- 스크롤할 때마다 함수가 실행되므로 성능에 주의

### 2. .addClass() / .removeClass()
- jQuery 메서드로 HTML 요소에 클래스 추가/제거
- CSS에서 해당 클래스의 스타일이 자동 적용

### 3. position: fixed
- 요소를 뷰포트 기준으로 고정
- 스크롤해도 위치가 변하지 않음

### 4. transition
- CSS 속성 변경 시 부드러운 애니메이션 효과
- `transition: all 0.3s ease` = 모든 속성 변경을 0.3초 동안 부드럽게

### 5. box-shadow
- 요소에 그림자 효과 추가
- `0 2px 10px rgba(0, 0, 0, 0.1)` = 오른쪽 2px, 아래 10px, 투명도 0.1

---

## 성능 최적화 팁

### 1. 변수 캐싱
```javascript
// ❌ 나쁜 예 (매번 DOM 탐색)
$('#navbar').addClass('active');

// ✅ 좋은 예 (한 번만 탐색)
var $navbar = $('#navbar');
$navbar.addClass('active');
```

### 2. 스로틀링 (Throttling)
```javascript
var scrollTimer = null;
$(window).scroll(function() {
  if (scrollTimer !== null) {
    clearTimeout(scrollTimer);
  }
  scrollTimer = setTimeout(function() {
    // 스크롤 처리 코드
  }, 10);
});
```

---

## 브라우저 호환성

- Chrome, Firefox, Safari, Edge 최신 버전 지원
- IE11 이상 지원 (jQuery 사용)

---

## 문제 해결

### 네비게이션이 고정되지 않음
- `position: fixed`가 CSS에 있는지 확인
- z-index 값 확인 (다른 요소에 가려질 수 있음)

### 스크롤 시 작동하지 않음
- jQuery가 로드되었는지 확인
- 브라우저 콘솔에서 오류 확인

### 그림자가 나타나지 않음
- `.active` 클래스가 추가되는지 확인 (개발자 도구 사용)
- `box-shadow` 속성이 CSS에 있는지 확인

---

## 추가 기능 아이디어

1. **스크롤 진행 표시**: 스크롤 위치에 따른 진행 바
2. **메뉴 하이라이트**: 현재 섹션에 해당하는 메뉴 강조
3. **백 투 탑 버튼**: 맨 위로 이동하는 버튼
4. **애니메이션 효과**: 스크롤 시 요소 페이드인 효과

---

## 참고 자료

- [jQuery 공식 문서](https://api.jquery.com/)
- [CSS position 속성](https://developer.mozilla.org/ko/docs/Web/CSS/position)
- [CSS transition 속성](https://developer.mozilla.org/ko/docs/Web/CSS/transition)
