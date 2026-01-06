# 6회차 — CSS Grid 실전

## 학습 목표
- CSS Grid의 기본 개념과 구조를 이해할 수 있다
- Grid 컨테이너와 아이템의 주요 속성을 사용할 수 있다
- 복잡한 페이지 레이아웃을 Grid로 구현할 수 있다
- Grid와 Flexbox를 조합하여 사용할 수 있다

---

## 1. CSS Grid란?

### 1.1 Grid의 목적

CSS Grid는 **2차원 레이아웃**을 위한 CSS 레이아웃 모델입니다.

**특징:**
- 가로와 세로 모두 제어 가능 (2차원)
- 복잡한 레이아웃을 쉽게 구현
- 명시적인 그리드 구조
- 반응형 레이아웃에 적합

**1차원 vs 2차원:**
- **Flexbox**: 가로 또는 세로 중 하나의 방향 (1차원)
- **Grid**: 가로와 세로 모두 (2차원)

### 1.2 Grid 구조

```
┌─────────────────────────────────┐
│      Grid Container (부모)      │
│  ┌──────┬──────┬──────┐       │
│  │Cell 1│Cell 2│Cell 3│       │
│  ├──────┼──────┼──────┤       │
│  │Cell 4│Cell 5│Cell 6│       │
│  ├──────┼──────┼──────┤       │
│  │Cell 7│Cell 8│Cell 9│       │
│  └──────┴──────┴──────┘       │
│  Column (열)                    │
│  ↑ Row (행)                     │
└─────────────────────────────────┘
```

**구성 요소:**
- **Grid Container (부모)**: `display: grid`를 가진 요소
- **Grid Item (자식)**: Grid Container의 직접 자식 요소
- **Grid Line (그리드 선)**: 그리드를 나누는 선
- **Grid Track (트랙)**: 그리드 선 사이의 공간 (행 또는 열)
- **Grid Cell (셀)**: 행과 열이 교차하는 영역
- **Grid Area (영역)**: 하나 이상의 셀로 구성된 영역

---

## 2. Grid 컨테이너 속성

### 2.1 display: grid

Grid를 활성화합니다.

```css
.container {
    display: grid;  /* Grid 활성화 */
}
```

**특징:**
- 부모 요소에 적용
- 직접 자식 요소들이 Grid Item이 됨
- 기본적으로 한 열로 배치

### 2.2 grid-template-columns

열(Column)의 크기와 개수를 정의합니다.

```css
.container {
    display: grid;
    grid-template-columns: 200px 200px 200px;  /* 3개 열, 각 200px */
    grid-template-columns: 1fr 2fr 1fr;        /* 비율: 1:2:1 */
    grid-template-columns: repeat(3, 1fr);     /* 3개 열, 균등 분배 */
    grid-template-columns: 200px 1fr 200px;    /* 고정 + 유연 */
    grid-template-columns: minmax(200px, 1fr); /* 최소 200px, 최대 1fr */
}
```

**단위:**
- `px`, `em`, `rem`: 고정 크기
- `fr` (fraction): 남은 공간의 비율
- `%`: 부모 요소 기준 백분율
- `auto`: 콘텐츠 크기
- `minmax(min, max)`: 최소/최대 크기

**repeat() 함수:**
```css
grid-template-columns: repeat(3, 1fr);        /* 3개 열, 균등 */
grid-template-columns: repeat(4, 100px);       /* 4개 열, 각 100px */
grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); /* 자동 반응형 */
```

### 2.3 grid-template-rows

행(Row)의 크기와 개수를 정의합니다.

```css
.container {
    display: grid;
    grid-template-rows: 100px 200px 100px;  /* 3개 행 */
    grid-template-rows: 1fr 2fr 1fr;        /* 비율 */
    grid-template-rows: repeat(3, auto);     /* 3개 행, 자동 높이 */
}
```

**자동 행 생성:**
- 명시적으로 정의하지 않은 행은 `auto`로 생성됨
- 콘텐츠에 따라 자동으로 높이 결정

### 2.4 grid-template-areas

영역에 이름을 지정하여 레이아웃을 정의합니다.

```css
.container {
    display: grid;
    grid-template-columns: 200px 1fr 200px;
    grid-template-rows: 100px 1fr 100px;
    grid-template-areas:
        "header header header"
        "sidebar main aside"
        "footer footer footer";
}
```

**사용법:**
- 각 셀에 이름 지정
- 같은 이름을 여러 셀에 사용하여 영역 확장
- `.` (점)으로 빈 셀 표시

**예시:**
```css
grid-template-areas:
    "header header header"
    "sidebar main main"
    "footer footer footer";
```

### 2.5 gap (간격)

그리드 아이템 사이의 간격을 지정합니다.

```css
.container {
    display: grid;
    gap: 20px;           /* 모든 방향 간격 */
    gap: 10px 20px;       /* 행 간격 열 간격 */
    row-gap: 10px;        /* 행 간격 */
    column-gap: 20px;     /* 열 간격 */
}
```

**구형 브라우저:**
```css
grid-gap: 20px;          /* 구형 브라우저 */
grid-row-gap: 10px;
grid-column-gap: 20px;
```

---

## 3. Grid 아이템 속성

### 3.1 grid-column / grid-row

아이템이 차지할 열/행을 지정합니다.

```css
.item {
    grid-column: 1 / 3;      /* 1번 선부터 3번 선까지 (2개 열) */
    grid-row: 1 / 2;          /* 1번 선부터 2번 선까지 (1개 행) */
    
    /* 단축 속성 */
    grid-column-start: 1;
    grid-column-end: 3;
    grid-row-start: 1;
    grid-row-end: 2;
}
```

**span 키워드:**
```css
.item {
    grid-column: 1 / span 2;  /* 1번 선부터 2개 열 */
    grid-column: span 2;      /* 현재 위치부터 2개 열 */
}
```

### 3.2 grid-area

영역 이름으로 아이템을 배치합니다.

```css
.container {
    grid-template-areas:
        "header header"
        "sidebar main"
        "footer footer";
}

.header {
    grid-area: header;
}

.sidebar {
    grid-area: sidebar;
}

.main {
    grid-area: main;
}

.footer {
    grid-area: footer;
}
```

**또는 grid-column/grid-row 단축:**
```css
.item {
    grid-area: 1 / 1 / 3 / 3;  /* row-start / col-start / row-end / col-end */
}
```

### 3.3 justify-self / align-self

개별 아이템의 정렬을 지정합니다.

```css
.item {
    justify-self: start;    /* 열 방향 시작 */
    justify-self: end;      /* 열 방향 끝 */
    justify-self: center;   /* 열 방향 중앙 */
    justify-self: stretch;  /* 열 방향 늘림 (기본값) */
    
    align-self: start;      /* 행 방향 시작 */
    align-self: end;        /* 행 방향 끝 */
    align-self: center;     /* 행 방향 중앙 */
    align-self: stretch;    /* 행 방향 늘림 (기본값) */
}
```

### 3.4 place-self

`justify-self`와 `align-self`를 한 번에 지정합니다.

```css
.item {
    place-self: center;           /* justify-self와 align-self 모두 center */
    place-self: start end;        /* justify-self: start, align-self: end */
}
```

---

## 4. 정렬 속성

### 4.1 justify-items

모든 아이템의 열 방향 정렬을 지정합니다.

```css
.container {
    display: grid;
    justify-items: start;    /* 시작점 정렬 */
    justify-items: end;      /* 끝점 정렬 */
    justify-items: center;   /* 중앙 정렬 */
    justify-items: stretch;  /* 늘림 (기본값) */
}
```

### 4.2 align-items

모든 아이템의 행 방향 정렬을 지정합니다.

```css
.container {
    display: grid;
    align-items: start;      /* 시작점 정렬 */
    align-items: end;        /* 끝점 정렬 */
    align-items: center;     /* 중앙 정렬 */
    align-items: stretch;    /* 늘림 (기본값) */
}
```

### 4.3 justify-content / align-content

그리드 전체를 컨테이너 내에서 정렬합니다.

```css
.container {
    display: grid;
    grid-template-columns: 200px 200px;
    justify-content: center;     /* 열 방향 중앙 */
    align-content: center;       /* 행 방향 중앙 */
}
```

**값:**
- `start`, `end`, `center`
- `space-between`, `space-around`, `space-evenly`
- `stretch` (기본값)

### 4.4 place-items / place-content

정렬 속성을 한 번에 지정합니다.

```css
.container {
    place-items: center;         /* justify-items와 align-items 모두 center */
    place-content: center;       /* justify-content와 align-content 모두 center */
}
```

---

## 5. 실전 레이아웃 예제

### 5.1 기본 그리드 레이아웃

```html
<div class="grid-container">
    <div class="item">1</div>
    <div class="item">2</div>
    <div class="item">3</div>
    <div class="item">4</div>
    <div class="item">5</div>
    <div class="item">6</div>
</div>
```

```css
.grid-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
}
```

### 5.2 복잡한 페이지 레이아웃

```html
<div class="page-layout">
    <header>헤더</header>
    <aside class="sidebar">사이드바</aside>
    <main>메인 콘텐츠</main>
    <aside class="aside">사이드바 2</aside>
    <footer>푸터</footer>
</div>
```

```css
.page-layout {
    display: grid;
    grid-template-columns: 200px 1fr 200px;
    grid-template-rows: 100px 1fr 100px;
    grid-template-areas:
        "header header header"
        "sidebar main aside"
        "footer footer footer";
    gap: 20px;
    min-height: 100vh;
}

header {
    grid-area: header;
}

.sidebar {
    grid-area: sidebar;
}

main {
    grid-area: main;
}

.aside {
    grid-area: aside;
}

footer {
    grid-area: footer;
}
```

### 5.3 반응형 그리드

```css
.grid-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
}
```

**설명:**
- `auto-fit`: 가능한 많은 열 생성
- `minmax(250px, 1fr)`: 최소 250px, 최대 1fr
- 화면 크기에 따라 자동으로 열 개수 조절

### 5.4 카드 그리드

```css
.card-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 25px;
}
```

**auto-fit vs auto-fill:**
- `auto-fit`: 빈 트랙을 축소
- `auto-fill`: 빈 트랙 유지

---

## 6. Grid와 Flexbox 조합

### 6.1 언제 Grid를 사용할까?

**Grid 사용:**
- 2차원 레이아웃 (가로 + 세로)
- 복잡한 페이지 레이아웃
- 정확한 그리드 구조가 필요할 때

**Flexbox 사용:**
- 1차원 레이아웃 (가로 또는 세로)
- 아이템 정렬과 분배
- 동적인 콘텐츠

### 6.2 Grid 안에 Flexbox

```html
<div class="grid-container">
    <header class="header">
        <div class="logo">로고</div>
        <nav>메뉴</nav>
    </header>
    <main>메인</main>
</div>
```

```css
.grid-container {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: auto 1fr;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
```

### 6.3 Flexbox 안에 Grid

```html
<div class="flex-container">
    <div class="sidebar">사이드바</div>
    <div class="grid-content">
        <div class="card">카드 1</div>
        <div class="card">카드 2</div>
        <div class="card">카드 3</div>
    </div>
</div>
```

```css
.flex-container {
    display: flex;
    gap: 20px;
}

.sidebar {
    flex: 0 0 250px;
}

.grid-content {
    flex: 1;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
}
```

### 6.4 실전 조합 예제

```css
/* 전체 레이아웃: Grid */
.page {
    display: grid;
    grid-template-areas:
        "header header"
        "sidebar main"
        "footer footer";
    grid-template-columns: 250px 1fr;
    min-height: 100vh;
}

/* 헤더: Flexbox */
header {
    grid-area: header;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

/* 메인: Grid */
main {
    grid-area: main;
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
}
```

---

## 7. Grid 팁과 주의사항

### 7.1 자주 하는 실수

#### 1. fr과 고정 크기 혼용
```css
/* 나쁜 예 */
grid-template-columns: 200px 1fr 200px 1fr;  /* 복잡함 */

/* 좋은 예 */
grid-template-columns: 200px 1fr 200px;  /* 명확함 */
```

#### 2. grid-area 이름 오타
```css
/* 나쁜 예 */
grid-template-areas: "header header";  /* 오타 */
.item { grid-area: hedear; }  /* 오타 */

/* 좋은 예 */
grid-template-areas: "header header";
.item { grid-area: header; }
```

#### 3. gap 브라우저 호환성
```css
/* 최신 브라우저 */
gap: 20px;

/* 구형 브라우저 */
grid-gap: 20px;
```

### 7.2 유용한 패턴

#### 반응형 그리드
```css
.grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
}
```

#### 고정 + 유연
```css
.layout {
    display: grid;
    grid-template-columns: 250px 1fr;
}
```

#### 복잡한 레이아웃
```css
.page {
    display: grid;
    grid-template-areas:
        "header header header"
        "sidebar main aside"
        "footer footer footer";
    grid-template-columns: 200px 1fr 200px;
    grid-template-rows: auto 1fr auto;
}
```

---

## 8. 실습 체크리스트

### 기본 개념
- [ ] Grid의 구조를 이해함 (Container, Item, Track, Cell, Area)
- [ ] grid-template-columns로 열을 정의할 수 있음
- [ ] grid-template-rows로 행을 정의할 수 있음
- [ ] gap으로 간격을 조절할 수 있음

### 영역 배치
- [ ] grid-area로 영역을 배치할 수 있음
- [ ] grid-column/grid-row로 위치를 지정할 수 있음
- [ ] grid-template-areas로 레이아웃을 정의할 수 있음

### 정렬
- [ ] justify-items/align-items로 정렬할 수 있음
- [ ] justify-self/align-self로 개별 정렬할 수 있음

### 실전 레이아웃
- [ ] 복잡한 페이지 레이아웃을 만들 수 있음
- [ ] 반응형 그리드를 만들 수 있음
- [ ] Grid와 Flexbox를 조합할 수 있음

---

## 다음 단계
- Grid 고급 기법 (subgrid, masonry)
- 반응형 웹 디자인 심화
- CSS 애니메이션
- 실전 프로젝트


