package a0127.student;

public class Student {
    private String name;
    private int score;
    
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    // 파일 저장용 문자열
    public String toFileString() {
        return name + "|" + score;
    }
    
    // 파일에서 읽은 문자열로 객체 생성
    public static Student fromFileString(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length == 2) {
                String name = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());
                return new Student(name, score);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }
    
    // Getter
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    // Setter (점수 업데이트용)
    public void setScore(int score) {
        this.score = score;
    }
    
    @Override
    public String toString() {
        return name + ": " + score + "점";
    }
}
