# 아코디언 메뉴 (Width 방식) - 완전 정복

## 📋 목차
1. [개요](#개요)
2. [코드 분석](#코드-분석)
3. [forEach로 변환된 코드](#foreach로-변환된-코드)
4. [라인별 상세 설명](#라인별-상세-설명)
5. [실행 흐름](#실행-흐름)
6. [핵심 개념](#핵심-개념)

---

## 개요

이 코드는 **width 속성을 조절**하여 아코디언 메뉴를 구현한 예제입니다. `dt` 요소를 클릭하면 해당 `dd` 요소의 너비를 조절하여 열고 닫는 효과를 만듭니다.

**주요 특징:**
- `dd` 요소의 `width`를 `0px`와 `695px`로 조절
- 한 번에 하나의 메뉴만 열림 (다른 메뉴는 자동으로 닫힘)
- 선택된 `dt`의 `span`에 `selected` 클래스 추가
- 첫 번째 메뉴가 기본적으로 열려있음

---

## 코드 분석

### 원본 코드 (for 루프 사용)

```javascript
document.addEventListener('DOMContentLoaded', function(){
    var ddList = document.querySelectorAll('dd');
    var dtList = document.querySelectorAll('dl dt');
    var dtListSpan = document.querySelectorAll('dl dt span');
    
    // 1. 첫 번째를 제외한 모든 dd를 닫기
    for (var i = 1; i < ddList.length; i++) {
        ddList[i].style.width = '0px';
    }

    // 2. 첫 번째 dt의 span에 selected 클래스 추가
    dtListSpan[0].classList.add('selected');

    // 3. 각 dt에 클릭 이벤트 리스너 추가
    for(var i = 0; i < dtList.length; i++){
        dtList[i].addEventListener('click', function(){
            var nextDD = this.nextElementSibling;

            // 4. 클릭한 dd가 닫혀있으면 모든 dd 닫기
            if(nextDD.style.width == '0px' || nextDD.style.width === ''){
                for(var j = 0; j < ddList.length; j++){
                    ddList[j].style.width = '0px';
                }
            }
            
            // 5. 클릭한 dd 열기
            nextDD.style.width = "695px";

            // 6. 모든 span에서 selected 제거
            for(var k = 0; k < dtList.length; k++){
                dtListSpan[k].classList.remove('selected');
            }
            
            // 7. 클릭한 dt의 span에 selected 추가
            this.querySelector('span').classList.add('selected');
        });
    }
});
```

---

## forEach로 변환된 코드

```javascript
document.addEventListener('DOMContentLoaded', function(){
    var ddList = document.querySelectorAll('dd');
    var dtList = document.querySelectorAll('dl dt');
    var dtListSpan = document.querySelectorAll('dl dt span');
    
    // 1. 첫 번째를 제외한 모든 dd를 닫기
    ddList.forEach(function(dd, index) {
        if (index !== 0) {
            dd.style.width = '0px';
        }
    });

    // 2. 첫 번째 dt의 span에 selected 클래스 추가
    dtListSpan[0].classList.add('selected');

    // 3. 각 dt에 클릭 이벤트 리스너 추가
    dtList.forEach(function(dt) {
        dt.addEventListener('click', function(){
            var nextDD = this.nextElementSibling;

            // 4. 클릭한 dd가 닫혀있으면 모든 dd 닫기
            if(nextDD.style.width == '0px' || nextDD.style.width === ''){
                ddList.forEach(function(dd) {
                    dd.style.width = '0px';
                });
            }
            
            // 5. 클릭한 dd 열기
            nextDD.style.width = "695px";

            // 6. 모든 span에서 selected 제거
            dtListSpan.forEach(function(span) {
                span.classList.remove('selected');
            });
            
            // 7. 클릭한 dt의 span에 selected 추가
            this.querySelector('span').classList.add('selected');
        });
    });
});
```

### ES6 화살표 함수 버전 (더 간결)

```javascript
document.addEventListener('DOMContentLoaded', () => {
    const ddList = document.querySelectorAll('dd');
    const dtList = document.querySelectorAll('dl dt');
    const dtListSpan = document.querySelectorAll('dl dt span');
    
    // 1. 첫 번째를 제외한 모든 dd를 닫기
    ddList.forEach((dd, index) => {
        if (index !== 0) {
            dd.style.width = '0px';
        }
    });

    // 2. 첫 번째 dt의 span에 selected 클래스 추가
    dtListSpan[0].classList.add('selected');

    // 3. 각 dt에 클릭 이벤트 리스너 추가
    dtList.forEach(dt => {
        dt.addEventListener('click', function(){
            const nextDD = this.nextElementSibling;

            // 4. 클릭한 dd가 닫혀있으면 모든 dd 닫기
            if(nextDD.style.width == '0px' || nextDD.style.width === ''){
                ddList.forEach(dd => {
                    dd.style.width = '0px';
                });
            }
            
            // 5. 클릭한 dd 열기
            nextDD.style.width = "695px";

            // 6. 모든 span에서 selected 제거
            dtListSpan.forEach(span => {
                span.classList.remove('selected');
            });
            
            // 7. 클릭한 dt의 span에 selected 추가
            this.querySelector('span').classList.add('selected');
        });
    });
});
```

---

## 라인별 상세 설명

### 1. DOMContentLoaded 이벤트 리스너

```javascript
document.addEventListener('DOMContentLoaded', function(){
```

**설명:**
- `DOMContentLoaded`: HTML 문서가 완전히 로드되고 파싱된 후 실행
- DOM 요소를 선택하기 전에 문서가 준비되어야 함
- 스크립트가 `<head>`에 있어도 안전하게 실행 가능

---

### 2. DOM 요소 선택

```javascript
var ddList = document.querySelectorAll('dd');
var dtList = document.querySelectorAll('dl dt');
var dtListSpan = document.querySelectorAll('dl dt span');
```

**요소 설명:**

| 변수 | 선택자 | 설명 |
|------|--------|------|
| `ddList` | `'dd'` | 모든 `dd` 요소들 (아코디언 내용 영역) |
| `dtList` | `'dl dt'` | 모든 `dt` 요소들 (아코디언 제목) |
| `dtListSpan` | `'dl dt span'` | 모든 `dt` 내부의 `span` 요소들 |

**HTML 구조 예시:**
```html
<dl>
    <dt><span>Step.1</span></dt>
    <dd>내용...</dd>
    <dt><span>Step.2</span></dt>
    <dd>내용...</dd>
</dl>
```

---

### 3. 초기화: 첫 번째를 제외한 모든 dd 닫기

#### 원본 코드 (for 루프)
```javascript
for (var i = 1; i < ddList.length; i++) {
    ddList[i].style.width = '0px';
}
```

#### forEach 버전
```javascript
ddList.forEach(function(dd, index) {
    if (index !== 0) {
        dd.style.width = '0px';
    }
});
```

**설명:**
- `forEach()`: 배열/NodeList의 각 요소에 대해 함수 실행
- `dd`: 현재 처리 중인 `dd` 요소
- `index`: 현재 인덱스 (0부터 시작)
- `index !== 0`: 첫 번째(`index === 0`)를 제외한 모든 `dd`
- `width = '0px'`: 너비를 0으로 설정하여 숨김

**왜 첫 번째를 제외하나요?**
- 첫 번째 메뉴는 기본적으로 열려있어야 함
- 사용자가 페이지를 열었을 때 첫 번째 메뉴가 보이도록 함

---

### 4. 첫 번째 dt의 span에 selected 클래스 추가

```javascript
dtListSpan[0].classList.add('selected');
```

**설명:**
- `dtListSpan[0]`: 첫 번째 `span` 요소
- `classList.add('selected')`: `selected` 클래스 추가
- CSS에서 `.selected` 클래스로 선택된 메뉴의 스타일 적용

---

### 5. 각 dt에 클릭 이벤트 리스너 추가

#### 원본 코드 (for 루프)
```javascript
for(var i = 0; i < dtList.length; i++){
    dtList[i].addEventListener('click', function(){
        // ...
    });
}
```

#### forEach 버전
```javascript
dtList.forEach(function(dt) {
    dt.addEventListener('click', function(){
        // ...
    });
});
```

**설명:**
- `forEach()`: 각 `dt` 요소에 대해 반복
- `addEventListener('click', ...)`: 클릭 이벤트 리스너 추가
- `function()`: 이벤트 핸들러 함수 (일반 함수 사용 → `this`가 `dt` 요소를 가리킴)

**왜 일반 함수를 사용하나요?**
- 화살표 함수(`=>`)를 사용하면 `this`가 `dt` 요소를 가리키지 않음
- 일반 함수를 사용하면 `this`가 클릭한 `dt` 요소를 가리킴

---

### 6. 클릭한 dd가 닫혀있는지 확인

```javascript
var nextDD = this.nextElementSibling;

if(nextDD.style.width == '0px' || nextDD.style.width === ''){
    // 모든 dd 닫기
}
```

**설명:**
- `this.nextElementSibling`: 클릭한 `dt`의 다음 형제 요소 (`dd`)
- `nextElementSibling`: 같은 부모를 가진 다음 요소
- `nextDD.style.width == '0px'`: 인라인 스타일로 `width`가 `0px`인지 확인
- `nextDD.style.width === ''`: 인라인 스타일이 없거나 빈 문자열인지 확인

**조건문의 의미:**
- `dd`가 닫혀있으면 (`width === '0px'` 또는 `width === ''`)
- 모든 `dd`를 먼저 닫고
- 클릭한 `dd`만 열기

**왜 이렇게 하나요?**
- 한 번에 하나의 메뉴만 열리도록 하기 위함
- 다른 메뉴가 열려있으면 먼저 닫고 새 메뉴 열기

---

### 7. 모든 dd 닫기

#### 원본 코드 (for 루프)
```javascript
for(var j = 0; j < ddList.length; j++){
    ddList[j].style.width = '0px';
}
```

#### forEach 버전
```javascript
ddList.forEach(function(dd) {
    dd.style.width = '0px';
});
```

**설명:**
- 모든 `dd` 요소를 순회하며
- `width`를 `0px`로 설정하여 닫기

---

### 8. 클릭한 dd 열기

```javascript
nextDD.style.width = "695px";
```

**설명:**
- 클릭한 `dt`의 다음 `dd` 요소의 너비를 `695px`로 설정
- `695px`: 메뉴가 열렸을 때의 너비 (디자인에 따라 조절 가능)

---

### 9. 모든 span에서 selected 제거

#### 원본 코드 (for 루프)
```javascript
for(var k = 0; k < dtList.length; k++){
    dtListSpan[k].classList.remove('selected');
}
```

#### forEach 버전
```javascript
dtListSpan.forEach(function(span) {
    span.classList.remove('selected');
});
```

**설명:**
- 모든 `span` 요소를 순회하며
- `selected` 클래스를 제거하여 이전 선택 상태 해제

---

### 10. 클릭한 dt의 span에 selected 추가

```javascript
this.querySelector('span').classList.add('selected');
```

**설명:**
- `this`: 클릭한 `dt` 요소
- `querySelector('span')`: `dt` 내부의 `span` 요소 찾기
- `classList.add('selected')`: `selected` 클래스 추가
- CSS에서 `.selected` 클래스로 선택된 메뉴의 스타일 적용

---

## 실행 흐름

### 시나리오 1: 페이지 로드 시

```
1. DOMContentLoaded 이벤트 발생
2. ddList, dtList, dtListSpan 선택
3. 첫 번째를 제외한 모든 dd의 width를 0px로 설정
   - ddList[1].style.width = '0px'
   - ddList[2].style.width = '0px'
   - ...
4. 첫 번째 dt의 span에 selected 클래스 추가
5. 각 dt에 클릭 이벤트 리스너 추가
```

**결과:**
- 첫 번째 메뉴만 열려있음
- 첫 번째 메뉴의 `dt`에 `selected` 클래스가 적용됨

---

### 시나리오 2: 두 번째 메뉴 클릭 시

```
1. 사용자가 두 번째 dt 클릭
2. nextDD = 두 번째 dd 요소
3. nextDD.style.width 확인
   - '0px' 또는 ''이므로 조건 만족
4. 모든 dd의 width를 0px로 설정
   - 첫 번째 dd 닫힘
   - 두 번째 dd 닫힘 (곧바로 열릴 예정)
   - 세 번째 dd 닫힘
5. nextDD.style.width = "695px" 설정
   - 두 번째 dd 열림
6. 모든 span에서 selected 제거
7. 두 번째 dt의 span에 selected 추가
```

**결과:**
- 두 번째 메뉴만 열려있음
- 두 번째 메뉴의 `dt`에 `selected` 클래스가 적용됨

---

### 시나리오 3: 같은 메뉴를 다시 클릭 시

```
1. 사용자가 이미 열려있는 두 번째 dt 클릭
2. nextDD = 두 번째 dd 요소
3. nextDD.style.width 확인
   - '695px'이므로 조건 불만족
4. 모든 dd 닫기 단계 건너뜀
5. nextDD.style.width = "695px" 설정 (이미 695px)
6. 모든 span에서 selected 제거
7. 두 번째 dt의 span에 selected 추가
```

**결과:**
- 두 번째 메뉴가 계속 열려있음 (변화 없음)

**⚠️ 문제점:**
- 같은 메뉴를 다시 클릭해도 닫히지 않음
- 개선이 필요할 수 있음

---

## 핵심 개념

### 1. forEach() 메서드

```javascript
array.forEach(function(element, index, array) {
    // 각 요소에 대해 실행할 코드
});
```

**매개변수:**
- `element`: 현재 처리 중인 요소
- `index`: 현재 인덱스 (선택적)
- `array`: 원본 배열 (선택적)

**장점:**
- 코드가 더 읽기 쉬움
- 인덱스 관리 불필요
- 실수로 인덱스 범위를 벗어날 위험 없음

**for 루프 vs forEach:**
```javascript
// for 루프
for(var i = 0; i < array.length; i++){
    console.log(array[i]);
}

// forEach
array.forEach(function(item) {
    console.log(item);
});
```

---

### 2. nextElementSibling

```javascript
var nextDD = this.nextElementSibling;
```

**설명:**
- 같은 부모를 가진 다음 형제 요소를 반환
- `dt`의 다음 형제는 `dd` 요소

**HTML 구조:**
```html
<dl>
    <dt>제목</dt>  ← this
    <dd>내용</dd>  ← nextElementSibling
</dl>
```

---

### 3. style.width 조절

```javascript
dd.style.width = '0px';    // 닫기
dd.style.width = '695px'; // 열기
```

**설명:**
- 인라인 스타일로 직접 `width` 속성 설정
- CSS transition과 함께 사용하면 애니메이션 효과 가능

**주의사항:**
- `style.width`는 인라인 스타일만 확인
- CSS 파일에서 설정한 `width`는 확인하지 않음
- `window.getComputedStyle()`을 사용하면 CSS 파일의 스타일도 확인 가능

---

### 4. classList 메서드

```javascript
element.classList.add('selected');    // 클래스 추가
element.classList.remove('selected');  // 클래스 제거
element.classList.toggle('selected');  // 클래스 토글
```

**설명:**
- `classList`: 요소의 클래스 목록을 관리하는 객체
- `add()`: 클래스 추가
- `remove()`: 클래스 제거
- `toggle()`: 클래스가 있으면 제거, 없으면 추가

---

### 5. 클로저와 this 바인딩

```javascript
dtList.forEach(function(dt) {
    dt.addEventListener('click', function(){
        // this는 클릭한 dt 요소를 가리킴
        var nextDD = this.nextElementSibling;
    });
});
```

**설명:**
- 일반 함수(`function()`)를 사용하면 `this`가 이벤트 타겟을 가리킴
- 화살표 함수(`=>`)를 사용하면 `this`가 상위 스코프를 가리킴

**화살표 함수 사용 시 문제:**
```javascript
dtList.forEach(dt => {
    dt.addEventListener('click', () => {
        // this가 dt를 가리키지 않음!
        var nextDD = this.nextElementSibling; // ❌ 오류
    });
});
```

---

## 개선 가능한 부분

### 1. 같은 메뉴를 다시 클릭하면 닫히도록 개선

```javascript
dtList.forEach(function(dt) {
    dt.addEventListener('click', function(){
        const nextDD = this.nextElementSibling;
        const isOpen = nextDD.style.width === '695px';

        if (isOpen) {
            // 이미 열려있으면 닫기
            nextDD.style.width = '0px';
            this.querySelector('span').classList.remove('selected');
        } else {
            // 닫혀있으면 열기
            ddList.forEach(function(dd) {
                dd.style.width = '0px';
            });
            nextDD.style.width = "695px";
            
            dtListSpan.forEach(function(span) {
                span.classList.remove('selected');
            });
            this.querySelector('span').classList.add('selected');
        }
    });
});
```

### 2. CSS transition 추가로 애니메이션 효과

```css
dd {
    transition: width 0.3s ease;
    overflow: hidden;
}
```

### 3. window.getComputedStyle() 사용

```javascript
const computedStyle = window.getComputedStyle(nextDD);
if (computedStyle.width === '0px' || computedStyle.width === '695px') {
    // ...
}
```

---

## 마무리

이 코드는 **width 속성을 조절**하여 아코디언 메뉴를 구현한 간단한 예제입니다. `forEach()`를 사용하면 코드가 더 읽기 쉽고 안전해집니다.

**핵심 학습 포인트:**
1. ✅ `forEach()` 메서드의 활용
2. ✅ `nextElementSibling`으로 형제 요소 찾기
3. ✅ `style.width`로 인라인 스타일 조절
4. ✅ `classList`로 클래스 관리
5. ✅ 클로저와 `this` 바인딩 이해

이 코드를 이해하면 다양한 아코디언 메뉴 구현이 가능합니다!
