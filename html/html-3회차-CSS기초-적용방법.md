# 3회차 — CSS 기초와 적용방법

## 학습 목표
- CSS의 다양한 적용 방식을 이해하고 적절히 사용할 수 있다
- CSS 우선순위(specificity)를 이해하고 스타일 충돌을 해결할 수 있다
- 다양한 선택자를 사용하여 원하는 요소를 선택할 수 있다
- 텍스트 스타일링을 통해 가독성을 향상시킬 수 있다
- 색상, 배경, 테두리를 활용하여 시각적 효과를 만들 수 있다

---

## 1. CSS 적용 방식

CSS(Cascading Style Sheets)는 HTML 요소의 스타일을 정의하는 언어입니다. CSS를 적용하는 방법은 세 가지가 있습니다.

### 1.1 인라인 스타일 (Inline Style)

HTML 요소의 `style` 속성에 직접 CSS를 작성하는 방법입니다.

```html
<p style="color: red; font-size: 20px;">인라인 스타일 예제</p>
```

**특징:**
- 가장 높은 우선순위
- HTML과 CSS가 섞여 있어 유지보수가 어려움
- 특정 요소에만 일시적으로 적용할 때 사용
- **권장하지 않음** (가능하면 피하는 것이 좋음)

### 1.2 내부 스타일시트 (Internal Stylesheet)

HTML 문서의 `<head>` 안에 `<style>` 태그를 사용하여 CSS를 작성하는 방법입니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>내부 스타일시트 예제</title>
    <style>
        p {
            color: blue;
            font-size: 18px;
        }
        h1 {
            color: red;
        }
    </style>
</head>
<body>
    <h1>제목</h1>
    <p>문단</p>
</body>
</html>
```

**특징:**
- 해당 HTML 파일에만 적용
- 작은 프로젝트나 단일 페이지에 적합
- 여러 페이지에서 재사용 불가

### 1.3 외부 스타일시트 (External Stylesheet)

별도의 CSS 파일을 만들고 `<link>` 태그로 연결하는 방법입니다.

**HTML 파일:**
```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>외부 스타일시트 예제</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>제목</h1>
    <p>문단</p>
</body>
</html>
```

**CSS 파일 (styles.css):**
```css
p {
    color: blue;
    font-size: 18px;
}

h1 {
    color: red;
}
```

**특징:**
- **가장 권장되는 방법**
- 여러 HTML 파일에서 재사용 가능
- HTML과 CSS 분리로 유지보수 용이
- 캐싱으로 성능 향상

### 1.4 여러 스타일시트 연결

여러 개의 외부 스타일시트를 연결할 수 있습니다.

```html
<head>
    <link rel="stylesheet" href="reset.css">
    <link rel="stylesheet" href="layout.css">
    <link rel="stylesheet" href="theme.css">
</head>
```

**순서:**
- 나중에 연결된 스타일시트가 우선순위가 높음
- 일반적으로 reset.css → layout.css → theme.css 순서

### 1.5 @import 사용

CSS 파일 내에서 다른 CSS 파일을 불러올 수 있습니다.

```css
@import url('reset.css');
@import url('layout.css');

/* 나머지 CSS 코드 */
```

**주의사항:**
- `<link>` 태그보다 성능이 떨어짐
- 가능하면 `<link>` 태그 사용 권장

---

## 2. CSS 우선순위 (Specificity)

여러 CSS 규칙이 같은 요소에 적용될 때, 어떤 규칙이 우선되는지 결정하는 것이 **특이성(Specificity)**입니다.

### 2.1 우선순위 계산

특이성은 점수로 계산됩니다:

1. **인라인 스타일**: 1000점
2. **ID 선택자**: 100점
3. **클래스 선택자, 속성 선택자, 가상 클래스**: 10점
4. **태그 선택자, 가상 요소**: 1점

**예시:**
```css
/* 특이성: 1점 (태그) */
p { color: blue; }

/* 특이성: 10점 (클래스) */
.text { color: red; }

/* 특이성: 100점 (ID) */
#title { color: green; }

/* 특이성: 11점 (태그 1 + 클래스 10) */
p.text { color: purple; }

/* 특이성: 111점 (태그 1 + 클래스 10 + ID 100) */
p.text#title { color: orange; }
```

### 2.2 우선순위 규칙

1. **특이성이 높을수록 우선**
2. **특이성이 같으면 나중에 선언된 것이 우선**
3. **`!important`는 최우선** (사용 지양)

```css
p {
    color: blue;
}

p {
    color: red; /* 이것이 적용됨 (나중에 선언) */
}
```

### 2.3 !important 사용

`!important`는 모든 우선순위를 무시하고 최우선으로 적용됩니다.

```css
p {
    color: blue !important;
}

#title {
    color: red; /* !important 때문에 blue가 적용됨 */
}
```

**주의사항:**
- `!important`는 가능하면 사용하지 않는 것이 좋음
- 사용하면 나중에 스타일 수정이 어려워짐
- 정말 필요한 경우에만 사용 (예: 외부 라이브러리 스타일 덮어쓰기)

### 2.4 우선순위 예제

```html
<p id="intro" class="text">안녕하세요</p>
```

```css
/* 특이성: 1점 - 적용 안 됨 */
p { color: blue; }

/* 특이성: 10점 - 적용 안 됨 */
.text { color: red; }

/* 특이성: 100점 - 적용됨! */
#intro { color: green; }

/* 특이성: 1점 - 인라인 스타일이 최우선 */
/* <p style="color: purple;"> */
```

---

## 3. 선택자 기초

선택자는 스타일을 적용할 HTML 요소를 선택하는 방법입니다.

### 3.1 태그 선택자 (Type Selector)

HTML 태그 이름으로 선택합니다.

```css
p {
    color: blue;
}

h1 {
    font-size: 32px;
}

div {
    margin: 10px;
}
```

**특이성:** 1점

### 3.2 클래스 선택자 (Class Selector)

클래스 이름으로 선택합니다. 점(.)으로 시작합니다.

```html
<p class="highlight">강조된 텍스트</p>
<p class="highlight important">두 클래스 적용</p>
```

```css
.highlight {
    background-color: yellow;
}

.important {
    font-weight: bold;
}

/* 여러 클래스 동시 선택 */
.highlight.important {
    color: red;
}
```

**특이성:** 10점

**특징:**
- 하나의 요소에 여러 클래스 적용 가능
- 재사용성이 높음
- 가장 많이 사용되는 선택자

### 3.3 ID 선택자 (ID Selector)

ID 이름으로 선택합니다. 샵(#)으로 시작합니다.

```html
<div id="header">헤더</div>
<p id="intro">소개</p>
```

```css
#header {
    background-color: #333;
    color: white;
}

#intro {
    font-size: 18px;
}
```

**특이성:** 100점

**주의사항:**
- 하나의 HTML 문서에서 같은 ID는 한 번만 사용
- 재사용성이 낮음
- 가능하면 클래스 선택자 사용 권장

### 3.4 후손 선택자 (Descendant Selector)

특정 요소의 자손(하위) 요소를 선택합니다. 공백으로 구분합니다.

```html
<div class="container">
    <p>이것은 선택됨</p>
    <section>
        <p>이것도 선택됨</p>
    </section>
</div>
<p>이것은 선택 안 됨</p>
```

```css
.container p {
    color: blue;
}
```

**의미:** `.container` 안에 있는 모든 `p` 태그 (직접 자식이 아니어도 됨)

### 3.5 자식 선택자 (Child Selector)

특정 요소의 직접 자식만 선택합니다. `>` 기호를 사용합니다.

```html
<div class="container">
    <p>이것은 선택됨 (직접 자식)</p>
    <section>
        <p>이것은 선택 안 됨 (손자)</p>
    </section>
</div>
```

```css
.container > p {
    color: blue;
}
```

**의미:** `.container`의 직접 자식인 `p` 태그만

### 3.6 속성 선택자 (Attribute Selector)

요소의 속성으로 선택합니다.

```html
<input type="text">
<input type="email">
<a href="https://example.com">링크</a>
```

```css
/* 특정 속성이 있는 요소 */
[type] {
    border: 1px solid #ccc;
}

/* 속성 값이 정확히 일치 */
[type="text"] {
    background-color: #f0f0f0;
}

/* 속성 값이 포함 */
[href*="example"] {
    color: blue;
}

/* 속성 값이 시작 */
[href^="https"] {
    font-weight: bold;
}

/* 속성 값이 끝남 */
[href$=".com"] {
    text-decoration: underline;
}
```

**속성 선택자 종류:**
- `[attr]`: 속성이 있는 요소
- `[attr="value"]`: 속성 값이 정확히 일치
- `[attr*="value"]`: 속성 값에 포함
- `[attr^="value"]`: 속성 값이 시작
- `[attr$="value"]`: 속성 값이 끝남
- `[attr~="value"]`: 공백으로 구분된 단어 중 하나
- `[attr|="value"]`: 속성 값이 "value" 또는 "value-"로 시작

### 3.7 선택자 조합

여러 선택자를 조합하여 사용할 수 있습니다.

```css
/* 태그 + 클래스 */
p.highlight {
    color: red;
}

/* 여러 요소 선택 */
h1, h2, h3 {
    color: blue;
}

/* 복합 선택자 */
.container > p.highlight {
    font-weight: bold;
}
```

### 3.8 선택자 우선순위 요약

1. 인라인 스타일 (1000점)
2. ID 선택자 (100점)
3. 클래스 선택자, 속성 선택자 (10점)
4. 태그 선택자 (1점)

---

## 4. 텍스트 스타일링

### 4.1 폰트 속성 (font)

#### font-family
글꼴을 지정합니다.

```css
p {
    font-family: Arial, sans-serif;
}

h1 {
    font-family: "Times New Roman", serif;
}
```

**폰트 스택:**
- 여러 폰트를 나열하면 앞에서부터 사용 가능한 폰트를 사용
- 마지막에 일반 폰트 계열 지정 (serif, sans-serif, monospace, cursive, fantasy)

**웹 폰트 사용:**
```html
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
```

```css
body {
    font-family: 'Noto Sans KR', sans-serif;
}
```

#### font-size
글자 크기를 지정합니다.

```css
p {
    font-size: 16px; /* 픽셀 */
}

h1 {
    font-size: 2em; /* 부모 요소의 2배 */
}

h2 {
    font-size: 150%; /* 부모 요소의 150% */
}

.small {
    font-size: 0.875rem; /* 루트 요소 기준 */
}
```

**단위:**
- `px`: 픽셀 (고정 크기)
- `em`: 부모 요소 기준 배수
- `rem`: 루트 요소(html) 기준 배수 (권장)
- `%`: 부모 요소 기준 백분율

#### font-weight
글자 굵기를 지정합니다.

```css
p {
    font-weight: normal; /* 400 */
}

.bold {
    font-weight: bold; /* 700 */
}

.light {
    font-weight: 300;
}

.custom {
    font-weight: 600; /* 100~900 */
}
```

**값:**
- `normal` (400): 기본
- `bold` (700): 굵게
- `lighter`: 더 가늘게
- `bolder`: 더 굵게
- `100~900`: 숫자로 지정

#### font-style
글자 스타일을 지정합니다.

```css
p {
    font-style: normal;
}

.italic {
    font-style: italic; /* 기울임 */
}

.oblique {
    font-style: oblique; /* 기울임 (약간 다름) */
}
```

#### font (단축 속성)
여러 폰트 속성을 한 번에 지정합니다.

```css
p {
    font: italic bold 16px/1.5 Arial, sans-serif;
}
/* font: style weight size/line-height family */
```

**순서:** style → weight → size/line-height → family

### 4.2 line-height
줄 간격을 지정합니다.

```css
p {
    line-height: 1.5; /* 글자 크기의 1.5배 (권장) */
}

.paragraph {
    line-height: 24px; /* 고정 값 */
}

.tight {
    line-height: 1.2; /* 좁은 간격 */
}

.spacious {
    line-height: 2; /* 넓은 간격 */
}
```

**값:**
- 숫자 (권장): 글자 크기의 배수 (1.5 = 150%)
- 픽셀: 고정 값
- 백분율: 글자 크기의 백분율

**권장 값:**
- 본문: 1.5 ~ 1.8
- 제목: 1.2 ~ 1.4

### 4.3 letter-spacing
자간(글자 사이 간격)을 지정합니다.

```css
p {
    letter-spacing: normal; /* 기본값 */
}

.wide {
    letter-spacing: 2px; /* 넓은 간격 */
}

.tight {
    letter-spacing: -1px; /* 좁은 간격 */
}

.heading {
    letter-spacing: 0.1em; /* 상대적 간격 */
}
```

**값:**
- `normal`: 기본값
- 픽셀: 고정 값
- `em`: 글자 크기 기준

### 4.4 text-align
텍스트 정렬을 지정합니다.

```css
p {
    text-align: left; /* 왼쪽 정렬 (기본값) */
}

.center {
    text-align: center; /* 가운데 정렬 */
}

.right {
    text-align: right; /* 오른쪽 정렬 */
}

.justify {
    text-align: justify; /* 양쪽 정렬 */
}
```

### 4.5 text-decoration
텍스트 장식을 지정합니다.

```css
a {
    text-decoration: none; /* 밑줄 제거 */
}

.underline {
    text-decoration: underline; /* 밑줄 */
}

.line-through {
    text-decoration: line-through; /* 취소선 */
}

.overline {
    text-decoration: overline; /* 윗줄 */
}
```

### 4.6 text-transform
텍스트 대소문자를 변환합니다.

```css
.uppercase {
    text-transform: uppercase; /* 대문자 */
}

.lowercase {
    text-transform: lowercase; /* 소문자 */
}

.capitalize {
    text-transform: capitalize; /* 단어 첫 글자 대문자 */
}
```

---

## 5. 색상 (Color)

### 5.1 색상 지정 방법

#### 키워드
```css
p {
    color: red;
    background-color: blue;
}
```

#### 16진수 (HEX)
```css
p {
    color: #ff0000; /* 빨강 */
    color: #00ff00; /* 초록 */
    color: #0000ff; /* 파랑 */
    color: #333; /* #333333의 축약형 */
}
```

#### RGB
```css
p {
    color: rgb(255, 0, 0); /* 빨강 */
    color: rgb(255, 0, 0, 0.5); /* 반투명 빨강 (RGBA) */
}
```

#### HSL
```css
p {
    color: hsl(0, 100%, 50%); /* 빨강 */
    color: hsla(0, 100%, 50%, 0.5); /* 반투명 빨강 */
}
```

**HSL 설명:**
- H (Hue): 색상 (0~360)
- S (Saturation): 채도 (0~100%)
- L (Lightness): 명도 (0~100%)

### 5.2 색상 속성

```css
p {
    color: #333; /* 글자 색상 */
    background-color: #fff; /* 배경 색상 */
    border-color: #ccc; /* 테두리 색상 */
}
```

---

## 6. 배경 (Background)

### 6.1 background-color
배경 색상을 지정합니다.

```css
div {
    background-color: #f0f0f0;
}

.header {
    background-color: rgba(0, 0, 0, 0.8); /* 반투명 검정 */
}
```

### 6.2 background-image
배경 이미지를 지정합니다.

```css
.hero {
    background-image: url('image.jpg');
}

.pattern {
    background-image: url('pattern.png');
}
```

### 6.3 background-repeat
배경 이미지 반복을 지정합니다.

```css
div {
    background-repeat: repeat; /* 반복 (기본값) */
    background-repeat: no-repeat; /* 반복 안 함 */
    background-repeat: repeat-x; /* 가로만 반복 */
    background-repeat: repeat-y; /* 세로만 반복 */
}
```

### 6.4 background-position
배경 이미지 위치를 지정합니다.

```css
div {
    background-position: center; /* 가운데 */
    background-position: top left; /* 왼쪽 위 */
    background-position: 50% 50%; /* 퍼센트 */
    background-position: 10px 20px; /* 픽셀 */
}
```

### 6.5 background-size
배경 이미지 크기를 지정합니다.

```css
div {
    background-size: cover; /* 영역을 가득 채움 */
    background-size: contain; /* 이미지 전체가 보이도록 */
    background-size: 100% 100%; /* 가로 세로 100% */
    background-size: 200px 150px; /* 고정 크기 */
}
```

### 6.6 background (단축 속성)
여러 배경 속성을 한 번에 지정합니다.

```css
div {
    background: #f0f0f0 url('image.jpg') no-repeat center/cover;
}
/* background: color image repeat position/size */
```

---

## 7. 테두리 (Border)

### 7.1 border-width
테두리 두께를 지정합니다.

```css
div {
    border-width: 1px;
    border-width: thin; /* 얇게 */
    border-width: medium; /* 중간 */
    border-width: thick; /* 두껍게 */
    border-width: 2px 4px; /* 위아래 좌우 */
    border-width: 1px 2px 3px 4px; /* 위 오른쪽 아래 왼쪽 */
}
```

### 7.2 border-style
테두리 스타일을 지정합니다.

```css
div {
    border-style: solid; /* 실선 */
    border-style: dashed; /* 점선 */
    border-style: dotted; /* 점 */
    border-style: double; /* 이중선 */
    border-style: none; /* 없음 */
    border-style: groove; /* 홈 */
    border-style: ridge; /* 능선 */
    border-style: inset; /* 안으로 */
    border-style: outset; /* 밖으로 */
}
```

### 7.3 border-color
테두리 색상을 지정합니다.

```css
div {
    border-color: #333;
    border-color: red blue; /* 위아래 좌우 */
    border-color: red blue green yellow; /* 위 오른쪽 아래 왼쪽 */
}
```

### 7.4 border (단축 속성)
테두리 속성을 한 번에 지정합니다.

```css
div {
    border: 2px solid #333;
}
/* border: width style color */
```

### 7.5 개별 테두리
각 방향의 테두리를 개별적으로 지정할 수 있습니다.

```css
div {
    border-top: 2px solid red;
    border-right: 1px dashed blue;
    border-bottom: 3px dotted green;
    border-left: 1px solid #333;
}
```

### 7.6 border-radius
테두리 모서리를 둥글게 만듭니다.

```css
div {
    border-radius: 5px; /* 모든 모서리 */
    border-radius: 10px 20px; /* 왼쪽위/오른쪽아래 왼쪽아래/오른쪽위 */
    border-radius: 5px 10px 15px 20px; /* 왼쪽위 오른쪽위 오른쪽아래 왼쪽아래 */
    border-radius: 50%; /* 원형 */
}
```

---

## 8. 실습 체크리스트

### CSS 적용 방식
- [ ] 인라인, 내부, 외부 스타일시트의 차이를 이해
- [ ] 외부 스타일시트를 사용할 수 있음
- [ ] 여러 스타일시트를 연결할 수 있음

### 우선순위
- [ ] 특이성(specificity)의 개념을 이해
- [ ] 우선순위를 계산할 수 있음
- [ ] !important 사용을 피할 수 있음

### 선택자
- [ ] 태그, 클래스, ID 선택자를 사용할 수 있음
- [ ] 후손 선택자와 자식 선택자의 차이를 이해
- [ ] 속성 선택자를 사용할 수 있음
- [ ] 선택자를 조합할 수 있음

### 텍스트 스타일링
- [ ] font-family, font-size, font-weight를 사용할 수 있음
- [ ] line-height로 가독성을 향상시킬 수 있음
- [ ] letter-spacing을 조절할 수 있음
- [ ] text-align, text-decoration을 사용할 수 있음

### 색상/배경/테두리
- [ ] 다양한 색상 지정 방법을 사용할 수 있음
- [ ] 배경 색상과 이미지를 설정할 수 있음
- [ ] 테두리를 만들고 스타일을 지정할 수 있음
- [ ] border-radius로 모서리를 둥글게 만들 수 있음

---

## 다음 단계
- 박스 모델 (margin, padding, width, height)
- 레이아웃 (display, position)
- Flexbox와 Grid
- 반응형 웹 디자인


