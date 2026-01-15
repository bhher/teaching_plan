# ArrayList 기본 예제 (생성, 읽기, 수정, 삭제)

## 📌 ArrayList 기본 사용법

ArrayList의 가장 기본적인 4가지 작업을 예제로 설명합니다.

---

## 1️⃣ ArrayList 생성

### 기본 생성

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        // ArrayList 생성
        ArrayList<String> list = new ArrayList<>();
        
        System.out.println("ArrayList 생성 완료!");
        System.out.println("크기: " + list.size());  // 0
    }
}
```

**출력:**
```
ArrayList 생성 완료!
크기: 0
```

### 초기 용량 지정 생성

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        // 초기 용량 10으로 생성
        ArrayList<String> list = new ArrayList<>(10);
        
        System.out.println("초기 용량 10으로 생성 완료!");
    }
}
```

### 다른 컬렉션으로부터 생성

```java
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListExample {
    public static void main(String[] args) {
        // 배열로부터 생성
        String[] array = {"사과", "바나나", "오렌지"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        
        System.out.println("배열로부터 생성: " + list);
        // 출력: [사과, 바나나, 오렌지]
    }
}
```

---

## 2️⃣ ArrayList 읽기 (조회)

### 요소 읽기

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        // 인덱스로 요소 읽기
        String fruit1 = fruits.get(0);  // "사과"
        String fruit2 = fruits.get(1);  // "바나나"
        String fruit3 = fruits.get(2);  // "오렌지"
        
        System.out.println("첫 번째 과일: " + fruit1);
        System.out.println("두 번째 과일: " + fruit2);
        System.out.println("세 번째 과일: " + fruit3);
    }
}
```

**출력:**
```
첫 번째 과일: 사과
두 번째 과일: 바나나
세 번째 과일: 오렌지
```

### 전체 목록 읽기 (반복문)

#### 방법 1: 일반 for문

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        // 일반 for문으로 순회
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println((i + 1) + "번째: " + fruits.get(i));
        }
    }
}
```

**출력:**
```
1번째: 사과
2번째: 바나나
3번째: 오렌지
```

#### 방법 2: 향상된 for문 (권장)

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        // 향상된 for문으로 순회
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
```

**출력:**
```
사과
바나나
오렌지
```

### 크기 확인

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        System.out.println("과일 개수: " + fruits.size());  // 3
    }
}
```

---

## 3️⃣ ArrayList 수정

### 요소 수정 (set)

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        System.out.println("수정 전: " + fruits);
        // 출력: [사과, 바나나, 오렌지]
        
        // 인덱스 1의 요소를 "포도"로 수정
        fruits.set(1, "포도");
        
        System.out.println("수정 후: " + fruits);
        // 출력: [사과, 포도, 오렌지]
    }
}
```

### 여러 요소 수정

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        System.out.println("수정 전: " + fruits);
        
        // 여러 요소 수정
        fruits.set(0, "딸기");
        fruits.set(1, "포도");
        fruits.set(2, "수박");
        
        System.out.println("수정 후: " + fruits);
        // 출력: [딸기, 포도, 수박]
    }
}
```

---

## 4️⃣ ArrayList 삭제

### 인덱스로 삭제

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        System.out.println("삭제 전: " + fruits);
        // 출력: [사과, 바나나, 오렌지]
        
        // 인덱스 1 삭제 (바나나)
        fruits.remove(1);
        
        System.out.println("삭제 후: " + fruits);
        // 출력: [사과, 오렌지]
        System.out.println("크기: " + fruits.size());  // 2
    }
}
```

### 값으로 삭제

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        System.out.println("삭제 전: " + fruits);
        // 출력: [사과, 바나나, 오렌지]
        
        // 값으로 삭제 (바나나)
        fruits.remove("바나나");
        
        System.out.println("삭제 후: " + fruits);
        // 출력: [사과, 오렌지]
    }
}
```

### 모든 요소 삭제

```java
import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("오렌지");
        
        System.out.println("삭제 전: " + fruits);
        System.out.println("크기: " + fruits.size());  // 3
        
        // 모든 요소 삭제
        fruits.clear();
        
        System.out.println("삭제 후: " + fruits);
        // 출력: []
        System.out.println("크기: " + fruits.size());  // 0
    }
}
```

---

## 5️⃣ 종합 예제

### 완전한 CRUD 예제

```java
import java.util.ArrayList;

public class ArrayListCRUD {
    public static void main(String[] args) {
        // ========== 1. 생성 (Create) ==========
        ArrayList<String> list = new ArrayList<>();
        System.out.println("1. ArrayList 생성");
        System.out.println("크기: " + list.size() + "\n");
        
        // ========== 2. 추가 (Create) ==========
        list.add("사과");
        list.add("바나나");
        list.add("오렌지");
        System.out.println("2. 요소 추가");
        System.out.println("현재 목록: " + list);
        System.out.println("크기: " + list.size() + "\n");
        
        // ========== 3. 읽기 (Read) ==========
        System.out.println("3. 요소 읽기");
        System.out.println("첫 번째: " + list.get(0));
        System.out.println("두 번째: " + list.get(1));
        System.out.println("세 번째: " + list.get(2));
        System.out.println("전체 목록:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + list.get(i));
        }
        System.out.println();
        
        // ========== 4. 수정 (Update) ==========
        System.out.println("4. 요소 수정");
        System.out.println("수정 전: " + list);
        list.set(1, "포도");  // 바나나 → 포도
        System.out.println("수정 후: " + list + "\n");
        
        // ========== 5. 삭제 (Delete) ==========
        System.out.println("5. 요소 삭제");
        System.out.println("삭제 전: " + list);
        list.remove(0);  // 사과 삭제
        System.out.println("삭제 후: " + list);
        System.out.println("크기: " + list.size() + "\n");
        
        // ========== 최종 상태 ==========
        System.out.println("최종 상태: " + list);
        System.out.println("최종 크기: " + list.size());
    }
}
```

**출력:**
```
1. ArrayList 생성
크기: 0

2. 요소 추가
현재 목록: [사과, 바나나, 오렌지]
크기: 3

3. 요소 읽기
첫 번째: 사과
두 번째: 바나나
세 번째: 오렌지
전체 목록:
  1. 사과
  2. 바나나
  3. 오렌지

4. 요소 수정
수정 전: [사과, 바나나, 오렌지]
수정 후: [사과, 포도, 오렌지]

5. 요소 삭제
삭제 전: [사과, 포도, 오렌지]
삭제 후: [포도, 오렌지]
크기: 2

최종 상태: [포도, 오렌지]
최종 크기: 2
```

---

## 6️⃣ 실전 예제: 학생 목록 관리

```java
import java.util.ArrayList;

public class StudentListExample {
    public static void main(String[] args) {
        // ========== 생성 ==========
        ArrayList<String> students = new ArrayList<>();
        
        // ========== 추가 ==========
        students.add("홍길동");
        students.add("김철수");
        students.add("이영희");
        System.out.println("등록된 학생: " + students);
        
        // ========== 읽기 ==========
        System.out.println("\n=== 학생 목록 ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + "번: " + students.get(i));
        }
        
        // ========== 수정 ==========
        System.out.println("\n=== 학생 정보 수정 ===");
        System.out.println("수정 전: " + students);
        students.set(1, "김민수");  // 김철수 → 김민수
        System.out.println("수정 후: " + students);
        
        // ========== 삭제 ==========
        System.out.println("\n=== 학생 삭제 ===");
        System.out.println("삭제 전: " + students);
        students.remove("이영희");  // 이영희 삭제
        System.out.println("삭제 후: " + students);
        
        // ========== 최종 상태 ==========
        System.out.println("\n최종 학생 목록: " + students);
        System.out.println("총 학생 수: " + students.size());
    }
}
```

**출력:**
```
등록된 학생: [홍길동, 김철수, 이영희]

=== 학생 목록 ===
1번: 홍길동
2번: 김철수
3번: 이영희

=== 학생 정보 수정 ===
수정 전: [홍길동, 김철수, 이영희]
수정 후: [홍길동, 김민수, 이영희]

=== 학생 삭제 ===
삭제 전: [홍길동, 김민수, 이영희]
삭제 후: [홍길동, 김민수]

최종 학생 목록: [홍길동, 김민수]
총 학생 수: 2
```

---

## 7️⃣ 정수 ArrayList 예제

```java
import java.util.ArrayList;

public class IntegerListExample {
    public static void main(String[] args) {
        // ========== 생성 ==========
        ArrayList<Integer> numbers = new ArrayList<>();
        
        // ========== 추가 ==========
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        System.out.println("추가 후: " + numbers);
        
        // ========== 읽기 ==========
        System.out.println("첫 번째 숫자: " + numbers.get(0));
        System.out.println("두 번째 숫자: " + numbers.get(1));
        
        // 합계 계산
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("합계: " + sum);
        
        // ========== 수정 ==========
        numbers.set(0, 100);  // 10 → 100
        System.out.println("수정 후: " + numbers);
        
        // ========== 삭제 ==========
        numbers.remove(1);  // 인덱스 1 삭제 (20)
        System.out.println("삭제 후: " + numbers);
    }
}
```

**출력:**
```
추가 후: [10, 20, 30]
첫 번째 숫자: 10
두 번째 숫자: 20
합계: 60
수정 후: [100, 20, 30]
삭제 후: [100, 30]
```

---

## 8️⃣ 주요 메서드 정리

### 생성 관련

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `new ArrayList<>()` | 기본 생성 | `ArrayList<String> list = new ArrayList<>();` |
| `new ArrayList<>(10)` | 초기 용량 지정 | `ArrayList<String> list = new ArrayList<>(10);` |

### 추가 관련

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `add(element)` | 끝에 추가 | `list.add("사과");` |
| `add(index, element)` | 특정 위치에 추가 | `list.add(0, "사과");` |

### 읽기 관련

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `get(index)` | 인덱스로 읽기 | `String item = list.get(0);` |
| `size()` | 크기 확인 | `int size = list.size();` |
| `contains(element)` | 포함 여부 확인 | `boolean exists = list.contains("사과");` |
| `indexOf(element)` | 인덱스 찾기 | `int index = list.indexOf("사과");` |

### 수정 관련

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `set(index, element)` | 요소 수정 | `list.set(0, "딸기");` |

### 삭제 관련

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `remove(index)` | 인덱스로 삭제 | `list.remove(0);` |
| `remove(element)` | 값으로 삭제 | `list.remove("사과");` |
| `clear()` | 모든 요소 삭제 | `list.clear();` |

---

## 9️⃣ 주의사항

### 1. 인덱스 범위 체크

```java
ArrayList<String> list = new ArrayList<>();
list.add("사과");

// ❌ 오류! 인덱스 범위 초과
// String item = list.get(10);  // IndexOutOfBoundsException

// ✅ 올바른 방법
if (list.size() > 0) {
    String item = list.get(0);
}
```

### 2. Integer 타입 주의

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);

// ❌ 오류! 인덱스로 인식됨
// numbers.remove(10);  // IndexOutOfBoundsException

// ✅ 올바른 방법: 값으로 삭제
numbers.remove(Integer.valueOf(10));  // 값 10 삭제
numbers.remove(0);  // 인덱스 0 삭제
```

### 3. null 체크

```java
ArrayList<String> list = new ArrayList<>();
list.add(null);
list.add("사과");

// null 요소도 포함될 수 있음
for (String item : list) {
    if (item != null) {  // null 체크 필요
        System.out.println(item);
    }
}
```

---

## 🔟 빠른 참조표

### 기본 패턴

```java
// 1. 생성
ArrayList<타입> list = new ArrayList<>();

// 2. 추가
list.add(요소);

// 3. 읽기
타입 item = list.get(인덱스);

// 4. 수정
list.set(인덱스, 새요소);

// 5. 삭제
list.remove(인덱스);  // 또는 list.remove(요소);

// 6. 크기 확인
int size = list.size();

// 7. 순회
for (타입 item : list) {
    // 처리
}
```

---

## 📚 관련 자료

- [배열 vs ArrayList 완전 비교](./배열-vs-ArrayList-비교.md)
- [학생 관리 시스템 (ArrayList 버전)](./학생관리시스템-ArrayList-실습문제.md)

