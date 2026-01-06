# 5회차 — Flexbox 실전

## 학습 목표
- Flexbox의 기본 개념과 구조를 이해할 수 있다
- Flex 컨테이너와 아이템의 주요 속성을 사용할 수 있다
- 정렬 속성을 활용하여 레이아웃을 만들 수 있다
- 유연한 아이템 크기를 제어할 수 있다
- 실전 레이아웃을 Flexbox로 구현할 수 있다

---

## 1. Flexbox란?

### 1.1 Flexbox의 목적

Flexbox(Flexible Box)는 **1차원 레이아웃**을 위한 CSS 레이아웃 모델입니다.

**특징:**
- 가로 또는 세로 방향으로 요소를 배치
- 공간을 효율적으로 분배
- 정렬과 간격 조절이 쉬움
- 반응형 레이아웃에 적합

**1차원 vs 2차원:**
- **Flexbox**: 가로 또는 세로 중 하나의 방향 (1차원)
- **Grid**: 가로와 세로 모두 (2차원)

### 1.2 Flexbox 구조

```
┌─────────────────────────────────┐
│      Flex Container (부모)      │
│  ┌──────┐  ┌──────┐  ┌──────┐  │
│  │Item 1│  │Item 2│  │Item 3│  │
│  └──────┘  └──────┘  └──────┘  │
│  Main Axis (주축) →              │
│  ↑ Cross Axis (교차축)           │
└─────────────────────────────────┘
```

**구성 요소:**
- **Flex Container (부모)**: `display: flex`를 가진 요소
- **Flex Item (자식)**: Flex Container의 직접 자식 요소
- **Main Axis (주축)**: 아이템이 배치되는 주요 방향
- **Cross Axis (교차축)**: 주축에 수직인 방향

---

## 2. Flex 컨테이너 속성

### 2.1 display: flex

Flexbox를 활성화합니다.

```css
.container {
    display: flex;  /* Flexbox 활성화 */
}
```

**특징:**
- 부모 요소에 적용
- 직접 자식 요소들이 Flex Item이 됨
- 기본적으로 가로 방향 배치

### 2.2 flex-direction

주축의 방향을 지정합니다.

```css
.container {
    display: flex;
    flex-direction: row;        /* 기본값: 가로 (왼쪽→오른쪽) */
    flex-direction: row-reverse; /* 가로 (오른쪽→왼쪽) */
    flex-direction: column;     /* 세로 (위→아래) */
    flex-direction: column-reverse; /* 세로 (아래→위) */
}
```

**값 설명:**
- `row`: 가로 방향 (기본값)
- `row-reverse`: 가로 방향 반대
- `column`: 세로 방향
- `column-reverse`: 세로 방향 반대

### 2.3 flex-wrap

아이템이 넘칠 때 줄바꿈을 허용할지 지정합니다.

```css
.container {
    display: flex;
    flex-wrap: nowrap;  /* 기본값: 줄바꿈 안 함 */
    flex-wrap: wrap;    /* 줄바꿈 허용 */
    flex-wrap: wrap-reverse; /* 줄바꿈 허용 (역순) */
}
```

**값 설명:**
- `nowrap`: 줄바꿈 안 함 (기본값, 아이템이 축소될 수 있음)
- `wrap`: 줄바꿈 허용
- `wrap-reverse`: 줄바꿈 허용 (역순)

### 2.4 flex-flow (단축 속성)

`flex-direction`과 `flex-wrap`을 한 번에 지정합니다.

```css
.container {
    display: flex;
    flex-flow: row wrap;  /* direction wrap */
    flex-flow: column nowrap;
}
```

---

## 3. 정렬 속성

### 3.1 justify-content

주축(Main Axis) 방향으로 아이템을 정렬합니다.

```css
.container {
    display: flex;
    justify-content: flex-start;    /* 시작점 정렬 (기본값) */
    justify-content: flex-end;     /* 끝점 정렬 */
    justify-content: center;       /* 가운데 정렬 */
    justify-content: space-between; /* 양끝 정렬, 사이 간격 */
    justify-content: space-around;  /* 양쪽 간격 동일 */
    justify-content: space-evenly; /* 모든 간격 동일 */
}
```

**값 설명:**
- `flex-start`: 시작점 정렬 (기본값)
- `flex-end`: 끝점 정렬
- `center`: 가운데 정렬
- `space-between`: 양끝 정렬, 아이템 사이 간격
- `space-around`: 아이템 양쪽 간격 동일
- `space-evenly`: 모든 간격 동일

**시각화:**
```
flex-start:    [A][B][C]───────
flex-end:      ───────[A][B][C]
center:        ───[A][B][C]───
space-between: [A]───[B]───[C]
space-around:  ─[A]─[B]─[C]─
space-evenly:  ─[A]─[B]─[C]─
```

### 3.2 align-items

교차축(Cross Axis) 방향으로 아이템을 정렬합니다.

```css
.container {
    display: flex;
    align-items: stretch;    /* 기본값: 늘림 */
    align-items: flex-start; /* 시작점 정렬 */
    align-items: flex-end;   /* 끝점 정렬 */
    align-items: center;     /* 가운데 정렬 */
    align-items: baseline;   /* 텍스트 기준선 정렬 */
}
```

**값 설명:**
- `stretch`: 늘려서 채움 (기본값)
- `flex-start`: 시작점 정렬
- `flex-end`: 끝점 정렬
- `center`: 가운데 정렬
- `baseline`: 텍스트 기준선 정렬

**시각화 (세로 방향):**
```
stretch:    ┌─┐ ┌─┐ ┌─┐
            │A│ │B│ │C│
            └─┘ └─┘ └─┘

flex-start: ┌─┐
            │A│ ┌─┐ ┌─┐
            └─┘ │B│ │C│
                └─┘ └─┘

center:     ┌─┐
          ┌─┐│A│┌─┐
          │B│└─┘│C│
          └─┘   └─┘
```

### 3.3 align-content

여러 줄이 있을 때 교차축 방향으로 줄들을 정렬합니다.

```css
.container {
    display: flex;
    flex-wrap: wrap;
    align-content: stretch;      /* 기본값: 늘림 */
    align-content: flex-start;  /* 시작점 정렬 */
    align-content: flex-end;    /* 끝점 정렬 */
    align-content: center;      /* 가운데 정렬 */
    align-content: space-between; /* 양끝 정렬 */
    align-content: space-around;  /* 양쪽 간격 동일 */
}
```

**주의사항:**
- `flex-wrap: wrap`일 때만 효과가 있음
- 한 줄일 때는 `align-items`가 적용됨

### 3.4 gap

아이템 사이의 간격을 지정합니다.

```css
.container {
    display: flex;
    gap: 20px;           /* 모든 방향 간격 */
    gap: 10px 20px;      /* 세로 가로 */
    row-gap: 10px;       /* 세로 간격 */
    column-gap: 20px;    /* 가로 간격 */
}
```

**특징:**
- 아이템 사이에만 간격 적용 (양끝에는 적용 안 됨)
- `margin`보다 간편함
- 최신 브라우저 지원

---

## 4. Flex 아이템 속성

### 4.1 flex-grow

남은 공간을 아이템이 얼마나 차지할지 지정합니다.

```css
.item {
    flex-grow: 0;  /* 기본값: 늘지 않음 */
    flex-grow: 1;  /* 남은 공간을 차지 */
    flex-grow: 2;  /* 다른 아이템의 2배 */
}
```

**동작 방식:**
- 기본값: `0` (늘지 않음)
- 숫자가 클수록 더 많은 공간 차지
- 비율로 계산됨

**예시:**
```css
.item1 { flex-grow: 1; }  /* 1/3 */
.item2 { flex-grow: 2; }  /* 2/3 */
.item3 { flex-grow: 1; }  /* 1/3 */
```

### 4.2 flex-shrink

공간이 부족할 때 아이템이 얼마나 줄어들지 지정합니다.

```css
.item {
    flex-shrink: 1;  /* 기본값: 줄어듦 */
    flex-shrink: 0;  /* 줄어들지 않음 */
    flex-shrink: 2;  /* 다른 아이템의 2배로 줄어듦 */
}
```

**동작 방식:**
- 기본값: `1` (줄어듦)
- `0`이면 최소 크기 유지
- 숫자가 클수록 더 많이 줄어듦

### 4.3 flex-basis

아이템의 초기 크기를 지정합니다.

```css
.item {
    flex-basis: auto;    /* 기본값: 콘텐츠 크기 */
    flex-basis: 200px;   /* 고정 크기 */
    flex-basis: 30%;     /* 비율 */
    flex-basis: 0;       /* 최소 크기 */
}
```

**동작 방식:**
- `auto`: 콘텐츠 크기
- 고정 값: 픽셀, 퍼센트 등
- `0`: 최소 크기 (grow/shrink 계산 기준)

### 4.4 flex (단축 속성)

`flex-grow`, `flex-shrink`, `flex-basis`를 한 번에 지정합니다.

```css
.item {
    flex: 1;              /* flex: 1 1 0 */
    flex: 0 1 auto;       /* 기본값 */
    flex: 1 0 200px;      /* grow shrink basis */
    flex: 0 0 200px;      /* 고정 크기 (grow: 0, shrink: 0) */
}
```

**주요 값:**
- `flex: 1`: 남은 공간을 모두 차지
- `flex: 0 0 200px`: 고정 크기 200px
- `flex: 0 1 auto`: 기본값 (늘지 않고 줄어듦)

**자주 사용하는 패턴:**
```css
/* 균등 분배 */
.item { flex: 1; }

/* 고정 크기 */
.sidebar { flex: 0 0 250px; }

/* 비율 분배 */
.item1 { flex: 2; }  /* 2배 */
.item2 { flex: 1; }  /* 1배 */
```

### 4.5 align-self

개별 아이템의 교차축 정렬을 지정합니다.

```css
.item {
    align-self: auto;      /* 기본값: 부모의 align-items 따름 */
    align-self: flex-start;
    align-self: flex-end;
    align-self: center;
    align-self: stretch;
    align-self: baseline;
}
```

**특징:**
- 개별 아이템에 적용
- 부모의 `align-items`보다 우선순위 높음

### 4.6 order

아이템의 순서를 변경합니다.

```css
.item {
    order: 0;  /* 기본값 */
    order: 1;  /* 뒤로 이동 */
    order: -1; /* 앞으로 이동 */
}
```

**특징:**
- HTML 구조는 변경하지 않고 시각적 순서만 변경
- 숫자가 작을수록 앞에 배치
- 기본값: `0`

---

## 5. 실전 레이아웃 예제

### 5.1 헤더 레이아웃

```html
<header class="header">
    <div class="logo">로고</div>
    <nav class="nav">
        <a href="#">메뉴1</a>
        <a href="#">메뉴2</a>
        <a href="#">메뉴3</a>
    </nav>
    <div class="user">사용자</div>
</header>
```

```css
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
    background-color: #333;
    color: white;
}

.logo {
    flex: 0 0 auto;
}

.nav {
    display: flex;
    gap: 20px;
}

.user {
    flex: 0 0 auto;
}
```

### 5.2 카드 행 레이아웃

```html
<div class="card-row">
    <div class="card">카드 1</div>
    <div class="card">카드 2</div>
    <div class="card">카드 3</div>
</div>
```

```css
.card-row {
    display: flex;
    gap: 20px;
    flex-wrap: wrap;
}

.card {
    flex: 1 1 300px;  /* 최소 300px, 균등 분배 */
    min-width: 0;     /* flex-basis보다 작아질 수 있음 */
}
```

### 5.3 푸터 레이아웃

```html
<footer class="footer">
    <div class="footer-section">
        <h3>회사 정보</h3>
        <p>주소, 연락처 등</p>
    </div>
    <div class="footer-section">
        <h3>링크</h3>
        <ul>...</ul>
    </div>
    <div class="footer-section">
        <h3>SNS</h3>
        <div class="social">...</div>
    </div>
</footer>
```

```css
.footer {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    gap: 30px;
    padding: 40px;
    background-color: #2c3e50;
    color: white;
}

.footer-section {
    flex: 1 1 250px;
    min-width: 200px;
}
```

### 5.4 중앙 정렬

```css
.center-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}
```

### 5.5 스티키 푸터

```css
body {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

main {
    flex: 1;  /* 남은 공간 모두 차지 */
}

footer {
    flex: 0 0 auto;  /* 고정 크기 */
}
```

---

## 6. Flexbox 팁과 주의사항

### 6.1 자주 하는 실수

#### 1. flex: 1과 width 충돌
```css
/* 나쁜 예 */
.item {
    flex: 1;
    width: 200px;  /* flex가 우선됨 */
}

/* 좋은 예 */
.item {
    flex: 1;
    min-width: 200px;  /* 최소 크기 */
}
```

#### 2. flex-wrap과 고정 크기
```css
/* 나쁜 예 */
.item {
    flex: 1 1 300px;
    width: 300px;  /* 불필요 */
}

/* 좋은 예 */
.item {
    flex: 1 1 300px;  /* flex-basis로 충분 */
}
```

#### 3. gap 브라우저 호환성
```css
/* 구형 브라우저 대응 */
.container {
    display: flex;
    gap: 20px;  /* 최신 브라우저 */
}

.item {
    margin-right: 20px;  /* 구형 브라우저 대체 */
}

.item:last-child {
    margin-right: 0;
}
```

### 6.2 유용한 패턴

#### 균등 분배
```css
.items {
    display: flex;
}

.item {
    flex: 1;  /* 모두 균등 */
}
```

#### 고정 + 유연
```css
.container {
    display: flex;
}

.sidebar {
    flex: 0 0 250px;  /* 고정 */
}

.main {
    flex: 1;  /* 유연 */
}
```

#### 반응형 카드
```css
.card-grid {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
}

.card {
    flex: 1 1 300px;  /* 최소 300px, 균등 분배 */
    max-width: 400px;  /* 최대 크기 제한 */
}
```

---

## 7. 실습 체크리스트

### 기본 개념
- [ ] Flexbox의 구조를 이해함 (Container, Item, Axis)
- [ ] flex-direction으로 방향을 변경할 수 있음
- [ ] flex-wrap으로 줄바꿈을 제어할 수 있음

### 정렬
- [ ] justify-content로 주축 정렬을 할 수 있음
- [ ] align-items로 교차축 정렬을 할 수 있음
- [ ] align-content를 이해함
- [ ] gap으로 간격을 조절할 수 있음

### 아이템 크기
- [ ] flex-grow로 공간 분배를 할 수 있음
- [ ] flex-shrink를 이해함
- [ ] flex-basis로 초기 크기를 지정할 수 있음
- [ ] flex 단축 속성을 사용할 수 있음

### 실전 레이아웃
- [ ] 헤더 레이아웃을 만들 수 있음
- [ ] 카드 행 레이아웃을 만들 수 있음
- [ ] 푸터 레이아웃을 만들 수 있음
- [ ] 중앙 정렬을 할 수 있음

---

## 다음 단계
- Grid 레이아웃
- Flexbox와 Grid 조합
- 반응형 웹 디자인
- CSS 애니메이션


