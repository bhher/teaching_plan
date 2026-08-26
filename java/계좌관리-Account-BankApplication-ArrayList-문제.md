# 20. 계좌 관리 프로그램 (ArrayList 버전)

키보드로부터 계좌 정보를 입력받아 계좌를 관리하는 프로그램입니다.

- 계좌는 **`Account` 객체**로 생성됩니다.
- `BankApplication`에서 **`ArrayList<Account>`** 로 관리합니다.
- 실행 결과를 보고 `Account`와 `BankApplication` 클래스를 작성하세요.
- 키보드 입력은 **`Scanner`의 `nextLine()`** 을 사용합니다.

---

## 메뉴

```text
----------------------------------------------------------
1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료
----------------------------------------------------------
선택>
```

---

## 실행 결과

배열 버전과 동일합니다.

1. 계좌생성: `111-111 홍길동 10000`, `111-222 강자바 20000`
2. 계좌목록 출력
3. 예금: `111-111`에 `5000`
4. 출금: `111-222`에서 `3000`
5. 목록 확인: `15000`, `17000` → 종료

---

## 클래스 설계

### Account

| 필드 | 타입 | 설명 |
|------|------|------|
| `ano` | `String` | 계좌번호 |
| `owner` | `String` | 계좌주 |
| `balance` | `int` | 잔액 |

### BankApplication

```java
private static ArrayList<Account> accountList = new ArrayList<>();
private static Scanner scanner = new Scanner(System.in);
```

### 더미 데이터 (프로그램 시작 시 3개)

```java
accountList.add(new Account("111-111", "홍길동", 10000));
accountList.add(new Account("111-222", "강자바", 20000));
accountList.add(new Account("111-333", "이코딩", 30000));
```

| 계좌번호 | 계좌주 | 잔액 |
|----------|--------|------|
| 111-111 | 홍길동 | 10000 |
| 111-222 | 강자바 | 20000 |
| 111-333 | 이코딩 | 30000 |

| 메서드 | 역할 |
|--------|------|
| `createAccount()` | `accountList.add(...)` |
| `accountList()` | for-each로 출력 |
| `deposit()` | 찾아 잔액 증가 |
| `withdraw()` | 찾아 잔액 감소 |
| `findAccount(String ano)` | 계좌 찾기 |

---

## 배열 vs ArrayList

| | 배열 | ArrayList |
|--|------|-----------|
| 선언 | `Account[100]` | `ArrayList<Account>` |
| 추가 | null 자리 찾아 저장 | `add()` |
| 크기 | 고정 100 | 자동 증가 |

---

## 참고

- 정답: [계좌관리-Account-BankApplication-ArrayList-정답.md](./계좌관리-Account-BankApplication-ArrayList-정답.md)
- 배열 버전: [계좌관리-Account-BankApplication-문제.md](./계좌관리-Account-BankApplication-문제.md)
