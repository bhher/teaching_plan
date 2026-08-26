# 11장 DOM 조작 — 정답

문제: [11장_DOM_조작-문제.md](./11장_DOM_조작-문제.md)

---

# Part 1. DOM 개요 · 요소 선택 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 1 | ② | DOM = HTML 문서를 객체 트리로 표현 |
| 2 | ② | ID 선택 → `getElementById('btn')` |
| 3 | ③ | 여러 개 → `querySelectorAll('.item')` |
| 4 | ③ | `querySelector`는 **첫 번째** 하나만 |
| 5 | ② | `HTMLCollection` 등 (배열과 유사, forEach는 ES6+에서 NodeList만) |
| 6 | `querySelector` | CSS 선택자로 첫 요소 1개 |
| 7 | ② | `NodeList.forEach`로 각각 리스너 추가 |
| 8 | ② | `querySelector`는 `#id`, `.class` 등 사용 가능 |

---

# Part 2. 내용 변경 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 9 | ② | 텍스트만 → `textContent` (XSS 위험 적음) |
| 10 | ② | `innerHTML`은 HTML **태그로 파싱** |
| 11 | ② | 악성 `<script>` 삽입 가능 → XSS |
| 12 | ② | `textContent` 설정 후 `"변경됨"` |

---

# Part 3. 속성 · classList · style 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 13 | ④ | `setAttribute('src','b.jpg')` 또는 `img.src = 'b.jpg'` |
| 14 | ④ | `classList.add('a','b')` — **여러 클래스 한 번에 추가 가능** |
| 15 | ② | 하이픈 → camelCase: `backgroundColor` |
| 16 | ② | `contains` → true면 클래스 **있음** |

---

# Part 4. 요소 생성 · 추가 · 삭제 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 17 | ① | `document.createElement('li')` |
| 18 | ① | `appendChild` = 부모의 **마지막 자식**으로 추가 |
| 19 | ① | `parent.removeChild(child)` |
| 20 | ① | ES6+ `element.remove()` |

---

# Part 5. 이벤트 · DOM 로드 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 21 | ② | `addEventListener('click', fn)` |
| 22 | ② | HTML 파싱 전이면 요소 없음 → `null` |
| 23 | ② | `DOMContentLoaded` 또는 `</body>` 앞 script |
| 24 | ① | `button.textContent` 또는 핸들러 내부 `this.textContent` |

---

# Part 6. 코드 결과 · 빈칸 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 25 | ② | 클릭 후 `"변경된 제목"` |
| 26 | `createElement` / `appendChild` | 생성 → 내용 → 부모에 추가 |
| 27 | ③ | `querySelector`는 조회만 (선택) |

---

# Part 7. 코드 작성 정답 (28~32)

## 28번. 텍스트 변경

```html
<h1 id="title">제목</h1>
<button id="btn">변경</button>
```

```javascript
const btn = document.getElementById('btn');
const title = document.getElementById('title');

btn.addEventListener('click', function() {
    title.textContent = '안녕 DOM';
});
```

---

## 29번. 리스트 항목 추가

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
        input.value = '';
    }
});
```

---

## 30번. 이미지 변경

```html
<img id="image" src="pic1.jpg" alt="이미지">
<button id="changeBtn">이미지 변경</button>
```

```javascript
const changeBtn = document.getElementById('changeBtn');
const image = document.getElementById('image');
let current = 1;

changeBtn.addEventListener('click', function() {
    current = current === 1 ? 2 : 1;
    image.src = `pic${current}.jpg`;
});
```

---

## 31번. 배경색 순환

```html
<div id="box">박스</div>
<button id="colorBtn">색상 변경</button>
```

```javascript
const colorBtn = document.getElementById('colorBtn');
const box = document.getElementById('box');
const colors = ['red', 'blue', 'green', 'yellow'];
let index = 0;

colorBtn.addEventListener('click', function() {
    index = (index + 1) % colors.length;
    box.style.backgroundColor = colors[index];
});
```

---

## 32번. 클릭 시 li 제거

```html
<ul id="list">
    <li>항목 1</li>
    <li>항목 2</li>
    <li>항목 3</li>
</ul>
```

```javascript
const items = document.querySelectorAll('#list li');

items.forEach(function(item) {
    item.addEventListener('click', function() {
        this.remove();
        // 또는 item.remove();
    });
});
```

**동적으로 추가된 li까지 처리하려면 (심화)**

```javascript
document.getElementById('list').addEventListener('click', function(e) {
    if (e.target.tagName === 'LI') {
        e.target.remove();
    }
});
```

---

## 참고 교안

- [11장_DOM_조작.md](./11장_DOM_조작.md)
- [12장_이벤트.md](./12장_이벤트.md)
