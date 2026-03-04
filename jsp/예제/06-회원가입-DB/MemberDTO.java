package jsp.member;

import java.sql.Timestamp;
import java.util.List;

/**
 * 회원 정보를 담는 DTO (Data Transfer Object)
 */
public class MemberDTO {
    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String gender;
    private String city;
    private String bio;
    private Timestamp regDate;
    private List<String> hobbies;  // 취미 리스트
    
    // 기본 생성자
    public MemberDTO() {
    }
    
    // 전체 필드 생성자
    public MemberDTO(int id, String userId, String password, String name, 
                     String email, String gender, String city, String bio, 
                     Timestamp regDate, List<String> hobbies) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.city = city;
        this.bio = bio;
        this.regDate = regDate;
        this.hobbies = hobbies;
    }
    
    // 회원가입용 생성자 (id, regDate 제외)
    public MemberDTO(String userId, String password, String name, 
                     String email, String gender, String city, String bio, 
                     List<String> hobbies) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.city = city;
        this.bio = bio;
        this.hobbies = hobbies;
    }
    
    // Getter와 Setter 메서드
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public Timestamp getRegDate() {
        return regDate;
    }
    
    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
    
    public List<String> getHobbies() {
        return hobbies;
    }
    
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
    
    // 유틸리티 메서드
    /**
     * 취미를 쉼표로 구분된 문자열로 반환
     */
    public String getHobbiesAsString() {
        if (hobbies == null || hobbies.isEmpty()) {
            return "";
        }
        return String.join(", ", hobbies);
    }
    
    /**
     * 이메일이 있는지 확인
     */
    public boolean hasEmail() {
        return email != null && !email.trim().isEmpty();
    }
    
    /**
     * 취미가 있는지 확인
     */
    public boolean hasHobbies() {
        return hobbies != null && !hobbies.isEmpty();
    }
    
    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", regDate=" + regDate +
                ", hobbies=" + hobbies +
                '}';
    }
}
