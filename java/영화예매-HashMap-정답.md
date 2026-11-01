# 영화 예매 시스템 (HashMap) - 정답

문제: [영화예매-HashMap-문제.md](./영화예매-HashMap-문제.md)

---

## MovieTicket4.java

```java
package a0123.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieTicket4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 영화 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("어벤져스", 15000);
        menu.put("기생충", 12000);
        menu.put("범죄도시", 13000);
        menu.put("인터스텔라", 14000);
        menu.put("타이타닉", 12000);
        menu.put("겨울왕국", 11000);
        menu.put("오펜하이머", 15000);

        // 예매 내역 저장
        HashMap<String, Integer> order = new HashMap<>();

        while (true) {
            // 메뉴 출력
            System.out.println("\n=== 영화 메뉴 ===");
            for (Map.Entry<String, Integer> entry : menu.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
            }

            System.out.print("\n주문할 영화를 입력하세요 (종료: '종료'): ");
            String movieName = scanner.nextLine();

            if (movieName.equals("종료")) {
                break;
            }

            if (!menu.containsKey(movieName)) {
                System.out.println("해당 영화가 없습니다. 다시 입력해 주세요");
                continue;
            }

            // 매수 입력
            System.out.print("매수를 입력하세요 : ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            if (quantity <= 0) {
                System.out.println("매수는 1개 이상이어야 합니다.");
                continue;
            }

            // 예매 내역에 추가 (이미 예매한 영화면 매수 누적)
            if (order.containsKey(movieName)) {
                order.put(movieName, order.get(movieName) + quantity);
            } else {
                order.put(movieName, quantity);
            }

            int price = menu.get(movieName);
            System.out.println(movieName + " " + quantity + "매가 예매되었습니다. (금액: "
                    + (price * quantity) + "원)");
        }

        // 예매 내역 및 총 금액 계산
        System.out.println("\n=== 예매 내역 ===");
        int totalAmount = 0;
        int totalQuantity = 0;

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String movieName = entry.getKey();
            int quantity = entry.getValue();
            int price = menu.get(movieName);
            int subtotal = price * quantity;

            System.out.println(movieName + " X " + quantity + " = " + subtotal);
            totalAmount += subtotal;
            totalQuantity += quantity;
        }

        System.out.println("\n총 금액 : " + totalAmount + "원");

        if (totalQuantity > 0) {
            double averageAmount = (double) totalAmount / totalQuantity;
            System.out.printf("평균 금액 : %.2f원\n", averageAmount);
        }

        scanner.close();
    }
}
```

---

## 커피 → 영화 대응표

| 커피 주문 | 영화 예매 |
|-----------|-----------|
| `coffeeName` | `movieName` |
| `CoffeeMenu4` | `MovieTicket4` |
| `수량` | `매수` |
| `커피 메뉴` | `영화 메뉴` |
| `주문 내역` | `예매 내역` |
| `개` | `매` |

---

## 핵심 로직

```java
// 같은 영화 재주문 → 매수 누적
if (order.containsKey(movieName)) {
    order.put(movieName, order.get(movieName) + quantity);
} else {
    order.put(movieName, quantity);
}

// 평균 = 총 금액 / 총 매수
double averageAmount = (double) totalAmount / totalQuantity;
```
