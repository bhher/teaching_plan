import java.time.LocalDate;

/**
 * Member 클래스 테스트를 위한 Main 파일
 * 회원 정보 출력 메서드 예제를 실행
 */
public class MemberMain {
    public static void main(String[] args) {
        System.out.println("========== 회원 정보 출력 예제 ==========\n");
        
        // 회원 객체 생성 (다양한 가입일로 등급 테스트)
        Member member1 = new Member("이영희", "lee@example.com", 
                                   LocalDate.of(2023, 1, 1));  // VIP 등급
        Member member2 = new Member("박민수", "park@example.com", 
                                   LocalDate.of(2024, 6, 1));  // Bronze 등급
        Member member3 = new Member("김철수", "kim@example.com", 
                                   LocalDate.of(2023, 7, 1));  // Gold 등급
        Member member4 = new Member("최지영", "choi@example.com", 
                                   LocalDate.of(2024, 3, 1));  // Silver 등급
        
        // 회원1 정보 출력
        member1.printMemberInfo();
        System.out.println();
        
        // 회원2 정보 출력
        member2.printMemberInfo();
        System.out.println();
        
        // 회원3 간단한 정보 출력
        member3.printSimpleInfo();
        System.out.println();
        
        // 회원4 환영 메시지 출력
        member4.printWelcomeMessage();
        
        System.out.println("\n========== 개별 메서드 테스트 ==========");
        
        // 가입 기간 (일수) 계산 테스트
        System.out.println(member1.getName() + " 가입 기간: " + 
                          member1.getJoinDays() + "일");
        System.out.println(member2.getName() + " 가입 기간: " + 
                          member2.getJoinDays() + "일");
        System.out.println(member3.getName() + " 가입 기간: " + 
                          member3.getJoinDays() + "일");
        
        // 가입 기간 (개월수) 계산 테스트
        System.out.println(member1.getName() + " 가입 기간: " + 
                          member1.getJoinMonths() + "개월");
        System.out.println(member2.getName() + " 가입 기간: " + 
                          member2.getJoinMonths() + "개월");
        System.out.println(member3.getName() + " 가입 기간: " + 
                          member3.getJoinMonths() + "개월");
        
        // 회원 등급 계산 테스트
        System.out.println(member1.getName() + " 회원 등급: " + 
                          member1.getMemberGrade());
        System.out.println(member2.getName() + " 회원 등급: " + 
                          member2.getMemberGrade());
        System.out.println(member3.getName() + " 회원 등급: " + 
                          member3.getMemberGrade());
        System.out.println(member4.getName() + " 회원 등급: " + 
                          member4.getMemberGrade());
        
        System.out.println("\n========== Getter 메서드 테스트 ==========");
        System.out.println("이름: " + member1.getName());
        System.out.println("이메일: " + member1.getEmail());
        System.out.println("가입일: " + member1.getJoinDate());
        
        System.out.println("\n========== 모든 회원 간단 정보 ==========");
        member1.printSimpleInfo();
        member2.printSimpleInfo();
        member3.printSimpleInfo();
        member4.printSimpleInfo();
        
        System.out.println("\n========== 회원 등급별 분류 ==========");
        System.out.println("VIP 등급 회원:");
        if (member1.getMemberGrade().equals("VIP")) {
            member1.printSimpleInfo();
        }
        if (member3.getMemberGrade().equals("VIP")) {
            member3.printSimpleInfo();
        }
        
        System.out.println("\nGold 등급 회원:");
        if (member3.getMemberGrade().equals("Gold")) {
            member3.printSimpleInfo();
        }
        
        System.out.println("\nSilver 등급 회원:");
        if (member4.getMemberGrade().equals("Silver")) {
            member4.printSimpleInfo();
        }
        
        System.out.println("\nBronze 등급 회원:");
        if (member2.getMemberGrade().equals("Bronze")) {
            member2.printSimpleInfo();
        }
    }
}






