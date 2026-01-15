# 학생 관리 시스템 정답 (ArrayList 버전)

## 📌 전체 코드

### Student.java

```java
package student.management;

/**
 * 학생 정보를 나타내는 클래스
 */
public class Student {
    private String studentId;  // 학번
    private String name;       // 이름
    private int age;          // 나이
    private String major;     // 전공
    
    // 생성자
    public Student(String studentId, String name, int age, String major) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
    }
    
    // Getter 메서드
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
    
    // Setter 메서드 (수정 기능을 위해 필요)
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
}
```

---

### StudentApplication.java

```java
package student.management;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 학생 관리 시스템 메인 클래스 (ArrayList 버전)
 */
public class StudentApplication {
    // 모든 학생 정보를 저장하는 ArrayList (크기 제한 없음)
    private static ArrayList<Student> studentsList = new ArrayList<>();
    
    // 사용자 입력을 받기 위한 Scanner
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean run = true;
        
        while (run) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("1. 학생등록 | 2. 학생목록 | 3. 학생검색 | 4. 학생수정 | 5. 학생삭제 | 6. 종료");
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("선택> ");
            
            int selNum = 0;
            try {
                selNum = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                // 잘못된 입력 처리
            }
            
            switch (selNum) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    studentList();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("프로그램 종료");
                    run = false;
                    break;
                default:
                    break;
            }
        }
        
        scanner.close();
    }
    
    /**
     * 학생 등록
     */
    private static void registerStudent() {
        System.out.println("-----------");
        System.out.println("학생등록");
        System.out.println("-----------");
        
        System.out.print("학번: ");
        String studentId = scanner.nextLine();
        
        // 중복 체크
        if (findStudent(studentId) != null) {
            System.out.println("이미 존재하는 학번입니다.");
            return;
        }
        
        System.out.print("이름: ");
        String name = scanner.nextLine();
        
        System.out.print("나이: ");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }
        
        System.out.print("전공: ");
        String major = scanner.nextLine();
        
        // Student 객체 생성
        Student newStudent = new Student(studentId, name, age, major);
        
        // ArrayList에 추가 (배열과 달리 빈 자리 찾을 필요 없음!)
        studentsList.add(newStudent);
        System.out.println("결과: 학생이 등록되었습니다.");
    }
    
    /**
     * 학생 목록 출력
     */
    private static void studentList() {
        System.out.println("-----------");
        System.out.println("학생목록");
        System.out.println("-----------");
        
        // ArrayList 순회 (null 체크 불필요!)
        for (Student student : studentsList) {
            System.out.printf("%s %s   %d %s\n", 
                student.getStudentId(), 
                student.getName(), 
                student.getAge(), 
                student.getMajor());
        }
        
        // 또는 인덱스로 순회
        /*
        for (int i = 0; i < studentsList.size(); i++) {
            Student student = studentsList.get(i);
            System.out.printf("%s %s   %d %s\n", 
                student.getStudentId(), 
                student.getName(), 
                student.getAge(), 
                student.getMajor());
        }
        */
    }
    
    /**
     * 학생 검색
     */
    private static void searchStudent() {
        System.out.println("-----------");
        System.out.println("학생검색");
        System.out.println("-----------");
        
        System.out.print("학번: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudent(studentId);
        
        if (student == null) {
            System.out.println("결과: 학생을 찾을 수 없습니다.");
        } else {
            System.out.println("결과: 학생을 찾았습니다.");
            System.out.printf("학번: %s, 이름: %s, 나이: %d, 전공: %s\n",
                student.getStudentId(),
                student.getName(),
                student.getAge(),
                student.getMajor());
        }
    }
    
    /**
     * 학생 정보 수정
     */
    private static void updateStudent() {
        System.out.println("-----------");
        System.out.println("학생수정");
        System.out.println("-----------");
        
        System.out.print("학번: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudent(studentId);
        
        if (student == null) {
            System.out.println("결과: 학생을 찾을 수 없습니다.");
            return;
        }
        
        System.out.print("이름(수정): ");
        String name = scanner.nextLine();
        
        System.out.print("나이(수정): ");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }
        
        System.out.print("전공(수정): ");
        String major = scanner.nextLine();
        
        // Student 정보 수정
        student.setName(name);
        student.setAge(age);
        student.setMajor(major);
        
        System.out.println("결과: 학생 정보가 수정되었습니다.");
    }
    
    /**
     * 학생 삭제
     */
    private static void deleteStudent() {
        System.out.println("-----------");
        System.out.println("학생삭제");
        System.out.println("-----------");
        
        System.out.print("학번: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudent(studentId);
        
        if (student == null) {
            System.out.println("결과: 학생을 찾을 수 없습니다.");
            return;
        }
        
        // ArrayList에서 삭제 (배열과 달리 null로 설정할 필요 없음!)
        studentsList.remove(student);
        System.out.println("결과: 학생이 삭제되었습니다.");
        
        // 또는 인덱스로 삭제
        /*
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getStudentId().equals(studentId)) {
                studentsList.remove(i);
                System.out.println("결과: 학생이 삭제되었습니다.");
                break;
            }
        }
        */
    }
    
    /**
     * 학번으로 학생을 찾는 보조 메서드
     * @param studentId 학번
     * @return Student 객체 (찾지 못하면 null)
     */
    private static Student findStudent(String studentId) {
        // ArrayList 순회 (null 체크 불필요!)
        for (Student student : studentsList) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
        
        // 또는 인덱스로 순회
        /*
        for (int i = 0; i < studentsList.size(); i++) {
            Student student = studentsList.get(i);
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
        */
    }
}
```

---

## 📝 코드 설명

### 1. Student 클래스

- **필드**: 학번, 이름, 나이, 전공 (모두 private)
- **생성자**: 모든 필드를 초기화
- **Getter/Setter**: 필드 접근 및 수정을 위한 메서드
- 배열 버전과 동일함

### 2. StudentApplication 클래스

#### main 메서드

- `while` 반복문으로 메뉴를 계속 출력
- 사용자 선택에 따라 해당 기능 호출
- 6번(종료) 선택 시 프로그램 종료

#### registerStudent() - 학생 등록

**배열 버전:**
```java
// 배열의 빈 자리에 저장
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] == null) {
        studentsArray[i] = newStudent;
        System.out.println("결과: 학생이 등록되었습니다.");
        break;
    }
}
```

**ArrayList 버전:**
```java
// ArrayList에 추가 (간단!)
studentsList.add(newStudent);
System.out.println("결과: 학생이 등록되었습니다.");
```

**차이점:**
- 배열: 빈 자리(null)를 찾아야 함
- ArrayList: 바로 `add()`로 추가 가능 (더 간단!)

#### studentList() - 학생 목록

**배열 버전:**
```java
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] != null) {  // null 체크 필요!
        Student student = studentsArray[i];
        // 출력
    }
}
```

**ArrayList 버전:**
```java
// 향상된 for문 사용 (null 체크 불필요!)
for (Student student : studentsList) {
    // 출력
}
```

**차이점:**
- 배열: null 체크 필요
- ArrayList: null 체크 불필요 (모든 요소가 유효함)

#### searchStudent() - 학생 검색

- 배열 버전과 동일 (findStudent 메서드 사용)

#### updateStudent() - 학생 수정

- 배열 버전과 동일 (Setter 메서드 사용)

#### deleteStudent() - 학생 삭제

**배열 버전:**
```java
// 배열에서 해당 학생을 찾아 null로 설정
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] != null && studentsArray[i].getStudentId().equals(studentId)) {
        studentsArray[i] = null;  // null로 설정 (실제로는 빈 자리만 생김)
        System.out.println("결과: 학생이 삭제되었습니다.");
        break;
    }
}
```

**ArrayList 버전:**
```java
// ArrayList에서 삭제 (실제로 제거됨!)
studentsList.remove(student);
System.out.println("결과: 학생이 삭제되었습니다.");
```

**차이점:**
- 배열: null로 설정 (빈 자리만 생김, 실제 삭제 아님)
- ArrayList: 실제로 요소가 제거됨 (크기도 감소)

#### findStudent() - 보조 메서드

**배열 버전:**
```java
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] != null) {  // null 체크 필요!
        String dbStudentId = studentsArray[i].getStudentId();
        if (dbStudentId.equals(studentId)) {
            student = studentsArray[i];
            break;
        }
    }
}
```

**ArrayList 버전:**
```java
// 향상된 for문 사용 (null 체크 불필요!)
for (Student student : studentsList) {
    if (student.getStudentId().equals(studentId)) {
        return student;
    }
}
return null;
```

**차이점:**
- 배열: null 체크 필요
- ArrayList: null 체크 불필요, 코드가 더 간단

---

## 🔍 주요 포인트

### 1. ArrayList 관리

```java
// 학생 등록: 바로 추가
studentsList.add(newStudent);

// 학생 삭제: 바로 삭제
studentsList.remove(student);

// 크기 확인
int size = studentsList.size();

// 요소 접근
Student student = studentsList.get(index);
```

### 2. 중복 체크

```java
if (findStudent(studentId) != null) {
    System.out.println("이미 존재하는 학번입니다.");
    return;
}
```

### 3. 예외 처리

```java
try {
    age = Integer.parseInt(scanner.nextLine());
} catch (Exception e) {
    System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
    return;
}
```

### 4. null 체크 불필요

```java
// 배열 버전: null 체크 필요
if (studentsArray[i] != null) {
    // 처리
}

// ArrayList 버전: null 체크 불필요!
for (Student student : studentsList) {
    // 모든 요소가 유효함
}
```

### 5. String 비교

```java
// == 대신 equals() 사용
if (student.getStudentId().equals(studentId)) {
    // 일치
}
```

---

## 🎯 배열 버전 vs ArrayList 버전 비교

| 구분 | 배열 버전 | ArrayList 버전 |
|------|----------|---------------|
| **선언** | `Student[] studentsArray = new Student[100]` | `ArrayList<Student> studentsList = new ArrayList<>()` |
| **크기 제한** | 최대 100명 (고정) | 제한 없음 (동적) |
| **크기 확인** | `.length` (필드) | `.size()` (메서드) |
| **요소 접근** | `array[i]` | `list.get(i)` |
| **학생 등록** | 빈 자리(null) 찾아서 저장 | `list.add(student)` 바로 추가 |
| **학생 삭제** | `array[i] = null` (빈 자리 생성) | `list.remove(student)` (실제 삭제) |
| **순회 시 null 체크** | **필수** | **불필요** |
| **메모리 효율** | 사용 안 하는 공간 낭비 가능 | 필요한 만큼만 사용 |
| **코드 복잡도** | 높음 (빈 자리 찾기 등) | 낮음 (간단한 메서드 호출) |
| **코드 라인 수** | 더 많음 | 더 적음 |

### 코드 비교 예시

#### 학생 등록

**배열 버전 (5줄):**
```java
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] == null) {
        studentsArray[i] = newStudent;
        break;
    }
}
```

**ArrayList 버전 (1줄):**
```java
studentsList.add(newStudent);
```

#### 학생 목록

**배열 버전 (6줄, null 체크 포함):**
```java
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] != null) {
        Student student = studentsArray[i];
        // 출력
    }
}
```

**ArrayList 버전 (3줄, null 체크 불필요):**
```java
for (Student student : studentsList) {
    // 출력
}
```

#### 학생 삭제

**배열 버전 (6줄):**
```java
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] != null && studentsArray[i].getStudentId().equals(studentId)) {
        studentsArray[i] = null;
        break;
    }
}
```

**ArrayList 버전 (1줄):**
```java
studentsList.remove(student);
```

---

## 📚 학습 목표 달성 체크

- ✅ 클래스 설계 (Student)
- ✅ ArrayList 사용법
- ✅ ArrayList 관리 (등록, 삭제)
- ✅ 메서드 분리 (findStudent)
- ✅ 예외 처리 (try-catch)
- ✅ 중복 체크
- ✅ 메뉴 시스템
- ✅ Getter/Setter 활용
- ✅ 향상된 for문 사용
- ✅ String 비교 (equals)
- ✅ 배열과 ArrayList의 차이 이해

---

## 💡 ArrayList의 장점

### 1. 동적 크기

```java
// 배열: 크기 고정
Student[] array = new Student[100];  // 최대 100명

// ArrayList: 크기 무제한
ArrayList<Student> list = new ArrayList<>();  // 제한 없음!
```

### 2. 간단한 추가/삭제

```java
// 배열: 복잡한 로직 필요
for (int i = 0; i < array.length; i++) {
    if (array[i] == null) {
        array[i] = student;
        break;
    }
}

// ArrayList: 한 줄로 해결!
list.add(student);
```

### 3. null 체크 불필요

```java
// 배열: null 체크 필수
if (array[i] != null) {
    // 처리
}

// ArrayList: 모든 요소가 유효함
for (Student s : list) {
    // null 체크 불필요!
}
```

### 4. 실제 삭제

```java
// 배열: null로 설정 (빈 자리만 생김)
array[i] = null;

// ArrayList: 실제로 제거됨
list.remove(student);
// 리스트 크기도 자동으로 감소!
```

### 5. 메모리 효율

```java
// 배열: 미리 100개 공간 할당 (낭비 가능)
Student[] array = new Student[100];  // 10명만 사용해도 100개 공간!

// ArrayList: 필요한 만큼만 사용
ArrayList<Student> list = new ArrayList<>();  // 10명이면 10개만!
```

---

## 🚀 추가 학습

### ArrayList의 다른 유용한 메서드

```java
ArrayList<Student> list = new ArrayList<>();

// 포함 여부 확인
boolean exists = list.contains(student);

// 인덱스 찾기
int index = list.indexOf(student);

// 모든 요소 삭제
list.clear();

// 비어있는지 확인
boolean isEmpty = list.isEmpty();

// 특정 인덱스에 추가
list.add(0, student);  // 맨 앞에 추가

// 특정 인덱스의 요소 변경
list.set(0, newStudent);
```

---

## 📚 참고 자료

- [배열 vs ArrayList 완전 비교](./배열-vs-ArrayList-비교.md)
- [학생 관리 시스템 (배열 버전)](./학생관리시스템-정답.md)
- [학생 관리 시스템 실습 문제 (ArrayList 버전)](./학생관리시스템-ArrayList-실습문제.md)


