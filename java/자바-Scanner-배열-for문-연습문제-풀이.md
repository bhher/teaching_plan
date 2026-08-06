# 자바 Scanner · 배열 · for문 연습문제 풀이

문제 원본: [자바-Scanner-배열-for문-연습문제.md](./자바-Scanner-배열-for문-연습문제.md)

---

## 문제 1. 숫자 3개 입력 후 출력

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[3];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("숫자 입력: ");
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        sc.close();
    }
}
```

### 출력 예시

```
숫자 입력: 10
숫자 입력: 20
숫자 입력: 30
10
20
30
```

---

## 문제 2. 합계 구하기

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            System.out.print("숫자 입력: ");
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        System.out.println("합계: " + sum);
        sc.close();
    }
}
```

### 출력 예시

```
숫자 입력: 1
숫자 입력: 2
숫자 입력: 3
숫자 입력: 4
숫자 입력: 5
합계: 15
```

---

## 문제 3. 평균 구하기

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] score = new int[4];
        int sum = 0;

        for (int i = 0; i < score.length; i++) {
            System.out.print("점수 입력: ");
            score[i] = sc.nextInt();
            sum += score[i];
        }

        int avg = sum / score.length;
        System.out.println("평균: " + avg);
        sc.close();
    }
}
```

### 출력 예시

```
점수 입력: 80
점수 입력: 90
점수 입력: 70
점수 입력: 100
평균: 85
```

---

## 문제 4. 최댓값 구하기

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("숫자 입력: ");
            arr[i] = sc.nextInt();
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        System.out.println("최댓값: " + max);
        sc.close();
    }
}
```

### 출력 예시

```
숫자 입력: 3
숫자 입력: 9
숫자 입력: 2
숫자 입력: 7
숫자 입력: 5
최댓값: 9
```

---

## 문제 5. 최솟값 구하기

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("숫자 입력: ");
            arr[i] = sc.nextInt();
        }

        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        System.out.println("최솟값: " + min);
        sc.close();
    }
}
```

### 출력 예시

```
숫자 입력: 12
숫자 입력: 4
숫자 입력: 8
숫자 입력: 15
숫자 입력: 6
최솟값: 4
```

---

## 문제 6. 짝수 개수 세기

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[6];
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            System.out.print("숫자 입력: ");
            arr[i] = sc.nextInt();

            if (arr[i] % 2 == 0) {
                count++;
            }
        }

        System.out.println("짝수 개수: " + count);
        sc.close();
    }
}
```

### 출력 예시

```
숫자 입력: 1
숫자 입력: 2
숫자 입력: 3
숫자 입력: 4
숫자 입력: 5
숫자 입력: 6
짝수 개수: 3
```

---

## 문제 7. 홀수만 출력

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("숫자 입력: ");
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                System.out.println(arr[i]);
            }
        }

        sc.close();
    }
}
```

### 출력 예시

```
숫자 입력: 10
숫자 입력: 11
숫자 입력: 12
숫자 입력: 13
숫자 입력: 14
11
13
```

---

## 문제 8. 거꾸로 출력

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("숫자 입력: ");
            arr[i] = sc.nextInt();
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.println(arr[i]);
        }

        sc.close();
    }
}
```

### 출력 예시

```
숫자 입력: 1
숫자 입력: 2
숫자 입력: 3
숫자 입력: 4
숫자 입력: 5
5
4
3
2
1
```

---

## 문제 9. 특정 값 찾기

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("숫자 입력: ");
            arr[i] = sc.nextInt();
        }

        System.out.print("찾을 숫자: ");
        int target = sc.nextInt();

        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("있음");
        } else {
            System.out.println("없음");
        }

        sc.close();
    }
}
```

### 출력 예시

```
숫자 입력: 10
숫자 입력: 20
숫자 입력: 30
숫자 입력: 40
숫자 입력: 50
찾을 숫자: 30
있음
```

---

## 문제 10. 학생 점수 입력

### 풀이

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("학생 수: ");
        int n = sc.nextInt();

        int[] score = new int[n];
        int sum = 0;

        for (int i = 0; i < score.length; i++) {
            System.out.print("점수 입력: ");
            score[i] = sc.nextInt();
            sum += score[i];
        }

        double avg = (double) sum / score.length;

        System.out.println("합계: " + sum);
        System.out.println("평균: " + avg);
        sc.close();
    }
}
```

### 출력 예시

```
학생 수: 3
점수 입력: 80
점수 입력: 90
점수 입력: 100
합계: 270
평균: 90.0
```
