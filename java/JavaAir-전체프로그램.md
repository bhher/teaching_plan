# JavaAir 항공 예약 시스템 - 전체 프로그램

## 목차

1. [시스템 개요](#시스템-개요)
2. [프로젝트 구조](#프로젝트-구조)
3. [전체 소스 코드](#전체-소스-코드)
   - [Passenger.java](#passengerjava)
   - [Flight.java](#flightjava)
   - [FlightManager.java](#flightmanagerjava)
   - [FileC.java](#filecjava)
   - [FlightReservationMain.java](#flightreservationmainjava)
4. [실행 방법](#실행-방법)
5. [주요 기능](#주요-기능)

---

## 시스템 개요

**JavaAir**는 항공편 예약을 관리하는 콘솔 기반 Java 애플리케이션입니다.

### 주요 기능

1. ✅ 항공편 목록 조회
2. ✅ 항공편 예매 (국내선/국제선)
3. ✅ 예약 조회
4. ✅ 티켓 파일 저장
5. ✅ 항공편 일정 업로드

### 시스템 특징

- **국제선 제한**: 만 15세 이상만 예약 가능
- **좌석 선택**: 20개 좌석 중 선택
- **비밀번호 인증**: 예약 조회 시 비밀번호 확인
- **파일 입출력**: 예약 정보 파일 저장 및 일정 업로드

---

## 프로젝트 구조

```
a0402.javaair
├── Passenger.java          (승객 정보 관리)
├── Flight.java             (항공편 정보 관리)
├── FlightManager.java      (항공편 예약 관리)
├── FileC.java              (파일 입출력 처리)
└── FlightReservationMain.java (메인 클래스)
```

---

## 전체 소스 코드

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
    //15세 이상만 국제선 예약이 가능
    public boolean man15(Passenger p){
        int y = p.birthDate / 10000;// 831016이라면 83만 남음
        int m = (p.birthDate % 10000) /100; // 1016에 100나눠서 10만 남음
        int d = p.birthDate % 100; //16만 남음

        if(y > 0 && y <= 25){
            y = y +2000;
        } else {
            y += 1900;
        }
        //1983/10/16
        LocalDate birthDate2 = LocalDate.of(y, m, d);//생년월일
        LocalDate currentDate = LocalDate.now();//오늘 년월일
        int age = Period.between(birthDate2, currentDate).getYears();//생년월일과 오늘의 년월일을 비교해서 연도만 빼서 연령을 구함
        return age > 15;
    }
    /*
        public boolean isEligibleForInternationalFlight() {
            int y = birthDate / 10000;
            int m = (birthDate % 10000) / 100;
            int d = birthDate % 100;

            y = (y > 0 && y <= 24) ? y + 2000 : y + 1900;

            LocalDate birthDate2 = LocalDate.of(y, m, d);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate2, currentDate).getYears();

            return age > 15;
        }
     */
}
```

**주요 메서드:**
- `man15(Passenger p)`: 만 15세 이상인지 확인하는 메서드
  - 생년월일을 연/월/일로 분리
  - 연도 변환 (00~25는 2000년대, 그 외는 1900년대)
  - `LocalDate`와 `Period`로 나이 계산

---

### Flight.java

```java
package a0402.javaair;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Flight {
    //목적지, 비행시간, 금액
    private String destination;
    private String time;
    private int price;
    private DecimalFormat priceFormat = new DecimalFormat("#,###원");
    private Boolean internationalFlight; //국제여부
    private ArrayList<String> seats; //좌석
    
    public Flight(String destination, String time, int price, Boolean internationalFlight) {
        this.destination = destination;
        this.time = time;
        this.price = price;
        this.internationalFlight = internationalFlight;
        seats = new ArrayList<String>();
        for(int i = 1; i <= 20; i++){
            seats.add(i+"");//숫자를 문자열로 넣기위해""한거임
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

    //좌석정보 출력(좌석을 4개씩 출력하여 가로로 배열)
    public void seatToString(){
        for(int i = 0; i < seats.size()-3; i+=4){
            System.out.printf("|   [%2s]\t\t[%2s][%2s]\t\t[%2s]   |\n", seats.get(i),seats.get(i+1),seats.get(i+2),seats.get(i+3));
        }
    }
}

/* 
    |   [ 1]        [ 2][ 3]      [ 4]   |
    |   [ 5]        [ 6][ 7]      [ 8]   |
    |   [ 9]        [10][11]      [12]   |
    |   [13]        [14][15]      [16]   |
    |   [17]        [18][19]      [20]   |
*/
```

**주요 메서드:**
- `toString()`: 항공편 정보를 문자열로 반환 (가격 포맷팅 포함)
- `seatToString()`: 좌석 배치도를 출력 (4개씩 그룹화)

---

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
    private static ArrayList<Flight> flights; //항공편 정보를 저장 ArrayList
    private static ArrayList<Passenger> passengers; //예약된 승객정보

    //승객을 키로하고, 예약된 항공편을 값으로 가지는 Map
    private static Map<String, Flight> reservationMap = new HashMap<>();

    private static FileC fc = new FileC(); //파일 관련작업
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
        //항공편 목록이 출력 -> flight 에서 toString 이용해서
        System.out.println("=========================== "+str+ " =========================" );
        int count = 1;
        for(Flight flight: flights){
            System.out.println(count + ""+ flight);
            count++;
        }
        System.out.println("============================================================");

    }
    public void bookFlight() throws InterruptedException {
        //국제선 - 사용자이름, 생년월일 받아서 나이확인하고 만 15미만 예약거절
        for(;;){//무한루프
            displayFlightList("항공편예매");
            System.out.print("예매할 항공편 입력 > ");
            try {
                int bookNum = Integer.parseInt(sc.next());
                if(bookNum > flights.size() || bookNum < 1){
                    //예약할 수 있는 목록의 갯수보다 크거나 목록이 없으면
                    System.out.println("잘못되 입력입니다.");
                    continue;
                    //유요하지 않은 입력은 받은 경우 루프 다음을 반복
                    //사용자에게 올바른 입력을 다시 요청
                }
                System.out.println("선택한 항공편");
                System.out.println("============================================================");
                System.out.println(bookNum + "" + flights.get(bookNum-1));
                System.out.println("============================================================");
                Flight sf =  flights.get(bookNum-1);
                if(flights.get(bookNum-1).getInternationalFlight()){//국제선이면
                    System.out.println("국제선은 만15세이상 예매가능");
                    passengerInfo(sf);
                }else{
                    passengerInfo(sf);
                }
                if(passengers != null && !passengers.isEmpty()){
                    String seatNum  = Integer.toString(seatSelection(sf)); //좌석
                    passengers.get(passengers.size()-1).setSeat(seatNum);
                    // 현재 passengers 리스트에서 가장 마지막에 추가된 승객 좌석 번호를 설정
                    System.out.println("예약중입니다.");
                    Thread.sleep(2000); //2초후 실행
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
    private int seatSelection(Flight flight) { //좌석예약
        int seatNum =-1;
        while(true){
            try {
                System.out.println("========================================");
                flight.seatToString(); //빈좌석이 보이는 print
                System.out.println("좌선번호를 선택하세요");
                System.out.print("선택>");
                int seatInt= sc.nextInt() - 1;
                sc.nextLine();
                if(seatInt+1 < 1 || seatInt+1 > 20){//좌석 1~20
                    System.out.println("존재하지 않는 좌석입니다.");
                }else if(flight.getSeats().get(seatInt).equals("XX")){
                    System.out.println("이미 예약된 좌석입니다.");
                }else{
                    // 좌석이 비어있으면
                    flight.getSeats().set(seatInt, "XX"); //좌석수정
                    //"XX"로 좌석을 표시
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
    private void passengerInfo(Flight flight) {// 사용자 입력해서 예약
        System.out.println("예매자 정보를 입력하세요");
        System.out.print("이름 : ");
        String name = sc.next();
        System.out.printf("생년월일(6자리):");
        try {
            int birthDate = Integer.parseInt(sc.next());
            Passenger p = new Passenger(name, birthDate);
            if(!p.man15(p)  && flight.getInternationalFlight()){
                //왼쪽 과 오른쪽 true 이여야만 실행 - 15미만이고 국제선이면 
                System.out.println("만 15미만은 국제선 예약불가입니다.");
            }else{
                System.out.println("결제 비밀 번호");
                String pw = sc.next();
                p = new Passenger(name,birthDate,pw);
                passengers.add(p); //항공 예약 명단에 추가
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
                String pw = sc.next();//비밀번호 키보드 입력
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
        //reservationMap("이름정보", Flight("항공권명","항공시간",가격,"국제여부"))
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
    //항공편 목록을 외부에서 접근하는 getter메소드

    public static Map<String, Flight> getReservationMap() {
        return reservationMap;
    }
    //예약정보를 외부에서 접근하는 getter 메소드
}
```

**주요 메서드:**
- `displayFlightList(String str)`: 항공편 목록 출력
- `bookFlight()`: 항공편 예매 처리
- `seatSelection(Flight flight)`: 좌석 선택 처리
- `passengerInfo(Flight flight)`: 승객 정보 입력
- `checkReservation()`: 예약 조회
- `checkPassword(int index)`: 비밀번호 확인
- `ticketPrint()`: 티켓 정보 출력
- `ticketSave()`: 티켓 파일 저장

---

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
        //이어 작성하기 구현 및 폴더 까지 생성 구현
        // try {
        //     File file = new File("d:\\ticket\\ticket.txt");
        //     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        //     //BufferedWriter를 사용하여 file에 데이터를 쓸 준비하는것
        //     //FileWriter는 기본적으로 기존 파일을 덮어씀
        //     if(file.isFile() && file.canWrite()){
        //         //.canWrite - 쓰기 권한이 있는지 확인 true - 쓰기 권한 있음
        //         bufferedWriter.write(fm.ticketPrint(reservationMap, name));//티겟 정보를 file에 작성
        //         bufferedWriter.flush();//버퍼에 있는 데이터를 파일에 저장
        //         System.out.println("파일 저장 성공");
        //         bufferedWriter.close();//버퍼 닫기
        //     } else {
        //         file = new File("d:\\ticket");
        //         file.mkdir();// 디렉토리에 폴더 생성
        //         file = new File("d:\\ticket"+"\\"+"ticket.txt");
        //         file.createNewFile();
        //     }
        // } catch (IOException e) {
        //     System.out.println("파일 저장 실패");
        // }

        try {
            File dir = new File("d:\\ticket");
            if (!dir.exists()) {
                dir.mkdir(); // 폴더가 없으면 생성
            }
    
            File file = new File(dir, "ticket.txt");
            boolean isNewFile = file.createNewFile(); // 파일이 없으면 생성
    
            // append 모드 활성화
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    
            if (file.canWrite()) {
                // 기존 파일이 있으면 개행 후 추가
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
            // BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String line;
            System.out.println("===============================================================");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String[] flight = line.split("/");// (/)로 끊어서 배열로 만들고 그 정보를 Flight에 넣는것
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

**주요 메서드:**
- `ticketSaveFile()`: 티켓 정보를 파일에 저장 (append 모드)
- `upload()`: 항공편 일정 파일 읽기 (UTF-8 인코딩)

---

### FlightReservationMain.java

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

**주요 기능:**
- 메인 메뉴 시스템
- 라벨이 붙은 무한 루프
- 예외 처리 (NumberFormatException, InterruptedException)

---

## 실행 방법

### 1. 프로젝트 구조 생성

```
프로젝트 루트/
└── a0402/
    └── javaair/
        ├── Passenger.java
        ├── Flight.java
        ├── FlightManager.java
        ├── FileC.java
        └── FlightReservationMain.java
```

### 2. 컴파일 및 실행

```bash
# 컴파일
javac a0402/javaair/*.java

# 실행
java a0402.javaair.FlightReservationMain
```

### 3. 파일 준비 (선택사항)

항공편 업로드 기능을 사용하려면 다음 파일을 준비하세요:

**`d:\ticket\schedule.txt`**
```
도쿄/14:30/350000/true
부산/09:15/65000/false
런던/22:00/1500000/true
```

**파일 형식:** `목적지/출발시간/가격/국제선여부`

---

## 주요 기능

### 1. 항공편 목록 조회
- 메뉴에서 1번 선택
- 등록된 모든 항공편 정보 출력

### 2. 항공편 예매
- 메뉴에서 2번 선택
- 항공편 선택
- 국제선이면 만 15세 이상 확인
- 승객 정보 입력 (이름, 생년월일, 비밀번호)
- 좌석 선택 (1~20번)
- 예약 완료

### 3. 예약 조회
- 메뉴에서 3번 선택
- 예약자 이름 입력
- 비밀번호 확인
- 티켓 정보 출력

### 4. 티켓 저장
- 메뉴에서 4번 선택
- 예약 조회 후 비밀번호 확인
- `d:\ticket\ticket.txt` 파일에 저장

### 5. 항공편 업로드
- 메뉴에서 5번 선택
- `d:\ticket\schedule.txt` 파일 읽기
- 항공편 목록에 추가

---

## 핵심 개념

### 1. 생년월일 처리
- 형식: YYMMDD (6자리)
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
- `ArrayList<Flight>`: 항공편 목록
- `ArrayList<Passenger>`: 승객 목록
- `HashMap<String, Flight>`: 예약 정보

### 6. 라벨이 붙은 루프
- `Outter:while(true) { ... }`
- `break Outter;`로 외부 루프 탈출

---

## 데이터 구조

### 초기 항공편 데이터
```java
new Flight("제주","11:55", 78000, false)      // 국내선
new Flight("이스탄불","17:10",1200000,true)   // 국제선
new Flight("방콕","21:35",280000,true)        // 국제선
```

### 좌석 구조
- 총 20개 좌석 (1번~20번)
- 예약된 좌석은 "XX"로 표시
- 좌석 배치도: 4개씩 그룹화하여 출력

---

**작성일:** 2026-01-30  
**프로젝트:** JavaAir 항공 예약 시스템  
**버전:** 1.0
