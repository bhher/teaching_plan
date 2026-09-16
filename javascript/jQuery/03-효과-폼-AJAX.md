# jQuery 03 — 효과 · 폼 · AJAX (20~31)

[← 전체 목록](./README.md)

---

## 20. Effect ⭐⭐⭐⭐

```javascript
$("#box").hide();
$("#box").show();
$("#box").toggle();           // 보이면 숨김, 숨으면 보임
$("#box").toggle(300);        // 속도(ms)
```

---

## 21. Fade ⭐⭐⭐

```javascript
$("#box").fadeOut(500);
$("#box").fadeIn(500);
$("#box").fadeToggle(500);
$("#box").fadeTo(500, 0.3);   // 투명도 0.3
```

---

## 22. Slide ⭐⭐⭐⭐ (아코디언)

```javascript
$(".title").on("click", function () {
  $(this).next().slideToggle(300);
});
```

```javascript
$("#panel").slideUp();
$("#panel").slideDown();
```

---

## 23. animate ⭐⭐⭐

```javascript
$("#box").animate({ left: "300px", opacity: 0.5 }, 1000);
```

| | jQuery `animate` | CSS `transition` |
|--|------------------|------------------|
| 작성 | JS에서 수치 지정 | CSS에 속성 지정 |
| 성능 | 구식·무거울 수 있음 | 브라우저 최적화 |
| 용도 | 레거시·빠른 데모 | 현대 프론트 권장 |

숫자로 애니메이션 가능한 CSS 위주 (`left`, `width`, `opacity` 등).  
`display` 같은 값은 animate로 부드럽게 바꾸기 어렵습니다.

---

## 24. 메서드 체이닝 ⭐⭐⭐⭐

```javascript
$("#box")
  .css("color", "red")
  .slideUp(500)
  .slideDown(500);
```

대부분 메서드가 **jQuery 객체 자신**을 반환해서 이어 붙일 수 있습니다.

---

## 25. Form 처리 ⭐⭐⭐⭐⭐

```javascript
$("#name").val();
$("input[name=gender]:checked").val();           // 라디오
$("input[name=hobby]:checked").each(function () {
  console.log($(this).val());
});
$("#job option:selected").val();                 // select
// 또는
$("#job").val();
```

---

## 26. 체크박스 · 전체 선택 ⭐⭐⭐⭐

```javascript
$("#all").on("click", function () {
  $("input[name=hobby]").prop("checked", this.checked);
});
```

`this.checked`는 DOM의 실제 체크 상태입니다.

---

## 27. `attr()` vs `prop()` ⭐⭐⭐⭐

| | `attr` | `prop` |
|--|--------|--------|
| 대상 | HTML 속성 문자열 | DOM 프로퍼티 |
| 예 | `href`, `src`, `id` | `checked`, `disabled`, `selected` |

```javascript
$("#check").prop("checked", true);
$("#check").prop("disabled", true);

// 읽기
if ($("#check").prop("checked")) { ... }
```

> 체크 여부는 **`prop("checked")`** 를 쓰세요. (`attr`은 초기 HTML만 볼 때가 있음)

---

## 28. AJAX ⭐⭐⭐⭐⭐

```javascript
$.ajax({
  url: "https://jsonplaceholder.typicode.com/posts/1",
  type: "GET",
  success: function (data) {
    console.log(data);
  },
  error: function () {
    console.log("오류");
  }
});
```

---

## 29. 단축 메서드

```javascript
$.get(url, function (data) { ... });
$.post(url, { name: "홍길동" }, function (data) { ... });
$.getJSON(url, function (data) { ... });
```

---

## 30. JSON과 AJAX · Spring 연동 개념 ⭐⭐⭐⭐⭐

```text
브라우저 (jQuery)
    ↓  $.ajax  (HTTP + JSON)
Spring Boot Controller (@RestController)
    ↓
Service → Repository → DB
    ↓
JSON 응답
    ↓
success 콜백에서 화면 그리기 (append 등)
```

```javascript
$.ajax({
  url: "/api/users",
  type: "GET",
  dataType: "json",
  success: function (data) {
    console.log(data);
  }
});
```

수업에서 백엔드가 없으면 **JSONPlaceholder** 같은 공개 API로 연습합니다.  
→ `examples/08-AJAX게시판.html`

---

## 31. AJAX POST ⭐⭐⭐⭐⭐

```javascript
$.ajax({
  url: "/api/users",
  type: "POST",
  contentType: "application/json",
  data: JSON.stringify({
    name: "홍길동",
    age: 20
  }),
  success: function (data) {
    console.log(data);
  }
});
```

| 키 | 의미 |
|----|------|
| `contentType` | 보내는 형식 (JSON이면 `application/json`) |
| `data` | 본문 — 객체면 `JSON.stringify` |
| `dataType` | 응답을 JSON으로 파싱 기대 |

Spring 예 (개념):

```java
@PostMapping("/api/users")
public User create(@RequestBody User user) {
    return userService.save(user);
}
```

---

## 미니 예제 — fade + 체이닝

```html
<button id="go">실행</button>
<div id="box" style="width:100px;height:100px;background:skyblue;"></div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(function () {
  $("#go").on("click", function () {
    $("#box")
      .fadeOut(300)
      .fadeIn(300)
      .animate({ marginLeft: "80px" }, 400);
  });
});
</script>
```
