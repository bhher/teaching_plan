# 이미지 슬라이더 main_vanilla.js - 완전 정복

## 📋 목차
1. [개요](#개요)
2. [전체 코드 구조](#전체-코드-구조)
3. [코드 라인별 상세 분석](#코드-라인별-상세-분석)
4. [핵심 개념](#핵심-개념)
5. [실행 흐름 시나리오](#실행-흐름-시나리오)
6. [CSS와의 연동](#css와의-연동)
7. [핵심 기술 포인트](#핵심-기술-포인트)
8. [개선 가능한 부분](#개선-가능한-부분)

---

## 개요

이 코드는 **순수 JavaScript**로 작성된 무한 루프 이미지 슬라이더입니다. jQuery 없이 네이티브 JavaScript만으로 구현되었으며, `transitionend` 이벤트를 활용하여 자연스러운 무한 루프를 구현합니다.

**주요 특징:**
- ✅ 순수 JavaScript (jQuery 없음)
- ✅ 무한 루프 슬라이드 (끝없이 순환)
- ✅ 자동 재생 기능 (3초 간격)
- ✅ 이전/다음 버튼 제어
- ✅ 페이저(점) 클릭으로 직접 이동
- ✅ 마우스 오버 시 자동 재생 일시 정지
- ✅ 화살표 함수와 ES6 문법 활용

---

## 전체 코드 구조

```javascript
document.addEventListener('DOMContentLoaded', () => {
    // 1. DOM 요소 선택
    // 2. 변수 초기화
    // 3. 복제본 생성 및 추가
    // 4. 초기 위치 설정
    // 5. 슬라이드 이동 함수 정의
    // 6. 무한 루프 로직 (transitionend)
    // 7. 이벤트 바인딩
    // 8. 자동 재생 기능
});
```

---

## 코드 라인별 상세 분석

### 1. DOMContentLoaded 이벤트 리스너

```javascript
document.addEventListener('DOMContentLoaded', () => {
```

**설명:**
- `DOMContentLoaded`: HTML 문서가 완전히 로드되고 파싱된 후 실행
- DOM이 준비되기 전에 JavaScript가 실행되는 것을 방지
- 화살표 함수(`=>`) 사용으로 간결한 코드

**왜 필요한가?**
- 스크립트가 `<head>`에 있거나 DOM 요소보다 먼저 실행될 수 있음
- DOM 요소를 선택하기 전에 문서가 준비되어야 함

---

### 2. DOM 요소 선택

```javascript
const imgs = document.querySelector('.imgs');
const items = document.querySelectorAll('.imgs li');
const pager = document.querySelectorAll('.pager li');
const prevBtn = document.querySelector('.prev');
const nextBtn = document.querySelector('.next');
const wrap = document.getElementById('wrap');
```

**요소 설명:**

| 변수 | 선택자 | 설명 |
|------|--------|------|
| `imgs` | `.imgs` | 슬라이드 컨테이너 (`<ul>` 요소) |
| `items` | `.imgs li` | 모든 슬라이드 아이템 (복제 전, 5개) |
| `pager` | `.pager li` | 페이저(점) 요소들 (5개) |
| `prevBtn` | `.prev` | 이전 버튼 (`<p>` 요소) |
| `nextBtn` | `.next` | 다음 버튼 (`<p>` 요소) |
| `wrap` | `#wrap` | 전체 래퍼 요소 |

**메서드 차이:**
- `querySelector()`: 첫 번째 요소만 반환 (단수)
- `querySelectorAll()`: 모든 요소를 NodeList로 반환 (복수)
- `getElementById()`: ID로 요소 선택 (단수)

---

### 3. 변수 초기화

```javascript
const count = items.length; // 실제 이미지 개수 (5개)
let i = 1; // 현재 인덱스 (1번부터 시작)
let timer; // 자동 재생 타이머
```

**변수 설명:**

- **`count`**: 실제 슬라이드 개수 (5개)
  - 복제본을 제외한 원본 슬라이드 개수
  - `const`로 선언하여 변경 불가

- **`i`**: 현재 슬라이드 인덱스
  - `1`부터 시작 (실제 1번 슬라이드)
  - `let`으로 선언하여 변경 가능

- **`timer`**: 자동 재생을 위한 `setInterval` ID 저장
  - `clearInterval(timer)`로 타이머 중지 시 사용

---

### 4. 복제본 생성 및 추가 (무한 루프 핵심)

```javascript
// 1. 무한 루프를 위한 앞뒤 복제본 생성 및 추가
const firstClone = items[0].cloneNode(true);
const lastClone = items[count - 1].cloneNode(true);

imgs.appendChild(firstClone); // 마지막 뒤에 1번 복제본 추가
imgs.insertBefore(lastClone, items[0]); // 1번 앞에 마지막 복제본 추가
```

**단계별 설명:**

#### 4-1. 복제본 생성

```javascript
const firstClone = items[0].cloneNode(true);
const lastClone = items[count - 1].cloneNode(true);
```

- `items[0]`: 첫 번째 슬라이드 (1번)
- `items[count - 1]`: 마지막 슬라이드 (5번, `items[4]`)
- `cloneNode(true)`: 깊은 복사 (자식 요소까지 모두 복사)
  - `true`: 깊은 복사 (이미지 포함)
  - `false`: 얕은 복사 (요소만 복사)

#### 4-2. 복제본 추가

```javascript
imgs.appendChild(firstClone); // 맨 뒤에 추가
imgs.insertBefore(lastClone, items[0]); // 맨 앞에 추가
```

**최종 구조:**
```
원본:        [1, 2, 3, 4, 5]
복제 후:     [5_clone, 1, 2, 3, 4, 5, 1_clone]
인덱스:      0         1  2  3  4  5  6
```

**시각적 표현:**
```
┌─────────┬────┬────┬────┬────┬────┬─────────┐
│ 5_clone │ 1  │ 2  │ 3  │ 4  │ 5  │ 1_clone │
└─────────┴────┴────┴────┴────┴────┴─────────┘
    0       1   2   3   4   5     6
```

**왜 복제본이 필요한가?**
- 마지막 슬라이드에서 다음 버튼 클릭 시 첫 번째로 자연스럽게 이동
- 첫 번째 슬라이드에서 이전 버튼 클릭 시 마지막으로 자연스럽게 이동
- 복제본을 통해 "끝없는 루프" 효과 구현

---

### 5. 초기 위치 설정

```javascript
// 2. 초기 위치 설정 (실제 1번 이미지가 보이도록)
imgs.style.marginLeft = '-100%';
```

**설명:**
- `margin-left: -100%`는 한 슬라이드 너비만큼 왼쪽으로 이동
- 복제본(인덱스 0)을 건너뛰고 실제 1번 슬라이드(인덱스 1)가 보이도록 설정

**CSS와의 연동:**
- `.slide .imgs li`의 너비가 `14.2857%` (100% / 7)
- `-100%`는 정확히 한 슬라이드 너비만큼 이동

**시각적 표현:**
```
초기 상태 (margin-left: -100%):
[5_clone, 1, 2, 3, 4, 5, 1_clone]
         ↑
    보이는 슬라이드 (1번)
```

---

### 6. 슬라이드 이동 함수

```javascript
// 3. 슬라이드 이동 핵심 함수
function move(index, speed = 0.6) {
    // 애니메이션 적용
    imgs.style.transition = speed > 0 ? `margin-left ${speed}s ease` : 'none';
    imgs.style.marginLeft = `${-index * 100}%`;
    
    // 페이저(점) 상태 업데이트 (나머지 연산자를 이용해 복제본 구간에서도 정확한 인덱스 표시)
    let pagerIdx = (index - 1 + count) % count;
    pager.forEach((p, idx) => {
        p.classList.toggle('on', idx === pagerIdx);
    });
    
    i = index;
}
```

**매개변수:**
- `index`: 이동할 슬라이드 인덱스 (0~6)
- `speed`: 애니메이션 속도 (기본값 0.6초)
  - `speed > 0`: 애니메이션 있음
  - `speed = 0`: 애니메이션 없음 (즉시 이동)

**단계별 분석:**

#### 6-1. Transition 설정

```javascript
imgs.style.transition = speed > 0 ? `margin-left ${speed}s ease` : 'none';
```

- **조건부 연산자 (`? :`)**: 삼항 연산자
  - `speed > 0`: 애니메이션 있음 → `margin-left 0.6s ease`
  - `speed = 0`: 애니메이션 없음 → `none`

- **템플릿 리터럴 (`\`\``)**: 문자열 보간
  - `${speed}`: 변수 값을 문자열에 삽입

#### 6-2. 위치 이동

```javascript
imgs.style.marginLeft = `${-index * 100}%`;
```

**계산 예시:**
- 인덱스 0: `-0 * 100% = 0%` (5_clone)
- 인덱스 1: `-1 * 100% = -100%` (실제 1번)
- 인덱스 2: `-2 * 100% = -200%` (실제 2번)
- 인덱스 5: `-5 * 100% = -500%` (실제 5번)
- 인덱스 6: `-6 * 100% = -600%` (1_clone)

#### 6-3. 페이저 업데이트

```javascript
let pagerIdx = (index - 1 + count) % count;
pager.forEach((p, idx) => {
    p.classList.toggle('on', idx === pagerIdx);
});
```

**페이저 인덱스 계산 공식:**
```javascript
pagerIdx = (index - 1 + count) % count
```

**예시:**
- 인덱스 0 (5_clone): `(0 - 1 + 5) % 5 = 4` → 페이저 4번 활성화
- 인덱스 1 (실제 1번): `(1 - 1 + 5) % 5 = 0` → 페이저 0번 활성화
- 인덱스 5 (실제 5번): `(5 - 1 + 5) % 5 = 4` → 페이저 4번 활성화
- 인덱스 6 (1_clone): `(6 - 1 + 5) % 5 = 0` → 페이저 0번 활성화

**나머지 연산자(`%`)의 역할:**
- 복제본(인덱스 0, 6)과 실제 슬라이드(인덱스 1~5)를 동일한 페이저로 매핑
- 페이저는 0~4까지만 있으므로 나머지 연산으로 변환

**`classList.toggle()` 메서드:**
```javascript
p.classList.toggle('on', idx === pagerIdx);
```
- 두 번째 매개변수가 `true`: 클래스 추가
- 두 번째 매개변수가 `false`: 클래스 제거
- `idx === pagerIdx`: 현재 페이저가 활성화할 페이저인지 확인

#### 6-4. 현재 인덱스 업데이트

```javascript
i = index;
```

- 전역 변수 `i`를 업데이트하여 현재 위치 추적
- 다음 이동 시 `i + 1` 또는 `i - 1`로 계산

---

### 7. 무한 루프 로직 (transitionend 이벤트)

```javascript
// 4. 무한 루프 '순간 점프' 로직 (가장 중요)
imgs.addEventListener('transitionend', () => {
    // 마지막 복제본(0번)에 도달하면 실제 마지막(5번) 위치로 순간 이동
    if (i === 0) {
        move(count, 0); 
    } 
    // 첫 번째 복제본(6번)에 도달하면 실제 첫 번째(1번) 위치로 순간 이동
    else if (i === count + 1) {
        move(1, 0);
    }
});
```

**핵심 개념:**

#### 7-1. transitionend 이벤트

- CSS `transition`이 완료되면 자동으로 발생
- 애니메이션이 끝난 후에만 실행되므로 타이밍이 정확함
- `setTimeout`보다 더 정확한 타이밍 보장

#### 7-2. 인덱스 0 처리 (마지막 복제본)

```javascript
if (i === 0) {
    move(count, 0);  // count = 5
}
```

**시나리오:**
1. 현재 인덱스 1 (실제 1번 슬라이드)
2. 이전 버튼 클릭 → `move(0)` 호출
3. 애니메이션으로 인덱스 0 (5_clone)으로 이동
4. 애니메이션 완료 → `transitionend` 발생
5. `i === 0` 조건 만족
6. `move(5, 0)` 호출 → 실제 5번으로 즉시 점프 (애니메이션 없음)

**시각적 흐름:**
```
1번 → 이전 버튼 클릭
[5_clone, 1, 2, 3, 4, 5, 1_clone]
↑
애니메이션으로 이동
↓
transitionend 발생
↓
[5_clone, 1, 2, 3, 4, 5, 1_clone]
                            ↑
                    즉시 점프 (애니메이션 없음)
```

#### 7-3. 인덱스 6 처리 (첫 번째 복제본)

```javascript
else if (i === count + 1) {  // count + 1 = 6
    move(1, 0);
}
```

**시나리오:**
1. 현재 인덱스 5 (실제 5번 슬라이드)
2. 다음 버튼 클릭 → `move(6)` 호출
3. 애니메이션으로 인덱스 6 (1_clone)으로 이동
4. 애니메이션 완료 → `transitionend` 발생
5. `i === 6` 조건 만족
6. `move(1, 0)` 호출 → 실제 1번으로 즉시 점프 (애니메이션 없음)

**시각적 흐름:**
```
5번 → 다음 버튼 클릭
[5_clone, 1, 2, 3, 4, 5, 1_clone]
                            ↑
                    애니메이션으로 이동
                            ↓
                    transitionend 발생
                            ↓
[5_clone, 1, 2, 3, 4, 5, 1_clone]
         ↑
    즉시 점프 (애니메이션 없음)
```

**왜 사용자가 점프를 느끼지 못하는가?**
- 복제본과 실제 슬라이드가 동일한 이미지
- 점프가 매우 빠르게 일어남 (애니메이션 없음)
- 사용자는 자연스러운 무한 루프로 인식

---

### 8. 이벤트 바인딩

```javascript
// 5. 버튼 및 페이저 클릭 이벤트 바인딩
nextBtn.onclick = () => move(i + 1);
prevBtn.onclick = () => move(i - 1);

pager.forEach((p, idx) => {
    p.onclick = () => move(idx + 1);
});
```

**설명:**

#### 8-1. 다음 버튼

```javascript
nextBtn.onclick = () => move(i + 1);
```

- 화살표 함수로 간결한 코드
- 현재 인덱스 `i`에 1을 더하여 다음 슬라이드로 이동
- 클로저를 통해 `i` 변수에 접근

#### 8-2. 이전 버튼

```javascript
prevBtn.onclick = () => move(i - 1);
```

- 현재 인덱스 `i`에서 1을 빼서 이전 슬라이드로 이동

#### 8-3. 페이저 클릭

```javascript
pager.forEach((p, idx) => {
    p.onclick = () => move(idx + 1);
});
```

- `forEach()`: 각 페이저에 대해 반복
- `idx`: 페이저 인덱스 (0~4)
- `idx + 1`: 실제 슬라이드 인덱스로 변환 (1~5)

**이벤트 핸들러 방식:**
- `onclick` 속성 사용 (간단한 경우)
- `addEventListener()` 대신 사용 가능 (단일 핸들러만 필요)

---

### 9. 자동 재생 기능

```javascript
// 6. 자동 재생 기능
const startTimer = () => {
    timer = setInterval(() => move(i + 1), 3000);
};

const stopTimer = () => {
    clearInterval(timer);
};

// 마우스 오버 시 일시 정지
wrap.onmouseenter = stopTimer;
wrap.onmouseleave = startTimer;

// 실행 시작
startTimer();
```

**단계별 설명:**

#### 9-1. 타이머 시작 함수

```javascript
const startTimer = () => {
    timer = setInterval(() => move(i + 1), 3000);
};
```

- `setInterval()`: 지정된 시간마다 함수 실행
- `3000`: 3초 (3000밀리초)
- `() => move(i + 1)`: 화살표 함수로 다음 슬라이드로 이동
- `timer`: 타이머 ID 저장 (나중에 중지 시 사용)

#### 9-2. 타이머 중지 함수

```javascript
const stopTimer = () => {
    clearInterval(timer);
};
```

- `clearInterval()`: `setInterval`로 생성한 타이머 중지
- `timer`: 중지할 타이머 ID

#### 9-3. 마우스 이벤트

```javascript
wrap.onmouseenter = stopTimer;
wrap.onmouseleave = startTimer;
```

- `mouseenter`: 마우스가 요소 위에 올라갈 때
- `mouseleave`: 마우스가 요소에서 벗어날 때
- 사용자가 슬라이더를 보고 있을 때 자동 재생 중지
- 사용자가 슬라이더에서 벗어나면 자동 재생 재개

#### 9-4. 초기 실행

```javascript
startTimer();
```

- 페이지 로드 시 자동 재생 시작

---

## 핵심 개념

### 1. 무한 루프의 원리

**복제본 전략:**
```
원본: [1, 2, 3, 4, 5]
복제: [5_clone, 1, 2, 3, 4, 5, 1_clone]
```

- 마지막을 앞에 복제 → 첫 번째에서 이전 버튼 클릭 시 자연스럽게 마지막으로 이동
- 첫 번째를 뒤에 복제 → 마지막에서 다음 버튼 클릭 시 자연스럽게 첫 번째로 이동

### 2. transitionend 이벤트의 활용

- CSS transition 완료 시 자동 발생
- `setTimeout`보다 정확한 타이밍
- 애니메이션 완료 후에만 실행되므로 안전함

### 3. 나머지 연산자(%)를 이용한 인덱스 매핑

```javascript
pagerIdx = (index - 1 + count) % count
```

- 복제본(인덱스 0, 6)과 실제 슬라이드(인덱스 1~5)를 동일한 페이저로 매핑
- 페이저는 0~4까지만 있으므로 나머지 연산으로 변환

### 4. 화살표 함수와 클로저

```javascript
nextBtn.onclick = () => move(i + 1);
```

- 화살표 함수로 간결한 코드
- 클로저를 통해 외부 변수 `i`에 접근

---

## 실행 흐름 시나리오

### 시나리오 1: 다음 버튼 클릭 (1번 → 2번)

```
1. 사용자가 다음 버튼 클릭
2. move(2) 호출 (i + 1 = 2)
3. transition 설정: 'margin-left 0.6s ease'
4. margin-left: -200% 설정
5. 애니메이션 시작 (1번 → 2번)
6. 페이저 업데이트 (0번 → 1번)
7. i = 2 업데이트
8. 애니메이션 완료 (transitionend 발생)
9. i === 2이므로 조건 불만족, 점프하지 않음
```

### 시나리오 2: 마지막에서 다음 버튼 클릭 (5번 → 1번)

```
1. 현재 i = 5 (마지막 슬라이드)
2. 사용자가 다음 버튼 클릭
3. move(6) 호출 (i + 1 = 6)
4. transition 설정: 'margin-left 0.6s ease'
5. margin-left: -600% 설정 (1_clone 위치)
6. 애니메이션 시작 (5번 → 1_clone)
7. 페이저 업데이트 (4번 → 0번, 1_clone이므로)
8. i = 6 업데이트
9. 애니메이션 완료 (transitionend 발생)
10. i === 6 (count + 1)이므로 조건 만족
11. move(1, 0) 호출 (실제 1번으로 점프)
12. transition = 'none' 설정 (애니메이션 없음)
13. margin-left: -100% 즉시 설정
14. 페이저 업데이트 (0번 유지)
15. i = 1 업데이트
```

### 시나리오 3: 첫 번째에서 이전 버튼 클릭 (1번 → 5번)

```
1. 현재 i = 1 (첫 번째 슬라이드)
2. 사용자가 이전 버튼 클릭
3. move(0) 호출 (i - 1 = 0)
4. transition 설정: 'margin-left 0.6s ease'
5. margin-left: 0% 설정 (5_clone 위치)
6. 애니메이션 시작 (1번 → 5_clone)
7. 페이저 업데이트 (0번 → 4번, 5_clone이므로)
8. i = 0 업데이트
9. 애니메이션 완료 (transitionend 발생)
10. i === 0이므로 조건 만족
11. move(5, 0) 호출 (실제 5번으로 점프)
12. transition = 'none' 설정 (애니메이션 없음)
13. margin-left: -500% 즉시 설정
14. 페이저 업데이트 (4번 유지)
15. i = 5 업데이트
```

### 시나리오 4: 자동 재생

```
1. startTimer() 호출
2. setInterval 시작 (3초마다 실행)
3. 3초 후: move(i + 1) 호출
4. 다음 슬라이드로 이동
5. 3초 후: 다시 move(i + 1) 호출
6. 반복...
7. 마지막 슬라이드에서 다음 이동 시 시나리오 2와 동일
```

---

## CSS와의 연동

### CSS 구조

```css
.slide .imgs {
    width: 700%;  /* 7개 슬라이드 (복제본 포함) */
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
}

.slide .imgs li {
    width: 14.2857%;  /* 100% / 7 = 14.2857% */
    float: left;
    height: 100%;
}
```

**JavaScript와의 연동:**
- JavaScript는 `margin-left`를 `-100%`, `-200%` 등으로 설정
- CSS에서 각 `li`가 `14.2857%` 너비를 가지므로 `-100%`는 정확히 한 슬라이드 너비
- `transition` 속성은 JavaScript에서 동적으로 설정

---

## 핵심 기술 포인트

### 1. cloneNode(true)

```javascript
const firstClone = items[0].cloneNode(true);
```

- `true`: 깊은 복사 (자식 요소까지 모두 복사)
- 이미지까지 포함하여 완전히 동일한 요소 생성

### 2. insertBefore와 appendChild

```javascript
imgs.insertBefore(lastClone, items[0]);  // 앞에 추가
imgs.appendChild(firstClone);            // 뒤에 추가
```

- `insertBefore()`: 특정 요소 앞에 삽입
- `appendChild()`: 맨 뒤에 추가

### 3. transitionend 이벤트

```javascript
imgs.addEventListener('transitionend', () => {
    // transition 완료 후 실행
});
```

- CSS transition이 완료되면 자동 발생
- `setTimeout`보다 정확한 타이밍 보장

### 4. 나머지 연산자(%)를 이용한 인덱스 매핑

```javascript
let pagerIdx = (index - 1 + count) % count;
```

- 복제본과 실제 슬라이드를 동일한 페이저로 매핑
- 페이저는 0~4까지만 있으므로 나머지 연산으로 변환

### 5. 화살표 함수와 클로저

```javascript
nextBtn.onclick = () => move(i + 1);
```

- 화살표 함수로 간결한 코드
- 클로저를 통해 외부 변수 `i`에 접근

### 6. 기본 매개변수 (Default Parameters)

```javascript
function move(index, speed = 0.6) {
```

- `speed`의 기본값이 `0.6`초
- `move(1)` 호출 시 `speed`는 자동으로 `0.6`
- `move(1, 0)` 호출 시 `speed`는 `0` (애니메이션 없음)

### 7. 템플릿 리터럴 (Template Literals)

```javascript
imgs.style.marginLeft = `${-index * 100}%`;
```

- 백틱(`\`\``) 사용
- `${}`로 변수 값을 문자열에 삽입

---

## 개선 가능한 부분

### 1. transitionend 이벤트 중복 방지

현재 코드는 모든 `transitionend`에 대해 이벤트가 발생하므로, 다른 transition이 있을 경우 문제가 될 수 있습니다.

**개선안:**
```javascript
imgs.addEventListener('transitionend', (e) => {
    // margin-left transition만 처리
    if (e.propertyName !== 'margin-left') return;
    
    if (i === 0) {
        move(count, 0);
    } else if (i === count + 1) {
        move(1, 0);
    }
});
```

### 2. 자동 재생 중지 시 타이머 정리

마우스가 빠르게 들어갔다 나갔다 할 때 타이머가 중복 생성될 수 있습니다.

**개선안:**
```javascript
const startTimer = () => {
    stopTimer(); // 기존 타이머 정리
    timer = setInterval(() => move(i + 1), 3000);
};
```

### 3. 페이저 업데이트 최적화

현재는 모든 페이저를 순회하며 업데이트합니다.

**개선안:**
```javascript
// 현재 활성화된 페이저 찾기
const currentActive = document.querySelector('.pager li.on');
if (currentActive) currentActive.classList.remove('on');
pager[pagerIdx].classList.add('on');
```

### 4. 터치 이벤트 지원

모바일 환경을 위한 스와이프 제스처 추가 가능합니다.

**개선안:**
```javascript
let startX = 0;
let currentX = 0;

imgs.addEventListener('touchstart', (e) => {
    startX = e.touches[0].clientX;
});

imgs.addEventListener('touchend', (e) => {
    currentX = e.changedTouches[0].clientX;
    const diff = startX - currentX;
    
    if (Math.abs(diff) > 50) {
        if (diff > 0) {
            move(i + 1); // 오른쪽으로 스와이프 → 다음
        } else {
            move(i - 1); // 왼쪽으로 스와이프 → 이전
        }
    }
});
```

### 5. 키보드 네비게이션

방향키로 슬라이드 이동 기능 추가 가능합니다.

**개선안:**
```javascript
document.addEventListener('keydown', (e) => {
    if (e.key === 'ArrowRight') {
        move(i + 1);
    } else if (e.key === 'ArrowLeft') {
        move(i - 1);
    }
});
```

### 6. 에러 처리

DOM 요소가 없을 경우를 대비한 에러 처리 추가 가능합니다.

**개선안:**
```javascript
if (!imgs || !nextBtn || !prevBtn) {
    console.error('필수 DOM 요소를 찾을 수 없습니다.');
    return;
}
```

---

## 마무리

이 코드는 **순수 JavaScript로 구현된 무한 루프 슬라이더**의 완벽한 예제입니다. `transitionend` 이벤트를 활용하여 자연스러운 무한 루프를 구현했으며, 코드가 간결하고 이해하기 쉽습니다.

**핵심 학습 포인트:**
1. ✅ 무한 루프 구현 방법 (복제본 활용)
2. ✅ `transitionend` 이벤트의 활용
3. ✅ CSS transition과 JavaScript의 조합
4. ✅ 나머지 연산자를 이용한 인덱스 매핑
5. ✅ 화살표 함수와 클로저
6. ✅ 이벤트 기반 프로그래밍
7. ✅ ES6 문법 활용 (화살표 함수, 템플릿 리터럴, 기본 매개변수)

이 코드를 이해하면 다양한 슬라이더 구현이 가능합니다!
