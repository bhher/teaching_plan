# Ex07FlexGrid 해설 — Tailwind Flex vs Grid

`Ex07FlexGrid`는 Tailwind CSS에서 **Flex와 Grid의 차이**를 한 번에 보여주는 React 컴포넌트입니다.  
수업용으로는 아래처럼 이해하면 가장 쉽습니다.

관련 파일: `react/tailwind-examples/src/examples/Ex07FlexGrid.jsx`

---

## 1. 전체 구조

```jsx
export default function Ex07FlexGrid() {
  return (
    <div className="space-y-8">
      ...
    </div>
  );
}
```

| 항목 | 의미 |
|------|------|
| `Ex07FlexGrid` | React 컴포넌트 |
| `export default` | 다른 파일에서 `import`해서 사용 가능 |
| `space-y-8` | 자식 요소들 사이 **세로 간격 2rem(32px)** |

배치 순서: **제목 → Flex 예제 → Flex 정렬 예제 → Grid 예제** (세로로 쌓임)

---

## 2. Flex와 Grid의 핵심 차이

| Flex | Grid |
|------|------|
| **1차원** 배치 | **2차원** 배치 |
| 가로 **또는** 세로 중심 | **행 + 열** |
| 요소를 한 줄로 배치할 때 편리 | 표 형태의 레이아웃에 편리 |
| `flex` | `grid` |
| `justify-*`, `items-*` | `grid-cols-*`, `col-span-*` |

쉽게 말하면:

- **Flex** = 일렬로 정렬하기  
- **Grid** = 칸을 만들어 배치하기  

---

## 3. 첫 번째 Flex 예제

```jsx
<div className="flex flex-wrap gap-4">
```

중요한 클래스 3개:

### `flex`

```css
display: flex;
```

- 부모를 Flex 컨테이너로 만듦  
- 기본적으로 자식이 **가로**로 배치됨  

```
A    B    C
```

### `gap-4`

```css
gap: 1rem; /* 16px */
```

자식 사이 간격:

```
A  ← 16px →  B  ← 16px →  C
```

### `flex-wrap`

기본 Flex는 공간이 부족해도 한 줄에 계속 넣으려 함:

```
A B C D E
```

`flex-wrap`을 쓰면 공간이 부족할 때 **다음 줄**로 넘어감:

```
A B C
D E
```

정리:

> `flex` + `flex-wrap`  
> = 가로로 배치하다가 공간이 부족하면 다음 줄로 내려보내라

---

## 4. `flex-1`이 중요한 부분

```jsx
<div className="flex-1 ...">A</div>
<div className="flex-1 ...">B</div>
<div className="flex-1 ...">C</div>
```

`flex-1` = **남는 공간을 똑같이 나눠 가져라**

부모가 넓을 때(예: 900px, gap 제외):

```
┌──────────┬──────────┬──────────┐
│    A     │    B     │    C     │
└──────────┴──────────┴──────────┘
```

A, B, C가 가능한 한 **동일한 너비**를 가짐.

---

## 5. `min-w-[120px]`

```jsx
min-w-[120px]
```

```css
min-width: 120px;
```

아무리 공간이 부족해도 **최소 너비 120px** 유지.

공간 충분할 때:

```
┌────────┬────────┬────────┐
│   A    │   B    │   C    │
└────────┴────────┴────────┘
```

화면이 좁아지면 `flex-wrap` 때문에:

```
┌────────┬────────┐
│   A    │   B    │
└────────┴────────┘
┌────────┐
│   C    │
└────────┘
```

### 수업 포인트 (네 가지 세트)

| 클래스 | 역할 |
|--------|------|
| `flex-1` | 남은 공간을 나눠 가짐 |
| `min-w-[120px]` | 최소 120px |
| `flex-wrap` | 공간 부족하면 다음 줄 |
| `gap-4` | 요소 사이 간격 |

이 네 개를 같이 기억하면 됩니다.

---

## 6. 박스 스타일 클래스

```jsx
<div className="flex-1 rounded-lg bg-teal-100 p-4 text-center min-w-[120px]">
  A
</div>
```

| 클래스 | 의미 |
|--------|------|
| `flex-1` | 남은 공간 균등 분배 |
| `rounded-lg` | 모서리 둥글게 |
| `bg-teal-100` | 배경색 |
| `p-4` | 안쪽 여백 |
| `text-center` | 글자 가운데 정렬 |
| `min-w-[120px]` | 최소 너비 120px |

---

## 7. 두 번째 Flex 예제 — 정렬

```jsx
<div className="flex items-center justify-between rounded-lg bg-slate-50 p-4">
```

내용 구조: **왼쪽 · 가운데 뱃지 · 오른쪽 버튼**

### `items-center`

```css
align-items: center;
```

교차축(기본 `row`일 때 **세로**) 가운데 정렬.  
높이가 달라도 세로 가운데가 맞음.

### `justify-between`

```css
justify-content: space-between;
```

주축(기본 `row`일 때 **가로**)으로 양끝·사이 공간 배분:

```
┌─────────────────────────────────────────────┐
│ 왼쪽       가운데 뱃지                 오른쪽 │
└─────────────────────────────────────────────┘
```

### 수업에서 꼭 강조할 부분

| 접두사 | 축 | 기본 `flex-row`일 때 |
|--------|-----|----------------------|
| `justify-*` | **주축** (main axis) | **가로** |
| `items-*` | **교차축** (cross axis) | **세로** |

---

## 8. 세 번째 — Grid

```jsx
<div className="grid grid-cols-3 gap-4">
```

### `grid`

```css
display: grid;
```

### `grid-cols-3`

```css
grid-template-columns: repeat(3, minmax(0, 1fr));
```

쉽게 말하면: **3개의 열을 만들어라**

이상적인 6칸 구조:

```
┌──────┬──────┬──────┐
│  1   │  2   │  3   │
├──────┼──────┼──────┤
│  4   │  5   │  6   │
└──────┴──────┴──────┘
```

이 예제는 요소가 5개이고 그중 하나가 `col-span-2`라서:

```
┌──────┬──────┬──────┐
│  1   │  2   │  3   │
├──────┴──────┼──────┤
│  col-span-2 │  5   │
└─────────────┴──────┘
```

---

## 9. `col-span-2`가 핵심

```jsx
<div className="col-span-2 ...">
  col-span-2
</div>
```

**2개의 열을 차지해라.**

3열 Grid에서:

```
1열    2열    3열
 ↓      ↓      ↓
┌──────┬──────┬──────┐
│      │      │      │
└──────┴──────┴──────┘
```

`col-span-2` 적용 후:

```
┌───────────────────┬──────┐
│     col-span-2    │  5   │
└───────────────────┴──────┘
      2칸              1칸
```

---

## 10. Flex vs Grid — 언제 쓰나?

### Flex

```jsx
<div className="flex gap-4">
```

```
A    B    C
```

**요소를 어떻게 정렬할까?** 에 강함.

예:

- 로고 · 메뉴 · 로그인  
- 아이콘 · 텍스트  

### Grid

```jsx
<div className="grid grid-cols-3 gap-4">
```

```
┌─────┬─────┬─────┐
│  A  │  B  │  C  │
├─────┼─────┼─────┤
│  D  │  E  │  F  │
└─────┴─────┴─────┘
```

**페이지를 몇 칸으로 나눠서 배치할까?** 에 강함.

예: 상품 카드 목록

```
┌─────┬─────┬─────┐
│상품1│상품2│상품3│
├─────┼─────┼─────┤
│상품4│상품5│상품6│
└─────┴─────┴─────┘
```

---

## 학생용 한 줄 요약

> **Flex**는 “아이템들을 어떻게 정렬할까?”에 집중하고,  
> **Grid**는 “페이지를 몇 칸으로 나눠서 배치할까?”에 집중합니다.

---

## 암기 체크리스트

### Flex

```
flex
flex-row
flex-col
flex-wrap
gap-4
flex-1

justify-center
justify-between
justify-around
justify-end

items-center
items-start
items-end
```

### Grid

```
grid
grid-cols-2
grid-cols-3
grid-cols-4
gap-4

col-span-2
col-span-3
```

### 이 예제에서 특히 강조할 5개

1. `flex-1`  
2. `justify-between`  
3. `items-center`  
4. `grid-cols-3`  
5. `col-span-2`  
