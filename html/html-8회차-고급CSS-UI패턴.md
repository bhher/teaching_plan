# 8회차 — 고급 CSS 기술 및 UI 패턴

## 학습 목표
- CSS 트랜지션과 트랜스폼을 사용하여 인터랙티브한 효과를 만들 수 있다
- @keyframes를 사용하여 애니메이션을 구현할 수 있다
- CSS 변수를 활용하여 재사용 가능한 스타일을 만들 수 있다
- BEM 등 클래스 네이밍 전략을 이해하고 적용할 수 있다
- 접근성과 성능을 고려한 CSS를 작성할 수 있다

---

## 1. 트랜지션 (Transition)

### 1.1 트랜지션이란?

트랜지션은 CSS 속성 값이 변경될 때 부드럽게 전환되는 효과를 제공합니다.

**특징:**
- 상태 변화를 부드럽게
- 사용자 경험 향상
- 간단한 애니메이션 효과

### 1.2 기본 문법

```css
.element {
    transition: property duration timing-function delay;
}
```

**속성:**
- `property`: 전환할 속성 (기본값: all)
- `duration`: 지속 시간 (필수)
- `timing-function`: 타이밍 함수 (기본값: ease)
- `delay`: 지연 시간 (기본값: 0)

### 1.3 기본 사용법

```css
.button {
    background-color: blue;
    transition: background-color 0.3s;
}

.button:hover {
    background-color: red;
}
```

**단축 속성:**
```css
/* 모든 속성, 0.3초, ease, 0초 지연 */
transition: all 0.3s;

/* 특정 속성만 */
transition: background-color 0.3s, transform 0.5s;

/* 여러 속성 */
transition: background-color 0.3s ease, transform 0.5s ease-in-out 0.2s;
```

### 1.4 개별 속성

```css
.element {
    transition-property: background-color, transform;
    transition-duration: 0.3s, 0.5s;
    transition-timing-function: ease, ease-in-out;
    transition-delay: 0s, 0.2s;
}
```

### 1.5 타이밍 함수 (Timing Function)

```css
transition-timing-function: ease;        /* 기본값: 느리게 시작, 빠르게, 느리게 끝 */
transition-timing-function: linear;      /* 일정한 속도 */
transition-timing-function: ease-in;     /* 느리게 시작 */
transition-timing-function: ease-out;    /* 느리게 끝 */
transition-timing-function: ease-in-out; /* 느리게 시작하고 끝 */
transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1); /* 커스텀 */
```

**시각화:**
```
ease:        ────╮
                ╰────
ease-in:     ╭───
            ╰────
ease-out:    ────╮
                ╰
linear:      ─────
```

### 1.6 전환 가능한 속성

**전환 가능:**
- 색상 (color, background-color, border-color)
- 크기 (width, height, font-size)
- 위치 (top, left, margin, padding)
- 변형 (transform)
- 투명도 (opacity)
- 그림자 (box-shadow)

**전환 불가능:**
- display
- font-family
- position (static ↔ relative 등)

---

## 2. 트랜스폼 (Transform)

### 2.1 트랜스폼이란?

트랜스폼은 요소를 변형(이동, 회전, 크기 조절, 기울임)시킵니다.

**특징:**
- 레이아웃에 영향 없음 (다른 요소 위치 변경 안 됨)
- GPU 가속 가능 (성능 좋음)
- 여러 변형 조합 가능

### 2.2 이동 (Translate)

```css
.element {
    transform: translateX(50px);   /* X축 이동 */
    transform: translateY(50px);   /* Y축 이동 */
    transform: translate(50px, 50px); /* X, Y 이동 */
    transform: translateZ(50px);    /* Z축 이동 (3D) */
}
```

**사용 예시:**
```css
.button:hover {
    transform: translateY(-5px);  /* 위로 5px 이동 */
}
```

### 2.3 회전 (Rotate)

```css
.element {
    transform: rotate(45deg);      /* 45도 회전 */
    transform: rotateX(45deg);     /* X축 회전 (3D) */
    transform: rotateY(45deg);     /* Y축 회전 (3D) */
    transform: rotateZ(45deg);     /* Z축 회전 */
}
```

### 2.4 크기 조절 (Scale)

```css
.element {
    transform: scale(1.2);         /* 1.2배 확대 */
    transform: scaleX(1.2);        /* X축만 확대 */
    transform: scaleY(1.2);        /* Y축만 확대 */
    transform: scale(1.2, 0.8);    /* X 1.2배, Y 0.8배 */
}
```

### 2.5 기울임 (Skew)

```css
.element {
    transform: skewX(15deg);       /* X축 기울임 */
    transform: skewY(15deg);       /* Y축 기울임 */
    transform: skew(15deg, 10deg); /* X, Y 기울임 */
}
```

### 2.6 변형 조합

```css
.element {
    transform: translate(50px, 50px) rotate(45deg) scale(1.2);
}
```

**주의사항:**
- 순서가 중요함 (오른쪽부터 적용)
- `transform: translate(50px) rotate(45deg);` ≠ `transform: rotate(45deg) translate(50px);`

### 2.7 transform-origin

변형의 기준점을 지정합니다.

```css
.element {
    transform-origin: center;       /* 기본값: 중앙 */
    transform-origin: top left;     /* 왼쪽 위 */
    transform-origin: 50% 50%;      /* 중앙 (퍼센트) */
    transform-origin: 0 0;          /* 왼쪽 위 (픽셀) */
}
```

---

## 3. 애니메이션 (Animation)

### 3.1 @keyframes

애니메이션의 키프레임을 정의합니다.

```css
@keyframes slideIn {
    from {
        transform: translateX(-100%);
    }
    to {
        transform: translateX(0);
    }
}

/* 또는 */
@keyframes slideIn {
    0% {
        transform: translateX(-100%);
    }
    100% {
        transform: translateX(0);
    }
}
```

### 3.2 animation 속성

```css
.element {
    animation: name duration timing-function delay iteration-count direction fill-mode;
}
```

**속성:**
- `name`: @keyframes 이름
- `duration`: 지속 시간 (필수)
- `timing-function`: 타이밍 함수
- `delay`: 지연 시간
- `iteration-count`: 반복 횟수 (기본값: 1, infinite: 무한)
- `direction`: 방향 (normal, reverse, alternate, alternate-reverse)
- `fill-mode`: 애니메이션 전후 상태 (none, forwards, backwards, both)

### 3.3 기본 사용법

```css
@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

.element {
    animation: fadeIn 1s ease-in-out;
}
```

### 3.4 개별 속성

```css
.element {
    animation-name: fadeIn;
    animation-duration: 1s;
    animation-timing-function: ease-in-out;
    animation-delay: 0.5s;
    animation-iteration-count: 3;
    animation-direction: alternate;
    animation-fill-mode: forwards;
}
```

### 3.5 애니메이션 제어

```css
/* 애니메이션 일시정지 */
.element {
    animation-play-state: paused;
}

/* JavaScript로 제어 */
element.style.animationPlayState = 'paused';
```

### 3.6 실전 애니메이션 예제

#### 페이드 인
```css
@keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
}
```

#### 슬라이드 인
```css
@keyframes slideIn {
    from {
        transform: translateX(-100%);
        opacity: 0;
    }
    to {
        transform: translateX(0);
        opacity: 1;
    }
}
```

#### 회전
```css
@keyframes spin {
    from { transform: rotate(0deg); }
    to { transform: rotate(360deg); }
}
```

#### 바운스
```css
@keyframes bounce {
    0%, 100% {
        transform: translateY(0);
    }
    50% {
        transform: translateY(-20px);
    }
}
```

---

## 4. CSS 변수 (Custom Properties)

### 4.1 CSS 변수란?

CSS 변수(Custom Properties)는 재사용 가능한 값을 저장하는 변수입니다.

**장점:**
- 재사용성 향상
- 유지보수 용이
- 테마 변경 쉬움
- 동적 값 변경 가능

### 4.2 변수 정의

```css
:root {
    --primary-color: #3498db;
    --secondary-color: #2ecc71;
    --font-size-base: 16px;
    --spacing-unit: 8px;
}
```

**범위:**
- `:root`: 전역 변수 (모든 요소에서 사용 가능)
- 특정 선택자: 해당 요소와 자식 요소에서만 사용

### 4.3 변수 사용

```css
.button {
    background-color: var(--primary-color);
    font-size: var(--font-size-base);
    padding: calc(var(--spacing-unit) * 2);
}
```

### 4.4 기본값 설정

```css
.element {
    color: var(--text-color, #333);  /* --text-color가 없으면 #333 사용 */
}
```

### 4.5 calc()와 조합

```css
:root {
    --spacing: 8px;
}

.element {
    padding: calc(var(--spacing) * 2);  /* 16px */
    margin: calc(var(--spacing) / 2);   /* 4px */
}
```

### 4.6 JavaScript로 동적 변경

```javascript
// 변수 값 변경
document.documentElement.style.setProperty('--primary-color', '#e74c3c');

// 변수 값 읽기
const color = getComputedStyle(document.documentElement)
    .getPropertyValue('--primary-color');
```

### 4.7 실전 활용

#### 테마 변경
```css
:root {
    --bg-color: #ffffff;
    --text-color: #333333;
}

[data-theme="dark"] {
    --bg-color: #1a1a1a;
    --text-color: #ffffff;
}

body {
    background-color: var(--bg-color);
    color: var(--text-color);
}
```

#### 반응형 변수
```css
:root {
    --font-size: 14px;
}

@media (min-width: 768px) {
    :root {
        --font-size: 16px;
    }
}

@media (min-width: 1024px) {
    :root {
        --font-size: 18px;
    }
}
```

---

## 5. 클래스 네이밍 전략

### 5.1 BEM (Block Element Modifier)

BEM은 CSS 클래스 네이밍 방법론입니다.

**구조:**
```
Block__Element--Modifier
```

**예시:**
```html
<div class="card">
    <div class="card__header">
        <h2 class="card__title">제목</h2>
    </div>
    <div class="card__body">
        <p class="card__text">내용</p>
    </div>
    <button class="card__button card__button--primary">버튼</button>
</div>
```

```css
/* Block */
.card { }

/* Element */
.card__header { }
.card__title { }
.card__body { }
.card__text { }
.card__button { }

/* Modifier */
.card__button--primary { }
.card__button--secondary { }
```

**규칙:**
- Block: 독립적인 컴포넌트
- Element: Block의 일부 (__로 연결)
- Modifier: Block 또는 Element의 변형 (--로 연결)

### 5.2 BEM 장점

- 명확한 구조
- 재사용성 향상
- 특이성 관리 용이
- 유지보수 용이

### 5.3 다른 네이밍 방법론

#### OOCSS (Object-Oriented CSS)
```css
.button { }
.button-primary { }
.button-large { }
```

#### SMACSS (Scalable and Modular Architecture)
```css
.layout-header { }
.module-card { }
.state-active { }
```

### 5.4 네이밍 가이드라인

**좋은 예:**
```css
.nav-menu { }
.card-title { }
.button-primary { }
```

**나쁜 예:**
```css
.red-box { }        /* 색상에 의존 */
.big-text { }      /* 크기에 의존 */
.left-side { }     /* 위치에 의존 */
```

---

## 6. 접근성 (Accessibility)

### 6.1 색상 대비 (Color Contrast)

텍스트와 배경의 대비가 충분해야 합니다.

**WCAG 기준:**
- 일반 텍스트: 최소 4.5:1
- 큰 텍스트 (18px 이상): 최소 3:1

**도구:**
- WebAIM Contrast Checker
- 브라우저 개발자 도구

**예시:**
```css
/* 나쁜 예: 대비 부족 */
.text {
    color: #999;
    background-color: #fff;
}

/* 좋은 예: 충분한 대비 */
.text {
    color: #333;
    background-color: #fff;
}
```

### 6.2 포커스 관리

키보드 사용자를 위한 포커스 스타일이 필요합니다.

```css
/* 기본 포커스 (브라우저 기본) */
.button:focus {
    outline: 2px solid blue;
}

/* 커스텀 포커스 */
.button:focus {
    outline: 3px solid #3498db;
    outline-offset: 2px;
    box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.3);
}

/* 포커스 제거 (비권장) */
.button:focus {
    outline: none;  /* 접근성 문제 */
}

/* 대안: 명확한 포커스 */
.button:focus {
    outline: none;
    box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.5);
}
```

### 6.3 스킵 링크

키보드 사용자가 메인 콘텐츠로 바로 이동할 수 있는 링크입니다.

```html
<a href="#main-content" class="skip-link">메인 콘텐츠로 건너뛰기</a>
```

```css
.skip-link {
    position: absolute;
    top: -40px;
    left: 0;
    background: #000;
    color: #fff;
    padding: 8px;
    text-decoration: none;
}

.skip-link:focus {
    top: 0;
}
```

### 6.4 접근성 체크리스트

- [ ] 색상 대비가 충분한가?
- [ ] 포커스 스타일이 명확한가?
- [ ] 키보드만으로 모든 기능 사용 가능한가?
- [ ] 스크린 리더가 이해할 수 있는가?
- [ ] 텍스트 크기 조절이 가능한가?

---

## 7. 성능 고려사항

### 7.1 리플로우와 리페인트 최소화

**비용이 높은 속성:**
- `width`, `height`, `margin`, `padding`
- `top`, `left`, `right`, `bottom`
- `display`, `position`

**비용이 낮은 속성:**
- `transform`, `opacity`
- `filter`, `will-change`

### 7.2 GPU 가속 활용

```css
.element {
    transform: translateZ(0);  /* GPU 가속 활성화 */
    will-change: transform;    /* 브라우저에 힌트 */
}
```

### 7.3 애니메이션 최적화

**좋은 예:**
```css
.element {
    transform: translateX(100px);  /* GPU 가속 */
    opacity: 0.5;                   /* GPU 가속 */
}
```

**나쁜 예:**
```css
.element {
    left: 100px;  /* 리플로우 발생 */
    width: 200px; /* 리플로우 발생 */
}
```

### 7.4 CSS 최적화 팁

- 불필요한 선택자 제거
- 중복 스타일 제거
- 미사용 CSS 제거
- Critical CSS 인라인
- CSS 압축 (minify)

### 7.5 will-change

브라우저에 변경될 속성을 미리 알려줍니다.

```css
.element {
    will-change: transform;  /* transform이 변경될 예정 */
}

.element:hover {
    transform: scale(1.2);
}
```

**주의사항:**
- 필요한 경우에만 사용
- 사용 후 제거 권장

---

## 8. 실전 UI 패턴

### 8.1 호버 효과

```css
.button {
    background-color: #3498db;
    transition: background-color 0.3s, transform 0.2s;
}

.button:hover {
    background-color: #2980b9;
    transform: translateY(-2px);
}
```

### 8.2 로딩 애니메이션

```css
@keyframes spin {
    to { transform: rotate(360deg); }
}

.spinner {
    animation: spin 1s linear infinite;
}
```

### 8.3 모달 애니메이션

```css
@keyframes fadeIn {
    from {
        opacity: 0;
        transform: scale(0.9);
    }
    to {
        opacity: 1;
        transform: scale(1);
    }
}

.modal {
    animation: fadeIn 0.3s ease-out;
}
```

### 8.4 카드 호버 효과

```css
.card {
    transition: transform 0.3s, box-shadow 0.3s;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0,0,0,0.2);
}
```

---

## 9. 실습 체크리스트

### 트랜지션/트랜스폼/애니메이션
- [ ] transition을 사용하여 부드러운 효과를 만들 수 있는가?
- [ ] transform으로 요소를 변형할 수 있는가?
- [ ] @keyframes로 애니메이션을 만들 수 있는가?

### CSS 변수
- [ ] CSS 변수를 정의하고 사용할 수 있는가?
- [ ] calc()와 조합하여 사용할 수 있는가?
- [ ] JavaScript로 동적 변경할 수 있는가?

### 네이밍 전략
- [ ] BEM 방법론을 이해하고 적용할 수 있는가?
- [ ] 의미 있는 클래스 이름을 지을 수 있는가?

### 접근성
- [ ] 색상 대비를 고려할 수 있는가?
- [ ] 포커스 스타일을 올바르게 설정할 수 있는가?

### 성능
- [ ] 성능에 좋은 속성을 선택할 수 있는가?
- [ ] GPU 가속을 활용할 수 있는가?

---

## 다음 단계
- CSS 프레임워크 (Bootstrap, Tailwind)
- CSS 전처리기 (Sass, Less)
- 실전 프로젝트
- 고급 애니메이션 기법


