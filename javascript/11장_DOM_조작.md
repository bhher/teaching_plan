# 11장. DOM 조작

## DOM이란?

**DOM (Document Object Model)**은 HTML 문서를 객체로 표현한 것입니다. JavaScript를 통해 HTML 요소에 접근하고 조작할 수 있게 해줍니다.

### DOM의 역할

- HTML 요소 선택
- 요소의 내용 변경
- 요소의 스타일 변경
- 요소 추가/삭제
- 이벤트 처리

---

## 1. DOM 요소 선택

### getElementById()

ID로 요소를 선택합니다.

```html
<button id="btn">클릭</button>
```

```javascript
const btn = document.getElementById('btn');
console.log(btn);
```

### getElementsByClassName()

클래스 이름으로 요소들을 선택합니다 (배열 반환).

```html
<div class="item">항목 1</div>
<div class="item">항목 2</div>
```

```javascript
const items = document.getElementsByClassName('item');
console.log(items);  // HTMLCollection (배열과 유사)
```

### getElementsByTagName()

태그 이름으로 요소들을 선택합니다.

```html
<p>문단 1</p>
<p>문단 2</p>
```

```javascript
const paragraphs = document.getElementsByTagName('p');
console.log(paragraphs);
```

### querySelector() (권장)

CSS 선택자를 사용하여 첫 번째 요소를 선택합니다.

```html
<div class="container">
    <p class="text">텍스트</p>
</div>
```

```javascript
const text = document.querySelector('.text');
const container = document.querySelector('.container');
const firstP = document.querySelector('p');
```

### querySelectorAll() (권장)

CSS 선택자를 사용하여 모든 요소를 선택합니다 (배열 반환).

```html
<p class="item">항목 1</p>
<p class="item">항목 2</p>
<p class="item">항목 3</p>
```

```javascript
const items = document.querySelectorAll('.item');
items.forEach(item => {
    console.log(item.textContent);
});
```

---

## 2. 요소 내용 변경

### textContent

텍스트 내용을 가져오거나 설정합니다.

```html
<p id="text">원본 텍스트</p>
```

```javascript
const text = document.getElementById('text');
console.log(text.textContent);  // "원본 텍스트"

text.textContent = '변경된 텍스트';
```

### innerHTML

HTML 내용을 가져오거나 설정합니다.

```html
<div id="container"></div>
```

```javascript
const container = document.getElementById('container');
container.innerHTML = '<h1>제목</h1><p>내용</p>';
```

**주의**: 사용자 입력을 `innerHTML`에 넣으면 XSS 공격 위험이 있습니다!

### innerText

보이는 텍스트만 가져옵니다 (CSS에 의해 숨겨진 텍스트 제외).

```javascript
const element = document.querySelector('.text');
console.log(element.innerText);
```

---

## 3. 요소 속성 조작

### getAttribute() / setAttribute()

```html
<img id="image" src="old.jpg" alt="이미지">
```

```javascript
const img = document.getElementById('image');

// 속성 가져오기
console.log(img.getAttribute('src'));  // "old.jpg"

// 속성 설정하기
img.setAttribute('src', 'new.jpg');
img.setAttribute('alt', '새 이미지');
```

### 속성 직접 접근

```javascript
const img = document.getElementById('image');

// 읽기
console.log(img.src);
console.log(img.alt);

// 쓰기
img.src = 'new.jpg';
img.alt = '새 이미지';
```

### classList

클래스를 조작합니다.

```html
<div id="box">박스</div>
```

```javascript
const box = document.getElementById('box');

// 클래스 추가
box.classList.add('active');
box.classList.add('highlight');

// 클래스 제거
box.classList.remove('active');

// 클래스 토글
box.classList.toggle('active');

// 클래스 확인
if (box.classList.contains('active')) {
    console.log('active 클래스가 있습니다');
}
```

---

## 4. 요소 스타일 변경

### style 속성

```html
<div id="box">박스</div>
```

```javascript
const box = document.getElementById('box');

// 스타일 직접 설정
box.style.color = 'red';
box.style.backgroundColor = 'blue';
box.style.fontSize = '20px';
box.style.padding = '10px';

// 여러 스타일 한 번에
box.style.cssText = 'color: red; background: blue; padding: 10px;';
```

**주의**: CSS 속성명은 camelCase로 변환됩니다 (`background-color` → `backgroundColor`).

---

## 5. 요소 생성 및 추가

### createElement()

새로운 요소를 생성합니다.

```javascript
const newDiv = document.createElement('div');
newDiv.textContent = '새로운 div';
newDiv.className = 'new-item';
```

### appendChild()

자식 요소를 추가합니다.

```html
<div id="container"></div>
```

```javascript
const container = document.getElementById('container');
const newP = document.createElement('p');
newP.textContent = '새로운 문단';
container.appendChild(newP);
```

### insertBefore()

특정 위치에 요소를 삽입합니다.

```javascript
const container = document.getElementById('container');
const newP = document.createElement('p');
newP.textContent = '첫 번째 문단';
const firstChild = container.firstChild;
container.insertBefore(newP, firstChild);
```

### removeChild()

요소를 제거합니다.

```javascript
const container = document.getElementById('container');
const child = container.querySelector('.item');
container.removeChild(child);
```

### remove()

요소를 직접 제거합니다 (ES6+).

```javascript
const item = document.querySelector('.item');
item.remove();
```

---

## 6. 실전 예제

### 예제 1: 버튼 클릭 시 텍스트 변경

```html
<h1 id="title">제목</h1>
<button id="btn">변경</button>
```

```javascript
const btn = document.getElementById('btn');
const title = document.getElementById('title');

btn.addEventListener('click', function() {
    title.textContent = '변경된 제목';
});
```

### 예제 2: 리스트에 항목 추가

```html
<ul id="list"></ul>
<input type="text" id="input">
<button id="addBtn">추가</button>
```

```javascript
const addBtn = document.getElementById('addBtn');
const input = document.getElementById('input');
const list = document.getElementById('list');

addBtn.addEventListener('click', function() {
    const text = input.value;
    if (text.trim() !== '') {
        const li = document.createElement('li');
        li.textContent = text;
        list.appendChild(li);
        input.value = '';  // 입력 필드 초기화
    }
});
```

### 예제 3: 이미지 변경

```html
<img id="image" src="image1.jpg" alt="이미지">
<button id="changeBtn">이미지 변경</button>
```

```javascript
const changeBtn = document.getElementById('changeBtn');
const image = document.getElementById('image');
let currentImage = 1;

changeBtn.addEventListener('click', function() {
    currentImage = currentImage === 1 ? 2 : 1;
    image.src = `image${currentImage}.jpg`;
});
```

### 예제 4: 동적 스타일 변경

```html
<div id="box">박스</div>
<button id="colorBtn">색상 변경</button>
```

```javascript
const colorBtn = document.getElementById('colorBtn');
const box = document.getElementById('box');
const colors = ['red', 'blue', 'green', 'yellow'];
let currentIndex = 0;

colorBtn.addEventListener('click', function() {
    currentIndex = (currentIndex + 1) % colors.length;
    box.style.backgroundColor = colors[currentIndex];
});
```

---

## 7. 이벤트와 함께 사용

### 기본 이벤트 리스너

```html
<button id="btn">클릭</button>
```

```javascript
const btn = document.getElementById('btn');

btn.addEventListener('click', function() {
    alert('버튼이 클릭되었습니다!');
});
```

### 여러 요소에 이벤트 추가

```html
<button class="btn">버튼 1</button>
<button class="btn">버튼 2</button>
<button class="btn">버튼 3</button>
```

```javascript
const buttons = document.querySelectorAll('.btn');

buttons.forEach(button => {
    button.addEventListener('click', function() {
        console.log(this.textContent + ' 클릭됨');
    });
});
```

---

## 주의사항

### 1. DOM 로드 대기

```javascript
// ❌ 오류: DOM이 로드되기 전에 실행
const btn = document.getElementById('btn');

// ✅ 올바른 방법
document.addEventListener('DOMContentLoaded', function() {
    const btn = document.getElementById('btn');
    // ...
});

// 또는 스크립트를 </body> 앞에 배치
```

### 2. querySelector vs getElementById

```javascript
// getElementById: 빠름, ID만 가능
const btn1 = document.getElementById('btn');

// querySelector: 유연함, CSS 선택자 사용 가능
const btn2 = document.querySelector('#btn');
const btn3 = document.querySelector('.button');
```

### 3. innerHTML 보안

```javascript
// ❌ 위험: XSS 공격 가능
const userInput = '<script>alert("XSS")</script>';
element.innerHTML = userInput;

// ✅ 안전: textContent 사용
element.textContent = userInput;
```

---

## 연습 문제

1. **텍스트 변경**
   - 버튼을 클릭하면 제목의 텍스트가 변경되도록 하세요.

2. **리스트 추가**
   - 입력 필드에 텍스트를 입력하고 버튼을 클릭하면 리스트에 항목이 추가되도록 하세요.

3. **이미지 변경**
   - 버튼을 클릭할 때마다 이미지가 변경되도록 하세요.

4. **스타일 변경**
   - 버튼을 클릭하면 박스의 배경색이 변경되도록 하세요.

5. **요소 제거**
   - 리스트 항목을 클릭하면 해당 항목이 제거되도록 하세요.

---

## 다음 장 예고

다음 장에서는 이벤트(Event)에 대해 더 자세히 학습합니다.








