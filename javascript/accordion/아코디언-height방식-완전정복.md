# 아코디언 메뉴 (Height + CSS Transition 방식) - 완전 정복

## 📋 목차
1. [개요](#개요)
2. [전체 코드 구조](#전체-코드-구조)
3. [HTML 구조](#html-구조)
4. [CSS 구조 분석](#css-구조-분석)
5. [JavaScript 코드 분석](#javascript-코드-분석)
6. [실행 흐름](#실행-흐름)
7. [핵심 개념](#핵심-개념)
8. [개선 가능한 부분](#개선-가능한-부분)

---

## 개요

이 코드는 **CSS `height` 속성과 `transition`**을 활용하여 아코디언 메뉴를 구현한 예제입니다. JavaScript는 클래스만 추가/제거하고, 실제 애니메이션은 CSS가 담당합니다.

**주요 특징:**
- ✅ CSS `transition`으로 부드러운 애니메이션
- ✅ `height: 0`과 `height: 300px`로 열고 닫기
- ✅ `active` 클래스로 `dd` 제어
- ✅ `selected` 클래스로 `dt` 스타일 제어
- ✅ 같은 메뉴를 다시 클릭하면 닫힘
- ✅ CSS `:hover`로 호버 효과 (JavaScript 불필요)

---

## 전체 코드 구조

```html
<!DOCTYPE html>
<html>
<head>
    <style>
        /* CSS: 기본 스타일 및 transition 설정 */
    </style>
</head>
<body>
    <dl>
        <dt class="selected">Step.1</dt>
        <dd class="active">내용...</dd>
        <dt>Step.2</dt>
        <dd>내용...</dd>
        <dt>Step.3</dt>
        <dd>내용...</dd>
    </dl>
    <script>
        /* JavaScript: 클릭 이벤트 및 클래스 제어 */
    </script>
</body>
</html>
```

---

## HTML 구조

```html
<dl>
    <dt class="selected">Step.1</dt>
    <dd class="active">
        <p>내용...</p>
    </dd>
    <dt>Step.2</dt>
    <dd>
        <p>내용...</p>
    </dd>
    <dt>Step.3</dt>
    <dd>
        <p>내용...</p>
    </dd>
</dl>
```

**구조 설명:**
- `<dl>`: 정의 목록 (Definition List)
- `<dt>`: 정의 용어 (Definition Term) - 아코디언 제목
- `<dd>`: 정의 설명 (Definition Description) - 아코디언 내용
- 첫 번째 `dt`에 `selected` 클래스, 첫 번째 `dd`에 `active` 클래스가 기본 적용됨

---

## CSS 구조 분석

### 1. 기본 리셋 및 레이아웃

```css
*{margin: 0;padding: 0;border:0;}
body{background-color: #252422;}
dl{ width: 800px; margin: 50px auto 0; }
```

**설명:**
- `*`: 모든 요소의 기본 마진/패딩/보더 제거
- `body`: 배경색 설정
- `dl`: 너비 800px, 중앙 정렬

---

### 2. dt (제목) 스타일

```css
dt{
    line-height: 35px;
    font-size: large;
    text-indent: 3em;
    font-weight: bold;
    color:#fff;
    height: 35px;
    background: url(./images/a1.jpg) no-repeat 0 0;
    cursor: pointer;
}
```

**속성 설명:**

| 속성 | 값 | 설명 |
|------|-----|------|
| `line-height` | `35px` | 줄 높이 (텍스트 수직 중앙 정렬) |
| `font-size` | `large` | 큰 글자 크기 |
| `text-indent` | `3em` | 텍스트 들여쓰기 (3배) |
| `font-weight` | `bold` | 굵은 글씨 |
| `color` | `#fff` | 흰색 텍스트 |
| `height` | `35px` | 고정 높이 |
| `background` | `url(...)` | 배경 이미지 |
| `cursor` | `pointer` | 마우스 포인터 모양 (클릭 가능 표시) |

---

### 3. dt:hover (호버 효과)

```css
dt:hover{
    background: url(./images/a2.jpg) no-repeat 0 0;
}
```

**설명:**
- 마우스를 올렸을 때 배경 이미지 변경
- **JavaScript 없이 CSS만으로 처리** (효율적)
- 사용자에게 인터랙티브한 느낌 제공

---

### 4. dt.selected (선택된 제목)

```css
dt.selected{
    background: url(./images/a3.jpg) no-repeat 0 0;
}
```

**설명:**
- 선택된 `dt`에 적용되는 스타일
- JavaScript에서 `selected` 클래스를 추가/제거하여 제어
- 열려있는 메뉴의 제목을 시각적으로 구분

---

### 5. dd (내용) 기본 스타일

```css
dd {
    margin: 0;
    background-color: #d4d0c8;
    height: 0;         /* 기본 높이 0 */
    overflow: hidden;  /* 넘치는 내용 숨김 */
    transition: height 0.3s ease; /* 높이 변화에 트랜지션 적용 */
}
```

**핵심 속성:**

| 속성 | 값 | 설명 |
|------|-----|------|
| `height` | `0` | 기본 높이 0 (닫힘 상태) |
| `overflow` | `hidden` | 넘치는 내용 숨김 (높이가 0일 때 내용이 보이지 않음) |
| `transition` | `height 0.3s ease` | 높이 변화 시 0.3초 동안 부드럽게 애니메이션 |

**왜 `height: 0`과 `overflow: hidden`을 함께 사용하나요?**
- `height: 0`만으로는 내용이 여전히 보일 수 있음
- `overflow: hidden`으로 넘치는 내용을 완전히 숨김
- 두 속성을 함께 사용하여 완전히 닫힌 상태 구현

---

### 6. dd.active (열린 내용)

```css
dd.active {
    height: 300px;     /* 열렸을 때 높이 */
}
```

**설명:**
- `active` 클래스가 추가되면 높이가 `300px`로 변경
- CSS `transition`이 자동으로 부드러운 애니메이션 생성
- JavaScript는 클래스만 추가/제거하면 됨

**애니메이션 동작:**
```
닫힘 상태: height: 0
    ↓ (active 클래스 추가)
열림 상태: height: 300px
    ↓ (transition: 0.3s ease)
부드러운 애니메이션!
```

---

### 7. dd p (내용 텍스트)

```css
dd p{
    margin: 0;
    text-indent: 1em;
    padding: 20px;
}
```

**설명:**
- `dd` 내부의 `<p>` 태그 스타일
- 들여쓰기와 패딩으로 가독성 향상

---

## JavaScript 코드 분석

### 전체 코드

```javascript
document.addEventListener('DOMContentLoaded',()=>{
    const dts = document.querySelectorAll('dt');
    const dds = document.querySelectorAll('dd');

    dts.forEach((dt, index)=>{
        dt.addEventListener('click',()=>{
            const targetDd = dds[index];
            const isAlreadyOpen = targetDd.classList.contains('active');

            //1. 모든 dt와 dd의 활성화 클래스 제거 (초기화)
            dts.forEach(el => el.classList.remove('selected'));
            dds.forEach(el => el.classList.remove('active'));

            //2. 클릭한 요소 다음에 있는 dd가 닫혀있었다면 열기
            if(!isAlreadyOpen){
                dt.classList.add('selected');
                targetDd.classList.add('active');
            }
        });
    });
});
```

---

### 라인별 상세 설명

#### 1. DOMContentLoaded 이벤트 리스너

```javascript
document.addEventListener('DOMContentLoaded',()=>{
```

**설명:**
- DOM이 완전히 로드된 후 실행
- 화살표 함수(`=>`) 사용으로 간결한 코드

---

#### 2. DOM 요소 선택

```javascript
const dts = document.querySelectorAll('dt');
const dds = document.querySelectorAll('dd');
```

**설명:**
- `dts`: 모든 `dt` 요소들 (NodeList)
- `dds`: 모든 `dd` 요소들 (NodeList)
- `const`: 재할당 불가능한 상수 선언

---

#### 3. 각 dt에 클릭 이벤트 리스너 추가

```javascript
dts.forEach((dt, index)=>{
    dt.addEventListener('click',()=>{
        // ...
    });
});
```

**설명:**
- `forEach()`: 각 `dt` 요소에 대해 반복
- `dt`: 현재 처리 중인 `dt` 요소
- `index`: 현재 인덱스 (0부터 시작)
- `addEventListener('click', ...)`: 클릭 이벤트 리스너 추가

**인덱스의 역할:**
- `dt`와 `dd`는 1:1 대응 관계
- `dts[0]` ↔ `dds[0]`
- `dts[1]` ↔ `dds[1]`
- `dts[2]` ↔ `dds[2]`

---

#### 4. 클릭한 dt에 대응하는 dd 찾기

```javascript
const targetDd = dds[index];
```

**설명:**
- 클릭한 `dt`의 인덱스를 사용하여 대응하는 `dd` 찾기
- `index`: 클릭한 `dt`의 인덱스
- `dds[index]`: 해당 인덱스의 `dd` 요소

**예시:**
- 첫 번째 `dt` 클릭 → `index = 0` → `dds[0]` (첫 번째 `dd`)
- 두 번째 `dt` 클릭 → `index = 1` → `dds[1]` (두 번째 `dd`)

---

#### 5. 이미 열려있는지 확인

```javascript
const isAlreadyOpen = targetDd.classList.contains('active');
```

**설명:**
- `classList.contains('active')`: `active` 클래스가 있는지 확인
- `true`: 이미 열려있음
- `false`: 닫혀있음

**왜 확인하나요?**
- 같은 메뉴를 다시 클릭하면 닫히도록 하기 위함
- 열려있으면 클래스 추가하지 않음 (닫힘)

---

#### 6. 모든 dt와 dd의 활성화 클래스 제거

```javascript
dts.forEach(el => el.classList.remove('selected'));
dds.forEach(el => el.classList.remove('active'));
```

**설명:**
- 모든 `dt`에서 `selected` 클래스 제거
- 모든 `dd`에서 `active` 클래스 제거
- **초기화 단계**: 다른 메뉴가 열려있으면 먼저 닫기

**왜 모든 것을 닫나요?**
- 한 번에 하나의 메뉴만 열리도록 하기 위함
- 다른 메뉴가 열려있으면 먼저 닫고 새 메뉴 열기

---

#### 7. 닫혀있었다면 열기

```javascript
if(!isAlreadyOpen){
    dt.classList.add('selected');
    targetDd.classList.add('active');
}
```

**설명:**
- `!isAlreadyOpen`: 닫혀있으면 (`false`이면)
- `dt.classList.add('selected')`: 클릭한 `dt`에 `selected` 클래스 추가
- `targetDd.classList.add('active')`: 대응하는 `dd`에 `active` 클래스 추가

**동작:**
- 닫혀있으면 → 열기 (`selected`, `active` 클래스 추가)
- 열려있으면 → 아무것도 하지 않음 (이미 모든 클래스가 제거되어 닫힘)

---

## 실행 흐름

### 시나리오 1: 페이지 로드 시

```
1. HTML 로드
2. 첫 번째 dt에 'selected' 클래스 (기본)
3. 첫 번째 dd에 'active' 클래스 (기본)
4. CSS 적용:
   - dt.selected → 배경 이미지 변경
   - dd.active → height: 300px
5. 첫 번째 메뉴가 열려있음
```

**결과:**
- 첫 번째 메뉴만 열려있음
- 첫 번째 메뉴의 `dt`에 `selected` 클래스 적용

---

### 시나리오 2: 두 번째 메뉴 클릭 시

```
1. 사용자가 두 번째 dt 클릭
2. targetDd = dds[1] (두 번째 dd)
3. isAlreadyOpen = false (닫혀있음)
4. 모든 dt에서 'selected' 제거
   - 첫 번째 dt의 'selected' 제거
5. 모든 dd에서 'active' 제거
   - 첫 번째 dd의 'active' 제거
   - 첫 번째 dd 닫힘 (height: 0으로 애니메이션)
6. !isAlreadyOpen이 true이므로:
   - 두 번째 dt에 'selected' 추가
   - 두 번째 dd에 'active' 추가
   - 두 번째 dd 열림 (height: 300px로 애니메이션)
```

**결과:**
- 첫 번째 메뉴 닫힘
- 두 번째 메뉴 열림
- 부드러운 애니메이션 효과

---

### 시나리오 3: 같은 메뉴를 다시 클릭 시

```
1. 사용자가 이미 열려있는 두 번째 dt 클릭
2. targetDd = dds[1] (두 번째 dd)
3. isAlreadyOpen = true (이미 열려있음)
4. 모든 dt에서 'selected' 제거
   - 두 번째 dt의 'selected' 제거
5. 모든 dd에서 'active' 제거
   - 두 번째 dd의 'active' 제거
   - 두 번째 dd 닫힘 (height: 0으로 애니메이션)
6. !isAlreadyOpen이 false이므로:
   - 클래스 추가하지 않음
```

**결과:**
- 두 번째 메뉴가 닫힘
- 모든 메뉴가 닫힌 상태

---

## 핵심 개념

### 1. CSS Transition

```css
transition: height 0.3s ease;
```

**설명:**
- CSS 속성 변화를 부드럽게 애니메이션
- `height` 속성이 변경될 때 0.3초 동안 부드럽게 전환
- JavaScript는 클래스만 추가/제거하면 CSS가 애니메이션 처리

**장점:**
- JavaScript에서 복잡한 애니메이션 로직 불필요
- 브라우저가 최적화하여 부드러운 애니메이션 제공
- 코드가 간결하고 유지보수 용이

---

### 2. classList API

```javascript
element.classList.add('active');      // 클래스 추가
element.classList.remove('active');    // 클래스 제거
element.classList.contains('active');  // 클래스 확인
element.classList.toggle('active');    // 클래스 토글
```

**설명:**
- 요소의 클래스를 쉽게 관리
- `add()`: 클래스 추가
- `remove()`: 클래스 제거
- `contains()`: 클래스 존재 여부 확인 (boolean 반환)
- `toggle()`: 클래스가 있으면 제거, 없으면 추가

---

### 3. forEach와 화살표 함수

```javascript
dts.forEach((dt, index) => {
    // ...
});
```

**설명:**
- `forEach()`: 배열/NodeList의 각 요소에 대해 함수 실행
- 화살표 함수(`=>`): 간결한 함수 표현
- `(dt, index)`: 현재 요소와 인덱스를 매개변수로 받음

**for 루프와 비교:**
```javascript
// for 루프
for(let i = 0; i < dts.length; i++){
    const dt = dts[i];
    // ...
}

// forEach
dts.forEach((dt, index) => {
    // ...
});
```

---

### 4. 인덱스 기반 매칭

```javascript
const targetDd = dds[index];
```

**설명:**
- `dt`와 `dd`는 HTML에서 순서대로 배치됨
- 같은 인덱스의 `dt`와 `dd`가 1:1 대응
- `nextElementSibling` 대신 인덱스로 매칭

**장점:**
- 코드가 더 명확하고 이해하기 쉬움
- 인덱스로 직접 접근하여 빠름

**단점:**
- HTML 구조가 변경되면 인덱스도 변경되어야 함

---

### 5. 조건부 클래스 추가

```javascript
if(!isAlreadyOpen){
    dt.classList.add('selected');
    targetDd.classList.add('active');
}
```

**설명:**
- 이미 열려있으면 클래스를 추가하지 않음
- 같은 메뉴를 다시 클릭하면 닫히도록 함
- 사용자 경험 향상

---

## 개선 가능한 부분

### 1. 동적 높이 계산

현재는 `height: 300px`로 고정되어 있어 내용이 많으면 잘릴 수 있습니다.

**개선안:**
```javascript
// JavaScript에서 동적으로 높이 계산
if(!isAlreadyOpen){
    targetDd.style.height = 'auto';
    const height = targetDd.scrollHeight;
    targetDd.style.height = '0px';
    
    requestAnimationFrame(() => {
        targetDd.style.height = height + 'px';
    });
    
    dt.classList.add('selected');
    targetDd.classList.add('active');
}
```

### 2. CSS 변수 활용

```css
:root {
    --dd-open-height: 300px;
    --transition-duration: 0.3s;
}

dd.active {
    height: var(--dd-open-height);
}
```

### 3. 접근성 개선

```html
<dt role="button" aria-expanded="true" aria-controls="dd1">
    Step.1
</dt>
<dd id="dd1" role="region">
    내용...
</dd>
```

### 4. 키보드 네비게이션

```javascript
dt.addEventListener('keydown', (e) => {
    if(e.key === 'Enter' || e.key === ' ') {
        e.preventDefault();
        dt.click();
    }
});
```

---

## 마무리

이 코드는 **CSS Transition을 활용한 아코디언 메뉴**의 완벽한 예제입니다. JavaScript는 최소한의 클래스 제어만 하고, 실제 애니메이션은 CSS가 담당하여 효율적이고 유지보수하기 좋은 코드입니다.

**핵심 학습 포인트:**
1. ✅ CSS `transition`의 활용
2. ✅ `height: 0`과 `overflow: hidden`의 조합
3. ✅ `classList` API로 클래스 관리
4. ✅ `forEach()`와 화살표 함수
5. ✅ 인덱스 기반 요소 매칭
6. ✅ 조건부 클래스 추가로 토글 기능 구현

이 코드를 이해하면 다양한 아코디언 메뉴 구현이 가능합니다!
