# 03. 사용자 정의 예외

직접 예외 클래스를 만들고 `throw` / `try-catch`로 처리합니다.

| 파일 | 역할 |
|------|------|
| `InvalidScoreException.java` | Unchecked (`RuntimeException`) — 점수 범위 |
| `InsufficientBalanceException.java` | Checked (`Exception`) — 잔액 부족 |
| `CustomExceptionDemo.java` | 실행 (점수 검증 + 출금) |

```powershell
javac *.java
java CustomExceptionDemo
```

| 부모 | 종류 | throws 선언 |
|------|------|-------------|
| `Exception` | Checked | **필수** |
| `RuntimeException` | Unchecked | 선택 |
