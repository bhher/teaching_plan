# 학생 관리 시스템 정답 (ArrayList 버전, 점수 포함)

## 📌 전체 코드

### Student.java

```java
package student.management;

/**
 * 학생 정보를 나타내는 클래스 (점수 포함)
 */
public class Student {
    private String studentId;  // 학번
    private String name;       // 이름
    private int age;          // 나이
    private String major;     // 전공
    private int kor;          // 국어 점수
    private int eng;          // 영어 점수
    private int math;         // 수학 점수
    
    // 생성자
    public Student(String studentId, String name, int age, String major, int kor, int eng, int math) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
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
    
    public int getKor() {
        return kor;
    }
    
    public int getEng() {
        return eng;
    }
    
    public int getMath() {
        return math;
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
    
    public void setKor(int kor) {
        this.kor = kor;
    }
    
    public void setEng(int eng) {
        this.eng = eng;
    }
    
    public void setMath(int math) {
        this.math = math;
    }
    
    // 총점 계산 메서드
    public int calculateTotal() {
        return kor + eng + math;
    }
    
    // 평균 계산 메서드
    public double calculateAverage() {
        return calculateTotal() / 3.0;
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
 * 학생 관리 시스템 메인 클래스 (ArrayList 버전, 점수 포함)
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
        
        // 국어 점수 입력 및 유효성 검사
        System.out.print("국어: ");
        int kor = 0;
        try {
            kor = Integer.parseInt(scanner.nextLine());
            if (kor < 0 || kor > 100) {
                System.out.println("점수는 0 이상 100 이하의 정수여야 합니다.");
                return;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }
        
        // 영어 점수 입력 및 유효성 검사
        System.out.print("영어: ");
        int eng = 0;
        try {
            eng = Integer.parseInt(scanner.nextLine());
            if (eng < 0 || eng > 100) {
                System.out.println("점수는 0 이상 100 이하의 정수여야 합니다.");
                return;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }
        
        // 수학 점수 입력 및 유효성 검사
        System.out.print("수학: ");
        int math = 0;
        try {
            math = Integer.parseInt(scanner.nextLine());
            if (math < 0 || math > 100) {
                System.out.println("점수는 0 이상 100 이하의 정수여야 합니다.");
                return;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }
        
        // Student 객체 생성
        Student newStudent = new Student(studentId, name, age, major, kor, eng, math);
        
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
            System.out.printf("%s %s   %d %s   국어:%d 영어:%d 수학:%d 총점:%d 평균:%.2f\n", 
                student.getStudentId(), 
                student.getName(), 
                student.getAge(), 
                student.getMajor(),
                student.getKor(),
                student.getEng(),
                student.getMath(),
                student.calculateTotal(),
                student.calculateAverage());
        }
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
            System.out.printf("국어: %d, 영어: %d, 수학: %d, 총점: %d, 평균: %.2f\n",
                student.getKor(),
                student.getEng(),
                student.getMath(),
                student.calculateTotal(),
                student.calculateAverage());
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
        
        // 국어 점수 입력 및 유효성 검사
        System.out.print("국어(수정): ");
        int kor = 0;
        try {
            kor = Integer.parseInt(scanner.nextLine());
            if (kor < 0 || kor > 100) {
                System.out.println("점수는 0 이상 100 이하의 정수여야 합니다.");
                return;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }
        
        // 영어 점수 입력 및 유효성 검사
        System.out.print("영어(수정): ");
        int eng = 0;
        try {
            eng = Integer.parseInt(scanner.nextLine());
            if (eng < 0 || eng > 100) {
                System.out.println("점수는 0 이상 100 이하의 정수여야 합니다.");
                return;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }
        
        // 수학 점수 입력 및 유효성 검사
        System.out.print("수학(수정): ");
        int math = 0;
        try {
            math = Integer.parseInt(scanner.nextLine());
            if (math < 0 || math > 100) {
                System.out.println("점수는 0 이상 100 이하의 정수여야 합니다.");
                return;
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }
        
        // Student 정보 수정
        student.setName(name);
        student.setAge(age);
        student.setMajor(major);
        student.setKor(kor);
        student.setEng(eng);
        student.setMath(math);
        
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
    }
}
```

---

## 📝 코드 설명

### 1. Student 클래스

- **필드**: 학번, 이름, 나이, 전공, 국어, 영어, 수학 점수
- **생성자**: 모든 필드를 초기화
- **Getter/Setter**: 필드 접근 및 수정을 위한 메서드
- **계산 메서드**: `calculateTotal()`, `calculateAverage()`

### 2. StudentApplication 클래스

#### registerStudent() - 학생 등록

**배열 버전:**
```java
// 배열의 빈 자리에 저장
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] == null) {
        studentsArray[i] = newStudent;
        break;
    }
}
```

**ArrayList 버전:**
```java
// ArrayList에 추가 (간단!)
studentsList.add(newStudent);
```

**점수 유효성 검사:**
```java
int kor = Integer.parseInt(scanner.nextLine());
if (kor < 0 || kor > 100) {
    System.out.println("점수는 0 이상 100 이하의 정수여야 합니다.");
    return;
}
```

#### studentList() - 학생 목록

**배열 버전:**
```java
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] != null) {  // null 체크 필요!
        Student student = studentsArray[i];
        // 출력 (점수, 총점, 평균 포함)
    }
}
```

**ArrayList 버전:**
```java
// 향상된 for문 사용 (null 체크 불필요!)
for (Student student : studentsList) {
    System.out.printf("%s %s   %d %s   국어:%d 영어:%d 수학:%d 총점:%d 평균:%.2f\n", 
        student.getStudentId(), 
        student.getName(), 
        student.getAge(), 
        student.getMajor(),
        student.getKor(),
        student.getEng(),
        student.getMath(),
        student.calculateTotal(),
        student.calculateAverage());
}
```

#### searchStudent() - 학생 검색

- 점수, 총점, 평균 정보 포함하여 출력

#### updateStudent() - 학생 수정

- 점수 수정 시 유효성 검사 수행
- Setter로 점수 수정 후 총점과 평균이 자동으로 재계산됨

#### deleteStudent() - 학생 삭제

**배열 버전:**
```java
// 배열에서 해당 학생을 찾아 null로 설정
for (int i = 0; i < studentsArray.length; i++) {
    if (studentsArray[i] != null && studentsArray[i].getStudentId().equals(studentId)) {
        studentsArray[i] = null;
        break;
    }
}
```

**ArrayList 버전:**
```java
// ArrayList에서 삭제 (실제로 제거됨!)
studentsList.remove(student);
```

---

## 🔍 주요 포인트

### 1. 점수 유효성 검사

```java
int kor = Integer.parseInt(scanner.nextLine());
if (kor < 0 || kor > 100) {
    System.out.println("점수는 0 이상 100 이하의 정수여야 합니다.");
    return;
}
```

### 2. 총점과 평균 계산

```java
// Student 클래스의 메서드 사용
int total = student.calculateTotal();  // kor + eng + math
double average = student.calculateAverage();  // total / 3.0
```

### 3. 평균 출력 (소수점 둘째 자리)

```java
System.out.printf("평균: %.2f\n", student.calculateAverage());
```

### 4. ArrayList 관리

```java
// 학생 등록: 바로 추가
studentsList.add(newStudent);

// 학생 삭제: 바로 삭제
studentsList.remove(student);

// 순회: null 체크 불필요
for (Student student : studentsList) {
    // 모든 요소가 유효함
}
```

### 5. 점수 수정 시 자동 재계산

```java
student.setKor(90);
student.setEng(92);
student.setMath(88);
// 총점과 평균은 calculateTotal()과 calculateAverage() 메서드로 자동 계산됨
int total = student.calculateTotal();  // 270
double average = student.calculateAverage();  // 90.00
```

---

## 🎯 배열 버전 vs ArrayList 버전 비교

| 구분 | 배열 버전 | ArrayList 버전 |
|------|----------|---------------|
| **학생 등록** | 빈 자리(null) 찾아서 저장 | `list.add(student)` 바로 추가 |
| **학생 삭제** | `array[i] = null` (빈 자리 생성) | `list.remove(student)` (실제 삭제) |
| **순회 시 null 체크** | **필수** | **불필요** |
| **코드 복잡도** | 높음 | 낮음 |
| **크기 제한** | 최대 100명 | 제한 없음 |

---

## 📚 학습 목표 달성 체크

- ✅ 클래스 설계 (Student)
- ✅ ArrayList 사용법
- ✅ 점수 필드와 계산 메서드
- ✅ 점수 유효성 검사
- ✅ 예외 처리 (나이, 점수)
- ✅ 메서드 분리 (findStudent)
- ✅ 중복 체크
- ✅ 메뉴 시스템
- ✅ Getter/Setter 활용
- ✅ 향상된 for문 사용
- ✅ String 비교 (equals)

---

## 💡 개선 가능한 부분

1. **점수 입력 메서드 분리**: 점수 입력 및 유효성 검사 로직을 별도 메서드로 분리
2. **학번 유효성 검사**: 학번 형식 검증 추가
3. **나이 범위 체크**: 나이의 최소/최대 값 검증
4. **데이터 저장**: 파일에 저장하여 프로그램 종료 후에도 데이터 보존
5. **정렬 기능**: 총점, 평균 등으로 정렬 기능 추가
6. **통계 기능**: 전체 평균, 최고점, 최저점 등 통계 기능 추가

---

## 📚 참고 자료

- [배열 vs ArrayList 완전 비교](./배열-vs-ArrayList-비교.md)
- [학생 관리 시스템 (배열 버전, 점수 포함)](./학생관리시스템-점수포함-정답.md)
- [학생 관리 시스템 (ArrayList 버전)](./학생관리시스템-ArrayList-정답.md)


