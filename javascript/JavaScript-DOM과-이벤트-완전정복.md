# JavaScript DOM과 이벤트 완전 정복

## 목차

1. [DOM이란?](#dom이란)
2. [DOM 요소 선택](#dom-요소-선택)
3. [DOM 요소 조작](#dom-요소-조작)
4. [이벤트란?](#이벤트란)
5. [이벤트 리스너 추가](#이벤트-리스너-추가)
6. [주요 이벤트 타입](#주요-이벤트-타입)
7. [이벤트 객체](#이벤트-객체)
8. [이벤트 전파](#이벤트-전파)
9. [실전 예제](#실전-예제)

---

## DOM이란?

**DOM (Document Object Model)**은 HTML 문서를 객체로 표현한 것입니다. JavaScript를 통해 HTML 요소에 접근하고 조작할 수 있게 해줍니다.

### DOM의 역할

- ✅ **요소 선택**: HTML 요소를 JavaScript로 선택
- ✅ **내용 변경**: 텍스트, HTML 내용 수정
- ✅ **스타일 변경**: CSS 스타일 동적 변경
- ✅ **요소 추가/삭제**: 동적으로 요소 생성 및 제거
- ✅ **이벤트 처리**: 사용자 상호작용 처리

### DOM 트리 구조

```
document (문서)
└── html
    ├── head
    │   ├── title
    │   └── meta
    └── body
        ├── h1
        ├── p
        └── button
```

---

## DOM 요소 선택

### 1. getElementById() - ID로 선택

**가장 많이 사용되는 방법**

```html
<button id="myBtn">클릭하세요</button>
<p id="message">메시지</p>
```

```javascript
// ID로 요소 선택
const btn = document.getElementById('myBtn');
const message = document.getElementById('message');

console.log(btn);      // <button id="myBtn">클릭하세요</button>
console.log(message);  // <p id="message">메시지</p>
```

**특징:**
- ID는 문서당 하나만 존재해야 함
- 요소가 없으면 `null` 반환
- 가장 빠른 선택 방법

---

### 2. getElementsByClassName() - 클래스로 선택

**여러 요소를 배열로 반환**

```html
<div class="item">항목 1</div>
<div class="item">항목 2</div>
<div class="item">항목 3</div>
```

```javascript
// 클래스명으로 요소들 선택
const items = document.getElementsByClassName('item');
console.log(items);  // HTMLCollection (배열과 유사)

// 각 요소에 접근
for (let i = 0; i < items.length; i++) {
    console.log(items[i].textContent);
}

// 또는 for...of 사용
for (let item of items) {
    console.log(item.textContent);
}
```

**특징:**
- `HTMLCollection` 반환 (배열과 유사하지만 배열은 아님)
- 동적으로 변경됨 (요소 추가/삭제 시 자동 반영)

---

### 3. getElementsByTagName() - 태그명으로 선택

```html
<p>문단 1</p>
<p>문단 2</p>
<p>문단 3</p>
```

```javascript
// 태그명으로 요소들 선택
const paragraphs = document.getElementsByTagName('p');
console.log(paragraphs);  // HTMLCollection

paragraphs.forEach(p => {
    console.log(p.textContent);
});
```

---

### 4. querySelector() - CSS 선택자로 선택 (권장)

**CSS 선택자를 사용하여 첫 번째 요소 선택**

```html
<div class="container">
    <p class="text">텍스트 1</p>
    <p class="text">텍스트 2</p>
</div>
```

```javascript
// CSS 선택자 사용
const text = document.querySelector('.text');        // 첫 번째 .text 요소
const container = document.querySelector('.container');
const firstP = document.querySelector('p');           // 첫 번째 p 요소
const byId = document.querySelector('#myBtn');       // ID 선택자도 가능

console.log(text.textContent);  // "텍스트 1"
```

**특징:**
- CSS 선택자 문법 사용 가능
- 첫 번째 요소만 반환
- 요소가 없으면 `null` 반환

---

### 5. querySelectorAll() - CSS 선택자로 모든 요소 선택 (권장)

**모든 요소를 배열로 반환**

```html
<p class="item">항목 1</p>
<p class="item">항목 2</p>
<p class="item">항목 3</p>
```

```javascript
// 모든 요소 선택
const items = document.querySelectorAll('.item');
console.log(items);  // NodeList (배열과 유사)

// forEach 사용 가능
items.forEach((item, index) => {
    console.log(`${index + 1}: ${item.textContent}`);
});

// 배열 메서드 사용
const texts = Array.from(items).map(item => item.textContent);
console.log(texts);  // ["항목 1", "항목 2", "항목 3"]
```

**특징:**
- `NodeList` 반환
- `forEach` 사용 가능
- CSS 선택자 문법 사용 가능

---

## DOM 요소 조작

### 1. 내용 변경

#### textContent - 텍스트 내용

```html
<p id="text">원본 텍스트</p>
```

```javascript
const text = document.getElementById('text');

// 텍스트 가져오기
console.log(text.textContent);  // "원본 텍스트"

// 텍스트 변경
text.textContent = '변경된 텍스트';
```

**특징:**
- HTML 태그는 텍스트로 처리됨
- 보안에 안전 (XSS 공격 방지)

#### innerHTML - HTML 내용

```html
<div id="content">원본 내용</div>
```

```javascript
const content = document.getElementById('content');

// HTML 가져오기
console.log(content.innerHTML);  // "원본 내용"

// HTML 변경 (태그 포함)
content.innerHTML = '<strong>강조된 텍스트</strong>';
```

**특징:**
- HTML 태그를 실제 HTML로 렌더링
- 동적으로 HTML 생성 가능
- **주의:** XSS 공격 위험 있음 (신뢰할 수 없는 데이터 사용 시 주의)

#### innerText - 보이는 텍스트만

```html
<div id="box">
    보이는 텍스트
    <span style="display: none;">숨겨진 텍스트</span>
</div>
```

```javascript
const box = document.getElementById('box');

console.log(box.textContent);  // "보이는 텍스트\n    숨겨진 텍스트"
console.log(box.innerText);    // "보이는 텍스트" (보이는 것만)
```

---

### 2. 속성 조작

#### getAttribute() / setAttribute()

```html
<img id="image" src="old.jpg" alt="이미지">
<a id="link" href="https://example.com">링크</a>
```

```javascript
const image = document.getElementById('image');
const link = document.getElementById('link');

// 속성 가져오기
console.log(image.getAttribute('src'));  // "old.jpg"
console.log(link.getAttribute('href'));  // "https://example.com"

// 속성 설정
image.setAttribute('src', 'new.jpg');
image.setAttribute('alt', '새 이미지');
link.setAttribute('href', 'https://newsite.com');
link.setAttribute('target', '_blank');
```

#### 직접 속성 접근

```javascript
// 직접 속성 접근 (더 간단)
image.src = 'new.jpg';
image.alt = '새 이미지';
link.href = 'https://newsite.com';
link.target = '_blank';

// 클래스 추가/제거
const element = document.getElementById('myElement');
element.className = 'new-class';
element.classList.add('active');      // 클래스 추가
element.classList.remove('active');   // 클래스 제거
element.classList.toggle('active');   // 클래스 토글
```

---

### 3. 스타일 변경

```html
<div id="box">박스</div>
```

```javascript
const box = document.getElementById('box');

// 스타일 직접 변경
box.style.color = 'red';
box.style.backgroundColor = 'yellow';
box.style.fontSize = '20px';
box.style.padding = '10px';
box.style.border = '2px solid blue';

// CSS 속성명은 camelCase로 변환
// background-color → backgroundColor
// font-size → fontSize
// margin-top → marginTop
```

**주의사항:**
- CSS 속성명은 하이픈(-)을 제거하고 camelCase로 변환
- `-` 뒤의 문자를 대문자로 변경

---

### 4. 요소 추가/삭제

#### createElement() - 요소 생성

```javascript
// 새로운 요소 생성
const newDiv = document.createElement('div');
newDiv.textContent = '새로운 div 요소';
newDiv.className = 'new-item';

// 속성 추가
newDiv.setAttribute('id', 'newDiv');
newDiv.style.color = 'blue';
```

#### appendChild() - 요소 추가

```html
<div id="container">
    <p>기존 내용</p>
</div>
```

```javascript
const container = document.getElementById('container');
const newP = document.createElement('p');
newP.textContent = '새로운 문단';

// 자식 요소로 추가 (맨 뒤에)
container.appendChild(newP);
```

#### insertBefore() - 특정 위치에 추가

```javascript
const container = document.getElementById('container');
const newP = document.createElement('p');
newP.textContent = '새로운 문단';
const firstChild = container.firstElementChild;

// 첫 번째 자식 앞에 추가
container.insertBefore(newP, firstChild);
```

#### removeChild() - 요소 삭제

```javascript
const container = document.getElementById('container');
const child = container.firstElementChild;

// 자식 요소 삭제
container.removeChild(child);

// 또는 간단하게
child.remove();  // 최신 방법
```

---

## 이벤트란?

**이벤트(Event)**는 사용자의 행동이나 브라우저의 동작을 의미합니다.

### 이벤트의 종류

- **마우스 이벤트**: click, dblclick, mouseover, mouseout 등
- **키보드 이벤트**: keydown, keyup, keypress 등
- **폼 이벤트**: submit, change, focus, blur 등
- **윈도우 이벤트**: load, resize, scroll 등

---

## 이벤트 리스너 추가

### 1. addEventListener() (권장)

**가장 권장되는 방법**

```html
<button id="btn">클릭하세요</button>
```

```javascript
const btn = document.getElementById('btn');

// 이벤트 리스너 추가
btn.addEventListener('click', function() {
    alert('버튼이 클릭되었습니다!');
});

// 화살표 함수 사용 가능
btn.addEventListener('click', () => {
    console.log('클릭됨');
});

// 외부 함수 사용
function handleClick() {
    alert('클릭됨');
}
btn.addEventListener('click', handleClick);
```

**장점:**
- 여러 이벤트 리스너 추가 가능
- 이벤트 제거 가능 (`removeEventListener`)
- HTML과 JavaScript 분리

---

### 2. 인라인 이벤트 (권장하지 않음)

```html
<!-- HTML에 직접 작성 -->
<button onclick="alert('클릭됨')">클릭</button>
<button onclick="handleClick()">클릭</button>
```

**단점:**
- HTML과 JavaScript가 섞임
- 유지보수 어려움
- 여러 이벤트 추가 불가

---

### 3. 이벤트 속성 (권장하지 않음)

```html
<button id="btn">클릭</button>
```

```javascript
const btn = document.getElementById('btn');

// 이벤트 속성 사용
btn.onclick = function() {
    alert('클릭됨');
};

// 덮어쓰기 문제: 이전 이벤트가 사라짐
btn.onclick = function() {
    console.log('새로운 클릭');
};  // 위의 alert는 실행되지 않음
```

**단점:**
- 하나의 이벤트만 등록 가능
- 나중에 추가한 이벤트가 이전 것을 덮어씀

---

## 주요 이벤트 타입

### 마우스 이벤트

```html
<button id="btn">버튼</button>
<div id="box">박스</div>
```

```javascript
const btn = document.getElementById('btn');
const box = document.getElementById('box');

// click: 클릭
btn.addEventListener('click', function() {
    console.log('클릭됨');
});

// dblclick: 더블 클릭
btn.addEventListener('dblclick', function() {
    console.log('더블 클릭됨');
});

// mouseover: 마우스가 요소 위로 이동
box.addEventListener('mouseover', function() {
    box.style.backgroundColor = 'yellow';
});

// mouseout: 마우스가 요소 밖으로 이동
box.addEventListener('mouseout', function() {
    box.style.backgroundColor = 'white';
});

// mousedown: 마우스 버튼 누름
btn.addEventListener('mousedown', function() {
    console.log('마우스 버튼 누름');
});

// mouseup: 마우스 버튼 뗌
btn.addEventListener('mouseup', function() {
    console.log('마우스 버튼 뗌');
});

// mousemove: 마우스 이동
box.addEventListener('mousemove', function(e) {
    console.log(`마우스 위치: ${e.clientX}, ${e.clientY}`);
});
```

---

### 키보드 이벤트

```html
<input type="text" id="input" placeholder="입력하세요">
```

```javascript
const input = document.getElementById('input');

// keydown: 키를 누름
input.addEventListener('keydown', function(event) {
    console.log('키 누름:', event.key);
    console.log('키 코드:', event.keyCode);
});

// keyup: 키를 뗌
input.addEventListener('keyup', function(event) {
    console.log('키 뗌:', event.key);
    
    // Enter 키 확인
    if (event.key === 'Enter') {
        console.log('Enter 키 입력됨');
    }
});

// keypress: 키 입력 (deprecated, keydown 사용 권장)
input.addEventListener('keypress', function(event) {
    console.log('키 입력:', event.key);
});
```

**특수 키 확인:**

```javascript
input.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        console.log('Enter 키');
    }
    if (event.key === 'Escape') {
        console.log('ESC 키');
    }
    if (event.ctrlKey && event.key === 's') {
        console.log('Ctrl+S (저장)');
        event.preventDefault();  // 기본 동작 방지
    }
});
```

---

### 폼 이벤트

```html
<form id="myForm">
    <input type="text" id="username" placeholder="사용자명">
    <input type="email" id="email" placeholder="이메일">
    <button type="submit">제출</button>
</form>
```

```javascript
const form = document.getElementById('myForm');
const username = document.getElementById('username');
const email = document.getElementById('email');

// submit: 폼 제출
form.addEventListener('submit', function(event) {
    event.preventDefault();  // 기본 제출 동작 방지
    
    console.log('폼 제출됨');
    console.log('사용자명:', username.value);
    console.log('이메일:', email.value);
});

// change: 값이 변경되고 포커스가 벗어날 때
username.addEventListener('change', function() {
    console.log('사용자명 변경됨:', username.value);
});

// input: 값이 변경될 때마다 (실시간)
username.addEventListener('input', function() {
    console.log('입력 중:', username.value);
});

// focus: 포커스 받을 때
username.addEventListener('focus', function() {
    username.style.borderColor = 'blue';
});

// blur: 포커스 잃을 때
username.addEventListener('blur', function() {
    username.style.borderColor = 'gray';
    
    // 유효성 검사
    if (username.value.length < 3) {
        alert('사용자명은 3자 이상이어야 합니다');
    }
});
```

---

### 윈도우 이벤트

```javascript
// load: 페이지 로드 완료
window.addEventListener('load', function() {
    console.log('페이지 로드 완료');
});

// DOMContentLoaded: DOM 로드 완료 (더 빠름)
document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM 로드 완료');
});

// resize: 창 크기 변경
window.addEventListener('resize', function() {
    console.log('창 크기 변경됨');
    console.log('너비:', window.innerWidth);
    console.log('높이:', window.innerHeight);
});

// scroll: 스크롤
window.addEventListener('scroll', function() {
    console.log('스크롤 위치:', window.scrollY);
    
    // 스크롤 위치에 따라 동작
    if (window.scrollY > 100) {
        console.log('100px 이상 스크롤됨');
    }
});
```

---

## 이벤트 객체

이벤트가 발생하면 **이벤트 객체(Event Object)**가 자동으로 전달됩니다.

### 이벤트 객체의 주요 속성

```html
<button id="btn">클릭하세요</button>
<input type="text" id="input">
```

```javascript
const btn = document.getElementById('btn');
const input = document.getElementById('input');

// 클릭 이벤트
btn.addEventListener('click', function(event) {
    console.log('이벤트 타입:', event.type);        // "click"
    console.log('대상 요소:', event.target);        // <button>
    console.log('현재 요소:', event.currentTarget); // <button>
    console.log('마우스 X:', event.clientX);        // 마우스 X 좌표
    console.log('마우스 Y:', event.clientY);       // 마우스 Y 좌표
});

// 키보드 이벤트
input.addEventListener('keydown', function(event) {
    console.log('키:', event.key);                 // 입력된 키
    console.log('키 코드:', event.keyCode);        // 키 코드
    console.log('Ctrl 키:', event.ctrlKey);        // Ctrl 키 누름 여부
    console.log('Shift 키:', event.shiftKey);      // Shift 키 누름 여부
    console.log('Alt 키:', event.altKey);          // Alt 키 누름 여부
});

// 기본 동작 방지
const link = document.querySelector('a');
link.addEventListener('click', function(event) {
    event.preventDefault();  // 링크 이동 방지
    console.log('링크 클릭됨 (이동 안 함)');
});
```

---

## 이벤트 전파

이벤트는 **버블링(Bubbling)**과 **캡처링(Capturing)** 단계를 거칩니다.

### 버블링 (Bubbling)

**이벤트가 자식 요소에서 부모 요소로 전파**

```html
<div id="parent">
    부모
    <div id="child">
        자식
        <button id="btn">버튼</button>
    </div>
</div>
```

```javascript
const parent = document.getElementById('parent');
const child = document.getElementById('child');
const btn = document.getElementById('btn');

// 버튼 클릭 시: btn → child → parent 순서로 이벤트 전파
btn.addEventListener('click', function(event) {
    console.log('버튼 클릭');
});

child.addEventListener('click', function(event) {
    console.log('자식 클릭');
});

parent.addEventListener('click', function(event) {
    console.log('부모 클릭');
});

// 출력 순서:
// 버튼 클릭
// 자식 클릭
// 부모 클릭
```

### 이벤트 전파 중지

```javascript
btn.addEventListener('click', function(event) {
    console.log('버튼 클릭');
    event.stopPropagation();  // 이벤트 전파 중지
});

child.addEventListener('click', function(event) {
    console.log('자식 클릭');  // 실행 안 됨
});
```

### 캡처링 (Capturing)

**이벤트가 부모 요소에서 자식 요소로 전파**

```javascript
// 세 번째 매개변수에 true를 전달하면 캡처링 단계에서 실행
parent.addEventListener('click', function(event) {
    console.log('부모 클릭 (캡처링)');
}, true);  // 캡처링 단계

btn.addEventListener('click', function(event) {
    console.log('버튼 클릭');
});

// 출력 순서:
// 부모 클릭 (캡처링)
// 버튼 클릭
```

---

## 실전 예제

### 예제 1: 간단한 클릭 카운터

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>클릭 카운터</title>
    <style>
        #counter {
            font-size: 24px;
            margin: 20px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div id="counter">클릭 횟수: 0</div>
    <button id="btn">클릭</button>

    <script>
        let count = 0;
        const counter = document.getElementById('counter');
        const btn = document.getElementById('btn');

        btn.addEventListener('click', function() {
            count++;
            counter.textContent = `클릭 횟수: ${count}`;
        });
    </script>
</body>
</html>
```

---

### 예제 2: 할 일 목록 (To-Do List)

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>할 일 목록</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 500px;
            margin: 50px auto;
        }
        #todoInput {
            width: 70%;
            padding: 10px;
            font-size: 16px;
        }
        #addBtn {
            padding: 10px 20px;
            font-size: 16px;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            padding: 10px;
            margin: 5px 0;
            background-color: #f0f0f0;
            display: flex;
            justify-content: space-between;
        }
        .delete {
            color: red;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>할 일 목록</h1>
    <input type="text" id="todoInput" placeholder="할 일을 입력하세요">
    <button id="addBtn">추가</button>
    <ul id="todoList"></ul>

    <script>
        const todoInput = document.getElementById('todoInput');
        const addBtn = document.getElementById('addBtn');
        const todoList = document.getElementById('todoList');

        // 추가 버튼 클릭
        addBtn.addEventListener('click', function() {
            addTodo();
        });

        // Enter 키 입력
        todoInput.addEventListener('keypress', function(event) {
            if (event.key === 'Enter') {
                addTodo();
            }
        });

        function addTodo() {
            const text = todoInput.value.trim();
            
            if (text === '') {
                alert('할 일을 입력하세요');
                return;
            }

            // 새로운 li 요소 생성
            const li = document.createElement('li');
            
            // 텍스트 추가
            const span = document.createElement('span');
            span.textContent = text;
            li.appendChild(span);

            // 삭제 버튼 추가
            const deleteBtn = document.createElement('span');
            deleteBtn.textContent = '삭제';
            deleteBtn.className = 'delete';
            deleteBtn.addEventListener('click', function() {
                li.remove();
            });
            li.appendChild(deleteBtn);

            // 목록에 추가
            todoList.appendChild(li);

            // 입력 필드 초기화
            todoInput.value = '';
            todoInput.focus();
        }
    </script>
</body>
</html>
```

---

### 예제 3: 이미지 갤러리

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>이미지 갤러리</title>
    <style>
        .gallery {
            display: flex;
            gap: 10px;
            margin: 20px 0;
        }
        .thumbnail {
            width: 100px;
            height: 100px;
            object-fit: cover;
            cursor: pointer;
            border: 2px solid transparent;
        }
        .thumbnail:hover {
            border-color: blue;
        }
        .thumbnail.active {
            border-color: red;
        }
        #mainImage {
            max-width: 500px;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <h1>이미지 갤러리</h1>
    <div class="gallery">
        <img src="image1.jpg" class="thumbnail" alt="이미지 1">
        <img src="image2.jpg" class="thumbnail" alt="이미지 2">
        <img src="image3.jpg" class="thumbnail" alt="이미지 3">
    </div>
    <img id="mainImage" src="image1.jpg" alt="메인 이미지">

    <script>
        const thumbnails = document.querySelectorAll('.thumbnail');
        const mainImage = document.getElementById('mainImage');

        thumbnails.forEach(thumbnail => {
            thumbnail.addEventListener('click', function() {
                // 모든 썸네일에서 active 클래스 제거
                thumbnails.forEach(t => t.classList.remove('active'));
                
                // 클릭한 썸네일에 active 클래스 추가
                thumbnail.classList.add('active');
                
                // 메인 이미지 변경
                mainImage.src = thumbnail.src;
                mainImage.alt = thumbnail.alt;
            });
        });
    </script>
</body>
</html>
```

---

### 예제 4: 폼 유효성 검사

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>폼 유효성 검사</title>
    <style>
        .error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
        input.error {
            border-color: red;
        }
        input.valid {
            border-color: green;
        }
    </style>
</head>
<body>
    <h1>회원가입</h1>
    <form id="signupForm">
        <div>
            <label>이름:</label>
            <input type="text" id="name" required>
            <div class="error" id="nameError"></div>
        </div>
        
        <div>
            <label>이메일:</label>
            <input type="email" id="email" required>
            <div class="error" id="emailError"></div>
        </div>
        
        <div>
            <label>비밀번호:</label>
            <input type="password" id="password" required>
            <div class="error" id="passwordError"></div>
        </div>
        
        <button type="submit">가입하기</button>
    </form>

    <script>
        const form = document.getElementById('signupForm');
        const nameInput = document.getElementById('name');
        const emailInput = document.getElementById('email');
        const passwordInput = document.getElementById('password');

        // 이름 유효성 검사
        nameInput.addEventListener('blur', function() {
            const name = nameInput.value.trim();
            const errorDiv = document.getElementById('nameError');
            
            if (name.length < 2) {
                nameInput.classList.add('error');
                nameInput.classList.remove('valid');
                errorDiv.textContent = '이름은 2자 이상이어야 합니다';
            } else {
                nameInput.classList.remove('error');
                nameInput.classList.add('valid');
                errorDiv.textContent = '';
            }
        });

        // 이메일 유효성 검사
        emailInput.addEventListener('blur', function() {
            const email = emailInput.value.trim();
            const errorDiv = document.getElementById('emailError');
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            
            if (!emailPattern.test(email)) {
                emailInput.classList.add('error');
                emailInput.classList.remove('valid');
                errorDiv.textContent = '올바른 이메일 형식이 아닙니다';
            } else {
                emailInput.classList.remove('error');
                emailInput.classList.add('valid');
                errorDiv.textContent = '';
            }
        });

        // 비밀번호 유효성 검사
        passwordInput.addEventListener('blur', function() {
            const password = passwordInput.value;
            const errorDiv = document.getElementById('passwordError');
            
            if (password.length < 6) {
                passwordInput.classList.add('error');
                passwordInput.classList.remove('valid');
                errorDiv.textContent = '비밀번호는 6자 이상이어야 합니다';
            } else {
                passwordInput.classList.remove('error');
                passwordInput.classList.add('valid');
                errorDiv.textContent = '';
            }
        });

        // 폼 제출
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            
            const name = nameInput.value.trim();
            const email = emailInput.value.trim();
            const password = passwordInput.value;
            
            // 모든 유효성 검사 통과 확인
            if (name.length >= 2 && 
                /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email) && 
                password.length >= 6) {
                alert('가입이 완료되었습니다!');
                form.reset();
            } else {
                alert('모든 항목을 올바르게 입력해주세요');
            }
        });
    </script>
</body>
</html>
```

---

### 예제 5: 모달 창 (Modal)

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>모달 창</title>
    <style>
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 1000;
        }
        .modal.active {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .modal-content {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            max-width: 500px;
            width: 90%;
        }
        .close {
            float: right;
            font-size: 24px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <button id="openModal">모달 열기</button>

    <div id="modal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>모달 제목</h2>
            <p>모달 내용입니다.</p>
        </div>
    </div>

    <script>
        const openBtn = document.getElementById('openModal');
        const modal = document.getElementById('modal');
        const closeBtn = document.querySelector('.close');

        // 모달 열기
        openBtn.addEventListener('click', function() {
            modal.classList.add('active');
        });

        // 모달 닫기 (X 버튼)
        closeBtn.addEventListener('click', function() {
            modal.classList.remove('active');
        });

        // 모달 배경 클릭 시 닫기
        modal.addEventListener('click', function(event) {
            if (event.target === modal) {
                modal.classList.remove('active');
            }
        });

        // ESC 키로 닫기
        document.addEventListener('keydown', function(event) {
            if (event.key === 'Escape' && modal.classList.contains('active')) {
                modal.classList.remove('active');
            }
        });
    </script>
</body>
</html>
```

---

### 예제 6: 탭 메뉴

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>탭 메뉴</title>
    <style>
        .tabs {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
        .tab {
            padding: 10px 20px;
            background-color: #f0f0f0;
            cursor: pointer;
            border: none;
        }
        .tab.active {
            background-color: #007bff;
            color: white;
        }
        .tab-content {
            display: none;
            padding: 20px;
            border: 1px solid #ddd;
        }
        .tab-content.active {
            display: block;
        }
    </style>
</head>
<body>
    <div class="tabs">
        <button class="tab active" data-tab="tab1">탭 1</button>
        <button class="tab" data-tab="tab2">탭 2</button>
        <button class="tab" data-tab="tab3">탭 3</button>
    </div>

    <div id="tab1" class="tab-content active">
        <h2>탭 1 내용</h2>
        <p>첫 번째 탭의 내용입니다.</p>
    </div>

    <div id="tab2" class="tab-content">
        <h2>탭 2 내용</h2>
        <p>두 번째 탭의 내용입니다.</p>
    </div>

    <div id="tab3" class="tab-content">
        <h2>탭 3 내용</h2>
        <p>세 번째 탭의 내용입니다.</p>
    </div>

    <script>
        const tabs = document.querySelectorAll('.tab');
        const contents = document.querySelectorAll('.tab-content');

        tabs.forEach(tab => {
            tab.addEventListener('click', function() {
                // 모든 탭과 콘텐츠에서 active 제거
                tabs.forEach(t => t.classList.remove('active'));
                contents.forEach(c => c.classList.remove('active'));

                // 클릭한 탭에 active 추가
                tab.classList.add('active');

                // 해당 콘텐츠에 active 추가
                const tabId = tab.getAttribute('data-tab');
                const content = document.getElementById(tabId);
                content.classList.add('active');
            });
        });
    </script>
</body>
</html>
```

---

## 주요 메서드 정리표

### DOM 선택 메서드

| 메서드 | 설명 | 반환값 |
|--------|------|--------|
| `getElementById(id)` | ID로 요소 선택 | Element 또는 null |
| `getElementsByClassName(class)` | 클래스로 요소들 선택 | HTMLCollection |
| `getElementsByTagName(tag)` | 태그명으로 요소들 선택 | HTMLCollection |
| `querySelector(selector)` | CSS 선택자로 첫 요소 선택 | Element 또는 null |
| `querySelectorAll(selector)` | CSS 선택자로 모든 요소 선택 | NodeList |

### DOM 조작 메서드

| 메서드/속성 | 설명 |
|------------|------|
| `textContent` | 텍스트 내용 가져오기/설정 |
| `innerHTML` | HTML 내용 가져오기/설정 |
| `createElement(tag)` | 새 요소 생성 |
| `appendChild(element)` | 자식 요소 추가 |
| `removeChild(element)` | 자식 요소 삭제 |
| `remove()` | 요소 삭제 |
| `setAttribute(name, value)` | 속성 설정 |
| `getAttribute(name)` | 속성 가져오기 |
| `classList.add(class)` | 클래스 추가 |
| `classList.remove(class)` | 클래스 제거 |
| `classList.toggle(class)` | 클래스 토글 |

### 이벤트 메서드

| 메서드 | 설명 |
|--------|------|
| `addEventListener(type, handler)` | 이벤트 리스너 추가 |
| `removeEventListener(type, handler)` | 이벤트 리스너 제거 |
| `preventDefault()` | 기본 동작 방지 |
| `stopPropagation()` | 이벤트 전파 중지 |

---

## 체크리스트

### DOM 선택
- [ ] getElementById 사용 가능
- [ ] querySelector 사용 가능
- [ ] querySelectorAll 사용 가능
- [ ] 여러 요소 선택 및 반복 처리 가능

### DOM 조작
- [ ] 텍스트 내용 변경 가능
- [ ] HTML 내용 변경 가능
- [ ] 스타일 변경 가능
- [ ] 요소 추가/삭제 가능
- [ ] 속성 조작 가능

### 이벤트
- [ ] addEventListener 사용 가능
- [ ] 주요 이벤트 타입 이해
- [ ] 이벤트 객체 활용 가능
- [ ] 이벤트 전파 이해
- [ ] 기본 동작 방지 가능

---

## 요약

### DOM 핵심 포인트

1. **요소 선택**: `getElementById`, `querySelector` 사용
2. **내용 변경**: `textContent`, `innerHTML` 사용
3. **스타일 변경**: `style` 속성 사용
4. **요소 추가/삭제**: `createElement`, `appendChild`, `remove` 사용

### 이벤트 핵심 포인트

1. **이벤트 리스너**: `addEventListener` 사용 (권장)
2. **이벤트 타입**: click, keydown, submit 등
3. **이벤트 객체**: event 매개변수로 접근
4. **이벤트 전파**: 버블링과 캡처링 이해

---

**DOM과 이벤트를 마스터하면 동적인 웹 페이지를 만들 수 있습니다!**
