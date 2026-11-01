# JavaScript · jQuery 한방 정리 (수업용 통합본)

비전공자·입문자용. 강사가 화면에 띄워 설명하기 좋은 **핵심만** 모았습니다.

| 상세 교안 | 경로 |
|-----------|------|
| JS 전체 | [JavaScript-한방정리.md](./JavaScript-한방정리.md) |
| jQuery 전체 | [jQuery-한방정리.md](./jQuery-한방정리.md) |
| JS 체크리스트 | [JavaScript-필수-체크리스트.md](./JavaScript-필수-체크리스트.md) |
| jQuery 체크리스트 | [jQuery-필수-체크리스트.md](./jQuery-필수-체크리스트.md) |
| **JS 문제 / 정답** | [JavaScript-한방정리-문제.md](./JavaScript-한방정리-문제.md) · [JavaScript-한방정리-정답.md](./JavaScript-한방정리-정답.md) |
| jQuery 문제 | [jQuery-한방정리-문제.md](./jQuery-한방정리-문제.md) |

---

## 📌 한 줄 요약

```
HTML = 뼈대
CSS  = 옷
JS   = 동작 (언어)
jQuery = JS로 DOM을 쉽게 다루는 라이브러리
```

```
사용자 클릭
   → 이벤트 (addEventListener / .on)
   → 화면 변경 (DOM / jQuery)
   → 서버 통신 (fetch / $.ajax)
   → Spring Boot → DB
```

---

## 1. JavaScript vs jQuery

| | JavaScript | jQuery |
|--|------------|--------|
| 정체 | 웹 **프로그래밍 언어** | **라이브러리** (JS로 만듦) |
| 필수? | 필수 | 선택 (레거시·빠른 UI) |
| 선택 | `querySelector` | `$("#id")` |
| 이벤트 | `addEventListener` | `.on("click")` |
| 서버 | `fetch` | `$.ajax` |

> 수업 권장: **JS DOM + 이벤트 + fetch**를 먼저, jQuery는 탭·아코디언·기존 코드 이해용.

---

## 2. JavaScript 핵심

### 2-1. 변수

```javascript
let name = "홍길동";   // 재할당 가능
const PI = 3.14;      // 상수 (재할당 불가)
// var 는 수업에서 비권장
```

### 2-2. 자료형 · 비교

```javascript
typeof "hi";     // "string"
typeof 10;       // "number"
typeof true;     // "boolean"

10 == "10";      // true  (느슨)
10 === "10";     // false (엄격) ← 수업은 === 사용
```

### 2-3. 조건 · 반복

```javascript
if (score >= 90) { }
else if (score >= 80) { }
else { }

for (let i = 0; i < 5; i++) { }
while (조건) { }

const arr = [1, 2, 3];
arr.forEach((n) => console.log(n));
```

### 2-4. 배열 · 객체

```javascript
const fruits = ["사과", "바나나"];
fruits.push("포도");
fruits.length;

const user = { name: "홍길동", age: 20 };
user.name;
user["age"];
```

자주 쓰는 배열 메서드:

```javascript
arr.map((x) => x * 2);
arr.filter((x) => x > 10);
arr.find((x) => x.id === 1);
```

### 2-5. 함수

```javascript
function add(a, b) {
  return a + b;
}

const add2 = (a, b) => a + b;
```

### 2-6. DOM · 이벤트 (필수)

```javascript
const title = document.querySelector("#title");
title.textContent = "안녕하세요";
title.classList.add("on");

document.querySelector("#btn").addEventListener("click", function (e) {
  e.preventDefault();
  console.log("클릭");
});
```

| 작업 | 코드 |
|------|------|
| 선택 | `querySelector` / `querySelectorAll` |
| 텍스트 | `textContent` |
| HTML | `innerHTML` |
| 클래스 | `classList.add/remove/toggle` |
| 입력값 | `input.value` |

### 2-7. 폼

```javascript
form.addEventListener("submit", function (e) {
  e.preventDefault();
  const name = document.querySelector("#name").value;
});
```

### 2-8. JSON · fetch

```javascript
JSON.stringify(obj);   // 객체 → 문자열
JSON.parse(str);       // 문자열 → 객체

const res = await fetch("/api/users");
const data = await res.json();

await fetch("/api/users", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ name: "홍길동" }),
});
```

### 2-9. ES6 빠른 메모

```javascript
`안녕 ${name}`;              // 템플릿 리터럴
const { name, age } = user;  // 구조 분해
const copy = [...arr];       // 전개
```

---

## 3. jQuery 핵심

### 3-1. 시작

```html
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
  $(function () {
    // DOM 준비 후 실행
  });
</script>
```

### 3-2. 선택 · 내용 · CSS

```javascript
$("#title");           // id
$(".item");            // class
$("div");              // 태그

$("#title").text("안녕");
$("#box").html("<b>강조</b>");
$("#name").val();      // input 값
$("#box").css("color", "red");
```

### 3-3. 클래스 · 속성

```javascript
$el.addClass("on");
$el.removeClass("on");
$el.toggleClass("on");
$el.attr("href");          // 속성
$el.prop("checked");       // 체크박스 등
```

### 3-4. 이벤트

```javascript
$("#btn").on("click", function (e) {
  e.preventDefault();
  $(this).addClass("on");  // 클릭한 그 요소
});
```

| 이벤트 | 메서드 예 |
|--------|-----------|
| 클릭 | `.on("click")` / `.click()` |
| 입력 | `.on("keyup")` |
| 제출 | `.on("submit")` |
| 스크롤 | `$(window).on("scroll")` |

### 3-5. 탐색

```javascript
$el.parent();
$el.children();
$el.find(".item");
$el.siblings();
$el.next();
$el.prev();
```

### 3-6. 추가 · 삭제 · 효과

```javascript
$parent.append("<li>새항목</li>");
$el.remove();
$el.empty();

$el.hide();
$el.show();
$el.toggle();
$el.slideToggle();   // 아코디언에 자주 사용
$el.fadeIn();
```

### 3-7. each

```javascript
$(".item").each(function (i) {
  console.log(i, $(this).text());
});
```

### 3-8. Ajax

```javascript
$.ajax({
  url: "/api/users",
  type: "GET",
  dataType: "json",
  success: function (data) {
    console.log(data);
  },
  error: function () {
    alert("실패");
  },
});
```

---

## 4. 같은 작업 대비표 (암기)

| 하고 싶은 일 | JavaScript | jQuery |
|--------------|------------|--------|
| id 선택 | `document.querySelector("#a")` | `$("#a")` |
| 텍스트 | `.textContent = "x"` | `.text("x")` |
| HTML | `.innerHTML = "..."` | `.html("...")` |
| 값 읽기 | `.value` | `.val()` |
| 클래스 추가 | `.classList.add("on")` | `.addClass("on")` |
| 클릭 | `.addEventListener("click", fn)` | `.on("click", fn)` |
| 숨기기 | `.style.display = "none"` | `.hide()` |
| 보이기 | `.style.display = "block"` | `.show()` |
| Ajax GET | `fetch(url).then(...)` | `$.ajax({ url, type:"GET" })` |

---

## 5. 수업에서 만드는 예제

| 예제 | JS | jQuery |
|------|----|--------|
| 버튼 클릭 → 문구 변경 | ✅ | ✅ |
| 탭 메뉴 | classList | addClass + siblings |
| 아코디언 | — | slideToggle |
| 폼 유효성 | value + preventDefault | val() |
| TOP 버튼 | scrollY | scrollTop() |
| 목록 출력 | fetch + innerHTML | $.ajax + append |

---

## 6. 우선순위 (체크용)

### JavaScript — 반드시

- [ ] `let` / `const`, `===`
- [ ] 배열·객체·함수
- [ ] `querySelector` + `addEventListener`
- [ ] `classList`, `textContent`, `value`
- [ ] `preventDefault`
- [ ] `fetch` + JSON

### jQuery — 반드시

- [ ] CDN + `$(function(){})`
- [ ] `$("#")` `$(".")` 선택
- [ ] `.text` `.html` `.val` `.css`
- [ ] `.on` + `$(this)`
- [ ] `.addClass` / `.parent` / `.find`
- [ ] `.hide` `.show` `.slideToggle`
- [ ] `$.ajax` 기본형

---

## 7. 강사 멘트

1. **jQuery는 언어가 아니라 도구**다.  
2. `$()` 는 “선택해서 jQuery 객체로 감싼다”.  
3. 이벤트 안에서는 **`$(this)`** = 방금 그 요소.  
4. JS를 알면 jQuery는 **짧은 문법**일 뿐이다.  
5. 서버 연동은 결국 **JSON + HTTP** (fetch든 ajax든).

---

## 8. 다음 단계

```
HTML/CSS
  → JavaScript (이 문서 §2)
  → jQuery UI 패턴 (이 문서 §3)
  → fetch / $.ajax
  → Spring Boot API 연동
```
