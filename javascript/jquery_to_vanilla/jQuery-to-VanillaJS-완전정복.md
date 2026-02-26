# jQuery to Vanilla JS 변환 완전 정복

## 📋 목차
1. [개요](#개요)
2. [기본 변환 규칙](#기본-변환-규칙)
3. [선택자 변환](#선택자-변환)
4. [DOM 조작 변환](#dom-조작-변환)
5. [이벤트 변환](#이벤트-변환)
6. [변환 비교표](#변환-비교표)

---

## 개요

이 문서는 jQuery 코드를 순수 JavaScript(Vanilla JS)로 변환하는 방법을 설명합니다.

**주요 변환 내용:**
- 탐색 선택자 (`:odd`, `:even`, `:first`, `:last`, `:contains`, `:has` 등)
- DOM 조작 (`append`, `prepend`, `insertBefore`, `insertAfter` 등)
- 속성 조작 (`attr`, `addClass`, `css` 등)

---

## 기본 변환 규칙

### 1. 문서 준비

**jQuery:**
```javascript
$(document).ready(function() {
    // 코드
});

// 또는
$(function() {
    // 코드
});
```

**Vanilla JS:**
```javascript
document.addEventListener('DOMContentLoaded', function() {
    // 코드
});
```

### 2. 요소 선택

**jQuery:**
```javascript
$('#id')           // ID 선택
$('.class')        // 클래스 선택
$('tag')           // 태그 선택
$('#id .class')    // 복합 선택
```

**Vanilla JS:**
```javascript
document.getElementById('id')                    // ID 선택
document.querySelector('.class')                 // 클래스 선택 (첫 번째)
document.querySelectorAll('.class')               // 클래스 선택 (모든 요소)
document.querySelector('tag')                    // 태그 선택 (첫 번째)
document.querySelectorAll('tag')                 // 태그 선택 (모든 요소)
document.querySelector('#id .class')             // 복합 선택
```

---

## 선택자 변환

### 1. :odd (홀수 인덱스)

**jQuery:**
```javascript
$("#list1 li:odd").css("background", "yellow");
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list1 li');
items.forEach(function(li, index) {
    if (index % 2 === 1) { // 홀수 인덱스 (1, 3, 5, ...)
        li.style.background = 'yellow';
    }
});
```

**설명:**
- `index % 2 === 1`: 나머지가 1이면 홀수 인덱스
- jQuery의 `:odd`는 인덱스 기준 (0부터 시작)

### 2. :even (짝수 인덱스)

**jQuery:**
```javascript
$("#list1 li:even").css("background", "gray");
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list1 li');
items.forEach(function(li, index) {
    if (index % 2 === 0) { // 짝수 인덱스 (0, 2, 4, ...)
        li.style.background = 'gray';
    }
});
```

**주의:**
- jQuery `:even`은 인덱스 기준 (0, 2, 4, ...)
- CSS `:nth-child(even)`은 1부터 시작 (2, 4, 6, ...)

### 3. :first (첫 번째)

**jQuery:**
```javascript
$("#list1 li:first").css("color", "red");
```

**Vanilla JS:**
```javascript
const firstLi = document.querySelector('#list1 li:first-child');
if (firstLi) {
    firstLi.style.color = 'red';
}

// 또는
const items = document.querySelectorAll('#list1 li');
if (items[0]) {
    items[0].style.color = 'red';
}
```

### 4. :last (마지막)

**jQuery:**
```javascript
$("#list1 li:last").css("color", "green");
```

**Vanilla JS:**
```javascript
const lastLi = document.querySelector('#list1 li:last-child');
if (lastLi) {
    lastLi.style.color = 'green';
}

// 또는
const items = document.querySelectorAll('#list1 li');
if (items.length > 0) {
    items[items.length - 1].style.color = 'green';
}
```

### 5. .eq() (인덱스로 선택)

**jQuery:**
```javascript
$("#list1 li").eq(1).css('font-style', 'italic');
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list1 li');
if (items[1]) {
    items[1].style.fontStyle = 'italic';
}
```

### 6. :lt() (less than, 미만)

**jQuery:**
```javascript
$("#list1 li:lt(2)").css("border", "dotted 2px aqua");
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list1 li');
items.forEach(function(li, index) {
    if (index < 2) { // 인덱스 0, 1
        li.style.border = 'dotted 2px aqua';
    }
});
```

### 7. :gt() (greater than, 초과)

**jQuery:**
```javascript
$("#list1 li:gt(2)").css("border", "dotted 2px purple");
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list1 li');
items.forEach(function(li, index) {
    if (index > 2) { // 인덱스 3, 4, 5, ...
        li.style.border = 'dotted 2px purple';
    }
});
```

### 8. :contains() (텍스트 포함)

**jQuery:**
```javascript
$("#list2 li:contains('리스트11')").css("color", "red");
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list2 li');
items.forEach(function(li) {
    if (li.textContent.includes('리스트11')) {
        li.style.color = 'red';
    }
});
```

**주의:**
- `textContent`: 요소의 텍스트 내용 (하위 요소 포함)
- `innerText`: 보이는 텍스트만 (스타일 영향 받음)
- `includes()`: 문자열 포함 여부 확인

### 9. :has() (하위 요소 포함)

**jQuery:**
```javascript
$("#list2 li:has('span')").css("color", "aqua");
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list2 li');
items.forEach(function(li) {
    if (li.querySelector('span')) {
        li.style.color = 'aqua';
    }
});
```

**설명:**
- `querySelector()`: 하위 요소 중 첫 번째 요소 찾기
- 찾으면 요소 반환, 없으면 `null` 반환

### 10. .find() (하위 요소 찾기)

**jQuery:**
```javascript
$("#list2 li").find("a").css("background-color", "red");
```

**Vanilla JS:**
```javascript
// 방법 1: 직접 선택
const links = document.querySelectorAll('#list2 li a');
links.forEach(function(link) {
    link.style.backgroundColor = 'red';
});

// 방법 2: 각 li에서 찾기
const items = document.querySelectorAll('#list2 li');
items.forEach(function(li) {
    const link = li.querySelector('a');
    if (link) {
        link.style.backgroundColor = 'red';
    }
});
```

---

## DOM 조작 변환

### 1. .html() (HTML 내용 변경)

**jQuery:**
```javascript
$(".theTitle").html("<a href='#'>요소 객체 조작</a>");
```

**Vanilla JS:**
```javascript
const theTitle = document.querySelector('.theTitle');
if (theTitle) {
    theTitle.innerHTML = "<a href='#'>요소 객체 조작</a>";
}
```

**비교:**
- `innerHTML`: HTML 태그 포함하여 설정
- `textContent`: 텍스트만 설정 (태그는 이스케이프됨)

### 2. .text() (텍스트 내용 변경)

**jQuery:**
```javascript
$(".obj1").text("리스트_2");
```

**Vanilla JS:**
```javascript
const obj1 = document.querySelector('.obj1');
if (obj1) {
    obj1.textContent = '리스트_2';
}
```

### 3. .append() (마지막에 추가)

**jQuery:**
```javascript
$("#list3").append("<li>list_6</li>");
```

**Vanilla JS:**
```javascript
// 방법 1: createElement 사용 (권장)
const newLi = document.createElement('li');
newLi.textContent = 'list_6';
list3.appendChild(newLi);

// 방법 2: innerHTML 사용 (주의: 기존 내용 덮어씀)
// list3.innerHTML += '<li>list_6</li>'; // 비권장
```

### 4. .prepend() (첫 번째에 추가)

**jQuery:**
```javascript
$("#list3").prepend("<li>list_1</li>");
```

**Vanilla JS:**
```javascript
const newLi = document.createElement('li');
newLi.textContent = 'list_1';
list3.insertBefore(newLi, list3.firstChild);
```

### 5. .insertBefore() (특정 요소 앞에 추가)

**jQuery:**
```javascript
$("<li>insertBefore</li>").insertBefore($('#list3 li').eq(4));
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list3 li');
if (items[4]) {
    const newLi = document.createElement('li');
    newLi.textContent = 'insertBefore';
    list3.insertBefore(newLi, items[4]);
}
```

### 6. .insertAfter() (특정 요소 뒤에 추가)

**jQuery:**
```javascript
$("<li>insertAfter</li>").insertAfter($("#list3 li").eq(5));
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list3 li');
if (items[5]) {
    const newLi = document.createElement('li');
    newLi.textContent = 'insertAfter';
    // insertAfter는 직접 메서드가 없으므로 다음 형제 앞에 삽입
    if (items[5].nextSibling) {
        list3.insertBefore(newLi, items[5].nextSibling);
    } else {
        list3.appendChild(newLi); // 마지막이면 appendChild
    }
}

// 또는 insertAdjacentElement 사용
if (items[5]) {
    const newLi = document.createElement('li');
    newLi.textContent = 'insertAfter';
    items[5].insertAdjacentElement('afterend', newLi);
}
```

**insertAdjacentElement 위치:**
- `'beforebegin'`: 요소 앞
- `'afterbegin'`: 요소 내부 시작
- `'beforeend'`: 요소 내부 끝
- `'afterend'`: 요소 뒤

### 7. .clone() (요소 복제)

**jQuery:**
```javascript
$('.obj1').clone().appendTo("#list3");
```

**Vanilla JS:**
```javascript
const obj1 = document.querySelector('.obj1');
if (obj1) {
    const cloned = obj1.cloneNode(true); // true = 자식 요소도 복제
    list3.appendChild(cloned);
}
```

**cloneNode 옵션:**
- `cloneNode(false)`: 요소만 복제 (자식 제외)
- `cloneNode(true)`: 요소와 모든 자식 복제

---

## 속성 및 스타일 조작 변환

### 1. .attr() (속성 가져오기/설정)

**jQuery:**
```javascript
// 속성 가져오기
var k = $('#list3 li').eq(0).attr('class');

// 속성 설정
$('#list3 li').eq(0).attr('class', 'obj2');
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list3 li');
if (items[0]) {
    // 속성 가져오기
    const k = items[0].getAttribute('class');
    console.log(k);
    
    // 속성 설정
    items[0].setAttribute('class', 'obj2');
}
```

### 2. .css() (스타일 설정)

**jQuery:**
```javascript
$('.obj2').css('background-color', 'yellow');
```

**Vanilla JS:**
```javascript
const obj2Elements = document.querySelectorAll('.obj2');
obj2Elements.forEach(function(el) {
    el.style.backgroundColor = 'yellow';
});
```

**스타일 속성 변환:**
- `background-color` → `backgroundColor` (카멜 케이스)
- `font-size` → `fontSize`
- `margin-left` → `marginLeft`

### 3. .addClass() (클래스 추가)

**jQuery:**
```javascript
$('#list3 li').eq(0).addClass("obj3");
```

**Vanilla JS:**
```javascript
const items = document.querySelectorAll('#list3 li');
if (items[0]) {
    items[0].classList.add('obj3');
}
```

**classList 메서드:**
- `add('class')`: 클래스 추가
- `remove('class')`: 클래스 제거
- `toggle('class')`: 클래스 토글
- `contains('class')`: 클래스 포함 여부 확인
- `replace('old', 'new')`: 클래스 교체

---

## 변환 비교표

| jQuery | Vanilla JS |
|--------|------------|
| `$(document).ready()` | `DOMContentLoaded` |
| `$('#id')` | `document.getElementById('id')` |
| `$('.class')` | `document.querySelectorAll('.class')` |
| `$('tag')` | `document.querySelectorAll('tag')` |
| `:odd` | `forEach` + `index % 2 === 1` |
| `:even` | `forEach` + `index % 2 === 0` |
| `:first` | `:first-child` 또는 `[0]` |
| `:last` | `:last-child` 또는 `[length-1]` |
| `.eq(n)` | `[n]` |
| `:lt(n)` | `forEach` + `index < n` |
| `:gt(n)` | `forEach` + `index > n` |
| `:contains('text')` | `textContent.includes('text')` |
| `:has('selector')` | `querySelector('selector')` |
| `.find('selector')` | `querySelectorAll('selector')` |
| `.html()` | `innerHTML` |
| `.text()` | `textContent` |
| `.append()` | `appendChild()` |
| `.prepend()` | `insertBefore(, firstChild)` |
| `.insertBefore()` | `insertBefore()` |
| `.insertAfter()` | `insertAdjacentElement('afterend')` |
| `.clone()` | `cloneNode(true)` |
| `.attr('name')` | `getAttribute('name')` |
| `.attr('name', 'value')` | `setAttribute('name', 'value')` |
| `.css('prop', 'value')` | `style.prop = 'value'` |
| `.addClass()` | `classList.add()` |
| `.removeClass()` | `classList.remove()` |
| `.hasClass()` | `classList.contains()` |

---

## 실전 예제

### 예제 1: 홀수/짝수 선택

```javascript
// jQuery
$("#list li:odd").css("background", "yellow");
$("#list li:even").css("background", "gray");

// Vanilla JS
const items = document.querySelectorAll('#list li');
items.forEach(function(li, index) {
    if (index % 2 === 1) {
        li.style.background = 'yellow';
    } else {
        li.style.background = 'gray';
    }
});
```

### 예제 2: 조건부 선택

```javascript
// jQuery
$("#list li:lt(3)").css("color", "red");
$("#list li:gt(3)").css("color", "blue");

// Vanilla JS
const items = document.querySelectorAll('#list li');
items.forEach(function(li, index) {
    if (index < 3) {
        li.style.color = 'red';
    } else if (index > 3) {
        li.style.color = 'blue';
    }
});
```

### 예제 3: 동적 요소 추가

```javascript
// jQuery
$("#list").append("<li>New Item</li>");
$("#list").prepend("<li>First Item</li>");

// Vanilla JS
const list = document.getElementById('list');

// append
const newLi1 = document.createElement('li');
newLi1.textContent = 'New Item';
list.appendChild(newLi1);

// prepend
const newLi2 = document.createElement('li');
newLi2.textContent = 'First Item';
list.insertBefore(newLi2, list.firstChild);
```

---

## 마무리

jQuery를 Vanilla JS로 변환하는 핵심은:

1. ✅ **선택자**: `querySelector`, `querySelectorAll` 사용
2. ✅ **반복**: `forEach`로 순회하며 조건 체크
3. ✅ **DOM 조작**: `createElement`, `appendChild`, `insertBefore` 사용
4. ✅ **속성**: `getAttribute`, `setAttribute` 사용
5. ✅ **스타일**: `style` 속성 직접 설정
6. ✅ **클래스**: `classList` API 사용

이제 jQuery 없이도 동일한 기능을 구현할 수 있습니다!
