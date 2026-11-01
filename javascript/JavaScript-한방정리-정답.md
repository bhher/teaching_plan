# JavaScript 한방 정리 — 정답

문제: [JavaScript-한방정리-문제.md](./JavaScript-한방정리-문제.md)  
교안: [JavaScript-한방정리.md](./JavaScript-한방정리.md)

---

# Part 1

## 1번
② `const`는 재할당 불가 (`let`은 가능)

## 2번
② `10 === "10"` → `false` (타입까지 비교)

## 3번
② `"string"` (소문자)

## 4번
② `` `안녕 ${name}` ``

## 5번 (예시)
`==`는 타입를 변환해 비교하고, `===`는 값과 타입을 모두 비교한다.

## 6번

```javascript
const score = 85;
const result = score >= 60 ? "합격" : "불합격";
console.log(result);
```

---

# Part 2

## 7번
②

## 8번

```javascript
let sum = 0;
for (let i = 1; i <= 100; i++) {
  sum += i;
}
console.log(sum); // 5050
```

## 9번

```javascript
const nums = [1, 2, 3, 4, 5, 6];
const evens = nums.filter((n) => n % 2 === 0);
console.log(evens); // [2, 4, 6]
```

## 10번

```javascript
const nums = [1, 2, 3];
const doubled = nums.map((n) => n * 2);
console.log(doubled); // [2, 4, 6]
```

## 11번
③ `user->name` 은 JavaScript 문법이 아님

## 12번

```javascript
const add = (a, b) => a + b;
console.log(add(3, 4)); // 7
```

## 13번

```javascript
const users = [
  { id: 1, name: "홍길동" },
  { id: 2, name: "김철수" },
];
const found = users.find((u) => u.id === 2);
console.log(found); // { id: 2, name: "김철수" }
```

---

# Part 3

## 14번
② id가 title인 첫 요소

## 15번
② `textContent`  
(`innerHTML`은 태그 해석 — XSS 주의)

## 16번
① `el.classList.add("on")`  
(②는 jQuery)

## 17번
② `addEventListener`

## 18번
② `e.preventDefault()`

## 19번

```javascript
document.querySelector("#btn").addEventListener("click", function () {
  document.querySelector("#msg").textContent = "클릭됨";
});
```

## 20번

```javascript
const name = document.querySelector("#name").value.trim();
if (name === "") {
  console.log("이름을 입력하세요");
} else {
  console.log(name);
}
```

## 21번 (예시)
이벤트가 발생한 요소(이벤트 대상, 리스너를 단 그 요소)

---

# Part 4

## 22번
② 객체 → JSON 문자열

## 23번
② JSON 문자열 → 객체

## 24번

```javascript
async function loadUsers() {
  const res = await fetch("/api/users");
  const data = await res.json();
  console.log(data);
}
loadUsers();
```

## 25번
② `JSON.stringify` + Content-Type

```javascript
await fetch("/api/users", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify(user),
});
```

## 26번
② `const copy = [...arr]`

---

# Part 5

## 27번
③

## 28번

```javascript
document.querySelectorAll(".tab-btn").forEach(function (btn) {
  btn.addEventListener("click", function () {
    document.querySelectorAll(".tab-btn").forEach(function (b) {
      b.classList.remove("on");
    });
    this.classList.add("on");
  });
});
```

## 29번

```javascript
document.querySelector("#add").addEventListener("click", function () {
  const input = document.querySelector("#todo");
  const text = input.value.trim();
  if (text === "") return;

  const li = document.createElement("li");
  li.textContent = text;
  document.querySelector("#list").append(li);
  input.value = "";
});
```

## 30번 (예시)
JSON, fetch(또는 Ajax), Spring Boot API(HTTP)

---

# 강사 채점 팁

| 문항 | 포인트 |
|------|--------|
| 2 | `===` 단골 |
| 15~16 | textContent vs innerHTML, classList vs jQuery |
| 18 | preventDefault |
| 24~25 | fetch GET/POST |
| 28~29 | 실제 동작 여부로 부분 점수 |

코드 문항은 **실행되는 골격**이면 70%까지 부여 가능.
