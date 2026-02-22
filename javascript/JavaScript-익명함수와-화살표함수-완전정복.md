# JavaScript 익명 함수와 화살표 함수 완전 정복

## 목차
1. [함수 선언의 세 가지 방법](#함수-선언의-세-가지-방법)
2. [익명 함수 (Anonymous Function)](#익명-함수-anonymous-function)
3. [화살표 함수 (Arrow Function)](#화살표-함수-arrow-function)
4. [익명 함수 vs 화살표 함수 비교](#익명-함수-vs-화살표-함수-비교)
5. [실전 예제](#실전-예제)
6. [주의사항](#주의사항)

---

## 함수 선언의 세 가지 방법

JavaScript에서 함수를 만드는 방법은 크게 세 가지가 있습니다:

### 1. 함수 선언식 (Function Declaration)

```javascript
function greet(name) {
    return "안녕하세요, " + name + "님!";
}

console.log(greet("홍길동")); // "안녕하세요, 홍길동님!"
```

**특징:**
- 함수 이름이 필수
- 호이스팅(hoisting) 됨 (코드 실행 전에 메모리에 등록됨)
- 어디서든 호출 가능

### 2. 함수 표현식 (Function Expression) - 익명 함수

```javascript
const greet = function(name) {
    return "안녕하세요, " + name + "님!";
};

console.log(greet("홍길동")); // "안녕하세요, 홍길동님!"
```

**특징:**
- 함수 이름이 없음 (익명 함수)
- 변수에 할당됨
- 호이스팅 안 됨 (선언 전에 호출 불가)

### 3. 화살표 함수 (Arrow Function)

```javascript
const greet = (name) => {
    return "안녕하세요, " + name + "님!";
};

console.log(greet("홍길동")); // "안녕하세요, 홍길동님!"
```

**특징:**
- `function` 키워드 대신 `=>` 사용
- 더 간결한 문법
- `this` 바인딩이 다름

---

## 익명 함수 (Anonymous Function)

### 정의

이름이 없는 함수를 **익명 함수(Anonymous Function)**라고 합니다. 함수 표현식에서 함수 이름을 생략한 형태입니다.

### 기본 문법

```javascript
const 함수명 = function(매개변수) {
    // 함수 내용
    return 반환값;
};
```

### 예제 1: 기본 사용

```javascript
// 익명 함수를 변수에 할당
const add = function(a, b) {
    return a + b;
};

console.log(add(3, 5)); // 8
```

### 예제 2: 즉시 실행 함수 (IIFE)

```javascript
// 함수를 정의하고 바로 실행
(function() {
    console.log("즉시 실행!");
})();

// 매개변수 전달
(function(name) {
    console.log("안녕하세요, " + name);
})("홍길동");
```

### 예제 3: 콜백 함수로 사용

```javascript
// 배열의 forEach 메서드에 익명 함수 전달
const numbers = [1, 2, 3, 4, 5];

numbers.forEach(function(num) {
    console.log(num * 2);
});
// 출력: 2, 4, 6, 8, 10

// setTimeout에 익명 함수 전달
setTimeout(function() {
    console.log("3초 후 실행!");
}, 3000);
```

### 예제 4: 이벤트 리스너에 사용

```javascript
const button = document.querySelector('button');

button.addEventListener('click', function() {
    console.log('버튼이 클릭되었습니다!');
});
```

### 예제 5: map, filter, reduce에 사용

```javascript
const numbers = [1, 2, 3, 4, 5];

// map: 각 요소를 변환
const doubled = numbers.map(function(num) {
    return num * 2;
});
console.log(doubled); // [2, 4, 6, 8, 10]

// filter: 조건에 맞는 요소만 필터링
const evens = numbers.filter(function(num) {
    return num % 2 === 0;
});
console.log(evens); // [2, 4]

// reduce: 배열을 하나의 값으로 축약
const sum = numbers.reduce(function(acc, num) {
    return acc + num;
}, 0);
console.log(sum); // 15
```

---

## 화살표 함수 (Arrow Function)

### 정의

ES6(ES2015)에서 도입된 더 간결한 함수 문법입니다. `function` 키워드 대신 `=>` 화살표를 사용합니다.

### 기본 문법

```javascript
// 기본 형태
const 함수명 = (매개변수) => {
    return 반환값;
};

// 매개변수가 하나일 때 괄호 생략 가능
const 함수명 = 매개변수 => {
    return 반환값;
};

// 단일 표현식일 때 return과 중괄호 생략 가능
const 함수명 = (매개변수) => 반환값;
```

### 예제 1: 기본 사용

```javascript
// 일반 함수
const add1 = function(a, b) {
    return a + b;
};

// 화살표 함수
const add2 = (a, b) => {
    return a + b;
};

// 화살표 함수 (간단한 형태)
const add3 = (a, b) => a + b;

console.log(add1(3, 5)); // 8
console.log(add2(3, 5)); // 8
console.log(add3(3, 5)); // 8
```

### 예제 2: 매개변수가 하나일 때

```javascript
// 괄호 생략 가능
const square1 = (x) => x * x;
const square2 = x => x * x; // 괄호 생략

console.log(square1(5)); // 25
console.log(square2(5)); // 25
```

### 예제 3: 매개변수가 없을 때

```javascript
// 괄호 필수
const greet1 = () => "안녕하세요!";
const greet2 = () => {
    return "안녕하세요!";
};

console.log(greet1()); // "안녕하세요!"
console.log(greet2()); // "안녕하세요!"
```

### 예제 4: 객체 반환 시 주의

```javascript
// 객체를 반환할 때는 괄호로 감싸야 함
const createUser1 = (name, age) => {
    return { name: name, age: age };
};

// 간단한 형태 (괄호 필수!)
const createUser2 = (name, age) => ({ name: name, age: age });

console.log(createUser1("홍길동", 25)); // {name: "홍길동", age: 25}
console.log(createUser2("홍길동", 25)); // {name: "홍길동", age: 25}
```

### 예제 5: 배열 메서드와 함께 사용

```javascript
const numbers = [1, 2, 3, 4, 5];

// map
const doubled = numbers.map(num => num * 2);
console.log(doubled); // [2, 4, 6, 8, 10]

// filter
const evens = numbers.filter(num => num % 2 === 0);
console.log(evens); // [2, 4]

// reduce
const sum = numbers.reduce((acc, num) => acc + num, 0);
console.log(sum); // 15

// find
const found = numbers.find(num => num > 3);
console.log(found); // 4
```

### 예제 6: 이벤트 리스너에 사용

```javascript
const button = document.querySelector('button');

// 화살표 함수 사용
button.addEventListener('click', () => {
    console.log('버튼이 클릭되었습니다!');
});

// 매개변수 사용
button.addEventListener('click', (event) => {
    console.log('이벤트:', event);
});
```

### 예제 7: setTimeout, setInterval에 사용

```javascript
// setTimeout
setTimeout(() => {
    console.log("3초 후 실행!");
}, 3000);

// setInterval
let count = 0;
const timer = setInterval(() => {
    count++;
    console.log(count);
    if(count >= 5) {
        clearInterval(timer);
    }
}, 1000);
```

---

## 익명 함수 vs 화살표 함수 비교

### 1. 문법 비교

```javascript
// 익명 함수
const func1 = function(a, b) {
    return a + b;
};

// 화살표 함수
const func2 = (a, b) => a + b;
```

### 2. this 바인딩 차이 ⭐ 중요!

```javascript
const obj = {
    name: "홍길동",
    
    // 익명 함수: this가 obj를 가리킴
    greet1: function() {
        console.log("안녕하세요, " + this.name);
    },
    
    // 화살표 함수: this가 상위 스코프를 가리킴 (여기서는 window/global)
    greet2: () => {
        console.log("안녕하세요, " + this.name); // undefined 또는 에러
    }
};

obj.greet1(); // "안녕하세요, 홍길동"
obj.greet2(); // "안녕하세요, undefined"
```

### 3. 이벤트 리스너에서의 this

```javascript
const button = document.querySelector('button');

// 익명 함수: this가 button 요소를 가리킴
button.addEventListener('click', function() {
    console.log(this); // <button> 요소
    console.log(this.textContent); // 버튼의 텍스트
});

// 화살표 함수: this가 상위 스코프를 가리킴
button.addEventListener('click', () => {
    console.log(this); // window 객체
    console.log(this.textContent); // undefined
});
```

### 4. 생성자 함수로 사용 불가

```javascript
// 익명 함수: 생성자 함수로 사용 가능
const Person1 = function(name) {
    this.name = name;
};
const person1 = new Person1("홍길동"); // 가능

// 화살표 함수: 생성자 함수로 사용 불가
const Person2 = (name) => {
    this.name = name;
};
const person2 = new Person2("홍길동"); // 에러!
```

### 5. arguments 객체 사용 불가

```javascript
// 익명 함수: arguments 객체 사용 가능
const func1 = function() {
    console.log(arguments); // 전달된 모든 인수
};
func1(1, 2, 3); // Arguments(3) [1, 2, 3]

// 화살표 함수: arguments 객체 사용 불가
const func2 = () => {
    console.log(arguments); // 에러!
};
func2(1, 2, 3); // ReferenceError

// 대신 나머지 매개변수 사용
const func3 = (...args) => {
    console.log(args); // [1, 2, 3]
};
func3(1, 2, 3); // [1, 2, 3]
```

---

## 실전 예제

### 예제 1: DOM 조작

```javascript
// HTML
// <button id="btn">클릭</button>
// <div id="result"></div>

const button = document.getElementById('btn');
const result = document.getElementById('result');

// 익명 함수 사용
button.addEventListener('click', function() {
    result.textContent = '버튼이 클릭되었습니다!';
    this.style.backgroundColor = 'red'; // this가 button을 가리킴
});

// 화살표 함수 사용
button.addEventListener('click', () => {
    result.textContent = '버튼이 클릭되었습니다!';
    button.style.backgroundColor = 'blue'; // this 대신 button 직접 사용
});
```

### 예제 2: 배열 처리

```javascript
const students = [
    { name: "홍길동", score: 85 },
    { name: "김철수", score: 92 },
    { name: "이영희", score: 78 }
];

// 익명 함수 사용
const highScores1 = students.filter(function(student) {
    return student.score >= 80;
});

// 화살표 함수 사용 (더 간결)
const highScores2 = students.filter(student => student.score >= 80);

console.log(highScores1);
console.log(highScores2);
```

### 예제 3: 비동기 처리 (Promise)

```javascript
// 익명 함수 사용
fetch('https://api.example.com/data')
    .then(function(response) {
        return response.json();
    })
    .then(function(data) {
        console.log(data);
    })
    .catch(function(error) {
        console.error(error);
    });

// 화살표 함수 사용 (더 간결)
fetch('https://api.example.com/data')
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
```

### 예제 4: 클로저 (Closure)

```javascript
// 익명 함수로 클로저 생성
const createCounter1 = function() {
    let count = 0;
    return function() {
        return ++count;
    };
};

const counter1 = createCounter1();
console.log(counter1()); // 1
console.log(counter1()); // 2

// 화살표 함수로 클로저 생성
const createCounter2 = () => {
    let count = 0;
    return () => ++count;
};

const counter2 = createCounter2();
console.log(counter2()); // 1
console.log(counter2()); // 2
```

### 예제 5: 고차 함수 (Higher-Order Function)

```javascript
// 함수를 반환하는 함수
const multiply = (x) => {
    return (y) => x * y;
};

const double = multiply(2);
const triple = multiply(3);

console.log(double(5)); // 10
console.log(triple(5)); // 15

// 더 간단한 형태
const multiply2 = x => y => x * y;
const double2 = multiply2(2);
console.log(double2(5)); // 10
```

### 예제 6: 실제 프로젝트 예제 (아코디언 메뉴)

```javascript
// 익명 함수 사용
document.addEventListener('DOMContentLoaded', function() {
    const menuLinks = document.querySelectorAll('.menu-link');
    
    menuLinks.forEach(function(link) {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const subMenu = this.nextElementSibling;
            // this가 link 요소를 가리킴
            subMenu.classList.toggle('active');
        });
    });
});

// 화살표 함수 사용
document.addEventListener('DOMContentLoaded', () => {
    const menuLinks = document.querySelectorAll('.menu-link');
    
    menuLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const subMenu = link.nextElementSibling;
            // this 대신 link 직접 사용
            subMenu.classList.toggle('active');
        });
    });
});
```

---

## 주의사항

### 1. this 바인딩 주의

```javascript
const obj = {
    name: "홍길동",
    items: [1, 2, 3],
    
    // ❌ 잘못된 사용: 화살표 함수에서 this 사용
    print1: () => {
        this.items.forEach(item => {
            console.log(this.name + ": " + item); // this.name이 undefined
        });
    },
    
    // ✅ 올바른 사용: 익명 함수 사용
    print2: function() {
        this.items.forEach(item => {
            console.log(this.name + ": " + item); // 정상 작동
        });
    },
    
    // ✅ 올바른 사용: 화살표 함수지만 this를 사용하지 않음
    print3: function() {
        const name = this.name; // 미리 저장
        this.items.forEach(item => {
            console.log(name + ": " + item); // 정상 작동
        });
    }
};
```

### 2. 메서드 정의 시 주의

```javascript
const obj = {
    // ✅ 일반 메서드: this가 obj를 가리킴
    method1: function() {
        return this;
    },
    
    // ❌ 화살표 함수: this가 상위 스코프를 가리킴
    method2: () => {
        return this; // window 또는 global
    },
    
    // ✅ ES6 메서드 축약형 (권장)
    method3() {
        return this; // obj를 가리킴
    }
};
```

### 3. 프로토타입 메서드 정의 시 주의

```javascript
function Person(name) {
    this.name = name;
}

// ✅ 익명 함수 사용 (권장)
Person.prototype.greet = function() {
    return "안녕하세요, " + this.name;
};

// ❌ 화살표 함수 사용 (this가 Person 생성자가 아닌 상위 스코프를 가리킴)
Person.prototype.greet2 = () => {
    return "안녕하세요, " + this.name; // undefined
};
```

### 4. 이벤트 리스너에서 this 사용 시 주의

```javascript
const button = document.querySelector('button');

// ✅ 익명 함수: this가 button을 가리킴
button.addEventListener('click', function() {
    console.log(this); // <button> 요소
    this.style.color = 'red';
});

// ❌ 화살표 함수: this가 window를 가리킴
button.addEventListener('click', () => {
    console.log(this); // window 객체
    this.style.color = 'red'; // 에러 발생 가능
});

// ✅ 화살표 함수 사용 시: 이벤트 객체 사용
button.addEventListener('click', (e) => {
    console.log(e.target); // <button> 요소
    e.target.style.color = 'red';
});
```

---

## 언제 무엇을 사용할까?

### 익명 함수를 사용하는 경우

1. **메서드 정의** (객체의 메서드)
2. **생성자 함수**
3. **this가 필요한 경우** (이벤트 리스너, 메서드)
4. **arguments 객체가 필요한 경우**

### 화살표 함수를 사용하는 경우

1. **콜백 함수** (map, filter, forEach 등)
2. **간단한 함수** (한 줄로 표현 가능한 경우)
3. **this 바인딩이 필요 없는 경우**
4. **비동기 처리** (Promise, async/await)

---

## 요약

| 특징 | 익명 함수 | 화살표 함수 |
|------|----------|------------|
| 문법 | `function() {}` | `() => {}` |
| this 바인딩 | 호출한 객체 | 상위 스코프 |
| 생성자 함수 | 가능 | 불가능 |
| arguments 객체 | 사용 가능 | 사용 불가 |
| 코드 길이 | 길다 | 짧다 |
| 호이스팅 | 안 됨 | 안 됨 |

---

## 연습 문제

### 문제 1: 배열 변환

다음 배열을 화살표 함수를 사용하여 각 요소를 제곱한 배열을 만드세요.

```javascript
const numbers = [1, 2, 3, 4, 5];
// 답: [1, 4, 9, 16, 25]
```

### 문제 2: 필터링

다음 배열에서 짝수만 필터링하세요 (화살표 함수 사용).

```javascript
const numbers = [1, 2, 3, 4, 5, 6, 7, 8];
// 답: [2, 4, 6, 8]
```

### 문제 3: 이벤트 리스너

버튼 클릭 시 "클릭되었습니다!"를 출력하는 이벤트 리스너를 작성하세요 (두 가지 방법 모두).

### 문제 4: 객체 메서드

다음 코드에서 올바르게 작동하도록 수정하세요.

```javascript
const calculator = {
    value: 0,
    add: (num) => {
        this.value += num; // 문제: this가 calculator를 가리키지 않음
    }
};
```

---

## 정답

### 문제 1 정답

```javascript
const numbers = [1, 2, 3, 4, 5];
const squared = numbers.map(num => num * num);
console.log(squared); // [1, 4, 9, 16, 25]
```

### 문제 2 정답

```javascript
const numbers = [1, 2, 3, 4, 5, 6, 7, 8];
const evens = numbers.filter(num => num % 2 === 0);
console.log(evens); // [2, 4, 6, 8]
```

### 문제 3 정답

```javascript
// 방법 1: 익명 함수
button.addEventListener('click', function() {
    console.log('클릭되었습니다!');
});

// 방법 2: 화살표 함수
button.addEventListener('click', () => {
    console.log('클릭되었습니다!');
});
```

### 문제 4 정답

```javascript
// 방법 1: 익명 함수 사용
const calculator = {
    value: 0,
    add: function(num) {
        this.value += num;
    }
};

// 방법 2: ES6 메서드 축약형
const calculator = {
    value: 0,
    add(num) {
        this.value += num;
    }
};
```

---

이제 익명 함수와 화살표 함수를 완벽하게 이해하셨습니다! 🎉
