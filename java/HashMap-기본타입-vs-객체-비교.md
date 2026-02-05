# HashMap: 기본 타입 vs 객체 저장 비교

## 두 가지 방법

### 방법 1: 기본 타입 저장 (Integer)

```java
Map<String, Integer> menu = new HashMap<>();
menu.put("Americano", 3000);
menu.put("Latte", 4000);
```

### 방법 2: 객체 저장 (Coffee)

```java
Map<String, Coffee> menuMap = new HashMap<>();
menuMap.put("Americano", new Coffee("Americano", 3000));
menuMap.put("Latte", new Coffee("Latte", 4000));
```

---

## 핵심 차이점

### 1. 저장되는 데이터 타입

| 방법 | 저장 타입 | 예시 |
|------|----------|------|
| **방법 1** | `Integer` (기본 타입 래퍼 클래스) | `3000` (숫자만) |
| **방법 2** | `Coffee` (사용자 정의 객체) | `Coffee 객체` (이름, 가격 등 포함) |

**비유:**
- 방법 1: 이름표에 가격만 적어둠
- 방법 2: 이름표에 전체 정보 카드가 붙어있음

---

## 상세 비교

### 방법 1: `Map<String, Integer>` - 기본 타입 저장

#### 코드 예시

```java
Map<String, Integer> menu = new HashMap<>();
menu.put("Americano", 3000);
menu.put("Latte", 4000);

// 사용 방법
int price = menu.get("Americano");  // 3000
System.out.println("Americano - " + menu.get("Americano") + "원");
```

#### 특징

**장점:**
- ✅ **간단함**: 코드가 짧고 이해하기 쉬움
- ✅ **빠름**: 메모리 사용량이 적음
- ✅ **직접 접근**: 가격을 바로 가져올 수 있음
- ✅ **학습용으로 적합**: 초보자가 이해하기 쉬움

**단점:**
- ❌ **제한적**: 가격 정보만 저장 가능
- ❌ **확장 어려움**: 나중에 다른 정보 추가 시 구조 변경 필요
- ❌ **타입 안정성 낮음**: 가격만 저장하므로 다른 정보와 혼동 가능
- ❌ **재사용 어려움**: 다른 곳에서 사용하기 어려움

#### 사용 예시

```java
// 메뉴 출력
for(String coffee : menu.keySet()) {
    System.out.println(coffee + " - " + menu.get(coffee) + "원");
}

// 가격 조회
int price = menu.get("Latte");  // 4000
```

---

### 방법 2: `Map<String, Coffee>` - 객체 저장

#### 코드 예시

```java
Map<String, Coffee> menuMap = new HashMap<>();
menuMap.put("Americano", new Coffee("Americano", 3000));
menuMap.put("Latte", new Coffee("Latte", 4000));

// 사용 방법
Coffee coffee = menuMap.get("Americano");
int price = coffee.getPrice();  // 3000
String name = coffee.getName();  // "Americano"
System.out.println(coffee);  // toString() 자동 호출
```

#### 특징

**장점:**
- ✅ **확장성**: 나중에 정보 추가가 쉬움 (설명, 카테고리, 칼로리 등)
- ✅ **객체지향적**: 데이터와 메서드를 함께 관리
- ✅ **타입 안정성**: Coffee 객체만 저장 가능
- ✅ **재사용성**: Coffee 클래스를 다른 곳에서도 사용 가능
- ✅ **유지보수**: 관련 정보가 한 곳에 모여있어 관리가 쉬움
- ✅ **메서드 활용**: toString(), equals(), hashCode() 등 활용 가능

**단점:**
- ❌ **복잡함**: 코드가 더 길고 복잡함
- ❌ **메모리**: 객체 생성으로 인한 메모리 사용 증가
- ❌ **학습 곡선**: 객체지향 개념 이해 필요

#### 사용 예시

```java
// 메뉴 출력 (toString() 활용)
for(Coffee coffee : menuMap.values()) {
    System.out.println(coffee);  // "Americano - 3000원"
}

// 가격 조회
Coffee coffee = menuMap.get("Latte");
int price = coffee.getPrice();  // 4000

// 이름 조회
String name = coffee.getName();  // "Latte"
```

---

## 실제 코드 비교

### 방법 1: 기본 타입 사용

```java
public class CoffeeOrder {
    public static void main(String[] args) {
        Map<String, Integer> menu = new HashMap<>();
        menu.put("Americano", 3000);
        menu.put("Latte", 4000);
        
        // 메뉴 출력
        for(String coffee : menu.keySet()) {
            System.out.println(coffee + " - " + menu.get(coffee) + "원");
        }
        
        // 가격 조회
        int price = menu.get("Americano");
        System.out.println("가격: " + price + "원");
    }
}
```

**출력:**
```
Americano - 3000원
Latte - 4000원
가격: 3000원
```

### 방법 2: 객체 사용

```java
public class CoffeeOrderSystem {
    public static void main(String[] args) {
        Map<String, Coffee> menuMap = new HashMap<>();
        menuMap.put("Americano", new Coffee("Americano", 3000));
        menuMap.put("Latte", new Coffee("Latte", 4000));
        
        // 메뉴 출력 (toString() 활용)
        for(Coffee coffee : menuMap.values()) {
            System.out.println(coffee);  // toString() 자동 호출
        }
        
        // 가격 조회
        Coffee coffee = menuMap.get("Americano");
        System.out.println("가격: " + coffee.getPrice() + "원");
        System.out.println("이름: " + coffee.getName());
    }
}
```

**출력:**
```
Americano - 3000원
Latte - 4000원
가격: 3000원
이름: Americano
```

---

## 확장성 비교

### 시나리오: 커피에 설명 정보 추가

#### 방법 1: 어려움

```java
// 문제: 설명을 어디에 저장할까?
Map<String, Integer> menu = new HashMap<>();  // 가격만 저장 가능
Map<String, String> descriptions = new HashMap<>();  // 별도 Map 필요!

menu.put("Americano", 3000);
descriptions.put("Americano", "진한 에스프레소와 뜨거운 물");

// 두 개의 Map을 함께 관리해야 함 (불편!)
```

**문제점:**
- 두 개의 Map을 동시에 관리해야 함
- 데이터 일관성 보장이 어려움
- 코드가 복잡해짐

#### 방법 2: 쉬움

```java
// Coffee 클래스에 필드만 추가하면 됨
public class Coffee {
    private String name;
    private int price;
    private String description;  // 추가!
    
    public Coffee(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    // ... getter 메서드들
}

// 사용
menuMap.put("Americano", new Coffee("Americano", 3000, "진한 에스프레소와 뜨거운 물"));

// 한 번에 모든 정보 접근 가능
Coffee coffee = menuMap.get("Americano");
System.out.println(coffee.getName());        // 이름
System.out.println(coffee.getPrice());       // 가격
System.out.println(coffee.getDescription()); // 설명
```

**장점:**
- 하나의 객체에 모든 정보 포함
- 데이터 일관성 보장
- 코드가 깔끔함

---

## 메모리 사용량 비교

### 방법 1: 기본 타입

```java
Map<String, Integer> menu = new HashMap<>();
menu.put("Americano", 3000);
```

**메모리:**
- String 객체: "Americano" (약 24 bytes)
- Integer 객체: 3000 (약 16 bytes)
- **총: 약 40 bytes**

### 방법 2: 객체

```java
Map<String, Coffee> menuMap = new HashMap<>();
menuMap.put("Americano", new Coffee("Americano", 3000));
```

**메모리:**
- String 객체: "Americano" (약 24 bytes)
- Coffee 객체:
  - String name: "Americano" (약 24 bytes)
  - int price: 4 bytes
  - 객체 헤더: 약 16 bytes
- **총: 약 68 bytes**

**차이:** 방법 2가 약 1.7배 더 많은 메모리 사용 (하지만 현대 컴퓨터에서는 무시할 수 있는 수준)

---

## 언제 어떤 방법을 사용할까?

### 방법 1 (`Map<String, Integer>`)을 사용하는 경우

✅ **적합한 상황:**
- 간단한 프로젝트
- 가격 정보만 필요할 때
- 학습 목적
- 빠른 프로토타이핑
- 메모리가 매우 제한적인 환경

**예시:**
- 간단한 계산기
- 기본적인 메뉴 시스템
- 학습용 예제

### 방법 2 (`Map<String, Coffee>`)을 사용하는 경우

✅ **적합한 상황:**
- 실제 프로젝트
- 나중에 정보 추가 가능성이 있을 때
- 객체지향 설계가 필요한 경우
- 코드 재사용이 중요한 경우
- 유지보수가 중요한 프로젝트

**예시:**
- 실제 쇼핑몰 시스템
- 복잡한 메뉴 관리 시스템
- 데이터베이스 연동이 필요한 경우

---

## 실전 예시: 정보 추가 시나리오

### 시나리오: 커피에 카테고리와 칼로리 정보 추가

#### 방법 1: 어려움

```java
// 4개의 Map을 관리해야 함!
Map<String, Integer> prices = new HashMap<>();
Map<String, String> categories = new HashMap<>();
Map<String, Integer> calories = new HashMap<>();
Map<String, String> descriptions = new HashMap<>();

prices.put("Americano", 3000);
categories.put("Americano", "에스프레소");
calories.put("Americano", 10);
descriptions.put("Americano", "진한 맛");

// 정보 조회도 복잡함
int price = prices.get("Americano");
String category = categories.get("Americano");
int calorie = calories.get("Americano");
String desc = descriptions.get("Americano");
```

**문제점:**
- Map이 많아질수록 관리가 어려움
- 데이터 일관성 보장 어려움
- 코드가 복잡해짐

#### 방법 2: 쉬움

```java
// Coffee 클래스만 수정
public class Coffee {
    private String name;
    private int price;
    private String category;      // 추가
    private int calories;         // 추가
    private String description;   // 추가
    
    // 생성자, getter 메서드들...
}

// 사용은 간단
menuMap.put("Americano", new Coffee("Americano", 3000, "에스프레소", 10, "진한 맛"));

// 정보 조회도 간단
Coffee coffee = menuMap.get("Americano");
int price = coffee.getPrice();
String category = coffee.getCategory();
int calorie = coffee.getCalories();
String desc = coffee.getDescription();
```

**장점:**
- 하나의 객체로 모든 정보 관리
- 코드가 깔끔하고 이해하기 쉬움
- 데이터 일관성 보장

---

## 성능 비교

### 조회 속도

**방법 1:**
```java
int price = menu.get("Americano");  // O(1) - 매우 빠름
```

**방법 2:**
```java
Coffee coffee = menuMap.get("Americano");  // O(1) - 매우 빠름
int price = coffee.getPrice();             // O(1) - 매우 빠름
```

**결론:** 둘 다 HashMap이므로 조회 속도는 동일 (O(1))

### 메모리 사용량

- **방법 1:** 적음 (기본 타입만 저장)
- **방법 2:** 많음 (객체 저장)

**하지만:** 현대 컴퓨터에서는 차이가 미미함

---

## 코드 가독성 비교

### 방법 1: 간단하지만 제한적

```java
// 가격만 조회 가능
int price = menu.get("Americano");
```

### 방법 2: 더 많은 정보 접근 가능

```java
// 여러 정보 접근 가능
Coffee coffee = menuMap.get("Americano");
String name = coffee.getName();
int price = coffee.getPrice();
// 나중에 추가된 정보도 쉽게 접근
String category = coffee.getCategory();
int calories = coffee.getCalories();
```

---

## 결론 및 권장사항

### 초보자에게는

**방법 1 (`Map<String, Integer>`)을 권장:**
- 이해하기 쉬움
- 빠르게 결과 확인 가능
- HashMap 기본 개념 학습에 적합

### 실무에서는

**방법 2 (`Map<String, Coffee>`)을 권장:**
- 확장성이 좋음
- 유지보수가 쉬움
- 객체지향 설계 원칙에 부합
- 실제 프로젝트에 적합

### 학습 순서

1. **1단계**: 방법 1로 기본 개념 이해
2. **2단계**: 방법 2로 객체지향 개념 학습
3. **3단계**: 프로젝트에 맞는 방법 선택

---

## 요약 표

| 항목 | 방법 1 (Integer) | 방법 2 (Coffee) |
|------|------------------|-----------------|
| **코드 길이** | 짧음 ✅ | 길음 |
| **이해 난이도** | 쉬움 ✅ | 어려움 |
| **확장성** | 낮음 | 높음 ✅ |
| **유지보수** | 어려움 | 쉬움 ✅ |
| **메모리** | 적음 ✅ | 많음 |
| **실무 적합성** | 낮음 | 높음 ✅ |
| **학습용 적합성** | 높음 ✅ | 낮음 |
| **객체지향** | ❌ | ✅ |

---

## 실습 문제

### 문제 1: 방법 1을 방법 2로 변환하기

다음 코드를 객체를 사용하는 방식으로 변경하세요:

```java
Map<String, Integer> menu = new HashMap<>();
menu.put("Cappuccino", 5000);
menu.put("Macchiato", 5500);
```

**정답:**
```java
Map<String, Coffee> menuMap = new HashMap<>();
menuMap.put("Cappuccino", new Coffee("Cappuccino", 5000));
menuMap.put("Macchiato", new Coffee("Macchiato", 5500));
```

### 문제 2: 정보 추가하기

Coffee 클래스에 `category` 필드를 추가하고, 카테고리별로 메뉴를 출력하는 메서드를 작성하세요.

---

**핵심 정리:**
- **간단한 프로젝트**: 방법 1 (Integer)
- **실무 프로젝트**: 방법 2 (Coffee 객체)
- **학습 목적**: 방법 1부터 시작
- **확장성 필요**: 방법 2 선택
