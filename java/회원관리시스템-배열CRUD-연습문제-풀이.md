# 회원 관리 시스템 (배열 + CRUD) - 풀이

문제 원본: [회원관리시스템-배열CRUD-연습문제.md](./회원관리시스템-배열CRUD-연습문제.md)

`BookManager`와 같은 CRUD 구조이며, 입력은 모두 **`nextLine()`** 을 사용합니다.

---

## Member.java

```java
package a0731.member1;

public class Member {
    private int no;
    private String name;
    private String email;
    private int age;

    public Member(int no, String name, String email, int age) {
        this.no = no;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

---

## MemberManager.java

```java
package a0731.member1;

import java.util.Scanner;

public class MemberManager {
    static Member[] memberList = new Member[100];
    static int memberCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            printMenu();
            // nextInt() 대신 nextLine() + parseInt
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createMember(scanner);
                    break;
                case 2:
                    readMembers();
                    break;
                case 3:
                    updateMember(scanner);
                    break;
                case 4:
                    deleteMember(scanner);
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    run = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 1~5 사이의 숫자를 입력해주세요.\n");
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("==================================================");
        System.out.println(" 1. 회원등록(C) | 2. 회원조회(R) | 3. 회원수정(U) | 4. 회원삭제(D) | 5. 종료 ");
        System.out.println("==================================================");
        System.out.print("선택> ");
    }

    public static void createMember(Scanner scanner) {
        System.out.println("\n--- [회원 등록] ---");
        if (memberCount >= 100) {
            System.out.println("더 이상 회원을 등록할 수 없습니다. (저장 공간 부족)\n");
            return;
        }

        int no = memberCount + 1;

        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("이메일: ");
        String email = scanner.nextLine();

        System.out.print("나이: ");
        int age = Integer.parseInt(scanner.nextLine());

        memberList[memberCount] = new Member(no, name, email, age);
        memberCount++;

        System.out.println("성공: '" + name + "' 회원이 등록되었습니다. (관리번호: " + no + ")\n");
    }

    public static void readMembers() {
        System.out.println("\n--- [전체 회원 목록] ---");
        if (memberCount == 0) {
            System.out.println("등록된 회원이 없습니다.\n");
            return;
        }

        for (int i = 0; i < memberCount; i++) {
            Member m = memberList[i];
            System.out.printf("관리번호: %d | 이름: %s | 이메일: %s | 나이: %d세\n",
                    m.getNo(), m.getName(), m.getEmail(), m.getAge());
        }
        System.out.println();
    }

    public static void updateMember(Scanner scanner) {
        System.out.println("\n--- [회원 정보 수정] ---");
        readMembers();
        if (memberCount == 0) return;

        System.out.print("수정할 회원의 관리번호 입력: ");
        int targetNo = Integer.parseInt(scanner.nextLine());

        int index = findMemberIndexByNo(targetNo);

        if (index == -1) {
            System.out.println("결과: 일치하는 관리번호의 회원이 없습니다.\n");
            return;
        }

        System.out.println("현재 선택된 회원: " + memberList[index].getName()
                + " (" + memberList[index].getEmail() + ")");

        System.out.print("새로운 이름: ");
        String newName = scanner.nextLine();

        System.out.print("새로운 이메일: ");
        String newEmail = scanner.nextLine();

        System.out.print("새로운 나이: ");
        int newAge = Integer.parseInt(scanner.nextLine());

        memberList[index].setName(newName);
        memberList[index].setEmail(newEmail);
        memberList[index].setAge(newAge);

        System.out.println("결과: 회원 정보가 성공적으로 수정되었습니다.\n");
    }

    public static void deleteMember(Scanner scanner) {
        System.out.println("\n--- [회원 삭제] ---");
        readMembers();
        if (memberCount == 0) return;

        System.out.print("삭제할 회원의 관리번호 입력: ");
        int targetNo = Integer.parseInt(scanner.nextLine());

        int index = findMemberIndexByNo(targetNo);

        if (index == -1) {
            System.out.println("결과: 일치하는 관리번호의 회원이 없습니다.\n");
            return;
        }

        for (int i = index; i < memberCount - 1; i++) {
            memberList[i] = memberList[i + 1];
        }

        memberList[memberCount - 1] = null;
        memberCount--;

        System.out.println("결과: 회원이 삭제되었습니다.\n");
    }

    public static int findMemberIndexByNo(int no) {
        for (int i = 0; i < memberCount; i++) {
            if (memberList[i].getNo() == no) {
                return i;
            }
        }
        return -1;
    }
}
```

---

## nextLine 사용 포인트

| 입력 | 코드 |
|------|------|
| 메뉴 번호 | `Integer.parseInt(scanner.nextLine())` |
| 이름 / 이메일 | `scanner.nextLine()` |
| 나이 / 관리번호 | `Integer.parseInt(scanner.nextLine())` |

### 왜 nextLine만 쓰나요?

1. `next()` / `nextInt()` 후 `nextLine()`을 쓰면 **버퍼에 남은 엔터** 때문에 입력이 건너뛰어질 수 있음
2. `nextLine()`만 쓰면 버퍼 문제가 거의 없음
3. 이름에 **공백**이 있어도 한 줄로 받을 수 있음 (`홍 길동`)

---

## BookManager와 대응표

| BookManager | MemberManager |
|-------------|---------------|
| `Book` | `Member` |
| `title`, `author`, `price` | `name`, `email`, `age` |
| `bookList`, `bookCount` | `memberList`, `memberCount` |
| `createBook` | `createMember` |
| `readBooks` | `readMembers` |
| `updateBook` | `updateMember` |
| `deleteBook` | `deleteMember` |
| `findBookIndexByNo` | `findMemberIndexByNo` |
| `next()`, `nextInt()` | **전부 `nextLine()`** |

---

## 실행 방법

```bash
cd java
javac a0731/member1/Member.java a0731/member1/MemberManager.java
java a0731.member1.MemberManager
```

---

## 소스 파일 위치

- [a0731/member1/Member.java](./a0731/member1/Member.java)
- [a0731/member1/MemberManager.java](./a0731/member1/MemberManager.java)
