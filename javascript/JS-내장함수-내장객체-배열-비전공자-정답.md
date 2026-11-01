# 자바스크립트 내장 함수 · 내장 객체 · 배열 종합 정답 (비전공자)

문제: [JS-내장함수-내장객체-배열-비전공자-문제.md](./JS-내장함수-내장객체-배열-비전공자-문제.md)

---

# Part 1. 내장 함수 · 내장 객체 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 1 | ② | `prompt()` = 입력창 |
| 2 | ③ | 취소 → `false`, 확인 → `true` |
| 3 | ② | `Number("100")` → 숫자 100, `Number("100px")` → `NaN` (전체가 숫자여야 함) |
| 4 | ② | `parseInt`는 앞쪽 숫자만 읽음 → `100` |
| 5 | ② | `parseFloat()` = 실수 변환 |
| 6 | ② | `0`, `""` → false / `"Java"` → true |
| 7 | ② | `isNaN("Java")` → true = 숫자가 아님 |
| 8 | ③ | 쿼리 **값** 하나는 `encodeURIComponent` |
| 9 | ② | 밀리초 단위 → 3000 = 3초 |
| 10 | ② | `clearInterval(id)` |
| 11 | `eval` | 보안상 실무 비권장 |
| 12 | ② | floor=3, ceil=4, round=4 |
| 13 | ③ | 최댓값 30 |
| 14 | ② | 0 이상 1 **미만** |
| 15 | ② | `getMonth()`는 0=1월 → 8월은 `getMonth()+1` |
| 16 | ① | `indexOf("S")` → 4, `substring(0,4)` → `"Java"` |
| 17 | ② | 문자열은 불변이라 새 문자열 반환 |
| 18 | ② | `"100"` / `"string"` |
| 19 | `confirm` / `true` | 확인=true, 취소=false |
| 20 | ③ | `alert`, `prompt`, `confirm`은 브라우저 전용 |

---

# Part 2. 배열 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 21 | ② | 인덱스 1 = `"바나나"` |
| 22 | ② | 배열은 0번부터 |
| 23 | ② | 1번 칸을 `"포도"`로 변경 |
| 24 | ③ | push 후 길이 3 |
| 25 | ② | `pop()` = 끝에서 제거 + 반환 |
| 26 | ① | `shift()` = 앞에서 제거, `포도` 반환 |
| 27 | ② | `-1` = 없음 |
| 28 | ③ | 사과=true, 포도=false |
| 29 | ① | 1번 `"바나나"` 1개 삭제 |
| 30 | ② | `slice(시작, 끝)` — 끝 인덱스는 **포함 안 함** |
| 31 | ② | 기본 `sort()`는 문자열 비교 → `[10,2,30,5]` |
| 32 | ② | 숫자 오름차순: `(a,b)=>a-b` |
| 33 | ③ | `join("-")` → `"사과-바나나-오렌지"` |
| 34 | ② | 30 이상 → `[30,40,50]` |
| 35 | ② | 각 요소 ×10 → `[10,20,30]` |
| 36 | ② | `find` = 첫 번째 하나 |
| 37 | ② | 80+90+100 = 270 |
| 38 | ② | 점수 오름차순: 김철수 → 홍길동 → 이영희 |
| 39 | ③ | `slice`는 복사만, 원본 유지 |
| 40 | `push` / `pop` | 끝 추가 / 끝 삭제 |

---

# Part 3. 코드 작성 정답 (41~45)

## 41번. prompt + alert

```javascript
let name = prompt("이름을 입력하세요.");
alert("안녕하세요, " + name + "님!");
// 또는
alert(`안녕하세요, ${name}님!`);
```

---

## 42번. parseFloat + Math.round

```javascript
let num = parseFloat("3.14");
console.log(Math.round(num));   // 3
```

---

## 43번. splice + push + join

```javascript
let fruits = ["사과", "바나나", "오렌지"];

fruits.splice(fruits.indexOf("바나나"), 1);   // 바나나 삭제
// 또는 fruits.splice(1, 1);

fruits.push("딸기");

let result = fruits.join(", ");
console.log(result);   // "사과, 오렌지, 딸기"
```

---

## 44번. filter + map

```javascript
let nums = [15, 8, 42, 3, 27];

let result = nums
    .filter(n => n > 10)
    .map(n => n * 2);

console.log(result);   // [30, 84, 54]
```

---

## 45번. 객체 배열 — 90점 이상 이름

```javascript
let students = [
    { name: "홍길동", score: 90 },
    { name: "김철수", score: 80 },
    { name: "이영희", score: 95 },
    { name: "박민수", score: 70 }
];

let names = students
    .filter(s => s.score >= 90)
    .map(s => s.name);

console.log(names);   // ["홍길동", "이영희"]
```

**한 줄로**

```javascript
let names = students.filter(s => s.score >= 90).map(s => s.name);
```

---

## 참고 교안

- [자바스크립트-내장함수-내장객체-비전공자-정리.md](./자바스크립트-내장함수-내장객체-비전공자-정리.md)
- [자바스크립트-배열-비전공자-정리.md](./자바스크립트-배열-비전공자-정리.md)
