# 회원 관리 시스템 - ArrayList 변환 문제

**총점: 100점**

**시험 시간: 120분**

**주의사항:**
- 각 클래스는 독립적인 파일로 작성하세요
- 클래스명과 메서드명은 정확히 지켜주세요
- 컴파일 오류가 발생하면 해당 부분은 0점 처리됩니다
- ArrayList를 사용하여 배열의 제한을 제거하세요

---

## 문제: 배열 기반 회원 관리 시스템을 ArrayList로 변경

### 문제 설명

기존에 배열(`MemberDTO[]`)을 사용하던 회원 관리 시스템을 `ArrayList<MemberDTO>`로 변경하여 구현하세요.
배열의 크기 제한(10개)을 제거하고, 동적으로 회원을 추가/삭제할 수 있도록 수정하세요.

### 기존 코드 구조

**MemberDTO 클래스** (변경 없음)
```java
package day9.example01;

public class MemberDTO {
    private int id;
    private String name;
    private int age;
    private String address;
    
    // 기본 생성자, getter, setter, toString() 메서드
}
```

**FileClass 클래스** (변경 없음)
- 파일 생성, 읽기, 쓰기 기능 제공

### 요구사항

#### 1. DAO 클래스 수정 (80점)

**필드 변경**
- 기존: `private MemberDTO mList[] = new MemberDTO[10];`
- 변경: `private ArrayList<MemberDTO> mList;`
- `listSize` 변수 제거 (ArrayList의 `size()` 메서드 사용)

**생성자 수정**
- ArrayList 초기화
- 테스트 데이터 3개 추가 (기존과 동일)

**CRUD 메서드 수정**

**insert 메서드 (10점)**
```java
private void insert(MemberDTO m)
```
- ArrayList의 `add()` 메서드 사용
- `listSize++` 제거

**delete 메서드 (15점)**
```java
private void delete(int index)
```
- ArrayList의 `remove(index)` 메서드 사용
- `mList[index] = null` 대신 `remove()` 사용

**select 메서드 (5점)**
```java
private MemberDTO select(int index)
```
- `mList[index]` 대신 `mList.get(index)` 사용

**update 메서드 (10점)**
```java
private void update(int index, MemberDTO m)
```
- `mList[index] = m` 대신 `mList.set(index, m)` 사용

**printAll 메서드 (15점)**
```java
public void printAll()
```
- `mList.length` 대신 `mList.size()` 사용
- `mList[i]` 대신 `mList.get(i)` 사용
- null 체크 대신 ArrayList의 요소 직접 접근
- 주소 출력 오류 수정 (기존 코드에 `getAge()` 대신 `getAddress()` 사용)

**findIndex 메서드 (10점)**
```java
private int findIndex()
```
- `mList.length` 대신 `mList.size()` 사용
- `mList[i]` 대신 `mList.get(i)` 사용
- null 체크 제거 (ArrayList는 null 요소를 저장하지 않음)

**userInsert 메서드 (10점)**
```java
public void userInsert()
```
- `listSize < 10` 조건 제거 (ArrayList는 크기 제한 없음)
- `listSize++` 제거

**dataSave 메서드 (5점)**
```java
public void dataSave() throws Exception
```
- `mList.length` 대신 `mList.size()` 사용
- `mList[i]` 대신 `mList.get(i)` 사용

#### 2. 필요한 import 문 추가

```java
import java.util.ArrayList;
```

### 실행 결과 예시

```
1. 회원정보 입력
2. 회원정보 삭제
3. 회원정보 검색
4. 회원정보 수정
5. 회원 목록 보기
6. 파일로 저장하기
7. 회원정보 파일 불러오기
0. 종료
>> 5
< 전체 회원목록 >
< 1. test1 회원 >
회원번호 : 0
나이: 11
주소: 서울
< 2. test2 회원 >
회원번호 : 1
나이: 22
주소: 부산
< 3. test3 회원 >
회원번호 : 2
나이: 33
주소: 대구
```

### 채점 기준

| 항목 | 배점 | 평가 내용 |
|------|------|-----------|
| 필드 및 생성자 수정 | 10점 | ArrayList 선언 및 초기화, listSize 제거 |
| insert 메서드 | 10점 | ArrayList의 add() 사용 |
| delete 메서드 | 15점 | ArrayList의 remove() 사용 |
| select 메서드 | 5점 | ArrayList의 get() 사용 |
| update 메서드 | 10점 | ArrayList의 set() 사용 |
| printAll 메서드 | 15점 | size(), get() 사용 및 오류 수정 |
| findIndex 메서드 | 10점 | size(), get() 사용 |
| userInsert 메서드 | 10점 | 크기 제한 제거 |
| dataSave 메서드 | 5점 | size(), get() 사용 |
| import 문 | 5점 | ArrayList import 추가 |
| **총점** | **100점** | |

### 평가 내용

1. **ArrayList 사용 (40점)**
   - 배열 대신 ArrayList 선언 및 초기화
   - 배열 인덱스 접근(`[]`) 대신 메서드 사용(`get()`, `set()`, `add()`, `remove()`)
   - `length` 대신 `size()` 사용

2. **크기 제한 제거 (15점)**
   - `listSize` 변수 제거
   - 크기 제한 조건 제거 (`listSize < 10` 등)

3. **메서드 수정 (35점)**
   - 모든 메서드에서 ArrayList 메서드 정확히 사용
   - null 체크 로직 수정

4. **오류 수정 (10점)**
   - printAll 메서드의 주소 출력 오류 수정
   - 예외 처리 개선

### 제출 방법

1. `MemberDTO.java` 파일 (변경 없음)
2. `DAO.java` 파일 (수정된 버전)
3. `FileClass.java` 파일 (변경 없음)
4. `mainClass.java` 파일 (변경 없음)

### 힌트

- `ArrayList<MemberDTO> mList = new ArrayList<>();`로 선언
- `mList.add(m)`: 요소 추가
- `mList.remove(index)`: 인덱스로 요소 제거
- `mList.get(index)`: 인덱스로 요소 가져오기
- `mList.set(index, m)`: 인덱스로 요소 수정
- `mList.size()`: 리스트 크기 반환
- `mList.isEmpty()`: 리스트가 비어있는지 확인
