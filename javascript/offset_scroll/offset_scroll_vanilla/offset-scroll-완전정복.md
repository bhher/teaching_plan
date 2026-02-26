# Offset Scroll - 완전 정복 (순수 JavaScript 버전)

## 📋 목차
1. [개요](#개요)
2. [전체 코드 구조](#전체-코드-구조)
3. [코드 라인별 상세 분석](#코드-라인별-상세-분석)
4. [핵심 개념](#핵심-개념)
5. [실행 흐름](#실행-흐름)
6. [jQuery vs 순수 JavaScript 비교](#jquery-vs-순수-javascript-비교)
7. [개선 가능한 부분](#개선-가능한-부분)

---

## 개요

이 코드는 **스크롤 위치에 따른 네비게이션 활성화**와 **부드러운 스크롤 이동**을 구현한 예제입니다. jQuery 없이 순수 JavaScript만으로 작성되었습니다.

**주요 기능:**
- ✅ 스크롤 위치에 따른 네비게이션 자동 활성화
- ✅ floatdiv가 스크롤을 따라다니는 효과
- ✅ 네비게이션 클릭 시 부드러운 스크롤 이동
- ✅ 드래그 가능한 팝업
- ✅ 쿠키를 이용한 팝업 표시 제어
- ✅ fadeIn/fadeOut 애니메이션

---

## 전체 코드 구조

```javascript
document.addEventListener('DOMContentLoaded', function(){
    // 1. 쿠키 관련 함수
    // 2. DOM 요소 선택
    // 3. 초기값 설정
    // 4. 부드러운 스크롤 함수
    // 5. 스크롤 이벤트 처리
    // 6. 클릭 이벤트 처리
    // 7. 드래그 기능 구현
    // 8. 팝업 관련 기능
});
```

---

## 코드 라인별 상세 분석

### 1. DOMContentLoaded 이벤트 리스너

```javascript
document.addEventListener('DOMContentLoaded', function(){
```

**설명:**
- DOM이 완전히 로드된 후 실행
- HTML 요소를 선택하기 전에 문서가 준비되어야 함
- jQuery의 `$(function(){})`와 동일한 역할

---

### 2. 쿠키 관련 함수

#### 2-1. setCookie 함수

```javascript
function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}
```

**매개변수:**
- `name`: 쿠키 이름
- `value`: 쿠키 값
- `days`: 유효 기간 (일 단위)

**동작 과정:**
1. `days`가 있으면 만료 날짜 계산
2. 현재 시간 + (days × 24시간 × 60분 × 60초 × 1000밀리초)
3. `document.cookie`에 쿠키 저장

**예시:**
```javascript
setCookie('pop', 'no', 1); // 'pop' 쿠키에 'no' 값을 1일 동안 저장
```

---

#### 2-2. getCookie 함수

```javascript
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}
```

**동작 과정:**
1. `document.cookie`를 `;`로 분리하여 배열 생성
2. 각 쿠키를 순회하며 앞뒤 공백 제거
3. 찾는 쿠키 이름과 일치하면 값 반환
4. 없으면 `null` 반환

**예시:**
```javascript
var popValue = getCookie('pop'); // 'pop' 쿠키 값 가져오기
```

---

### 3. DOM 요소 선택

```javascript
var articles = document.querySelectorAll('section > article');
var navLis = document.querySelectorAll('nav ul li');
var floatdivLis = document.querySelectorAll('#floatdiv ul li');
var floatdiv = document.getElementById('floatdiv');
var nav = document.querySelector('nav');
var sTop = document.getElementById('sTop');
var popup = document.getElementById('popup');
var noticeWrap = document.getElementById('notice_wrap');
var expiresChk = document.getElementById('expiresChk');
var closeBtn = document.querySelector('.closeBtn');
```

**요소 설명:**

| 변수 | 선택자 | 설명 |
|------|--------|------|
| `articles` | `'section > article'` | 모든 article 요소들 |
| `navLis` | `'nav ul li'` | 네비게이션 메뉴 항목들 |
| `floatdivLis` | `'#floatdiv ul li'` | floatdiv 내부의 버튼들 |
| `floatdiv` | `'#floatdiv'` | 스크롤을 따라다니는 div |
| `nav` | `'nav'` | 네비게이션 바 |
| `sTop` | `'#sTop'` | 스크롤 위치를 표시하는 span |
| `popup` | `'#popup'` | 팝업 창 |
| `noticeWrap` | `'#notice_wrap'` | 공지사항 팝업 |
| `expiresChk` | `'#expiresChk'` | 체크박스 |
| `closeBtn` | `'.closeBtn'` | 닫기 버튼 |

---

### 4. 초기값 설정

#### 4-1. article의 offset().top 값 저장

```javascript
var articleOffsets = [];
articles.forEach(function(article){
    articleOffsets.push(article.getBoundingClientRect().top + window.pageYOffset);
});
```

**설명:**
- 각 article의 초기 위치를 배열에 저장
- `getBoundingClientRect().top`: 뷰포트 기준 상대 위치
- `window.pageYOffset`: 현재 스크롤 위치
- 합산하면 문서 전체 기준 절대 위치

**왜 저장하나요?**
- 스크롤 이벤트에서 매번 계산하면 성능 저하
- 초기값을 저장해두고 재사용

---

#### 4-2. floatdiv의 초기 위치 저장

```javascript
var dTop = floatdiv.getBoundingClientRect().top + window.pageYOffset - window.pageYOffset;
```

**설명:**
- `dTop`: floatdiv의 초기 top 위치
- 스크롤에 따라 floatdiv의 위치를 조정할 때 기준점으로 사용

**주의:**
- `- window.pageYOffset`을 한 번 더 빼는 것은 초기 로드 시 스크롤이 0이므로 실제로는 `getBoundingClientRect().top`만 저장

---

### 5. 부드러운 스크롤 함수

```javascript
function smoothScrollTo(targetTop, duration) {
    duration = duration || 1300;
    var startPosition = window.pageYOffset;
    var distance = targetTop - startPosition;
    var startTime = null;

    function step(timestamp) {
        if (!startTime) startTime = timestamp;
        var progress = timestamp - startTime;
        var percentage = Math.min(progress / duration, 1);
        
        // easing function (easeInOutCubic)
        percentage = percentage < 0.5 
            ? 4 * percentage * percentage * percentage 
            : 1 - Math.pow(-2 * percentage + 2, 3) / 2;
        
        window.scrollTo(0, startPosition + distance * percentage);
        
        if (progress < duration) {
            window.requestAnimationFrame(step);
        }
    }
    
    window.requestAnimationFrame(step);
}
```

**매개변수:**
- `targetTop`: 목표 스크롤 위치
- `duration`: 애니메이션 시간 (기본값 1300ms)

**동작 과정:**

1. **초기값 설정**
   ```javascript
   var startPosition = window.pageYOffset; // 시작 위치
   var distance = targetTop - startPosition; // 이동할 거리
   ```

2. **애니메이션 루프**
   ```javascript
   function step(timestamp) {
       // timestamp: requestAnimationFrame이 전달하는 시간값
   }
   ```

3. **진행률 계산**
   ```javascript
   var progress = timestamp - startTime; // 경과 시간
   var percentage = Math.min(progress / duration, 1); // 0~1 사이 값
   ```

4. **Easing 함수 (easeInOutCubic)**
   ```javascript
   percentage = percentage < 0.5 
       ? 4 * percentage * percentage * percentage  // 가속
       : 1 - Math.pow(-2 * percentage + 2, 3) / 2; // 감속
   ```
   - 처음에는 천천히 시작
   - 중간에는 빠르게
   - 끝에는 천천히 멈춤

5. **스크롤 위치 업데이트**
   ```javascript
   window.scrollTo(0, startPosition + distance * percentage);
   ```

**jQuery `.animate()`와 비교:**
```javascript
// jQuery
$('html, body').animate({scrollTop: offset_t}, 1300);

// 순수 JavaScript
smoothScrollTo(offset_t, 1300);
```

---

### 6. 스크롤 이벤트 처리

```javascript
var isScrolling = false;
window.addEventListener('scroll', function(){
    if (isScrolling) return; // 애니메이션 중이면 무시
    
    var sct = window.pageYOffset || document.documentElement.scrollTop;
    
    // 스크롤 위치 표시
    sTop.textContent = Math.round(sct);
    
    // floatdiv 위치 조정
    // ...
    
    // nav에 'on' 클래스 추가/제거
    // ...
    
    // 네비게이션 활성화
    // ...
});
```

#### 6-1. isScrolling 플래그

```javascript
var isScrolling = false;
if (isScrolling) return;
```

**설명:**
- 부드러운 스크롤 애니메이션 중에는 스크롤 이벤트 무시
- 중복 실행 방지

---

#### 6-2. 스크롤 위치 표시

```javascript
var sct = window.pageYOffset || document.documentElement.scrollTop;
sTop.textContent = Math.round(sct);
```

**설명:**
- `window.pageYOffset`: 현재 스크롤 위치 (표준)
- `document.documentElement.scrollTop`: 구형 브라우저 호환
- `Math.round()`: 소수점 반올림

---

#### 6-3. floatdiv 위치 조정

```javascript
var currentTop = floatdiv.getBoundingClientRect().top + window.pageYOffset;
var targetTop = dTop + sct;

function updateFloatDiv() {
    var current = floatdiv.getBoundingClientRect().top + window.pageYOffset;
    var diff = targetTop - current;
    if (Math.abs(diff) > 1) {
        floatdiv.style.top = (current - window.pageYOffset + diff * 0.1) + 'px';
        requestAnimationFrame(updateFloatDiv);
    }
}
updateFloatDiv();
```

**동작 과정:**
1. 목표 위치 계산: `dTop + sct`
2. 현재 위치와의 차이 계산
3. 차이의 10%씩 이동 (부드러운 애니메이션)
4. `requestAnimationFrame`으로 반복

**jQuery `.stop().animate()`와 비교:**
```javascript
// jQuery
$('#floatdiv').stop().animate({top:dTop+sct}, 500);

// 순수 JavaScript
// updateFloatDiv() 함수로 구현
```

---

#### 6-4. nav에 'on' 클래스 추가/제거

```javascript
if(sct > 0){
    nav.classList.add('on');
} else {
    nav.classList.remove('on');
}
```

**설명:**
- 스크롤이 0보다 크면 nav에 'on' 클래스 추가
- 스크롤이 0이면 'on' 클래스 제거
- CSS에서 `.nav.on` 스타일로 배경색 변경

---

#### 6-5. 네비게이션 활성화

```javascript
for(var i = 0; i < articles.length; i++){
    var articleTop = articles[i].getBoundingClientRect().top + window.pageYOffset;
    
    if(sct >= articleTop - 100){
        // 모든 on 클래스 제거
        navLis.forEach(function(li){
            li.classList.remove('on');
        });
        floatdivLis.forEach(function(li){
            li.classList.remove('on');
        });
        
        // 현재 섹션에 on 클래스 추가
        navLis[i].classList.add('on');
        floatdivLis[i].classList.add('on');
    }
}
```

**동작 과정:**
1. 각 article의 현재 위치 계산
2. 스크롤 위치가 article 위치 - 100px 이상이면 활성화
3. 모든 메뉴에서 'on' 클래스 제거
4. 현재 섹션에 해당하는 메뉴에 'on' 클래스 추가

**왜 -100px인가요?**
- 약간의 여유 공간을 두어 스크롤이 article에 도달하기 전에 활성화
- 사용자 경험 향상

---

### 7. 클릭 이벤트 처리

#### 7-1. floatdiv 클릭 이벤트

```javascript
floatdivLis.forEach(function(li, index){
    li.addEventListener('click', function(e){
        e.preventDefault();
        
        var articleTop = articles[index].getBoundingClientRect().top + window.pageYOffset;
        
        isScrolling = true;
        smoothScrollTo(articleTop, 1300);
        
        setTimeout(function(){
            isScrolling = false;
        }, 1300);
        
        // 네비게이션 활성화
        // ...
    });
});
```

**동작 과정:**
1. 클릭한 버튼의 인덱스로 해당 article 찾기
2. article의 위치 계산
3. 부드러운 스크롤 이동
4. 네비게이션 활성화

---

#### 7-2. nav 클릭 이벤트

```javascript
navLis.forEach(function(li, index){
    li.addEventListener('click', function(e){
        e.preventDefault();
        
        var articleTop = articles[index].getBoundingClientRect().top + window.pageYOffset;
        
        isScrolling = true;
        smoothScrollTo(articleTop, 1300);
        
        setTimeout(function(){
            isScrolling = false;
        }, 1300);
        
        // 네비게이션 활성화
        // ...
    });
});
```

**설명:**
- floatdiv 클릭 이벤트와 동일한 로직
- 네비게이션 메뉴 클릭 시 해당 섹션으로 이동

---

### 8. 드래그 기능 구현

```javascript
function makeDraggable(element) {
    var isDragging = false;
    var currentX;
    var currentY;
    var initialX;
    var initialY;
    var xOffset = 0;
    var yOffset = 0;

    element.addEventListener('mousedown', dragStart);
    document.addEventListener('mousemove', drag);
    document.addEventListener('mouseup', dragEnd);

    function dragStart(e) {
        if (e.type === "touchstart") {
            initialX = e.touches[0].clientX - xOffset;
            initialY = e.touches[0].clientY - yOffset;
        } else {
            initialX = e.clientX - xOffset;
            initialY = e.clientY - yOffset;
        }

        if (e.target === element || element.contains(e.target)) {
            isDragging = true;
        }
    }

    function drag(e) {
        if (isDragging) {
            e.preventDefault();
            
            if (e.type === "touchmove") {
                currentX = e.touches[0].clientX - initialX;
                currentY = e.touches[0].clientY - initialY;
            } else {
                currentX = e.clientX - initialX;
                currentY = e.clientY - initialY;
            }

            xOffset = currentX;
            yOffset = currentY;

            element.style.transform = "translate(" + currentX + "px, " + currentY + "px)";
        }
    }

    function dragEnd(e) {
        initialX = currentX;
        initialY = currentY;
        isDragging = false;
    }
}
```

**동작 과정:**

1. **dragStart (드래그 시작)**
   - 마우스/터치 시작 위치 저장
   - `isDragging = true` 설정

2. **drag (드래그 중)**
   - 마우스/터치 이동 위치 계산
   - `transform: translate()`로 요소 이동

3. **dragEnd (드래그 종료)**
   - `isDragging = false` 설정
   - 현재 위치를 초기 위치로 저장

**jQuery UI `.draggable()`와 비교:**
```javascript
// jQuery UI
$('#popup').draggable();

// 순수 JavaScript
makeDraggable(popup);
```

---

### 9. fadeIn/fadeOut 함수

#### 9-1. fadeOut 함수

```javascript
function fadeOut(element, duration) {
    duration = duration || 200;
    var startOpacity = parseFloat(window.getComputedStyle(element).opacity) || 1;
    var startTime = null;

    function step(timestamp) {
        if (!startTime) startTime = timestamp;
        var progress = timestamp - startTime;
        var percentage = Math.min(progress / duration, 1);
        var opacity = startOpacity * (1 - percentage);
        
        element.style.opacity = opacity;
        
        if (progress < duration) {
            window.requestAnimationFrame(step);
        } else {
            element.style.display = 'none';
            element.style.opacity = '';
        }
    }
    
    element.style.opacity = startOpacity;
    element.style.display = 'block';
    window.requestAnimationFrame(step);
}
```

**동작 과정:**
1. 현재 opacity 값 가져오기
2. 0부터 1까지 점진적으로 감소
3. 완료 후 `display: none` 설정

---

#### 9-2. fadeIn 함수

```javascript
function fadeIn(element, duration) {
    duration = duration || 200;
    element.style.display = 'block';
    element.style.opacity = '0';
    
    var startTime = null;
    function step(timestamp) {
        if (!startTime) startTime = timestamp;
        var progress = timestamp - startTime;
        var percentage = Math.min(progress / duration, 1);
        
        element.style.opacity = percentage;
        
        if (progress < duration) {
            window.requestAnimationFrame(step);
        } else {
            element.style.opacity = '';
        }
    }
    
    window.requestAnimationFrame(step);
}
```

**동작 과정:**
1. 요소를 보이게 설정 (`display: block`)
2. opacity를 0으로 설정
3. 0부터 1까지 점진적으로 증가

**jQuery `.fadeOut()`, `.fadeIn()`과 비교:**
```javascript
// jQuery
$('#popup').fadeOut('fast');
$('#popup').fadeIn('fast');

// 순수 JavaScript
fadeOut(popup, 200);
fadeIn(popup, 200);
```

---

### 10. 팝업 관련 기능

#### 10-1. 팝업 표시 (쿠키 확인)

```javascript
if(getCookie('pop') != 'no'){
    popup.style.display = 'block';
    fadeIn(popup, 200);
}
```

**설명:**
- 'pop' 쿠키가 'no'가 아니면 팝업 표시
- fadeIn 애니메이션 적용

---

#### 10-2. 팝업 닫기 버튼

```javascript
var popupAreas = document.querySelectorAll('#popup area');
if(popupAreas.length > 0){
    // 첫 번째 area (창닫기)
    popupAreas[0].addEventListener('click', function(e){
        e.preventDefault();
        fadeOut(popup, 200);
        return false;
    });

    // 두 번째 area (하루동안 안보기)
    popupAreas[1].addEventListener('click', function(e){
        e.preventDefault();
        setCookie('pop', 'no', 1); // 1일
        fadeOut(popup, 200);
        return false;
    });
}
```

**설명:**
- 첫 번째 area: 그냥 닫기
- 두 번째 area: 쿠키 저장 후 닫기 (1일 동안 안보기)

---

#### 10-3. 공지사항 팝업

```javascript
if(getCookie('popup') == 'none'){
    noticeWrap.style.display = 'none';
}

if(closeBtn){
    closeBtn.addEventListener('click', function(){
        if(expiresChk && expiresChk.checked){
            setCookie('popup', 'none', 3); // 3일
        }
        fadeOut(noticeWrap, 200);
    });
}
```

**설명:**
- 'popup' 쿠키가 'none'이면 숨김
- 체크박스가 체크되어 있으면 3일 동안 쿠키 저장

---

### 11. 리사이즈 이벤트

```javascript
window.addEventListener('resize', function(){
    articleOffsets = [];
    articles.forEach(function(article){
        articleOffsets.push(article.getBoundingClientRect().top + window.pageYOffset);
    });
    dTop = floatdiv.getBoundingClientRect().top + window.pageYOffset - window.pageYOffset;
});
```

**설명:**
- 창 크기 변경 시 article 위치 재계산
- floatdiv 위치도 재계산

---

## 핵심 개념

### 1. getBoundingClientRect()

```javascript
element.getBoundingClientRect().top
```

**설명:**
- 요소의 뷰포트 기준 위치 반환
- `.top`: 요소의 상단 위치
- 스크롤 위치를 더하면 문서 전체 기준 절대 위치

**예시:**
```javascript
var absoluteTop = element.getBoundingClientRect().top + window.pageYOffset;
```

---

### 2. requestAnimationFrame

```javascript
window.requestAnimationFrame(step);
```

**설명:**
- 브라우저가 다음 리페인트 전에 함수 실행
- 부드러운 애니메이션을 위한 최적의 방법
- 60fps 목표로 실행

**장점:**
- 브라우저가 최적화하여 실행
- 탭이 비활성화되면 자동으로 중지
- `setTimeout`보다 정확함

---

### 3. Easing 함수

```javascript
percentage = percentage < 0.5 
    ? 4 * percentage * percentage * percentage  // 가속
    : 1 - Math.pow(-2 * percentage + 2, 3) / 2; // 감속
```

**설명:**
- 애니메이션의 가속/감속 곡선
- `easeInOutCubic`: 처음과 끝이 느리고 중간이 빠름
- 자연스러운 움직임 구현

---

### 4. transform vs position

```javascript
// transform 사용 (드래그)
element.style.transform = "translate(" + currentX + "px, " + currentY + "px)";

// position 사용 (floatdiv)
element.style.top = value + 'px';
```

**차이점:**
- `transform`: GPU 가속, 레이아웃 재계산 없음 (성능 좋음)
- `position`: 레이아웃 재계산 필요 (성능 나쁨)

---

## 실행 흐름

### 시나리오 1: 페이지 로드 시

```
1. DOMContentLoaded 이벤트 발생
2. 쿠키 함수 정의
3. DOM 요소 선택
4. article 위치 저장
5. floatdiv 초기 위치 저장
6. 쿠키 확인하여 팝업 표시/숨김
7. 이벤트 리스너 등록
```

---

### 시나리오 2: 스크롤 시

```
1. 사용자가 스크롤
2. scroll 이벤트 발생
3. isScrolling 확인 (false면 진행)
4. 스크롤 위치 표시 업데이트
5. floatdiv 위치 조정 (애니메이션)
6. nav에 'on' 클래스 추가/제거
7. 현재 스크롤 위치에 맞는 네비게이션 활성화
```

---

### 시나리오 3: 네비게이션 클릭 시

```
1. 사용자가 네비게이션 메뉴 클릭
2. click 이벤트 발생
3. preventDefault()로 기본 동작 방지
4. 해당 article 위치 계산
5. isScrolling = true 설정
6. smoothScrollTo() 호출
7. 부드러운 스크롤 애니메이션 시작
8. 네비게이션 활성화
9. 1300ms 후 isScrolling = false
```

---

## jQuery vs 순수 JavaScript 비교

| 기능 | jQuery | 순수 JavaScript |
|------|--------|----------------|
| DOM 준비 | `$(function(){})` | `document.addEventListener('DOMContentLoaded', function(){})` |
| 요소 선택 | `$('.class')` | `document.querySelectorAll('.class')` |
| 스크롤 위치 | `$(window).scrollTop()` | `window.pageYOffset` |
| 요소 위치 | `$('.el').offset().top` | `element.getBoundingClientRect().top + window.pageYOffset` |
| 클래스 추가 | `.addClass('on')` | `.classList.add('on')` |
| 클래스 제거 | `.removeClass('on')` | `.classList.remove('on')` |
| 애니메이션 | `.animate({scrollTop: val}, 1300)` | `smoothScrollTo(val, 1300)` |
| 페이드아웃 | `.fadeOut('fast')` | `fadeOut(element, 200)` |
| 드래그 | `.draggable()` | `makeDraggable(element)` |
| 쿠키 | `$.cookie('name', 'value', {expires:1})` | `setCookie('name', 'value', 1)` |

---

## 개선 가능한 부분

### 1. 성능 최적화 (Throttle)

```javascript
function throttle(func, wait) {
    var timeout;
    return function() {
        var context = this, args = arguments;
        if (!timeout) {
            timeout = setTimeout(function() {
                timeout = null;
                func.apply(context, args);
            }, wait);
        }
    };
}

window.addEventListener('scroll', throttle(function(){
    // 스크롤 이벤트 처리
}, 16)); // 약 60fps
```

---

### 2. Intersection Observer API 사용

```javascript
var observer = new IntersectionObserver(function(entries) {
    entries.forEach(function(entry) {
        if (entry.isIntersecting) {
            // article이 보일 때 처리
        }
    });
}, { threshold: 0.5 });

articles.forEach(function(article) {
    observer.observe(article);
});
```

**장점:**
- 스크롤 이벤트보다 성능 좋음
- 브라우저가 최적화하여 실행

---

### 3. CSS Scroll Snap 사용

```css
section {
    scroll-snap-type: y mandatory;
}

article {
    scroll-snap-align: start;
}
```

**장점:**
- JavaScript 없이도 스냅 스크롤 구현 가능
- 네이티브 브라우저 기능 활용

---

### 4. 모듈화

```javascript
// scrollHandler.js
export function initScrollHandler() {
    // 스크롤 관련 기능
}

// popupHandler.js
export function initPopupHandler() {
    // 팝업 관련 기능
}
```

---

## 마무리

이 코드는 **순수 JavaScript로 구현한 스크롤 기반 네비게이션**의 완벽한 예제입니다. jQuery 없이도 모든 기능을 구현했으며, 성능과 유지보수성을 고려한 코드입니다.

**핵심 학습 포인트:**
1. ✅ `getBoundingClientRect()`로 요소 위치 계산
2. ✅ `requestAnimationFrame`으로 부드러운 애니메이션
3. ✅ Easing 함수로 자연스러운 움직임
4. ✅ 쿠키를 이용한 상태 관리
5. ✅ 드래그 기능 직접 구현
6. ✅ fadeIn/fadeOut 애니메이션 구현
7. ✅ 스크롤 이벤트 최적화

이 코드를 이해하면 다양한 스크롤 기반 인터랙션 구현이 가능합니다!
