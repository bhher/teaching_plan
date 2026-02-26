# jQuery animate()를 Vanilla JS로 변환 완전 정복

## 📋 목차
1. [개요](#개요)
2. [기본 animate() 변환](#기본-animate-변환)
3. [애니메이션 체이닝](#애니메이션-체이닝)
4. [핵심 개념](#핵심-개념)
5. [Easing 함수](#easing-함수)
6. [실전 예제](#실전-예제)

---

## 개요

이 문서는 jQuery의 `animate()` 메서드를 순수 JavaScript로 변환하는 방법을 설명합니다.

**주요 변환 내용:**
- `animate()` - 여러 속성을 동시에 애니메이션
- 체이닝 - 연속 애니메이션
- Easing 함수 - 애니메이션 속도 곡선

---

## 기본 animate() 변환

### jQuery 코드

```javascript
$('#box1').animate({
    left: "500px",
    width: "200px",
    height: "200px",
    opacity: 0.5
}, 2000);
```

### Vanilla JS 코드

```javascript
animate(box1, {
    left: '500px',
    width: '200px',
    height: '200px',
    opacity: 0.5
}, 2000);
```

---

## 애니메이션 체이닝

### jQuery 코드

```javascript
$('#box1').animate({
    left: "500px",
    width: "200px",
    height: "200px",
    opacity: 0.5
}, 2000).animate({
    left: "0",
    width: "100px",
    height: "100px",
    opacity: 1
}, 1000);
```

### Vanilla JS 코드 (Promise 사용)

```javascript
animate(box1, {
    left: '500px',
    width: '200px',
    height: '200px',
    opacity: 0.5
}, 2000).then(function() {
    // 첫 번째 애니메이션 완료 후 두 번째 애니메이션 실행
    return animate(box1, {
        left: '0',
        width: '100px',
        height: '100px',
        opacity: 1
    }, 1000);
});
```

---

## 핵심 개념

### 1. animate() 함수 구현

```javascript
function animate(element, properties, duration, easing) {
    return new Promise(function(resolve) {
        const startTime = performance.now();
        const startValues = {};
        const endValues = {};
        
        // 시작값과 끝값 저장
        for (let prop in properties) {
            const computedStyle = window.getComputedStyle(element);
            
            // 시작값 가져오기
            if (prop === 'opacity') {
                startValues[prop] = parseFloat(computedStyle.opacity) || 1;
            } else {
                const value = computedStyle[prop] || '0px';
                startValues[prop] = parseFloat(value) || 0;
            }
            
            // 끝값 저장
            endValues[prop] = parseFloat(properties[prop]);
        }
        
        // 애니메이션 루프
        function animationFrame(currentTime) {
            const elapsed = currentTime - startTime;
            const progress = Math.min(elapsed / duration, 1);
            const easedProgress = ease(progress);
            
            // 각 속성 애니메이션
            for (let prop in properties) {
                const start = startValues[prop];
                const end = endValues[prop];
                const current = start + (end - start) * easedProgress;
                
                if (prop === 'opacity') {
                    element.style.opacity = current;
                } else {
                    element.style[prop] = current + 'px';
                }
            }
            
            if (progress < 1) {
                requestAnimationFrame(animationFrame);
            } else {
                resolve();
            }
        }
        
        requestAnimationFrame(animationFrame);
    });
}
```

### 2. 보간(Interpolation)

```javascript
const current = start + (end - start) * easedProgress;
```

**설명:**
- `start`: 시작값
- `end`: 끝값
- `easedProgress`: 0~1 사이의 진행률 (easing 적용)
- `current`: 현재 값

**예시:**
```
start = 100, end = 500, progress = 0.5
current = 100 + (500 - 100) * 0.5 = 300
```

### 3. requestAnimationFrame

```javascript
function animationFrame(currentTime) {
    // 애니메이션 로직
    if (progress < 1) {
        requestAnimationFrame(animationFrame);
    }
}

requestAnimationFrame(animationFrame);
```

**설명:**
- 브라우저의 다음 리페인트 전에 실행
- 60fps 목표 (약 16.67ms마다 실행)
- `setTimeout`보다 정확하고 부드러움

### 4. performance.now()

```javascript
const startTime = performance.now();
const elapsed = currentTime - startTime;
```

**설명:**
- 고해상도 타임스탬프 반환
- `Date.now()`보다 정확함
- 밀리초 단위

---

## Easing 함수

### Linear (선형)

```javascript
function linear(t) {
    return t;
}
```

**특징:**
- 일정한 속도
- 자연스럽지 않음

### Ease In Quad

```javascript
function easeInQuad(t) {
    return t * t;
}
```

**특징:**
- 시작이 느리고 점점 빨라짐
- 가속 효과

### Ease Out Quad

```javascript
function easeOutQuad(t) {
    return t * (2 - t);
}
```

**특징:**
- 시작이 빠르고 점점 느려짐
- 감속 효과

### Ease In Out Quad (기본값)

```javascript
function easeInOutQuad(t) {
    return t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t;
}
```

**특징:**
- 시작과 끝이 느리고 중간이 빠름
- 가장 자연스러운 효과

### Easing 함수 비교

| 함수 | 시작 | 중간 | 끝 | 용도 |
|------|------|------|-----|------|
| `linear` | 일정 | 일정 | 일정 | 단순 이동 |
| `easeInQuad` | 느림 | 빠름 | 빠름 | 나타나기 |
| `easeOutQuad` | 빠름 | 빠름 | 느림 | 사라지기 |
| `easeInOutQuad` | 느림 | 빠름 | 느림 | 일반적인 애니메이션 |

---

## 실전 예제

### 예제 1: 기본 애니메이션

```javascript
// jQuery
$('#box').animate({
    left: '500px',
    width: '200px'
}, 1000);

// Vanilla JS
animate(document.getElementById('box'), {
    left: '500px',
    width: '200px'
}, 1000);
```

### 예제 2: 연속 애니메이션

```javascript
// jQuery
$('#box').animate({left: '500px'}, 1000)
         .animate({top: '300px'}, 1000)
         .animate({left: '0'}, 1000);

// Vanilla JS
animate(box, {left: '500px'}, 1000)
    .then(() => animate(box, {top: '300px'}, 1000))
    .then(() => animate(box, {left: '0'}, 1000));
```

### 예제 3: 여러 속성 동시 애니메이션

```javascript
// jQuery
$('#box').animate({
    left: '500px',
    width: '200px',
    height: '200px',
    opacity: 0.5
}, 2000);

// Vanilla JS
animate(box, {
    left: '500px',
    width: '200px',
    height: '200px',
    opacity: 0.5
}, 2000);
```

### 예제 4: 커스텀 Easing 사용

```javascript
// Vanilla JS
animate(box, {
    left: '500px'
}, 1000, easeInCubic); // 커스텀 easing 함수 사용
```

---

## 속성 변환

### 지원하는 속성

| 속성 | 단위 | 설명 |
|------|------|------|
| `left`, `right` | px | 위치 |
| `top`, `bottom` | px | 위치 |
| `width`, `height` | px | 크기 |
| `opacity` | 0~1 | 투명도 |
| `marginLeft`, `marginTop` 등 | px | 마진 |
| `paddingLeft`, `paddingTop` 등 | px | 패딩 |

### 주의사항

**1. position 속성 필요:**
```css
#box {
    position: relative; /* 또는 absolute, fixed */
}
```

**2. 초기값 설정:**
- `left`, `top` 등은 초기값이 없으면 `auto`로 계산됨
- `getComputedStyle()`로 현재값 가져오기

**3. 단위 처리:**
```javascript
// px 단위 속성
element.style.left = current + 'px';

// opacity (단위 없음)
element.style.opacity = current;
```

---

## 성능 최적화

### 1. GPU 가속 활용

```javascript
// transform 사용 (GPU 가속)
element.style.transform = `translateX(${current}px)`;

// left 사용 (CPU)
element.style.left = current + 'px';
```

**권장:**
- `left`, `top` 대신 `transform: translateX/Y` 사용
- 더 부드러운 애니메이션

### 2. will-change 힌트

```css
#box {
    will-change: transform, opacity;
}
```

**설명:**
- 브라우저에 변경 예정 속성 알림
- 최적화 힌트 제공

---

## 마무리

jQuery `animate()`를 Vanilla JS로 변환하는 핵심은:

1. ✅ **requestAnimationFrame**: 부드러운 애니메이션
2. ✅ **보간(Interpolation)**: 시작값과 끝값 사이 계산
3. ✅ **Easing 함수**: 자연스러운 속도 곡선
4. ✅ **Promise**: 체이닝 지원
5. ✅ **getComputedStyle()**: 현재값 가져오기

이제 jQuery 없이도 모든 애니메이션을 구현할 수 있습니다!
