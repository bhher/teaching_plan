# Flexbox 카드 레이아웃 코드 설명

## 📐 전체 구조

```
┌─────────────────────────────────────────┐
│              body (배경)                  │
│  ┌───────────────────────────────────┐  │
│  │      .container (중앙 정렬)         │  │
│  │  ┌─────────────────────────────┐  │  │
│  │  │  h1 (제목)                   │  │  │
│  │  └─────────────────────────────┘  │  │
│  │  ┌─────────────────────────────┐  │  │
│  │  │  .card-container (Flex)      │  │  │
│  │  │  ┌────┐ ┌────┐ ┌────┐ ┌────┐│  │  │
│  │  │  │카드1│ │카드2│ │카드3│ │카드4││  │  │
│  │  │  └────┘ └────┘ └────┘ └────┘│  │  │
│  │  └─────────────────────────────┘  │  │
│  └───────────────────────────────────┘  │
└─────────────────────────────────────────┘
```

---

## 🎨 CSS 코드 상세 설명

### 1. 리셋 스타일 (8-12줄)

```css
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
```

**설명:**
- `*`: 모든 요소에 적용
- `margin: 0; padding: 0;`: 브라우저 기본 여백 제거
- `box-sizing: border-box;`: 패딩과 테두리를 포함한 크기 계산
  - 예: `width: 280px` = 내용 + 패딩 + 테두리 = 280px

---

### 2. body 스타일 (14-19줄)

```css
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    min-height: 100vh;
    padding: 40px 20px;
}
```

**설명:**
- `font-family`: 시스템 폰트 사용
- `background: linear-gradient()`: 135도 각도 그라데이션 배경
  - 시작: 보라색 (#667eea)
  - 끝: 진한 보라색 (#764ba2)
- `min-height: 100vh`: 최소 높이를 뷰포트 높이로 설정
- `padding`: 상하 40px, 좌우 20px 여백

---

### 3. 컨테이너 (21-24줄)

```css
.container {
    max-width: 1200px;
    margin: 0 auto;
}
```

**설명:**
- `max-width: 1200px`: 최대 너비 1200px (큰 화면에서도 너무 넓어지지 않음)
- `margin: 0 auto;`: 상하 0, 좌우 자동 → **중앙 정렬**
  - 좌우 마진을 자동으로 계산하여 가운데 배치

---

### 4. 제목 스타일 (26-32줄)

```css
h1 {
    text-align: center;
    color: white;
    margin-bottom: 40px;
    font-size: 2.5rem;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}
```

**설명:**
- `text-align: center`: 텍스트 중앙 정렬
- `color: white`: 흰색 텍스트
- `text-shadow`: 텍스트 그림자 효과
  - 오른쪽 2px, 아래 2px
  - 흐림 4px
  - 검은색 30% 투명도

---

### 5. ⭐ Flexbox 컨테이너 (34-40줄)

```css
.card-container {
    display: flex;              /* Flexbox 활성화 */
    flex-wrap: wrap;            /* 줄바꿈 허용 */
    gap: 30px;                  /* 카드 간 간격 */
    justify-content: center;    /* 주축(가로) 중앙 정렬 */
    align-items: stretch;       /* 교차축(세로) 늘리기 */
}
```

**설명:**

#### `display: flex`
- Flexbox 레이아웃 활성화
- 자식 요소들이 Flex Item이 됨

#### `flex-wrap: wrap`
- 공간이 부족하면 다음 줄로 이동
- `nowrap` (기본값): 줄바꿈 안 함, 아이템이 축소됨
- `wrap`: 줄바꿈 허용 ✅

#### `gap: 30px`
- 카드 사이 간격 30px
- `margin` 대신 사용 (더 간단하고 깔끔함)

#### `justify-content: center`
- **주축(가로) 방향** 정렬
- 카드들을 가로 중앙에 배치

#### `align-items: stretch`
- **교차축(세로) 방향** 정렬
- 모든 카드의 높이를 동일하게 맞춤

**시각화:**
```
justify-content: center (주축 - 가로)
┌─────────────────────────────────────┐
│    [카드1] [카드2] [카드3] [카드4]    │ ← 가로 중앙
└─────────────────────────────────────┘

align-items: stretch (교차축 - 세로)
┌─────┐
│카드1 │ ← 모든 카드 높이 동일
├─────┤
│카드2 │
├─────┤
│카드3 │
├─────┤
│카드4 │
└─────┘
```

---

### 6. ⭐ 카드 스타일 (42-52줄)

```css
.card {
    flex: 1 1 250px;           /* Flex 속성 */
    max-width: 280px;          /* 최대 너비 */
    background: white;          /* 배경색 */
    border-radius: 15px;        /* 둥근 모서리 */
    overflow: hidden;          /* 넘치는 부분 숨김 */
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);  /* 그림자 */
    transition: transform 0.3s ease, box-shadow 0.3s ease;  /* 애니메이션 */
    display: flex;             /* 카드 내부도 Flex */
    flex-direction: column;    /* 세로 방향 배치 */
}
```

**설명:**

#### `flex: 1 1 250px` (단축 속성)
- `flex-grow: 1` → 남은 공간을 균등 분배
- `flex-shrink: 1` → 공간 부족 시 축소 가능
- `flex-basis: 250px` → 기본 크기 250px

**예시:**
```
화면 너비 1200px, 카드 4개
- 각 카드 기본: 250px
- 남은 공간: 1200 - (250×4) - (30×3 gap) = 330px
- 각 카드에 추가: 330 ÷ 4 = 82.5px
- 최종 크기: 약 332px (하지만 max-width: 280px 제한)
```

#### `max-width: 280px`
- 카드의 최대 너비 제한
- 너무 커지지 않도록 방지

#### `border-radius: 15px`
- 모서리를 15px 반지름으로 둥글게

#### `overflow: hidden`
- 카드 내부 요소가 카드 영역을 벗어나면 숨김
- 이미지가 카드 모서리를 넘지 않도록

#### `box-shadow`
- 그림자 효과
- `0 10px 30px`: 오른쪽 0, 아래 10px, 흐림 30px
- `rgba(0, 0, 0, 0.3)`: 검은색 30% 투명도

#### `display: flex` + `flex-direction: column`
- 카드 내부도 Flexbox 사용
- 세로 방향으로 배치 (이미지 → 제목 → 설명 → 버튼)

**카드 내부 구조:**
```
┌─────────────────┐
│   이미지         │ ← flex-start
├─────────────────┤
│   제목           │
├─────────────────┤
│   설명           │ ← flex: 1 (남은 공간 차지)
├─────────────────┤
│   버튼           │ ← flex-end
└─────────────────┘
```

---

### 7. 호버 효과 (54-57줄)

```css
.card:hover {
    transform: translateY(-10px);  /* 위로 10px 이동 */
    box-shadow: 0 15px 40px rgba(0, 0, 0, 0.4);  /* 그림자 강화 */
}
```

**설명:**
- 마우스를 올리면 카드가 위로 10px 이동
- 그림자가 더 진하고 크게 변경
- `transition` 속성으로 부드러운 애니메이션

---

### 8. 이미지 스타일 (59-64줄)

```css
.card-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
    display: block;
}
```

**설명:**
- `width: 100%`: 카드 너비에 맞춤
- `height: 200px`: 고정 높이
- `object-fit: cover`: 비율 유지하며 영역 채움
  - 이미지가 잘릴 수 있지만 영역을 완전히 채움
- `display: block`: 인라인 요소의 하단 여백 제거

**object-fit 비교:**
```
cover:     [이미지가 영역을 완전히 채움, 잘릴 수 있음]
contain:   [이미지 전체가 보임, 여백 생길 수 있음]
fill:      [비율 무시하고 늘림]
```

---

### 9. 카드 내용 영역 (66-71줄)

```css
.card-content {
    padding: 20px;
    flex: 1;
    display: flex;
    flex-direction: column;
}
```

**설명:**
- `padding: 20px`: 내부 여백
- `flex: 1`: 남은 공간을 모두 차지
- `display: flex` + `flex-direction: column`: 세로 배치

**역할:**
- 이미지 아래 공간을 모두 차지
- 내부 요소들을 세로로 배치

---

### 10. 제목 스타일 (73-78줄)

```css
.card-title {
    font-size: 1.5rem;
    font-weight: bold;
    color: #333;
    margin-bottom: 10px;
}
```

**설명:**
- `font-size: 1.5rem`: 기본 폰트 크기의 1.5배
- `font-weight: bold`: 굵은 글씨
- `color: #333`: 진한 회색
- `margin-bottom: 10px`: 아래 여백

---

### 11. 설명 스타일 (80-85줄)

```css
.card-description {
    color: #666;
    line-height: 1.6;
    margin-bottom: 15px;
    flex: 1;
}
```

**설명:**
- `color: #666`: 중간 회색
- `line-height: 1.6`: 줄 간격 (가독성 향상)
- `flex: 1`: 남은 공간 차지
  - 버튼이 항상 하단에 위치하도록

---

### 12. 버튼 스타일 (87-98줄)

```css
.card-button {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    border: none;
    padding: 12px 24px;
    border-radius: 8px;
    cursor: pointer;
    font-size: 1rem;
    font-weight: 600;
    transition: opacity 0.3s ease;
    align-self: flex-start;
}
```

**설명:**
- `background: linear-gradient()`: 그라데이션 배경
- `align-self: flex-start`: 버튼만 왼쪽 정렬
  - 부모의 `align-items`와 독립적으로 정렬
- `cursor: pointer`: 마우스 포인터 변경
- `transition`: 호버 시 부드러운 변화

---

### 13. 반응형 디자인 (104-126줄)

#### 모바일 (768px 이하)

```css
@media (max-width: 768px) {
    .card-container {
        flex-direction: column;  /* 세로 배치 */
        align-items: center;     /* 중앙 정렬 */
    }

    .card {
        max-width: 100%;         /* 전체 너비 */
        width: 100%;
    }

    h1 {
        font-size: 2rem;         /* 제목 크기 축소 */
    }
}
```

**설명:**
- 화면이 작으면 카드를 세로로 배치
- 각 카드가 전체 너비 사용

**레이아웃 변화:**
```
데스크톱:  [카드1] [카드2] [카드3] [카드4]
모바일:    [카드1]
           [카드2]
           [카드3]
           [카드4]
```

#### 태블릿 (769px ~ 1024px)

```css
@media (min-width: 769px) and (max-width: 1024px) {
    .card {
        flex: 1 1 calc(50% - 30px);  /* 2열 배치 */
        max-width: calc(50% - 30px);
    }
}
```

**설명:**
- 2열로 배치
- `calc(50% - 30px)`: 50% 너비에서 gap(30px) 제외

---

## 📊 Flexbox 속성 요약

### 컨테이너 속성 (부모)

| 속성 | 값 | 설명 |
|------|-----|------|
| `display` | `flex` | Flexbox 활성화 |
| `flex-wrap` | `wrap` | 줄바꿈 허용 |
| `gap` | `30px` | 아이템 간 간격 |
| `justify-content` | `center` | 주축(가로) 중앙 정렬 |
| `align-items` | `stretch` | 교차축(세로) 늘리기 |

### 아이템 속성 (자식)

| 속성 | 값 | 설명 |
|------|-----|------|
| `flex` | `1 1 250px` | 성장/축소/기본크기 |
| `max-width` | `280px` | 최대 너비 제한 |
| `align-self` | `flex-start` | 개별 정렬 (버튼) |

---

## 🎯 핵심 포인트

### 1. 이중 Flexbox 구조
```
.card-container (Flex) → 카드들을 가로 배치
    └─ .card (Flex, column) → 카드 내부를 세로 배치
```

### 2. 반응형 원리
- `flex-wrap: wrap`: 공간 부족 시 자동 줄바꿈
- `flex: 1 1 250px`: 유연한 크기 조절
- `@media`: 화면 크기에 따른 스타일 변경

### 3. 카드 높이 통일
- `align-items: stretch`: 모든 카드 높이 동일
- `.card-content`에 `flex: 1`: 내용 영역이 남은 공간 차지
- 버튼이 항상 하단에 위치

### 4. 시각적 효과
- `box-shadow`: 입체감
- `border-radius`: 모던한 느낌
- `transition`: 부드러운 애니메이션
- `hover`: 인터랙티브 효과

---

## 🔄 실행 흐름

1. **페이지 로드**
   - body에 그라데이션 배경 적용
   - container가 중앙에 배치

2. **카드 배치**
   - `.card-container`가 Flexbox로 활성화
   - 카드들이 가로로 배치
   - 공간이 부족하면 자동 줄바꿈

3. **카드 내부**
   - 각 카드가 세로 Flexbox
   - 이미지 → 제목 → 설명 → 버튼 순서

4. **반응형**
   - 화면 크기에 따라 레이아웃 변경
   - 모바일: 세로 배치
   - 태블릿: 2열
   - 데스크톱: 4열

5. **인터랙션**
   - 마우스 호버 시 카드 상승
   - 버튼 호버 시 투명도 변경

---

## 💡 학습 포인트

### ✅ 이해해야 할 개념
1. **Flexbox 기본**
   - `display: flex`로 활성화
   - 주축과 교차축 개념

2. **flex 속성**
   - `flex: grow shrink basis`
   - 유연한 크기 조절

3. **정렬 속성**
   - `justify-content`: 주축 정렬
   - `align-items`: 교차축 정렬
   - `align-self`: 개별 정렬

4. **반응형 디자인**
   - `flex-wrap`: 자동 줄바꿈
   - `@media`: 미디어 쿼리

### ✅ 실전 팁
1. **gap 사용**: `margin` 대신 `gap`으로 간격 관리
2. **flex: 1**: 남은 공간을 차지하는 요소에 사용
3. **이중 Flexbox**: 외부와 내부 모두 Flexbox 활용
4. **object-fit: cover**: 이미지 비율 유지하며 채우기
