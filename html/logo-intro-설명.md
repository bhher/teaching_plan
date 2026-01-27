# 로고 인트로 애니메이션 설명

## 📋 생성된 파일

1. **`logo-intro-animation.html`** - CSS 기반 로고 애니메이션 (기본 버전)
2. **`logo-intro-svg.html`** - SVG 기반 로고 애니메이션 (고급 버전)
3. **`logo-intro-simple.html`** - 간단한 로고 애니메이션 (실용 버전)

---

## 🎬 애니메이션 시퀀스

### 기본 흐름

1. **원이 굴러옴** (0~2초)
   - 왼쪽에서 오른쪽으로 굴러오면서 2바퀴 회전
   - 중앙에 도착하여 멈춤

2. **직선들이 그려짐** (2~4.5초)
   - 위쪽 수평선 (2초)
   - 아래쪽 수평선 (2.5초)
   - 왼쪽 수직선 (3초)
   - 오른쪽 수직선 (3.5초)
   - 대각선들 (4~4.5초)

3. **텍스트 나타남** (5초 이후)
   - 로고 텍스트 페이드인
   - 서브 텍스트 페이드인
   - 버튼들 페이드인

---

## 📝 각 파일 상세 설명

### 1. logo-intro-animation.html (기본 버전)

**특징:**
- CSS만으로 구현
- 원 + 직선 조합
- 대각선 포함

**주요 애니메이션:**

```css
/* 원이 굴러오는 애니메이션 */
@keyframes rollIn {
    0% {
        left: -150px;
        transform: translateY(-50%) rotate(0deg);
    }
    100% {
        left: 100px;
        transform: translateY(-50%) rotate(720deg);
    }
}

/* 직선이 그려지는 애니메이션 */
@keyframes drawHorizontalTop {
    0% { width: 0; opacity: 0; }
    100% { width: 200px; opacity: 1; }
}
```

**사용 기술:**
- `transform: rotate()` - 회전
- `transform: translateX/Y()` - 이동
- `width/height` 변화로 그리기 효과
- `opacity` 변화로 나타나기 효과

---

### 2. logo-intro-svg.html (SVG 버전)

**특징:**
- SVG `<line>` 요소 사용
- `stroke-dasharray`와 `stroke-dashoffset`로 그리기 효과
- 더 부드러운 선 그리기

**주요 애니메이션:**

```css
.line-path {
    stroke-dasharray: 1000;
    stroke-dashoffset: 1000;
    animation: drawLine 1s ease-out forwards;
}

@keyframes drawLine {
    0% {
        stroke-dashoffset: 1000; /* 선이 보이지 않음 */
        opacity: 0;
    }
    100% {
        stroke-dashoffset: 0; /* 선이 완전히 그려짐 */
        opacity: 1;
    }
}
```

**SVG 장점:**
- 벡터 그래픽으로 확대해도 깨지지 않음
- 선 그리기 효과가 더 자연스러움
- 복잡한 경로도 표현 가능

**SVG 구조:**
```html
<svg viewBox="0 0 400 400">
    <circle class="rolling-circle" cx="200" cy="200" r="50" />
    <line class="line-path line-top" x1="100" y1="100" x2="300" y2="100" />
    <!-- ... -->
</svg>
```

---

### 3. logo-intro-simple.html (간단한 버전)

**특징:**
- 가장 실용적인 버전
- 원 + 4방향 직선만 사용
- 깔끔하고 명확한 로고 형태

**애니메이션 순서:**
1. 원이 굴러와서 중앙에 멈춤 (1.5초)
2. 위쪽 직선 그려짐 (1.8초)
3. 아래쪽 직선 그려짐 (2.2초)
4. 왼쪽 직선 그려짐 (2.6초)
5. 오른쪽 직선 그려짐 (3초)
6. 텍스트 나타남 (3.5초 이후)

---

## 🔧 주요 CSS 속성 설명

### 1. `transform: rotate()`

```css
transform: rotate(720deg); /* 2바퀴 회전 */
```

- 원이 굴러가는 효과를 위해 회전 사용
- `720deg` = 2바퀴

---

### 2. `stroke-dasharray`와 `stroke-dashoffset`

```css
stroke-dasharray: 1000;    /* 점선 패턴 길이 */
stroke-dashoffset: 1000;   /* 점선 시작 위치 */
```

**동작 원리:**
- `stroke-dasharray`: 점선 패턴의 길이 설정
- `stroke-dashoffset`: 점선의 시작 위치를 이동
- `offset`을 줄이면 선이 그려지는 것처럼 보임

**예시:**
```
초기: [----] (보이지 않음, offset=1000)
중간: [--] (일부 보임, offset=500)
최종: [----] (완전히 보임, offset=0)
```

---

### 3. `animation` 속성

```css
animation: rollIn 2s ease-out forwards;
```

- `rollIn`: 애니메이션 이름
- `2s`: 지속 시간
- `ease-out`: 타이밍 함수 (빠르게 시작, 느리게 끝)
- `forwards`: 종료 후 마지막 상태 유지

---

### 4. `animation-delay`

```css
animation: drawLine 1s ease-out 2s forwards;
/*                                    ^^^
                                    delay */
```

- 애니메이션 시작을 지연시킴
- 여러 요소가 순차적으로 나타나도록 함

---

## 🎨 커스터마이징 팁

### 1. 색상 변경

```css
/* 원 색상 */
.circle {
    background: #ff6b6b; /* 빨간색으로 변경 */
}

/* 직선 색상 */
.line {
    background: #4ecdc4; /* 청록색으로 변경 */
}
```

---

### 2. 속도 조절

```css
/* 더 빠르게 */
animation: rollIn 1s ease-out forwards; /* 2s → 1s */

/* 더 느리게 */
animation: rollIn 3s ease-out forwards; /* 2s → 3s */
```

---

### 3. 원 크기 변경

```css
.circle {
    width: 150px;  /* 120px → 150px */
    height: 150px;
}
```

---

### 4. 직선 두께 변경

```css
.line {
    height: 10px;  /* 6px → 10px (수평선) */
    width: 10px;   /* 6px → 10px (수직선) */
}
```

---

### 5. 로고 형태 변경

**예: 원 안에 별 만들기**
```html
<div class="logo-area">
    <div class="circle"></div>
    <!-- 별 모양 직선들 추가 -->
    <div class="line line-star-1"></div>
    <div class="line line-star-2"></div>
    <!-- ... -->
</div>
```

---

## 📱 반응형 디자인

모든 버전에 반응형 디자인이 포함되어 있습니다:

```css
@media (max-width: 768px) {
    .logo-area {
        width: 250px;
        height: 250px;
    }
    
    .circle {
        width: 100px;
        height: 100px;
    }
    
    .logo-text {
        font-size: 2.5rem;
    }
}
```

---

## 🚀 활용 예시

1. **웹사이트 로고 인트로**
   - 페이지 로드 시 로고 애니메이션 표시

2. **앱 스플래시 화면**
   - 앱 시작 시 로고 애니메이션

3. **프레젠테이션**
   - 슬라이드 시작 시 로고 소개

4. **브랜드 소개**
   - 브랜드 로고를 동적으로 표현

---

## 💡 학습 포인트

1. **CSS 애니메이션**
   - `@keyframes`로 애니메이션 정의
   - `animation` 속성으로 적용
   - `transform`으로 변형 효과

2. **SVG 애니메이션**
   - SVG 요소 사용법
   - `stroke-dasharray/offset`로 그리기 효과

3. **타이밍 제어**
   - `animation-delay`로 순차적 애니메이션
   - 여러 요소의 동기화

4. **반응형 디자인**
   - 미디어 쿼리로 모바일 대응

---

## 🔄 애니메이션 재시작

JavaScript로 애니메이션을 재시작할 수 있습니다:

```javascript
// 방법 1: 페이지 새로고침
location.reload();

// 방법 2: CSS 클래스 토글
element.classList.remove('animation-class');
setTimeout(() => {
    element.classList.add('animation-class');
}, 10);
```

---

## 📊 성능 최적화

1. **하드웨어 가속 사용**
   ```css
   .circle {
       will-change: transform; /* 브라우저에 힌트 제공 */
   }
   ```

2. **transform 사용**
   - `left/top` 대신 `transform` 사용 (더 부드러움)

3. **애니메이션 최적화**
   - 불필요한 애니메이션 제거
   - `opacity`와 `transform`만 사용 (GPU 가속)

---

## 🎯 다음 단계

이 코드를 기반으로:
- 더 복잡한 로고 형태 만들기
- 인터랙티브 요소 추가
- 사운드 효과 추가
- 다른 애니메이션 효과 실험

등을 시도해볼 수 있습니다!
