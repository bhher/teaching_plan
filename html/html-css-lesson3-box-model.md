## HTML + CSS 3차시 – 박스 모델(Box Model)과 카드 레이아웃

### 0. 학습 목표

- CSS **박스 모델(Box Model)** 의 4가지 구성 요소: `content`, `padding`, `border`, `margin` 을 설명할 수 있다.
- `box-sizing` 속성의 의미와 `content-box` vs `border-box` 차이를 이해한다.
- 요소의 **실제 표시 크기(총 너비/높이)** 가 어떻게 계산되는지 예시로 설명할 수 있다.
- 박스 모델을 활용해 **여러 개의 카드 레이아웃**을 만든다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 왜 박스 모델이 중요할까?

- 웹 페이지의 거의 모든 요소는 “**박스**”로 표현된다.
  - 버튼도 박스, 카드도 박스, 입력창도 박스.
- “왜 생각한 것보다 더 크게/작게 보이지?” → 대부분 **박스 모델과 여백** 문제.
- 레이아웃이 깨질 때 가장 먼저 확인해야 할 개념이 박스 모델이다.

---

#### 2) 박스 모델 4요소 – content / padding / border / margin

글로 표현한 구조:

```txt
┌───────────────────────────────┐  ← margin (이웃 박스와의 거리)
│   ┌───────────────────────┐   │  ← border (테두리)
│   │   ┌───────────────┐   │   │  ← padding (안쪽 여백)
│   │   │   content     │   │   │  ← content (실제 내용)
│   │   └───────────────┘   │   │
│   └───────────────────────┘   │
└───────────────────────────────┘
```

- **content**
  - 글자, 이미지 등 **실제 내용이 들어가는 영역**
  - `width`, `height` 로 크기를 지정하면 **기본적으로 content 영역 크기**를 말한다.

- **padding**
  - content와 border 사이의 **안쪽 여백**
  - 내용이 박스 테두리에 너무 붙어 보이지 않도록 띄워주는 역할

- **border**
  - 박스의 테두리
  - 두께, 스타일, 색상 지정 가능  
    예: `border: 1px solid #ccc;`

- **margin**
  - 박스 바깥쪽 여백 → **이웃 박스들과의 거리**
  - 카드들 사이 간격, 섹션 간 간격을 줄 때 자주 사용

---

#### 3) 크기 계산 방식 – 총 너비, 총 높이

기본 `box-sizing: content-box` 일 때:

- 총 너비(width 방향)

\[
총\ 너비 = margin-left + border-left + padding-left + content-width + padding-right + border-right + margin-right
\]

예:

```css
.box {
  width: 200px;       /* content 영역 너비 */
  padding: 10px;      /* 좌우 각각 10px */
  border: 2px solid #000; /* 좌우 각각 2px */
  margin: 20px;       /* 좌우 각각 20px */
}
```

- content 너비: 200px
- padding: 좌/우 10px → 20px
- border: 좌/우 2px → 4px
- margin: 좌/우 20px → 40px

따라서:

\[
총\ 너비 = 40(margin) + 4(border) + 20(padding) + 200(content) = 264px
\]

학생들에게는 “**겉에서 겹겹이 둘러싸고 있는 것들을 전부 더한 값이 실제 차지하는 크기**” 라고 설명해도 좋다.

---

#### 4) `box-sizing` 개념

- `box-sizing` 은 **width/height가 어디까지를 포함하는지**를 결정하는 속성.

1. 기본값: `content-box`
   - `width`/`height` = **content 영역만**의 크기
   - padding, border는 나중에 **밖으로 더해짐**
   - 계산하면 “어? 왜 생각보다 커지지?” 하는 경험이 자주 생긴다.

2. 자주 쓰는 값: `border-box`

```css
* {
  box-sizing: border-box;
}
```

- `width`/`height` 값 안에 **content + padding + border** 가 모두 포함
- 즉, `width: 300px` 이라면, padding과 border를 포함해도 **전체 박스 너비가 300px을 넘지 않음**
- 실무에서 레이아웃 깨짐을 줄이기 위해 거의 항상 **초기 설정으로 사용하는 패턴**

간단 비교 예시:

```css
.box1 {
  box-sizing: content-box; /* 기본값 */
  width: 200px;
  padding: 20px;
  border: 2px solid #000;
}

.box2 {
  box-sizing: border-box;
  width: 200px;
  padding: 20px;
  border: 2px solid #000;
}
```

- `.box1` 은 실제로 200px보다 더 넓어짐 (padding + border가 더해짐).
- `.box2` 는 padding과 border를 포함해도 **총 너비가 200px로 유지**된다.

> 입문자에게는  
> “`border-box` 를 쓰면 **생각한 그대로의 크기**가 나와서 조금 더 편하다”  
> 라고 설명해 주면 이해가 쉽다.

---

### 2. 실습 단계 – 카드 레이아웃 만들기

#### 2.1 목표

- 박스 모델을 활용해 **여러 개의 카드가 일정 간격으로 나란히 배치된 레이아웃**을 만든다.
- 각 카드에 `padding`, `border`, `margin` 을 직접 조정해 보며 크기 변화를 눈으로 확인한다.

#### 2.2 파일 구조

```txt
project/
  index.html
  style.css
```

#### 2.3 실습 흐름

1. `index.html` 에 카드 3개 정도의 구조를 만든다.
2. `style.css` 에 `box-sizing: border-box;` 초기 설정을 추가한다.
3. `.card`에 `padding`, `border`, `margin`, `width` 를 적용해 박스 모델을 체험한다.
4. `.card-list` 컨테이너에 간단한 레이아웃(가로/세로 배치)을 준다.  
   (예: `display: flex; gap: 16px;` 정도, flex 설명은 깊게 안 들어가도 됨)

---

### 3. 코드 스켈레톤

> 완성본이 아니라, **연습용 틀**만 제공합니다.  
> 색상, 정확한 크기, 세부 디자인은 수업 중 또는 과제로 채워 넣습니다.

#### 3.1 `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>박스 모델 – 카드 레이아웃</title>
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <h1>박스 모델 연습 – 카드 레이아웃</h1>

    <div class="card-list">
      <div class="card">
        <h2 class="card-title">카드 1</h2>
        <p class="card-content">
          카드 1의 내용입니다. padding, border, margin을 바꿔 보세요.
        </p>
      </div>

      <div class="card">
        <h2 class="card-title">카드 2</h2>
        <p class="card-content">
          카드 2의 내용입니다. 값들을 비교해 보고 차이를 느껴보세요.
        </p>
      </div>

      <div class="card">
        <h2 class="card-title">카드 3</h2>
        <p class="card-content">
          카드 3의 내용입니다. margin을 조정해 간격을 바꿔 보세요.
        </p>
      </div>
    </div>
  </body>
</html>
```

#### 3.2 `style.css`

```css
/* 박스 모델을 다루기 편하게 하는 기본 설정 */
*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
    sans-serif;
  background-color: #f5f5f5;
  margin: 0;
  padding: 20px;
}

h1 {
  text-align: center;
  margin-bottom: 24px;
}

/* 카드들을 감싸는 컨테이너 */
.card-list {
  max-width: 900px;
  margin: 0 auto;

  /* 카드들을 가로로 나란히 배치 (필요 시 수업 중 설명) */
  display: flex; /* 또는 grid 등으로 바꿔보는 것도 가능 */
  gap: 16px; /* 카드 사이 간격 */
}

/* 개별 카드 박스 */
.card {
  /* 박스 모델 관련 속성들을 실험해 볼 핵심 영역 */
  width: 100%;
  padding: 16px;
  border: 2px solid #ddd;
  margin: 0; /* 여기 값을 바꾸며 카드 간격 변화를 실험 */

  background-color: #ffffff;
  border-radius: 8px;

  /* 총 크기 확인을 위해 임시 배경색/테두리 색을 학생들이 바꿔 보게 해도 좋음 */
}

.card-title {
  margin-top: 0;
  margin-bottom: 8px;
  font-size: 18px;
}

.card-content {
  margin: 0;
  font-size: 14px;
  line-height: 1.5;
}
```

> 수업 중에 할 수 있는 추가 실험 예시(말로 안내):
> - `.card` 의 `padding` 을 16px → 40px 으로 바꾸면, 내용과 테두리 사이 간격이 넓어지는 것 확인
> - `border` 두께를 2px → 10px 으로 바꿔 총 크기가 어떻게 느껴지는지 보기
> - `margin-right` 만 따로 지정해, 카드 사이 간격을 한쪽 방향으로만 벌려 보기

---

### 4. 과제

1. **카드 스타일 변경**
   - 각 카드에 서로 다른 `padding`, `border`, `margin` 값을 적용해 보고  
     “어떤 값 조합이 가장 보기 좋은지”를 본인 의견과 함께 기록해 보기.

2. **크기 계산 적어 보기**
   - `.card` 에서 `width`, `padding`, `border`, `margin` 값을 임의로 정해 놓고,
   - 종이에 “content + padding + border + margin” 을 더해서 **총 너비**를 계산해 본다.
   - 브라우저 개발자 도구(F12)를 켜서 실제 박스 모델 표시와 비교해 보기.

3. **두 가지 box-sizing 비교 (심화)**
   - `.card` 에 대해
     - 버전 A: `box-sizing: content-box;`
     - 버전 B: `box-sizing: border-box;`
   - 같은 `width`, `padding`, `border` 값을 주고, 두 버전이 **어떻게 다르게 보이는지** 직접 확인한 후  
     “실무에서는 왜 border-box 를 많이 쓰는지” 자신의 말로 정리해 본다.



