# Comparator 인터페이스를 이용한 학생 정렬 프로그램 상세 설명

## 목차

1. [프로그램 개요](#프로그램-개요)
2. [Comparable vs Comparator 비교](#comparable-vs-comparator-비교)
3. [전체 코드 구조](#전체-코드-구조)
4. [Comparator 인터페이스 이해](#comparator-인터페이스-이해)
5. [클래스별 상세 설명](#클래스별-상세-설명)
6. [Collections.sort() 오버로드](#collectionssort-오버로드)
7. [실행 흐름](#실행-흐름)
8. [실행 예시](#실행-예시)
9. [핵심 개념 정리](#핵심-개념-정리)
10. [다양한 Comparator 구현 방법](#다양한-comparator-구현-방법)
11. [실전 활용 팁](#실전-활용-팁)
12. [연습 문제](#연습-문제)

---

## 프로그램 개요

이 프로그램은 학생 정보를 입력받아 **사용자가 선택한 정렬 기준**에 따라 정렬하는 프로그램입니다.

**주요 특징:**
- `Comparator` 인터페이스를 사용하여 정렬 기준을 외부에서 정의
- 사용자가 정렬 기준을 선택할 수 있음 (이름, 나이, 학번)
- `Student` 클래스는 `Comparable`을 구현하지 않아도 됨
- 여러 정렬 기준을 동시에 사용 가능

**이전 예제와의 차이:**
- **Comparable 예제**: 정렬 기준이 클래스 내부에 고정 (`compareTo()`)
- **현재 예제**: 정렬 기준을 외부에서 선택 가능 (`Comparator`)

---

## Comparable vs Comparator 비교

### 비교 표

| 항목 | Comparable | Comparator |
|------|-----------|-----------|
| **인터페이스 위치** | 정렬할 클래스 내부 | 별도의 클래스로 구현 |
| **메서드** | `compareTo(T o)` | `compare(T o1, T o2)` |
| **정렬 기준** | 하나만 정의 가능 | 여러 개 정의 가능 |
| **클래스 수정** | 필요 (클래스에 구현) | 불필요 (별도 클래스) |
| **사용 시점** | 클래스 설계 시 | 런타임에 선택 가능 |
| **유연성** | 낮음 (하나의 기준만) | 높음 (여러 기준 선택) |
| **사용법** | `Collections.sort(list)` | `Collections.sort(list, comparator)` |

### 언제 무엇을 사용할까?

**Comparable을 사용하는 경우:**
- 클래스에 **자연스러운 정렬 순서**가 있는 경우
- 예: String (알파벳 순), Integer (숫자 순), Date (시간 순)
- 대부분의 경우 **하나의 정렬 기준**만 필요한 경우

**Comparator를 사용하는 경우:**
- **여러 정렬 기준**이 필요한 경우
- 클래스를 수정할 수 없는 경우 (라이브러리 클래스 등)
- **런타임에 정렬 기준을 선택**해야 하는 경우
- 클래스의 기본 정렬 순서와 다른 방식으로 정렬하고 싶은 경우

### 예시 비교

**Comparable 방식:**
```java
class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);  // 이름 순만 가능
    }
}

Collections.sort(students);  // 이름 순으로만 정렬 가능
```

**Comparator 방식:**
```java
class Student {
    // Comparable 구현 불필요
}

Collections.sort(students, new NameComparator());   // 이름 순
Collections.sort(students, new AgeComparator());    // 나이 순
Collections.sort(students, new IdComparator());     // 학번 순
```

---

## 전체 코드 구조

```java
package a0331.sort.hak4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        // ArrayList 생성
        // 학생 정보 입력
        // 정렬 기준 선택
        // 선택한 기준에 따라 Collections.sort() 호출
        // 결과 출력
    }
}

class Student {
    // 필드: name, age, studentId
    // 생성자, getter/setter, toString()
    // Comparable 구현 불필요!
}

class NameComparator implements Comparator<Student> {
    // 이름 순 정렬 기준
}

class AgeComparator implements Comparator<Student> {
    // 나이 순 정렬 기준
}

class IdComparator implements Comparator<Student> {
    // 학번 순 정렬 기준
}
```

---

## Comparator 인터페이스 이해

### 인터페이스 정의

**Comparator 인터페이스:**
```java
public interface Comparator<T> {
    int compare(T o1, T o2);
    // 기타 default 메서드들...
}
```

**핵심 메서드:**
- `compare(T o1, T o2)`: 두 객체를 비교하여 순서를 결정

**반환값:**
- **음수**: o1이 o2보다 작음 (o1이 앞에 옴)
- **0**: o1과 o2가 같음
- **양수**: o1이 o2보다 큼 (o1이 뒤에 옴)

### Comparator의 장점

1. **클래스 수정 불필요**: Student 클래스를 수정하지 않아도 됨
2. **여러 정렬 기준**: 여러 Comparator를 만들어서 사용 가능
3. **런타임 선택**: 사용자가 정렬 기준을 선택할 수 있음
4. **유연성**: 필요에 따라 새로운 정렬 기준 추가 가능

---

## 클래스별 상세 설명

### 1. Student 클래스

**특징:**
- `Comparable` 인터페이스를 **구현하지 않음**
- 정렬 기준은 별도의 Comparator 클래스에서 정의
- 클래스는 데이터만 담당 (단일 책임 원칙)

**코드:**
```java
class Student {
    private String name;
    private int age;
    private int studentId;
    
    // 생성자, getter/setter, toString()
    // Comparable 구현 없음!
}
```

**이전 예제와의 차이:**
- Comparable 예제: `class Student implements Comparable<Student>`
- 현재 예제: `class Student` (인터페이스 구현 없음)

### 2. NameComparator 클래스

**역할:** 이름 순으로 정렬하는 Comparator

**코드:**
```java
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
```

**동작 원리:**
1. `o1.getName()`: 첫 번째 학생의 이름
2. `o2.getName()`: 두 번째 학생의 이름
3. `compareTo()`: String의 `compareTo()` 메서드로 알파벳 순 비교
4. 반환값: 이름 비교 결과

**예시:**
```java
Student s1 = new Student("Alice", 20, 1);
Student s2 = new Student("Bob", 21, 2);

NameComparator comparator = new NameComparator();
int result = comparator.compare(s1, s2);
// "Alice".compareTo("Bob") → 음수 반환
// s1이 s2보다 앞에 옴
```

### 3. AgeComparator 클래스

**역할:** 나이 순으로 정렬하는 Comparator

**코드:**
```java
class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
```

**동작 원리:**
1. `o1.getAge()`: 첫 번째 학생의 나이
2. `o2.getAge()`: 두 번째 학생의 나이
3. `Integer.compare()`: 두 정수를 안전하게 비교
4. 반환값: 나이 비교 결과

**왜 Integer.compare()를 사용하는가?**
- 오버플로우 방지
- 명확한 비교 로직
- 가독성 향상

**주의사항:**
```java
// 위험한 방법 (오버플로우 가능)
return o1.getAge() - o2.getAge();

// 안전한 방법 (권장)
return Integer.compare(o1.getAge(), o2.getAge());
```

### 4. IdComparator 클래스

**역할:** 학번 순으로 정렬하는 Comparator

**코드:**
```java
class IdComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getStudentId(), o2.getStudentId());
    }
}
```

**동작 원리:**
- AgeComparator와 동일한 패턴
- 학번을 비교하여 정렬

### 5. Main1 클래스

#### 정렬 기준 선택

**코드:**
```java
System.out.println("정렬 기준을 선택하세요");
System.out.println("1. 이름");  
System.out.println("2. 나이");  
System.out.println("3. 학번");
int choice = sc.nextInt();
```

**사용자 입력에 따른 분기:**
```java
switch (choice) {
    case 1:
        Collections.sort(students, new NameComparator());
        break;
    case 2:
        Collections.sort(students, new AgeComparator());
        break;
    case 3:
        Collections.sort(students, new IdComparator());
        break;
    default:
        break;
}
```

**동작:**
- 사용자가 1을 선택 → 이름 순 정렬
- 사용자가 2를 선택 → 나이 순 정렬
- 사용자가 3을 선택 → 학번 순 정렬

---

## Collections.sort() 오버로드

### 두 가지 오버로드

**1. Comparable을 사용하는 경우:**
```java
public static <T extends Comparable<? super T>> void sort(List<T> list)
```

**사용법:**
```java
// Student가 Comparable을 구현한 경우
Collections.sort(students);
```

**2. Comparator를 사용하는 경우:**
```java
public static <T> void sort(List<T> list, Comparator<? super T> c)
```

**사용법:**
```java
// Comparator 객체를 전달
Collections.sort(students, new NameComparator());
Collections.sort(students, new AgeComparator());
Collections.sort(students, new IdComparator());
```

### 오버로드 선택

**컴파일러는 어떻게 선택할까?**
- 첫 번째 인자만 전달 → Comparable 버전 사용
- 두 번째 인자(Comparator)도 전달 → Comparator 버전 사용

**예시:**
```java
// Comparable 버전 (Student가 Comparable 구현 필요)
Collections.sort(students);

// Comparator 버전 (Student는 Comparable 불필요)
Collections.sort(students, new NameComparator());
```

---

## 실행 흐름

```
시작
  ↓
ArrayList<Student> 생성
  ↓
학생 수 입력 (n)
  ↓
┌─────────────────┐
│ 학생 정보 입력   │ ← n번 반복
│ - 이름          │
│ - 나이          │
│ - 학번          │
│ students.add()  │
└─────────────────┘
  ↓
정렬 기준 선택 메뉴 출력
  ↓
사용자 선택 (1, 2, 3)
  ↓
┌─────────────────┐
│ switch 문으로    │
│ 분기:           │
│ 1 → NameComparator│
│ 2 → AgeComparator │
│ 3 → IdComparator │
└─────────────────┘
  ↓
Collections.sort(students, comparator)
  ↓
┌─────────────────┐
│ 내부 동작:      │
│ Comparator의    │
│ compare() 호출  │
│ 정렬 수행       │
└─────────────────┘
  ↓
정렬된 결과 출력
  ↓
종료
```

### Collections.sort() 내부 동작 (Comparator 버전)

```
Collections.sort(students, comparator) 호출
  ↓
리스트의 각 요소를 비교
  ↓
┌─────────────────────────┐
│ comparator.compare(s1, s2)│
│   ↓                      │
│ 음수: s1이 s2보다 앞     │
│ 0:   s1과 s2가 같음      │
│ 양수: s1이 s2보다 뒤     │
└─────────────────────────┘
  ↓
비교 결과에 따라 요소 재배치
  ↓
정렬 완료
```

---

## 실행 예시

### 입력 예시

```
학생 수 입력 :3
학생 이름: Charlie
학생 나이: 20
학생 학번: 2024003
학생 이름: Alice
학생 나이: 19
학생 학번: 2024001
학생 이름: Bob
학생 나이: 21
학생 학번: 2024002
정렬 기준을 선택하세요
1. 이름
2. 나이
3. 학번
2
```

### 출력 예시 (나이 순 정렬)

```
정렬된 학생 목록:
Student [ name = Alice, age = 19, studentId = 2024001]
Student [ name = Charlie, age = 20, studentId = 2024003]
Student [ name = Bob, age = 21, studentId = 2024002]
```

### 다른 선택 예시

**사용자가 1을 선택한 경우 (이름 순):**
```
정렬된 학생 목록:
Student [ name = Alice, age = 19, studentId = 2024001]
Student [ name = Bob, age = 21, studentId = 2024002]
Student [ name = Charlie, age = 20, studentId = 2024003]
```

**사용자가 3을 선택한 경우 (학번 순):**
```
정렬된 학생 목록:
Student [ name = Alice, age = 19, studentId = 2024001]
Student [ name = Bob, age = 21, studentId = 2024002]
Student [ name = Charlie, age = 20, studentId = 2024003]
```

---

## 핵심 개념 정리

### 1. Comparator 인터페이스

**정의:**
- 두 객체를 비교하여 정렬 순서를 결정하는 인터페이스
- `compare(T o1, T o2)` 메서드를 구현해야 함

**특징:**
- 클래스를 수정하지 않아도 정렬 기준 정의 가능
- 여러 정렬 기준을 동시에 사용 가능
- 런타임에 정렬 기준 선택 가능

**구현 방법:**
```java
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
```

### 2. compare() 메서드

**역할:** 두 객체를 비교하여 순서를 결정

**시그니처:**
```java
int compare(T o1, T o2)
```

**반환값:**
- **음수**: o1이 o2보다 작음 (o1이 앞에 옴)
- **0**: o1과 o2가 같음
- **양수**: o1이 o2보다 큼 (o1이 뒤에 옴)

**구현 패턴:**
```java
// 오름차순
return o1.getField().compareTo(o2.getField());

// 내림차순
return o2.getField().compareTo(o1.getField());
```

### 3. Collections.sort() 오버로드

**두 가지 버전:**

**버전 1: Comparable 사용**
```java
Collections.sort(list);  // list의 요소가 Comparable 구현 필요
```

**버전 2: Comparator 사용**
```java
Collections.sort(list, comparator);  // Comparator 객체 전달
```

**선택 기준:**
- 클래스를 수정할 수 있고 하나의 정렬 기준만 필요 → Comparable
- 여러 정렬 기준이 필요하거나 클래스를 수정할 수 없음 → Comparator

### 4. Comparable vs Comparator

**비교:**

| 항목 | Comparable | Comparator |
|------|-----------|-----------|
| **위치** | 클래스 내부 | 별도 클래스 |
| **메서드** | `compareTo(T o)` | `compare(T o1, T o2)` |
| **정렬 기준** | 하나만 | 여러 개 가능 |
| **클래스 수정** | 필요 | 불필요 |
| **유연성** | 낮음 | 높음 |

**사용 시나리오:**

**Comparable:**
- 클래스에 자연스러운 정렬 순서가 있는 경우
- 예: String, Integer, Date

**Comparator:**
- 여러 정렬 기준이 필요한 경우
- 클래스를 수정할 수 없는 경우
- 런타임에 정렬 기준을 선택해야 하는 경우

### 5. Integer.compare() 사용

**역할:** 두 정수를 안전하게 비교

**사용법:**
```java
Integer.compare(a, b)
```

**반환값:**
- a < b: 음수
- a == b: 0
- a > b: 양수

**왜 사용하는가?**
- 오버플로우 방지
- 명확한 비교 로직
- 가독성 향상

**비교:**
```java
// 위험한 방법
return o1.getAge() - o2.getAge();  // 오버플로우 가능!

// 안전한 방법 (권장)
return Integer.compare(o1.getAge(), o2.getAge());
```

### 6. switch 문과 Comparator 선택

**코드:**
```java
switch (choice) {
    case 1:
        Collections.sort(students, new NameComparator());
        break;
    case 2:
        Collections.sort(students, new AgeComparator());
        break;
    case 3:
        Collections.sort(students, new IdComparator());
        break;
}
```

**동작:**
- 사용자 입력에 따라 적절한 Comparator 객체 생성
- 해당 Comparator로 정렬 수행

---

## 다양한 Comparator 구현 방법

### 방법 1: 별도 클래스로 구현 (현재 코드)

```java
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

Collections.sort(students, new NameComparator());
```

**장점:**
- 코드가 명확하고 읽기 쉬움
- 재사용 가능
- 테스트하기 쉬움

**단점:**
- 클래스가 많아짐
- 간단한 비교에도 클래스를 만들어야 함

### 방법 2: 익명 클래스 사용

```java
Collections.sort(students, new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
});
```

**장점:**
- 별도 클래스 불필요
- 한 번만 사용하는 경우 유용

**단점:**
- 코드가 길어짐
- 재사용 불가

### 방법 3: 람다 표현식 사용 (Java 8+)

```java
Collections.sort(students, (o1, o2) -> o1.getName().compareTo(o2.getName()));
```

**또는:**

```java
Collections.sort(students, Comparator.comparing(Student::getName));
```

**장점:**
- 코드가 매우 간결
- 가독성 향상

**단점:**
- Java 8 이상 필요
- 복잡한 비교 로직에는 부적합

### 방법 4: 메서드 참조 사용 (Java 8+)

```java
// 이름 순
Collections.sort(students, Comparator.comparing(Student::getName));

// 나이 순
Collections.sort(students, Comparator.comparing(Student::getAge));

// 학번 순
Collections.sort(students, Comparator.comparing(Student::getStudentId));
```

**장점:**
- 매우 간결하고 읽기 쉬움
- 타입 안정성 보장

**단점:**
- Java 8 이상 필요
- 복잡한 비교 로직에는 부적합

### 방법 5: 다중 정렬 기준

```java
class NameAgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        // 1순위: 이름
        int nameCompare = o1.getName().compareTo(o2.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }
        // 2순위: 나이 (이름이 같을 때만)
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
```

**Java 8+ 방식:**
```java
Collections.sort(students, 
    Comparator.comparing(Student::getName)
              .thenComparing(Student::getAge));
```

---

## 실전 활용 팁

### 팁 1: Comparator를 상수로 정의

```java
class Student {
    // ...
    
    public static final Comparator<Student> BY_NAME = 
        Comparator.comparing(Student::getName);
    
    public static final Comparator<Student> BY_AGE = 
        Comparator.comparing(Student::getAge);
    
    public static final Comparator<Student> BY_ID = 
        Comparator.comparing(Student::getStudentId);
}

// 사용
Collections.sort(students, Student.BY_NAME);
```

### 팁 2: 역순 정렬

```java
// 방법 1: reversed() 사용
Collections.sort(students, 
    Comparator.comparing(Student::getName).reversed());

// 방법 2: compare()에서 순서 바꾸기
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o2.getName().compareTo(o1.getName());  // 순서 반대
    }
}
```

### 팁 3: null 값 처리

```java
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null) return -1;  // null을 앞에
        if (o2 == null) return 1;   // null을 앞에
        return o1.getName().compareTo(o2.getName());
    }
}
```

**Java 8+ 방식:**
```java
Collections.sort(students, 
    Comparator.nullsFirst(Comparator.comparing(Student::getName)));
```

### 팁 4: 대소문자 구분 없이 정렬

```java
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
```

### 팁 5: 여러 기준으로 정렬

```java
// 이름 → 나이 → 학번 순
Collections.sort(students,
    Comparator.comparing(Student::getName)
              .thenComparing(Student::getAge)
              .thenComparing(Student::getStudentId));
```

---

## 연습 문제

### 문제 1: 내림차순 정렬

각 Comparator를 수정하여 내림차순으로 정렬하도록 변경하세요.

**힌트:**
```java
// 방법 1: compare()에서 순서 바꾸기
return o2.getName().compareTo(o1.getName());

// 방법 2: reversed() 사용 (Java 8+)
Collections.sort(students, 
    Comparator.comparing(Student::getName).reversed());
```

### 문제 2: 새로운 정렬 기준 추가

나이와 이름을 모두 고려하는 Comparator를 추가하세요. 나이가 같으면 이름 순으로 정렬하세요.

### 문제 3: 메뉴 확장

정렬 기준 메뉴에 "4. 나이 내림차순", "5. 이름 내림차순" 옵션을 추가하세요.

### 문제 4: 람다 표현식으로 변경

현재 코드를 람다 표현식으로 변경하세요.

**힌트:**
```java
Collections.sort(students, 
    (o1, o2) -> o1.getName().compareTo(o2.getName()));
```

### 문제 5: Comparator.comparing() 사용

Java 8의 `Comparator.comparing()`을 사용하여 코드를 간소화하세요.

**힌트:**
```java
Collections.sort(students, Comparator.comparing(Student::getName));
```

### 문제 6: 다중 정렬 기준

이름이 같으면 나이 순, 나이도 같으면 학번 순으로 정렬하는 Comparator를 만드세요.

---

## 세 가지 정렬 방법 비교 요약

### 방법 1: 직접 구현 (삽입 정렬)

```java
private static void InsertionSort(Student[] students) {
    // 알고리즘 직접 구현
}
```

**특징:**
- 알고리즘 학습에 유용
- 코드가 복잡함
- 정렬 기준 변경 시 코드 수정 필요

### 방법 2: Comparable 사용

```java
class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }
}

Collections.sort(students);
```

**특징:**
- 하나의 정렬 기준만 가능
- 클래스 내부에 정렬 기준 정의
- 간단하고 직관적

### 방법 3: Comparator 사용 (현재 예제)

```java
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

Collections.sort(students, new NameComparator());
```

**특징:**
- 여러 정렬 기준 가능
- 클래스 수정 불필요
- 런타임에 정렬 기준 선택 가능
- 가장 유연함

---

## 요약

### 핵심 정리

1. **Comparator 인터페이스**는 클래스를 수정하지 않고 정렬 기준을 정의할 수 있게 해줍니다.
2. **compare()** 메서드는 두 객체를 비교하여 순서를 결정합니다.
3. **Collections.sort()**는 Comparator 객체를 받아서 정렬을 수행합니다.
4. **여러 Comparator**를 만들어서 다양한 정렬 기준을 사용할 수 있습니다.
5. **Integer.compare()**는 정수를 안전하게 비교합니다.

### 학습 포인트

- ✅ Comparator 인터페이스의 개념과 사용법
- ✅ compare() 메서드 구현 방법
- ✅ Collections.sort()의 두 가지 오버로드
- ✅ Comparable과 Comparator의 차이점
- ✅ 다양한 Comparator 구현 방법
- ✅ 실전 활용 팁

### 선택 가이드

**언제 Comparable을 사용할까?**
- 클래스에 자연스러운 정렬 순서가 있는 경우
- 하나의 정렬 기준만 필요한 경우

**언제 Comparator를 사용할까?**
- 여러 정렬 기준이 필요한 경우
- 클래스를 수정할 수 없는 경우
- 런타임에 정렬 기준을 선택해야 하는 경우

---

**작성일:** 2026-01-30  
**범위:** Comparator 인터페이스를 이용한 학생 정렬 프로그램 상세 설명
