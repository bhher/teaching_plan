# 메서드 참조와 Comparator.comparing()을 이용한 학생 정렬 프로그램 상세 설명

## 목차

1. [프로그램 개요](#프로그램-개요)
2. [이전 예제들과의 비교](#이전-예제들과의-비교)
3. [전체 코드 구조](#전체-코드-구조)
4. [핵심 개념 이해](#핵심-개념-이해)
5. [클래스별 상세 설명](#클래스별-상세-설명)
6. [메서드 참조 (Method Reference)](#메서드-참조-method-reference)
7. [Comparator.comparing() 메서드](#comparatorcomparing-메서드)
8. [ArrayList.sort() vs Collections.sort()](#arraylistsort-vs-collectionssort)
9. [실행 흐름](#실행-흐름)
10. [실행 예시](#실행-예시)
11. [핵심 개념 정리](#핵심-개념-정리)
12. [다양한 사용 방법](#다양한-사용-방법)
13. [실전 활용 팁](#실전-활용-팁)
14. [연습 문제](#연습-문제)

---

## 프로그램 개요

이 프로그램은 **Java 8의 새로운 기능**을 활용하여 학생 정보를 정렬하는 프로그램입니다.

**주요 특징:**
- `ArrayList.sort()` 메서드 사용
- `Comparator.comparing()` 정적 메서드 활용
- **메서드 참조(Method Reference)** 사용 (`Student::getName`)
- 별도의 Comparator 클래스 불필요
- 코드가 매우 간결하고 읽기 쉬움

**이전 예제와의 차이:**
- **이전**: 별도의 Comparator 클래스 작성 필요
- **현재**: 메서드 참조로 한 줄로 해결

---

## 이전 예제들과의 비교

### 세 가지 방법 비교

| 항목 | Comparator 클래스 | Collections.sort() | ArrayList.sort() + 메서드 참조 |
|------|------------------|-------------------|---------------------------|
| **코드 길이** | 길음 (별도 클래스) | 중간 | 짧음 (한 줄) |
| **가독성** | 보통 | 좋음 | 매우 좋음 |
| **Java 버전** | Java 1.2+ | Java 1.2+ | Java 8+ |
| **별도 클래스** | 필요 | 필요 | 불필요 |
| **메서드 참조** | 불가 | 불가 | 가능 |

### 코드 비교

**방법 1: 별도 Comparator 클래스 (이전 예제)**
```java
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

Collections.sort(students, new NameComparator());
```

**방법 2: 익명 클래스**
```java
Collections.sort(students, new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
});
```

**방법 3: 람다 표현식**
```java
Collections.sort(students, (o1, o2) -> o1.getName().compareTo(o2.getName()));
```

**방법 4: 메서드 참조 (현재 예제)**
```java
students.sort(Comparator.comparing(Student::getName));
```

**결론:** 메서드 참조가 가장 간결하고 읽기 쉽습니다!

---

## 전체 코드 구조

```java
package a0331.sort.hak5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        // ArrayList 생성
        // 학생 정보 입력
        // 정렬 기준 선택
        // ArrayList.sort() + Comparator.comparing() 사용
        // 결과 출력
    }
}

class Student {
    // 필드: name, age, studentId
    // 생성자, getter, toString()
    // Comparable 구현 불필요!
    // 별도 Comparator 클래스 불필요!
}
```

**특징:**
- 별도의 Comparator 클래스가 없음
- Student 클래스는 단순한 데이터 클래스
- 정렬 로직이 main 메서드에 한 줄로 표현됨

---

## 핵심 개념 이해

### 1. 메서드 참조 (Method Reference)

**정의:** 기존 메서드를 참조하여 람다 표현식을 더 간결하게 만드는 기능

**문법:**
```java
클래스명::메서드명
```

**예시:**
```java
Student::getName  // Student 클래스의 getName() 메서드 참조
```

**등가 표현:**
```java
// 람다 표현식
student -> student.getName()

// 메서드 참조
Student::getName
```

### 2. Comparator.comparing()

**정의:** 객체의 특정 필드를 추출하여 Comparator를 생성하는 정적 메서드

**시그니처:**
```java
public static <T, U extends Comparable<? super U>> 
    Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor)
```

**사용법:**
```java
Comparator.comparing(Student::getName)
```

**동작:**
1. `Student::getName`으로 각 Student 객체에서 이름 추출
2. 추출한 이름들을 비교하는 Comparator 생성
3. 반환된 Comparator로 정렬 수행

### 3. ArrayList.sort()

**정의:** ArrayList에 직접 정렬 기능을 제공하는 메서드 (Java 8+)

**시그니처:**
```java
public void sort(Comparator<? super E> c)
```

**사용법:**
```java
students.sort(Comparator.comparing(Student::getName));
```

**장점:**
- `Collections.sort()`보다 더 직관적
- 리스트 자체에 정렬 기능이 있음을 명확히 표현

---

## 클래스별 상세 설명

### 1. Student 클래스

**특징:**
- 매우 단순한 데이터 클래스
- `Comparable` 구현 불필요
- 별도의 Comparator 클래스 불필요
- getter 메서드만 있으면 됨

**코드:**
```java
class Student {
    private String name;
    private int age;
    private int studentId;

    public Student(String name, int age, int studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "이름: " + name + ", 나이: " + age + ", 학번: " + studentId;
    }
}
```

**주의사항:**
- 정렬에 사용할 필드의 **getter 메서드**가 반드시 필요함
- 메서드 참조는 getter 메서드를 참조함

### 2. Main1 클래스

#### 정렬 기준 선택 및 정렬

**코드:**
```java
switch (choice) {
    case 1:
        students.sort(Comparator.comparing(Student::getName));
        break;
    case 2:
        students.sort(Comparator.comparingInt(Student::getAge));
        break;
    case 3:
        students.sort(Comparator.comparingInt(Student::getStudentId));
        break;    
    default:
        System.out.println("잘못된 선택입니다.");
        break;
}
```

**각 케이스 설명:**

**케이스 1: 이름 순 정렬**
```java
students.sort(Comparator.comparing(Student::getName));
```
- `Student::getName`: 각 Student 객체의 getName() 메서드 참조
- `Comparator.comparing()`: 이름을 비교하는 Comparator 생성
- `students.sort()`: 생성된 Comparator로 정렬 수행

**케이스 2: 나이 순 정렬**
```java
students.sort(Comparator.comparingInt(Student::getAge));
```
- `Student::getAge`: 각 Student 객체의 getAge() 메서드 참조
- `Comparator.comparingInt()`: int 타입 전용 비교 메서드
- 더 효율적 (박싱/언박싱 없음)

**케이스 3: 학번 순 정렬**
```java
students.sort(Comparator.comparingInt(Student::getStudentId));
```
- 나이 순 정렬과 동일한 패턴
- 학번(int 타입)을 비교

---

## 메서드 참조 (Method Reference)

### 메서드 참조의 종류

**1. 인스턴스 메서드 참조**
```java
Student::getName
```
- 특정 타입의 인스턴스 메서드를 참조
- 가장 많이 사용되는 형태

**2. 정적 메서드 참조**
```java
Integer::compare
```

**3. 특정 객체의 인스턴스 메서드 참조**
```java
student::getName
```

**4. 생성자 참조**
```java
Student::new
```

### 메서드 참조 vs 람다 표현식

**람다 표현식:**
```java
students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
```

**메서드 참조:**
```java
students.sort(Comparator.comparing(Student::getName));
```

**장점:**
- 코드가 더 간결함
- 가독성 향상
- 타입 추론이 더 명확함

### 메서드 참조 동작 원리

**예시:**
```java
Comparator.comparing(Student::getName)
```

**동작 과정:**
1. `Student::getName`은 `Function<Student, String>`과 동일
2. 각 Student 객체에 대해 `getName()` 호출
3. 반환된 String 값들을 비교
4. Comparator 생성

**등가 코드:**
```java
// 메서드 참조
Comparator.comparing(Student::getName)

// 람다 표현식
Comparator.comparing(student -> student.getName())

// 익명 클래스
new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
```

---

## Comparator.comparing() 메서드

### comparing() 메서드

**시그니처:**
```java
public static <T, U extends Comparable<? super U>> 
    Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor)
```

**매개변수:**
- `keyExtractor`: 객체에서 비교할 키를 추출하는 함수

**반환값:**
- 추출한 키를 비교하는 Comparator

**사용 예시:**
```java
// String 타입 필드 (Comparable 구현)
Comparator.comparing(Student::getName)

// Integer 타입 필드 (Comparable 구현)
Comparator.comparing(Student::getAge)  // getAge()가 Integer 반환하는 경우
```

### comparingInt() 메서드

**시그니처:**
```java
public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> keyExtractor)
```

**특징:**
- int 타입 전용
- 박싱/언박싱 없이 직접 비교
- 성능상 이점

**사용 예시:**
```java
// int 타입 필드
Comparator.comparingInt(Student::getAge)
Comparator.comparingInt(Student::getStudentId)
```

### comparing() vs comparingInt()

**비교:**

| 항목 | comparing() | comparingInt() |
|------|------------|---------------|
| **타입** | Comparable 구현 타입 | int 전용 |
| **박싱** | 발생 가능 | 없음 |
| **성능** | 약간 느림 | 더 빠름 |
| **사용** | String, Integer 등 | int, long 등 |

**예시:**
```java
// String은 Comparable이므로 comparing() 사용
Comparator.comparing(Student::getName)

// int는 기본 타입이므로 comparingInt() 사용 (권장)
Comparator.comparingInt(Student::getAge)

// comparing()도 가능하지만 비효율적
Comparator.comparing(Student::getAge)  // 자동 박싱 발생
```

---

## ArrayList.sort() vs Collections.sort()

### 비교

| 항목 | Collections.sort() | ArrayList.sort() |
|------|-------------------|-----------------|
| **메서드 위치** | Collections 클래스 | ArrayList 인스턴스 |
| **호출 방법** | `Collections.sort(list)` | `list.sort(comparator)` |
| **가독성** | 보통 | 더 직관적 |
| **Java 버전** | Java 1.2+ | Java 8+ |

### 사용 예시

**Collections.sort():**
```java
Collections.sort(students, Comparator.comparing(Student::getName));
```

**ArrayList.sort():**
```java
students.sort(Comparator.comparing(Student::getName));
```

**결론:** `ArrayList.sort()`가 더 직관적이고 읽기 쉽습니다!

### 내부 구현

**ArrayList.sort() 내부:**
```java
public void sort(Comparator<? super E> c) {
    final int expectedModCount = modCount;
    Arrays.sort((E[]) elementData, 0, size, c);
    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
    modCount++;
}
```

**실제로는:** 내부적으로 `Collections.sort()`와 동일한 알고리즘 사용

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
│ 1 → comparing(  │
│     Student::   │
│     getName)    │
│ 2 → comparingInt│
│     (Student::  │
│     getAge)     │
│ 3 → comparingInt│
│     (Student::  │
│     getStudentId│
└─────────────────┘
  ↓
students.sort(comparator)
  ↓
┌─────────────────┐
│ 내부 동작:      │
│ 1. 메서드 참조로│
│    키 추출      │
│ 2. 추출한 키로  │
│    비교         │
│ 3. 정렬 수행    │
└─────────────────┘
  ↓
정렬된 결과 출력
  ↓
종료
```

### 정렬 과정 상세

**예시: 이름 순 정렬**

```
students.sort(Comparator.comparing(Student::getName))
  ↓
각 Student 객체에 대해 getName() 호출
  ↓
이름 추출: ["Charlie", "Alice", "Bob"]
  ↓
이름들을 비교하여 순서 결정
  ↓
["Alice", "Bob", "Charlie"] 순서로 재배치
  ↓
정렬 완료
```

---

## 실행 예시

### 입력 예시

```
학생 수를 입력하세요: 3
학생 이름: Charlie
학생 나이: 20
학생 학번: 2024003
학생 이름: Alice
학생 나이: 19
학생 학번: 2024001
학생 이름: Bob
학생 나이: 21
학생 학번: 2024002
정렬 기준을 선택하세요:
1. 이름
2. 나이
3. 학번
1
```

### 출력 예시 (이름 순)

```
정렬된 학생 목록:
이름: Alice, 나이: 19, 학번: 2024001
이름: Bob, 나이: 21, 학번: 2024002
이름: Charlie, 나이: 20, 학번: 2024003
```

### 다른 선택 예시

**사용자가 2를 선택한 경우 (나이 순):**
```
정렬된 학생 목록:
이름: Alice, 나이: 19, 학번: 2024001
이름: Charlie, 나이: 20, 학번: 2024003
이름: Bob, 나이: 21, 학번: 2024002
```

**사용자가 3을 선택한 경우 (학번 순):**
```
정렬된 학생 목록:
이름: Alice, 나이: 19, 학번: 2024001
이름: Bob, 나이: 21, 학번: 2024002
이름: Charlie, 나이: 20, 학번: 2024003
```

---

## 핵심 개념 정리

### 1. 메서드 참조 (Method Reference)

**정의:** 기존 메서드를 참조하여 람다 표현식을 간결하게 만드는 기능

**문법:**
```java
클래스명::메서드명
```

**종류:**
- 인스턴스 메서드 참조: `Student::getName`
- 정적 메서드 참조: `Integer::compare`
- 생성자 참조: `Student::new`

**등가 표현:**
```java
// 람다 표현식
student -> student.getName()

// 메서드 참조
Student::getName
```

### 2. Comparator.comparing()

**역할:** 객체의 특정 필드를 추출하여 Comparator를 생성

**시그니처:**
```java
public static <T, U extends Comparable<? super U>> 
    Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor)
```

**사용법:**
```java
Comparator.comparing(Student::getName)
```

**동작:**
1. 각 객체에서 키 추출 (getName() 호출)
2. 추출한 키들을 비교하는 Comparator 생성
3. 반환

### 3. Comparator.comparingInt()

**역할:** int 타입 필드를 추출하여 Comparator를 생성

**시그니처:**
```java
public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> keyExtractor)
```

**특징:**
- int 전용
- 박싱/언박싱 없음
- 성능상 이점

**사용법:**
```java
Comparator.comparingInt(Student::getAge)
```

### 4. ArrayList.sort()

**역할:** 리스트를 직접 정렬하는 메서드

**시그니처:**
```java
public void sort(Comparator<? super E> c)
```

**사용법:**
```java
students.sort(Comparator.comparing(Student::getName));
```

**장점:**
- `Collections.sort()`보다 직관적
- 코드가 더 읽기 쉬움

### 5. Function 인터페이스

**역할:** 하나의 인자를 받아 결과를 반환하는 함수형 인터페이스

**정의:**
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

**메서드 참조와의 관계:**
```java
Student::getName  // Function<Student, String>과 동일
```

**동작:**
```java
Function<Student, String> getName = Student::getName;
String name = getName.apply(student);  // student.getName()과 동일
```

---

## 다양한 사용 방법

### 방법 1: 기본 사용 (현재 코드)

```java
// 이름 순
students.sort(Comparator.comparing(Student::getName));

// 나이 순
students.sort(Comparator.comparingInt(Student::getAge));

// 학번 순
students.sort(Comparator.comparingInt(Student::getStudentId));
```

### 방법 2: 역순 정렬

```java
// 이름 역순
students.sort(Comparator.comparing(Student::getName).reversed());

// 나이 역순
students.sort(Comparator.comparingInt(Student::getAge).reversed());
```

### 방법 3: 다중 정렬 기준

```java
// 이름 → 나이 → 학번 순
students.sort(
    Comparator.comparing(Student::getName)
              .thenComparingInt(Student::getAge)
              .thenComparingInt(Student::getStudentId)
);
```

### 방법 4: null 값 처리

```java
// null을 앞에 배치
students.sort(
    Comparator.nullsFirst(Comparator.comparing(Student::getName))
);

// null을 뒤에 배치
students.sort(
    Comparator.nullsLast(Comparator.comparing(Student::getName))
);
```

### 방법 5: 대소문자 구분 없이 정렬

```java
// comparing()에 추가 Comparator 전달
students.sort(
    Comparator.comparing(
        Student::getName, 
        String.CASE_INSENSITIVE_ORDER
    )
);
```

### 방법 6: 커스텀 비교 로직

```java
// 이름 길이 순으로 정렬
students.sort(
    Comparator.comparingInt(s -> s.getName().length())
);

// 나이와 학번의 합으로 정렬
students.sort(
    Comparator.comparingInt(s -> s.getAge() + s.getStudentId())
);
```

---

## 실전 활용 팁

### 팁 1: comparingInt() vs comparing()

**권장:**
```java
// int 타입은 comparingInt() 사용 (성능상 이점)
students.sort(Comparator.comparingInt(Student::getAge));
```

**비권장:**
```java
// comparing()도 가능하지만 비효율적 (박싱 발생)
students.sort(Comparator.comparing(Student::getAge));
```

### 팁 2: 다중 정렬 기준 체이닝

```java
// 여러 기준을 체이닝하여 연결
students.sort(
    Comparator.comparing(Student::getName)
              .thenComparingInt(Student::getAge)
              .thenComparingInt(Student::getStudentId)
);
```

**동작:**
1. 이름 순으로 정렬
2. 이름이 같으면 나이 순으로 정렬
3. 이름과 나이가 같으면 학번 순으로 정렬

### 팁 3: 역순 정렬

```java
// 전체를 역순으로
students.sort(
    Comparator.comparing(Student::getName).reversed()
);

// 특정 기준만 역순으로
students.sort(
    Comparator.comparing(Student::getName)
              .thenComparingInt(Student::getAge).reversed()  // 나이만 역순
);
```

### 팁 4: 조건부 정렬

```java
// 나이가 20 이상인 학생만 정렬
students.stream()
    .filter(s -> s.getAge() >= 20)
    .sorted(Comparator.comparing(Student::getName))
    .forEach(System.out::println);
```

### 팁 5: 정렬 기준을 변수로 저장

```java
// Comparator를 변수에 저장하여 재사용
Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
students.sort(nameComparator);

// 다른 리스트에도 사용 가능
otherStudents.sort(nameComparator);
```

---

## 연습 문제

### 문제 1: 내림차순 정렬

각 정렬 기준을 내림차순으로 변경하세요.

**힌트:**
```java
students.sort(Comparator.comparing(Student::getName).reversed());
```

### 문제 2: 다중 정렬 기준

이름이 같으면 나이 순으로 정렬하도록 변경하세요.

**힌트:**
```java
students.sort(
    Comparator.comparing(Student::getName)
              .thenComparingInt(Student::getAge)
);
```

### 문제 3: 새로운 정렬 기준 추가

메뉴에 "4. 이름 길이 순" 옵션을 추가하세요.

**힌트:**
```java
students.sort(Comparator.comparingInt(s -> s.getName().length()));
```

### 문제 4: 대소문자 구분 없이 정렬

이름을 대소문자 구분 없이 정렬하도록 변경하세요.

**힌트:**
```java
students.sort(
    Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER)
);
```

### 문제 5: 람다 표현식으로 변경

메서드 참조를 람다 표현식으로 변경하세요.

**힌트:**
```java
// 메서드 참조
students.sort(Comparator.comparing(Student::getName));

// 람다 표현식
students.sort(Comparator.comparing(s -> s.getName()));
```

### 문제 6: 정렬 기준 선택 개선

사용자가 여러 정렬 기준을 선택할 수 있도록 개선하세요 (예: "이름 → 나이").

---

## 네 가지 정렬 방법 종합 비교

### 방법 1: 직접 구현 (삽입 정렬)

```java
private static void InsertionSort(Student[] students) {
    // 알고리즘 직접 구현
}
```

**특징:**
- 학습 목적에 적합
- 코드가 복잡함

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
- 클래스 내부에 정의

### 방법 3: Comparator 클래스

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
- 별도 클래스 필요

### 방법 4: 메서드 참조 (현재 예제)

```java
students.sort(Comparator.comparing(Student::getName));
```

**특징:**
- 가장 간결하고 읽기 쉬움
- Java 8+ 필요
- 별도 클래스 불필요

---

## 요약

### 핵심 정리

1. **메서드 참조**는 람다 표현식을 더 간결하게 만듭니다.
2. **Comparator.comparing()**은 객체의 필드를 추출하여 Comparator를 생성합니다.
3. **Comparator.comparingInt()**는 int 타입 전용으로 성능상 이점이 있습니다.
4. **ArrayList.sort()**는 `Collections.sort()`보다 더 직관적입니다.
5. **별도의 Comparator 클래스 없이** 한 줄로 정렬이 가능합니다.

### 학습 포인트

- ✅ 메서드 참조의 개념과 사용법
- ✅ Comparator.comparing() 메서드 이해
- ✅ Comparator.comparingInt() 사용법
- ✅ ArrayList.sort() vs Collections.sort()
- ✅ 다양한 정렬 패턴 구현
- ✅ Java 8의 함수형 프로그래밍 기능 활용

### 선택 가이드

**언제 메서드 참조를 사용할까?**
- 람다 표현식이 단순히 메서드를 호출하는 경우
- 코드 가독성을 높이고 싶은 경우
- Java 8 이상을 사용하는 경우

**언제 Comparator.comparing()을 사용할까?**
- 객체의 특정 필드로 정렬할 때
- 별도의 Comparator 클래스를 만들고 싶지 않을 때
- 코드를 간결하게 유지하고 싶을 때

---

**작성일:** 2026-01-30  
**범위:** 메서드 참조와 Comparator.comparing()을 이용한 학생 정렬 프로그램 상세 설명
