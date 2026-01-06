# 4회차 — 박스 모델과 레이아웃 기본

## 학습 목표
- CSS 박스 모델의 구조를 이해하고 margin, padding, border를 사용할 수 있다
- box-sizing 속성의 차이를 이해하고 적절히 사용할 수 있다
- display 속성의 종류와 차이를 이해할 수 있다
- float와 clear의 개념을 이해하고 현대적 대체 방법을 알 수 있다
- 레이아웃 문제를 디버깅할 수 있다

---

## 1. 박스 모델 (Box Model)

### 1.1 박스 모델이란?

모든 HTML 요소는 박스 모델에 따라 렌더링됩니다. 박스 모델은 콘텐츠를 감싸는 여러 영역으로 구성됩니다.

```
┌─────────────────────────────────┐
│         Margin (외부 여백)        │
│  ┌───────────────────────────┐  │
│  │      Border (테두리)       │  │
│  │  ┌─────────────────────┐  │  │
│  │  │   Padding (내부 여백) │  │  │
│  │  │  ┌───────────────┐  │  │  │
│  │  │  │   Content     │  │  │  │
│  │  │  │   (콘텐츠)     │  │  │  │
│  │  │  └───────────────┘  │  │  │
│  │  └─────────────────────┘  │  │
│  └───────────────────────────┘  │
└─────────────────────────────────┘
```

**구성 요소:**
1. **Content (콘텐츠)**: 실제 내용이 표시되는 영역
2. **Padding (내부 여백)**: 콘텐츠와 테두리 사이의 공간
3. **Border (테두리)**: 패딩을 감싸는 선
4. **Margin (외부 여백)**: 테두리 밖의 공간

### 1.2 Content (콘텐츠)

실제 텍스트, 이미지 등이 표시되는 영역입니다.

```css
div {
    width: 200px;  /* 콘텐츠 영역의 너비 */
    height: 100px;  /* 콘텐츠 영역의 높이 */
}
```

### 1.3 Padding (내부 여백)

콘텐츠와 테두리 사이의 공간입니다. 배경색이 적용됩니다.

```css
div {
    padding: 20px;           /* 모든 방향 20px */
    padding: 10px 20px;       /* 위아래 10px, 좌우 20px */
    padding: 10px 20px 30px;  /* 위 10px, 좌우 20px, 아래 30px */
    padding: 10px 20px 30px 40px; /* 위 오른쪽 아래 왼쪽 */
    
    /* 개별 속성 */
    padding-top: 10px;
    padding-right: 20px;
    padding-bottom: 30px;
    padding-left: 40px;
}
```

**특징:**
- 배경색이 적용됨
- 요소의 클릭 가능 영역이 증가
- 음수 값 사용 불가

### 1.4 Border (테두리)

패딩을 감싸는 선입니다.

```css
div {
    border: 2px solid #333;  /* 단축 속성 */
    
    /* 개별 속성 */
    border-width: 2px;
    border-style: solid;
    border-color: #333;
    
    /* 방향별 지정 */
    border-top: 2px solid red;
    border-right: 1px dashed blue;
    border-bottom: 3px dotted green;
    border-left: 1px solid #333;
}
```

**특징:**
- 배경색이 적용됨
- 요소의 크기에 포함됨

### 1.5 Margin (외부 여백)

테두리 밖의 공간입니다. 배경색이 적용되지 않습니다.

```css
div {
    margin: 20px;           /* 모든 방향 20px */
    margin: 10px 20px;      /* 위아래 10px, 좌우 20px */
    margin: 10px 20px 30px; /* 위 10px, 좌우 20px, 아래 30px */
    margin: 10px 20px 30px 40px; /* 위 오른쪽 아래 왼쪽 */
    
    /* 개별 속성 */
    margin-top: 10px;
    margin-right: 20px;
    margin-bottom: 30px;
    margin-left: 40px;
    
    /* 중앙 정렬 */
    margin: 0 auto;  /* 위아래 0, 좌우 자동 (중앙 정렬) */
}
```

**특징:**
- 배경색이 적용되지 않음
- 음수 값 사용 가능
- 인접한 요소의 margin이 겹침 (margin collapse)

### 1.6 Margin Collapse (마진 겹침)

인접한 블록 요소의 상하 마진이 겹쳐서 하나의 마진으로 합쳐지는 현상입니다.

```html
<div class="box1">박스 1</div>
<div class="box2">박스 2</div>
```

```css
.box1 {
    margin-bottom: 30px;
}

.box2 {
    margin-top: 20px;
}
/* 실제 간격: 30px (큰 값이 적용됨) */
```

**마진 겹침이 발생하는 경우:**
- 블록 요소의 상하 마진
- 부모와 자식 요소의 첫 번째/마지막 자식의 마진
- 빈 요소의 상하 마진

**마진 겹침이 발생하지 않는 경우:**
- 좌우 마진
- 인라인 요소
- float, position: absolute/fixed 요소

### 1.7 Box-sizing

요소의 크기 계산 방식을 지정합니다.

#### content-box (기본값)

```css
div {
    box-sizing: content-box;  /* 기본값 */
    width: 200px;
    padding: 20px;
    border: 2px solid #333;
}
/* 실제 너비: 200px + 40px(padding) + 4px(border) = 244px */
```

**계산 방식:**
- `width` = 콘텐츠 영역만
- 실제 너비 = width + padding + border

#### border-box (권장)

```css
div {
    box-sizing: border-box;
    width: 200px;
    padding: 20px;
    border: 2px solid #333;
}
/* 실제 너비: 200px (padding과 border 포함) */
```

**계산 방식:**
- `width` = 콘텐츠 + padding + border
- 실제 너비 = width

**전역 설정 (권장):**
```css
* {
    box-sizing: border-box;
}
```

### 1.8 박스 모델 시각화

개발자 도구를 사용하여 박스 모델을 시각화할 수 있습니다.

**방법:**
1. `F12`로 개발자 도구 열기
2. 요소 선택 도구 (`Ctrl+Shift+C`)로 요소 선택
3. Elements 탭에서 선택된 요소 확인
4. Computed 탭에서 박스 모델 확인

---

## 2. Display와 흐름

### 2.1 Display 속성

요소가 화면에 어떻게 표시되는지를 결정합니다.

### 2.2 Block (블록)

**특징:**
- 항상 새 줄에서 시작
- 너비는 부모 요소의 100% (기본값)
- 높이는 콘텐츠에 따라 결정
- margin, padding, width, height 사용 가능
- 다른 블록 요소나 인라인 요소를 포함할 수 있음

**블록 요소 예시:**
- `<div>`, `<p>`, `<h1>`~`<h6>`, `<section>`, `<article>`, `<ul>`, `<li>` 등

```css
div {
    display: block;  /* 기본값 */
    width: 200px;
    height: 100px;
    margin: 10px;
    padding: 20px;
}
```

### 2.3 Inline (인라인)

**특징:**
- 같은 줄에 배치
- 너비와 높이는 콘텐츠에 따라 결정
- width, height 사용 불가
- 상하 margin 사용 불가 (좌우만 가능)
- padding은 적용되지만 위아래는 다른 요소에 영향 없음
- 다른 인라인 요소만 포함 가능

**인라인 요소 예시:**
- `<span>`, `<a>`, `<strong>`, `<em>`, `<img>` 등

```css
span {
    display: inline;  /* 기본값 */
    /* width, height 사용 불가 */
    margin: 0 10px;  /* 좌우만 가능 */
    padding: 5px 10px;  /* 가능하지만 위아래는 영향 없음 */
}
```

### 2.4 Inline-block (인라인 블록)

**특징:**
- 인라인처럼 같은 줄에 배치
- 블록처럼 width, height 사용 가능
- margin, padding 모두 사용 가능
- 다른 블록 요소나 인라인 요소를 포함할 수 있음

**사용 예시:**
- 버튼을 가로로 나열
- 카드 레이아웃
- 네비게이션 메뉴

```css
.button {
    display: inline-block;
    width: 120px;
    height: 40px;
    margin: 10px;
    padding: 10px 20px;
}
```

### 2.5 None

요소를 완전히 숨깁니다.

```css
.hidden {
    display: none;  /* 공간도 차지하지 않음 */
}
```

**vs visibility: hidden:**
```css
.hidden1 {
    display: none;  /* 공간 차지 안 함, 레이아웃에 영향 없음 */
}

.hidden2 {
    visibility: hidden;  /* 공간은 차지, 레이아웃에 영향 있음 */
}
```

### 2.6 기타 Display 값

#### Flex
```css
.container {
    display: flex;  /* Flexbox 레이아웃 */
}
```

#### Grid
```css
.container {
    display: grid;  /* Grid 레이아웃 */
}
```

#### Table
```css
div {
    display: table;
    display: table-cell;
    display: table-row;
}
```

---

## 3. 정렬 및 Float, Clear

### 3.1 Float의 역사적 맥락

Float는 원래 텍스트 주변에 이미지를 배치하기 위해 만들어졌습니다. 하지만 레이아웃을 만들기 위해 널리 사용되었습니다.

**원래 목적:**
```css
img {
    float: left;  /* 텍스트가 이미지 주변으로 흐름 */
}
```

**레이아웃 용도 (과거):**
```css
.sidebar {
    float: left;
    width: 200px;
}

.main {
    float: right;
    width: calc(100% - 200px);
}
```

### 3.2 Float 속성

요소를 왼쪽 또는 오른쪽으로 띄웁니다.

```css
.float-left {
    float: left;  /* 왼쪽으로 띄움 */
}

.float-right {
    float: right;  /* 오른쪽으로 띄움 */
}

.float-none {
    float: none;  /* 띄우지 않음 (기본값) */
}
```

**특징:**
- 요소가 부모의 왼쪽/오른쪽에 배치됨
- 다른 요소들이 주변으로 흐름
- 부모 요소의 높이가 사라질 수 있음 (clear 필요)

### 3.3 Float 문제점

#### 1. 부모 높이 사라짐

```html
<div class="parent">
    <div class="float-child">Float 요소</div>
</div>
```

```css
.parent {
    border: 2px solid #333;
}

.float-child {
    float: left;
}
/* 부모의 높이가 0이 됨 */
```

**해결 방법:**
- Clear 사용
- Overflow 사용
- Clearfix 사용

#### 2. 레이아웃 깨짐

Float 요소 다음의 요소들이 예상치 못한 위치에 배치될 수 있습니다.

### 3.4 Clear 속성

Float의 영향을 제거합니다.

```css
.clear-left {
    clear: left;  /* 왼쪽 float 제거 */
}

.clear-right {
    clear: right;  /* 오른쪽 float 제거 */
}

.clear-both {
    clear: both;  /* 양쪽 float 제거 (가장 많이 사용) */
}

.clear-none {
    clear: none;  /* 제거 안 함 (기본값) */
}
```

**사용 예시:**
```html
<div class="float-box">Float 요소</div>
<div class="clear-box">Clear 요소</div>
```

```css
.float-box {
    float: left;
}

.clear-box {
    clear: both;  /* 위의 float 영향 제거 */
}
```

### 3.5 Clearfix

부모 요소에 float된 자식이 있을 때 부모의 높이를 유지하는 방법입니다.

**방법 1: 가상 요소 사용 (권장)**
```css
.clearfix::after {
    content: "";
    display: table;
    clear: both;
}
```

**방법 2: Overflow 사용**
```css
.parent {
    overflow: auto;  /* 또는 hidden */
}
```

**방법 3: 빈 요소 사용 (권장하지 않음)**
```html
<div class="parent">
    <div class="float-child"></div>
    <div class="clear"></div>
</div>
```

```css
.clear {
    clear: both;
}
```

### 3.6 Float의 현대적 대체 방법

#### 1. Flexbox (권장)

```css
.container {
    display: flex;
    justify-content: space-between;
}

.sidebar {
    width: 200px;
}

.main {
    flex: 1;
}
```

#### 2. Grid (권장)

```css
.container {
    display: grid;
    grid-template-columns: 200px 1fr;
    gap: 20px;
}
```

#### 3. Inline-block

```css
.item {
    display: inline-block;
    width: calc(33.333% - 20px);
    vertical-align: top;
}
```

**Float 사용 권장 시기:**
- 텍스트 주변에 이미지 배치 (원래 목적)
- 레거시 브라우저 지원 필요 시

**Float 사용 비권장 시기:**
- 레이아웃 만들기 (Flexbox/Grid 사용 권장)
- 현대적 웹 개발

---

## 4. 레이아웃 문제 디버깅 기초

### 4.1 개발자 도구 활용

#### Elements 탭
- HTML 구조 확인
- CSS 스타일 확인
- 실시간 스타일 수정

#### Computed 탭
- 최종 적용된 스타일 확인
- 박스 모델 시각화
- 계산된 값 확인

#### Styles 탭
- 적용된 CSS 규칙 확인
- 우선순위 확인
- 비활성화된 스타일 확인

### 4.2 일반적인 문제와 해결

#### 문제 1: 요소가 보이지 않음

**원인:**
- `display: none`
- `visibility: hidden`
- `opacity: 0`
- `width: 0` 또는 `height: 0`
- `overflow: hidden`과 크기 문제
- `position`과 `z-index` 문제

**해결:**
1. 개발자 도구에서 요소 선택
2. Computed 탭에서 display, visibility 확인
3. Styles 탭에서 관련 속성 확인

#### 문제 2: 레이아웃이 깨짐

**원인:**
- Float 문제
- Box-sizing 문제
- Margin collapse
- Width 계산 오류

**해결:**
1. 박스 모델 확인 (개발자 도구)
2. box-sizing 확인
3. Float clear 확인
4. 부모 요소의 overflow 확인

#### 문제 3: 요소가 예상 위치에 없음

**원인:**
- Position 속성
- Float
- Margin 값
- Transform

**해결:**
1. Computed 탭에서 position 확인
2. Margin 값 확인
3. Float 여부 확인

#### 문제 4: 스타일이 적용되지 않음

**원인:**
- 특이성(Specificity) 문제
- !important 사용
- 잘못된 선택자
- 캐시 문제

**해결:**
1. Styles 탭에서 비활성화된 스타일 확인
2. 특이성 계산
3. 브라우저 캐시 삭제 (`Ctrl+Shift+R`)

### 4.3 디버깅 체크리스트

- [ ] 개발자 도구로 요소 선택
- [ ] Computed 탭에서 박스 모델 확인
- [ ] Styles 탭에서 적용된 규칙 확인
- [ ] 비활성화된 스타일 확인
- [ ] Console 탭에서 에러 확인
- [ ] Network 탭에서 리소스 로딩 확인

### 4.4 유용한 디버깅 기법

#### 1. 임시 테두리 추가
```css
* {
    border: 1px solid red !important;
}
```

#### 2. 배경색 추가
```css
div {
    background-color: rgba(255, 0, 0, 0.1) !important;
}
```

#### 3. 콘솔에서 요소 확인
```javascript
// 개발자 도구 Console에서
$0  // 선택된 요소
$0.offsetWidth  // 요소 너비
$0.offsetHeight  // 요소 높이
```

#### 4. 박스 모델 계산기
개발자 도구의 Computed 탭에서 자동으로 계산된 값을 확인할 수 있습니다.

---

## 5. 실습 체크리스트

### 박스 모델
- [ ] margin, padding, border를 사용할 수 있음
- [ ] box-sizing의 차이를 이해함
- [ ] margin collapse를 이해함
- [ ] 개발자 도구로 박스 모델을 확인할 수 있음

### Display
- [ ] block, inline, inline-block의 차이를 이해함
- [ ] 각 display 값의 특징을 알고 있음
- [ ] 적절한 display 값을 선택할 수 있음

### Float와 Clear
- [ ] float의 개념을 이해함
- [ ] clear의 필요성을 이해함
- [ ] clearfix를 사용할 수 있음
- [ ] 현대적 대체 방법(Flexbox/Grid)을 알고 있음

### 디버깅
- [ ] 개발자 도구를 사용할 수 있음
- [ ] 박스 모델을 시각화할 수 있음
- [ ] 일반적인 문제를 해결할 수 있음

---

## 다음 단계
- Flexbox 레이아웃
- Grid 레이아웃
- Position 속성 (absolute, relative, fixed, sticky)
- 반응형 웹 디자인


