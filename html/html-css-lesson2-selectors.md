## HTML + CSS 2차시 – 선택자(Selector) 핵심

### 0. 학습 목표

- CSS **선택자(selector)** 가 무엇인지 설명할 수 있다.
- 태그 선택자, 클래스 선택자, 아이디 선택자의 차이를 이해하고 사용할 수 있다.
- 선택자 **우선순위 개념(specificity)** 을 간단한 예제로 이해한다.
- `class`와 `id`를 활용해 간단한 **카드(Card) UI**를 만든다.

---

### 1. 이론 – 선택자 기본 (슬라이드 흐름)

#### 1) 선택자(selector)란?

- CSS에서 “**어떤 HTML 요소에 스타일을 적용할지** 고르는 문법”

```css
선택자 {
  속성: 값;
}
```

예:

```css
p {
  color: blue;
}
```

- 위 코드는 **모든 `<p>` 태그의 글자 색을 파란색**으로 만든다.

---

### 2. 태그 선택자, 클래스 선택자, 아이디 선택자

#### 2.1 태그 선택자 (Element Selector)

- **태그 이름**으로 선택

```css
p {
  font-size: 16px;
}

h1 {
  color: darkblue;
}
```

- 특징:
  - 페이지 안의 **해당 태그 전부**에 스타일 적용
  - 너무 많이 사용하면 “모든 p가 다 바뀌어서” 세부 조정이 어려울 수 있음

---

#### 2.2 클래스 선택자 (Class Selector)

- HTML의 `class` 속성을 기준으로 선택
- CSS에서 **`.`(점)** 으로 시작

```html
<div class="card">...</div>
<p class="highlight">중요한 문장</p>
```

```css
.card {
  border: 1px solid #ddd;
  padding: 16px;
}

.highlight {
  background-color: yellow;
}
```

- 특징:
  - **여러 요소에 같은 클래스**를 붙이면, 한 번에 동일한 스타일 적용
  - 재사용성 가장 좋음 → 실무에서 가장 많이 사용

---

#### 2.3 아이디 선택자 (ID Selector)

- HTML의 `id` 속성을 기준으로 선택
- CSS에서 **`#`(샾)** 으로 시작

```html
<h1 id="main-title">사이트 제목</h1>
```

```css
#main-title {
  font-size: 32px;
  color: teal;
}
```

- 특징:
  - 문서 안에서 **고유한 요소(1개)** 에 스타일 적용
  - 같은 `id`를 여러 요소에 쓰지 않는 것이 원칙
  - 우선순위가 높음 (클래스/태그보다 강하다)

> 실무 팁:  
> 스타일링에는 주로 **클래스 선택자**를 사용하고,  
> `id`는 JS로 특정 요소를 찾거나, 페이지 내 앵커(점프) 용도로만 쓰는 경우가 많다.

---

### 3. 선택자 우선순위 개념 (Specificity)

#### 3.1 왜 필요할까?

- 같은 요소에 **여러 스타일 규칙이 동시에 적용**될 수 있다.
  - 예: `p { color: blue; }` 와 `.highlight { color: red; }` 가 같은 `<p>` 에 적용되는 경우
- 이때, 어떤 규칙이 이길지 결정하는 것이 **우선순위**.

#### 3.2 대략적인 순서 (점수 개념)

1. `!important` (가장 강함 – 가급적 사용 자제)
2. **아이디 선택자** (`#id`) – 점수 100
3. **클래스 / 속성 / 의사 클래스** (`.class`) – 점수 10
4. **태그 / 의사 요소** (`p`, `h1`) – 점수 1

간단 예:

```css
p {
  color: blue;      /* 점수 1 */
}

.highlight {
  color: red;       /* 점수 10 */
}

#main-text {
  color: green;     /* 점수 100 */
}
```

```html
<p id="main-text" class="highlight">텍스트</p>
```

- 이 경우, 같은 요소에 세 가지 규칙이 모두 적용되지만,  
  **id 선택자(`#main-text`)가 이겨서 글자색은 초록색**이 된다.

> 입문 단계에서는 “**id > class > 태그** 순으로 강하다” 정도만 기억해도 충분.

---

### 4. 실습 – class와 id를 활용한 카드 UI 만들기

#### 4.1 목표

- HTML로 카드 구조를 만들고,
- CSS에서 **태그/클래스/아이디 선택자**를 모두 사용해 스타일링 해본다.

#### 4.2 파일 구조 예시

```txt
project/
  index.html
  style.css
```

---

### 5. 코드 스켈레톤

> 아래 코드는 **틀(skeleton)** 만 제공하며,  
> 색상/크기/폰트 등은 수업 중 또는 과제로 학생들이 채우게 합니다.

#### 5.1 `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>Selector 실습 – 카드 UI</title>
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <h1 id="page-title">카드 UI 연습</h1>

    <div class="card-list">
      <div class="card">
        <h2 class="card-title">카드 제목 1</h2>
        <p class="card-description">카드 내용 1입니다.</p>
        <button class="card-button">자세히 보기</button>
      </div>

      <div class="card featured">
        <h2 class="card-title">카드 제목 2</h2>
        <p class="card-description">카드 내용 2입니다.</p>
        <button class="card-button">자세히 보기</button>
      </div>

      <div class="card">
        <h2 class="card-title">카드 제목 3</h2>
        <p class="card-description">카드 내용 3입니다.</p>
        <button class="card-button">자세히 보기</button>
      </div>
    </div>
  </body>
</html>
```

#### 5.2 `style.css`

```css
/* 태그 선택자 예시 */
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
}

h1 {
  text-align: center;
}

/* 아이디 선택자 예시 */
#page-title {
  /* 페이지 제목에만 적용할 스타일 */
}

/* 클래스 선택자 예시 */
.card-list {
  /* 카드들을 배치하는 컨테이너 스타일 (예: 가로/세로 정렬, gap 등) */
}

.card {
  /* 각 카드 박스 스타일 (border, padding, background-color 등) */
}

.card-title {
  /* 카드 제목 스타일 */
}

.card-description {
  /* 카드 내용 스타일 */
}

.card-button {
  /* 버튼 스타일 */
}

/* 추가 클래스: 특별히 강조할 카드 */
.featured {
  /* featured 카드에만 다른 배경색이나 테두리 적용 */
}
```

---

### 6. 실습 진행 가이드 (강사용)

1. **1단계 – HTML 구조 확인**
   - `index.html`의 구조를 함께 보면서,
   - 어떤 요소에 어떤 `class`/`id`가 붙어 있는지 학생들과 함께 짚어본다.

2. **2단계 – CSS 선택자 채우기**
   - 태그 선택자: `body`, `h1`
   - 클래스 선택자: `.card-list`, `.card`, `.card-title`, `.card-description`, `.card-button`, `.featured`
   - 아이디 선택자: `#page-title`

3. **3단계 – 스타일 실험**
   - `.card`에 배경색/테두리/여백 적용
   - `.featured` 카드만 색상을 다르게
   - `.card-button`에 hover 효과(선택): 마우스 올렸을 때 배경색 바꾸기 등

---

### 7. 과제

1. **카드 내용 변경**
   - 각 카드에 실제로 자신이 관심 있는 주제(예: “HTML 공부”, “React 배우기”, “취미 생활”)를 제목/내용으로 작성한다.

2. **선택자 활용 연습**
   - `id`를 하나 더 만들어서, 특정 카드에만 별도의 스타일을 적용해 본다.
     - 예: 세 번째 카드 제목에만 다른 색상 적용
   - `.card-list .card-title` 처럼 **후손 선택자**를 사용해, 카드 안에 있는 제목만 스타일 지정해 보기 (심화).

3. **우선순위 실험**
   - 같은 요소에 대해 태그/클래스/아이디 선택자로 서로 다른 색을 지정해 보고,  
     **어떤 색이 실제로 적용되는지 확인**해 본다.  
   - 예:  
     ```css
     h1 { color: blue; }
     .title { color: red; }
     #page-title { color: green; }
     ```
     → `id`가 가장 강하다는 것을 눈으로 확인.



