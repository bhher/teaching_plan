## 2차시 React 입문 교안 – `useState` 실습

### 0. 강의 목표

- **상태(state)**를 일상적인 비유로 이해한다.
- React의 `useState` 훅을 사용해 **카운터 앱**을 만든다.
- 버튼 클릭 시 **숫자 증가/감소** 기능을 구현한다.
- **잘못된 state 사용 예제**를 보고, 왜 문제인지 이해한다.
- **상태 초기값**을 바꾸면서 컴포넌트 동작을 관찰한다.

---

### 1. 상태(state)란 무엇인가? (비유 설명)

#### 1) 상태의 개념

- **상태(state)**: “지금 이 순간 컴포넌트가 기억하고 있는 값”
  - 화면에 보여지는 내용에 **직접적인 영향을 주는 데이터**
  - 값이 바뀌면, React가 알아서 **화면을 다시 그려줌(렌더링)**.

#### 2) 비유 1 – 카페 주문판
ㅠ
- 카페 카운터에 있는 **디지털 주문 번호판**을 떠올려 보자.
  - 손님이 올 때마다 번호가 1씩 증가.
  - 번호가 바뀌면, **화면에 보이는 숫자도 함께 바뀜**.
- 여기서:
  - “현재 주문 번호” = **상태(state)**
  - “번호를 올리는 버튼” = **상태를 변경하는 함수(setState)**
  - “화면에 보이는 LED 숫자” = **UI(컴포넌트의 렌더링 결과)**

#### 3) 비유 2 – 화이트보드

- 교실 앞 화이트보드에 **현재 인원 수**를 적어둔다고 생각해 보자.
  - 학생이 들어오면 `+1`, 나가면 `-1`
  - 항상 화이트보드에 적힌 숫자를 보고 “지금 인원”을 알 수 있다.
- 여기서:
  - 화이트보드의 숫자 = **state**
  - 지우고 다시 쓰는 동작 = **setState**

> 강의 멘트 예시  
> “React 컴포넌트도 자기만의 화이트보드를 하나 가지고 있다고 생각하시면 됩니다.  
> 그 화이트보드에 적힌 값이 **state**이고, 우리가 `setState`로 그 숫자를 지우고 다시 쓰는 거예요.”

---

### 2. `useState` 기본 문법

#### 2.1 import

```jsx
import { useState } from 'react';
```

#### 2.2 사용 형태

```jsx
const [value, setValue] = useState(초기값);
```

- `value`: 현재 상태 값
- `setValue`: 상태를 변경하는 함수
- `useState(초기값)`: 컴포넌트가 처음 렌더링될 때 **초기값**으로 상태를 만들고,  
  그 이후에는 React가 상태를 기억하고 있다가, `setValue`로 변경할 때마다 다시 렌더링한다.

예시:

```jsx
const [count, setCount] = useState(0);
```

---

### 3. 실습 – 기본 카운터 컴포넌트 만들기

#### 3.1 목표

- 버튼 2개(“증가”, “감소”)와 숫자를 화면에 표시하는 **카운터 컴포넌트**를 만든다.

#### 3.2 기본 코드 예시

```jsx
// Counter.jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0); // state 선언

  const handleIncrease = () => {
    setCount(count + 1); // 1 증가
  };

  const handleDecrease = () => {
    setCount(count - 1); // 1 감소
  };

  return (
    <div>
      <h2>카운터: {count}</h2>
      <button onClick={handleIncrease}>증가</button>
      <button onClick={handleDecrease}>감소</button>
    </div>
  );
}

export default Counter;
```

#### 3.3 `App.jsx`에서 사용

```jsx
// App.jsx
import Counter from './Counter.jsx';

function App() {
  return (
    <div>
      <h1>useState 카운터 실습</h1>
      <Counter />
    </div>
  );
}

export default App;
```

#### 3.4 동작 설명

- `useState(0)`:
  - `count`의 초기값은 `0`
  - 화면에는 **카운터: 0** 이 출력
- “증가” 버튼 클릭:
  - `setCount(count + 1)` 실행 → `count` 값이 1 증가
  - React가 컴포넌트를 **다시 렌더링** → 화면에 새로운 값 표시
- “감소” 버튼도 동일한 원리로 동작

> 강의 멘트 예시  
> “여기서 중요한 건, **우리가 DOM을 직접 수정하지 않는다**는 점입니다.  
> 그냥 `count`를 바꾸고, React에게 ‘다시 그려줘’라고 부탁하는 느낌으로 `setCount`를 호출하는 거예요.”

---

### 4. 잘못된 state 사용 예제들

실습 중에 자주 나오는 실수들을 미리 보여주고, **왜 문제인지** 설명해 줍니다.

#### 4.1 count를 직접 수정하는 경우 (❌)

```jsx
// ❌ 이렇게 하면 안 됨
function CounterBad() {
  const [count, setCount] = useState(0);

  const handleIncrease = () => {
    count = count + 1; // 직접 수정 (에러 + 동작 안 함)
  };

  return (
    <div>
      <h2>카운터: {count}</h2>
      <button onClick={handleIncrease}>증가</button>
    </div>
  );
}
```

**문제점**

- `count`는 `const`로 선언되어 있어서 **직접 재할당 불가능**
- 설령 `let`으로 바꿔도:
  - React는 `setCount`를 통해서만 “상태가 바뀌었다”는 것을 알 수 있음
  - `count`만 바꾸고 **`setCount`를 호출하지 않으면 화면이 안 바뀜**

**올바른 방식**

```jsx
const handleIncrease = () => {
  setCount(count + 1);
};
```

---

#### 4.2 배열/객체를 직접 변경하는 경우 (❌, 심화 개념)

```jsx
// ❌ 나쁜 예
const [user, setUser] = useState({ name: '홍길동', age: 20 });

const handleBirthday = () => {
  user.age = user.age + 1; // 객체를 직접 수정
  setUser(user);           // 같은 객체를 다시 넣음
};
```

**문제점**

- React는 **참조(주소)**가 바뀌었는지 보고 “상태가 변했다”고 판단하는 경우가 많음
- 같은 객체를 수정만 하고 다시 넣으면, **변경을 감지하지 못할 수 있음**

**올바른 방식 (얕은 복사)**

```jsx
const handleBirthday = () => {
  setUser({
    ...user,
    age: user.age + 1,
  });
};
```

> 2차시에서는 “상태는 **불변(immutable)**처럼 다룬다” 정도만 언급하고,  
> 상세한 immutability 개념은 이후 차시에서 다루어도 좋습니다.

---

#### 4.3 연속 업데이트 시 이전 state를 참조하지 않는 경우 (주의)

```jsx
// 연속으로 2번 증가 시키고 싶을 때
const handleDoubleIncrease = () => {
  setCount(count + 1);
  setCount(count + 1); // 기대: 2 증가, 실제: 1만 증가할 수 있음
};
```

**이유**

- `setCount`는 **비동기적으로 작동**할 수 있음
- 두 줄 모두 “지금의 count 값”을 기준으로 `+1`만 한 것

**올바른 방식: 함수형 업데이트**

```jsx
const handleDoubleIncrease = () => {
  setCount(prev => prev + 1);
  setCount(prev => prev + 1); // 결과적으로 2 증가
};
```

여기서 `prev`는 “이전 상태 값”을 React가 넘겨주는 것.

---

### 5. 실습 과제 – 카운터 앱 확장

#### 5.1 기본 과제 – 카운터 앱

**요구사항**

- 현재 숫자를 화면에 표시
- “증가” 버튼 → 숫자 1 증가
- “감소” 버튼 → 숫자 1 감소
- 숫자가 바뀔 때마다 화면이 업데이트

**힌트 코드**

```jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);

  const handleIncrease = () => {
    setCount(count + 1);
  };

  const handleDecrease = () => {
    setCount(count - 1);
  };

  return (
    <div>
      <h2>카운터: {count}</h2>
      <button onClick={handleIncrease}>증가</button>
      <button onClick={handleDecrease}>감소</button>
    </div>
  );
}

export default Counter;
```

> 과제 진행 시, 학생들에게 “버튼 이름, 스타일, 초기값 등을 자유롭게 바꿔보라”고 유도하면 좋습니다.

---

#### 5.2 과제 1 – 상태 초기값 변경 실습

**목표**

- `useState(0)`의 초기값을 **여러 가지 숫자로 바꿔보면서**  
  컴포넌트가 처음 렌더링될 때 화면이 어떻게 달라지는지 체험.

**실습 내용 예시**

1. 초기값을 `0` → `10` → `100` 등으로 바꿔보기

```jsx
const [count, setCount] = useState(10);
```

2. “초기 카운트 값을 props로 받아서 설정”해 보기 (조금 심화)

```jsx
// Counter.jsx
import { useState } from 'react';

function Counter({ initialValue }) {
  const [count, setCount] = useState(initialValue);

  // ... (증가/감소 함수는 동일)
}

export default Counter;
```

```jsx
// App.jsx
function App() {
  return (
    <div>
      <h1>초기값이 다른 카운터들</h1>
      <Counter initialValue={0} />
      <Counter initialValue={10} />
      <Counter initialValue={100} />
    </div>
  );
}
```

**지도 포인트**

- “초기값은 **컴포넌트가 처음 만들어질 때 한 번만** 사용된다”는 점 강조.
- 이후에는 `setCount`를 통해서만 값이 바뀐다.

---

#### 5.3 과제 2 – 리셋 버튼 추가

**요구사항**

- “리셋” 버튼을 하나 더 추가
- 리셋 버튼 클릭 시, **초기값으로 되돌리기**

**힌트**

```jsx
const [count, setCount] = useState(initialValue);

const handleReset = () => {
  setCount(initialValue);
};
```

학생들이 “초기값을 기억해 두는 방법”을 스스로 생각해 보게 한 뒤,  


