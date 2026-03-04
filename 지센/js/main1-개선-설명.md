# main.js → main1.js 개선 설명

## 📋 개선 전후 비교

### ❌ 기존 코드 (main.js)의 문제점

```javascript
// 문제 1: 중복된 함수 정의
function checkItemsVisible() {
    // ... 함수 내용
}

// 문제 2: 사용되지 않는 이벤트 리스너
$(window).on('scroll resize', checkItemsVisible);
$(window).trigger('scroll');

// 문제 3: Deprecated 이벤트 사용
$(document).on('DOMNodeInserted', '.item.visible', function() {
    // DOMNodeInserted는 더 이상 사용되지 않음
});

// 문제 4: 중복된 스크롤 이벤트 핸들러
$(window).on('scroll resize', function() {
    // 위의 checkItemsVisible과 거의 동일한 로직
});
```

**문제점 요약:**
1. ❌ 중복된 스크롤 이벤트 핸들러 (2개)
2. ❌ `checkItemsVisible()` 함수가 정의되었지만 실제로는 사용되지 않음
3. ❌ `DOMNodeInserted` 이벤트 사용 (deprecated, 브라우저에서 제거됨)
4. ❌ 코드가 복잡하고 이해하기 어려움

---

### ✅ 개선된 코드 (main1.js)

```javascript
// 1. 초기 상태 설정: 모든 아이템을 숨김 상태로
$('.item').css({
    'opacity': '0',
    'transform': 'translateY(30px)',
    'transition': 'opacity 0.6s ease, transform 0.6s ease'
});

// 2. 각 아이템에 순차적 딜레이 추가 (선택사항)
$('.item').each(function(index) {
    $(this).css('transition-delay', (index * 0.1) + 's');
});

// 3. 스크롤 이벤트 핸들러 (하나만 사용)
$(window).on('scroll resize', function() {
    $('.item').each(function() {
        // 요소의 위치 정보
        var elementTop = $(this).offset().top;
        var elementBottom = elementTop + $(this).outerHeight();
        
        // 뷰포트의 위치 정보
        var viewportTop = $(window).scrollTop();
        var viewportBottom = viewportTop + $(window).height();
        
        // 요소가 뷰포트 안에 보이는지 확인
        if (elementBottom > viewportTop && elementTop < viewportBottom) {
            // 보이면 페이드인 효과 적용
            $(this).css({
                'opacity': '1',
                'transform': 'translateY(0)'
            });
        }
    });
});

// 4. 페이지 로드 시 한 번 실행
$(window).trigger('scroll');
```

**개선 사항:**
1. ✅ 중복 제거: 스크롤 이벤트 핸들러를 하나로 통합
2. ✅ 불필요한 함수 제거: `checkItemsVisible()` 함수 삭제
3. ✅ Deprecated 이벤트 제거: `DOMNodeInserted` 제거
4. ✅ 명확한 주석: 각 단계별 설명 추가
5. ✅ 간단하고 이해하기 쉬운 구조

---

## 🔍 코드 동작 원리

### 1단계: 초기 상태 설정

```javascript
$('.item').css({
    'opacity': '0',                    // 투명하게 시작
    'transform': 'translateY(30px)',   // 아래로 30px 이동한 상태
    'transition': 'opacity 0.6s ease, transform 0.6s ease'  // 부드러운 전환 효과
});
```

**의미:**
- 모든 `.item` 요소를 처음에는 보이지 않게 설정
- 아래로 30px 이동한 상태에서 시작
- 나중에 보일 때 부드럽게 나타나도록 transition 설정

---

### 2단계: 순차적 딜레이 추가 (선택사항)

```javascript
$('.item').each(function(index) {
    $(this).css('transition-delay', (index * 0.1) + 's');
});
```

**의미:**
- 첫 번째 아이템: 0초 딜레이
- 두 번째 아이템: 0.1초 딜레이
- 세 번째 아이템: 0.2초 딜레이
- ...

**효과:**
- 아이템들이 순차적으로 나타나는 효과
- 더 자연스러운 애니메이션

---

### 3단계: 스크롤 감지 및 애니메이션 적용

```javascript
$(window).on('scroll resize', function() {
    $('.item').each(function() {
        // 요소의 위치 계산
        var elementTop = $(this).offset().top;        // 요소의 상단 위치
        var elementBottom = elementTop + $(this).outerHeight();  // 요소의 하단 위치
        
        // 뷰포트의 위치 계산
        var viewportTop = $(window).scrollTop();      // 현재 스크롤 위치
        var viewportBottom = viewportTop + $(window).height();  // 뷰포트 하단
        
        // 요소가 뷰포트 안에 있는지 확인
        if (elementBottom > viewportTop && elementTop < viewportBottom) {
            // 보이면 페이드인 효과 적용
            $(this).css({
                'opacity': '1',
                'transform': 'translateY(0)'
            });
        }
    });
});
```

**동작 과정:**

```
사용자가 스크롤
    ↓
각 .item 요소를 확인
    ↓
요소의 위치 계산 (상단, 하단)
    ↓
뷰포트의 위치 계산 (상단, 하단)
    ↓
요소가 뷰포트 안에 있는가?
    ↓
YES → opacity: 1, transform: translateY(0) 적용
NO → 아무것도 하지 않음
```

**위치 계산 예시:**

```
┌─────────────────┐ ← viewportTop (스크롤 위치)
│                 │
│   뷰포트 영역    │
│                 │
│                 │
└─────────────────┘ ← viewportBottom
         ↓
    ┌─────────┐
    │ .item   │ ← elementTop
    │         │
    └─────────┘ ← elementBottom
```

**조건 확인:**
- `elementBottom > viewportTop`: 요소의 하단이 뷰포트 상단보다 아래에 있음
- `elementTop < viewportBottom`: 요소의 상단이 뷰포트 하단보다 위에 있음
- 두 조건을 모두 만족하면 → 요소가 뷰포트 안에 보임!

---

### 4단계: 초기 실행

```javascript
$(window).trigger('scroll');
```

**의미:**
- 페이지가 로드되었을 때 스크롤 이벤트를 한 번 실행
- 초기 화면에 이미 보이는 아이템들도 애니메이션이 적용되도록 함

---

## 📊 코드 라인 수 비교

| 항목 | 기존 (main.js) | 개선 (main1.js) | 차이 |
|------|---------------|----------------|------|
| 전체 라인 수 | 128줄 | 95줄 | **-33줄** |
| 스크롤 관련 코드 | 54줄 (74-128) | 30줄 | **-24줄** |
| 중복 코드 | 있음 | 없음 | ✅ |
| 함수 개수 | 1개 (사용 안됨) | 0개 | ✅ |

---

## 🎯 주요 개선 사항 상세

### 1. 중복 제거

**기존:**
```javascript
// 첫 번째 스크롤 핸들러
$(window).on('scroll resize', checkItemsVisible);

// 두 번째 스크롤 핸들러 (거의 동일한 로직)
$(window).on('scroll resize', function() {
    // ... 동일한 로직
});
```

**개선:**
```javascript
// 하나의 스크롤 핸들러만 사용
$(window).on('scroll resize', function() {
    // ... 로직
});
```

**효과:**
- 코드 중복 제거
- 성능 향상 (이벤트 리스너가 하나만 등록됨)
- 유지보수 용이

---

### 2. 불필요한 함수 제거

**기존:**
```javascript
function checkItemsVisible() {
    $('.item').each(function() {
        // ... 로직
        $(this).addClass('visible');  // 클래스만 추가하고 실제 스타일은 적용 안됨
    });
}
```

**문제점:**
- 함수가 정의되었지만 실제로는 사용되지 않음
- `addClass('visible')`만 하고 실제 스타일은 다른 곳에서 적용

**개선:**
- 함수 제거하고 직접 로직 구현
- 더 명확하고 직관적

---

### 3. Deprecated 이벤트 제거

**기존:**
```javascript
$(document).on('DOMNodeInserted', '.item.visible', function() {
    // DOMNodeInserted는 더 이상 사용되지 않음
});
```

**문제점:**
- `DOMNodeInserted` 이벤트는 브라우저에서 제거됨
- 작동하지 않을 수 있음

**개선:**
- 이벤트 제거
- 직접 CSS 스타일 적용으로 대체

---

## 💡 사용 방법

### HTML에서 main1.js 사용하기

```html
<!-- 기존 -->
<script src="js/main.js"></script>

<!-- 변경 -->
<script src="js/main1.js"></script>
```

---

## 🔧 추가 개선 가능 사항

### 1. 성능 최적화 (throttle 사용)

```javascript
// throttle 함수 추가
function throttle(func, wait) {
    var timeout;
    return function() {
        var context = this, args = arguments;
        if (!timeout) {
            timeout = setTimeout(function() {
                timeout = null;
                func.apply(context, args);
            }, wait);
        }
    };
}

// 스크롤 이벤트에 throttle 적용
$(window).on('scroll resize', throttle(function() {
    // ... 기존 로직
}, 100));  // 100ms마다 최대 1번 실행
```

**효과:**
- 스크롤 이벤트가 너무 자주 발생하는 것을 방지
- 성능 향상

---

### 2. CSS 클래스 사용 (권장)

**JavaScript:**
```javascript
// CSS 클래스만 추가/제거
if (elementBottom > viewportTop && elementTop < viewportBottom) {
    $(this).addClass('visible');
}
```

**CSS:**
```css
.item {
    opacity: 0;
    transform: translateY(30px);
    transition: opacity 0.6s ease, transform 0.6s ease;
}

.item.visible {
    opacity: 1;
    transform: translateY(0);
}
```

**장점:**
- 스타일과 로직 분리
- CSS에서 애니메이션 제어 가능
- 더 깔끔한 코드

---

## ✅ 요약

### 개선 전 (main.js)
- ❌ 중복된 코드
- ❌ 사용되지 않는 함수
- ❌ Deprecated 이벤트
- ❌ 복잡하고 이해하기 어려움

### 개선 후 (main1.js)
- ✅ 중복 제거
- ✅ 간단하고 명확한 구조
- ✅ 주석으로 설명 추가
- ✅ 현대적인 코드 스타일
- ✅ 유지보수 용이

**결론:** `main1.js`는 기존 코드보다 **33줄 더 짧고**, **더 간단하고**, **더 이해하기 쉬운** 코드입니다!
