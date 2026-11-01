# React 3·4·5차시 + 여러 Input 관리 — 문제

**교안**
- [react-3차시-컴포넌트개념.md](./react-3차시-컴포넌트개념.md)
- [react-4차시-Props.md](./react-4차시-Props.md)
- [react-5차시-State-useState.md](./react-5차시-State-useState.md)
- [React-여러-Input-관리.md](./React-여러-Input-관리.md)

**정답:** [React-3-4-5차시-Input관리-정답.md](./React-3-4-5차시-Input관리-정답.md)

> Part D(여러 Input)는 교안의 이름/닉네임 예제와 **다른 주제**(상품 등록 폼)로 출제합니다.

---

# Part A. 3차시 — 컴포넌트

## 1번

컴포넌트에 대한 설명으로 **올바른** 것은?

① CSS 파일만 의미한다  
② UI를 재사용 가능한 조각으로 나눈 것이다  
③ 데이터베이스 테이블이다  
④ npm 패키지 이름이다  

---

## 2번

현재 React에서 **권장**하는 컴포넌트 작성 방식은?

① 클래스형만  
② **함수형** 컴포넌트  
③ jQuery만  
④ HTML만  

---

## 3번 (코드)

화면에 `환영합니다!` 를 출력하는 함수형 컴포넌트 `Welcome`을 작성하고  
`export default` 하시오. (파일명 예: `Welcome.jsx`)

---

## 4번 (코드)

`Header.jsx`에 `사이트 제목`을 `h1`으로 표시하는 컴포넌트를 만들고,  
`App.jsx`에서 import 하여 사용하시오.

---

## 5번 (단답)

같은 컴포넌트를 여러 번 쓰는 **장점**을 한 줄로 쓰시오. (재사용 관점)

---

# Part B. 4차시 — Props

## 6번

Props의 전달 방향으로 **올바른** 것은?

① 자식 → 부모  
② **부모 → 자식**  
③ 형제끼리만  
④ 서버 → DB만  

---

## 7번

Props의 특징으로 **올바른** 것은?

① 자식이 마음대로 수정한다  
② **읽기 전용**이다  
③ state와 완전히 같다  
④ CSS만 전달한다  

---

## 8번 (코드)

`Card` 컴포넌트가 `title`, `content` props를 받아 출력하도록 작성하시오.  
`App`에서 서로 다른 내용의 `Card`를 **2개** 사용하시오.

---

## 9번 (코드)

다음을 **비구조화 할당**으로 바꾸시오.

```jsx
function Hello(props) {
  return <div style={{ color: props.color }}>안녕하세요 {props.name}</div>
}
```

---

## 10번 (코드) — PostCard 변형

`ProductCard` 컴포넌트를 만드시오. (게시글이 아니라 **상품**)

| props | 의미 |
|-------|------|
| name | 상품명 |
| price | 가격 |
| seller | 판매자 |

`App`에서 상품 카드 **2개** 이상 출력하시오.

---

## 11번 (객관식)

`name`을 안 넘겼을 때 기본값 `"손님"`을 쓰는 **권장** 방법은?

① `Hello.defaultProps`만 가능  
② `function Hello({ name = '손님' })`  
③ `window.name = '손님'`  
④ props는 기본값 불가  

---

# Part C. 5차시 — useState

## 12번

State와 Props 비교로 **올바른** 것은?

① State는 읽기 전용, Props는 변경 가능  
② **State는 컴포넌트 내부에서 변경 가능**, Props는 읽기 전용  
③ 둘 다 부모만 변경한다  
④ 둘 다 화면을 안 바꾼다  

---

## 13번

`let count = 0`만 올리고 화면이 안 바뀌는 이유로 **적절한** 것은?

① JavaScript 오류  
② **state가 아니라서 리렌더링이 안 됨**  
③ props가 없어서  
④ JSX를 못 써서  

---

## 14번 (코드)

버튼을 누를 때마다 숫자가 1씩 증가하는 `Counter`를 `useState`로 작성하시오.  
초기값 `0`.

---

## 15번 (코드)

`좋아요` / `좋아요 취소` 문구가 바뀌는 토글 버튼을 `useState`(boolean)로 작성하시오.

---

## 16번 (코드)

`useState`로 `on`/`off` 전등 상태를 만들고,  
버튼 클릭 시 `"켜짐"` / `"꺼짐"` 텍스트가 바뀌게 하시오.

---

## 17번 (단답)

`const [count, setCount] = useState(0)` 에서  
첫 번째·두 번째 값이 각각 무엇인지 쓰시오.

---

# Part D. 여러 Input 관리 — **변형 문제** (상품 등록)

> 교안 예제(이름/닉네임)와 다르게  
> **`title` / `price` / `category`** 세 칸을 객체 state로 관리합니다.

## 18번 (객관식)

여러 input을 **하나의 객체 state**로 관리할 때 input에 꼭 맞춰야 할 속성은?

① `id`만  
② **`name`** (state의 key와 동일)  
③ `className`만  
④ `placeholder`만  

---

## 19번 (단답)

`setInputs({ ...inputs, [name]: value })` 에서  
`...inputs` 의 역할을 **한 줄**로 쓰시오.

---

## 20번 (코드) — ProductForm

아래 요구로 `ProductForm.jsx`를 완성하시오.

**요구사항**
1. state 초기값:
   ```js
   { title: '', price: '', category: '' }
   ```
2. input 3개  
   - 상품명 → `name="title"`  
   - 가격 → `name="price"`  
   - 분류 → `name="category"` (예: 전자제품 / 의류)  
3. **`onChange` 함수는 하나만** (교안과 같은 패턴)  
4. `value`는 state와 연결 (제어 컴포넌트)  
5. **초기화** 버튼 → 세 칸 모두 `''`  
6. 아래에 미리보기 출력:
   ```text
   [분류] 상품명 - 가격원
   ```
   예: `[전자제품] 키보드 - 45000원`

**힌트**

```jsx
const onChange = (e) => {
  const { name, value } = e.target
  setForm({
    ...form,
    [name]: value,
  })
}
```

---

## 21번 (코드) — 조금 더

`ProductForm`에 다음을 추가하시오.

1. 상품명이 비어 있으면 미리보기 대신  
   `"상품명을 입력하세요"` 표시  
2. 초기화 후 미리보기도 안 보이게(또는 안내 문구만)

---

## 22번 (코드) — App 연결

`App.jsx`에서 `ProductForm`을 불러와 화면에 보이게 하시오.  
제목으로 `상품 등록` `h1`을 위에 두시오.

---

## 23번 (서술)

이름/닉네임 예제와 상품 폼 예제의 **같은 점**을 한 줄로 쓰시오.  
(객체 state + `name` + 하나의 onChange)

---

## 24번 (객관식)

다음 중 **잘못된** 코드는?

① `setForm({ ...form, [name]: value })`  
② `form.title = value` 후 화면이 반드시 갱신된다  
③ `const { title, price, category } = form`  
④ `<input name="title" value={title} onChange={onChange} />`  

---

## 25번 (종합)

다음을 모두 포함한 미니 페이지를 만드시오.

1. `Header` 컴포넌트 — props로 `siteName` 받기 (3·4차시)  
2. `ProductForm` — 객체 state로 3개 input (여러 Input)  
3. `Counter` — 조회수처럼 `useState`로 +1 버튼 (5차시)  
4. `App`에서 위 컴포넌트들을 조립  

---

# 채점 가이드

| Part | 문항 | 배점 예 |
|------|------|---------|
| A 컴포넌트 | 1~5 | 15 |
| B Props | 6~11 | 20 |
| C State | 12~17 | 20 |
| D 여러 Input | 18~24 | 30 |
| 종합 | 25 | 15 |
| **합계** | | **100** |

**통과:** 70점 + Part D 20번 동작  
**Part D:** 이름/닉네임 그대로 제출 시 감점 — **상품 폼**이어야 함
