# Java 한방 정리 — 정답

문제: [Java-한방정리-문제.md](./Java-한방정리-문제.md)  
교안: [Java-한방정리.md](./Java-한방정리.md)

---

# Part 1

## 1번
② `public static void main(String[] args)`

## 2번
② `Hello.java` (대소문자 포함 클래스명과 동일)

## 3번
③ `String` (참조형)

## 4번
①  
`10/3` → `3` (정수 나눗셈), `10%3` → `1`

## 5번 (예시)
기본형은 값을 직접 저장하고, 참조형은 객체의 주소(참조)를 저장한다.

## 6번

```java
int score = 85;
String result = (score >= 60) ? "합격" : "불합격";
System.out.println(result);
```

---

# Part 2

## 7번
②

## 8번

```java
int sum = 0;
for (int i = 1; i <= 100; i++) {
    sum += i;
}
System.out.println(sum);  // 5050
```

## 9번

```java
int score = 82;
if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else if (score >= 70) {
    System.out.println("C");
} else {
    System.out.println("D");
}
// 출력: B
```

## 10번

```java
for (int i = 1; i <= 9; i++) {
    System.out.println("2 * " + i + " = " + (2 * i));
}
```

---

# Part 3

## 11번
② `arr.length`

## 12번
② 0부터 시작

## 13번

```java
int[] scores = {90, 80, 70, 100, 60};
int sum = 0;
for (int s : scores) {
    sum += s;
}
double avg = (double) sum / scores.length;
System.out.println("합계: " + sum);    // 400
System.out.println("평균: " + avg);    // 80.0
```

## 14번

```java
public class AddTest {
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(add(10, 20));  // 30
    }
}
```

## 15번
② 같은 이름, 매개변수를 다르게

---

# Part 4

## 16번
② 클래스 = 설계도, 객체 = 실체

## 17번

```java
public class Student {
    String name;
    int age;

    void introduce() {
        System.out.println("나는 " + name + ", " + age + "살");
    }

    public static void main(String[] args) {
        Student s = new Student();
        s.name = "홍길동";
        s.age = 20;
        s.introduce();
    }
}
```

## 18번
④ 생성자는 `static`이 아니다 (틀리다고 한 문장이 ④)

## 19번

```java
public class Book {
    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public static void main(String[] args) {
        Book b = new Book("자바의 정석", 30000);
        System.out.println(b.getTitle() + " / " + b.getPrice());
    }
}
```

## 20번
② 이 객체 자신

---

# Part 5

## 21번
②

## 22번
② Dog의 sound (동적 바인딩 / 다형성)

## 23번

```java
public interface Payable {
    void pay(int amount);
}

public class CardPay implements Payable {
    @Override
    public void pay(int amount) {
        System.out.println("카드 결제: " + amount);
    }

    public static void main(String[] args) {
        Payable p = new CardPay();
        p.pay(10000);  // 카드 결제: 10000
    }
}
```

(인터페이스와 클래스를 파일로 나눠도 됨)

## 24번
`"abc"`는 숫자가 아니라서 `Integer.parseInt`가 `NumberFormatException`을 발생시키기 때문.

## 25번
② `equals`

---

# Part 6

## 26번
③ 인덱스는 **0**부터이다 (①②④는 맞음)

## 27번

```java
import java.util.ArrayList;

ArrayList<String> list = new ArrayList<>();
list.add("사과");
list.add("바나나");
list.add("포도");

for (String fruit : list) {
    System.out.println(fruit);
}
```

## 28번

```java
import java.util.HashMap;

HashMap<String, Integer> map = new HashMap<>();
map.put("국어", 90);
map.put("수학", 80);
System.out.println(map.get("국어"));  // 90
```

## 29번 (예시 골격)

```java
import java.util.ArrayList;
import java.util.Scanner;

public class StudentNameApp {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1.추가 2.목록 3.삭제 4.종료");
            int menu = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            if (menu == 1) {
                System.out.print("이름: ");
                names.add(sc.nextLine());
            } else if (menu == 2) {
                for (int i = 0; i < names.size(); i++) {
                    System.out.println(i + ": " + names.get(i));
                }
            } else if (menu == 3) {
                System.out.print("삭제 인덱스: ");
                int idx = sc.nextInt();
                if (idx >= 0 && idx < names.size()) {
                    names.remove(idx);
                }
            } else if (menu == 4) {
                break;
            }
        }
        sc.close();
    }
}
```

## 30번 (예시)
JDBC → MyBatis(또는 JPA) → Spring Boot  
(체크리스트: JDBC → MyBatis/JPA → Spring Boot)

---

# 강사 채점 팁

| 문항 | 포인트 |
|------|--------|
| 3, 25 | String 참조형 / equals — 단골 오답 |
| 11 | `length` vs `length()` |
| 18 | 생성자에 static 없다는 것 |
| 22 | 다형성 실행 결과 |
| 29 | nextInt 후 nextLine 버퍼 처리 여부 |

부분 점수: 코드 문항은 **동작 골격**이 있으면 70%까지 부여 가능.
