# CoffeeService 클래스 완전 정리

커피 주문 프로그램의 **핵심 비즈니스 로직**을 담당하는 클래스입니다.

---

## 1. 전체 구조

```text
CoffeeImpl (main)
        │
        ▼
CoffeeService.start()
        │
        ├── coffee.getMenu()   // 메뉴 생성 · 출력
        ├── order()            // 주문 받기
        ├── totalOrder()       // 주문 내역(영수증) 출력
        ├── payment()          // 결제
        └── end()              // 음료 제공
```

| 클래스 | 역할 |
|--------|------|
| `CoffeeImpl` | `main` — 프로그램 시작 |
| `CoffeeService` | 주문 · 결제 흐름 전체 |
| `Coffee` | 싱글톤 메뉴 관리 |
| `Customer` | 고객 번호 · 잔액 · 주문 내역 |

---

## 2. 멤버 변수

### 2-1. `reOrder`

```java
private boolean reOrder = false;
```

**재주문 여부**

| 상태 | 의미 |
|------|------|
| `false` | 첫 주문 |
| `true` | 추가 주문 중 |

```text
아메리카노 2잔
      ↓
추가주문 (Y)
      ↓
reOrder = true
```

---

### 2-2. `orderNum`

```java
private int orderNum = 1;
```

**주문(고객) 번호**

| 고객 | 번호 |
|------|------|
| 처음 고객 | 1번 |
| 다음 고객 | 2번 |

결제 성공 후 `orderNum++` 로 증가합니다.

---

### 2-3. `orderList`

```java
Map<String, Integer> orderList;
```

**주문 목록** — 커피 이름 → 잔 수

예: 아메리카노 2잔, 카페라떼 1잔

| Key | Value |
|-----|-------|
| 아메리카노 | 2 |
| 카페라떼 | 1 |

---

## 3. 생성자

```java
public CoffeeService() {
    orderList = new LinkedHashMap<>();
}
```

객체가 만들어질 때 **주문용 Map**을 생성합니다.

### 왜 `LinkedHashMap`인가?

**입력한 순서대로** 출력하기 위해서입니다.

```text
1. 아메리카노
2. 카페모카
3. 카페라떼
```

`HashMap`은 순서가 보장되지 않고, `LinkedHashMap`은 넣은 순서를 유지합니다.

---

## 4. Coffee 객체 (싱글톤)

```java
Coffee coffee = Coffee.getInstance();
```

`Coffee`는 **싱글톤**입니다.

- `new Coffee()` ❌ (생성자가 private)
- `Coffee.getInstance()` ✅ — 프로그램 전체에서 **객체 하나** 공유

메뉴 데이터(`coffeeList`, `menu`)를 한곳에서 관리합니다.

---

## 5. `start()` — 프로그램 시작

```java
public void start()
```

### 실행 순서

```text
프로그램 시작
      │
      ▼
인사 메시지
      │
      ▼
Customer 생성
      │
      ▼
메뉴 출력 (coffee.getMenu())
      │
      ▼
주문 (order())
      │
      ▼
주문 내역 (totalOrder())
      │
      ▼
결제 (payment)  ← totalOrder 안에서 호출
      │
      ▼
2초 대기 (Thread.sleep)
      │
      ▼
음료 제공 (end())
```

### 주요 코드

```java
customer = new Customer(orderNum);  // 예: 1번 고객
coffee.getMenu();                   // 메뉴 출력
order();                            // 주문 받기
totalOrder(customer);               // 영수증 + 결제
// sleep 후
end();                              // 음료 나옴
```

---

## 6. `order()` — 주문 받기 (핵심)

```java
private void order()
```

### 실행 흐름

```text
메뉴 (이미 출력됨)
      │
번호 입력
      │
커피 선택
      │
수량 입력
      │
Map에 저장
      │
추가 주문? (addOrder)
```

### 6-1. 번호 입력

```java
String choice = sc.next();
// 예: "3"
```

### 6-2. 숫자로 변환

```java
int choiceNum = Integer.parseInt(choice.substring(0, 1));
// "3" → 3
```

`0` 이면 주문 취소 (`System.exit(0)`).

### 6-3. 커피 이름 가져오기

```java
String coffeeName = coffee.coffeeList.get(choiceNum - 1);
```

| 입력 | index | 커피 |
|------|-------|------|
| 1 | 0 | 아메리카노 |
| 2 | 1 | 카푸치노 |
| 3 | 2 | 아이스 아메리카노 |

사용자가 **3** → `index = 2` → 아이스 아메리카노

### 6-4. 수량 입력

```java
int orderCount = sc.nextInt();
// 예: 2 → 2잔
```

---

## 7. 재주문 처리

```java
if (reOrder) { ... }
```

이미 한 번 주문한 뒤 **추가 주문**일 때 동작합니다.

### 예시

```text
처음: 아메리카노 2잔
추가: 아메리카노 3잔
  → 2 + 3 = 5잔으로 누적
```

### 기존 for문 방식의 문제

```java
for (String coff : orderList.keySet()) {
    if (coff.equals(coffeeName)) {
        // 누적
    } else {
        orderList.put(coffeeName, orderCount);
        break;
    }
}
```

여러 종류를 담은 뒤 다른 커피를 추가할 때 **논리 오류**가 날 수 있습니다.

### 권장 작성법

```java
if (orderList.containsKey(coffeeName)) {
    orderList.put(
        coffeeName,
        orderList.get(coffeeName) + orderCount
    );
} else {
    orderList.put(coffeeName, orderCount);
}
```

또는 한 줄로:

```java
orderList.put(
    coffeeName,
    orderList.getOrDefault(coffeeName, 0) + orderCount
);
```

반복문 없이 **같은 커피면 누적, 없으면 추가**합니다.

---

## 8. Customer에 주문 저장

```java
customer.setCoffeeOrder(orderList);
```

`Customer` 객체 안에 현재 주문 Map이 들어갑니다.

```text
아메리카노 → 2
카페라떼   → 1
```

---

## 9. `addOrder()` — 추가 주문

```java
addOrder();
```

```text
주문을 계속 하시겠습니까?
예(Y) / 아니오(N)
```

| 입력 | 동작 |
|------|------|
| 예 / Y / y | `reOrder = true` → 메뉴 다시 → `order()` 재호출 |
| 아니오 / N / n | 추가 주문 종료 → `totalOrder`로 진행 |

`order()` → `addOrder()` → (Y이면) 다시 `order()`  
→ **재귀 호출** 형태입니다.

---

## 10. `totalOrder()` — 영수증

각 주문에 대해:

```text
금액 = 메뉴 단가 × 잔 수
예) 아메리카노 2잔 → 2000 × 2 = 4000
```

`totalMoney`에 계속 합산한 뒤 출력하고, `payment(totalMoney)`를 호출합니다.

### DecimalFormat

```java
DecimalFormat f = new DecimalFormat("###,000원");
// 5000 → "5,000원"
```

---

## 11. `payment()` — 결제

```java
int payResult = customer.getMoney() - totalMoney;
```

### 예시

| 항목 | 금액 |
|------|------|
| 카드(잔액) | 10,000 |
| 주문 | 7,000 |
| 남은 금액 | 3,000 |

```text
잔액 부족 (payResult < 0)
      → 주문 실패 메시지

잔액 충분
      → 결제 완료, 잔액 갱신, orderNum++
```

결제 중 `Thread.sleep(2500)` 으로 잠시 대기합니다.

---

## 12. `end()` — 음료 제공

2초 대기 후:

```text
○번 고객님 주문하신 음료 나왔습니다.
 [1] 아메리카노     :  2잔
 ...
```

---

## 13. 전체 실행 흐름 (한눈에)

```text
CoffeeImpl.main()
        │
        ▼
CoffeeService.start()
        │
        ▼
Customer 생성 (orderNum)
        │
        ▼
Coffee.getMenu()
        │
        ▼
order()  ←──┐
        │    │ (추가 주문 Y)
        ▼    │
addOrder() ─┘
        │ (N)
        ▼
totalOrder()
        │
        ▼
payment()
        │
        ▼
2초 대기
        │
        ▼
end()
```

---

## 14. 메서드 요약표

| 메서드 | 역할 |
|--------|------|
| `start()` | 전체 흐름 시작 |
| `order()` | 번호·수량 입력, Map 저장 |
| `addOrder()` | 추가 주문 Y/N |
| `totalOrder()` | 영수증 · 합계 |
| `payment()` | 잔액 확인 · 결제 |
| `end()` | 음료 준비 완료 안내 |

---

## 15. 수업에서 꼭 기억할 점

1. **비즈니스 로직의 중심**은 `CoffeeService`
2. **메뉴**는 싱글톤 `Coffee`, **고객**은 `Customer`
3. 주문은 **`LinkedHashMap`** 에 순서대로 저장
4. 재주문은 `containsKey` / `getOrDefault` 가 for문보다 안전
5. `addOrder` → `order` 재호출로 **추가 주문 루프** 구현

---

## 관련 자료

- [README.md](./README.md)
- [커피주문시스템-고급버전-해설.md](../../커피주문시스템-고급버전-해설.md)
- 코드: `CoffeeService.java`, `Coffee.java`, `Customer.java`, `CoffeeImpl.java`
