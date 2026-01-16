# CSS ::before와 ::after 완전 정리

## 📌 목차

1. [의사 요소(Pseudo-element)란?](#1-의사-요소pseudo-element란)
2. [::before와 ::after 기본 개념](#2-before와-after-기본-개념)
3. [기본 사용법](#3-기본-사용법)
4. [content 속성](#4-content-속성)
5. [실전 예제](#5-실전-예제)
6. [고급 활용](#6-고급-활용)
7. [주의사항](#7-주의사항)

---

## 1️⃣ 의사 요소(Pseudo-element)란?

### 의사 요소의 정의

**의사 요소(Pseudo-element)**는 HTML 요소의 특정 부분을 선택하거나, 존재하지 않는 요소를 CSS로 생성할 수 있게 해주는 특별한 선택자입니다.

### 주요 의사 요소

| 의사 요소 | 설명 |
|----------|------|
| `::before` | 요소의 앞에 콘텐츠 추가 |
| `::after` | 요소의 뒤에 콘텐츠 추가 |
| `::first-line` | 첫 번째 줄 선택 |
| `::first-letter` | 첫 번째 글자 선택 |
| `::selection` | 선택된 텍스트 스타일 |

**참고:** CSS2에서는 `:before`, `:after` (콜론 1개)를 사용했지만, CSS3에서는 `::before`, `::after` (콜론 2개)를 권장합니다.

---

## 2️⃣ ::before와 ::after 기본 개념

### ::before

- 요소의 **내부 맨 앞**에 가상의 요소를 생성합니다
- HTML을 수정하지 않고도 콘텐츠를 추가할 수 있습니다

### ::after

- 요소의 **내부 맨 뒤**에 가상의 요소를 생성합니다
- HTML을 수정하지 않고도 콘텐츠를 추가할 수 있습니다

### 구조 이해

```html
<div class="box">내용</div>
```

**CSS 적용:**
```css
.box::before {
    content: "앞에 추가";
}

.box::after {
    content: "뒤에 추가";
}
```

**실제 구조 (개발자 도구에서 보이는 모습):**
```html
<div class="box">
    ::before "앞에 추가"
    내용
    ::after "뒤에 추가"
</div>
```

---

## 3️⃣ 기본 사용법

### 기본 문법

```css
선택자::before {
    content: "내용";
    /* 다른 스타일 속성들 */
}

선택자::after {
    content: "내용";
    /* 다른 스타일 속성들 */
}
```

### 필수 속성: content

**중요:** `::before`와 `::after`를 사용하려면 반드시 `content` 속성이 있어야 합니다!

```css
/* ✅ 올바른 사용 */
.box::before {
    content: "텍스트";
}

/* ❌ 오류! content가 없으면 표시되지 않음 */
.box::before {
    color: red;  /* content 없으면 작동 안 함 */
}
```

---

## 4️⃣ content 속성

### content 속성 값

| 값 | 설명 | 예제 |
|----|------|------|
| `"텍스트"` | 일반 텍스트 | `content: "Hello";` |
| `""` | 빈 문자열 (장식용) | `content: "";` |
| `attr(속성명)` | HTML 속성 값 사용 | `content: attr(data-text);` |
| `url(이미지경로)` | 이미지 삽입 | `content: url("icon.png");` |
| `counter()` | 카운터 값 | `content: counter(num);` |
| `none` | 콘텐츠 없음 | `content: none;` |

### 예제 1: 텍스트 추가

```html
<p class="quote">인생은 짧다</p>
```

```css
.quote::before {
    content: "💬 ";
}

.quote::after {
    content: " - 명언";
}
```

**결과:** 💬 인생은 짧다 - 명언

### 예제 2: 빈 문자열 (장식용)

```html
<div class="decorated">제목</div>
```

```css
.decorated::before {
    content: "";
    display: inline-block;
    width: 20px;
    height: 20px;
    background-color: red;
    margin-right: 10px;
}
```

**결과:** 빨간 사각형이 제목 앞에 표시됨

### 예제 3: HTML 속성 사용

```html
<a href="#" data-tooltip="클릭하세요">링크</a>
```

```css
a::after {
    content: " (" attr(data-tooltip) ")";
    color: gray;
    font-size: 0.8em;
}
```

**결과:** 링크 (클릭하세요)

---

## 5️⃣ 실전 예제

### 예제 1: 인용구 스타일

```html
<blockquote class="quote">
    성공은 준비된 자에게 찾아온다.
</blockquote>
```

```css
.quote {
    position: relative;
    padding: 20px;
    padding-left: 60px;
    background-color: #f5f5f5;
    border-left: 4px solid #3498db;
    font-style: italic;
}

.quote::before {
    content: """;
    position: absolute;
    left: 15px;
    top: 10px;
    font-size: 60px;
    color: #3498db;
    line-height: 1;
    font-family: Georgia, serif;
}

.quote::after {
    content: """;
    font-size: 60px;
    color: #3498db;
    font-family: Georgia, serif;
    vertical-align: -20px;
}
```

**결과:**
```
" 성공은 준비된 자에게 찾아온다. "
```

### 예제 2: 링크에 아이콘 추가

```html
<a href="#" class="external-link">외부 링크</a>
<a href="#" class="download-link">다운로드</a>
```

```css
.external-link::after {
    content: " 🔗";
    font-size: 0.8em;
}

.download-link::before {
    content: "⬇ ";
    font-size: 0.8em;
}
```

**결과:**
- 외부 링크 🔗
- ⬇ 다운로드

### 예제 3: 장식용 선 (구분선)

```html
<h2 class="section-title">섹션 제목</h2>
```

```css
.section-title {
    position: relative;
    text-align: center;
    padding: 20px 0;
}

.section-title::before,
.section-title::after {
    content: "";
    position: absolute;
    top: 50%;
    width: 40%;
    height: 2px;
    background-color: #333;
}

.section-title::before {
    left: 0;
}

.section-title::after {
    right: 0;
}
```

**결과:**
```
────────── 섹션 제목 ──────────
```

### 예제 4: 배지(Badge) 만들기

```html
<span class="badge">New</span>
```

```css
.badge {
    position: relative;
    display: inline-block;
    padding: 5px 15px;
    background-color: #e74c3c;
    color: white;
    border-radius: 20px;
}

.badge::before {
    content: "";
    position: absolute;
    left: -5px;
    top: 50%;
    transform: translateY(-50%);
    width: 0;
    height: 0;
    border-top: 8px solid transparent;
    border-bottom: 8px solid transparent;
    border-right: 8px solid #e74c3c;
}
```

**결과:** 말풍선 모양의 배지

### 예제 5: 체크리스트

```html
<ul class="checklist">
    <li>항목 1</li>
    <li>항목 2</li>
    <li>항목 3</li>
</ul>
```

```css
.checklist li {
    list-style: none;
    padding-left: 30px;
    position: relative;
    margin-bottom: 10px;
}

.checklist li::before {
    content: "✓";
    position: absolute;
    left: 0;
    color: #27ae60;
    font-weight: bold;
    font-size: 1.2em;
}
```

**결과:**
```
✓ 항목 1
✓ 항목 2
✓ 항목 3
```

### 예제 6: 툴팁(Tooltip)

```html
<span class="tooltip" data-text="이것은 툴팁입니다">마우스를 올려보세요</span>
```

```css
.tooltip {
    position: relative;
    cursor: pointer;
    color: #3498db;
    text-decoration: underline;
}

.tooltip::after {
    content: attr(data-text);
    position: absolute;
    bottom: 100%;
    left: 50%;
    transform: translateX(-50%);
    padding: 8px 12px;
    background-color: #333;
    color: white;
    border-radius: 4px;
    white-space: nowrap;
    opacity: 0;
    pointer-events: none;
    transition: opacity 0.3s;
    margin-bottom: 5px;
}

.tooltip::before {
    content: "";
    position: absolute;
    bottom: 100%;
    left: 50%;
    transform: translateX(-50%);
    border: 5px solid transparent;
    border-top-color: #333;
    opacity: 0;
    pointer-events: none;
    transition: opacity 0.3s;
}

.tooltip:hover::after,
.tooltip:hover::before {
    opacity: 1;
}
```

**결과:** 마우스 호버 시 툴팁 표시

### 예제 7: 카운터 번호

```html
<h2 class="numbered">제목 1</h2>
<h2 class="numbered">제목 2</h2>
<h2 class="numbered">제목 3</h2>
```

```css
body {
    counter-reset: section;
}

.numbered::before {
    content: counter(section) ". ";
    counter-increment: section;
    color: #e74c3c;
    font-weight: bold;
}
```

**결과:**
```
1. 제목 1
2. 제목 2
3. 제목 3
```

### 예제 8: 장식용 도형

```html
<div class="decorated-box">내용</div>
```

```css
.decorated-box {
    position: relative;
    padding: 30px;
    background-color: #f8f9fa;
    border: 2px solid #3498db;
}

.decorated-box::before {
    content: "";
    position: absolute;
    top: -10px;
    left: 20px;
    width: 20px;
    height: 20px;
    background-color: #3498db;
    transform: rotate(45deg);
}

.decorated-box::after {
    content: "";
    position: absolute;
    bottom: -10px;
    right: 20px;
    width: 20px;
    height: 20px;
    background-color: #3498db;
    transform: rotate(45deg);
}
```

**결과:** 상단과 하단에 다이아몬드 모양 장식

---

## 6️⃣ 고급 활용

### 예제 1: 말풍선 만들기

```html
<div class="speech-bubble">안녕하세요!</div>
```

```css
.speech-bubble {
    position: relative;
    padding: 15px 20px;
    background-color: #3498db;
    color: white;
    border-radius: 10px;
    max-width: 200px;
}

.speech-bubble::after {
    content: "";
    position: absolute;
    bottom: -10px;
    left: 20px;
    width: 0;
    height: 0;
    border-left: 10px solid transparent;
    border-right: 10px solid transparent;
    border-top: 10px solid #3498db;
}
```

### 예제 2: 로딩 애니메이션

```html
<div class="loading">로딩 중...</div>
```

```css
.loading::after {
    content: "...";
    animation: dots 1.5s steps(4, end) infinite;
}

@keyframes dots {
    0%, 20% {
        content: ".";
    }
    40% {
        content: "..";
    }
    60%, 100% {
        content: "...";
    }
}
```

### 예제 3: 필수 입력 표시

```html
<label class="required">이름</label>
```

```css
.required::after {
    content: " *";
    color: #e74c3c;
    font-weight: bold;
}
```

**결과:** 이름 *

### 예제 4: 가격 표시

```html
<span class="price" data-price="10000">가격</span>
```

```css
.price::before {
    content: "₩";
}

.price::after {
    content: " (" attr(data-price) "원)";
    font-size: 0.8em;
    color: gray;
}
```

**결과:** ₩가격 (10000원)

### 예제 5: 아이콘과 텍스트 조합

```html
<button class="btn-icon">저장</button>
```

```css
.btn-icon {
    padding: 10px 20px;
    padding-left: 40px;
    position: relative;
    background-color: #27ae60;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.btn-icon::before {
    content: "💾";
    position: absolute;
    left: 10px;
    top: 50%;
    transform: translateY(-50%);
}
```

**결과:** 💾 저장 버튼

---

## 7️⃣ 주의사항

### 1. content 속성 필수

```css
/* ❌ 작동하지 않음 */
.box::before {
    color: red;
}

/* ✅ 올바른 사용 */
.box::before {
    content: "";
    color: red;
}
```

### 2. 인라인 요소 기본값

`::before`와 `::after`는 기본적으로 **인라인 요소**입니다.

```css
.box::before {
    content: "";
    width: 100px;  /* 인라인 요소는 width/height 적용 안 됨 */
    height: 100px;
}

/* ✅ block 또는 inline-block으로 변경 */
.box::before {
    content: "";
    display: block;  /* 또는 inline-block */
    width: 100px;
    height: 100px;
}
```

### 3. img, input 등에는 사용 불가

일부 요소는 `::before`와 `::after`를 사용할 수 없습니다:

- `<img>` (대체 요소)
- `<input>` (대체 요소)
- `<br>`
- `<hr>`

```html
<!-- ❌ 작동하지 않음 -->
<img src="photo.jpg" class="photo">

<!-- ✅ 작동함 -->
<div class="photo-wrapper">
    <img src="photo.jpg" class="photo">
</div>
```

### 4. z-index 사용

`::before`와 `::after`는 부모 요소의 스택 컨텍스트에 포함됩니다.

```css
.box {
    position: relative;
    z-index: 1;
}

.box::before {
    content: "";
    position: absolute;
    z-index: -1;  /* 부모보다 뒤에 배치 */
}
```

### 5. position: absolute 사용 시

```css
.box {
    position: relative;  /* 부모에 relative 필요 */
}

.box::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
}
```

---

## 8️⃣ ::before vs ::after 선택 가이드

### ::before를 사용하는 경우

- 요소의 **앞**에 콘텐츠를 추가할 때
- 아이콘이나 장식이 **왼쪽**에 올 때
- 번호나 라벨이 **앞**에 올 때

### ::after를 사용하는 경우

- 요소의 **뒤**에 콘텐츠를 추가할 때
- 아이콘이나 장식이 **오른쪽**에 올 때
- 툴팁이나 설명이 **뒤**에 올 때

### 둘 다 사용하는 경우

- 양쪽에 장식이 필요할 때
- 앞뒤로 구분선이 필요할 때
- 복잡한 장식 요소를 만들 때

---

## 9️⃣ 실전 활용 패턴

### 패턴 1: 장식용 도형

```css
.decorated::before {
    content: "";
    display: inline-block;
    width: 10px;
    height: 10px;
    background-color: #3498db;
    margin-right: 10px;
    border-radius: 50%;
}
```

### 패턴 2: 구분선

```css
.divider::before {
    content: "";
    display: block;
    width: 100%;
    height: 1px;
    background-color: #ddd;
    margin: 20px 0;
}
```

### 패턴 3: 아이콘 추가

```css
.external-link::after {
    content: " 🔗";
    font-size: 0.8em;
    opacity: 0.7;
}
```

### 패턴 4: 배경 오버레이

```css
.overlay::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 1;
}
```

---

## 🔟 종합 예제: 카드 컴포넌트

```html
<div class="card">
    <h3 class="card-title">카드 제목</h3>
    <p class="card-content">카드 내용입니다.</p>
</div>
```

```css
.card {
    position: relative;
    padding: 30px;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}

.card::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 4px;
    background: linear-gradient(90deg, #667eea 0%, #764ba2 100%);
}

.card-title::before {
    content: "📌 ";
}

.card-content::after {
    content: " →";
    color: #667eea;
    font-weight: bold;
}
```

---

## 📚 핵심 정리

### 필수 사항

1. ✅ **content 속성 필수**: 없으면 표시되지 않음
2. ✅ **인라인 요소**: 기본값이 inline이므로 block/inline-block 필요 시 변경
3. ✅ **position 사용**: absolute/fixed 사용 시 부모에 relative 필요

### 활용 팁

1. ✅ **HTML 수정 없이 콘텐츠 추가**: 순수 CSS로만 가능
2. ✅ **장식 요소**: 도형, 아이콘, 구분선 등
3. ✅ **동적 콘텐츠**: `attr()`로 HTML 속성 값 사용
4. ✅ **애니메이션**: transition, animation과 함께 사용

### 자주 사용하는 패턴

- 장식용 도형: `content: "";` + `display: block;` + 크기/색상
- 아이콘 추가: `content: "이모지";` 또는 `content: url();`
- 구분선: `content: "";` + `display: block;` + `border` 또는 `background`
- 툴팁: `content: attr();` + `position: absolute;` + `opacity`

---

## 📚 관련 자료

- [CSS Position 완전 정리](./CSS-Position-완전정리.md)
- [HTML/CSS 한방 정리](./HTML-CSS-한방정리.md)


