# useEffect 실전 예제

**예제 전체 설명 (Example1~4 · Ex01~03 · ExTimer):** [useEffect-예제전체-설명.md](./useEffect-예제전체-설명.md)  
**객관식:** [useEffect-예제전체-객관식-문제.md](./useEffect-예제전체-객관식-문제.md) · [정답](./useEffect-예제전체-객관식-정답.md)  
**React·CSS:** [useEffect-example-설명.md](./useEffect-example-설명.md)  
**개념:** [../React-useEffect-정리.md](../React-useEffect-정리.md)

## 실행

```bash
cd useEffect-example
npm install
npm run dev
```

**F12 콘솔** 확인

## 탭 구성

| 탭 | 파일 | 내용 |
|----|------|------|
| 예제1 | `Example1.jsx` | `[]` 마운트 1회 |
| 예제2 | `Example2.jsx` | `[count]` + title |
| 예제3 | `Example3.jsx` | Cleanup 개념 |
| 예제4 | `Example4.jsx` | 마운트 + count + Cleanup |
| Ex01 | `Ex01.jsx` | `[]` — +1해도 Effect 안 함 |
| Ex02 | `Ex02.jsx` | `[count]` |
| Ex03 | `Ex03.jsx` | 배열 없음 — 매 렌더 |
| **ExTimer** | `ExTimer.jsx` + `Timer.jsx` | **setInterval + clearInterval** |
