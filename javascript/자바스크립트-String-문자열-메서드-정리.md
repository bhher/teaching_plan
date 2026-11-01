# 자바스크립트 String(문자열) 메서드 정리 (비전공자)

JavaScript에서 **문자열(String)** 을 다룰 때 자주 사용하는 메서드를 초보자 기준으로 정리했습니다.

> 문자열 메서드는 **원본을 바꾸지 않고** 새 문자열을 반환합니다.  
> `"hello".toUpperCase()` 후에도 `"hello"`는 그대로입니다.

---

## 1. 문자열의 길이 — `length`

문자열에 **몇 개의 문자**가 들어 있는지 확인합니다.

```javascript
let str = "Hello";
console.log(str.length);  // 5
```

한글도 **글자 1개**로 계산합니다.

```javascript
let str = "안녕하세요";
console.log(str.length);  // 5
```

`length`는 **메서드가 아니라 속성(property)** 이므로 `()`를 붙이지 않습니다.

```javascript
str.length   // O
str.length() // X
```

---

## 2. 특정 위치의 문자 — `charAt()`

```javascript
let str = "Hello";

console.log(str.charAt(0));  // H
console.log(str.charAt(1));  // e
console.log(str.charAt(4));  // o
```

문자열 인덱스는 **0부터** 시작합니다.

```text
H  e  l  l  o
0  1  2  3  4
```

---

## 3. `[]`로 문자 가져오기

`charAt()` 대신 **배열처럼** 접근할 수 있습니다.

```javascript
let str = "Hello";

console.log(str[0]);  // H
console.log(str[1]);  // e
console.log(str[4]);  // o
```

초보자에게 자주 쓰는 방법입니다.

---

## 4. 문자열에서 찾기 — `indexOf()`

문자(또는 문자열)가 **몇 번째 위치**에 있는지 찾습니다.

```javascript
let str = "Hello";

console.log(str.indexOf("H"));  // 0
console.log(str.indexOf("e"));  // 1
console.log(str.indexOf("l"));  // 2  (첫 번째 l)
```

문자열도 검색할 수 있습니다.

```javascript
let str = "Hello JavaScript";
console.log(str.indexOf("JavaScript"));  // 6
```

**없으면 `-1`** 을 반환합니다.

```javascript
console.log(str.indexOf("Python"));  // -1
```

### 실무 예 — 이메일 `@` 확인

```javascript
let email = "abc@gmail.com";

if (email.indexOf("@") != -1) {
    console.log("이메일 형식입니다.");
}
```

---

## 5. 마지막 위치 — `lastIndexOf()`

같은 문자가 여러 개일 때 **마지막** 위치를 찾습니다.

```javascript
let str = "Hello";

console.log(str.indexOf("l"));      // 2
console.log(str.lastIndexOf("l"));  // 3
```

---

## 6. 포함 여부 — `includes()`

특정 문자열이 **들어 있는지** 확인합니다. 결과는 `true` / `false`.

```javascript
let str = "Hello JavaScript";

console.log(str.includes("JavaScript"));  // true
console.log(str.includes("Python"));      // false
```

### 조건문과 함께

```javascript
let email = "test@gmail.com";

if (email.includes("@")) {
    console.log("골뱅이가 있습니다.");
}
```

> `indexOf() !== -1` 대신 **`includes()`** 가 읽기 쉽습니다.

---

## 7. 문자열 자르기 — `substring()`

문자열 **일부**를 잘라냅니다.

```javascript
let str = "JavaScript";
console.log(str.substring(0, 4));  // Java
```

**끝 번호는 포함하지 않습니다.**

```text
J  a  v  a  S  c  r  i  p  t
0  1  2  3  4  5  6  7  8  9
↑           ↑
0           4

substring(0, 4)  →  0 ~ 3  →  "Java"
```

---

## 8. `slice()`

`substring()`과 비슷하게 자릅니다.

```javascript
let str = "JavaScript";

console.log(str.slice(0, 4));    // Java
console.log(str.slice(4, 10));   // Script
```

**음수 인덱스**를 쓸 수 있습니다. (뒤에서부터)

```javascript
console.log(str.slice(-6));  // Script
```

---

## 9. `substr()` (권장하지 않음)

```javascript
let str = "JavaScript";
console.log(str.substr(0, 4));   // Java
console.log(str.substr(4, 6));   // Script
```

- 1번째: 시작 위치  
- 2번째: **가져올 글자 개수**

현재는 **`slice()` / `substring()`** 사용을 권장합니다.

---

## 10. 대문자 — `toUpperCase()`

```javascript
let str = "hello";
console.log(str.toUpperCase());  // HELLO
```

---

## 11. 소문자 — `toLowerCase()`

```javascript
let str = "HELLO";
console.log(str.toLowerCase());  // hello
```

### 로그인 아이디 처리

```javascript
let id = "ABC";
id = id.toLowerCase();
console.log(id);  // abc
```

---

## 12. 앞뒤 공백 제거 — `trim()`

입력값 **앞뒤 공백**을 제거합니다.

```javascript
let str = "   Hello   ";
console.log(str.trim());  // "Hello"
```

회원가입 · 로그인에서 **매우 자주** 사용합니다.

```javascript
let name = document.querySelector("#name").value;
name = name.trim();
```

---

## 13. 앞쪽만 — `trimStart()`

```javascript
let str = "   Hello   ";
console.log(str.trimStart());  // "Hello   "
```

---

## 14. 뒤쪽만 — `trimEnd()`

```javascript
let str = "   Hello   ";
console.log(str.trimEnd());  // "   Hello"
```

---

## 15. 문자열 변경 — `replace()`

특정 문자열을 **다른 문자열로** 바꿉니다.

```javascript
let str = "Hello Java";
console.log(str.replace("Java", "JavaScript"));
// Hello JavaScript
```

**첫 번째만** 바뀝니다.

```javascript
let str = "apple apple apple";
console.log(str.replace("apple", "banana"));
// banana apple apple
```

---

## 16. 모두 변경 — `replaceAll()`

```javascript
let str = "apple apple apple";
console.log(str.replaceAll("apple", "banana"));
// banana banana banana
```

---

## 17. 문자열 나누기 — `split()` ⭐

문자열을 **배열**로 만듭니다. 매우 중요합니다.

```javascript
let str = "사과,바나나,오렌지";
let fruits = str.split(",");
console.log(fruits);
// ["사과", "바나나", "오렌지"]
```

```javascript
let str = "Java,HTML,CSS,JavaScript";
let arr = str.split(",");

console.log(arr[0]);  // Java
console.log(arr[1]);  // HTML
console.log(arr[2]);  // CSS
console.log(arr[3]);  // JavaScript
```

배열을 다시 문자열로 합칠 때는 **`join()`** (배열 메서드)을 씁니다.

```javascript
arr.join(", ");  // "Java, HTML, CSS, JavaScript"
```

---

## 18. 문자열 합치기 — `concat()`

```javascript
let str1 = "Hello";
let str2 = "JavaScript";
console.log(str1.concat(" ", str2));  // Hello JavaScript
```

실무에서는 **`+`** 또는 **템플릿 리터럴**을 더 많이 씁니다.

```javascript
let result = str1 + " " + str2;
let result2 = `${str1} ${str2}`;
```

---

## 19. 시작 문자열 확인 — `startsWith()`

```javascript
let url = "https://www.google.com";

console.log(url.startsWith("https"));  // true
console.log(url.startsWith("http"));   // true
console.log(url.startsWith("www"));    // false
```

---

## 20. 끝 문자열 확인 — `endsWith()`

```javascript
let file = "test.jpg";

console.log(file.endsWith(".jpg"));  // true
console.log(file.endsWith(".png"));  // false
```

파일 확장자 검사 등에 사용합니다.

---

## 21. 문자 반복 — `repeat()`

```javascript
let str = "Hi";
console.log(str.repeat(3));  // HiHiHi
```

---

## 22. 문자열 비교

```javascript
let a = "apple";
let b = "apple";
console.log(a === b);  // true
```

대소문자가 다르면 **다릅니다.**

```javascript
console.log("Java" === "java");  // false
```

대소문자 무시 비교:

```javascript
let a = "Java";
let b = "java";
console.log(a.toLowerCase() === b.toLowerCase());  // true
```

---

## 23. 자주 쓰는 메서드 한눈에 (표)

| 메서드 | 기능 | 예 |
|--------|------|-----|
| `length` | 문자열 길이 | `"Hello".length` |
| `charAt()` | 특정 위치 문자 | `"Hello".charAt(1)` |
| `indexOf()` | 위치 찾기 | `"Hello".indexOf("e")` |
| `lastIndexOf()` | 마지막 위치 | `"Hello".lastIndexOf("l")` |
| `includes()` | 포함 여부 | `"Hello".includes("ell")` |
| `slice()` | 자르기 | `"Hello".slice(1, 3)` |
| `substring()` | 자르기 | `"Hello".substring(1, 3)` |
| `toUpperCase()` | 대문자 | `"hello".toUpperCase()` |
| `toLowerCase()` | 소문자 | `"HELLO".toLowerCase()` |
| `trim()` | 앞뒤 공백 제거 | `" hi ".trim()` |
| `replace()` | 일부 변경 | `"abc".replace("a", "x")` |
| `replaceAll()` | 모두 변경 | `"aaa".replaceAll("a", "b")` |
| `split()` | 배열로 분리 | `"a,b,c".split(",")` |
| `concat()` | 연결 | `"A".concat("B")` |
| `startsWith()` | 시작 확인 | `"Hello".startsWith("He")` |
| `endsWith()` | 끝 확인 | `"test.jpg".endsWith(".jpg")` |
| `repeat()` | 반복 | `"*".repeat(5)` |

---

## 24. 비전공자 — 우선 익힐 8가지

수업에서는 아래 **8개**부터 익히면 충분합니다.

| 순서 | 메서드 | 한 줄 설명 |
|------|--------|------------|
| 1 | `length` | 길이 |
| 2 | `charAt()` / `str[0]` | 한 글자 |
| 3 | `indexOf()` | 위치 찾기 |
| 4 | `includes()` | 포함 여부 |
| 5 | `slice()` | 자르기 |
| 6 | `toUpperCase()` / `toLowerCase()` | 대·소문자 |
| 7 | `trim()` | 공백 제거 |
| 8 | `split()` | 배열로 나누기 |

특히 **`indexOf`, `includes`, `slice`, `split`, `trim`** 은  
HTML 입력폼, 회원가입, 로그인, 검색 기능에서 자주 씁니다.

---

## 25. 치트시트

```javascript
let s = "  Hello JavaScript  ";

s.length                    // 20 (공백 포함)
s.trim()                    // "Hello JavaScript"
s.includes("Java")          // true
s.indexOf("Java")           // 7
s.slice(0, 5)               // "Hello"
s.toUpperCase()             // "  HELLO JAVASCRIPT  "
s.toLowerCase()             // "  hello javascript  "
"a,b,c".split(",")          // ["a","b","c"]
"test.jpg".endsWith(".jpg") // true
```

### 한 줄 요약

> **찾기** `indexOf` / `includes` · **자르기** `slice` · **나누기** `split` · **정리** `trim` · **대소문자** `toUpper/LowerCase`

---

## 관련 자료

- [4장_데이터_타입.md](./4장_데이터_타입.md) — String 타입
- [자바스크립트-내장함수-내장객체-비전공자-정리.md](./자바스크립트-내장함수-내장객체-비전공자-정리.md)
- [9장_배열.md](./9장_배열.md) — `split()` 결과 배열 처리
