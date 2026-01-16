# ArrayList CRUD 간단 예제

## 📋 개요
ArrayList를 사용하여 객체를 생성, 조회, 수정, 삭제하는 기본적인 CRUD 작업을 학습하는 예제입니다.

---

## 🏗 클래스 구조

```
Person.java        (도메인 클래스 - 데이터 저장)
PersonManager.java (관리 클래스 - CRUD 기능)
Main.java          (실행 클래스)
```

---

## 📦 1. Person 클래스 (도메인 클래스)

```java
package example;

public class Person {
    private String name;
    private int age;

    // 기본 생성자
    public Person() {
    }

    // 매개변수 생성자
    public Person(String name, int age) {
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
        this.age = age;
    }

    // toString
    @Override
    public String toString() {
        return "이름: " + name + ", 나이: " + age;
    }
}
```

---

## 🗂 2. PersonManager 클래스 (관리 클래스)

```java
package example;

import java.util.ArrayList;

public class PersonManager {
    private ArrayList<Person> persons;

    // 생성자 - ArrayList 초기화
    public PersonManager() {
        persons = new ArrayList<>();
    }

    // 1. 추가 (Create)
    public void addPerson(String name, int age) {
        Person person = new Person(name, age);
        persons.add(person);
        System.out.println("추가 완료: " + person);
    }

    // 2. 조회 - 전체 목록 (Read - All)
    public void showAllPersons() {
        System.out.println("=== 전체 목록 ===");
        if (persons.isEmpty()) {
            System.out.println("목록이 비어있습니다.");
            return;
        }
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    // 3. 조회 - 특정 인덱스 (Read - One)
    public void showPerson(int index) {
        if (index >= 0 && index < persons.size()) {
            System.out.println("조회 결과: " + persons.get(index));
        } else {
            System.out.println("잘못된 인덱스입니다.");
        }
    }

    // 4. 수정 (Update)
    public void updatePerson(int index, String name, int age) {
        if (index >= 0 && index < persons.size()) {
            Person person = persons.get(index);
            person.setName(name);
            person.setAge(age);
            System.out.println("수정 완료: " + person);
        } else {
            System.out.println("잘못된 인덱스입니다.");
        }
    }

    // 5. 삭제 (Delete)
    public void deletePerson(int index) {
        if (index >= 0 && index < persons.size()) {
            Person removed = persons.remove(index);
            System.out.println("삭제 완료: " + removed);
        } else {
            System.out.println("잘못된 인덱스입니다.");
        }
    }

    // 리스트 크기 반환
    public int getSize() {
        return persons.size();
    }
}
```

---

## 🖥 3. Main 클래스 (실행 클래스)

```java
package example;

public class Main {
    public static void main(String[] args) {
        PersonManager manager = new PersonManager();

        System.out.println("=== 1. 추가 (Create) ===");
        manager.addPerson("홍길동", 25);
        manager.addPerson("김철수", 30);
        manager.addPerson("이영희", 28);

        System.out.println("\n=== 2. 조회 - 전체 (Read All) ===");
        manager.showAllPersons();

        System.out.println("\n=== 3. 조회 - 특정 (Read One) ===");
        manager.showPerson(1);  // 인덱스 1번 조회

        System.out.println("\n=== 4. 수정 (Update) ===");
        manager.updatePerson(0, "홍길동수정", 26);

        System.out.println("\n=== 5. 수정 후 조회 ===");
        manager.showAllPersons();

        System.out.println("\n=== 6. 삭제 (Delete) ===");
        manager.deletePerson(2);  // 인덱스 2번 삭제

        System.out.println("\n=== 7. 삭제 후 조회 ===");
        manager.showAllPersons();

        System.out.println("\n=== 8. 리스트 크기 ===");
        System.out.println("현재 인원 수: " + manager.getSize());
    }
}
```

---

## 🖥 실행 결과

```
=== 1. 추가 (Create) ===
추가 완료: 이름: 홍길동, 나이: 25
추가 완료: 이름: 김철수, 나이: 30
추가 완료: 이름: 이영희, 나이: 28

=== 2. 조회 - 전체 (Read All) ===
=== 전체 목록 ===
이름: 홍길동, 나이: 25
이름: 김철수, 나이: 30
이름: 이영희, 나이: 28

=== 3. 조회 - 특정 (Read One) ===
조회 결과: 이름: 김철수, 나이: 30

=== 4. 수정 (Update) ===
수정 완료: 이름: 홍길동수정, 나이: 26

=== 5. 수정 후 조회 ===
=== 전체 목록 ===
이름: 홍길동수정, 나이: 26
이름: 김철수, 나이: 30
이름: 이영희, 나이: 28

=== 6. 삭제 (Delete) ===
삭제 완료: 이름: 이영희, 나이: 28

=== 7. 삭제 후 조회 ===
=== 전체 목록 ===
이름: 홍길동수정, 나이: 26
이름: 김철수, 나이: 30

=== 8. 리스트 크기 ===
현재 인원 수: 2
```

---

## 📚 상세 설명

### 1. 객체 생성 및 ArrayList 초기화

```java
PersonManager manager = new PersonManager();
```

**동작 과정:**
1. `PersonManager` 객체 생성
2. 생성자에서 `persons = new ArrayList<>()` 실행
3. 빈 ArrayList 생성

**메모리 구조:**
```
manager
  └── persons (ArrayList)
        └── [] (비어있음, size = 0)
```

### 2. 추가 (Create) - `addPerson()`

```java
public void addPerson(String name, int age) {
    Person person = new Person(name, age);  // 1. 객체 생성
    persons.add(person);                     // 2. 리스트에 추가
}
```

**단계별 설명:**

**1단계: 객체 생성**
```java
Person person = new Person(name, age);
```
- `Person` 객체를 힙 메모리에 생성
- 생성자로 `name`, `age` 초기화

**2단계: 리스트에 추가**
```java
persons.add(person);
```
- ArrayList의 `add()` 메서드로 객체 추가
- 리스트 끝에 자동으로 추가됨

**메모리 변화:**
```
추가 전:
persons: []

추가 후 (홍길동 추가):
persons: [Person(name="홍길동", age=25)]
          ↑
       인덱스 0
```

**실행 예시:**
```java
manager.addPerson("홍길동", 25);
manager.addPerson("김철수", 30);
manager.addPerson("이영희", 28);
```

**리스트 상태:**
```
인덱스 | 객체
-------|-------------------
  0    | Person("홍길동", 25)
  1    | Person("김철수", 30)
  2    | Person("이영희", 28)
```

### 3. 조회 - 전체 (Read All) - `showAllPersons()`

```java
public void showAllPersons() {
    for (Person person : persons) {
        System.out.println(person);
    }
}
```

**향상된 for문 (Enhanced for loop):**
```java
for (Person person : persons) {
    // persons의 각 요소를 person 변수에 할당하며 반복
}
```

**동작 과정:**
1. `persons` 리스트의 첫 번째 요소부터 마지막 요소까지 반복
2. 각 요소를 `person` 변수에 할당
3. `System.out.println(person)` 실행
   - 자동으로 `person.toString()` 호출

**실행 흐름:**
```
1회차: person = persons[0] → Person("홍길동", 25) 출력
2회차: person = persons[1] → Person("김철수", 30) 출력
3회차: person = persons[2] → Person("이영희", 28) 출력
종료
```

**일반 for문으로 표현:**
```java
for (int i = 0; i < persons.size(); i++) {
    Person person = persons.get(i);
    System.out.println(person);
}
```

### 4. 조회 - 특정 (Read One) - `showPerson()`

```java
public void showPerson(int index) {
    if (index >= 0 && index < persons.size()) {
        Person person = persons.get(index);
        System.out.println("조회 결과: " + person);
    } else {
        System.out.println("잘못된 인덱스입니다.");
    }
}
```

**인덱스 유효성 검사:**
```java
index >= 0                    // 음수 체크
index < persons.size()        // 리스트 크기 체크
```

**예시:**
```java
persons: [홍길동, 김철수, 이영희]
          인덱스: 0    1     2

showPerson(1);  // ✅ 유효: 김철수 출력
showPerson(5);  // ❌ 무효: "잘못된 인덱스입니다."
showPerson(-1); // ❌ 무효: "잘못된 인덱스입니다."
```

**`get()` 메서드:**
```java
Person person = persons.get(index);
```
- 인덱스로 요소를 가져옴
- 인덱스가 범위를 벗어나면 `IndexOutOfBoundsException` 발생
- 따라서 유효성 검사 필수

### 5. 수정 (Update) - `updatePerson()`

```java
public void updatePerson(int index, String name, int age) {
    if (index >= 0 && index < persons.size()) {
        Person person = persons.get(index);  // 1. 객체 가져오기
        person.setName(name);                 // 2. 필드 수정
        person.setAge(age);                   // 3. 필드 수정
        System.out.println("수정 완료: " + person);
    }
}
```

**동작 과정:**

**1단계: 객체 가져오기**
```java
Person person = persons.get(index);
```
- 인덱스로 객체 참조 가져오기
- `person`은 실제 객체를 가리킴 (참조)

**2단계: 필드 수정**
```java
person.setName(name);
person.setAge(age);
```
- Setter 메서드로 필드 값 변경
- **같은 객체**를 수정하므로 리스트에도 자동 반영됨

**메모리 구조:**
```
수정 전:
persons[0] → Person(name="홍길동", age=25)

수정 실행:
person = persons[0]  (같은 객체 참조)
person.setName("홍길동수정")
person.setAge(26)

수정 후:
persons[0] → Person(name="홍길동수정", age=26)
```

**중요 포인트:**
- 객체 참조 개념: `person`과 `persons.get(index)`는 **같은 객체**를 가리킴
- Setter로 수정하면 원본 객체가 변경됨
- `persons.set(index, person)`은 불필요 (이미 같은 객체 참조)

**대안: 새 객체로 교체**
```java
// 방법 2: 새 객체로 교체
Person newPerson = new Person(name, age);
persons.set(index, newPerson);
```

### 6. 삭제 (Delete) - `deletePerson()`

```java
public void deletePerson(int index) {
    if (index >= 0 && index < persons.size()) {
        Person removed = persons.remove(index);  // 삭제 및 반환
        System.out.println("삭제 완료: " + removed);
    }
}
```

**`remove()` 메서드:**
```java
Person removed = persons.remove(index);
```
- 인덱스의 요소를 삭제
- 삭제된 요소를 반환
- 삭제 후 뒤의 요소들이 자동으로 앞으로 이동

**삭제 과정:**
```
삭제 전:
인덱스 | 객체
-------|-------------------
  0    | Person("홍길동", 25)
  1    | Person("김철수", 30)
  2    | Person("이영희", 28)

deletePerson(2) 실행:

1단계: 인덱스 2의 요소 삭제
  → Person("이영희", 28) 삭제

2단계: 리스트 자동 재정렬
  → 뒤의 요소가 자동으로 앞으로 이동 (이 경우 없음)

삭제 후:
인덱스 | 객체
-------|-------------------
  0    | Person("홍길동", 25)
  1    | Person("김철수", 30)
```

**중간 인덱스 삭제 예시:**
```
삭제 전: [A, B, C, D]
         인덱스: 0  1  2  3

deletePerson(1) 실행:

1단계: 인덱스 1(B) 삭제
2단계: C, D가 앞으로 이동

삭제 후: [A, C, D]
         인덱스: 0  1  2
```

**주의사항:**
- 삭제 후 인덱스가 변경됨
- 여러 개를 삭제할 때는 뒤에서부터 삭제하거나 Iterator 사용

### 7. 리스트 크기 확인 - `getSize()`

```java
public int getSize() {
    return persons.size();
}
```

**`size()` 메서드:**
- 리스트에 저장된 요소의 개수 반환
- 배열의 `length`와 유사

---

## 🔑 핵심 개념 정리

### 1. CRUD 패턴

| 작업 | 메서드 | 설명 |
|------|--------|------|
| **C**reate | `add()` | 객체 생성 후 리스트에 추가 |
| **R**ead | `get()`, 순회 | 객체 조회 |
| **U**pdate | `set()`, Setter | 객체 필드 수정 |
| **D**elete | `remove()` | 객체 삭제 |

### 2. ArrayList 주요 메서드

| 메서드 | 반환 타입 | 설명 |
|--------|----------|------|
| `add(E e)` | `boolean` | 끝에 요소 추가 |
| `get(int index)` | `E` | 인덱스의 요소 반환 |
| `set(int index, E e)` | `E` | 인덱스의 요소 교체 |
| `remove(int index)` | `E` | 인덱스의 요소 삭제 및 반환 |
| `size()` | `int` | 리스트 크기 반환 |
| `isEmpty()` | `boolean` | 비어있는지 확인 |

### 3. 객체 참조 개념

```java
Person person = persons.get(0);  // person은 persons[0]과 같은 객체 참조
person.setName("수정");          // persons[0]의 실제 데이터 변경
```

**메모리 구조:**
```
persons[0] ──┐
             ├─→ Person(name="홍길동")
person ──────┘
```

### 4. 인덱스 범위

```java
인덱스 범위: 0 ~ (size() - 1)

persons.size() = 3
유효한 인덱스: 0, 1, 2
```

---

## 💡 실전 활용 팁

### 1. 인덱스 유효성 검사

```java
// ✅ 좋은 방법
if (index >= 0 && index < persons.size()) {
    // 안전한 작업
}

// ❌ 나쁜 방법 (예외 발생)
persons.get(index);  // 인덱스 범위 체크 없음
```

### 2. 빈 리스트 체크

```java
// ✅ 좋은 방법
if (persons.isEmpty()) {
    System.out.println("목록이 비어있습니다.");
    return;
}

// 또는
if (persons.size() == 0) {
    // ...
}
```

### 3. 향상된 for문 vs 일반 for문

```java
// 향상된 for문: 읽기 전용, 간단
for (Person person : persons) {
    System.out.println(person);
}

// 일반 for문: 인덱스 필요할 때
for (int i = 0; i < persons.size(); i++) {
    System.out.println(i + ": " + persons.get(i));
}
```

### 4. 여러 개 삭제 시 주의

```java
// ❌ 잘못된 방법 (인덱스 변경으로 문제 발생)
for (int i = 0; i < persons.size(); i++) {
    if (조건) {
        persons.remove(i);  // 인덱스 변경으로 일부 건너뛰어짐
    }
}

// ✅ 올바른 방법 1: 뒤에서부터 삭제
for (int i = persons.size() - 1; i >= 0; i--) {
    if (조건) {
        persons.remove(i);
    }
}

// ✅ 올바른 방법 2: Iterator 사용
Iterator<Person> iterator = persons.iterator();
while (iterator.hasNext()) {
    Person person = iterator.next();
    if (조건) {
        iterator.remove();
    }
}
```

---

## 🎓 연습 문제

### 문제 1: 이름으로 검색
```java
// 이름으로 Person을 찾아 반환하는 메서드를 작성하세요
public Person findByName(String name) {
    // 코드 작성
}
```

### 문제 2: 나이로 필터링
```java
// 특정 나이 이상인 Person들의 리스트를 반환하는 메서드를 작성하세요
public ArrayList<Person> filterByAge(int minAge) {
    // 코드 작성
}
```

### 문제 3: 모든 Person 나이 증가
```java
// 모든 Person의 나이를 1씩 증가시키는 메서드를 작성하세요
public void increaseAllAges() {
    // 코드 작성
}
```

---

## 📝 정리

1. **추가**: `add()` 메서드로 객체를 리스트 끝에 추가
2. **조회**: `get(index)` 또는 향상된 for문으로 순회
3. **수정**: `get()`으로 객체 가져온 후 Setter로 필드 수정
4. **삭제**: `remove(index)`로 삭제 (인덱스 자동 조정)
5. **인덱스**: 항상 유효성 검사 필수 (0 ~ size()-1)
6. **객체 참조**: 같은 객체를 여러 변수가 참조할 수 있음

