# C언어 기출문제 정리 (2022년~2024년)

## 2022년 1회

### 문제 6
**문제**: 다음 C언어로 구현된 프로그램을 실행하여 7을 입력했을 때, 그 실행결과를 쓰시오

```c
#include <stdio.h>
 
main() {
  int n, count=0;
  scanf("%d", &n);
  for(int i=2; i<=n/2;i++)
    if(i%1==0)
      count++;
  printf("%d",count);
}
```

**정답**: `2`

**해설**: `i%1==0`은 항상 참이므로 (모든 수는 1로 나누어떨어짐), 루프가 실행되는 횟수만큼 count가 증가합니다. `i=2`부터 `i<=3`까지이므로 2번 실행됩니다.

---

### 문제 9
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  int n=3, r=0;
  for(int i=1; i<10;i=i+2)
    r=r+n*i;
  printf("%d", r);
}
```

**정답**: `75`

**해설**: 
- i = 1: r = 0 + 3*1 = 3
- i = 3: r = 3 + 3*3 = 12
- i = 5: r = 12 + 3*5 = 27
- i = 7: r = 27 + 3*7 = 48
- i = 9: r = 48 + 3*9 = 75

---

### 문제 14
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  int n1=15, n2=22;
  n1 ^=n2;
  n2 ^=n1;
  n1 ^=n2;
  printf("%d %d", n1, n2);
}
```

**정답**: `22 15`

**해설**: XOR 연산을 이용한 변수 교환
- n1 = 15, n2 = 22
- n1 ^= n2 → n1 = 15 XOR 22 = 25
- n2 ^= n1 → n2 = 22 XOR 25 = 15
- n1 ^= n2 → n1 = 25 XOR 15 = 22

---

### 문제 17
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  int num=35, evencnt=0, oddcnt=0;
  for(int i=1; i<=num; i++){
    if(i%2==0)
      evencnt++;
    else
      oddcnt++;
  }
  printf("%d %d", evencnt, oddcnt);
}
```

**정답**: `17 18`

**해설**: 1부터 35까지의 수 중 짝수는 17개, 홀수는 18개입니다.

---

## 2022년 2회

### 문제 1
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  int a=27, b=12;
  int l, g;
  for(int i=b; i>0; i--){
    if(a%i==0&&b%i==0){
      g=i;
      break;
    }
  }
  l=a*b/g;
  printf("%d",g+l);
}
```

**정답**: `111`

**해설**: 
- 최대공약수(g): 27과 12의 최대공약수는 3
- 최소공배수(l): 27*12/3 = 108
- g+l = 3+108 = 111

---

### 문제 7
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  int sum=0;
  int i=0;
  for(i=0;i<=10;i++){
    if(i%2!=0)
      continue;
        sum+=i;
  }
  printf("%d",i+sum);
}
```

**정답**: `41`

**해설**: 
- 짝수만 sum에 더함: 0+2+4+6+8+10 = 30
- 루프 종료 후 i = 11
- i+sum = 11+30 = 41

---

### 문제 14
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
main() {
  int arr[6];
  int max=0, min=99;
  int sum=0;
  for(int i=0; i<6;i++){
    arr[i]=i*i;
    sum+=arr[i];
  }
  for(int i=0;i<6;i++){
    if (max<arr[i])
      max=arr[i];
    if (min>arr[i])
      min=arr[i];
  }
  printf("%.2f",(sum-max-min)/4.0);
}
```

**정답**: `7.50`

**해설**: 
- arr = {0, 1, 4, 9, 16, 25}
- sum = 55, max = 25, min = 0
- (55-25-0)/4.0 = 30/4.0 = 7.50

---

### 문제 15
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
#include <math.h>
main() {
  int arr[5];
  for(int i=0; i<5;i++){
    arr[i]=(i+2)+(i*2);
  }
  for(int i=0;i<5;i++){
    printf("%d",check(arr[i]));
  }
}
int check(int a){
  int n=(int)sqrt(a);
  int i=2;
  while(i<=n){
    if(a%i==0) return 0;
    i++;
  }
  return 1;
}
```

**정답**: `11010`

**해설**: 
- arr = {2, 5, 8, 11, 14}
- check(2) = 1 (소수)
- check(5) = 1 (소수)
- check(8) = 0 (합성수)
- check(11) = 1 (소수)
- check(14) = 0 (합성수)

---

### 문제 19
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  char a[3][5]={"KOR","HUM","RES"};
  char*pa[]={a[0],a[1],a[2]};
  int n=sizeof(pa)/sizeof(pa[0]);
  for(int i=0; i<n;i++)
    printf("%c", pa[i][i]);
}
```

**정답**: `KUS`

**해설**: 
- pa[0][0] = 'K'
- pa[1][1] = 'U'
- pa[2][2] = 'S'

---

## 2022년 3회

### 문제 6
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  int a[3][5]={{27,13,21,41,12},{11,20,17,35,15},{21,15,32,14,10}};
  int sum, ssum=0;
  for(int i=0;i<3; i++){
    sum=0;
    for(int j=0; j<5;j++)
      sum+=a[i][j];
    ssum+=sum;
  }
  printf("%d",ssum);
}
```

**정답**: `304`

**해설**: 모든 요소의 합
- 첫 번째 행: 27+13+21+41+12 = 114
- 두 번째 행: 11+20+17+35+15 = 98
- 세 번째 행: 21+15+32+14+10 = 92
- 총합: 114+98+92 = 304

---

### 문제 8
**문제**: 다음 C언어로 구현된 프로그램을 분석하여 그 실행 결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  int i=1, n=0;
  while(i<=50){
    if(i%7==0)
      n+=i;
    i++;
  }
  printf("%d", n);
}
```

**정답**: `196`

**해설**: 1부터 50까지 7의 배수의 합
- 7+14+21+28+35+42+49 = 196

---

### 문제 16
**문제**: 다음 C언어로 구현된 프로그램에서 화면에 5를 입력한 후 나타나는 결과를 쓰시오.

```c
#include <stdio.h>
 
main() {
  int n, sum=0;
  printf("정수를 입력하시오 : ");
  scanf("%d",&n);
  for (int i=1; i<=n;i++)
    sum=sum+i;
  printf("%d", sum);
}
```

**정답**: `15`

**해설**: 1부터 5까지의 합 = 1+2+3+4+5 = 15

---

## 2023년 1회

### 문제 4
**문제**: 다음 코드에서 (가)에 들어갈 것을 쓰시오.

```c
#include<stdio.h>
#define LEN 10
void swap(int arr[], int i, int j) {
	int temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
}
int partition(int arr[], int left, int right) {
	int pivot = arr[(left + right) / (가)];
	while(left <= right) {
		while(arr[left] < pivot)
			left++;
		while(arr[right] > pivot) 
			right--;
		if(left <= right) {
			swap(arr, left, right);
			left++;
			right--;
		}
	}
	return left;
}
void sort(int arr[], int left, int right) {
	if(left >= right)
		return;
	int center = partition(arr, left, right);
	sort(arr, left, center-1);
	sort(arr, center, right);
}
void quick_sort(int arr[]) {
	sort(arr, 0, LEN-1);
}
int main(void) {
	int data[LEN] = {5, 8, 3, 12, 9, 25, 15, 21, 1, 19};
	quick_sort(data);
	for(int i=0;i<LEN;i++){
		printf("%d\n", data[i]);
	}
}
```

**정답**: `2`

**해설**: 퀵 정렬에서 피벗을 중간값으로 선택하기 위해 (left+right)/2를 계산합니다.

---

### 문제 5
**문제**: 다음 코드에서 (가)에 들어갈 것을 쓰시오.

```c
#include<stdio.h>
int SumNTo1(int n) {
	if(n <= 1)
		return 1;
	else
		return n + (가);
}
int main() {
	int result = SumNTo1(100);
	printf("%d", result);
}
```

**정답**: `SumNTo1(n-1)`

**해설**: 재귀 함수로 1부터 n까지의 합을 구하는 함수입니다.

---

### 문제 16
**문제**: 다음 코드에서 (가)에 들어갈 것을 쓰시오.

```c
#include<stdio.h>
int recursive(int n) {
	if(n <= 1) 
		return 1;
	else 
		return n*n+recursive((가));
}
int main() {
	int i;
	scanf("%d", &i);
	printf("%d ", recursive(i));
}
```

**정답**: `n-1`

**해설**: 재귀 호출 시 n을 1씩 감소시켜야 합니다.

---

## 2023년 2회

### 문제 3
**문제**: 출력 결과

```c
#include<stdio.h>
int main() {
	int d = 55;
	int n = 4;
	int r = 0, q = 0;
	r = d;
	while(r >= 4) {
		r = r - n;
		q++;
	}
	printf("%d 그리고 ", q);
	printf("%d", r);
}
```

**정답**: `13 그리고 3`

**해설**: 나눗셈 연산을 뺄셈으로 구현
- 55를 4로 나눈 몫: 13, 나머지: 3

---

### 문제 16
**문제**: 코드 아래에 있는 것이 출력결과인데 출력결과의 파란색 빈칸에 들어갈 출력값을 적으시오

```c
#include<stdio.h>
void Sort(int a[][6]) {
	for(int i=0; i<=4; i++) {
		for(int j=5; j>=0; j--) {
			if(i==0 | i==4 | j==0 | j==5) {
				printf("%3d", a[i][j]);
			}
			else {
				printf("   ");  // 공백 3칸 
			}
		}
		printf("\n");
	}
}
int main() {
	int a[5][6] = {{0, 1, 2, 3, 4, 5},
				{6, 7, 8, 9, 10, 11},
				{12, 13, 14, 15, 16, 17},
				{18, 19, 20, 21, 22, 23},
				{24, 25, 26, 27, 28, 29}};
	Sort(a);
}
```

**정답**: 
```
17                           12
23                           18
```

**해설**: 가장자리 요소만 출력하되, 열을 역순으로 출력합니다.

---

### 문제 17
**문제**: 출력결과

```c
#include<stdio.h>
int main() {
	char s1[5] = "abcd";
	char s2[5];
	for(int i=0; i<4; i++) {
		s2[i] = s1[3-i];
	}
	s2[5] = '\0';
	printf("%s", s2);
}
```

**정답**: `dcba`

**해설**: 문자열을 역순으로 복사합니다.

---

### 문제 18
**문제**: 출력결과

```c
#include<stdio.h>
int main() {
	// A = 65, a = 97
	int a = 10;
	char b = 'a';
	printf("%d \n", a);
	printf("%d \n", b); 
	printf("%c", b); 
}
```

**정답**: 
```
10
97
a
```

**해설**: 
- %d로 출력하면 문자 'a'의 ASCII 값인 97이 출력됩니다.
- %c로 출력하면 문자 'a'가 출력됩니다.

---

### 문제 20
**문제**: 출력결과

```c
#include<stdio.h>
int main() {
	int result=1, i;
	for(i=1; i<=5; i++) {
		result *= i;
	}
	printf("%d", result);
}
```

**정답**: `120`

**해설**: 5! = 1×2×3×4×5 = 120

---

## 2023년 3회

### 문제 2
**문제**: 출력결과

```c
#include<stdio.h>
#define MAX 4
int prin(int a);
int main() {
	prin(MAX);
}
int prin(int a) {
	if(a > 1) prin(a-1);
	printf("%d", a);
}
```

**정답**: `1234`

**해설**: 재귀 호출 후 출력하므로 1, 2, 3, 4 순서로 출력됩니다.

---

### 문제 5
**문제**: 빈칸(가)의 답을 쓰시오

```c
#include <stdio.h>
#define SIZE 5
int p[4][2] = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
int snail(int a[SIZE][SIZE]) {
	int i = 0, j = 0, v = 1, c = 0;
	a[i][j] = v;
	v += 1;
	while (v <= SIZE * SIZE) {
		int ni = i + p[c][0];
		int nj = j + p[c][1];
		if (0 <= ni && ni < SIZE && 0 <= nj && nj < SIZE && a[ni][nj] == 0) {
			i = ni;
			j = nj;
			a[i][j] = v;
			v += 1;
 
		}
		else {
			c = (c + 1) % (  가  );
		}
 
	}
 
}
 
 
int main() {
	int a[SIZE][SIZE] = { 0 };
	snail(a);
	for (int i = 0; i < SIZE; i++) {
		for (int j = 0; j < SIZE; j++) {
			printf("%3d", a[i][j]);
		}
		printf("\n");
 
 
 
	}
 
}
```

**정답**: `4`

**해설**: 방향 배열 p의 크기가 4이므로, 방향을 순환하기 위해 4로 나눈 나머지를 사용합니다.

---

### 문제 7
**문제**: 출력결과

```c
#include<stdio.h>
int main() {
    int data[] = {15, 12, 26, 23, 31, 36};
    int temp = 0;
    int cnt = sizeof(data)/sizeof(int);
    for(int i=0; i < cnt-1; i++) {
        for(int j=0; j < cnt - 1 - i; j++){
            if(data[j] > data[j+1]) {
                temp = data[j];
                data[j] = data[j+1];
                data[j+1] = temp;
            }
            }
        }
      
    for(int i=1; i<cnt-1;i++){
        printf("%d ", data[i]);
    }
}
```

**정답**: `15 23 26 31`

**해설**: 버블 정렬 후 정렬된 배열: {12, 15, 23, 26, 31, 36}
- 인덱스 1부터 4까지 출력: 15, 23, 26, 31

---

### 문제 9
**문제**: 출력결과

```c
#include<stdio.h>
int sub(int* a) {
	printf("%d", *a);
	printf("%d", a[2]);
}
int main() {
	int a[10] = {1,2,3,4,5,6};
	sub(a);
	sub(a+2);
}
```

**정답**: `1335`

**해설**: 
- sub(a): *a = 1, a[2] = 3 → "13"
- sub(a+2): *(a+2) = 3, (a+2)[2] = a[4] = 5 → "35"

---

### 문제 13
**문제**: (가), (나)에 적절한 것을 쓰시오

```c
#include<stdio.h>
int compare(int i, int j, int *ma, int *in) {
	if(i>j) {
		*ma = j;
		*in = i;
	}
	else {
		*ma = i;
		*in = j;
	}
}
int main() {
	int max = 0;
	int min = 0;
	compare(3, 7, (가), (나));
	printf("작은수:%d 큰수:%d", max, min);
}
```

**정답**: 
- (가) `&max`
- (나) `&min`

**해설**: 포인터를 통해 값을 변경하려면 주소를 전달해야 합니다.

---

## 2024년 1회

### 문제 1
**문제**: C언어 출력결과

```c
#include <stdio.h>
#include <string.h>
int main() {
	char* str = "abCDEfGh";
	int count = 0;
	for (int i = 0; i < strlen(str); i++) {
		char ch = str[i];
		if (ch >= 'A' && ch <= 'Z') {
			count++;
 
		}
	}
	printf("%d", count);
 
 
 
}
```

**정답**: `4`

**해설**: 대문자 개수 세기: C, D, E, G → 4개

---

### 문제 18
**문제**: C언어 아래 string, test를 입력했을 때 출력결과

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main() {
	int N = 2;
	char* str[N];
	for (int i = 0; i < N; i++) {
		char input[100];
		scanf("%s", input);
		str[i] = (char*)malloc(sizeof(char) * (strlen(input) + 1));
		strcpy(str[i], input);
	}
		while (N > 0) {
			printf("%d번 출력 :", N);
			for (int i = strlen(str[N - 1]) - 1; i >= 0; i--) {
				printf("%c", str[N - 1][i]);
			}
				printf("\n");
				N--;
 
			}
 
		}
```

**입력**: 
```
string
test
```

**정답**: 
```
2번 출력 :tset
1번 출력 :gnirts
```

**해설**: 입력받은 문자열을 역순으로 출력합니다.

---

### 문제 19
**문제**: C언어 출력결과

```c
#include <stdio.h>
int main() {
int sum = 0;
int i = 329;
do {
 
sum = 999 % i:
i++;
} while(sum != 0);
printf("%d", i);
return 0;
 
}
```

**정답**: `334`

**해설**: 
- i=329: 999%329 = 12 (0이 아님)
- i=330: 999%330 = 9 (0이 아님)
- i=331: 999%331 = 6 (0이 아님)
- i=332: 999%332 = 3 (0이 아님)
- i=333: 999%333 = 0 (조건 만족, 루프 종료)
- i=334 출력

---

## 2024년 2회

### 문제 3
**문제**: C언어 실행결과

```c
#include <stdio.h>
int main( ) {
 
int i = 3;
int j =5;
printf("%d", (i | j) - (i &j));
return 0;
}
```

**정답**: `6`

**해설**: 
- i | j = 3 | 5 = 7 (비트 OR)
- i & j = 3 & 5 = 1 (비트 AND)
- 7 - 1 = 6

---

### 문제 5
**문제**: C언어 출력결과

```c
#include <stdio.h>
struct number {
float i;
float j;
};
 
int main() {
struct number a = {3.0, 4.0};
struct number b = {4.0, 5.0};
printf("%.2f 그리고 %.2f", a.i+b.i, a.j + b.j);
 
}
```

**정답**: `7.00 그리고 9.00`

**해설**: 
- a.i + b.i = 3.0 + 4.0 = 7.00
- a.j + b.j = 4.0 + 5.0 = 9.00

---

### 문제 7
**문제**: 다음은 구구단의 3단을 출력하는 코드를 C언어로 구현한 것이다. 괄호에 알맞은 수식을 채워 코드를 완성하시오.

```c
#include <stdio.h>
int main( ) {
 
int n = 3;
 
for(int i = 1; i <= 9; i++) {
printf("%d*%d=%d\n", n, i, (가));
 
   }
}
```

**정답**: `(가) n*i`

**해설**: 구구단 출력을 위한 곱셈 연산입니다.

---

### 문제 9
**문제**: C언어 출력 결과

```c
#include <stdio.h>
int main( ) {
int n1 =11;
 
int n2 = 0;
int* p = NULL;
p = &n1;
n2 = *p + n1;
printf("%d", *p-n1+n2);
}
```

**정답**: `22`

**해설**: 
- p = &n1, *p = 11
- n2 = 11 + 11 = 22
- *p - n1 + n2 = 11 - 11 + 22 = 22

---

### 문제 17
**문제**: C언어 실행결과

```c
#include <stdio.h>
int main( ) {
int a[5] ={3, 4, 5, 10, 2};
int *p = a;
int i, j, temp;
for(i = 0; i <5; i++) {
for(j =i+1; j <5; j++) {
if(*(p+i) > *(p+j)) {
temp = *(p+i);
*(p+i) = *(p+j);
*(p+j) = temp;
         }
     }
     printf("%d ", *(p+i));
  }
}
```

**정답**: `2 3 4 5 10`

**해설**: 선택 정렬로 배열을 정렬하며 각 단계마다 출력합니다.

---

## 2024년 3회

### 문제 4
**문제**: C언어 출력결과

```c
#include <stdio.h>
union Number {
int intV;
int floatV;
};
 
struct data {
union Number n1;
union Number n2;
char islnt;
};
 
void func(struct data *a) {
if (a->islnt) {
a->n1.intV +=a->n2.intV;
}
else {
a->n1.floatV += a->n2.floatV;
 
   }
}
 
int main() {
struct data a = {{.intV = 5}, {.floatV = 3.5}, 1};
func(&a);
printf("%d", a.n1.intV);
return 0;
}
```

**정답**: `8`

**해설**: 
- islnt = 1이므로 intV로 연산
- n1.intV = 5, n2.intV = 3 (floatV를 intV로 해석하면 3)
- 5 + 3 = 8

---

### 문제 9
**문제**: C언어 출력결과

```c
#include <stdio.h>
void swap(int *ptr1, int *ptr2) {
int temp = *ptr1;
*ptr1 = *ptr2;
*ptr2 = temp;
}
 
void func(int *arr, int n) {
int *ptr1 = &arr[0];
int *ptr2 = &arr[n-1];
while(ptr1 <ptr2) {
swap(ptr1, ptr2);
ptr1++;
ptr2 --;
  }
}
 
int main( ) {
int arr[] = {1, 2, 3, 4, 5};
func(arr, 5);
printf("%d", arr[2]);
return 0;
}
```

**정답**: `3`

**해설**: 배열을 역순으로 바꾸면 {5, 4, 3, 2, 1}이 되고, arr[2] = 3입니다.

---

### 문제 11
**문제**: C언어 출력결과

```c
#include <stdio.h>
int main( ) {
int sum = 0;
int i = 0;
for(i = 0; i <= 10; i++) {
if(i % 2 != 0)
 
continue;
 
sum += i;
 }
printf("%d", i + sum);
}
```

**정답**: `41`

**해설**: 
- 짝수만 sum에 더함: 0+2+4+6+8+10 = 30
- 루프 종료 후 i = 11
- i+sum = 11+30 = 41

---

### 문제 14
**문제**: C언어 출력결과

```c
#include <stdio.h>
int factorial(int n, int from, int to, int temp) {
if(n == 0) {
return 0;
  }
else {
return factorial(n-1, from, temp, to)+1 + factorial(n-1, temp, to, from);
   }
}
int main( ) {
int n = 3;
printf("%d", factorial(n, 3, 2, 1));
return 0;
}
```

**정답**: `7`

**해설**: 하노이 탑 문제의 이동 횟수를 계산하는 재귀 함수입니다.
- n개의 원판을 옮기는 최소 이동 횟수: 2^n - 1
- n=3일 때: 2^3 - 1 = 7

---

### 문제 19
**문제**: C언어 출력 결과

```c
#include <stdio.h>
void func(int ** arr, int size) {
for(int i = 0; i < size; i++) {
*(*arr + i) = (*(*arr + i) + i) % size;
 
 }
}
int main( ){
int arr[] = {3, 1, 4, 1, 5};
int* p = arr;
int ** pp = &p;
int num = 6;
func(pp, 5);
num = arr[2];
printf("%d", num);
return 0;
 
}
```

**정답**: `1`

**해설**: 
- arr = {3, 1, 4, 1, 5}
- func에서 각 요소를 (원래값 + 인덱스) % 5로 변경
- arr[0] = (3+0)%5 = 3
- arr[1] = (1+1)%5 = 2
- arr[2] = (4+2)%5 = 1
- arr[3] = (1+3)%5 = 4
- arr[4] = (5+4)%5 = 4
- arr[2] = 1

---

## 문제 유형별 분류

### 1. 반복문 및 조건문
- 2022년 1회 문제 6, 9, 17
- 2022년 2회 문제 7
- 2022년 3회 문제 8, 16
- 2023년 2회 문제 3, 20
- 2024년 1회 문제 1, 19
- 2024년 2회 문제 3, 7
- 2024년 3회 문제 11

### 2. 배열 및 포인터
- 2022년 2회 문제 14, 15, 19
- 2022년 3회 문제 6
- 2023년 2회 문제 16, 17
- 2023년 3회 문제 7, 9, 13
- 2024년 2회 문제 9, 17
- 2024년 3회 문제 9, 19

### 3. 재귀 함수
- 2023년 1회 문제 5, 16
- 2023년 3회 문제 2
- 2024년 3회 문제 14

### 4. 정렬 알고리즘
- 2023년 1회 문제 4 (퀵 정렬)
- 2023년 3회 문제 5 (달팽이 배열)
- 2023년 3회 문제 7 (버블 정렬)
- 2024년 2회 문제 17 (선택 정렬)

### 5. 비트 연산
- 2022년 1회 문제 14 (XOR 교환)
- 2024년 2회 문제 3 (OR, AND)

### 6. 구조체 및 공용체
- 2024년 2회 문제 5 (구조체)
- 2024년 3회 문제 4 (공용체)

### 7. 문자열 처리
- 2022년 2회 문제 19
- 2023년 2회 문제 17, 18
- 2024년 1회 문제 1, 18

---

**작성일**: 2024년  
**범위**: 2022년~2024년 기출문제  
**과목**: C언어 프로그래밍
