# JavaAir 항공예약시스템 - 예외 처리 완전 정복

## 목차

1. [예외 처리 개요](#예외-처리-개요)
2. [사용된 예외 종류](#사용된-예외-종류)
3. [예외별 상세 설명](#예외별-상세-설명)
4. [예외 처리 패턴](#예외-처리-패턴)
5. [예외 처리 체크리스트](#예외-처리-체크리스트)

---

## 예외 처리 개요

### 예외(Exception)란?

프로그램 실행 중 발생하는 **예상치 못한 오류 상황**을 의미합니다.

### 예외 처리가 필요한 이유

1. **프로그램 안정성**: 예외 발생 시 프로그램이 비정상 종료되는 것을 방지
2. **사용자 경험**: 사용자에게 명확한 오류 메시지 제공
3. **데이터 보호**: 잘못된 데이터로 인한 시스템 오류 방지
4. **디버깅 용이**: 문제 발생 위치와 원인 파악 용이

---

## 사용된 예외 종류

JavaAir 시스템에서 사용된 예외는 총 **6가지**입니다:

| 예외 종류 | 발생 위치 | 처리 방법 |
|----------|----------|----------|
| **NumberFormatException** | 문자열 → 숫자 변환 | try-catch |
| **InputMismatchException** | Scanner 잘못된 입력 | try-catch |
| **DateTimeException** | 날짜 형식 오류 | try-catch |
| **IOException** | 파일 입출력 오류 | try-catch |
| **FileNotFoundException** | 파일 없음 | try-catch |
| **InterruptedException** | Thread.sleep() | throws |

---

## 예외별 상세 설명

### 1. NumberFormatException

#### 발생 위치

**위치 1: FlightReservationMain - 메뉴 입력**
```java
public static void main(String[] args) {
    String menuStr = sc.next();
    int menu = -1;
    try {
        menu = Integer.parseInt(menuStr);  // ⚠️ 여기서 발생 가능
    } catch (NumberFormatException e) {
        menu = 9;  // 잘못된 입력은 default로 처리
    }
}
```

**위치 2: FlightManager - 항공편 선택**
```java
public void bookFlight() throws InterruptedException {
    for(;;){
        try {
            int bookNum = Integer.parseInt(sc.next());  // ⚠️ 여기서 발생 가능
            // ...
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        }
    }
}
```

**위치 3: FileC - 파일 업로드**
```java
public void upload() {
    while ((line = bufferedReader.readLine()) != null) {
        String[] flight = line.split("/");
        FlightManager.getFlights().add(
            new Flight(
                flight[0],
                flight[1],
                Integer.parseInt(flight[2]),  // ⚠️ 여기서 발생 가능
                Boolean.parseBoolean(flight[3])
            )
        );
    }
}
```

#### 발생 원인

**`Integer.parseInt()`** 메서드가 숫자가 아닌 문자열을 받을 때 발생합니다.

**발생 예시:**
```java
Integer.parseInt("123");     // ✅ 정상: 123 반환
Integer.parseInt("abc");      // ❌ NumberFormatException 발생
Integer.parseInt("12.5");     // ❌ NumberFormatException 발생
Integer.parseInt("");        // ❌ NumberFormatException 발생
Integer.parseInt(" 123 ");    // ❌ NumberFormatException 발생 (공백 포함)
```

#### 왜 처리해야 하는가?

1. **사용자 입력 오류 대응**
   - 사용자가 숫자 대신 문자 입력 가능
   - 예: 메뉴에서 "abc" 입력, 항공편 번호에 "가나다" 입력

2. **파일 데이터 오류 대응**
   - 파일 형식이 잘못되었을 때 대응
   - 예: `schedule.txt`에 "도쿄/14:30/abc/true" (가격이 숫자가 아님)

3. **프로그램 비정상 종료 방지**
   - 예외 처리 없으면 프로그램이 강제 종료됨
   - 사용자 경험 저하

#### 처리 방법

```java
try {
    int num = Integer.parseInt(input);
    // 정상 처리
} catch (NumberFormatException e) {
    System.out.println("숫자만 입력 가능합니다.");
    // 오류 메시지 출력 후 다시 입력받기
}
```

#### 실제 코드 예시

**FlightReservationMain:**
```java
try {
    menu = Integer.parseInt(menuStr);
} catch (NumberFormatException e) {
    menu = 9;  // default로 처리하여 switch문에서 무시
}
```

**FlightManager:**
```java
try {
    int bookNum = Integer.parseInt(sc.next());
    // 정상 처리
} catch (NumberFormatException e) {
    System.out.println("잘못된 입력입니다.");
    continue;  // 다시 입력받기
}
```

---

### 2. InputMismatchException

#### 발생 위치

**FlightManager - seatSelection() 메서드**
```java
private int seatSelection(Flight flight) {
    int seatNum = -1;
    while(true){
        try {
            int seatInt = sc.nextInt() - 1;  // ⚠️ 여기서 발생 가능
            // ...
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다.");
            sc.nextLine();  // 버퍼 비우기
        }
    }
}
```

#### 발생 원인

**`Scanner.nextInt()`** 메서드가 정수가 아닌 값을 받을 때 발생합니다.

**발생 예시:**
```java
Scanner sc = new Scanner(System.in);
sc.nextInt();  // 사용자가 "5" 입력 → ✅ 정상: 5 반환
sc.nextInt();  // 사용자가 "abc" 입력 → ❌ InputMismatchException
sc.nextInt();  // 사용자가 "12.5" 입력 → ❌ InputMismatchException
sc.nextInt();  // 사용자가 "5.0" 입력 → ❌ InputMismatchException
```

#### 왜 처리해야 하는가?

1. **사용자 입력 오류 대응**
   - 좌석 번호에 숫자 대신 문자 입력
   - 예: "5번" 대신 "five" 입력

2. **입력 버퍼 문제 해결**
   - 예외 발생 시 입력 버퍼에 잘못된 값이 남음
   - `sc.nextLine()`으로 버퍼 비우기 필요

3. **무한 루프 방지**
   - 예외 처리 없으면 같은 잘못된 값이 계속 읽힘
   - 프로그램이 멈추는 것처럼 보임

#### 처리 방법

```java
try {
    int seatInt = sc.nextInt() - 1;
    // 정상 처리
} catch (InputMismatchException e) {
    System.out.println("잘못된 입력입니다.");
    sc.nextLine();  // ⭐ 버퍼 비우기 필수!
}
```

#### 버퍼 비우기가 중요한 이유

```java
// 잘못된 코드
try {
    int seatInt = sc.nextInt();  // 사용자가 "abc" 입력
} catch (InputMismatchException e) {
    System.out.println("잘못된 입력입니다.");
    // sc.nextLine() 없음 ❌
}
// 다음 반복에서 sc.nextInt() 호출 시
// 여전히 "abc"가 버퍼에 남아있어서 같은 예외 발생
// 무한 루프 발생!
```

```java
// 올바른 코드
try {
    int seatInt = sc.nextInt();  // 사용자가 "abc" 입력
} catch (InputMismatchException e) {
    System.out.println("잘못된 입력입니다.");
    sc.nextLine();  // ✅ 버퍼 비우기
}
// 다음 반복에서 새로운 입력을 받을 수 있음
```

---

### 3. DateTimeException

#### 발생 위치

**FlightManager - passengerInfo() 메서드**
```java
private void passengerInfo(Flight flight) {
    System.out.print("생년월일(6자리):");
    try {
        int birthDate = Integer.parseInt(sc.next());
        Passenger p = new Passenger(name, birthDate);
        // ...
    } catch (DateTimeException e) {  // ⚠️ 여기서 처리
        System.out.println("생년월일을 6자리로 입력해주세요 ex)010225");
    }
}
```

**Passenger - man15() 메서드 내부**
```java
public boolean man15(Passenger p){
    int y = p.birthDate / 10000;
    int m = (p.birthDate % 10000) /100;
    int d = p.birthDate % 100;
    
    if(y > 0 && y <= 25){
        y = y + 2000;
    } else {
        y += 1900;
    }
    
    LocalDate birthDate2 = LocalDate.of(y, m, d);  // ⚠️ 여기서 발생 가능
    // ...
}
```

#### 발생 원인

**`LocalDate.of()`** 메서드가 유효하지 않은 날짜를 받을 때 발생합니다.

**발생 예시:**
```java
LocalDate.of(2024, 2, 30);   // ❌ 2월은 30일 없음
LocalDate.of(2024, 13, 1);   // ❌ 13월은 없음
LocalDate.of(2024, 0, 1);    // ❌ 0월은 없음
LocalDate.of(2024, 2, 0);     // ❌ 0일은 없음
LocalDate.of(2024, 2, 29);   // ✅ 정상 (윤년)
LocalDate.of(2023, 2, 29);   // ❌ 평년에는 2월 29일 없음
```

#### 실제 발생 시나리오

**사용자 입력:**
```
생년월일(6자리): 990230
```

**처리 과정:**
```java
int birthDate = 990230;
int y = 990230 / 10000 = 99;
int m = (990230 % 10000) / 100 = 2;
int d = 990230 % 100 = 30;

y = 99 + 1900 = 1999;

LocalDate.of(1999, 2, 30);  // ❌ DateTimeException 발생!
// 1999년 2월은 28일까지만 있음
```

#### 왜 처리해야 하는가?

1. **잘못된 날짜 입력 방지**
   - 사용자가 존재하지 않는 날짜 입력 가능
   - 예: 2월 30일, 13월 1일 등

2. **데이터 무결성 보장**
   - 유효하지 않은 날짜로 인한 계산 오류 방지
   - 나이 계산이 잘못될 수 있음

3. **사용자 피드백 제공**
   - 명확한 오류 메시지로 올바른 형식 안내

#### 처리 방법

```java
try {
    int birthDate = Integer.parseInt(sc.next());
    // 생년월일 분리 및 LocalDate 생성
    LocalDate birthDate2 = LocalDate.of(y, m, d);
} catch (DateTimeException e) {
    System.out.println("생년월일을 6자리로 입력해주세요 ex)010225");
}
```

#### 개선 가능한 부분

현재 코드는 `DateTimeException`을 잡지만, 실제로는 `LocalDate.of()` 호출이 `passengerInfo()` 내부가 아니라 `man15()` 내부에서 발생합니다.

**개선 방안:**
```java
private void passengerInfo(Flight flight) {
    try {
        int birthDate = Integer.parseInt(sc.next());
        Passenger p = new Passenger(name, birthDate);
        
        // 나이 확인 시 DateTimeException 발생 가능
        if(!p.man15(p) && flight.getInternationalFlight()){
            // ...
        }
    } catch (NumberFormatException e) {
        System.out.println("숫자만 입력 가능합니다.");
    } catch (DateTimeException e) {
        System.out.println("생년월일을 6자리로 입력해주세요 ex)010225");
    }
}
```

---

### 4. IOException

#### 발생 위치

**위치 1: FileC - ticketSaveFile() 메서드**
```java
public void ticketSaveFile(Map<String, Flight> reservationMap, String name) {
    try {
        File dir = new File("d:\\ticket");
        if (!dir.exists()) {
            dir.mkdir();  // ⚠️ IOException 발생 가능
        }
    
        File file = new File(dir, "ticket.txt");
        boolean isNewFile = file.createNewFile();  // ⚠️ IOException 발생 가능
    
        BufferedWriter bufferedWriter = new BufferedWriter(
            new FileWriter(file, true));  // ⚠️ IOException 발생 가능
        
        bufferedWriter.write(...);  // ⚠️ IOException 발생 가능
        bufferedWriter.flush();     // ⚠️ IOException 발생 가능
        bufferedWriter.close();     // ⚠️ IOException 발생 가능
        
    } catch (IOException e) {
        System.out.println("파일 저장 실패: ");
    }
}
```

**위치 2: FileC - upload() 메서드**
```java
public void upload() {
    try {
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), "UTF-8"));
        
        while ((line = bufferedReader.readLine()) != null) {  // ⚠️ IOException 발생 가능
            // ...
        }
        
        bufferedReader.close();  // ⚠️ IOException 발생 가능
        
    } catch (FileNotFoundException e) {
        System.out.println("schedule.txt 파일이 존재하지 않음");
    } catch (IOException e) {
        System.out.println("파일 읽기 실패");
    }
}
```

#### 발생 원인

파일 입출력 작업 중 발생하는 모든 오류를 포함합니다.

**발생 예시:**
- 파일 쓰기 권한 없음
- 디스크 공간 부족
- 파일이 다른 프로그램에서 사용 중
- 네트워크 드라이브 연결 끊김
- 파일 경로가 너무 김

#### 왜 처리해야 하는가?

1. **파일 시스템 오류 대응**
   - 디스크 공간 부족
   - 파일 권한 문제
   - 파일이 사용 중

2. **프로그램 안정성**
   - 파일 저장 실패해도 프로그램 계속 실행
   - 사용자에게 오류 메시지 제공

3. **데이터 손실 방지**
   - 파일 저장 실패 시 사용자에게 알림
   - 재시도 가능하도록 안내

#### 처리 방법

```java
try {
    // 파일 입출력 작업
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    writer.write(data);
    writer.close();
} catch (IOException e) {
    System.out.println("파일 저장 실패: " + e.getMessage());
    // 사용자에게 오류 알림
}
```

#### 실제 코드 예시

**ticketSaveFile():**
```java
try {
    File dir = new File("d:\\ticket");
    if (!dir.exists()) {
        dir.mkdir();  // 폴더 생성 실패 가능
    }
    
    File file = new File(dir, "ticket.txt");
    file.createNewFile();  // 파일 생성 실패 가능
    
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(file, true));  // 파일 열기 실패 가능
    
    bufferedWriter.write(...);  // 쓰기 실패 가능
    
} catch (IOException e) {
    System.out.println("파일 저장 실패: ");
}
```

---

### 5. FileNotFoundException

#### 발생 위치

**FileC - upload() 메서드**
```java
public void upload() {
    try {
        File file = new File("d:\\ticket\\schedule.txt");
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), "UTF-8"));  // ⚠️ 여기서 발생 가능
        
        // ...
        
    } catch (FileNotFoundException e) {
        System.out.println("schedule.txt 파일이 존재하지 않음");
    } catch (IOException e) {
        System.out.println("파일 읽기 실패");
    }
}
```

#### 발생 원인

**`FileInputStream`** 생성자나 **`FileReader`** 생성자가 존재하지 않는 파일을 열려고 할 때 발생합니다.

**발생 예시:**
```java
FileInputStream fis = new FileInputStream("없는파일.txt");
// ❌ FileNotFoundException 발생

File file = new File("없는파일.txt");
FileReader reader = new FileReader(file);
// ❌ FileNotFoundException 발생
```

#### 왜 처리해야 하는가?

1. **파일 존재 여부 확인**
   - 파일이 없을 때 명확한 메시지 제공
   - 사용자가 파일을 생성할 수 있도록 안내

2. **IOException과 구분**
   - 파일이 없는 경우와 파일 읽기 오류를 구분
   - 더 정확한 오류 메시지 제공

3. **사용자 경험 향상**
   - "파일이 없습니다" vs "파일 읽기 실패"
   - 사용자가 문제를 더 쉽게 파악

#### 처리 방법

```java
try {
    FileInputStream fis = new FileInputStream("schedule.txt");
    // 파일 읽기
} catch (FileNotFoundException e) {
    System.out.println("파일이 존재하지 않습니다.");
    // 파일 생성 안내 또는 기본값 사용
} catch (IOException e) {
    System.out.println("파일 읽기 중 오류 발생");
}
```

#### 실제 코드 예시

**upload():**
```java
try {
    File file = new File("d:\\ticket\\schedule.txt");
    BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(new FileInputStream(file), "UTF-8"));
    // 파일이 없으면 FileNotFoundException 발생
    
} catch (FileNotFoundException e) {
    System.out.println("schedule.txt 파일이 존재하지 않음");
    // 사용자에게 파일 생성 안내
} catch (IOException e) {
    System.out.println("파일 읽기 실패");
    // 기타 파일 읽기 오류
}
```

#### 예외 계층 구조

```
IOException (부모)
├── FileNotFoundException (자식)
└── 기타 IOException
```

**주의사항:**
- `FileNotFoundException`은 `IOException`의 자식 클래스
- catch 블록 순서가 중요함
- 자식 예외를 먼저 catch해야 함

```java
// ✅ 올바른 순서
catch (FileNotFoundException e) {  // 자식 먼저
    // ...
} catch (IOException e) {  // 부모 나중
    // ...
}

// ❌ 잘못된 순서
catch (IOException e) {  // 부모 먼저
    // FileNotFoundException도 여기서 잡힘
} catch (FileNotFoundException e) {  // 도달 불가
    // ...
}
```

---

### 6. InterruptedException

#### 발생 위치

**FlightManager - bookFlight() 메서드**
```java
public void bookFlight() throws InterruptedException {
    // ...
    System.out.println("예약중입니다.");
    Thread.sleep(2000);  // ⚠️ 여기서 발생 가능
    System.out.println("예약에 성공했습니다.");
    // ...
}
```

**FlightReservationMain - main() 메서드**
```java
public static void main(String[] args) {
    // ...
    case 2:
        try {
            fm.bookFlight();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    break;
}
```

#### 발생 원인

**`Thread.sleep()`** 메서드가 실행 중에 스레드가 인터럽트될 때 발생합니다.

**발생 시나리오:**
- 다른 스레드가 현재 스레드를 인터럽트
- 프로그램 종료 시그널
- 스레드 풀에서 작업 취소

#### 왜 처리해야 하는가?

1. **스레드 안전성**
   - 멀티스레드 환경에서 스레드 인터럽트 처리
   - 프로그램 안정성 보장

2. **정상 종료 보장**
   - 인터럽트 발생 시 정상적으로 처리
   - 데이터 손실 방지

3. **컴파일 오류 방지**
   - `Thread.sleep()`은 checked exception
   - 반드시 처리해야 함

#### 처리 방법

**방법 1: throws 선언**
```java
public void bookFlight() throws InterruptedException {
    Thread.sleep(2000);
    // 호출하는 쪽에서 처리
}
```

**방법 2: try-catch 처리**
```java
public void bookFlight() {
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        System.out.println("작업이 중단되었습니다.");
        Thread.currentThread().interrupt();  // 인터럽트 상태 복원
    }
}
```

#### 실제 코드 예시

**FlightManager:**
```java
public void bookFlight() throws InterruptedException {
    // ...
    Thread.sleep(2000);  // throws 선언으로 예외 전파
    // ...
}
```

**FlightReservationMain:**
```java
case 2:
    try {
        fm.bookFlight();  // InterruptedException 발생 가능
    } catch (InterruptedException e) {
        e.printStackTrace();  // 예외 처리
    }
    break;
```

#### 권장 처리 방법

```java
try {
    Thread.sleep(2000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();  // 인터럽트 상태 복원
    // 로그 기록 또는 사용자 알림
    return;  // 메서드 종료
}
```

---

## 예외 처리 패턴

### 1. try-catch 패턴

**기본 구조:**
```java
try {
    // 예외 발생 가능한 코드
} catch (예외타입 e) {
    // 예외 처리
}
```

**사용 예시:**
```java
// NumberFormatException 처리
try {
    int num = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("숫자만 입력 가능합니다.");
}

// InputMismatchException 처리
try {
    int seat = sc.nextInt();
} catch (InputMismatchException e) {
    System.out.println("잘못된 입력입니다.");
    sc.nextLine();  // 버퍼 비우기
}
```

### 2. throws 선언 패턴

**기본 구조:**
```java
public void method() throws 예외타입 {
    // 예외 발생 가능한 코드
    // 예외를 호출하는 쪽으로 전파
}
```

**사용 예시:**
```java
// InterruptedException 전파
public void bookFlight() throws InterruptedException {
    Thread.sleep(2000);
    // 호출하는 쪽에서 처리
}
```

### 3. 다중 catch 블록 패턴

**기본 구조:**
```java
try {
    // 예외 발생 가능한 코드
} catch (자식예외 e) {
    // 자식 예외 처리
} catch (부모예외 e) {
    // 부모 예외 처리
}
```

**사용 예시:**
```java
try {
    FileInputStream fis = new FileInputStream(file);
    // ...
} catch (FileNotFoundException e) {
    System.out.println("파일이 존재하지 않음");
} catch (IOException e) {
    System.out.println("파일 읽기 실패");
}
```

**주의사항:**
- 자식 예외를 먼저 catch해야 함
- 부모 예외가 먼저 있으면 자식 예외 catch 블록에 도달하지 않음

### 4. finally 블록 패턴

**기본 구조:**
```java
try {
    // 예외 발생 가능한 코드
} catch (예외타입 e) {
    // 예외 처리
} finally {
    // 항상 실행되는 코드 (리소스 정리 등)
}
```

**사용 예시:**
```java
BufferedWriter writer = null;
try {
    writer = new BufferedWriter(new FileWriter(file));
    writer.write(data);
} catch (IOException e) {
    System.out.println("파일 저장 실패");
} finally {
    if (writer != null) {
        try {
            writer.close();  // 항상 파일 닫기
        } catch (IOException e) {
            // 닫기 실패 처리
        }
    }
}
```

**Java 7+ try-with-resources (권장):**
```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    writer.write(data);
    // 자동으로 close() 호출됨
} catch (IOException e) {
    System.out.println("파일 저장 실패");
}
```

---

## 예외 처리 체크리스트

### 입력 관련 예외

- [ ] **NumberFormatException**
  - `Integer.parseInt()` 사용 시 처리
  - 사용자가 숫자가 아닌 값 입력 가능

- [ ] **InputMismatchException**
  - `Scanner.nextInt()` 사용 시 처리
  - `sc.nextLine()`으로 버퍼 비우기 필수

### 날짜 관련 예외

- [ ] **DateTimeException**
  - `LocalDate.of()` 사용 시 처리
  - 유효하지 않은 날짜 입력 방지

### 파일 관련 예외

- [ ] **FileNotFoundException**
  - 파일 읽기 시 처리
  - 파일 존재 여부 확인

- [ ] **IOException**
  - 모든 파일 입출력 작업 시 처리
  - 파일 쓰기 권한, 디스크 공간 등 확인

### 스레드 관련 예외

- [ ] **InterruptedException**
  - `Thread.sleep()` 사용 시 처리
  - `throws` 선언 또는 `try-catch` 처리

---

## 예외 처리 모범 사례

### 1. 구체적인 예외 처리

```java
// ❌ 나쁜 예
catch (Exception e) {
    System.out.println("오류 발생");
}

// ✅ 좋은 예
catch (NumberFormatException e) {
    System.out.println("숫자만 입력 가능합니다.");
} catch (FileNotFoundException e) {
    System.out.println("파일이 존재하지 않습니다.");
}
```

### 2. 의미 있는 오류 메시지

```java
// ❌ 나쁜 예
catch (IOException e) {
    System.out.println("오류");
}

// ✅ 좋은 예
catch (IOException e) {
    System.out.println("파일 저장 실패: " + e.getMessage());
    // 또는
    System.out.println("파일 저장 실패: 파일 권한을 확인하세요.");
}
```

### 3. 리소스 정리

```java
// ✅ try-with-resources 사용 (권장)
try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    writer.write(data);
} catch (IOException e) {
    System.out.println("파일 저장 실패");
}
// 자동으로 close() 호출됨
```

### 4. 버퍼 비우기 (Scanner)

```java
// ✅ InputMismatchException 처리 시
catch (InputMismatchException e) {
    System.out.println("잘못된 입력입니다.");
    sc.nextLine();  // 버퍼 비우기 필수!
}
```

---

## 예외 처리 흐름도

### NumberFormatException 처리 흐름

```
사용자 입력: "abc"
    ↓
Integer.parseInt("abc")
    ↓
NumberFormatException 발생
    ↓
catch 블록 실행
    ↓
오류 메시지 출력
    ↓
다시 입력받기 (continue)
```

### InputMismatchException 처리 흐름

```
사용자 입력: "five"
    ↓
sc.nextInt()
    ↓
InputMismatchException 발생
    ↓
catch 블록 실행
    ↓
오류 메시지 출력
    ↓
sc.nextLine()로 버퍼 비우기
    ↓
다시 입력받기 (while 루프)
```

### IOException 처리 흐름

```
파일 저장 시도
    ↓
디스크 공간 부족 또는 권한 없음
    ↓
IOException 발생
    ↓
catch 블록 실행
    ↓
오류 메시지 출력
    ↓
프로그램 계속 실행
```

---

## 학습 포인트

### 1. Checked Exception vs Unchecked Exception

**Checked Exception (컴파일 타임 체크):**
- 반드시 처리해야 함
- `throws` 선언 또는 `try-catch` 필수
- 예: `IOException`, `InterruptedException`

**Unchecked Exception (런타임 체크):**
- 처리하지 않아도 컴파일 가능
- 예: `NumberFormatException`, `InputMismatchException`, `DateTimeException`

### 2. 예외 계층 구조

```
Throwable
├── Error (시스템 오류)
└── Exception
    ├── RuntimeException (Unchecked)
    │   ├── NumberFormatException
    │   ├── InputMismatchException
    │   └── DateTimeException
    └── 기타 Exception (Checked)
        ├── IOException
        │   └── FileNotFoundException
        └── InterruptedException
```

### 3. 예외 전파

```java
// 메서드 1
public void method1() throws IOException {
    method2();  // IOException 전파
}

// 메서드 2
public void method2() throws IOException {
    FileInputStream fis = new FileInputStream("file.txt");
    // IOException 발생 가능
}

// 호출하는 쪽
try {
    method1();
} catch (IOException e) {
    // 처리
}
```

---

## 요약

### 예외 처리의 핵심

1. **예외는 예상치 못한 상황을 처리하는 것**
2. **사용자에게 명확한 오류 메시지 제공**
3. **프로그램이 비정상 종료되지 않도록 보호**
4. **데이터 무결성 보장**

### JavaAir에서의 예외 처리

| 예외 | 발생 위치 | 처리 이유 |
|------|----------|----------|
| NumberFormatException | 문자열→숫자 변환 | 사용자 입력 오류 대응 |
| InputMismatchException | Scanner 입력 | 잘못된 타입 입력 대응 |
| DateTimeException | 날짜 생성 | 유효하지 않은 날짜 방지 |
| IOException | 파일 입출력 | 파일 시스템 오류 대응 |
| FileNotFoundException | 파일 읽기 | 파일 없음 대응 |
| InterruptedException | Thread.sleep() | 스레드 인터럽트 처리 |

---

**작성일:** 2026-01-30  
**관련 파일:** JavaAir-항공예약시스템-완전정복.md
