# CSS Animation 인트로 페이지 상세 설명

## 📋 목차
1. [전체 구조](#1-전체-구조)
2. [CSS 애니메이션 종류](#2-css-애니메이션-종류)
3. [주요 기능 설명](#3-주요-기능-설명)
4. [JavaScript 기능](#4-javascript-기능)
5. [반응형 디자인](#5-반응형-디자인)

---

## 1. 전체 구조

### HTML 구조
```html
<body>
  <div class="particles">        <!-- 배경 파티클 -->
  <div class="container">        <!-- 메인 컨테이너 -->
    <div class="spinner">        <!-- 로딩 스피너 -->
    <div class="logo">           <!-- 로고 -->
    <h1 class="title">           <!-- 메인 타이틀 -->
    <p class="subtitle">         <!-- 서브타이틀 -->
    <div class="typing-text">    <!-- 타이핑 텍스트 -->
    <div class="button-group">   <!-- 버튼 그룹 -->
</body>
```

---

## 2. CSS 애니메이션 종류

### 2.1 배경 그라데이션 애니메이션

```css
body {
    background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
    background-size: 400% 400%;
    animation: gradientShift 15s ease infinite;
}
```

**설명:**
- **4가지 색상**의 그라데이션 배경
- `background-size: 400% 400%`: 배경을 4배 크기로 설정 (애니메이션 공간 확보)
- `animation: gradientShift 15s ease infinite`: 15초 동안 무한 반복

**@keyframes gradientShift:**
```css
@keyframes gradientShift {
    0% { background-position: 0% 50%; }    /* 시작 위치 */
    50% { background-position: 100% 50%; } /* 중간 위치 */
    100% { background-position: 0% 50%; }  /* 원래 위치로 복귀 */
}
```

**효과:** 배경이 부드럽게 좌우로 움직이며 색상이 변화합니다.

---

### 2.2 로딩 스피너 애니메이션

```css
.spinner {
    width: 80px;
    height: 80px;
    border: 8px solid rgba(255, 255, 255, 0.3);
    border-top: 8px solid #fff;
    border-radius: 50%;
    animation: spin 1s linear infinite;
}
```

**설명:**
- 원형 테두리 중 **윗부분만 흰색**으로 설정
- 나머지는 반투명 흰색
- `border-radius: 50%`: 완전한 원형

**@keyframes spin:**
```css
@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
```

**효과:** 시계방향으로 계속 회전하는 로딩 스피너

---

### 2.3 페이드인 업 애니메이션 (Fade In Up)

```css
.title {
    opacity: 0;
    animation: fadeInUp 1s ease 0.5s forwards;
}
```

**설명:**
- `opacity: 0`: 처음에는 보이지 않음
- `animation: fadeInUp 1s ease 0.5s forwards`
  - `1s`: 애니메이션 지속 시간
  - `ease`: 부드러운 가속/감속
  - `0.5s`: 0.5초 후 시작 (delay)
  - `forwards`: 애니메이션 종료 후 마지막 상태 유지

**@keyframes fadeInUp:**
```css
@keyframes fadeInUp {
    from {
        opacity: 0;
        transform: translateY(30px);  /* 아래 30px에서 시작 */
    }
    to {
        opacity: 1;
        transform: translateY(0);      /* 원래 위치로 */
    }
}
```

**효과:** 아래에서 위로 올라오며 나타나는 효과

**사용 위치:**
- `.title` (0.5초 후 시작)
- `.subtitle` (1초 후 시작)
- `.button-group` (2초 후 시작)

---

### 2.4 스케일 인 애니메이션 (Scale In)

```css
.logo {
    opacity: 0;
    animation: scaleIn 0.8s ease 0.2s forwards;
}
```

**@keyframes scaleIn:**
```css
@keyframes scaleIn {
    from {
        opacity: 0;
        transform: scale(0.5);  /* 50% 크기에서 시작 */
    }
    to {
        opacity: 1;
        transform: scale(1);     /* 원래 크기로 */
    }
}
```

**효과:** 작은 크기에서 커지며 나타나는 효과

---

### 2.5 펄스 애니메이션 (Pulse)

```css
.pulse {
    animation: pulse 2s ease-in-out infinite;
}
```

**@keyframes pulse:**
```css
@keyframes pulse {
    0%, 100% { transform: scale(1); }    /* 원래 크기 */
    50% { transform: scale(1.1); }       /* 110% 크기 */
}
```

**설명:**
- `ease-in-out`: 시작과 끝이 부드러움
- `infinite`: 무한 반복

**효과:** 크기가 커졌다 작아졌다 반복 (심장박동 효과)

---

### 2.6 파티클 플로팅 애니메이션

```css
.particle {
    position: absolute;
    width: 10px;
    height: 10px;
    background: rgba(255, 255, 255, 0.5);
    border-radius: 50%;
    animation: float 15s infinite;
}
```

**@keyframes float:**
```css
@keyframes float {
    0% {
        transform: translateY(100vh) rotate(0deg);  /* 화면 아래에서 시작 */
        opacity: 0;
    }
    10% { opacity: 1; }  /* 나타남 */
    90% { opacity: 1; }   /* 유지 */
    100% {
        transform: translateY(-100px) rotate(360deg); /* 위로 올라가며 회전 */
        opacity: 0;
    }
}
```

**설명:**
- 각 파티클마다 다른 `animation-delay`와 `animation-duration` 설정
- `nth-child()` 선택자로 각각 다른 속도와 시작 시간 부여

**효과:** 배경에 떠다니는 파티클 효과

---

### 2.7 버튼 호버 효과

```css
.btn {
    transition: all 0.3s ease;
}

.btn:hover {
    background: rgba(255, 255, 255, 0.3);
    transform: translateY(-5px);  /* 위로 5px 이동 */
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
}
```

**설명:**
- `transition: all 0.3s ease`: 모든 속성 변화를 0.3초 동안 부드럽게
- `transform: translateY(-5px)`: 마우스 오버 시 위로 이동
- `box-shadow`: 그림자 효과 추가

**효과:** 버튼에 마우스를 올리면 위로 떠오르는 효과

---

### 2.8 깜빡임 애니메이션 (Blink)

```css
.typing-text::after {
    content: '|';
    animation: blink 1s infinite;
}
```

**@keyframes blink:**
```css
@keyframes blink {
    0%, 50% { opacity: 1; }   /* 보임 */
    51%, 100% { opacity: 0; }  /* 숨김 */
}
```

**효과:** 타이핑 커서(|)가 깜빡이는 효과

---

## 3. 주요 기능 설명

### 3.1 Flexbox 레이아웃

```css
.container {
    display: flex;
    flex-direction: column;      /* 세로 방향 */
    justify-content: center;     /* 수직 중앙 정렬 */
    align-items: center;         /* 수평 중앙 정렬 */
    height: 100vh;              /* 화면 전체 높이 */
}
```

**효과:** 모든 요소가 화면 중앙에 정렬됨

---

### 3.2 Backdrop Filter (블러 효과)

```css
.btn {
    backdrop-filter: blur(10px);
}
```

**설명:**
- 배경을 흐리게 만드는 효과
- 반투명 배경과 함께 사용하면 글래스모피즘(Glassmorphism) 효과

---

### 3.3 z-index 레이어링

```css
.particles {
    z-index: -1;  /* 배경 레이어 */
}
```

**설명:**
- 파티클이 콘텐츠 뒤에 위치하도록 설정
- 음수 값으로 배경 레이어로 만듦

---

## 4. JavaScript 기능

### 4.1 타이핑 효과 구현

```javascript
const texts = [
    "환영합니다!",
    "CSS Animation으로 만든",
    "멋진 인트로 페이지입니다.",
    "다양한 효과를 확인해보세요!"
];

let textIndex = 0;      // 현재 텍스트 인덱스
let charIndex = 0;      // 현재 문자 인덱스
let isDeleting = false; // 삭제 중인지 여부
```

**동작 원리:**

1. **타이핑 모드** (`isDeleting = false`)
   - 한 글자씩 추가
   - `charIndex++`
   - 속도: 100ms

2. **삭제 모드** (`isDeleting = true`)
   - 한 글자씩 삭제
   - `charIndex--`
   - 속도: 50ms (더 빠름)

3. **전환 조건**
   - 타이핑 완료 → 2초 대기 → 삭제 모드 시작
   - 삭제 완료 → 다음 텍스트로 이동

**코드 흐름:**
```javascript
function typeText() {
    const currentText = texts[textIndex];
    
    if (isDeleting) {
        // 삭제 중
        typingText.textContent = currentText.substring(0, charIndex - 1);
        charIndex--;
    } else {
        // 타이핑 중
        typingText.textContent = currentText.substring(0, charIndex + 1);
        charIndex++;
    }
    
    // 다음 텍스트로 이동
    if (!isDeleting && charIndex === currentText.length) {
        typeSpeed = 2000;  // 완성 후 2초 대기
        isDeleting = true; // 삭제 모드로 전환
    } else if (isDeleting && charIndex === 0) {
        isDeleting = false;
        textIndex = (textIndex + 1) % texts.length; // 다음 텍스트
    }
    
    setTimeout(typeText, typeSpeed);
}
```

---

### 4.2 버튼 클릭 이벤트

```javascript
document.querySelectorAll('.btn').forEach(btn => {
    btn.addEventListener('click', function(e) {
        e.preventDefault();  // 기본 동작 방지
        this.style.animation = 'pulse 0.5s ease';  // 펄스 효과
        setTimeout(() => {
            this.style.animation = '';  // 애니메이션 제거
        }, 500);
    });
});
```

**효과:** 버튼 클릭 시 펄스 애니메이션 실행

---

## 5. 반응형 디자인

### 5.1 미디어 쿼리

```css
@media (max-width: 768px) {
    .title {
        font-size: 2.5rem;  /* 4rem → 2.5rem */
    }
    
    .subtitle {
        font-size: 1.2rem;   /* 1.5rem → 1.2rem */
    }
    
    .button-group {
        flex-direction: column;  /* 세로 배치 */
        width: 80%;
    }
    
    .btn {
        width: 100%;  /* 전체 너비 */
    }
}
```

**설명:**
- 화면 너비가 768px 이하일 때 적용
- 폰트 크기 축소
- 버튼을 세로로 배치

---

## 6. 애니메이션 타이밍 요약

| 요소 | 애니메이션 | 시작 시간 | 지속 시간 |
|------|-----------|----------|----------|
| 로고 | scaleIn | 0.2초 | 0.8초 |
| 스피너 | spin | 0초 | 1초 (무한) |
| 타이틀 | fadeInUp | 0.5초 | 1초 |
| 서브타이틀 | fadeInUp | 1초 | 1초 |
| 타이핑 텍스트 | fadeIn | 1.5초 | 1초 |
| 버튼 그룹 | fadeInUp | 2초 | 1초 |

**순서:** 로고 → 타이틀 → 서브타이틀 → 타이핑 텍스트 → 버튼

---

## 7. 주요 CSS 속성 설명

### 7.1 `animation` 속성

```css
animation: name duration timing-function delay iteration-count direction fill-mode;
```

**예시:**
```css
animation: fadeInUp 1s ease 0.5s forwards;
```

- `fadeInUp`: 애니메이션 이름
- `1s`: 지속 시간
- `ease`: 타이밍 함수
- `0.5s`: 지연 시간
- `forwards`: 종료 후 상태 유지

---

### 7.2 `transform` 속성

- `translateY(30px)`: Y축으로 이동
- `translateX(-100px)`: X축으로 이동
- `rotate(360deg)`: 회전
- `scale(0.5)`: 크기 조절

---

### 7.3 `transition` 속성

```css
transition: all 0.3s ease;
```

- `all`: 모든 속성에 적용
- `0.3s`: 전환 시간
- `ease`: 타이밍 함수

---

## 8. 학습 포인트

1. **@keyframes**: 애니메이션의 각 단계 정의
2. **animation 속성**: 애니메이션 적용
3. **transform**: 요소 변형 (이동, 회전, 크기)
4. **opacity**: 투명도 조절
5. **transition**: 부드러운 전환 효과
6. **JavaScript**: 동적 타이핑 효과 구현

---

## 9. 활용 예시

이 코드를 활용하여:
- 웹사이트 인트로 페이지
- 로딩 화면
- 랜딩 페이지
- 프레젠테이션 시작 화면

등에 사용할 수 있습니다.

---

## 10. 커스터마이징 팁

1. **색상 변경**: `background` 그라데이션 색상 수정
2. **속도 조절**: `animation-duration` 값 변경
3. **텍스트 변경**: JavaScript의 `texts` 배열 수정
4. **파티클 개수**: HTML의 `.particle` div 개수 조절
5. **폰트 변경**: `font-family` 수정
