# 5차시. State (useState)

## 학습 목표
- State의 개념을 이해할 수 있다
- useState Hook을 사용할 수 있다
- 상태 변경 시 자동 렌더링을 이해할 수 있다
- useState를 활용하여 버튼 클릭 카운트와 토글 버튼을 만들 수 있다

---

## 1. State란?

### 1.1 State의 정의

**State (상태)**는 컴포넌트 내부에서 변경 가능한 데이터입니다.

**특징:**
- ✅ 컴포넌트 내부에서 관리
- ✅ 변경 가능 (Props와 다름)
- ✅ 상태가 변경되면 자동으로 화면이 다시 그려짐 (리렌더링)

### 1.2 Props vs State

| 항목 | Props | State |
|------|-------|-------|
| 정의 | 부모에서 전달받은 데이터 | 컴포넌트 내부 데이터 |
| 변경 가능 | ❌ 읽기 전용 | ✅ 변경 가능 |
| 전달 방향 | 부모 → 자식 | 컴포넌트 내부 |
| 사용 목적 | 데이터 전달 | 동적 데이터 관리 |

**예시:**
```jsx
// Props: 부모에서 받은 데이터 (변경 불가)
function Welcome({ name }) {
  return <h1>안녕하세요, {name}님!</h1>;
}

// State: 컴포넌트 내부 데이터 (변경 가능)
function Counter() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}
```

### 1.3 State가 필요한 이유

**State 없이 (문제):**
```jsx
function Counter() {
  let count = 0;
  
  const handleClick = () => {
    count = count + 1;
    console.log(count); // 콘솔에는 증가하지만 화면은 안 바뀜
  };
  
  return <button onClick={handleClick}>{count}</button>;
}
```

**문제점:**
- ❌ 화면이 업데이트되지 않음
- ❌ React가 변경사항을 감지하지 못함

**State 사용 (해결):**
```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  const handleClick = () => {
    setCount(count + 1); // 화면이 자동으로 업데이트됨!
  };
  
  return <button onClick={handleClick}>{count}</button>;
}
```

**장점:**
- ✅ 상태 변경 시 자동으로 화면 업데이트
- ✅ React가 변경사항을 추적

---

## 2. useState Hook

### 2.1 Hook이란?

**Hook**은 React 함수형 컴포넌트에서 상태와 생명주기 기능을 사용할 수 있게 해주는 함수입니다.

**Hook 규칙:**
- ✅ 함수형 컴포넌트에서만 사용
- ✅ 최상위 레벨에서만 호출 (조건문, 반복문 안에서 사용 불가)
- ✅ `use`로 시작하는 이름

**주요 Hook:**
- `useState`: 상태 관리
- `useEffect`: 생명주기 관리
- `useContext`: Context 사용

### 2.2 useState 기본 문법

**기본 구조:**
```jsx
import { useState } from 'react';

function 컴포넌트() {
  const [상태변수, 상태변경함수] = useState(초기값);
  
  return (
    <div>
      {상태변수}
      <button onClick={() => 상태변경함수(새값)}>변경</button>
    </div>
  );
}
```

**예시:**
```jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <p>카운트: {count}</p>
      <button onClick={() => setCount(count + 1)}>증가</button>
    </div>
  );
}
```

**구성 요소:**
- `count`: 현재 상태 값
- `setCount`: 상태를 변경하는 함수
- `useState(0)`: 초기값 0으로 설정

### 2.3 useState 동작 원리

**초기 렌더링:**
```jsx
const [count, setCount] = useState(0);
// count = 0
```

**상태 변경:**
```jsx
setCount(1);
// count = 1, 컴포넌트가 다시 렌더링됨
```

**다시 상태 변경:**
```jsx
setCount(2);
// count = 2, 컴포넌트가 다시 렌더링됨
```

**중요:** `setCount`를 호출하면 컴포넌트가 자동으로 다시 렌더링됩니다!

---

## 3. useState 사용 예제

### 예제 1: 숫자 카운터

```jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  
  const increment = () => {
    setCount(count + 1);
  };
  
  const decrement = () => {
    setCount(count - 1);
  };
  
  const reset = () => {
    setCount(0);
  };
  
  return (
    <div>
      <h2>카운터: {count}</h2>
      <button onClick={increment}>+1</button>
      <button onClick={decrement}>-1</button>
      <button onClick={reset}>리셋</button>
    </div>
  );
}
```

### 예제 2: 문자열 상태

```jsx
import { useState } from 'react';

function Greeting() {
  const [name, setName] = useState('');
  
  return (
    <div>
      <input 
        type="text" 
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="이름을 입력하세요"
      />
      <p>안녕하세요, {name || '게스트'}님!</p>
    </div>
  );
}
```

### 예제 3: 불린 상태

```jsx
import { useState } from 'react';

function Toggle() {
  const [isOn, setIsOn] = useState(false);
  
  return (
    <div>
      <p>상태: {isOn ? '켜짐' : '꺼짐'}</p>
      <button onClick={() => setIsOn(!isOn)}>
        {isOn ? '끄기' : '켜기'}
      </button>
    </div>
  );
}
```

### 예제 4: 배열 상태

```jsx
import { useState } from 'react';

function TodoList() {
  const [todos, setTodos] = useState(['할일 1', '할일 2']);
  
  const addTodo = () => {
    setTodos([...todos, '새 할일']);
  };
  
  return (
    <div>
      <ul>
        {todos.map((todo, index) => (
          <li key={index}>{todo}</li>
        ))}
      </ul>
      <button onClick={addTodo}>추가</button>
    </div>
  );
}
```

---

## 4. 상태 변경 방법

### 4.1 직접 값 설정

```jsx
const [count, setCount] = useState(0);

// 직접 값 설정
setCount(10);  // count = 10
```

### 4.2 이전 값 기반으로 변경

**함수형 업데이트:**
```jsx
const [count, setCount] = useState(0);

// 이전 값을 기반으로 변경
setCount(prevCount => prevCount + 1);
```

**왜 함수형 업데이트를 사용할까?**

**문제 상황:**
```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  const handleClick = () => {
    setCount(count + 1);
    setCount(count + 1);  // 같은 값이 두 번 설정됨!
  };
  
  return <button onClick={handleClick}>{count}</button>;
}
// 클릭 시 1만 증가 (예상: 2 증가)
```

**해결: 함수형 업데이트**
```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  const handleClick = () => {
    setCount(prev => prev + 1);
    setCount(prev => prev + 1);  // 이전 값을 기반으로 계산
  };
  
  return <button onClick={handleClick}>{count}</button>;
}
// 클릭 시 2 증가 ✅
```

**함수형 업데이트 사용 시기:**
- ✅ 여러 번 연속으로 상태 변경할 때
- ✅ 이전 상태 값을 기반으로 계산할 때
- ✅ 비동기 작업 후 상태 변경할 때

---

## 5. 실습: 버튼 클릭 카운트

### 실습 1: 기본 카운터

**요구사항:**
- 증가 버튼 클릭 시 카운트 증가
- 감소 버튼 클릭 시 카운트 감소
- 리셋 버튼으로 0으로 초기화

**코드:**
```jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  
  return (
    <div style={{ 
      textAlign: 'center', 
      padding: '2rem',
      fontFamily: 'Arial, sans-serif'
    }}>
      <h1 style={{ fontSize: '3rem', margin: '1rem 0' }}>
        {count}
      </h1>
      
      <div style={{ display: 'flex', gap: '1rem', justifyContent: 'center' }}>
        <button 
          onClick={() => setCount(count - 1)}
          style={{
            padding: '10px 20px',
            fontSize: '1.2rem',
            backgroundColor: '#ff4444',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          -1
        </button>
        
        <button 
          onClick={() => setCount(0)}
          style={{
            padding: '10px 20px',
            fontSize: '1.2rem',
            backgroundColor: '#666',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          리셋
        </button>
        
        <button 
          onClick={() => setCount(count + 1)}
          style={{
            padding: '10px 20px',
            fontSize: '1.2rem',
            backgroundColor: '#44ff44',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          +1
        </button>
      </div>
    </div>
  );
}

export default Counter;
```

### 실습 2: 함수형 업데이트 사용

**요구사항:**
- +5 버튼 추가
- 함수형 업데이트 사용

**코드:**
```jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  
  const increment = () => {
    setCount(prev => prev + 1);
  };
  
  const incrementByFive = () => {
    setCount(prev => prev + 5);
  };
  
  const decrement = () => {
    setCount(prev => prev - 1);
  };
  
  const reset = () => {
    setCount(0);
  };
  
  return (
    <div>
      <h1>카운트: {count}</h1>
      <button onClick={increment}>+1</button>
      <button onClick={incrementByFive}>+5</button>
      <button onClick={decrement}>-1</button>
      <button onClick={reset}>리셋</button>
    </div>
  );
}
```

### 실습 3: 최소/최대값 제한

**요구사항:**
- 카운트가 0 미만으로 내려가지 않도록
- 카운트가 10을 초과하지 않도록

**코드:**
```jsx
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  
  const increment = () => {
    if (count < 10) {
      setCount(count + 1);
    }
  };
  
  const decrement = () => {
    if (count > 0) {
      setCount(count - 1);
    }
  };
  
  return (
    <div>
      <h1>카운트: {count}</h1>
      <p>범위: 0 ~ 10</p>
      <button onClick={increment} disabled={count >= 10}>
        증가
      </button>
      <button onClick={decrement} disabled={count <= 0}>
        감소
      </button>
    </div>
  );
}
```

---

## 6. 실습: 토글 버튼

### 실습 1: 기본 토글

**요구사항:**
- 버튼 클릭 시 켜짐/꺼짐 상태 변경
- 상태에 따라 다른 텍스트와 색상 표시

**코드:**
```jsx
import { useState } from 'react';

function Toggle() {
  const [isOn, setIsOn] = useState(false);
  
  const toggle = () => {
    setIsOn(!isOn);
  };
  
  return (
    <div style={{ textAlign: 'center', padding: '2rem' }}>
      <div style={{
        fontSize: '2rem',
        marginBottom: '1rem',
        color: isOn ? '#44ff44' : '#ff4444'
      }}>
        {isOn ? '🟢 켜짐' : '🔴 꺼짐'}
      </div>
      
      <button 
        onClick={toggle}
        style={{
          padding: '15px 30px',
          fontSize: '1.2rem',
          backgroundColor: isOn ? '#44ff44' : '#ff4444',
          color: 'white',
          border: 'none',
          borderRadius: '8px',
          cursor: 'pointer',
          transition: 'all 0.3s'
        }}
      >
        {isOn ? '끄기' : '켜기'}
      </button>
    </div>
  );
}

export default Toggle;
```

### 실습 2: 다중 토글

**요구사항:**
- 여러 개의 독립적인 토글 버튼

**코드:**
```jsx
import { useState } from 'react';

function MultiToggle() {
  const [lights, setLights] = useState({
    livingRoom: false,
    bedroom: false,
    kitchen: false
  });
  
  const toggleLight = (room) => {
    setLights(prev => ({
      ...prev,
      [room]: !prev[room]
    }));
  };
  
  return (
    <div style={{ padding: '2rem' }}>
      <h2>조명 제어</h2>
      
      <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
        <div>
          <span>거실: </span>
          <button onClick={() => toggleLight('livingRoom')}>
            {lights.livingRoom ? '🟢 켜짐' : '🔴 꺼짐'}
          </button>
        </div>
        
        <div>
          <span>침실: </span>
          <button onClick={() => toggleLight('bedroom')}>
            {lights.bedroom ? '🟢 켜짐' : '🔴 꺼짐'}
          </button>
        </div>
        
        <div>
          <span>부엌: </span>
          <button onClick={() => toggleLight('kitchen')}>
            {lights.kitchen ? '🟢 켜짐' : '🔴 꺼짐'}
          </button>
        </div>
      </div>
    </div>
  );
}

export default MultiToggle;
```

### 실습 3: 체크박스 토글

**요구사항:**
- 체크박스 형태의 토글
- 체크 상태에 따라 다른 스타일

**코드:**
```jsx
import { useState } from 'react';

function CheckboxToggle() {
  const [isChecked, setIsChecked] = useState(false);
  
  return (
    <div style={{ padding: '2rem' }}>
      <label style={{ 
        display: 'flex', 
        alignItems: 'center', 
        gap: '10px',
        cursor: 'pointer'
      }}>
        <input
          type="checkbox"
          checked={isChecked}
          onChange={(e) => setIsChecked(e.target.checked)}
          style={{ width: '20px', height: '20px' }}
        />
        <span style={{ fontSize: '1.2rem' }}>
          {isChecked ? '✅ 동의함' : '☐ 동의 안 함'}
        </span>
      </label>
    </div>
  );
}

export default CheckboxToggle;
```

---

## 7. 실습 과제

### 과제 1: 좋아요 버튼

**요구사항:**
- 좋아요 버튼 클릭 시 카운트 증가
- 하트 아이콘으로 표시
- 좋아요 상태에 따라 색상 변경

**예시:**
```jsx
<LikeButton />  // ❤️ 0
// 클릭 후
<LikeButton />  // ❤️ 1 (빨간색)
```

### 과제 2: 다크 모드 토글

**요구사항:**
- 다크 모드/라이트 모드 토글
- 모드에 따라 배경색과 텍스트 색상 변경

**예시:**
```jsx
// 라이트 모드: 흰 배경, 검은 텍스트
// 다크 모드: 검은 배경, 흰 텍스트
```

### 과제 3: 쇼핑 카트 카운터

**요구사항:**
- 상품 수량 증가/감소 버튼
- 최소 1개, 최대 10개 제한
- 총 가격 계산 (단가 × 수량)

---

## 8. 자주 발생하는 오류

### 오류 1: `useState is not defined`

**원인:** useState를 import하지 않음

**해결:**
```jsx
// ✅
import { useState } from 'react';
```

### 오류 2: `Cannot read property of undefined`

**원인:** 상태를 직접 수정하려고 함

**해결:**
```jsx
// ❌
const [user, setUser] = useState({ name: '홍길동' });
user.name = '김철수';  // 직접 수정 불가!

// ✅
setUser({ ...user, name: '김철수' });
```

### 오류 3: 상태가 업데이트되지 않음

**원인:** 같은 값을 설정하거나 조건문 문제

**해결:**
```jsx
// ❌
if (count < 10) {
  setCount(count + 1);
  setCount(count + 1);  // 같은 값
}

// ✅
setCount(prev => prev + 1);
```

---

## 9. useState 모범 사례

### 사례 1: 초기값 설정

**✅ 좋은 예:**
```jsx
const [count, setCount] = useState(0);
const [name, setName] = useState('');
const [isActive, setIsActive] = useState(false);
```

**❌ 나쁜 예:**
```jsx
const [count, setCount] = useState();  // undefined
```

### 사례 2: 상태 구조화

**✅ 좋은 예 (관련 상태 그룹화):**
```jsx
const [user, setUser] = useState({
  name: '',
  email: '',
  age: 0
});
```

**❌ 나쁜 예 (너무 많은 개별 상태):**
```jsx
const [name, setName] = useState('');
const [email, setEmail] = useState('');
const [age, setAge] = useState(0);
// ... 10개 이상
```

### 사례 3: 함수형 업데이트

**✅ 좋은 예:**
```jsx
setCount(prev => prev + 1);
```

**❌ 나쁜 예 (의존성 문제 가능):**
```jsx
setCount(count + 1);  // count가 최신이 아닐 수 있음
```

---

## 10. 다음 차시 예고

다음 차시에서는 **이벤트 처리**를 배웁니다:
- onClick, onChange 이벤트
- 이벤트 객체 사용
- 인풋 값 제어
- 로그인 폼 만들기

---

## 요약

### 핵심 개념

1. **State**: 컴포넌트 내부에서 변경 가능한 데이터
2. **useState**: 상태를 관리하는 Hook
3. **리렌더링**: 상태 변경 시 자동으로 화면 업데이트
4. **함수형 업데이트**: 이전 값을 기반으로 상태 변경

### 필수 문법

```jsx
// useState 사용
import { useState } from 'react';

const [상태, 상태변경함수] = useState(초기값);

// 상태 변경
상태변경함수(새값);
상태변경함수(prev => prev + 1);  // 함수형 업데이트
```

### 체크리스트

- [ ] State의 개념 이해
- [ ] useState Hook 사용법 이해
- [ ] 상태 변경 시 리렌더링 이해
- [ ] 버튼 클릭 카운트 만들기 완료
- [ ] 토글 버튼 만들기 완료
- [ ] 함수형 업데이트 이해

---

**다음 차시에서 만나요! 🚀**

