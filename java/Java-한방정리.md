# ☕ Java 한방 정리 (입문 → 객체지향 기초 완성)

## 📌 한 줄 요약

**Java는 클래스와 객체로 프로그램을 만드는 언어다.  
문법 → 배열·메서드 → 클래스 → 상속·인터페이스 → 컬렉션 순으로 익힌다.**

**체크리스트:** [Java-필수-체크리스트.md](./Java-필수-체크리스트.md)  
**문제 / 정답:** [Java-한방정리-문제.md](./Java-한방정리-문제.md) · [Java-한방정리-정답.md](./Java-한방정리-정답.md)

---

## 목차

1. [Java란?](#1-java란)
2. [프로그램 기본 구조](#2-프로그램-기본-구조-무조건-암기-)
3. [변수와 자료형](#3-변수와-자료형)
4. [연산자](#4-연산자)
5. [조건문](#5-조건문)
6. [반복문](#6-반복문)
7. [배열](#7-배열)
8. [메서드](#8-메서드)
9. [클래스와 객체](#9-클래스와-객체-)
10. [생성자 · Getter/Setter](#10-생성자--gettersetter)
11. [상속 · 오버라이딩](#11-상속--오버라이딩)
12. [인터페이스](#12-인터페이스)
13. [예외 처리](#13-예외-처리)
14. [컬렉션](#14-컬렉션-arraylist--hashmap)
15. [String](#15-string)
16. [한 장 요약](#16-한-장-요약)

---

## 1. Java란?

### 기본 개념

**Java**는 **객체지향(Object-Oriented)** 프로그래밍 언어입니다.

### 특징

| 특징 | 설명 |
|------|------|
| JVM | 어디서나 실행 (Write Once, Run Anywhere) |
| 객체지향 | 클래스·객체·상속·다형성 |
| 컴파일 | `.java` → `.class` → JVM 실행 |
| 확장자 | `.java` |

### 실행 흐름

```
소스코드 (.java)
    ↓ javac 컴파일
바이트코드 (.class)
    ↓ java 실행
JVM이 프로그램 실행
```

### 웹/백엔드에서의 위치

```
HTML/CSS/JS  (화면)
      ↓
Java / Spring (서버 로직)
      ↓
MySQL         (데이터)
```

---

## 2. 프로그램 기본 구조 (무조건 암기 ⭐)

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

| 요소 | 의미 |
|------|------|
| `public class Hello` | 클래스 선언 (파일명 = 클래스명) |
| `main` | 프로그램 시작점 |
| `System.out.println` | 콘솔 출력 |

### 출력

```java
System.out.println("줄바꿈 있음");
System.out.print("줄바꿈 없음");
System.out.printf("이름: %s, 나이: %d\n", "홍길동", 20);
```

### 입력 (Scanner)

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
String name = sc.nextLine();  // 문자열
int age = sc.nextInt();       // 정수
```

---

## 3. 변수와 자료형

### 변수 선언

```java
int age = 20;
String name = "홍길동";
double score = 95.5;
boolean pass = true;
```

### 기본형 vs 참조형

| 구분 | 종류 | 예 |
|------|------|-----|
| **기본형** | 값을 직접 저장 | `int`, `double`, `char`, `boolean` |
| **참조형** | 주소를 저장 | `String`, 배열, 클래스 |

### 자주 쓰는 자료형

| 타입 | 설명 | 예 |
|------|------|-----|
| `int` | 정수 | `10` |
| `long` | 큰 정수 | `10000000000L` |
| `double` | 실수 | `3.14` |
| `char` | 문자 1개 | `'A'` |
| `boolean` | 참/거짓 | `true` / `false` |
| `String` | 문자열 | `"Hello"` |

### 형변환

```java
int a = 10;
double b = a;        // 자동 (작 → 큰)
int c = (int) 3.9;   // 강제 (큰 → 작), 소수 버림 → 3
```

---

## 4. 연산자

```java
// 산술
+  -  *  /  %

// 비교 → true/false
==  !=  >  <  >=  <=

// 논리
&& (그리고)   || (또는)   ! (부정)

// 증감
i++;   // 1 증가
i--;   // 1 감소

// 대입
=  +=  -=  *=  /=
```

```java
int n = 10;
System.out.println(n % 2 == 0);  // 짝수면 true
```

---

## 5. 조건문

### if

```java
if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else {
    System.out.println("C");
}
```

### switch

```java
switch (day) {
    case 1:
        System.out.println("월");
        break;
    case 2:
        System.out.println("화");
        break;
    default:
        System.out.println("기타");
}
```

### 삼항 연산자

```java
String result = (score >= 60) ? "합격" : "불합격";
```

---

## 6. 반복문

### for

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

### while

```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

### do-while

```java
do {
    // 최소 1번 실행
} while (조건);
```

### break / continue

```java
break;     // 반복 즉시 종료
continue;  // 이번 회차만 건너뛰고 다음으로
```

### 향상된 for (배열·컬렉션)

```java
int[] nums = {1, 2, 3};
for (int n : nums) {
    System.out.println(n);
}
```

---

## 7. 배열

### 선언과 사용

```java
int[] scores = new int[3];
scores[0] = 90;
scores[1] = 80;
scores[2] = 70;

int[] arr = {90, 80, 70};

System.out.println(arr.length);  // 길이
System.out.println(arr[0]);      // 첫 요소 (인덱스 0부터)
```

### 2차원 배열

```java
int[][] map = {
    {1, 2, 3},
    {4, 5, 6}
};
```

### 배열 vs ArrayList

| | 배열 | ArrayList |
|--|------|-----------|
| 크기 | 고정 | 가변 |
| 문법 | `int[]` | `ArrayList<Integer>` |

---

## 8. 메서드

### 기본 형태

```java
반환타입 메서드이름(매개변수) {
    // 할 일
    return 값;  // 반환타입이 void면 return 생략 가능
}
```

```java
public static int add(int a, int b) {
    return a + b;
}

public static void printHello() {
    System.out.println("Hello");
}
```

### 오버로딩 (Overloading)

**같은 이름**, **매개변수만 다르게**

```java
void print(int a) { }
void print(String s) { }
void print(int a, int b) { }
```

---

## 9. 클래스와 객체 ⭐

### 한 줄

**클래스 = 설계도 / 객체 = 설계도로 만든 실체**

```java
public class Student {
    // 필드 (속성)
    String name;
    int age;

    // 메서드 (행동)
    void introduce() {
        System.out.println("나는 " + name + ", " + age + "살");
    }
}
```

```java
Student s1 = new Student();  // 객체 생성
s1.name = "홍길동";
s1.age = 20;
s1.introduce();
```

```
클래스 Student          객체 s1
┌─────────────┐        ┌─────────────┐
│ name        │   →    │ name=홍길동  │
│ age         │        │ age=20      │
│ introduce() │        └─────────────┘
└─────────────┘
```

---

## 10. 생성자 · Getter/Setter

### 생성자

객체를 **만들 때** 호출. 클래스 이름과 같고 반환타입 없음.

```java
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

Student s = new Student("홍길동", 20);
```

- `this` = **이 객체 자신**
- 생성자 오버로딩 가능 (매개변수 다르게 여러 개)

### 캡슐화 + Getter/Setter

필드를 `private`으로 숨기고, 메서드로 접근.

```java
public String getName() { return name; }
public void setName(String name) { this.name = name; }
```

### toString

```java
@Override
public String toString() {
    return "Student{name='" + name + "', age=" + age + "}";
}
```

---

## 11. 상속 · 오버라이딩

### 상속 (extends)

부모의 필드·메서드를 물려받음.

```java
public class Animal {
    void sound() {
        System.out.println("소리");
    }
}

public class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("멍멍");
    }
}
```

### 오버라이딩 vs 오버로딩

| | 오버로딩 | 오버라이딩 |
|--|----------|------------|
| 어디서 | 같은 클래스 | 상속 관계 |
| 무엇 | 매개변수 다르게 | 부모 메서드 **내용** 재정의 |
| 이름 | 같음 | 같음 |

### 다형성 (부모 타입으로 자식 다루기)

```java
Animal a = new Dog();
a.sound();  // 멍멍 (실제 객체의 메서드)
```

---

## 12. 인터페이스

**규약(해야 할 일의 목록)**. 구현 클래스가 `implements`.

```java
public interface Payable {
    void pay(int amount);
}

public class CardPay implements Payable {
    @Override
    public void pay(int amount) {
        System.out.println("카드 결제: " + amount);
    }
}
```

- 클래스: 다중 상속 불가 / 인터페이스: 여러 개 구현 가능
- Spring·실무에서 **느슨한 결합**에 자주 사용

---

## 13. 예외 처리

```java
try {
    int n = Integer.parseInt("abc");  // 예외 발생 가능
} catch (NumberFormatException e) {
    System.out.println("숫자로 변환 실패");
} finally {
    System.out.println("항상 실행");
}
```

| 키워드 | 역할 |
|--------|------|
| `try` | 시도 |
| `catch` | 잡아서 처리 |
| `finally` | 예외 여부와 관계없이 실행 |
| `throw` | 예외를 발생시킴 |
| `throws` | 메서드가 예외를 밖으로 던짐 |

---

## 14. 컬렉션 (ArrayList · HashMap)

### ArrayList — 순서가 있는 목록

```java
import java.util.ArrayList;

ArrayList<String> list = new ArrayList<>();
list.add("사과");
list.add("바나나");
System.out.println(list.get(0));   // 사과
System.out.println(list.size());
list.remove(0);
```

### HashMap — 키 → 값

```java
import java.util.HashMap;

HashMap<String, Integer> map = new HashMap<>();
map.put("국어", 90);
map.put("수학", 80);
System.out.println(map.get("국어"));  // 90
```

### List vs Map 한 줄

| | List | Map |
|--|------|-----|
| 접근 | 인덱스 `0,1,2…` | 키 `"국어"` |
| 예 | 학생 명단 | 과목→점수 |

---

## 15. String

```java
String s = "Hello Java";

s.length();
s.equals("Hello Java");   // 문자열 비교는 == 말고 equals
s.contains("Java");
s.substring(0, 5);        // Hello
s.replace("Java", "World");
s.split(" ");
```

```java
// 잘못된 비교
if (s == "Hello Java") { }

// 올바른 비교
if (s.equals("Hello Java")) { }
```

---

## 16. 한 장 요약

```
변수·연산자 → if/for → 배열 → 메서드
        ↓
   클래스 / 객체 / 생성자 / Getter·Setter
        ↓
   상속 · 오버라이딩 · 인터페이스
        ↓
   예외 · ArrayList · HashMap · String
```

### 자주 나오는 실수

| 실수 | 고치기 |
|------|--------|
| 문자열 `==` 비교 | `equals` |
| 배열 길이 `arr.length()` | `arr.length` (괄호 없음) |
| Scanner `nextInt` 후 `nextLine` | 버퍼에 남은 엔터 주의 |
| `UPDATE`/`DELETE` 비유는 SQL | Java는 `=` 대입, `==` 비교 |
| main 없는 실행 | `public static void main` 확인 |

### 다음 단계

| 주제 | 자료 예 |
|------|---------|
| 필수 체크 | [Java-필수-체크리스트.md](./Java-필수-체크리스트.md) |
| 인터페이스 | [인터페이스-완전정리.md](./인터페이스-완전정리.md) |
| 예외 | [예외처리/](./예외처리/) |
| 컬렉션 | [컬렉션/](./컬렉션/) |
| Stream | [자바-Stream-API-정리.md](./자바-Stream-API-정리.md) |
| CRUD 실습 | [crud-직원관리/](./crud-직원관리/), [계좌관리](./계좌관리-Account-BankApplication-문제.md) |

---

## 강사 멘트 (화면용)

1. **클래스 = 설계도, 객체 = 실물**  
2. **메서드 = 객체가 할 수 있는 일**  
3. **private + getter/setter = 캡슐화**  
4. **extends = 물려받기, implements = 약속 지키기**  
5. **ArrayList는 늘어나는 배열처럼** 쓰면 된다  
