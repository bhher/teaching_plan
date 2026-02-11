# 정렬 알고리즘 예제 코드

## 파일 목록

1. **BubbleSort.java** - 버블 정렬 구현
2. **InsertionSort.java** - 삽입 정렬 구현
3. **SelectionSort.java** - 선택 정렬 구현
4. **SortingAlgorithmsDemo.java** - 세 가지 알고리즘 통합 데모

## 컴파일 및 실행

### 개별 파일 실행

```bash
# 버블 정렬
javac BubbleSort.java
java BubbleSort

# 삽입 정렬
javac InsertionSort.java
java InsertionSort

# 선택 정렬
javac SelectionSort.java
java SelectionSort
```

### 통합 데모 실행

```bash
javac SortingAlgorithmsDemo.java
java SortingAlgorithmsDemo
```

## 각 파일 설명

### BubbleSort.java

**기능:**
- 버블 정렬 오름차순/내림차순 구현
- 정렬 과정 출력 기능 포함

**주요 메서드:**
- `bubbleSort(int[] arr)`: 오름차순 정렬
- `bubbleSortDescending(int[] arr)`: 내림차순 정렬
- `bubbleSortWithSteps(int[] arr)`: 정렬 과정 출력

### InsertionSort.java

**기능:**
- 삽입 정렬 오름차순/내림차순 구현
- 정렬 과정 출력 기능 포함

**주요 메서드:**
- `insertionSort(int[] arr)`: 오름차순 정렬
- `insertionSortDescending(int[] arr)`: 내림차순 정렬
- `insertionSortWithSteps(int[] arr)`: 정렬 과정 출력

### SelectionSort.java

**기능:**
- 선택 정렬 오름차순/내림차순 구현
- 정렬 과정 출력 기능 포함

**주요 메서드:**
- `selectionSort(int[] arr)`: 오름차순 정렬
- `selectionSortDescending(int[] arr)`: 내림차순 정렬
- `selectionSortWithSteps(int[] arr)`: 정렬 과정 출력

### SortingAlgorithmsDemo.java

**기능:**
- 세 가지 정렬 알고리즘을 한 번에 비교
- 성능 측정 기능 포함
- 사용자 입력 받아 정렬 수행

**사용법:**
1. 배열 크기 입력
2. 배열 요소 입력
3. 세 가지 알고리즘의 결과와 실행 시간 확인

## 실행 예시

```
=== 정렬 알고리즘 비교 프로그램 ===

배열 크기 입력: 5
배열 요소 입력:
5
2
8
1
9

원본 배열: [5, 2, 8, 1, 9]

=== 정렬 결과 ===

버블 정렬:   [1, 2, 5, 8, 9] (시간: 1234 ns)
삽입 정렬:   [1, 2, 5, 8, 9] (시간: 987 ns)
선택 정렬:   [1, 2, 5, 8, 9] (시간: 1456 ns)

모든 알고리즘이 동일한 결과를 생성: true
```

## 학습 포인트

1. 각 알고리즘의 동작 원리 이해
2. 정렬 과정을 단계별로 확인
3. 성능 비교 및 분석
4. 코드 구현 연습

## 참고 자료

상세한 설명은 `정렬알고리즘-버블-삽입-선택-완전정복.md` 파일을 참고하세요.
