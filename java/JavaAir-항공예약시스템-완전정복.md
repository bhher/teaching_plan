# JavaAir 항공 예약 시스템 - 완전 정복

## 목차

1. [시스템 개요](#시스템-개요)
2. [클래스 구조](#클래스-구조)
3. [클래스별 상세 설명](#클래스별-상세-설명)
4. [주요 기능](#주요-기능)
5. [데이터 흐름](#데이터-흐름)
6. [핵심 개념 정리](#핵심-개념-정리)

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

## 클래스 구조

```
JavaAir 시스템
├── FlightReservationMain (메인 클래스)
│   └── 프로그램 진입점, 메뉴 처리
│
├── FlightManager (항공편 관리)
│   ├── flights (ArrayList<Flight>) - 항공편 목록
│   ├── passengers (ArrayList<Passenger>) - 승객 목록
│   └── reservationMap (Map<String, Flight>) - 예약 정보
│
├── Flight (항공편)
│   ├── destination - 목적지
│   ├── time - 출발시간
│   ├── price - 가격
│   ├── internationalFlight - 국제선 여부
│   └── seats (ArrayList<String>) - 좌석 정보
│
├── Passenger (승객)
│   ├── name - 이름
│   ├── birthDate - 생년월일 (6자리)
│   ├── pw - 비밀번호
│   └── seat - 좌석 번호
│
└── FileC (파일 처리)
    ├── ticketSaveFile() - 티켓 저장
    └── upload() - 일정 업로드
```

---

## 클래스별 상세 설명

### 1. Passenger 클래스

**역할:** 승객 정보를 관리하는 클래스

#### 필드 (Field)

```java
private String name;        // 이름
private int birthDate;      // 생년월일 (6자리: YYMMDD)
private String pw;          // 비밀번호
private String seat;        // 좌석 번호
```

#### 생성자 (Constructor)

**생성자 1: 기본 정보만**
```java
public Passenger(String name, int birthDate)
```
- 이름과 생년월일만으로 생성
- 비밀번호와 좌석은 나중에 설정

**생성자 2: 비밀번호 포함**
```java
public Passenger(String name, int birthDate, String pw)
```
- 이름, 생년월일, 비밀번호로 생성
- 예약 시 사용

#### 주요 메서드

##### `man15(Passenger p)` - 만 15세 이상 확인

```java
public boolean man15(Passenger p){
    int y = p.birthDate / 10000;        // 연도 추출 (예: 831016 → 83)
    int m = (p.birthDate % 10000) /100; // 월 추출 (예: 831016 → 10)
    int d = p.birthDate % 100;          // 일 추출 (예: 831016 → 16)

    if(y > 0 && y <= 25){
        y = y + 2000;  // 2000년대생
    } else {
        y += 1900;     // 1900년대생
    }
    
    LocalDate birthDate2 = LocalDate.of(y, m, d);
    LocalDate currentDate = LocalDate.now();
    int age = Period.between(birthDate2, currentDate).getYears();
    
    return age >= 15;  // 만 15세 이상이면 true
}
```

**동작 원리:**
1. 생년월일을 연/월/일로 분리
2. 연도를 4자리로 변환 (00~25는 2000년대, 그 외는 1900년대)
3. `LocalDate`로 생년월일 객체 생성
4. 현재 날짜와 비교하여 나이 계산
5. 만 15세 이상이면 `true` 반환

**예시:**
```java
Passenger p = new Passenger("홍길동", 831016);
// 1983년 10월 16일생
// 현재 날짜가 2024년이면 나이 = 41세
// man15(p) = true
```

---

### 2. Flight 클래스

**역할:** 항공편 정보를 관리하는 클래스

#### 필드 (Field)

```java
private String destination;              // 목적지
private String time;                     // 출발시간
private int price;                       // 가격
private DecimalFormat priceFormat;       // 가격 포맷터
private Boolean internationalFlight;     // 국제선 여부
private ArrayList<String> seats;        // 좌석 목록 (1~20)
```

#### 생성자 (Constructor)

```java
public Flight(String destination, String time, int price, Boolean internationalFlight)
```

**동작:**
- 항공편 정보 초기화
- 좌석 목록 생성 (1~20번)
- 각 좌석을 문자열로 저장

```java
seats = new ArrayList<String>();
for(int i = 1; i <= 20; i++){
    seats.add(i+"");  // 숫자를 문자열로 변환
}
```

#### 주요 메서드

##### `toString()` - 항공편 정보 출력

```java
@Override
public String toString() {
    String priceComma = priceFormat.format(price);
    return ". "+"목적지 : " + destination + ", 출발시간 : " + time + ", 가격 : " + priceComma;
}
```

**출력 예시:**
```
. 목적지 : Jeju, 출발시간 : 11:55, 가격 : 78,000원
```

##### `seatToString()` - 좌석 배치도 출력

```java
public void seatToString(){
    for(int i = 0; i < seats.size()-3; i+=4){
        System.out.printf("|   [%2s]\t\t[%2s][%2s]\t\t[%2s]   |\n", 
            seats.get(i), seats.get(i+1), seats.get(i+2), seats.get(i+3));
    }
}
```

**출력 형식:**
```
|   [ 1]        [ 2][ 3]      [ 4]   |
|   [ 5]        [ 6][ 7]      [ 8]   |
|   [ 9]        [10][11]      [12]   |
|   [13]        [14][15]      [16]   |
|   [17]        [18][19]      [20]   |
```

**설명:**
- 4개씩 그룹화하여 출력
- `i+=4`로 4칸씩 건너뜀
- `seats.size()-3`까지 반복 (마지막 4개 처리)

---

### 3. FlightManager 클래스

**역할:** 항공편 예약을 관리하는 핵심 클래스
    
#### 필드 (Field)

```java
private static ArrayList<Flight> flights;           // 항공편 목록
private static ArrayList<Passenger> passengers;     // 승객 목록
private static Map<String, Flight> reservationMap;  // 예약 정보 (이름 → 항공편)
private static FileC fc;                            // 파일 처리 객체
private Scanner sc;                                 // 입력 스캐너
```

#### 생성자 (Constructor)

```java
public FlightManager(){
    flights = new ArrayList<>();
    // 더미 데이터 추가
    flights.add(new Flight("Jeju","11:55", 78000, false));
    flights.add(new Flight("Istanbul","17:10",1200000,true));
    flights.add(new Flight("Bangkok","21:35",280000,true));
    
    passengers = new ArrayList<>();
    reservationMap = new HashMap<>();
    
    // 테스트 데이터
    Flight sf = flights.get(0);
    reservationMap.put("테스트", sf);
}
```

#### 주요 메서드

##### `displayFlightList(String str)` - 항공편 목록 출력

```java
public void displayFlightList(String str) {
    System.out.println("=========================== "+str+ " =========================" );
    int count = 1;
    for(Flight flight: flights){
        System.out.println(count + ""+ flight);
        count++;
    }
    System.out.println("============================================================");
}
```

**출력 예시:**
```
=========================== 항공편목록 =========================
1. 목적지 : Jeju, 출발시간 : 11:55, 가격 : 78,000원
2. 목적지 : Istanbul, 출발시간 : 17:10, 가격 : 1,200,000원
3. 목적지 : Bangkok, 출발시간 : 21:35, 가격 : 280,000원
============================================================
```

---

##### `bookFlight()` - 항공편 예매

**전체 흐름:**

```java
public void bookFlight() throws InterruptedException {
    for(;;){  // 무한 루프
        // 1. 항공편 목록 출력
        displayFlightList("항공편예매");
        
        // 2. 예매할 항공편 선택
        int bookNum = Integer.parseInt(sc.next());
        
        // 3. 유효성 검사
        if(bookNum > flights.size() || bookNum < 1){
            System.out.println("잘못된 입력입니다.");
            continue;
        }
        
        // 4. 선택한 항공편 정보 출력
        Flight sf = flights.get(bookNum-1);
        System.out.println(bookNum + "" + sf);
        
        // 5. 국제선 여부 확인 및 승객 정보 입력
        if(sf.getInternationalFlight()){  // 국제선이면
            System.out.println("국제선은 만15세이상 예매가능");
            passengerInfo(sf);
        } else {  // 국내선이면
            passengerInfo(sf);
        }
        
        // 6. 좌석 선택
        if(passengers != null && !passengers.isEmpty()){
            String seatNum = Integer.toString(seatSelection(sf));
            passengers.get(passengers.size()-1).setSeat(seatNum);
            
            // 7. 예약 완료 처리
            System.out.println("예약중입니다.");
            Thread.sleep(2000);
            System.out.println("예약에 성공했습니다.");
            
            // 8. 예약 정보 저장
            reservationMap.put(passengers.get(passengers.size()-1).getName(), sf);
            break;
        }
    }
}
```

**핵심 로직:**

1. **무한 루프**: 잘못된 입력 시 다시 입력받음
2. **예외 처리**: `NumberFormatException` 처리
3. **국제선 체크**: 국제선이면 나이 확인
4. **좌석 선택**: `seatSelection()` 메서드 호출
5. **예약 저장**: `reservationMap`에 저장

---

##### `seatSelection(Flight flight)` - 좌석 선택

```java
private int seatSelection(Flight flight) {
    int seatNum = -1;
    while(true){
        try {
            // 1. 좌석 배치도 출력
            flight.seatToString();
            
            // 2. 좌석 번호 입력
            System.out.println("좌석번호를 선택하세요");
            int seatInt = sc.nextInt() - 1;  // 배열 인덱스로 변환
            sc.nextLine();
            
            // 3. 유효성 검사
            if(seatInt+1 < 1 || seatInt+1 > 20){
                System.out.println("존재하지 않는 좌석입니다.");
            }
            // 4. 이미 예약된 좌석 확인
            else if(flight.getSeats().get(seatInt).equals("XX")){
                System.out.println("이미 예약된 좌석입니다.");
            }
            // 5. 좌석 예약
            else {
                flight.getSeats().set(seatInt, "XX");  // "XX"로 표시
                System.out.println("좌석 선택이 완료되었습니다.");
                seatNum = seatInt;  // ⭐ 반환값에 저장 (변수 스코프 문제 해결)
                break;
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다.");
            sc.nextLine();
        }
    }
    return seatNum;
}
```

**동작 원리:**

1. 좌석 배치도 출력
2. 사용자가 좌석 번호 입력 (1~20)
3. 배열 인덱스로 변환 (`seatInt - 1`)
4. 유효성 검사:
   - 범위 확인 (1~20)
   - 예약 여부 확인 ("XX"인지)
5. 예약 가능하면 "XX"로 표시
6. **`seatNum = seatInt;`로 반환값 저장** ⭐
   - `seatInt`는 `while` 블록 내부 변수
   - `break` 후 접근 불가
   - 메서드 스코프 변수 `seatNum`에 값 저장 필요
7. 좌석 번호 반환

**예시:**
```java
// 좌석 5번 선택
seatInt = 5 - 1 = 4
flight.getSeats().set(4, "XX")  // 좌석 예약 표시
seatNum = 4;  // ⭐ 반환값에 저장 (break 후 seatInt 접근 불가)
break;
return seatNum;  // 4 반환
// 좌석 배치도에서 5번이 "XX"로 표시됨
```

**`seatNum = seatInt;`를 하는 이유:**
- `seatInt`는 `while` 블록 내부 변수로 `break` 후 접근 불가
- 메서드는 `int`를 반환해야 하므로 메서드 스코프 변수 `seatNum`에 값 저장 필요
- 호출하는 쪽에서 선택된 좌석 번호를 받아 승객 객체에 저장

---

##### `passengerInfo(Flight flight)` - 승객 정보 입력

```java
private void passengerInfo(Flight flight) {
    System.out.println("예매자 정보를 입력하세요");
    System.out.print("이름 : ");
    String name = sc.next();
    
    System.out.printf("생년월일(6자리):");
    try {
        int birthDate = Integer.parseInt(sc.next());
        Passenger p = new Passenger(name, birthDate);
        
        // 국제선이고 만 15세 미만이면 예약 거절
        if(!p.man15(p) && flight.getInternationalFlight()){
            System.out.println("만 15미만은 국제선 예약불가입니다.");
        } else {
            // 비밀번호 입력받아 승객 생성
            System.out.println("결제 비밀 번호");
            String pw = sc.next();
            p = new Passenger(name, birthDate, pw);
            passengers.add(p);  // 승객 목록에 추가
        }
    } catch (DateTimeException e) {
        System.out.println("생년월일을 6자리로 입력해주세요 ex)010225");
    }
}
```

**동작 흐름:**

1. 이름 입력
2. 생년월일 입력 (6자리)
3. `Passenger` 객체 생성
4. **국제선 체크**:
   - 국제선이고 만 15세 미만 → 예약 거절
   - 그 외 → 비밀번호 입력받아 승객 추가

**예외 처리:**
- `DateTimeException`: 잘못된 날짜 형식

---

##### `checkReservation()` - 예약 조회

```java
public void checkReservation() {
    int index = search("예약확인");
    checkPassword(index);
}
```

**동작:**
1. `search()`로 승객 검색
2. `checkPassword()`로 비밀번호 확인
3. 티켓 정보 출력

---

##### `checkPassword(int index)` - 비밀번호 확인

```java
private void checkPassword(int index) {
    for(;;){  // 무한 루프
        if(index != -1){
            System.out.println("결제 비밀번호");
            String pw = sc.next();
            
            // 비밀번호 일치 확인
            if(passengers.get(index).getPw().equals(pw)){
                System.out.println("비밀번호가 일치합니다.");
                System.out.println(ticketPrint(reservationMap, passengers.get(index).getName()));
                break;
            }
        }
    }
}
```

**동작:**
- 비밀번호 입력받아 일치 확인
- 일치하면 티켓 정보 출력
- 일치하지 않으면 다시 입력받음

---

##### `search(String str)` - 승객 검색

```java
private int search(String str) {
    System.out.println("===================== " + str + " =====================");
    System.out.print("예약자 이름: ");
    String name = sc.next();
    sc.nextLine();
    
    int index = -1;
    if(passengers != null){
        for(int i = 0; i < passengers.size(); i++){
            if(passengers.get(i).getName().equals(name)){
                index = i;
            }
        }
    }
    return index;  // 찾으면 인덱스, 못 찾으면 -1
}
```

**동작:**
- 이름으로 승객 검색
- 찾으면 인덱스 반환, 못 찾으면 -1 반환

---

##### `ticketPrint()` - 티켓 출력

```java
String ticketPrint(Map<String, Flight> reservationMap, String name) {
    int index = -1;
    // 승객 인덱스 찾기
    if(passengers != null){
        for(int i = 0; i < passengers.size(); i++){
            if(passengers.get(i).getName().equals(name)){
                index = i;
            }
        }
    }
    
    // 좌석 번호 계산 (배열 인덱스 + 1)
    int seat = Integer.parseInt(passengers.get(index).getSeat()) + 1;
    
    return  "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n\n" +
            "\t" + name + "님의 티켓정보" +
            "| 좌석 : " + seat + "번\n"+
            "." + reservationMap.get(name) + "\n\n" +
            "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";
}
```

**출력 예시:**
```
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

        홍길동님의 티켓정보| 좌석 : 5번
. 목적지 : Jeju, 출발시간 : 11:55, 가격 : 78,000원

ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
```

---

##### `ticketSave()` - 티켓 파일 저장

```java
public void ticketSave() {
    int index = search("티켓조회");
    checkPassword(index);
    fc.ticketSaveFile(reservationMap, passengers.get(index).getName());
}
```

**동작:**
1. 승객 검색
2. 비밀번호 확인
3. 파일에 티켓 정보 저장

---

### 4. FileC 클래스

**역할:** 파일 입출력을 처리하는 클래스

#### 필드

```java
private FlightManager fm = new FlightManager();
```

#### 주요 메서드

##### `ticketSaveFile()` - 티켓 파일 저장

```java
public void ticketSaveFile(Map<String, Flight> reservationMap, String name) {
    try {
        // 1. 폴더 생성 (없으면)
        File dir = new File("d:\\ticket");
        if (!dir.exists()) {
            dir.mkdir();
        }
    
        // 2. 파일 생성 (없으면)
        File file = new File(dir, "ticket.txt");
        boolean isNewFile = file.createNewFile();
    
        // 3. append 모드로 파일 열기
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
    
        if (file.canWrite()) {
            // 4. 기존 파일이 있으면 개행 후 추가
            if (!isNewFile) {
                bufferedWriter.newLine();
            }
            // 5. 티켓 정보 작성
            bufferedWriter.write(fm.ticketPrint(reservationMap, name));
            bufferedWriter.flush();
            System.out.println("파일 저장 성공");
        }
    
        bufferedWriter.close();
    } catch (IOException e) {
        System.out.println("파일 저장 실패: ");
    }
}
```

**핵심 포인트:**

1. **폴더 생성**: `d:\ticket` 폴더가 없으면 생성
2. **파일 생성**: `ticket.txt` 파일이 없으면 생성
3. **append 모드**: `FileWriter(file, true)` - 기존 내용에 추가
4. **개행 처리**: 기존 파일이 있으면 개행 후 추가

**파일 경로:**
- `d:\ticket\ticket.txt`

---

##### `upload()` - 항공편 일정 업로드

```java
public void upload() {
    try {
        File file = new File("d:\\ticket\\schedule.txt");
        // UTF-8 인코딩으로 파일 읽기
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(file), "UTF-8"));
        
        String line;
        System.out.println("===============================================================");
        
        // 한 줄씩 읽기
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            // "/"로 분리하여 항공편 정보 추출
            String[] flight = line.split("/");
            // Flight 객체 생성하여 목록에 추가
            FlightManager.getFlights().add(
                new Flight(
                    flight[0],                          // 목적지
                    flight[1],                          // 출발시간
                    Integer.parseInt(flight[2]),        // 가격
                    Boolean.parseBoolean(flight[3])     // 국제선 여부
                )
            );
        }
    } catch (FileNotFoundException e) {
        System.out.println("schedule.txt 파일이 존재하지 않음");
    } catch (IOException e) {
        System.out.println("파일 읽기 실패");
    }
}
```

**파일 형식 (`schedule.txt`):**
```
도쿄/14:30/350000/true
부산/09:15/65000/false
런던/22:00/1500000/true
```

**동작:**
1. `schedule.txt` 파일 읽기
2. 각 줄을 "/"로 분리
3. `Flight` 객체 생성
4. `FlightManager`의 `flights` 목록에 추가

---

### 5. FlightReservationMain 클래스

**역할:** 프로그램의 진입점, 메뉴 처리

#### main 메서드

```java
public static void main(String[] args) {
    FlightManager fm = new FlightManager();
    Scanner sc = new Scanner(System.in);
    FileC fc = new FileC();
    
    System.out.println(fm.airplane);  // 비행기 아스키 아트 출력
    System.out.println("==============JavaAir 에 오신걸 환영합니다.==============");

    Outter:while(true){  // 라벨이 붙은 무한 루프
        System.out.println("1. 항공편 목록\n2. 항공편 예매\n3. 예약 조회\n4. 티켓 저장\n5. 항공편 업로드 \n0.종료\n");
        System.out.print("메뉴입력>");

        String menuStr = sc.next();
        sc.nextLine();  // 버퍼 비우기

        int menu = -1;
        try {
            menu = Integer.parseInt(menuStr);
        } catch (NumberFormatException e) {
            menu = 9;  // 잘못된 입력은 default로 처리
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
                break Outter;  // 라벨이 붙은 루프 탈출
            default:
                break;
        }
    }
}
```

**메뉴 구조:**

| 번호 | 기능 | 메서드 |
|------|------|--------|
| 1 | 항공편 목록 | `displayFlightList()` |
| 2 | 항공편 예매 | `bookFlight()` |
| 3 | 예약 조회 | `checkReservation()` |
| 4 | 티켓 저장 | `ticketSave()` |
| 5 | 항공편 업로드 | `upload()` |
| 0 | 종료 | 프로그램 종료 |

**핵심 포인트:**

1. **라벨이 붙은 루프**: `Outter:while(true)`
   - `break Outter;`로 외부 루프 탈출 가능
2. **예외 처리**: `NumberFormatException` 처리
3. **버퍼 비우기**: `sc.nextLine()`로 입력 버퍼 정리

---

## 주요 기능

### 1. 항공편 예매 프로세스

```
1. 항공편 목록 출력
   ↓
2. 항공편 선택
   ↓
3. 국제선 여부 확인
   ├─ 국제선 → 나이 확인 (만 15세 이상)
   └─ 국내선 → 바로 진행
   ↓
4. 승객 정보 입력
   ├─ 이름
   ├─ 생년월일 (6자리)
   └─ 비밀번호
   ↓
5. 좌석 선택
   ├─ 좌석 배치도 출력
   ├─ 좌석 번호 입력
   └─ 예약 가능 여부 확인
   ↓
6. 예약 완료
   └─ reservationMap에 저장
```

### 2. 예약 조회 프로세스

```
1. 이름 입력
   ↓
2. 승객 검색
   ├─ 찾음 → 다음 단계
   └─ 못 찾음 → 종료
   ↓
3. 비밀번호 확인
   ├─ 일치 → 티켓 출력
   └─ 불일치 → 다시 입력
```

### 3. 파일 저장 프로세스

```
1. 티켓 조회 (이름 + 비밀번호)
   ↓
2. 폴더 확인/생성 (d:\ticket)
   ↓
3. 파일 확인/생성 (ticket.txt)
   ↓
4. append 모드로 파일 열기
   ↓
5. 티켓 정보 작성
   ↓
6. 파일 저장
```

---

## 데이터 흐름

### 예약 데이터 흐름

```
사용자 입력
    ↓
Passenger 객체 생성
    ↓
passengers (ArrayList)에 추가
    ↓
좌석 선택 → Flight.seats 수정
    ↓
reservationMap (Map)에 저장
    (키: 이름, 값: Flight 객체)
```

### 데이터 구조

#### 1. flights (ArrayList<Flight>)
```java
[
    Flight("Jeju", "11:55", 78000, false),
    Flight("Istanbul", "17:10", 1200000, true),
    Flight("Bangkok", "21:35", 280000, true)
]
```

#### 2. passengers (ArrayList<Passenger>)
```java
[
    Passenger("홍길동", 831016, "1234", "4"),
    Passenger("김영희", 950315, "5678", "12")
]
```

#### 3. reservationMap (Map<String, Flight>)
```java
{
    "홍길동" → Flight("Jeju", "11:55", 78000, false),
    "김영희" → Flight("Bangkok", "21:35", 280000, true)
}
```

#### 4. Flight.seats (ArrayList<String>)
```java
["1", "2", "3", "XX", "5", ..., "20"]
// "XX"는 예약된 좌석
```

---

## 핵심 개념 정리

### 1. 생년월일 처리

**6자리 형식:** `YYMMDD`
- 예: `831016` = 1983년 10월 16일
- 예: `050225` = 2005년 2월 25일

**연도 변환 로직:**
```java
if(y > 0 && y <= 25){
    y = y + 2000;  // 2000년대생
} else {
    y += 1900;     // 1900년대생
}
```

**나이 계산:**
```java
LocalDate birthDate2 = LocalDate.of(y, m, d);
LocalDate currentDate = LocalDate.now();
int age = Period.between(birthDate2, currentDate).getYears();
```

### 2. 좌석 관리

**좌석 번호 vs 배열 인덱스:**
- 좌석 번호: 1~20 (사용자에게 보여줌)
- 배열 인덱스: 0~19 (내부 처리)

**변환:**
```java
// 입력: 좌석 번호 5
int seatInt = sc.nextInt() - 1;  // 배열 인덱스: 4

// 출력: 배열 인덱스 4
int seat = Integer.parseInt(passengers.get(index).getSeat()) + 1;  // 좌석 번호: 5
```

**예약 표시:**
```java
flight.getSeats().set(seatInt, "XX");  // 예약된 좌석을 "XX"로 표시
```

### 3. 파일 입출력

#### 파일 저장 (append 모드)
```java
BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
```
- `true`: append 모드 (기존 내용에 추가)
- `false`: overwrite 모드 (기존 내용 덮어쓰기)

#### 파일 읽기 (UTF-8)
```java
BufferedReader bufferedReader = new BufferedReader(
    new InputStreamReader(new FileInputStream(file), "UTF-8"));
```
- 한글 깨짐 방지를 위해 UTF-8 인코딩 지정

### 4. 예외 처리

#### NumberFormatException
```java
try {
    int bookNum = Integer.parseInt(sc.next());
} catch (NumberFormatException e) {
    System.out.println("잘못된 입력입니다.");
}
```

#### InputMismatchException
```java
try {
    int seatInt = sc.nextInt() - 1;
} catch (InputMismatchException e) {
    System.out.println("잘못된 입력입니다.");
    sc.nextLine();  // 버퍼 비우기
}
```

#### IOException
```java
try {
    // 파일 입출력 작업
} catch (IOException e) {
    System.out.println("파일 저장 실패");
}
```

### 5. 컬렉션 활용

#### ArrayList
```java
ArrayList<Flight> flights;        // 항공편 목록
ArrayList<Passenger> passengers; // 승객 목록
ArrayList<String> seats;          // 좌석 목록
```

#### HashMap
```java
Map<String, Flight> reservationMap;  // 예약 정보 (이름 → 항공편)
```

**사용 예시:**
```java
// 추가
reservationMap.put("홍길동", flight);

// 조회
Flight f = reservationMap.get("홍길동");

// 확인
if(reservationMap.containsKey("홍길동")) { ... }
```

### 6. 정적(static) 변수와 메서드

#### static 변수
```java
private static ArrayList<Flight> flights;
private static Map<String, Flight> reservationMap;
```

**특징:**
- 클래스 레벨에서 공유
- 인스턴스 생성 없이 접근 가능
- `FlightManager.getFlights()`로 접근

#### static 메서드
```java
public static ArrayList<Flight> getFlights() {
    return flights;
}
```

**사용:**
```java
FlightManager.getFlights().add(new Flight(...));
```

---

## 코드 분석 포인트

### 1. 생성자 오버로딩

**Passenger 클래스:**
```java
// 생성자 1: 기본 정보만
public Passenger(String name, int birthDate)

// 생성자 2: 비밀번호 포함
public Passenger(String name, int birthDate, String pw)
```

**사용 시나리오:**
1. 나이 확인 시: 생성자 1 사용
2. 예약 완료 시: 생성자 2 사용

### 2. 메서드 오버라이딩

**Flight 클래스:**
```java
@Override
public String toString() {
    // 항공편 정보를 문자열로 반환
}
```

**장점:**
- `System.out.println(flight)`로 바로 출력 가능
- 코드 가독성 향상

### 3. 무한 루프와 break

**bookFlight() 메서드:**
```java
for(;;){  // 무한 루프
    // 입력 처리
    if(조건){
        break;  // 예약 성공 시 루프 탈출
    }
}
```

**checkPassword() 메서드:**
```java
for(;;){  // 무한 루프
    if(비밀번호 일치){
        break;  // 비밀번호 일치 시 루프 탈출
    }
}
```

### 4. 라벨이 붙은 루프

**FlightReservationMain:**
```java
Outter:while(true){
    switch(menu){
        case 0:
            break Outter;  // 외부 루프 탈출
    }
}
```

**장점:**
- 중첩된 루프에서 외부 루프 탈출 가능

### 5. Thread.sleep() 사용

```java
System.out.println("예약중입니다.");
Thread.sleep(2000);  // 2초 대기
System.out.println("예약에 성공했습니다.");
```

**용도:**
- 사용자 경험 향상
- 처리 중임을 시각적으로 표현

**주의사항:**
- `InterruptedException` 처리 필요
- `throws InterruptedException` 선언

---

## 실행 흐름 예시

### 시나리오: Jeju 항공편 예매

```
1. 프로그램 시작
   → 비행기 아스키 아트 출력
   → "JavaAir 에 오신걸 환영합니다" 출력

2. 메뉴 선택: 2 (항공편 예매)
   → 항공편 목록 출력
   → "예매할 항공편 입력 > " 출력

3. 항공편 선택: 1
   → "선택한 항공편" 출력
   → Jeju 항공편 정보 출력

4. 승객 정보 입력
   → "이름 : " → 홍길동 입력
   → "생년월일(6자리):" → 831016 입력
   → 나이 확인 (만 15세 이상)
   → "결제 비밀 번호" → 1234 입력

5. 좌석 선택
   → 좌석 배치도 출력
   → "좌석번호를 선택하세요" → 5 입력
   → 좌석 5번을 "XX"로 표시

6. 예약 완료
   → "예약중입니다." 출력
   → 2초 대기
   → "예약에 성공했습니다." 출력
   → 예약 정보 출력

7. reservationMap에 저장
   → "홍길동" → Flight("Jeju", ...) 저장
```

---

## 개선 가능한 부분

### 1. 예외 처리 강화

**현재:**
```java
catch (DateTimeException e) {
    System.out.println("생년월일을 6자리로 입력해주세요");
}
```

**개선:**
```java
catch (DateTimeException e) {
    System.out.println("생년월일을 6자리로 입력해주세요 ex)010225");
} catch (NumberFormatException e) {
    System.out.println("숫자만 입력 가능합니다.");
}
```

### 2. 입력 검증 강화

**생년월일 검증:**
```java
// 현재: 단순히 6자리만 확인
// 개선: 유효한 날짜인지 확인
if(birthDate < 100000 || birthDate > 999999) {
    System.out.println("6자리 숫자를 입력하세요");
}
```

### 3. 코드 중복 제거

**search() 메서드 중복:**
- `checkReservation()`과 `ticketSave()`에서 동일한 검색 로직 사용
- 이미 `search()` 메서드로 분리되어 있음 (좋은 설계)

### 4. 상수 정의

**매직 넘버 제거:**
```java
// 현재
if(seatInt+1 < 1 || seatInt+1 > 20)

// 개선
private static final int MIN_SEAT = 1;
private static final int MAX_SEAT = 20;
if(seatInt+1 < MIN_SEAT || seatInt+1 > MAX_SEAT)
```

---

## 학습 포인트

### 1. 객체지향 설계

- **캡슐화**: private 필드, public 메서드
- **책임 분리**: 각 클래스가 명확한 역할
- **재사용성**: 메서드 분리로 코드 재사용

### 2. 컬렉션 활용

- **ArrayList**: 순서가 있는 목록 관리
- **HashMap**: 키-값 쌍으로 빠른 검색

### 3. 파일 입출력

- **BufferedReader/BufferedWriter**: 효율적인 파일 처리
- **UTF-8 인코딩**: 한글 처리
- **append 모드**: 기존 파일에 추가

### 4. 날짜/시간 처리

- **LocalDate**: 날짜 객체
- **Period**: 날짜 차이 계산

### 5. 예외 처리

- **try-catch**: 예외 상황 처리
- **throws**: 예외 전파

---

## 체크리스트

- [ ] Passenger 클래스 이해
- [ ] Flight 클래스 이해
- [ ] FlightManager 클래스 이해
- [ ] FileC 클래스 이해
- [ ] 생년월일 처리 로직 이해
- [ ] 좌석 선택 로직 이해
- [ ] 파일 입출력 이해
- [ ] 예외 처리 이해
- [ ] 컬렉션 활용 이해
- [ ] 전체 프로그램 흐름 이해

---

**작성일:** 2026-01-30  
**프로젝트:** JavaAir 항공 예약 시스템
