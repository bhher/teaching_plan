/**
 * 객체지향 프로그래밍 종합 예제
 * 클래스, 객체, 생성자, this, 접근제어자를 모두 활용한 예제
 */
public class OOPComprehensive {
    // 학생 클래스
    public static class Student {
        private String name;
        private int age;
        private String major;
        private double[] scores;
        
        // 생성자
        public Student(String name, int age, String major) {
            this.name = name;
            this.age = age;
            this.major = major;
            this.scores = new double[3];
        }
        
        // Getter
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getMajor() { return major; }
        
        // Setter
        public void setName(String name) {
            if (name != null && !name.trim().isEmpty()) {
                this.name = name;
            }
        }
        
        public void setAge(int age) {
            if (age >= 0 && age <= 150) {
                this.age = age;
            }
        }
        
        // 점수 설정
        public void setScores(double korean, double math, double english) {
            scores[0] = korean;
            scores[1] = math;
            scores[2] = english;
        }
        
        // 평균 계산
        public double calculateAverage() {
            double sum = 0;
            for (double score : scores) {
                sum += score;
            }
            return sum / scores.length;
        }
        
        // 등급 계산
        public String getGrade() {
            double avg = calculateAverage();
            if (avg >= 90) return "A";
            else if (avg >= 80) return "B";
            else if (avg >= 70) return "C";
            else if (avg >= 60) return "D";
            else return "F";
        }
        
        // 정보 출력
        public void displayInfo() {
            System.out.println("이름: " + name);
            System.out.println("나이: " + age);
            System.out.println("전공: " + major);
            System.out.println("국어: " + scores[0] + ", 수학: " + scores[1] + ", 영어: " + scores[2]);
            System.out.println("평균: " + String.format("%.2f", calculateAverage()));
            System.out.println("등급: " + getGrade());
        }
    }
    
    // 직사각형 클래스
    public static class Rectangle {
        private double width;
        private double height;
        
        // 생성자
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        // Getter
        public double getWidth() { return width; }
        public double getHeight() { return height; }
        
        // Setter
        public void setWidth(double width) {
            if (width > 0) {
                this.width = width;
            }
        }
        
        public void setHeight(double height) {
            if (height > 0) {
                this.height = height;
            }
        }
        
        // 넓이 계산
        public double calculateArea() {
            return width * height;
        }
        
        // 둘레 계산
        public double calculatePerimeter() {
            return 2 * (width + height);
        }
        
        // 정보 출력
        public void displayInfo() {
            System.out.println("너비: " + width);
            System.out.println("높이: " + height);
            System.out.println("넓이: " + calculateArea());
            System.out.println("둘레: " + calculatePerimeter());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 객체지향 프로그래밍 종합 예제 ===\n");
        
        // 학생 객체 생성
        System.out.println("--- 학생 관리 ---");
        Student student1 = new Student("홍길동", 20, "컴퓨터공학");
        student1.setScores(85, 90, 88);
        student1.displayInfo();
        
        System.out.println();
        
        Student student2 = new Student("김철수", 21, "전자공학");
        student2.setScores(92, 87, 95);
        student2.displayInfo();
        
        // 직사각형 객체 생성
        System.out.println("\n--- 직사각형 계산 ---");
        Rectangle rect1 = new Rectangle(10, 5);
        rect1.displayInfo();
        
        System.out.println();
        
        Rectangle rect2 = new Rectangle(7.5, 3.2);
        rect2.displayInfo();
        
        // Setter 사용
        System.out.println("\n--- Setter 사용 ---");
        System.out.println("rect1 너비 변경 전:");
        rect1.displayInfo();
        
        rect1.setWidth(15);
        rect1.setHeight(8);
        
        System.out.println("\nrect1 너비 변경 후:");
        rect1.displayInfo();
        
        System.out.println("\n=== 객체지향 프로그래밍의 장점 ===");
        System.out.println("1. 코드 재사용: 클래스를 재사용하여 여러 객체 생성");
        System.out.println("2. 캡슐화: private 필드로 데이터 보호");
        System.out.println("3. 유지보수: 클래스 단위로 수정 가능");
        System.out.println("4. 모듈화: 프로그램을 작은 단위로 나누어 관리");
    }
}

