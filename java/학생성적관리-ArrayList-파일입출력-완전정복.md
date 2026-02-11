# 학생성적관리 시스템 - ArrayList와 파일입출력 완전 정복

## 목차

1. [시스템 개요](#시스템-개요)
2. [클래스 구조](#클래스-구조)
3. [클래스별 상세 설명](#클래스별-상세-설명)
4. [데이터 흐름](#데이터-흐름)
5. [주요 기능 설명](#주요-기능-설명)
6. [코드 개선 제안](#코드-개선-제안)

---

## 시스템 개요

### 프로젝트 정보

**프로젝트명:** 학생성적관리 시스템  
**패키지:** `day10.example2`  
**주요 기술:**
- ArrayList를 이용한 데이터 관리
- 파일 입출력 (FileReader, FileWriter)
- CRUD 기능 (생성, 조회, 수정, 삭제)
- 메뉴 기반 사용자 인터페이스

### 주요 기능

1. 학생정보 입력
2. 학생정보 삭제
3. 학생정보 검색
4. 학생정보 수정
5. 학생 목록 보기
6. 파일로 저장하기
7. 학생정보 파일 불러오기

---

## 클래스 구조

```
day10.example2
├── mainClass          (메인 클래스 - 프로그램 진입점)
├── StudentDAO         (데이터 접근 객체 - CRUD 로직)
├── StudentDTO         (데이터 전송 객체 - 학생 정보)
└── FileClass          (파일 처리 클래스 - 파일 입출력)
```

### 클래스 역할

| 클래스 | 역할 | 주요 책임 |
|--------|------|-----------|
| `mainClass` | 프로그램 진입점 | 메뉴 출력, 사용자 입력 처리 |
| `StudentDAO` | 데이터 접근 객체 | ArrayList 관리, CRUD 작업 |
| `StudentDTO` | 데이터 전송 객체 | 학생 정보 저장 |
| `FileClass` | 파일 처리 | 파일 생성, 읽기, 쓰기 |

---

## 클래스별 상세 설명

### 1. StudentDTO 클래스

**역할:** 학생 정보를 담는 데이터 클래스

#### 필드 (Field)

```java
private int id;        // 학생 ID
private String name;    // 이름
private int age;       // 나이
private int kor;       // 국어 점수
private int eng;       // 영어 점수
private int math;      // 수학 점수
```

#### 생성자 (Constructor)

**1. 기본 생성자**
```java
public StudentDTO() {
}
```

**2. 매개변수 생성자**
```java
public StudentDTO(int id, String name, int age, int kor, int eng, int math) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
}
```

#### 주요 메서드

**Getter/Setter 메서드**
- `getId()`, `setId()`
- `getName()`, `setName()`
- `getAge()`, `setAge()`
- `getKor()`, `setKor()`
- `getEng()`, `setEng()`
- `getMath()`, `setMath()`

**toString() 메서드**
```java
@Override
public String toString() {
    return " "+name + " \t " + age + "\t " + kor + "\t " + eng + "\t " + math;
}
```

**설명:**
- 객체의 문자열 표현 반환
- 탭(`\t`)으로 구분하여 출력 형식 지정

---

### 2. StudentDAO 클래스

**역할:** 학생 데이터를 관리하는 데이터 접근 객체

#### 필드 (Field)

```java
Scanner sc = new Scanner(System.in);
private ArrayList<StudentDTO> slist;  // 학생 리스트
FileClass file = new FileClass("Student","Student_Grade");  // 파일 처리 객체
```

#### 생성자 (Constructor)

```java
public StudentDAO() {
    slist = new ArrayList<StudentDTO>();
    
    // 기본데이터
    StudentDTO s1 = new StudentDTO(0,"테스트1",11,100,90,80);
    StudentDTO s2 = new StudentDTO(1,"테스트2",22,90,89,91);
    StudentDTO s3 = new StudentDTO(2,"테스트3",33,85,77,55);
    StudentDTO s4 = new StudentDTO(3,"테스트4",44,77,68,85);
    
    slist.add(s1);
    slist.add(s2);
    slist.add(s3);
    slist.add(s4);
}
```

**설명:**
- ArrayList 초기화
- 테스트용 기본 데이터 4개 추가

#### CRUD 메서드

**1. insert() - 추가**
```java
public void insert(StudentDTO s) {
    slist.add(s);
}
```

**설명:**
- ArrayList에 학생 객체 추가
- 리스트 끝에 추가됨

**2. delete() - 삭제**
```java
public void delete(int index) {
    slist.remove(index);
}
```

**설명:**
- 인덱스로 학생 삭제
- 삭제 후 뒤의 요소들이 앞으로 이동

**3. select() - 조회**
```java
public StudentDTO select(int index) {
    return slist.get(index);
}
```

**설명:**
- 인덱스로 학생 정보 조회
- StudentDTO 객체 반환

**4. update() - 수정**
```java
public void update(int index, StudentDTO s) {
    slist.set(index, s);
}
```

**설명:**
- 인덱스 위치의 학생 정보를 새 객체로 교체
- `set()` 메서드 사용

**5. printAll() - 전체 출력**
```java
public void printAll() {
    System.out.println(" 이름\t 나이\t 국어\t 영어 \t수학\n"
            + "-------------------------------------");
    for (int i = 0; i < slist.size(); i++) {
        System.out.println(slist.get(i).toString());
    }
}
```

**설명:**
- 모든 학생 정보를 표 형식으로 출력
- 헤더와 구분선 출력

**6. searchIndex() - 검색**
```java
public int searchIndex() {
    int index = -1;
    System.out.println("학생의 이름을 입력하세요");
    System.out.print(">> ");
    String name = sc.next();
    for (int i = 0; i < slist.size(); i++) {
        if(slist.get(i).getName().equals(name)) {
            index = i;
            break;
        }
    }
    return index;
}
```

**설명:**
- 이름으로 학생 검색
- 찾으면 인덱스 반환, 못 찾으면 -1 반환
- 첫 번째 일치하는 학생만 반환

#### 사용자 인터페이스 메서드

**1. userInsert() - 학생 추가**
```java
public void userInsert() {
    StudentDTO s = new StudentDTO();
    s.setId(slist.size()-1);  // ⚠️ 버그: slist.size()여야 함
    System.out.println("< 학생 추가하기 > ");
    System.out.print("이름 : ");
    s.setName(sc.next());
    System.out.print("나이 : ");
    s.setAge(sc.nextInt());
    System.out.print("국어점수 : ");
    s.setKor(sc.nextInt());
    System.out.print("영어점수 : ");
    s.setEng(sc.nextInt());
    System.out.print("수학점수 : ");
    s.setMath(sc.nextInt());
    
    insert(s);
    System.out.println("학생이 추가되었습니다.");
}
```

**설명:**
- 사용자로부터 입력받아 학생 추가
- **버그**: `s.setId(slist.size()-1)` → `s.setId(slist.size())`로 수정 필요

**2. userSelect() - 학생 조회**
```java
public void userSelect() {
    System.out.println("< 학생정보 보기 >");
    int index = searchIndex();
    if(index == -1) {
        System.out.println("찾는 학생이 없습니다.");
    }else {
        System.out.println(" 이름\t 나이\t 국어\t 영어 \t수학\n"
                + "-------------------------------------");
        StudentDTO s = select(index);
        System.out.println(s);
    }
}
```

**설명:**
- 이름으로 검색하여 학생 정보 출력
- 찾지 못하면 오류 메시지 출력

**3. userDelete() - 학생 삭제**
```java
public void userDelete() {
    System.out.println("< 학생정보 삭제 >");
    int index = searchIndex();
    if(index == -1) {
        System.out.println("찾는 학생이 없습니다.");
    }else {
        String name = slist.get(index).getName();
        delete(index);
        System.out.println(name+" 학생정보를 삭제했습니다.");
    }
}
```

**설명:**
- 이름으로 검색하여 학생 삭제
- 삭제 전 이름을 저장하여 확인 메시지 출력

**4. userUpdate() - 학생 수정**
```java
public void userUpdate() {
    System.out.println("< 학생정보 수정 >");
    int index = searchIndex();
    if(index == -1) {
        System.out.println("찾는 학생이 없습니다.");
    }else {
        StudentDTO s = new StudentDTO();
        s.setId(slist.get(index).getId());
        s.setName(slist.get(index).getName());
        s.setAge(slist.get(index).getAge());
        
        System.out.println("<" + slist.get(index).getName()+ "학생의 정보수정 >");
        System.out.print("국어점수 : ");
        s.setKor(sc.nextInt());
        System.out.print("영어점수 : ");
        s.setEng(sc.nextInt());
        System.out.print("수학점수 : ");
        s.setMath(sc.nextInt());
        update(index, s);
    }
}
```

**설명:**
- 이름으로 검색하여 학생 정보 수정
- ID, 이름, 나이는 유지하고 점수만 수정
- 새 객체를 생성하여 교체

#### 파일 처리 메서드

**1. dataSave() - 파일 저장**
```java
public void dataSave() throws Exception{
    file.create();
    String str = " 이름\t 나이\t 국어\t 영어 \t수학\n"
            + "-------------------------------------\n";
    for (int i = 0; i < slist.size(); i++) {
        str += slist.get(i).toString()+"\n";
    }
    file.write(str);
    System.out.println("데이터를 저장했습니다.");
}
```

**설명:**
- 모든 학생 정보를 문자열로 변환
- 파일에 저장
- 헤더 포함하여 저장

**2. dataLoad() - 파일 불러오기**
```java
public void dataLoad() {
    try {
        file.read();
    } catch (Exception e) {
        System.out.println("읽을 파일이 없습니다.");
    }
}
```

**설명:**
- 파일에서 데이터 읽기
- 파일이 없으면 오류 메시지 출력
- **주의**: 읽은 데이터를 ArrayList에 로드하지 않음 (단순 출력만)

---

### 3. FileClass 클래스

**역할:** 파일 입출력을 담당하는 유틸리티 클래스

#### 필드 (Field)

```java
private File file;
private String dir;
private String fileName;
```

#### 생성자 (Constructor)

**1. 기본 생성자**
```java
public FileClass() {
    file = new File("d:\\");
}
```

**2. 매개변수 생성자**
```java
public FileClass(String dir, String fileName) {
    file = new File("d:\\" + dir + "\\" + fileName+".txt");
    this.dir = "d:\\" + dir;
    this.fileName = fileName +".txt";
}
```

**설명:**
- 디렉토리와 파일명을 받아 파일 경로 생성
- 기본 경로: `d:\Student\Student_Grade.txt`

#### 주요 메서드

**1. check() - 파일 존재 확인**
```java
public boolean check(File file) {
    if(file.exists()) {
        return true; 
    }
    return false;
}
```

**설명:**
- 파일이 존재하는지 확인
- 존재하면 `true`, 없으면 `false`

**2. create() - 파일 생성**
```java
public void create() throws Exception{
    boolean exist = check(file);
    if(exist) {
        file.delete();
        file.createNewFile();
    }
    else {
        file = new File(dir);
        file.mkdirs();
        file = new File(dir+"\\" + fileName);
        file.createNewFile();
    }
}
```

**설명:**
- 파일이 있으면 삭제 후 재생성
- 파일이 없으면 디렉토리 생성 후 파일 생성
- `mkdirs()`: 필요한 모든 상위 디렉토리까지 생성

**3. read() - 파일 읽기**
```java
public void read() throws Exception{
    boolean exist = check(file);
    if(exist) {
        FileReader fr = new FileReader(file);
        BufferedReader bw = new BufferedReader(fr);
        String str;
        while((str = bw.readLine()) != null ){
            System.out.println(str);
        }
        bw.close();
    }else {
        System.out.println("읽을 파일이 없습니다.");
    }
}
```

**설명:**
- 파일이 존재하면 한 줄씩 읽어서 출력
- `BufferedReader`로 효율적인 읽기
- 파일이 없으면 오류 메시지 출력
- **주의**: `FileReader`는 닫히지 않음 (리소스 누수 가능)

**4. write() - 파일 쓰기**
```java
public void write(String str) throws Exception{
    FileWriter fw = new FileWriter(file);
    PrintWriter pw = new PrintWriter(fw);
    pw.println(str);
    fw.close();
}
```

**설명:**
- 문자열을 파일에 쓰기
- `PrintWriter`로 편리한 출력
- **주의**: `PrintWriter`는 닫히지 않음 (리소스 누수 가능)

---

### 4. mainClass 클래스

**역할:** 프로그램의 진입점, 메뉴 처리

#### main() 메서드

```java
public static void main(String[] args) {
    StudentDAO test = new StudentDAO();
    Scanner sc = new Scanner(System.in);
    while (true) {
        // 메뉴 출력
        System.out.println("1. 학생정보 입력");
        System.out.println("2. 학생정보 삭제");
        System.out.println("3. 학생정보 검색");
        System.out.println("4. 학생정보 수정");
        System.out.println("5. 학생 목록 보기");
        System.out.println("6. 파일로 저장하기");
        System.out.println("7. 학생정보 파일 불러오기");
        System.out.println("0. 종료");

        System.out.print(">> ");
        int choice;
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            choice = -1;
        }

        // 메뉴 처리
        if (choice == 1) {
            test.userInsert();
        } else if (choice == 2) {
            test.userDelete();
        } else if (choice == 3) {
            test.userSelect();
        } else if (choice == 4) {
            test.userUpdate();
        } else if (choice == 5) {
            test.printAll();
        } else if (choice == 6) {
            try {
                test.dataSave();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (choice == 7) {
            test.dataLoad();
        } else if (choice == 0) {
            System.out.println("종료합니다.");
            sc.close();
            System.exit(0);
        } else {
            System.out.println("잘못 선택했습니다.");
        }
    }
}
```

**설명:**
- 무한 루프로 메뉴 반복 출력
- 사용자 입력에 따라 해당 기능 호출
- 예외 처리로 잘못된 입력 처리
- 0번 선택 시 프로그램 종료

---

## 데이터 흐름

### 1. 학생 추가 흐름

```
사용자 입력
    ↓
mainClass.main()
    ↓
StudentDAO.userInsert()
    ↓
Scanner로 정보 입력
    ↓
StudentDTO 객체 생성
    ↓
StudentDAO.insert()
    ↓
ArrayList에 추가
```

### 2. 학생 조회 흐름

```
사용자 입력 (이름)
    ↓
mainClass.main()
    ↓
StudentDAO.userSelect()
    ↓
StudentDAO.searchIndex()
    ↓
ArrayList 순회하여 이름 검색
    ↓
인덱스 반환
    ↓
StudentDAO.select()
    ↓
StudentDTO 객체 반환
    ↓
화면에 출력
```

### 3. 파일 저장 흐름

```
사용자 선택 (6번)
    ↓
mainClass.main()
    ↓
StudentDAO.dataSave()
    ↓
FileClass.create() - 파일 생성
    ↓
ArrayList를 문자열로 변환
    ↓
FileClass.write() - 파일에 쓰기
    ↓
파일 저장 완료
```

---

## 주요 기능 설명

### 1. CRUD 기능

**Create (생성):**
- `userInsert()`: 사용자 입력으로 학생 추가
- `insert()`: ArrayList에 추가

**Read (조회):**
- `userSelect()`: 이름으로 학생 검색하여 출력
- `printAll()`: 모든 학생 목록 출력
- `select()`: 인덱스로 학생 조회

**Update (수정):**
- `userUpdate()`: 이름으로 검색하여 점수 수정
- `update()`: 인덱스로 학생 정보 교체

**Delete (삭제):**
- `userDelete()`: 이름으로 검색하여 삭제
- `delete()`: 인덱스로 학생 삭제

### 2. 파일 입출력

**파일 저장:**
- `dataSave()`: ArrayList의 모든 데이터를 파일로 저장
- 파일 경로: `d:\Student\Student_Grade.txt`

**파일 불러오기:**
- `dataLoad()`: 파일에서 데이터 읽기
- **주의**: 읽은 데이터를 ArrayList에 로드하지 않음 (단순 출력만)

### 3. 검색 기능

**searchIndex() 메서드:**
- 이름으로 학생 검색
- 첫 번째 일치하는 학생의 인덱스 반환
- 못 찾으면 -1 반환

---

## 코드 개선 제안

### 1. 버그 수정

#### 버그 1: userInsert()의 ID 설정

**현재 코드:**
```java
s.setId(slist.size()-1);
```

**문제:**
- 첫 번째 학생 추가 시: `slist.size() = 4` → ID = 3 (중복)
- 마지막 인덱스와 동일한 ID가 설정됨

**수정:**
```java
s.setId(slist.size());  // 다음 ID로 설정
```

#### 버그 2: FileClass의 리소스 관리

**현재 코드:**
```java
public void read() throws Exception{
    FileReader fr = new FileReader(file);
    BufferedReader bw = new BufferedReader(fr);
    // ...
    bw.close();  // BufferedReader만 닫음
    // FileReader는 닫히지 않음
}
```

**수정:**
```java
public void read() throws Exception{
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    try {
        String str;
        while((str = br.readLine()) != null ){
            System.out.println(str);
        }
    } finally {
        br.close();
        fr.close();
    }
}
```

**또는 try-with-resources 사용:**
```java
public void read() throws Exception{
    try (FileReader fr = new FileReader(file);
         BufferedReader br = new BufferedReader(fr)) {
        String str;
        while((str = br.readLine()) != null ){
            System.out.println(str);
        }
    }
}
```

#### 버그 3: dataLoad() 기능 불완전

**현재 코드:**
```java
public void dataLoad() {
    try {
        file.read();  // 단순 출력만 함
    } catch (Exception e) {
        System.out.println("읽을 파일이 없습니다.");
    }
}
```

**문제:**
- 파일에서 읽은 데이터를 ArrayList에 로드하지 않음
- 단순히 화면에 출력만 함

**개선:**
```java
public void dataLoad() {
    try {
        FileReader fr = new FileReader(file.file);
        BufferedReader br = new BufferedReader(fr);
        
        String line;
        br.readLine(); // 헤더 건너뛰기
        br.readLine(); // 구분선 건너뛰기
        
        slist.clear(); // 기존 데이터 초기화
        
        while((line = br.readLine()) != null && !line.trim().isEmpty()) {
            // 파일 형식에 맞게 파싱하여 StudentDTO 생성
            // 예: " 이름\t 나이\t 국어\t 영어 \t수학"
            String[] parts = line.trim().split("\t");
            if(parts.length >= 5) {
                StudentDTO s = new StudentDTO();
                s.setName(parts[0].trim());
                s.setAge(Integer.parseInt(parts[1].trim()));
                s.setKor(Integer.parseInt(parts[2].trim()));
                s.setEng(Integer.parseInt(parts[3].trim()));
                s.setMath(Integer.parseInt(parts[4].trim()));
                slist.add(s);
            }
        }
        
        br.close();
        fr.close();
        System.out.println("데이터를 불러왔습니다.");
    } catch (Exception e) {
        System.out.println("파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
    }
}
```

### 2. 코드 개선 사항

#### 개선 1: switch문 사용

**현재 코드:**
```java
if (choice == 1) {
    test.userInsert();
} else if (choice == 2) {
    test.userDelete();
} // ...
```

**개선:**
```java
switch(choice) {
    case 1:
        test.userInsert();
        break;
    case 2:
        test.userDelete();
        break;
    // ...
    default:
        System.out.println("잘못 선택했습니다.");
}
```

#### 개선 2: 예외 처리 강화

**현재 코드:**
```java
try {
    choice = sc.nextInt();
} catch (Exception e) {
    choice = -1;
}
```

**개선:**
```java
try {
    choice = sc.nextInt();
    sc.nextLine(); // 버퍼 비우기
} catch (InputMismatchException e) {
    System.out.println("숫자를 입력하세요.");
    sc.nextLine(); // 잘못된 입력 제거
    choice = -1;
}
```

#### 개선 3: ID 자동 관리

**개선된 userInsert():**
```java
public void userInsert() {
    StudentDTO s = new StudentDTO();
    s.setId(slist.size()); // 수정: 다음 ID로 설정
    // ...
}
```

#### 개선 4: 파일 경로 설정

**현재 코드:**
```java
FileClass file = new FileClass("Student","Student_Grade");
```

**개선:**
```java
// 파일 경로를 상수로 관리
private static final String FILE_DIR = "Student";
private static final String FILE_NAME = "Student_Grade";
FileClass file = new FileClass(FILE_DIR, FILE_NAME);
```

#### 개선 5: 검색 기능 강화

**현재 코드:**
```java
public int searchIndex() {
    // 첫 번째 일치하는 학생만 반환
}
```

**개선:**
```java
// 여러 학생 검색 가능하도록
public List<Integer> searchIndices(String name) {
    List<Integer> indices = new ArrayList<>();
    for (int i = 0; i < slist.size(); i++) {
        if(slist.get(i).getName().equals(name)) {
            indices.add(i);
        }
    }
    return indices;
}
```

---

## 실행 예시

### 메뉴 화면

```
1. 학생정보 입력
2. 학생정보 삭제
3. 학생정보 검색
4. 학생정보 수정
5. 학생 목록 보기
6. 파일로 저장하기
7. 학생정보 파일 불러오기
0. 종료
>> 
```

### 학생 목록 보기 (5번)

```
 이름	 나이	 국어	 영어 	수학
-------------------------------------
 테스트1 	 11	 100	 90	 80
 테스트2 	 22	 90	 89	 91
 테스트3 	 33	 85	 77	 55
 테스트4 	 44	 77	 68	 85
```

### 학생 추가 (1번)

```
< 학생 추가하기 > 
이름 : 홍길동
나이 : 20
국어점수 : 85
영어점수 : 90
수학점수 : 95
학생이 추가되었습니다.
```

---

## 핵심 학습 포인트

### 1. ArrayList 사용법

- `add()`: 요소 추가
- `remove()`: 요소 삭제
- `get()`: 요소 조회
- `set()`: 요소 수정
- `size()`: 리스트 크기

### 2. 파일 입출력

- `FileReader` / `FileWriter`: 문자 기반 입출력
- `BufferedReader`: 효율적인 읽기
- `PrintWriter`: 편리한 쓰기
- 리소스 관리: `close()` 또는 try-with-resources

### 3. CRUD 패턴

- Create: `insert()`
- Read: `select()`, `printAll()`
- Update: `update()`
- Delete: `delete()`

### 4. 예외 처리

- `try-catch`: 예외 처리
- `throws`: 예외 전달
- 입력 검증: 잘못된 입력 처리

### 5. 객체지향 설계

- DTO: 데이터 저장
- DAO: 데이터 접근 로직
- 분리된 책임: 각 클래스의 역할 분리

---

## 체크리스트

### ArrayList
- [ ] ArrayList 생성 및 초기화
- [ ] 요소 추가 (`add()`)
- [ ] 요소 삭제 (`remove()`)
- [ ] 요소 조회 (`get()`)
- [ ] 요소 수정 (`set()`)
- [ ] 리스트 순회 (`for` 루프)

### 파일 입출력
- [ ] 파일 생성 (`createNewFile()`)
- [ ] 파일 읽기 (`FileReader`, `BufferedReader`)
- [ ] 파일 쓰기 (`FileWriter`, `PrintWriter`)
- [ ] 리소스 관리 (`close()`)

### CRUD 기능
- [ ] Create: 학생 추가
- [ ] Read: 학생 조회
- [ ] Update: 학생 수정
- [ ] Delete: 학생 삭제

### 예외 처리
- [ ] try-catch 사용
- [ ] 입력 검증
- [ ] 파일 오류 처리

---

**작성일:** 2026-01-30  
**프로젝트:** 학생성적관리 시스템 - ArrayList와 파일입출력
