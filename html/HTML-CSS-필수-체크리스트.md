# HTML / CSS 필수 체크리스트

풀스택 과정(HTML → CSS → JavaScript/jQuery → Spring Boot) 기준으로,  
학생이 **반드시 직접 할 수 있어야 하는** 항목을 단계별로 정리한 문서입니다.

---

## 목차

1. [HTML 필수](#-1단계--html-필수)
2. [Form](#-2단계--form-)
3. [CSS 기본](#-3단계--css-기본-)
4. [CSS 선택자](#-4단계--css-선택자-)
5. [Box Model](#-5단계--css-box-model-)
6. [색상 / 글자](#-6단계--색상--글자-)
7. [배경 / 테두리](#-7단계--배경--테두리-)
8. [Display](#-8단계--display-)
9. [Flexbox](#-9단계--flexbox-)
10. [Position](#-10단계--position-)
11. [Grid](#-11단계--grid-)
12. [Transition / Transform](#-12단계--transition--transform-)
13. [반응형 웹](#-13단계--반응형-웹-)
14. [우선순위 정리](#-우선순위-정리)
15. [최종 실습 과제](#-최종-실습-과제)

---

## 🟢 1단계 — HTML 필수

### 문서 기본 구조 ⭐⭐⭐

학생이 **직접 처음부터** 만들 수 있어야 합니다.

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>페이지 제목</title>
</head>
<body>

</body>
</html>
```

| 태그/속성 | 역할 |
|-----------|------|
| `<!DOCTYPE html>` | HTML5 문서 선언 |
| `<html>` | 문서 루트 |
| `<head>` | 메타정보, 제목, CSS 링크 |
| `<meta charset="UTF-8">` | 한글 인코딩 |
| `<meta name="viewport"...>` | 모바일 반응형 필수 |
| `<title>` | 브라우저 탭 제목 |
| `<body>` | 화면에 보이는 내용 |

- [ ] 빈 HTML 문서를 처음부터 직접 작성할 수 있다

---

### 기본 태그 ⭐⭐⭐

| 태그 | 용도 |
|------|------|
| `h1` ~ `h6` | 제목 (크기·중요도) |
| `p` | 문단 |
| `div` | 블록 영역 (레이아웃 구분) |
| `span` | 인라인 영역 (텍스트 일부 스타일) |
| `br` | 줄바꿈 |
| `hr` | 수평선 |

**div vs span (반드시 이해)**

| | `div` | `span` |
|---|-------|--------|
| 성격 | **block** — 한 줄 전체 차지 | **inline** — 글자 옆에 붙음 |
| 용도 | 레이아웃·섹션 나누기 | 문장 안 일부 강조 |

- [ ] `div`와 `span`의 차이를 설명할 수 있다

---

### 링크 / 이미지 ⭐⭐⭐

```html
<a href="https://example.com">이동</a>

<img src="images/a.jpg" alt="상품 이미지">
```

| 태그/속성 | 설명 |
|-----------|------|
| `<a>` | 링크 |
| `href` | 이동할 주소 |
| `<img>` | 이미지 |
| `src` | 이미지 경로 |
| `alt` | 대체 텍스트 (접근성·이미지 깨짐 시) |

- [ ] 링크와 이미지를 올바른 속성과 함께 작성할 수 있다

---

### 목록 ⭐⭐

```html
<ul>
    <li>Java</li>
    <li>Spring</li>
</ul>

<ol>
    <li>첫 번째</li>
    <li>두 번째</li>
</ol>
```

| 태그 | 용도 |
|------|------|
| `ul` | 순서 없는 목록 |
| `ol` | 순서 있는 목록 |
| `li` | 목록 항목 |

- [ ] `ul` / `ol` / `li`로 메뉴·목록을 만들 수 있다

---

### Semantic HTML ⭐⭐⭐

**꼭 가르치는 것을 추천합니다.**

| 태그 | 용도 |
|------|------|
| `header` | 상단 영역 (로고, GNB) |
| `nav` | 내비게이션 메뉴 |
| `main` | 본문 핵심 콘텐츠 |
| `section` | 주제별 구역 |
| `article` | 독립적인 글·카드 |
| `aside` | 사이드바·부가 정보 |
| `footer` | 하단 영역 |

**이해해야 할 구조**

```
header
 └── nav

main
 ├── section
 └── article

footer
```

- [ ] 위 구조를 보고 각 태그 역할을 설명할 수 있다

---

## 🟢 2단계 — Form ⭐⭐⭐⭐⭐

풀스택에서는 **매우 중요**합니다. Spring 연동의 시작점입니다.

### 필수 태그

| 태그 | 용도 |
|------|------|
| `form` | 입력 폼 전체 |
| `input` | 입력 필드 |
| `label` | 입력 항목 이름 |
| `button` | 버튼 |
| `textarea` | 여러 줄 입력 |
| `select` | 드롭다운 |
| `option` | 선택 항목 |

### input type (최소한)

| type | 용도 |
|------|------|
| `text` | 일반 텍스트 |
| `password` | 비밀번호 |
| `email` | 이메일 |
| `number` | 숫자 |
| `radio` | 단일 선택 |
| `checkbox` | 다중 선택 |
| `file` | 파일 업로드 |
| `submit` | 제출 버튼 |

### 특히 중요한 속성

| 속성 | 설명 |
|------|------|
| `name` | 서버로 전송되는 **필드 이름** (Spring DTO와 연결) |
| `id` | 요소 고유 식별 (`label for`와 연결) |
| `value` | 입력값 / 버튼 값 |
| `placeholder` | 안내 문구 |

```html
<label for="username">아이디</label>
<input type="text"
       id="username"
       name="username"
       placeholder="아이디 입력">
```

### Spring과의 연결

```
HTML form
   ↓
name 속성
   ↓
Controller
   ↓
DTO
```

- [ ] `name` / `id` / `value` / `placeholder` 차이를 설명할 수 있다
- [ ] `label for`와 `input id`를 연결할 수 있다
- [ ] 로그인·회원가입 폼을 직접 작성할 수 있다

---

## 🔵 3단계 — CSS 기본 ⭐⭐⭐⭐⭐

### CSS 적용 방법

| 방법 | 예시 | 실무 |
|------|------|------|
| Inline | `<p style="color:red;">` | 거의 사용 안 함 |
| Internal | `<style>` 태그 안 | 소규모·학습용 |
| **External** | `<link rel="stylesheet" href="style.css">` | **주로 사용** |

```html
<link rel="stylesheet" href="style.css">
```

- [ ] 외부 CSS 파일을 HTML에 연결할 수 있다

---

## 🔵 4단계 — CSS 선택자 ⭐⭐⭐⭐⭐

### 반드시 알아야 함

| 선택자 | 문법 | 예시 |
|--------|------|------|
| 태그 | `태그명 {}` | `p {}` |
| 클래스 | `.클래스명 {}` | `.box {}` |
| ID | `#id명 {}` | `#header {}` |
| 자손 | `.부모 자손 {}` | `.box p {}` |
| 자식 | `.부모 > 자식 {}` | `.box > p {}` |
| 그룹 | `a, b {}` | `h1, p {}` |

### 가상 클래스 ⭐⭐⭐

| 선택자 | 용도 |
|--------|------|
| `:hover` | 마우스 올렸을 때 |
| `:focus` | 입력창 포커스 시 |
| `:checked` | 체크박스·라디오 선택 시 |
| `:first-child` | 첫 번째 자식 |
| `:last-child` | 마지막 자식 |
| `:nth-child()` | n번째 자식 |

```css
button:hover {
    background: black;
    color: white;
}
```

- [ ] 클래스·ID·자손·자식 선택자를 구분할 수 있다
- [ ] `:hover`를 직접 적용해 볼 수 있다

---

## 🔵 5단계 — CSS Box Model ⭐⭐⭐⭐⭐

**가장 중요합니다.** 학생이 아래 그림을 이해해야 합니다.

```
┌──────────────────────┐
│       margin         │
│  ┌────────────────┐  │
│  │     border     │  │
│  │ ┌────────────┐ │  │
│  │ │   padding  │ │  │
│  │ │ ┌────────┐ │ │  │
│  │ │ │content │ │ │  │
│  │ │ └────────┘ │ │  │
│  │ └────────────┘ │  │
│  └────────────────┘  │
└──────────────────────┘
```

| 속성 | 설명 |
|------|------|
| `width` / `height` | 콘텐츠 영역 크기 |
| `padding` | 안쪽 여백 |
| `border` | 테두리 |
| `margin` | 바깥 여백 |
| `box-sizing` | 크기 계산 방식 |

```css
* {
    box-sizing: border-box;
}
```

> `border-box`: `width`에 padding·border가 **포함**됨 → 레이아웃 계산이 쉬움

- [ ] margin / padding / border / content 차이를 설명할 수 있다
- [ ] `box-sizing: border-box`의 의미를 안다

---

## 🔵 6단계 — 색상 / 글자 ⭐⭐⭐

| 속성 | 용도 |
|------|------|
| `color` | 글자 색 |
| `background-color` | 배경 색 |
| `font-size` | 글자 크기 |
| `font-weight` | 굵기 (bold 등) |
| `font-family` | 글꼴 |
| `text-align` | 정렬 (left / center / right) |
| `line-height` | 줄 간격 |
| `letter-spacing` | 자간 |

- [ ] 기본 타이포그래피 속성을 사용할 수 있다

---

## 🔵 7단계 — 배경 / 테두리 ⭐⭐

| 속성 | 용도 |
|------|------|
| `background-image` | 배경 이미지 |
| `background-size` | 배경 크기 (`cover` 등) |
| `background-position` | 배경 위치 |
| `border` | 테두리 |
| `border-radius` | 모서리 둥글게 |
| `box-shadow` | 그림자 (카드 UI) |

- [ ] 카드 UI 형태(둥근 모서리 + 그림자)를 만들 수 있다

---

## 🔴 8단계 — Display ⭐⭐⭐⭐⭐

**반드시 체크하세요.** JavaScript/jQuery와도 직결됩니다.

| 값 | 설명 |
|----|------|
| `block` | 한 줄 전체 차지 (div, p 등) |
| `inline` | 글자 옆에 붙음 (span, a 등) |
| `inline-block` | 인라인처럼 붙으면서 width/height 지정 가능 |
| `none` | 화면에서 숨김 (공간도 없음) |

```css
.hidden {
    display: none;
}
```

> jQuery `hide()` / `show()` 와 연결해서 이해하면 좋습니다.

- [ ] block / inline / inline-block / none 차이를 설명할 수 있다

---

## 🔴 9단계 — Flexbox ⭐⭐⭐⭐⭐

현대 웹 개발 **필수**. 직접 만들어 보게 하는 것을 추천합니다.

| 속성 | 용도 |
|------|------|
| `display: flex` | Flex 컨테이너 |
| `flex-direction` | 방향 (row / column) |
| `justify-content` | 주축 정렬 |
| `align-items` | 교차축 정렬 |
| `flex-wrap` | 줄바꿈 |
| `gap` | 요소 간격 |
| `flex` | 자식 요소 비율 |

```css
.container {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
```

- [ ] 헤더(로고 + 메뉴 + 로그인)를 Flex로 배치할 수 있다
- [ ] `justify-content` / `align-items` 차이를 안다

---

## 🔴 10단계 — Position ⭐⭐⭐⭐⭐

| 값 | 설명 |
|----|------|
| `static` | 기본값 (일반 흐름) |
| `relative` | 원래 위치 기준 이동 |
| `absolute` | **가장 가까운 positioned 부모** 기준 |
| `fixed` | 뷰포트(화면) 기준 고정 |
| `sticky` | 스크롤 시 고정 |

### relative + absolute (중요)

```css
.parent {
    position: relative;
}

.child {
    position: absolute;
    right: 10px;
    top: 10px;
}
```

### 함께 알아야 할 속성

- `top` / `right` / `bottom` / `left`
- `z-index` (겹침 순서)

- [ ] `relative` 부모 + `absolute` 자식 관계를 설명할 수 있다
- [ ] `fixed`로 상단 고정 메뉴를 만들 수 있다

---

## 🟠 11단계 — Grid ⭐⭐⭐

React 전에 **기본 Grid**만 알면 충분합니다.

```css
.container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
}
```

| 속성 | 용도 |
|------|------|
| `display: grid` | Grid 컨테이너 |
| `grid-template-columns` | 열 정의 |
| `grid-template-rows` | 행 정의 |
| `gap` | 간격 |
| `fr` | 비율 단위 |

### Flex vs Grid

| | Flex | Grid |
|---|------|------|
| 배치 | **1차원** (가로 또는 세로) | **2차원** (가로 + 세로) |
| 용도 | 헤더, 메뉴, 1줄 카드 | 상품 목록, 갤러리 |

- [ ] 3열 상품 카드 레이아웃을 Grid로 만들 수 있다

---

## 🟠 12단계 — Transition / Transform ⭐⭐⭐

JavaScript/jQuery 애니메이션과 연결하기 좋습니다.

| 속성/함수 | 용도 |
|-----------|------|
| `transition` | 속성 변화를 부드럽게 |
| `transform` | 변형 |
| `scale()` | 확대/축소 |
| `translate()` | 이동 |
| `rotate()` | 회전 |

```css
.box {
    transition: 0.3s;
}

.box:hover {
    transform: scale(1.1);
}
```

- [ ] hover 시 부드러운 확대 효과를 적용할 수 있다

---

## 🔴 13단계 — 반응형 웹 ⭐⭐⭐⭐⭐

풀스택 과정이면 **반드시** 필요합니다.

| 개념/단위 | 설명 |
|-----------|------|
| `@media` | 화면 크기별 스타일 분기 |
| `%` | 부모 기준 비율 |
| `rem` / `em` | 글자 크기 기준 |
| `vw` / `vh` | 뷰포트 기준 |
| `max-width` / `min-width` | 최대·최소 너비 |

```css
@media (max-width: 768px) {
    .container {
        flex-direction: column;
    }
}
```

- [ ] PC → 모바일로 바뀔 때 레이아웃이 변하는 원리를 이해한다
- [ ] `@media`로 모바일 메뉴·세로 배치를 적용할 수 있다

---

## ⭐ 우선순위 정리

### 🔥 반드시 할 것

| 영역 | 항목 |
|------|------|
| HTML | 기본 구조, Semantic HTML, `a` / `img`, `div` / `span` |
| Form | `form` / `input`, `name` / `id` / `value` |
| CSS | 선택자, Box Model, `display`, Flexbox, Position, 반응형 |

### ⭐ 알면 좋은 것

- Grid
- 가상 클래스 (`:hover`, `:nth-child` 등)
- `background`, `border-radius`, `box-shadow`
- `transition`, `transform`

### 🟡 시간이 부족하면 간단히

- CSS `animation`
- 복잡한 Grid
- 복잡한 선택자
- 고급 position
- CSS 변수 (`--custom-property`)

---

## 🎯 최종 실습 과제

HTML/CSS 수업 마무리 시 **작은 쇼핑몰 메인 화면**을 학생이 **처음부터 직접** 만들게 합니다.  
강사 코드를 그대로 따라 치는 것이 아니라, 스스로 구조를 잡을 수 있어야 합니다.

### 와이어프레임

```
┌─────────────────────────────────────┐
│ LOGO       메뉴1 메뉴2 메뉴3  로그인 │ ← header
├─────────────────────────────────────┤
│                                     │
│          메인 배너                   │
│                                     │
├─────────────────────────────────────┤
│             상품 목록                │
│                                     │
│   ┌─────┐ ┌─────┐ ┌─────┐           │
│   │상품1│ │상품2│ │상품3│           │
│   └─────┘ └─────┘ └─────┘           │
│                                     │
├─────────────────────────────────────┤
│              footer                 │
└─────────────────────────────────────┘
```

### 포함해야 할 HTML

- [ ] `header` / `nav` / `main` / `section` / `footer`
- [ ] `a` (메뉴, 로그인 링크)
- [ ] `img` (로고, 배너, 상품 이미지)

### 포함해야 할 CSS

- [ ] 선택자 (클래스, 자손 등)
- [ ] Box Model (`padding`, `margin`, `border-box`)
- [ ] Flex (헤더 정렬)
- [ ] Grid (상품 3열)
- [ ] Position (필요 시 배지·버튼)
- [ ] `:hover` (메뉴·카드)
- [ ] `@media` (모바일 1열)

### 완료 기준

> 위 과제를 **혼자서 처음부터** 만들 수 있다면 HTML/CSS 기초는 충분히 끝낸 것으로 봅니다.

---

## 📌 커리큘럼 연계 안내

jQuery까지 진행한 상태라면, HTML/CSS를 무한 확장하기보다:

1. 이 체크리스트에서 **빠진 부분만 보충**
2. **JavaScript / jQuery** (DOM, 이벤트, 효과)
3. **AJAX / JSON**
4. **Spring Boot** (Form `name` → Controller → DTO)

순으로 넘어가는 것을 권장합니다.

---

## 관련 교안

- [HTML-CSS-한방정리.md](./HTML-CSS-한방정리.md)
- [HTML-폼-태그-정리.md](./HTML-폼-태그-정리.md)
- [html-css-lesson5-layout-display-position.md](./html-css-lesson5-layout-display-position.md)
- [html-css-lesson6-flexbox.md](./html-css-lesson6-flexbox.md)
- [html-css-lesson7-responsive-intro.md](./html-css-lesson7-responsive-intro.md)
