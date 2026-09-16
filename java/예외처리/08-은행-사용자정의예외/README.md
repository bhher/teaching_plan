# throw vs throws / 사용자 정의 예외

## throw

```java
throw new InsufficientBalanceException("잔액이 부족합니다.");
```

→ 예외를 **실제로 발생**시킴.

## throws

```java
public void withdraw(int money) throws InsufficientBalanceException
```

→ “이 메서드에서 이 예외가 **날 수 있다**. 호출하는 쪽에서 처리하라.”

## 사용자 정의 예외

```java
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message); // e.getMessage() 가능
    }
}
```

```text
Exception
    └── InsufficientBalanceException  (우리가 만든 것)
```

| | Checked (`extends Exception`) | Unchecked (`extends RuntimeException`) |
|--|-------------------------------|----------------------------------------|
| throws 선언 | **필수**(또는 메서드 내 try-catch) | 선택 |
| 예 | 잔액 부족, 가입 나이 | 점수 범위 오류 등 |

실행:

```powershell
javac *.java
java BankMain
java RegisterMain   # 09 폴더
```
