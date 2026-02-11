# Example5_PracticalUse.java 상세 해설

## 파일 개요

**파일명:** `Example5_PracticalUse.java`

**목적:** 실제 개발에서 자주 사용하는 업캐스팅 패턴을 보여주는 실전 예제

**시스템:** 직원 관리 시스템 (Employee Management System)

**주요 학습 내용:**
- 추상 클래스와 상속
- 업캐스팅과 다형성
- 배열과 업캐스팅
- 공통 메서드를 활용한 코드 재사용
- instanceof와 다운캐스팅

---

## 클래스 구조

```
Employee (추상 클래스)
├── FullTimeEmployee (정규직)
├── PartTimeEmployee (시간제)
└── Manager (관리자)
```

### 클래스 계층도

```
        Employee (추상)
         /  |  \
        /   |   \
FullTime  PartTime  Manager
```

---

## 클래스별 상세 설명

### 1. Employee 클래스 (추상 클래스)

**역할:** 모든 직원의 공통 속성과 행동을 정의하는 추상 클래스

#### 필드 (Field)

```java
protected String name;        // 이름
protected int id;             // 직원 ID
protected double baseSalary;  // 기본급
```

**설명:**
- `protected`: 자식 클래스에서 접근 가능
- 공통 속성: 모든 직원이 가지는 기본 정보

#### 생성자 (Constructor)

```java
Employee(String name, int id, double baseSalary) {
    this.name = name;
    this.id = id;
    this.baseSalary = baseSalary;
}
```

**설명:**
- 모든 직원의 공통 정보를 초기화
- 자식 클래스에서 `super()`로 호출

#### 추상 메서드

```java
abstract double calculateSalary();
```

**설명:**
- 각 직원 타입마다 급여 계산 방식이 다름
- 추상 메서드로 선언하여 자식 클래스에서 반드시 구현하도록 강제
- 다형성의 핵심: 각 객체가 자신의 방식으로 계산

#### 공통 메서드

**1. printInfo() - 정보 출력**
```java
void printInfo() {
    System.out.println("ID: " + id + ", 이름: " + name + 
                      ", 기본급: " + baseSalary + 
                      ", 실급여: " + calculateSalary());
}
```

**설명:**
- 모든 직원의 정보를 출력하는 공통 메서드
- `calculateSalary()` 호출 시 각 객체의 실제 타입에 따라 다른 메서드 실행 (다형성)
- 업캐스팅된 객체에서도 동일하게 작동

**2. getName() - 이름 반환**
```java
String getName() {
    return name;
}
```

**설명:**
- 이름을 반환하는 간단한 getter 메서드
- 검색 등에서 활용

---

### 2. FullTimeEmployee 클래스 (정규직)

**역할:** 정규직 직원을 나타내는 클래스

#### 필드 (Field)

```java
private double bonus;  // 보너스
```

**설명:**
- 정규직만 가지는 특별한 속성
- 기본급 + 보너스로 급여 계산

#### 생성자 (Constructor)

```java
FullTimeEmployee(String name, int id, double baseSalary, double bonus) {
    super(name, id, baseSalary);
    this.bonus = bonus;
}
```

**설명:**
- `super()`로 부모 생성자 호출
- 보너스 정보 추가

#### 메서드 오버라이딩

**calculateSalary() - 급여 계산**
```java
@Override
double calculateSalary() {
    return baseSalary + bonus;
}
```

**설명:**
- 정규직의 급여 계산 방식: 기본급 + 보너스
- `@Override` 어노테이션으로 오버라이딩 명시
- 업캐스팅 시에도 이 메서드가 호출됨 (동적 바인딩)

**workFullTime() - 정규직 업무**
```java
void workFullTime() {
    System.out.println(name + "이(가) 정규직으로 일합니다.");
}
```

**설명:**
- 정규직만 가지는 고유 메서드
- 업캐스팅된 상태에서는 호출 불가
- 다운캐스팅 후 호출 가능

---

### 3. PartTimeEmployee 클래스 (시간제)

**역할:** 시간제 직원을 나타내는 클래스

#### 필드 (Field)

```java
private int workHours;      // 근무 시간
private double hourlyRate;  // 시간당 임금
```

**설명:**
- 시간제 직원만 가지는 속성
- 근무 시간과 시간당 임금으로 급여 계산

#### 생성자 (Constructor)

```java
PartTimeEmployee(String name, int id, double baseSalary, int workHours, double hourlyRate) {
    super(name, id, baseSalary);
    this.workHours = workHours;
    this.hourlyRate = hourlyRate;
}
```

**설명:**
- 기본급은 0일 수 있음 (시간제는 시간당 임금만 받을 수도 있음)
- 근무 시간과 시간당 임금 설정

#### 메서드 오버라이딩

**calculateSalary() - 급여 계산**
```java
@Override
double calculateSalary() {
    return baseSalary + (workHours * hourlyRate);
}
```

**설명:**
- 시간제 급여 계산: 기본급 + (근무시간 × 시간당 임금)
- 각 직원 타입마다 다른 계산 방식

**workPartTime() - 시간제 업무**
```java
void workPartTime() {
    System.out.println(name + "이(가) 시간제로 " + workHours + "시간 일합니다.");
}
```

**설명:**
- 시간제 직원만 가지는 고유 메서드

---

### 4. Manager 클래스 (관리자)

**역할:** 관리자 직원을 나타내는 클래스

#### 필드 (Field)

```java
private double teamBonus;  // 팀 보너스
private int teamSize;      // 팀 크기
```

**설명:**
- 관리자만 가지는 속성
- 팀 보너스와 팀 크기로 급여 계산

#### 생성자 (Constructor)

```java
Manager(String name, int id, double baseSalary, double teamBonus, int teamSize) {
    super(name, id, baseSalary);
    this.teamBonus = teamBonus;
    this.teamSize = teamSize;
}
```

**설명:**
- 관리자의 기본 정보와 팀 정보 설정

#### 메서드 오버라이딩

**calculateSalary() - 급여 계산**
```java
@Override
double calculateSalary() {
    return baseSalary + teamBonus * teamSize;
}
```

**설명:**
- 관리자 급여 계산: 기본급 + (팀 보너스 × 팀 크기)
- 팀이 클수록 급여가 높아짐

**manageTeam() - 팀 관리**
```java
void manageTeam() {
    System.out.println(name + "이(가) " + teamSize + "명의 팀을 관리합니다.");
}
```

**설명:**
- 관리자만 가지는 고유 메서드

---

## main() 메서드 상세 분석

### 1. 객체 생성 및 업캐스팅

```java
Employee[] employees = new Employee[5];
employees[0] = new FullTimeEmployee("홍길동", 1, 3000000, 500000);
employees[1] = new PartTimeEmployee("김영희", 2, 0, 20, 15000);
employees[2] = new Manager("박철수", 3, 5000000, 100000, 5);
employees[3] = new FullTimeEmployee("이미영", 4, 2500000, 300000);
employees[4] = new PartTimeEmployee("최민수", 5, 0, 15, 12000);
```

**설명:**
- **업캐스팅**: 자식 클래스 객체를 부모 타입 배열에 저장
- 다양한 타입의 객체를 하나의 배열로 관리 가능
- 다형성의 핵심: 하나의 타입으로 여러 타입의 객체 처리

**업캐스팅 과정:**
```
FullTimeEmployee 객체 → Employee 타입으로 변환
PartTimeEmployee 객체 → Employee 타입으로 변환
Manager 객체 → Employee 타입으로 변환
```

---

### 2. 모든 직원 정보 출력

```java
System.out.println("=== 모든 직원 정보 출력 ===");
for (Employee emp : employees) {
    emp.printInfo();  // 다형성: 각 직원 타입에 맞는 계산
}
```

**설명:**
- 향상된 for문으로 모든 직원 순회
- `emp.printInfo()` 호출 시:
  - `printInfo()`는 부모 클래스의 메서드
  - 내부에서 `calculateSalary()` 호출
  - 각 객체의 실제 타입에 따라 다른 `calculateSalary()` 실행
  - **다형성(Polymorphism)**의 예시

**실행 과정:**
1. `employees[0]` (FullTimeEmployee):
   - `printInfo()` 호출
   - 내부에서 `calculateSalary()` 호출
   - FullTimeEmployee의 `calculateSalary()` 실행
   - 결과: 기본급 + 보너스

2. `employees[1]` (PartTimeEmployee):
   - PartTimeEmployee의 `calculateSalary()` 실행
   - 결과: 기본급 + (근무시간 × 시간당 임금)

3. `employees[2]` (Manager):
   - Manager의 `calculateSalary()` 실행
   - 결과: 기본급 + (팀 보너스 × 팀 크기)

**출력 예시:**
```
=== 모든 직원 정보 출력 ===
ID: 1, 이름: 홍길동, 기본급: 3000000.0, 실급여: 3500000.0
ID: 2, 이름: 김영희, 기본급: 0.0, 실급여: 300000.0
ID: 3, 이름: 박철수, 기본급: 5000000.0, 실급여: 5500000.0
ID: 4, 이름: 이미영, 기본급: 2500000.0, 실급여: 2800000.0
ID: 5, 이름: 최민수, 기본급: 0.0, 실급여: 180000.0
```

---

### 3. 전체 급여 합계 계산

```java
System.out.println("\n=== 전체 급여 합계 ===");
double totalSalary = 0;
for (Employee emp : employees) {
    totalSalary += emp.calculateSalary();
}
System.out.println("전체 급여 합계: " + String.format("%,.0f원", totalSalary));
```

**설명:**
- 모든 직원의 급여를 합산
- 업캐스팅의 장점: 하나의 반복문으로 모든 타입 처리 가능
- 각 객체의 실제 타입에 맞는 계산 방식 자동 적용

**계산 과정:**
- 홍길동: 3,500,000원
- 김영희: 300,000원
- 박철수: 5,500,000원
- 이미영: 2,800,000원
- 최민수: 180,000원
- **합계: 12,280,000원**

**업캐스팅의 장점:**
- 타입별로 다른 코드를 작성할 필요 없음
- 공통 인터페이스로 처리 가능
- 코드 재사용성 향상

---

### 4. 직원 타입별 처리

```java
System.out.println("\n=== 직원 타입별 처리 ===");
for (Employee emp : employees) {
    if (emp instanceof FullTimeEmployee) {
        FullTimeEmployee ft = (FullTimeEmployee) emp;
        ft.workFullTime();
    } else if (emp instanceof PartTimeEmployee) {
        PartTimeEmployee pt = (PartTimeEmployee) emp;
        pt.workPartTime();
    } else if (emp instanceof Manager) {
        Manager mgr = (Manager) emp;
        mgr.manageTeam();
    }
}
```

**설명:**
- `instanceof` 연산자로 객체의 실제 타입 확인
- **다운캐스팅(Downcasting)**: 부모 타입을 자식 타입으로 변환
- 자식 클래스에만 있는 메서드 호출

**실행 과정:**
1. `emp instanceof FullTimeEmployee`: FullTimeEmployee인지 확인
2. `(FullTimeEmployee) emp`: 다운캐스팅
3. `ft.workFullTime()`: 정규직 고유 메서드 호출

**출력 예시:**
```
=== 직원 타입별 처리 ===
홍길동이(가) 정규직으로 일합니다.
김영희이(가) 시간제로 20시간 일합니다.
박철수이(가) 5명의 팀을 관리합니다.
이미영이(가) 정규직으로 일합니다.
최민수이(가) 시간제로 15시간 일합니다.
```

**주의사항:**
- `instanceof` 확인 없이 다운캐스팅하면 `ClassCastException` 발생 가능
- 안전한 다운캐스팅을 위해 `instanceof` 사용 필수

---

### 5. 특정 직원 검색

```java
System.out.println("\n=== 특정 직원 검색 ===");
String searchName = "홍길동";
Employee found = findEmployee(employees, searchName);
if (found != null) {
    System.out.println("검색 결과:");
    found.printInfo();
} else {
    System.out.println(searchName + "을(를) 찾을 수 없습니다.");
}
```

**설명:**
- 이름으로 직원 검색
- `findEmployee()` 메서드 활용
- 업캐스팅을 활용한 공통 메서드의 예시

---

## findEmployee() 메서드 분석

```java
static Employee findEmployee(Employee[] employees, String name) {
    for (Employee emp : employees) {
        if (emp.getName().equals(name)) {
            return emp;
        }
    }
    return null;
}
```

**설명:**
- **업캐스팅의 활용**: 부모 타입을 매개변수로 받아 모든 자식 타입 처리 가능
- 공통 메서드(`getName()`)를 활용하여 타입에 관계없이 검색 가능
- 코드 재사용성 향상

**동작 과정:**
1. 배열의 모든 직원 순회
2. 각 직원의 이름과 검색 이름 비교
3. 일치하면 해당 객체 반환 (업캐스팅된 상태)
4. 못 찾으면 `null` 반환

**장점:**
- 타입별로 다른 검색 메서드를 만들 필요 없음
- 하나의 메서드로 모든 타입 처리
- 새로운 직원 타입 추가 시에도 수정 불필요

---

## 업캐스팅의 실전 활용 패턴

### 패턴 1: 배열과 업캐스팅

```java
Employee[] employees = new Employee[5];
employees[0] = new FullTimeEmployee(...);
employees[1] = new PartTimeEmployee(...);
```

**장점:**
- 다양한 타입의 객체를 하나의 배열로 관리
- 반복문으로 공통 처리 가능

---

### 패턴 2: 다형성을 활용한 공통 처리

```java
for (Employee emp : employees) {
    emp.calculateSalary();  // 각 객체의 실제 타입에 맞는 메서드 호출
}
```

**장점:**
- 타입별로 다른 코드 작성 불필요
- 코드 중복 제거
- 유지보수 용이

---

### 패턴 3: instanceof와 다운캐스팅

```java
if (emp instanceof FullTimeEmployee) {
    FullTimeEmployee ft = (FullTimeEmployee) emp;
    ft.workFullTime();
}
```

**장점:**
- 타입별 특수 기능 처리 가능
- 안전한 다운캐스팅

---

## 실행 결과

```
=== 실전 활용 예제: 직원 관리 시스템 ===

=== 모든 직원 정보 출력 ===
ID: 1, 이름: 홍길동, 기본급: 3000000.0, 실급여: 3500000.0
ID: 2, 이름: 김영희, 기본급: 0.0, 실급여: 300000.0
ID: 3, 이름: 박철수, 기본급: 5000000.0, 실급여: 5500000.0
ID: 4, 이름: 이미영, 기본급: 2500000.0, 실급여: 2800000.0
ID: 5, 이름: 최민수, 기본급: 0.0, 실급여: 180000.0

=== 전체 급여 합계 ===
전체 급여 합계: 12,280,000원

=== 직원 타입별 처리 ===
홍길동이(가) 정규직으로 일합니다.
김영희이(가) 시간제로 20시간 일합니다.
박철수이(가) 5명의 팀을 관리합니다.
이미영이(가) 정규직으로 일합니다.
최민수이(가) 시간제로 15시간 일합니다.

=== 특정 직원 검색 ===
검색 결과:
ID: 1, 이름: 홍길동, 기본급: 3000000.0, 실급여: 3500000.0
```

---

## 핵심 학습 포인트

### 1. 추상 클래스와 추상 메서드

**추상 클래스:**
- `abstract class Employee`: 인스턴스 생성 불가
- 공통 속성과 행동 정의
- 일부 메서드는 추상 메서드로 선언

**추상 메서드:**
- `abstract double calculateSalary()`
- 자식 클래스에서 반드시 구현해야 함
- 다형성의 기반이 됨

---

### 2. 업캐스팅과 다형성

**업캐스팅:**
```java
Employee emp = new FullTimeEmployee(...);
```

**다형성:**
- 같은 메서드 호출이 객체 타입에 따라 다른 동작
- `emp.calculateSalary()` 호출 시 실제 객체 타입에 맞는 메서드 실행

**장점:**
- 코드 재사용성 향상
- 유지보수 용이
- 확장성 향상

---

### 3. 배열과 업캐스팅

**배열 선언:**
```java
Employee[] employees = new Employee[5];
```

**다양한 타입 저장:**
```java
employees[0] = new FullTimeEmployee(...);
employees[1] = new PartTimeEmployee(...);
employees[2] = new Manager(...);
```

**공통 처리:**
```java
for (Employee emp : employees) {
    emp.calculateSalary();  // 각 타입에 맞는 계산
}
```

---

### 4. instanceof와 다운캐스팅

**타입 확인:**
```java
if (emp instanceof FullTimeEmployee) {
    // FullTimeEmployee인 경우
}
```

**다운캐스팅:**
```java
FullTimeEmployee ft = (FullTimeEmployee) emp;
```

**주의사항:**
- `instanceof` 확인 없이 다운캐스팅 시 오류 발생 가능
- 안전한 다운캐스팅 필수

---

### 5. 공통 메서드 활용

**공통 메서드:**
```java
static Employee findEmployee(Employee[] employees, String name) {
    // 모든 타입에 대해 동작
}
```

**장점:**
- 타입별로 다른 메서드 작성 불필요
- 코드 중복 제거
- 확장성 향상

---

## 실전 활용 시나리오

### 시나리오 1: 급여 관리 시스템

**요구사항:**
- 다양한 타입의 직원 관리
- 각 직원의 급여 계산
- 전체 급여 합계

**해결:**
- 업캐스팅으로 배열에 저장
- 다형성으로 각 타입에 맞는 계산
- 공통 메서드로 합계 계산

---

### 시나리오 2: 직원 검색 시스템

**요구사항:**
- 이름으로 직원 검색
- 모든 타입의 직원 검색 가능

**해결:**
- 공통 메서드(`getName()`) 활용
- 업캐스팅된 배열에서 검색
- 타입에 관계없이 동작

---

### 시나리오 3: 직원 타입별 특수 기능

**요구사항:**
- 정규직: 정규직 업무 처리
- 시간제: 시간제 업무 처리
- 관리자: 팀 관리 기능

**해결:**
- `instanceof`로 타입 확인
- 다운캐스팅 후 특수 메서드 호출

---

## 코드 개선 제안

### 개선 1: switch 표현식 사용 (Java 14+)

```java
for (Employee emp : employees) {
    switch (emp) {
        case FullTimeEmployee ft -> ft.workFullTime();
        case PartTimeEmployee pt -> pt.workPartTime();
        case Manager mgr -> mgr.manageTeam();
        default -> System.out.println("알 수 없는 타입");
    }
}
```

---

### 개선 2: 인터페이스 활용

```java
interface Workable {
    void work();
}

class FullTimeEmployee extends Employee implements Workable {
    @Override
    public void work() {
        workFullTime();
    }
}
```

---

### 개선 3: Stream API 활용 (Java 8+)

```java
// 전체 급여 합계
double totalSalary = Arrays.stream(employees)
    .mapToDouble(Employee::calculateSalary)
    .sum();

// 특정 이름 검색
Optional<Employee> found = Arrays.stream(employees)
    .filter(emp -> emp.getName().equals(searchName))
    .findFirst();
```

---

## 체크리스트

### 추상 클래스
- [ ] 추상 클래스 정의 방법
- [ ] 추상 메서드 선언
- [ ] 자식 클래스에서 구현

### 업캐스팅
- [ ] 업캐스팅 개념 이해
- [ ] 배열과 업캐스팅 활용
- [ ] 다형성 이해

### 다형성
- [ ] 메서드 오버라이딩
- [ ] 동적 바인딩 이해
- [ ] 공통 처리 방법

### 다운캐스팅
- [ ] instanceof 연산자 사용
- [ ] 안전한 다운캐스팅
- [ ] 타입별 특수 기능 처리

### 실전 활용
- [ ] 배열과 업캐스팅 패턴
- [ ] 공통 메서드 활용
- [ ] 코드 재사용성 향상

---

## 요약

### 핵심 개념

1. **추상 클래스**: 공통 속성과 행동 정의, 일부 메서드는 추상 메서드로
2. **업캐스팅**: 자식 객체를 부모 타입으로 참조
3. **다형성**: 같은 메서드 호출이 객체 타입에 따라 다른 동작
4. **배열 활용**: 다양한 타입을 하나의 배열로 관리
5. **공통 처리**: 타입에 관계없이 공통 메서드로 처리

### 실전 활용 패턴

- **배열과 업캐스팅**: 다양한 타입을 하나의 배열로 관리
- **다형성 활용**: 공통 메서드로 타입별 다른 동작 처리
- **instanceof와 다운캐스팅**: 타입별 특수 기능 처리

### 장점

- 코드 재사용성 향상
- 유지보수 용이
- 확장성 향상
- 코드 중복 제거

---

**작성일:** 2026-01-30  
**파일:** Example5_PracticalUse.java 상세 해설
