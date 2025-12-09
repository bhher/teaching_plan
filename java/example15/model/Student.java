/**
 * Student 클래스
 * 학생 정보를 담는 도메인 모델 클래스
 */
package model;

public class Student {
    private String studentId;  // 학번
    private String name;        // 이름
    private int age;            // 나이
    private String major;       // 전공
    
    // 기본 생성자
    public Student() {
    }
    
    // 전체 필드 생성자
    public Student(String studentId, String name, int age, String major) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.major = major;
    }
    
    // Getter 메서드
    public String getStudentId() {
        return studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getMajor() {
        return major;
    }
    
    // Setter 메서드
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    /**
     * 학생 정보를 문자열로 반환
     */
    @Override
    public String toString() {
        return String.format("학번: %s, 이름: %s, 나이: %d, 전공: %s", 
            studentId, name, age, major);
    }
    
    /**
     * 파일 저장용 형식으로 변환
     */
    public String toFileString() {
        return studentId + "," + name + "," + age + "," + major;
    }
    
    /**
     * 파일에서 읽은 문자열로부터 Student 객체 생성
     */
    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            return new Student(
                parts[0].trim(),
                parts[1].trim(),
                Integer.parseInt(parts[2].trim()),
                parts[3].trim()
            );
        }
        return null;
    }
    
    /**
     * 학번으로 비교 (equals)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return studentId != null && studentId.equals(student.studentId);
    }
    
    /**
     * 학번의 해시코드 반환
     */
    @Override
    public int hashCode() {
        return studentId != null ? studentId.hashCode() : 0;
    }
}

