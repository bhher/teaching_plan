package a0127.student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private ArrayList<Student> students;
    private String filename;
    
    public StudentManager() {
        this.students = new ArrayList<>();
        this.filename = "c:/Users/TJ/memo/student.txt";
    }

    // 출력 디렉토리(폴더) 확인 및 생성
    private void ensureDirectory() {
        File file = new File(filename);
        File parentDir = file.getParentFile(); // c:/Users/TJ/memo/
        if (parentDir != null && !parentDir.exists()) { // 폴더가 없으면
            parentDir.mkdirs(); // 폴더를 만들어라
            System.out.println("디렉토리가 생성되었습니다: " + parentDir.getPath());
            // 만들어진 폴더를 화면에 보여주세요
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        
        while (true) {
            System.out.println("\n=== 학생 성적 관리 ===");
            System.out.println("1. 학생 추가");
            System.out.println("2. 전체 조회");
            System.out.println("3. 파일 저장");
            System.out.println("4. 파일 불러오기");
            System.out.println("5. 평균 계산");
            System.out.println("6. 학생 점수 업데이트");
            System.out.println("7. 학생 삭제");
            System.out.println("8. 종료");
            System.out.print("선택 > ");
            
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
            } catch (Exception e) {
                System.out.println("숫자만 입력하세요.");
                sc.nextLine(); // 잘못된 입력 제거
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("이름 입력: ");
                    String name = sc.nextLine();
                    System.out.print("점수 입력: ");
                    try {
                        int score = sc.nextInt();
                        sc.nextLine();
                        manager.addStudent(name, score);
                    } catch (Exception e) {
                        System.out.println("점수는 숫자로 입력하세요.");
                        sc.nextLine();
                    }
                    break;
                case 2:
                    manager.printAll();
                    break;
                case 3:
                    manager.saveToFile();
                    break;
                case 4:
                    manager.loadFromFile();
                    break;
                case 5:
                    manager.calculateAverage();
                    break;
                case 6:
                    System.out.print("점수를 수정할 학생 이름 입력: ");
                    String updateName = sc.nextLine();
                    System.out.print("새 점수 입력: ");
                    try {
                        int newScore = sc.nextInt();
                        sc.nextLine();
                        manager.updateStudent(updateName, newScore);
                    } catch (Exception e) {
                        System.out.println("점수는 숫자로 입력하세요.");
                        sc.nextLine();
                    }
                    break;
                case 7:
                    System.out.print("삭제할 학생 이름 입력: ");
                    String deleteName = sc.nextLine();
                    manager.deleteStudent(deleteName);
                    break;
                case 8:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;
                default:
                    System.out.println("1~8번 중에 선택하세요.");
            }
        }
    }

    // 평균 계산
    private void calculateAverage() {
        if (students.isEmpty()) {
            System.out.println("학생이 없습니다.");
            return;
        }
        int sum = 0; // 합계 저장할 변수 sum
        for (Student student : students) {
            sum += student.getScore(); // student 객체의 점수(score)
        }
        double average = (double) sum / students.size();
        System.out.println("전체 학생 수: " + students.size() + "명");
        System.out.println("평균 점수: " + average + "점");
    }

    // 파일에서 학생 정보 읽기
    private void loadFromFile() {
        File file = new File(filename); // 파일의 위치정보
        if (!file.exists()) {
            System.out.println("파일이 없습니다. 새로 시작합니다.");
            return; // 메서드 빠져나간다.
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // 공백 삭제 후 line에 입력
                if (!line.isEmpty()) { // line(학생정보) 비어있지 않으면
                    Student student = Student.fromFileString(line); // sam|90
                    if (student != null) {
                        students.add(student);
                    }
                }
            }
            System.out.println("파일 불러오기 완료! (" + students.size() + "명)");
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }

    // 파일에 학생 정보 저장
    private void saveToFile() {
        // 디렉토리 확인 및 생성
        ensureDirectory();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                bw.write(student.toFileString());
                bw.newLine(); // 줄바꿈
            }
            System.out.println("파일 저장 완료: " + filename);
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }

    // 전체 조회
    private void printAll() {
        if (students.isEmpty()) { // 학생이 없다면 printAll 빠져나감
            System.out.println("등록된 학생이 없습니다.");
            return;
        }
        System.out.println("=== 학생 목록 ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }

    // 학생 추가
    private void addStudent(String name, int score) {
        students.add(new Student(name, score));
        System.out.println("학생이 추가되었습니다.");
        // ArrayList에 추가
    }

    // 학생 찾기 (이름으로)
    private Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    // 학생 점수 업데이트
    private void updateStudent(String name, int newScore) {
        Student student = findStudent(name);
        if (student != null) {
            // Student 클래스에 setScore 메서드가 있다고 가정
            // 만약 없다면 Student 클래스를 수정해야 함
            student.setScore(newScore);
            System.out.println(name + " 학생의 점수가 " + newScore + "점으로 수정되었습니다.");
            saveToFile(); // 수정 후 자동 저장
        } else {
            System.out.println("해당 이름의 학생을 찾을 수 없습니다.");
        }
    }

    // 학생 삭제
    private void deleteStudent(String name) {
        Student student = findStudent(name);
        if (student != null) {
            students.remove(student);
            System.out.println(name + " 학생이 삭제되었습니다.");
            saveToFile(); // 삭제 후 자동 저장
        } else {
            System.out.println("해당 이름의 학생을 찾을 수 없습니다.");
        }
    }
}
