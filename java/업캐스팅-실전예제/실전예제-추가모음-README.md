# 업캐스팅 실전 예제 추가 모음

## 목차

1. [Example8_BookManagement - 도서 관리 시스템](#example8_bookmanagement)
2. [Example9_PaymentSystem - 결제 시스템](#example9_paymentsystem)
3. [Example10_MediaPlayer - 미디어 플레이어 시스템](#example10_mediaplayer)
4. [Example11_VehicleRental - 차량 렌탈 시스템](#example11_vehiclerental)

---

## Example8_BookManagement

**파일명:** `Example8_BookManagement.java`

**시스템:** 도서 관리 시스템

**주요 특징:**
- 다양한 도서 타입 관리 (일반도서, 전자책, 오디오북, 전집)
- 각 도서 타입마다 다른 할인율 적용
- 다형성을 활용한 가격 계산

**학습 내용:**
- 추상 클래스와 상속
- 업캐스팅과 다형성
- 배열과 업캐스팅
- 공통 메서드 활용

**클래스 구조:**
```
Book (추상)
├── RegularBook (일반도서)
├── EBook (전자책)
├── AudioBook (오디오북)
└── BookSet (전집)
```

**실행 방법:**
```bash
javac Example8_BookManagement.java
java Example8_BookManagement
```

---

## Example9_PaymentSystem

**파일명:** `Example9_PaymentSystem.java`

**시스템:** 결제 시스템

**주요 특징:**
- 다양한 결제 수단 처리 (신용카드, 계좌이체, 모바일결제, 현금)
- 각 결제 수단마다 다른 수수료율
- 결제 처리 및 통계

**학습 내용:**
- 추상 메서드 오버라이딩
- 업캐스팅을 활용한 공통 처리
- 타입별 특수 기능 처리
- 결제 수단별 검색

**클래스 구조:**
```
PaymentMethod (추상)
├── CreditCard (신용카드)
├── BankTransfer (계좌이체)
├── MobilePayment (모바일결제)
└── CashPayment (현금)
```

**실행 방법:**
```bash
javac Example9_PaymentSystem.java
java Example9_PaymentSystem
```

---

## Example10_MediaPlayer

**파일명:** `Example10_MediaPlayer.java`

**시스템:** 미디어 플레이어 시스템

**주요 특징:**
- 다양한 미디어 파일 타입 처리 (오디오, 비디오, 이미지, 문서)
- 각 미디어 타입마다 다른 재생 방식
- 파일 정보 출력 및 통계

**학습 내용:**
- 추상 클래스와 다형성
- 업캐스팅을 활용한 미디어 관리
- 타입별 필터링
- 파일 크기 및 시간 포맷팅

**클래스 구조:**
```
MediaFile (추상)
├── AudioFile (오디오)
├── VideoFile (비디오)
├── ImageFile (이미지)
└── DocumentFile (문서)
```

**실행 방법:**
```bash
javac Example10_MediaPlayer.java
java Example10_MediaPlayer
```

---

## Example11_VehicleRental

**파일명:** `Example11_VehicleRental.java`

**시스템:** 차량 렌탈 시스템

**주요 특징:**
- 다양한 차량 타입 관리 (승용차, SUV, 트럭, 오토바이)
- 각 차량 타입마다 다른 할인 정책
- 렌탈 기간에 따른 가격 계산

**학습 내용:**
- 추상 클래스와 상속
- 업캐스팅과 다형성
- 조건부 할인 계산
- 차량 타입별 특수 기능

**클래스 구조:**
```
Vehicle (추상)
├── Sedan (승용차)
├── SUV (SUV)
├── Truck (트럭)
└── Motorcycle (오토바이)
```

**실행 방법:**
```bash
javac Example11_VehicleRental.java
java Example11_VehicleRental
```

---

## 공통 패턴

### 1. 추상 클래스 사용

모든 예제에서 추상 클래스를 사용하여:
- 공통 속성 정의
- 추상 메서드로 자식 클래스에서 구현 강제
- 다형성의 기반 제공

### 2. 업캐스팅 활용

```java
ParentType[] array = new ParentType[size];
array[0] = new ChildType1(...);
array[1] = new ChildType2(...);
```

### 3. 다형성을 활용한 공통 처리

```java
for (ParentType item : array) {
    item.commonMethod();  // 각 타입에 맞는 동작
}
```

### 4. instanceof와 다운캐스팅

```java
if (item instanceof ChildType) {
    ChildType child = (ChildType) item;
    child.specialMethod();
}
```

### 5. 공통 메서드 활용

```java
static ParentType findItem(ParentType[] items, String searchKey) {
    // 타입에 관계없이 동작하는 공통 메서드
}
```

---

## 학습 순서 권장

1. **Example8_BookManagement** - 기본 패턴 이해
2. **Example9_PaymentSystem** - 추상 메서드 활용
3. **Example10_MediaPlayer** - 복잡한 정보 처리
4. **Example11_VehicleRental** - 조건부 계산

---

## 핵심 개념 정리

### 추상 클래스
- 공통 속성과 행동 정의
- 추상 메서드로 구현 강제
- 인스턴스 생성 불가

### 업캐스팅
- 자식 객체를 부모 타입으로 참조
- 자동 변환
- 부모 클래스 멤버만 접근 가능

### 다형성
- 같은 메서드 호출이 객체 타입에 따라 다른 동작
- 동적 바인딩
- 코드 재사용성 향상

### 공통 처리
- 배열과 반복문으로 모든 타입 처리
- 타입별 특수 기능은 다운캐스팅으로 처리

---

**작성일:** 2026-01-30  
**범위:** 업캐스팅 실전 예제 추가 모음
