# Lottery.java 변경 사항 정리

## 📋 개요

이 문서는 `Lottery.java` 파일의 주요 변경 사항을 정리한 것입니다. 로또 번호 생성 방식과 당첨 결과 계산 로직이 개선되었습니다.

---

## 🔄 주요 변경 사항

### 1. `randomLotteryNum()` 메서드 개선

#### 변경 전 (추정)
```java
public void randomLotteryNum() {
    // Math.random()을 사용한 중복 체크 방식
    // 또는 다른 방식의 랜덤 번호 생성
}
```

#### 변경 후 (현재)
```java
public void randomLotteryNum() {
    ArrayList<Integer> numbers = new ArrayList<>();
    
    // 1~45까지의 숫자 리스트 생성
    for(int i = 1; i <= 45; i++){
        numbers.add(i);
    }
    
    // 리스트를 섞기
    Collections.shuffle(numbers);
    
    // 앞에서 6개 선택
    for(int i = 0; i < 6; i++){
        randomNumbers[i] = numbers.get(i);
    }
    
    // 오름차순 정렬 (Bubble Sort)
    for(int i = 0; i < 5; i++){
        for(int j = i + 1; j < 6; j++){
            if(randomNumbers[i] > randomNumbers[j]){
                int temp = randomNumbers[i];
                randomNumbers[i] = randomNumbers[j];
                randomNumbers[j] = temp;
            }
        }
    }
    
    // 문자열로 변환
    randomNum = "";
    for(int i = 0; i < 6; i++){
        if(i > 0) randomNum += " ";
        randomNum += String.format("%2d", randomNumbers[i]);
    }
}
```

#### 변경 내용 상세

1. **ArrayList와 Collections.shuffle() 사용**
   - ✅ 중복 없이 번호 생성 보장
   - ✅ 더 효율적인 랜덤 선택 방식
   - ✅ 1~45 범위의 모든 숫자를 리스트로 생성 후 섞기

2. **Bubble Sort로 정렬**
   - ✅ 오름차순 정렬 구현
   - ✅ 중첩 for문을 사용한 간단한 정렬 알고리즘

3. **String.format() 사용**
   - ✅ `String.format("%2d", randomNumbers[i])`로 두 자리 숫자 포맷팅
   - ✅ 한 자리 숫자도 " 5" 형태로 일관된 출력

4. **번호 범위 변경**
   - ✅ 1~45 범위로 변경 (실제 로또 번호 범위)

---

### 2. `result()` 메서드 개선

#### 변경 전 (추정)
```java
public void result(){
    // 단순한 결과 출력
    // 또는 matchCount 계산 로직이 없었음
}
```

#### 변경 후 (현재)
```java
public void result(){
    Membership m = Membership.getInstance();
    Purchase p = Purchase.getInstance();
    
    System.out.println("[INFO] " + m.name + "님의 당첨 결과 3초 후에 안내드립니다.");
    System.out.println("\n=================================================================\n");
    Loading l = new Loading();
    l.loading();
    
    // 당첨 번호 개수 계산
    int matchCount = 0;
    for(int i = 0; i < 6; i++){
        for(int j = 0; j < 6; j++){
            if(p.customerNumbers[i] == randomNumbers[j]){
                matchCount++;
                break;
            }
        }
    }
    
    System.out.println("[INFO] " + m.name + "님의 로또번호는 " + p.customerNum + "입니다.");
    System.out.println("[INFO] 당첨번호는 " + randomNum + "입니다.");
    
    if(matchCount == 6){
        System.out.println("[INFO] 축하드립니다! 1등에 당첨되셨습니다! (6개 일치)");
    } else if(matchCount == 5){
        System.out.println("[INFO] 축하드립니다! 2등에 당첨되셨습니다! (5개 일치)");
    } else if(matchCount == 4){
        System.out.println("[INFO] 축하드립니다! 3등에 당첨되셨습니다! (4개 일치)");
    } else if(matchCount == 3){
        System.out.println("[INFO] 축하드립니다! 4등에 당첨되셨습니다! (3개 일치)");
    } else {
        System.out.println("[INFO] 일치한 번호: " + matchCount + "개");
        System.out.println("[INFO] 낙첨입니다. 다음기회를 노려보세요");
    }
    
    System.out.println("\n========================================================");
    System.out.println("[INFO] 로또추첨이 완료됐습니다. 감사합니다.");
    System.out.println("\n========================================================");
}
```

#### 변경 내용 상세

1. **matchCount 계산 로직 추가**
   ```java
   int matchCount = 0;
   for(int i = 0; i < 6; i++){
       for(int j = 0; j < 6; j++){
           if(p.customerNumbers[i] == randomNumbers[j]){
               matchCount++;
               break; // 중복 체크 방지
           }
       }
   }
   ```
   - ✅ 고객 번호와 당첨 번호를 비교하여 일치 개수 계산
   - ✅ `break`를 사용하여 한 번호가 여러 번 매칭되는 것 방지

2. **등수 출력 로직 추가**
   - ✅ 6개 일치: 1등
   - ✅ 5개 일치: 2등
   - ✅ 4개 일치: 3등
   - ✅ 3개 일치: 4등
   - ✅ 2개 이하: 낙첨

3. **출력 메시지 개선**
   - ✅ 고객 번호와 당첨 번호를 명확히 표시
   - ✅ 등수별 축하 메시지 출력
   - ✅ 낙첨 시 일치한 번호 개수 표시

---

## 📊 변경 사항 요약표

| 항목 | 변경 전 | 변경 후 |
|------|---------|---------|
| **번호 생성 방식** | Math.random() 기반 (추정) | ArrayList + Collections.shuffle() |
| **중복 처리** | 수동 체크 (추정) | 자동 처리 (shuffle 사용) |
| **정렬 방식** | 없음 또는 다른 방식 | Bubble Sort |
| **번호 범위** | 미확인 | 1~45 |
| **번호 포맷팅** | 단순 문자열 연결 | String.format("%2d") 사용 |
| **당첨 결과 계산** | 없음 또는 단순 출력 | matchCount 계산 + 등수 출력 |
| **등수 출력** | 없음 | 1~4등 + 낙첨 구분 |

---

## 🔍 핵심 개선 포인트

### 1. 중복 없는 랜덤 번호 생성

**개선 전:**
- Math.random()으로 번호 생성 후 중복 체크 필요
- 중복 발생 시 다시 생성하는 비효율적 방식

**개선 후:**
- 1~45 리스트 생성 → shuffle → 앞 6개 선택
- 중복 불가능한 구조적 보장

### 2. 정렬 알고리즘 구현

**Bubble Sort 알고리즘:**
```java
for(int i = 0; i < 5; i++){
    for(int j = i + 1; j < 6; j++){
        if(randomNumbers[i] > randomNumbers[j]){
            // swap
        }
    }
}
```

**특징:**
- 간단하고 이해하기 쉬운 정렬 방식
- 6개 숫자만 정렬하므로 효율성 문제 없음

### 3. 당첨 결과 계산 로직

**이중 반복문을 사용한 매칭:**
```java
for(int i = 0; i < 6; i++){           // 고객 번호 순회
    for(int j = 0; j < 6; j++){        // 당첨 번호 순회
        if(p.customerNumbers[i] == randomNumbers[j]){
            matchCount++;
            break; // 한 번 매칭되면 다음 고객 번호로
        }
    }
}
```

**특징:**
- 모든 고객 번호를 당첨 번호와 비교
- `break`로 중복 매칭 방지

---

## 📝 추가된 Import 문

```java
import java.util.ArrayList;    // ArrayList 사용
import java.util.Collections;  // Collections.shuffle() 사용
```

---

## 🎯 학습 포인트

### 1. Collections.shuffle()
- 리스트의 요소를 무작위로 섞는 메서드
- 중복 없이 랜덤 선택이 필요한 경우 유용

### 2. Bubble Sort
- 인접한 두 요소를 비교하여 정렬하는 알고리즘
- 간단하지만 대량 데이터에는 비효율적
- 6개 숫자 정렬에는 적합

### 3. String.format()
- 문자열 포맷팅 메서드
- `%2d`: 두 자리 정수 (한 자리 숫자는 앞에 공백)
- 예: `String.format("%2d", 5)` → `" 5"`

### 4. break 문
- 반복문을 즉시 종료
- 중복 매칭 방지에 사용

---

## 🔄 코드 흐름도

### randomLotteryNum() 메서드
```
1. ArrayList 생성 (1~45)
   ↓
2. Collections.shuffle() (리스트 섞기)
   ↓
3. 앞에서 6개 선택
   ↓
4. Bubble Sort로 정렬
   ↓
5. String.format()으로 포맷팅
   ↓
6. randomNum 문자열 완성
```

### result() 메서드
```
1. Singleton 인스턴스 가져오기
   ↓
2. Loading 애니메이션 실행
   ↓
3. matchCount 계산 (이중 반복문)
   ↓
4. 고객 번호와 당첨 번호 출력
   ↓
5. matchCount에 따른 등수 출력
```

---

## ✅ 변경 사항 검증

### 1. 번호 생성 검증
- ✅ 1~45 범위 확인
- ✅ 중복 없음 확인
- ✅ 오름차순 정렬 확인
- ✅ 두 자리 포맷 확인

### 2. 당첨 결과 검증
- ✅ matchCount 정확도 확인
- ✅ 등수 출력 정확도 확인
- ✅ 낙첨 메시지 확인

---

## 📚 관련 개념

1. **ArrayList**: 동적 배열, 크기 변경 가능
2. **Collections.shuffle()**: 리스트 무작위 섞기
3. **Bubble Sort**: 버블 정렬 알고리즘
4. **String.format()**: 문자열 포맷팅
5. **break**: 반복문 즉시 종료
6. **Singleton Pattern**: 단일 인스턴스 보장

---

## 🎓 개선 효과

1. **코드 가독성 향상**: 명확한 로직 구조
2. **중복 방지**: 구조적으로 중복 불가능
3. **정확한 결과**: matchCount 계산으로 정확한 등수 판정
4. **사용자 경험**: 명확한 등수 출력과 메시지

---

## 📅 변경 일자

- 변경 일자: 2025-01-30 (추정)
- 변경 내용: 로또 번호 생성 및 당첨 결과 계산 로직 개선

---

이 문서는 `Lottery.java`의 주요 변경 사항을 정리한 것입니다. 추가 질문이나 수정 사항이 있으면 알려주세요!
