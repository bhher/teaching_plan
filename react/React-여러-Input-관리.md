# React 여러 Input 관리 (객체 state)

**관련:** [react-5차시-State-useState.md](./react-5차시-State-useState.md) · [react-6차시-이벤트처리.md](./react-6차시-이벤트처리.md) · [react-10차시-폼처리.md](./react-10차시-폼처리.md)

**문제 / 정답 (3·4·5차시 포함, Input은 상품 폼 변형):**  
[React-3-4-5차시-Input관리-문제.md](./React-3-4-5차시-Input관리-문제.md) · [React-3-4-5차시-Input관리-정답.md](./React-3-4-5차시-Input관리-정답.md)

## 한 문장 요약

> **input의 `name`을 state 객체의 key로 쓰고, `value`를 그 값으로 저장하면  
> 여러 input을 하나의 state · 하나의 `onChange`로 관리할 수 있다.**

---

## 1. 전체 코드

```jsx
import { useState } from 'react'

function InputSamples() {
  // 여러 input 값을 하나의 객체 state로 관리
  const [inputs, setInputs] = useState({
    name: '',
    nickname: '',
  })

  // 구조 분해로 name, nickname 추출
  const { name, nickname } = inputs

  // input 값이 바뀔 때
  const onChange = (e) => {
    const { value, name } = e.target

    setInputs({
      ...inputs,     // 기존 state 복사
      [name]: value, // 해당 input만 변경
    })
  }

  // 초기화
  const onReset = () => {
    setInputs({
      name: '',
      nickname: '',
    })
  }

  return (
    <div>
      <input
        placeholder="이름"
        name="name"
        onChange={onChange}
        value={name}
      />

      <input
        placeholder="닉네임"
        name="nickname"
        onChange={onChange}
        value={nickname}
      />

      <button onClick={onReset}>초기화</button>

      <div>
        <b>값: </b>
        {name}({nickname})
      </div>
    </div>
  )
}

export default InputSamples
```

---

## 2. state 만들기

```jsx
const [inputs, setInputs] = useState({
  name: '',
  nickname: '',
})
```

`inputs` 객체 하나로 두 input 값을 관리합니다.

```text
inputs
 ├── name
 └── nickname
```

초기값:

```js
{
  name: '',
  nickname: ''
}
```

---

## 3. 구조 분해 할당

```jsx
const { name, nickname } = inputs
```

아래와 같습니다.

```jsx
const name = inputs.name
const nickname = inputs.nickname
```

그래서 JSX에서 `value={name}`, `value={nickname}` 처럼 씁니다.

---

## 4. input의 `name` 속성

```jsx
<input name="name" value={name} onChange={onChange} />
<input name="nickname" value={nickname} onChange={onChange} />
```

| input | `name` 속성 | 연결되는 state |
|-------|-------------|----------------|
| 이름 | `"name"` | `inputs.name` |
| 닉네임 | `"nickname"` | `inputs.nickname` |

`name`으로 **어느 필드를 바꿀지** 결정합니다.

---

## 5. onChange

```jsx
const onChange = (e) => {
  const { value, name } = e.target

  setInputs({
    ...inputs,
    [name]: value,
  })
}
```

입력할 때마다 실행됩니다.

---

## 6. `e.target`

`e.target` = **이벤트가 난 HTML 요소**(해당 input).

이름 칸에 `홍길동` 입력 시:

| 식 | 결과 |
|----|------|
| `e.target.name` | `"name"` |
| `e.target.value` | `"홍길동"` |

---

## 7. `[name]: value` (계산된 속성명)

`name` 변수 값에 따라 객체의 **key**가 정해집니다.

### 이름 input

```text
name = "name", value = "홍길동"
→  name: "홍길동"
```

### 닉네임 input

```text
name = "nickname", value = "길동이"
→  nickname: "길동이"
```

---

## 8. `...inputs` (스프레드)

```jsx
setInputs({
  ...inputs,
  [name]: value,
})
```

기존 객체를 **복사**한 뒤, 바뀐 필드만 덮어씁니다.

현재:

```js
{ name: '홍길동', nickname: '' }
```

닉네임만 바꾸면:

```js
{
  ...inputs,
  nickname: '길동이'
}
// → { name: '홍길동', nickname: '길동이' }
```

> React에서 객체 state는 **새 객체**로 바꿔야 리렌더링이 됩니다.  
> `inputs.name = value`처럼 직접 수정하지 않습니다.

(더 안전한 패턴)

```jsx
setInputs((prev) => ({
  ...prev,
  [name]: value,
}))
```

---

## 9. 초기화 `onReset`

```jsx
const onReset = () => {
  setInputs({
    name: '',
    nickname: '',
  })
}
```

```jsx
<button onClick={onReset}>초기화</button>
```

```text
홍길동 / 길동이  →  (클릭)  →  빈 값 / 빈 값
```

---

## 10. 전체 동작 흐름

```text
입력
  ↓ onChange
e.target → name, value 추출
  ↓
setInputs({ ...inputs, [name]: value })
  ↓
state 변경 → 화면 다시 렌더
```

초기화:

```text
버튼 클릭 → onReset → setInputs({ name:'', nickname:'' }) → 화면 비움
```

---

## ⭐ 핵심 코드 3개

### ① 객체 state

```jsx
const [inputs, setInputs] = useState({
  name: '',
  nickname: '',
})
```

### ② 여러 input → 함수 하나

```jsx
const { value, name } = e.target
setInputs({
  ...inputs,
  [name]: value,
})
```

### ③ 초기화

```jsx
const onReset = () => {
  setInputs({ name: '', nickname: '' })
}
```

---

## 체크리스트

- [ ] `useState`에 객체를 넣을 수 있다
- [ ] input에 `name` / `value` / `onChange`를 연결한다
- [ ] `e.target.name`, `e.target.value`를 이해한다
- [ ] `[name]: value` 계산된 속성명을 이해한다
- [ ] `...inputs`로 나머지 필드를 유지한다
- [ ] 초기화 버튼을 만들 수 있다

---

## 실습 과제 (확장)

1. `email` 필드를 추가하고 같은 `onChange`로 관리하기  
2. 초기화 시 세 칸 모두 비우기  
3. 이름이 비어 있으면 `"이름을 입력하세요"` 문구 보이기 (조건부 렌더링)
