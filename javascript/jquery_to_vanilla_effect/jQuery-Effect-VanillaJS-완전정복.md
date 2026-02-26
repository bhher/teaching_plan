# jQuery Effect를 Vanilla JS로 변환 완전 정복

## 📋 목차
1. [개요](#개요)
2. [기본 효과 변환](#기본-효과-변환)
3. [슬라이드 효과 변환](#슬라이드-효과-변환)
4. [페이드 효과 변환](#페이드-효과-변환)
5. [효과 함수 구현](#효과-함수-구현)
6. [변환 비교표](#변환-비교표)

---

## 개요

이 문서는 jQuery의 효과 메서드(`show`, `hide`, `toggle`, `slideUp`, `slideDown`, `fadeIn`, `fadeOut` 등)를 순수 JavaScript로 변환하는 방법을 설명합니다.

**주요 변환 내용:**
- `show()` / `hide()` / `toggle()` - 표시/숨김 효과
- `slideUp()` / `slideDown()` / `slideToggle()` - 슬라이드 효과
- `fadeIn()` / `fadeOut()` / `fadeToggle()` - 페이드 효과
- `fadeTo()` - 특정 투명도로 설정

---

## 기본 효과 변환

### 1. hide() 효과

**jQuery:**
```javascript
$(".box1").hide("slow");
```

**Vanilla JS:**
```javascript
function hide(element, duration) {
    const dur = parseDuration(duration);
    element.style.transition = `opacity ${dur}ms ease`;
    element.style.opacity = '0';
    
    setTimeout(function() {
        element.style.display = 'none';
    }, dur);
}

hide(box1, 'slow');
```

**동작 과정:**
1. `opacity`를 0으로 애니메이션
2. 애니메이션 완료 후 `display: none` 설정

### 2. show() 효과

**jQuery:**
```javascript
$(".box1").show(1000);
```

**Vanilla JS:**
```javascript
function show(element, duration) {
    const dur = parseDuration(duration);
    element.style.display = 'block';
    element.style.opacity = '0';
    
    // 다음 프레임에서 opacity 애니메이션 시작
    requestAnimationFrame(function() {
        element.style.transition = `opacity ${dur}ms ease`;
        element.style.opacity = '1';
    });
}

show(box1, 1000);
```

**동작 과정:**
1. `display: block` 설정
2. `opacity: 0`으로 초기화
3. `requestAnimationFrame`으로 다음 프레임에서 `opacity: 1`로 애니메이션

**requestAnimationFrame이 필요한 이유:**
- 브라우저가 스타일 변경을 배치 처리하기 때문
- 즉시 `opacity: 1`을 설정하면 애니메이션이 보이지 않음

### 3. toggle() 효과

**jQuery:**
```javascript
$(".box2").toggle(500);
```

**Vanilla JS:**
```javascript
function toggle(element, duration) {
    const isHidden = element.style.display === 'none' || 
                    window.getComputedStyle(element).display === 'none';
    
    if (isHidden) {
        show(element, duration);
    } else {
        hide(element, duration);
    }
}

toggle(box2, 500);
```

**설명:**
- 요소의 현재 상태 확인
- 숨겨져 있으면 `show()`, 보이면 `hide()` 호출

---

## 슬라이드 효과 변환

### 1. slideUp() 효과

**jQuery:**
```javascript
$(this).parent().next().slideUp(500);
```

**Vanilla JS:**
```javascript
function slideUp(element, duration) {
    const dur = parseDuration(duration);
    const height = element.scrollHeight;
    
    element.style.height = height + 'px';
    element.style.overflow = 'hidden';
    element.style.transition = `height ${dur}ms ease`;
    
    // 다음 프레임에서 높이 애니메이션 시작
    requestAnimationFrame(function() {
        element.style.height = '0px';
    });
    
    setTimeout(function() {
        element.style.display = 'none';
        element.style.height = '';
    }, dur);
}

const target = btn.parentElement.nextElementSibling;
slideUp(target, 500);
```

**동작 과정:**
1. 현재 높이(`scrollHeight`) 저장
2. `height`를 현재 높이로 설정
3. `overflow: hidden` 설정
4. `requestAnimationFrame`으로 다음 프레임에서 `height: 0`으로 애니메이션
5. 애니메이션 완료 후 `display: none` 설정

### 2. slideDown() 효과

**jQuery:**
```javascript
$(this).parent().next().slideDown("fast");
```

**Vanilla JS:**
```javascript
function slideDown(element, duration) {
    const dur = parseDuration(duration);
    const height = element.scrollHeight;
    
    element.style.display = 'block';
    element.style.height = '0px';
    element.style.overflow = 'hidden';
    element.style.transition = `height ${dur}ms ease`;
    
    // 다음 프레임에서 높이 애니메이션 시작
    requestAnimationFrame(function() {
        element.style.height = height + 'px';
    });
    
    setTimeout(function() {
        element.style.height = '';
        element.style.overflow = '';
    }, dur);
}

const target = btn.parentElement.nextElementSibling;
slideDown(target, 'fast');
```

**동작 과정:**
1. `display: block` 설정
2. `height: 0`으로 초기화
3. `overflow: hidden` 설정
4. `requestAnimationFrame`으로 다음 프레임에서 실제 높이로 애니메이션
5. 애니메이션 완료 후 `height`와 `overflow` 초기화

### 3. slideToggle() 효과

**jQuery:**
```javascript
$(this).parent().next().slideToggle("fast");
```

**Vanilla JS:**
```javascript
function slideToggle(element, duration) {
    const isHidden = element.style.display === 'none' || 
                    window.getComputedStyle(element).display === 'none';
    
    if (isHidden) {
        slideDown(element, duration);
    } else {
        slideUp(element, duration);
    }
}

const target = btn.parentElement.nextElementSibling;
slideToggle(target, 'fast');
```

---

## 페이드 효과 변환

### 1. fadeOut() 효과

**jQuery:**
```javascript
$(this).parent().next().fadeOut(1000);
```

**Vanilla JS:**
```javascript
function fadeOut(element, duration) {
    const dur = parseDuration(duration);
    element.style.transition = `opacity ${dur}ms ease`;
    element.style.opacity = '0';
    
    setTimeout(function() {
        element.style.display = 'none';
    }, dur);
}

const target = btn.parentElement.nextElementSibling;
fadeOut(target, 1000);
```

### 2. fadeIn() 효과

**jQuery:**
```javascript
$(this).parent().next().fadeIn('slow');
```

**Vanilla JS:**
```javascript
function fadeIn(element, duration) {
    const dur = parseDuration(duration);
    element.style.display = 'block';
    element.style.opacity = '0';
    
    // 다음 프레임에서 opacity 애니메이션 시작
    requestAnimationFrame(function() {
        element.style.transition = `opacity ${dur}ms ease`;
        element.style.opacity = '1';
    });
}

const target = btn.parentElement.nextElementSibling;
fadeIn(target, 'slow');
```

### 3. fadeToggle() 효과

**jQuery:**
```javascript
$(this).parent().next().fadeToggle("normal");
```

**Vanilla JS:**
```javascript
function fadeToggle(element, duration) {
    const isHidden = element.style.display === 'none' || 
                    window.getComputedStyle(element).display === 'none';
    
    if (isHidden) {
        fadeIn(element, duration);
    } else {
        fadeOut(element, duration);
    }
}

const target = btn.parentElement.nextElementSibling;
fadeToggle(target, 'normal');
```

### 4. fadeTo() 효과

**jQuery:**
```javascript
$(this).parent().next().fadeTo("slow", 0.3);
$(this).parent().next().fadeTo("slow", 1);
```

**Vanilla JS:**
```javascript
function fadeTo(element, duration, opacity) {
    const dur = parseDuration(duration);
    element.style.display = 'block';
    element.style.transition = `opacity ${dur}ms ease`;
    element.style.opacity = opacity;
    
    // opacity가 0이면 완료 후 display: none
    if (opacity === 0) {
        setTimeout(function() {
            element.style.display = 'none';
        }, dur);
    }
}

const target = btn.parentElement.nextElementSibling;
fadeTo(target, 'slow', 0.3); // 30% 투명도
fadeTo(target, 'slow', 1);   // 완전히 불투명
```

**설명:**
- 특정 `opacity` 값으로 애니메이션
- `opacity`는 0~1 사이의 값
- `opacity: 0`이면 완료 후 `display: none` 설정

---

## 효과 함수 구현

### 시간 변환 함수

```javascript
function parseDuration(duration) {
    if (typeof duration === 'string') {
        switch(duration) {
            case 'fast': return 200;
            case 'normal': return 400;
            case 'slow': return 600;
            default: return 400;
        }
    }
    return duration || 400;
}
```

**jQuery 시간 값:**
- `'fast'`: 200ms
- `'normal'`: 400ms
- `'slow'`: 600ms
- 숫자: 밀리초 단위 (예: `1000` = 1초)

### 요소 상태 확인

```javascript
function isHidden(element) {
    return element.style.display === 'none' || 
           window.getComputedStyle(element).display === 'none';
}
```

**설명:**
- `style.display`: 인라인 스타일 확인
- `getComputedStyle()`: 계산된 스타일 확인 (CSS 포함)

---

## 변환 비교표

| jQuery | Vanilla JS | 설명 |
|--------|------------|------|
| `.hide(duration)` | `hide(element, duration)` | 숨기기 (opacity) |
| `.show(duration)` | `show(element, duration)` | 보이기 (opacity) |
| `.toggle(duration)` | `toggle(element, duration)` | 토글 (opacity) |
| `.slideUp(duration)` | `slideUp(element, duration)` | 위로 슬라이드 |
| `.slideDown(duration)` | `slideDown(element, duration)` | 아래로 슬라이드 |
| `.slideToggle(duration)` | `slideToggle(element, duration)` | 슬라이드 토글 |
| `.fadeOut(duration)` | `fadeOut(element, duration)` | 페이드 아웃 |
| `.fadeIn(duration)` | `fadeIn(element, duration)` | 페이드 인 |
| `.fadeToggle(duration)` | `fadeToggle(element, duration)` | 페이드 토글 |
| `.fadeTo(duration, opacity)` | `fadeTo(element, duration, opacity)` | 특정 투명도로 |

---

## 핵심 개념

### 1. requestAnimationFrame

```javascript
element.style.opacity = '0';
requestAnimationFrame(function() {
    element.style.transition = 'opacity 500ms ease';
    element.style.opacity = '1';
});
```

**설명:**
- 브라우저의 다음 리페인트 전에 실행
- 스타일 변경을 배치 처리하여 애니메이션이 보이도록 함
- `setTimeout`보다 정확하고 부드러움

### 2. scrollHeight

```javascript
const height = element.scrollHeight;
```

**설명:**
- 요소의 실제 콘텐츠 높이 (스크롤 포함)
- `offsetHeight`: 보이는 높이
- `clientHeight`: 패딩 포함 높이
- `scrollHeight`: 전체 콘텐츠 높이

### 3. CSS Transition

```javascript
element.style.transition = 'opacity 500ms ease';
element.style.opacity = '0';
```

**설명:**
- CSS 속성 변화에 애니메이션 적용
- `transition: property duration timing-function`
- `ease`: 기본 이징 함수

### 4. getComputedStyle()

```javascript
const display = window.getComputedStyle(element).display;
```

**설명:**
- 요소의 계산된 스타일 가져오기
- CSS와 인라인 스타일 모두 고려
- 읽기 전용

---

## 실전 예제

### 예제 1: 간단한 hide/show

```javascript
// hide
function hide(element, duration = 400) {
    element.style.transition = `opacity ${duration}ms ease`;
    element.style.opacity = '0';
    setTimeout(() => {
        element.style.display = 'none';
    }, duration);
}

// show
function show(element, duration = 400) {
    element.style.display = 'block';
    element.style.opacity = '0';
    requestAnimationFrame(() => {
        element.style.transition = `opacity ${duration}ms ease`;
        element.style.opacity = '1';
    });
}
```

### 예제 2: 슬라이드 효과

```javascript
function slideUp(element, duration = 400) {
    const height = element.scrollHeight;
    element.style.height = height + 'px';
    element.style.overflow = 'hidden';
    element.style.transition = `height ${duration}ms ease`;
    
    requestAnimationFrame(() => {
        element.style.height = '0px';
    });
    
    setTimeout(() => {
        element.style.display = 'none';
        element.style.height = '';
    }, duration);
}
```

### 예제 3: 페이드 효과

```javascript
function fadeOut(element, duration = 400) {
    element.style.transition = `opacity ${duration}ms ease`;
    element.style.opacity = '0';
    
    setTimeout(() => {
        element.style.display = 'none';
    }, duration);
}

function fadeIn(element, duration = 400) {
    element.style.display = 'block';
    element.style.opacity = '0';
    
    requestAnimationFrame(() => {
        element.style.transition = `opacity ${duration}ms ease`;
        element.style.opacity = '1';
    });
}
```

---

## 마무리

jQuery Effect를 Vanilla JS로 변환하는 핵심은:

1. ✅ **CSS Transition**: 부드러운 애니메이션
2. ✅ **requestAnimationFrame**: 애니메이션 시작 타이밍 제어
3. ✅ **scrollHeight**: 실제 콘텐츠 높이 측정
4. ✅ **setTimeout**: 애니메이션 완료 후 처리
5. ✅ **getComputedStyle()**: 요소 상태 확인

이제 jQuery 없이도 모든 효과를 구현할 수 있습니다!
