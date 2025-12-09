# 프로젝트 리뷰 가이드

## 코드 리뷰 체크리스트

### 1. 코드 품질

#### 변수명
- [ ] 변수명이 의미 있게 지어졌는가?
- [ ] 약어 사용을 최소화했는가?
- [ ] 일관된 네이밍 컨벤션을 따르는가?

**예시:**
```java
// 나쁜 예
String s = "홍길동";
int n = 20;

// 좋은 예
String studentName = "홍길동";
int studentAge = 20;
```

#### 메서드
- [ ] 메서드가 하나의 책임만 수행하는가?
- [ ] 메서드명이 동작을 명확히 나타내는가?
- [ ] 메서드 길이가 적절한가? (일반적으로 20-30줄 이하)

**예시:**
```java
// 나쁜 예: 여러 책임
public void processStudent() {
    // 입력 받기
    // 검증하기
    // 저장하기
    // 출력하기
}

// 좋은 예: 단일 책임
public Student inputStudent() { }
public boolean validateStudent(Student student) { }
public void saveStudent(Student student) { }
public void printStudent(Student student) { }
```

#### 중복 코드
- [ ] 중복 코드가 없는가?
- [ ] 공통 로직을 메서드로 추출했는가?

**예시:**
```java
// 나쁜 예: 중복 코드
public void addStudent() {
    System.out.print("학번: ");
    String id = scanner.nextLine();
    // ...
}

public void updateStudent() {
    System.out.print("학번: ");
    String id = scanner.nextLine();
    // ...
}

// 좋은 예: 메서드 추출
private String inputStudentId() {
    System.out.print("학번: ");
    return scanner.nextLine();
}
```

#### 주석
- [ ] 필요한 곳에만 주석이 있는가?
- [ ] 주석이 코드를 설명하는가? (코드 자체가 명확하면 주석 불필요)
- [ ] JavaDoc 주석이 적절히 사용되었는가?

**예시:**
```java
// 나쁜 예: 불필요한 주석
int age = 20; // 나이를 20으로 설정

// 좋은 예: 의미 있는 주석
// 최소 나이 제한 확인
if (age < MIN_AGE) {
    throw new IllegalArgumentException("나이는 " + MIN_AGE + " 이상이어야 합니다.");
}
```

### 2. 객체지향 원칙

#### 단일 책임 원칙 (SRP)
- [ ] 각 클래스가 하나의 책임만 가지는가?

**예시:**
```java
// 나쁜 예: 여러 책임
class StudentManager {
    // 학생 관리
    // 파일 처리
    // 사용자 입력
}

// 좋은 예: 책임 분리
class StudentService { }      // 비즈니스 로직
class StudentRepository { }   // 데이터 저장
class StudentController { }   // 사용자 입력
```

#### 캡슐화
- [ ] 필드가 private으로 선언되었는가?
- [ ] getter/setter가 적절히 사용되는가?
- [ ] 불필요한 setter가 없는가?

**예시:**
```java
// 나쁜 예: public 필드
public class Student {
    public String name;
}

// 좋은 예: 캡슐화
public class Student {
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }
        this.name = name;
    }
}
```

#### 의존성 주입
- [ ] 의존성이 생성자를 통해 주입되는가?
- [ ] 하드코딩된 의존성이 없는가?

**예시:**
```java
// 나쁜 예: 하드코딩
public class StudentController {
    private StudentService service = new StudentService();
}

// 좋은 예: 의존성 주입
public class StudentController {
    private StudentService service;
    
    public StudentController(StudentService service) {
        this.service = service;
    }
}
```

### 3. 예외 처리

#### 예외 처리
- [ ] 예외 처리가 적절한가?
- [ ] 구체적인 예외를 처리하는가?
- [ ] 예외를 무시하지 않는가?

**예시:**
```java
// 나쁜 예: 예외 무시
try {
    file.read();
} catch (Exception e) {
    // 아무것도 하지 않음
}

// 좋은 예: 적절한 예외 처리
try {
    file.read();
} catch (FileNotFoundException e) {
    System.out.println("파일을 찾을 수 없습니다: " + e.getMessage());
    logger.error("파일 읽기 오류", e);
}
```

#### 사용자 친화적 메시지
- [ ] 사용자에게 친절한 오류 메시지를 제공하는가?

**예시:**
```java
// 나쁜 예
System.out.println("오류");

// 좋은 예
System.out.println("오류: 학생을 찾을 수 없습니다. (학번: " + studentId + ")");
```

#### 리소스 관리
- [ ] 리소스가 제대로 해제되는가?
- [ ] try-with-resources를 사용하는가?

**예시:**
```java
// 나쁜 예: 리소스 누수 가능
FileReader fr = new FileReader("file.txt");
// close() 호출 안 함

// 좋은 예: try-with-resources
try (FileReader fr = new FileReader("file.txt")) {
    // 사용
}  // 자동으로 close()
```

### 4. 성능

#### 불필요한 객체 생성
- [ ] 루프 안에서 불필요한 객체를 생성하지 않는가?

**예시:**
```java
// 나쁜 예: 루프 안에서 객체 생성
for (int i = 0; i < 1000; i++) {
    String str = new String("Hello");
}

// 좋은 예: 루프 밖에서 생성
String str = "Hello";
for (int i = 0; i < 1000; i++) {
    // 사용
}
```

#### 효율적인 알고리즘
- [ ] 적절한 자료구조를 사용하는가?
- [ ] 불필요한 반복이 없는가?

**예시:**
```java
// 나쁜 예: O(n²)
for (Student s1 : students) {
    for (Student s2 : students) {
        if (s1.equals(s2)) { }
    }
}

// 좋은 예: O(n)
Set<Student> studentSet = new HashSet<>(students);
```

## 개선 사항

### 1. 입력 검증 강화

```java
public class InputValidator {
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 150;
    
    public static boolean isValidAge(int age) {
        return age >= MIN_AGE && age <= MAX_AGE;
    }
    
    public static boolean isValidStudentId(String studentId) {
        return studentId != null && 
               !studentId.trim().isEmpty() && 
               studentId.matches("\\d{7}");  // 7자리 숫자
    }
    
    public static boolean isValidName(String name) {
        return name != null && 
               !name.trim().isEmpty() && 
               name.length() <= 50;
    }
}
```

### 2. 에러 메시지 개선

```java
public class ErrorMessages {
    public static final String STUDENT_NOT_FOUND = 
        "학생을 찾을 수 없습니다. (학번: %s)";
    
    public static final String INVALID_INPUT = 
        "잘못된 입력입니다: %s";
    
    public static String format(String template, Object... args) {
        return String.format(template, args);
    }
}
```

### 3. 로깅 추가

```java
import java.util.logging.Logger;

public class StudentService {
    private static final Logger logger = 
        Logger.getLogger(StudentService.class.getName());
    
    public void addStudent(Student student) {
        logger.info("학생 추가 시도: " + student.getStudentId());
        
        if (findStudent(student.getStudentId()) != null) {
            logger.warning("중복 학번: " + student.getStudentId());
            throw new IllegalArgumentException("이미 존재하는 학번입니다.");
        }
        
        students.add(student);
        logger.info("학생 추가 완료: " + student.getStudentId());
    }
}
```

### 4. 상수 추출

```java
public class Constants {
    public static final String FILE_PATH = "data/students.txt";
    public static final int MIN_AGE = 0;
    public static final int MAX_AGE = 150;
    public static final int MAX_NAME_LENGTH = 50;
}
```

## 리팩토링 체크리스트

- [ ] 중복 코드 제거
- [ ] 긴 메서드 분리
- [ ] 매직 넘버를 상수로 추출
- [ ] 의미 있는 변수명 사용
- [ ] 불필요한 주석 제거
- [ ] 예외 처리 개선
- [ ] 입력 검증 추가
- [ ] 에러 메시지 개선

## 프로젝트 완성도 평가

### 기능 완성도 (40점)
- [ ] 모든 CRUD 기능 구현 (20점)
- [ ] 파일 저장/불러오기 구현 (10점)
- [ ] 예외 처리 구현 (10점)

### 코드 품질 (30점)
- [ ] 객체지향 원칙 준수 (15점)
- [ ] 코드 가독성 (10점)
- [ ] 주석 및 문서화 (5점)

### 설계 (20점)
- [ ] 클래스 설계 (10점)
- [ ] 패키지 구조 (5점)
- [ ] 확장 가능성 (5점)

### 테스트 및 검증 (10점)
- [ ] 기능 테스트 (5점)
- [ ] 예외 상황 테스트 (5점)

## 총평

프로젝트를 완성한 후 다음을 확인하세요:

1. **기능이 모두 동작하는가?**
2. **코드가 읽기 쉬운가?**
3. **수정하기 쉬운가?**
4. **확장 가능한가?**
5. **예외 상황을 잘 처리하는가?**

이 체크리스트를 통해 프로젝트의 품질을 평가하고 개선할 수 있습니다.

