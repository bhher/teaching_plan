# 정렬(Comparable/Comparator) + 사용자 정의 예외 — 1일 수업

한 폴더에 파일이 몰리지 않도록 **교시별 폴더**로 나눴습니다.  
수업할 때 **해당 폴더만** 열고 실행하면 됩니다.

```
정렬-예외-1일/
├── README.md              ← 이 파일 (전체 안내)
├── 01-Comparable/         ← 1~2교시 예제
├── 02-Comparator/         ← 3교시 예제 (데모)
├── 02-Comparator-문제/    ← 학생용 (TODO)
├── 02-Comparator-정답/    ← 정답 코드
├── 03-예외/               ← 4~5교시
└── 04-실전/               ← 6교시 (정렬+예외)
```

| 폴더 | 내용 | 실행 |
|------|------|------|
| [01-Comparable](01-Comparable/) | 기본 정렬 `compareTo` | `java ComparableDemo` |
| [02-Comparator](02-Comparator/) | 기준 바꾸기 데모 | `java ComparatorDemo` |
| [02-Comparator-문제](02-Comparator-문제/) | **문제** (TODO 채우기) | `java ComparatorPractice` |
| [02-Comparator-정답](02-Comparator-정답/) | **정답** | `java ComparatorPractice` |
| [03-예외](03-예외/) | Checked / Unchecked | `java CustomExceptionDemo` |
| [04-실전](04-실전/) | 상품 정렬 + 가격 예외 | `java ProductSortExceptionDemo` |

```powershell
cd 01-Comparable
javac *.java
java ComparableDemo
```

상세 교안:  
- [Comparable-인터페이스-정렬-상세설명.md](../Comparable-인터페이스-정렬-상세설명.md)  
- [Comparator-인터페이스-정렬-상세설명.md](../Comparator-인터페이스-정렬-상세설명.md)  
- [10장_예외_처리.md](../10장_예외_처리.md)

---

## Part A. 정렬

### 왜 인터페이스가 필요한가?

`Collections.sort(list)` 는 **무엇을 기준으로 앞/뒤를 정할지** 알아야 합니다.

| 방식 | 어디에 기준을 쓰나 | 메서드 | 폴더 |
|------|-------------------|--------|------|
| **Comparable** | 클래스 **안** | `compareTo` | `01-Comparable` |
| **Comparator** | 클래스 **밖** | `compare` | `02-Comparator` |

```text
음수  →  내가 앞
0     →  같다
양수  →  내가 뒤
```

### Comparable vs Comparator

| | Comparable | Comparator |
|--|------------|------------|
| 구현 위치 | 도메인 클래스 | 별도/람다 |
| 호출 | `sort(list)` | `sort(list, comparator)` |
| 기준 개수 | 보통 1개 | 여러 개 자유 |

---

## Part B. 사용자 정의 예외 (`03-예외`)

| 부모 | 종류 | throws |
|------|------|--------|
| `Exception` | Checked | **필수** |
| `RuntimeException` | Unchecked | 선택 |

패턴: 예외 클래스 만들기 → 조건에서 `throw` → `try-catch`

---

## Part C. 실전 (`04-실전`)

상품 등록 시 가격 검증(예외) + 가격/이름 정렬

---

## 체크리스트

- [ ] `compareTo` / `compare` 반환값 (음/0/양)
- [ ] `sort(list)` vs `sort(list, comp)`
- [ ] `Exception` vs `RuntimeException`
- [ ] `throw` / `throws` / `try-catch`
