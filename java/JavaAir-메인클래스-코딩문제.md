# JavaAir 항공 예약 시스템 - 메인 클래스 코딩 문제

## 문제

JavaAir 항공 예약 시스템의 메인 클래스를 작성하세요. 다음 요구사항을 만족하는 `FlightReservationMain` 클래스를 완성하세요.

### 요구사항

1. **패키지 선언**: `a0402.javaair` 패키지에 속해야 합니다.

2. **필요한 클래스 사용**:
   - `FlightManager` 클래스를 사용하여 항공편 관리 기능을 제공합니다.
   - `FileC` 클래스를 사용하여 파일 입출력 기능을 제공합니다.
   - `Scanner`를 사용하여 사용자 입력을 받습니다.

3. **프로그램 시작 시 출력**:
   - `FlightManager` 객체의 `airplane` 필드를 출력합니다.
   - 환영 메시지를 출력합니다.

4. **메뉴 시스템 구현**:
   - 무한 루프를 사용하여 메뉴를 반복 표시합니다.
   - 라벨이 붙은 루프를 사용하여 외부 루프를 탈출할 수 있도록 합니다.
   - 다음 메뉴를 제공합니다:
     - 1: 항공편 목록 조회
     - 2: 항공편 예매
     - 3: 예약 조회
     - 4: 티켓 저장
     - 5: 항공편 업로드
     - 0: 프로그램 종료

5. **입력 처리**:
   - 사용자 입력을 문자열로 받아 정수로 변환합니다.
   - 숫자가 아닌 입력이 들어오면 `NumberFormatException`을 처리하여 기본값(9)으로 설정합니다.
   - 입력 버퍼를 비우기 위해 `sc.nextLine()`을 사용합니다.

6. **메뉴별 기능 호출**:
   - case 1: `fm.displayFlightList("항공편목록")` 호출
   - case 2: `fm.bookFlight()` 호출 (InterruptedException 처리 필요)
   - case 3: `fm.checkReservation()` 호출
   - case 4: `fm.ticketSave()` 호출
   - case 5: `fc.upload()` 호출
   - case 0: 종료 메시지 출력 후 Scanner 닫기, 외부 루프 탈출

7. **예외 처리**:
   - `NumberFormatException`: 메뉴 입력이 숫자가 아닐 때 처리
   - `InterruptedException`: `bookFlight()` 메서드에서 발생할 수 있는 예외 처리

### 힌트

- 라벨이 붙은 루프: `Outter:while(true) { ... }`
- 라벨을 사용한 break: `break Outter;`
- switch-case 문을 사용하여 메뉴를 처리합니다.
- try-catch 문을 사용하여 예외를 처리합니다.

---

## 정답

```java
package a0402.javaair;

import java.util.Scanner;

public class FlightReservationMain {
    public static void main(String[] args) {
        FlightManager fm = new FlightManager();
        //FlightManager 객체가 생성 과 동시에 더미 데이터 들어갈 예정
        Scanner sc = new Scanner(System.in);
        FileC fc = new FileC();
        System.out.println(fm.airplane);
        System.out.println("==============JavaAir 에 오신걸 환영합니다.==============");

        Outter:while(true){
            System.out.println("1. 항공편 목록\n2. 항공편 예매\n3. 예약 조회\n4. 티켓 저장\n5. 항공편 업로드 \n0.종료\n");
            System.out.print("메뉴입력>");

            String menuStr = sc.next();
            sc.nextLine(); //버퍼 비우기

            int menu = -1;
            try {
                menu = Integer.parseInt(menuStr);
            } catch (NumberFormatException e) {
                menu = 9;
            }
            switch (menu) {
                case 1:
                    fm.displayFlightList("항공편목록");
                    break;
                case 2:
                    try {
                        fm.bookFlight();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    fm.checkReservation();
                    break;
                case 4:
                    fm.ticketSave();
                    break;
                case 5:
                    fc.upload();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    break Outter;            
                default:
                    break;
            }
        }
    }
}
```

---

## 채점 기준

| 항목 | 배점 | 설명 |
|------|------|------|
| 패키지 및 import | 10점 | 올바른 패키지 선언 및 필요한 import 문 |
| 객체 생성 | 10점 | FlightManager, Scanner, FileC 객체 생성 |
| 초기 출력 | 10점 | airplane 출력 및 환영 메시지 |
| 무한 루프 구조 | 15점 | 라벨이 붙은 무한 루프 구현 |
| 입력 처리 | 15점 | 문자열 입력, 정수 변환, 예외 처리, 버퍼 비우기 |
| switch-case 문 | 20점 | 모든 메뉴 케이스 올바르게 구현 |
| 예외 처리 | 15점 | NumberFormatException, InterruptedException 처리 |
| 프로그램 종료 | 5점 | case 0에서 올바른 종료 처리 |

**총점: 100점**

---

## 핵심 포인트 설명

### 1. 라벨이 붙은 루프
```java
Outter:while(true) {
    // ...
    break Outter;  // 외부 루프 탈출
}
```
- 중첩된 루프에서 외부 루프를 탈출하기 위해 사용
- `break Outter;`로 라벨이 붙은 루프를 탈출할 수 있음

### 2. 입력 버퍼 비우기
```java
String menuStr = sc.next();
sc.nextLine(); // 버퍼 비우기
```
- `sc.next()` 후 남은 개행 문자를 제거하기 위해 필요
- 다음 입력을 정상적으로 받기 위해 필수

### 3. 예외 처리
```java
try {
    menu = Integer.parseInt(menuStr);
} catch (NumberFormatException e) {
    menu = 9;  // default로 처리
}
```
- 숫자가 아닌 입력이 들어올 때 프로그램이 중단되지 않도록 처리
- 잘못된 입력은 default 케이스로 처리

### 4. InterruptedException 처리
```java
try {
    fm.bookFlight();
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
- `bookFlight()` 메서드 내부에서 `Thread.sleep()`을 사용하므로 발생할 수 있는 예외
- 반드시 처리해야 함
