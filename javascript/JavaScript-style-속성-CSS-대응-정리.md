# JavaScript style 속성 정리 (CSS ↔ JS)

JavaScript에서 DOM 요소의 스타일을 바꿀 때 `element.style.속성` 을 사용합니다.

```javascript
let ele1 = document.getElementById("m1");
ele1.style.color = "red";
ele1.style.fontSize = "30px";
```

CSS 파일에 쓰는 이름과 **JavaScript에서 쓰는 이름이 다릅니다.**  
(하이픈 `-` → camelCase)

---

## 0. CSS ↔ JavaScript 이름 변환 (가장 중요) ⭐⭐⭐

CSS에서는 **하이픈(-)** 을 씁니다. JavaScript `style`에서는 **camelCase** 로 바꿉니다.

| CSS | JavaScript |
|-----|------------|
| `background-color` | `backgroundColor` |
| `font-size` | `fontSize` |
| `margin-top` | `marginTop` |
| `margin-left` | `marginLeft` |
| `padding-top` | `paddingTop` |
| `border-radius` | `borderRadius` |
| `text-align` | `textAlign` |
| `justify-content` | `justifyContent` |
| `align-items` | `alignItems` |
| `box-shadow` | `boxShadow` |

### 규칙

```text
font-size     →  fontSize      (하이픈 제거 + 다음 단어 첫 글자 대문자)
background-color  →  backgroundColor
```

> **`-`를 그대로 쓰면 동작하지 않습니다.**  
> `ele1.style.font-size = "20px"` ❌  
> `ele1.style.fontSize = "20px"` ✅

---

## 1. 위치 관련 ⭐

마우스 따라다니기 예제에서 **가장 먼저** 배우면 좋습니다.

| JavaScript | CSS | 설명 |
|------------|-----|------|
| `style.top` | `top` | 위에서 얼마나 떨어졌는지 |
| `style.left` | `left` | 왼쪽에서 얼마나 떨어졌는지 |
| `style.right` | `right` | 오른쪽에서 |
| `style.bottom` | `bottom` | 아래에서 |

```javascript
ele1.style.top = "100px";
ele1.style.left = "200px";
```

CSS:

```css
top: 100px;
left: 200px;
```

### ⚠️ `position` 이 필요합니다

`top`, `left`가 제대로 동작하려면 보통 **`position`** 을 함께 설정합니다.

```javascript
ele1.style.position = "absolute";
ele1.style.top = "100px";
ele1.style.left = "200px";
```

---

## 2. 크기 관련

| JavaScript | CSS | 설명 |
|------------|-----|------|
| `style.width` | `width` | 가로 크기 |
| `style.height` | `height` | 세로 크기 |
| `style.maxWidth` | `max-width` | 최대 가로 |
| `style.maxHeight` | `max-height` | 최대 세로 |
| `style.minWidth` | `min-width` | 최소 가로 |
| `style.minHeight` | `min-height` | 최소 세로 |

```javascript
ele1.style.width = "200px";
ele1.style.height = "100px";
```

---

## 3. 배경 관련

| JavaScript | CSS |
|------------|-----|
| `style.backgroundColor` | `background-color` |
| `style.backgroundImage` | `background-image` |
| `style.backgroundSize` | `background-size` |
| `style.backgroundPosition` | `background-position` |
| `style.backgroundRepeat` | `background-repeat` |

```javascript
ele1.style.backgroundColor = "skyblue";
```

CSS:

```css
background-color: skyblue;
```

---

## 4. 글자 관련 ⭐

| JavaScript | CSS | 설명 |
|------------|-----|------|
| `style.color` | `color` | 글자 색 |
| `style.fontSize` | `font-size` | 글자 크기 |
| `style.fontWeight` | `font-weight` | 굵기 |
| `style.fontFamily` | `font-family` | 글꼴 |
| `style.fontStyle` | `font-style` | 기울임 |
| `style.textAlign` | `text-align` | 정렬 |
| `style.lineHeight` | `line-height` | 줄 높이 |
| `style.textDecoration` | `text-decoration` | 밑줄 등 |

```javascript
ele1.style.color = "red";
ele1.style.fontSize = "30px";
ele1.style.fontWeight = "bold";
ele1.style.textAlign = "center";
```

---

## 5. 여백 관련 ⭐

| JavaScript | CSS | 설명 |
|------------|-----|------|
| `style.margin` | `margin` | 바깥 여백 |
| `style.marginTop` | `margin-top` | 위 |
| `style.marginRight` | `margin-right` | 오른쪽 |
| `style.marginBottom` | `margin-bottom` | 아래 |
| `style.marginLeft` | `margin-left` | 왼쪽 |
| `style.padding` | `padding` | 안쪽 여백 |
| `style.paddingTop` | `padding-top` | 위 |
| `style.paddingRight` | `padding-right` | 오른쪽 |
| `style.paddingBottom` | `padding-bottom` | 아래 |
| `style.paddingLeft` | `padding-left` | 왼쪽 |

```javascript
ele1.style.marginTop = "20px";
ele1.style.padding = "10px";
```

---

## 6. 테두리 관련

| JavaScript | CSS |
|------------|-----|
| `style.border` | `border` |
| `style.borderColor` | `border-color` |
| `style.borderWidth` | `border-width` |
| `style.borderStyle` | `border-style` |
| `style.borderRadius` | `border-radius` |

```javascript
ele1.style.border = "2px solid black";
ele1.style.borderRadius = "10px";
```

CSS:

```css
border: 2px solid black;
border-radius: 10px;
```

---

## 7. 화면 표시 관련 ⭐

| JavaScript | CSS | 설명 |
|------------|-----|------|
| `style.display` | `display` | 표시 방식 |
| `style.visibility` | `visibility` | 보이기/숨기기 (공간 유지) |
| `style.opacity` | `opacity` | 투명도 |
| `style.overflow` | `overflow` | 넘치는 내용 |

### `display`

```javascript
ele1.style.display = "none";   // 화면에서 사라짐 (아코디언 등)
ele1.style.display = "block";  // 다시 나타남
```

### `opacity`

```javascript
ele1.style.opacity = "0.5";  // 50% 투명
ele1.style.opacity = "1";    // 불투명
```

---

## 8. Flex 관련

CSS Flex를 JavaScript에서도 바꿀 수 있습니다.

```javascript
ele1.style.display = "flex";
ele1.style.justifyContent = "center";
ele1.style.alignItems = "center";
```

CSS:

```css
display: flex;
justify-content: center;
align-items: center;
```

| JavaScript | CSS |
|------------|-----|
| `style.display` | `display` |
| `style.flexDirection` | `flex-direction` |
| `style.justifyContent` | `justify-content` |
| `style.alignItems` | `align-items` |
| `style.flexWrap` | `flex-wrap` |
| `style.gap` | `gap` |

---

## 9. Position 관련 ⭐

```javascript
ele1.style.position = "absolute";
ele1.style.top = "100px";
ele1.style.left = "100px";
```

| 값 | 설명 |
|----|------|
| `static` | 기본 (top/left 거의 안 먹음) |
| `relative` | 원래 위치 기준 |
| `absolute` | 가까운 positioned 부모 기준 |
| `fixed` | 화면(뷰포트) 기준 고정 |
| `sticky` | 스크롤 시 붙음 |

`top`, `left`와 **함께** 씁니다.

---

## 10. 그림자

```javascript
ele1.style.boxShadow = "5px 5px 10px gray";
```

CSS:

```css
box-shadow: 5px 5px 10px gray;
```

---

## 11. 한 번에 여러 스타일 적용 — 원형 요소 예제

```javascript
let ele1 = document.getElementById("m1");

ele1.style.position = "absolute";
ele1.style.width = "100px";
ele1.style.height = "100px";
ele1.style.backgroundColor = "skyblue";
ele1.style.border = "2px solid black";
ele1.style.borderRadius = "50%";   // 원형
ele1.style.top = "100px";
ele1.style.left = "200px";
```

```text
       ┌───────────┐
       │   원형    │  ← left: 200px, top: 100px
       └───────────┘
```

`borderRadius: "50%"` + 같은 width/height → **원**

---

## 12. `cssText` — 한 줄로 여러 스타일

속성을 하나씩 넣는 대신:

```javascript
ele1.style.cssText = `
    position: absolute;
    width: 100px;
    height: 100px;
    background-color: skyblue;
    border-radius: 50%;
`;
```

> `cssText` 안에서는 **CSS 문법**(하이픈)을 쓸 수 있습니다.

---

## 13. 비전공자 우선 10개

| 순서 | JavaScript | 용도 |
|------|------------|------|
| 1 | `position` + `top` / `left` | 위치 (마우스 따라가기) |
| 2 | `width` / `height` | 크기 |
| 3 | `backgroundColor` | 배경색 |
| 4 | `color` / `fontSize` | 글자 |
| 5 | `margin` / `padding` | 여백 |
| 6 | `display` | 보이기/숨기기 |
| 7 | `opacity` | 투명도 |
| 8 | `border` / `borderRadius` | 테두리·둥글게 |
| 9 | `justifyContent` / `alignItems` | Flex 정렬 |
| 10 | camelCase 변환 규칙 | `-` → 대문자 |

---

## 14. 치트시트

```javascript
const el = document.getElementById("box");

// 위치
el.style.position = "absolute";
el.style.top = "100px";
el.style.left = "200px";

// 크기 · 배경
el.style.width = "200px";
el.style.height = "100px";
el.style.backgroundColor = "skyblue";

// 글자
el.style.color = "white";
el.style.fontSize = "20px";
el.style.textAlign = "center";

// 숨기기
el.style.display = "none";
el.style.display = "block";

// Flex
el.style.display = "flex";
el.style.justifyContent = "center";
el.style.alignItems = "center";
```

### 한 줄 요약

> CSS는 `font-size`, JS는 `fontSize`.  
> **위치는 `position` + `top`/`left`**, **숨기기는 `display: none`**.

---

## 관련 자료

- [11장_DOM_조작.md](./11장_DOM_조작.md) — `getElementById`, `style`
- [html/CSS-Background-옵션-정리.md](../html/CSS-Background-옵션-정리.md)
- [12장_이벤트.md](./12장_이벤트.md) — 마우스 이벤트
