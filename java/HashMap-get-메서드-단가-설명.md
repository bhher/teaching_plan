# HashMap.get() 메서드와 단가 개념 설명

## 질문: `coffee.menu.get(coffeeName)`이 왜 커피 단가일까요?

---

## 코드 분석

### 1. menu의 구조

```java
Map<String, Integer> menu = new LinkedHashMap<>();
menu.put("Americano", 3000);
menu.put("Latte", 4000);
menu.put("Mocha", 4500);
```

**구조:**
- **키 (Key)**: `String` - 커피 이름 ("Americano", "Latte", ...)
- **값 (Value)**: `Integer` - 가격 (3000, 4000, ...)

**의미:**
- "Americano" → 3000원
- "Latte" → 4000원
- "Mocha" → 4500원

---

### 2. get() 메서드의 동작

```java
int coffeeUnitPrice = coffee.menu.get(coffeeName);
```

**단계별 설명:**

1. **`coffee.menu`**: Coffee 객체의 menu 맵에 접근
2. **`.get(coffeeName)`**: 커피 이름으로 값을 조회
3. **반환값**: Integer (가격)
4. **변수명**: `coffeeUnitPrice` (커피 단가)

**예시:**
```java
String coffeeName = "Americano";
int coffeeUnitPrice = coffee.menu.get(coffeeName);
// coffeeUnitPrice = 3000
```

---

## 왜 "단가"라고 부를까?

### 단가의 의미

**단가 (Unit Price)**: 하나당 가격, 개당 가격

- **커피 단가**: 커피 한 잔당 가격
- **도서 단가**: 도서 한 권당 가격 (또는 1일당 가격)

### 코드에서의 의미

```java
Map<String, Integer> menu;
// menu의 값은 "커피 한 잔당 가격"을 의미
menu.put("Americano", 3000);  // 아메리카노 한 잔 = 3000원
```

**따라서:**
```java
int coffeeUnitPrice = coffee.menu.get(coffeeName);
// coffeeUnitPrice = 커피 한 잔당 가격 = 단가
```

---

## 실제 사용 예시

### 전체 계산 과정

```java
// 1. 커피 이름으로 단가 조회
String coffeeName = "Americano";
int coffeeUnitPrice = coffee.menu.get(coffeeName);  
// coffeeUnitPrice = 3000 (아메리카노 한 잔당 가격)

// 2. 주문 수량
int orderCount = 3;  // 3잔 주문

// 3. 총 금액 계산
int coffeePrice = coffeeUnitPrice * orderCount;
// coffeePrice = 3000 * 3 = 9000원
```

**공식:**
```
총 금액 = 단가 × 수량
coffeePrice = coffeeUnitPrice × orderCount
```

---

## 변수명의 의미

### coffeeUnitPrice

**구성:**
- `coffee`: 커피
- `Unit`: 단위 (하나, 개당)
- `Price`: 가격

**의미:** 커피 한 잔당 가격 = 커피 단가

### 다른 변수명 예시

```java
int coffeeUnitPrice;      // 커피 단가
int coffeePricePerUnit;   // 커피 단위당 가격
int unitPrice;            // 단가
int pricePerCup;          // 잔당 가격
int singlePrice;          // 단일 가격
```

**모두 같은 의미:** 커피 한 잔당 가격

---

## Map.get() 메서드 상세 설명

### 기본 사용법

```java
Map<String, Integer> menu = new HashMap<>();
menu.put("Americano", 3000);

// get() 메서드로 값 조회
Integer price = menu.get("Americano");  // 3000
```

### get() 메서드의 특징

1. **키로 값 조회**: 키를 입력하면 해당 값을 반환
2. **없으면 null**: 키가 없으면 `null` 반환
3. **타입 반환**: Map의 Value 타입 반환 (여기서는 Integer)

### 예시

```java
Map<String, Integer> menu = new HashMap<>();
menu.put("Americano", 3000);
menu.put("Latte", 4000);

// 정상적인 경우
int price1 = menu.get("Americano");  // 3000
int price2 = menu.get("Latte");      // 4000

// 키가 없는 경우
Integer price3 = menu.get("Mocha");  // null
```

---

## 단가 vs 총액 비교

### 단가 (Unit Price)

```java
int coffeeUnitPrice = coffee.menu.get(coffeeName);
// coffeeUnitPrice = 3000 (한 잔당 가격)
```

**의미:** 하나당 가격

### 총액 (Total Price)

```java
int coffeePrice = coffeeUnitPrice * orderCount;
// coffeePrice = 3000 * 3 = 9000 (3잔의 총 가격)
```

**의미:** 전체 가격

### 비교 표

| 항목 | 단가 | 총액 |
|------|------|------|
| **의미** | 하나당 가격 | 전체 가격 |
| **계산** | `menu.get(coffeeName)` | `단가 × 수량` |
| **예시** | 3000원 (1잔) | 9000원 (3잔) |
| **변수명** | `coffeeUnitPrice` | `coffeePrice` |

---

## 실제 코드에서의 흐름

### CoffeeService.java의 totalOrder() 메서드

```java
for(Map.Entry<String,Integer> order : customer.getCoffeeOrder().entrySet()){
    String coffeeName = order.getKey();           // 커피 이름
    int orderCount = order.getValue();            // 주문 수량
    
    // 여기서 단가를 가져옴
    int coffeeUnitPrice = coffee.menu.get(coffeeName);
    // coffeeUnitPrice = 3000 (아메리카노 한 잔당 가격)
    
    // 총액 계산
    coffeePrice = coffeeUnitPrice * orderCount;
    // coffeePrice = 3000 * 3 = 9000원 (3잔의 총 가격)
    
    totalMoney = totalMoney + coffeePrice;
}
```

**단계별 설명:**

1. **커피 이름 추출**: `coffeeName = "Americano"`
2. **주문 수량 추출**: `orderCount = 3`
3. **단가 조회**: `coffeeUnitPrice = menu.get("Americano")` → 3000
4. **총액 계산**: `coffeePrice = 3000 * 3` → 9000
5. **총 금액 누적**: `totalMoney += 9000`

---

## 왜 menu에 저장된 값이 단가인가?

### menu의 초기화 코드

```java
menu.put("Americano", 3000);
menu.put("Latte", 4000);
menu.put("Mocha", 4500);
```

**의미:**
- "Americano" → 3000원 (아메리카노 한 잔당 가격)
- "Latte" → 4000원 (라떼 한 잔당 가격)
- "Mocha" → 4500원 (모카 한 잔당 가격)

**이유:**
- 메뉴에는 **기본 단가**가 저장됨
- 수량은 주문 시 입력받음
- 총액은 단가 × 수량으로 계산

---

## 다른 예시로 이해하기

### 도서 대여 시스템

```java
Map<String, Integer> menu;
menu.put("자바의 정석", 1000);  // 1일당 대여 가격

// 대여일수는 별도로 입력받음
int rentalDays = 5;

// 총 대여료 계산
int unitPrice = menu.get("자바의 정석");  // 1000원 (1일당)
int totalPrice = unitPrice * rentalDays;  // 1000 * 5 = 5000원
```

**의미:**
- `menu.get()`: 1일당 대여 가격 (단가)
- `rentalDays`: 대여일수
- `totalPrice`: 총 대여료

---

## 요약

### `coffee.menu.get(coffeeName)`이 단가인 이유

1. **menu의 구조**
   - 키: 커피 이름
   - 값: 커피 한 잔당 가격

2. **get() 메서드**
   - 커피 이름으로 가격 조회
   - 반환값: 커피 한 잔당 가격

3. **단가의 정의**
   - 하나당 가격
   - 개당 가격
   - 여기서는 "커피 한 잔당 가격"

4. **사용 목적**
   - 총액 계산을 위해 필요
   - 총액 = 단가 × 수량

---

## 핵심 정리

```java
// menu에 저장된 값
menu.put("Americano", 3000);
// 의미: 아메리카노 한 잔 = 3000원 (단가)

// get()으로 조회
int coffeeUnitPrice = coffee.menu.get("Americano");
// coffeeUnitPrice = 3000 (단가)

// 총액 계산
int totalPrice = coffeeUnitPrice * 3;  // 3000 * 3 = 9000원
```

**결론:**
- `menu.get(coffeeName)`은 커피 한 잔당 가격을 반환
- 이것이 바로 "단가"입니다!
- 총액을 계산할 때 단가 × 수량으로 사용됩니다.

---

## 추가 학습

### 단가 관련 용어

- **단가 (Unit Price)**: 하나당 가격
- **총액 (Total Price)**: 전체 가격
- **수량 (Quantity)**: 개수
- **공식**: 총액 = 단가 × 수량

### 코드에서의 표현

```java
// 단가
int unitPrice = menu.get(itemName);

// 수량
int quantity = orderCount;

// 총액
int totalPrice = unitPrice * quantity;
```

**이 패턴은 모든 상품 관리 시스템에서 사용됩니다!**
