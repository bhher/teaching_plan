# HashMap entrySet() 사용법 설명

---

## 목차

1. [entrySet() 기본 개념](#1-entryset-기본-개념)
2. [코드 예시](#2-코드-예시)
3. [단계별 설명](#3-단계별-설명)
4. [다른 순회 방법과 비교](#4-다른-순회-방법과-비교)
5. [실전 예제](#5-실전-예제)

---

## 1. entrySet() 기본 개념

### 1-1. entrySet()이란?

**`entrySet()`**은 HashMap에 저장된 **모든 키-값 쌍을 Set 형태로 반환**하는 메서드입니다.

- 각 키-값 쌍은 `Map.Entry` 객체로 표현됩니다
- `Map.Entry`는 하나의 키와 값의 묶음입니다
- `entrySet()`을 사용하면 키와 값을 동시에 순회할 수 있습니다

### 1-2. entrySet()의 반환 타입

```java
Set<Map.Entry<String, Integer>> entrySet = scores.entrySet();
```

- **반환 타입**: `Set<Map.Entry<K, V>>`
- **K**: 키의 타입 (예: `String`)
- **V**: 값의 타입 (예: `Integer`)

---

## 2. 코드 예시

### 2-1. 기본 코드

```java
import java.util.HashMap;
import java.util.Map;

public class EntrySetExample {
    public static void main(String[] args) {
        // HashMap 생성
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("홍길동", 85);
        scores.put("김철수", 90);
        scores.put("이영희", 88);
        
        // 키-값 쌍 순회
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```

### 2-2. 실행 결과

```
김철수: 90
이영희: 88
홍길동: 85
```

⚠️ **주의**: HashMap은 **순서를 보장하지 않습니다**. 출력 순서가 입력 순서와 다를 수 있습니다.

---

## 3. 단계별 설명

### 3-1. scores.entrySet()

```java
scores.entrySet()
```

**의미:**
- HashMap 안의 **모든 (키, 값) 쌍을 Set 형태로 반환**합니다
- 각 요소는 `Map.Entry<String, Integer>` 타입입니다

**타입:**
```java
Set<Map.Entry<String, Integer>>
```

**내부 구조:**
```
entrySet
 ├─ Entry 1: ("홍길동", 85)
 ├─ Entry 2: ("김철수", 90)
 └─ Entry 3: ("이영희", 88)
```

---

### 3-2. Map.Entry<String, Integer> entry

```java
Map.Entry<String, Integer> entry
```

**의미:**
- 하나의 **키-값 묶음 객체**입니다
- 반복문의 각 반복마다 하나의 Entry 객체를 가져옵니다

**내부 구조 개념:**
```
entry
 ├─ key   → String  (예: "홍길동")
 └─ value → Integer (예: 85)
```

**Entry 객체의 주요 메서드:**
- `getKey()`: 키 반환
- `getValue()`: 값 반환
- `setValue(V value)`: 값 변경 (선택적)

---

### 3-3. entry.getKey()

```java
entry.getKey()
```

**의미:**
- 현재 순회 중인 **키를 반환**합니다
- 반환 타입: `String` (또는 지정한 키 타입)

**예시:**
```java
entry.getKey()  // "홍길동"
entry.getKey()  // "김철수"
entry.getKey()  // "이영희"
```

---

### 3-4. entry.getValue()

```java
entry.getValue()
```

**의미:**
- 해당 키에 **대응하는 값을 반환**합니다
- 반환 타입: `Integer` (또는 지정한 값 타입)

**예시:**
```java
entry.getValue()  // 85
entry.getValue()  // 90
entry.getValue()  // 88
```

---

## 4. 다른 순회 방법과 비교

### 4-1. entrySet() 사용 (권장)

```java
// 키와 값을 동시에 가져옴
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

**장점:**
- ✅ 키와 값을 **한 번에** 가져올 수 있습니다
- ✅ `get()` 메서드를 호출하지 않아도 됩니다 (효율적)
- ✅ 코드가 간결하고 읽기 쉽습니다

---

### 4-2. keySet() 사용

```java
// 키만 가져와서 값은 get()으로 조회
for (String key : scores.keySet()) {
    System.out.println(key + ": " + scores.get(key));
}
```

**단점:**
- ❌ 각 키마다 `get()` 메서드를 호출해야 합니다
- ❌ `entrySet()`보다 **비효율적**입니다 (O(n) vs O(n))

**언제 사용?**
- 키만 필요할 때
- 값이 필요 없을 때

---

### 4-3. values() 사용

```java
// 값만 가져옴
for (Integer value : scores.values()) {
    System.out.println(value);
}
```

**단점:**
- ❌ 키 정보를 알 수 없습니다
- ❌ 키와 값을 함께 사용할 수 없습니다

**언제 사용?**
- 값만 필요할 때
- 모든 값의 합계, 평균 등을 계산할 때

---

### 4-4. 성능 비교

| 방법 | 시간 복잡도 | 설명 |
|------|------------|------|
| `entrySet()` | O(n) | 키-값 쌍을 직접 순회 (가장 효율적) |
| `keySet()` + `get()` | O(n) | 각 키마다 `get()` 호출 (약간 비효율적) |
| `values()` | O(n) | 값만 순회 (키 정보 없음) |

**결론:** 키와 값을 모두 사용할 때는 **`entrySet()`을 사용하는 것이 가장 효율적**입니다.

---

## 5. 실전 예제

### 5-1. 예제 1: 학생 점수 출력

```java
import java.util.HashMap;
import java.util.Map;

public class StudentScores {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("홍길동", 85);
        scores.put("김철수", 90);
        scores.put("이영희", 88);
        scores.put("박영수", 92);
        
        System.out.println("=== 학생 점수 ===");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String name = entry.getKey();
            int score = entry.getValue();
            System.out.println(name + ": " + score + "점");
        }
    }
}
```

**실행 결과:**
```
=== 학생 점수 ===
김철수: 90점
이영희: 88점
홍길동: 85점
박영수: 92점
```

---

### 5-2. 예제 2: 평균 점수 계산

```java
import java.util.HashMap;
import java.util.Map;

public class AverageScore {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("홍길동", 85);
        scores.put("김철수", 90);
        scores.put("이영희", 88);
        
        int sum = 0;
        int count = 0;
        
        // entrySet()으로 모든 점수 합산
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            sum += entry.getValue();
            count++;
        }
        
        double average = (double) sum / count;
        System.out.println("평균 점수: " + average + "점");
    }
}
```

**실행 결과:**
```
평균 점수: 87.66666666666667점
```

---

### 5-3. 예제 3: 최고 점수 학생 찾기

```java
import java.util.HashMap;
import java.util.Map;

public class TopStudent {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("홍길동", 85);
        scores.put("김철수", 90);
        scores.put("이영희", 88);
        
        String topStudent = null;
        int maxScore = Integer.MIN_VALUE;
        
        // entrySet()으로 최고 점수 학생 찾기
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                topStudent = entry.getKey();
            }
        }
        
        System.out.println("최고 점수 학생: " + topStudent + " (" + maxScore + "점)");
    }
}
```

**실행 결과:**
```
최고 점수 학생: 김철수 (90점)
```

---

### 5-4. 예제 4: 조건부 출력

```java
import java.util.HashMap;
import java.util.Map;

public class FilterScores {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("홍길동", 85);
        scores.put("김철수", 90);
        scores.put("이영희", 88);
        scores.put("박영수", 75);
        
        System.out.println("=== 80점 이상 학생 ===");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() >= 80) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
            }
        }
    }
}
```

**실행 결과:**
```
=== 80점 이상 학생 ===
김철수: 90점
이영희: 88점
홍길동: 85점
```

---

## 전체 요약

### entrySet() 사용 패턴

```java
// 기본 패턴
for (Map.Entry<키타입, 값타입> entry : map.entrySet()) {
    키타입 key = entry.getKey();
    값타입 value = entry.getValue();
    // key와 value 사용
}
```

### 핵심 포인트

1. **`entrySet()`**: 모든 키-값 쌍을 Set으로 반환
2. **`Map.Entry`**: 하나의 키-값 묶음 객체
3. **`getKey()`**: 현재 Entry의 키 반환
4. **`getValue()`**: 현재 Entry의 값 반환
5. **효율성**: `keySet()` + `get()`보다 효율적

### 언제 사용하나?

- ✅ 키와 값을 **동시에** 사용할 때
- ✅ 모든 키-값 쌍을 **순회**할 때
- ✅ **성능**이 중요한 경우
- ✅ 코드를 **간결하게** 작성하고 싶을 때

### 비교표

| 방법 | 키 접근 | 값 접근 | 효율성 | 사용 시기 |
|------|---------|---------|--------|-----------|
| `entrySet()` | `entry.getKey()` | `entry.getValue()` | ⭐⭐⭐⭐⭐ | 키와 값 모두 필요 |
| `keySet()` + `get()` | 직접 | `map.get(key)` | ⭐⭐⭐ | 키만 순회, 값은 필요시 |
| `values()` | 불가능 | 직접 | ⭐⭐⭐⭐ | 값만 필요 |

---

## 추가 학습

- `LinkedHashMap`: 순서를 보장하는 HashMap
- `TreeMap`: 정렬된 순서로 저장하는 Map
- `forEach()` 메서드와 람다 표현식
- 스트림 API를 사용한 Map 순회
