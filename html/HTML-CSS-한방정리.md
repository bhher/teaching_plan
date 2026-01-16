# 🌐 HTML / CSS 한방 정리 (입문 → 기초 완성)

## 📌 한 줄 요약

**HTML은 뼈대, CSS는 옷이다. 둘이 합쳐져야 웹 페이지가 된다.**

---

## 1️⃣ HTML이란?

### 기본 개념

**HTML (HyperText Markup Language)**은 웹 페이지의 **구조(뼈대)**를 만드는 언어입니다.

### HTML의 역할

- 제목
- 문단
- 이미지
- 버튼
- 입력창

👉 **"무엇이 있는지"를 정의**

### HTML의 특징

- 마크업 언어 (태그로 구조 표현)
- 웹 브라우저가 해석하여 화면에 표시
- 확장자: `.html`

---

## 2️⃣ HTML 기본 구조 (무조건 암기 ⭐)

### 기본 템플릿

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>나의 첫 웹페이지</title>
</head>
<body>
    내용이 들어가는 곳
</body>
</html>
```

### 각 태그 역할

| 태그 | 의미 | 설명 |
|------|------|------|
| `<!DOCTYPE>` | HTML5 선언 | 문서 타입 선언 |
| `<html>` | 문서 전체 | HTML 문서의 최상위 요소 |
| `<head>` | 설정 영역 | 메타데이터, 스타일, 스크립트 등 |
| `<body>` | 화면에 보이는 영역 | 실제 화면에 표시되는 내용 |

### 필수 메타 태그

```html
<head>
    <meta charset="UTF-8">  <!-- 한글 인코딩 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  <!-- 반응형 -->
    <title>페이지 제목</title>  <!-- 브라우저 탭 제목 -->
</head>
```

---

## 3️⃣ HTML 기본 태그들

### 🔹 글자 관련 태그

```html
<!-- 제목 태그 (h1 ~ h6) -->
<h1>제목 1 (가장 큼)</h1>
<h2>제목 2</h2>
<h3>제목 3</h3>
<h4>제목 4</h4>
<h5>제목 5</h5>
<h6>제목 6 (가장 작음)</h6>

<!-- 문단 -->
<p>문단입니다. 여러 줄로 나눌 수 있습니다.</p>

<!-- 짧은 글 (인라인) -->
<span>짧은 글</span>

<!-- 줄바꿈 -->
<br>

<!-- 강조 -->
<strong>굵은 글</strong>
<em>기울임</em>
```

### 🔹 목록 태그

```html
<!-- 순서 없는 목록 (ul: unordered list) -->
<ul>
    <li>사과</li>
    <li>바나나</li>
    <li>오렌지</li>
</ul>

<!-- 순서 있는 목록 (ol: ordered list) -->
<ol>
    <li>첫번째</li>
    <li>두번째</li>
    <li>세번째</li>
</ol>
```

### 🔹 링크 & 이미지

```html
<!-- 링크 (a: anchor) -->
<a href="https://naver.com">네이버</a>
<a href="page2.html">다른 페이지</a>
<a href="#section1">페이지 내 이동</a>

<!-- 이미지 -->
<img src="cat.jpg" alt="고양이">
<img src="images/photo.png" alt="사진 설명">

<!-- 속성 설명 -->
<!-- src: 이미지 경로 -->
<!-- alt: 이미지 설명 (접근성, 이미지 로드 실패 시 표시) -->
```

### 🔹 입력 폼 (아주 중요 ⭐⭐⭐)

```html
<form>
    <!-- 텍스트 입력 -->
    <input type="text" placeholder="이름">
    
    <!-- 비밀번호 입력 -->
    <input type="password" placeholder="비밀번호">
    
    <!-- 이메일 입력 -->
    <input type="email" placeholder="이메일">
    
    <!-- 숫자 입력 -->
    <input type="number" placeholder="나이">
    
    <!-- 체크박스 -->
    <input type="checkbox" id="agree">
    <label for="agree">동의합니다</label>
    
    <!-- 라디오 버튼 -->
    <input type="radio" name="gender" value="male" id="male">
    <label for="male">남성</label>
    
    <input type="radio" name="gender" value="female" id="female">
    <label for="female">여성</label>
    
    <!-- 선택 박스 -->
    <select>
        <option>선택하세요</option>
        <option>옵션 1</option>
        <option>옵션 2</option>
    </select>
    
    <!-- 텍스트 영역 -->
    <textarea rows="5" cols="30" placeholder="메시지를 입력하세요"></textarea>
    
    <!-- 버튼 -->
    <button type="submit">로그인</button>
    <button type="button">취소</button>
</form>
```

### 🔹 기타 중요 태그

```html
<!-- 구분선 -->
<hr>

<!-- 인용문 -->
<blockquote>인용문 내용</blockquote>

<!-- 코드 -->
<code>코드 내용</code>

<!-- 미리 정의된 형식 유지 -->
<pre>
    여러 줄
    그대로
    표시
</pre>

<!-- 의미 없는 컨테이너 -->
<div>블록 컨테이너</div>
<span>인라인 컨테이너</span>
```

---

## 4️⃣ 블록 vs 인라인 (시험 단골 ⭐⭐)

### 🔹 블록 요소 (Block Element)

**특징:**
- 한 줄 전체 차지
- 줄바꿈 발생
- width, height 설정 가능
- margin, padding 상하좌우 모두 적용

**대표 태그:**
- `<div>`, `<p>`, `<h1>~<h6>`, `<ul>`, `<ol>`, `<li>`
- `<form>`, `<table>`, `<section>`, `<article>`

**예시:**
```html
<div>블록 요소 1</div>
<div>블록 요소 2</div>
<!-- 결과: 세로로 배치됨 -->
```

### 🔹 인라인 요소 (Inline Element)

**특징:**
- 내용만큼만 차지
- 줄바꿈 ❌ (옆으로 배치)
- width, height 설정 불가
- margin, padding 좌우만 적용

**대표 태그:**
- `<span>`, `<a>`, `<img>`, `<strong>`, `<em>`
- `<input>`, `<button>`, `<label>`

**예시:**
```html
<span>인라인 요소 1</span>
<span>인라인 요소 2</span>
<!-- 결과: 가로로 배치됨 -->
```

### 🔹 인라인-블록 요소 (Inline-Block)

**특징:**
- 인라인처럼 옆으로 배치
- 블록처럼 width, height 설정 가능

**사용법:**
```css
.element {
    display: inline-block;
    width: 100px;
    height: 100px;
}
```

### 비교표

| 구분 | 블록 요소 | 인라인 요소 | 인라인-블록 |
|------|----------|------------|------------|
| **줄바꿈** | 발생 | 없음 | 없음 |
| **너비/높이** | 설정 가능 | 설정 불가 | 설정 가능 |
| **여백** | 상하좌우 | 좌우만 | 상하좌우 |
| **예시** | `<div>`, `<p>` | `<span>`, `<a>` | `display: inline-block` |

---

## 5️⃣ CSS란?

### 기본 개념

**CSS (Cascading Style Sheets)**는 HTML을 **꾸미는 언어**입니다.

### CSS의 역할

- 색상
- 크기
- 위치
- 레이아웃
- 반응형

👉 **"어떻게 보일지"를 정의**

### CSS의 특징

- 스타일 시트 언어
- HTML과 분리하여 작성 가능
- 확장자: `.css`

---

## 6️⃣ CSS 적용 방법 3가지

### ❌ 1. 인라인 스타일 (비추천)

```html
<p style="color: red; font-size: 20px;">글자</p>
```

**단점:**
- HTML과 CSS가 섞여 있음
- 재사용 불가
- 유지보수 어려움

### ⚠️ 2. 내부 스타일 (간단한 페이지용)

```html
<head>
    <style>
        p {
            color: red;
            font-size: 20px;
        }
    </style>
</head>
```

**장점:**
- HTML 파일 하나로 관리
- 간단한 페이지에 적합

**단점:**
- 여러 페이지에서 재사용 어려움

### ✅ 3. 외부 CSS (실무 필수 ⭐⭐⭐)

```html
<head>
    <link rel="stylesheet" href="style.css">
</head>
```

**style.css 파일:**
```css
p {
    color: red;
    font-size: 20px;
}
```

**장점:**
- HTML과 CSS 분리
- 여러 페이지에서 재사용 가능
- 유지보수 용이
- 캐싱 효율

---

## 7️⃣ CSS 기본 문법

### 문법 구조

```css
선택자 {
    속성: 값;
    속성: 값;
}
```

### 예시

```css
p {
    color: red;
    font-size: 20px;
    font-weight: bold;
}
```

### 주석

```css
/* 이것은 주석입니다 */
p {
    color: blue; /* 인라인 주석도 가능 */
}
```

---

## 8️⃣ CSS 선택자 (⭐⭐⭐ 핵심)

### 🔹 태그 선택자 (Element Selector)

```css
p {
    color: blue;
}

h1 {
    font-size: 32px;
}
```

**의미:** 모든 `<p>` 태그에 적용

### 🔹 클래스 선택자 (Class Selector) - 가장 중요 ⭐

```css
.box {
    width: 100px;
    height: 100px;
    background-color: red;
}

.title {
    font-size: 24px;
    color: blue;
}
```

**HTML:**
```html
<div class="box"></div>
<h1 class="title">제목</h1>
```

**특징:**
- 여러 요소에 동일한 스타일 적용 가능
- 재사용성 높음
- 가장 많이 사용

### 🔹 아이디 선택자 (ID Selector)

```css
#title {
    color: red;
    font-size: 32px;
}

#header {
    background-color: gray;
}
```

**HTML:**
```html
<h1 id="title">제목</h1>
<div id="header">헤더</div>
```

**특징:**
- 하나의 요소에만 적용 (고유성)
- 클래스보다 우선순위 높음
- JavaScript에서 많이 사용

### 🔹 자식 선택자

```css
/* 직계 자식만 선택 */
.parent > .child {
    color: red;
}

/* 모든 하위 요소 선택 */
.parent .descendant {
    color: blue;
}
```

### 🔹 그룹 선택자

```css
h1, h2, h3 {
    color: blue;
    font-weight: bold;
}
```

### 🔹 가상 클래스 선택자

```css
/* 링크 상태 */
a:link { color: blue; }      /* 방문 전 */
a:visited { color: purple; } /* 방문 후 */
a:hover { color: red; }      /* 마우스 오버 */
a:active { color: orange; }  /* 클릭 시 */

/* 첫 번째 자식 */
li:first-child {
    color: red;
}

/* 마지막 자식 */
li:last-child {
    color: blue;
}
```

### 선택자 우선순위

1. **인라인 스타일** (`style="..."`)
2. **ID 선택자** (`#id`)
3. **클래스 선택자** (`.class`)
4. **태그 선택자** (`p`)

---

## 9️⃣ CSS 기본 속성들

### 🔹 글자 관련

```css
/* 색상 */
color: red;
color: #ff0000;
color: rgb(255, 0, 0);

/* 크기 */
font-size: 16px;
font-size: 1.5em;
font-size: 100%;

/* 굵기 */
font-weight: normal;
font-weight: bold;
font-weight: 700;

/* 글꼴 */
font-family: "맑은 고딕", sans-serif;
font-family: Arial, Helvetica, sans-serif;

/* 정렬 */
text-align: left;
text-align: center;
text-align: right;
text-align: justify;

/* 줄 간격 */
line-height: 1.5;
line-height: 24px;

/* 장식 */
text-decoration: none;      /* 없음 */
text-decoration: underline; /* 밑줄 */
text-decoration: line-through; /* 취소선 */
```

### 🔹 크기

```css
/* 너비 */
width: 200px;
width: 50%;
width: 100vw;  /* 뷰포트 너비 */

/* 높이 */
height: 100px;
height: 50%;
height: 100vh;  /* 뷰포트 높이 */

/* 최소/최대 크기 */
min-width: 200px;
max-width: 1200px;
min-height: 100px;
max-height: 500px;
```

### 🔹 여백 (⭐⭐⭐ 핵심)

#### Margin (바깥 여백)

```css
/* 개별 설정 */
margin-top: 10px;
margin-right: 20px;
margin-bottom: 30px;
margin-left: 40px;

/* 축약형 */
margin: 10px;              /* 상하좌우 모두 10px */
margin: 10px 20px;         /* 상하 10px, 좌우 20px */
margin: 10px 20px 30px;    /* 상 10px, 좌우 20px, 하 30px */
margin: 10px 20px 30px 40px; /* 상 우 하 좌 (시계방향) */

/* 가운데 정렬 */
margin: 0 auto;  /* 좌우 자동 (블록 요소 가운데 정렬) */
```

#### Padding (안쪽 여백)

```css
/* 개별 설정 */
padding-top: 10px;
padding-right: 20px;
padding-bottom: 30px;
padding-left: 40px;

/* 축약형 */
padding: 10px;              /* 상하좌우 모두 10px */
padding: 10px 20px;         /* 상하 10px, 좌우 20px */
padding: 10px 20px 30px;    /* 상 10px, 좌우 20px, 하 30px */
padding: 10px 20px 30px 40px; /* 상 우 하 좌 (시계방향) */
```

**차이점:**
- **Margin**: 요소와 요소 사이의 간격 (바깥쪽)
- **Padding**: 요소 내부의 여백 (안쪽)

### 🔹 배경

```css
/* 배경색 */
background-color: red;
background-color: #ff0000;
background-color: rgba(255, 0, 0, 0.5);  /* 투명도 */

/* 배경 이미지 */
background-image: url("image.jpg");
background-size: cover;      /* 영역 전체 덮기 */
background-size: contain;    /* 비율 유지하며 맞추기 */
background-position: center; /* 위치 */
background-repeat: no-repeat; /* 반복 없음 */

/* 축약형 */
background: #ff0000 url("image.jpg") center/cover no-repeat;
```

### 🔹 테두리

```css
/* 개별 설정 */
border-width: 1px;
border-style: solid;
border-color: black;

/* 축약형 */
border: 1px solid black;
border: 2px dashed red;
border: 3px dotted blue;

/* 둥근 모서리 */
border-radius: 5px;
border-radius: 50%;  /* 원형 */
border-radius: 10px 20px;  /* 좌상 우하 */
```

---

## 🔟 박스 모델 (시험 단골 ⭐⭐)

### 구조

```
┌─────────────────────────────────┐
│         Margin (바깥 여백)        │
│  ┌───────────────────────────┐  │
│  │      Border (테두리)       │  │
│  │  ┌─────────────────────┐  │  │
│  │  │   Padding (안쪽 여백) │  │  │
│  │  │  ┌───────────────┐  │  │  │
│  │  │  │   Content     │  │  │  │
│  │  │  │   (콘텐츠)     │  │  │  │
│  │  │  └───────────────┘  │  │  │
│  │  └─────────────────────┘  │  │
│  └───────────────────────────┘  │
└─────────────────────────────────┘
```

### 예시

```css
div {
    width: 200px;        /* 콘텐츠 너비 */
    height: 100px;       /* 콘텐츠 높이 */
    padding: 20px;       /* 안쪽 여백 */
    border: 1px solid black;  /* 테두리 */
    margin: 10px;        /* 바깥 여백 */
}
```

### Box-sizing

```css
/* 기본값: content-box */
/* width = 콘텐츠만 (padding, border 제외) */
.box1 {
    box-sizing: content-box;
    width: 200px;
    padding: 20px;
    border: 1px solid;
    /* 실제 너비 = 200 + 20*2 + 1*2 = 242px */
}

/* 권장: border-box */
/* width = 콘텐츠 + padding + border 포함 */
.box2 {
    box-sizing: border-box;
    width: 200px;
    padding: 20px;
    border: 1px solid;
    /* 실제 너비 = 200px (내부 계산) */
}

/* 전역 설정 (권장) */
* {
    box-sizing: border-box;
}
```

---

## 1️⃣1️⃣ display 속성

### 주요 값

```css
/* 블록 요소 */
display: block;
/* 특징: 한 줄 전체 차지, 줄바꿈 발생 */

/* 인라인 요소 */
display: inline;
/* 특징: 내용만큼만 차지, 줄바꿈 없음 */

/* 인라인-블록 */
display: inline-block;
/* 특징: 인라인처럼 옆으로 배치, 블록처럼 크기 설정 가능 */

/* 숨김 */
display: none;
/* 특징: 완전히 제거 (공간도 없음) */

/* Flexbox */
display: flex;
/* 특징: 유연한 레이아웃 */

/* Grid */
display: grid;
/* 특징: 2차원 그리드 레이아웃 */
```

### 예시

```css
/* 인라인 요소를 블록으로 변경 */
span {
    display: block;
}

/* 블록 요소를 인라인으로 변경 */
div {
    display: inline;
}

/* 요소 숨기기 */
.hidden {
    display: none;
}
```

---

## 1️⃣2️⃣ position (위치 제어)

### Position 값

```css
/* 기본값: 문서 흐름에 따라 배치 */
position: static;

/* 상대 위치: 원래 위치 기준 */
position: relative;
top: 10px;
left: 20px;

/* 절대 위치: 부모 요소 기준 (relative인 부모) */
position: absolute;
top: 50px;
right: 30px;

/* 고정 위치: 화면 기준 (스크롤해도 고정) */
position: fixed;
top: 0;
left: 0;
```

### 예시

```css
/* 상대 위치 */
.box {
    position: relative;
    top: 20px;    /* 원래 위치에서 아래로 20px */
    left: 30px;   /* 원래 위치에서 오른쪽으로 30px */
}

/* 절대 위치 */
.child {
    position: absolute;
    top: 0;
    right: 0;
    /* 부모가 relative면 부모 기준, 아니면 body 기준 */
}

/* 고정 위치 (헤더, 네비게이션) */
.header {
    position: fixed;
    top: 0;
    width: 100%;
    background: white;
    z-index: 1000;  /* 다른 요소 위에 표시 */
}
```

### Z-index (겹침 순서)

```css
.box1 {
    position: absolute;
    z-index: 1;  /* 뒤에 */
}

.box2 {
    position: absolute;
    z-index: 2;  /* 앞에 */
}
```

---

## 1️⃣3️⃣ Flexbox (요즘 레이아웃 핵심 ⭐⭐⭐)

### 기본 구조

```css
.container {
    display: flex;
    justify-content: center;  /* 가로 정렬 */
    align-items: center;      /* 세로 정렬 */
}
```

### 주요 속성

#### 컨테이너 속성

```css
.container {
    display: flex;
    
    /* 가로 정렬 */
    justify-content: flex-start;   /* 왼쪽 */
    justify-content: center;       /* 가운데 */
    justify-content: flex-end;     /* 오른쪽 */
    justify-content: space-between; /* 양쪽 끝 */
    justify-content: space-around; /* 균등 분배 */
    justify-content: space-evenly;  /* 완전 균등 */
    
    /* 세로 정렬 */
    align-items: flex-start;   /* 위 */
    align-items: center;       /* 가운데 */
    align-items: flex-end;     /* 아래 */
    align-items: stretch;      /* 늘리기 */
    
    /* 방향 */
    flex-direction: row;        /* 가로 (기본) */
    flex-direction: column;     /* 세로 */
    flex-direction: row-reverse; /* 가로 역순 */
    flex-direction: column-reverse; /* 세로 역순 */
    
    /* 줄바꿈 */
    flex-wrap: nowrap;  /* 줄바꿈 없음 (기본) */
    flex-wrap: wrap;    /* 줄바꿈 허용 */
    
    /* 간격 */
    gap: 20px;  /* 아이템 사이 간격 */
}
```

#### 아이템 속성

```css
.item {
    /* 크기 조절 */
    flex-grow: 1;      /* 남은 공간 차지 */
    flex-shrink: 1;    /* 공간 부족 시 축소 */
    flex-basis: 200px; /* 기본 크기 */
    
    /* 축약형 */
    flex: 1;  /* flex-grow: 1, shrink: 1, basis: 0 */
    
    /* 정렬 */
    align-self: center;  /* 개별 아이템 정렬 */
}
```

### 실전 예제

```css
/* 카드 레이아웃 */
.card-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
}

.card {
    flex: 1 1 300px;  /* 최소 300px, 가능한 만큼 늘어남 */
    max-width: 400px;
}

/* 헤더 레이아웃 */
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
}

/* 중앙 정렬 */
.center {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
```

---

## 1️⃣4️⃣ 반응형 기초 (모바일 대응)

### 미디어 쿼리

```css
/* 기본 스타일 (모바일 우선) */
body {
    font-size: 14px;
    padding: 10px;
}

/* 태블릿 (768px 이상) */
@media (min-width: 768px) {
    body {
        font-size: 16px;
        padding: 20px;
    }
}

/* 데스크톱 (1024px 이상) */
@media (min-width: 1024px) {
    body {
        font-size: 18px;
        padding: 30px;
    }
}

/* 최대 너비 제한 */
@media (max-width: 768px) {
    .container {
        width: 100%;
    }
}
```

### Viewport 설정

```html
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
```

### 반응형 예제

```css
/* 모바일 우선 디자인 */
.container {
    width: 100%;
    padding: 10px;
}

/* 태블릿 */
@media (min-width: 768px) {
    .container {
        max-width: 750px;
        margin: 0 auto;
        padding: 20px;
    }
}

/* 데스크톱 */
@media (min-width: 1024px) {
    .container {
        max-width: 1200px;
    }
}
```

---

## 1️⃣5️⃣ HTML + CSS 예제 (완성형)

### 카드 컴포넌트

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>카드 예제</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: '맑은 고딕', sans-serif;
            background-color: #f5f5f5;
            padding: 50px;
        }
        
        .card {
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 20px;
            text-align: center;
            background-color: white;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            margin: 0 auto;
        }
        
        .card h2 {
            color: #333;
            margin-bottom: 10px;
        }
        
        .card p {
            color: #666;
            margin-bottom: 20px;
        }
        
        .card button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .card button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="card">
        <h2>홍길동</h2>
        <p>웹 개발자</p>
        <button>연락하기</button>
    </div>
</body>
</html>
```

### 그리드 레이아웃

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>그리드 레이아웃</title>
    <style>
        .container {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            padding: 20px;
        }
        
        .item {
            background-color: #e0e0e0;
            padding: 20px;
            text-align: center;
            border-radius: 5px;
        }
        
        @media (max-width: 768px) {
            .container {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="item">아이템 1</div>
        <div class="item">아이템 2</div>
        <div class="item">아이템 3</div>
    </div>
</body>
</html>
```

---

## 1️⃣6️⃣ 초보자가 꼭 기억할 5가지

### 1️⃣ HTML = 구조
- 웹 페이지의 뼈대를 만듦
- 태그로 내용을 구조화

### 2️⃣ CSS = 디자인
- HTML을 꾸미는 역할
- 색상, 크기, 위치, 레이아웃 담당

### 3️⃣ class 선택자 가장 많이 씀
- 재사용성 높음
- 여러 요소에 동일한 스타일 적용 가능

### 4️⃣ margin / padding 구분
- **Margin**: 요소와 요소 사이 (바깥)
- **Padding**: 요소 내부 여백 (안쪽)

### 5️⃣ 레이아웃은 flex부터
- 현대적인 레이아웃 방식
- 간단하고 강력함
- 반응형에 유리

---

## 1️⃣7️⃣ 이 다음에 뭘 하면 좋을까?

### 추천 학습 순서

1. **HTML 폼(form) 완전 정복**
   - 다양한 input 타입
   - 폼 검증
   - 폼 제출 처리

2. **CSS Flex 완전 정복**
   - 복잡한 레이아웃 만들기
   - 중앙 정렬 마스터
   - 반응형 Flexbox

3. **CSS Grid 배우기**
   - 2차원 레이아웃
   - 복잡한 그리드 구조
   - Grid vs Flexbox

4. **반응형 레이아웃**
   - 미디어 쿼리 심화
   - 모바일 우선 디자인
   - 반응형 이미지

5. **JavaScript 기초**
   - DOM 조작
   - 이벤트 처리
   - 동적 웹 페이지

6. **프레임워크/라이브러리**
   - React / Vue (프론트엔드)
   - Bootstrap / Tailwind CSS (CSS 프레임워크)
   - Spring / Node.js (백엔드 연동)

---

## 🔚 핵심 요약

### 한 줄 요약
**HTML은 뼈대, CSS는 옷이다. 둘이 합쳐져야 웹 페이지가 된다.**

### 핵심 개념 정리

| 구분 | HTML | CSS |
|------|------|-----|
| **역할** | 구조 (뼈대) | 디자인 (옷) |
| **목적** | "무엇이 있는지" | "어떻게 보일지" |
| **확장자** | `.html` | `.css` |
| **태그/속성** | 태그로 구조화 | 선택자로 스타일링 |

### 필수 암기 사항

1. **HTML 기본 구조** - `<!DOCTYPE>`, `<html>`, `<head>`, `<body>`
2. **블록 vs 인라인** - 차이점과 특징
3. **CSS 선택자** - 태그, 클래스, ID
4. **박스 모델** - margin, padding, border, content
5. **Flexbox** - `display: flex`, `justify-content`, `align-items`

### 실무 팁

- ✅ **외부 CSS 파일 사용** (유지보수 용이)
- ✅ **클래스 선택자 우선 사용** (재사용성)
- ✅ **모바일 우선 디자인** (반응형)
- ✅ **Flexbox로 레이아웃** (현대적 방식)
- ✅ **의미 있는 태그 사용** (시맨틱 HTML)

---

## 📚 추가 학습 자료

- [HTML 기본 구조 가이드](./html-1회차-기초구조-시맨틱마크업.md)
- [CSS 기초 적용 방법](./html-3회차-CSS기초-적용방법.md)
- [Flexbox 실전](./html-5회차-Flexbox실전.md)
- [CSS Grid 실전](./html-6회차-CSSGrid실전.md)
- [반응형 웹 디자인](./html-7회차-반응형웹-모바일우선.md)

---

**🎯 학습 완료 체크리스트**

- [ ] HTML 기본 구조를 만들 수 있다
- [ ] 주요 HTML 태그를 사용할 수 있다
- [ ] 블록과 인라인 요소를 구분할 수 있다
- [ ] CSS를 3가지 방법으로 적용할 수 있다
- [ ] CSS 선택자를 사용할 수 있다
- [ ] margin과 padding을 구분할 수 있다
- [ ] 박스 모델을 이해했다
- [ ] Flexbox로 레이아웃을 만들 수 있다
- [ ] 반응형 미디어 쿼리를 사용할 수 있다
- [ ] 완성된 웹 페이지를 만들 수 있다





