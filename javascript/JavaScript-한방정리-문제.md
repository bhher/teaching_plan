# JavaScript 한방 정리 — 문제

교안: [JavaScript-한방정리.md](./JavaScript-한방정리.md)  
체크리스트: [JavaScript-필수-체크리스트.md](./JavaScript-필수-체크리스트.md)  
통합: [JavaScript-jQuery-한방정리.md](./JavaScript-jQuery-한방정리.md)  
정답: [JavaScript-한방정리-정답.md](./JavaScript-한방정리-정답.md)

---

# Part 1. 기본 문법 · 연산자

## 1번

`let`과 `const`에 대한 설명으로 **올바른** 것은?

① 둘 다 재할당이 가능하다  
② `const`는 재할당이 불가하다  
③ `let`만 블록 스코프가 있다  
④ 수업에서는 `var`를 최우선으로 쓴다  

---

## 2번

다음 중 결과가 **`false`** 인 것은?

① `10 === 10`  
② `10 === "10"`  
③ `"a" === "a"`  
④ `true === true`  

---

## 3번

`typeof "Hello"` 의 결과는?

① `"String"`  
② `"string"`  
③ `"text"`  
④ `"object"`  

---

## 4번

템플릿 리터럴로 **올바른** 코드는? (`name` 변수 사용)

① `"안녕 " + name`  
② `` `안녕 ${name}` ``  
③ `'안녕 ${name}'`  
④ `"안녕 ${name}"` (일반 큰따옴표만)  

---

## 5번 (단답)

`==` 와 `===` 의 차이를 **한 줄**로 쓰시오.

---

## 6번 (코드)

점수 `score = 85` 일 때 60점 이상이면 `"합격"`, 아니면 `"불합격"`을  
**삼항 연산자**로 구해 `result`에 넣고 출력하시오.

---

# Part 2. 조건 · 반복 · 배열 · 객체 · 함수

## 7번

`break`와 `continue` 설명으로 **올바른** 것은?

① 둘 다 함수를 종료한다  
② `break`는 반복 종료, `continue`는 이번만 건너뛴다  
③ `continue`가 반복을 완전히 끝낸다  
④ 브라우저를 새로고침한다  

---

## 8번 (코드)

`for`로 1부터 100까지 합을 구해 출력하시오.

---

## 9번 (코드)

다음 배열에서 **짝수만** 골라 새 배열로 만드시오. (`filter` 사용)

```javascript
const nums = [1, 2, 3, 4, 5, 6];
```

---

## 10번 (코드)

`map`으로 각 숫자에 2를 곱한 새 배열을 만드시오.

```javascript
const nums = [1, 2, 3];
```

---

## 11번

객체에서 값을 읽는 방법으로 **틀린** 것은?

① `user.name`  
② `user["age"]`  
③ `user->name`  
④ `const { name } = user` (구조 분해)  

---

## 12번 (코드)

두 수를 더해 반환하는 함수를 **화살표 함수**로 작성하고  
`add(3, 4)` 결과를 출력하시오.

---

## 13번 (코드)

객체 배열에서 `id`가 `2`인 항목을 `find`로 찾으시오.

```javascript
const users = [
  { id: 1, name: "홍길동" },
  { id: 2, name: "김철수" },
];
```

---

# Part 3. DOM · 이벤트 · 폼

## 14번

`document.querySelector("#title")` 이 선택하는 것은?

① class가 title인 모든 요소  
② id가 title인 **첫 번째** 요소  
③ 모든 title 태그  
④ name이 title인 input  

---

## 15번

화면에 **태그 없이 텍스트만** 넣을 때 권장하는 속성은?

① `innerHTML`  
② `textContent`  
③ `outerHTML`  
④ `value`  

---

## 16번

클래스를 추가하는 **올바른** 코드는?

① `el.classList.add("on")`  
② `el.addClass("on")`  
③ `el.class = "on"`  
④ `el.css("on")`  

---

## 17번

클릭 이벤트를 등록하는 **올바른** 코드는?

① `btn.on("click", fn)`  
② `btn.addEventListener("click", fn)`  
③ `btn.click = fn`  
④ `btn.attach("click", fn)`  

---

## 18번

폼 제출 시 페이지 새로고침을 막는 코드는?

① `e.stop()`  
② `e.preventDefault()`  
③ `e.block()`  
④ `return false` 만 반드시 써야 한다 (유일하게)  

---

## 19번 (코드)

id가 `btn`인 버튼을 클릭하면 id `msg`인 요소의 텍스트를  
`"클릭됨"`으로 바꾸는 코드를 작성하시오.

---

## 20번 (코드)

id `name`인 input 값을 읽어 콘솔에 출력하시오.  
(빈 문자열이면 `"이름을 입력하세요"` 출력)

---

## 21번 (단답)

이벤트 리스너 **일반 함수** 안에서 `this`는 보통 무엇을 가리키는가?

---

# Part 4. JSON · fetch · ES6

## 22번

`JSON.stringify(obj)` 의 역할은?

① 문자열 → 객체  
② 객체 → JSON **문자열**  
③ HTML로 변환  
④ 서버 URL 생성  

---

## 23번

`JSON.parse(str)` 의 역할은?

① 객체 → 문자열  
② JSON 문자열 → **객체**  
③ 배열만 만든다  
④ DOM을 파싱한다  

---

## 24번 (코드)

`fetch`로 `"/api/users"` GET 요청 후 JSON을 받아  
콘솔에 출력하는 코드를 `async/await`로 작성하시오.

---

## 25번

`fetch` POST 시 body에 객체를 넣을 때 **올바른** 처리에 가까운 것은?

① `body: user` (객체 그대로)  
② `body: JSON.stringify(user)` + Content-Type JSON  
③ `body: JSON.parse(user)`  
④ `body`는 항상 생략  

---

## 26번

다음 중 ES6 **전개 연산자** 사용은?

① `const a = arr[0]`  
② `const copy = [...arr]`  
③ `arr.push(1)`  
④ `arr.length`  

---

# Part 5. 종합 · 비교

## 27번

JavaScript와 jQuery 설명으로 **올바른** 것은?

① jQuery는 프로그래밍 언어이다  
② JavaScript는 라이브러리이다  
③ jQuery는 JavaScript로 DOM을 쉽게 다루는 **라이브러리**이다  
④ fetch는 jQuery 전용이다  

---

## 28번 (코드 · 탭 골격)

버튼 `.tab-btn`을 클릭하면:

1. 모든 버튼에서 `on` 제거  
2. 클릭한 버튼에만 `on` 추가  

순수 JS로 작성하시오. (`querySelectorAll` + `classList`)

---

## 29번 (코드 · 할 일)

입력창 `#todo`와 버튼 `#add`가 있다.  
버튼 클릭 시 입력값을 `<ul id="list">`에 `<li>`로 추가하시오.  
(추가 후 input 비우기)

---

## 30번 (서술)

체크리스트 기준으로, 화면 동작을 만든 뒤 서버와 연동하려면  
무엇을 배우면 되는지 **키워드 3개**를 쓰시오.

---

# 채점 가이드 (강사용)

| 구분 | 문항 | 배점 예 |
|------|------|---------|
| 객관·단답 | 1~5, 7, 11, 14~18, 21~23, 25~27, 30 | 각 2~3점 |
| 코드 | 6, 8~10, 12~13, 19~20, 24, 28 | 각 4~6점 |
| 종합 | 29 | 10점 |
| **합계** | | **약 100점** |

**통과 권장:** 70점 + 19·24번 중 1개 이상 동작
