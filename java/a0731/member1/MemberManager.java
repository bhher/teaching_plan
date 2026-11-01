package a0731.member1;

import java.util.Scanner;

public class MemberManager {
    // 최대 100명의 회원을 저장할 수 있는 배열과 카운터 변수
    static Member[] memberList = new Member[100];
    static int memberCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    createMember(scanner); // C
                    break;
                case 2:
                    readMembers();         // R
                    break;
                case 3:
                    updateMember(scanner); // U
                    break;
                case 4:
                    deleteMember(scanner); // D
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

    // 1. C (Create)
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

    // 2. R (Read)
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

    // 3. U (Update)
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

    // 4. D (Delete)
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

        // 배열 당기기
        for (int i = index; i < memberCount - 1; i++) {
            memberList[i] = memberList[i + 1];
        }

        memberList[memberCount - 1] = null;
        memberCount--;

        System.out.println("결과: 회원이 삭제되었습니다.\n");
    }

    // 관리번호로 인덱스 찾기
    public static int findMemberIndexByNo(int no) {
        for (int i = 0; i < memberCount; i++) {
            if (memberList[i].getNo() == no) {
                return i;
            }
        }
        return -1;
    }
}
