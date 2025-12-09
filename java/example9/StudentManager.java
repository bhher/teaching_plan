import java.util.*;

/**
 * 학생 관리 시스템 실전 예제
 * List, Set, Map을 활용한 종합 예제
 */
public class StudentManager {
    // 학생 클래스
    static class Student {
        private String name;
        private int age;
        private int score;
        
        public Student(String name, int age, int score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public int getScore() { return score; }
        
        @Override
        public String toString() {
            return name + " (나이: " + age + ", 점수: " + score + ")";
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 학생 관리 시스템 ===\n");
        
        // 학생 리스트
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("홍길동", 20, 85));
        students.add(new Student("김철수", 21, 90));
        students.add(new Student("이영희", 20, 88));
        students.add(new Student("박민수", 22, 92));
        students.add(new Student("최지영", 19, 78));
        
        System.out.println("--- 전체 학생 목록 ---");
        for (Student student : students) {
            System.out.println(student);
        }
        
        // 점수별로 그룹화 (Map 사용)
        System.out.println("\n--- 점수별 그룹화 ---");
        HashMap<String, ArrayList<Student>> scoreGroups = new HashMap<>();
        scoreGroups.put("A등급", new ArrayList<>());
        scoreGroups.put("B등급", new ArrayList<>());
        scoreGroups.put("C등급", new ArrayList<>());
        scoreGroups.put("F등급", new ArrayList<>());
        
        for (Student student : students) {
            String grade = getGrade(student.getScore());
            scoreGroups.get(grade).add(student);
        }
        
        for (Map.Entry<String, ArrayList<Student>> entry : scoreGroups.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (Student student : entry.getValue()) {
                System.out.println("  - " + student.getName() + " (" + student.getScore() + "점)");
            }
        }
        
        // 나이별 통계
        System.out.println("\n--- 나이별 통계 ---");
        HashMap<Integer, Integer> ageCount = new HashMap<>();
        for (Student student : students) {
            ageCount.put(student.getAge(), ageCount.getOrDefault(student.getAge(), 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : ageCount.entrySet()) {
            System.out.println(entry.getKey() + "세: " + entry.getValue() + "명");
        }
        
        // 평균 점수 계산
        System.out.println("\n--- 통계 ---");
        int totalScore = 0;
        for (Student student : students) {
            totalScore += student.getScore();
        }
        double average = (double) totalScore / students.size();
        System.out.println("평균 점수: " + String.format("%.2f", average));
        
        // 최고점 학생 찾기
        Student topStudent = students.get(0);
        for (Student student : students) {
            if (student.getScore() > topStudent.getScore()) {
                topStudent = student;
            }
        }
        System.out.println("최고점 학생: " + topStudent.getName() + " (" + topStudent.getScore() + "점)");
        
        // 점수 순으로 정렬
        System.out.println("\n--- 점수 순 정렬 ---");
        students.sort((s1, s2) -> s2.getScore() - s1.getScore());  // 내림차순
        for (Student student : students) {
            System.out.println(student.getName() + ": " + student.getScore() + "점");
        }
        
        // 이름 중복 확인 (Set 사용)
        System.out.println("\n--- 이름 중복 확인 ---");
        HashSet<String> nameSet = new HashSet<>();
        for (Student student : students) {
            if (!nameSet.add(student.getName())) {
                System.out.println("중복된 이름 발견: " + student.getName());
            }
        }
        System.out.println("고유한 이름 개수: " + nameSet.size());
    }
    
    private static String getGrade(int score) {
        if (score >= 90) return "A등급";
        else if (score >= 80) return "B등급";
        else if (score >= 70) return "C등급";
        else return "F등급";
    }
}

