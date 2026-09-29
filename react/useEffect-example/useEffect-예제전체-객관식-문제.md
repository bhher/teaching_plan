# useEffect-example 객관식 문제

**범위:** [useEffect-예제전체-설명.md](./useEffect-예제전체-설명.md)  
**정답:** [useEffect-예제전체-객관식-정답.md](./useEffect-예제전체-객관식-정답.md)

---

## 1번
`useEffect(() => { ... }, [])` 의 실행 시점으로 올바른 것은?

① 버튼을 클릭할 때마다  
② **컴포넌트가 처음 마운트될 때 1회**  
③ State가 바뀔 때마다  
④ 렌더링될 때마다  

## 2번
`useEffect(() => { ... }, [count])` 에 대한 설명으로 옳은 것은?

① count와 무관하게 1회만 실행  
② **처음 실행 + count가 변경될 때 실행**  
③ count가 바뀔 때만 실행되고 처음엔 실행 안 함  
④ Cleanup만 실행  

## 3번
의존성 배열을 **아예 안 쓴** `useEffect(() => { ... })` 는?

① 한 번도 실행 안 됨  
② 마운트 1회만  
③ **렌더링될 때마다 실행**  
④ 언마운트될 때만  

## 4번
Ex01에서 +1을 눌러 count가 바뀌어도 Effect 로그가 안 찍히는 이유는?

① 버그  
② **의존성이 `[]`라서**  
③ useState를 안 써서  
④ Cleanup 때문에  

## 5번
예제2·Ex02에서 `document.title`을 바꾸는 이유는?

① CSS를 바꾸려고  
② **렌더 후 DOM(탭 제목)을 조작하는 예**  
③ count를 초기화하려고  
④ Cleanup 예제라서  

## 6번
예제3에서 「숨기기」를 누르면 Cleanup이 실행되는 직접적 이유는?

① setInterval이 돌아서  
② **자식 컴포넌트가 언마운트되어서**  
③ count가 0이 되어서  
④ 의존성 배열이 없어서  

## 7번
ExTimer에서 Cleanup에 꼭 넣어야 할 것은?

① `setCount(0)`  
② **`clearInterval(timer)`**  
③ `addEventListener`  
④ `document.title = ''`  

## 8번
Cleanup이 **실행되지 않는** 경우는?

① 타이머 숨기기(언마운트)  
② 다른 탭으로 이동해 컴포넌트 제거  
③ **setInterval이 1초마다 돌 때**  
④ 의존성이 바뀌어 Effect를 다시 돌리기 직전  

## 9번
Cleanup에 대한 설명으로 **잘못된** 것은?

① 컴포넌트 언마운트 시 실행될 수 있다  
② **매초·매 스크롤마다 반드시 실행된다**  
③ setInterval / addEventListener 정리에 쓴다  
④ Effect를 끝낼 때 정리하는 함수다  

## 10번
예제3(Cleanup 개념)과 ExTimer의 가장 큰 차이는?

① 예제3만 `[]`를 쓴다  
② **ExTimer는 `clearInterval`로 실제로 타이머를 끈다**  
③ ExTimer는 useEffect를 안 쓴다  
④ 예제3만 언마운트가 있다  

## 11번
`setCount((prev) => prev + 1)` 을 쓰는 이유로 적절한 것은?

① CSS 때문에  
② **이전 state를 안전하게 +1 (클로저 문제 완화)**  
③ Cleanup 때문에  
④ 의존성 배열 때문에  

## 12번
수업 핵심 문장에 가장 가까운 것은?

① useEffect는 onClick과 같다  
② **타이머·이벤트를 켰으면 언마운트 때 Cleanup으로 끈다**  
③ 의존성 배열은 항상 생략한다  
④ Cleanup은 매초 실행해야 한다  
