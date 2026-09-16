# JavaScript 필수 체크리스트

풀스택(HTML/CSS → **JavaScript** → jQuery → fetch → Spring Boot) 기준.  
학생이 **직접 할 수 있어야 하는** 항목입니다.

**통합 교안:** [JavaScript-jQuery-한방정리.md](./JavaScript-jQuery-한방정리.md)  
**상세:** [JavaScript-한방정리.md](./JavaScript-한방정리.md)  
**문제 / 정답:** [JavaScript-한방정리-문제.md](./JavaScript-한방정리-문제.md) · [JavaScript-한방정리-정답.md](./JavaScript-한방정리-정답.md)

---

## 목차

1. [기본 문법](#-1단계--기본-문법-)
2. [조건 · 반복](#-2단계--조건--반복-)
3. [배열 · 객체](#-3단계--배열--객체-)
4. [함수](#-4단계--함수-)
5. [DOM](#-5단계--dom-)
6. [이벤트](#-6단계--이벤트-)
7. [폼](#-7단계--폼-)
8. [JSON · fetch](#-8단계--json--fetch-)
9. [ES6+](#-9단계--es6-)
10. [종합 실습](#-10단계--종합-실습-)
11. [우선순위](#우선순위-정리)

---

## 🟢 1단계 — 기본 문법 ⭐⭐⭐

- [ ] `let` / `const` 차이를 설명한다
- [ ] 문자열·숫자·불리언·배열·객체를 선언한다
- [ ] `typeof` 결과를 읽는다
- [ ] 비교는 `===` 을 쓴다 (`==` 와 구분)
- [ ] 템플릿 리터럴 `` `안녕 ${name}` `` 을 쓴다

---

## 🟢 2단계 — 조건 · 반복 ⭐⭐⭐

- [ ] `if` / `else if` / `else` 작성
- [ ] `for` / `while` 작성
- [ ] `for...of` 또는 `forEach` 로 배열 순회
- [ ] `break` / `continue` 구분

**미니**

- [ ] 점수 → 학점
- [ ] 1~100 합

---

## 🟢 3단계 — 배열 · 객체 ⭐⭐⭐

- [ ] 배열 `push` / 인덱스 / `length`
- [ ] `map` / `filter` / `find` 중 2개 이상 사용
- [ ] 객체 속성 읽기·쓰기 (`obj.name`, `obj["age"]`)
- [ ] 배열 안에 객체 구조를 이해한다

---

## 🟢 4단계 — 함수 ⭐⭐⭐

- [ ] `function` 선언·호출·`return`
- [ ] 화살표 함수 `(a, b) => a + b`
- [ ] 매개변수를 받아 DOM/계산에 사용

---

## 🟡 5단계 — DOM ⭐⭐⭐

```javascript
document.querySelector("#title");
```

- [ ] `querySelector` / `querySelectorAll` 사용
- [ ] `textContent` / `innerHTML` 구분
- [ ] `classList.add` / `remove` / `toggle`
- [ ] `style` 또는 클래스로 보이기/숨기기
- [ ] 요소 생성 `createElement` + `append` (권장)

---

## 🟡 6단계 — 이벤트 ⭐⭐⭐

- [ ] `addEventListener("click", ...)` 작성
- [ ] `e.preventDefault()` 사용
- [ ] `e.target` 을 안다
- [ ] 일반 함수에서 `this` ≈ 이벤트 대상임을 안다

---

## 🟡 7단계 — 폼 ⭐⭐⭐

- [ ] `input.value` 로 값 읽기
- [ ] submit 시 `preventDefault` 후 검사
- [ ] 빈 값 / 길이 등 간단한 유효성 검사

---

## 🟠 8단계 — JSON · fetch ⭐⭐⭐

- [ ] `JSON.stringify` / `JSON.parse` 역할 설명
- [ ] `fetch` GET 후 `.json()` 으로 데이터 받기
- [ ] `async` / `await` 로 작성
- [ ] POST로 JSON body 전송 (한 번이라도)

---

## 🟠 9단계 — ES6+ ⭐⭐

- [ ] 구조 분해 `const { name } = user`
- [ ] 전개 `[...arr]`
- [ ] 모듈 `import` / `export` 개념 (알면 가점)

---

## 🔴 10단계 — 종합 실습 ⭐⭐⭐

아래 중 **2개 이상** 혼자 완성:

- [ ] 버튼 클릭 → 문구/색/클래스 변경
- [ ] 탭 메뉴 (버튼·패널 class `on`)
- [ ] 할 일 목록 (추가·삭제)
- [ ] 폼 유효성 + 결과 출력
- [ ] fetch로 목록 가져와 화면에 출력

---

## 우선순위 정리

| 우선 | 내용 |
|------|------|
| 반드시 | 변수, ===, 배열·객체, 함수, querySelector, 이벤트, value, fetch |
| 든든히 | map/filter, classList, async/await, POST |
| 다음에 | 모듈 번들러, 복잡한 이벤트 위임 |

### 자가 진단 (8문항)

1. [ ] `let`/`const` + `===`  
2. [ ] 배열 forEach 또는 map  
3. [ ] 함수 작성·호출  
4. [ ] querySelector로 텍스트 변경  
5. [ ] 클릭 이벤트  
6. [ ] input value 읽기  
7. [ ] classList 토글  
8. [ ] fetch GET  

**6개 이상 → jQuery·Spring 연동 가능**  
**4~5 → DOM·이벤트 집중**  
**3 이하 → 1~4단계 재학습**
