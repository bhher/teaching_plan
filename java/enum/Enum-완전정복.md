# Java Enum 완전 정복

## 📋 목차
1. [Enum이란?](#enum이란)
2. [기본 Enum 사용법](#기본-enum-사용법)
3. [Enum의 특징](#enum의-특징)
4. [Enum 고급 사용법](#enum-고급-사용법)
5. [실전 예제](#실전-예제)
6. [Enum vs 상수](#enum-vs-상수)

---

## Enum이란?

**Enum (열거형)**은 서로 관련된 상수들을 하나의 그룹으로 묶어서 관리하는 Java의 특별한 클래스 타입입니다.

### 왜 Enum을 사용할까?

**문제점 (Enum 없이):**
```java
// 상수로 관리하는 경우
public static final int MONDAY = 1;
public static final int TUESDAY = 2;
public static final int WEDNESDAY = 3;
// ... 문제: 타입 안정성 없음, 잘못된 값 입력 가능
```

**해결책 (Enum 사용):**
```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
// 장점: 타입 안정성, 자동완성, 컴파일 타임 체크
```

---

## 기본 Enum 사용법

### 1. 간단한 Enum 정의

```java
public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
```

### 2. Enum 사용 예제

```java
public class EnumExample {
    public static void main(String[] args) {
        // Enum 변수 선언 및 사용
        Day today = Day.MONDAY;
        
        // 출력
        System.out.println("오늘은 " + today + "입니다.");
        // 출력: 오늘은 MONDAY입니다.
        
        // Enum 값 비교
        if (today == Day.MONDAY) {
            System.out.println("월요일입니다!");
        }
        
        // switch문에서 사용
        switch (today) {
            case MONDAY:
                System.out.println("월요일 - 시작하는 날");
                break;
            case FRIDAY:
                System.out.println("금요일 - 주말 전날");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("주말입니다!");
                break;
            default:
                System.out.println("평일입니다.");
        }
    }
}
```

---

## Enum의 특징

### 1. 타입 안정성 (Type Safety)

```java
// ❌ 잘못된 사용 (컴파일 에러)
Day day = 1;  // 에러! int를 Day에 할당 불가

// ✅ 올바른 사용
Day day = Day.MONDAY;  // 정상!
```

### 2. 자동완성 지원

IDE에서 `Day.`를 입력하면 모든 Enum 값이 자동완성으로 나타납니다.

### 3. 컴파일 타임 체크

잘못된 값을 사용하면 컴파일 시점에 에러가 발생합니다.

### 4. 모든 Enum 값 순회

```java
// values() 메서드로 모든 Enum 값 가져오기
for (Day day : Day.values()) {
    System.out.println(day);
}
// 출력:
// MONDAY
// TUESDAY
// WEDNESDAY
// ...
```

### 5. name()과 ordinal()

```java
Day day = Day.MONDAY;

System.out.println(day.name());    // "MONDAY" (문자열)
System.out.println(day.ordinal()); // 0 (순서, 0부터 시작)
```

---

## Enum 고급 사용법

### 1. Enum에 필드와 메서드 추가

```java
public enum Planet {
    // 각 Enum 값에 데이터 추가
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);
    
    // 필드
    private final double mass;      // 질량 (kg)
    private final double radius;    // 반지름 (m)
    
    // 생성자 (private만 가능)
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    // 메서드
    public double getMass() {
        return mass;
    }
    
    public double getRadius() {
        return radius;
    }
    
    // 중력 계산 메서드
    public double surfaceGravity() {
        double G = 6.67300E-11;  // 중력 상수
        return G * mass / (radius * radius);
    }
    
    // 체중 계산 메서드
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}

// 사용 예제
public class PlanetExample {
    public static void main(String[] args) {
        double earthWeight = 175;  // 지구에서의 체중 (kg)
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        
        for (Planet p : Planet.values()) {
            System.out.printf("Weight on %s is %f%n", 
                p, p.surfaceWeight(mass));
        }
    }
}
```

### 2. Enum에 추상 메서드 추가

```java
public enum Operation {
    PLUS {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        public double apply(double x, double y) {
            return x / y;
        }
    };
    
    // 추상 메서드 (각 Enum 값에서 구현해야 함)
    public abstract double apply(double x, double y);
}

// 사용 예제
public class Calculator {
    public static void main(String[] args) {
        double result1 = Operation.PLUS.apply(10, 5);   // 15.0
        double result2 = Operation.MINUS.apply(10, 5);  // 5.0
        double result3 = Operation.TIMES.apply(10, 5);  // 50.0
        double result4 = Operation.DIVIDE.apply(10, 5); // 2.0
        
        System.out.println("10 + 5 = " + result1);
        System.out.println("10 - 5 = " + result2);
        System.out.println("10 * 5 = " + result3);
        System.out.println("10 / 5 = " + result4);
    }
}
```

### 3. Enum에 일반 메서드 추가

```java
public enum Status {
    PENDING("대기중"),
    PROCESSING("처리중"),
    COMPLETED("완료"),
    CANCELLED("취소됨");
    
    private final String description;
    
    Status(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    // 일반 메서드 추가
    public boolean isCompleted() {
        return this == COMPLETED;
    }
    
    public boolean isPending() {
        return this == PENDING;
    }
    
    // 정적 메서드
    public static Status fromString(String name) {
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + name);
    }
}

// 사용 예제
public class StatusExample {
    public static void main(String[] args) {
        Status status = Status.PROCESSING;
        
        System.out.println(status.getDescription());  // "처리중"
        System.out.println(status.isCompleted());     // false
        System.out.println(status.isPending());       // false
        
        // 문자열로부터 Enum 가져오기
        Status status2 = Status.fromString("COMPLETED");
        System.out.println(status2);  // COMPLETED
    }
}
```

---

## 실전 예제

### 예제 1: 로또 등수 Enum

```java
public enum LottoRank {
    FIRST(6, "1등", 2000000000),      // 6개 일치
    SECOND(5, "2등", 50000000),       // 5개 일치
    THIRD(4, "3등", 1500000),         // 4개 일치
    FOURTH(3, "4등", 50000),         // 3개 일치
    FIFTH(2, "5등", 5000),           // 2개 일치
    NONE(0, "낙첨", 0);              // 당첨 없음
    
    private final int matchCount;     // 일치 개수
    private final String name;        // 등수 이름
    private final long prize;         // 상금
    
    LottoRank(int matchCount, String name, long prize) {
        this.matchCount = matchCount;
        this.name = name;
        this.prize = prize;
    }
    
    public int getMatchCount() {
        return matchCount;
    }
    
    public String getName() {
        return name;
    }
    
    public long getPrize() {
        return prize;
    }
    
    // 일치 개수로 등수 찾기
    public static LottoRank fromMatchCount(int matchCount) {
        for (LottoRank rank : values()) {
            if (rank.matchCount == matchCount) {
                return rank;
            }
        }
        return NONE;
    }
    
    // 당첨 여부 확인
    public boolean isWinner() {
        return this != NONE;
    }
}

// 사용 예제
public class LottoRankExample {
    public static void main(String[] args) {
        int matchCount = 4;
        LottoRank rank = LottoRank.fromMatchCount(matchCount);
        
        System.out.println("일치 개수: " + matchCount);
        System.out.println("등수: " + rank.getName());
        System.out.println("상금: " + rank.getPrize() + "원");
        System.out.println("당첨 여부: " + (rank.isWinner() ? "당첨" : "낙첨"));
        
        // 출력:
        // 일치 개수: 4
        // 등수: 3등
        // 상금: 1500000원
        // 당첨 여부: 당첨
    }
}
```

### 예제 2: HTTP 상태 코드 Enum

```java
public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
    
    private final int code;
    private final String message;
    
    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    // 코드로 상태 찾기
    public static HttpStatus fromCode(int code) {
        for (HttpStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown HTTP status code: " + code);
    }
    
    // 성공 여부 확인
    public boolean isSuccess() {
        return code >= 200 && code < 300;
    }
    
    // 에러 여부 확인
    public boolean isError() {
        return code >= 400;
    }
}

// 사용 예제
public class HttpStatusExample {
    public static void main(String[] args) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        
        System.out.println("코드: " + status.getCode());        // 404
        System.out.println("메시지: " + status.getMessage());   // "Not Found"
        System.out.println("성공: " + status.isSuccess());      // false
        System.out.println("에러: " + status.isError());        // true
        
        // 코드로 찾기
        HttpStatus status2 = HttpStatus.fromCode(200);
        System.out.println(status2);  // OK
    }
}
```

### 예제 3: 방향 Enum (게임 예제)

```java
public enum Direction {
    NORTH("북", 0, -1) {
        public Direction opposite() {
            return SOUTH;
        }
    },
    SOUTH("남", 0, 1) {
        public Direction opposite() {
            return NORTH;
        }
    },
    EAST("동", 1, 0) {
        public Direction opposite() {
            return WEST;
        }
    },
    WEST("서", -1, 0) {
        public Direction opposite() {
            return EAST;
        }
    };
    
    private final String name;
    private final int dx;  // x 방향 변화량
    private final int dy;  // y 방향 변화량
    
    Direction(String name, int dx, int dy) {
        this.name = name;
        this.dx = dx;
        this.dy = dy;
    }
    
    public String getName() {
        return name;
    }
    
    public int getDx() {
        return dx;
    }
    
    public int getDy() {
        return dy;
    }
    
    // 추상 메서드: 반대 방향 반환
    public abstract Direction opposite();
    
    // 좌표 이동
    public int[] move(int x, int y) {
        return new int[]{x + dx, y + dy};
    }
}

// 사용 예제
public class DirectionExample {
    public static void main(String[] args) {
        int x = 5, y = 5;
        
        // 북쪽으로 이동
        Direction dir = Direction.NORTH;
        int[] newPos = dir.move(x, y);
        System.out.println("현재 위치: (" + x + ", " + y + ")");
        System.out.println("북쪽으로 이동: (" + newPos[0] + ", " + newPos[1] + ")");
        // 출력: 현재 위치: (5, 5)
        //       북쪽으로 이동: (5, 4)
        
        // 반대 방향
        Direction opposite = dir.opposite();
        System.out.println("반대 방향: " + opposite.getName());  // 남
    }
}
```

---

## Enum vs 상수

### 상수 사용 (Enum 이전)

```java
public class StatusConstants {
    public static final int PENDING = 1;
    public static final int PROCESSING = 2;
    public static final int COMPLETED = 3;
    public static final int CANCELLED = 4;
}

// 문제점
int status = 999;  // 잘못된 값도 컴파일됨!
if (status == StatusConstants.PENDING) {  // 타입 안정성 없음
    // ...
}
```

### Enum 사용 (권장)

```java
public enum Status {
    PENDING, PROCESSING, COMPLETED, CANCELLED
}

// 장점
Status status = Status.PENDING;  // 타입 안정성 보장
// Status status = 999;  // 컴파일 에러!
```

### 비교표

| 항목 | 상수 | Enum |
|------|------|------|
| **타입 안정성** | ❌ 없음 | ✅ 있음 |
| **자동완성** | ⚠️ 제한적 | ✅ 완벽 |
| **컴파일 체크** | ❌ 없음 | ✅ 있음 |
| **메서드 추가** | ❌ 불가 | ✅ 가능 |
| **필드 추가** | ❌ 불가 | ✅ 가능 |
| **switch 사용** | ⚠️ 가능하지만 위험 | ✅ 안전 |
| **순회** | ❌ 어려움 | ✅ values() 사용 |

---

## Enum 메서드 정리

### 자동 제공 메서드

```java
public enum Color {
    RED, GREEN, BLUE
}

Color color = Color.RED;

// 1. name() - Enum 이름 문자열 반환
String name = color.name();  // "RED"

// 2. ordinal() - Enum 순서 반환 (0부터 시작)
int order = color.ordinal();  // 0

// 3. values() - 모든 Enum 값 배열 반환
Color[] allColors = Color.values();  // [RED, GREEN, BLUE]

// 4. valueOf() - 문자열로 Enum 찾기
Color red = Color.valueOf("RED");  // Color.RED
// Color invalid = Color.valueOf("YELLOW");  // IllegalArgumentException

// 5. equals() - 값 비교
boolean isRed = color.equals(Color.RED);  // true

// 6. == 연산자 - 값 비교 (권장)
boolean isRed2 = (color == Color.RED);  // true

// 7. compareTo() - 순서 비교
int result = Color.RED.compareTo(Color.GREEN);  // 음수 (RED가 앞)
```

---

## Enum 주의사항

### 1. ordinal() 사용 주의

```java
// ❌ 나쁜 예: ordinal()에 의존
public enum Status {
    PENDING,    // ordinal = 0
    PROCESSING, // ordinal = 1
    COMPLETED   // ordinal = 2
}

// 문제: 순서가 바뀌면 코드가 깨짐
if (status.ordinal() == 0) {  // 위험!
    // ...
}

// ✅ 좋은 예: 명시적 필드 사용
public enum Status {
    PENDING(0),
    PROCESSING(1),
    COMPLETED(2);
    
    private final int code;
    
    Status(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}
```

### 2. switch문에서 default 사용

```java
// ✅ 좋은 예: default 처리
switch (status) {
    case PENDING:
        // ...
        break;
    case COMPLETED:
        // ...
        break;
    default:
        // 예상치 못한 경우 처리
        throw new IllegalArgumentException("Unknown status: " + status);
}
```

### 3. Enum 값 비교는 == 사용

```java
// ✅ 권장: == 사용 (null 체크 주의)
if (status == Status.PENDING) {
    // ...
}

// ⚠️ 가능하지만 불필요: equals() 사용
if (status.equals(Status.PENDING)) {
    // ...
}

// ❌ 나쁜 예: == null 체크 없이 사용
if (status == Status.PENDING) {  // status가 null이면 NullPointerException
    // ...
}

// ✅ 안전한 예: null 체크
if (status != null && status == Status.PENDING) {
    // ...
}
```

---

## 실전 활용 팁

### 1. Singleton 패턴과 함께 사용

```java
public enum Singleton {
    INSTANCE;
    
    private int value;
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}

// 사용
Singleton.INSTANCE.setValue(100);
int value = Singleton.INSTANCE.getValue();
```

### 2. Strategy 패턴과 함께 사용

```java
public enum PaymentMethod {
    CREDIT_CARD {
        public void pay(double amount) {
            System.out.println("신용카드로 " + amount + "원 결제");
        }
    },
    BANK_TRANSFER {
        public void pay(double amount) {
            System.out.println("계좌이체로 " + amount + "원 결제");
        }
    },
    CASH {
        public void pay(double amount) {
            System.out.println("현금으로 " + amount + "원 결제");
        }
    };
    
    public abstract void pay(double amount);
}
```

### 3. 상태 머신 구현

```java
public enum OrderStatus {
    ORDERED {
        public OrderStatus next() {
            return PAID;
        }
    },
    PAID {
        public OrderStatus next() {
            return SHIPPED;
        }
    },
    SHIPPED {
        public OrderStatus next() {
            return DELIVERED;
        }
    },
    DELIVERED {
        public OrderStatus next() {
            return this;  // 최종 상태
        }
    };
    
    public abstract OrderStatus next();
}
```

---

## 마무리

Enum은 Java에서 **타입 안정성**과 **가독성**을 높이는 강력한 기능입니다.

**핵심 포인트:**
1. ✅ 타입 안정성 보장
2. ✅ 자동완성 지원
3. ✅ 컴파일 타임 체크
4. ✅ 필드와 메서드 추가 가능
5. ✅ 추상 메서드 구현 가능
6. ✅ switch문과 잘 어울림

**사용 시기:**
- 관련된 상수들을 그룹화할 때
- 타입 안정성이 중요할 때
- 메서드나 필드가 필요한 상수일 때
- 상태나 옵션을 표현할 때

이제 Enum을 활용하여 더 안전하고 읽기 쉬운 코드를 작성할 수 있습니다!
