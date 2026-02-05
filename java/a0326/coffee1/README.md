# 커피 주문 시스템 프로젝트

## 프로젝트 개요

HashMap을 활용한 커피 주문 관리 시스템입니다.

## 파일 구조

```
a0326/coffee1/
├── CoffeeOrder.java          # 기본 버전 (단일 클래스)
├── Coffee.java               # 개선 버전 - Coffee 클래스
├── Menu.java                 # 개선 버전 - Menu 클래스
├── OrderManager.java         # 개선 버전 - OrderManager 클래스
└── CoffeeOrderSystem.java    # 개선 버전 - 메인 클래스
```

## 실행 방법

### 기본 버전 실행

```bash
javac CoffeeOrder.java
java CoffeeOrder
```

### 개선 버전 실행

```bash
javac *.java
java CoffeeOrderSystem
```

## 주요 기능

1. **메뉴 관리**
   - 커피 이름과 가격을 HashMap에 저장
   - 메뉴 조회 기능

2. **주문 기능**
   - 커피 이름과 수량 입력
   - 주문 누적 기능
   - 주문 내역 출력

3. **입력 검증**
   - 메뉴 존재 여부 확인
   - 수량 유효성 검사
   - 예외 처리

## 학습 포인트

- HashMap 사용법
- getOrDefault() 메서드 활용
- 예외 처리 (NumberFormatException)
- 반복문과 조건문 활용

## 참고 자료

- 문제: `커피주문시스템-문제.md`
- 해설: `커피주문시스템-해설.md`
- 개선 버전: `커피주문시스템-개선버전.md`
