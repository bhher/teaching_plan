# CSS Transition · Animation 정답

문제: [CSS-Transition-Animation-문제.md](./CSS-Transition-Animation-문제.md)  
HTML 예제: [CSS-Transition-Animation-정답예제.html](./CSS-Transition-Animation-정답예제.html)

---

## A. 객관식 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 1 | ② | transition은 **상태 변화**를 부드럽게 연결한다 |
| 2 | ② | `속성 → duration → timing → delay` |
| 3 | ② | hover 색 변화는 `transition`이 적합 |
| 4 | ② | Y축 음수 = 위쪽 이동 (`translateY(-10px)`) |
| 5 | ③ | 배율 변경은 `scale` |
| 6 | ① | transition=상태변화 / animation=keyframes 다단계 |
| 7 | ① | `forwards`로 끝 상태 유지 |
| 8 | ② | `infinite` = 무한 반복 |
| 9 | ② | `animation-delay`는 시작을 늦춘다 |
| 10 | ③ | `transform`, `opacity`가 상대적으로 가벼움 |

---

## B. 빈칸 정답

### 11번
```css
transform: rotate(45deg);
```

### 12번
```css
transition: all 0.4s ease;
```

### 13번
```css
animation: bounce 2s;
```

### 14번
```css
animation-fill-mode: forwards;
```

### 15번
```css
transform: translateX(50px);
```

---

## C. 코드 작성 정답

### 16번. 버튼 hover

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

### 17번. 카드 확대

```css
.card {
  transition: transform 0.25s ease, box-shadow 0.25s ease;
}

.card:hover {
  transform: scale(1.05);
  box-shadow: 0 12px 28px rgba(0, 0, 0, 0.15);
}
```

### 18번. 로딩 스피너

```css
@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

.spinner {
  animation: spin 1s linear infinite;
}
```

---

## D. OX 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 19 | **X** | `display`는 transition 대상이 되기 어렵다. `opacity` 등을 사용 |
| 20 | **O** | 여러 transform은 한 속성에 나란히 작성 |
| 21 | **X** | transition은 hover 등 **상태 변화**가 있어야 동작 |
| 22 | **X** | 다단계 애니메이션에는 `@keyframes`가 필요 |

---

## 한눈에 비교

| | transition | animation |
|--|------------|-----------|
| 역할 | 변화를 부드럽게 | 키프레임으로 움직임 |
| 트리거 | hover, class 변경 등 | 자동 실행 가능 |
| 반복 | 보통 1회(왕복은 상태 따라) | `infinite` 가능 |
| 키워드 | `transition` | `@keyframes`, `animation` |

---

## 자주 쓰는 패턴

```css
/* 떠오르는 버튼 */
.btn {
  transition: transform 0.25s ease, background-color 0.25s ease;
}
.btn:hover {
  transform: translateY(-4px);
}

/* 페이드 인 */
@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}
.box {
  animation: fadeIn 0.6s ease forwards;
}

/* 스피너 */
@keyframes spin {
  to { transform: rotate(360deg); }
}
.loader {
  animation: spin 1s linear infinite;
}
```
