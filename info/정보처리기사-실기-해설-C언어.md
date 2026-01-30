# 정보처리기사 실기시험 해설 (C 언어)

---

## 문제 1 해설: 배열 처리 알고리즘 (20점)

### 전체 코드

```c
#include <stdio.h>
#include <stdlib.h>

void printArray(int arr[], int n) {
    printf("[");
    for (int i = 0; i < n; i++) {
        printf("%d", arr[i]);
        if (i < n - 1) {
            printf(", ");
        }
    }
    printf("]\n");
}

int main() {
    int arr[] = {3, 7, 2, 9, 1, 5, 8, 4, 6};
    int n = sizeof(arr) / sizeof(arr[0]);
    
    // 1. 최댓값과 최솟값 찾기
    int max = arr[0];
    int min = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
        if (arr[i] < min) {
            min = arr[i];
        }
    }
    printf("최댓값: %d\n", max);
    printf("최솟값: %d\n", min);
    
    // 2. 평균값 계산
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += arr[i];
    }
    double average = (double)sum / n;
    printf("평균값: %.2f\n", average);
    
    // 3. 짝수만 필터링
    int evenCount = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i] % 2 == 0) {
            evenCount++;
        }
    }
    int* evenArr = (int*)malloc(evenCount * sizeof(int));
    int index = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i] % 2 == 0) {
            evenArr[index++] = arr[i];
        }
    }
    printf("짝수 배열: ");
    printArray(evenArr, evenCount);
    free(evenArr);
    
    // 4. 오름차순 정렬 (버블 정렬)
    int* sortedArr = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        sortedArr[i] = arr[i];
    }
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - 1 - i; j++) {
            if (sortedArr[j] > sortedArr[j + 1]) {
                int temp = sortedArr[j];
                sortedArr[j] = sortedArr[j + 1];
                sortedArr[j + 1] = temp;
            }
        }
    }
    printf("정렬된 배열: ");
    printArray(sortedArr, n);
    free(sortedArr);
    
    return 0;
}
```

### 더 간단한 방법 (정적 배열 사용)

```c
#include <stdio.h>

void printArray(int arr[], int n) {
    printf("[");
    for (int i = 0; i < n; i++) {
        printf("%d", arr[i]);
        if (i < n - 1) printf(", ");
    }
    printf("]\n");
}

void bubbleSort(int arr[], int n) {
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

int main() {
    int arr[] = {3, 7, 2, 9, 1, 5, 8, 4, 6};
    int n = sizeof(arr) / sizeof(arr[0]);
    
    // 최댓값, 최솟값
    int max = arr[0], min = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] > max) max = arr[i];
        if (arr[i] < min) min = arr[i];
    }
    printf("최댓값: %d\n", max);
    printf("최솟값: %d\n", min);
    
    // 평균값
    int sum = 0;
    for (int i = 0; i < n; i++) sum += arr[i];
    printf("평균값: %.2f\n", (double)sum / n);
    
    // 짝수 필터링
    int evenArr[10], evenCount = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i] % 2 == 0) {
            evenArr[evenCount++] = arr[i];
        }
    }
    printf("짝수 배열: ");
    printArray(evenArr, evenCount);
    
    // 정렬
    int sortedArr[10];
    for (int i = 0; i < n; i++) sortedArr[i] = arr[i];
    bubbleSort(sortedArr, n);
    printf("정렬된 배열: ");
    printArray(sortedArr, n);
    
    return 0;
}
```

### 핵심 포인트

1. **배열 크기 계산**: `sizeof(arr) / sizeof(arr[0])`
2. **최댓값/최솟값**: 첫 번째 요소를 초기값으로 설정하고 순회하며 비교
3. **평균값**: 합계를 배열 길이로 나눔 (정수 나눗셈 주의 - `(double)` 캐스팅)
4. **짝수 필터링**: `% 2 == 0` 조건 사용
5. **정렬**: 버블 정렬 또는 `qsort()` 함수 사용 가능
6. **동적 메모리**: `malloc()` 사용 시 반드시 `free()` 호출

---

## 문제 2 해설: 문자열 처리 알고리즘 (20점)

### 전체 코드

```c
#include <stdio.h>
#include <string.h>
#include <ctype.h>

// 문자열을 역순으로 만드는 함수
void reverseString(char* str) {
    int len = strlen(str);
    for (int i = 0; i < len / 2; i++) {
        char temp = str[i];
        str[i] = str[len - 1 - i];
        str[len - 1 - i] = temp;
    }
}

int main() {
    char text[] = "Hello World Java Programming";
    char words[10][50];  // 최대 10개 단어, 각 단어 최대 50자
    int wordCount = 0;
    
    // 1. 공백 기준으로 단어 분리
    char* token = strtok(text, " ");
    while (token != NULL) {
        strcpy(words[wordCount], token);
        wordCount++;
        token = strtok(NULL, " ");
    }
    
    printf("단어 배열: [");
    for (int i = 0; i < wordCount; i++) {
        printf("%s", words[i]);
        if (i < wordCount - 1) printf(", ");
    }
    printf("]\n");
    
    // 2. 각 단어의 길이 계산
    int lengths[10];
    for (int i = 0; i < wordCount; i++) {
        lengths[i] = strlen(words[i]);
    }
    printf("각 단어의 길이: [");
    for (int i = 0; i < wordCount; i++) {
        printf("%d", lengths[i]);
        if (i < wordCount - 1) printf(", ");
    }
    printf("]\n");
    
    // 3. 대문자로 시작하는 단어 필터링
    printf("대문자로 시작하는 단어: [");
    int first = 1;
    for (int i = 0; i < wordCount; i++) {
        if (isupper(words[i][0])) {
            if (!first) printf(", ");
            printf("%s", words[i]);
            first = 0;
        }
    }
    printf("]\n");
    
    // 4. 모든 단어를 역순으로 출력
    printf("역순 문자열: ");
    for (int i = 0; i < wordCount; i++) {
        char temp[50];
        strcpy(temp, words[i]);
        reverseString(temp);
        printf("%s", temp);
        if (i < wordCount - 1) printf(" ");
    }
    printf("\n");
    
    return 0;
}
```

### 더 간단한 방법 (문자열 복사 사용)

```c
#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main() {
    char text[] = "Hello World Java Programming";
    char words[10][50];
    int wordCount = 0;
    
    // 단어 분리
    char* token = strtok(text, " ");
    while (token != NULL) {
        strcpy(words[wordCount++], token);
        token = strtok(NULL, " ");
    }
    
    // 출력
    printf("단어 배열: [");
    for (int i = 0; i < wordCount; i++) {
        printf("%s%s", words[i], (i < wordCount - 1) ? ", " : "");
    }
    printf("]\n");
    
    // 길이 계산
    printf("각 단어의 길이: [");
    for (int i = 0; i < wordCount; i++) {
        printf("%d%s", (int)strlen(words[i]), (i < wordCount - 1) ? ", " : "");
    }
    printf("]\n");
    
    // 대문자로 시작하는 단어
    printf("대문자로 시작하는 단어: [");
    int first = 1;
    for (int i = 0; i < wordCount; i++) {
        if (isupper(words[i][0])) {
            printf("%s%s", first ? "" : ", ", words[i]);
            first = 0;
        }
    }
    printf("]\n");
    
    // 역순 출력
    printf("역순 문자열: ");
    for (int i = 0; i < wordCount; i++) {
        int len = strlen(words[i]);
        for (int j = len - 1; j >= 0; j--) {
            printf("%c", words[i][j]);
        }
        if (i < wordCount - 1) printf(" ");
    }
    printf("\n");
    
    return 0;
}
```

### 핵심 포인트

1. **문자열 분리**: `strtok()` 함수 사용 (문자열을 수정하므로 원본 보존 필요 시 복사)
2. **문자열 복사**: `strcpy()` 함수 사용
3. **문자열 길이**: `strlen()` 함수 사용
4. **대문자 확인**: `isupper()` 함수 사용 (ctype.h 필요)
5. **문자열 역순**: 인덱스를 이용한 문자 교환
6. **2차원 배열**: `char words[10][50]` 형태로 단어 배열 저장

---

## 문제 3 해설: 반복문과 조건문 활용 (20점)

### 전체 코드

```c
#include <stdio.h>
#include <stdbool.h>

// 소수 판별 함수
bool isPrime(int n) {
    if (n < 2) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false;
    
    // 3부터 √n까지 홀수만 확인
    for (int i = 3; i * i <= n; i += 2) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

// 자릿수 합 계산 함수
int sumOfDigits(int n) {
    int sum = 0;
    while (n > 0) {
        sum += n % 10;  // 일의 자리 추출
        n /= 10;        // 일의 자리 제거
    }
    return sum;
}

void printArray(int arr[], int count) {
    printf("[");
    for (int i = 0; i < count; i++) {
        printf("%d", arr[i]);
        if (i < count - 1) printf(", ");
    }
    printf("]\n");
}

int main() {
    int commonMultiples[100];
    int cmCount = 0;
    
    // 1. 3과 5의 공배수
    for (int i = 1; i <= 100; i++) {
        if (i % 3 == 0 && i % 5 == 0) {
            commonMultiples[cmCount++] = i;
        }
    }
    printf("3과 5의 공배수: ");
    printArray(commonMultiples, cmCount);
    
    // 2. 소수 찾기
    int primes[100];
    int primeCount = 0;
    for (int i = 2; i <= 100; i++) {
        if (isPrime(i)) {
            primes[primeCount++] = i;
        }
    }
    printf("소수: ");
    printArray(primes, primeCount);
    
    // 3. 자릿수 합이 10 이상인 수
    int digitSum10[100];
    int dsCount = 0;
    for (int i = 1; i <= 100; i++) {
        if (sumOfDigits(i) >= 10) {
            digitSum10[dsCount++] = i;
        }
    }
    printf("자릿수 합이 10 이상인 수: ");
    printArray(digitSum10, dsCount);
    
    return 0;
}
```

### stdbool.h 없이 작성하는 방법

```c
#include <stdio.h>

// 소수 판별 함수 (bool 대신 int 사용)
int isPrime(int n) {
    if (n < 2) return 0;
    if (n == 2) return 1;
    if (n % 2 == 0) return 0;
    
    for (int i = 3; i * i <= n; i += 2) {
        if (n % i == 0) return 0;
    }
    return 1;
}

int sumOfDigits(int n) {
    int sum = 0;
    while (n > 0) {
        sum += n % 10;
        n /= 10;
    }
    return sum;
}

int main() {
    // 3과 5의 공배수
    printf("3과 5의 공배수: [");
    int first = 1;
    for (int i = 1; i <= 100; i++) {
        if (i % 3 == 0 && i % 5 == 0) {
            printf("%s%d", first ? "" : ", ", i);
            first = 0;
        }
    }
    printf("]\n");
    
    // 소수
    printf("소수: [");
    first = 1;
    for (int i = 2; i <= 100; i++) {
        if (isPrime(i)) {
            printf("%s%d", first ? "" : ", ", i);
            first = 0;
        }
    }
    printf("]\n");
    
    // 자릿수 합이 10 이상
    printf("자릿수 합이 10 이상인 수: [");
    first = 1;
    for (int i = 1; i <= 100; i++) {
        if (sumOfDigits(i) >= 10) {
            printf("%s%d", first ? "" : ", ", i);
            first = 0;
        }
    }
    printf("]\n");
    
    return 0;
}
```

### 핵심 포인트

1. **공배수**: `i % 3 == 0 && i % 5 == 0` 조건 사용
2. **소수 판별**: 
   - 2는 소수
   - 짝수는 소수가 아님 (2 제외)
   - 3부터 √n까지 홀수만 확인하여 효율성 향상
3. **자릿수 합**: 
   - `n % 10`으로 일의 자리 추출
   - `n /= 10`으로 일의 자리 제거
   - 반복하여 모든 자릿수 합 계산
4. **배열 출력**: 첫 번째 요소 처리 시 쉼표 제외

---

## 문제 4 해설: 구조체와 포인터 활용 (20점)

### 전체 코드

```c
#include <stdio.h>
#include <string.h>

typedef struct {
    int student_id;
    char name[50];
    int age;
    char department[50];
    int score;
} Student;

int main() {
    Student students[] = {
        {1, "김철수", 20, "컴퓨터공학", 85},
        {2, "이영희", 21, "전자공학", 92},
        {3, "박민수", 20, "컴퓨터공학", 78},
        {4, "최지영", 22, "전자공학", 88},
        {5, "정수진", 21, "컴퓨터공학", 95},
        {6, "한동욱", 20, "기계공학", 82},
        {7, "송미영", 22, "컴퓨터공학", 90},
        {8, "윤태호", 21, "전자공학", 87}
    };
    int student_count = 8;
    
    // 1. 컴퓨터공학과 학생들의 평균 점수
    int csSum = 0, csCount = 0;
    for (int i = 0; i < student_count; i++) {
        if (strcmp(students[i].department, "컴퓨터공학") == 0) {
            csSum += students[i].score;
            csCount++;
        }
    }
    printf("컴퓨터공학과 평균 점수: %.2f\n", (double)csSum / csCount);
    
    // 2. 각 학과별 학생 수와 평균 점수
    char departments[10][50];
    int deptCount = 0;
    
    // 고유한 학과 찾기
    for (int i = 0; i < student_count; i++) {
        int found = 0;
        for (int j = 0; j < deptCount; j++) {
            if (strcmp(students[i].department, departments[j]) == 0) {
                found = 1;
                break;
            }
        }
        if (!found) {
            strcpy(departments[deptCount++], students[i].department);
        }
    }
    
    printf("학과별 통계:\n");
    for (int i = 0; i < deptCount; i++) {
        int count = 0, sum = 0;
        for (int j = 0; j < student_count; j++) {
            if (strcmp(students[j].department, departments[i]) == 0) {
                count++;
                sum += students[j].score;
            }
        }
        printf("  %s: 학생수 %d명, 평균 %.2f점\n", 
               departments[i], count, (double)sum / count);
    }
    
    // 3. 점수가 85점 이상인 학생
    printf("85점 이상 학생:\n");
    for (int i = 0; i < student_count; i++) {
        if (students[i].score >= 85) {
            printf("  %s: %d점\n", students[i].name, students[i].score);
        }
    }
    
    // 4. 나이가 21세 이상인 학생 중 점수가 가장 높은 학생
    Student* maxStudent = NULL;
    int maxScore = -1;
    for (int i = 0; i < student_count; i++) {
        if (students[i].age >= 21 && students[i].score > maxScore) {
            maxScore = students[i].score;
            maxStudent = &students[i];
        }
    }
    if (maxStudent != NULL) {
        printf("21세 이상 최고점 학생: %s (%d점)\n", 
               maxStudent->name, maxStudent->score);
    }
    
    return 0;
}
```

### 핵심 포인트

1. **구조체 정의**: `typedef struct` 사용
2. **문자열 비교**: `strcmp()` 함수 사용 (같으면 0 반환)
3. **문자열 복사**: `strcpy()` 함수 사용
4. **포인터 사용**: 최고점 학생 찾기 시 포인터 활용
5. **구조체 멤버 접근**: `.` 연산자 사용
6. **포인터를 통한 접근**: `->` 연산자 사용

---

## 문제 5 해설: 업무 프로세스 및 신기술 용어 (20점)

### 5-1. 업무 프로세스 (10점)

```
[주문 접수] → [①재고 확인] → [②결제 확인] → [배송 준비] → [배송] → [③배송 완료]
```

**해설:**
- 온라인 쇼핑몰의 주문 처리 프로세스는 다음과 같은 순서로 진행됩니다:
  1. **주문 접수**: 고객이 주문을 요청
  2. **재고 확인**: 주문한 상품의 재고 확인
  3. **결제 확인**: 결제가 완료되었는지 확인
  4. **배송 준비**: 상품 포장 및 배송 준비
  5. **배송**: 물류 시스템을 통한 배송
  6. **배송 완료**: 고객이 상품을 수령

### 5-2. 신기술 용어 설명 (10점)

#### 1. DevOps
**답:** 개발(Development)과 운영(Operations)을 결합한 문화 및 방법론으로, 개발팀과 운영팀 간의 협업을 강화하여 소프트웨어 개발과 배포 프로세스를 자동화하고 효율화하는 것을 목표로 합니다.

#### 2. CI/CD
**답:** 
- **CI (Continuous Integration)**: 개발자들이 코드 변경사항을 공유 저장소에 자주 병합하고, 자동화된 빌드 및 테스트를 통해 통합 오류를 조기에 발견하는 개발 방법론입니다.
- **CD (Continuous Deployment/Delivery)**: CI를 통해 통합된 코드를 자동으로 테스트, 빌드, 배포하는 프로세스입니다.

#### 3. 마이크로서비스 아키텍처(Microservices Architecture)
**답:** 하나의 큰 애플리케이션을 작은 독립적인 서비스들로 분해하여 개발하고 배포하는 아키텍처 패턴입니다. 각 서비스는 독립적으로 개발, 배포, 확장이 가능하며, API를 통해 통신합니다.

#### 4. 컨테이너화(Containerization)
**답:** 애플리케이션과 그 실행 환경을 하나의 패키지로 묶어 어느 환경에서든 동일하게 실행할 수 있도록 하는 기술입니다. Docker가 대표적인 컨테이너 기술이며, 가상머신보다 가볍고 빠르게 동작합니다.

#### 5. API Gateway
**답:** 클라이언트와 백엔드 서비스 사이에서 모든 API 요청을 중앙에서 관리하는 단일 진입점 역할을 하는 서버입니다. 라우팅, 인증/인가, 로드 밸런싱, 모니터링 등의 기능을 제공합니다.

---

## 문제 6 해설: 종합 알고리즘 문제 (20점)

### 전체 코드

```c
#include <stdio.h>
#include <string.h>

int main() {
    char names[][20] = {"김철수", "이영희", "박민수", "최지영", "정수진"};
    int scores[] = {85, 92, 78, 88, 95};
    int count = 5;
    
    // 1. 평균 점수 계산
    int sum = 0;
    for (int i = 0; i < count; i++) {
        sum += scores[i];
    }
    double average = (double)sum / count;
    printf("평균 점수: %.1f\n", average);
    
    // 2. 평균 이상 학생 출력
    printf("평균 이상 학생:\n");
    for (int i = 0; i < count; i++) {
        if (scores[i] >= average) {
            printf("  %s: %d점\n", names[i], scores[i]);
        }
    }
    
    // 3. 등급 부여 및 집계
    int gradeCount[4] = {0};  // A, B, C, D
    char grades[5];
    
    for (int i = 0; i < count; i++) {
        if (scores[i] >= 90) {
            grades[i] = 'A';
            gradeCount[0]++;
        } else if (scores[i] >= 80) {
            grades[i] = 'B';
            gradeCount[1]++;
        } else if (scores[i] >= 70) {
            grades[i] = 'C';
            gradeCount[2]++;
        } else {
            grades[i] = 'D';
            gradeCount[3]++;
        }
    }
    
    // 4. 등급별 학생 수 출력
    printf("\n등급별 학생 수:\n");
    printf("  A등급: %d명\n", gradeCount[0]);
    printf("  B등급: %d명\n", gradeCount[1]);
    printf("  C등급: %d명\n", gradeCount[2]);
    printf("  D등급: %d명\n", gradeCount[3]);
    
    return 0;
}
```

### 핵심 포인트

1. **평균 계산**: 합계를 학생 수로 나눔 (정수 나눗셈 주의)
2. **조건 필터링**: 평균 이상 학생만 출력
3. **등급 부여**: if-else if 문으로 점수 범위에 따라 등급 결정
4. **집계**: 등급별로 카운트하여 출력
5. **2차원 문자 배열**: `char names[][20]` 형태로 이름 배열 저장

---

## 전체 문제 해설 요약

### C 언어 공통 주의사항

1. **헤더 파일**: 필요한 헤더 파일 포함 (`stdio.h`, `string.h`, `stdlib.h` 등)
2. **변수 초기화**: 반복문에서 사용하는 변수는 적절히 초기화
3. **배열 인덱스**: 배열 범위를 벗어나지 않도록 주의
4. **타입 변환**: 정수 나눗셈 시 실수 결과가 필요하면 `(double)` 캐스팅
5. **메모리 관리**: `malloc()` 사용 시 반드시 `free()` 호출
6. **문자열 처리**: `strcpy()`, `strcmp()`, `strlen()` 등 표준 함수 활용
7. **출력 형식**: `printf()`의 형식 지정자 정확히 사용 (`%.2f` 등)

### 실기 시험 팁

1. **시간 관리**: 각 문제당 약 25분 할당
2. **코드 구조**: 명확한 변수명과 주석 사용
3. **함수 분리**: 반복되는 로직은 함수로 분리
4. **부분 점수**: 완전하지 않아도 부분적으로 맞으면 점수 획득 가능
5. **디버깅**: 간단한 테스트 케이스로 검증
6. **컴파일 오류**: 문법 오류 없이 작성 (컴파일 가능한 코드)

### C 언어 특화 팁

1. **배열 크기**: 컴파일 타임에 알 수 있는 경우 `sizeof()` 사용
2. **동적 할당**: 크기를 모를 때 `malloc()` 사용, 반드시 `free()`
3. **문자열**: C 언어는 문자열이 없으므로 `char` 배열 사용
4. **포인터**: 구조체 포인터 사용 시 `->` 연산자
5. **표준 함수**: `qsort()` 정렬 함수 활용 가능
