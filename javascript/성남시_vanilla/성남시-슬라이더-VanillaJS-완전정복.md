# 성남시 이미지 슬라이더 - Vanilla JS 완전 정복

## 📋 목차
1. [개요](#개요)
2. [jQuery vs Vanilla JS 비교](#jquery-vs-vanilla-js-비교)
3. [코드 상세 설명](#코드-상세-설명)
4. [핵심 개념](#핵심-개념)
5. [실행 흐름](#실행-흐름)

---

## 개요

이 프로젝트는 jQuery 플러그인(bxSlider)을 사용하던 이미지 슬라이더를 **순수 JavaScript(Vanilla JS)**로 변환한 것입니다.

**주요 기능:**
- ✅ 이미지 자동 슬라이드
- ✅ 이전/다음 버튼으로 수동 제어
- ✅ 자동 재생 정지/재개 버튼
- ✅ 현재 슬라이드 번호 표시
- ✅ 부드러운 페이드 전환 효과

---

## jQuery vs Vanilla JS 비교

### jQuery 버전 (원본)

```javascript
$(function(){
    let slide = $('.bxup').bxSlider({
        auto: true,
        controls:false,
        pager:false,
        onSlideAfter:function($slideElement, oldIndex, newIndex){
            $('.count span').text(newIndex + 1);
        }
    });
    $('.prev').click(function(){
        slide.goToPrevSlide()
        return false;
    })
    $('.next').click(function(){
        slide.goToNextSlide()
        return false;
    })
    $('.stop').click(function (){
        if($('.stop').hasClass('on') == false){
            $(this).addClass('on');
            slide.stopAuto();
        } else {
            $(this).removeClass('on');
            slide.startAuto();
        }
    })
})
```

### Vanilla JS 버전 (변환)

```javascript
document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('slider');
    const slides = slider.querySelectorAll('li');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const stopBtn = document.getElementById('stopBtn');
    
    let currentIndex = 0;
    let autoPlayInterval = null;
    let isAutoPlay = true;
    
    // 자동 재생 시작
    startAutoPlay();
    
    // 이전 버튼 클릭
    prevBtn.addEventListener('click', function(e) {
        e.preventDefault();
        goToPrevSlide();
    });
    
    // 다음 버튼 클릭
    nextBtn.addEventListener('click', function(e) {
        e.preventDefault();
        goToNextSlide();
    });
    
    // 정지/재생 버튼 클릭
    stopBtn.addEventListener('click', function(e) {
        e.preventDefault();
        toggleAutoPlay();
    });
    
    // 함수 구현...
});
```

### 변환 비교표

| 항목 | jQuery | Vanilla JS |
|------|--------|------------|
| **DOM 선택** | `$('.class')` | `document.querySelector()` |
| **이벤트 리스너** | `.click()` | `addEventListener('click')` |
| **클래스 추가** | `.addClass()` | `classList.add()` |
| **클래스 제거** | `.removeClass()` | `classList.remove()` |
| **클래스 확인** | `.hasClass()` | `classList.contains()` |
| **텍스트 변경** | `.text()` | `textContent` |
| **자동 재생** | `bxSlider` 플러그인 | `setInterval()` |
| **문서 준비** | `$(function(){})` | `DOMContentLoaded` |

---

## 코드 상세 설명

### 1. HTML 구조

```html
<article>
    <ul class="slider" id="slider">
        <li><img src="img/s1.png" alt=""></li>
        <li><img src="img/s2.jpg" alt=""></li>
        <!-- ... -->
    </ul>
</article>
<div class="down">
    <div class="count">
        <span id="currentSlide">1</span> / <span id="totalSlides">7</span>
    </div>
    <div class="over-btn">
        <span class="prev" id="prevBtn"></span>
        <span class="stop" id="stopBtn"></span>
        <span class="next" id="nextBtn"></span>
    </div>
</div>
```

**변경 사항:**
- `bxup` 클래스 → `slider` 클래스로 변경
- 각 요소에 `id` 추가 (JavaScript에서 쉽게 접근)

### 2. CSS 스타일

```css
.slider li {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    opacity: 0;  /* 기본적으로 숨김 */
    transition: opacity 0.5s ease-in-out;  /* 페이드 효과 */
}

.slider li.active {
    opacity: 1;  /* 활성 슬라이드만 보임 */
    z-index: 1;
}
```

**핵심 개념:**
- 모든 슬라이드를 `position: absolute`로 겹쳐서 배치
- `opacity: 0`으로 기본 숨김
- `active` 클래스가 있는 슬라이드만 `opacity: 1`로 표시
- `transition`으로 부드러운 페이드 효과

### 3. JavaScript 로직

#### 3.1 변수 선언

```javascript
const slider = document.getElementById('slider');
const slides = slider.querySelectorAll('li');
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');
const stopBtn = document.getElementById('stopBtn');
const currentSlideSpan = document.getElementById('currentSlide');
const totalSlidesSpan = document.getElementById('totalSlides');

let currentIndex = 0;  // 현재 슬라이드 인덱스
let autoPlayInterval = null;  // 자동 재생 타이머
let isAutoPlay = true;  // 자동 재생 상태
```

#### 3.2 슬라이드 표시 함수

```javascript
function showSlide(index) {
    // 모든 슬라이드 숨기기
    slides.forEach(function(slide) {
        slide.classList.remove('active');
    });
    
    // 현재 슬라이드 표시
    slides[index].classList.add('active');
    
    // 현재 슬라이드 번호 업데이트
    currentSlideSpan.textContent = index + 1;
}
```

**동작 과정:**
1. 모든 슬라이드에서 `active` 클래스 제거
2. 현재 인덱스의 슬라이드에 `active` 클래스 추가
3. 슬라이드 번호 업데이트

#### 3.3 이전/다음 슬라이드 이동

```javascript
function goToPrevSlide() {
    currentIndex--;
    if (currentIndex < 0) {
        currentIndex = slides.length - 1;  // 마지막 슬라이드로
    }
    showSlide(currentIndex);
}

function goToNextSlide() {
    currentIndex++;
    if (currentIndex >= slides.length) {
        currentIndex = 0;  // 첫 번째 슬라이드로
    }
    showSlide(currentIndex);
}
```

**무한 루프 구현:**
- 이전 버튼: 첫 번째 슬라이드에서 이전 버튼 클릭 시 마지막 슬라이드로 이동
- 다음 버튼: 마지막 슬라이드에서 다음 버튼 클릭 시 첫 번째 슬라이드로 이동

#### 3.4 자동 재생 함수

```javascript
function startAutoPlay() {
    if (autoPlayInterval) {
        clearInterval(autoPlayInterval);
    }
    
    autoPlayInterval = setInterval(function() {
        goToNextSlide();
    }, 3000);  // 3초마다 실행
    
    isAutoPlay = true;
    stopBtn.classList.remove('on');
}

function stopAutoPlay() {
    if (autoPlayInterval) {
        clearInterval(autoPlayInterval);
        autoPlayInterval = null;
    }
    isAutoPlay = false;
    stopBtn.classList.add('on');
}

function toggleAutoPlay() {
    if (isAutoPlay) {
        stopAutoPlay();
    } else {
        startAutoPlay();
    }
}
```

**동작 과정:**
1. `setInterval()`로 3초마다 `goToNextSlide()` 호출
2. 정지 버튼 클릭 시 `clearInterval()`로 타이머 제거
3. 재생 버튼 클릭 시 다시 `setInterval()` 시작

#### 3.5 이벤트 리스너

```javascript
// 이전 버튼 클릭
prevBtn.addEventListener('click', function(e) {
    e.preventDefault();
    goToPrevSlide();
});

// 다음 버튼 클릭
nextBtn.addEventListener('click', function(e) {
    e.preventDefault();
    goToNextSlide();
});

// 정지/재생 버튼 클릭
stopBtn.addEventListener('click', function(e) {
    e.preventDefault();
    toggleAutoPlay();
});
```

**`e.preventDefault()`의 역할:**
- 기본 동작 방지 (필요한 경우)
- `<a>` 태그나 폼 제출 등의 기본 동작을 막음

---

## 핵심 개념

### 1. DOMContentLoaded 이벤트

```javascript
document.addEventListener('DOMContentLoaded', function() {
    // DOM이 완전히 로드된 후 실행
});
```

**설명:**
- HTML 문서가 완전히 로드되고 파싱된 후 실행
- jQuery의 `$(function(){})`와 동일한 역할
- 스크립트가 `<head>`에 있어도 안전하게 실행 가능

### 2. querySelector와 querySelectorAll

```javascript
// 단일 요소 선택
const slider = document.getElementById('slider');
const prevBtn = document.querySelector('#prevBtn');

// 여러 요소 선택
const slides = slider.querySelectorAll('li');
```

**비교:**
- `getElementById()`: ID로 단일 요소 선택
- `querySelector()`: CSS 선택자로 단일 요소 선택
- `querySelectorAll()`: CSS 선택자로 여러 요소 선택 (NodeList 반환)

### 3. classList API

```javascript
// 클래스 추가
element.classList.add('active');

// 클래스 제거
element.classList.remove('active');

// 클래스 토글
element.classList.toggle('active');

// 클래스 확인
if (element.classList.contains('active')) {
    // ...
}
```

**jQuery 비교:**
- `.addClass()` → `classList.add()`
- `.removeClass()` → `classList.remove()`
- `.toggleClass()` → `classList.toggle()`
- `.hasClass()` → `classList.contains()`

### 4. setInterval과 clearInterval

```javascript
// 타이머 시작
const interval = setInterval(function() {
    // 반복 실행할 코드
}, 3000);  // 3초마다 실행

// 타이머 정지
clearInterval(interval);
```

**설명:**
- `setInterval()`: 일정 시간마다 함수 반복 실행
- `clearInterval()`: 반복 실행 중지
- 반환값(interval ID)을 저장해두어야 정지 가능

### 5. forEach 메서드

```javascript
slides.forEach(function(slide) {
    slide.classList.remove('active');
});

// 화살표 함수 사용
slides.forEach(slide => {
    slide.classList.remove('active');
});
```

**설명:**
- 배열이나 NodeList의 각 요소에 대해 함수 실행
- jQuery의 `.each()`와 유사한 기능

---

## 실행 흐름

### 초기화 단계

```
1. DOMContentLoaded 이벤트 발생
   ↓
2. DOM 요소 선택 (slider, slides, buttons 등)
   ↓
3. 변수 초기화 (currentIndex = 0, isAutoPlay = true)
   ↓
4. 초기 슬라이드 표시 (showSlide(0))
   ↓
5. 자동 재생 시작 (startAutoPlay())
   ↓
6. 이벤트 리스너 등록 (버튼 클릭 이벤트)
```

### 사용자 상호작용

```
사용자가 "다음" 버튼 클릭
   ↓
nextBtn.addEventListener('click') 실행
   ↓
goToNextSlide() 호출
   ↓
currentIndex 증가
   ↓
showSlide(currentIndex) 호출
   ↓
모든 슬라이드에서 'active' 클래스 제거
   ↓
현재 슬라이드에 'active' 클래스 추가
   ↓
CSS transition으로 페이드 효과 발생
   ↓
슬라이드 번호 업데이트
```

### 자동 재생 흐름

```
startAutoPlay() 호출
   ↓
setInterval() 시작 (3초마다 실행)
   ↓
3초 경과
   ↓
goToNextSlide() 자동 호출
   ↓
다음 슬라이드로 이동
   ↓
3초 경과
   ↓
반복...
```

---

## 주요 개선 사항

### 1. 의존성 제거

**변경 전:**
- jQuery 라이브러리 필요
- bxSlider 플러그인 필요

**변경 후:**
- 순수 JavaScript만 사용
- 외부 라이브러리 불필요

### 2. 성능 향상

- jQuery 오버헤드 제거
- 더 가벼운 코드
- 더 빠른 실행 속도

### 3. 코드 가독성

- 명확한 함수명
- 단계별 주석
- 구조화된 코드

### 4. 유지보수성

- 표준 JavaScript 사용
- 플러그인 업데이트 불필요
- 커스터마이징 용이

---

## 추가 기능 (선택사항)

### 마우스 호버 시 일시 정지

```javascript
slider.addEventListener('mouseenter', function() {
    if (isAutoPlay) {
        clearInterval(autoPlayInterval);
    }
});

slider.addEventListener('mouseleave', function() {
    if (isAutoPlay) {
        startAutoPlay();
    }
});
```

**설명:**
- 마우스를 슬라이더 위에 올리면 자동 재생 일시 정지
- 마우스를 떼면 자동 재생 재개

---

## 마무리

이 프로젝트는 jQuery 플러그인을 순수 JavaScript로 변환하는 완벽한 예제입니다.

**핵심 학습 포인트:**
1. ✅ DOM 선택 및 조작
2. ✅ 이벤트 리스너 등록
3. ✅ 클래스 추가/제거
4. ✅ setInterval/clearInterval 사용
5. ✅ 페이드 효과 구현
6. ✅ 무한 루프 슬라이더

**다음 단계:**
- 터치 이벤트 추가 (모바일 지원)
- 키보드 네비게이션 추가 (좌우 화살표 키)
- 페이징 인디케이터 추가
- 애니메이션 효과 다양화

이제 순수 JavaScript로 슬라이더를 구현할 수 있습니다!
