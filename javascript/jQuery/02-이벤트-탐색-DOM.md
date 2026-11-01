# jQuery 02 — 이벤트 · 탐색 · DOM (10~19)

[← 전체 목록](./README.md)

---

## 10. 이벤트 ⭐⭐⭐⭐⭐

```javascript
$("#btn").click(function () {
  alert("클릭");
});
```

| 종류 | 메서드 |
|------|--------|
| 마우스 | `click` `mouseenter` `mouseleave` `mouseover` `mouseout` `mousemove` |
| 키보드 | `keydown` `keyup` `keypress` |
| 폼 | `submit` `change` `focus` `blur` |

> 실무에서는 아래 **`on()`** 을 더 많이 씁니다.

---

## 11. `on()` ⭐⭐⭐⭐⭐

```javascript
$("#btn").on("click", function () {
  alert("클릭");
});

$("#box").on({
  mouseenter: function () {
    console.log("들어옴");
  },
  mouseleave: function () {
    console.log("나감");
  }
});
```

해제: `$("#btn").off("click");`

---

## 12. 이벤트 객체 ⭐⭐⭐

```javascript
$("#btn").on("click", function (e) {
  console.log(e);
  e.preventDefault();   // a, submit 기본 동작 막기
  e.stopPropagation();  // 버블링 막기
});
```

| 메서드 | 역할 |
|--------|------|
| `preventDefault()` | 링크 이동·폼 전송 등 기본 동작 취소 |
| `stopPropagation()` | 부모로 이벤트 전파 중단 |

---

## 13. 이벤트 위임 ⭐⭐⭐⭐⭐

나중에 `append`로 생긴 `li`에도 클릭이 먹게 하려면 **부모에 위임**합니다.

```javascript
$("#list").on("click", "li", function () {
  alert($(this).text());
});
```

```text
클릭 li → 이벤트가 #list까지 올라감 → 필터 "li" → 핸들러 실행
(= JavaScript event delegation)
```

잘못된 예 (기존 li에만 연결):

```javascript
$("li").on("click", function () { ... });  // 나중에 추가된 li는 안 됨
```

---

## 14. `this`와 `$(this)` ⭐⭐⭐⭐⭐

```javascript
$("li").on("click", function () {
  console.log(this);        // DOM 객체
  console.log($(this));     // jQuery 객체

  $(this).css("color", "red");  // jQuery 메서드 사용
  // this.style.color = "red";  // DOM 방식
});
```

| | 타입 | 쓰는 법 |
|--|------|---------|
| `this` | DOM | `.style` `.innerHTML` |
| `$(this)` | jQuery | `.css()` `.text()` `.addClass()` |

---

## 15. DOM 탐색 ⭐⭐⭐⭐⭐

```javascript
$("#menu").parent();      // 바로 위 부모
$("#menu").children();    // 직계 자식
$("#menu").find("li");    // 후손 중 li
$("#item").siblings();    // 형제
$("#item").next();        // 다음 형제
$("#item").prev();        // 이전 형제
$("#item").parents();     // 조상 전부
$("#item").closest("ul"); // 가장 가까운 ul 조상
```

```html
<ul id="menu">
  <li>A</li>
  <li id="item">B</li>
  <li>C</li>
</ul>
```

```javascript
$("#item").next().text();      // "C"
$("#menu").children().length;  // 3
```

---

## 16. 요소 추가 ⭐⭐⭐⭐

```javascript
$("#list").append("<li>맨 뒤</li>");
$("#list").prepend("<li>맨 앞</li>");
$("#box").after("<p>box 다음</p>");
$("#box").before("<p>box 이전</p>");
```

| 메서드 | 위치 |
|--------|------|
| `append` | **안쪽** 맨 뒤 |
| `prepend` | **안쪽** 맨 앞 |
| `after` | **바깥** 다음 |
| `before` | **바깥** 이전 |

---

## 17. 요소 삭제 ⭐⭐⭐

```javascript
$("#box").remove();  // 요소 자체 삭제 (이벤트도 함께 제거)
$("#box").empty();   // 안쪽 내용만 비움 (box는 남음)
```

```html
<div id="box"><span>안녕</span></div>
<!-- remove → div 사라짐 -->
<!-- empty  → <div id="box"></div> -->
```

---

## 18. 요소 복사 ⭐⭐

```javascript
let $copy = $("#box").clone();           // 구조만
let $copy2 = $("#box").clone(true);      // 이벤트까지 복사
$("#wrap").append($copy);
```

---

## 19. 반복문 ⭐⭐⭐⭐

```javascript
// 선택된 요소마다
$("li").each(function (index) {
  console.log(index, $(this).text());
});

// 배열/객체
let students = ["Kim", "Lee", "Park"];
$.each(students, function (index, item) {
  console.log(index, item);
});
```

| jQuery | Vanilla |
|--------|---------|
| `$("li").each(...)` | `NodeList.forEach` |
| `$.each(arr, ...)` | `arr.forEach` |

---

## 미니 예제 — 위임 + this

```html
<ul id="list">
  <li>사과</li>
  <li>배</li>
</ul>
<button id="add">추가</button>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(function () {
  $("#list").on("click", "li", function () {
    $(this).css("color", "tomato");
  });

  $("#add").on("click", function () {
    $("#list").append("<li>새 과일</li>");  // 위임 덕분에 클릭됨
  });
});
</script>
```
