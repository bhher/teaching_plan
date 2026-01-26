# 배열에서 ArrayList로 변경하기 - 상세 설명

## 1. 왜 ArrayList를 사용하는가?

### 배열의 문제점

```java
// 배열 사용 시
private MemberDTO[] mList = new MemberDTO[10];  // 최대 10개만 저장 가능
private int listSize = 0;

public void userInsert() {
    if(listSize < 10) {  // 크기 제한 체크 필요
        mList[listSize] = new MemberDTO();
        listSize++;  // 크기 관리 필요
    } else {
        System.out.println("저장 공간이 부족합니다.");
    }
}
```

**문제점:**
1. 크기가 고정되어 있어 유연성이 떨어짐
2. 크기를 직접 관리해야 함 (`listSize` 변수 필요)
3. 삭제 시 `null`로만 설정하여 실제 삭제가 아님
4. 크기 제한으로 인한 오류 가능성

### ArrayList의 장점

```java
// ArrayList 사용 시
private ArrayList<MemberDTO> mList = new ArrayList<>();  // 크기 제한 없음

public void userInsert() {
    MemberDTO m = new MemberDTO();
    // ... 입력 받기
    mList.add(m);  // 간단하게 추가, 크기 자동 관리
}
```

**장점:**
1. 동적 크기 조절 (자동으로 크기 증가/감소)
2. 크기 관리 불필요 (`size()` 메서드로 확인)
3. 실제 삭제 가능 (`remove()` 메서드)
4. 크기 제한 없음

## 2. 주요 변경 사항 상세 설명

### 2.1 필드 선언 변경

**배열 방식**
```java
private MemberDTO[] mList = new MemberDTO[10];
private int listSize = 0;
```

**ArrayList 방식**
```java
import java.util.ArrayList;  // import 필요

private ArrayList<MemberDTO> mList = new ArrayList<>();
// listSize 변수 불필요
```

**설명:**
- `ArrayList<MemberDTO>`: 제네릭을 사용하여 타입 지정
- `<>` 안에 저장할 객체 타입 명시
- `new ArrayList<>()`: 빈 ArrayList 생성 (크기 0)
- `listSize` 변수는 `mList.size()`로 대체 가능

### 2.2 생성자 변경

**배열 방식**
```java
public DAO() {
    // 모든 요소를 null로 초기화
    for (int i = 0; i < mList.length; i++) {
        mList[i] = new MemberDTO();
    }
    
    // 테스트 데이터 추가
    mList[0].setId(0);
    mList[0].setName("test1");
    // ...
    listSize++;
}
```

**ArrayList 방식**
```java
public DAO() {
    // 초기화 불필요 (ArrayList는 빈 상태로 시작)
    
    // 테스트 데이터 추가
    MemberDTO m1 = new MemberDTO();
    m1.setId(0);
    m1.setName("test1");
    m1.setAge(11);
    m1.setAddress("서울");
    mList.add(m1);  // add()로 추가
    
    // 나머지도 동일...
}
```

**차이점:**
- 배열: 모든 공간을 미리 초기화해야 함
- ArrayList: 필요할 때만 `add()`로 추가

### 2.3 요소 추가 (insert)

**배열 방식**
```java
private void insert(MemberDTO m) {
    if(listSize < mList.length) {  // 크기 체크
        mList[listSize] = m;  // 인덱스 지정 필요
        listSize++;  // 크기 증가
    }
}
```

**ArrayList 방식**
```java
private void insert(MemberDTO m) {
    mList.add(m);  // 끝에 자동 추가, 크기 자동 증가
}
```

**차이점:**
- 배열: 인덱스를 직접 지정하고 크기를 관리해야 함
- ArrayList: `add()`로 끝에 자동 추가, 크기 자동 관리

### 2.4 요소 삭제 (delete)

**배열 방식**
```java
private void delete(int index) {
    mList[index] = null;  // null로만 설정 (실제 삭제 아님)
    // 배열 크기는 그대로, 빈 공간이 남음
}
```

**ArrayList 방식**
```java
private void delete(int index) {
    mList.remove(index);  // 실제로 요소 제거
    // 뒤의 요소들이 자동으로 앞으로 이동
}
```

**차이점:**
- 배열: `null`로 설정만 하여 실제 삭제가 아님, 빈 공간 발생
- ArrayList: 실제로 제거하고 인덱스 재정렬

**예시:**
```java
// 배열: [A, B, C, D] → delete(1) → [A, null, C, D] (B는 null로만 변경)
// ArrayList: [A, B, C, D] → remove(1) → [A, C, D] (B가 실제로 제거됨)
```

### 2.5 요소 조회 (select)

**배열 방식**
```java
private MemberDTO select(int index) {
    return mList[index];  // [] 연산자 사용
}
```

**ArrayList 방식**
```java
private MemberDTO select(int index) {
    return mList.get(index);  // get() 메서드 사용
}
```

**차이점:**
- 배열: `[]` 연산자로 직접 접근
- ArrayList: `get()` 메서드로 접근

### 2.6 요소 수정 (update)

**배열 방식**
```java
private void update(int index, MemberDTO m) {
    mList[index] = m;  // 직접 할당
}
```

**ArrayList 방식**
```java
private void update(int index, MemberDTO m) {
    mList.set(index, m);  // set() 메서드 사용
}
```

**차이점:**
- 배열: `[]` 연산자로 직접 할당
- ArrayList: `set()` 메서드로 수정

### 2.7 전체 출력 (printAll)

**배열 방식**
```java
public void printAll() {
    for (int i = 0; i < mList.length; i++) {  // length 사용
        if(mList[i] != null && mList[i].getName() != null) {  // null 체크
            System.out.println(mList[i].getName());
        }
    }
}
```

**ArrayList 방식**
```java
public void printAll() {
    for (int i = 0; i < mList.size(); i++) {  // size() 사용
        MemberDTO m = mList.get(i);  // get() 사용
        if(m != null && m.getName() != null) {
            System.out.println(m.getName());
        }
    }
}
```

**향상된 방법 (향상된 for문)**
```java
public void printAll() {
    for (MemberDTO m : mList) {  // 간단한 순회
        if(m != null && m.getName() != null) {
            System.out.println(m.getName());
        }
    }
}
```

**차이점:**
- `length` → `size()`
- `mList[i]` → `mList.get(i)`
- 향상된 for문 사용 가능

### 2.8 인덱스 찾기 (findIndex)

**배열 방식**
```java
private int findIndex() {
    String name = sc.next();
    for (int i = 0; i < mList.length; i++) {
        if(mList[i] != null && mList[i].getName().equals(name)) {
            return i;
        }
    }
    return -1;
}
```

**ArrayList 방식**
```java
private int findIndex() {
    String name = sc.next();
    for (int i = 0; i < mList.size(); i++) {
        MemberDTO m = mList.get(i);
        if(m != null && m.getName().equals(name)) {
            return i;
        }
    }
    return -1;
}
```

**향상된 방법**
```java
private int findIndex() {
    String name = sc.next();
    for (int i = 0; i < mList.size(); i++) {
        if(mList.get(i).getName().equals(name)) {
            return i;
        }
    }
    return -1;
}
```

## 3. ArrayList 주요 메서드 정리

### 3.1 요소 추가

```java
// 끝에 추가
mList.add(member);  // 항상 성공 (크기 제한 없음)

// 특정 위치에 삽입
mList.add(0, member);  // 인덱스 0 위치에 삽입

// 여러 요소 한번에 추가
mList.addAll(otherList);  // 다른 리스트의 모든 요소 추가
```

### 3.2 요소 삭제

```java
// 인덱스로 삭제
mList.remove(0);  // 인덱스 0의 요소 삭제

// 객체로 삭제
mList.remove(member);  // 같은 객체 삭제 (equals() 사용)

// 모든 요소 삭제
mList.clear();  // 리스트 비우기
```

### 3.3 요소 접근

```java
// 요소 가져오기
MemberDTO m = mList.get(0);  // 인덱스 0의 요소

// 요소 수정
mList.set(0, newMember);  // 인덱스 0의 요소 수정

// 요소 존재 확인
boolean exists = mList.contains(member);  // 포함 여부 확인

// 인덱스 찾기
int index = mList.indexOf(member);  // 첫 번째 인덱스 반환
```

### 3.4 크기 관련

```java
// 크기 확인
int size = mList.size();  // 요소 개수

// 비어있는지 확인
boolean isEmpty = mList.isEmpty();  // 비어있으면 true
```

### 3.5 순회 방법

**방법 1: 일반 for문**
```java
for (int i = 0; i < mList.size(); i++) {
    MemberDTO m = mList.get(i);
    System.out.println(m);
}
```

**방법 2: 향상된 for문 (권장)**
```java
for (MemberDTO m : mList) {
    System.out.println(m);
}
```

**방법 3: Iterator 사용**
```java
Iterator<MemberDTO> it = mList.iterator();
while(it.hasNext()) {
    MemberDTO m = it.next();
    System.out.println(m);
}
```

## 4. 실전 예제 비교

### 예제 1: 회원 추가

**배열**
```java
public void addMember(MemberDTO m) {
    if(listSize >= mList.length) {
        System.out.println("저장 공간이 부족합니다.");
        return;
    }
    mList[listSize] = m;
    listSize++;
}
```

**ArrayList**
```java
public void addMember(MemberDTO m) {
    mList.add(m);  // 간단!
}
```

### 예제 2: 회원 삭제 후 정리

**배열**
```java
public void deleteMember(int index) {
    mList[index] = null;
    // 빈 공간이 남음, 나중에 정리 필요
    // 또는 뒤의 요소들을 앞으로 이동시키는 복잡한 로직 필요
}
```

**ArrayList**
```java
public void deleteMember(int index) {
    mList.remove(index);  // 자동으로 정리됨
}
```

### 예제 3: 회원 검색

**배열**
```java
public MemberDTO findMember(String name) {
    for (int i = 0; i < mList.length; i++) {
        if(mList[i] != null && mList[i].getName().equals(name)) {
            return mList[i];
        }
    }
    return null;
}
```

**ArrayList**
```java
public MemberDTO findMember(String name) {
    for (MemberDTO m : mList) {  // 향상된 for문
        if(m.getName().equals(name)) {
            return m;
        }
    }
    return null;
}
```

## 5. 주의사항

### 5.1 인덱스 범위 체크

```java
// 배열: ArrayIndexOutOfBoundsException 가능
mList[10] = m;  // 크기가 10이면 인덱스 10은 범위 초과

// ArrayList: IndexOutOfBoundsException 가능
mList.get(10);  // 크기가 10이면 인덱스 10은 범위 초과 (0~9만 유효)
```

**안전한 접근:**
```java
if(index >= 0 && index < mList.size()) {
    MemberDTO m = mList.get(index);
}
```

### 5.2 null 체크

```java
// ArrayList도 null 요소 저장 가능 (하지만 권장하지 않음)
mList.add(null);  // 가능하지만 피해야 함

// 안전한 접근
for (MemberDTO m : mList) {
    if(m != null) {  // null 체크 필요
        System.out.println(m.getName());
    }
}
```

### 5.3 삭제 시 주의

```java
// 반복문 중 삭제 시 주의
for (int i = 0; i < mList.size(); i++) {
    if(조건) {
        mList.remove(i);  // 인덱스가 변경되므로 주의!
        i--;  // 인덱스 조정 필요
    }
}

// 역순으로 삭제 (더 안전)
for (int i = mList.size() - 1; i >= 0; i--) {
    if(조건) {
        mList.remove(i);  // 뒤에서부터 삭제하면 인덱스 문제 없음
    }
}
```

## 6. 성능 비교

| 작업 | 배열 | ArrayList |
|------|------|-----------|
| 접근 | O(1) | O(1) |
| 추가 (끝) | O(1) | O(1) (평균) |
| 삭제 (중간) | O(n) | O(n) |
| 검색 | O(n) | O(n) |
| 메모리 | 고정 | 동적 (약간의 오버헤드) |

**결론:** 대부분의 경우 성능 차이는 미미하며, ArrayList의 편의성이 더 중요함

## 7. 언제 배열을 사용해야 하나?

1. **크기가 고정된 경우**: 예) 월별 데이터 (12개)
2. **성능이 매우 중요한 경우**: 게임 엔진, 수치 계산
3. **기본 타입 배열**: `int[]`, `double[]` 등 (성능상 유리)

**대부분의 경우 ArrayList를 사용하는 것이 좋습니다!**
