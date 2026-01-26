# Java ArrayList & HashMap 연습 문제 정답

---

## 목차

1. [ArrayList 기본 정답](#1-arraylist-기본-정답)
2. [중복 제거 정답](#2-중복-제거-정답)
3. [단어 빈도 정답](#3-단어-빈도-정답)
4. [학생 관리 정답](#4-학생-관리-정답)
5. [정렬 정답](#5-정렬-정답)
6. [종합 예제 정답](#6-종합-예제-정답)

---

## 1. ArrayList 기본 정답

### 📁 Problem1.java

```java
import java.util.ArrayList;

public class Problem1 {
    public static void main(String[] args) {
        // ArrayList 생성
        ArrayList<Integer> list = new ArrayList<>();
        
        // 1부터 10까지 숫자 추가
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        
        // 리스트 출력 (한 줄로)
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        
        // 또는 향상된 for문 사용
        // for (int num : list) {
        //     System.out.print(num + " ");
        // }
        // System.out.println();
    }
}
```

### 🔍 코드 설명

#### 1. ArrayList 생성
```java
ArrayList<Integer> list = new ArrayList<>();
```
- `ArrayList<Integer>`: 정수형 리스트 생성
- 제네릭 `<Integer>`를 사용하여 타입 안정성 확보

#### 2. 숫자 추가
```java
for (int i = 1; i <= 10; i++) {
    list.add(i);
}
```
- `add()` 메서드로 리스트에 요소 추가
- 1부터 10까지 반복하여 추가

#### 3. 리스트 출력
```java
for (int i = 0; i < list.size(); i++) {
    System.out.print(list.get(i));
    if (i < list.size() - 1) {
        System.out.print(" ");
    }
}
```
- `get(i)`: 인덱스 i의 요소 가져오기
- `size()`: 리스트의 크기 반환
- 마지막 요소 뒤에는 공백을 출력하지 않음

### 📊 실행 결과

```
1 2 3 4 5 6 7 8 9 10
```

---

## 2. 중복 제거 정답

### 📁 Problem2.java

#### 방법 1: contains() 메서드 사용

```java
import java.util.ArrayList;

public class Problem2 {
    public static void main(String[] args) {
        // 원본 리스트 생성 (중복 포함)
        ArrayList<String> originalList = new ArrayList<>();
        originalList.add("사과");
        originalList.add("바나나");
        originalList.add("사과");
        originalList.add("오렌지");
        originalList.add("바나나");
        originalList.add("포도");
        originalList.add("사과");
        
        System.out.println("중복 제거 전: " + originalList);
        
        // 중복 제거된 리스트 생성
        ArrayList<String> uniqueList = new ArrayList<>();
        
        for (String item : originalList) {
            // contains()로 중복 확인
            if (!uniqueList.contains(item)) {
                uniqueList.add(item);
            }
        }
        
        System.out.println("중복 제거 후: " + uniqueList);
    }
}
```

#### 방법 2: LinkedHashSet 사용 (권장)

```java
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Problem2 {
    public static void main(String[] args) {
        // 원본 리스트 생성
        ArrayList<String> originalList = new ArrayList<>();
        originalList.add("사과");
        originalList.add("바나나");
        originalList.add("사과");
        originalList.add("오렌지");
        originalList.add("바나나");
        originalList.add("포도");
        originalList.add("사과");
        
        System.out.println("중복 제거 전: " + originalList);
        
        // LinkedHashSet으로 중복 자동 제거 (순서 유지)
        LinkedHashSet<String> set = new LinkedHashSet<>(originalList);
        
        // 다시 ArrayList로 변환
        ArrayList<String> uniqueList = new ArrayList<>(set);
        
        System.out.println("중복 제거 후: " + uniqueList);
    }
}
```

### 🔍 코드 설명

#### 방법 1: contains() 사용
- `contains(item)`: 리스트에 해당 요소가 있는지 확인
- 없으면 추가, 있으면 추가하지 않음
- 시간 복잡도: O(n²)

#### 방법 2: LinkedHashSet 사용
- `LinkedHashSet`: 중복을 자동으로 제거하면서 순서 유지
- `HashSet`보다 효율적 (시간 복잡도: O(n))
- 순서가 중요할 때 사용

### 📊 실행 결과

```
중복 제거 전: [사과, 바나나, 사과, 오렌지, 바나나, 포도, 사과]
중복 제거 후: [사과, 바나나, 오렌지, 포도]
```

---

## 3. 단어 빈도 정답

### 📁 Problem3.java

```java
import java.util.HashMap;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 문장 입력받기
        System.out.print("문장을 입력하세요: ");
        String sentence = scanner.nextLine();
        
        // 문장을 단어로 분리
        String[] words = sentence.split(" ");
        
        // HashMap 생성 (단어를 키, 빈도를 값으로 저장)
        HashMap<String, Integer> wordCount = new HashMap<>();
        
        // 각 단어의 빈도 계산
        for (String word : words) {
            if (wordCount.containsKey(word)) {
                // 이미 존재하면 빈도 증가
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                // 처음 나오는 단어면 1로 초기화
                wordCount.put(word, 1);
            }
        }
        
        // 결과 출력
        System.out.println("\n단어 빈도:");
        for (String word : wordCount.keySet()) {
            System.out.println(word + ": " + wordCount.get(word) + "회");
        }
        
        scanner.close();
    }
}
```

### 🔍 코드 설명

#### 1. 문장 입력 및 분리
```java
String sentence = scanner.nextLine();
String[] words = sentence.split(" ");
```
- `nextLine()`: 한 줄 전체 입력받기
- `split(" ")`: 공백을 기준으로 문자열 분리

#### 2. HashMap으로 빈도 계산
```java
HashMap<String, Integer> wordCount = new HashMap<>();
```
- 키: 단어 (String)
- 값: 빈도 (Integer)

```java
if (wordCount.containsKey(word)) {
    wordCount.put(word, wordCount.get(word) + 1);
} else {
    wordCount.put(word, 1);
}
```
- `containsKey()`: 키가 존재하는지 확인
- `get()`: 값 가져오기
- `put()`: 키-값 쌍 추가 또는 수정

#### 3. 결과 출력
```java
for (String word : wordCount.keySet()) {
    System.out.println(word + ": " + wordCount.get(word) + "회");
}
```
- `keySet()`: 모든 키를 가져오기
- 각 키에 대한 값(빈도) 출력

### 📊 실행 결과

```
문장을 입력하세요: 자바는 객체지향 프로그래밍 언어입니다 자바는 재미있습니다

단어 빈도:
자바는: 2회
객체지향: 1회
프로그래밍: 1회
언어입니다: 1회
재미있습니다: 1회
```

---

## 4. 학생 관리 정답

### 📁 Problem4.java

```java
import java.util.HashMap;

public class Problem4 {
    public static void main(String[] args) {
        // HashMap 생성 (이름을 키, 점수를 값으로 저장)
        HashMap<String, Integer> students = new HashMap<>();
        
        // 학생 정보 추가
        students.put("홍길동", 85);
        students.put("김영희", 92);
        students.put("박철수", 78);
        students.put("이영수", 90);
        
        // 학생 점수 출력
        System.out.println("학생 점수:");
        for (String name : students.keySet()) {
            System.out.println(name + ": " + students.get(name) + "점");
        }
        
        // 평균 점수 계산
        int sum = 0;
        int count = students.size();
        
        for (int score : students.values()) {
            sum += score;
        }
        
        double average = (double) sum / count;
        
        // 평균 점수 출력
        System.out.println("\n평균 점수: " + average + "점");
    }
}
```

### 🔍 코드 설명

#### 1. HashMap 생성 및 데이터 추가
```java
HashMap<String, Integer> students = new HashMap<>();
students.put("홍길동", 85);
```
- 키: 학생 이름 (String)
- 값: 점수 (Integer)
- `put()`: 키-값 쌍 추가

#### 2. 학생 점수 출력
```java
for (String name : students.keySet()) {
    System.out.println(name + ": " + students.get(name) + "점");
}
```
- `keySet()`: 모든 키(이름) 가져오기
- `get(name)`: 해당 이름의 점수 가져오기

#### 3. 평균 점수 계산
```java
int sum = 0;
for (int score : students.values()) {
    sum += score;
}
double average = (double) sum / count;
```
- `values()`: 모든 값(점수) 가져오기
- 합계를 구한 후 학생 수로 나누기
- `(double)` 형변환으로 실수 나눗셈 수행

### 📊 실행 결과

```
학생 점수:
홍길동: 85점
김영희: 92점
박철수: 78점
이영수: 90점

평균 점수: 86.25점
```

---

## 5. 정렬 정답

### 📁 Problem5.java

```java
import java.util.ArrayList;
import java.util.Collections;

public class Problem5 {
    public static void main(String[] args) {
        // ArrayList 생성
        ArrayList<Integer> list = new ArrayList<>();
        
        // 숫자 추가
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(9);
        list.add(3);
        list.add(7);
        list.add(4);
        list.add(6);
        
        // 정렬 전 출력
        System.out.println("정렬 전: " + list);
        
        // 정렬 (오름차순)
        Collections.sort(list);
        
        // 정렬 후 출력
        System.out.println("정렬 후: " + list);
    }
}
```

### 🔍 코드 설명

#### 1. 리스트 생성 및 데이터 추가
```java
ArrayList<Integer> list = new ArrayList<>();
list.add(5);
list.add(2);
// ...
```
- 정수형 리스트에 숫자 추가

#### 2. 정렬
```java
Collections.sort(list);
```
- `Collections.sort()`: 리스트를 오름차순으로 정렬
- 원본 리스트가 정렬됨 (새 리스트 생성 안 함)

#### 3. 대안 방법
```java
// 방법 2: list.sort() 사용
list.sort(null);

// 방법 3: Comparator 사용 (내림차순)
Collections.sort(list, Collections.reverseOrder());
```

### 📊 실행 결과

```
정렬 전: [5, 2, 8, 1, 9, 3, 7, 4, 6]
정렬 후: [1, 2, 3, 4, 5, 6, 7, 8, 9]
```

---

## 6. 종합 예제 정답

### 📁 Problem6.java

```java
import java.util.ArrayList;

// Student 클래스
class Student {
    private String name;
    private int age;
    private int score;
    
    // 생성자
    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
    
    // getter 메서드
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public int getScore() {
        return score;
    }
    
    // toString 메서드
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", score=" + score + "}";
    }
}

// 메인 클래스
public class Problem6 {
    public static void main(String[] args) {
        // 학생 리스트 생성
        ArrayList<Student> students = new ArrayList<>();
        
        // 학생 정보 추가
        students.add(new Student("홍길동", 20, 85));
        students.add(new Student("김영희", 21, 92));
        students.add(new Student("박철수", 19, 78));
        students.add(new Student("이영수", 20, 90));
        
        // 모든 학생 정보 출력
        System.out.println("=== 학생 정보 ===");
        for (Student student : students) {
            System.out.println(student);
        }
        
        // 평균 점수 계산
        int sum = 0;
        for (Student student : students) {
            sum += student.getScore();
        }
        double average = (double) sum / students.size();
        
        System.out.println("\n평균 점수: " + average + "점");
        
        // 최고 점수 학생 찾기
        Student topStudent = students.get(0);
        for (Student student : students) {
            if (student.getScore() > topStudent.getScore()) {
                topStudent = student;
            }
        }
        
        System.out.println("최고 점수 학생: " + topStudent);
    }
}
```

### 🔍 코드 설명

#### 1. Student 클래스
```java
class Student {
    private String name;
    private int age;
    private int score;
    
    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", score=" + score + "}";
    }
}
```
- 필드: 이름, 나이, 점수
- 생성자: 모든 필드 초기화
- getter 메서드: 필드 값 반환
- `toString()`: 객체를 문자열로 변환

#### 2. 학생 정보 관리
```java
ArrayList<Student> students = new ArrayList<>();
students.add(new Student("홍길동", 20, 85));
```
- `ArrayList<Student>`: Student 객체를 저장하는 리스트
- `new Student()`: Student 객체 생성 및 추가

#### 3. 평균 점수 계산
```java
int sum = 0;
for (Student student : students) {
    sum += student.getScore();
}
double average = (double) sum / students.size();
```
- 모든 학생의 점수를 합산
- 학생 수로 나누어 평균 계산

#### 4. 최고 점수 학생 찾기
```java
Student topStudent = students.get(0);
for (Student student : students) {
    if (student.getScore() > topStudent.getScore()) {
        topStudent = student;
    }
}
```
- 첫 번째 학생을 초기값으로 설정
- 나머지 학생들과 비교하여 더 높은 점수를 가진 학생으로 갱신

### 📊 실행 결과

```
=== 학생 정보 ===
Student{name='홍길동', age=20, score=85}
Student{name='김영희', age=21, score=92}
Student{name='박철수', age=19, score=78}
Student{name='이영수', age=20, score=90}

평균 점수: 86.25점
최고 점수 학생: Student{name='김영희', age=21, score=92}
```

---

## 전체 실행 파일 (통합 테스트용)

### Problem1.java
```java
import java.util.ArrayList;

public class Problem1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
```

### Problem2.java
```java
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Problem2 {
    public static void main(String[] args) {
        ArrayList<String> originalList = new ArrayList<>();
        originalList.add("사과");
        originalList.add("바나나");
        originalList.add("사과");
        originalList.add("오렌지");
        originalList.add("바나나");
        originalList.add("포도");
        originalList.add("사과");
        
        System.out.println("중복 제거 전: " + originalList);
        
        LinkedHashSet<String> set = new LinkedHashSet<>(originalList);
        ArrayList<String> uniqueList = new ArrayList<>(set);
        
        System.out.println("중복 제거 후: " + uniqueList);
    }
}
```

### Problem3.java
```java
import java.util.HashMap;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("문장을 입력하세요: ");
        String sentence = scanner.nextLine();
        
        String[] words = sentence.split(" ");
        HashMap<String, Integer> wordCount = new HashMap<>();
        
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        
        System.out.println("\n단어 빈도:");
        for (String word : wordCount.keySet()) {
            System.out.println(word + ": " + wordCount.get(word) + "회");
        }
        
        scanner.close();
    }
}
```

### Problem4.java
```java
import java.util.HashMap;

public class Problem4 {
    public static void main(String[] args) {
        HashMap<String, Integer> students = new HashMap<>();
        students.put("홍길동", 85);
        students.put("김영희", 92);
        students.put("박철수", 78);
        students.put("이영수", 90);
        
        System.out.println("학생 점수:");
        for (String name : students.keySet()) {
            System.out.println(name + ": " + students.get(name) + "점");
        }
        
        int sum = 0;
        for (int score : students.values()) {
            sum += score;
        }
        double average = (double) sum / students.size();
        
        System.out.println("\n평균 점수: " + average + "점");
    }
}
```

### Problem5.java
```java
import java.util.ArrayList;
import java.util.Collections;

public class Problem5 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(9);
        list.add(3);
        list.add(7);
        list.add(4);
        list.add(6);
        
        System.out.println("정렬 전: " + list);
        Collections.sort(list);
        System.out.println("정렬 후: " + list);
    }
}
```

### Problem6.java
```java
import java.util.ArrayList;

class Student {
    private String name;
    private int age;
    private int score;
    
    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", score=" + score + "}";
    }
}

public class Problem6 {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("홍길동", 20, 85));
        students.add(new Student("김영희", 21, 92));
        students.add(new Student("박철수", 19, 78));
        students.add(new Student("이영수", 20, 90));
        
        System.out.println("=== 학생 정보 ===");
        for (Student student : students) {
            System.out.println(student);
        }
        
        int sum = 0;
        for (Student student : students) {
            sum += student.getScore();
        }
        double average = (double) sum / students.size();
        System.out.println("\n평균 점수: " + average + "점");
        
        Student topStudent = students.get(0);
        for (Student student : students) {
            if (student.getScore() > topStudent.getScore()) {
                topStudent = student;
            }
        }
        System.out.println("최고 점수 학생: " + topStudent);
    }
}
```

---

## 핵심 포인트

### 1. ArrayList 사용법
- `add()`: 요소 추가
- `get()`: 요소 가져오기
- `size()`: 리스트 크기
- `contains()`: 요소 존재 여부 확인
- `Collections.sort()`: 정렬

### 2. HashMap 사용법
- `put()`: 키-값 쌍 추가
- `get()`: 값 가져오기
- `keySet()`: 모든 키 가져오기
- `values()`: 모든 값 가져오기
- `containsKey()`: 키 존재 여부 확인
- `getOrDefault()`: 값 가져오기 (없으면 기본값)

### 3. 주요 패턴
- **반복문**: `for-each` 루프로 리스트/맵 순회
- **조건문**: `if` 문으로 조건 확인
- **계산**: 합계, 평균, 최댓값/최솟값 찾기
- **객체**: 클래스 정의 및 객체 생성

---

## 추가 학습

- `HashSet`, `LinkedHashSet`: 중복 제거
- `TreeMap`: 정렬된 맵
- `Collections` 클래스의 다양한 메서드
- 제네릭(Generic)의 활용
- 람다 표현식과 스트림 API
