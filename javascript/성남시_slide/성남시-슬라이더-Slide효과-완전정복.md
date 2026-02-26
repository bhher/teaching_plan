# 성남시 이미지 슬라이더 - Slide 효과 완전 정복

## 📋 목차
1. [개요](#개요)
2. [Fade vs Slide 효과 비교](#fade-vs-slide-효과-비교)
3. [코드 상세 설명](#코드-상세-설명)
4. [핵심 개념](#핵심-개념)
5. [실행 흐름](#실행-흐름)

---

## 개요

이 프로젝트는 **슬라이드 효과(Slide Effect)**를 사용한 이미지 슬라이더입니다. Fade 효과와 달리 이미지가 좌우로 슬라이드되며 전환됩니다.

**주요 기능:**
- ✅ 좌우 슬라이드 애니메이션
- ✅ 자동 슬라이드
- ✅ 이전/다음 버튼으로 수동 제어
- ✅ 자동 재생 정지/재개 버튼
- ✅ 현재 슬라이드 번호 표시

---

## Fade vs Slide 효과 비교

### Fade 효과 (이전 버전)

```css
.slider li {
    position: absolute;
    opacity: 0;
    transition: opacity 0.5s;
}

.slider li.active {
    opacity: 1;
}
```

**특징:**
- 모든 슬라이드가 같은 위치에 겹쳐있음
- `opacity`로 페이드 인/아웃
- 이미지가 서서히 나타나고 사라짐

### Slide 효과 (현재 버전)

```css
.slider {
    display: flex;
    width: calc(100% * 7);
    transition: transform 0.5s;
}

.slider li {
    width: 960px;
    flex-shrink: 0;
}
```

**특징:**
- 모든 슬라이드가 가로로 나란히 배치
- `transform: translateX()`로 이동
- 이미지가 좌우로 슬라이드됨

---

## 코드 상세 설명

### 1. HTML 구조

```html
<article>
    <ul class="slider" id="slider">
        <li><img src="img/s1.png" alt=""></li>
        <li><img src="img/s2.jpg" alt=""></li>
        <!-- ... -->
    </ul>
</article>
```

**구조:**
- `article`: 슬라이더 컨테이너 (960px × 420px)
- `ul.slider`: 모든 슬라이드를 포함하는 리스트
- `li`: 각 슬라이드 아이템

### 2. CSS 스타일

#### 컨테이너 설정

```css
article {
    width: 960px;
    height: 420px;
    position: relative;
    overflow: hidden;  /* 넘치는 부분 숨김 */
    margin: 0 auto;
}
```

**설명:**
- `overflow: hidden`: 슬라이드가 이동할 때 넘치는 부분 숨김
- `position: relative`: 자식 요소의 기준점

#### 슬라이더 리스트 설정

```css
.slider {
    list-style: none;
    width: 100%;
    height: 100%;
    display: flex;  /* 가로 배치 */
    position: relative;
    transition: transform 0.5s ease-in-out;  /* 부드러운 이동 */
    width: calc(100% * 7); /* 슬라이드 개수만큼 너비 확장 */
}
```

**핵심 개념:**
- `display: flex`: 슬라이드들을 가로로 배치
- `width: calc(100% * 7)`: 7개 슬라이드 × 960px = 6720px
- `transition: transform`: transform 변화에 애니메이션 적용

#### 슬라이드 아이템 설정

```css
.slider li {
    width: 960px;  /* article 너비와 동일 */
    height: 100%;
    flex-shrink: 0;  /* 축소되지 않도록 */
}
```

**설명:**
- `width: 960px`: 각 슬라이드의 너비
- `flex-shrink: 0`: flex 컨테이너에서 축소되지 않도록 설정

### 3. JavaScript 로직

#### 변수 선언

```javascript
const slider = document.getElementById('slider');
const slides = slider.querySelectorAll('li');
const slideWidth = 960; // article 너비와 동일
let currentIndex = 0;
```

**설명:**
- `slideWidth`: 각 슬라이드의 너비 (960px)
- `currentIndex`: 현재 슬라이드 인덱스

#### 슬라이드 표시 함수

```javascript
function showSlide(index) {
    // transform을 사용하여 슬라이드 이동
    const translateX = -index * slideWidth;
    slider.style.transform = `translateX(${translateX}px)`;
    
    // 현재 슬라이드 번호 업데이트
    currentSlideSpan.textContent = index + 1;
}
```

**동작 원리:**

1. **translateX 계산:**
   ```javascript
   translateX = -index * slideWidth
   ```
   - `index = 0`: `translateX = 0px` (첫 번째 슬라이드)
   - `index = 1`: `translateX = -960px` (두 번째 슬라이드)
   - `index = 2`: `translateX = -1920px` (세 번째 슬라이드)

2. **transform 적용:**
   ```javascript
   slider.style.transform = `translateX(${translateX}px)`;
   ```
   - `ul.slider`를 왼쪽으로 이동시켜 해당 슬라이드가 보이도록 함

3. **애니메이션:**
   - CSS의 `transition: transform 0.5s`로 부드러운 이동

**시각적 설명:**

```
초기 상태 (index = 0):
┌─────────────────────────────────────────┐
│ [s1] [s2] [s3] [s4] [s5] [s6] [s7]     │
│  ↑                                     │
│  보이는 부분 (960px)                    │
└─────────────────────────────────────────┘
transform: translateX(0px)

다음 슬라이드 (index = 1):
┌─────────────────────────────────────────┐
│ [s1] [s2] [s3] [s4] [s5] [s6] [s7]     │
│       ↑                                 │
│       보이는 부분 (960px)                │
└─────────────────────────────────────────┘
transform: translateX(-960px)
```

---

## 핵심 개념

### 1. transform: translateX()

```javascript
slider.style.transform = `translateX(-960px)`;
```

**설명:**
- 요소를 X축(가로) 방향으로 이동
- 음수 값: 왼쪽으로 이동
- 양수 값: 오른쪽으로 이동
- `position` 속성과 달리 레이아웃에 영향 없음

**예시:**
```javascript
translateX(0px)    // 원래 위치
translateX(-960px) // 왼쪽으로 960px 이동
translateX(960px)  // 오른쪽으로 960px 이동
```

### 2. CSS Transition

```css
transition: transform 0.5s ease-in-out;
```

**설명:**
- `transform` 속성 변화에 애니메이션 적용
- `0.5s`: 애니메이션 지속 시간
- `ease-in-out`: 시작과 끝이 부드러운 애니메이션

**비교:**
```css
/* Transition 없음: 즉시 이동 */
transform: translateX(-960px);

/* Transition 있음: 부드럽게 이동 */
transition: transform 0.5s;
transform: translateX(-960px);
```

### 3. Flexbox 레이아웃

```css
.slider {
    display: flex;
    width: calc(100% * 7);
}

.slider li {
    flex-shrink: 0;
}
```

**설명:**
- `display: flex`: 가로 배치
- `flex-shrink: 0`: 축소 방지 (각 슬라이드가 960px 유지)

### 4. calc() 함수

```css
width: calc(100% * 7);
```

**설명:**
- CSS 계산 함수
- `100% * 7 = 700%` (7배 너비)
- 슬라이드 개수에 따라 동적으로 계산 가능

---

## 실행 흐름

### 초기화 단계

```
1. DOMContentLoaded 이벤트 발생
   ↓
2. DOM 요소 선택
   ↓
3. 변수 초기화 (currentIndex = 0)
   ↓
4. showSlide(0) 호출
   ↓
   - translateX = -0 * 960 = 0px
   - transform: translateX(0px)
   - 첫 번째 슬라이드 표시
   ↓
5. 자동 재생 시작
```

### 슬라이드 이동 단계

```
사용자가 "다음" 버튼 클릭
   ↓
goToNextSlide() 호출
   ↓
currentIndex++ (0 → 1)
   ↓
showSlide(1) 호출
   ↓
translateX = -1 * 960 = -960px 계산
   ↓
slider.style.transform = 'translateX(-960px)' 설정
   ↓
CSS transition으로 부드럽게 이동 (0.5초)
   ↓
두 번째 슬라이드가 보임
```

### 무한 루프 구현

```
마지막 슬라이드 (index = 6)에서 다음 버튼 클릭
   ↓
currentIndex++ (6 → 7)
   ↓
if (currentIndex >= slides.length) 체크
   ↓
currentIndex = 0 (첫 번째로 돌아감)
   ↓
showSlide(0) 호출
   ↓
translateX = 0px
   ↓
첫 번째 슬라이드로 부드럽게 이동
```

---

## Fade vs Slide 비교표

| 항목 | Fade 효과 | Slide 효과 |
|------|-----------|------------|
| **CSS 속성** | `opacity` | `transform: translateX()` |
| **레이아웃** | `position: absolute` (겹침) | `display: flex` (나란히) |
| **애니메이션** | 페이드 인/아웃 | 좌우 슬라이드 |
| **시각적 효과** | 서서히 나타남/사라짐 | 좌우로 이동 |
| **성능** | 좋음 | 매우 좋음 (GPU 가속) |
| **사용자 경험** | 부드러움 | 동적임 |

---

## 성능 최적화

### GPU 가속 활용

```css
.slider {
    transform: translateX(0px);
    will-change: transform; /* 브라우저에 힌트 제공 */
}
```

**설명:**
- `transform`은 GPU 가속을 받음
- `opacity`보다 성능이 좋음
- `will-change`로 브라우저에 최적화 힌트 제공

### 하드웨어 가속

```css
.slider {
    transform: translateZ(0); /* GPU 가속 활성화 */
}
```

**설명:**
- 3D 변환을 사용하여 GPU 가속 활성화
- 더 부드러운 애니메이션

---

## 추가 개선 사항

### 1. 무한 루프 슬라이더 (Clone 방식)

```javascript
// 첫 번째와 마지막 슬라이드를 복제
const firstClone = slides[0].cloneNode(true);
const lastClone = slides[slides.length - 1].cloneNode(true);

slider.appendChild(firstClone);
slider.insertBefore(lastClone, slides[0]);
```

**장점:**
- 끝에서 끝으로 자연스럽게 이동
- 점프 없이 무한 루프

### 2. 터치 이벤트 지원

```javascript
let startX = 0;
let currentX = 0;

slider.addEventListener('touchstart', function(e) {
    startX = e.touches[0].clientX;
});

slider.addEventListener('touchmove', function(e) {
    currentX = e.touches[0].clientX;
});

slider.addEventListener('touchend', function(e) {
    const diff = startX - currentX;
    if (diff > 50) {
        goToNextSlide();
    } else if (diff < -50) {
        goToPrevSlide();
    }
});
```

**설명:**
- 모바일에서 스와이프 제스처 지원
- 50px 이상 드래그 시 슬라이드 이동

### 3. 키보드 네비게이션

```javascript
document.addEventListener('keydown', function(e) {
    if (e.key === 'ArrowLeft') {
        goToPrevSlide();
    } else if (e.key === 'ArrowRight') {
        goToNextSlide();
    }
});
```

**설명:**
- 좌우 화살표 키로 슬라이드 제어
- 접근성 향상

---

## 마무리

Slide 효과 슬라이더는 **동적이고 시각적으로 매력적인** 사용자 경험을 제공합니다.

**핵심 학습 포인트:**
1. ✅ `transform: translateX()` 사용
2. ✅ Flexbox로 슬라이드 배치
3. ✅ CSS Transition으로 부드러운 애니메이션
4. ✅ 무한 루프 구현
5. ✅ GPU 가속 활용

**Fade vs Slide 선택 가이드:**
- **Fade**: 부드럽고 우아한 효과 (포트폴리오, 갤러리)
- **Slide**: 동적이고 현대적인 효과 (제품 소개, 뉴스)

이제 두 가지 효과를 모두 구현할 수 있습니다!
