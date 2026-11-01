# 선택 정렬 · 삽입 정렬 · 버블 정렬

**오름차순** 기준으로 설명합니다. (작은 수 → 큰 수)  
예제 배열: `[5, 2, 8, 1, 9]`

| 정렬 | 한 줄 요약 | 비유 |
|------|------------|------|
| **선택 정렬** | 남은 구간에서 **최솟값**을 골라 앞자리와 교환 | 줄에서 가장 작은 사람부터 앞으로 |
| **삽입 정렬** | 현재 값을 **앞쪽 정렬된 구간**에 끼워 넣기 | 카드 손에 집어넣기 |
| **버블 정렬** | 옆자리와 비교해 **큰 수를 뒤로** 밀어내기 | 거품이 위로 올라감 |

---

## 목차

1. [공통 개념](#1-공통-개념)
2. [선택 정렬 (Selection Sort)](#2-선택-정렬-selection-sort)
3. [삽입 정렬 (Insertion Sort)](#3-삽입-정렬-insertion-sort)
4. [버블 정렬 (Bubble Sort)](#4-버블-정렬-bubble-sort)
5. [세 정렬 비교](#5-세-정렬-비교)
6. [Java 구현](#6-java-구현)
7. [C 구현](#7-c-구현)

---

## 1. 공통 개념

### 정렬이란?

```
정렬 전:  [ 5 ] [ 2 ] [ 8 ] [ 1 ] [ 9 ]
인덱스:     0     1     2     3     4

정렬 후:  [ 1 ] [ 2 ] [ 5 ] [ 8 ] [ 9 ]
```

### 교환 (swap)

세 정렬 모두 **두 칸의 값을 바꾸는** 동작이 자주 나옵니다.

```
교환 전:  a = 5 , b = 2
          temp = a   →  a = b   →  b = temp
교환 후:  a = 2 , b = 5
```

```
┌─────┐     ┌─────┐
│  5  │ ←→  │  2  │
└─────┘     └─────┘
```

---

## 2. 선택 정렬 (Selection Sort)

### 아이디어

> **i번째 자리에 올 값** = 아직 안 본 구간(`i` ~ 끝)에서 **가장 작은 값**을 찾아와 교환

```
정렬된 구간 | 미정렬 구간
───────────┼────────────
  (앞쪽)   |  (뒤쪽, 여기서 최솟값 탐색)
```

### 도식화 — `[5, 2, 8, 1, 9]`

#### 1패스 (i = 0) — 0번 자리에 최솟값

```
인덱스:   0    1    2    3    4
값:     [5]  [2]  [8]  [1]  [9]
         ↑              ↑
        자리          최솟값(1) 발견

교환: arr[0] ↔ arr[3]

결과:   [1]  [2]  [8]  [5]  [9]
        ███
      확정
```

#### 2패스 (i = 1)

```
        [1]  [2]  [8]  [5]  [9]
              ↑
            이미 최솟값(2) → 교환 없음 (또는 자기 자신)

결과:   [1]  [2]  [8]  [5]  [9]
        ███████
```

#### 3패스 (i = 2)

```
        [1]  [2]  [8]  [5]  [9]
                    ↑    ↑
                   자리  최솟값(5)

교환: arr[2] ↔ arr[3]

결과:   [1]  [2]  [5]  [8]  [9]
        ████████████
```

#### 4패스 (i = 3)

```
        [1]  [2]  [5]  [8]  [9]
                         ↑
                       이미 정렬됨

최종:   [1]  [2]  [5]  [8]  [9]
```

### 흐름도

```
for i = 0 ~ n-2:
    minIdx = i
    for j = i+1 ~ n-1:
        if arr[j] < arr[minIdx]:
            minIdx = j
    swap(arr[i], arr[minIdx])
```

### 특징

| 항목 | 내용 |
|------|------|
| 시간 복잡도 | O(n²) — 항상 이중 루프 |
| 교환 횟수 | 적음 (패스당 최대 1번) |
| 안정 정렬 | ❌ (같은 값의 상대 순서가 바뀔 수 있음) |

---

## 3. 삽입 정렬 (Insertion Sort)

### 아이디어

> 카드를 손에 쥐듯, **현재 값(key)** 을 앞쪽 **이미 정렬된 구간**에 맞는 위치에 삽입

```
정렬된 구간 ←── key를 끼워 넣음 ── 미정렬
[ 정렬됨... ]     key      [ 아직... ]
```

### 도식화 — `[5, 2, 8, 1, 9]`

#### 시작

```
[5]  [2]  [8]  [1]  [9]
 ███
 정렬된 구간(길이 1)
```

#### i = 1, key = 2

```
앞쪽: [5]  ← 2보다 큼 → 한 칸 뒤로 밀기

[5]  [5]  [8]  [1]  [9]   (밀린 상태)
 ↓
[2]  [5]  [8]  [1]  [9]   ← 2를 빈자리에 삽입
████████
```

#### i = 2, key = 8

```
앞쪽 [2,5] 는 이미 8보다 작음 → 그대로

[2]  [5]  [8]  [1]  [9]
████████████
```

#### i = 3, key = 1

```
8, 5, 2 를 차례로 뒤로 밀고 → 맨 앞에 1 삽입

[1]  [2]  [5]  [8]  [9]
████████████████
```

#### i = 4, key = 9

```
앞에 모두 작음 → 그대로

[1]  [2]  [5]  [8]  [9]
```

### 한 단계 자세히 (key 삽입)

```
정렬된 구간:  [2] [5] [8]   |  key=1  |  [9]
                              ↓
밀기:         [2] [5] [8] [8]
              [2] [5] [5] [8]
              [2] [2] [5] [8]
삽입:         [1] [2] [5] [8] | [9]
```

### 흐름도

```
for i = 1 ~ n-1:
    key = arr[i]
    j = i - 1
    while j >= 0 and arr[j] > key:
        arr[j+1] = arr[j]   # 뒤로 밀기
        j--
    arr[j+1] = key          # 빈자리에 삽입
```

### 특징

| 항목 | 내용 |
|------|------|
| 시간 복잡도 | 최악 O(n²), **거의 정렬된 데이터는 O(n)** |
| 안정 정렬 | ✅ |
| 실무 감각 | 작은 배열·부분 정렬에 유리 |

---

## 4. 버블 정렬 (Bubble Sort)

### 아이디어

> 인접한 두 칸을 비교해 **왼쪽이 더 크면 교환** → 큰 값이 **거품처럼 맨 뒤로** 이동

```
한 패스가 끝나면 → 가장 큰 값이 맨 뒤에 확정
```

### 도식화 — `[5, 2, 8, 1, 9]` (1패스)

```
비교 교환 과정 (→ 방향으로 한 바퀴)

[5] [2] [8] [1] [9]
 5>2 → 교환
[2] [5] [8] [1] [9]
    5<8 → 유지
[2] [5] [8] [1] [9]
       8>1 → 교환
[2] [5] [1] [8] [9]
          8<9 → 유지
[2] [5] [1] [8] [9]
                      ↑
                   9는 이미 끝
                   (이 패스에서 8이 뒤로 감)
```

실제 1패스 후 (큰 값이 뒤로):

```
시작:  5  2  8  1  9
       ↓
패스1: 2  5  1  8  9   ← 맨 뒤 쪽 큰 값들이 자리 잡기 시작
패스2: 2  1  5  8  9
패스3: 1  2  5  8  9
패스4: 1  2  5  8  9   완료
```

### 인접 비교 한 장면

```
   j        j+1
┌─────┐  ┌─────┐
│  8  │  │  1  │   8 > 1 이므로 교환
└─────┘  └─────┘
     ↘  ↙
┌─────┐  ┌─────┐
│  1  │  │  8  │
└─────┘  └─────┘
```

### 흐름도

```
for i = 0 ~ n-2:           # 패스 횟수
    for j = 0 ~ n-2-i:     # 끝쪽은 이미 확정이라 안 봄
        if arr[j] > arr[j+1]:
            swap(arr[j], arr[j+1])
```

### 최적화 (선택)

한 패스에서 **교환이 한 번도 없으면** 이미 정렬됨 → 조기 종료 가능.

### 특징

| 항목 | 내용 |
|------|------|
| 시간 복잡도 | O(n²) |
| 안정 정렬 | ✅ |
| 이해도 | 가장 직관적 (초보 학습용) |

---

## 5. 세 정렬 비교

```
선택:  미정렬에서 ★최솟값★ 골라 → 앞과 교환
       [■■■|······]
            ↑ min 탐색

삽입:  key를 뽑아 → 앞 정렬 구간에 ★끼워넣기★
       [■■■] ← key | ···

버블:  옆끼리 비교 → ★큰 수를 뒤로★
       [·]↔[·]↔[·]↔[·]  ⇒  큰 값 거품↑
```

| | 선택 | 삽입 | 버블 |
|--|------|------|------|
| 핵심 동작 | 최솟값 선택 후 교환 | 밀고 삽입 | 인접 교환 |
| 최악 시간 | O(n²) | O(n²) | O(n²) |
| 최선 시간 | O(n²) | **O(n)** | O(n)~O(n²)* |
| 안정 정렬 | ❌ | ✅ | ✅ |
| 교환 횟수 | 적음 | 중간 | 많을 수 있음 |

\*버블은 교환 없음 체크로 조기 종료 시 최선 O(n)

### 언제 무엇을?

- **개념 이해**: 버블  
- **거의 정렬된 데이터**: 삽입  
- **교환을 줄이고 싶을 때**: 선택  

> 실무 대용량은 `Arrays.sort` / `qsort` 등 라이브러리 정렬을 씁니다.  
> 위 세 가지는 **알고리즘 기초·시험**용으로 중요합니다.

---

## 6. Java 구현

### 6-1. 선택 정렬

```java
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // 교환
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9};
        selectionSort(arr);
        for (int x : arr) {
            System.out.print(x + " ");
        }
        // 출력: 1 2 5 8 9
    }
}
```

### 6-2. 삽입 정렬

```java
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9};
        insertionSort(arr);
        for (int x : arr) {
            System.out.print(x + " ");
        }
        // 출력: 1 2 5 8 9
    }
}
```

### 6-3. 버블 정렬

```java
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /** 교환 없으면 조기 종료 */
    public static void bubbleSortOptimized(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9};
        bubbleSort(arr);
        for (int x : arr) {
            System.out.print(x + " ");
        }
        // 출력: 1 2 5 8 9
    }
}
```

### 6-4. 한 파일에서 비교 (Java)

```java
public class SortDemo {
    static void print(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    static int[] copy(int[] src) {
        int[] d = new int[src.length];
        System.arraycopy(src, 0, d, 0, src.length);
        return d;
    }

    public static void main(String[] args) {
        int[] origin = {5, 2, 8, 1, 9};

        int[] a = copy(origin);
        SelectionSort.selectionSort(a);
        System.out.print("선택: "); print(a);

        int[] b = copy(origin);
        InsertionSort.insertionSort(b);
        System.out.print("삽입: "); print(b);

        int[] c = copy(origin);
        BubbleSort.bubbleSort(c);
        System.out.print("버블: "); print(c);
    }
}
```

---

## 7. C 구현

### 7-1. 선택 정렬

```c
#include <stdio.h>

void selectionSort(int arr[], int n) {
    int i, j, minIdx, temp;
    for (i = 0; i < n - 1; i++) {
        minIdx = i;
        for (j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        temp = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = temp;
    }
}

int main(void) {
    int arr[] = {5, 2, 8, 1, 9};
    int n = 5, i;

    selectionSort(arr, n);

    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    /* 출력: 1 2 5 8 9 */
    return 0;
}
```

### 7-2. 삽입 정렬

```c
#include <stdio.h>

void insertionSort(int arr[], int n) {
    int i, j, key;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
}

int main(void) {
    int arr[] = {5, 2, 8, 1, 9};
    int n = 5, i;

    insertionSort(arr, n);

    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    return 0;
}
```

### 7-3. 버블 정렬

```c
#include <stdio.h>

void bubbleSort(int arr[], int n) {
    int i, j, temp;
    for (i = 0; i < n - 1; i++) {
        for (j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

void bubbleSortOptimized(int arr[], int n) {
    int i, j, temp, swapped;
    for (i = 0; i < n - 1; i++) {
        swapped = 0;
        for (j = 0; j < n - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                swapped = 1;
            }
        }
        if (!swapped) break;
    }
}

int main(void) {
    int arr[] = {5, 2, 8, 1, 9};
    int n = 5, i;

    bubbleSort(arr, n);

    for (i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    return 0;
}
```

### 7-4. C — 세 정렬 한 파일 예제

```c
#include <stdio.h>
#include <string.h>

void printArr(int arr[], int n) {
    int i;
    for (i = 0; i < n; i++) printf("%d ", arr[i]);
    printf("\n");
}

void copyArr(int src[], int dest[], int n) {
    memcpy(dest, src, n * sizeof(int));
}

/* selectionSort / insertionSort / bubbleSort 함수는 위에 정의 */

int main(void) {
    int origin[] = {5, 2, 8, 1, 9};
    int a[5], b[5], c[5];
    int n = 5;

    copyArr(origin, a, n);
    selectionSort(a, n);
    printf("선택: "); printArr(a, n);

    copyArr(origin, b, n);
    insertionSort(b, n);
    printf("삽입: "); printArr(b, n);

    copyArr(origin, c, n);
    bubbleSort(c, n);
    printf("버블: "); printArr(c, n);

    return 0;
}
```

---

## Java vs C 문법 대응

| | Java | C |
|--|------|---|
| 배열 길이 | `arr.length` | 매개변수 `n`으로 전달 |
| 출력 | `System.out.print` | `printf` |
| 교환 | `int temp = ...` | 동일 |
| 클래스 | `public class ...` | 함수만 사용 |

로직(이중 루프, 비교, 교환)은 **언어와 무관하게 동일**합니다.

---

## 연습 문제

1. `[4, 3, 2, 1]` 에 대해 **버블 정렬 1패스** 후 배열을 손으로 적으세요.  
2. 선택 정렬에서 **교환은 패스당 최대 몇 번**인가?  
3. 이미 정렬된 `[1,2,3,4,5]` 에서 **삽입 정렬**의 비교 횟수가 적은 이유를 설명하세요.  
4. 위 세 정렬을 Java 또는 C로 직접 컴파일·실행해 `1 2 5 8 9`가 나오는지 확인하세요.

---

## 관련 자료

- [java/정렬알고리즘-버블-삽입-선택-완전정복.md](./java/정렬알고리즘-버블-삽입-선택-완전정복.md)
- [java/정렬알고리즘-예제코드/](./java/정렬알고리즘-예제코드/)
- [c/C언어-작업형-문제.md](./c/C언어-작업형-문제.md) (문제 10: 버블 정렬)
