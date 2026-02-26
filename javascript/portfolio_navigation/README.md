# 포트폴리오 웹사이트 - 스마트 네비게이션

포트폴리오용 웹사이트의 스크롤 기반 네비게이션 시스템입니다.

## 📋 목차
1. [프로젝트 개요](#프로젝트-개요)
2. [주요 기능](#주요-기능)
3. [기술 스택](#기술-스택)
4. [파일 구조](#파일-구조)
5. [코드 설명](#코드-설명)
6. [성능 최적화](#성능-최적화)
7. [사용 방법](#사용-방법)

---

## 프로젝트 개요

이 프로젝트는 실무에서 사용할 수 있는 수준의 포트폴리오 웹사이트 네비게이션 시스템입니다.

### 핵심 기능
- ✅ 스크롤 시 네비게이션 sticky 처리
- ✅ 스크롤 방향 감지 (아래/위)
- ✅ 동적 배경색 변경 (투명 → 흰색)
- ✅ 성능 최적화 (throttle, requestAnimationFrame)
- ✅ 반응형 디자인 (모바일, 태블릿, 데스크톱)
- ✅ 순수 JavaScript (jQuery 없음)
- ✅ Flexbox 기반 레이아웃

---

## 주요 기능

### 1. 스크롤 기반 Sticky 네비게이션
- 스크롤 시 네비게이션이 상단에 고정
- `position: fixed` 사용

### 2. 스크롤 방향 감지
- **아래로 스크롤**: 네비게이션 숨김 (`translateY(-100%)`)
- **위로 스크롤**: 네비게이션 표시 (`translateY(0)`)

### 3. 동적 배경색 변경
- **초기 상태**: 투명 배경 (`transparent`)
- **스크롤 후**: 흰색 배경 + 그림자 효과

### 4. 성능 최적화
- **Throttle**: 스크롤 이벤트 실행 빈도 제한
- **requestAnimationFrame**: 부드러운 애니메이션
- **Passive Event Listeners**: 스크롤 성능 향상

---

## 기술 스택

- **HTML5**: 시맨틱 마크업
- **CSS3**: Flexbox, CSS Variables, Transitions
- **Vanilla JavaScript**: 순수 JavaScript (ES6+)
- **모듈화**: 기능별 파일 분리

---

## 파일 구조

```
portfolio_navigation/
├── index.html              # 메인 HTML 파일
├── css/
│   └── style.css           # 스타일시트 (Flexbox 기반)
├── js/
│   ├── navigation.js       # 네비게이션 모듈 (핵심 로직)
│   └── main.js             # 메인 애플리케이션 로직
└── README.md               # 문서
```

---

## 코드 설명

### 1. Navigation Module (navigation.js)

#### 핵심 로직 흐름

```javascript
스크롤 이벤트 발생
    ↓
Throttle로 실행 빈도 제한
    ↓
현재 스크롤 위치 확인
    ↓
스크롤 방향 감지 (이전 위치와 비교)
    ↓
조건에 따라 처리:
  - 스크롤 위치 > 100px → .active 클래스 추가 (배경색 변경)
  - 아래로 스크롤 → .hidden 클래스 추가 (숨김)
  - 위로 스크롤 → .hidden 클래스 제거 (표시)
```

#### 주요 함수

**1. throttle()**
```javascript
function throttle(func, delay) {
  // 함수 실행 빈도를 제한하여 성능 최적화
  // delay 시간 내에는 한 번만 실행
}
```

**2. handleScroll()**
```javascript
function handleScroll() {
  const currentScrollTop = getScrollTop();
  const scrollingDown = detectScrollDirection(currentScrollTop);
  
  // 배경색 변경
  toggleNavbarActive(currentScrollTop > 100);
  
  // 표시/숨김 처리
  if (scrollingDown) {
    toggleNavbarVisibility(true);  // 숨김
  } else {
    toggleNavbarVisibility(false); // 표시
  }
}
```

**3. detectScrollDirection()**
```javascript
function detectScrollDirection(currentScrollTop) {
  const scrollingDown = currentScrollTop > state.lastScrollTop;
  state.lastScrollTop = currentScrollTop;
  return scrollingDown;
}
```

### 2. CSS (style.css)

#### Flexbox 레이아웃

```css
/* 네비게이션 컨테이너 */
.navbar-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/* 메뉴 리스트 */
.navbar-menu {
  display: flex;
  gap: 30px;
  align-items: center;
}
```

#### CSS Variables 사용

```css
:root {
  --primary-color: #667eea;
  --navbar-height: 70px;
  --transition: all 0.3s ease;
}
```

#### 상태별 스타일

```css
/* 기본 상태 (투명) */
.navbar {
  background-color: transparent;
  transform: translateY(0);
}

/* 활성화 상태 (흰색 배경) */
.navbar.active {
  background-color: #fff;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* 숨김 상태 */
.navbar.hidden {
  transform: translateY(-100%);
}
```

### 3. Main Script (main.js)

#### 모바일 메뉴 토글
```javascript
navbarToggle.addEventListener('click', function() {
  navbarToggle.classList.toggle('active');
  navbarMenu.classList.toggle('active');
});
```

#### 부드러운 스크롤
```javascript
window.scrollTo({
  top: targetPosition,
  behavior: 'smooth'
});
```

#### 활성 섹션 하이라이트
```javascript
function highlightActiveSection() {
  // 현재 보이는 섹션에 해당하는 메뉴 항목 하이라이트
}
```

---

## 성능 최적화

### 1. Throttle 적용
```javascript
const throttledHandleScroll = throttle(handleScroll, 100);
window.addEventListener('scroll', throttledHandleScroll);
```
- 스크롤 이벤트 실행 빈도를 100ms로 제한
- 불필요한 함수 실행 방지

### 2. Passive Event Listeners
```javascript
window.addEventListener('scroll', handler, { passive: true });
```
- 브라우저가 스크롤 성능을 최적화할 수 있도록 함
- preventDefault()를 호출하지 않을 때 사용

### 3. requestAnimationFrame
```javascript
window.requestAnimationFrame(function() {
  highlightActiveSection();
});
```
- 브라우저 리플로우와 동기화하여 부드러운 애니메이션

### 4. DOM 요소 캐싱
```javascript
const navbar = document.querySelector('#navbar');
// 한 번만 DOM 탐색하고 재사용
```

### 5. 상태 관리
```javascript
const state = {
  lastScrollTop: 0,
  isScrollingDown: false,
  isNavbarActive: false
};
```
- 불필요한 DOM 조작 방지
- 상태 변경 시에만 클래스 추가/제거

---

## 사용 방법

### 1. 기본 사용
```html
<!-- HTML -->
<nav class="navbar" id="navbar">
  <!-- 네비게이션 내용 -->
</nav>
```

```javascript
// JavaScript는 자동으로 초기화됨
// navigation.js가 DOM 로드 시 자동 실행
```

### 2. 커스터마이징

#### 스크롤 임계값 변경
```javascript
// js/navigation.js
const CONFIG = {
  scrollThreshold: 200,  // 100px → 200px
};
```

#### Throttle 지연 시간 조정
```javascript
const CONFIG = {
  throttleDelay: 50,  // 100ms → 50ms (더 빠른 반응)
};
```

#### CSS 변수 변경
```css
/* css/style.css */
:root {
  --primary-color: #your-color;
  --navbar-height: 80px;
}
```

---

## 브라우저 호환성

- Chrome, Firefox, Safari, Edge 최신 버전
- IE11+ (일부 기능 제한)

---

## 실무 적용 팁

### 1. 모듈화
- 기능별로 파일 분리 (navigation.js, main.js)
- 재사용 가능한 구조

### 2. 설정 관리
- CONFIG 객체로 설정값 중앙 관리
- 유지보수 용이

### 3. 에러 처리
```javascript
if (!navbar) {
  console.error('Navigation element not found');
  return;
}
```

### 4. 주석 작성
- 함수별 상세 주석
- 코드 이해도 향상

### 5. 성능 모니터링
- Chrome DevTools Performance 탭으로 성능 확인
- 불필요한 리플로우/리페인트 최소화

---

## 추가 개선 사항

### 가능한 확장 기능
1. **스크롤 진행 표시**: 스크롤 위치에 따른 진행 바
2. **애니메이션 효과**: 섹션 진입 시 페이드인 효과
3. **다크 모드**: 테마 전환 기능
4. **접근성 향상**: 키보드 네비게이션 지원

---

## 문제 해결

### 네비게이션이 고정되지 않음
- `position: fixed` 확인
- z-index 값 확인

### 스크롤 방향 감지가 작동하지 않음
- `lastScrollTop` 초기화 확인
- 스크롤 이벤트 리스너 등록 확인

### 성능 이슈
- Throttle 지연 시간 조정
- 불필요한 DOM 조작 제거
- 브라우저 DevTools로 성능 분석

---

## 라이선스

이 프로젝트는 교육 목적으로 제작되었습니다.
