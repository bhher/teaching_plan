# Vanilla JS 슬라이더 완전 정복

## 📋 목차
1. [개요](#개요)
2. [슬라이더 종류별 설명](#슬라이더-종류별-설명)
3. [공통 기능](#공통-기능)
4. [비교표](#비교표)
5. [실전 활용 팁](#실전-활용-팁)

---

## 개요

jQuery 플러그인(bxSlider, Swiper, Slick) 없이 순수 JavaScript로 구현한 다양한 슬라이더 예제 모음입니다.

**주요 특징:**
- ✅ jQuery 의존성 없음
- ✅ 가볍고 빠름
- ✅ 커스터마이징 용이
- ✅ 모던 브라우저 지원

---

## 슬라이더 종류별 설명

### 1. 기본 슬라이더 (`basic/`)

**특징:**
- 가장 간단한 형태
- display 속성으로 슬라이드 전환
- 인디케이터 포함

**구현 방식:**
```javascript
// display로 전환
slide.classList.remove('active');
slides[currentIndex].classList.add('active');
```

**CSS:**
```css
.slide {
    display: none;
}
.slide.active {
    display: block;
}
```

**장점:**
- 구현이 간단함
- 메모리 효율적

**단점:**
- 애니메이션 효과 없음
- 전환이 즉시 발생

---

### 2. 페이드 슬라이더 (`fade/`)

**특징:**
- 부드러운 페이드 인/아웃 효과
- opacity와 transition 사용
- 슬라이드 콘텐츠 오버레이 지원

**구현 방식:**
```javascript
// opacity로 전환
slide.style.opacity = '0';
slides[currentIndex].style.opacity = '1';
```

**CSS:**
```css
.slide {
    position: absolute;
    opacity: 0;
    transition: opacity 0.8s ease-in-out;
}
.slide.active {
    opacity: 1;
    z-index: 1;
}
```

**장점:**
- 부드러운 전환 효과
- 시각적으로 우아함

**단점:**
- 모든 슬라이드가 DOM에 존재 (메모리 사용)

---

### 3. 슬라이드 효과 (`slide/`)

**특징:**
- 좌우로 슬라이드되는 효과
- transform: translateX() 사용
- GPU 가속 활용

**구현 방식:**
```javascript
// transform으로 이동
const translateX = -currentIndex * 100;
slider.style.transform = `translateX(${translateX}%)`;
```

**CSS:**
```css
.slider {
    display: flex;
    transition: transform 0.6s ease;
}
.slide {
    min-width: 100%;
    flex-shrink: 0;
}
```

**장점:**
- 동적인 시각 효과
- 성능이 좋음 (GPU 가속)
- 무한 루프 구현 용이

**단점:**
- 모든 슬라이드가 한 줄에 배치 (DOM 크기 증가)

---

### 4. 무한 루프 슬라이더 (`infinite/`)

**특징:**
- 끝없이 반복되는 슬라이더
- 첫 번째와 마지막 슬라이드 복제
- transitionend 이벤트 활용

**구현 방식:**
```javascript
// 복제본 생성
const firstClone = slides[0].cloneNode(true);
const lastClone = slides[slideCount - 1].cloneNode(true);

// transitionend로 점프
slider.addEventListener('transitionend', function() {
    if (currentIndex === 0) {
        showSlide(slideCount, false); // 애니메이션 없이 점프
    }
});
```

**장점:**
- 자연스러운 무한 루프
- 사용자 경험 향상

**단점:**
- 구현이 복잡함
- 복제본 관리 필요

---

### 5. 썸네일 슬라이더 (`thumbnail/`)

**특징:**
- 메인 슬라이더 + 썸네일 네비게이션
- 썸네일 클릭으로 이동
- 썸네일 자동 스크롤

**구현 방식:**
```javascript
// 썸네일 클릭
thumbnails.forEach(function(thumbnail, index) {
    thumbnail.addEventListener('click', function() {
        showMainSlide(index);
    });
});

// 썸네일 스크롤
activeThumbnail.scrollIntoView({
    behavior: 'smooth',
    inline: 'center'
});
```

**장점:**
- 직관적인 네비게이션
- 많은 슬라이드 관리 용이

**단점:**
- 레이아웃이 복잡함
- 이미지가 많아짐

---

### 6. 자동 재생 슬라이더 (`autoplay/`)

**특징:**
- 자동으로 슬라이드 전환
- 재생/일시정지 버튼
- 마우스 호버 시 일시 정지

**구현 방식:**
```javascript
// 자동 재생
autoPlayInterval = setInterval(function() {
    showSlide(currentIndex + 1);
}, 3000);

// 마우스 호버 시 정지
sliderWrapper.addEventListener('mouseenter', function() {
    stopAutoPlay();
});
```

**장점:**
- 사용자 개입 최소화
- 주목도 향상

**단점:**
- 자동 전환이 방해될 수 있음
- 접근성 고려 필요

---

### 7. Slick-like 슬라이더 (`slick-like/`)

**특징:**
- Slick Slider와 유사한 기능
- 여러 슬라이드 동시 표시 (반응형)
- 터치/마우스 드래그 지원
- 무한 루프
- Dots 네비게이션

**구현 방식:**
```javascript
// 화면 크기에 따라 표시 개수 변경
function getSlidesToShow() {
    if (window.innerWidth >= 1024) return 3; // 데스크톱: 3개
    if (window.innerWidth >= 768) return 2;  // 태블릿: 2개
    return 1; // 모바일: 1개
}

// 터치/마우스 드래그
slider.addEventListener('touchstart', function(e) {
    startX = e.touches[0].clientX;
});
```

**CSS:**
```css
/* 반응형: 화면 크기에 따라 표시 개수 변경 */
@media (min-width: 768px) {
    .slide { min-width: 50%; } /* 2개씩 */
}
@media (min-width: 1024px) {
    .slide { min-width: 33.333%; } /* 3개씩 */
}
```

**장점:**
- Slick Slider와 유사한 UX
- 반응형으로 여러 슬라이드 표시
- 터치 제스처 지원
- jQuery 없이 작동

**단점:**
- 구현이 복잡함
- 브라우저 호환성 고려 필요

**Slick Slider와의 차이점:**
- ✅ jQuery 의존성 없음
- ✅ 더 가벼움
- ✅ 커스터마이징 용이
- ❌ 일부 고급 기능 없음 (lazy loading, fade 효과 등)

---

## 공통 기능

### 1. 네비게이션 버튼

모든 슬라이더에 이전/다음 버튼이 포함되어 있습니다.

```javascript
prevBtn.addEventListener('click', function() {
    showSlide(currentIndex - 1);
});

nextBtn.addEventListener('click', function() {
    showSlide(currentIndex + 1);
});
```

### 2. 인디케이터

대부분의 슬라이더에 점(인디케이터) 네비게이션이 있습니다.

```javascript
indicators.forEach(function(indicator, index) {
    indicator.addEventListener('click', function() {
        showSlide(index);
    });
});
```

### 3. 키보드 네비게이션

좌우 화살표 키로 슬라이드 제어가 가능합니다.

```javascript
document.addEventListener('keydown', function(e) {
    if (e.key === 'ArrowLeft') {
        showSlide(currentIndex - 1);
    } else if (e.key === 'ArrowRight') {
        showSlide(currentIndex + 1);
    }
});
```

### 4. 반응형 디자인

모든 슬라이더는 반응형으로 작동합니다.

```css
.slider-container {
    max-width: 1200px;
    margin: 0 auto;
}

@media (max-width: 768px) {
    .slider-wrapper {
        height: 300px;
    }
}
```

---

## 비교표

| 슬라이더 | 애니메이션 | 무한 루프 | 자동 재생 | 썸네일 | 여러 슬라이드 | 터치 스와이프 | 복잡도 |
|---------|-----------|----------|----------|--------|------------|------------|--------|
| **기본** | ❌ 없음 | ✅ | ❌ | ❌ | ❌ | ❌ | ⭐ |
| **페이드** | ✅ 페이드 | ✅ | ❌ | ❌ | ❌ | ❌ | ⭐⭐ |
| **슬라이드** | ✅ 슬라이드 | ✅ | ❌ | ❌ | ❌ | ❌ | ⭐⭐ |
| **무한 루프** | ✅ 슬라이드 | ✅ 자연스러움 | ❌ | ❌ | ❌ | ❌ | ⭐⭐⭐ |
| **썸네일** | ✅ 슬라이드 | ✅ | ❌ | ✅ | ❌ | ❌ | ⭐⭐⭐ |
| **자동 재생** | ✅ 슬라이드 | ✅ | ✅ | ❌ | ❌ | ❌ | ⭐⭐⭐ |
| **Slick-like** | ✅ 슬라이드 | ✅ | ❌ | ❌ | ✅ 반응형 | ✅ | ⭐⭐⭐⭐ |

---

## 실전 활용 팁

### 1. 성능 최적화

**이미지 지연 로딩:**
```html
<img src="images/slide1.jpg" loading="lazy" alt="Slide 1">
```

**will-change 사용:**
```css
.slider {
    will-change: transform;
}
```

### 2. 접근성 향상

**ARIA 속성 추가:**
```html
<div class="slider" role="region" aria-label="이미지 슬라이더">
    <div class="slide" aria-hidden="false">
```

**키보드 포커스:**
```css
.prev-btn:focus,
.next-btn:focus {
    outline: 2px solid #007bff;
}
```

### 3. 터치 제스처 지원

```javascript
let startX = 0;
let currentX = 0;

slider.addEventListener('touchstart', function(e) {
    startX = e.touches[0].clientX;
});

slider.addEventListener('touchend', function(e) {
    const diff = startX - currentX;
    if (Math.abs(diff) > 50) {
        if (diff > 0) {
            showSlide(currentIndex + 1);
        } else {
            showSlide(currentIndex - 1);
        }
    }
});
```

### 4. 이미지 최적화

**WebP 형식 사용:**
```html
<picture>
    <source srcset="images/slide1.webp" type="image/webp">
    <img src="images/slide1.jpg" alt="Slide 1">
</picture>
```

**반응형 이미지:**
```html
<img srcset="images/slide1-small.jpg 480w,
             images/slide1-medium.jpg 768w,
             images/slide1-large.jpg 1200w"
     sizes="(max-width: 480px) 100vw,
            (max-width: 768px) 100vw,
            1200px"
     src="images/slide1-large.jpg" alt="Slide 1">
```

---

## 마무리

이 슬라이더 모음집을 통해 jQuery 없이도 다양한 슬라이더를 구현할 수 있습니다.

**선택 가이드:**
- **간단한 갤러리**: 기본 슬라이더
- **포트폴리오**: 페이드 슬라이더
- **제품 소개**: 슬라이드 효과
- **배너**: 무한 루프 + 자동 재생
- **이미지 갤러리**: 썸네일 슬라이더
- **Slick Slider 대체**: Slick-like 슬라이더 (여러 슬라이드 표시, 터치 스와이프 필요 시)

각 슬라이더는 독립적으로 작동하며, 필요에 따라 기능을 조합할 수 있습니다!
