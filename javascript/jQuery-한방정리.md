# jQuery 한방 정리 (기초 → Spring Boot 연동)

풀스택 과정(HTML/CSS → JavaScript → **jQuery** → AJAX → Spring Boot) 기준으로  
학생이 **반드시 이해하고 직접 쓸 수 있어야 하는** jQuery 핵심을 정리한 문서입니다.

**통합(화면용):** [JavaScript-jQuery-한방정리.md](./JavaScript-jQuery-한방정리.md)  
**체크리스트:** [jQuery-필수-체크리스트.md](./jQuery-필수-체크리스트.md)  
**문제:** [jQuery-한방정리-문제.md](./jQuery-한방정리-문제.md) · [jQuery-한방정리-정답.md](./jQuery-한방정리-정답.md)  
**JavaScript:** [JavaScript-한방정리.md](./JavaScript-한방정리.md) · [JavaScript-필수-체크리스트.md](./JavaScript-필수-체크리스트.md)

---

## 한 줄 요약

**jQuery = JavaScript로 HTML(DOM)을 쉽게 다루기 위한 라이브러리**

```javascript
// JavaScript
document.querySelector("#title").textContent = "안녕하세요";

// jQuery
$("#title").text("안녕하세요");
```

```
사용자 행동 → .on() 이벤트 → DOM 조작 → $.ajax() → Spring Boot → DB
```

---

## 목차

1. [기본 사용법](#1-jquery-기본-사용법-)
2. [$() 문법](#2--문법-)
3. [선택자](#3-jquery-선택자-)
4. [내용 변경](#4-내용-변경-)
5. [CSS 변경](#5-css-변경-)
6. [클래스 조작](#6-클래스-조작-)
7. [속성(attribute)](#7-속성attribute-)
8. [form 값](#8-form-값-)
9. [이벤트](#9-이벤트-)
10. [주요 이벤트](#10-주요-이벤트)
11. [이벤트 객체](#11-이벤트-객체-)
12. [this / $(this)](#12-this--this-)
13. [반복 처리 .each()](#13-반복-처리-each-)
14. [탐색 Traversing](#14-탐색-traversing-)
15. [요소 추가](#15-요소-추가-)
16. [요소 삭제](#16-요소-삭제-)
17. [보이기 / 숨기기](#17-보이기--숨기기-)
18. [애니메이션](#18-애니메이션-)
19. [크기](#19-크기-)
20. [위치](#20-위치-)
21. [Scroll](#21-scroll-)
22. [:checked](#22-checked-)
23. [필터 선택자](#23-필터-선택자-)
24. [jQuery UI](#24-jquery-ui-)
25. [Cookie 플러그인](#25-cookie-플러그인-)
26. [Ajax](#26-ajax-)
27. [Ajax → Spring Boot](#27-jquery-ajax--spring-boot)
28. [핵심 메서드 한 장 정리](#28-jquery-핵심-메서드-한-장-정리)
29. [우선순위 정리](#-우선순위-정리)

---

## 1. jQuery 기본 사용법 ⭐⭐⭐⭐⭐

### CDN 불러오기

HTML에서 jQuery를 **먼저** 불러옵니다.

```html
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="main.js"></script>
```

### DOM 준비 후 실행

```javascript
$(function () {
    // jQuery 코드 (HTML 로딩 완료 후 실행)
});
```

위 코드는 아래와 **같은 의미**입니다.

```javascript
$(document).ready(function () {
    // jQuery 코드
});
```

**학생 입문 예시:**

```javascript
$(function () {
    console.log("HTML 로딩 완료");
});
```

- [ ] CDN으로 jQuery를 불러올 수 있다
- [ ] `$(function(){})` 로 DOM 준비 후 코드를 실행할 수 있다

---

## 2. $() 문법 ⭐⭐⭐⭐⭐

jQuery에서 **가장 많이 사용**하는 문법입니다.

```javascript
$("#title");   // id가 title인 요소
$(".box");     // class가 box인 요소
$("p");        // 모든 p 요소
```

| 형태 | 의미 |
|------|------|
| `$("선택자")` | CSS 선택자와 동일한 방식으로 요소 선택 |
| 반환값 | **jQuery 객체** (DOM 요소가 아님) |

- [ ] `$()` 로 id / class / 태그를 선택할 수 있다

---

## 3. jQuery 선택자 ⭐⭐⭐⭐⭐

### HTML 예시

```html
<div id="box">
    <p class="text">첫 번째</p>
    <p class="text">두 번째</p>
</div>
```

### 선택 방법

| 선택 | 코드 |
|------|------|
| ID | `$("#box")` |
| class | `$(".text")` |
| 태그 | `$("p")` |
| 자손 | `$("#box p")` |
| 자식 | `$("#box > p")` |
| 여러 개 | `$("h1, p")` |

- [ ] id / class / 자손 / 자식 선택자를 사용할 수 있다

---

## 4. 내용 변경 ⭐⭐⭐⭐⭐

### text() — 텍스트만

```javascript
$("#title").text("안녕하세요");

// HTML 태그는 문자로 처리됨
$("#title").text("<strong>Hello</strong>");
// 화면: <strong>Hello</strong> (태그 적용 안 됨)
```

### html() — HTML 태그 적용

```javascript
$("#title").html("<strong>Hello</strong>");
// 화면: **Hello** (굵게 표시)
```

| 메서드 | 역할 |
|--------|------|
| `.text()` | 텍스트 (태그 무시) |
| `.html()` | HTML (태그 적용) |

- [ ] `text()`와 `html()`의 차이를 설명할 수 있다

---

## 5. CSS 변경 ⭐⭐⭐⭐⭐

### 한 가지 속성

```javascript
$("#box").css("color", "red");
$("#box").css("width", "200px");
```

### 여러 속성

```javascript
$("#box").css({
    "color": "red",
    "font-size": "30px",
    "background-color": "yellow"
});
```

- [ ] `.css()`로 스타일을 변경할 수 있다

---

## 6. 클래스 조작 ⭐⭐⭐⭐⭐

**실무에서 매우 중요**합니다.

```javascript
$("#box").addClass("active");      // 클래스 추가
$("#box").removeClass("active");   // 클래스 제거
$("#box").toggleClass("active");   // 있으면 제거, 없으면 추가
$("#box").hasClass("active");      // 클래스 존재 여부 (true/false)
```

**실무에서 많이 쓰는 형태:**

```javascript
$("#menu").toggleClass("active");   // 햄버거 메뉴 열기/닫기
```

- [ ] `addClass` / `removeClass` / `toggleClass`를 사용할 수 있다

---

## 7. 속성(attribute) ⭐⭐⭐⭐⭐

### attr() — 설정

```javascript
$("img").attr("src", "cat.jpg");
```

```html
<!-- 변경 전 -->
<img src="dog.jpg">
<!-- 변경 후 -->
<img src="cat.jpg">
```

### attr() — 가져오기 / 제거

```javascript
let src = $("img").attr("src");
$("img").removeAttr("src");
```

- [ ] `attr()`로 속성을 읽고·쓰고·삭제할 수 있다

---

## 8. form 값 ⭐⭐⭐⭐⭐

입력값은 **`.val()`** 을 사용합니다.

```html
<input type="text" id="username">
```

```javascript
let name = $("#username").val();        // 값 가져오기
$("#username").val("홍길동");            // 값 넣기
```

### 메서드 구분 (핵심)

| 메서드 | 용도 |
|--------|------|
| `.text()` | HTML **텍스트** 내용 |
| `.html()` | HTML **태그 포함** 내용 |
| `.val()` | **input** 값 |
| `.attr()` | HTML **속성** |
| `.css()` | **CSS** 스타일 |

- [ ] `input` 값을 `.val()`로 읽고 쓸 수 있다

---

## 9. 이벤트 ⭐⭐⭐⭐⭐

### click() — 단축 방식

```javascript
$("#btn").click(function () {
    alert("버튼 클릭");
});
```

### .on() — 권장 방식 (현대 jQuery)

```javascript
$("#btn").on("click", function () {
    alert("버튼 클릭");
});
```

> 수업에서는 **`.on()`** 을 기본으로 가르치는 것을 권장합니다.

- [ ] `.on("click", ...)` 으로 클릭 이벤트를 처리할 수 있다

---

## 10. 주요 이벤트

```javascript
$("#btn").on("click", function () { });
$("#box").on("mouseover", function () { });
$("#input").on("focus", function () { });
$("#input").on("blur", function () { });
$("#input").on("keyup", function () { });
$("#form").on("submit", function (e) { });
```

| 이벤트 | 의미 |
|--------|------|
| `click` | 클릭 |
| `dblclick` | 더블 클릭 |
| `mouseover` | 마우스 올라감 |
| `mouseout` | 마우스 나감 |
| `mouseenter` | 마우스 진입 |
| `mouseleave` | 마우스 이탈 |
| `focus` | 포커스 |
| `blur` | 포커스 해제 |
| `keydown` | 키 누름 |
| `keyup` | 키 뗌 |
| `change` | 값 변경 (select, checkbox 등) |
| `submit` | form 제출 |

---

## 11. 이벤트 객체 ⭐⭐⭐⭐

```javascript
$("#btn").on("click", function (e) {
    console.log(e);
});
```

### preventDefault() — 기본 동작 막기

```javascript
$("#form").on("submit", function (e) {
    e.preventDefault();   // 폼 제출(페이지 새로고침) 막기
});

$("a").on("click", function (e) {
    e.preventDefault();   // 링크 이동 막기
});
```

- [ ] `e.preventDefault()`로 폼 제출·링크 이동을 막을 수 있다

---

## 12. this / $(this) ⭐⭐⭐⭐⭐

학생들이 **많이 헷갈리는** 부분입니다.

```javascript
$("li").on("click", function () {
    console.log(this);              // 클릭된 li (DOM 객체)
    alert($(this).text());          // jQuery 메서드 사용 시 $(this)
});
```

```
this        → JavaScript DOM 객체
$(this)     → jQuery 객체 (메서드 사용 가능)
```

| | `this` | `$(this)` |
|---|--------|-----------|
| 타입 | DOM 요소 | jQuery 객체 |
| `.text()` | ❌ 불가 | ✅ 가능 |
| `.addClass()` | ❌ 불가 | ✅ 가능 |

- [ ] `this`와 `$(this)`의 차이를 설명할 수 있다

---

## 13. 반복 처리 .each() ⭐⭐⭐⭐⭐

```html
<ul>
    <li>사과</li>
    <li>바나나</li>
    <li>포도</li>
</ul>
```

```javascript
$("li").each(function () {
    console.log($(this).text());
});
```

```
사과
바나나
포도
```

> JavaScript의 `document.querySelectorAll("li").forEach(...)` 와 비슷합니다.

- [ ] `.each()`로 여러 요소를 순회할 수 있다

---

## 14. 탐색 Traversing ⭐⭐⭐⭐

jQuery의 **큰 장점** 중 하나입니다.

```html
<div class="box">
    <h2>제목</h2>
    <p>내용</p>
</div>
```

| 메서드 | 의미 |
|--------|------|
| `.parent()` | 부모 요소 |
| `.children()` | 자식 요소들 |
| `.children("p")` | 특정 자식만 |
| `.next()` | 다음 형제 |
| `.prev()` | 이전 형제 |
| `.siblings()` | 형제 요소들 |
| `.find("p")` | 자손 중 찾기 |

```javascript
$("h2").parent();
$(".box").children();
$(".box").children("p");
$("h2").next();
$("p").prev();
$("h2").siblings();
$(".box").find("p");
```

- [ ] `parent` / `children` / `find` / `siblings`를 사용할 수 있다

---

## 15. 요소 추가 ⭐⭐⭐⭐

```javascript
$("#list").append("<li>사과</li>");    // 뒤에 추가
$("#list").prepend("<li>사과</li>");   // 앞에 추가

// 요소 객체로 추가 (권장)
$("#list").append(
    $("<li>").text("사과")
);
```

| 메서드 | 의미 |
|--------|------|
| `append()` | **안쪽 맨 뒤**에 추가 |
| `prepend()` | **안쪽 맨 앞**에 추가 |

---

## 16. 요소 삭제 ⭐⭐⭐⭐

```javascript
$("#box").remove();   // 요소 자체 삭제
$("#box").empty();    // 안쪽 내용만 삭제 (요소는 남음)
```

```
remove()  →  <div id="box"> 자체가 사라짐

empty()   →  <div id="box"></div>  (껍데기만 남음)
```

| 메서드 | 의미 |
|--------|------|
| `remove()` | 요소 **자체** 삭제 |
| `empty()` | **내부 내용만** 삭제 |

---

## 17. 보이기 / 숨기기 ⭐⭐⭐⭐⭐

```javascript
$("#box").hide();
$("#box").show();
$("#box").toggle();   // 보이면 숨기고, 숨기면 보임
```

> JavaScript: `element.style.display = "none"` 과 대응됩니다.

- [ ] `hide` / `show` / `toggle`을 사용할 수 있다

---

## 18. 애니메이션 ⭐⭐⭐⭐

### fade

```javascript
$("#box").fadeIn();
$("#box").fadeOut();
$("#box").fadeToggle();
$("#box").fadeOut(1000);   // 1초 동안 사라짐
```

### slide (아코디언에 많이 사용)

```javascript
$("#box").slideUp();
$("#box").slideDown();
$("#box").slideToggle();
```

- [ ] `fadeToggle` / `slideToggle`로 아코디언을 만들 수 있다

---

## 19. 크기 ⭐⭐⭐

```javascript
$("#box").width();    // 너비 가져오기
$("#box").height();   // 높이 가져오기

$("#box").width(300);
$("#box").height(200);
```

---

## 20. 위치 ⭐⭐⭐

```javascript
$("#box").offset();    // 문서(document) 기준 위치
$("#box").position();  // 부모 요소 기준 위치
```

---

## 21. Scroll ⭐⭐⭐⭐

### 스크롤 위치

```javascript
$(window).scrollTop();   // 현재 스크롤 양
```

### 스크롤 이벤트

```javascript
$(window).on("scroll", function () {
    console.log($(window).scrollTop());
});
```

### TOP 버튼 (강의 예제로 추천)

```javascript
$("#topBtn").on("click", function () {
    $("html, body").animate({
        scrollTop: 0
    }, 500);
});
```

- [ ] `scrollTop()`으로 스크롤 위치를 읽을 수 있다
- [ ] TOP 버튼으로 맨 위로 스크롤할 수 있다

---

## 22. :checked ⭐⭐⭐⭐

```javascript
if ($("#agree").is(":checked")) {
    alert("동의했습니다.");
}

let checked = $("#agree").prop("checked");
```

| 방법 | 설명 |
|------|------|
| `.is(":checked")` | 체크 여부 (jQuery 필터) |
| `.prop("checked")` | checked 속성 값 (권장) |

---

## 23. 필터 선택자 ⭐⭐⭐

```javascript
$("li:first");    // 첫 번째
$("li:last");     // 마지막
$("li:eq(2)");    // 3번째 (0부터)
$("li:even");     // 짝수 번째
$("li:odd");      // 홀수 번째
```

```javascript
$("li:eq(2)").css("color", "red");   // 3번째 li 빨간색
```

---

## 24. jQuery UI ⭐⭐⭐

> **주의:** `draggable()` 등은 jQuery **기본 기능이 아닙니다.**

```
jQuery  +  jQuery UI  →  draggable(), droppable() 등
```

```html
<script src="jquery.min.js"></script>
<script src="jquery-ui.min.js"></script>
```

```javascript
$("#box").draggable();
```

| | jQuery 본체 | jQuery UI |
|---|-------------|-----------|
| 선택·조작·이벤트 | ✅ | — |
| draggable, sortable | — | ✅ |

---

## 25. Cookie 플러그인 ⭐⭐⭐

> **주의:** `$.cookie()`도 jQuery **기본 기능이 아닙니다.**  
> 별도 **쿠키 플러그인**이 필요합니다.

```javascript
$.cookie("popup", "none", { expires: 3 });
let popup = $.cookie("popup");
```

| API | 정체 |
|-----|------|
| `$("#box")` | jQuery 본체 |
| `$.cookie()` | jquery.cookie **플러그인** |
| `$("#box").draggable()` | jQuery **UI** |

학생들에게 **본체 / UI / 플러그인**을 구분해 주세요.

---

## 26. Ajax ⭐⭐⭐⭐⭐

jQuery의 **가장 중요한 활용** 중 하나입니다.

### GET

```javascript
$.ajax({
    url: "/api/users",
    type: "GET",
    success: function (data) {
        console.log(data);
    },
    error: function () {
        console.log("오류 발생");
    }
});
```

### POST

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

### 단축 메서드

```javascript
$.get("/api/users", function (data) { console.log(data); });
$.post("/api/users", { name: "홍길동" }, function (data) { });
```

- [ ] `$.ajax`로 GET / POST 요청을 보낼 수 있다

---

## 27. jQuery Ajax → Spring Boot

학생들에게 **이 연결을 꼭** 보여주면 좋습니다.

### 요청 흐름

```
브라우저
   │
   │ $.ajax()
   ↓
Spring Boot (Controller)
   ↓
Service
   ↓
Repository
   ↓
MySQL
```

### 응답 흐름

```
MySQL
 ↓
Repository
 ↓
Service
 ↓
Controller
 ↓
JSON
 ↓
$.ajax() success
 ↓
$("#list").append(...)  등으로 화면 갱신
```

> 이 부분부터 **프론트엔드와 백엔드가 연결**되는 느낌을 학생들이 받습니다.

---

## 28. jQuery 핵심 메서드 한 장 정리

| 기능 | jQuery |
|------|--------|
| 선택 | `$()` |
| 텍스트 | `.text()` |
| HTML | `.html()` |
| CSS | `.css()` |
| 클래스 추가 | `.addClass()` |
| 클래스 삭제 | `.removeClass()` |
| 클래스 토글 | `.toggleClass()` |
| 클래스 확인 | `.hasClass()` |
| 속성 | `.attr()` |
| 속성 삭제 | `.removeAttr()` |
| 입력값 | `.val()` |
| 이벤트 | `.on()` |
| 반복 | `.each()` |
| 부모 | `.parent()` |
| 자식 | `.children()` |
| 다음 | `.next()` |
| 이전 | `.prev()` |
| 찾기 | `.find()` |
| 뒤에 추가 | `.append()` |
| 앞에 추가 | `.prepend()` |
| 삭제 | `.remove()` |
| 내부 삭제 | `.empty()` |
| 숨김 | `.hide()` |
| 표시 | `.show()` |
| 토글 | `.toggle()` |
| 페이드 | `.fadeIn()` / `.fadeOut()` |
| 슬라이드 | `.slideUp()` / `.slideDown()` |
| 스크롤 | `.scrollTop()` |
| Ajax | `$.ajax()` |

---

## JavaScript vs jQuery 빠른 대응

| 작업 | JavaScript | jQuery |
|------|------------|--------|
| 선택 | `document.querySelector("#id")` | `$("#id")` |
| 텍스트 | `.textContent` | `.text()` |
| HTML | `.innerHTML` | `.html()` |
| 클래스 | `.classList.add()` | `.addClass()` |
| 이벤트 | `.addEventListener()` | `.on()` |
| 숨기기 | `.style.display = "none"` | `.hide()` |
| Ajax | `fetch()` | `$.ajax()` |

---

## ⭐ 우선순위 정리

### 🔥 반드시 할 것

| 영역 | 항목 |
|------|------|
| 기본 | CDN, `$(function(){})`, `$()` 선택자 |
| 조작 | `.text()` `.html()` `.val()` `.css()` `.attr()` |
| 클래스 | `add/remove/toggleClass`, `$(this)` |
| 이벤트 | `.on("click")`, `e.preventDefault()` |
| 탐색 | `.parent()` `.children()` `.find()` |
| 효과 | `hide/show/toggle`, `slideToggle` (아코디언) |
| 스크롤 | `scrollTop()`, TOP 버튼 |
| 서버 | `$.ajax` GET/POST, Spring Boot 연결 이해 |

### ⭐ 알면 좋은 것

- `.each()`, 필터 선택자 (`:eq`, `:first`)
- `fadeIn/Out`, `animate`
- `:checked`, `.prop()`
- `offset()` / `position()`
- jQuery UI, Cookie **플러그인 구분**

### 🟡 시간이 부족하면 간단히

- `before()` / `after()` / `clone()`
- 복잡한 필터 선택자
- `$.when()` / Deferred
- jQuery Migrate

---

## 🎯 학생 완료 기준

- [ ] 탭 메뉴 / 아코디언을 jQuery로 직접 구현할 수 있다
- [ ] 폼 입력값을 `.val()`로 읽고 유효성 검사할 수 있다
- [ ] `$.ajax`로 API 데이터를 받아 화면에 출력할 수 있다
- [ ] jQuery 본체 / UI / 플러그인 차이를 설명할 수 있다

---

## 관련 교안·예제

| 자료 | 경로 |
|------|------|
| JavaScript 한방 정리 | [JavaScript-한방정리.md](./JavaScript-한방정리.md) |
| jQuery 커리큘럼 (31주제) | [jQuery/README.md](./jQuery/README.md) |
| 선택자·조작 | [jQuery/01-기본-선택자-조작.md](./jQuery/01-기본-선택자-조작.md) |
| 이벤트·탐색 | [jQuery/02-이벤트-탐색-DOM.md](./jQuery/02-이벤트-탐색-DOM.md) |
| 효과·AJAX | [jQuery/03-효과-폼-AJAX.md](./jQuery/03-효과-폼-AJAX.md) |
| 실습 예제 HTML | [jQuery/examples/](./jQuery/examples/) |
| jQuery → Vanilla | [jquery_to_vanilla/jQuery-to-VanillaJS-완전정복.md](./jquery_to_vanilla/jQuery-to-VanillaJS-완전정복.md) |
| 스크롤 도식화 | [scroll/offsetTop-scrollTop-도식화.md](./scroll/offsetTop-scrollTop-도식화.md) |
