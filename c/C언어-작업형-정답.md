# C언어 작업형 평가 문제 정답 (10문제)

문제: [C언어-작업형-문제.md](./C언어-작업형-문제.md)

---

## 문제 1: 배열의 최댓값과 최솟값 찾기

### problem1.c

```c
#include <stdio.h>

int main() {
    int arr[5];
    int i, max, min;

    for (i = 0; i < 5; i++) {
        scanf("%d", &arr[i]);
    }

    max = arr[0];
    min = arr[0];

    for (i = 1; i < 5; i++) {
        if (arr[i] > max) max = arr[i];
        if (arr[i] < min) min = arr[i];
    }

    printf("최댓값: %d\n", max);
    printf("최솟값: %d\n", min);

    return 0;
}
```

**실행 예:** `10 5 20 8 15` → 최댓값 20, 최솟값 5

---

## 문제 2: 평균 이상인 값의 개수

### problem2.c

```c
#include <stdio.h>

int main() {
    int arr[10];
    int i, sum = 0, count = 0;
    float avg;

    for (i = 0; i < 10; i++) {
        scanf("%d", &arr[i]);
        sum += arr[i];
    }

    avg = (float)sum / 10;

    for (i = 0; i < 10; i++) {
        if (arr[i] >= avg) count++;
    }

    printf("평균: %.2f\n", avg);
    printf("평균 이상인 값의 개수: %d\n", count);

    return 0;
}
```

**실행 예:** 평균 80.00, 개수 5  
(85,90,95,88,92 → 5개)

---

## 문제 3: 문자열에서 특정 문자 개수 세기

### problem3.c

```c
#include <stdio.h>
#include <string.h>

int main() {
    char str[101];
    char ch;
    int i, len, count = 0;

    fgets(str, 101, stdin);   /* 공백 포함 한 줄 */

    /* fgets가 읽은 끝의 \n 제거 */
    len = strlen(str);
    if (len > 0 && str[len - 1] == '\n') {
        str[len - 1] = '\0';
        len--;
    }

    scanf(" %c", &ch);        /* 앞 공백/개행 무시 */

    for (i = 0; i < len; i++) {
        if (str[i] == ch) count++;
    }

    printf("%c가 나타나는 횟수: %d\n", ch, count);

    return 0;
}
```

**참고:** `Hello World` + `l` → 3회 (`l` 세 곳)

`scanf("%s")`만 쓰면 공백 전까지만 읽히므로, 예시1은 `fgets`가 맞습니다.

---

## 문제 4: 배열에서 짝수와 홀수 분리하기

### problem4.c

```c
#include <stdio.h>

int main() {
    int arr[8];
    int even[8], odd[8];
    int i, e = 0, o = 0;

    for (i = 0; i < 8; i++) {
        scanf("%d", &arr[i]);
        if (arr[i] % 2 == 0)
            even[e++] = arr[i];
        else
            odd[o++] = arr[i];
    }

    printf("짝수: ");
    if (e == 0) {
        printf("없음\n");
    } else {
        for (i = 0; i < e; i++) {
            printf("%d", even[i]);
            if (i < e - 1) printf(" ");
        }
        printf("\n");
    }

    printf("홀수: ");
    if (o == 0) {
        printf("없음\n");
    } else {
        for (i = 0; i < o; i++) {
            printf("%d", odd[i]);
            if (i < o - 1) printf(" ");
        }
        printf("\n");
    }

    return 0;
}
```

---

## 문제 5: 배열 역순 출력

### problem5.c

```c
#include <stdio.h>

int main() {
    int arr[5];
    int i;

    for (i = 0; i < 5; i++) {
        scanf("%d", &arr[i]);
    }

    printf("역순: ");
    for (i = 4; i >= 0; i--) {
        printf("%d", arr[i]);
        if (i > 0) printf(" ");
    }
    printf("\n");

    return 0;
}
```

**실행 예:** `1 2 3 4 5` → `역순: 5 4 3 2 1`

---

## 문제 6: 배열에서 값 검색하기

### problem6.c

```c
#include <stdio.h>

int main() {
    int arr[5];
    int key, i, found = 0, idx = -1;

    for (i = 0; i < 5; i++) {
        scanf("%d", &arr[i]);
    }
    scanf("%d", &key);

    for (i = 0; i < 5; i++) {
        if (arr[i] == key) {
            found = 1;
            idx = i;
            break;
        }
    }

    if (found)
        printf("인덱스: %d\n", idx);
    else
        printf("없음\n");

    return 0;
}
```

**실행 예:** `20` → 인덱스 2 / `99` → 없음

---

## 문제 7: 1부터 n까지 합 (함수)

### problem7.c

```c
#include <stdio.h>

int sumToN(int n) {
    int i, sum = 0;
    for (i = 1; i <= n; i++) {
        sum += i;
    }
    return sum;
}

int main() {
    int n;
    scanf("%d", &n);
    printf("합: %d\n", sumToN(n));
    return 0;
}
```

**실행 예:** `10` → `합: 55`

---

## 문제 8: 두 수 교환하기 (포인터)

### problem8.c

```c
#include <stdio.h>

void swap(int *x, int *y) {
    int temp = *x;
    *x = *y;
    *y = temp;
}

int main() {
    int a, b;

    scanf("%d %d", &a, &b);
    printf("교환 전: a=%d b=%d\n", a, b);

    swap(&a, &b);

    printf("교환 후: a=%d b=%d\n", a, b);
    return 0;
}
```

**핵심:** `swap(&a, &b)` — 주소를 넘겨 원본 변경

---

## 문제 9: 학생 구조체 평균

### problem9.c

```c
#include <stdio.h>

struct Student {
    char name[20];
    int score;
};

int main() {
    struct Student s[3];
    int i, sum = 0;

    for (i = 0; i < 3; i++) {
        scanf("%s %d", s[i].name, &s[i].score);
        sum += s[i].score;
    }

    printf("평균: %.2f\n", (double)sum / 3);
    return 0;
}
```

**실행 예:** Hong 85, Kim 90, Lee 80 → `평균: 85.00`

---

## 문제 10: 배열 오름차순 정렬 (버블 정렬)

### problem10.c

```c
#include <stdio.h>

int main() {
    int arr[5];
    int i, j, temp;

    for (i = 0; i < 5; i++) {
        scanf("%d", &arr[i]);
    }

    /* 버블 정렬 (오름차순) */
    for (i = 0; i < 4; i++) {
        for (j = 0; j < 4 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    printf("정렬: ");
    for (i = 0; i < 5; i++) {
        printf("%d", arr[i]);
        if (i < 4) printf(" ");
    }
    printf("\n");

    return 0;
}
```

**실행 예:** `5 2 8 1 9` → `정렬: 1 2 5 8 9`

---

## 채점용 빠른 체크

| 문제 | 입력 예 | 기대 출력 |
|------|---------|-----------|
| 1 | `10 5 20 8 15` | 최댓값 20 / 최솟값 5 |
| 2 | `85 90 75 60 95 80 70 65 88 92` | 평균 80.00 / 개수 5 |
| 3 | `Hello World` + `l` | 횟수 3 |
| 4 | `1 2 3 4 5 6 7 8` | 짝수 2 4 6 8 / 홀수 1 3 5 7 |
| 5 | `1 2 3 4 5` | 역순: 5 4 3 2 1 |
| 6 | `10 5 20 8 15` + `20` | 인덱스: 2 |
| 7 | `10` | 합: 55 |
| 8 | `10 20` | 교환 후 a=20 b=10 |
| 9 | Hong 85 / Kim 90 / Lee 80 | 평균: 85.00 |
| 10 | `5 2 8 1 9` | 정렬: 1 2 5 8 9 |

---

## 제출 파일

```
problem1.c  …  problem10.c
```
