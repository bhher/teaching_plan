## HTML + CSS 1차시 – 프로필 카드 디자인

### 0. 학습 목표

- 간단한 HTML 구조 위에 CSS를 적용해 **프로필 카드 UI**를 만들 수 있다.
- CSS를 적용하는 3가지 방법(인라인, `<style>`, 외부 CSS 파일)의 개념을 이해한다.
- 기본 선택자(태그, 클래스, 아이디)를 사용해 스타일을 지정할 수 있다.
- 박스 모델(폭, 높이, 여백, 테두리)을 이용해 카드 형태를 만든다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) HTML 구조 복습 (오늘 사용할 태그)

- `<div>`: 박스(상자)를 만드는 태그
- `<img>`: 이미지
- `<h1>`, `<h2>`: 제목
- `<p>`: 문단
- `<button>` 또는 `<a>`: 버튼/링크 역할

프로필 카드에 들어갈 요소 예시:

- 프로필 이미지
- 이름, 직무
- 한 줄 소개
- “자세히 보기” 버튼 또는 링크

#### 2) CSS 적용 방법 3가지 (개념만)

1. **인라인 스타일**

```html
<p style="color: red;">빨간 글자</p>
```

- 장점: 빠르게 테스트 가능
- 단점: 코드가 지저분해지고, 재사용/유지보수 어려움

2. **`<style>` 태그 (문서 안에 CSS 작성)**

```html
<style>
  p {
    color: blue;
  }
</style>
```

- 한 HTML 파일 안에서만 사용되는 CSS를 작성할 때 편리

3. **외부 CSS 파일 연결 (실무에서 가장 많이 사용)**

```html
<link rel="stylesheet" href="style.css" />
```

- 장점:
  - HTML 구조와 스타일을 분리 → 코드가 깔끔
  - 여러 HTML 파일에서 같은 스타일 재사용 가능

> 오늘 실습에서는 **외부 CSS 파일 방식**(`style.css`)을 사용합니다.

#### 3) CSS 선택자 개념 (가볍게 복습)

- 태그 선택자: `p { ... }`
- 클래스 선택자: `.card { ... }`
- 아이디 선택자: `#main-title { ... }`

> 오늘은 **클래스 선택자**를 주로 사용해서 프로필 카드를 디자인합니다.

#### 4) 박스 모델 기초

- 모든 박스는 네 부분으로 이루어져 있다:
  - `width` / `height`: 내용 영역 크기
  - `padding`: 내용과 테두리 사이의 안쪽 여백
  - `border`: 박스 테두리
  - `margin`: 박스 바깥 여백

간단한 그림 설명 (글로 표현):

```txt
[ margin ]
  [ border ]
    [ padding ]
      [ content ]
```

프로필 카드는 하나의 `div` 박스에:
- 배경색, 테두리, padding, margin, width 등을 적절히 주어 “카드 모양”을 만든다.

#### 5) 글자/색상/배경색 스타일링 기초

- 글자 크기: `font-size`
- 글자 색: `color`
- 배경색: `background-color`
- 가운데 정렬: `text-align: center;`

예:

```css
.card-title {
  font-size: 20px;
  color: #333;
  text-align: center;
}
```

---

### 2. 실습 단계 – 프로필 카드 만들기

#### 2.1 전체 흐름

1. `index.html` 에 프로필 카드에 필요한 HTML 구조를 작성한다.
2. `style.css` 파일을 만들어 HTML에 연결한다.
3. CSS로 카드의 모양(크기, 여백, 배경색, 테두리)을 꾸민다.
4. 폰트 크기/색상/정렬을 조정해, 보기 좋은 프로필 카드를 완성한다.

#### 2.2 파일 구조

```txt
project/
  index.html
  style.css
```

---

### 3. 코드 스켈레톤

> 아래 코드는 **틀(skeleton)** 이며,  
> 실제 색상/크기/텍스트는 수업 중 또는 과제로 학생들이 채우도록 합니다.

#### 3.1 `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>프로필 카드</title>
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <h1 id="page-title">프로필 카드 예제</h1>

    <div class="card">
      <img
        class="card-avatar"
        src="images/profile.jpg"
        alt="사용자 프로필 사진"
      />
      <h2 class="card-name">홍길동</h2>
      <p class="card-job">프론트엔드 입문자</p>
      <p class="card-intro">
        간단한 자기소개 한 줄을 여기에 작성합니다.
      </p>
      <button class="card-button">자세히 보기</button>
    </div>
  </body>
</html>
```

> 이미지 경로(`images/profile.jpg`)는 수업 환경에 맞게 변경합니다.  
> 실제로는 `https://via.placeholder.com/150` 같은 임시 이미지 URL을 사용해도 좋습니다.

#### 3.2 `style.css`

```css
/* 전체 기본 스타일 */
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
    sans-serif;
  background-color: #f4f4f4;
}

/* 페이지 제목 */
#page-title {
  text-align: center;
  margin-top: 30px;
}

/* 카드 박스 */
.card {
  /* 폭, 여백, 테두리, 배경색, 그림자 등을 설정 */
  width: 300px;
  margin: 40px auto; /* 가운데 정렬용 */
  padding: 20px;
  border-radius: 10px;
  background-color: #ffffff;
  border: 1px solid #ddd;
  /* box-shadow 등은 수업 중에 추가해 볼 수 있음 */
}

/* 프로필 이미지 */
.card-avatar {
  /* 이미지를 동그랗게 만들거나, 크기를 조정 */
  display: block;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  object-fit: cover;
  margin: 0 auto 16px auto; /* 가운데 정렬 + 아래 여백 */
}

/* 이름 */
.card-name {
  text-align: center;
  font-size: 24px;
  margin-bottom: 4px;
}

/* 직무/직업 */
.card-job {
  text-align: center;
  color: #666;
  margin-bottom: 12px;
}

/* 한 줄 소개 */
.card-intro {
  font-size: 14px;
  color: #333;
  text-align: center;
  margin-bottom: 16px;
}

/* 버튼 */
.card-button {
  display: block;
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #667eea;
  color: white;
  font-weight: 500;
  cursor: pointer;
}

.card-button:hover {
  background-color: #5563c1;
}
```

---

### 4. 과제

1. **색상/폰트 커스터마이징**
   - 카드 배경색, 버튼 색상을 자신이 좋아하는 색조로 변경해 보기
   - 이름/직무의 글꼴 크기, 색상을 조정해 시각적으로 더 눈에 띄게 만들기

2. **여러 개의 카드 만들기**
   - `card` 박스를 복사해서 2~3개로 늘리고,
   - 각각 다른 이름/직무/이미지를 사용해 “멤버 리스트” 형태로 배치해 보기
   - 여러 카드를 가로로 배치하고 싶다면:
     - `.card-list` 컨테이너를 만들고, `display: flex` 또는 `display: grid`를 이용해 정렬 시도(선택 사항)

3. **옵션 과제(심화)**
   - 버튼 대신 `<a>` 태그를 사용해, 자신의 GitHub/블로그/포트폴리오 링크를 연결해 보기
   - 호버 효과를 추가:
     - 카드에 마우스를 올렸을 때 그림자(`box-shadow`)가 진해지도록 만들기

> 다음 차시 예고:  
> “다음 시간에는 선택자 우선순위와 여러 종류의 선택자를 더 다양하게 사용해서,  
> 카드 리스트 전체를 레이아웃하는 법을 연습해 보겠습니다.”



