# useEffect-example 객관식 정답

문제: [useEffect-예제전체-객관식-문제.md](./useEffect-예제전체-객관식-문제.md)

| 번호 | 정답 |
|------|------|
| 1 | ② |
| 2 | ② |
| 3 | ③ |
| 4 | ② |
| 5 | ② |
| 6 | ② |
| 7 | ② |
| 8 | ③ |
| 9 | ② |
| 10 | ② |
| 11 | ② |
| 12 | ② |

---

## 간단 해설

1. `[]` → 마운트 1회  
2. `[count]` → 처음 + count 변경 시  
3. 배열 생략 → 매 렌더  
4. Ex01은 `[]`라 +1해도 Effect 안 함  
5. `document.title` = 렌더 후 DOM 조작 예  
6. `{show && <Child />}` false → 언마운트 → Cleanup  
7. ExTimer Cleanup = `clearInterval`  
8. 1초마다 도는 건 **인터벌 콜백**이지 Cleanup 아님  
9. Cleanup은 매초 실행 **아님**  
10. 예제3은 로그, ExTimer는 실제 clear  
11. 함수형 업데이트 `prev => prev + 1`  
12. 켰으면 언마운트 때 끈다  

**배점 예:** 문항당 1점, 12점 만점 · 통과 8점 이상
