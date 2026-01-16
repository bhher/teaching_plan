# Flex 이미지 레이아웃 설명

## 📌 요구사항

- 1000px 너비
- 중앙 정렬
- Flexbox 사용
- 4개의 이미지를 나란히 배열

---

## 🎯 핵심 CSS 코드

### 기본 구조

```css
.bigox {
    width: 1000px;
    margin: 0 auto;           /* 중앙 정렬 */
    display: flex;           /* Flexbox 사용 */
    justify-content: space-between;  /* 요소들 사이 간격 */
}
```

### 각 박스 스타일

```css
.box {
    flex: 1;                 /* 동일한 크기로 늘어남 */
}

.box img {
    width: 100%;
    height: 100%;
    object-fit: cover;       /* 이미지 비율 유지 */
}
```

---

## 📝 상세 설명

### 1. 중앙 정렬

```css
.bigox {
    width: 1000px;
    margin: 0 auto;  /* 좌우 자동 마진 = 중앙 정렬 */
}
```

**설명:**
- `width: 1000px`: 컨테이너 너비 1000px
- `margin: 0 auto`: 위아래 0, 좌우 자동 (중앙 정렬)

### 2. Flexbox 설정

```css
.bigox {
    display: flex;
    justify-content: space-between;
}
```

**설명:**
- `display: flex`: Flexbox 활성화
- `justify-content: space-between`: 요소들 사이에 균등한 간격

**다른 옵션:**
```css
justify-content: flex-start;    /* 왼쪽 정렬 */
justify-content: flex-end;      /* 오른쪽 정렬 */
justify-content: center;        /* 중앙 정렬 */
justify-content: space-around;  /* 양쪽 여백 포함 균등 간격 */
justify-content: space-between; /* 요소들 사이 균등 간격 */
justify-content: space-evenly;  /* 완전 균등 간격 */
```

### 3. 각 박스 크기

```css
.box {
    flex: 1;  /* 각 박스가 동일한 크기로 늘어남 */
}
```

**설명:**
- `flex: 1`: 각 요소가 동일한 비율로 공간을 차지
- 4개 요소 = 각각 25% 너비

**다른 옵션:**
```css
flex: 1;        /* 동일한 크기 */
flex: 0 0 200px; /* 고정 크기 200px */
flex: 2;        /* 다른 요소보다 2배 크기 */
```

### 4. 이미지 스타일

```css
.box img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
```

**설명:**
- `width: 100%`: 부모 너비에 맞춤
- `height: 100%`: 부모 높이에 맞춤
- `object-fit: cover`: 비율 유지하며 영역 채우기

**object-fit 옵션:**
```css
object-fit: cover;     /* 비율 유지, 영역 채움 (잘릴 수 있음) */
object-fit: contain;  /* 비율 유지, 전체 보임 (여백 생길 수 있음) */
object-fit: fill;      /* 비율 무시, 영역 채움 */
```

---

## 🎨 추가 스타일 옵션

### 옵션 1: gap 사용 (간격)

```css
.bigox {
    display: flex;
    gap: 20px;  /* 요소들 사이 20px 간격 */
}
```

**장점:**
- `justify-content: space-between` 대신 사용 가능
- 더 직관적이고 간단함

### 옵션 2: 고정 높이 설정

```css
.box {
    flex: 1;
    height: 300px;  /* 고정 높이 */
}
```

### 옵션 3: 호버 효과

```css
.box img {
    transition: transform 0.3s ease;
}

.box:hover img {
    transform: scale(1.05);  /* 5% 확대 */
}
```

### 옵션 4: 그림자와 둥근 모서리

```css
.box img {
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
```

---

## 📊 레이아웃 비교

### 방법 1: space-between (기본)

```css
.bigox {
    display: flex;
    justify-content: space-between;
}
```

**결과:**
```
[이미지1]    [이미지2]    [이미지3]    [이미지4]
```

### 방법 2: gap 사용

```css
.bigox {
    display: flex;
    gap: 20px;
}
```

**결과:**
```
[이미지1] [이미지2] [이미지3] [이미지4]
  ↑20px    ↑20px    ↑20px
```

### 방법 3: space-around

```css
.bigox {
    display: flex;
    justify-content: space-around;
}
```

**결과:**
```
[이미지1]  [이미지2]  [이미지3]  [이미지4]
 ↑여백      ↑여백      ↑여백      ↑여백
```

---

## 🔧 반응형 디자인

### 모바일 대응

```css
@media (max-width: 768px) {
    .bigox {
        width: 100%;
        flex-direction: column;  /* 세로로 배치 */
    }
    
    .box {
        max-width: 100%;
    }
}
```

### 태블릿 대응

```css
@media (max-width: 1024px) {
    .bigox {
        width: 100%;
        padding: 20px;
    }
    
    .box {
        max-width: calc(50% - 10px);  /* 2열로 배치 */
    }
}
```

---

## 💡 완성된 코드 (추가 효과 포함)

```css
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

ul {
    list-style: none;
}

.bigox {
    width: 1000px;
    margin: 0 auto;
    display: flex;
    gap: 20px;
    padding: 20px 0;
}

.box {
    flex: 1;
    overflow: hidden;  /* 이미지가 넘치지 않도록 */
    border-radius: 8px;
}

.box img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    display: block;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease;
}

.box:hover img {
    transform: scale(1.05);
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
}
```

---

## 📚 핵심 포인트

1. ✅ **중앙 정렬**: `margin: 0 auto`
2. ✅ **Flexbox**: `display: flex`
3. ✅ **균등 배치**: `flex: 1` 또는 `justify-content: space-between`
4. ✅ **이미지 비율**: `object-fit: cover`
5. ✅ **간격 조정**: `gap` 또는 `justify-content`

---

## 🎯 최종 결과

```
┌─────────────────────────────────────────────┐
│         (중앙 정렬, 1000px)                 │
│  ┌────┐  ┌────┐  ┌────┐  ┌────┐           │
│  │이미지1│ │이미지2│ │이미지3│ │이미지4│           │
│  └────┘  └────┘  └────┘  └────┘           │
│     ↑      ↑      ↑      ↑                │
│   동일한 크기, 나란히 배열                  │
└─────────────────────────────────────────────┘
```


