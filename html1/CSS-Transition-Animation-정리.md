# CSS Transition · Animation 정리

속성 변화를 부드럽게 만들 때 쓰는 **transition**과  
여러 단계 움직임을 만드는 **animation**을 한눈에 정리한 문서입니다.

관련 자료:
- 문제: [CSS-Transition-Animation-문제.md](./CSS-Transition-Animation-문제.md)
- 정답: [CSS-Transition-Animation-정답.md](./CSS-Transition-Animation-정답.md)
- HTML 예제: [CSS-Transition-Animation-정답예제.html](./CSS-Transition-Animation-정답예제.html)

---

## 1. 한눈에 비교

| | transition | animation |
|--|------------|-----------|
| 역할 | 상태 변화를 **부드럽게** 연결 | `@keyframes`로 **다단계** 움직임 |
| 트리거 | hover, class 추가 등 | 자동 실행 가능 |
| 반복 | 보통 상태 따라 1회 | `infinite` 가능 |
| 키워드 | `transition` | `@keyframes`, `animation` |
| 예시 | 버튼 색이 천천히 바뀜 | 스피너가 계속 회전 |

```text
transition  : A 상태 ──부드럽게──▶ B 상태
animation   : 0% → 50% → 100% 여러 장면으로 움직임
```

---

## 2. Transition (트랜지션)

### 2.1 개념

CSS 속성 값이 바뀔 때 **즉시 바뀌지 않고** 지정한 시간 동안 부드럽게 변합니다.

### 2.2 단축 속성 순서

```css
transition: 속성 시간 타이밍 지연;
/*          property duration timing-function delay */
```

```css
transition: background-color 0.3s ease 0.1s;
```

| 값 | 의미 | 예 |
|----|------|-----|
| property | 어떤 속성을 바꿀지 | `background-color`, `transform`, `all` |
| duration | 지속 시간 (필수) | `0.3s`, `300ms` |
| timing-function | 속도 곡선 | `ease`, `linear` |
| delay | 시작 전 대기 | `0.1s` |

### 2.3 자주 쓰는 예

```css
.btn {
  background: #333;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.btn:hover {
  background: #e74c3c;
  transform: translateY(-4px);
}
```

```css
/* 모든 속성 */
transition: all 0.4s ease;

/* 특정 속성만 */
transition: transform 0.25s ease, box-shadow 0.25s ease;
```

### 2.4 타이밍 함수

| 값 | 느낌 |
|----|------|
| `ease` | 느리게 시작 → 빠르게 → 느리게 끝 (기본) |
| `linear` | 일정한 속도 |
| `ease-in` | 느리게 시작 |
| `ease-out` | 느리게 끝 |
| `ease-in-out` | 시작과 끝이 느림 |

### 2.5 전환하기 좋은 속성

- 잘 됨: `color`, `background-color`, `opacity`, `transform`, `box-shadow`
- 비권장/어려움: `display` (`none` ↔ `block`은 transition으로 부드럽지 않음)

---

## 3. Transform (트랜스폼)

transition / animation과 자주 함께 씁니다.  
요소를 **이동·회전·확대**합니다.

| 함수 | 의미 | 예 |
|------|------|-----|
| `translateX()` | 좌우 이동 | `translateX(50px)` 오른쪽 |
| `translateY()` | 상하 이동 | `translateY(-10px)` 위 |
| `rotate()` | 회전 | `rotate(45deg)` |
| `scale()` | 확대/축소 | `scale(1.05)` 1.05배 |
| `skew()` | 기울임 | `skewX(10deg)` |

```css
/* 여러 개는 한 줄에 */
transform: translateX(10px) scale(1.1) rotate(5deg);
```

> `translateY(-값)` = 위로 이동  
> `translateY(+값)` = 아래로 이동

---

## 4. Animation (애니메이션)

### 4.1 개념

`@keyframes`로 **시작~끝(중간 포함)** 장면을 정의하고,  
`animation`으로 실행합니다.

### 4.2 @keyframes

```css
@keyframes fadeIn {
  from { opacity: 0; }
  to   { opacity: 1; }
}

/* 또는 퍼센트 */
@keyframes spin {
  0%   { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
```

### 4.3 animation 단축

```css
animation: 이름 시간 타이밍 지연 횟수 방향 채움;
```

```css
.box {
  animation: fadeIn 1s ease forwards;
}

.spinner {
  animation: spin 1s linear infinite;
}
```

| 속성 | 의미 | 예 |
|------|------|-----|
| `animation-name` | keyframes 이름 | `spin` |
| `animation-duration` | 시간 | `1s` |
| `animation-timing-function` | 속도 | `linear` |
| `animation-delay` | 시작 지연 | `0.5s` |
| `animation-iteration-count` | 반복 | `1`, `infinite` |
| `animation-fill-mode` | 끝난 뒤 상태 | `forwards` (끝 상태 유지) |

### 4.4 자주 쓰는 패턴

**페이드 인**
```css
@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}
.box {
  animation: fadeIn 0.6s ease forwards;
}
```

**스피너**
```css
@keyframes spin {
  to { transform: rotate(360deg); }
}
.loader {
  animation: spin 1s linear infinite;
}
```

**바운스**
```css
@keyframes bounce {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-20px); }
}
.ball {
  animation: bounce 2s ease infinite;
}
```

---

## 5. 실전 UI 패턴

### 버튼 hover
```css
.btn {
  transition: background-color 0.3s ease, transform 0.3s ease;
}
.btn:hover {
  background: #e74c3c;
  transform: translateY(-4px);
}
```

### 카드 확대
```css
.card {
  transition: transform 0.25s ease, box-shadow 0.25s ease;
}
.card:hover {
  transform: scale(1.05);
  box-shadow: 0 12px 28px rgba(0, 0, 0, 0.15);
}
```

---

## 6. 성능 팁

| 권장 | 비권장 |
|------|--------|
| `transform` | `top`, `left`로 위치 이동 |
| `opacity` | `width`, `height`를 자주 변경 |
| | `display`로 나타내기/숨기기 애니메이션 |

`transform`과 `opacity`가 비교적 가볍습니다.

---

## 7. 자주 하는 실수

1. **`display`에 transition** → 안 부드러움. `opacity` / `visibility` 사용
2. **transform을 여러 줄로 따로 씀** → 마지막만 적용됨. 한 줄에 이어 쓰기
3. **`@keyframes` 없이 animation만** → 다단계 움직임 불가
4. **transition을 기대하는데 상태 변화가 없음** → hover/class 변경이 있어야 동작

---

## 8. 치트시트

```css
/* Transition */
transition: all 0.3s ease;
transition: transform 0.25s ease, opacity 0.25s ease;

/* Transform */
transform: translateX(50px);
transform: translateY(-10px);
transform: rotate(45deg);
transform: scale(1.1);
transform: translateX(10px) scale(1.1);

/* Animation */
@keyframes name { from {} to {} }
animation: name 1s ease forwards;
animation: name 1s linear infinite;
animation-delay: 0.5s;
animation-fill-mode: forwards;
```

---

## 9. 한 줄 요약

- **transition** = 상태가 바뀔 때 부드럽게
- **transform** = 이동·회전·확대 (모양 바꾸기)
- **animation** = keyframes로 자동/반복 움직임
