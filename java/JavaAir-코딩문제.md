# JavaAir 항공 예약 시스템 - 코딩 문제 모음

## 문제 1: Passenger 클래스 구현

### 요구사항

승객 정보를 관리하는 `Passenger` 클래스를 작성하세요.

1. **패키지**: `a0402.javaair`

2. **필드 (모두 private)**:
   - `String name` - 이름
   - `int birthDate` - 생년월일 (6자리: YYMMDD 형식)
   - `String pw` - 비밀번호
   - `String seat` - 좌석 번호

3. **생성자 (2개)**:
   - 생성자 1: `Passenger(String name, int birthDate)` - 이름과 생년월일만 받는 생성자
   - 생성자 2: `Passenger(String name, int birthDate, String pw)` - 이름, 생년월일, 비밀번호를 받는 생성자

4. **Getter/Setter 메서드**: 모든 필드에 대한 getter와 setter 메서드 작성

5. **`man15(Passenger p)` 메서드**:
   - 만 15세 이상인지 확인하는 메서드
   - 생년월일을 연/월/일로 분리
   - 연도 변환 로직:
     - 연도가 0보다 크고 25 이하이면 2000년대 (y + 2000)
     - 그 외는 1900년대 (y + 1900)
   - `LocalDate`와 `Period`를 사용하여 나이 계산
   - 만 15세 이상이면 `true`, 미만이면 `false` 반환

### 힌트
- `java.time.LocalDate`와 `java.time.Period` import 필요
- 생년월일 분리: `birthDate / 10000` (연도), `(birthDate % 10000) / 100` (월), `birthDate % 100` (일)
- `Period.between(birthDate2, currentDate).getYears()`로 나이 계산

---

## 문제 2: Flight 클래스 구현

### 요구사항

항공편 정보를 관리하는 `Flight` 클래스를 작성하세요.

1. **패키지**: `a0402.javaair`

2. **필드 (모두 private)**:
   - `String destination` - 목적지
   - `String time` - 출발시간
   - `int price` - 가격
   - `DecimalFormat priceFormat` - 가격 포맷터 (초기값: `new DecimalFormat("#,###원")`)
   - `Boolean internationalFlight` - 국제선 여부
   - `ArrayList<String> seats` - 좌석 목록

3. **생성자**:
   - `Flight(String destination, String time, int price, Boolean internationalFlight)`
   - 생성자에서 좌석 목록을 초기화 (1번부터 20번까지 문자열로 저장)
   - 예: `seats.add("1")`, `seats.add("2")`, ... `seats.add("20")`

4. **Getter/Setter 메서드**: 모든 필드에 대한 getter와 setter 메서드 작성

5. **`toString()` 메서드 (오버라이딩)**:
   - 가격을 포맷터로 포맷팅하여 반환
   - 형식: `. 목적지 : [목적지], 출발시간 : [시간], 가격 : [포맷된 가격]`
   - 예: `. 목적지 : Jeju, 출발시간 : 11:55, 가격 : 78,000원`

6. **`seatToString()` 메서드**:
   - 좌석 배치도를 출력하는 메서드
   - 좌석을 4개씩 그룹화하여 출력
   - 형식: `|   [좌석1]        [좌석2][좌석3]      [좌석4]   |`
   - 반복문에서 `i += 4`로 4칸씩 건너뛰기
   - `seats.size()-3`까지 반복

### 힌트
- `java.text.DecimalFormat` import 필요
- `java.util.ArrayList` import 필요
- 좌석 초기화: `for(int i = 1; i <= 20; i++) { seats.add(i+""); }`
- 좌석 출력: `System.out.printf()` 사용

---

## 문제 3: FlightManager 클래스 구현

### 요구사항

항공편 예약을 관리하는 `FlightManager` 클래스를 작성하세요.

1. **패키지**: `a0402.javaair`

2. **필드 (static)**:
   - `private static ArrayList<Flight> flights` - 항공편 목록
   - `private static ArrayList<Passenger> passengers` - 승객 목록
   - `private static Map<String, Flight> reservationMap` - 예약 정보 (이름 → 항공편)
   - `private static FileC fc` - 파일 처리 객체
   - `Scanner sc` - 입력 스캐너

3. **생성자**:
   - `flights` ArrayList 초기화
   - 더미 데이터 3개 추가:
     - `new Flight("제주","11:55", 78000, false)`
     - `new Flight("이스탄불","17:10",1200000,true)`
     - `new Flight("방콕","21:35",280000,true)`
   - `passengers` ArrayList 초기화
   - `reservationMap` HashMap 초기화
   - 테스트 데이터 추가: 첫 번째 항공편을 "테스트"라는 이름으로 예약

4. **`displayFlightList(String str)` 메서드**:
   - 항공편 목록을 출력
   - 번호와 함께 각 항공편 정보 출력 (Flight의 toString() 사용)

5. **`bookFlight()` 메서드**:
   - 무한 루프로 항공편 예매 처리
   - 항공편 목록 출력 후 선택받기
   - 유효성 검사 (1 이상, flights.size() 이하)
   - 국제선이면 나이 확인 (만 15세 이상)
   - 승객 정보 입력받기
   - 좌석 선택하기
   - 예약 완료 후 `reservationMap`에 저장
   - `InterruptedException` throws 선언

6. **`seatSelection(Flight flight)` 메서드 (private)**:
   - 좌석 선택 처리
   - 무한 루프로 유효한 좌석 선택까지 반복
   - 좌석 번호 입력 (1~20)
   - 배열 인덱스로 변환 (`seatInt - 1`)
   - 예약 여부 확인 ("XX"인지)
   - 예약 가능하면 "XX"로 표시
   - 선택된 좌석 번호 반환 (배열 인덱스)

7. **`passengerInfo(Flight flight)` 메서드 (private)**:
   - 승객 정보 입력받기
   - 이름, 생년월일 입력
   - 국제선이고 만 15세 미만이면 예약 거절
   - 비밀번호 입력받아 승객 생성 및 추가

8. **`checkReservation()` 메서드**:
   - 예약 조회 기능
   - `search()`로 승객 검색
   - `checkPassword()`로 비밀번호 확인

9. **`checkPassword(int index)` 메서드 (private)**:
   - 비밀번호 확인
   - 무한 루프로 비밀번호 일치할 때까지 반복
   - 일치하면 티켓 정보 출력

10. **`search(String str)` 메서드 (private)**:
    - 이름으로 승객 검색
    - 찾으면 인덱스 반환, 못 찾으면 -1 반환

11. **`ticketPrint(Map<String, Flight> reservationMap, String name)` 메서드**:
    - 티켓 정보를 문자열로 반환
    - 좌석 번호는 배열 인덱스 + 1로 계산

12. **`ticketSave()` 메서드**:
    - 티켓 파일 저장 기능
    - 승객 검색 → 비밀번호 확인 → 파일 저장

13. **Static getter 메서드**:
    - `getFlights()` - flights 반환
    - `getReservationMap()` - reservationMap 반환

### 힌트
- `Thread.sleep(2000)` 사용 시 `InterruptedException` 처리
- 좌석 번호와 배열 인덱스 변환 주의
- `seatNum = seatInt;`로 반환값 저장 (변수 스코프 문제)

---

## 문제 4: FileC 클래스 구현

### 요구사항

파일 입출력을 처리하는 `FileC` 클래스를 작성하세요.

1. **패키지**: `a0402.javaair`

2. **필드**:
   - `private FlightManager fm` - FlightManager 객체

3. **`ticketSaveFile(Map<String, Flight> reservationMap, String name)` 메서드**:
   - 티켓 정보를 파일에 저장
   - `d:\ticket` 폴더가 없으면 생성
   - `ticket.txt` 파일이 없으면 생성
   - **append 모드**로 파일 열기 (`FileWriter(file, true)`)
   - 기존 파일이 있으면 개행 후 추가
   - `BufferedWriter`를 사용하여 파일 작성
   - `flush()` 후 `close()` 호출
   - `IOException` 처리

4. **`upload()` 메서드**:
   - `d:\ticket\schedule.txt` 파일 읽기
   - **UTF-8 인코딩**으로 파일 읽기 (`InputStreamReader`, `FileInputStream` 사용)
   - 각 줄을 "/"로 분리하여 항공편 정보 추출
   - `Flight` 객체 생성하여 `FlightManager.getFlights()`에 추가
   - 파일 형식: `목적지/출발시간/가격/국제선여부`
   - 예: `도쿄/14:30/350000/true`
   - `FileNotFoundException`, `IOException` 처리

### 힌트
- `FileWriter(file, true)` - append 모드
- `InputStreamReader(new FileInputStream(file), "UTF-8")` - UTF-8 인코딩
- `line.split("/")` - 문자열 분리
- `Boolean.parseBoolean()` - 문자열을 boolean으로 변환

---

## 문제 5: FlightReservationMain 클래스 구현

### 요구사항

프로그램의 진입점이 되는 `FlightReservationMain` 클래스를 작성하세요.

1. **패키지**: `a0402.javaair`

2. **main 메서드**:
   - `FlightManager` 객체 생성
   - `Scanner` 객체 생성
   - `FileC` 객체 생성
   - `fm.airplane` 출력 (비행기 아스키 아트)
   - 환영 메시지 출력

3. **메뉴 시스템**:
   - **라벨이 붙은 무한 루프** 사용 (`Outter:while(true)`)
   - 메뉴 출력:
     - 1. 항공편 목록
     - 2. 항공편 예매
     - 3. 예약 조회
     - 4. 티켓 저장
     - 5. 항공편 업로드
     - 0. 종료

4. **입력 처리**:
   - 문자열로 입력받기
   - `sc.nextLine()`으로 버퍼 비우기
   - `Integer.parseInt()`로 정수 변환
   - `NumberFormatException` 처리 (기본값 9로 설정)

5. **switch-case 문**:
   - case 1: `fm.displayFlightList("항공편목록")`
   - case 2: `fm.bookFlight()` (InterruptedException 처리)
   - case 3: `fm.checkReservation()`
   - case 4: `fm.ticketSave()`
   - case 5: `fc.upload()`
   - case 0: 종료 메시지 출력, Scanner 닫기, `break Outter;`
   - default: break

### 힌트
- 라벨이 붙은 루프: `Outter:while(true) { ... }`
- 라벨을 사용한 break: `break Outter;`
- 입력 버퍼 비우기 필수

---

## 정답 파일

### Passenger.java
```java
package a0402.javaair;

import java.time.LocalDate;
import java.time.Period;

public class Passenger {
    private String name;
    private int birthDate;
    private String pw;
    private String seat;
    
    public Passenger(String name, int birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Passenger(String name, int birthDate, String pw) {
        this.name = name;
        this.birthDate = birthDate;
        this.pw = pw;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getSeat() {
        return seat;
    }
    public void setSeat(String seat) {
        this.seat = seat;
    }
    
    public boolean man15(Passenger p){
        int y = p.birthDate / 10000;
        int m = (p.birthDate % 10000) /100;
        int d = p.birthDate % 100;

        if(y > 0 && y <= 25){
            y = y +2000;
        } else {
            y += 1900;
        }
        
        LocalDate birthDate2 = LocalDate.of(y, m, d);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate2, currentDate).getYears();
        return age > 15;
    }
}
```

### Flight.java
```java
package a0402.javaair;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Flight {
    private String destination;
    private String time;
    private int price;
    private DecimalFormat priceFormat = new DecimalFormat("#,###원");
    private Boolean internationalFlight;
    private ArrayList<String> seats;
    
    public Flight(String destination, String time, int price, Boolean internationalFlight) {
        this.destination = destination;
        this.time = time;
        this.price = price;
        this.internationalFlight = internationalFlight;
        seats = new ArrayList<String>();
        for(int i = 1; i <= 20; i++){
            seats.add(i+"");
        }
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public DecimalFormat getPriceFormat() {
        return priceFormat;
    }
    public void setPriceFormat(DecimalFormat priceFormat) {
        this.priceFormat = priceFormat;
    }
    public Boolean getInternationalFlight() {
        return internationalFlight;
    }
    public void setInternationalFlight(Boolean internationalFlight) {
        this.internationalFlight = internationalFlight;
    }
    public ArrayList<String> getSeats() {
        return seats;
    }
    public void setSeats(ArrayList<String> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        String priceComma = priceFormat.format(price);
        return ". "+"목적지 : " + destination + ", 출발시간 : " + time + ", 가격 : " + priceComma;
    }

    public void seatToString(){
        for(int i = 0; i < seats.size()-3; i+=4){
            System.out.printf("|   [%2s]\t\t[%2s][%2s]\t\t[%2s]   |\n", seats.get(i),seats.get(i+1),seats.get(i+2),seats.get(i+3));
        }
    }
}
```

### FlightManager.java
```java
package a0402.javaair;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class FlightManager {
    private static ArrayList<Flight> flights;
    private static ArrayList<Passenger> passengers;
    private static Map<String, Flight> reservationMap = new HashMap<>();
    private static FileC fc = new FileC();
    Scanner sc = new Scanner(System.in);
    
    public FlightManager(){
        flights = new ArrayList<>();
        flights.add(new Flight("제주","11:55", 78000, false));
        flights.add(new Flight("이스탄불","17:10",1200000,true));
        flights.add(new Flight("방콕","21:35",280000,true));
        passengers = new ArrayList<>();
        Flight sf = flights.get(0);
        reservationMap.put("테스트", sf);
    }
    
    public String airplane = "                       |                      \n" + "                      _|_                     \n" + "                    /_____\\                  \n" + "                   /oo   oo\\                 \n" + " \\_________________\\       /_________________/\n" + "  `-------|---|-----\\_____/-----|---|-------'\n" + "         ( ) ( )  O|OOo|oOO|O  ( ) ( )   \n";
    
    public void displayFlightList(String str) {
        System.out.println("=========================== "+str+ " =========================" );
        int count = 1;
        for(Flight flight: flights){
            System.out.println(count + ""+ flight);
            count++;
        }
        System.out.println("============================================================");
    }
    
    public void bookFlight() throws InterruptedException {
        for(;;){
            displayFlightList("항공편예매");
            System.out.print("예매할 항공편 입력 > ");
            try {
                int bookNum = Integer.parseInt(sc.next());
                if(bookNum > flights.size() || bookNum < 1){
                    System.out.println("잘못되 입력입니다.");
                    continue;
                }
                System.out.println("선택한 항공편");
                System.out.println("============================================================");
                System.out.println(bookNum + "" + flights.get(bookNum-1));
                System.out.println("============================================================");
                Flight sf =  flights.get(bookNum-1);
                if(flights.get(bookNum-1).getInternationalFlight()){
                    System.out.println("국제선은 만15세이상 예매가능");
                    passengerInfo(sf);
                }else{
                    passengerInfo(sf);
                }
                if(passengers != null && !passengers.isEmpty()){
                    String seatNum  = Integer.toString(seatSelection(sf));
                    passengers.get(passengers.size()-1).setSeat(seatNum);
                    System.out.println("예약중입니다.");
                    Thread.sleep(2000);
                    System.out.println("==================================================");
                    System.out.println("예약에 성공했습니다.");
                    System.out.println("[" + passengers.get(passengers.size()-1).getName()+"] 님의 예약정보");
                    System.out.println(bookNum + "" + sf);
                    System.out.println("==================================================");
                    System.out.println("잠시후 메인 화면으로 이동합니다.");
                    Thread.sleep(2000);
                    reservationMap.put(passengers.get(passengers.size()-1).getName(),sf);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    
    private int seatSelection(Flight flight) {
        int seatNum =-1;
        while(true){
            try {
                System.out.println("========================================");
                flight.seatToString();
                System.out.println("좌선번호를 선택하세요");
                System.out.print("선택>");
                int seatInt= sc.nextInt() - 1;
                sc.nextLine();
                if(seatInt+1 < 1 || seatInt+1 > 20){
                    System.out.println("존재하지 않는 좌석입니다.");
                }else if(flight.getSeats().get(seatInt).equals("XX")){
                    System.out.println("이미 예약된 좌석입니다.");
                }else{
                    flight.getSeats().set(seatInt, "XX");
                    System.out.println("좌석 선택이 완료되었습니다.");
                    seatNum = seatInt;
                    break;
                }
            } catch (InputMismatchException e) {
                 System.out.println("잘못된 입력입니다.");
                 sc.nextLine();
            }
        }
        return seatNum;
    }
    
    private void passengerInfo(Flight flight) {
        System.out.println("예매자 정보를 입력하세요");
        System.out.print("이름 : ");
        String name = sc.next();
        System.out.printf("생년월일(6자리):");
        try {
            int birthDate = Integer.parseInt(sc.next());
            Passenger p = new Passenger(name, birthDate);
            if(!p.man15(p)  && flight.getInternationalFlight()){
                System.out.println("만 15미만은 국제선 예약불가입니다.");
            }else{
                System.out.println("결제 비밀 번호");
                String pw = sc.next();
                p = new Passenger(name,birthDate,pw);
                passengers.add(p);
            }
        } catch (DateTimeException e) {
            System.out.println("생년월일을 6자리로 입력해주세요 ex)010225");
        }
    }
    
    public void checkReservation() {
        int index = search("예약확인");
        checkPassword(index);
    }

    private void checkPassword(int index) {
        for(;;){
            if(index !=-1){
                System.out.println("결제 비밀번호");
                String pw = sc.next();
                System.out.println();
                if(passengers.get(index).getPw().equals(pw)){
                    System.out.println("비밀번호가 일치합니다.");
                    System.out.println(ticketPrint(reservationMap, passengers.get(index).getName()));
                    break;
                }
            }
        }
    }
    
    String ticketPrint(Map<String, Flight> reservationMap, String name) {
       int index = -1;
        if(passengers != null){
            for(int i = 0; i < passengers.size();i++){
                if(passengers.get(i).getName().equals(name)){
                    index = i;
                }
            }
        }
        int seat = Integer.parseInt(passengers.get(index).getSeat())+1;
        return  "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n\n" +
                "\t" + name + "님의 티켓정보" +
                "| 좌석 : " + seat + "번\n"+
                "." + reservationMap.get(name) + "\n\n" +
                "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";
    }
    
    private int search(String str) {
        System.out.println("===================== " + str + " =====================");
        System.out.print("예약자 이름: ");
        String name = sc.next();
        sc.nextLine();
        int index = -1;
        if(passengers != null){
            for(int i = 0; i < passengers.size();i++){
                if(passengers.get(i).getName().equals(name)){
                    index = i;
                }
            }
        }
        return index;
    }
    
    public void ticketSave() {
        int index = search("티켓조회");
        checkPassword(index);
        fc.ticketSaveFile(reservationMap, passengers.get(index).getName());
    }
    
    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    public static Map<String, Flight> getReservationMap() {
        return reservationMap;
    }
}
```

### FileC.java
```java
package a0402.javaair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class FileC {
    private FlightManager fm = new FlightManager();

    public void ticketSaveFile(Map<String, Flight> reservationMap, String name) {
        try {
            File dir = new File("d:\\ticket");
            if (!dir.exists()) {
                dir.mkdir();
            }
    
            File file = new File(dir, "ticket.txt");
            boolean isNewFile = file.createNewFile();
    
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    
            if (file.canWrite()) {
                if (!isNewFile) {
                    bufferedWriter.newLine();
                }
                bufferedWriter.write(fm.ticketPrint(reservationMap, name));
                bufferedWriter.flush();
                System.out.println("파일 저장 성공");
            }
    
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("파일 저장 실패: ");
        }
    }

    public void upload() {
        try {
            File file = new File("d:\\ticket\\schedule.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String line;
            System.out.println("===============================================================");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] flight = line.split("/");
                FlightManager.getFlights().add(new Flight(flight[0], flight[1], Integer.parseInt(flight[2]), Boolean.parseBoolean(flight[3])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("schedule.txt 파일이 존재하지 않음");
        } catch (IOException e) {
            System.out.println("파일 읽기 실패");
        }
    }
}
```

### FlightReservationMain.java
```java
package a0402.javaair;

import java.util.Scanner;

public class FlightReservationMain {
    public static void main(String[] args) {
        FlightManager fm = new FlightManager();
        Scanner sc = new Scanner(System.in);
        FileC fc = new FileC();
        System.out.println(fm.airplane);
        System.out.println("==============JavaAir 에 오신걸 환영합니다.==============");

        Outter:while(true){
            System.out.println("1. 항공편 목록\n2. 항공편 예매\n3. 예약 조회\n4. 티켓 저장\n5. 항공편 업로드 \n0.종료\n");
            System.out.print("메뉴입력>");

            String menuStr = sc.next();
            sc.nextLine();

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

| 문제 | 배점 | 주요 평가 항목 |
|------|------|---------------|
| 문제 1: Passenger | 20점 | 생성자, getter/setter, man15() 메서드 |
| 문제 2: Flight | 20점 | 생성자, toString(), seatToString() |
| 문제 3: FlightManager | 30점 | 예약 로직, 좌석 선택, 예외 처리 |
| 문제 4: FileC | 15점 | 파일 입출력, append 모드, UTF-8 인코딩 |
| 문제 5: FlightReservationMain | 15점 | 메뉴 시스템, 라벨 루프, 예외 처리 |

**총점: 100점**

---

## 핵심 개념 정리

### 1. 생년월일 처리
- 6자리 형식: YYMMDD
- 연도 변환: 00~25는 2000년대, 그 외는 1900년대
- `LocalDate`와 `Period`로 나이 계산

### 2. 좌석 관리
- 좌석 번호: 1~20 (사용자)
- 배열 인덱스: 0~19 (내부 처리)
- 예약 표시: "XX"

### 3. 파일 입출력
- Append 모드: `FileWriter(file, true)`
- UTF-8 인코딩: `InputStreamReader(new FileInputStream(file), "UTF-8")`

### 4. 예외 처리
- `NumberFormatException`: 숫자 변환 실패
- `InputMismatchException`: 잘못된 입력 타입
- `InterruptedException`: Thread.sleep() 관련
- `IOException`: 파일 입출력 관련

### 5. 컬렉션
- `ArrayList`: 순서가 있는 목록
- `HashMap`: 키-값 쌍으로 빠른 검색

### 6. 라벨이 붙은 루프
- `Outter:while(true) { ... }`
- `break Outter;`로 외부 루프 탈출
