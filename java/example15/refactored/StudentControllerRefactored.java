/**
 * 리팩토링된 StudentController
 * 코드 리팩토링 예제를 보여줍니다
 */
package controller;

import model.Student;
import service.StudentService;
import repository.StudentRepository;
import java.util.List;
import java.util.Scanner;

public class StudentControllerRefactored {
    private StudentService studentService;
    private StudentRepository studentRepository;
    private Scanner scanner;
    
    // 상수 추출
    private static final String MENU_SEPARATOR = "=== 메뉴 ===";
    private static final String STUDENT_NOT_FOUND = "학생을 찾을 수 없습니다.";
    
    public StudentControllerRefactored(StudentService studentService, 
                                       StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 메인 실행 메서드
     */
    public void run() {
        System.out.println("=== 학생 관리 시스템 ===");
        
        while (true) {
            printMenu();
            int choice = getChoice();
            
            if (choice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            
            processMenu(choice);
            System.out.println();
        }
        
        scanner.close();
    }
    
    /**
     * 메뉴 출력 (메서드 추출)
     */
    private void printMenu() {
        System.out.println("\n" + MENU_SEPARATOR);
        System.out.println("1. 학생 등록");
        System.out.println("2. 학생 조회 (전체)");
        System.out.println("3. 학생 조회 (학번)");
        System.out.println("4. 학생 수정");
        System.out.println("5. 학생 삭제");
        System.out.println("6. 파일 저장");
        System.out.println("7. 파일 불러오기");
        System.out.println("0. 종료");
        System.out.print("선택: ");
    }
    
    /**
     * 사용자 선택 입력 받기 (메서드 추출)
     */
    private int getChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
    
    /**
     * 메뉴 처리 (switch 문)
     */
    private void processMenu(int choice) {
        switch (choice) {
            case 1 -> addStudent();
            case 2 -> showAllStudents();
            case 3 -> findStudent();
            case 4 -> updateStudent();
            case 5 -> deleteStudent();
            case 6 -> saveToFile();
            case 7 -> loadFromFile();
            default -> System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
        }
    }
    
    /**
     * 학생 등록 (입력 검증 강화)
     */
    private void addStudent() {
        System.out.println("\n=== 학생 등록 ===");
        
        String studentId = inputStudentId("학번");
        if (studentId == null) return;
        
        String name = inputName("이름");
        if (name == null) return;
        
        int age = inputAge("나이");
        if (age < 0) return;
        
        String major = inputMajor("전공");
        if (major == null) return;
        
        Student student = new Student(studentId, name, age, major);
        studentService.addStudent(student);
    }
    
    /**
     * 전체 학생 조회 (메서드 추출)
     */
    private void showAllStudents() {
        System.out.println("\n=== 전체 학생 조회 ===");
        
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("등록된 학생이 없습니다.");
            return;
        }
        
        printStudentList(students);
    }
    
    /**
     * 학번으로 학생 조회 (메서드 추출)
     */
    private void findStudent() {
        System.out.println("\n=== 학생 조회 (학번) ===");
        
        String studentId = inputStudentId("학번");
        if (studentId == null) return;
        
        Student student = studentService.findStudent(studentId);
        
        if (student == null) {
            System.out.println(STUDENT_NOT_FOUND + " (학번: " + studentId + ")");
        } else {
            printStudentInfo(student);
        }
    }
    
    /**
     * 학생 정보 수정 (메서드 분리)
     */
    private void updateStudent() {
        System.out.println("\n=== 학생 수정 ===");
        
        String studentId = inputStudentId("수정할 학번");
        if (studentId == null) return;
        
        Student existingStudent = studentService.findStudent(studentId);
        if (existingStudent == null) {
            System.out.println(STUDENT_NOT_FOUND + " (학번: " + studentId + ")");
            return;
        }
        
        System.out.println("현재 정보: " + existingStudent);
        System.out.println("\n새로운 정보를 입력하세요 (엔터 시 기존 값 유지):");
        
        String name = inputNameWithDefault("이름", existingStudent.getName());
        if (name == null) return;
        
        int age = inputAgeWithDefault("나이", existingStudent.getAge());
        if (age < 0) return;
        
        String major = inputMajorWithDefault("전공", existingStudent.getMajor());
        if (major == null) return;
        
        studentService.updateStudent(studentId, name, age, major);
    }
    
    /**
     * 학생 삭제 (확인 로직 분리)
     */
    private void deleteStudent() {
        System.out.println("\n=== 학생 삭제 ===");
        
        String studentId = inputStudentId("삭제할 학번");
        if (studentId == null) return;
        
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println(STUDENT_NOT_FOUND + " (학번: " + studentId + ")");
            return;
        }
        
        System.out.println("삭제할 학생 정보: " + student);
        
        if (confirmAction("정말 삭제하시겠습니까?")) {
            studentService.deleteStudent(studentId);
        } else {
            System.out.println("삭제가 취소되었습니다.");
        }
    }
    
    /**
     * 파일 저장 (메서드 추출)
     */
    private void saveToFile() {
        System.out.println("\n=== 파일 저장 ===");
        
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("저장할 학생이 없습니다.");
            return;
        }
        
        boolean success = studentRepository.saveToFile(students);
        if (success) {
            System.out.println("파일 저장이 완료되었습니다.");
        }
    }
    
    /**
     * 파일 불러오기 (로직 분리)
     */
    private void loadFromFile() {
        System.out.println("\n=== 파일 불러오기 ===");
        
        List<Student> loadedStudents = studentRepository.loadFromFile();
        
        if (loadedStudents.isEmpty()) {
            System.out.println("불러올 학생이 없습니다.");
            return;
        }
        
        if (confirmAction("기존 데이터를 덮어쓰시겠습니까?")) {
            loadAndReplace(loadedStudents);
        } else {
            loadAndMerge(loadedStudents);
        }
    }
    
    // === 입력 헬퍼 메서드들 (메서드 추출) ===
    
    private String inputStudentId(String prompt) {
        System.out.print(prompt + ": ");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("오류: " + prompt + "을(를) 입력해주세요.");
            return null;
        }
        return value;
    }
    
    private String inputName(String prompt) {
        System.out.print(prompt + ": ");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("오류: " + prompt + "을(를) 입력해주세요.");
            return null;
        }
        return value;
    }
    
    private int inputAge(String prompt) {
        System.out.print(prompt + ": ");
        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("오류: 숫자를 입력해주세요.");
            return -1;
        }
    }
    
    private String inputMajor(String prompt) {
        System.out.print(prompt + ": ");
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            System.out.println("오류: " + prompt + "을(를) 입력해주세요.");
            return null;
        }
        return value;
    }
    
    private String inputNameWithDefault(String prompt, String defaultValue) {
        System.out.print(prompt + " [" + defaultValue + "]: ");
        String value = scanner.nextLine().trim();
        return value.isEmpty() ? defaultValue : value;
    }
    
    private int inputAgeWithDefault(String prompt, int defaultValue) {
        System.out.print(prompt + " [" + defaultValue + "]: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자를 입력해주세요.");
            return -1;
        }
    }
    
    private String inputMajorWithDefault(String prompt, String defaultValue) {
        System.out.print(prompt + " [" + defaultValue + "]: ");
        String value = scanner.nextLine().trim();
        return value.isEmpty() ? defaultValue : value;
    }
    
    // === 출력 헬퍼 메서드들 (메서드 추출) ===
    
    private void printStudentList(List<Student> students) {
        System.out.println("총 " + students.size() + "명");
        System.out.println("----------------------------------------");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
    
    private void printStudentInfo(Student student) {
        System.out.println("학생 정보:");
        System.out.println(student);
    }
    
    // === 유틸리티 메서드들 (메서드 추출) ===
    
    private boolean confirmAction(String message) {
        System.out.print(message + " (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        return confirm.equals("y") || confirm.equals("yes");
    }
    
    private void loadAndReplace(List<Student> students) {
        studentService.clear();
        for (Student student : students) {
            studentService.addStudent(student);
        }
        System.out.println("파일 불러오기가 완료되었습니다.");
    }
    
    private void loadAndMerge(List<Student> students) {
        int addedCount = 0;
        for (Student student : students) {
            if (studentService.addStudent(student)) {
                addedCount++;
            }
        }
        System.out.println(addedCount + "명의 학생이 추가되었습니다.");
    }
}

