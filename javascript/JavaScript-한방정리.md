# JavaScript 한방 정리 (기초 → Spring Boot 연동)

풀스택 과정(HTML/CSS → JavaScript → jQuery → AJAX/JSON → Spring Boot) 기준으로  
학생이 **반드시 이해하고 직접 쓸 수 있어야 하는** JavaScript 핵심을 정리한 문서입니다.

**통합(화면용):** [JavaScript-jQuery-한방정리.md](./JavaScript-jQuery-한방정리.md)  
**체크리스트:** [JavaScript-필수-체크리스트.md](./JavaScript-필수-체크리스트.md)  
**문제 / 정답:** [JavaScript-한방정리-문제.md](./JavaScript-한방정리-문제.md) · [JavaScript-한방정리-정답.md](./JavaScript-한방정리-정답.md)  
**jQuery:** [jQuery-한방정리.md](./jQuery-한방정리.md) · [jQuery-필수-체크리스트.md](./jQuery-필수-체크리스트.md)

---

## 목차

1. [기본 문법](#1-javascript-기본-문법-)
2. [연산자](#2-연산자-)
3. [조건문](#3-조건문-)
4. [반복문](#4-반복문-)
5. [배열](#5-배열-)
6. [객체](#6-객체-)
7. [함수](#7-함수-)
8. [DOM](#8-dom-)
9. [DOM 조작](#9-dom-조작-)
10. [이벤트](#10-이벤트-)
11. [이벤트 객체](#11-이벤트-객체-)
12. [this](#12-this-)
13. [폼 처리](#13-폼-처리-)
14. [JSON](#14-json-)
15. [비동기 처리](#15-비동기-처리-)
16. [Fetch API](#16-fetch-api-)
17. [POST 요청](#17-post-)
18. [ES6+ 문법](#18-es6-문법-)
19. [모듈](#19-모듈-)
20. [jQuery와 비교](#20-jquery와-비교)
21. [우선순위 정리](#-우선순위-정리)
22. [전체 웹 개발 흐름](#-전체-웹-개발-흐름)

---

## 한 줄 요약

**HTML은 뼈대, CSS는 옷, JavaScript는 동작이다.**

```
사용자 행동 → 이벤트 → DOM 조작 → (fetch) → Spring Boot → DB
```

---

## 1. JavaScript 기본 문법 ⭐⭐⭐

### 변수

```javascript
let name = "홍길동";
const age = 20;
var address = "서울";   // 예전 방식 → 기본적으로 권장하지 않음
```

| 키워드 | 설명 |
|--------|------|
| `let` | 값 변경 가능 (재할당 가능) |
| `const` | 재할당 불가 (객체·배열 내부 변경은 가능) |
| `var` | 함수 스코프, 호이스팅 문제 → **실무·수업 모두 `let`/`const` 권장** |

```javascript
let score = 80;
score = 90;   // let은 변경 가능
```

### 자료형

```javascript
let name = "홍길동";       // String (문자열)
let age = 20;             // Number (숫자)
let isStudent = true;     // Boolean (참/거짓)
let data = null;          // null (의도적 빈 값)
let x;                    // undefined (값 미할당)
```

### 배열

```javascript
let fruits = ["사과", "바나나", "포도"];
```

### 객체

```javascript
let student = {
    name: "홍길동",
    age: 20,
    score: 90
};
```

- [ ] `let` / `const` 차이를 설명할 수 있다
- [ ] 배열과 객체를 선언하고 값에 접근할 수 있다

---

## 2. 연산자 ⭐⭐⭐

### 산술 연산자

```javascript
let a = 10;
let b = 3;

console.log(a + b);   // 13
console.log(a - b);   // 7
console.log(a * b);   // 30
console.log(a / b);   // 3.333...
console.log(a % b);   // 1 (나머지)
```

### 비교 연산자

```javascript
10 > 5        // true
10 < 5        // false
10 == "10"    // true  (값만 비교, 타입 변환)
10 === "10"   // false (값 + 타입 비교)
```

> **실무에서는 `===` 사용을 권장합니다.**

| 연산자 | 의미 |
|--------|------|
| `==` | 값만 같으면 true (타입 변환 발생) |
| `===` | 값과 타입이 모두 같아야 true |
| `!=` / `!==` | 같지 않음 (`!==` 권장) |

- [ ] `==`와 `===`의 차이를 설명할 수 있다

---

## 3. 조건문 ⭐⭐⭐

```javascript
let score = 85;

if (score >= 90) {
    console.log("A");
} else if (score >= 80) {
    console.log("B");
} else {
    console.log("C");
}
```

### 삼항 연산자

```javascript
let result = score >= 60 ? "합격" : "불합격";
```

- [ ] if / else if / else로 분기할 수 있다
- [ ] 삼항 연산자로 한 줄 조건 처리를 할 수 있다

---

## 4. 반복문 ⭐⭐⭐

### for

```javascript
for (let i = 0; i < 5; i++) {
    console.log(i);   // 0 1 2 3 4
}
```

### while

```javascript
let i = 0;

while (i < 5) {
    console.log(i);
    i++;
}
```

- [ ] for / while로 반복문을 작성할 수 있다

---

## 5. 배열 ⭐⭐⭐⭐⭐

프론트엔드에서 **매우 중요**합니다.

```javascript
let fruits = ["사과", "바나나", "포도"];
```

### 기본 접근

```javascript
console.log(fruits[0]);   // "사과"
console.log(fruits.length);  // 3
```

### 주요 메서드

| 메서드 | 의미 |
|--------|------|
| `push()` | 마지막에 추가 |
| `pop()` | 마지막 삭제 |
| `shift()` | 첫 번째 삭제 |
| `unshift()` | 처음에 추가 |
| `slice()` | 일부 추출 (원본 유지) |
| `splice()` | 추가/삭제 (원본 변경) |
| `indexOf()` | 위치 찾기 |
| `includes()` | 포함 여부 |
| `forEach()` | 반복 |
| `map()` | 새로운 배열 생성 |
| `filter()` | 조건에 맞는 배열 |
| `find()` | 조건에 맞는 첫 요소 |
| `reduce()` | 하나의 값으로 축약 |

```javascript
let numbers = [1, 2, 3, 4, 5];

numbers.forEach(function (num) {
    console.log(num);
});

// map 예시
let doubled = numbers.map(num => num * 2);
// [2, 4, 6, 8, 10]
```

- [ ] `push` / `pop` / `forEach` / `map` / `filter`를 사용할 수 있다

---

## 6. 객체 ⭐⭐⭐⭐⭐

```javascript
const user = {
    name: "홍길동",
    age: 20,
    email: "test@test.com"
};
```

### 접근

```javascript
console.log(user.name);      // 점 표기법
console.log(user["age"]);    // 대괄호 표기법
```

### 값 변경 / 속성 추가

```javascript
user.age = 30;
user.address = "서울";
```

- [ ] 객체 속성에 접근·수정·추가할 수 있다

---

## 7. 함수 ⭐⭐⭐⭐⭐

> **함수 = 특정 작업을 하나로 묶어 놓은 것**

### 기본 함수 (함수 선언)

```javascript
function add(a, b) {
    return a + b;
}

let result = add(10, 20);
console.log(result);   // 30
```

### 함수 표현식

```javascript
const add = function (a, b) {
    return a + b;
};
```

### 화살표 함수 ⭐⭐⭐⭐⭐

```javascript
const add = (a, b) => {
    return a + b;
};

// 한 줄이면 return 생략 가능
const add = (a, b) => a + b;
```

- [ ] 함수 선언 / 표현식 / 화살표 함수를 작성할 수 있다

---

## 8. DOM ⭐⭐⭐⭐⭐

**여기부터 웹 개발에서 JavaScript의 핵심입니다.**

### HTML

```html
<h1 id="title">안녕하세요</h1>
```

### JavaScript

```javascript
const title = document.getElementById("title");
title.innerHTML = "반갑습니다";
```

### 주요 선택 방법

```javascript
document.getElementById("title");
document.querySelector("#title");
document.querySelector(".box");
document.querySelectorAll(".item");
```

| 방법 | 설명 |
|------|------|
| `getElementById` | id 하나 선택 |
| `querySelector` | CSS 선택자로 **첫 번째** 요소 |
| `querySelectorAll` | CSS 선택자로 **모든** 요소 (NodeList) |

> 현대 JS에서는 **`querySelector` / `querySelectorAll`** 을 많이 사용합니다.

- [ ] id / class로 DOM 요소를 선택할 수 있다

---

## 9. DOM 조작 ⭐⭐⭐⭐⭐

### 내용 변경

```javascript
element.innerHTML = "안녕하세요";   // HTML 태그 포함 가능
element.textContent = "안녕하세요"; // 텍스트만
```

### 스타일 변경

```javascript
element.style.color = "red";
element.style.fontSize = "30px";
```

### 클래스 조작

```javascript
element.classList.add("active");
element.classList.remove("active");
element.classList.toggle("active");
element.classList.contains("active");
```

### 속성 조작

```javascript
element.setAttribute("src", "image.jpg");
element.getAttribute("src");
element.removeAttribute("src");
```

- [ ] `innerHTML` / `textContent` / `classList` / `style`을 사용할 수 있다

---

## 10. 이벤트 ⭐⭐⭐⭐⭐

웹에서 **매우 중요**합니다.

```javascript
const button = document.querySelector("#btn");

button.addEventListener("click", function () {
    alert("버튼 클릭!");
});
```

### 주요 이벤트

| 이벤트 | 의미 |
|--------|------|
| `click` | 클릭 |
| `dblclick` | 더블 클릭 |
| `mouseover` | 마우스 올림 |
| `mouseout` | 마우스 나감 |
| `mousemove` | 마우스 이동 |
| `keydown` | 키 누름 |
| `keyup` | 키 뗌 |
| `change` | 값 변경 (select, checkbox 등) |
| `input` | 입력 중 |
| `submit` | 폼 제출 |
| `focus` | 포커스 |
| `blur` | 포커스 해제 |

- [ ] `addEventListener`로 클릭·입력 이벤트를 처리할 수 있다

---

## 11. 이벤트 객체 ⭐⭐⭐⭐

```javascript
button.addEventListener("click", function (e) {
    console.log(e);
});
```

```javascript
document.addEventListener("mousemove", function (e) {
    console.log(e.pageX);
    console.log(e.pageY);
});
```

### 흐름

```
사용자가 행동
     ↓
이벤트 발생
     ↓
이벤트 객체(e) 전달
     ↓
e를 통해 이벤트 정보 확인
```

| 속성/메서드 | 설명 |
|-------------|------|
| `e.preventDefault()` | 기본 동작 막기 (폼 제출, 링크 이동 등) |
| `e.target` | 이벤트가 발생한 요소 |
| `e.pageX` / `e.pageY` | 마우스 좌표 |

- [ ] 이벤트 객체 `e`의 역할을 설명할 수 있다

---

## 12. this ⭐⭐⭐⭐

이벤트에서 **많이 사용**합니다.

```javascript
const items = document.querySelectorAll("li");

items.forEach(function (item) {
    item.addEventListener("click", function () {
        console.log(this);           // 클릭된 li 요소
        console.log(this.innerHTML);
    });
});
```

### 일반 함수 vs 화살표 함수

```javascript
// 일반 함수 → this = 이벤트가 발생한 요소
item.addEventListener("click", function () {
    console.log(this);   // 클릭된 li
});

// 화살표 함수 → this가 다르게 동작 (상위 스코프의 this)
item.addEventListener("click", () => {
    console.log(this);   // window 등 (요소가 아님)
});
```

> 학생들에게는 **이벤트의 `this`는 일반 함수로 먼저** 설명하는 것이 좋습니다.

- [ ] 이벤트 핸들러에서 `this`가 클릭된 요소를 가리킴을 이해한다

---

## 13. 폼 처리 ⭐⭐⭐⭐⭐

### HTML

```html
<form id="loginForm">
    <input type="text" id="userid">
    <button type="submit">로그인</button>
</form>
```

### JavaScript

```javascript
const form = document.querySelector("#loginForm");

form.addEventListener("submit", function (e) {
    e.preventDefault();   // 기본 제출(페이지 새로고침) 막기

    const userid = document.querySelector("#userid").value;
    console.log(userid);
});
```

### 반드시 알아야 할 것

| 항목 | 설명 |
|------|------|
| `e.preventDefault()` | 폼의 **기본 동작을 막음** (페이지 이동·새로고침 방지) |
| `input.value` | 입력값 가져오기 |

- [ ] 폼 submit을 막고 `input.value`로 값을 읽을 수 있다

---

## 14. JSON ⭐⭐⭐⭐⭐

Spring Boot와 연결하려면 **반드시** 알아야 합니다.

### JSON 형식

```json
{
    "name": "홍길동",
    "age": 20
}
```

### JavaScript 객체

```javascript
const user = {
    name: "홍길동",
    age: 20
};
```

### 변환

```javascript
// 객체 → JSON 문자열
JSON.stringify(user);
// '{"name":"홍길동","age":20}'

// JSON 문자열 → 객체
JSON.parse('{"name":"홍길동","age":20}');
```

### Spring Boot와의 연결

```
JavaScript 객체
      ↓
JSON.stringify()
      ↓
JSON 문자열
      ↓
HTTP (요청/응답 body)
      ↓
Spring Boot (@RequestBody)
```

- [ ] `JSON.stringify` / `JSON.parse`를 사용할 수 있다

---

## 15. 비동기 처리 ⭐⭐⭐⭐⭐

JavaScript는 **비동기**로 동작하는 경우가 많습니다 (API 호출, 타이머 등).

### Promise

```javascript
const promise = new Promise(function (resolve, reject) {
    resolve("성공");
});

promise.then(result => {
    console.log(result);
});
```

### async / await ⭐⭐⭐⭐⭐

```javascript
async function getData() {
    const result = await getSomething();
    console.log(result);
}
```

> **`await` = 결과가 올 때까지 기다렸다가 다음 코드를 실행**

- [ ] `async` / `await`의 기본 개념을 설명할 수 있다

---

## 16. Fetch API ⭐⭐⭐⭐⭐

JavaScript → Spring Boot **REST API 연결의 핵심**입니다.

### GET (then 체인)

```javascript
fetch("/api/users")
    .then(response => response.json())
    .then(data => {
        console.log(data);
    });
```

### GET (async/await) — 권장

```javascript
async function getUsers() {
    const response = await fetch("/api/users");
    const data = await response.json();
    console.log(data);
}
```

- [ ] `fetch`로 GET 요청 후 `response.json()`으로 데이터를 받을 수 있다

---

## 17. POST ⭐⭐⭐⭐⭐

```javascript
async function saveUser() {
    const user = {
        name: "홍길동",
        age: 20
    };

    const response = await fetch("/api/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    });

    const data = await response.json();
    console.log(data);
}
```

| 항목 | 설명 |
|------|------|
| `method: "POST"` | HTTP 메서드 |
| `headers` | 요청 헤더 (JSON 전송 시 필수) |
| `body` | 전송할 데이터 (`JSON.stringify`) |

### 전체 웹 개발 흐름 (이 정도까지 이해하면)

```
HTML
 ↓
JavaScript (fetch)
 ↓
REST API
 ↓
Spring Boot (Controller)
 ↓
Service
 ↓
Repository
 ↓
DB
```

- [ ] POST로 JSON 데이터를 서버에 전송할 수 있다

---

## 18. ES6+ 문법 ⭐⭐⭐⭐⭐

현대 JavaScript·React에서 **중요**합니다.

### 구조 분해 (Destructuring)

```javascript
// 객체
const user = { name: "홍길동", age: 20 };
const { name, age } = user;

// 배열
const [a, b, c] = [10, 20, 30];
```

### 전개 연산자 (Spread)

```javascript
const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5];   // [1, 2, 3, 4, 5]

const obj1 = { name: "홍길동" };
const obj2 = { ...obj1, age: 20 };
```

### 템플릿 리터럴

```javascript
const name = "홍길동";
console.log(`안녕하세요 ${name}님`);
```

### 기본 매개변수

```javascript
function hello(name = "손님") {
    console.log(name);
}
```

- [ ] 구조 분해, 전개 연산자, 템플릿 리터럴을 사용할 수 있다

---

## 19. 모듈 ⭐⭐⭐⭐

React로 넘어가기 전에 알아두면 좋습니다.

### math.js

```javascript
export function add(a, b) {
    return a + b;
}
```

### main.js

```javascript
import { add } from "./math.js";

console.log(add(10, 20));
```

> React에서 `import React from "react";` 와 같은 원리입니다.

```html
<script type="module" src="main.js"></script>
```

- [ ] `export` / `import`의 기본 개념을 이해한다

---

## 20. jQuery와 비교

풀스택 수업에서 jQuery를 병행할 때 **관계를 정리**해 주세요.

### jQuery

```javascript
$("#btn").click(function () {
    $("#box").hide();
});
```

### 순수 JavaScript

```javascript
document.querySelector("#btn")
    .addEventListener("click", function () {
        document.querySelector("#box").style.display = "none";
    });
```

### 비교표

| | jQuery | JavaScript |
|---|--------|------------|
| 정체 | DOM 조작을 쉽게 해주는 **라이브러리** | 웹 브라우저 **프로그래밍 언어** |
| 선택 | `$("#id")`, `$(".class")` | `querySelector`, `querySelectorAll` |
| 이벤트 | `.click()`, `.on()` | `addEventListener` |
| 숨기기 | `.hide()` / `.show()` | `style.display = "none"` |
| AJAX | `$.ajax()`, `$.get()` | `fetch()` |

> jQuery를 많이 가르치기보다 **JavaScript DOM + 이벤트 + fetch**를 확실하게 가르치는 것을 권장합니다.  
> jQuery는 레거시·빠른 프로토타입·기존 프로젝트 이해용으로 보충합니다.

---

## ⭐ 우선순위 정리

### 🔥 반드시 할 것

| 영역 | 항목 |
|------|------|
| 기본 | `let`/`const`, 자료형, 연산자 (`===`), 조건·반복 |
| 데이터 | 배열 메서드 (`forEach`, `map`, `filter`), 객체 |
| 함수 | 함수 선언, 화살표 함수 |
| DOM | `querySelector`, `addEventListener`, `classList`, `innerHTML` |
| 폼 | `preventDefault`, `input.value` |
| 서버 연동 | JSON, `fetch` GET/POST, `async`/`await` |

### ⭐ 알면 좋은 것

- ES6+ (구조 분해, 전개 연산자, 템플릿 리터럴)
- 이벤트 객체 (`e.target`, `e.pageX`)
- `this` (일반 함수 vs 화살표 함수)
- Promise, 모듈 (`import`/`export`)

### 🟡 시간이 부족하면 간단히

- `reduce`, `splice` 등 고급 배열 메서드
- Promise 직접 생성 (`new Promise`)
- 복잡한 이벤트 위임
- 모듈 번들러 (Webpack, Vite) 내부 동작

---

## 🎯 전체 웹 개발 흐름

```
┌─────────┐     ┌──────────────┐     ┌─────────────┐
│  HTML   │     │  JavaScript  │     │ Spring Boot │
│ (구조)  │ ──→ │ DOM·이벤트   │ ──→ │ Controller  │
│ Form    │     │ fetch·JSON   │     │ Service     │
└─────────┘     └──────────────┘     │ Repository  │
                                     └──────┬──────┘
                                            ↓
                                       ┌─────────┐
                                       │   DB    │
                                       └─────────┘
```

### 학생 완료 기준

- [ ] 버튼 클릭으로 DOM 내용·스타일·클래스를 변경할 수 있다
- [ ] 폼 입력값을 읽고 유효성 검사를 할 수 있다
- [ ] `fetch`로 API에서 데이터를 가져와 화면에 표시할 수 있다
- [ ] `fetch` POST로 JSON 데이터를 서버에 전송할 수 있다

---

## 관련 교안·예제

| 자료 | 경로 |
|------|------|
| HTML/CSS 필수 | [../html/HTML-CSS-필수-체크리스트.md](../html/HTML-CSS-필수-체크리스트.md) |
| DOM·이벤트 예제 | [DOM-이벤트-예제코드/](./DOM-이벤트-예제코드/) |
| jQuery 커리큘럼 | [jQuery/README.md](./jQuery/README.md) |
| jQuery → Vanilla | [jquery_to_vanilla/jQuery-to-VanillaJS-완전정복.md](./jquery_to_vanilla/jQuery-to-VanillaJS-완전정복.md) |
| ES6 기초 | [../react/javascript-기초/01-ES6-문법/](../react/javascript-기초/01-ES6-문법/) |
| 배열 정리 | [자바스크립트-배열-비전공자-정리.md](./자바스크립트-배열-비전공자-정리.md) |
