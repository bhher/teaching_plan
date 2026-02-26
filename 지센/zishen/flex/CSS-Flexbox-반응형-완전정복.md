# CSS Flexbox 반응형 레이아웃 - 완전 정복

## 📋 목차
1. [개요](#개요)
2. [Flexbox 기본 개념](#flexbox-기본-개념)
3. [반응형 Flexbox 구현](#반응형-flexbox-구현)
4. [미디어 쿼리별 상세 설명](#미디어-쿼리별-상세-설명)
5. [Grid vs Flexbox 비교](#grid-vs-flexbox-비교)
6. [핵심 개념](#핵심-개념)

---

## 개요

이 예제는 **CSS Flexbox**를 사용하여 반응형 레이아웃을 구현한 것입니다. 화면 크기에 따라 flex 아이템의 너비가 자동으로 조정됩니다.

**주요 특징:**
- ✅ 데스크톱: 3열 레이아웃
- ✅ 태블릿: 2열 레이아웃
- ✅ 모바일: 1열 레이아웃
- ✅ Footer도 반응형 Flexbox 적용

**반응형 브레이크포인트:**
- 1200px 이하: gap 조정
- 1024px 이하: 3열 → 2열
- 768px 이하: 2열 → 1열
- 480px 이하: 폰트 크기 및 패딩 조정

---

## Flexbox 기본 개념

### 1. display: flex

```css
.items-flex {
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
}
```

**설명:**
- `display: flex`: Flexbox 레이아웃 활성화
- `flex-wrap: wrap`: 아이템이 넘치면 다음 줄로 이동
- `gap`: 아이템 간 간격

### 2. flex 속성

```css
.item {
    flex: 0 0 calc(33.333% - 20px);
}
```

**flex 속성 문법:**
```css
flex: [flex-grow] [flex-shrink] [flex-basis];
```

**설명:**
- `flex-grow`: 남은 공간을 채울 비율 (0 = 증가 안함)
- `flex-shrink`: 공간이 부족할 때 줄어들 비율 (0 = 줄어들지 않음)
- `flex-basis`: 기본 크기

**예시:**
```css
flex: 0 0 calc(33.333% - 20px);
/* grow: 0, shrink: 0, basis: 33.333% - gap */
```

### 3. calc() 함수

```css
flex: 0 0 calc(33.333% - 20px);
```

**설명:**
- CSS 계산 함수
- `calc(33.333% - 20px)`: 33.333%에서 20px을 뺀 값
- gap을 고려한 정확한 너비 계산

---

## 반응형 Flexbox 구현

### 전체 반응형 구조

```css
/* 기본: 3열 (데스크톱) */
.items-flex {
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
}

.items-flex .item {
    flex: 0 0 calc(33.333% - 20px); /* 3열 */
}

/* 태블릿: 2열 */
@media screen and (max-width: 1024px) {
    .items-flex .item {
        flex: 0 0 calc(50% - 12.5px); /* 2열 */
    }
}

/* 모바일: 1열 */
@media screen and (max-width: 768px) {
    .items-flex {
        flex-direction: column;
    }
    
    .items-flex .item {
        flex: 1 1 100%; /* 1열 */
    }
}
```

---

## 미디어 쿼리별 상세 설명

### 1. 기본 스타일 (1200px 초과)

```css
.items-flex {
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
    justify-content: space-between;
}

.items-flex .item {
    flex: 0 0 calc(33.333% - 20px);
    min-width: 0;
}
```

**설명:**
- `flex-wrap: wrap`: 줄바꿈 허용
- `justify-content: space-between`: 양쪽 정렬
- `flex: 0 0 calc(33.333% - 20px)`: 3열, 각각 33.333% 너비
- `calc(33.333% - 20px)`: gap(30px)의 2/3를 빼서 정확한 너비 계산

**레이아웃:**
```
┌─────────┬─────────┬─────────┐
│  Item 1 │  Item 2 │  Item 3 │
└─────────┴─────────┴─────────┘
```

---

### 2. 1200px 이하 (태블릿 - gap 조정)

```css
@media screen and (max-width: 1200px) {
    .items-flex {
        gap: 25px;
    }
    
    .items-flex .item {
        flex: 0 0 calc(33.333% - 17px);
    }
}
```

**설명:**
- 열 개수는 그대로 3열 유지
- gap만 약간 줄여서 공간 효율성 향상

---

### 3. 1024px 이하 (태블릿 - 2열)

```css
@media screen and (max-width: 1024px) {
    .items-flex .item {
        flex: 0 0 calc(50% - 12.5px); /* 2열로 변경 */
    }
    
    .section-title {
        font-size: 28px;
        margin-bottom: 40px;
    }
    
    .best-items,
    .new-items {
        padding: 60px 20px;
    }
    
    /* Footer: 2열 */
    .footer-content {
        gap: 30px;
    }
    
    .footer-about {
        flex: 1 1 100%; /* 첫 번째 행 전체 차지 */
    }
    
    .footer-links,
    .footer-info,
    .footer-contact {
        flex: 1 1 calc(50% - 15px); /* 2열 */
    }
}
```

**레이아웃:**
```
┌─────────┬─────────┐
│  Item 1 │  Item 2 │
├─────────┴─────────┤
│      Item 3       │
└───────────────────┘
```

**핵심 개념:**

1. **flex: 0 0 calc(50% - 12.5px)**
   - 3열에서 2열로 변경
   - 각 아이템이 50% 너비 (gap 고려)

2. **flex: 1 1 100%**
   ```css
   .footer-about {
       flex: 1 1 100%;
   }
   ```
   - `flex-grow: 1`: 남은 공간 채움
   - `flex-shrink: 1`: 줄어들 수 있음
   - `flex-basis: 100%`: 기본 100% 너비
   - Footer의 첫 번째 섹션이 전체 너비를 차지

---

### 4. 768px 이하 (모바일 - 1열)

```css
@media screen and (max-width: 768px) {
    .items-flex {
        flex-direction: column; /* 세로 방향 */
        gap: 30px;
        max-width: 400px;
        margin: 0 auto;
    }
    
    .items-flex .item {
        flex: 1 1 100%; /* 1열: 전체 너비 */
    }
    
    /* Footer: 1열 */
    .footer-content {
        flex-direction: column;
        gap: 30px;
    }
    
    .footer-about,
    .footer-links,
    .footer-info,
    .footer-contact {
        flex: 1 1 100%; /* 1열: 전체 너비 */
    }
}
```

**레이아웃:**
```
┌─────────┐
│  Item 1 │
├─────────┤
│  Item 2 │
├─────────┤
│  Item 3 │
└─────────┘
```

**핵심 개념:**

1. **flex-direction: column**
   - 기본값은 `row` (가로 방향)
   - `column`으로 변경하면 세로 방향 배치

2. **flex: 1 1 100%**
   - 모든 아이템이 100% 너비
   - 세로로 쌓임

3. **max-width: 400px**
   - 모바일에서 너무 넓게 보이지 않도록 최대 너비 제한

---

### 5. 480px 이하 (작은 모바일)

```css
@media screen and (max-width: 480px) {
    .section-title {
        font-size: 20px;
        letter-spacing: 1px;
        margin-bottom: 25px;
    }
    
    .best-items,
    .new-items {
        padding: 40px 15px;
    }
    
    .item {
        border-radius: 6px;
    }
    
    .item-info {
        padding: 15px;
    }
    
    .btn-view {
        padding: 10px 25px;
        font-size: 13px;
    }
    
    .footer {
        padding: 30px 15px 15px;
    }
    
    .footer-section h3 {
        font-size: 18px;
        margin-bottom: 15px;
    }
    
    .footer-section h4 {
        font-size: 14px;
        margin-bottom: 15px;
    }
    
    .footer-about p,
    .footer-contact p {
        font-size: 13px;
    }
    
    .footer-links a,
    .footer-info a {
        font-size: 13px;
    }
    
    .footer-bottom p {
        font-size: 12px;
    }
}
```

**설명:**
- 폰트 크기 더 작게 조정
- 패딩 더 작게 조정
- 작은 화면에 최적화

---

## Grid vs Flexbox 비교

### Grid 방식

```css
.items-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 30px;
}

@media screen and (max-width: 1024px) {
    .items-grid {
        grid-template-columns: repeat(2, 1fr);
    }
}
```

**특징:**
- 2차원 레이아웃 (행+열)
- 열 개수만 변경하면 됨
- 간단하고 직관적

### Flexbox 방식

```css
.items-flex {
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
}

.items-flex .item {
    flex: 0 0 calc(33.333% - 20px);
}

@media screen and (max-width: 1024px) {
    .items-flex .item {
        flex: 0 0 calc(50% - 12.5px);
    }
}
```

**특징:**
- 1차원 레이아웃 (행 또는 열)
- 각 아이템의 flex 속성 변경 필요
- calc()로 gap 고려한 계산 필요

---

## 핵심 개념

### 1. flex 속성의 세 가지 값

```css
flex: [grow] [shrink] [basis];
```

**예시:**
```css
flex: 0 0 calc(33.333% - 20px);
/* grow: 0, shrink: 0, basis: calc(33.333% - 20px) */
```

**설명:**
- `grow: 0`: 남은 공간을 채우지 않음 (고정 크기)
- `shrink: 0`: 공간이 부족해도 줄어들지 않음
- `basis: calc(33.333% - 20px)`: 기본 크기 (gap 고려)

---

### 2. flex-wrap

```css
flex-wrap: wrap;
```

**설명:**
- 아이템이 컨테이너를 넘치면 다음 줄로 이동
- `nowrap`: 줄바꿈 없음 (기본값)
- `wrap`: 줄바꿈 허용
- `wrap-reverse`: 역순으로 줄바꿈

---

### 3. flex-direction

```css
flex-direction: column;
```

**설명:**
- Flexbox의 주축 방향 설정
- `row`: 가로 방향 (기본값)
- `column`: 세로 방향
- `row-reverse`: 가로 역순
- `column-reverse`: 세로 역순

---

### 4. calc() 함수로 gap 고려

```css
flex: 0 0 calc(33.333% - 20px);
```

**계산 과정:**
- 3열이므로 각각 33.333%
- gap이 30px이므로 각 아이템 양쪽에 15px씩
- 하지만 실제로는 gap이 아이템 사이에만 있으므로:
  - 첫 번째와 마지막 아이템은 한쪽에만 gap
  - 중간 아이템은 양쪽에 gap
  - 평균적으로 각 아이템에 20px (30px의 2/3) 차감

**정확한 계산:**
```css
/* 3열, gap 30px */
flex: 0 0 calc(33.333% - 20px);
/* 33.333% - (30px * 2/3) = 33.333% - 20px */

/* 2열, gap 25px */
flex: 0 0 calc(50% - 12.5px);
/* 50% - (25px * 1/2) = 50% - 12.5px */
```

---

### 5. justify-content

```css
justify-content: space-between;
```

**설명:**
- 주축(main axis) 방향 정렬
- `flex-start`: 시작점 정렬
- `flex-end`: 끝점 정렬
- `center`: 중앙 정렬
- `space-between`: 양쪽 정렬 (사이 간격 동일)
- `space-around`: 주변 간격 동일
- `space-evenly`: 모든 간격 동일

---

### 6. min-width: 0

```css
.item {
    flex: 0 0 calc(33.333% - 20px);
    min-width: 0;
}
```

**설명:**
- Flexbox 아이템의 기본 `min-width`는 `auto`
- 내용이 길면 아이템이 줄어들지 않음
- `min-width: 0`으로 설정하면 내용에 맞춰 줄어들 수 있음

---

## 실전 예제

### Items Flexbox 반응형

```css
/* 기본: 3열 */
.items-flex {
    display: flex;
    flex-wrap: wrap;
    gap: 30px;
}

.items-flex .item {
    flex: 0 0 calc(33.333% - 20px);
}

/* 태블릿: 2열 */
@media screen and (max-width: 1024px) {
    .items-flex .item {
        flex: 0 0 calc(50% - 12.5px);
    }
}

/* 모바일: 1열 */
@media screen and (max-width: 768px) {
    .items-flex {
        flex-direction: column;
    }
    
    .items-flex .item {
        flex: 1 1 100%;
    }
}
```

**시각적 변화:**

```
데스크톱 (>1024px):
┌─────┬─────┬─────┐
│  1  │  2  │  3  │
└─────┴─────┴─────┘

태블릿 (768px~1024px):
┌─────┬─────┐
│  1  │  2  │
├─────┴─────┤
│     3     │
└───────────┘

모바일 (<768px):
┌─────┐
│  1  │
├─────┤
│  2  │
├─────┤
│  3  │
└─────┘
```

---

### Footer Flexbox 반응형

```css
/* 기본: 4열 */
.footer-content {
    display: flex;
    flex-wrap: wrap;
    gap: 40px;
}

.footer-about {
    flex: 2 1 300px; /* 2배 크기 */
}

.footer-links,
.footer-info {
    flex: 1 1 200px; /* 1배 크기 */
}

.footer-contact {
    flex: 1.5 1 250px; /* 1.5배 크기 */
}

/* 태블릿: 2열 */
@media screen and (max-width: 1024px) {
    .footer-about {
        flex: 1 1 100%; /* 첫 번째 행 전체 차지 */
    }
    
    .footer-links,
    .footer-info,
    .footer-contact {
        flex: 1 1 calc(50% - 15px); /* 2열 */
    }
}

/* 모바일: 1열 */
@media screen and (max-width: 768px) {
    .footer-content {
        flex-direction: column;
    }
    
    .footer-about,
    .footer-links,
    .footer-info,
    .footer-contact {
        flex: 1 1 100%; /* 1열: 전체 너비 */
    }
}
```

**시각적 변화:**

```
데스크톱 (>1024px):
┌──────────┬─────┬─────┬──────┐
│  About   │ CS  │Info │Contact│
└──────────┴─────┴─────┴──────┘

태블릿 (768px~1024px):
┌──────────┬──────────┐
│  About   │          │
├──────────┼──────────┤
│    CS    │   Info   │
├──────────┴──────────┤
│      Contact        │
└─────────────────────┘

모바일 (<768px):
┌──────────┐
│  About   │
├──────────┤
│    CS    │
├──────────┤
│   Info   │
├──────────┤
│ Contact  │
└──────────┘
```

---

## Flexbox 속성 정리

### 컨테이너 속성

| 속성 | 값 | 설명 |
|------|-----|------|
| `display` | `flex` | Flexbox 활성화 |
| `flex-direction` | `row`, `column` | 주축 방향 |
| `flex-wrap` | `wrap`, `nowrap` | 줄바꿈 |
| `justify-content` | `space-between`, `center` | 주축 정렬 |
| `align-items` | `center`, `stretch` | 교차축 정렬 |
| `gap` | `30px` | 아이템 간 간격 |

### 아이템 속성

| 속성 | 값 | 설명 |
|------|-----|------|
| `flex` | `0 0 calc(33.333% - 20px)` | grow shrink basis |
| `flex-grow` | `0`, `1` | 남은 공간 채우기 |
| `flex-shrink` | `0`, `1` | 줄어들기 허용 |
| `flex-basis` | `33.333%`, `200px` | 기본 크기 |

---

## 실전 팁

### 1. gap과 calc() 계산

**3열 레이아웃:**
```css
gap: 30px;
flex: 0 0 calc(33.333% - 20px);
/* 30px * 2/3 = 20px */
```

**2열 레이아웃:**
```css
gap: 25px;
flex: 0 0 calc(50% - 12.5px);
/* 25px * 1/2 = 12.5px */
```

**공식:**
```
아이템 너비 = (100% / 열 개수) - (gap * (열 개수 - 1) / 열 개수)
```

---

### 2. flex-basis의 다양한 표현

```css
/* 퍼센트 */
flex: 0 0 33.333%;

/* calc() 사용 (gap 고려) */
flex: 0 0 calc(33.333% - 20px);

/* 고정 크기 */
flex: 0 0 300px;

/* auto (내용에 맞춤) */
flex: 0 0 auto;
```

---

### 3. flex-direction: column 활용

```css
@media screen and (max-width: 768px) {
    .items-flex {
        flex-direction: column; /* 세로 방향 */
    }
    
    .items-flex .item {
        flex: 1 1 100%; /* 전체 너비 */
    }
}
```

**장점:**
- `flex-wrap`보다 명확함
- 세로 배치가 확실함
- 코드가 더 읽기 쉬움

---

## Grid vs Flexbox 선택 가이드

### Grid를 사용하는 경우

- ✅ 2차원 레이아웃 (행+열)
- ✅ 복잡한 그리드 시스템
- ✅ 정확한 열 개수 제어가 필요할 때
- ✅ 카드 그리드 레이아웃

### Flexbox를 사용하는 경우

- ✅ 1차원 레이아웃 (행 또는 열)
- ✅ 컴포넌트 내부 정렬
- ✅ 동적 크기 조정이 필요할 때
- ✅ 간단한 반응형 레이아웃

---

## 마무리

이 CSS Flexbox 반응형 레이아웃은 **화면 크기에 따라 flex 아이템의 너비가 자동으로 조정**되는 완벽한 예제입니다.

**핵심 학습 포인트:**
1. ✅ `display: flex`로 Flexbox 레이아웃 활성화
2. ✅ `flex-wrap: wrap`으로 줄바꿈 허용
3. ✅ `flex` 속성으로 아이템 크기 제어
4. ✅ `calc()` 함수로 gap 고려한 정확한 계산
5. ✅ `flex-direction: column`으로 세로 배치
6. ✅ 미디어 쿼리로 반응형 구현

이 코드를 이해하면 다양한 Flexbox 반응형 레이아웃 구현이 가능합니다!
