# HashMap을 활용한 커피 메뉴 관리 예제

---

## 목차

1. [기본 예제: 커피 메뉴 출력](#1-기본-예제-커피-메뉴-출력)
2. [메뉴 가격 조회](#2-메뉴-가격-조회)
3. [메뉴 추가 및 수정](#3-메뉴-추가-및-수정)
4. [가격대별 메뉴 찾기](#4-가격대별-메뉴-찾기)
5. [종합 예제: 커피 주문 시스템](#5-종합-예제-커피-주문-시스템)

---

## 1. 기본 예제: 커피 메뉴 출력

### 1-1. 문제 설명

커피 메뉴와 가격을 HashMap에 저장하고, 모든 메뉴를 출력하는 프로그램을 작성하세요.

### 1-2. 코드

```java
import java.util.HashMap;
import java.util.Map;

public class CoffeeMenu {
    public static void main(String[] args) {
        // HashMap 생성 (메뉴명을 키, 가격을 값으로 저장)
        HashMap<String, Integer> menu = new HashMap<>();
        
        // 커피 메뉴 추가
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        menu.put("에스프레소", 3500);
        menu.put("바닐라라떼", 5500);
        
        // 모든 메뉴 출력
        System.out.println("=== 커피 메뉴 ===");
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            String coffee = entry.getKey();
            int price = entry.getValue();
            System.out.println(coffee + ": " + price + "원");
        }
    }
}
```

### 1-3. 실행 결과

```
=== 커피 메뉴 ===
카푸치노: 5000원
에스프레소: 3500원
바닐라라떼: 5500원
아메리카노: 4000원
카페라떼: 4500원
```

---

## 2. 메뉴 가격 조회

### 2-1. 문제 설명

사용자가 입력한 커피 메뉴의 가격을 조회하는 프로그램을 작성하세요.

### 2-2. 코드

```java
import java.util.HashMap;
import java.util.Scanner;

public class CoffeePriceLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 커피 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        menu.put("에스프레소", 3500);
        menu.put("바닐라라떼", 5500);
        menu.put("카라멜마키아토", 6000);
        
        // 메뉴 가격 조회
        System.out.print("조회할 커피 메뉴를 입력하세요: ");
        String coffeeName = scanner.nextLine();
        
        if (menu.containsKey(coffeeName)) {
            int price = menu.get(coffeeName);
            System.out.println(coffeeName + "의 가격은 " + price + "원입니다.");
        } else {
            System.out.println("해당 메뉴가 없습니다.");
        }
        
        scanner.close();
    }
}
```

### 2-3. 실행 결과

```
조회할 커피 메뉴를 입력하세요: 카페라떼
카페라떼의 가격은 4500원입니다.
```

---

## 3. 메뉴 추가 및 수정

### 3-1. 문제 설명

커피 메뉴를 추가하고, 기존 메뉴의 가격을 수정하는 프로그램을 작성하세요.

### 3-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMenuManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 커피 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        
        System.out.println("=== 초기 메뉴 ===");
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
        }
        
        // 새 메뉴 추가
        System.out.print("\n추가할 메뉴 이름: ");
        String newMenu = scanner.nextLine();
        System.out.print("가격: ");
        int newPrice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
        
        menu.put(newMenu, newPrice);
        System.out.println(newMenu + " 메뉴가 추가되었습니다.");
        
        // 기존 메뉴 가격 수정
        System.out.print("\n수정할 메뉴 이름: ");
        String updateMenu = scanner.nextLine();
        
        if (menu.containsKey(updateMenu)) {
            System.out.print("새 가격: ");
            int updatePrice = scanner.nextInt();
            menu.put(updateMenu, updatePrice);
            System.out.println(updateMenu + "의 가격이 " + updatePrice + "원으로 변경되었습니다.");
        } else {
            System.out.println("해당 메뉴가 없습니다.");
        }
        
        // 최종 메뉴 출력
        System.out.println("\n=== 최종 메뉴 ===");
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
        }
        
        scanner.close();
    }
}
```

### 3-3. 실행 결과

```
=== 초기 메뉴 ===
카푸치노: 5000원
아메리카노: 4000원
카페라떼: 4500원

추가할 메뉴 이름: 바닐라라떼
가격: 5500
바닐라라떼 메뉴가 추가되었습니다.

수정할 메뉴 이름: 아메리카노
새 가격: 4500
아메리카노의 가격이 4500원으로 변경되었습니다.

=== 최종 메뉴 ===
카푸치노: 5000원
바닐라라떼: 5500원
아메리카노: 4500원
카페라떼: 4500원
```

---

## 4. 가격대별 메뉴 찾기

### 4-1. 문제 설명

특정 가격 이하의 커피 메뉴를 찾아 출력하는 프로그램을 작성하세요.

### 4-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeePriceFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 커피 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        menu.put("에스프레소", 3500);
        menu.put("바닐라라떼", 5500);
        menu.put("카라멜마키아토", 6000);
        menu.put("콜드브루", 5500);
        
        System.out.print("최대 가격을 입력하세요: ");
        int maxPrice = scanner.nextInt();
        
        System.out.println("\n=== " + maxPrice + "원 이하 메뉴 ===");
        boolean found = false;
        
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            if (entry.getValue() <= maxPrice) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("해당 가격대의 메뉴가 없습니다.");
        }
        
        scanner.close();
    }
}
```

### 4-3. 실행 결과

```
최대 가격을 입력하세요: 4500

=== 4500원 이하 메뉴 ===
에스프레소: 3500원
아메리카노: 4000원
카페라떼: 4500원
```

---

## 5. 종합 예제: 커피 주문 시스템

### 5-1. 문제 설명

커피 메뉴를 관리하고 주문을 받아 총 금액을 계산하는 프로그램을 작성하세요.

### 5-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeOrderSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 커피 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        menu.put("에스프레소", 3500);
        menu.put("바닐라라떼", 5500);
        menu.put("카라멜마키아토", 6000);
        menu.put("콜드브루", 5500);
        
        // 주문 내역 저장
        HashMap<String, Integer> order = new HashMap<>();
        
        while (true) {
            // 메뉴 출력
            System.out.println("\n=== 커피 메뉴 ===");
            for (Map.Entry<String, Integer> entry : menu.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
            }
            
            System.out.println("\n주문할 메뉴를 입력하세요 (종료: '종료'): ");
            String coffeeName = scanner.nextLine();
            
            if (coffeeName.equals("종료")) {
                break;
            }
            
            // 메뉴 존재 확인
            if (!menu.containsKey(coffeeName)) {
                System.out.println("해당 메뉴가 없습니다. 다시 입력해주세요.");
                continue;
            }
            
            // 수량 입력
            System.out.print("수량을 입력하세요: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기
            
            if (quantity <= 0) {
                System.out.println("수량은 1개 이상이어야 합니다.");
                continue;
            }
            
            // 주문 내역에 추가 (이미 주문한 메뉴면 수량 추가)
            if (order.containsKey(coffeeName)) {
                order.put(coffeeName, order.get(coffeeName) + quantity);
            } else {
                order.put(coffeeName, quantity);
            }
            
            int price = menu.get(coffeeName);
            System.out.println(coffeeName + " " + quantity + "개가 주문되었습니다. (금액: " + (price * quantity) + "원)");
        }
        
        // 주문 내역 및 총 금액 계산
        System.out.println("\n=== 주문 내역 ===");
        int totalAmount = 0;
        
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String coffeeName = entry.getKey();
            int quantity = entry.getValue();
            int price = menu.get(coffeeName);
            int subtotal = price * quantity;
            
            System.out.println(coffeeName + " x " + quantity + " = " + subtotal + "원");
            totalAmount += subtotal;
        }
        
        System.out.println("\n총 금액: " + totalAmount + "원");
        
        scanner.close();
    }
}
```

### 5-3. 실행 결과

```
=== 커피 메뉴 ===
카푸치노: 5000원
에스프레소: 3500원
바닐라라떼: 5500원
아메리카노: 4000원
카페라떼: 4500원
카라멜마키아토: 6000원
콜드브루: 5500원

주문할 메뉴를 입력하세요 (종료: '종료'): 
아메리카노
수량을 입력하세요: 2
아메리카노 2개가 주문되었습니다. (금액: 8000원)

=== 커피 메뉴 ===
카푸치노: 5000원
에스프레소: 3500원
바닐라라떼: 5500원
아메리카노: 4000원
카페라떼: 4500원
카라멜마키아토: 6000원
콜드브루: 5500원

주문할 메뉴를 입력하세요 (종료: '종료'): 
카페라떼
수량을 입력하세요: 1
카페라떼 1개가 주문되었습니다. (금액: 4500원)

=== 커피 메뉴 ===
카푸치노: 5000원
에스프레소: 3500원
바닐라라떼: 5500원
아메리카노: 4000원
카페라떼: 4500원
카라멜마키아토: 6000원
콜드브루: 5500원

주문할 메뉴를 입력하세요 (종료: '종료'): 
종료

=== 주문 내역 ===
아메리카노 x 2 = 8000원
카페라떼 x 1 = 4500원

총 금액: 12500원
```

---

## 추가 예제: 평균 가격 계산

### 코드

```java
import java.util.HashMap;
import java.util.Map;

public class CoffeeAveragePrice {
    public static void main(String[] args) {
        // 커피 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        menu.put("에스프레소", 3500);
        menu.put("바닐라라떼", 5500);
        
        // 평균 가격 계산
        int sum = 0;
        int count = menu.size();
        
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            sum += entry.getValue();
        }
        
        double average = (double) sum / count;
        
        System.out.println("=== 커피 메뉴 ===");
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
        }
        
        System.out.println("\n평균 가격: " + average + "원");
    }
}
```

### 실행 결과

```
=== 커피 메뉴 ===
카푸치노: 5000원
에스프레소: 3500원
바닐라라떼: 5500원
아메리카노: 4000원
카페라떼: 4500원

평균 가격: 4500.0원
```

---

## 추가 예제: 가장 비싼/저렴한 메뉴 찾기

### 코드

```java
import java.util.HashMap;
import java.util.Map;

public class CoffeePriceRange {
    public static void main(String[] args) {
        // 커피 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        menu.put("에스프레소", 3500);
        menu.put("바닐라라떼", 5500);
        menu.put("카라멜마키아토", 6000);
        
        // 가장 비싼 메뉴 찾기
        String expensiveCoffee = null;
        int maxPrice = Integer.MIN_VALUE;
        
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            if (entry.getValue() > maxPrice) {
                maxPrice = entry.getValue();
                expensiveCoffee = entry.getKey();
            }
        }
        
        // 가장 저렴한 메뉴 찾기
        String cheapCoffee = null;
        int minPrice = Integer.MAX_VALUE;
        
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            if (entry.getValue() < minPrice) {
                minPrice = entry.getValue();
                cheapCoffee = entry.getKey();
            }
        }
        
        System.out.println("=== 커피 메뉴 ===");
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
        }
        
        System.out.println("\n가장 비싼 메뉴: " + expensiveCoffee + " (" + maxPrice + "원)");
        System.out.println("가장 저렴한 메뉴: " + cheapCoffee + " (" + minPrice + "원)");
    }
}
```

### 실행 결과

```
=== 커피 메뉴 ===
카푸치노: 5000원
에스프레소: 3500원
바닐라라떼: 5500원
아메리카노: 4000원
카페라떼: 4500원
카라멜마키아토: 6000원

가장 비싼 메뉴: 카라멜마키아토 (6000원)
가장 저렴한 메뉴: 에스프레소 (3500원)
```

---

## 전체 요약

### HashMap을 활용한 커피 메뉴 관리 패턴

```java
// 1. 메뉴 초기화
HashMap<String, Integer> menu = new HashMap<>();
menu.put("메뉴명", 가격);

// 2. 메뉴 조회
if (menu.containsKey("메뉴명")) {
    int price = menu.get("메뉴명");
}

// 3. 메뉴 순회
for (Map.Entry<String, Integer> entry : menu.entrySet()) {
    String name = entry.getKey();
    int price = entry.getValue();
}

// 4. 메뉴 수정
menu.put("메뉴명", 새가격);

// 5. 메뉴 삭제
menu.remove("메뉴명");
```

### 핵심 포인트

1. **키-값 쌍**: 메뉴명(키)과 가격(값)을 쌍으로 저장
2. **entrySet()**: 모든 메뉴를 효율적으로 순회
3. **containsKey()**: 메뉴 존재 여부 확인
4. **get()**: 특정 메뉴의 가격 조회
5. **put()**: 메뉴 추가 또는 가격 수정

### 활용 예시

- ✅ 메뉴 관리 시스템
- ✅ 주문 시스템
- ✅ 가격 조회 시스템
- ✅ 통계 분석 (평균, 최댓값, 최솟값)
- ✅ 필터링 (가격대별 메뉴 찾기)

---

## 연습 문제

1. **메뉴 삭제 기능 추가**: 사용자가 입력한 메뉴를 삭제하는 기능을 추가하세요.

2. **할인 시스템**: 특정 가격 이상의 메뉴에 10% 할인을 적용하는 프로그램을 작성하세요.

3. **카테고리별 관리**: 핫/아이스 커피를 구분하여 관리하는 프로그램을 작성하세요.

4. **인기 메뉴 추적**: 주문 횟수를 추적하여 인기 메뉴를 찾는 프로그램을 작성하세요.
