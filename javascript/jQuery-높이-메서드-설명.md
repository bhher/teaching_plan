# jQuery 높이 관련 메서드 설명

## 📏 `outerHeight()` 메서드

### 기본 개념

```javascript
$(this).outerHeight();
```

**의미:**
- 요소의 **외부 높이**를 반환합니다
- **콘텐츠 + 패딩 + 보더**를 포함한 높이입니다
- 마진은 **포함하지 않습니다**

---

## 🎯 jQuery 높이 메서드 비교

### 1. `height()` - 콘텐츠 높이만

```javascript
$(element).height();
```

**포함하는 것:**
- ✅ 콘텐츠 높이만

**포함하지 않는 것:**
- ❌ 패딩 (padding)
- ❌ 보더 (border)
- ❌ 마진 (margin)

**예시:**
```css
.box {
    height: 100px;        /* 콘텐츠 높이 */
    padding: 20px;        /* 패딩 */
    border: 5px solid;   /* 보더 */
    margin: 10px;        /* 마진 */
}
```

```javascript
$('.box').height();  // 100 (콘텐츠 높이만)
```

---

### 2. `innerHeight()` - 콘텐츠 + 패딩

```javascript
$(element).innerHeight();
```

**포함하는 것:**
- ✅ 콘텐츠 높이
- ✅ 패딩 (padding)

**포함하지 않는 것:**
- ❌ 보더 (border)
- ❌ 마진 (margin)

**예시:**
```css
.box {
    height: 100px;        /* 콘텐츠 높이 */
    padding: 20px;        /* 패딩 (상하 20px씩 = 40px) */
    border: 5px solid;   /* 보더 */
    margin: 10px;        /* 마진 */
}
```

```javascript
$('.box').innerHeight();  // 140 (100 + 20 + 20)
```

**계산:**
- 콘텐츠: 100px
- 패딩 상단: 20px
- 패딩 하단: 20px
- **합계: 140px**

---

### 3. `outerHeight()` - 콘텐츠 + 패딩 + 보더

```javascript
$(element).outerHeight();
// 또는
$(element).outerHeight(false);  // 기본값
```

**포함하는 것:**
- ✅ 콘텐츠 높이
- ✅ 패딩 (padding)
- ✅ 보더 (border)

**포함하지 않는 것:**
- ❌ 마진 (margin)

**예시:**
```css
.box {
    height: 100px;        /* 콘텐츠 높이 */
    padding: 20px;        /* 패딩 (상하 20px씩) */
    border: 5px solid;   /* 보더 (상하 5px씩) */
    margin: 10px;        /* 마진 */
}
```

```javascript
$('.box').outerHeight();  // 150 (100 + 20 + 20 + 5 + 5)
```

**계산:**
- 콘텐츠: 100px
- 패딩 상단: 20px
- 패딩 하단: 20px
- 보더 상단: 5px
- 보더 하단: 5px
- **합계: 150px**

---

### 4. `outerHeight(true)` - 콘텐츠 + 패딩 + 보더 + 마진

```javascript
$(element).outerHeight(true);
```

**포함하는 것:**
- ✅ 콘텐츠 높이
- ✅ 패딩 (padding)
- ✅ 보더 (border)
- ✅ 마진 (margin)

**예시:**
```css
.box {
    height: 100px;        /* 콘텐츠 높이 */
    padding: 20px;        /* 패딩 (상하 20px씩) */
    border: 5px solid;   /* 보더 (상하 5px씩) */
    margin: 10px;        /* 마진 (상하 10px씩) */
}
```

```javascript
$('.box').outerHeight(true);  // 170 (100 + 20 + 20 + 5 + 5 + 10 + 10)
```

**계산:**
- 콘텐츠: 100px
- 패딩 상단: 20px
- 패딩 하단: 20px
- 보더 상단: 5px
- 보더 하단: 5px
- 마진 상단: 10px
- 마진 하단: 10px
- **합계: 170px**

---

## 📊 시각적 비교

```
┌─────────────────────────────────────┐
│  margin: 10px                       │ ← outerHeight(true)에 포함
│  ┌───────────────────────────────┐ │
│  │ border: 5px                   │ │ ← outerHeight()에 포함
│  │ ┌───────────────────────────┐ │ │
│  │ │ padding: 20px             │ │ │ ← innerHeight()에 포함
│  │ │ ┌───────────────────────┐ │ │ │
│  │ │ │ 콘텐츠: 100px         │ │ │ │ ← height()에 포함
│  │ │ └───────────────────────┘ │ │ │
│  │ └───────────────────────────┘ │ │ │
│  └───────────────────────────────┘ │ │
│                                     │ │
└─────────────────────────────────────┘ │
```

**높이 값:**
- `height()`: 100px
- `innerHeight()`: 140px (100 + 20 + 20)
- `outerHeight()`: 150px (100 + 20 + 20 + 5 + 5)
- `outerHeight(true)`: 170px (100 + 20 + 20 + 5 + 5 + 10 + 10)

---

## 💡 실제 사용 예시

### 예시 1: 요소의 실제 높이 계산

```javascript
// 요소의 실제 높이 (보더 포함)
var elementHeight = $(this).outerHeight();

// 요소의 하단 위치 계산
var elementTop = $(this).offset().top;
var elementBottom = elementTop + elementHeight;
```

**왜 `outerHeight()`를 사용하나요?**
- 보더를 포함한 실제 높이가 필요하기 때문
- 요소의 정확한 하단 위치를 계산하기 위해

---

### 예시 2: 뷰포트 안에 있는지 확인

```javascript
$(window).on('scroll', function() {
    $('.item').each(function() {
        // 요소의 상단 위치
        var elementTop = $(this).offset().top;
        
        // 요소의 하단 위치 (보더 포함)
        var elementBottom = elementTop + $(this).outerHeight();
        
        // 뷰포트의 상단 위치
        var viewportTop = $(window).scrollTop();
        
        // 뷰포트의 하단 위치
        var viewportBottom = viewportTop + $(window).height();
        
        // 요소가 뷰포트 안에 있는지 확인
        if (elementBottom > viewportTop && elementTop < viewportBottom) {
            // 보이는 영역에 있음
            $(this).addClass('visible');
        }
    });
});
```

**왜 `outerHeight()`를 사용하나요?**
- 요소의 보더까지 포함한 전체 높이가 필요
- 정확한 위치 계산을 위해

---

## 🔍 다른 메서드와의 차이

### `offset().top` vs `position().top`

```javascript
// 문서 기준 위치 (스크롤 포함)
var top1 = $(element).offset().top;

// 부모 요소 기준 위치 (스크롤 제외)
var top2 = $(element).position().top;
```

**차이점:**
- `offset().top`: 문서의 맨 위를 기준으로 한 위치
- `position().top`: 부모 요소를 기준으로 한 위치

---

## 📝 정리

| 메서드 | 포함하는 것 | 사용 예시 |
|--------|------------|----------|
| `height()` | 콘텐츠만 | 콘텐츠 높이 확인 |
| `innerHeight()` | 콘텐츠 + 패딩 | 패딩 포함 높이 확인 |
| `outerHeight()` | 콘텐츠 + 패딩 + 보더 | **실제 요소 높이** (가장 많이 사용) |
| `outerHeight(true)` | 콘텐츠 + 패딩 + 보더 + 마진 | 마진 포함 전체 높이 |

---

## ✅ 결론

**`$(this).outerHeight()`는:**
- ✅ 요소의 **실제 높이**를 반환합니다
- ✅ **콘텐츠 + 패딩 + 보더**를 포함합니다
- ✅ 마진은 포함하지 않습니다
- ✅ 요소의 정확한 위치 계산에 사용됩니다

**가장 많이 사용되는 메서드**입니다!
