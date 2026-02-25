# 스와이프 슬라이더 완전 정복

## 📋 목차
1. [스와이프 슬라이더란?](#스와이프-슬라이더란)
2. [터치 이벤트 기본 개념](#터치-이벤트-기본-개념)
3. [Vanilla JS 구현](#vanilla-js-구현)
4. [라이브러리별 구현](#라이브러리별-구현)
5. [비교표](#비교표)

---

## 스와이프 슬라이더란?

**스와이프 슬라이더**는 터치 제스처(스와이프)로 슬라이드를 이동시킬 수 있는 슬라이더입니다.

### 특징
- ✅ 터치 스와이프 지원 (모바일)
- ✅ 마우스 드래그 지원 (데스크톱)
- ✅ 자연스러운 사용자 경험
- ✅ 모던 웹 표준

---

## 터치 이벤트 기본 개념

### 터치 이벤트 종류

| 이벤트 | 설명 |
|--------|------|
| `touchstart` | 터치 시작 |
| `touchmove` | 터치 이동 중 |
| `touchend` | 터치 종료 |
| `touchcancel` | 터치 취소 |

### 마우스 이벤트

| 이벤트 | 설명 |
|--------|------|
| `mousedown` | 마우스 버튼 누름 |
| `mousemove` | 마우스 이동 |
| `mouseup` | 마우스 버튼 놓음 |

---

## Vanilla JS 구현

순수 JavaScript로 구현한 스와이프 슬라이더 예제는 `example/` 폴더를 참고하세요.

### 주요 기능
- 터치 스와이프
- 마우스 드래그
- 스와이프 임계값 설정
- 부드러운 애니메이션

---

## 라이브러리별 구현

### bxSlider
```javascript
$('.slider').bxSlider({
    touchEnabled: true,
    swipeThreshold: 50
});
```

### Slick Slider
```javascript
$('.slider').slick({
    swipe: true,
    touchMove: true,
    swipeToSlide: true
});
```

### Swiper.js
```javascript
const swiper = new Swiper('.swiper', {
    touchEventsTarget: 'container',
    threshold: 10
});
```

---

## 비교표

| 라이브러리 | 터치 지원 | 마우스 드래그 | 커스터마이징 | 파일 크기 |
|-----------|----------|--------------|------------|----------|
| **Vanilla JS** | ✅ | ✅ | ⭐⭐⭐⭐⭐ | ~5KB |
| **bxSlider** | ✅ | ❌ | ⭐⭐⭐ | ~15KB |
| **Slick Slider** | ✅ | ❌ | ⭐⭐⭐⭐ | ~50KB |
| **Swiper.js** | ✅ | ✅ | ⭐⭐⭐⭐⭐ | ~40KB |

---

## 참고 자료

- [Vanilla JS 예제](./example/)
- [bxSlider 문서](../bxslider/)
- [Slick Slider 문서](../slick_slider/)
