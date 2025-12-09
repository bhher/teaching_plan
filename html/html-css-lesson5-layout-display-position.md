## HTML + CSS 5차시 – 레이아웃 기초 (Display & Position)

### 0. 학습 목표

- `display` 속성의 기본 값인 `block`, `inline`, `inline-block` 의 차이를 설명할 수 있다.
- `position: static / relative / absolute` 의 개념과 사용 예를 이해한다.
- 간단한 정렬(가로/세로 배치의 기본 아이디어)을 설명할 수 있다.
- 위 개념을 활용해 **배너(banner) 영역**을 만든다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 레이아웃이란?

- 화면에 있는 여러 박스들을 **어디에, 어떻게 배치할지**를 결정하는 것.
- 지금까지는 “박스 하나의 모양”을 다뤘다면,  
  이제부터는 “박스들 사이의 관계(위치)”를 다룬다.

---

### 2) display – block / inline / inline-block

#### 2.1 block 요소

- 예: `<div>`, `<p>`, `<h1>` 등
- 특징:
  - **한 줄을 가득 차지**한다.
  - 기본적으로 **줄바꿈이 자동으로 일어남**.
  - `width`, `height`, `margin`, `padding` 등 레이아웃 관련 속성을 자유롭게 줄 수 있다.

```css
div {
  display: block; /* 기본값 (명시 안 해도 block인 경우가 많음) */
}
```

#### 2.2 inline 요소

- 예: `<span>`, `<a>`, `<strong>`, `<em>` 등
- 특징:
  - **줄 안에서 흐르듯이 배치**된다.
  - 여러 개가 한 줄에 이어붙는다.
  - `width`, `height` 를 직접 지정해도 잘 적용되지 않는다.
  - 좌우 `margin`, `padding` 은 어느 정도 적용되지만, 위아래는 제약이 있다.

```css
span {
  display: inline;
}
```

#### 2.3 inline-block

- block + inline 의 중간 느낌.
- 특징:
  - 줄 안에서 이어붙지만(inline처럼 한 줄에 여러 개),  
    `width`, `height`, `margin`, `padding` 을 block처럼 자유롭게 줄 수 있다.

```css
.nav-item {
  display: inline-block;
  padding: 8px 16px;
}
```

설명 포인트:
- “네비게이션 메뉴 버튼들” 같이 **여러 개가 가로로 나란히 있으면서도  
  박스처럼 크기를 조절하고 싶은 경우** inline-block을 많이 사용한다.

---

### 3) position – static / relative / absolute

#### 3.1 기본 – static

- 기본 값. 아무 설정을 안 하면 **모든 요소는 static**.
- 문서 흐름(위에서 아래, 왼쪽에서 오른쪽)에 따라 자연스럽게 배치된다.
- `top`, `left` 같은 위치 속성은 적용되지 않는다.

```css
.box {
  position: static; /* 기본값 */
}
```

#### 3.2 relative – “원래 자리에서 살짝 이동”

- 요소를 **원래 있어야 할 위치**를 기준으로 살짝 이동시킬 때 사용.
- `top`, `left`, `right`, `bottom` 으로 미세 조정 가능.
- 화면에서 이동해 보여도, **원래 자리는 그대로 차지하고 있다고 생각**하면 이해가 쉽다.

```css
.badge {
  position: relative;
  top: -5px;  /* 원래 자리에서 위로 5px 이동 */
  left: 10px; /* 원래 자리에서 오른쪽으로 10px 이동 */
}
```

#### 3.3 absolute – “부모를 기준으로 딱 붙이기”

- 요소를 일반 흐름에서 **떼어내고**, 특정 위치에 “고정”시키는 느낌.
- 기준:
  - **가장 가까운 `position: relative` (또는 absolute, fixed) 부모**를 기준으로 배치.
  - 그런 부모가 없으면 **브라우저 전체(body 기준)** 를 기준으로 한다.

```css
.badge-wrapper {
  position: relative; /* 기준이 되는 박스 */
}

.badge {
  position: absolute;
  top: 0;
  right: 0;
}
```

설명 포인트:
- relative는 “**기준점 만들기 + 살짝 이동**”
- absolute는 “**그 기준점에 딱 붙이기**”
- 오늘은 **배너 안의 작은 요소 위치 잡기** 정도에만 사용해 보고,  
  복잡한 레이아웃은 이후 flex/grid 에서 더 다룬다.

---

### 4) 간단한 정렬 개념

#### 4.1 가로 정렬의 기본 아이디어

- block 요소를 가운데 정렬하고 싶을 때 자주 쓰는 패턴:

```css
.banner {
  width: 800px;
  margin: 0 auto; /* 좌우 auto → 수평 가운데 정렬 */
}
```

- 텍스트를 가운데 정렬:

```css
.banner-title {
  text-align: center;
}
```

설명 포인트:
- **박스 자체의 위치**를 가운데로 → `margin: 0 auto;`  
- **박스 안의 글자**를 가운데로 → `text-align: center;`

#### 4.2 세로 정렬 (간단 개념만)

- 세로 중앙 정렬은 `flex` 나 `grid` 를 쓰면 편하지만,  
  이 차시에서는 “**텍스트 줄 간격(line-height)** 을 이용한 간단한 개념” 정도만 언급.

예:

```css
.simple-banner {
  height: 60px;
  line-height: 60px; /* 한 줄짜리 텍스트를 세로 가운데로 보이게 하는 트릭 */
}
```

> 단, 여러 줄 텍스트에는 이 방법이 어울리지 않음을 함께 설명.

---

### 2. 실습 단계 – 배너 영역 만들기

#### 2.1 목표

- 페이지 상단에 **단순한 배너 영역**을 만들고,
- `display`, `position`, 정렬 개념을 사용해 간단한 레이아웃을 구성한다.

#### 2.2 파일 구조

```txt
project/
  index.html
  style.css
```

#### 2.3 실습 흐름

1. `index.html` 에 상단 배너 영역(`.banner`) 구조를 만든다.
2. `style.css` 에 배경색, 높이, 텍스트 스타일 등을 지정한다.
3. 배너 안에:
   - 왼쪽: 사이트 로고/제목
   - 오른쪽 상단: 작은 배지(badge) 또는 “NEW” 표시
   를 배치하면서 `display`, `position` 을 실습한다.

---

### 3. 코드 스켈레톤

> 완성본이 아니라, **연습용 틀**만 제공합니다.  
> 색상, 크기, 텍스트 내용은 수업 중 또는 과제로 조정합니다.

#### 3.1 `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>레이아웃 기초 – 배너</title>
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <header class="banner">
      <div class="banner-inner">
        <h1 class="banner-logo">My Website</h1>
        <div class="banner-badge-wrapper">
          <span class="banner-badge">NEW</span>
        </div>
      </div>
    </header>

    <main>
      <p>본문 내용 예시입니다. 배너 아래에 오는 영역입니다.</p>
    </main>
  </body>
</html>
```

#### 3.2 `style.css`

```css
body {
  margin: 0;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
    sans-serif;
}

/* 배너 전체 영역 */
.banner {
  background-color: #3f51b5;
  color: #ffffff;
  padding: 12px 0;
}

/* 배너 안쪽 컨테이너 – 가운데 정렬 */
.banner-inner {
  width: 900px;
  margin: 0 auto; /* 가로 가운데 정렬 */

  /* 배너 안에서 로고와 배지를 좌우에 배치하는 방법은
     display, position, float 등 여러 가지가 있으나
     이 차시에서는 display/position 위주로 체험해 본다. */
  position: relative; /* absolute 배지의 기준이 되는 박스 */
}

/* 로고(제목) – block 요소 */
.banner-logo {
  display: inline-block; /* 텍스트 크기만큼만 차지하도록 */
  margin: 0;
  font-size: 24px;
}

/* 배지 래퍼 – 위치 기준을 relative로 할 수도 있고,
   여기서는 배너-inner가 기준이므로 absolute만 써도 됨 */
.banner-badge-wrapper {
  /* 구조를 이해하기 위한 감싸는 요소 */
}

/* 배지 – 오른쪽 위에 붙이기 */
.banner-badge {
  position: absolute;
  top: 10px;
  right: 0;

  display: inline-block;
  padding: 4px 8px;
  font-size: 12px;
  background-color: #ff9800;
  color: #000;
  border-radius: 10px;
}

main {
  padding: 16px;
}
```

> 수업 중 실험 아이디어:
> - `.banner-logo` 의 `display` 값을 `block`, `inline`, `inline-block` 으로 바꿔 보며  
>   줄바꿈/폭 차이를 눈으로 확인하기
> - `.banner-badge` 의 `position` 을 `static`, `relative`, `absolute` 로 바꾸어  
>   배지가 어디에 나타나는지 비교해 보기
> - `.banner-inner` 의 `width` 를 줄이거나 없애고, `margin: 0 auto` 를 제거해  
>   “가운데 정렬이 풀린 상태”를 보여준 뒤 다시 복구해 보기

---

### 4. 과제

1. **배너 텍스트 변경**
   - `My Website` 대신 자신이 만들고 싶은 사이트 이름으로 변경.
   - 배지 텍스트도 `"NEW"`, `"BETA"`, `"SALE"` 등으로 바꿔 보기.

2. **display 실험**
   - 배너 안에 메뉴 항목 2~3개를 추가하고,  
     각각을 `display: inline` / `inline-block` 으로 바꿔 보며  
     한 줄에 어떻게 배치되는지 확인.

3. **position 실험 (심화)**
   - 배지 위치를 `top`, `right`, `left`, `bottom` 값을 바꿔가며  
     배너 안에서 원하는 자리(예: 왼쪽 아래)에 배치해 보기.
   - `position: relative` 를 `.banner-inner` 대신 다른 요소에 줬을 때,  
     absolute 기준이 어떻게 바뀌는지 관찰하고 노트에 정리.



