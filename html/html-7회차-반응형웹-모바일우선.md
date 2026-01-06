# 7회차 — 반응형 웹(Responsive)과 모바일 우선 설계

## 학습 목표
- 뷰포트 메타 태그의 중요성을 이해하고 올바르게 설정할 수 있다
- 모바일 우선 설계 원칙을 이해하고 적용할 수 있다
- 미디어 쿼리를 사용하여 반응형 디자인을 구현할 수 있다
- 브레이크포인트를 정의하고 레이아웃을 전환할 수 있다
- 이미지와 미디어를 반응형으로 처리할 수 있다

---

## 1. 반응형 웹이란?

### 1.1 반응형 웹의 정의

반응형 웹 디자인(Responsive Web Design)은 하나의 웹사이트가 다양한 화면 크기와 기기에 맞춰 자동으로 레이아웃을 조절하는 디자인 기법입니다.

**목표:**
- 모든 기기에서 최적의 사용자 경험 제공
- 하나의 코드베이스로 모든 기기 지원
- 유지보수 비용 절감

**장점:**
- 개발 및 유지보수 효율성
- SEO 최적화 (하나의 URL)
- 사용자 경험 향상
- 비용 절감

### 1.2 반응형 vs 적응형 vs 모바일 전용

#### 반응형 (Responsive)
- 하나의 HTML/CSS
- CSS 미디어 쿼리로 레이아웃 조절
- **권장 방법**

#### 적응형 (Adaptive)
- 여러 개의 HTML (기기별)
- 서버에서 기기 감지하여 다른 페이지 제공
- 복잡하고 유지보수 어려움

#### 모바일 전용
- 모바일용 별도 사이트 (m.example.com)
- 데스크톱과 모바일 분리
- SEO 문제 가능

---

## 2. 뷰포트 메타 태그

### 2.1 뷰포트란?

뷰포트(Viewport)는 웹 페이지가 표시되는 화면 영역입니다.

**문제:**
- 모바일 브라우저는 기본적으로 데스크톱 페이지를 축소하여 표시
- 사용자가 확대/축소해야 함
- 반응형 디자인이 제대로 작동하지 않음

### 2.2 뷰포트 메타 태그

```html
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
```

**속성 설명:**
- `width=device-width`: 뷰포트 너비를 기기의 화면 너비로 설정
- `initial-scale=1.0`: 초기 확대/축소 비율을 1.0으로 설정 (100%)
- `maximum-scale=1.0`: 최대 확대 비율 제한 (선택사항)
- `user-scalable=no`: 사용자 확대/축소 비활성화 (접근성 문제로 권장하지 않음)

**권장 설정:**
```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

**추가 옵션 (필요시):**
```html
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0, user-scalable=yes">
```

### 2.3 뷰포트 메타 태그 없을 때

뷰포트 메타 태그가 없으면:
- 모바일에서 데스크톱 페이지가 축소되어 표시됨
- 텍스트가 너무 작아서 읽기 어려움
- 사용자가 수동으로 확대해야 함
- 반응형 CSS가 제대로 작동하지 않음

---

## 3. 모바일 우선 설계 (Mobile First)

### 3.1 모바일 우선이란?

모바일 우선(Mobile First)은 작은 화면부터 디자인을 시작하여 점진적으로 큰 화면에 맞춰 확장하는 설계 방법입니다.

**원칙:**
1. 기본 스타일: 모바일 (작은 화면)
2. 미디어 쿼리: 태블릿, 데스크톱 (큰 화면)

### 3.2 모바일 우선의 장점

**성능:**
- 모바일 사용자는 필요한 CSS만 로드
- 불필요한 리소스 제거
- 빠른 로딩 속도

**사용자 경험:**
- 모바일 사용자가 많음
- 핵심 기능에 집중
- 간결한 디자인

**개발 효율:**
- 작은 화면부터 시작하여 점진적 향상
- 복잡도 관리 용이

### 3.3 모바일 우선 vs 데스크톱 우선

#### 모바일 우선 (권장)
```css
/* 기본: 모바일 */
.container {
    width: 100%;
    padding: 10px;
}

/* 태블릿 이상 */
@media (min-width: 768px) {
    .container {
        width: 750px;
        padding: 20px;
    }
}

/* 데스크톱 */
@media (min-width: 1024px) {
    .container {
        width: 1200px;
        padding: 30px;
    }
}
```

#### 데스크톱 우선 (비권장)
```css
/* 기본: 데스크톱 */
.container {
    width: 1200px;
    padding: 30px;
}

/* 태블릿 이하 */
@media (max-width: 1023px) {
    .container {
        width: 750px;
        padding: 20px;
    }
}

/* 모바일 */
@media (max-width: 767px) {
    .container {
        width: 100%;
        padding: 10px;
    }
}
```

**문제점:**
- 모바일에서 불필요한 CSS 로드
- 성능 저하
- 복잡한 미디어 쿼리

---

## 4. 미디어 쿼리 (Media Queries)

### 4.1 미디어 쿼리란?

미디어 쿼리는 화면 크기, 해상도, 방향 등에 따라 다른 CSS를 적용할 수 있게 해주는 CSS 기능입니다.

### 4.2 기본 문법

```css
@media (조건) {
    /* CSS 규칙 */
}
```

**예시:**
```css
@media (min-width: 768px) {
    .container {
        width: 750px;
    }
}
```

### 4.3 미디어 쿼리 적용 방법

#### 방법 1: CSS 파일 내부
```css
/* 기본 스타일 */
.container {
    width: 100%;
}

/* 미디어 쿼리 */
@media (min-width: 768px) {
    .container {
        width: 750px;
    }
}
```

#### 방법 2: 별도 CSS 파일
```html
<link rel="stylesheet" href="mobile.css">
<link rel="stylesheet" href="tablet.css" media="(min-width: 768px)">
<link rel="stylesheet" href="desktop.css" media="(min-width: 1024px)">
```

### 4.4 미디어 쿼리 조건

#### 화면 너비
```css
@media (min-width: 768px) { }  /* 768px 이상 */
@media (max-width: 767px) { }   /* 767px 이하 */
@media (width: 768px) { }       /* 정확히 768px */
```

#### 화면 높이
```css
@media (min-height: 600px) { }  /* 높이 600px 이상 */
@media (max-height: 599px) { }  /* 높이 599px 이하 */
```

#### 화면 방향
```css
@media (orientation: portrait) { }   /* 세로 모드 */
@media (orientation: landscape) { }   /* 가로 모드 */
```

#### 해상도
```css
@media (min-resolution: 2dppx) { }   /* 고해상도 디스플레이 */
@media (-webkit-min-device-pixel-ratio: 2) { }  /* Safari */
```

#### 복합 조건
```css
/* AND 조건 */
@media (min-width: 768px) and (max-width: 1023px) { }

/* OR 조건 */
@media (min-width: 768px), (orientation: landscape) { }

/* NOT 조건 */
@media not (max-width: 767px) { }
```

### 4.5 모바일 우선 미디어 쿼리 패턴

```css
/* 기본: 모바일 (320px~) */
.container {
    width: 100%;
    padding: 10px;
}

/* 작은 태블릿 (480px~) */
@media (min-width: 480px) {
    .container {
        padding: 15px;
    }
}

/* 태블릿 (768px~) */
@media (min-width: 768px) {
    .container {
        width: 750px;
        margin: 0 auto;
        padding: 20px;
    }
}

/* 데스크톱 (1024px~) */
@media (min-width: 1024px) {
    .container {
        width: 1200px;
        padding: 30px;
    }
}

/* 큰 데스크톱 (1440px~) */
@media (min-width: 1440px) {
    .container {
        width: 1400px;
    }
}
```

---

## 5. 브레이크포인트 정의

### 5.1 브레이크포인트란?

브레이크포인트(Breakpoint)는 레이아웃이 변경되는 화면 크기 지점입니다.

### 5.2 일반적인 브레이크포인트

**모바일 우선 기준:**
```css
/* 모바일: 320px ~ 479px (기본) */
/* 작은 태블릿: 480px ~ 767px */
/* 태블릿: 768px ~ 1023px */
/* 데스크톱: 1024px ~ 1439px */
/* 큰 데스크톱: 1440px ~ */
```

**실제 사용 예시:**
```css
/* 모바일 (기본) */
body {
    font-size: 14px;
}

/* 작은 태블릿 */
@media (min-width: 480px) {
    body {
        font-size: 15px;
    }
}

/* 태블릿 */
@media (min-width: 768px) {
    body {
        font-size: 16px;
    }
}

/* 데스크톱 */
@media (min-width: 1024px) {
    body {
        font-size: 18px;
    }
}
```

### 5.3 콘텐츠 기반 브레이크포인트

고정된 크기가 아닌 콘텐츠에 따라 브레이크포인트를 정의할 수도 있습니다.

```css
/* 콘텐츠가 600px를 넘으면 레이아웃 변경 */
.container {
    display: block;
}

@media (min-width: 600px) {
    .container {
        display: grid;
        grid-template-columns: 1fr 1fr;
    }
}
```

### 5.4 CSS 변수로 브레이크포인트 관리

```css
:root {
    --breakpoint-sm: 480px;
    --breakpoint-md: 768px;
    --breakpoint-lg: 1024px;
    --breakpoint-xl: 1440px;
}

@media (min-width: var(--breakpoint-md)) {
    .container {
        width: 750px;
    }
}
```

---

## 6. 유동형 레이아웃 (Fluid Layout)

### 6.1 고정 너비 vs 유동 너비

#### 고정 너비 (Fixed Width)
```css
.container {
    width: 1200px;  /* 고정 크기 */
}
```

**문제점:**
- 작은 화면에서 가로 스크롤 발생
- 큰 화면에서 공간 낭비

#### 유동 너비 (Fluid Width)
```css
.container {
    width: 100%;           /* 부모의 100% */
    max-width: 1200px;    /* 최대 크기 제한 */
    margin: 0 auto;       /* 중앙 정렬 */
}
```

**장점:**
- 모든 화면 크기에 적응
- 가로 스크롤 없음
- 공간 효율적 사용

### 6.2 유동형 레이아웃 기법

#### max-width 사용
```css
.container {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}
```

#### 퍼센트 사용
```css
.sidebar {
    width: 30%;
}

.main {
    width: 70%;
}
```

#### calc() 사용
```css
.main {
    width: calc(100% - 250px);  /* 전체에서 사이드바 제외 */
}
```

#### Flexbox와 조합
```css
.container {
    display: flex;
    flex-wrap: wrap;
}

.item {
    flex: 1 1 300px;  /* 최소 300px, 유연하게 */
    min-width: 0;
}
```

#### Grid와 조합
```css
.container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
}
```

---

## 7. 반응형 이미지와 미디어

### 7.1 반응형 이미지

#### max-width: 100%
```css
img {
    max-width: 100%;
    height: auto;
}
```

**효과:**
- 이미지가 컨테이너보다 크지 않음
- 비율 유지
- 가로 스크롤 방지

#### object-fit
```css
img {
    width: 100%;
    height: 300px;
    object-fit: cover;      /* 영역을 가득 채움 */
    object-fit: contain;    /* 전체가 보이도록 */
    object-fit: fill;       /* 늘림 */
}
```

### 7.2 srcset과 sizes (고급)

다양한 해상도에 맞는 이미지를 제공합니다.

```html
<img 
    src="image-small.jpg"
    srcset="image-small.jpg 480w,
            image-medium.jpg 768w,
            image-large.jpg 1024w"
    sizes="(max-width: 480px) 100vw,
           (max-width: 768px) 50vw,
           33vw"
    alt="반응형 이미지">
```

### 7.3 picture 요소

조건에 따라 다른 이미지를 표시합니다.

```html
<picture>
    <source media="(min-width: 1024px)" srcset="desktop.jpg">
    <source media="(min-width: 768px)" srcset="tablet.jpg">
    <img src="mobile.jpg" alt="반응형 이미지">
</picture>
```

### 7.4 반응형 비디오

```css
.video-container {
    position: relative;
    padding-bottom: 56.25%; /* 16:9 비율 */
    height: 0;
    overflow: hidden;
}

.video-container iframe,
.video-container video {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
```

---

## 8. 레이아웃 전환 사례

### 8.1 모바일 → 태블릿 → 데스크톱

#### 예시 1: 네비게이션 메뉴

**모바일:**
- 햄버거 메뉴 (☰)
- 세로 메뉴

**태블릿:**
- 가로 메뉴
- 일부 항목만 표시

**데스크톱:**
- 전체 메뉴 표시
- 드롭다운 메뉴

#### 예시 2: 카드 그리드

**모바일:**
```css
.card-grid {
    display: block;
}

.card {
    width: 100%;
    margin-bottom: 20px;
}
```

**태블릿:**
```css
@media (min-width: 768px) {
    .card-grid {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 20px;
    }
}
```

**데스크톱:**
```css
@media (min-width: 1024px) {
    .card-grid {
        grid-template-columns: repeat(3, 1fr);
    }
}
```

#### 예시 3: 사이드바 레이아웃

**모바일:**
- 사이드바 숨김
- 메인 콘텐츠만 표시

**태블릿:**
- 사이드바 상단 또는 하단
- 메인 콘텐츠와 분리

**데스크톱:**
- 사이드바 왼쪽
- 메인 콘텐츠와 나란히

---

## 9. 실전 전략

### 9.1 모바일 우선 개발 순서

1. **모바일 디자인**
   - 핵심 기능에 집중
   - 간결한 레이아웃
   - 터치 친화적

2. **태블릿 확장**
   - 추가 공간 활용
   - 2열 레이아웃
   - 더 많은 콘텐츠 표시

3. **데스크톱 최적화**
   - 최대 공간 활용
   - 복잡한 레이아웃
   - 호버 효과 등

### 9.2 테스트 방법

#### 브라우저 개발자 도구
- `F12` → 디바이스 모드
- 다양한 기기 크기 테스트
- 실제 기기 테스트 권장

#### 실제 기기 테스트
- 다양한 모바일 기기
- 다양한 브라우저
- 다양한 화면 크기

### 9.3 성능 최적화

#### 이미지 최적화
- 적절한 크기의 이미지 사용
- WebP 형식 사용
- lazy loading 적용

#### CSS 최적화
- 불필요한 미디어 쿼리 제거
- 모바일 우선으로 최소 CSS
- Critical CSS 인라인

---

## 10. 실습 체크리스트

### 뷰포트
- [ ] 뷰포트 메타 태그를 올바르게 설정했는가?
- [ ] 모든 페이지에 뷰포트 메타 태그가 있는가?

### 모바일 우선
- [ ] 기본 스타일이 모바일용인가?
- [ ] 미디어 쿼리가 min-width를 사용하는가?
- [ ] 점진적 향상(Progressive Enhancement)을 따르는가?

### 미디어 쿼리
- [ ] 적절한 브레이크포인트를 정의했는가?
- [ ] 레이아웃이 각 브레이크포인트에서 올바르게 전환되는가?
- [ ] 불필요한 미디어 쿼리가 없는가?

### 반응형 요소
- [ ] 이미지가 반응형으로 처리되었는가?
- [ ] 비디오가 반응형으로 처리되었는가?
- [ ] 텍스트 크기가 적절한가?
- [ ] 터치 영역이 충분한가? (최소 44x44px)

### 테스트
- [ ] 다양한 화면 크기에서 테스트했는가?
- [ ] 실제 기기에서 테스트했는가?
- [ ] 가로 스크롤이 없는가?

---

## 다음 단계
- CSS 변수와 커스텀 속성
- CSS 애니메이션
- 실전 프로젝트
- 성능 최적화


