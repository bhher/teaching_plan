# CSS Transition · Animation 문제

`html1` 폴더 학습용 문제입니다.  
정답: [CSS-Transition-Animation-정답.md](./CSS-Transition-Animation-정답.md)

---

## A. 객관식 (1~10)

### 1번

`transition`의 역할로 옳은 것은?

① 페이지 로드 시 자동으로 계속 움직인다  
② 속성 값이 바뀔 때 변화를 부드럽게 이어 준다  
③ HTML 태그를 만든다  
④ 이미지를 다운로드한다

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

버튼에 마우스를 올리면(`:hover`) 배경색이 0.3초 동안 바뀌게 하려면?

① `animation: background 0.3s;`  
② `transition: background-color 0.3s;`  
③ `transform: background-color 0.3s;`  
④ `@keyframes`만 작성하면 된다

---

### 4번

요소를 **위로 10px** 이동시키는 코드는?

① `transform: translateX(10px);`  
② `transform: translateY(-10px);`  
③ `transform: scale(10px);`  
④ `transform: rotate(10px);`

---

### 5번

카드를 hover할 때 **1.1배 확대**하려면?

① `transform: rotate(1.1);`  
② `transform: skew(1.1);`  
③ `transform: scale(1.1);`  
④ `transition: scale(1.1);`

---

### 6번

`transition`과 `animation`의 차이로 옳은 것은?

① transition은 상태 변화 시, animation은 `@keyframes`로 여러 단계 움직임을 만들 수 있다  
② 둘 다 반드시 JavaScript가 필요하다  
③ animation은 hover에서만 동작한다  
④ transition은 무한 반복만 가능하다

---

### 7번

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
④ 배경색만 바뀐다

---

### 8번

애니메이션을 **무한 반복**하려면?

① `animation-iteration-count: 1;`  
② `animation-iteration-count: infinite;`  
③ `animation-delay: infinite;`  
④ `transition: infinite;`

---

### 9번

`animation-delay: 0.5s;`의 의미는?

① 애니메이션 속도를 0.5초로 한다  
② 애니메이션 시작을 0.5초 늦춘다  
③ 애니메이션을 0.5번만 반복한다  
④ 투명도를 0.5로 만든다

---

### 10번

성능상 비교적 권장되는 애니메이션 속성은?

① `width`, `height`, `top`, `left`를 자주 바꾸기  
② `margin`, `padding`을 크게 바꾸기  
③ `transform`, `opacity` 위주로 움직이기  
④ `display: none` ↔ `block`을 transition으로 처리하기

---

## B. 빈칸 채우기 (11~15)

### 11번

45도 회전:

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

### 15번

오른쪽으로 50px 이동:

```css
transform: ________(50px);
```

---

## C. 코드 작성 (16~18)

### 16번. 버튼 hover

요구사항:

- 기본 배경: `#333`
- hover 시 배경: `#e74c3c`
- hover 시 위로 4px 이동
- 변화는 `0.3s`, `ease`

`.btn` / `.btn:hover` CSS를 작성하세요.

---

### 17번. 카드 확대

요구사항:

- hover 시 `scale(1.05)`
- `box-shadow`가 진해지게
- `transition`으로 `transform`, `box-shadow`만 0.25초

`.card` / `.card:hover` CSS를 작성하세요.

---

### 18번. 로딩 스피너

요구사항:

- `@keyframes spin` : `0%` → `rotate(0deg)`, `100%` → `rotate(360deg)`
- `.spinner`에 `animation: spin 1s linear infinite`

CSS를 작성하세요.

---

## D. OX 문제 (19~22)

### 19번

`display: none` ↔ `display: block`은 `transition`으로 부드럽게 바꿀 수 있다. (O / X)

---

### 20번

여러 `transform`은 한 줄에 이어서 쓴다.  
예: `transform: translateX(10px) scale(1.1);` (O / X)

---

### 21번

`transition`은 hover 같은 상태 변화가 없어도 자동으로 계속 움직인다. (O / X)

---

### 22번

`@keyframes` 없이 `animation`만 쓰면 복잡한 다단계 애니메이션을 만들 수 있다. (O / X)

---

## 참고

- 정답: [CSS-Transition-Animation-정답.md](./CSS-Transition-Animation-정답.md)
- HTML 예제: [CSS-Transition-Animation-정답예제.html](./CSS-Transition-Animation-정답예제.html)
