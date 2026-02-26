# CSS Grid 반응형 레이아웃 - 완전 정복

## 📋 목차
1. [개요](#개요)
2. [Grid 기본 개념](#grid-기본-개념)
3. [반응형 Grid 구현](#반응형-grid-구현)
4. [미디어 쿼리별 상세 설명](#미디어-쿼리별-상세-설명)
5. [핵심 개념](#핵심-개념)
6. [실전 예제](#실전-예제)

---

## 개요

이 예제는 **CSS Grid**를 사용하여 반응형 레이아웃을 구현한 것입니다. 화면 크기에 따라 그리드 열의 개수가 자동으로 조정됩니다.

**주요 특징:**
- ✅ 데스크톱: 3열 그리드
- ✅ 태블릿: 2열 그리드
- ✅ 모바일: 1열 그리드
- ✅ Footer도 반응형 그리드 적용

**반응형 브레이크포인트:**
- 1200px 이하: gap 조정
- 1024px 이하: 3열 → 2열
- 768px 이하: 2열 → 1열
- 480px 이하: 폰트 크기 및 패딩 조정

---

## Grid 기본 개념

### 1. display: grid

```css
.items-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 30px;
}
```

**설명:**
- `display: grid`: Grid 레이아웃 활성화
- `grid-template-columns`: 열(column) 정의
- `repeat(3, 1fr)`: 3개의 동일한 너비 열 생성
- `gap`: 그리드 아이템 간 간격

### 2. grid-template-columns

**기본 문법:**
```css
grid-template-columns: repeat(3, 1fr);
```

**의미:**
- `repeat(3, 1fr)`: 3번 반복, 각각 `1fr` (동일한 비율)
- `1fr`: 사용 가능한 공간의 1/n (Fractional Unit)

**다른 표현 방법:**
```css
/* 동일한 의미 */
grid-template-columns: repeat(3, 1fr);
grid-template-columns: 1fr 1fr 1fr;
grid-template-columns: 33.33% 33.33% 33.33%;
```

### 3. gap

```css
gap: 30px;
```

**설명:**
- 그리드 아이템 간 간격
- `row-gap`과 `column-gap`을 한 번에 설정
- `gap: 30px` = `row-gap: 30px; column-gap: 30px;`

---

## 반응형 Grid 구현

### 전체 반응형 구조

```css
/* 기본: 3열 (데스크톱) */
.items-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 30px;
}

/* 태블릿: 2열 */
@media screen and (max-width: 1024px) {
    .items-grid {
        grid-template-columns: repeat(2, 1fr);
        gap: 25px;
    }
}

/* 모바일: 1열 */
@media screen and (max-width: 768px) {
    .items-grid {
        grid-template-columns: 1fr;
        gap: 30px;
        max-width: 400px;
        margin: 0 auto;
    }
}
```

---

## 미디어 쿼리별 상세 설명

### 1. 기본 스타일 (1200px 초과)

```css
.items-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);  /* 3열 */
    gap: 30px;
}
```

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
    .items-grid {
        gap: 25px;  /* 간격만 줄임 */
    }
    
    .footer-content {
        gap: 30px;
    }
}
```

**설명:**
- 열 개수는 그대로 3열 유지
- 간격만 약간 줄여서 공간 효율성 향상

---

### 3. 1024px 이하 (태블릿 - 2열)

```css
@media screen and (max-width: 1024px) {
    .items-grid {
        grid-template-columns: repeat(2, 1fr);  /* 2열로 변경 */
        gap: 25px;
    }
    
    .section-title {
        font-size: 28px;
        margin-bottom: 40px;
    }
    
    .best-items,
    .new-items {
        padding: 60px 20px;
    }
    
    /* Footer: 2열 그리드 */
    .footer-content {
        grid-template-columns: 1fr 1fr;
        gap: 30px;
    }
    
    .footer-about {
        grid-column: 1 / -1;  /* 첫 번째 행 전체 차지 */
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

1. **grid-template-columns: repeat(2, 1fr)**
   - 3열에서 2열로 변경
   - 각 열이 동일한 너비 (`1fr`)

2. **grid-column: 1 / -1**
   ```css
   .footer-about {
       grid-column: 1 / -1;
   }
   ```
   - 첫 번째 열부터 마지막 열까지 차지
   - `1`: 첫 번째 열 시작
   - `-1`: 마지막 열 끝
   - Footer의 첫 번째 섹션이 전체 너비를 차지

---

### 4. 768px 이하 (모바일 - 1열)

```css
@media screen and (max-width: 768px) {
    .items-grid {
        grid-template-columns: 1fr;  /* 1열로 변경 */
        gap: 30px;
        max-width: 400px;
        margin: 0 auto;
    }
    
    .section-title {
        font-size: 24px;
        margin-bottom: 30px;
    }
    
    .best-items,
    .new-items {
        padding: 50px 20px;
    }
    
    .item-name {
        font-size: 16px;
    }
    
    .item-price {
        font-size: 15px;
    }
    
    /* Footer: 1열 그리드 */
    .footer-content {
        grid-template-columns: 1fr;
        gap: 30px;
    }
    
    .footer-about {
        grid-column: 1;
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

1. **grid-template-columns: 1fr**
   - 1열만 사용
   - 모든 아이템이 세로로 배치

2. **max-width: 400px**
   - 모바일에서 너무 넓게 보이지 않도록 최대 너비 제한
   - 가독성 향상

3. **margin: 0 auto**
   - 중앙 정렬
   - 좌우 여백이 동일하게 분배

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

## 핵심 개념

### 1. repeat() 함수

```css
grid-template-columns: repeat(3, 1fr);
```

**문법:**
```css
repeat(반복횟수, 크기)
```

**예시:**
```css
repeat(3, 1fr)        /* 1fr 1fr 1fr */
repeat(2, 100px)      /* 100px 100px */
repeat(4, minmax(200px, 1fr))  /* 반응형 열 */
```

**장점:**
- 코드가 간결함
- 유지보수 용이

---

### 2. fr 단위 (Fractional Unit)

```css
grid-template-columns: repeat(3, 1fr);
```

**설명:**
- 사용 가능한 공간을 비율로 나눔
- `1fr` = 전체 공간의 1/n
- `2fr` = 전체 공간의 2/n

**예시:**
```css
grid-template-columns: 1fr 2fr 1fr;
/* 결과: 25% 50% 25% */
```

---

### 3. grid-column

```css
.footer-about {
    grid-column: 1 / -1;
}
```

**문법:**
```css
grid-column: 시작열 / 끝열;
```

**예시:**
```css
grid-column: 1 / 3;      /* 1열부터 3열 전까지 */
grid-column: 1 / -1;      /* 첫 열부터 마지막 열까지 */
grid-column: span 2;      /* 2열 차지 */
```

---

### 4. gap 속성

```css
gap: 30px;
```

**설명:**
- 그리드 아이템 간 간격
- `row-gap`과 `column-gap`을 한 번에 설정

**개별 설정:**
```css
row-gap: 20px;      /* 행 간격 */
column-gap: 30px;   /* 열 간격 */
gap: 20px 30px;     /* row-gap column-gap */
```

---

### 5. max-width와 margin: 0 auto

```css
.items-grid {
    max-width: 400px;
    margin: 0 auto;
}
```

**설명:**
- `max-width`: 최대 너비 제한
- `margin: 0 auto`: 좌우 중앙 정렬
- 모바일에서 너무 넓게 보이지 않도록 제한

---

## 실전 예제

### Items Grid 반응형

```css
/* 기본: 3열 */
.items-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 30px;
}

/* 태블릿: 2열 */
@media screen and (max-width: 1024px) {
    .items-grid {
        grid-template-columns: repeat(2, 1fr);
        gap: 25px;
    }
}

/* 모바일: 1열 */
@media screen and (max-width: 768px) {
    .items-grid {
        grid-template-columns: 1fr;
        gap: 30px;
        max-width: 400px;
        margin: 0 auto;
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

### Footer Grid 반응형

```css
/* 기본: 4열 */
.footer-content {
    display: grid;
    grid-template-columns: 2fr 1fr 1fr 1.5fr;
    gap: 40px;
}

/* 태블릿: 2열 */
@media screen and (max-width: 1024px) {
    .footer-content {
        grid-template-columns: 1fr 1fr;
        gap: 30px;
    }
    
    .footer-about {
        grid-column: 1 / -1;  /* 첫 번째 행 전체 차지 */
    }
}

/* 모바일: 1열 */
@media screen and (max-width: 768px) {
    .footer-content {
        grid-template-columns: 1fr;
        gap: 30px;
    }
    
    .footer-about {
        grid-column: 1;
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

## 반응형 브레이크포인트 전략

### 모바일 퍼스트 (Mobile First)

```css
/* 모바일 기본 */
.items-grid {
    grid-template-columns: 1fr;
}

/* 태블릿 */
@media screen and (min-width: 768px) {
    .items-grid {
        grid-template-columns: repeat(2, 1fr);
    }
}

/* 데스크톱 */
@media screen and (min-width: 1024px) {
    .items-grid {
        grid-template-columns: repeat(3, 1fr);
    }
}
```

**장점:**
- 모바일 성능 최적화
- 점진적 향상 (Progressive Enhancement)

### 데스크톱 퍼스트 (Desktop First) - 현재 방식

```css
/* 데스크톱 기본 */
.items-grid {
    grid-template-columns: repeat(3, 1fr);
}

/* 태블릿 */
@media screen and (max-width: 1024px) {
    .items-grid {
        grid-template-columns: repeat(2, 1fr);
    }
}

/* 모바일 */
@media screen and (max-width: 768px) {
    .items-grid {
        grid-template-columns: 1fr;
    }
}
```

**장점:**
- 기존 코드와 호환성 좋음
- 이해하기 쉬움

---

## Grid vs Flexbox

| 특징 | Grid | Flexbox |
|------|------|---------|
| 차원 | 2차원 (행+열) | 1차원 (행 또는 열) |
| 레이아웃 | 전체 레이아웃 | 컴포넌트 내부 |
| 정렬 | 행과 열 동시 제어 | 한 방향만 제어 |
| 사용 예 | 카드 그리드, 전체 레이아웃 | 네비게이션, 버튼 그룹 |

**Grid를 사용하는 경우:**
- 카드 그리드 레이아웃
- 복잡한 2차원 레이아웃
- 반응형 그리드 시스템

**Flexbox를 사용하는 경우:**
- 한 방향 정렬
- 컴포넌트 내부 레이아웃
- 간단한 정렬

---

## 실전 팁

### 1. auto-fit과 auto-fill

```css
/* 자동으로 열 개수 조정 */
.items-grid {
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
}
```

**설명:**
- `auto-fit`: 사용 가능한 공간에 맞춰 열 개수 자동 조정
- `minmax(300px, 1fr)`: 최소 300px, 최대 1fr
- 미디어 쿼리 없이도 반응형 구현 가능

---

### 2. grid-auto-rows

```css
.items-grid {
    grid-auto-rows: minmax(200px, auto);
}
```

**설명:**
- 자동 생성되는 행의 높이 설정
- 모든 행이 동일한 최소 높이를 가짐

---

### 3. grid-template-areas

```css
.footer-content {
    display: grid;
    grid-template-areas:
        "about cs info contact"
        "about cs info contact";
    grid-template-columns: 2fr 1fr 1fr 1.5fr;
}

.footer-about {
    grid-area: about;
}
```

**설명:**
- 영역 이름으로 레이아웃 정의
- 시각적으로 레이아웃 구조 파악 용이

---

## 마무리

이 CSS Grid 반응형 레이아웃은 **화면 크기에 따라 자동으로 열 개수가 조정**되는 완벽한 예제입니다.

**핵심 학습 포인트:**
1. ✅ `display: grid`로 Grid 레이아웃 활성화
2. ✅ `grid-template-columns`로 열 개수 제어
3. ✅ `repeat()` 함수로 간결한 코드 작성
4. ✅ `fr` 단위로 비율 기반 레이아웃
5. ✅ `grid-column`으로 영역 확장
6. ✅ `gap`으로 간격 제어
7. ✅ 미디어 쿼리로 반응형 구현

이 코드를 이해하면 다양한 반응형 그리드 레이아웃 구현이 가능합니다!
