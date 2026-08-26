# jQuery · JavaScript 선택자 · 탐색 · 조작 정답

문제: [jQuery-JavaScript-선택자-탐색-조작-문제.md](./jQuery-JavaScript-선택자-탐색-조작-문제.md)

---

## Part 1. 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 1 | ② | jQuery 미로드·경로 오류·순서 오류 → `$ is not defined` |
| 2 | ② | DOM ready (`$(function(){})` = `$(document).ready`) |
| 3 | ② | `DOMContentLoaded` |
| 4 | ② | 전체 선택자 `$('*')` |
| 5 | ① | `#id` ↔ `getElementById` |
| 6 | ② | `querySelector`는 첫 개만, 여러 개는 `querySelectorAll` + 반복 |
| 7 | ② | `>` = 자식 결합자 |
| 8 | ② | 체이닝 |
| 9 | ① | `parent()` / `parentNode` |
| 10 | ② | `next()` / `nextElementSibling` |
| 11 | ② | `prev()` / `previousElementSibling` |
| 12 | ② | `siblings('li')` — 자신 제외 형제 |
| 13 | ① | 속성 선택자 / `:text` 의사선택자 |
| 14 | `css` / `style` | `$().css` , `element.style` |

---

## Part 2. 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 15 | ② | `:odd` = 인덱스 1,3,5… |
| 16 | ② | jQuery even/odd는 0-base, nth-child는 1-base |
| 17 | ① | `:first` / `:last` |
| 18 | ② | `eq(1)` = 두 번째 요소 |
| 19 | ② | `lt(2)` → 0,1 |
| 20 | ② | `gt(2)` → 3,4,5… |

---

## Part 3. 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 21 | ② | `:contains` 텍스트 포함 |
| 22 | ② | `:has(span)` 하위 span 보유 |
| 23 | ② | `.find('a')` 자손 a |
| 24 | ② | html vs text |
| 25 | ② | `append` = 끝, `prepend` = 앞 |
| 26 | ② | `insertBefore` = 기준 앞 |
| 27 | ② | `clone` + `appendTo` |
| 28 | ① | attr ↔ get/setAttribute |
| 29 | ② | addClass는 추가 (attr로 class 통째 교체와 다름) |
| 30 | `parentNode` / `nextElementSibling` / `previousElementSibling` / `appendChild`(또는 insertAdjacentHTML) / `getAttribute('class')` | |

---

## Part 4. 코드 정답

### 31번. odd / even

```javascript
$("#list1 li:odd").css("background", "yellow");
$("#list1 li:even").css("background", "gray");
```

---

### 32번. 자식 선택 (Vanilla)

```javascript
document.addEventListener('DOMContentLoaded', function () {
  document.querySelectorAll('.obj3 > li').forEach(function (el) {
    el.style.fontWeight = 'bold';
  });
  document.querySelectorAll('.obj3 > .theObj1').forEach(function (el) {
    el.style.color = 'purple';
  });
});
```

---

### 33번. prepend / append

```javascript
$("#list3").prepend("<li>list_1</li>");
$("#list3").append("<li>list_6</li>");
```

---

### 34번. jQuery → JS

```javascript
document.addEventListener('DOMContentLoaded', function () {
  var obj5 = document.querySelector('.obj5');
  if (obj5 && obj5.parentNode) {
    obj5.parentNode.style.border = 'dashed 2px #f00';
  }

  var obj4 = document.querySelector('.obj4');
  if (obj4 && obj4.nextElementSibling) {
    obj4.nextElementSibling.style.color = 'orange';
  }
});
```

---

## jQuery ↔ JavaScript 빠른 표

| jQuery | JavaScript |
|--------|------------|
| `$(function(){})` | `DOMContentLoaded` |
| `$('*')` | `querySelectorAll('*')` |
| `$('#id')` | `getElementById('id')` |
| `$('.class')` | `querySelectorAll('.class')` |
| `.css('color','red')` | `.style.color = 'red'` |
| `.parent()` | `.parentNode` |
| `.children()` | `.children` |
| `.next()` | `.nextElementSibling` |
| `.prev()` | `.previousElementSibling` |
| `.siblings()` | 부모의 children 중 자신 제외 |
| `.find('a')` | `.querySelectorAll('a')` (기준 요소에서) |
| `.html(...)` | `.innerHTML` |
| `.text(...)` | `.textContent` |
| `.append(...)` | `.insertAdjacentHTML('beforeend', ...)` / `appendChild` |
| `.prepend(...)` | `'afterbegin'` |
| `.attr('class')` | `.getAttribute('class')` |
| `.attr('class','x')` | `.setAttribute('class','x')` |
| `.addClass('x')` | `.classList.add('x')` |
| `.eq(n)` | `[n]` (NodeList) |
| `:odd` / `:even` | 인덱스 `% 2` |
| `:contains` | `textContent.includes(...)` |
| `:has('span')` | `.querySelector('span')` 있는지 |

---

## 형제(siblings) Vanilla 예

```javascript
var obj6 = document.querySelector('.obj6');
var kids = obj6.parentElement.children;
for (var i = 0; i < kids.length; i++) {
  if (kids[i] !== obj6 && kids[i].tagName === 'LI') {
    kids[i].style.textAlign = 'center';
  }
}
```

---

## 수업 포인트

1. **jQuery 로드 실패** → `$ is not defined`  
2. **선택자** `#` `.` `>` 와 **탐색** parent/next/prev/siblings  
3. **eq / lt / gt / odd / even** 은 인덱스 0 기준  
4. **조작** html·text·append·prepend·attr·addClass  
5. 같은 일을 JS로 할 때는 `querySelector(All)` + `style` + DOM 프로퍼티
