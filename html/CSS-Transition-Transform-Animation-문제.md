# CSS Transition · Transform · Animation 문제

8회차(고급 CSS UI 패턴) 학습용 문제입니다.  
정답: [CSS-Transition-Transform-Animation-정답.md](./CSS-Transition-Transform-Animation-정답.md)

---

## A. 객관식 (1~10)

### 1번

`transition`의 역할로 옳은 것은?

① 페이지가 로드되면 자동으로 계속 움직인다  
② 속성 값이 바뀔 때 변화를 부드럽게 이어 준다  
③ HTML 구조를 바꾼다  
④ JavaScript 없이 서버와 통신한다

---

### 2번

다음 단축 속성의 순서로 올바른 것은?

```css
transition: background-color 0.3s ease 0.1s;
```

① 지연 → 시간 → 속성 → 타이밍  
② 속성 → 시간 → 타이밍 → 지연  
③ 시간 → 속성 → 지연 → 타이밍  
④ 타이밍 → 속성 → 시간 → 지연

---

### 3번

버튼을 올리면(`:hover`) 배경색이 0.3초 동안 바뀌게 하려면?

① `animation: background 0.3s;`  
② `transition: background-color 0.3s;`  
③ `transform: background-color 0.3s;`  
④ `@keyframes background { }` 만 작성

---

### 4번

요소를 **오른쪽으로 30px** 이동시키는 코드는?

① `transform: translateY(30px);`  
② `transform: scale(30px);`  
③ `transform: translateX(30px);`  
④ `transform: rotate(30px);`

---

### 5번

카드를 살짝 키우는(`1.05배`) hover 효과에 적합한 것은?

① `transform: rotate(1.05);`  
② `transform: skew(1.05);`  
③ `transform: scale(1.05);`  
④ `transform: translate(1.05);`

---

### 6번

`transform: translateY(-8px);` 의 의미는?

① 아래로 8px 이동  
② 위로 8px 이동  
③ 8배 확대  
④ 8도 회전

---

### 7번

`transition`과 `animation`의 차이로 옳은 것은?

① transition은 상태 변화 시, animation은 @keyframes로 여러 단계 움직임을 만들 수 있다  
② 둘 다 반드시 JavaScript가 필요하다  
③ animation은 hover에서만 동작한다  
④ transition은 무한 반복만 가능하다

---

### 8번

다음 코드의 결과로 알맞은 것은?

```css
@keyframes fadeIn {
  from { opacity: 0; }
  to   { opacity: 1; }
}
.box {
  animation: fadeIn 1s ease forwards;
}
```

① 박스가 1초 동안 점점 나타나고, 끝나면 보인 상태를 유지한다  
② 박스가 즉시 사라진다  
③ 박스가 계속 깜빡인다  
④ transform만 변경된다

---

### 9번

애니메이션을 **무한 반복**하려면?

① `animation-iteration-count: 1;`  
② `animation-iteration-count: infinite;`  
③ `animation-delay: infinite;`  
④ `transition: infinite;`

---

### 10번

다음 중 **성능상 비교적 권장**되는 애니메이션 속성은?

① `width`, `height`, `top`, `left` 를 자주 바꾸기  
② `margin`, `padding` 을 크게 바꾸기  
③ `transform`, `opacity` 위주로 움직이기  
④ `display: none` ↔ `block` 을 transition으로 처리하기

---

## B. 빈칸 채우기 (11~14)

### 11번

요소를 45도 회전:

```css
transform: ________(45deg);
```

---

### 12번

모든 속성 변화를 0.4초, ease로:

```css
transition: ________ 0.4s ease;
```

---

### 13번

keyframes 이름 `bounce`를 2초 동안 실행:

```css
animation: ________ 2s;
```

---

### 14번

애니메이션이 끝난 뒤 **마지막 키프레임 상태 유지**:

```css
animation-fill-mode: ________;
```

---

## C. 코드 작성 (15~17)

### 15번. 버튼 hover 효과

요구사항:

- 기본 배경: `#333`
- hover 시 배경: `#c45c26`
- hover 시 위로 3px (`translateY`)
- 변화는 `0.25s`, `ease`

`.btn` / `.btn:hover` CSS를 작성하세요.

---

### 16번. 카드 확대 + 그림자

요구사항:

- hover 시 `scale(1.03)`
- `box-shadow`가 진해지게
- `transition`으로 `transform`과 `box-shadow`만 0.3초

`.card` / `.card:hover` CSS를 작성하세요.

---

### 17번. 로딩 스피너

요구사항:

- `@keyframes spin` : `0%` → `rotate(0deg)`, `100%` → `rotate(360deg)`
- `.spinner`에 `animation: spin 1s linear infinite`

CSS를 작성하세요.

---

## D. OX 문제 (18~20)

### 18번

`display: none` ↔ `display: block` 은 `transition`으로 부드럽게 바꿀 수 있다. (O / X)

---

### 19번

`transform`을 여러 개 쓸 때는 한 줄에 이어서 쓴다.  
예: `transform: translateX(10px) scale(1.1);` (O / X)

---

### 20번

`animation-delay: 0.5s;` 는 애니메이션 시작을 0.5초 늦춘다. (O / X)

---

## 참고

- 교안: [html-8회차-고급CSS-UI패턴.md](./html-8회차-고급CSS-UI패턴.md)
- 정답: [CSS-Transition-Transform-Animation-정답.md](./CSS-Transition-Transform-Animation-정답.md)
