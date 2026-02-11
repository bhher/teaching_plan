# Java 업캐스팅 실전 예제 코드

## 목차

1. [예제 1: 기본 업캐스팅](#예제-1-기본-업캐스팅)
2. [예제 2: 다형성과 업캐스팅](#예제-2-다형성과-업캐스팅)
3. [예제 3: 필드와 메서드의 차이](#예제-3-필드와-메서드의-차이)
4. [예제 4: static 메서드와 업캐스팅](#예제-4-static-메서드와-업캐스팅)
5. [예제 5: 실전 활용 예제](#예제-5-실전-활용-예제)
6. [예제 6: 생성자 호출 순서](#예제-6-생성자-호출-순서)
7. [예제 7: 인터페이스와 업캐스팅](#예제-7-인터페이스와-업캐스팅)

---

## 예제 1: 기본 업캐스팅

**파일명:** `Example1_BasicUpcasting.java`

**학습 내용:**
- 기본적인 업캐스팅 개념
- 메서드 오버라이딩과 동적 바인딩
- 다운캐스팅으로 자식 메서드 호출

**핵심 포인트:**
- `Animal animal = new Dog();` - 업캐스팅
- 오버라이딩된 메서드는 자식 클래스의 메서드 호출
- 자식 클래스에만 있는 메서드는 다운캐스팅 필요

**실행 방법:**
```bash
javac Example1_BasicUpcasting.java
java Example1_BasicUpcasting
```

---

## 예제 2: 다형성과 업캐스팅

**파일명:** `Example2_Polymorphism.java`

**학습 내용:**
- 배열과 업캐스팅
- 다형성을 활용한 공통 처리
- 추상 클래스와 업캐스팅

**핵심 포인트:**
- 다양한 도형 객체를 부모 타입 배열에 저장
- 반복문으로 공통 처리
- `instanceof`로 타입 확인 후 다운캐스팅

**실행 방법:**
```bash
javac Example2_Polymorphism.java
java Example2_Polymorphism
```

---

## 예제 3: 필드와 메서드의 차이

**파일명:** `Example3_FieldVsMethod.java`

**학습 내용:**
- 필드의 정적 바인딩
- 메서드의 동적 바인딩
- 필드 숨김(Field Hiding) 개념

**핵심 포인트:**
- 필드는 참조 변수 타입에 따라 결정됨
- 메서드는 실제 객체 타입에 따라 결정됨
- `super`로 부모 필드 접근 가능

**실행 방법:**
```bash
javac Example3_FieldVsMethod.java
java Example3_FieldVsMethod
```

---

## 예제 4: static 메서드와 업캐스팅

**파일명:** `Example4_StaticMethod.java`

**학습 내용:**
- static 메서드는 오버라이딩되지 않음
- 메서드 숨김(Method Hiding) 개념
- static 필드와 인스턴스 필드의 차이

**핵심 포인트:**
- static 메서드는 참조 변수 타입에 따라 호출됨
- 인스턴스 메서드는 실제 객체 타입에 따라 호출됨
- static은 오버라이딩이 아닌 메서드 숨김

**실행 방법:**
```bash
javac Example4_StaticMethod.java
java Example4_StaticMethod
```

---

## 예제 5: 실전 활용 예제

**파일명:** `Example5_PracticalUse.java`

**학습 내용:**
- 실제 개발에서 사용하는 업캐스팅 패턴
- 직원 관리 시스템 구현
- 공통 메서드를 활용한 코드 재사용

**핵심 포인트:**
- 다양한 타입의 객체를 배열에 저장
- 다형성을 활용한 급여 계산
- 타입별 특수 기능 처리

**실행 방법:**
```bash
javac Example5_PracticalUse.java
java Example5_PracticalUse
```

---

## 예제 6: 생성자 호출 순서

**파일명:** `Example6_ConstructorOrder.java`

**학습 내용:**
- 생성자 호출 순서
- 초기화 블록 실행 순서
- super() 자동 호출

**핵심 포인트:**
- 실행 순서: 부모 초기화 블록 → 부모 생성자 → 자식 초기화 블록 → 자식 생성자
- `super()`는 자동으로 호출됨
- 초기화 블록은 생성자보다 먼저 실행됨

**실행 방법:**
```bash
javac Example6_ConstructorOrder.java
java Example6_ConstructorOrder
```

---

## 예제 7: 인터페이스와 업캐스팅

**파일명:** `Example7_InterfaceUpcasting.java`

**학습 내용:**
- 인터페이스 타입으로 구현 클래스 참조
- 인터페이스와 업캐스팅의 유사성
- 다형성을 활용한 공통 처리

**핵심 포인트:**
- 인터페이스 타입으로 구현 클래스 객체 참조
- 인터페이스에 정의된 메서드만 호출 가능
- `instanceof`로 타입 확인 후 다운캐스팅

**실행 방법:**
```bash
javac Example7_InterfaceUpcasting.java
java Example7_InterfaceUpcasting
```

---

## 전체 컴파일 및 실행

모든 파일을 한 번에 컴파일:
```bash
javac *.java
```

개별 실행:
```bash
java Example1_BasicUpcasting
java Example2_Polymorphism
java Example3_FieldVsMethod
java Example4_StaticMethod
java Example5_PracticalUse
java Example6_ConstructorOrder
java Example7_InterfaceUpcasting
```

---

## 학습 순서 권장

1. **Example1_BasicUpcasting** - 기본 개념 이해
2. **Example3_FieldVsMethod** - 필드와 메서드의 차이 이해
3. **Example6_ConstructorOrder** - 생성자 호출 순서 이해
4. **Example4_StaticMethod** - static 메서드의 특성 이해
5. **Example2_Polymorphism** - 다형성 활용
6. **Example7_InterfaceUpcasting** - 인터페이스 활용
7. **Example5_PracticalUse** - 실전 활용

---

## 핵심 개념 정리

### 업캐스팅의 특징

1. **자동 변환**: 명시적 캐스팅 불필요
2. **접근 제한**: 부모 클래스 멤버만 접근 가능
3. **동적 바인딩**: 오버라이딩된 메서드는 자식 클래스의 메서드 호출

### 필드 vs 메서드

| 구분 | 필드 | 메서드 |
|------|------|--------|
| 바인딩 | 정적 바인딩 | 동적 바인딩 |
| 결정 시점 | 컴파일 타임 | 런타임 |
| 참조 기준 | 참조 변수 타입 | 실제 객체 타입 |

### static 메서드

- 오버라이딩되지 않음
- 메서드 숨김(Method Hiding) 발생
- 참조 변수 타입에 따라 호출됨

### 생성자 호출 순서

1. 부모 초기화 블록
2. 부모 생성자
3. 자식 초기화 블록
4. 자식 생성자

---

**작성일:** 2026-01-30  
**범위:** Java 업캐스팅 실전 예제 코드
