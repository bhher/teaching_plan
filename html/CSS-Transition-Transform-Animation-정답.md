# CSS Transition · Transform · Animation 정답

문제: [CSS-Transition-Transform-Animation-문제.md](./CSS-Transition-Transform-Animation-문제.md)

---

## A. 객관식 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 1 | ② | transition은 **상태 변화**를 부드럽게 연결 |
| 2 | ② | `속성 → duration → timing → delay` |
| 3 | ② | hover 색 변화는 transition이 적합 |
| 4 | ③ | X축 이동은 `translateX` |
| 5 | ③ | 배율 변경은 `scale` |
| 6 | ② | Y축 음수 = 위쪽 이동 |
| 7 | ① | transition=상태변화 / animation=keyframes 다단계 |
| 8 | ① | `forwards`로 끝 상태 유지 |
| 9 | ② | `infinite` = 무한 반복 |
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

---

## C. 코드 작성 정답

### 15번. 버튼 hover

```css
.btn {
  background: #333;
  transition: background-color 0.25s ease, transform 0.25s ease;
}

.btn:hover {
  background: #c45c26;
  transform: translateY(-3px);
}
```

### 16번. 카드

```css
.card {
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
  transform: scale(1.03);
  box-shadow: 0 12px 28px rgba(0, 0, 0, 0.15);
}
```

### 17번. 스피너

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
| 18 | **X** | `display`는 transition 대상이 되기 어렵다. `opacity`, `visibility` 등을 사용 |
| 19 | **O** | 여러 transform은 한 속성에 나란히 작성 |
| 20 | **O** | `animation-delay`는 시작 지연 |

---

## 한눈에 비교

| | transition | transform | animation |
|--|------------|-----------|-----------|
| 역할 | 변화를 부드럽게 | 이동/회전/확대 | 키프레임 움직임 |
| 트리거 | hover 등 상태 변화 | 단독 또는 transition/animation과 함께 | 자동/클래스 추가 |
| 키워드 | `transition` | `translate` `rotate` `scale` | `@keyframes` `animation` |

---

## 자주 쓰는 패턴

```css
/* 떠오르는 카드 */
.card {
  transition: transform 0.25s ease, box-shadow 0.25s ease;
}
.card:hover {
  transform: translateY(-6px);
}

/* 페이드 인 */
@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}
.box {
  animation: fadeIn 0.6s ease forwards;
}
```
