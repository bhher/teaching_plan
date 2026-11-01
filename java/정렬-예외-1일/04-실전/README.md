# 04. 실전 — 정렬 + 예외

상품 등록(가격 검증)과 Comparable/Comparator 정렬을 한 번에 봅니다.

| 파일 | 역할 |
|------|------|
| `InvalidPriceException.java` | 가격 ≤ 0 이면 예외 |
| `Product.java` | 상품 + `Comparable` (가격 오름차순) |
| `ProductSortExceptionDemo.java` | 등록 → 정렬 실행 |

```powershell
javac *.java
java ProductSortExceptionDemo
```
