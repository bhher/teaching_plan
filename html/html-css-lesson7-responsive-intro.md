## HTML + CSS 7차시 – 반응형 웹 기초

### 0. 학습 목표

- **반응형 웹(Responsive Web)** 의 개념을 이해한다.
- `viewport` 메타 태그의 역할을 설명할 수 있다.
- `@media` **미디어 쿼리(media query)** 로 화면 크기에 따라 스타일을 변경할 수 있다.
- **모바일 퍼스트(mobile-first)** 설계 개념을 이해하고,  
  간단한 레이아웃을 화면 크기에 따라 다르게 보이도록 만든다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 반응형 웹이란?

- PC, 태블릿, 스마트폰 등 **화면 크기가 다른 기기에서 모두 보기 좋은 웹 페이지**.
- 한 가지 HTML 문서에, CSS로 화면 크기에 따라 **레이아웃/폰트/배치**를 조정한다.
- 예:
  - PC: 가로로 카드 4개
  - 태블릿: 카드 2개
  - 모바일: 카드 1개

> 예전에는 PC용 사이트, 모바일용 사이트를 따로 만들기도 했지만,  
> 요즘은 **반응형 웹** 패턴이 거의 표준이다.

---

#### 2) viewport 메타 태그

- 모바일 브라우저는 기본적으로 **PC 화면을 축소해서 보여주는 방식**을 사용한다.
- 반응형 웹을 제대로 보여주려면,  
  “**화면 너비를 기기 화면 너비에 맞춰 줘**” 라고 알려줘야 한다.

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
```

- `width=device-width`
  - 가로 너비를 기기 실제 화면 너비에 맞춘다.
- `initial-scale=1.0`
  - 처음 로딩할 때 확대/축소 비율을 1.0(기본)으로 맞춤.

설명 포인트:
- 이 메타 태그가 없으면, 모바일에서 글자가 너무 작게 보이거나  
  반응형 CSS가 기대한 대로 동작하지 않을 수 있다.

---

#### 3) media query – 화면 크기에 따라 스타일 바꾸기

- **미디어 쿼리(media query)** 는  
  “조건(예: 화면 너비가 600px 이하일 때)”에 따라 CSS를 다르게 적용하는 문법.

기본 형태:

```css
@media (조건) {
  /* 이 안의 스타일은 조건을 만족할 때만 적용 */
}
```

예: 화면 너비가 768px 이하일 때 글자 크기 줄이기

```css
p {
  font-size: 18px;
}

@media (max-width: 768px) {
  p {
    font-size: 16px;
  }
}
```

- `max-width: 768px`
  - 화면 너비가 **768px 이하(작은 화면)** 일 때 적용.

자주 쓰는 조건 예시:
- `@media (max-width: 768px)` : 태블릿/작은 화면 기준
- `@media (max-width: 480px)` : 모바일 기준

---

#### 4) 모바일 퍼스트(Mobile First) 개념

- **모바일 기준으로 먼저 디자인**하고,  
  화면이 커질수록 레이아웃/스타일을 확장하는 방식.

- 기본 아이디어:
  1. 기본 CSS = **모바일용 스타일**
  2. `@media (min-width: ...)` 를 사용해 **더 큰 화면에서만** 추가 스타일 적용

예:

```css
/* 1) 기본: 모바일용 (세로 1열) */
.card-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

/* 2) 화면이 768px 이상이면(태블릿/PC) 가로 2열 */
@media (min-width: 768px) {
  .card-list {
    flex-direction: row;
    flex-wrap: wrap;
  }
}
```

설명 포인트:
- 모바일 퍼스트는 “**가장 작은 기기 → 큰 기기** 순서로 생각하기”.
- 실제 사용자가 모바일로 접속하는 비율이 더 높기 때문에  
  **모바일 경험을 먼저 최적화**하는 것이 중요하다.

---

### 2. 실습 단계 – 화면 크기에 따라 레이아웃 변경

#### 2.1 목표

- 같은 HTML 구조를 유지하면서,
- 화면 크기에 따라 **카드 레이아웃(상품/게시글 등)이 1열 → 2열 → 3열** 로 바뀌도록 만든다.
- viewport 메타 태그와 media query의 효과를 직접 확인한다.

#### 2.2 파일 구조

```txt
project/
  index.html
  style.css
```

#### 2.3 실습 흐름

1. `index.html` 에 카드 여러 개가 들어 있는 `.card-list` 구조를 만든다.
2. `style.css` 에 기본(모바일 기준) 스타일을 작성한다.
3. `@media (min-width: 768px)` 와 `@media (min-width: 1024px)` 를 사용해  
   화면이 넓어질수록 카드가 더 많이 가로로 배치되도록 만든다.
4. 브라우저 창 크기를 조절하거나, 개발자 도구의 **디바이스 모드**로  
   모바일/태블릿/PC 화면을 번갈아 보며 변화를 확인한다.

---

### 3. 코드 스켈레톤

> 완성본이 아니라, **연습용 틀**만 제공합니다.  
> 카드 내용, 색상, 카드 개수 등은 수업 중 또는 과제로 수정합니다.

#### 3.1 `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0"
    />
    <title>반응형 웹 기초</title>
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <header class="site-header">
      <h1 class="site-title">반응형 카드 레이아웃</h1>
    </header>

    <main class="site-main">
      <section class="card-section">
        <h2 class="section-title">게시글 목록</h2>

        <div class="card-list">
          <article class="card">
            <h3 class="card-title">게시글 1</h3>
            <p class="card-content">
              간단한 내용 요약이 들어갑니다.
            </p>
          </article>

          <article class="card">
            <h3 class="card-title">게시글 2</h3>
            <p class="card-content">
              간단한 내용 요약이 들어갑니다.
            </p>
          </article>

          <article class="card">
            <h3 class="card-title">게시글 3</h3>
            <p class="card-content">
              간단한 내용 요약이 들어갑니다.
            </p>
          </article>

          <article class="card">
            <h3 class="card-title">게시글 4</h3>
            <p class="card-content">
              간단한 내용 요약이 들어갑니다.
            </p>
          </article>
        </div>
      </section>
    </main>
  </body>
</html>
```

#### 3.2 `style.css`

```css
/* 기본(모바일 퍼스트) 스타일 */
body {
  margin: 0;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
    sans-serif;
  background-color: #f5f5f5;
}

.site-header {
  background-color: #3f51b5;
  color: #ffffff;
  padding: 16px;
  text-align: center;
}

.site-main {
  padding: 16px;
}

.card-section {
  max-width: 1000px;
  margin: 0 auto;
}

.section-title {
  margin-bottom: 16px;
  font-size: 20px;
  text-align: center;
}

/* 카드 리스트 – 모바일에서는 1열 세로 배치 */
.card-list {
  display: flex;
  flex-direction: column; /* 위에서 아래로 쌓기 */
  gap: 16px;
}

.card {
  background-color: #ffffff;
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
}

.card-title {
  margin-top: 0;
  margin-bottom: 8px;
  font-size: 18px;
}

.card-content {
  margin: 0;
  font-size: 14px;
  color: #555555;
}

/* 1단계: 화면이 768px 이상일 때 (태블릿 이상) → 2열 배치 */
@media (min-width: 768px) {
  .card-list {
    flex-direction: row;
    flex-wrap: wrap;
  }

  .card {
    flex: 1 1 calc(50% - 16px); /* 2열 정도 느낌 (gap 고려) */
  }
}

/* 2단계: 화면이 1024px 이상일 때 (큰 태블릿/PC) → 3열 배치 */
@media (min-width: 1024px) {
  .card {
    flex: 1 1 calc(33.333% - 16px); /* 3열 정도 느낌 */
  }
}
```

> 수업 중 실험 아이디어:
> - `meta viewport` 태그를 잠시 지워 보고, 모바일에서 화면이 어떻게 달라지는지 비교
> - `@media (min-width: 768px)` 와 `@media (max-width: 768px)` 의 차이를 보여 주며  
>   “기본을 모바일로 둘지, 데스크톱으로 둘지” 에 따라 전략이 달라짐을 설명
> - 카드 개수를 늘리거나 줄여 보며, 줄바꿈과 열 개수가 어떻게 바뀌는지 관찰

---

### 4. 과제

1. **콘텐츠 채우기**
   - 카드 제목과 내용을 실제로 자신이 쓰고 싶은 게시글/프로젝트/상품 등으로 바꿔 보기.
   - 카드 개수를 6개 이상으로 늘려, 스크롤되는 레이아웃을 만들어 본다.

2. **브레이크포인트 조정**
   - `768px`, `1024px` 대신 다른 값(예: 600px, 900px)을 사용해 보고,  
     어떤 화면 크기에서 레이아웃이 바뀌는지 직접 확인해 기록한다.

3. **모바일 퍼스트 정리 (심화)**
   - “기본 CSS = 모바일용” 이라는 관점에서,  
     **왜 min-width 미디어 쿼리를 사용하면 모바일 퍼스트가 되는지**  
     자신의 말로 한 문단으로 정리해 본다.



