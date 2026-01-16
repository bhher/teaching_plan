# 생성자, Getter, Setter, 오버로딩, 오버라이딩, toString 완전 정리

## 📌 목차

1. [생성자 (Constructor)](#1-생성자-constructor)
2. [Getter 메서드](#2-getter-메서드)
3. [Setter 메서드](#3-setter-메서드)
4. [오버로딩 (Overloading)](#4-오버로딩-overloading)
5. [오버라이딩 (Overriding)](#5-오버라이딩-overriding)
6. [toString() 메서드](#6-tostring-메서드)
7. [종합 비교표](#7-종합-비교표)

---

## 1️⃣ 생성자 (Constructor)

### 생성자란?

**생성자(Constructor)**는 객체를 생성할 때 호출되는 특별한 메서드입니다. 객체의 초기 상태를 설정하는 역할을 합니다.

### 생성자의 특징

1. **클래스 이름과 동일**한 이름을 가집니다
2. **반환 타입이 없습니다** (void도 사용하지 않음)
3. **new 키워드**와 함께 호출됩니다
4. 객체 생성 시 **자동으로 호출**됩니다

### 기본 생성자 (Default Constructor)

```java
public class Student {
    private String name;
    private int age;
    
    // 기본 생성자 (매개변수 없음)
    public Student() {
        // 아무것도 하지 않아도 기본값으로 초기화됨
        // name = null, age = 0
    }
}
```

**기본 생성자 사용:**
```java
Student student = new Student();  // 기본 생성자 호출
```

### 매개변수가 있는 생성자

```java
public class Student {
    private String name;
    private int age;
    
    // 매개변수가 있는 생성자
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

**매개변수 생성자 사용:**
```java
Student student = new Student("홍길동", 20);  // 매개변수 전달
```

### 생성자 오버로딩

같은 클래스에 여러 개의 생성자를 정의할 수 있습니다. (매개변수의 개수나 타입이 달라야 함)

```java
public class Student {
    private String name;
    private int age;
    
    // 기본 생성자
    public Student() {
        this.name = "이름없음";
        this.age = 0;
    }
    
    // 이름만 받는 생성자
    public Student(String name) {
        this.name = name;
        this.age = 0;
    }
    
    // 이름과 나이를 받는 생성자
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

**사용 예시:**
```java
Student s1 = new Student();                    // 기본 생성자
Student s2 = new Student("홍길동");            // 이름만
Student s3 = new Student("홍길동", 20);        // 이름과 나이
```

### this 키워드

`this`는 현재 객체를 가리키는 참조 변수입니다.

```java
public class Student {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;    // 필드 name에 매개변수 name을 할당
        this.age = age;      // 필드 age에 매개변수 age를 할당
    }
}
```

**this를 사용하는 이유:**
- 매개변수 이름과 필드 이름이 같을 때 구분하기 위해
- 필드에 접근한다는 것을 명확히 표현하기 위해

### 생성자 체이닝 (Constructor Chaining)

생성자에서 다른 생성자를 호출할 수 있습니다. `this()` 키워드를 사용합니다.

```java
public class Student {
    private String name;
    private int age;
    
    // 기본 생성자
    public Student() {
        this("이름없음", 0);  // 다른 생성자 호출
    }
    
    // 이름만 받는 생성자
    public Student(String name) {
        this(name, 0);  // 다른 생성자 호출
    }
    
    // 이름과 나이를 받는 생성자 (실제 초기화 작업)
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

**주의사항:**
- `this()`는 생성자의 첫 번째 줄에만 올 수 있습니다
- 하나의 생성자에서 다른 생성자를 호출할 수 있습니다

### 생성자 vs 일반 메서드

| 구분 | 생성자 | 일반 메서드 |
|------|--------|------------|
| **이름** | 클래스 이름과 동일 | 자유롭게 지을 수 있음 |
| **반환 타입** | 없음 | 있음 (void 포함) |
| **호출 시점** | 객체 생성 시 자동 호출 | 명시적으로 호출 |
| **호출 방법** | `new 클래스명()` | `객체.메서드명()` |
| **목적** | 객체 초기화 | 기능 수행 |

---

## 2️⃣ Getter 메서드

### Getter란?

**Getter**는 private 필드의 값을 읽어오기 위한 메서드입니다.

### Getter 작성 규칙

1. 메서드 이름: `get + 필드명(첫 글자 대문자)`
2. 반환 타입: 필드의 타입과 동일
3. 매개변수: 없음
4. 접근 제어자: 보통 `public`

### Getter 예제

```java
public class Student {
    private String name;    // private 필드
    private int age;        // private 필드
    
    // name 필드의 Getter
    public String getName() {
        return name;
    }
    
    // age 필드의 Getter
    public int getAge() {
        return age;
    }
}
```

**Getter 사용:**
```java
Student student = new Student("홍길동", 20);

String name = student.getName();  // "홍길동" 반환
int age = student.getAge();       // 20 반환

System.out.println(name);  // 홍길동
System.out.println(age);   // 20
```

### Getter를 사용하는 이유

1. **캡슐화 (Encapsulation)**: 필드를 private으로 숨기고, Getter로만 접근 가능하게 함
2. **데이터 보호**: 직접 접근을 막아 데이터의 무결성 유지
3. **유연성**: 나중에 값을 반환하기 전에 추가 로직을 넣을 수 있음

### Getter 활용 예제

```java
public class BankAccount {
    private int balance;
    
    // Getter: 잔액을 읽을 때 로그를 남김
    public int getBalance() {
        System.out.println("잔액 조회: " + balance);
        return balance;
    }
}
```

---

## 3️⃣ Setter 메서드

### Setter란?

**Setter**는 private 필드의 값을 설정(변경)하기 위한 메서드입니다.

### Setter 작성 규칙

1. 메서드 이름: `set + 필드명(첫 글자 대문자)`
2. 반환 타입: `void`
3. 매개변수: 필드의 타입과 동일한 매개변수 하나
4. 접근 제어자: 보통 `public`

### Setter 예제

```java
public class Student {
    private String name;
    private int age;
    
    // name 필드의 Setter
    public void setName(String name) {
        this.name = name;
    }
    
    // age 필드의 Setter
    public void setAge(int age) {
        this.age = age;
    }
}
```

**Setter 사용:**
```java
Student student = new Student();

student.setName("홍길동");  // name 필드 설정
student.setAge(20);         // age 필드 설정

System.out.println(student.getName());  // 홍길동
System.out.println(student.getAge());   // 20
```

### Setter를 사용하는 이유

1. **캡슐화**: 필드를 private으로 숨기고, Setter로만 수정 가능하게 함
2. **유효성 검사**: 값 설정 전에 유효성을 검사할 수 있음
3. **데이터 보호**: 잘못된 값 설정을 방지

### Setter 활용 예제 (유효성 검사)

```java
public class Student {
    private String name;
    private int age;
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("이름은 비어있을 수 없습니다.");
            return;
        }
        this.name = name;
    }
    
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            System.out.println("나이는 0 이상 150 이하여야 합니다.");
            return;
        }
        this.age = age;
    }
}
```

**사용 예시:**
```java
Student student = new Student();

student.setAge(-10);  // 에러 메시지 출력, age는 변경되지 않음
student.setAge(20);   // 정상적으로 age 설정

student.setName("");  // 에러 메시지 출력, name은 변경되지 않음
student.setName("홍길동");  // 정상적으로 name 설정
```

### Getter와 Setter 함께 사용 예제

```java
public class Student {
    private String name;
    private int age;
    
    // 생성자
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getter
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Setter
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        if (age < 0) {
            System.out.println("나이는 0 이상이어야 합니다.");
            return;
        }
        this.age = age;
    }
}
```

**사용 예시:**
```java
// 생성자로 초기화
Student student = new Student("홍길동", 20);

// Getter로 읽기
System.out.println(student.getName());  // 홍길동
System.out.println(student.getAge());   // 20

// Setter로 수정
student.setName("김철수");
student.setAge(21);

// Getter로 다시 읽기
System.out.println(student.getName());  // 김철수
System.out.println(student.getAge());   // 21
```

---

## 4️⃣ 오버로딩 (Overloading)

### 오버로딩이란?

**오버로딩(Overloading)**은 같은 클래스 내에서 **같은 이름의 메서드를 여러 개 정의**하는 것을 말합니다.

### 오버로딩의 조건

1. **메서드 이름이 같아야 함**
2. **매개변수의 개수 또는 타입이 달라야 함**
3. 반환 타입은 상관없음 (같아도 됨)

### 오버로딩 예제

```java
public class Calculator {
    
    // 정수 두 개를 더하는 메서드
    public int add(int a, int b) {
        return a + b;
    }
    
    // 실수 두 개를 더하는 메서드 (매개변수 타입이 다름)
    public double add(double a, double b) {
        return a + b;
    }
    
    // 정수 세 개를 더하는 메서드 (매개변수 개수가 다름)
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

**사용 예시:**
```java
Calculator calc = new Calculator();

System.out.println(calc.add(10, 20));           // 30 (int, int)
System.out.println(calc.add(10.5, 20.5));       // 31.0 (double, double)
System.out.println(calc.add(10, 20, 30));       // 60 (int, int, int)
```

### 생성자 오버로딩

```java
public class Student {
    private String name;
    private int age;
    
    // 기본 생성자
    public Student() {
        this.name = "이름없음";
        this.age = 0;
    }
    
    // 이름만 받는 생성자
    public Student(String name) {
        this.name = name;
        this.age = 0;
    }
    
    // 이름과 나이를 받는 생성자
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

### 오버로딩 주의사항

**❌ 잘못된 오버로딩:**
```java
public int add(int a, int b) {
    return a + b;
}

// 오류! 매개변수 이름만 다르고 타입과 개수가 같음
public int add(int x, int y) {
    return x + y;
}

// 오류! 반환 타입만 다름 (매개변수가 동일)
public double add(int a, int b) {
    return (double)(a + b);
}
```

**✅ 올바른 오버로딩:**
```java
// 매개변수 개수가 다름
public int add(int a, int b) { }
public int add(int a, int b, int c) { }

// 매개변수 타입이 다름
public int add(int a, int b) { }
public double add(double a, double b) { }

// 매개변수 순서가 다름
public void print(String name, int age) { }
public void print(int age, String name) { }
```

### 오버로딩 vs 오버라이딩

| 구분 | 오버로딩 (Overloading) | 오버라이딩 (Overriding) |
|------|----------------------|----------------------|
| **위치** | 같은 클래스 내 | 자식 클래스에서 |
| **메서드 시그니처** | 다름 (매개변수 다름) | 같음 (동일) |
| **목적** | 다양한 방식으로 같은 기능 제공 | 부모 메서드 재정의 |
| **컴파일 시점** | 컴파일 타임에 결정 | 런타임에 결정 |

---

## 5️⃣ 오버라이딩 (Overriding)

### 오버라이딩이란?

**오버라이딩(Overriding)**은 부모 클래스의 메서드를 **자식 클래스에서 재정의**하는 것을 말합니다.

### 오버라이딩의 조건

1. **메서드 시그니처가 동일해야 함** (이름, 매개변수, 반환 타입)
2. **접근 제어자는 같거나 더 넓어야 함**
3. **@Override 어노테이션 사용 권장**

### 오버라이딩 예제

```java
// 부모 클래스
public class Animal {
    public void makeSound() {
        System.out.println("동물이 소리를 냅니다.");
    }
}

// 자식 클래스
public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("멍멍!");
    }
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("야옹!");
    }
}
```

**사용 예시:**
```java
Animal animal1 = new Dog();
animal1.makeSound();  // 멍멍! (자식 클래스의 메서드 호출)

Animal animal2 = new Cat();
animal2.makeSound();  // 야옹! (자식 클래스의 메서드 호출)

Animal animal3 = new Animal();
animal3.makeSound();  // 동물이 소리를 냅니다. (부모 클래스의 메서드)
```

### @Override 어노테이션

`@Override` 어노테이션을 사용하면:
- 컴파일러가 오버라이딩이 올바른지 확인해줌
- 코드의 가독성이 향상됨
- 실수를 방지할 수 있음

```java
public class Dog extends Animal {
    @Override  // 이 어노테이션으로 오버라이딩임을 명확히 표현
    public void makeSound() {
        System.out.println("멍멍!");
    }
}
```

### 오버라이딩 규칙

1. **메서드 시그니처가 정확히 같아야 함**
```java
// 부모 클래스
public void print(String name) { }

// 자식 클래스
@Override
public void print(String name) { }  // ✅ 올바름

@Override
public void print(String text) { }  // ✅ 올바름 (매개변수 이름은 상관없음)

@Override
public void print(int name) { }     // ❌ 오류! 매개변수 타입이 다름
```

2. **접근 제어자는 같거나 더 넓어야 함**
```java
// 부모 클래스
protected void method() { }

// 자식 클래스
@Override
protected void method() { }  // ✅ 올바름

@Override
public void method() { }     // ✅ 올바름 (더 넓은 접근 제어자)

@Override
private void method() { }    // ❌ 오류! 더 좁은 접근 제어자
```

3. **반환 타입은 같아야 함** (단, 공변 반환 타입은 예외)
```java
// 부모 클래스
public Animal getAnimal() { }

// 자식 클래스
@Override
public Animal getAnimal() { }  // ✅ 올바름

@Override
public Dog getAnimal() { }     // ✅ 올바름 (공변 반환 타입: 자식 타입 반환 가능)

@Override
public String getAnimal() { }  // ❌ 오류! 반환 타입이 다름
```

### super 키워드로 부모 메서드 호출

자식 클래스에서 부모 클래스의 메서드를 호출할 수 있습니다.

```java
public class Animal {
    public void makeSound() {
        System.out.println("동물이 소리를 냅니다.");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        super.makeSound();        // 부모 클래스의 makeSound() 호출
        System.out.println("멍멍!");
    }
}
```

**실행 결과:**
```
동물이 소리를 냅니다.
멍멍!
```

### 오버라이딩할 수 없는 메서드

1. **final 메서드**: 재정의 불가
```java
public class Parent {
    public final void method() { }  // 오버라이딩 불가
}
```

2. **static 메서드**: 오버라이딩이 아니라 숨김(Hiding)
```java
public class Parent {
    public static void method() { }
}

public class Child extends Parent {
    public static void method() { }  // 오버라이딩이 아님 (메서드 숨김)
}
```

3. **private 메서드**: 상속되지 않음
```java
public class Parent {
    private void method() { }  // 오버라이딩 불가 (상속되지 않음)
}
```

---

## 6️⃣ toString() 메서드

### toString()이란?

**toString()**은 객체를 문자열로 표현하는 메서드입니다. 모든 클래스가 상속받는 `Object` 클래스의 메서드입니다.

### 기본 toString() 메서드

모든 클래스는 자동으로 `Object` 클래스를 상속받으므로 `toString()` 메서드를 가지고 있습니다.

```java
public class Student {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// 사용
Student student = new Student("홍길동", 20);
System.out.println(student);           // Student@2f92e0f4 (객체의 해시코드)
System.out.println(student.toString()); // Student@2f92e0f4 (동일)
```

**기본 toString()의 문제점:**
- 의미 있는 정보를 제공하지 않음
- 클래스명@해시코드 형태로만 출력됨

### toString() 오버라이딩

의미 있는 정보를 제공하기 위해 `toString()` 메서드를 오버라이딩합니다.

```java
public class Student {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}

// 사용
Student student = new Student("홍길동", 20);
System.out.println(student);  // Student{name='홍길동', age=20}
```

### toString() 오버라이딩 예제

```java
public class Student {
    private String studentId;
    private String name;
    private int age;
    private String major;
    
    public Student(String studentId, String name, int age, String major) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
    }
    
    @Override
    public String toString() {
        return String.format("학번: %s, 이름: %s, 나이: %d, 전공: %s", 
            studentId, name, age, major);
    }
}

// 사용
Student student = new Student("2024001", "홍길동", 20, "컴퓨터공학");
System.out.println(student);
// 출력: 학번: 2024001, 이름: 홍길동, 나이: 20, 전공: 컴퓨터공학
```

### toString() 활용 예제 (점수 포함)

```java
public class Student {
    private String name;
    private int kor, eng, math;
    
    public Student(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
    
    public int calculateTotal() {
        return kor + eng + math;
    }
    
    public double calculateAverage() {
        return calculateTotal() / 3.0;
    }
    
    @Override
    public String toString() {
        return String.format("%s - 국어:%d 영어:%d 수학:%d 총점:%d 평균:%.2f",
            name, kor, eng, math, calculateTotal(), calculateAverage());
    }
}

// 사용
Student student = new Student("홍길동", 85, 90, 88);
System.out.println(student);
// 출력: 홍길동 - 국어:85 영어:90 수학:88 총점:263 평균:87.67
```

### toString()의 활용

1. **디버깅**: 객체의 상태를 쉽게 확인
2. **로깅**: 로그에 객체 정보 출력
3. **문자열 연결**: 문자열과 함께 사용할 때 자동 호출

```java
Student student = new Student("홍길동", 20);

// 자동으로 toString() 호출
System.out.println("학생 정보: " + student);
// 출력: 학생 정보: Student{name='홍길동', age=20}

// 로그에도 활용
System.out.println("등록된 학생: " + student);
```

---

## 7️⃣ 종합 비교표

### 생성자, Getter, Setter 비교

| 구분 | 생성자 | Getter | Setter |
|------|--------|--------|--------|
| **목적** | 객체 초기화 | 값 읽기 | 값 설정 |
| **이름** | 클래스명과 동일 | `get필드명` | `set필드명` |
| **반환 타입** | 없음 | 필드 타입 | void |
| **매개변수** | 있거나 없음 | 없음 | 필드 타입 1개 |
| **호출 시점** | 객체 생성 시 | 필요할 때 | 필요할 때 |
| **호출 방법** | `new 클래스명()` | `객체.get메서드()` | `객체.set메서드(값)` |

### 오버로딩 vs 오버라이딩 비교

| 구분 | 오버로딩 | 오버라이딩 |
|------|---------|-----------|
| **정의** | 같은 클래스 내에서 메서드 이름은 같고 매개변수가 다른 메서드 여러 개 | 자식 클래스에서 부모 메서드를 재정의 |
| **위치** | 같은 클래스 | 자식 클래스 |
| **메서드 시그니처** | 다름 | 같음 |
| **접근 제어자** | 상관없음 | 같거나 더 넓어야 함 |
| **반환 타입** | 상관없음 | 같아야 함 |
| **컴파일/실행 시점** | 컴파일 타임 | 런타임 (다형성) |
| **키워드** | 필요 없음 | `@Override`, `super` |
| **목적** | 유연한 메서드 호출 | 부모 메서드 재정의 |

---

## 8️⃣ 실전 예제

### 완전한 Student 클래스 예제

```java
public class Student {
    // 필드
    private String studentId;
    private String name;
    private int age;
    private String major;
    
    // 생성자 오버로딩
    public Student() {
        this("", "", 0, "");
    }
    
    public Student(String studentId, String name) {
        this(studentId, name, 0, "");
    }
    
    public Student(String studentId, String name, int age, String major) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
    }
    
    // Getter
    public String getStudentId() {
        return studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getMajor() {
        return major;
    }
    
    // Setter (유효성 검사 포함)
    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("학번은 비어있을 수 없습니다.");
            return;
        }
        this.studentId = studentId;
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("이름은 비어있을 수 없습니다.");
            return;
        }
        this.name = name;
    }
    
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            System.out.println("나이는 0 이상 150 이하여야 합니다.");
            return;
        }
        this.age = age;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    // toString 오버라이딩
    @Override
    public String toString() {
        return String.format("학번: %s, 이름: %s, 나이: %d, 전공: %s",
            studentId, name, age, major);
    }
}
```

### 사용 예시

```java
public class Main {
    public static void main(String[] args) {
        // 생성자 오버로딩 활용
        Student s1 = new Student();
        Student s2 = new Student("2024001", "홍길동");
        Student s3 = new Student("2024002", "김철수", 20, "컴퓨터공학");
        
        // Getter로 값 읽기
        System.out.println(s3.getStudentId());  // 2024002
        System.out.println(s3.getName());       // 김철수
        System.out.println(s3.getAge());        // 20
        System.out.println(s3.getMajor());      // 컴퓨터공학
        
        // Setter로 값 수정
        s3.setAge(21);
        s3.setMajor("소프트웨어");
        
        // toString() 자동 호출
        System.out.println(s3);
        // 출력: 학번: 2024002, 이름: 김철수, 나이: 21, 전공: 소프트웨어
    }
}
```

---

## 9️⃣ 상속 관계에서의 오버라이딩 예제

```java
// 부모 클래스
public class Person {
    protected String name;
    protected int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void introduce() {
        System.out.println(name + "입니다.");
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

// 자식 클래스
public class Student extends Person {
    private String studentId;
    private String major;
    
    public Student(String studentId, String name, int age, String major) {
        super(name, age);  // 부모 생성자 호출
        this.studentId = studentId;
        this.major = major;
    }
    
    // 부모 메서드 오버라이딩
    @Override
    public void introduce() {
        super.introduce();  // 부모 메서드 호출
        System.out.println("학번: " + studentId + ", 전공: " + major);
    }
    
    // toString 오버라이딩
    @Override
    public String toString() {
        return String.format("Student{studentId='%s', name='%s', age=%d, major='%s'}",
            studentId, name, age, major);
    }
}
```

**사용 예시:**
```java
Person person = new Person("홍길동", 30);
person.introduce();  // 홍길동입니다.
System.out.println(person);  // Person{name='홍길동', age=30}

Student student = new Student("2024001", "김철수", 20, "컴퓨터공학");
student.introduce();
// 김철수입니다.
// 학번: 2024001, 전공: 컴퓨터공학
System.out.println(student);  
// Student{studentId='2024001', name='김철수', age=20, major='컴퓨터공학'}
```

---

## 🔟 핵심 정리

### 생성자
- ✅ 클래스 이름과 동일, 반환 타입 없음
- ✅ 객체 생성 시 자동 호출
- ✅ 오버로딩 가능
- ✅ `this()`로 다른 생성자 호출 가능

### Getter/Setter
- ✅ 캡슐화를 위한 필드 접근 메서드
- ✅ Getter: 값을 읽을 때 사용
- ✅ Setter: 값을 설정할 때 사용 (유효성 검사 가능)
- ✅ 네이밍 규칙: `get필드명`, `set필드명`

### 오버로딩
- ✅ 같은 클래스 내에서 메서드 이름은 같고 매개변수가 다름
- ✅ 유연한 메서드 호출 제공
- ✅ 컴파일 타임에 결정

### 오버라이딩
- ✅ 자식 클래스에서 부모 메서드 재정의
- ✅ 메서드 시그니처가 동일해야 함
- ✅ `@Override` 어노테이션 사용 권장
- ✅ `super`로 부모 메서드 호출 가능
- ✅ 런타임에 결정 (다형성)

### toString()
- ✅ 객체를 문자열로 표현
- ✅ 디버깅과 로깅에 유용
- ✅ 오버라이딩하여 의미 있는 정보 제공
- ✅ 문자열 연결 시 자동 호출

---

## 📚 관련 자료

- [상속 완전 정리](./상속-추상클래스-인터페이스.md)
- [메서드와 객체 역할 분리](./메서드와_객체역할분리.md)
- [학생 관리 시스템](./학생관리시스템-실습문제.md)


