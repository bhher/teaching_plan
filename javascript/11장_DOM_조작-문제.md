# 11장 DOM 조작 — 문제

[11장_DOM_조작.md](./11장_DOM_조작.md) 교안을 바탕으로 한 문제입니다.

정답: [11장_DOM_조작-정답.md](./11장_DOM_조작-정답.md)

---

# Part 1. DOM 개요 · 요소 선택

## 1번

**DOM(Document Object Model)** 에 대한 설명으로 올바른 것은?

① HTML을 이미지로 변환한 것이다  
② HTML 문서를 객체로 표현한 것이다  
③ CSS 파일을 관리하는 모델이다  
④ 서버와 통신하는 프로토콜이다

---

## 2번

ID가 `btn`인 버튼을 선택하는 코드는?

① `document.querySelector('.btn')`  
② `document.getElementById('btn')`  
③ `document.getElementsByTagName('btn')`  
④ `document.getElementsByClassName('btn')`

---

## 3번

클래스가 `item`인 **모든** 요소를 선택할 때 **권장**하는 방법은?

① `document.getElementById('item')`  
② `document.querySelector('.item')`  
③ `document.querySelectorAll('.item')`  
④ `document.getElementsById('item')`

---

## 4번

다음 HTML에서 `querySelector('p')` 가 선택하는 것은?

```html
<div class="box">
    <p>첫 번째</p>
    <p>두 번째</p>
</div>
```

① 두 `<p>` 모두  
② `<div class="box">`  
③ 첫 번째 `<p>` 하나  
④ `<p>`가 없으므로 `null`

---

## 5번

`getElementsByClassName('item')` 이 반환하는 것은?

① 하나의 요소  
② `NodeList` 또는 `HTMLCollection` (배열과 유사)  
③ 문자열  
④ `boolean`

---

## 6번 (빈칸)

CSS 선택자를 사용해 **첫 번째** 요소 하나만 선택하는 메서드는 `document.________()` 이다.

---

## 7번

다음 코드로 여러 `.btn`에 이벤트를 붙일 때 올바른 것은?

```javascript
const buttons = document.querySelectorAll('.btn');
```

① `buttons`는 배열이므로 `buttons[0].click()` 만 가능하다  
② `buttons.forEach(...)` 로 각 버튼에 리스너를 추가할 수 있다  
③ `querySelectorAll`은 ID만 선택할 수 있다  
④ `buttons`는 항상 `null`이다

---

## 8번

`getElementById` 와 `querySelector('#id')` 비교로 **옳은** 설명은?

① `getElementById`는 CSS 선택자를 쓸 수 있다  
② `querySelector`는 `#`, `.` 등 CSS 선택자를 쓸 수 있다  
③ 둘 다 태그 이름만 선택한다  
④ `querySelector`는 여러 요소를 반환한다

---

# Part 2. 내용 변경

## 9번

요소의 **텍스트만** 안전하게 바꿀 때 사용하는 속성은?

① `innerHTML`  
② `textContent`  
③ `outerHTML`  
④ `value` (모든 태그에 항상 존재)

---

## 10번

다음 코드 실행 후 `<div id="box">` 안의 HTML은?

```javascript
const box = document.getElementById('box');
box.innerHTML = '<h1>제목</h1><p>내용</p>';
```

① 텍스트 `"<h1>제목</h1><p>내용</p>"` 그대로 출력  
② `<h1>제목</h1>` 과 `<p>내용</p>` 가 **실제 HTML 요소**로 들어감  
③ `box`가 삭제됨  
④ 아무 변화 없음

---

## 11번

`innerHTML`에 사용자 입력을 그대로 넣으면 생길 수 있는 문제는?

① 페이지 로딩이 느려진다  
② XSS(스크립트 삽입) 공격 위험  
③ CSS가 적용되지 않는다  
④ DOM이 두 개 생성된다

---

## 12번

다음 코드의 결과는?

```javascript
const p = document.getElementById('text');
p.textContent = '변경됨';
console.log(p.textContent);
```

(`text` 요소에 원래 `"원본"` 이 있었다고 가정)

① `"원본"`  
② `"변경됨"`  
③ `undefined`  
④ `null`

---

# Part 3. 속성 · classList · style

## 13번

`<img id="img" src="a.jpg">` 에서 `src`를 `b.jpg`로 바꾸는 코드로 **둘 다** 맞는 것은?

① `img.getAttribute('src', 'b.jpg')`  
② `img.setAttribute('src', 'b.jpg')`  
③ `img.src = 'b.jpg'`  
④ ② 와 ③

---

## 14번

`classList` 사용법으로 **틀린** 것은?

① `box.classList.add('active')` — 클래스 추가  
② `box.classList.remove('active')` — 클래스 제거  
③ `box.classList.toggle('active')` — 있으면 제거, 없으면 추가  
④ `box.classList.add('a', 'b')` — `add`는 인자를 **한 개만** 넣을 수 있다

---

## 15번

다음 JavaScript에서 CSS `background-color`를 바꿀 때 올바른 속성명은?

```javascript
box.style.________ = 'blue';
```

① `background-color`  
② `backgroundColor`  
③ `BackgroundColor`  
④ `bgColor` (항상 표준)

---

## 16번

`box.classList.contains('active')` 가 `true`이면?

① `active` 클래스가 **없다**  
② `active` 클래스가 **있다**  
③ `box`가 DOM에서 제거되었다  
④ `box`는 `<input>` 요소이다

---

# Part 4. 요소 생성 · 추가 · 삭제

## 17번

새 `<li>` 요소를 만드는 코드는?

① `document.createElement('li')`  
② `document.newElement('li')`  
③ `document.appendChild('li')`  
④ `document.querySelector('li')`

---

## 18번

`container.appendChild(newP)` 의 의미는?

① `newP`를 `container` **안(자식)** 에 추가한다  
② `container`를 `newP` 안에 넣는다  
③ `newP`를 복사만 한다  
④ `container`를 삭제한다

---

## 19번

자식 요소를 부모에서 제거하는 메서드는?

① `parent.removeChild(child)`  
② `parent.deleteChild(child)`  
③ `child.appendChild(parent)`  
④ `document.remove('child')`

---

## 20번

ES6+에서 요소 **자신**을 DOM에서 제거하는 방법은?

① `element.remove()`  
② `element.delete()`  
③ `document.removeElement(element)`  
④ `element.removeChild()`

---

# Part 5. 이벤트 · DOM 로드

## 21번

버튼 클릭 시 함수를 실행하도록 연결하는 올바른 코드는?

① `btn.onClick = addEventListener('click', fn)`  
② `btn.addEventListener('click', fn)`  
③ `btn.addEvent('click', fn)`  
④ `btn.listen('click', fn)`

---

## 22번

`<head>` 안에서 바로 `getElementById('btn')` 을 실행하면 `null`이 나올 수 있는 이유는?

① ID가 중복되어서  
② **DOM이 아직 로드되기 전**이라 요소가 없어서  
③ JavaScript가 비활성화되어서  
④ `getElementById`는 항상 `null`을 반환해서

---

## 23번

DOM 로드 완료 후에 스크립트를 실행하는 올바른 방법은?

① `window.onload = function() { ... }` 만 가능  
② `document.addEventListener('DOMContentLoaded', function() { ... })`  
③ `<script>`를 `<head>` 최상단에 두기  
④ `alert()` 후에 실행하기

---

## 24번

`querySelectorAll('.btn')`로 선택한 각 버튼에서 `this.textContent`를 쓰려면?

```javascript
buttons.forEach(function(button) {
    button.addEventListener('click', function() {
        console.log(________);
    });
});
```

① `button.textContent` 또는 이벤트 핸들러 안에서 `this.textContent`  
② `textContent` 만 (변수 없이)  
③ `event.textContent`  
④ `document.textContent`

---

# Part 6. 코드 결과 · 빈칸

## 25번

다음 코드 실행 후 `#title`의 텍스트는?

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
// 사용자가 버튼을 1번 클릭했다고 가정
```

① `"제목"`  
② `"변경된 제목"`  
③ `undefined`  
④ 빈 문자열

---

## 26번 (빈칸)

리스트에 `<li>`를 추가할 때, 순서는  
① `document.________('li')` 로 요소 생성 →  
② `li.textContent = ...` →  
③ `list.________(li)` 로 부모에 붙인다.

---

## 27번

다음 중 **원본 DOM을 바꾸지 않고** 선택만 하는 것은?

① `textContent = '...'`  
② `classList.add('on')`  
③ `querySelector('#box')`  
④ `appendChild(li)`

---

# Part 7. 코드 작성 문제 (28~32)

## 28번

버튼 `#btn`을 클릭하면 `#title`의 텍스트가 `"안녕 DOM"`으로 바뀌도록 JavaScript를 작성하세요.

---

## 29번

`#input`에 입력한 값을 `#addBtn` 클릭 시 `#list`(`<ul>`)에 `<li>`로 추가하세요.  
빈 문자열은 추가하지 마세요. 추가 후 입력창은 비우세요.

---

## 30번

`#changeBtn` 클릭 시 `#image`의 `src`가 `pic1.jpg` ↔ `pic2.jpg` 로 번갈아 바뀌도록 작성하세요.

---

## 31번

`#colorBtn` 클릭할 때마다 `#box`의 배경색이 `red` → `blue` → `green` → `yellow` → `red` … 순으로 바뀌도록 작성하세요.  
(배열과 `%` 연산 또는 `classList` 중 아무 방식)

---

## 32번

`#list` 안의 각 `<li>`를 클릭하면 **그 항목만** DOM에서 제거되도록 작성하세요.  
(`querySelectorAll` + `forEach` + `addEventListener` 사용)

---

## 참고 교안

- [11장_DOM_조작.md](./11장_DOM_조작.md)
- [12장_이벤트.md](./12장_이벤트.md)
