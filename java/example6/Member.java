import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 회원 정보 출력 메서드 예제
 * 객체가 자신의 정보를 스스로 출력하도록 설계
 */
public class Member {
    // 필드 (속성)
    private String name;
    private String email;
    private LocalDate joinDate;
    
    // 생성자
    public Member(String name, String email, LocalDate joinDate) {
        this.name = name;
        this.email = email;
        this.joinDate = joinDate;
    }
    
    // Getter 메서드들
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public LocalDate getJoinDate() {
        return joinDate;
    }
    
    // 계산 메서드: 가입 기간 (일수)
    public long getJoinDays() {
        return ChronoUnit.DAYS.between(joinDate, LocalDate.now());
    }
    
    // 계산 메서드: 가입 기간 (개월수)
    public long getJoinMonths() {
        return ChronoUnit.MONTHS.between(joinDate, LocalDate.now());
    }
    
    // 계산 메서드: 회원 등급
    public String getMemberGrade() {
        long days = getJoinDays();
        if (days >= 365) return "VIP";
        else if (days >= 180) return "Gold";
        else if (days >= 90) return "Silver";
        else return "Bronze";
    }
    
    // 출력 메서드: 회원 정보 출력
    public void printMemberInfo() {
        System.out.println("=== 회원 정보 ===");
        System.out.println("이름: " + name);
        System.out.println("이메일: " + email);
        System.out.println("가입일: " + joinDate);
        System.out.println("가입 기간: " + getJoinDays() + "일 (" + getJoinMonths() + "개월)");
        System.out.println("회원 등급: " + getMemberGrade());
    }
    
    // 출력 메서드: 간단한 정보 출력
    public void printSimpleInfo() {
        System.out.println(name + " (" + email + ") - " + getMemberGrade());
    }
    
    // 출력 메서드: 환영 메시지 출력
    public void printWelcomeMessage() {
        System.out.println("환영합니다, " + name + "님!");
        System.out.println("현재 " + getMemberGrade() + " 등급입니다.");
    }
}






