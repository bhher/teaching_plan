# HashMap을 활용한 도서 관리 예제

---

## 목차

1. [기본 예제: 도서 목록 출력](#1-기본-예제-도서-목록-출력)
2. [도서 가격 조회](#2-도서-가격-조회)
3. [도서 추가 및 수정](#3-도서-추가-및-수정)
4. [가격대별 도서 찾기](#4-가격대별-도서-찾기)
5. [종합 예제: 도서 구매 시스템](#5-종합-예제-도서-구매-시스템)

---

## 1. 기본 예제: 도서 목록 출력

### 1-1. 문제 설명

도서명과 가격을 HashMap에 저장하고, 모든 도서를 출력하는 프로그램을 작성하세요.

### 1-2. 코드

```java
import java.util.HashMap;
import java.util.Map;

public class BookList {
    public static void main(String[] args) {
        // HashMap 생성 (도서명을 키, 가격을 값으로 저장)
        HashMap<String, Integer> books = new HashMap<>();
        
        // 도서 정보 추가
        books.put("자바의 정석", 30000);
        books.put("이펙티브 자바", 35000);
        books.put("코드 컴플리트", 40000);
        books.put("클린 코드", 28000);
        books.put("리팩토링", 32000);
        
        // 모든 도서 출력
        System.out.println("=== 도서 목록 ===");
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            String bookName = entry.getKey();
            int price = entry.getValue();
            System.out.println(bookName + ": " + price + "원");
        }
    }
}
```

### 1-3. 실행 결과

```
=== 도서 목록 ===
리팩토링: 32000원
코드 컴플리트: 40000원
클린 코드: 28000원
자바의 정석: 30000원
이펙티브 자바: 35000원
```

---

## 2. 도서 가격 조회

### 2-1. 문제 설명

사용자가 입력한 도서의 가격을 조회하는 프로그램을 작성하세요.

### 2-2. 코드

```java
import java.util.HashMap;
import java.util.Scanner;

public class BookPriceLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 도서 목록 초기화
        HashMap<String, Integer> books = new HashMap<>();
        books.put("자바의 정석", 30000);
        books.put("이펙티브 자바", 35000);
        books.put("코드 컴플리트", 40000);
        books.put("클린 코드", 28000);
        books.put("리팩토링", 32000);
        books.put("도메인 주도 설계", 38000);
        
        // 도서 가격 조회
        System.out.print("조회할 도서명을 입력하세요: ");
        String bookName = scanner.nextLine();
        
        if (books.containsKey(bookName)) {
            int price = books.get(bookName);
            System.out.println(bookName + "의 가격은 " + price + "원입니다.");
        } else {
            System.out.println("해당 도서가 없습니다.");
        }
        
        scanner.close();
    }
}
```

### 2-3. 실행 결과

```
조회할 도서명을 입력하세요: 자바의 정석
자바의 정석의 가격은 30000원입니다.
```

---

## 3. 도서 추가 및 수정

### 3-1. 문제 설명

도서를 추가하고, 기존 도서의 가격을 수정하는 프로그램을 작성하세요.

### 3-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 도서 목록 초기화
        HashMap<String, Integer> books = new HashMap<>();
        books.put("자바의 정석", 30000);
        books.put("이펙티브 자바", 35000);
        books.put("코드 컴플리트", 40000);
        
        System.out.println("=== 초기 도서 목록 ===");
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
        }
        
        // 새 도서 추가
        System.out.print("\n추가할 도서명: ");
        String newBook = scanner.nextLine();
        System.out.print("가격: ");
        int newPrice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
        
        books.put(newBook, newPrice);
        System.out.println(newBook + " 도서가 추가되었습니다.");
        
        // 기존 도서 가격 수정
        System.out.print("\n수정할 도서명: ");
        String updateBook = scanner.nextLine();
        
        if (books.containsKey(updateBook)) {
            System.out.print("새 가격: ");
            int updatePrice = scanner.nextInt();
            books.put(updateBook, updatePrice);
            System.out.println(updateBook + "의 가격이 " + updatePrice + "원으로 변경되었습니다.");
        } else {
            System.out.println("해당 도서가 없습니다.");
        }
        
        // 최종 도서 목록 출력
        System.out.println("\n=== 최종 도서 목록 ===");
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
        }
        
        scanner.close();
    }
}
```

### 3-3. 실행 결과

```
=== 초기 도서 목록 ===
코드 컴플리트: 40000원
자바의 정석: 30000원
이펙티브 자바: 35000원

추가할 도서명: 클린 코드
가격: 28000
클린 코드 도서가 추가되었습니다.

수정할 도서명: 자바의 정석
새 가격: 32000
자바의 정석의 가격이 32000원으로 변경되었습니다.

=== 최종 도서 목록 ===
코드 컴플리트: 40000원
클린 코드: 28000원
자바의 정석: 32000원
이펙티브 자바: 35000원
```

---

## 4. 가격대별 도서 찾기

### 4-1. 문제 설명

특정 가격 이하의 도서를 찾아 출력하는 프로그램을 작성하세요.

### 4-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookPriceFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 도서 목록 초기화
        HashMap<String, Integer> books = new HashMap<>();
        books.put("자바의 정석", 30000);
        books.put("이펙티브 자바", 35000);
        books.put("코드 컴플리트", 40000);
        books.put("클린 코드", 28000);
        books.put("리팩토링", 32000);
        books.put("도메인 주도 설계", 38000);
        books.put("테스트 주도 개발", 33000);
        
        System.out.print("최대 가격을 입력하세요: ");
        int maxPrice = scanner.nextInt();
        
        System.out.println("\n=== " + maxPrice + "원 이하 도서 ===");
        boolean found = false;
        
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            if (entry.getValue() <= maxPrice) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("해당 가격대의 도서가 없습니다.");
        }
        
        scanner.close();
    }
}
```

### 4-3. 실행 결과

```
최대 가격을 입력하세요: 32000

=== 32000원 이하 도서 ===
클린 코드: 28000원
자바의 정석: 30000원
리팩토링: 32000원
```

---

## 5. 종합 예제: 도서 구매 시스템

### 5-1. 문제 설명

도서 목록을 관리하고 구매를 받아 총 금액을 계산하는 프로그램을 작성하세요.

### 5-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookPurchaseSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 도서 목록 초기화
        HashMap<String, Integer> books = new HashMap<>();
        books.put("자바의 정석", 30000);
        books.put("이펙티브 자바", 35000);
        books.put("코드 컴플리트", 40000);
        books.put("클린 코드", 28000);
        books.put("리팩토링", 32000);
        books.put("도메인 주도 설계", 38000);
        books.put("테스트 주도 개발", 33000);
        
        // 구매 내역 저장
        HashMap<String, Integer> cart = new HashMap<>();
        
        while (true) {
            // 도서 목록 출력
            System.out.println("\n=== 도서 목록 ===");
            for (Map.Entry<String, Integer> entry : books.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
            }
            
            System.out.println("\n구매할 도서명을 입력하세요 (종료: '종료'): ");
            String bookName = scanner.nextLine();
            
            if (bookName.equals("종료")) {
                break;
            }
            
            // 도서 존재 확인
            if (!books.containsKey(bookName)) {
                System.out.println("해당 도서가 없습니다. 다시 입력해주세요.");
                continue;
            }
            
            // 수량 입력
            System.out.print("수량을 입력하세요: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기
            
            if (quantity <= 0) {
                System.out.println("수량은 1권 이상이어야 합니다.");
                continue;
            }
            
            // 구매 내역에 추가 (이미 구매한 도서면 수량 추가)
            if (cart.containsKey(bookName)) {
                cart.put(bookName, cart.get(bookName) + quantity);
            } else {
                cart.put(bookName, quantity);
            }
            
            int price = books.get(bookName);
            System.out.println(bookName + " " + quantity + "권이 장바구니에 추가되었습니다. (금액: " + (price * quantity) + "원)");
        }
        
        // 구매 내역 및 총 금액 계산
        System.out.println("\n=== 구매 내역 ===");
        int totalAmount = 0;
        
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String bookName = entry.getKey();
            int quantity = entry.getValue();
            int price = books.get(bookName);
            int subtotal = price * quantity;
            
            System.out.println(bookName + " x " + quantity + "권 = " + subtotal + "원");
            totalAmount += subtotal;
        }
        
        System.out.println("\n총 금액: " + totalAmount + "원");
        
        scanner.close();
    }
}
```

### 5-3. 실행 결과

```
=== 도서 목록 ===
리팩토링: 32000원
코드 컴플리트: 40000원
클린 코드: 28000원
자바의 정석: 30000원
이펙티브 자바: 35000원
도메인 주도 설계: 38000원
테스트 주도 개발: 33000원

구매할 도서명을 입력하세요 (종료: '종료'): 
자바의 정석
수량을 입력하세요: 2
자바의 정석 2권이 장바구니에 추가되었습니다. (금액: 60000원)

=== 도서 목록 ===
리팩토링: 32000원
코드 컴플리트: 40000원
클린 코드: 28000원
자바의 정석: 30000원
이펙티브 자바: 35000원
도메인 주도 설계: 38000원
테스트 주도 개발: 33000원

구매할 도서명을 입력하세요 (종료: '종료'): 
클린 코드
수량을 입력하세요: 1
클린 코드 1권이 장바구니에 추가되었습니다. (금액: 28000원)

=== 도서 목록 ===
리팩토링: 32000원
코드 컴플리트: 40000원
클린 코드: 28000원
자바의 정석: 30000원
이펙티브 자바: 35000원
도메인 주도 설계: 38000원
테스트 주도 개발: 33000원

구매할 도서명을 입력하세요 (종료: '종료'): 
종료

=== 구매 내역 ===
자바의 정석 x 2권 = 60000원
클린 코드 x 1권 = 28000원

총 금액: 88000원
```

---

## 추가 예제: 평균 가격 계산

### 코드

```java
import java.util.HashMap;
import java.util.Map;

public class BookAveragePrice {
    public static void main(String[] args) {
        // 도서 목록 초기화
        HashMap<String, Integer> books = new HashMap<>();
        books.put("자바의 정석", 30000);
        books.put("이펙티브 자바", 35000);
        books.put("코드 컴플리트", 40000);
        books.put("클린 코드", 28000);
        books.put("리팩토링", 32000);
        
        // 평균 가격 계산
        int sum = 0;
        int count = books.size();
        
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            sum += entry.getValue();
        }
        
        double average = (double) sum / count;
        
        System.out.println("=== 도서 목록 ===");
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
        }
        
        System.out.println("\n평균 가격: " + average + "원");
    }
}
```

### 실행 결과

```
=== 도서 목록 ===
리팩토링: 32000원
코드 컴플리트: 40000원
클린 코드: 28000원
자바의 정석: 30000원
이펙티브 자바: 35000원

평균 가격: 33000.0원
```

---

## 추가 예제: 가장 비싼/저렴한 도서 찾기

### 코드

```java
import java.util.HashMap;
import java.util.Map;

public class BookPriceRange {
    public static void main(String[] args) {
        // 도서 목록 초기화
        HashMap<String, Integer> books = new HashMap<>();
        books.put("자바의 정석", 30000);
        books.put("이펙티브 자바", 35000);
        books.put("코드 컴플리트", 40000);
        books.put("클린 코드", 28000);
        books.put("리팩토링", 32000);
        books.put("도메인 주도 설계", 38000);
        
        // 가장 비싼 도서 찾기
        String expensiveBook = null;
        int maxPrice = Integer.MIN_VALUE;
        
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            if (entry.getValue() > maxPrice) {
                maxPrice = entry.getValue();
                expensiveBook = entry.getKey();
            }
        }
        
        // 가장 저렴한 도서 찾기
        String cheapBook = null;
        int minPrice = Integer.MAX_VALUE;
        
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            if (entry.getValue() < minPrice) {
                minPrice = entry.getValue();
                cheapBook = entry.getKey();
            }
        }
        
        System.out.println("=== 도서 목록 ===");
        for (Map.Entry<String, Integer> entry : books.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
        }
        
        System.out.println("\n가장 비싼 도서: " + expensiveBook + " (" + maxPrice + "원)");
        System.out.println("가장 저렴한 도서: " + cheapBook + " (" + minPrice + "원)");
    }
}
```

### 실행 결과

```
=== 도서 목록 ===
리팩토링: 32000원
코드 컴플리트: 40000원
클린 코드: 28000원
자바의 정석: 30000원
이펙티브 자바: 35000원
도메인 주도 설계: 38000원

가장 비싼 도서: 코드 컴플리트 (40000원)
가장 저렴한 도서: 클린 코드 (28000원)
```

---

## 전체 요약

### HashMap을 활용한 도서 관리 패턴

```java
// 1. 도서 목록 초기화
HashMap<String, Integer> books = new HashMap<>();
books.put("도서명", 가격);

// 2. 도서 조회
if (books.containsKey("도서명")) {
    int price = books.get("도서명");
}

// 3. 도서 순회
for (Map.Entry<String, Integer> entry : books.entrySet()) {
    String name = entry.getKey();
    int price = entry.getValue();
}

// 4. 도서 수정
books.put("도서명", 새가격);

// 5. 도서 삭제
books.remove("도서명");
```

### 핵심 포인트

1. **키-값 쌍**: 도서명(키)과 가격(값)을 쌍으로 저장
2. **entrySet()**: 모든 도서를 효율적으로 순회
3. **containsKey()**: 도서 존재 여부 확인
4. **get()**: 특정 도서의 가격 조회
5. **put()**: 도서 추가 또는 가격 수정

### 활용 예시

- ✅ 도서 관리 시스템
- ✅ 온라인 서점 시스템
- ✅ 가격 조회 시스템
- ✅ 통계 분석 (평균, 최댓값, 최솟값)
- ✅ 필터링 (가격대별 도서 찾기)

---

## 연습 문제

1. **도서 삭제 기능 추가**: 사용자가 입력한 도서를 삭제하는 기능을 추가하세요.

2. **할인 시스템**: 특정 가격 이상의 도서에 15% 할인을 적용하는 프로그램을 작성하세요.

3. **카테고리별 관리**: 프로그래밍/소설/자기계발 등 카테고리를 구분하여 관리하는 프로그램을 작성하세요.

4. **베스트셀러 추적**: 판매량을 추적하여 베스트셀러를 찾는 프로그램을 작성하세요.
