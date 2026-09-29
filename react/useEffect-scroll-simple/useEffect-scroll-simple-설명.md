# useEffect-scroll-simple 설명

**폴더:** `react/useEffect-scroll-simple`  
**관련:** [useEffect-example](../useEffect-example/) · [React-useEffect-정리.md](../React-useEffect-정리.md)

```bash
cd useEffect-scroll-simple
npm install
npm run dev
```

아래로 스크롤 → 헤더 변경 · 우측 하단 `scrollY` 확인 · **F12 콘솔**

---

## 한 문장 (수업 핵심)

> **`addEventListener`로 등록한 이벤트는, 컴포넌트가 사라질 때 `removeEventListener`로 정리한다.**  
> 스크롤할 때마다 Cleanup이 도는 것이 **아니다.**

---

## 무엇을 하는 예제인가

스크롤 위치를 **state로 저장**하면서, **50px 이상인지**도 같이 확인합니다.

| State | 역할 | 예 |
|-------|------|-----|
| `scrollY` | 실제 스크롤 위치(px) | `127` |
| `isScrolled` | 50px을 **넘었는지** (true/false) | `true` |

헤더는 `isScrolled`에 따라 투명 ↔ 흰 배경으로 바뀝니다.

---

## 완성 코드 (`App.jsx` 핵심)

```jsx
const [isScrolled, setIsScrolled] = useState(false)
const [scrollY, setScrollY] = useState(0)

useEffect(() => {
  const handleScroll = () => {
    const y = window.scrollY // jQuery: $(window).scrollTop()

    setScrollY(Math.round(y))
    setIsScrolled(y > 50)
  }

  window.addEventListener('scroll', handleScroll)
  console.log('✅ scroll 이벤트 등록')

  return () => {
    window.removeEventListener('scroll', handleScroll)
    console.log('🧹 scroll 이벤트 제거')
  }
}, [])
```

```text
useEffect 실행 (마운트, 1회)
      ↓
이벤트 등록
      ↓
사용자가 스크롤
      ↓
handleScroll 실행
      ↓
scrollY / isScrolled 변경
      ↓
React 화면 업데이트
      ↓
(나중에) 컴포넌트 제거
      ↓
Cleanup → 이벤트 제거
```

---

## 1. handleScroll

```jsx
const handleScroll = () => {
```

스크롤이 발생했을 때 실행할 함수입니다.

```text
사용자가 스크롤
      ↓
scroll 이벤트 발생
      ↓
handleScroll() 실행
```

---

## 2. window.scrollY

```jsx
const y = window.scrollY
```

현재 **세로 스크롤 위치**입니다.

| 위치 | y |
|------|---|
| 맨 위 | `0` |
| 조금 | `30` |
| 50px | `50` |
| 100px | `100` |

주석의 `$(window).scrollTop()` (jQuery)과 거의 같은 역할입니다.

```js
const y = window.scrollY
// ≈ const y = $(window).scrollTop()
```

---

## 3. setScrollY(Math.round(y))

```jsx
setScrollY(Math.round(y))
```

스크롤 위치를 React state에 저장합니다.

```text
window.scrollY = 127.836...
        ↓
Math.round()
        ↓
128
        ↓
setScrollY(128)
```

화면 예: `scrollY: {scrollY}px` (우측 하단 `.scroll-info`)

`Math.round`는 표시를 깔끔하게 하려는 **교육용** 처리입니다.

```js
Math.round(10.4) // 10
Math.round(10.5) // 11
```

---

## 4. setIsScrolled(y > 50) ← 중요

```jsx
setIsScrolled(y > 50)
```

`y > 50`은 **true 또는 false**입니다.

| y | `y > 50` | setIsScrolled |
|---|----------|---------------|
| 20 | false | `false` |
| 50 | false | `false` (초과가 아님, `>` ) |
| 100 | true | `true` |

의미: **「지금 스크롤이 50px을 넘었는가?」**

올린 뒤 30px이면 다시 `false` → 헤더가 투명으로 돌아갑니다.  
이때 **useEffect가 다시 도는 게 아니라**, `handleScroll`만 다시 실행됩니다.

---

## 5. State 두 개의 역할

```jsx
const [scrollY, setScrollY] = useState(0)
const [isScrolled, setIsScrolled] = useState(false)
```

위치가 120px이면:

```text
window.scrollY → 120
┌──────────────────┐
│ scrollY = 120    │  ← 숫자 표시용
│ isScrolled=true  │  ← 헤더 스타일용
└──────────────────┘
```

---

## 6. addEventListener

```jsx
window.addEventListener('scroll', handleScroll)
```

의미: **window에서 scroll이 나면 `handleScroll`을 실행해라.**

```text
스크롤 발생
   ↓
scroll
   ↓
handleScroll()
   ↓
window.scrollY
   ↓
setScrollY() / setIsScrolled()
   ↓
React 리렌더 → 헤더·숫자 갱신
```

---

## 7. console.log('✅ scroll 이벤트 등록')

**스크롤할 때마다 실행되지 않습니다.**

`addEventListener`를 실행하는 순간(마운트 시 useEffect) **한 번**만 찍힙니다.

| 로그 | 언제 |
|------|------|
| `✅ scroll 이벤트 등록` | useEffect로 리스너 **등록**할 때 1회 |
| (스크롤 중) | `handleScroll`만 동작 — 등록 로그 없음 |
| `🧹 scroll 이벤트 제거` | Cleanup (언마운트 등) |

---

## 8. Cleanup이 왜 필요한가

등록만 하고 안 지우면, 컴포넌트가 없어져도 리스너가 남아 메모리 누수·중복 실행이 납니다.

```jsx
return () => {
  window.removeEventListener('scroll', handleScroll)
  console.log('🧹 scroll 이벤트 제거')
}
```

| | |
|--|--|
| 등록 | `addEventListener('scroll', handleScroll)` |
| 제거 | `removeEventListener('scroll', handleScroll)` |

### Cleanup이 도는 때 / 안 도는 때

| 상황 | Cleanup? |
|------|----------|
| 스크롤 올림·내림 | ❌ (`handleScroll`만) |
| 처음 페이지 진입 | ❌ (등록만) |
| 컴포넌트 **언마운트** | ✅ 제거 |
| 의존성 변경으로 Effect 재실행 직전 | ✅ |

이 예제는 `[]`라서 → **페이지/컴포넌트를 떠날 때** 제거됩니다.

---

## 9. JSX와 CSS 연결

### 헤더 클래스 전환

```jsx
<header className={`header ${isScrolled ? 'scrolled' : 'top'}`}>
```

| isScrolled | className | 모습 |
|------------|-----------|------|
| false | `header top` | 투명, 글자 흰색 |
| true | `header scrolled` | 흰 배경, 그림자 |

### `index.css` 요지

```css
.header {
  position: fixed;   /* 스크롤해도 위에 고정 */
  top: 0;
  left: 0;
  right: 0;
  transition: background 0.25s, padding 0.25s, ...;
}

.header.top {
  background: transparent;
  color: #fff;
}

.header.scrolled {
  background: #fff;
  color: #111;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
}

.hero {
  height: 70vh;      /* 스크롤할 높이 확보 */
  background: linear-gradient(...);
}

.scroll-info {
  position: fixed;
  right: 1rem;
  bottom: 1rem;      /* 현재 scrollY 표시 */
}
```

| CSS | 역할 |
|-----|------|
| `position: fixed` | 헤더·정보 박스가 화면에 고정 |
| `.top` / `.scrolled` | React state와 짝 |
| `.hero { height: 70vh }` | 스크롤할 공간을 만듦 |
| `transition` | 배경 전환을 부드럽게 |

---

## 10. 자주 하는 오해

| 오해 | 실제 |
|------|------|
| 50px 아래로 올리면 useEffect가 다시 실행된다 | ❌ `handleScroll`만 실행, `isScrolled`만 false |
| 스크롤마다 Cleanup이 돈다 | ❌ 언마운트(또는 Effect 재실행 전)에만 |
| `✅ 등록` 로그가 스크롤마다 찍혀야 한다 | ❌ 등록 시 1회만 |

---

## 11. useEffect-example 타이머와 비교

| | scroll-simple | ExTimer |
|--|---------------|---------|
| 등록 | `addEventListener` | `setInterval` |
| Cleanup | `removeEventListener` | `clearInterval` |
| 매 스크롤/매 초 | **핸들러**만 실행 | **인터벌 콜백**만 실행 |
| useEffect `[]` | 등록 1회 | 등록 1회 |

패턴은 같습니다.

> **켰으면 Cleanup으로 끈다. 끄는 시점은 “매 이벤트”가 아니라 “컴포넌트가 끝날 때”.**

---

## 12. 파일

| 파일 | 내용 |
|------|------|
| `src/App.jsx` | state + useEffect + 헤더/본문 |
| `src/index.css` | `.header.top` / `.scrolled` |
| `src/main.jsx` | 진입점 |

---

## 13. 수업 체크리스트

- [ ] `scrollY` = 숫자, `isScrolled` = true/false  
- [ ] `y > 50`이면 헤더 `scrolled`  
- [ ] 올려서 50 이하면 다시 `top` (useEffect 재실행 아님)  
- [ ] `✅ 등록`은 처음에만  
- [ ] Cleanup = `removeEventListener`
