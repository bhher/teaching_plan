# jQuery 이벤트 핸들러를 Vanilla JS로 변환 완전 정복

## 📋 목차
1. [개요](#개요)
2. [기본 이벤트 변환](#기본-이벤트-변환)
3. [이벤트 메서드 비교](#이벤트-메서드-비교)
4. [고급 이벤트 처리](#고급-이벤트-처리)
5. [실전 예제](#실전-예제)

---

## 개요

이 문서는 jQuery 이벤트 핸들러를 순수 JavaScript로 변환하는 방법을 설명합니다.

**주요 변환 내용:**
- `click()`, `mouseover()`, `focus()` 등 기본 이벤트
- `on()` 메서드로 여러 이벤트 등록
- `hover()` 메서드 (두 개의 함수)
- `mouseenter()` / `mouseleave()` vs `mouseover()` / `mouseout()`

---

## 기본 이벤트 변환

### 1. click 이벤트

**jQuery:**
```javascript
$("#btn1").click(function () {
    $("#textZone").css("color", "blue");
});
```

**Vanilla JS:**
```javascript
const btn1 = document.getElementById('btn1');
const textZone = document.getElementById('textZone');

btn1.addEventListener('click', function() {
    textZone.style.color = 'blue';
});
```

### 2. mouseover 이벤트

**jQuery:**
```javascript
$("#btn2").mouseover(function () {
    $("#textZone").css("background-color", "yellow");
});
```

**Vanilla JS:**
```javascript
const btn2 = document.getElementById('btn2');
const textZone = document.getElementById('textZone');

btn2.addEventListener('mouseover', function() {
    textZone.style.backgroundColor = 'yellow';
});
```

### 3. focus 이벤트

**jQuery:**
```javascript
$("#btn2").focus(function () {
    $("#textZone").css("background-color", "yellow");
});
```

**Vanilla JS:**
```javascript
const btn2 = document.getElementById('btn2');
const textZone = document.getElementById('textZone');

btn2.addEventListener('focus', function() {
    textZone.style.backgroundColor = 'yellow';
});
```

### 4. 여러 이벤트 등록 (on 메서드)

**jQuery:**
```javascript
// 한 가지 이상 이벤트 등록 시 on 메서드 사용
$("#btn3").on('mouseover focus', function () {
    $("#textZone").css("color", "green").css("font-weight", "bold");
});
```

**Vanilla JS:**
```javascript
const btn3 = document.getElementById('btn3');
const textZone = document.getElementById('textZone');

// 각 이벤트를 개별적으로 등록
btn3.addEventListener('mouseover', function() {
    textZone.style.color = 'green';
    textZone.style.fontWeight = 'bold';
});

btn3.addEventListener('focus', function() {
    textZone.style.color = 'green';
    textZone.style.fontWeight = 'bold';
});

// 또는 공통 함수 사용
function handleBtn3Event() {
    textZone.style.color = 'green';
    textZone.style.fontWeight = 'bold';
}

btn3.addEventListener('mouseover', handleBtn3Event);
btn3.addEventListener('focus', handleBtn3Event);
```

---

## 이벤트 메서드 비교

### mouseenter vs mouseover

**차이점:**
- **mouseover**: 요소와 자식 요소에 마우스가 올라갈 때마다 발생 (버블링)
- **mouseenter**: 요소 영역에 처음 진입할 때만 발생 (버블링 없음)

**jQuery:**
```javascript
$("#listWrap").mouseenter(function () {
    $(".list1").css("display", "block");
});
```

**Vanilla JS:**
```javascript
const listWrap = document.getElementById('listWrap');
const list1 = document.querySelector('.list1');

listWrap.addEventListener('mouseenter', function() {
    list1.style.display = 'block';
});
```

**시각적 설명:**
```
mouseover (버블링 발생):
┌─────────────────┐
│   listWrap      │ ← 마우스 진입
│   ┌──────────┐  │
│   │  list1   │  │ ← 자식 요소로 이동 시에도 이벤트 발생
│   └──────────┘  │
└─────────────────┘

mouseenter (버블링 없음):
┌─────────────────┐
│   listWrap      │ ← 마우스 진입 (한 번만 발생)
│   ┌──────────┐  │
│   │  list1   │  │ ← 자식 요소로 이동해도 이벤트 발생 안 함
│   └──────────┘  │
└─────────────────┘
```

### mouseleave vs mouseout

**차이점:**
- **mouseout**: 요소와 자식 요소에서 마우스가 벗어날 때마다 발생 (버블링)
- **mouseleave**: 요소 영역에서 완전히 벗어날 때만 발생 (버블링 없음)

**jQuery:**
```javascript
$("#listWrap").mouseleave(function () {
    $(".list1").css("display", "none");
});
```

**Vanilla JS:**
```javascript
const listWrap = document.getElementById('listWrap');
const list1 = document.querySelector('.list1');

listWrap.addEventListener('mouseleave', function() {
    list1.style.display = 'none';
});
```

---

## 고급 이벤트 처리

### hover 메서드 (두 개의 함수)

**jQuery:**
```javascript
$('.hover').hover(
    function() {
        // mouseenter 시 실행
        $(this).css("color", "aqua");
    },
    function() {
        // mouseleave 시 실행
        $(this).css("color", "red");
    }
);
```

**Vanilla JS:**
```javascript
const hoverLink = document.querySelector('.hover');

hoverLink.addEventListener('mouseenter', function() {
    this.style.color = 'aqua';
});

hoverLink.addEventListener('mouseleave', function() {
    this.style.color = 'red';
});
```

**설명:**
- jQuery의 `hover()`는 첫 번째 함수를 `mouseenter`, 두 번째 함수를 `mouseleave`에 바인딩
- Vanilla JS에서는 각각 `addEventListener`로 등록

---

## 이벤트 메서드 비교표

| jQuery | Vanilla JS | 설명 |
|--------|------------|------|
| `.click()` | `addEventListener('click')` | 클릭 이벤트 |
| `.mouseover()` | `addEventListener('mouseover')` | 마우스 오버 (버블링) |
| `.mouseenter()` | `addEventListener('mouseenter')` | 마우스 진입 (버블링 없음) |
| `.mouseout()` | `addEventListener('mouseout')` | 마우스 아웃 (버블링) |
| `.mouseleave()` | `addEventListener('mouseleave')` | 마우스 떠남 (버블링 없음) |
| `.focus()` | `addEventListener('focus')` | 포커스 이벤트 |
| `.blur()` | `addEventListener('blur')` | 포커스 해제 |
| `.hover(fn1, fn2)` | `mouseenter` + `mouseleave` | 호버 이벤트 |
| `.on('event1 event2')` | 각각 `addEventListener` | 여러 이벤트 등록 |

---

## 실전 예제

### 예제 1: 여러 이벤트 등록

**jQuery:**
```javascript
$("#btn").on('click mouseover focus', function() {
    console.log('이벤트 발생');
});
```

**Vanilla JS:**
```javascript
const btn = document.getElementById('btn');

function handleEvent() {
    console.log('이벤트 발생');
}

btn.addEventListener('click', handleEvent);
btn.addEventListener('mouseover', handleEvent);
btn.addEventListener('focus', handleEvent);
```

### 예제 2: hover 효과

**jQuery:**
```javascript
$('.item').hover(
    function() {
        $(this).addClass('active');
    },
    function() {
        $(this).removeClass('active');
    }
);
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('.item');

items.forEach(function(item) {
    item.addEventListener('mouseenter', function() {
        this.classList.add('active');
    });
    
    item.addEventListener('mouseleave', function() {
        this.classList.remove('active');
    });
});
```

### 예제 3: 이벤트 제거

**jQuery:**
```javascript
$("#btn").off('click');
```

**Vanilla JS:**
```javascript
const btn = document.getElementById('btn');
const handler = function() {
    console.log('클릭');
};

btn.addEventListener('click', handler);
// 나중에 제거
btn.removeEventListener('click', handler);
```

**주의:**
- `removeEventListener`는 같은 함수 참조가 필요함
- 익명 함수는 제거할 수 없음

---

## this 바인딩

### jQuery의 this

**jQuery:**
```javascript
$('.item').click(function() {
    $(this).css('color', 'red'); // this는 클릭한 요소
});
```

### Vanilla JS의 this

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('.item');

items.forEach(function(item) {
    item.addEventListener('click', function() {
        this.style.color = 'red'; // this는 클릭한 요소
    });
});
```

**화살표 함수 주의:**
```javascript
// ❌ 잘못된 예: 화살표 함수는 this 바인딩이 다름
items.forEach(item => {
    item.addEventListener('click', () => {
        this.style.color = 'red'; // this가 window를 가리킴
    });
});

// ✅ 올바른 예: 일반 함수 사용
items.forEach(item => {
    item.addEventListener('click', function() {
        this.style.color = 'red'; // this는 클릭한 요소
    });
});
```

---

## 이벤트 객체 (Event Object)

### jQuery

```javascript
$("#btn").click(function(e) {
    e.preventDefault(); // 기본 동작 방지
    e.stopPropagation(); // 이벤트 전파 방지
    console.log(e.type); // 이벤트 타입
});
```

### Vanilla JS

```javascript
const btn = document.getElementById('btn');

btn.addEventListener('click', function(e) {
    e.preventDefault(); // 기본 동작 방지
    e.stopPropagation(); // 이벤트 전파 방지
    console.log(e.type); // 이벤트 타입
    console.log(e.target); // 이벤트가 발생한 요소
    console.log(e.currentTarget); // 이벤트 리스너가 등록된 요소
});
```

---

## 이벤트 위임 (Event Delegation)

### jQuery

```javascript
// 동적으로 추가된 요소에도 이벤트 적용
$("#list").on('click', 'li', function() {
    $(this).css('color', 'red');
});
```

### Vanilla JS

```javascript
const list = document.getElementById('list');

list.addEventListener('click', function(e) {
    // 이벤트 버블링을 이용
    if (e.target.tagName === 'LI') {
        e.target.style.color = 'red';
    }
    
    // 또는 closest 사용
    const li = e.target.closest('li');
    if (li) {
        li.style.color = 'red';
    }
});
```

---

## 마무리

jQuery 이벤트 핸들러를 Vanilla JS로 변환하는 핵심은:

1. ✅ **기본 이벤트**: `addEventListener('event', handler)` 사용
2. ✅ **여러 이벤트**: 각각 `addEventListener`로 등록
3. ✅ **hover**: `mouseenter` + `mouseleave`로 분리
4. ✅ **this 바인딩**: 일반 함수 사용 (화살표 함수 주의)
5. ✅ **이벤트 제거**: `removeEventListener` 사용 (함수 참조 필요)
6. ✅ **이벤트 위임**: 부모 요소에 이벤트 등록 후 `e.target` 확인

이제 jQuery 없이도 모든 이벤트를 처리할 수 있습니다!
