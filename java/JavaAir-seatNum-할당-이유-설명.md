# seatNum = seatInt를 하는 이유

## 문제 상황

```java
private int seatSelection(Flight flight) {
    int seatNum = -1;  // 초기값 -1
    while(true){
        try {
            // ... 좌석 입력 및 검증 로직
            
            if(좌석이 유효하고 비어있으면){
                flight.getSeats().set(seatInt, "XX");  // 좌석 예약
                System.out.println("좌석 선택이 완료되었습니다.");
                seatNum = seatInt;  // ⭐ 이 부분이 왜 필요한가?
                break;
            }
        } catch (InputMismatchException e) {
            // 예외 처리
        }
    }
    return seatNum;  // 메서드가 int를 반환해야 함
}
```

---

## 왜 `seatNum = seatInt;`가 필요한가?

### 1. 메서드 반환값 설정

**메서드 시그니처:**
```java
private int seatSelection(Flight flight)
```

- 메서드는 `int` 타입을 반환해야 함
- 선택된 좌석 번호를 반환해야 함

### 2. 변수 스코프 문제

**문제가 되는 코드:**
```java
private int seatSelection(Flight flight) {
    int seatNum = -1;
    while(true){
        int seatInt = sc.nextInt() - 1;  // ⚠️ while 블록 내부 변수
        
        if(조건){
            flight.getSeats().set(seatInt, "XX");
            // seatNum = seatInt;  // ❌ 이 줄이 없으면?
            break;
        }
    }
    return seatNum;  // ⚠️ seatNum은 여전히 -1
}
```

**문제점:**
- `seatInt`는 `while` 블록 내부 변수
- `break` 후 `seatInt`는 접근 불가
- `seatNum`은 여전히 초기값 `-1`
- 반환값이 `-1`이 되어 호출하는 쪽에서 문제 발생

---

## 올바른 코드 분석

### 코드 구조

```java
private int seatSelection(Flight flight) {
    int seatNum = -1;           // 1. 반환값을 저장할 변수 (메서드 스코프)
    
    while(true){
        int seatInt = sc.nextInt() - 1;  // 2. 입력받은 좌석 번호 (블록 스코프)
        
        if(좌석이 유효하고 비어있으면){
            flight.getSeats().set(seatInt, "XX");  // 3. 좌석 예약 표시
            seatNum = seatInt;                     // 4. 반환값에 저장 ⭐
            break;                                 // 5. 루프 탈출
        }
    }
    
    return seatNum;  // 6. 선택된 좌석 번호 반환
}
```

### 단계별 설명

#### 1단계: 변수 초기화
```java
int seatNum = -1;
```
- 반환값을 저장할 변수 초기화
- `-1`은 "선택되지 않음"을 의미하는 기본값

#### 2단계: 좌석 번호 입력
```java
int seatInt = sc.nextInt() - 1;
```
- 사용자가 입력한 좌석 번호 (1~20)
- 배열 인덱스로 변환 (0~19)
- `while` 블록 내부 변수

#### 3단계: 좌석 예약
```java
flight.getSeats().set(seatInt, "XX");
```
- 선택한 좌석을 "XX"로 표시
- 좌석이 예약되었음을 나타냄

#### 4단계: 반환값 저장 ⭐
```java
seatNum = seatInt;
```
- **이 부분이 핵심!**
- `seatInt`의 값을 `seatNum`에 복사
- `seatNum`은 메서드 전체에서 접근 가능
- `break` 후에도 값이 유지됨

#### 5단계: 루프 탈출
```java
break;
```
- `while` 루프 종료
- `seatInt` 변수는 이 시점에서 접근 불가 (블록 스코프 종료)

#### 6단계: 값 반환
```java
return seatNum;
```
- 저장된 좌석 번호 반환
- `seatNum`에는 선택된 좌석 번호가 저장되어 있음

---

## 만약 `seatNum = seatInt;`가 없다면?

### 문제 코드

```java
private int seatSelection(Flight flight) {
    int seatNum = -1;
    while(true){
        int seatInt = sc.nextInt() - 1;
        
        if(조건){
            flight.getSeats().set(seatInt, "XX");
            // seatNum = seatInt;  // ❌ 이 줄이 없음
            break;
        }
    }
    return seatNum;  // ⚠️ 항상 -1 반환
}
```

### 발생하는 문제

1. **반환값이 항상 -1**
   - 좌석 선택이 성공해도 `seatNum`은 여전히 `-1`
   - 호출하는 쪽에서 잘못된 값 받음

2. **호출하는 쪽에서 문제 발생**
```java
// bookFlight() 메서드에서
String seatNum = Integer.toString(seatSelection(sf));
passengers.get(passengers.size()-1).setSeat(seatNum);
// seatNum = "-1"이 저장됨 ❌
```

3. **티켓 출력 시 오류**
```java
int seat = Integer.parseInt(passengers.get(index).getSeat()) + 1;
// seat = -1 + 1 = 0 ❌
// 티켓에 "좌석 : 0번"으로 표시됨
```

---

## 변수 스코프 이해

### 스코프 (Scope)란?

변수가 접근 가능한 범위를 의미합니다.

### 코드에서의 스코프

```java
private int seatSelection(Flight flight) {
    int seatNum = -1;           // ← 메서드 스코프 (메서드 전체에서 접근 가능)
    
    while(true){                // ← while 블록 시작
        int seatInt = ...;      // ← while 블록 스코프 (블록 내부에서만 접근 가능)
        
        if(조건){
            seatNum = seatInt;  // ← seatInt의 값을 seatNum에 복사
            break;              // ← while 블록 종료
        }
    }                           // ← while 블록 종료, seatInt는 더 이상 접근 불가
    
    return seatNum;             // ← seatNum은 여전히 접근 가능
}
```

### 시각적 표현

```
메서드 스코프 (seatSelection)
├── seatNum (-1)  ← 메서드 전체에서 접근 가능
│
└── while 블록 스코프
    ├── seatInt (입력값)  ← 블록 내부에서만 접근 가능
    │
    └── break 후
        └── seatInt 접근 불가 ❌
        └── seatNum 접근 가능 ✅
```

---

## 올바른 사용 예시

### 전체 흐름

```java
// 1. 메서드 호출
int selectedSeat = seatSelection(flight);

// 2. seatSelection() 메서드 내부
private int seatSelection(Flight flight) {
    int seatNum = -1;  // 초기값
    
    while(true){
        int seatInt = 5 - 1;  // 사용자가 5번 입력 → 인덱스 4
        
        if(조건 만족){
            flight.getSeats().set(4, "XX");  // 좌석 예약
            seatNum = 4;  // ⭐ 반환값에 저장
            break;
        }
    }
    
    return 4;  // seatNum의 값 반환
}

// 3. 호출하는 쪽에서 받음
String seatNum = Integer.toString(4);  // "4"
passengers.get(...).setSeat("4");      // 승객 객체에 좌석 저장
```

### 티켓 출력 시

```java
// 저장된 좌석 번호: "4" (문자열)
int seat = Integer.parseInt("4") + 1;  // 4 + 1 = 5
// 티켓에 "좌석 : 5번"으로 표시 ✅
```

---

## 핵심 정리

### `seatNum = seatInt;`를 하는 이유

1. **변수 스코프 문제 해결**
   - `seatInt`는 `while` 블록 내부 변수
   - `break` 후 접근 불가
   - 메서드 스코프 변수 `seatNum`에 값 저장 필요

2. **반환값 설정**
   - 메서드는 `int`를 반환해야 함
   - 선택된 좌석 번호를 반환해야 함
   - `seatNum`에 값을 저장해야 `return seatNum;`이 의미 있음

3. **데이터 전달**
   - 좌석 선택 성공 시 선택된 좌석 번호를 호출하는 쪽에 전달
   - 호출하는 쪽에서 승객 객체에 좌석 번호 저장

---

## 대안 방법

### 방법 1: 현재 방식 (권장)

```java
int seatNum = -1;
while(true){
    int seatInt = sc.nextInt() - 1;
    if(조건){
        seatNum = seatInt;  // 값 복사
        break;
    }
}
return seatNum;
```

**장점:**
- 명확하고 이해하기 쉬움
- 변수 스코프가 명확함

### 방법 2: 즉시 반환

```java
while(true){
    int seatInt = sc.nextInt() - 1;
    if(조건){
        flight.getSeats().set(seatInt, "XX");
        return seatInt;  // 즉시 반환
    }
}
return -1;  // 선택 실패 시 (실제로는 도달하지 않음)
```

**장점:**
- 코드가 더 간결함
- 변수 할당 불필요

**단점:**
- 좌석 예약 후 추가 처리가 어려움
- 현재 코드 구조와 맞지 않음

### 방법 3: 메서드 스코프 변수 사용

```java
int seatNum = -1;
int seatInt;  // 메서드 스코프로 선언
while(true){
    seatInt = sc.nextInt() - 1;  // 할당만
    if(조건){
        flight.getSeats().set(seatInt, "XX");
        seatNum = seatInt;
        break;
    }
}
return seatNum;
```

**장점:**
- 변수 스코프 문제 없음

**단점:**
- 변수가 메서드 전체에 노출됨
- 현재 방식이 더 명확함

---

## 학습 포인트

### 1. 변수 스코프 이해

- **블록 스코프**: `{}` 내부에서만 접근 가능
- **메서드 스코프**: 메서드 전체에서 접근 가능
- **변수 생명주기**: 블록 종료 시 변수 소멸

### 2. 반환값 관리

- 메서드가 값을 반환해야 할 때
- 반환할 값을 적절한 변수에 저장
- 반환 직전에 값이 설정되어야 함

### 3. 데이터 흐름

```
입력 (seatInt) 
  ↓
검증 및 처리
  ↓
저장 (seatNum = seatInt)
  ↓
반환 (return seatNum)
  ↓
호출하는 쪽에서 사용
```

---

## 체크리스트

- [ ] 변수 스코프 이해
- [ ] `seatNum = seatInt;`의 필요성 이해
- [ ] 메서드 반환값 관리 이해
- [ ] 데이터 흐름 이해
- [ ] 대안 방법 이해

---

**작성일:** 2026-01-30  
**관련 파일:** JavaAir-항공예약시스템-완전정복.md
