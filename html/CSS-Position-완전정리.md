# CSS Position 완전 정리

## 📌 학습 목표

- position 속성의 5가지 값을 이해한다
- 각 position 값의 특징과 차이를 구분할 수 있다
- position을 활용한 레이아웃을 만들 수 있다
- z-index의 개념을 이해한다

---

## 1️⃣ Position이란?

### 기본 개념

**position**은 요소의 **위치를 지정하는 방법**을 결정하는 CSS 속성입니다.

### Position의 5가지 값

1. **static** - 기본값 (문서 흐름에 따라 배치)
2. **relative** - 상대 위치 (원래 위치 기준)
3. **absolute** - 절대 위치 (부모 기준)
4. **fixed** - 고정 위치 (화면 기준)
5. **sticky** - 끈끈한 위치 (스크롤 시 고정)

### 위치 지정 속성

position이 `static`이 아닐 때 사용 가능:

- `top`: 위에서부터의 거리
- `right`: 오른쪽에서부터의 거리
- `bottom`: 아래에서부터의 거리
- `left`: 왼쪽에서부터의 거리

---

## 2️⃣ Static (기본값)

### 개념

**static**은 기본값으로, 요소가 **문서의 일반적인 흐름**에 따라 배치됩니다.

### 특징

- 문서 흐름에 따라 자연스럽게 배치
- `top`, `left`, `right`, `bottom` 속성이 **무시됨**
- `z-index` 속성이 **무시됨**

### 시각화

```
┌─────────────────────────────────┐
│  문서 흐름 (위에서 아래)         │
│                                 │
│  ┌─────┐                        │
│  │ Box1│  ← static (기본)       │
│  └─────┘                        │
│                                 │
│  ┌─────┐                        │
│  │ Box2│  ← static (기본)       │
│  └─────┘                        │
│                                 │
│  ┌─────┐                        │
│  │ Box3│  ← static (기본)       │
│  └─────┘                        │
└─────────────────────────────────┘
```

### 예제

```css
.box {
    position: static;  /* 기본값 (명시 안 해도 됨) */
    /* top, left 등은 적용되지 않음 */
}
```

```html
<div class="box">Box 1</div>
<div class="box">Box 2</div>
<div class="box">Box 3</div>
```

**결과:** 세 개의 박스가 세로로 순서대로 배치됨

---

## 3️⃣ Relative (상대 위치)

### 개념

**relative**는 요소를 **원래 있어야 할 위치**를 기준으로 이동시킵니다.

### 특징

- 원래 위치를 기준으로 이동
- **원래 자리는 그대로 차지** (다른 요소에 영향 없음)
- `top`, `left`, `right`, `bottom` 사용 가능
- 문서 흐름에서 제거되지 않음

### 시각화

#### 원래 위치

```
┌─────────────────────────────────┐
│  ┌─────┐                        │
│  │ Box1│                        │
│  └─────┘                        │
│                                 │
│  ┌─────┐                        │
│  │ Box2│  ← 원래 위치            │
│  └─────┘                        │
│                                 │
│  ┌─────┐                        │
│  │ Box3│                        │
│  └─────┘                        │
└─────────────────────────────────┘
```

#### position: relative 적용 후

```css
.box2 {
    position: relative;
    top: 20px;    /* 아래로 20px */
    left: 30px;   /* 오른쪽으로 30px */
}
```

```
┌─────────────────────────────────┐
│  ┌─────┐                        │
│  │ Box1│                        │
│  └─────┘                        │
│                                 │
│  ┌─────┐                        │
│  │     │  ← 원래 자리 (공간 차지) │
│  └─────┘                        │
│      ┌─────┐                    │
│      │ Box2│  ← 이동한 위치      │
│      └─────┘                    │
│                                 │
│  ┌─────┐                        │
│  │ Box3│  ← Box2의 원래 자리 영향│
│  └─────┘                        │
└─────────────────────────────────┘
```

### 예제

```css
.badge {
    position: relative;
    top: -10px;   /* 위로 10px */
    left: 20px;   /* 오른쪽으로 20px */
}
```

```html
<div class="container">
    <span class="badge">NEW</span>
    <p>상품 설명</p>
</div>
```

**결과:** 뱃지가 원래 위치에서 위로 10px, 오른쪽으로 20px 이동

### 사용 사례

- 미세한 위치 조정
- 아이콘, 뱃지 위치 조정
- 텍스트 오프셋

---

## 4️⃣ Absolute (절대 위치)

### 개념

**absolute**는 요소를 **일반 문서 흐름에서 제거**하고, **가장 가까운 positioned 부모**를 기준으로 배치합니다.

### 기준점 찾기

1. **부모 요소 중** `position: relative/absolute/fixed`인 요소 찾기
2. 있으면 → 그 부모를 기준
3. 없으면 → `body` (뷰포트)를 기준

### 특징

- 문서 흐름에서 **완전히 제거**됨
- 다른 요소가 그 자리를 차지함
- `top`, `left`, `right`, `bottom` 사용 가능
- 부모의 `position`에 따라 기준점이 달라짐

### 시각화

#### 부모가 static인 경우

```css
.parent {
    position: static;  /* 기본값 */
}

.child {
    position: absolute;
    top: 0;
    right: 0;
}
```

```
┌─────────────────────────────────┐
│  body (뷰포트)                  │
│  ┌───────────────────────────┐  │
│  │ .parent (static)          │  │
│  │                           │  │
│  │                           │  │
│  └───────────────────────────┘  │
│              ┌─────┐            │
│              │child│ ← body 기준 │
│              └─────┘            │
└─────────────────────────────────┘
```

#### 부모가 relative인 경우

```css
.parent {
    position: relative;  /* 기준점이 됨 */
}

.child {
    position: absolute;
    top: 0;
    right: 0;
}
```

```
┌─────────────────────────────────┐
│  body                           │
│  ┌───────────────────────────┐  │
│  │ .parent (relative)        │  │
│  │ ┌─────────────────────┐  │  │
│  │ │                     │  │  │
│  │ │              ┌─────┐ │  │  │
│  │ │              │child│ │  │  │
│  │ │              └─────┘ │  │  │
│  │ └─────────────────────┘  │  │
│  └───────────────────────────┘  │
└─────────────────────────────────┘
      ↑
   parent 기준
```

### 예제

```css
.container {
    position: relative;  /* 기준점 설정 */
    width: 300px;
    height: 200px;
    border: 1px solid #ccc;
}

.badge {
    position: absolute;
    top: 10px;
    right: 10px;
    background: red;
    color: white;
    padding: 5px 10px;
}
```

```html
<div class="container">
    <div class="badge">HOT</div>
    <p>상품 내용</p>
</div>
```

**결과:** 뱃지가 컨테이너의 우측 상단에 고정

### 사용 사례

- 모달 창
- 드롭다운 메뉴
- 툴팁
- 오버레이 요소
- 카드 내 뱃지

---

## 5️⃣ Fixed (고정 위치)

### 개념

**fixed**는 요소를 **뷰포트(화면)를 기준**으로 고정시킵니다. 스크롤해도 같은 위치에 고정됩니다.

### 특징

- 뷰포트(화면)를 기준으로 배치
- 스크롤해도 **위치 고정**
- 문서 흐름에서 **제거**됨
- 다른 요소가 그 자리를 차지함

### 시각화

```
┌─────────────────────────────────┐
│ ┌─────┐                         │
│ │헤더 │ ← fixed (스크롤해도 고정)│
│ └─────┘                         │
│                                 │
│  콘텐츠                          │
│  콘텐츠                          │
│  콘텐츠                          │
│  콘텐츠                          │
│  콘텐츠                          │
│  콘텐츠                          │
│  콘텐츠                          │
│                                 │
│ ┌─────┐                         │
│ │푸터 │ ← fixed (스크롤해도 고정)│
│ └─────┘                         │
└─────────────────────────────────┘
```

### 예제

```css
.header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background: white;
    z-index: 1000;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.floating-button {
    position: fixed;
    bottom: 20px;
    right: 20px;
    width: 60px;
    height: 60px;
    border-radius: 50%;
    background: #007bff;
    color: white;
}
```

```html
<header class="header">헤더</header>
<main>긴 콘텐츠...</main>
<button class="floating-button">↑</button>
```

**결과:** 
- 헤더는 스크롤해도 상단에 고정
- 버튼은 스크롤해도 우측 하단에 고정

### 사용 사례

- 고정 헤더/네비게이션
- 플로팅 버튼
- 고정 사이드바
- 알림 배너

---

## 6️⃣ Sticky (끈끈한 위치)

### 개념

**sticky**는 요소가 **스크롤 위치에 따라** 일반 흐름과 고정 사이를 전환합니다.

### 특징

- 일반 흐름에 따라 배치되다가
- 스크롤로 특정 위치에 도달하면 **고정**됨
- `top`, `left`, `right`, `bottom` 중 하나 이상 필요
- 부모 요소 범위 내에서만 작동

### 시각화

#### 스크롤 전

```
┌─────────────────────────────────┐
│  콘텐츠 1                        │
│  ┌───────────────────────────┐  │
│  │ 헤더 (sticky)             │  │
│  └───────────────────────────┘  │
│  콘텐츠 2                        │
│  콘텐츠 3                        │
└─────────────────────────────────┘
```

#### 스크롤 후 (top: 0에 도달)

```
┌─────────────────────────────────┐
│ ┌───────────────────────────┐  │
│ │ 헤더 (sticky, 고정됨)      │  │
│ └───────────────────────────┘  │
│  콘텐츠 1 (스크롤됨)            │
│  콘텐츠 2                       │
│  콘텐츠 3                       │
└─────────────────────────────────┘
```

### 예제

```css
.sticky-header {
    position: sticky;
    top: 0;
    background: white;
    z-index: 100;
    padding: 20px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}
```

```html
<div class="content">콘텐츠 1</div>
<header class="sticky-header">스티키 헤더</header>
<div class="content">콘텐츠 2</div>
<div class="content">콘텐츠 3</div>
```

**결과:** 
- 처음에는 일반 흐름에 따라 배치
- 스크롤하여 `top: 0` 위치에 도달하면 고정
- 부모 요소를 벗어나면 다시 일반 흐름으로

### 사용 사례

- 스티키 헤더
- 테이블 헤더 고정
- 스티키 사이드바
- 스크롤 시 고정되는 네비게이션

---

## 7️⃣ Z-index (겹침 순서)

### 개념

**z-index**는 요소의 **겹침 순서**를 결정합니다. 숫자가 클수록 위에 표시됩니다.

### 특징

- `position`이 `static`이 아닐 때만 작동
- 숫자가 클수록 위에 표시
- 음수 값 가능
- 같은 z-index면 나중에 작성된 요소가 위에

### 시각화

```
┌─────────────────────────────────┐
│                                 │
│  ┌─────┐                        │
│  │ Box1│  z-index: 1            │
│  └─────┘                        │
│      ┌─────┐                    │
│      │ Box2│  z-index: 2 (위에) │
│      └─────┘                    │
│          ┌─────┐                │
│          │ Box3│  z-index: 3    │
│          └─────┘                │
│                                 │
└─────────────────────────────────┘
```

### 예제

```css
.box1 {
    position: absolute;
    z-index: 1;
    background: red;
}

.box2 {
    position: absolute;
    z-index: 2;  /* Box1 위에 표시 */
    background: blue;
}

.box3 {
    position: absolute;
    z-index: 3;  /* 가장 위에 표시 */
    background: green;
}
```

---

## 8️⃣ Position 비교표

| 구분 | Static | Relative | Absolute | Fixed | Sticky |
|------|--------|----------|----------|-------|--------|
| **기준점** | 문서 흐름 | 원래 위치 | 부모/뷰포트 | 뷰포트 | 스크롤 위치 |
| **문서 흐름** | 유지 | 유지 | 제거 | 제거 | 유지→고정 |
| **원래 자리** | 차지 | 차지 | 비움 | 비움 | 차지→고정 |
| **top/left** | 무시 | 적용 | 적용 | 적용 | 적용 |
| **스크롤** | 따라감 | 따라감 | 따라감 | 고정 | 고정 |
| **사용 사례** | 기본 | 미세 조정 | 오버레이 | 고정 요소 | 스티키 헤더 |

---

## 9️⃣ 실전 예제

### 예제 1: 카드에 뱃지 배치

```html
<div class="card">
    <div class="badge">NEW</div>
    <h3>상품명</h3>
    <p>상품 설명</p>
</div>
```

```css
.card {
    position: relative;  /* 기준점 설정 */
    width: 300px;
    padding: 20px;
    border: 1px solid #ddd;
}

.badge {
    position: absolute;
    top: 10px;
    right: 10px;
    background: red;
    color: white;
    padding: 5px 10px;
    border-radius: 5px;
}
```

### 예제 2: 고정 헤더

```html
<header class="header">로고 | 메뉴</header>
<main>긴 콘텐츠...</main>
```

```css
.header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    background: white;
    z-index: 1000;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

main {
    margin-top: 60px;  /* 헤더 높이만큼 여백 */
}
```

### 예제 3: 모달 창

```html
<div class="modal-overlay">
    <div class="modal">
        <h2>모달 제목</h2>
        <p>모달 내용</p>
        <button>닫기</button>
    </div>
</div>
```

```css
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0,0,0,0.5);
    z-index: 2000;
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal {
    position: relative;
    background: white;
    padding: 30px;
    border-radius: 10px;
    max-width: 500px;
    z-index: 2001;
}
```

### 예제 4: 드롭다운 메뉴

```html
<div class="dropdown">
    <button>메뉴</button>
    <div class="dropdown-menu">
        <a href="#">항목 1</a>
        <a href="#">항목 2</a>
        <a href="#">항목 3</a>
    </div>
</div>
```

```css
.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-menu {
    position: absolute;
    top: 100%;
    left: 0;
    background: white;
    border: 1px solid #ddd;
    display: none;
    min-width: 150px;
}

.dropdown:hover .dropdown-menu {
    display: block;
}
```

---

## 🔟 Position 조합 예제

### Relative + Absolute 조합

```css
.container {
    position: relative;  /* 기준점 */
    width: 400px;
    height: 300px;
    border: 2px solid #333;
}

.top-left {
    position: absolute;
    top: 10px;
    left: 10px;
}

.top-right {
    position: absolute;
    top: 10px;
    right: 10px;
}

.bottom-left {
    position: absolute;
    bottom: 10px;
    left: 10px;
}

.bottom-right {
    position: absolute;
    bottom: 10px;
    right: 10px;
}

.center {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
```

### 시각화

```
┌─────────────────────────────┐
│ ┌──┐              ┌──┐     │
│ │TL│              │TR│     │
│ └──┘              └──┘     │
│                            │
│         ┌──────┐          │
│         │Center│          │
│         └──────┘          │
│                            │
│ ┌──┐              ┌──┐     │
│ │BL│              │BR│     │
│ └──┘              └──┘     │
└─────────────────────────────┘
```

---

## 1️⃣1️⃣ 주의사항

### 1. Absolute의 기준점

```css
/* ❌ 잘못된 예 */
.parent {
    /* position 없음 (static) */
}

.child {
    position: absolute;
    top: 0;
    right: 0;
    /* body를 기준으로 배치됨 (의도와 다를 수 있음) */
}

/* ✅ 올바른 예 */
.parent {
    position: relative;  /* 기준점 설정 */
}

.child {
    position: absolute;
    top: 0;
    right: 0;
    /* parent를 기준으로 배치됨 */
}
```

### 2. Fixed와 부모 요소

```css
/* Fixed는 항상 뷰포트 기준 */
.parent {
    position: relative;
    transform: translateX(100px);
}

.child {
    position: fixed;
    top: 0;
    /* 부모의 transform과 무관하게 뷰포트 기준 */
}
```

### 3. Sticky의 부모 범위

```css
/* Sticky는 부모 요소를 벗어나면 작동 안 함 */
.parent {
    height: 500px;
    overflow: hidden;  /* sticky가 작동 안 할 수 있음 */
}

.sticky-element {
    position: sticky;
    top: 0;
}
```

---

## 1️⃣2️⃣ 핵심 정리

### Position 선택 가이드

| 목적 | 사용할 Position |
|------|----------------|
| 기본 배치 | `static` (기본값) |
| 미세 조정 | `relative` |
| 오버레이 요소 | `absolute` |
| 고정 헤더/버튼 | `fixed` |
| 스크롤 시 고정 | `sticky` |

### 기억할 점

1. **Relative**: 원래 자리 기준, 자리는 차지
2. **Absolute**: 부모 기준, 자리는 비움
3. **Fixed**: 뷰포트 기준, 스크롤해도 고정
4. **Sticky**: 스크롤에 따라 고정/해제
5. **Z-index**: 겹침 순서 (static 제외)

---

## 📚 관련 자료

- [HTML 4회차: 박스모델 레이아웃 기본](./html-4회차-박스모델-레이아웃기본.md)
- [HTML 5회차: Flexbox 실전](./html-5회차-Flexbox실전.md)
- [HTML 6회차: CSS Grid 실전](./html-6회차-CSSGrid실전.md)




