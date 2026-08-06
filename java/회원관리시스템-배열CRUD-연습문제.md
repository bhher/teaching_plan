# 회원 관리 시스템 (배열 + CRUD) - 연습문제

`BookManager`와 같은 구조로 **회원 관리 CRUD** 프로그램을 작성하세요.  
입력은 모두 **`Scanner.nextLine()`** 을 사용합니다. (숫자도 `Integer.parseInt(scanner.nextLine())`)

---

## 패키지

- `a0731.member1`

---

## 1. Member 클래스

### 필드 (private)

| 필드 | 타입 | 설명 |
|------|------|------|
| `no` | `int` | 관리번호 |
| `name` | `String` | 회원 이름 |
| `email` | `String` | 이메일 |
| `age` | `int` | 나이 |

### 생성자

```java
public Member(int no, String name, String email, int age)
```

### Getter / Setter

- 모든 필드의 Getter
- `name`, `email`, `age` 의 Setter  
  (`no`는 등록 시 자동 부여이므로 Setter 생략 가능)

---

## 2. MemberManager 클래스

### 필드

```java
static Member[] memberList = new Member[100];
static int memberCount = 0;
```

### 메뉴

```
==================================================
 1. 회원등록(C) | 2. 회원조회(R) | 3. 회원수정(U) | 4. 회원삭제(D) | 5. 종료
==================================================
선택>
```

### 기능 요구사항

#### 1) 회원등록 (Create)

- 배열이 가득 찼으면 등록 불가 메시지 출력
- 관리번호는 `memberCount + 1` 로 자동 부여
- 이름, 이메일, 나이를 **nextLine** 으로 입력
- 나이는 `Integer.parseInt(scanner.nextLine())` 사용
- `Member` 객체를 만들어 `memberList`에 저장 후 `memberCount` 증가

#### 2) 회원조회 (Read)

- 등록된 회원이 없으면 안내 메시지 출력
- 있으면 관리번호, 이름, 이메일, 나이를 모두 출력

#### 3) 회원수정 (Update)

- 먼저 전체 목록 출력
- 수정할 **관리번호** 입력
- 해당 회원을 찾아 이름, 이메일, 나이를 다시 입력받아 Setter로 수정
- 없으면 "일치하는 관리번호의 회원이 없습니다." 출력

#### 4) 회원삭제 (Delete)

- 먼저 전체 목록 출력
- 삭제할 **관리번호** 입력
- 찾으면 배열 당기기(뒤 요소를 앞으로 한 칸씩 이동) 후 삭제
- `memberList[memberCount - 1] = null`, `memberCount--`

#### 5) 종료

- 프로그램 종료

### 필수 메서드

```java
public static void printMenu()
public static void createMember(Scanner scanner)
public static void readMembers()
public static void updateMember(Scanner scanner)
public static void deleteMember(Scanner scanner)
public static int findMemberIndexByNo(int no)  // 없으면 -1
```

---

## 입력 규칙 (중요)

| 잘못된 방식 | 올바른 방식 |
|-------------|-------------|
| `scanner.nextInt()` | `Integer.parseInt(scanner.nextLine())` |
| `scanner.next()` | `scanner.nextLine()` |

이름에 공백이 있어도 한 줄로 입력받을 수 있어야 합니다.

예:

```
이름: 홍 길동
이메일: hong@test.com
나이: 25
```

---

## 실행 흐름 예시

```
==================================================
 1. 회원등록(C) | 2. 회원조회(R) | 3. 회원수정(U) | 4. 회원삭제(D) | 5. 종료
==================================================
선택> 1

--- [회원 등록] ---
이름: 홍길동
이메일: hong@test.com
나이: 25
성공: '홍길동' 회원이 등록되었습니다. (관리번호: 1)

선택> 2

--- [전체 회원 목록] ---
관리번호: 1 | 이름: 홍길동 | 이메일: hong@test.com | 나이: 25세
```

---

## BookManager와 비교

| 항목 | BookManager | MemberManager |
|------|-------------|---------------|
| 배열 | `Book[] bookList` | `Member[] memberList` |
| 카운터 | `bookCount` | `memberCount` |
| 입력 | `next()`, `nextInt()` | **모두 `nextLine()`** |
| 수정/삭제 키 | 관리번호 | 관리번호 |

---

## 참고

- 풀이: [회원관리시스템-배열CRUD-연습문제-풀이.md](./회원관리시스템-배열CRUD-연습문제-풀이.md)
- 소스: [a0731/member1/](./a0731/member1/)
