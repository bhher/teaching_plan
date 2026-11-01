# Java 필수 체크리스트

풀스택 과정(HTML/CSS/JS → **Java** → JDBC/MyBatis → Spring Boot) 기준으로,  
학생이 **반드시 직접 할 수 있어야 하는** 항목을 단계별로 정리한 문서입니다.

**한방 정리:** [Java-한방정리.md](./Java-한방정리.md)  
**문제 / 정답:** [Java-한방정리-문제.md](./Java-한방정리-문제.md) · [Java-한방정리-정답.md](./Java-한방정리-정답.md)

---

## 목차

1. [환경 · 기본 구조](#-1단계--환경--기본-구조-)
2. [변수 · 연산자](#-2단계--변수--연산자-)
3. [조건 · 반복](#-3단계--조건--반복-)
4. [배열](#-4단계--배열-)
5. [메서드](#-5단계--메서드-)
6. [클래스 · 객체](#-6단계--클래스--객체-)
7. [생성자 · 캡슐화](#-7단계--생성자--캡슐화-)
8. [상속 · 다형성](#-8단계--상속--다형성-)
9. [인터페이스](#-9단계--인터페이스-)
10. [예외 처리](#-10단계--예외-처리-)
11. [컬렉션](#-11단계--컬렉션-)
12. [String · Scanner](#-12단계--string--scanner-)
13. [종합 실습](#-13단계--종합-실습-)
14. [우선순위 정리](#우선순위-정리)

---

## 🟢 1단계 — 환경 · 기본 구조 ⭐⭐⭐

### Hello / main

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

- [ ] 파일명과 `public class` 이름이 같아야 함을 안다
- [ ] `main`이 프로그램 시작점임을 설명한다
- [ ] `javac` / `java` 또는 IDE로 실행할 수 있다
- [ ] `println` / `print` 차이를 안다

---

## 🟢 2단계 — 변수 · 연산자 ⭐⭐⭐

| 타입 | 용도 |
|------|------|
| `int` | 정수 |
| `double` | 실수 |
| `boolean` | 참/거짓 |
| `char` | 문자 1개 |
| `String` | 문자열 |

- [ ] 변수 선언·초기화를 할 수 있다
- [ ] 기본형과 참조형의 차이를 한 줄로 말할 수 있다
- [ ] `+ - * / %` 를 사용할 수 있다
- [ ] `== != > <` 와 `&& \|\| !` 를 쓸 수 있다
- [ ] `(int)` 강제 형변환을 할 수 있다

---

## 🟢 3단계 — 조건 · 반복 ⭐⭐⭐

### if / switch

- [ ] `if` / `else if` / `else` 를 작성할 수 있다
- [ ] `switch` + `break` 를 작성할 수 있다
- [ ] 삼항 연산자 `? :` 를 읽을 수 있다

### for / while

- [ ] `for (int i = 0; i < n; i++)` 를 혼자 쓸 수 있다
- [ ] `while` 조건·증감을 맞게 쓴다
- [ ] `break` / `continue` 차이를 안다
- [ ] 향상된 for (`for (int n : arr)`) 를 쓸 수 있다

**미니 과제**

- [ ] 1~100 합 구하기
- [ ] 구구단 출력
- [ ] 점수 → 학점 출력

---

## 🟢 4단계 — 배열 ⭐⭐⭐

```java
int[] arr = new int[5];
int[] scores = {90, 80, 70};
```

- [ ] 배열을 선언·초기화할 수 있다
- [ ] 인덱스가 **0부터**임을 안다
- [ ] `arr.length` 로 길이를 구한다 (`length()` 아님)
- [ ] for로 배열을 순회할 수 있다
- [ ] 최대값·합계·평균을 배열로 구할 수 있다

---

## 🟢 5단계 — 메서드 ⭐⭐⭐

```java
public static int add(int a, int b) {
    return a + b;
}
```

- [ ] 매개변수·반환타입·`return` 을 구분해 작성한다
- [ ] `void` 메서드를 작성할 수 있다
- [ ] 메서드를 호출해 결과를 받을 수 있다
- [ ] **오버로딩**(같은 이름, 매개변수만 다름)을 설명할 수 있다

---

## 🟡 6단계 — 클래스 · 객체 ⭐⭐⭐

```java
Student s = new Student();
s.name = "홍길동";
```

- [ ] 클래스와 객체의 차이를 설명할 수 있다 (설계도 / 실체)
- [ ] 필드와 메서드를 가진 클래스를 작성할 수 있다
- [ ] `new` 로 객체를 생성할 수 있다
- [ ] 객체가 여러 개일 때 서로 다른 값을 가짐을 이해한다

**미니 과제**

- [ ] `Student` (name, age) + `introduce()` 작성
- [ ] 객체 2개 만들어 각각 출력

---

## 🟡 7단계 — 생성자 · 캡슐화 ⭐⭐⭐

```java
private String name;
public Student(String name) { this.name = name; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
```

- [ ] 생성자를 작성·호출할 수 있다
- [ ] `this` 의 의미를 안다
- [ ] 필드를 `private` 으로 둘 수 있다
- [ ] Getter / Setter를 작성할 수 있다
- [ ] 캡슐화 이유를 한 줄로 말할 수 있다
- [ ] `toString()` 을 오버라이딩할 수 있다

---

## 🟡 8단계 — 상속 · 다형성 ⭐⭐

```java
class Dog extends Animal {
    @Override
    void sound() { System.out.println("멍멍"); }
}
Animal a = new Dog();
```

- [ ] `extends` 로 상속할 수 있다
- [ ] `@Override` 로 메서드를 재정의할 수 있다
- [ ] **오버로딩 vs 오버라이딩** 을 구분한다
- [ ] 부모 타입 변수에 자식 객체를 담을 수 있다 (다형성)
- [ ] `super()` / `super.메서드()` 를 안다

---

## 🟡 9단계 — 인터페이스 ⭐⭐

```java
interface Payable { void pay(int amount); }
class CardPay implements Payable { ... }
```

- [ ] 인터페이스를 선언할 수 있다
- [ ] `implements` 로 구현할 수 있다
- [ ] “규약 / 약속”으로 설명할 수 있다
- [ ] 구현체를 바꿔도 호출 코드가 비슷해짐을 이해한다

---

## 🟠 10단계 — 예외 처리 ⭐⭐

```java
try {
    // ...
} catch (Exception e) {
    // ...
} finally {
    // ...
}
```

- [ ] `try-catch` 를 작성할 수 있다
- [ ] `finally` 역할을 안다
- [ ] `NumberFormatException` / `NullPointerException` 을 본 적 있다
- [ ] `throw` / `throws` 차이를 대략 안다
- [ ] 사용자 정의 예외 클래스를 만들어 본 적 있다 (심화)

---

## 🟠 11단계 — 컬렉션 ⭐⭐⭐

### ArrayList

- [ ] `ArrayList<String>` 을 만들고 `add` / `get` / `size` / `remove` 할 수 있다
- [ ] 배열과 ArrayList 차이를 말한다 (고정 vs 가변)
- [ ] for / 향상된 for로 순회할 수 있다

### HashMap

- [ ] `HashMap<String, Integer>` 에 `put` / `get` 할 수 있다
- [ ] 키·값 개념을 설명할 수 있다

**미니 과제**

- [ ] 학생 이름을 ArrayList로 관리
- [ ] 과목→점수를 HashMap으로 관리

---

## 🟠 12단계 — String · Scanner ⭐⭐⭐

### String

- [ ] `equals` 로 문자열을 비교한다 (`==` 사용 안 함)
- [ ] `length`, `contains`, `substring`, `split` 을 쓸 수 있다

### Scanner

- [ ] `nextLine` / `nextInt` 로 입력을 받는다
- [ ] `nextInt` 후 `nextLine` 버퍼 문제를 알고 있다

---

## 🔴 13단계 — 종합 실습 ⭐⭐⭐

아래 중 **1개 이상**을 혼자 완성할 수 있어야 합니다.

| 과제 | 필수 기술 |
|------|-----------|
| 회원/학생 ArrayList CRUD (메뉴) | 클래스, ArrayList, Scanner |
| 계좌 입출금 | 클래스, 예외 또는 조건 검증 |
| 도서/상품 관리 | 캡슐화, 검색·목록 |
| 인터페이스 결제 예제 | interface, 구현 클래스 |

- [ ] 메뉴(`1.등록 2.목록 3.종료`) 루프를 만들 수 있다
- [ ] 클래스 + ArrayList로 “추가·조회·삭제”를 구현할 수 있다
- [ ] null/빈 입력 등 간단한 예외·검증을 한다

**관련 문제 폴더**

- [crud-직원관리](./crud-직원관리/)
- [계좌관리-Account-BankApplication-문제.md](./계좌관리-Account-BankApplication-문제.md)
- [학생관리시스템-메뉴기반-캡슐화-문제.md](./학생관리시스템-메뉴기반-캡슐화-문제.md)
- [인터페이스/문제.md](./인터페이스/문제.md)

---

## 우선순위 정리

### 반드시 (⭐⭐⭐)

1. main · 출력 · 변수 · if/for  
2. 배열 · 메서드  
3. 클래스 · 객체 · 생성자 · Getter/Setter  
4. ArrayList · String.equals · Scanner  

###  ent든히 (⭐⭐)

5. 상속 · 오버라이딩 · 다형성  
6. 인터페이스  
7. try-catch  
8. HashMap  

### 다음 단계 (백엔드)

9. JDBC (Java ↔ MySQL)  
10. MyBatis / JPA  
11. Spring Boot  

---

## 자가 진단 (10문항)

할 수 있으면 ✅

1. [ ] Hello World를 처음부터 작성·실행  
2. [ ] if + for 로 학점·합계 프로그램  
3. [ ] 배열로 점수 평균  
4. [ ] 메서드 `add(a,b)` 작성  
5. [ ] Student 클래스 + 객체 2개  
6. [ ] private + getter/setter  
7. [ ] ArrayList에 학생 추가·출력  
8. [ ] 문자열 equals 비교  
9. [ ] try-catch 로 숫자 변환 오류 처리  
10. [ ] 메뉴형 CRUD 스케치(또는 완성)  

**8개 이상 ✅ → Java 기초 통과 → JDBC/Spring 준비**  
**5~7개 → 클래스·ArrayList 집중 복습**  
**4개 이하 → 1~5단계부터 다시**

---

## 강사 체크 (수업용)

| 주차 감각 | 볼 것 |
|-----------|--------|
| 초반 | main, 변수, if/for, 배열 |
| 중반 | 클래스, 생성자, 캡슐화 |
| 후반 | ArrayList CRUD, 상속·인터페이스, 예외 |

화면 한 줄:

> **문법으로 계산하고, 클래스로 묶고, ArrayList로 여러 개를 관리한다.**
