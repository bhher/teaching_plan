# 배열(Array) vs ArrayList 완전 비교

## 📌 학습 목표

- 배열과 ArrayList의 차이점을 이해한다
- 각각의 특징과 장단점을 이해한다
- 언제 배열을 사용하고, 언제 ArrayList를 사용할지 판단할 수 있다
- 배열과 ArrayList의 사용법을 비교하여 학습한다

---

## 1️⃣ 배열과 ArrayList란?

### 배열 (Array)

**배열**은 같은 타입의 데이터를 연속된 메모리 공간에 저장하는 자료구조입니다.

```java
// 배열 선언 및 초기화
int[] numbers = new int[5];           // 크기 5인 정수 배열
String[] names = {"홍길동", "김철수"};  // 초기값과 함께 선언
```

### ArrayList

**ArrayList**는 Java 컬렉션 프레임워크의 List 인터페이스를 구현한 클래스로, 배열을 기반으로 동적으로 크기가 변하는 자료구조입니다.

```java
// ArrayList 선언 및 초기화
import java.util.ArrayList;

ArrayList<Integer> numbers = new ArrayList<>();  // 정수 ArrayList
ArrayList<String> names = new ArrayList<>();     // 문자열 ArrayList
```

---

## 2️⃣ 기본 비교표

| 구분 | 배열 (Array) | ArrayList |
|------|-------------|-----------|
| **크기** | 고정 (생성 시 결정) | 동적 (자동 조정) |
| **선언** | `타입[] 변수명` | `ArrayList<타입> 변수명` |
| **생성** | `new 타입[크기]` | `new ArrayList<>()` |
| **길이/크기** | `.length` | `.size()` |
| **요소 접근** | `배열[인덱스]` | `.get(인덱스)` |
| **요소 변경** | `배열[인덱스] = 값` | `.set(인덱스, 값)` |
| **요소 추가** | 불가능 (크기 고정) | `.add(값)` / `.add(인덱스, 값)` |
| **요소 삭제** | 불가능 | `.remove(인덱스)` / `.remove(값)` |
| **타입** | 기본 타입, 참조 타입 모두 | 참조 타입만 (Wrapper 클래스) |
| **메모리** | 연속된 메모리 | 연속된 메모리 (내부 배열) |
| **성능** | 빠름 (직접 접근) | 약간 느림 (메서드 호출) |

---

## 3️⃣ 생성 및 초기화 비교

### 배열 생성 및 초기화

```java
// 방법 1: 크기만 지정
int[] arr1 = new int[5];  // 기본값으로 초기화 (0, false, null)

// 방법 2: 초기값과 함께 선언
int[] arr2 = {1, 2, 3, 4, 5};
String[] arr3 = {"사과", "바나나", "오렌지"};

// 방법 3: 선언과 생성 분리
int[] arr4;
arr4 = new int[3];

// 방법 4: 선언과 초기화 분리
int[] arr5;
arr5 = new int[]{10, 20, 30};
```

### ArrayList 생성 및 초기화

```java
import java.util.ArrayList;

// 방법 1: 기본 생성
ArrayList<Integer> list1 = new ArrayList<>();  // 초기 용량 10

// 방법 2: 초기 용량 지정
ArrayList<Integer> list2 = new ArrayList<>(20);  // 초기 용량 20

// 방법 3: 다른 컬렉션으로부터 생성
ArrayList<Integer> list3 = new ArrayList<>(list1);

// 방법 4: 요소 추가
ArrayList<String> list4 = new ArrayList<>();
list4.add("사과");
list4.add("바나나");
list4.add("오렌지");
```

---

## 4️⃣ 주요 작업 비교

### 4-1. 요소 접근 (읽기)

#### 배열

```java
int[] numbers = {10, 20, 30, 40, 50};

// 인덱스로 접근
int value = numbers[0];  // 10
int value2 = numbers[2]; // 30

// 반복문으로 접근
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}

// 향상된 for문
for (int num : numbers) {
    System.out.println(num);
}
```

#### ArrayList

```java
import java.util.ArrayList;

ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
numbers.add(50);

// 인덱스로 접근
int value = numbers.get(0);  // 10
int value2 = numbers.get(2); // 30

// 반복문으로 접근
for (int i = 0; i < numbers.size(); i++) {
    System.out.println(numbers.get(i));
}

// 향상된 for문
for (int num : numbers) {
    System.out.println(num);
}
```

### 4-2. 요소 변경 (수정)

#### 배열

```java
int[] numbers = {10, 20, 30};

// 직접 할당
numbers[0] = 100;  // {100, 20, 30}
numbers[1] = 200;  // {100, 200, 30}
```

#### ArrayList

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);

// set 메서드 사용
numbers.set(0, 100);  // {100, 20, 30}
numbers.set(1, 200);  // {100, 200, 30}
```

### 4-3. 요소 추가

#### 배열

```java
// ❌ 배열은 크기가 고정되어 있어 요소를 추가할 수 없음
int[] numbers = new int[5];
numbers[0] = 10;
numbers[1] = 20;
// numbers[5] = 30;  // ❌ ArrayIndexOutOfBoundsException

// 배열 크기 증가가 필요한 경우 → 새 배열 생성 필요
int[] numbers = {10, 20, 30};
int[] newNumbers = new int[numbers.length + 1];
for (int i = 0; i < numbers.length; i++) {
    newNumbers[i] = numbers[i];
}
newNumbers[numbers.length] = 40;  // 요소 추가
numbers = newNumbers;  // 참조 변경
```

#### ArrayList

```java
import java.util.ArrayList;

ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);      // 끝에 추가
numbers.add(20);      // 끝에 추가
numbers.add(30);      // 끝에 추가

numbers.add(1, 15);   // 인덱스 1 위치에 추가 → {10, 15, 20, 30}

// 크기가 자동으로 증가
numbers.add(40);      // {10, 15, 20, 30, 40}
```

### 4-4. 요소 삭제

#### 배열

```java
// ❌ 배열은 크기가 고정되어 있어 요소를 삭제할 수 없음
int[] numbers = {10, 20, 30, 40, 50};

// 요소를 "삭제"하려면 → 새 배열 생성 필요
int[] newNumbers = new int[numbers.length - 1];
for (int i = 0, j = 0; i < numbers.length; i++) {
    if (i != 2) {  // 인덱스 2 (30) 제외
        newNumbers[j++] = numbers[i];
    }
}
numbers = newNumbers;  // {10, 20, 40, 50}
```

#### ArrayList

```java
import java.util.ArrayList;

ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
numbers.add(50);

numbers.remove(2);        // 인덱스 2 삭제 → {10, 20, 40, 50}
numbers.remove(Integer.valueOf(20));  // 값 20 삭제 → {10, 40, 50}
```

### 4-5. 길이/크기 확인

#### 배열

```java
int[] numbers = {10, 20, 30};

int length = numbers.length;  // 3 (필드)
```

#### ArrayList

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);

int size = numbers.size();  // 3 (메서드)
```

---

## 5️⃣ 상세 비교

### 5-1. 크기 (Size)

#### 배열: 고정 크기

```java
int[] numbers = new int[5];  // 크기 5로 고정

// ❌ 크기 변경 불가
// numbers.length = 10;  // 컴파일 에러 (length는 final)

// 크기를 늘리려면 새 배열 생성 필요
int[] newNumbers = new int[10];
System.arraycopy(numbers, 0, newNumbers, 0, numbers.length);
numbers = newNumbers;
```

#### ArrayList: 동적 크기

```java
ArrayList<Integer> numbers = new ArrayList<>();  // 초기 용량 10

// 자동으로 크기 증가
numbers.add(1);
numbers.add(2);
// ... 10개 추가 후에도 자동으로 용량 증가

System.out.println(numbers.size());  // 현재 요소 개수
```

### 5-2. 타입 제한

#### 배열: 기본 타입 + 참조 타입

```java
// 기본 타입 배열 가능
int[] numbers = {1, 2, 3};
double[] prices = {1.5, 2.5, 3.5};
boolean[] flags = {true, false};

// 참조 타입 배열도 가능
String[] names = {"홍길동", "김철수"};
Integer[] integers = {1, 2, 3};
```

#### ArrayList: 참조 타입만 (Wrapper 클래스 사용)

```java
// ❌ 기본 타입 불가
// ArrayList<int> numbers = new ArrayList<>();  // 컴파일 에러

// ✅ Wrapper 클래스 사용
ArrayList<Integer> numbers = new ArrayList<>();
ArrayList<Double> prices = new ArrayList<>();
ArrayList<Boolean> flags = new ArrayList<>();

// 참조 타입
ArrayList<String> names = new ArrayList<>();
```

### 5-3. 메모리 관리

#### 배열

```java
// 배열은 연속된 메모리에 저장
int[] arr = new int[1000];  // 1000 * 4바이트 = 4000바이트 연속 메모리 할당

// 크기가 고정되어 있어 메모리 낭비 가능
int[] arr = new int[100];  // 10개만 사용해도 100개 공간 할당
```

#### ArrayList

```java
// ArrayList도 내부적으로 배열 사용
ArrayList<Integer> list = new ArrayList<>();  // 초기 용량 10

// 요소가 추가되면 내부 배열 크기 자동 증가 (약 1.5배)
// 메모리 효율적 (필요할 때만 증가)
```

### 5-4. 성능 비교

| 작업 | 배열 | ArrayList |
|------|------|-----------|
| **인덱스 접근** | O(1) - 매우 빠름 | O(1) - 빠름 (메서드 호출 오버헤드) |
| **끝에 추가** | 불가능 | O(1) 평균, O(n) 최악 (크기 증가 시) |
| **중간 삽입** | 불가능 | O(n) |
| **중간 삭제** | 불가능 | O(n) |
| **검색** | O(n) | O(n) |

---

## 6️⃣ 실전 예제 비교

### 예제 1: 점수 관리

#### 배열로 구현

```java
public class ScoreArray {
    public static void main(String[] args) {
        // 배열: 크기가 고정
        int[] scores = new int[5];
        scores[0] = 85;
        scores[1] = 90;
        scores[2] = 78;
        scores[3] = 92;
        scores[4] = 88;
        
        // 점수 출력
        for (int i = 0; i < scores.length; i++) {
            System.out.println((i + 1) + "번 학생: " + scores[i] + "점");
        }
        
        // 평균 계산
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = (double) sum / scores.length;
        System.out.println("평균: " + average);
        
        // ❌ 새로운 학생 추가 불가 (배열 크기 고정)
        // scores[5] = 95;  // 오류!
    }
}
```

#### ArrayList로 구현

```java
import java.util.ArrayList;

public class ScoreArrayList {
    public static void main(String[] args) {
        // ArrayList: 동적 크기
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(85);
        scores.add(90);
        scores.add(78);
        scores.add(92);
        scores.add(88);
        
        // 점수 출력
        for (int i = 0; i < scores.size(); i++) {
            System.out.println((i + 1) + "번 학생: " + scores.get(i) + "점");
        }
        
        // 평균 계산
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = (double) sum / scores.size();
        System.out.println("평균: " + average);
        
        // ✅ 새로운 학생 추가 가능
        scores.add(95);  // 자동으로 크기 증가
        System.out.println("6번 학생 추가: " + scores.get(5) + "점");
        
        // ✅ 특정 학생 제거 가능
        scores.remove(2);  // 3번 학생 제거
    }
}
```

### 예제 2: 이름 목록 관리

#### 배열로 구현

```java
public class NameArray {
    public static void main(String[] args) {
        String[] names = {"홍길동", "김철수", "이영희"};
        
        // 이름 출력
        for (String name : names) {
            System.out.println(name);
        }
        
        // 특정 이름 검색
        String target = "김철수";
        boolean found = false;
        for (String name : names) {
            if (name.equals(target)) {
                found = true;
                break;
            }
        }
        System.out.println(target + " 찾음: " + found);
        
        // ❌ 이름 추가/삭제 불가 (새 배열 생성 필요)
    }
}
```

#### ArrayList로 구현

```java
import java.util.ArrayList;

public class NameArrayList {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("홍길동");
        names.add("김철수");
        names.add("이영희");
        
        // 이름 출력
        for (String name : names) {
            System.out.println(name);
        }
        
        // 특정 이름 검색
        String target = "김철수";
        boolean found = names.contains(target);
        System.out.println(target + " 찾음: " + found);
        
        // 인덱스 찾기
        int index = names.indexOf(target);
        System.out.println(target + "의 인덱스: " + index);
        
        // ✅ 이름 추가
        names.add("박민수");  // 끝에 추가
        names.add(1, "최지영");  // 인덱스 1에 추가
        
        // ✅ 이름 삭제
        names.remove("김철수");  // 값으로 삭제
        names.remove(0);  // 인덱스로 삭제
    }
}
```

### 예제 3: 동적 크기 필요 시

#### 배열의 한계

```java
public class ArrayLimitation {
    public static void main(String[] args) {
        // 사용자가 몇 개를 입력할지 모름
        // 배열은 크기를 미리 정해야 함
        int[] numbers = new int[10];  // 최대 10개로 가정
        
        // 실제로는 3개만 입력
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        
        // 메모리 낭비: 7개 공간이 사용되지 않음
        
        // 11번째 추가하려면?
        // ❌ 불가능 - 새 배열 생성 필요
    }
}
```

#### ArrayList의 장점

```java
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListAdvantage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        
        // 사용자가 몇 개를 입력할지 모름 → ArrayList가 적합
        System.out.println("숫자를 입력하세요 (종료: -1):");
        
        while (true) {
            int input = scanner.nextInt();
            if (input == -1) {
                break;
            }
            numbers.add(input);  // 자동으로 크기 증가
        }
        
        System.out.println("입력된 숫자 개수: " + numbers.size());
        System.out.println("숫자들: " + numbers);
        
        // 메모리 효율적: 필요한 만큼만 사용
    }
}
```

---

## 7️⃣ 배열과 ArrayList 변환

### 배열 → ArrayList

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayToArrayList {
    public static void main(String[] args) {
        // 방법 1: Arrays.asList() + ArrayList 생성자
        String[] array = {"사과", "바나나", "오렌지"};
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(array));
        
        // 방법 2: 반복문으로 추가
        ArrayList<String> list2 = new ArrayList<>();
        for (String item : array) {
            list2.add(item);
        }
        
        // 방법 3: Arrays.asList() (크기 고정 리스트)
        List<String> list3 = Arrays.asList(array);
        // list3.add("포도");  // ❌ UnsupportedOperationException (크기 고정)
    }
}
```

### ArrayList → 배열

```java
import java.util.ArrayList;

public class ArrayListToArray {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("사과");
        list.add("바나나");
        list.add("오렌지");
        
        // 방법 1: toArray() 메서드
        String[] array1 = list.toArray(new String[list.size()]);
        String[] array2 = list.toArray(new String[0]);  // 크기 0도 가능
        
        // 방법 2: 반복문으로 복사
        String[] array3 = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array3[i] = list.get(i);
        }
    }
}
```

---

## 8️⃣ 언제 무엇을 사용할까?

### ✅ 배열을 사용해야 할 때

1. **크기가 고정되어 있고 변하지 않을 때**
   ```java
   // 요일: 7개로 고정
   String[] days = {"월", "화", "수", "목", "금", "토", "일"};
   ```

2. **성능이 매우 중요할 때**
   ```java
   // 게임의 픽셀 데이터 등
   int[] pixels = new int[1920 * 1080];
   ```

3. **기본 타입을 사용해야 할 때**
   ```java
   // 기본 타입 배열이 더 효율적
   int[] numbers = new int[1000];
   ```

4. **간단한 데이터 구조가 필요할 때**
   ```java
   // 단순한 데이터 저장
   int[] coordinates = {10, 20};
   ```

### ✅ ArrayList를 사용해야 할 때

1. **크기가 변할 수 있을 때**
   ```java
   // 사용자가 추가할 수 있는 목록
   ArrayList<String> todoList = new ArrayList<>();
   ```

2. **요소 추가/삭제가 빈번할 때**
   ```java
   // 동적으로 관리되는 목록
   ArrayList<Student> students = new ArrayList<>();
   students.add(new Student("홍길동"));
   students.remove(0);
   ```

3. **컬렉션 프레임워크의 기능이 필요할 때**
   ```java
   // contains(), indexOf() 등 편리한 메서드 필요
   ArrayList<String> names = new ArrayList<>();
   if (names.contains("홍길동")) { }
   ```

4. **동적 데이터 관리가 중요할 때**
   ```java
   // 파일에서 읽어오는 데이터 등
   ArrayList<String> lines = new ArrayList<>();
   // 파일을 읽어서 lines에 추가
   ```

---

## 9️⃣ 종합 비교 예제

```java
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayVsArrayList {
    public static void main(String[] args) {
        System.out.println("=== 배열 vs ArrayList 종합 비교 ===\n");
        
        // ========== 1. 생성 및 초기화 ==========
        System.out.println("--- 1. 생성 및 초기화 ---");
        
        // 배열
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("배열: " + Arrays.toString(arr));
        
        // ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        System.out.println("ArrayList: " + list);
        
        // ========== 2. 길이/크기 ==========
        System.out.println("\n--- 2. 길이/크기 ---");
        System.out.println("배열 길이: " + arr.length);
        System.out.println("ArrayList 크기: " + list.size());
        
        // ========== 3. 요소 접근 ==========
        System.out.println("\n--- 3. 요소 접근 ---");
        System.out.println("배열[0]: " + arr[0]);
        System.out.println("ArrayList.get(0): " + list.get(0));
        
        // ========== 4. 요소 변경 ==========
        System.out.println("\n--- 4. 요소 변경 ---");
        arr[0] = 100;
        list.set(0, 100);
        System.out.println("배열[0] = 100: " + Arrays.toString(arr));
        System.out.println("ArrayList.set(0, 100): " + list);
        
        // ========== 5. 요소 추가 ==========
        System.out.println("\n--- 5. 요소 추가 ---");
        // 배열: 불가능
        System.out.println("배열: 요소 추가 불가 (크기 고정)");
        
        // ArrayList: 가능
        list.add(60);
        System.out.println("ArrayList.add(60): " + list);
        list.add(2, 25);
        System.out.println("ArrayList.add(2, 25): " + list);
        
        // ========== 6. 요소 삭제 ==========
        System.out.println("\n--- 6. 요소 삭제 ---");
        // 배열: 불가능
        System.out.println("배열: 요소 삭제 불가 (크기 고정)");
        
        // ArrayList: 가능
        list.remove(2);
        System.out.println("ArrayList.remove(2): " + list);
        list.remove(Integer.valueOf(60));
        System.out.println("ArrayList.remove(60): " + list);
        
        // ========== 7. 검색 ==========
        System.out.println("\n--- 7. 검색 ---");
        // 배열: 반복문으로 직접 검색
        boolean found = false;
        for (int num : arr) {
            if (num == 30) {
                found = true;
                break;
            }
        }
        System.out.println("배열에서 30 찾기: " + found);
        
        // ArrayList: contains() 메서드
        System.out.println("ArrayList에서 30 찾기: " + list.contains(30));
        System.out.println("ArrayList에서 30의 인덱스: " + list.indexOf(30));
    }
}
```

---

## 🔟 핵심 정리

### 배열의 특징

- ✅ **고정 크기**: 생성 시 크기 결정, 변경 불가
- ✅ **빠른 접근**: 인덱스로 직접 접근 (O(1))
- ✅ **기본 타입 가능**: int, double 등 직접 사용
- ❌ **크기 변경 불가**: 요소 추가/삭제 불가
- ❌ **메모리 낭비 가능**: 사용하지 않는 공간

### ArrayList의 특징

- ✅ **동적 크기**: 필요에 따라 자동으로 크기 증가
- ✅ **편리한 메서드**: add(), remove(), contains() 등
- ✅ **유연성**: 요소 추가/삭제 자유롭게
- ❌ **Wrapper 클래스**: 기본 타입 대신 Integer, Double 등 사용
- ❌ **약간 느림**: 메서드 호출 오버헤드

### 선택 가이드

| 상황 | 권장사항 |
|------|----------|
| 크기가 고정되어 있음 | 배열 |
| 크기가 변할 수 있음 | ArrayList |
| 성능이 매우 중요 | 배열 |
| 요소 추가/삭제가 빈번 | ArrayList |
| 기본 타입 사용 | 배열 |
| 컬렉션 기능 필요 | ArrayList |
| 간단한 데이터 구조 | 배열 |
| 동적 데이터 관리 | ArrayList |

---

## 1️⃣1️⃣ 메모리 구조 이해

### 배열의 메모리 구조

```java
int[] numbers = {10, 20, 30};
```

**메모리 구조:**

```
Stack 메모리                Heap 메모리
┌─────────────┐           ┌─────────────────────┐
│ numbers     │──────────→│ [0] [1] [2]         │
│ (참조변수)   │           │  10  20  30         │
└─────────────┘           │  ↑                  │
                          │  연속된 메모리 공간  │
                          └─────────────────────┘
```

**특징:**
- 배열 요소들이 **연속된 메모리 공간**에 저장됨
- 인덱스로 직접 접근 가능 (주소 계산: `baseAddress + index * size`)

### ArrayList의 메모리 구조

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);
```

**메모리 구조:**

```
Stack 메모리                Heap 메모리
┌─────────────┐           ┌─────────────────────┐
│ list        │──────────→│ ArrayList 객체      │
│ (참조변수)   │           │  ┌───────────────┐  │
└─────────────┘           │  │ elementData[] │──→│ [0] [1] [2] ... [9] │
                          │  │ (Object[])    │  │  10  20  30  null ...│
                          │  └───────────────┘  │  ↑                   │
                          │  size = 3          │  내부 배열 (연속)    │
                          │  capacity = 10     │                      │
                          └─────────────────────┘  └─────────────────────┘
```

**특징:**
- ArrayList 객체가 내부적으로 **배열**을 가지고 있음
- 기본 용량(default capacity)은 10
- 요소가 추가되면 내부 배열 크기가 자동으로 증가

---

## 1️⃣2️⃣ ArrayList 내부 동작 원리

### 초기 용량과 크기 증가

```java
ArrayList<Integer> list = new ArrayList<>();  // 초기 용량: 10
```

**초기 상태:**

```
ArrayList 내부
┌─────────────────────────────┐
│ elementData: Object[10]     │  (용량: 10)
│ [0] [1] [2] ... [9]         │
│ null null null ... null     │
│                             │
│ size = 0                    │  (현재 요소 개수: 0)
│ capacity = 10               │  (용량: 10)
└─────────────────────────────┘
```

**요소 추가 과정:**

```java
list.add(10);  // size: 0 → 1
list.add(20);  // size: 1 → 2
// ... 10개 추가 후
list.add(100);  // 용량 부족! → 내부 배열 크기 증가
```

**용량 증가:**

```
11번째 요소 추가 시:
1. 새 배열 생성 (크기: 약 1.5배 = 15)
2. 기존 요소 복사
3. 새 요소 추가

이전: [10][20]...[90][null][null]...
      size=10, capacity=10

이후: [10][20]...[90][100][null][null][null][null][null]
      size=11, capacity=15
```

**코드로 확인:**

```java
ArrayList<Integer> list = new ArrayList<>();

// 초기 용량 확인
System.out.println("초기 상태");

// 요소 추가하면서 용량 변화 관찰
for (int i = 0; i < 20; i++) {
    list.add(i);
    // 실제로는 capacity 필드가 private이므로 직접 확인 불가
    // 하지만 내부적으로 자동으로 용량이 증가함
}
```

---

## 1️⃣3️⃣ 성능 상세 분석

### 시간 복잡도 비교

| 작업 | 배열 | ArrayList | 설명 |
|------|------|-----------|------|
| **인덱스 접근** | O(1) | O(1) | 둘 다 매우 빠름 |
| **끝에 추가** | 불가능 | O(1) 평균 | ArrayList는 평균적으로 빠름 |
| **중간 삽입** | 불가능 | O(n) | 뒤의 요소들을 모두 이동 |
| **중간 삭제** | 불가능 | O(n) | 뒤의 요소들을 모두 이동 |
| **검색** | O(n) | O(n) | 선형 검색 |

### 실제 성능 테스트 예제

```java
import java.util.ArrayList;

public class PerformanceTest {
    public static void main(String[] args) {
        final int SIZE = 100000;
        
        // 배열 테스트
        long start = System.currentTimeMillis();
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = i;  // 직접 접근
        }
        long arrayTime = System.currentTimeMillis() - start;
        
        // ArrayList 테스트
        start = System.currentTimeMillis();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);  // 메서드 호출
        }
        long listTime = System.currentTimeMillis() - start;
        
        System.out.println("배열 시간: " + arrayTime + "ms");
        System.out.println("ArrayList 시간: " + listTime + "ms");
        // 배열이 약간 더 빠를 수 있음 (메서드 호출 오버헤드 없음)
    }
}
```

**결과 예상:**
- 배열: 약간 빠름 (직접 접근, 오버헤드 없음)
- ArrayList: 약간 느림 (메서드 호출, null 체크 등 오버헤드)

**차이는 크지 않지만, 매우 많은 반복에서는 의미가 있을 수 있음**

---

## 1️⃣4️⃣ 실제 사용 패턴 비교

### 패턴 1: 고정된 데이터 집합

#### 배열 사용 (적합)

```java
// 요일은 항상 7개
String[] daysOfWeek = {
    "월요일", "화요일", "수요일", "목요일", 
    "금요일", "토요일", "일요일"
};

// 성적 등급 (5개로 고정)
char[] grades = {'A', 'B', 'C', 'D', 'F'};
```

#### ArrayList 사용 (과도함)

```java
// ❌ 크기가 고정인데 ArrayList 사용 (불필요)
ArrayList<String> days = new ArrayList<>();
days.add("월요일");
// ... 항상 7개만 사용
```

### 패턴 2: 사용자 입력 데이터

#### 배열 사용 (부적합)

```java
// ❌ 사용자가 몇 개를 입력할지 모름
int[] scores = new int[100];  // 최대 100개로 가정
// 실제로는 5개만 입력했을 수도 있음 → 메모리 낭비

// 101번째 입력 시?
// → ArrayIndexOutOfBoundsException 발생!
```

#### ArrayList 사용 (적합)

```java
// ✅ 크기가 유동적
ArrayList<Integer> scores = new ArrayList<>();
Scanner scanner = new Scanner(System.in);

while (scanner.hasNextInt()) {
    scores.add(scanner.nextInt());  // 자동으로 크기 증가
}

// 메모리 효율적: 필요한 만큼만 사용
```

### 패턴 3: 동적 목록 관리

#### 배열 사용 (복잡함)

```java
// 학생 목록 관리
Student[] students = new Student[10];
int count = 0;

// 추가
if (count < students.length) {
    students[count++] = new Student("홍길동");
} else {
    // 새 배열 생성 필요
    Student[] newStudents = new Student[students.length * 2];
    System.arraycopy(students, 0, newStudents, 0, students.length);
    students = newStudents;
    students[count++] = new Student("홍길동");
}

// 삭제
// → 복잡한 로직 필요 (요소 이동, null 처리 등)
```

#### ArrayList 사용 (간단함)

```java
// ✅ 간단하고 명확
ArrayList<Student> students = new ArrayList<>();

// 추가
students.add(new Student("홍길동"));

// 삭제
students.remove(0);  // 또는
students.removeIf(s -> s.getName().equals("홍길동"));
```

---

## 1️⃣5️⃣ 자주 발생하는 오류와 해결

### 오류 1: ArrayIndexOutOfBoundsException

```java
// ❌ 잘못된 코드
int[] arr = new int[5];
arr[5] = 10;  // 인덱스 범위 초과 (0~4만 유효)

// ✅ 올바른 코드
int[] arr = new int[5];
if (index >= 0 && index < arr.length) {
    arr[index] = 10;
}
```

### 오류 2: ArrayList에서 기본 타입 사용 시도

```java
// ❌ 컴파일 에러
ArrayList<int> numbers = new ArrayList<>();

// ✅ Wrapper 클래스 사용
ArrayList<Integer> numbers = new ArrayList<>();
```

### 오류 3: 배열의 length vs ArrayList의 size

```java
int[] arr = {1, 2, 3};
ArrayList<Integer> list = new ArrayList<>();
list.add(1);
list.add(2);
list.add(3);

// ❌ 잘못된 사용
int arrSize = arr.size();      // 컴파일 에러
int listLength = list.length;  // 컴파일 에러

// ✅ 올바른 사용
int arrSize = arr.length;      // 필드
int listSize = list.size();    // 메서드
```

### 오류 4: remove() 메서드 오해

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);

// ❌ 인덱스가 아닌 값으로 삭제하려면 Wrapper 클래스 사용
list.remove(10);  // 인덱스 10을 삭제하려고 시도 → IndexOutOfBoundsException

// ✅ 올바른 사용
list.remove(0);                    // 인덱스로 삭제
list.remove(Integer.valueOf(10));  // 값으로 삭제
```

---

## 1️⃣6️⃣ Wrapper 클래스 상세 설명

### 기본 타입과 Wrapper 클래스

| 기본 타입 | Wrapper 클래스 | 배열 | ArrayList |
|-----------|---------------|------|-----------|
| `int` | `Integer` | ✅ 가능 | ✅ Integer 사용 |
| `double` | `Double` | ✅ 가능 | ✅ Double 사용 |
| `boolean` | `Boolean` | ✅ 가능 | ✅ Boolean 사용 |
| `char` | `Character` | ✅ 가능 | ✅ Character 사용 |
| `byte` | `Byte` | ✅ 가능 | ✅ Byte 사용 |
| `short` | `Short` | ✅ 가능 | ✅ Short 사용 |
| `long` | `Long` | ✅ 가능 | ✅ Long 사용 |
| `float` | `Float` | ✅ 가능 | ✅ Float 사용 |

### Wrapper 클래스 사용 예제

```java
// 배열: 기본 타입 직접 사용
int[] numbers = {1, 2, 3};
double[] prices = {1.5, 2.5, 3.5};
boolean[] flags = {true, false};

// ArrayList: Wrapper 클래스 사용
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(1);      // 자동 박싱 (Auto-boxing)
numbers.add(2);
numbers.add(3);

ArrayList<Double> prices = new ArrayList<>();
prices.add(1.5);     // 자동 박싱
prices.add(2.5);

ArrayList<Boolean> flags = new ArrayList<>();
flags.add(true);     // 자동 박싱
flags.add(false);
```

### Auto-boxing과 Auto-unboxing

```java
ArrayList<Integer> list = new ArrayList<>();

// Auto-boxing: 기본 타입 → Wrapper 클래스
list.add(10);        // int 10이 자동으로 Integer 10으로 변환
// 내부적으로: list.add(Integer.valueOf(10));

// Auto-unboxing: Wrapper 클래스 → 기본 타입
int value = list.get(0);  // Integer가 자동으로 int로 변환
// 내부적으로: int value = list.get(0).intValue();
```

---

## 1️⃣7️⃣ 다차원 배열 vs ArrayList의 중첩

### 다차원 배열

```java
// 2차원 배열
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// 접근
int value = matrix[0][1];  // 2

// 크기 확인
int rows = matrix.length;        // 3
int cols = matrix[0].length;     // 3
```

### ArrayList 중첩

```java
// ArrayList의 ArrayList
ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

// 행 추가
ArrayList<Integer> row1 = new ArrayList<>();
row1.add(1);
row1.add(2);
row1.add(3);
matrix.add(row1);

ArrayList<Integer> row2 = new ArrayList<>();
row2.add(4);
row2.add(5);
row2.add(6);
matrix.add(row2);

// 접근
int value = matrix.get(0).get(1);  // 2

// 크기 확인
int rows = matrix.size();                    // 2
int cols = matrix.get(0).size();             // 3
```

### 비교표

| 특징 | 다차원 배열 | ArrayList 중첩 |
|------|------------|---------------|
| **크기** | 고정 (행×열) | 동적 (각 행 크기 다를 수 있음) |
| **접근** | `arr[i][j]` | `list.get(i).get(j)` |
| **행 크기** | 모두 동일 | 다를 수 있음 |
| **유연성** | 낮음 | 높음 |

---

## 1️⃣8️⃣ 컬렉션 프레임워크 맥락에서의 ArrayList

### 컬렉션 프레임워크 계층 구조

```
Collection 인터페이스
    └── List 인터페이스
            ├── ArrayList (구현 클래스)
            ├── LinkedList (구현 클래스)
            └── Vector (구현 클래스, 구식)
```

### List 인터페이스의 주요 메서드

ArrayList는 List 인터페이스를 구현하므로 다음 메서드들을 사용할 수 있습니다:

```java
List<String> list = new ArrayList<>();  // 다형성 활용

// List 인터페이스 메서드
list.add("사과");
list.get(0);
list.set(0, "딸기");
list.remove(0);
list.size();
list.isEmpty();
list.contains("사과");
list.indexOf("사과");
list.clear();
```

### 다형성 활용

```java
// List 인터페이스 타입으로 선언
List<String> list1 = new ArrayList<>();
List<String> list2 = new LinkedList<>();  // 나중에 쉽게 변경 가능

// 같은 인터페이스를 구현하므로 사용법 동일
list1.add("사과");
list2.add("사과");
```

---

## 1️⃣9️⃣ 실전 활용 예제

### 예제 1: 학생 성적 관리 시스템

#### 배열로 구현 (한계)

```java
public class StudentScoreArray {
    private int[] scores;
    private int count;
    
    public StudentScoreArray(int maxSize) {
        scores = new int[maxSize];
        count = 0;
    }
    
    public void addScore(int score) {
        if (count < scores.length) {
            scores[count++] = score;
        } else {
            System.out.println("더 이상 추가할 수 없습니다!");
        }
    }
    
    // 삭제 기능 복잡...
    // 평균 계산 등...
}
```

#### ArrayList로 구현 (유연함)

```java
import java.util.ArrayList;

public class StudentScoreArrayList {
    private ArrayList<Integer> scores;
    
    public StudentScoreArrayList() {
        scores = new ArrayList<>();
    }
    
    public void addScore(int score) {
        scores.add(score);  // 자동으로 크기 증가
    }
    
    public void removeScore(int index) {
        scores.remove(index);  // 간단하게 삭제
    }
    
    public double getAverage() {
        if (scores.isEmpty()) return 0;
        
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }
    
    public int getMaxScore() {
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }
}
```

### 예제 2: 파일에서 데이터 읽기

```java
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
    public static void main(String[] args) {
        // 파일에서 몇 줄을 읽을지 모름 → ArrayList 적합
        ArrayList<String> lines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(
                new FileReader("data.txt"))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);  // 자동으로 크기 증가
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("읽은 줄 수: " + lines.size());
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
```

### 예제 3: 동적 메뉴 시스템

```java
import java.util.ArrayList;
import java.util.Scanner;

public class MenuSystem {
    private ArrayList<String> menuItems;
    private Scanner scanner;
    
    public MenuSystem() {
        menuItems = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public void addMenuItem(String item) {
        menuItems.add(item);
    }
    
    public void removeMenuItem(int index) {
        if (index >= 0 && index < menuItems.size()) {
            menuItems.remove(index);
        }
    }
    
    public void displayMenu() {
        System.out.println("=== 메뉴 ===");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i));
        }
    }
    
    public int selectMenu() {
        displayMenu();
        System.out.print("선택: ");
        return scanner.nextInt();
    }
}
```

---

## 2️⃣0️⃣ 최종 선택 가이드

### 결정 트리

```
데이터를 저장해야 함
    │
    ├─ 크기가 고정되어 있나?
    │   ├─ 예 → 배열 사용
    │   └─ 아니오
    │       │
    │       ├─ 요소 추가/삭제가 빈번한가?
    │       │   ├─ 예 → ArrayList 사용
    │       │   └─ 아니오
    │       │       │
    │       │       ├─ 성능이 매우 중요한가?
    │       │       │   ├─ 예 → 배열 고려
    │       │       │   └─ 아니오 → ArrayList 사용
    │       │
    │       └─ 기본 타입을 사용해야 하나?
    │           ├─ 예 → 배열 사용
    │           └─ 아니오 → ArrayList 사용
```

### 빠른 참조표

| 상황 | 권장 | 이유 |
|------|------|------|
| 요일 7개 | 배열 | 크기 고정 |
| 사용자 입력 개수 미정 | ArrayList | 동적 크기 |
| 게임 픽셀 데이터 | 배열 | 성능 중요 |
| 학생 목록 관리 | ArrayList | 추가/삭제 빈번 |
| 좌표 (x, y) | 배열 | 간단한 고정 데이터 |
| 파일에서 읽은 라인들 | ArrayList | 개수 미정 |
| 성적 등급 (A~F) | 배열 | 크기 고정 |
| 쇼핑 카트 | ArrayList | 동적 추가/삭제 |

---

## 📚 관련 자료

- [5장: 배열과 문자열](./5장_배열과_문자열.md)
- [9장: 컬렉션 프레임워크](./9장_컬렉션_프레임워크.md)
- [예제 코드: ArrayExamples.java](./example5/ArrayExamples.java)
- [예제 코드: ListExample.java](./example9/ListExample.java)


