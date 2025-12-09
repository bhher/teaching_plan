## HTML + CSS 6차시 – Flexbox 레이아웃 기초

### 0. 학습 목표

- Flexbox에서 **flex container** 와 **flex item** 의 개념을 이해한다.
- `display: flex` 를 사용해 가로/세로 방향 레이아웃을 만들 수 있다.
- `justify-content`, `align-items`, `gap` 속성을 사용해  
  **가로/세로 정렬과 간격**을 조절할 수 있다.
- Flexbox를 활용해 **상품 목록 카드 정렬 레이아웃**을 만든다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) Flexbox란?

- Flexbox(플렉스 박스)는 **1차원(가로 또는 세로 한 방향)** 레이아웃을 쉽게 만들어 주는 CSS 시스템.
- 기존 `display: block / inline-block` 만으로는  
  가로 정렬, 가운데 정렬이 번거로웠던 문제를 많이 해결해 준다.

> “한 줄에 카드 여러 개를 **균등하게 나열**하고 싶을 때”,  
> “가운데 정렬을 수평/수직으로 동시에 하고 싶을 때” Flexbox가 매우 유용하다.

---

#### 2) flex container 와 flex item

- Flexbox를 쓰기 위한 첫 단계:

```css
.list {
  display: flex;
}
```

- `display: flex;`가 적용된 요소: **flex container**
- 그 안에 들어있는 자식 요소들: **flex item**

예:

```html
<div class="product-list">   <!-- flex container -->
  <div class="product-card">...</div> <!-- flex item -->
  <div class="product-card">...</div> <!-- flex item -->
  <div class="product-card">...</div> <!-- flex item -->
</div>
```

```css
.product-list {
  display: flex; /* 이 안의 자식들은 flex item 이 된다 */
}
```

설명 포인트:
- Flexbox는 항상 “**부모 하나(container)** + 그 안의 **자식들(item)**” 구조로 생각하면 이해가 쉽다.

---

#### 3) justify-content – 가로 방향(주축) 정렬

- 기본적으로 Flexbox의 **주축(main axis)** 은 가로 방향(`row`)이다.  
  (세로 방향으로 바꾸면 반대가 되지만, 입문 단계에서는 가로 기준으로 설명)

```css
.product-list {
  display: flex;
  justify-content: center;
}
```

- `justify-content` 는 **주축 방향(기본: 가로)** 으로 아이템들을 어떻게 정렬할지 결정.

주요 값:
- `flex-start` (기본값): 왼쪽 정렬
- `center`: 가운데 정렬
- `flex-end`: 오른쪽 정렬
- `space-between`: 양 끝을 붙이고, 나머지 공간을 **사이사이에 균등 분배**
- `space-around`: 아이템 양쪽에 여백을 둠 (양 끝은 절반 간격)

시각적 설명(예: 3개 아이템):

- `flex-start`  
  `[아이템][아이템][아이템]-----------------`

- `center`  
  `--------- [아이템][아이템][아이템] ------`

- `space-between`  
  `[아이템]------[아이템]------[아이템]`

---

#### 4) align-items – 세로 방향(교차축) 정렬

- `align-items` 는 **교차축(cross axis)** 방향 정렬을 담당.  
  기본 `flex-direction: row` 일 때 **세로 방향** 정렬.

```css
.product-list {
  display: flex;
  align-items: center;
}
```

주요 값:
- `stretch` (기본값): 아이템들을 컨테이너 높이에 맞게 늘림 (조건에 따라 다르게 보일 수 있음)
- `flex-start`: 위쪽에 맞춤
- `center`: 세로 가운데 정렬
- `flex-end`: 아래쪽에 맞춤

예:
- 카드들의 높이가 서로 다를 때, `align-items: flex-start;` vs `center;` 를 비교해 보면  
  세로 기준선이 어떻게 달라지는지 확인할 수 있다.

---

#### 5) gap – 아이템 사이 간격

- Flexbox에서 아이템들 사이 간격을 줄 때, 예전에는 `margin-right` 등을 사용해야 했지만  
  이제는 `gap` 속성으로 간단하게 줄 수 있다.

```css
.product-list {
  display: flex;
  gap: 16px; /* 아이템 사이 간격 16px */
}
```

- `row-gap`, `column-gap` 으로 가로/세로 간격을 따로 지정할 수도 있지만,  
  입문 단계에서는 `gap` 하나만으로도 충분하다.

설명 포인트:
- “각 카드에 margin을 주는 대신, **부모에 gap을 주면** 더 깔끔하게 간격을 관리할 수 있다.”

---

### 2. 실습 단계 – 상품 목록 카드 정렬

#### 2.1 목표

- HTML로 **상품 카드 구조**를 만들고,
- Flexbox로 카드들을 한 줄에 나란히 정렬해 본다.
- `justify-content`, `align-items`, `gap` 값을 바꿔 보며  
  레이아웃이 어떻게 달라지는지 눈으로 확인한다.

#### 2.2 파일 구조

```txt
project/
  index.html
  style.css
```

#### 2.3 실습 흐름

1. `index.html` 에 상품 카드 여러 개를 포함하는 `.product-list` 영역을 만든다.
2. `style.css` 에 카드 모양 기본 스타일을 지정한다.
3. `.product-list` 에 `display: flex` 를 적용해 가로 정렬을 만든다.
4. `justify-content`, `align-items`, `gap` 값을 여러 가지로 바꿔 보며 레이아웃 변화를 관찰한다.

---

### 3. 코드 스켈레톤

> 완성본이 아니라, **연습용 틀**만 제공합니다.  
> 상품명, 가격, 색상, 카드 개수 등은 수업 중 또는 과제로 수정합니다.

#### 3.1 `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>Flexbox – 상품 목록</title>
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <section class="product-section">
      <h1 class="section-title">인기 상품</h1>

      <div class="product-list">
        <article class="product-card">
          <h2 class="product-name">상품 A</h2>
          <p class="product-price">₩10,000</p>
          <p class="product-desc">간단한 상품 설명이 들어갑니다.</p>
        </article>

        <article class="product-card">
          <h2 class="product-name">상품 B</h2>
          <p class="product-price">₩15,000</p>
          <p class="product-desc">간단한 상품 설명이 들어갑니다.</p>
        </article>

        <article class="product-card">
          <h2 class="product-name">상품 C</h2>
          <p class="product-price">₩20,000</p>
          <p class="product-desc">간단한 상품 설명이 들어갑니다.</p>
        </article>

        <article class="product-card">
          <h2 class="product-name">상품 D</h2>
          <p class="product-price">₩25,000</p>
          <p class="product-desc">간단한 상품 설명이 들어갑니다.</p>
        </article>
      </div>
    </section>
  </body>
</html>
```

#### 3.2 `style.css`

```css
body {
  margin: 0;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
    sans-serif;
  background-color: #f5f5f5;
}

.product-section {
  max-width: 1000px;
  margin: 40px auto;
  padding: 0 16px;
}

.section-title {
  margin-bottom: 24px;
  font-size: 24px;
  text-align: center;
}

/* Flex container */
.product-list {
  display: flex; /* flex container 선언 */

  /* 아래 값들은 수업 중에 바꿔 보면서 실습 */
  justify-content: flex-start; /* center, space-between 등으로 실험 */
  align-items: stretch; /* center, flex-start 등으로 실험 */
  gap: 16px; /* 카드 사이 간격 */

  /* 많아졌을 때 줄바꿈 허용 여부 등은 추후 flex-wrap 에서 다룰 수 있음 */
}

/* Flex item – 상품 카드 */
.product-card {
  flex: 1 1 200px; /* (선택) flex-grow, flex-shrink, flex-basis 를 간단히 활용 */

  background-color: #ffffff;
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);

  display: flex;
  flex-direction: column;
  gap: 8px;
}

.product-name {
  margin: 0;
  font-size: 18px;
}

.product-price {
  margin: 0;
  font-weight: bold;
  color: #e53935;
}

.product-desc {
  margin: 0;
  font-size: 14px;
  color: #555555;
}
```

> 수업 중 실험 아이디어:
> - `justify-content` 를 `center`, `space-between`, `space-around` 로 바꿔 보며  
>   카드들이 어떻게 분포되는지 비교
> - `align-items` 를 `flex-start`, `center`, `stretch` 로 바꿔  
>   카드 높이가 다를 때 세로 기준선 변화 관찰
> - `gap` 값을 0, 8px, 32px 등으로 바꿔 아이템 사이 간격을 직관적으로 이해

---

### 4. 과제

1. **상품 정보 채우기**
   - 실제로 자신이 좋아하는 상품(책, 게임, 음식 등)을 예로 들어  
     상품명, 가격, 설명을 채워 넣는다.

2. **레이아웃 패턴 실험**
   - `justify-content` 와 `align-items` 조합을 여러 가지로 바꿔 보고,  
     “쇼핑몰에서 자주 보는 상품 나열 방식”과 비슷한 구성을 찾아본다.

3. **반응형 느낌 주기 (심화)**
   - 브라우저 너비를 줄였다 키워 보면서,  
     카드가 어떻게 줄어드는지 확인하고 스크린샷이나 메모로 기록한다.
   - 선택: `flex-wrap: wrap;` 를 `.product-list` 에 추가해,  
     줄바꿈이 허용되었을 때 레이아웃이 어떻게 달라지는지 실험해 본다.



