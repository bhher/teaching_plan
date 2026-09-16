# Java 예외(Exception) 처리 — 완전 정리

수업 순서대로 예외의 기초부터 **사용자 정의 예외**까지 정리합니다.

관련: [정렬-예외-1일/03-예외](../정렬-예외-1일/03-예외/) · [10장_예외_처리.md](../10장_예외_처리.md)

---

## 폴더 구성

```
java/예외처리/
├── README.md                 ← 이 파일
├── 01-기본-try-catch/
├── 02-getMessage-printStackTrace/
├── 03-Scanner-NumberFormat/
├── 04-여러-catch/
├── 05-배열-Null/
├── 06-finally/
├── 07-throw/
├── 08-은행-사용자정의예외/
└── 09-회원가입-사용자정의예외/
```

각 폴더에서:

```powershell
javac *.java
java 클래스이름
```

---

## 추천 수업 순서

```text
① 예외란?
② 예외가 발생하는 상황
③ try-catch
④ catch의 예외 객체
⑤ getMessage()
⑥ printStackTrace()
⑦ 여러 catch
⑧ finally
⑨ throw
⑩ throws
⑪ 사용자 정의 예외
⑫ 실전 (은행 · 회원가입)
```

---

## 1. 예외(Exception)란?

프로그램을 실행하다가 **정상적으로 처리할 수 없는 상황**이 생기는 것입니다.

```java
int a = 10;
int b = 0;
System.out.println(a / b);
```

```text
Exception in thread "main" java.lang.ArithmeticException: / by zero
```

→ 처리하지 않으면 **프로그램이 중단**됩니다.

---

## 2~4. try / catch 기본

```java
try {
    // 예외가 날 수 있는 코드
    int result = 10 / 0;
    System.out.println(result);  // 여기까지 안 옴
} catch (ArithmeticException e) {
    // 예외가 났을 때 처리
    System.out.println("0으로 나눌 수 없습니다.");
}
System.out.println("프로그램 종료");  // 계속 실행
```

```text
try → 예외 발생 → try 즉시 중단 → catch 실행 → 이후 코드 계속
```

`ArithmeticException e` — 발생한 **예외 객체**를 변수 `e`로 받음.

→ `01-기본-try-catch`

---

## 5~6. getMessage / printStackTrace

| 메서드 | 용도 |
|--------|------|
| `e.getMessage()` | 예외 메시지만 (`/ by zero`) |
| `e.printStackTrace()` | 발생 위치·호출 스택까지 (개발 시 많이 사용) |

→ `02-getMessage-printStackTrace`

---

## 7. Scanner + NumberFormatException ⭐

```java
try {
    int num = Integer.parseInt(sc.nextLine());
} catch (NumberFormatException e) {
    System.out.println("숫자만 입력하세요.");
}
```

문자 `"abc"`를 숫자로 바꾸려 하면 이 예외가 납니다.

→ `03-Scanner-NumberFormat`

---

## 8. 여러 catch

```java
try {
    ...
} catch (NumberFormatException e) {
    ...
} catch (ArithmeticException e) {
    ...
}
```

위에서부터 **맞는 타입 하나만** 실행됩니다.  
구체적인 예외를 위에, `Exception`은 아래에 두는 것이 좋습니다.

→ `04-여러-catch`

---

## 9~10. 배열 · NullPointerException

| 예외 | 상황 |
|------|------|
| `ArrayIndexOutOfBoundsException` | `arr[5]` (길이 3인데 5번 접근) |
| `NullPointerException` | `null`인 객체에 `.length()` 등 호출 |

→ `05-배열-Null`

---

## 11. catch (Exception e)

```text
Exception
   ├─ ArithmeticException
   ├─ NumberFormatException
   ├─ NullPointerException
   └─ ...
```

상위 타입으로 대부분을 받을 수 있지만, **가능하면 구체적인 예외**를 잡는 것이 좋습니다.

---

## 12~13. finally ⭐⭐⭐

예외 **발생 여부와 관계없이** 실행됩니다. (파일 닫기, 자원 정리 등)

```java
try { ... }
catch (Exception e) { ... }
finally {
    System.out.println("마무리 작업");
}
```

→ `06-finally`

---

## 14~15. throw ⭐⭐⭐

개발자가 **직접** 예외를 발생시킴.

```java
if (age < 18) {
    throw new RuntimeException("미성년자는 사용할 수 없습니다.");
}
```

→ `07-throw`

---

## 16~21. 사용자 정의 예외 · throw / throws

### 예외 클래스

```java
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);  // e.getMessage() 가능
    }
}
```

### throw vs throws (반드시 구분)

| | 역할 | 예 |
|--|------|-----|
| **throw** | 예외를 **실제로 던짐** | `throw new ...` |
| **throws** | 메서드에서 **날 수 있다고 선언** | `void withdraw(...) throws ...` |

```text
throw  → 예외를 던진다
throws → 발생 가능성을 알려 호출부가 처리하게 한다
```

Checked 예외(`extends Exception`)는 `throws` 또는 메서드 안에서 try-catch가 **필수**입니다.

→ `08-은행-사용자정의예외` · `09-회원가입-사용자정의예외`

---

## 치트시트

| 키워드/API | 의미 |
|------------|------|
| `try` | 위험한 코드 |
| `catch` | 예외 처리 |
| `finally` | 항상 실행 |
| `throw` | 예외 발생 |
| `throws` | 예외 선언 |
| `e.getMessage()` | 메시지 |
| `e.printStackTrace()` | 스택 추적 |
