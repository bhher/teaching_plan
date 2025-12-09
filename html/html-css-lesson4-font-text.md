## HTML + CSS 4차시 – 폰트 & 텍스트 스타일

### 0. 학습 목표

- HTML 텍스트 태그와 CSS 텍스트/폰트 관련 속성의 기본 역할을 이해한다.
- `font-size`, `font-weight`, `line-height`, `text-align`, `color` 를 사용해 글자를 읽기 좋게 꾸밀 수 있다.
- **web-safe font** 의 개념을 설명할 수 있다.
- 위 속성들을 활용해 **자기소개 섹션**을 디자인한다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) HTML 텍스트 태그 간단 복습

- 제목: `<h1>` ~ `<h6>`
- 문단: `<p>`
- 강조: `<strong>`, `<em>`

역할 정리:
- **`<h1>`~`<h6>`**: 페이지/섹션의 제목, 계층 구조를 표현
- **`<p>`**: 일반 문장/단락
- **`<strong>`**: 중요한 부분 → 보통 굵게 표시
- **`<em>`**: 강조, 억양 → 보통 기울임꼴

> 시맨틱(의미) 구조는 HTML이 담당하고,  
> “어떻게 보일지(색, 크기, 정렬)”는 CSS가 담당합니다.

---

#### 2) 글자 크기 – `font-size`

```css
p {
  font-size: 16px;
}

h1 {
  font-size: 32px;
}
```

- 단위 예시:
  - `px`: 픽셀, 고정 크기 (입문 단계에서 가장 많이 사용)
  - `rem`, `em`: 상대 크기 (추후 반응형/접근성에서 더 자세히 다룸)
- 기본 브라우저 글자 크기는 보통 **16px** 정도.

설명 포인트:
- 너무 작은 글씨는 읽기 힘들고, 너무 큰 글씨는 화면을 답답하게 만든다.
- 본문은 14~18px 정도, 제목은 그보다 크게 사용하는 패턴이 많다.

---

#### 3) 굵기 – `font-weight`

```css
.title {
  font-weight: 700; /* 굵게 */
}

.normal-text {
  font-weight: 400; /* 보통 */
}
```

- 숫자 값: 보통 400(일반), 700(굵게) 정도만 기억하면 충분
- 키워드: `normal`, `bold` 도 자주 사용

주의:
- 사용 중인 폰트가 해당 굵기를 지원해야 제대로 보인다.

---

#### 4) 줄 간격 – `line-height`

```css
p {
  line-height: 1.5;
}
```

- **줄과 줄 사이의 간격**을 조절하는 속성
- 단위 없이 1.4 ~ 1.8 사이 값을 많이 사용 (글이 답답하지 않고 읽기 편한 범위)

예:
- `line-height: 1.0;` → 줄 사이 간격이 거의 없음 (답답)
- `line-height: 1.6;` → 일반적인 본문에 많이 쓰는 값

학생들에게:
- “줄 간격을 넓혀주면 글이 **숨을 쉰다**고 생각하면 된다”라고 비유해 설명.

---

#### 5) 정렬과 색 – `text-align`, `color`

```css
.intro-title {
  text-align: center;
}

.intro-text {
  color: #333333;
}
```

- `text-align`
  - `left` (기본, 왼쪽 정렬)
  - `center` (가운데 정렬)
  - `right` (오른쪽 정렬)

- `color`
  - 글자 색상
  - 색상 표기: 색 이름(`red`), HEX(`#333`), RGB 등
  - 본문은 너무 연하거나 너무 진한 색 대신 **약간 회색 톤(#333 ~ #555)** 을 쓰면 눈이 편하다.

---

#### 6) web-safe font 개념

- **web-safe font** 란?
  - 대부분의 운영체제(Windows, macOS 등)에 기본으로 설치되어 있어  
    “어디서 봐도 거의 비슷하게 보이는 글꼴” 을 의미한다.

예시:
- `Arial`, `Helvetica`, `Tahoma`, `Verdana`, `Times New Roman`, `Georgia`, `Courier New` 등

CSS에서 폰트 지정 예:

```css
body {
  font-family: 'Segoe UI', Arial, sans-serif;
}
```

- 앞에서부터 순서대로 적용:
  1. `'Segoe UI'` 가 설치되어 있으면 그것 사용
  2. 없으면 `Arial` 사용
  3. 그것도 없으면 `sans-serif` 계열 기본 글꼴 사용

설명 포인트:
- 모든 사용자가 같은 폰트를 가지고 있는 것은 아니므로,  
  여러 개를 “백업 순서”로 적어 두는 것이 좋다.
- 나중에 **웹 폰트(Google Fonts)** 등을 배울 때, web-safe font와의 차이를 비교해 볼 수 있다.

---

### 2. 실습 단계 – 자기소개 섹션 스타일링

#### 2.1 목표

- 간단한 **자기소개 HTML 구조**를 만들고,
- 텍스트 관련 CSS 속성들을 이용해 **읽기 편하고 보기 좋은 섹션**으로 꾸민다.

#### 2.2 파일 구조

```txt
project/
  index.html
  style.css
```

#### 2.3 실습 흐름

1. `index.html` 에 자기소개 영역(`.intro-section`)을 만든다.
2. `style.css` 에 기본 폰트와 배경색을 지정한다.
3. 제목, 부제목, 본문 글자에 대해:
   - `font-size`, `font-weight`, `line-height`, `text-align`, `color` 를 단계적으로 적용해 본다.
4. web-safe font 조합을 하나 골라서 `font-family` 에 적용해 본다.

---

### 3. 코드 스켈레톤

> 완성본이 아니라, **연습용 틀**만 제공합니다.  
> 텍스트 내용, 색상, 크기 등은 수업 중 또는 과제로 학생들이 채우도록 합니다.

#### 3.1 `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>폰트 & 텍스트 스타일 – 자기소개</title>
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <section class="intro-section">
      <h1 class="intro-title">안녕하세요, 홍길동입니다.</h1>
      <h2 class="intro-subtitle">프론트엔드 개발을 꿈꾸는 입문자</h2>
      <p class="intro-text">
        자기소개 본문을 여기에 작성합니다. 어떤 것을 좋아하고, 무엇을 배우고 싶은지
        간단하게 적어보세요. 줄 간격과 글자 크기를 조절해 읽기 좋게 만들어 봅시다.
      </p>
      <p class="intro-text">
        취미나 관심사, 앞으로 만들고 싶은 웹 페이지에 대해서도 적어볼 수 있습니다.
      </p>
    </section>
  </body>
</html>
```

#### 3.2 `style.css`

```css
/* 전체 기본 스타일 */
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
    Arial, sans-serif; /* web-safe font 조합 예시 */
  background-color: #f4f4f4;
  margin: 0;
  padding: 40px 16px;
}

.intro-section {
  max-width: 700px;
  margin: 0 auto;
  padding: 24px;
  background-color: #ffffff;
  border-radius: 8px;
  /* 박스 모델 관련 속성은 3차시 복습 용도로 함께 설명 가능 */
}

.intro-title {
  /* 제목: 크고 굵게, 가운데 정렬 */
  font-size: 28px;
  font-weight: 700;
  text-align: center;
  margin-bottom: 8px;
}

.intro-subtitle {
  /* 부제목: 약간 더 작은 크기, 색은 조금 연하게 */
  font-size: 18px;
  font-weight: 500;
  text-align: center;
  color: #666666;
  margin-bottom: 16px;
}

.intro-text {
  /* 본문: 읽기 좋은 크기와 줄 간격 */
  font-size: 15px;
  line-height: 1.7;
  color: #333333;
  margin-bottom: 12px;
}
```

> 수업 중 실험 아이디어:
> - `font-size` 값을 키우거나 줄여보고, 어느 정도가 읽기 좋은지 토론
> - `line-height` 를 1.0 / 1.3 / 1.7 / 2.0 등으로 바꿔보며 느낌 비교
> - `text-align` 을 `left` vs `center` 로 바꾸며 어떤 상황에 어떤 정렬이 어울리는지 이야기하기

---

### 4. 과제

1. **자기소개 내용 채우기**
   - 자신의 실제 이름, 관심 분야, 목표 등을 작성해 **실제 자기소개 섹션**을 완성한다.
   - 문단을 최소 2개 이상 작성해, 줄 간격의 효과를 체험해 본다.

2. **텍스트 스타일 변경하기**
   - 제목/부제목/본문에 서로 다른 `font-size`, `font-weight`, `color` 값을 적용해  
     “어디를 가장 강조하고 싶은지”에 따라 스타일을 조정해 본다.

3. **web-safe font 실험 (심화)**
   - `font-family` 에 다른 web-safe font 조합을 넣어 보고,
   - 브라우저/운영체제에 따라 글꼴 모양이 어떻게 달라지는지 비교해 본다.
   - 마음에 드는 조합을 1~2개 골라 노트에 기록해 두기.



