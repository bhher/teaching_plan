# 더조은커피숍 주문 시스템 (고급 버전)

## 프로젝트 개요

싱글톤 패턴, LinkedHashMap, 재주문 기능, 결제 시스템을 포함한 고급 커피 주문 시스템입니다.

## 파일 구조

```
a0331/coffee/
├── Coffee.java           # 싱글톤 패턴으로 메뉴 관리
├── CoffeeService.java    # 주문 서비스 로직
├── Customer.java         # 고객 정보 관리
├── CoffeeImpl.java       # 메인 클래스
└── README.md            # 프로젝트 설명
```

## 주요 특징

1. **싱글톤 패턴**: Coffee 클래스가 싱글톤으로 구현됨
2. **LinkedHashMap**: 순서가 보장되는 메뉴 및 주문 관리
3. **재주문 기능**: 같은 커피 재주문 시 수량 누적
4. **결제 시스템**: 잔액 확인 및 결제 처리
5. **포맷팅**: DecimalFormat으로 가격 포맷팅
6. **지연 효과**: Thread.sleep()으로 제조 시간 시뮬레이션

## 실행 방법

```bash
javac *.java
java CoffeeImpl
```

## 클래스 설명

### Coffee.java
- 싱글톤 패턴으로 메뉴 관리
- LinkedHashMap으로 순서 보장
- DecimalFormat으로 가격 포맷팅

### CoffeeService.java
- 주문 받기
- 재주문 처리
- 주문 내역 출력
- 결제 처리
- **상세 정리:** [CoffeeService-완전정리.md](./CoffeeService-완전정리.md)

### Customer.java
- 고객 정보 관리
- 주문 번호, 잔액, 주문 내역

### CoffeeImpl.java
- 프로그램 진입점
- CoffeeService 시작

## 상세 해설

`커피주문시스템-고급버전-해설.md` 파일을 참고하세요.
