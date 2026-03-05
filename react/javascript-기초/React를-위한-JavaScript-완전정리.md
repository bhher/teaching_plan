# 📚 React를 위한 JavaScript 완전 정리

## 📋 목차

1. [ES6+ 필수 문법](#es6-필수-문법)
2. [배열 메서드](#배열-메서드)
3. [객체와 구조 분해](#객체와-구조-분해)
4. [함수 고급](#함수-고급)
5. [비동기 처리](#비동기-처리)
6. [React에서 자주 사용하는 패턴](#react에서-자주-사용하는-패턴)

---

## 🎯 ES6+ 필수 문법

### 1. let과 const

```javascript
// let: 변수 재할당 가능
let name = "홍길동";
name = "김철수";  // OK

// const: 상수 (재할당 불가)
const PI = 3.14159;
// PI = 3.14;  // 에러!
```

**React에서:**
- `const`를 주로 사용 (변수 재할당 최소화)
- `let`은 반복문 등에서만 사용

---

### 2. 화살표 함수

```javascript
// 기존 함수
function add(a, b) {
    return a + b;
}

// 화살표 함수
const add = (a, b) => {
    return a + b;
};

// 한 줄일 때
const add = (a, b) => a + b;
```

**React에서:**
```jsx
const handleClick = () => {
    console.log("클릭됨");
};

<button onClick={handleClick}>클릭</button>
```

---

### 3. 템플릿 리터럴

```javascript
const name = "홍길동";
const age = 25;

// 기존 방식
const message = "이름: " + name + ", 나이: " + age;

// 템플릿 리터럴
const message = `이름: ${name}, 나이: ${age}`;
```

**React에서:**
```jsx
<div>안녕하세요, {name}님!</div>
```

---

### 4. 구조 분해 할당

```javascript
// 배열
const [first, second] = [1, 2];

// 객체
const { name, age } = { name: "홍길동", age: 25 };
```

**React에서:**
```jsx
function UserCard({ name, age, email }) {
    return <div>{name} ({age}세)</div>;
}
```

---

### 5. 스프레드 연산자

```javascript
// 배열
const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5];  // [1, 2, 3, 4, 5]

// 객체
const obj1 = { name: "홍길동" };
const obj2 = { ...obj1, age: 25 };  // { name: "홍길동", age: 25 }
```

**React에서:**
```jsx
const [items, setItems] = useState([]);
setItems([...items, newItem]);  // 배열에 추가
```

---

## 📊 배열 메서드

### 1. map() - 배열 변환

```javascript
const numbers = [1, 2, 3];
const doubled = numbers.map(num => num * 2);
// [2, 4, 6]
```

**React에서:**
```jsx
const items = [1, 2, 3];
const list = items.map(item => <li key={item}>{item}</li>);
```

---

### 2. filter() - 배열 필터링

```javascript
const numbers = [1, 2, 3, 4, 5];
const evens = numbers.filter(num => num % 2 === 0);
// [2, 4]
```

**React에서:**
```jsx
const [todos, setTodos] = useState([...]);
const activeTodos = todos.filter(todo => !todo.done);
```

---

### 3. find() - 요소 찾기

```javascript
const users = [
    { id: 1, name: "홍길동" },
    { id: 2, name: "김철수" }
];
const user = users.find(u => u.id === 2);
// { id: 2, name: "김철수" }
```

---

### 4. forEach() - 배열 순회

```javascript
const fruits = ["사과", "바나나"];
fruits.forEach(fruit => console.log(fruit));
```

---

### 5. reduce() - 배열 축약

```javascript
const numbers = [1, 2, 3, 4, 5];
const sum = numbers.reduce((acc, num) => acc + num, 0);
// 15
```

---

### 6. 배열 메서드 체이닝

```javascript
const result = numbers
    .filter(num => num % 2 === 0)  // 짝수만
    .map(num => num * 2)            // 2배
    .filter(num => num > 10);       // 10보다 큰 수만
```

---

## 🎯 객체와 구조 분해

### 1. 객체 기본

```javascript
const person = {
    name: "홍길동",
    age: 25,
    city: "서울"
};

console.log(person.name);      // "홍길동"
console.log(person["age"]);    // 25
```

---

### 2. 객체 구조 분해

```javascript
const person = { name: "홍길동", age: 25, city: "서울" };

// 기본 구조 분해
const { name, age } = person;

// 기본값 설정
const { name, age = 0 } = person;

// 변수명 변경
const { name: userName, age: userAge } = person;
```

**React에서:**
```jsx
function Component({ title, content, author = "익명" }) {
    return <div>{title} - {author}</div>;
}
```

---

### 3. 중첩 객체 구조 분해

```javascript
const user = {
    name: "홍길동",
    address: {
        city: "서울",
        district: "강남구"
    }
};

const { name, address: { city } } = user;
```

---

### 4. 객체 스프레드

```javascript
const obj1 = { a: 1, b: 2 };
const obj2 = { ...obj1, c: 3 };      // { a: 1, b: 2, c: 3 }
const obj3 = { ...obj1, b: 3 };     // { a: 1, b: 3 } (덮어쓰기)
```

**React에서:**
```jsx
const [user, setUser] = useState({ name: "", age: 0 });
setUser({ ...user, name: "홍길동" });  // 기존 속성 유지하며 업데이트
```

---

## 🔧 함수 고급

### 1. 화살표 함수와 this

```javascript
// 일반 함수: this가 호출한 객체를 가리킴
const obj = {
    name: "객체",
    greet: function() {
        console.log(this.name);
    }
};

// 화살표 함수: this가 상위 스코프를 가리킴
const obj2 = {
    name: "객체",
    greet: () => {
        console.log(this.name);  // undefined
    }
};
```

---

### 2. 고차 함수

```javascript
function multiplyBy(n) {
    return function(x) {
        return x * n;
    };
}

const double = multiplyBy(2);
double(5);  // 10
```

---

### 3. 클로저

```javascript
function createCounter() {
    let count = 0;
    return function() {
        count++;
        return count;
    };
}

const counter = createCounter();
counter();  // 1
counter();  // 2
```

---

## ⚡ 비동기 처리

### 1. Promise

```javascript
const promise = new Promise((resolve, reject) => {
    setTimeout(() => {
        resolve("성공!");
    }, 1000);
});

promise
    .then(result => console.log(result))
    .catch(error => console.error(error));
```

---

### 2. async/await

```javascript
async function fetchData() {
    try {
        const response = await fetch('/api/data');
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}
```

**React에서:**
```jsx
useEffect(() => {
    async function loadData() {
        const data = await fetchData();
        setData(data);
    }
    loadData();
}, []);
```

---

### 3. Promise.all()

```javascript
const promises = [
    fetchUser(1),
    fetchUser(2),
    fetchUser(3)
];

Promise.all(promises)
    .then(users => {
        console.log("모든 사용자:", users);
    });
```

---

## 🎨 React에서 자주 사용하는 패턴

### 1. 상태 업데이트 패턴

```javascript
// 배열에 추가
setItems([...items, newItem]);

// 배열에서 제거
setItems(items.filter(item => item.id !== id));

// 배열 업데이트
setItems(items.map(item => 
    item.id === id ? { ...item, done: true } : item
));

// 객체 업데이트
setUser({ ...user, name: "홍길동" });
```

---

### 2. 조건부 렌더링

```javascript
// 삼항 연산자
{isLoading ? <Loading /> : <Content />}

// 논리 연산자
{user && <UserCard user={user} />}
{items.length > 0 && <ItemList items={items} />}
```

---

### 3. 이벤트 핸들러

```javascript
// 기본
const handleClick = () => {
    console.log("클릭됨");
};

// 매개변수 전달
const handleClick = (id) => {
    return (e) => {
        console.log("ID:", id);
    };
};

// 또는
const handleClick = (id) => (e) => {
    console.log("ID:", id);
};
```

---

### 4. 리스트 렌더링

```jsx
const items = [
    { id: 1, name: "사과" },
    { id: 2, name: "바나나" }
];

const list = items.map(item => (
    <li key={item.id}>{item.name}</li>
));
```

---

## ✅ 체크리스트

React를 배우기 전에 다음을 확인하세요:

- [ ] let과 const 이해
- [ ] 화살표 함수 사용 가능
- [ ] 템플릿 리터럴 사용 가능
- [ ] 구조 분해 할당 사용 가능
- [ ] 스프레드 연산자 사용 가능
- [ ] map, filter, find 사용 가능
- [ ] 객체 구조 분해 사용 가능
- [ ] Promise와 async/await 이해
- [ ] 배열 메서드 체이닝 가능

---

**이 개념들을 마스터하면 React 학습이 훨씬 쉬워집니다! 💪**
