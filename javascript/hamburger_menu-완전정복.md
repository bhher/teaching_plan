# hamburger_menu.html 햄버거 메뉴 완전 정복

## 목차
1. [개요](#개요)
2. [HTML 구조 분석](#html-구조-분석)
3. [CSS 스타일 상세 분석](#css-스타일-상세-분석)
4. [JavaScript 로직 완전 분석](#javascript-로직-완전-분석)
5. [핵심 개념 정리](#핵심-개념-정리)
6. [실행 흐름도](#실행-흐름도)
7. [jQuery vs JavaScript 비교](#jquery-vs-javascript-비교)
8. [코드 라인별 상세 설명](#코드-라인별-상세-설명)
9. [개선 가능한 부분](#개선-가능한-부분)

---

## 개요

`hamburger_menu.html`은 순수 JavaScript를 사용하여 구현한 햄버거 메뉴와 아코디언 서브메뉴를 결합한 반응형 네비게이션 메뉴입니다. jQuery 없이 순수 JavaScript로 구현되어 있습니다.

**주요 특징:**
- 순수 JavaScript (jQuery 없음)
- 햄버거 메뉴 클릭 시 사이드 메뉴 슬라이드 애니메이션
- 아코디언 방식 서브메뉴 (한 번에 하나만 열림)
- 동적 높이 계산 (`scrollHeight` 사용)
- 부드러운 CSS 트랜지션

**주요 기능:**
1. 햄버거 버튼 클릭 → 사이드 메뉴 열기/닫기
2. 메인 메뉴 클릭 → 서브메뉴 아코디언 방식 열기/닫기
3. 사이드 메뉴 닫을 때 모든 서브메뉴 자동 닫기

---

## HTML 구조 분석

```html
<header>
    <h1>더조은</h1>
    <p class="ham">메뉴바</p>
</header>
<nav>
    <ul>
        <li>
            <a href="#">회사소개</a>
            <ul class="sub">
                <li><a href="#">submenu1</a></li>
                <li><a href="#">submenu2</a></li>
                <li><a href="#">submenu3</a></li>
            </ul>
        </li>
        <!-- ... 더 많은 메뉴 항목들 ... -->
    </ul>
</nav>
```

### 구조 설명

1. **`<header>`**: 상단 헤더 영역
   - `h1`: 사이트 제목
   - `p.ham`: 햄버거 메뉴 버튼

2. **`<nav>`**: 사이드 네비게이션 메뉴
   - 초기 위치: `right: -200px` (화면 밖에 숨김)
   - 클릭 시 `right: 0px`로 이동하여 표시

3. **`<ul>`**: 메인 메뉴 리스트
   - 각 `li`는 메인 메뉴 항목

4. **`.sub`**: 서브메뉴 컨테이너
   - 초기 상태: `height: 0`, `opacity: 0` (숨김)
   - 클릭 시 JavaScript로 높이와 투명도 변경

---

## CSS 스타일 상세 분석

### 1. 기본 리셋 (8-11줄)

```css
* {
    margin: 0;
    padding: 0;
}
```

- 모든 요소의 기본 마진과 패딩 제거
- 브라우저 간 일관된 스타일링

### 2. 리스트 스타일 (13-15줄)

```css
ul {
    list-style: none;
}
```

- 리스트 불릿 포인트 제거

### 3. 링크 스타일 (17-20줄)

```css
a {
    text-decoration: none;
    color: #333;
}
```

- 링크 밑줄 제거 및 기본 색상 설정

### 4. 헤더 스타일 (25-35줄)

```css
header{
    width: 100%;
    height: 70px;
    position: relative;
    background-color: skyblue;
}
header h1{
    height: 70px;
    line-height: 70px;
    text-align: center;
}
```

- **`position: relative`**: 햄버거 버튼의 절대 위치 기준점
- **`line-height: 70px`**: 텍스트 수직 중앙 정렬

### 5. 햄버거 버튼 스타일 (36-50줄) ⭐ 핵심

```css
header p.ham{
    position: absolute;
    width: 50px;
    height: 50px;
    top: 10px;
    right: 10px;
    border: 1px solid #333;
    text-indent: -9999px;
    background: url(./images/ham.png) no-repeat 50% 50%;
    cursor: pointer;
}
header p.ham.on{
    background: url(./images/bar.png) no-repeat 50% 50%;
}
```

- **`position: absolute`**: 헤더 내 절대 위치
- **`text-indent: -9999px`**: 텍스트를 화면 밖으로 이동 (접근성을 위해 텍스트는 유지)
- **`background: url(...)`**: 햄버거 아이콘 이미지
- **`.ham.on`**: 클릭 시 아이콘 변경 (X 아이콘)

### 6. 사이드 메뉴 스타일 (51-59줄) ⭐ 핵심

```css
nav{
    width: 200px;
    background-color: lime;
    position: absolute;
    top: 70px;
    right: -200px;
    transition: all 0.3s;
}
```

- **`position: absolute`**: 절대 위치
- **`right: -200px`**: 초기 위치 (화면 밖에 숨김)
- **`transition: all 0.3s`**: 모든 속성 변경 시 0.3초 애니메이션
- 클릭 시 `right: 0px`로 변경되어 화면에 표시

### 7. 메인 메뉴 링크 스타일 (66-69줄)

```css
nav ul li > a{
    display: block;
    cursor: pointer;
}
```

- **`display: block`**: 전체 영역 클릭 가능
- **`cursor: pointer`**: 마우스 오버 시 포인터 커서

### 8. 서브메뉴 초기 상태 (72-77줄) ⭐ 핵심

```css
nav .sub {
    height: 0;
    overflow: hidden;
    transition: height 0.3s ease, opacity 0.3s ease;
    opacity: 0;
}
```

- **`height: 0`**: 초기 높이 0 (숨김)
- **`overflow: hidden`**: 내용이 넘치면 숨김
- **`transition`**: 높이와 투명도 변경 시 애니메이션
- **`opacity: 0`**: 초기 투명도 0

### 9. 서브메뉴 항목 스타일 (79-85줄)

```css
nav .sub li{
    background-color: #cf9;
}
nav .sub li:hover{
    background-color: #cf09;
}
```

- 서브메뉴 항목의 배경색과 호버 효과

---

## JavaScript 로직 완전 분석

### 전체 구조

```javascript
document.addEventListener('DOMContentLoaded', function(){
    // 1. 서브메뉴 초기화
    // 2. 슬라이드 함수 정의 (slideDown, slideUp)
    // 3. 메인 메뉴 클릭 이벤트 처리
    // 4. 햄버거 메뉴 클릭 이벤트 처리
});
```

### 단계별 분석

#### 1단계: DOM 로드 대기 (130-131줄)

```javascript
document.addEventListener('DOMContentLoaded', function(){
```

- DOM이 완전히 로드된 후에만 스크립트 실행
- HTML 요소들이 모두 준비된 상태에서 JavaScript 실행 보장

#### 2단계: 서브메뉴 초기화 (132-137줄)

```javascript
const subMenus = document.querySelectorAll('nav .sub');
subMenus.forEach(function(sub){
    sub.style.height = '0px';
    sub.style.opacity = '0';
});
```

- 모든 서브메뉴를 선택하여 초기 상태로 설정
- 높이와 투명도를 0으로 설정하여 숨김

#### 3단계: 슬라이드 다운 함수 (139-147줄) ⭐ 핵심

```javascript
function slideDown(element){
    element.style.height = '0px';
    element.style.opacity = '0';
    element.offsetHeight; // 리플로우 강제
    const height = element.scrollHeight;
    element.style.height = height + 'px';
    element.style.opacity = '1';
}
```

**동작 순서:**
1. 초기 상태 설정: `height: 0px`, `opacity: 0`
2. **리플로우 강제**: `offsetHeight` 읽기로 브라우저가 레이아웃 재계산
3. **실제 높이 계산**: `scrollHeight`로 서브메뉴의 실제 내용 높이 측정
4. 목표 상태 설정: 계산된 높이와 `opacity: 1`로 설정
5. CSS `transition`이 자동으로 애니메이션 처리

#### 4단계: 슬라이드 업 함수 (149-155줄) ⭐ 핵심

```javascript
function slideUp(element){
    element.style.height = element.scrollHeight + 'px';
    element.offsetHeight; // 리플로우 강제
    element.style.height = '0px';
    element.style.opacity = '0';
}
```

**동작 순서:**
1. 현재 높이를 명시적으로 설정 (`scrollHeight` 사용)
2. 리플로우 강제
3. 높이를 0으로 설정하여 닫기 애니메이션 시작

#### 5단계: 서브메뉴 열림 상태 확인 함수 (157-160줄)

```javascript
function isSubMenuOpen(subMenu){
    return subMenu.style.height !== '0px' && subMenu.style.height !== '';
}
```

- 서브메뉴가 열려있는지 확인
- `height`가 `'0px'`이 아니고 빈 문자열이 아니면 열려있음

#### 6단계: 메인 메뉴 클릭 이벤트 처리 (162-190줄) ⭐ 아코디언 효과

```javascript
const mainMenuLinks = document.querySelectorAll('nav > ul > li > a');
mainMenuLinks.forEach(function(link){
    link.addEventListener('click', function(e){
        e.preventDefault();
        
        const subMenu = this.nextElementSibling;
        
        if(subMenu && subMenu.classList.contains('sub')){
            if(!isSubMenuOpen(subMenu)){
                // 모든 서브메뉴 닫기
                subMenus.forEach(function(sub){
                    if(isSubMenuOpen(sub)){
                        slideUp(sub);
                    }
                });
                // 현재 서브메뉴 열기
                slideDown(subMenu);
            } else {
                // 모든 서브메뉴 닫기
                subMenus.forEach(function(sub){
                    if(isSubMenuOpen(sub)){
                        slideUp(sub);
                    }
                });
            }
        }
    });
});
```

**동작 원리:**
1. 모든 메인 메뉴 링크에 클릭 이벤트 리스너 추가
2. 클릭 시 기본 링크 동작 방지 (`preventDefault()`)
3. 다음 형제 요소(`nextElementSibling`)가 서브메뉴인지 확인
4. 서브메뉴가 닫혀있으면 → 모든 서브메뉴 닫고 현재 서브메뉴 열기
5. 서브메뉴가 열려있으면 → 모든 서브메뉴 닫기

#### 7단계: 햄버거 메뉴 클릭 이벤트 처리 (192-212줄) ⭐ 핵심

```javascript
const hamBtn = document.querySelector('.ham');
const nav = document.querySelector('nav');

hamBtn.addEventListener('click', function(){
    if(!this.classList.contains('on')){
        // 메뉴 열기
        nav.style.right = '0px';
        this.classList.add('on');
    } else {
        // 메뉴 닫기
        nav.style.right = '-200px';
        this.classList.remove('on');
        // 모든 서브메뉴 닫기
        subMenus.forEach(function(sub){
            if(isSubMenuOpen(sub)){
                slideUp(sub);
            }
        });
    }
});
```

**동작 원리:**
1. 햄버거 버튼과 네비게이션 요소 선택
2. 버튼 클릭 시:
   - **열기**: `nav.style.right = '0px'` (화면에 표시)
   - **닫기**: `nav.style.right = '-200px'` (화면 밖으로 숨김)
3. 버튼 클래스 토글 (`on` 클래스 추가/제거)
4. 메뉴 닫을 때 모든 서브메뉴도 함께 닫기

---

## 핵심 개념 정리

### 1. scrollHeight

```javascript
const height = element.scrollHeight;
```

- **정의**: 요소의 전체 스크롤 가능한 높이 (보이지 않는 부분 포함)
- **특징**: 
  - `overflow: hidden`이어도 실제 내용 높이를 반환
  - `height: 0`일 때도 실제 내용 높이를 알 수 있음
- **사용 목적**: 서브메뉴의 실제 높이를 동적으로 계산

### 2. offsetHeight (리플로우 강제)

```javascript
element.offsetHeight;
```

- **정의**: 요소의 보이는 높이 (패딩, 보더 포함)
- **여기서의 용도**: 리플로우(Reflow) 강제
- **리플로우 강제란?**
  - 브라우저가 DOM 변경사항을 즉시 반영하도록 강제
  - `offsetHeight`를 읽으면 브라우저가 레이아웃을 재계산
  - 이렇게 해야 `transition`이 제대로 작동

### 3. nextElementSibling

```javascript
const subMenu = this.nextElementSibling;
```

- **정의**: 현재 요소의 다음 형제 요소 반환
- **사용 목적**: 메인 메뉴 링크 다음에 있는 서브메뉴 찾기
- **주의**: 텍스트 노드는 제외하고 요소 노드만 반환

### 4. classList API

```javascript
this.classList.contains('on')  // 클래스 존재 확인
this.classList.add('on')       // 클래스 추가
this.classList.remove('on')    // 클래스 제거
```

- **장점**: 
  - 여러 클래스를 쉽게 관리
  - `className` 속성보다 안전하고 편리
- **jQuery 비교**:
  - `hasClass('on')` → `classList.contains('on')`
  - `addClass('on')` → `classList.add('on')`
  - `removeClass('on')` → `classList.remove('on')`

### 5. CSS Transition

```css
transition: all 0.3s;
transition: height 0.3s ease, opacity 0.3s ease;
```

- **동작**: CSS 속성 값이 변경될 때 자동으로 애니메이션
- **조건**: 
  - 시작 값과 끝 값이 모두 설정되어 있어야 함
  - 리플로우 강제로 브라우저가 시작 값을 인식해야 함

---

## 실행 흐름도

### 햄버거 메뉴 클릭 시 전체 흐름

```
1. 사용자가 햄버거 버튼 클릭
   ↓
2. 버튼에 'on' 클래스가 있는지 확인
   ├─ 없음? → 메뉴 열기
   │   ├─ nav.style.right = '0px' (화면에 표시)
   │   └─ 버튼에 'on' 클래스 추가 (아이콘 변경)
   │
   └─ 있음? → 메뉴 닫기
       ├─ nav.style.right = '-200px' (화면 밖으로 숨김)
       ├─ 버튼에서 'on' 클래스 제거 (아이콘 원복)
       └─ 모든 서브메뉴 닫기
```

### 메인 메뉴 클릭 시 전체 흐름

```
1. 사용자가 메인 메뉴 클릭
   ↓
2. preventDefault() - 기본 링크 동작 방지
   ↓
3. 다음 형제 요소가 서브메뉴인지 확인
   ↓
4. 서브메뉴 상태 확인
   ├─ 닫혀있음? → 열기
   │   ├─ 모든 서브메뉴 닫기
   │   └─ 현재 서브메뉴 열기 (slideDown)
   │       ├─ height: 0px, opacity: 0 설정
   │       ├─ 리플로우 강제
   │       ├─ scrollHeight로 실제 높이 계산
   │       └─ height: 실제높이, opacity: 1 설정 (애니메이션)
   │
   └─ 열려있음? → 닫기
       └─ 모든 서브메뉴 닫기 (slideUp)
           ├─ height: scrollHeight 설정
           ├─ 리플로우 강제
           └─ height: 0px, opacity: 0 설정 (애니메이션)
```

---

## jQuery vs JavaScript 비교

### 1. 요소 선택

**jQuery:**
```javascript
$('.ham')
$('nav')
$('nav .sub')
$('nav > ul > li > a')
```

**JavaScript:**
```javascript
document.querySelector('.ham')
document.querySelector('nav')
document.querySelectorAll('nav .sub')
document.querySelectorAll('nav > ul > li > a')
```

### 2. 이벤트 리스너

**jQuery:**
```javascript
$('.ham').click(function(){ ... })
```

**JavaScript:**
```javascript
hamBtn.addEventListener('click', function(){ ... })
```

### 3. 클래스 조작

**jQuery:**
```javascript
$(this).hasClass('on')
$(this).addClass('on')
$(this).removeClass('on')
```

**JavaScript:**
```javascript
this.classList.contains('on')
this.classList.add('on')
this.classList.remove('on')
```

### 4. 스타일 변경

**jQuery:**
```javascript
$('nav').css('right', '0px')
```

**JavaScript:**
```javascript
nav.style.right = '0px'
```

### 5. 슬라이드 애니메이션

**jQuery:**
```javascript
$('.sub').slideUp()
$('.sub').slideDown()
```

**JavaScript:**
```javascript
function slideUp(element){
    element.style.height = element.scrollHeight + 'px';
    element.offsetHeight;
    element.style.height = '0px';
    element.style.opacity = '0';
}

function slideDown(element){
    element.style.height = '0px';
    element.style.opacity = '0';
    element.offsetHeight;
    const height = element.scrollHeight;
    element.style.height = height + 'px';
    element.style.opacity = '1';
}
```

### 6. 형제 요소 찾기

**jQuery:**
```javascript
$(this).next('.sub')
```

**JavaScript:**
```javascript
this.nextElementSibling
// 또는
this.parentElement.querySelector('.sub')
```

---

## 코드 라인별 상세 설명

### 130-131줄: DOM 로드 대기

```javascript
document.addEventListener('DOMContentLoaded', function(){
```

- DOM이 완전히 로드된 후 실행 보장

### 132-137줄: 서브메뉴 초기화

```javascript
const subMenus = document.querySelectorAll('nav .sub');
subMenus.forEach(function(sub){
    sub.style.height = '0px';
    sub.style.opacity = '0';
});
```

- 모든 서브메뉴를 초기 상태로 설정

### 139-147줄: slideDown 함수

```javascript
function slideDown(element){
    element.style.height = '0px';
    element.style.opacity = '0';
    element.offsetHeight; // 리플로우 강제
    const height = element.scrollHeight;
    element.style.height = height + 'px';
    element.style.opacity = '1';
}
```

- 서브메뉴를 열기 위한 애니메이션 함수

### 149-155줄: slideUp 함수

```javascript
function slideUp(element){
    element.style.height = element.scrollHeight + 'px';
    element.offsetHeight; // 리플로우 강제
    element.style.height = '0px';
    element.style.opacity = '0';
}
```

- 서브메뉴를 닫기 위한 애니메이션 함수

### 157-160줄: isSubMenuOpen 함수

```javascript
function isSubMenuOpen(subMenu){
    return subMenu.style.height !== '0px' && subMenu.style.height !== '';
}
```

- 서브메뉴 열림 상태 확인

### 162-190줄: 메인 메뉴 클릭 이벤트

```javascript
const mainMenuLinks = document.querySelectorAll('nav > ul > li > a');
mainMenuLinks.forEach(function(link){
    link.addEventListener('click', function(e){
        e.preventDefault();
        const subMenu = this.nextElementSibling;
        // ... 아코디언 로직
    });
});
```

- 메인 메뉴 클릭 시 서브메뉴 토글

### 192-212줄: 햄버거 메뉴 클릭 이벤트

```javascript
const hamBtn = document.querySelector('.ham');
const nav = document.querySelector('nav');

hamBtn.addEventListener('click', function(){
    if(!this.classList.contains('on')){
        nav.style.right = '0px';
        this.classList.add('on');
    } else {
        nav.style.right = '-200px';
        this.classList.remove('on');
        // 모든 서브메뉴 닫기
    }
});
```

- 햄버거 버튼 클릭 시 사이드 메뉴 열기/닫기

---

## 개선 가능한 부분

### 1. 접근성 개선

```javascript
// aria-expanded 속성 추가
hamBtn.setAttribute('aria-expanded', 'false');
hamBtn.setAttribute('aria-label', '메뉴 열기');

// 토글 시 업데이트
hamBtn.setAttribute('aria-expanded', isOpen ? 'true' : 'false');
hamBtn.setAttribute('aria-label', isOpen ? '메뉴 닫기' : '메뉴 열기');
```

### 2. 키보드 네비게이션

```javascript
// Enter 또는 Space 키로 메뉴 토글
hamBtn.addEventListener('keydown', function(e){
    if(e.key === 'Enter' || e.key === ' '){
        e.preventDefault();
        this.click();
    }
});
```

### 3. ESC 키로 메뉴 닫기

```javascript
document.addEventListener('keydown', function(e){
    if(e.key === 'Escape' && nav.style.right === '0px'){
        nav.style.right = '-200px';
        hamBtn.classList.remove('on');
        // 모든 서브메뉴 닫기
    }
});
```

### 4. 외부 클릭 시 메뉴 닫기

```javascript
document.addEventListener('click', function(e){
    if(!nav.contains(e.target) && !hamBtn.contains(e.target)){
        if(nav.style.right === '0px'){
            nav.style.right = '-200px';
            hamBtn.classList.remove('on');
            // 모든 서브메뉴 닫기
        }
    }
});
```

### 5. 애니메이션 완료 후 처리

```javascript
nav.addEventListener('transitionend', function(){
    if(nav.style.right === '-200px'){
        nav.style.display = 'none'; // 완전히 숨김
    } else {
        nav.style.display = 'block';
    }
});
```

### 6. 모바일 최적화

```css
@media (max-width: 768px) {
    nav {
        width: 100%;
        right: -100%;
    }
}
```

### 7. 성능 최적화

```javascript
// requestAnimationFrame 사용
function slideDown(element){
    element.style.height = '0px';
    element.style.opacity = '0';
    element.offsetHeight;
    requestAnimationFrame(function(){
        const height = element.scrollHeight;
        element.style.height = height + 'px';
        element.style.opacity = '1';
    });
}
```

### 8. 코드 리팩토링

```javascript
// 함수로 분리하여 재사용성 향상
function toggleSideMenu(){
    const isOpen = hamBtn.classList.contains('on');
    if(!isOpen){
        openSideMenu();
    } else {
        closeSideMenu();
    }
}

function openSideMenu(){
    nav.style.right = '0px';
    hamBtn.classList.add('on');
}

function closeSideMenu(){
    nav.style.right = '-200px';
    hamBtn.classList.remove('on');
    closeAllSubMenus();
}
```

---

## 결론

`hamburger_menu.html`은 **햄버거 메뉴**와 **아코디언 서브메뉴**를 결합한 반응형 네비게이션 메뉴입니다. 순수 JavaScript로 구현되어 jQuery 없이도 동작하며, 동적 높이 계산과 리플로우 강제를 활용하여 자연스러운 애니메이션을 제공합니다.

**핵심 포인트:**
1. `scrollHeight`로 실제 높이 계산
2. `offsetHeight`로 리플로우 강제
3. CSS `transition`으로 부드러운 애니메이션
4. 아코디언 방식으로 한 번에 하나의 서브메뉴만 열림
5. 사이드 메뉴 슬라이드 애니메이션

이 방식을 이해하면 다양한 반응형 네비게이션 메뉴를 구현할 수 있습니다!
