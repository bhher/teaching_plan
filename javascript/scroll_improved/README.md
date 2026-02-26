# 스크롤 애니메이션 웹사이트 - 개선 버전

스크롤 기반 인터랙티브 웹사이트의 개선된 버전입니다.

## 📋 목차
1. [프로젝트 개요](#프로젝트-개요)
2. [주요 기능](#주요-기능)
3. [코드 설명](#코드-설명)
4. [개선사항](#개선사항)
5. [사용 방법](#사용-방법)

---

## 프로젝트 개요

이 프로젝트는 스크롤 위치에 따라 다양한 애니메이션과 인터랙션이 발생하는 웹사이트입니다.

### 핵심 기능
- ✅ 스크롤 시 헤더와 네비게이션 고정
- ✅ 섹션별 메뉴 하이라이트 (each문 사용)
- ✅ 스크롤 위치에 따른 요소 애니메이션
- ✅ 부드러운 스크롤 이동
- ✅ 성능 최적화 (throttle 적용)
- ✅ 반응형 디자인

---

## 주요 기능

### 1. 헤더 및 네비게이션 고정
- 스크롤 10px 이상 시 헤더와 네비게이션이 상단에 고정
- 상단 정보 바는 숨김 처리
- Hero 섹션 높이 조정

### 2. 섹션별 메뉴 하이라이트
- 현재 보이는 섹션에 해당하는 메뉴 항목 하이라이트
- `each()` 문을 사용하여 간결하게 구현

### 3. 요소 애니메이션
- **제품 소개 섹션**: 좌우 요소가 스크롤에 따라 나타남
- **기술 스택 섹션**: 카드들이 순차적으로 나타남 (200ms 간격)

### 4. 스크롤 위치 표시
- 우측 하단에 현재 스크롤 위치 실시간 표시

---

## 코드 설명

### 1. 섹션별 메뉴 하이라이트 (each문 사용)

#### 개선 전 (반복 코드)
```javascript
if(sct >= $('.container>div').eq(0).offset().top){
    $('nav ul li').removeClass('on');
    $('nav ul li').eq(0).addClass('on');
}
if(sct >= $('.container>div').eq(1).offset().top){
    $('nav ul li').removeClass('on');
    $('nav ul li').eq(1).addClass('on');
}
// ... 반복 ...
```

#### 개선 후 (each문)
```javascript
function handleMenuHighlight(scrollTop) {
    let activeIndex = -1;

    // each문으로 각 섹션을 순회
    $sections.each(function(index) {
        const $section = $(this);
        const sectionTop = $section.offset().top;
        const sectionHeight = $section.outerHeight();
        const offset = CONFIG.animationOffset;

        // 현재 스크롤 위치가 섹션 범위 내에 있는지 확인
        if (scrollTop >= sectionTop - offset && 
            scrollTop < sectionTop + sectionHeight - offset) {
            activeIndex = index;
        }
    });

    // 활성화된 섹션이 있으면 메뉴 업데이트
    if (activeIndex >= 0) {
        $navbarItems.removeClass('on');
        $navbarItems.eq(activeIndex).addClass('on');
    }
}
```

**핵심 포인트:**
- `each()` 문으로 반복 코드 제거
- 범위 체크로 더 정확한 섹션 감지
- DOM 조작을 한 번만 수행 (성능 향상)

### 2. Throttle을 사용한 성능 최적화

```javascript
function throttle(func, delay) {
    let timeoutId;
    let lastExecTime = 0;

    return function(...args) {
        const currentTime = Date.now();
        
        if (currentTime - lastExecTime > delay) {
            func.apply(this, args);
            lastExecTime = currentTime;
        } else {
            clearTimeout(timeoutId);
            timeoutId = setTimeout(() => {
                func.apply(this, args);
                lastExecTime = Date.now();
            }, delay - (currentTime - lastExecTime));
        }
    };
}

// 사용
const throttledHandleScroll = throttle(handleScroll, 50);
$window.on('scroll', throttledHandleScroll);
```

**효과:**
- 스크롤 이벤트 실행 빈도를 50ms로 제한
- 불필요한 함수 실행 방지
- 성능 향상

### 3. 요소 애니메이션 처리

#### 제품 소개 섹션 (좌우 애니메이션)
```javascript
// 왼쪽 요소
if (scrollTop >= section2Top - 300 && scrollTop < section2Bottom) {
    $productLeft.addClass('on');
}

// 오른쪽 요소 (약간 늦게 시작)
if (scrollTop >= section2Top + 200 && scrollTop < section2Bottom) {
    $productRight.addClass('on');
}
```

#### 기술 스택 카드 (순차 애니메이션)
```javascript
if (scrollTop >= section4Top - 300) {
    $skillCards.each(function(index) {
        const $card = $(this);
        setTimeout(function() {
            $card.addClass('active');
        }, index * 200); // 200ms 간격
    });
}
```

### 4. 부드러운 스크롤 이동

```javascript
$navbarItems.on('click', function(e) {
    e.preventDefault();
    
    const index = $(this).index();
    const $targetSection = $sections.eq(index);
    const targetOffset = $targetSection.offset().top - 60;
    
    $('html, body').stop().animate({
        scrollTop: targetOffset
    }, 1000, 'swing');
    
    return false;
});
```

---

## 개선사항

### 1. 코드 구조 개선
- ✅ 함수 분리로 가독성 향상
- ✅ 설정값 중앙 관리 (CONFIG 객체)
- ✅ DOM 요소 캐싱으로 성능 향상
- ✅ 주석 추가로 이해도 향상

### 2. 성능 최적화
- ✅ Throttle 적용
- ✅ DOM 조작 최소화
- ✅ 변수 캐싱

### 3. 코드 간결화
- ✅ each문으로 반복 코드 제거
- ✅ 함수 분리로 재사용성 향상
- ✅ 명확한 변수명 사용

### 4. 디자인 개선
- ✅ 현대적인 그라디언트 배경
- ✅ 부드러운 애니메이션 효과
- ✅ 반응형 디자인
- ✅ 그림자 및 블러 효과

---

## 파일 구조

```
scroll_improved/
├── index.html          # 메인 HTML 파일
├── css/
│   └── style.css      # 스타일시트
├── js/
│   └── main.js        # JavaScript 파일
└── README.md          # 문서
```

---

## 사용 방법

### 1. 기본 사용
```html
<!-- HTML -->
<nav class="navbar">
    <ul class="navbar-menu">
        <li class="navbar-item"><a href="#section1">메뉴1</a></li>
        <!-- ... -->
    </ul>
</nav>

<div class="container">
    <section id="section1" class="section">...</section>
    <!-- ... -->
</div>
```

### 2. JavaScript 자동 초기화
- 페이지 로드 시 자동으로 초기화됨
- 스크롤 이벤트 자동 감지
- 메뉴 클릭 시 자동 스크롤

---

## 주요 함수 설명

### handleScroll()
- 메인 스크롤 이벤트 핸들러
- 모든 스크롤 관련 기능을 통합 관리

### handleHeaderFixed(scrollTop)
- 헤더 및 네비게이션 고정 처리
- 스크롤 위치에 따라 클래스 추가/제거

### handleMenuHighlight(scrollTop)
- 섹션별 메뉴 하이라이트 처리
- **each문 사용**으로 간결하게 구현
- 범위 체크로 정확한 섹션 감지

### handleElementAnimations(scrollTop)
- 요소 애니메이션 처리
- 제품 소개 섹션 좌우 애니메이션
- 기술 스택 카드 순차 애니메이션

---

## 커스터마이징

### 스크롤 임계값 변경
```javascript
const CONFIG = {
    scrollThreshold: 50,  // 10px → 50px
};
```

### 애니메이션 속도 조정
```javascript
// 기술 스택 카드 애니메이션 간격
setTimeout(function() {
    $card.addClass('active');
}, index * 300); // 200ms → 300ms
```

### CSS 변수 변경
```css
:root {
    --primary-color: #your-color;
    --navbar-height: 120px;
}
```

---

## 브라우저 호환성

- Chrome, Firefox, Safari, Edge 최신 버전
- IE11+ (일부 기능 제한)

---

## 성능 최적화 팁

### 1. DOM 요소 캐싱
```javascript
// ❌ 나쁜 예
$('.navbar').addClass('fixed');

// ✅ 좋은 예
const $navbar = $('.navbar');
$navbar.addClass('fixed');
```

### 2. Throttle 사용
```javascript
const throttledHandleScroll = throttle(handleScroll, 50);
$window.on('scroll', throttledHandleScroll);
```

### 3. 불필요한 DOM 조작 방지
```javascript
// 상태 확인 후 클래스 추가/제거
if (!isActive && scrollTop > threshold) {
    $element.addClass('active');
    isActive = true;
}
```

---

## 문제 해결

### 메뉴 하이라이트가 작동하지 않음
- 섹션 ID와 메뉴 링크 href가 일치하는지 확인
- `offset().top` 값이 올바른지 확인

### 애니메이션이 부드럽지 않음
- CSS `transition` 속성 확인
- Throttle 지연 시간 조정

### 성능 이슈
- Throttle 지연 시간 증가
- 불필요한 DOM 조작 제거
- 브라우저 DevTools로 성능 분석

---

## 추가 기능 아이디어

1. **스크롤 진행 표시**: 스크롤 위치에 따른 진행 바
2. **패럴랙스 효과**: 배경 이미지 패럴랙스
3. **다크 모드**: 테마 전환 기능
4. **로딩 애니메이션**: 페이지 로드 시 애니메이션

---

## 참고 자료

- [jQuery 공식 문서](https://api.jquery.com/)
- [CSS Transitions](https://developer.mozilla.org/ko/docs/Web/CSS/transition)
- [Performance Best Practices](https://developer.mozilla.org/ko/docs/Web/Performance)
