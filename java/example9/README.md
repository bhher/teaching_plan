# 9장 예제 파일 실행 방법

## 명령줄에서 실행하기

### 1. ListExample.java
```bash
javac ListExample.java
java ListExample
```

### 2. SetExample.java
```bash
javac SetExample.java
java SetExample
```

### 3. MapExample.java
```bash
javac MapExample.java
java MapExample
```

### 4. IteratorExample.java
```bash
javac IteratorExample.java
java IteratorExample
```

### 5. CollectionComprehensive.java
```bash
javac CollectionComprehensive.java
java CollectionComprehensive
```

### 6. StudentManager.java
```bash
javac StudentManager.java
java StudentManager
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 주의사항

- 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- Java는 대소문자를 구분합니다
- 각 파일은 하나의 public 클래스를 포함해야 합니다
- 컬렉션은 제네릭을 사용하여 타입을 지정해야 합니다
- `import java.util.*;` 문을 사용하여 컬렉션 클래스를 import해야 합니다

## 학습 포인트

- **List**: 순서가 있고 중복을 허용하는 컬렉션
  - `ArrayList`: 인덱스 접근이 빠름
  - `LinkedList`: 중간 삽입/삭제가 빠름
- **Set**: 중복을 허용하지 않는 컬렉션
  - `HashSet`: 빠른 검색, 순서 보장 안 됨
  - `LinkedHashSet`: 삽입 순서 유지
  - `TreeSet`: 정렬된 순서
- **Map**: 키-값 쌍으로 저장하는 컬렉션
  - `HashMap`: 빠른 검색, 순서 보장 안 됨
  - `LinkedHashMap`: 삽입 순서 유지
  - `TreeMap`: 키가 정렬된 순서
- **Iterator**: 컬렉션을 순회하는 인터페이스

## 컬렉션 선택 가이드

| 상황 | 추천 컬렉션 |
|------|------------|
| 순서 중요, 중복 허용, 인덱스 접근 많음 | `ArrayList` |
| 중간 삽입/삭제 많음 | `LinkedList` |
| 중복 제거 필요 | `HashSet` |
| 정렬 필요 | `TreeSet` |
| 키-값 쌍 저장 | `HashMap` |
| 정렬된 키-값 쌍 | `TreeMap` |

## 주요 메서드

### List
- `add()`, `get()`, `set()`, `remove()`, `size()`, `contains()`, `indexOf()`

### Set
- `add()`, `remove()`, `contains()`, `size()`, `isEmpty()`, `clear()`

### Map
- `put()`, `get()`, `remove()`, `containsKey()`, `containsValue()`, `keySet()`, `values()`, `entrySet()`

### Iterator
- `hasNext()`, `next()`, `remove()`

