/**
 * StudentController 클래스
 * 사용자 입력을 받아 학생 관리 기능을 제어하는 컨트롤러 클래스
 */
package controller;

import model.Student;
import service.StudentService;
import repository.StudentRepository;
import java.util.List;
import java.util.Scanner;

public class StudentController {
    private StudentService studentService;
    private StudentRepository studentRepository;
    private Scanner scanner;
    
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
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
     * 메뉴 출력
     */
    private void printMenu() {
        System.out.println("\n=== 메뉴 ===");
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
     * 사용자 선택 입력 받기
     */
    private int getChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();  // 버퍼 비우기
            return choice;
        } catch (Exception e) {
            scanner.nextLine();  // 버퍼 비우기
            return -1;
        }
    }
    
    /**
     * 메뉴 처리
     */
    private void processMenu(int choice) {
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                showAllStudents();
                break;
            case 3:
                findStudent();
                break;
            case 4:
                updateStudent();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                saveToFile();
                break;
            case 7:
                loadFromFile();
                break;
            default:
                System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
        }
    }
    
    /**
     * 학생 등록
     */
    private void addStudent() {
        System.out.println("\n=== 학생 등록 ===");
        
        System.out.print("학번: ");
        String studentId = scanner.nextLine().trim();
        
        if (studentId.isEmpty()) {
            System.out.println("오류: 학번을 입력해주세요.");
            return;
        }
        
        System.out.print("이름: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("오류: 이름을 입력해주세요.");
            return;
        }
        
        System.out.print("나이: ");
        int age = getIntInput();
        if (age < 0) {
            return;
        }
        
        System.out.print("전공: ");
        String major = scanner.nextLine().trim();
        
        Student student = new Student(studentId, name, age, major);
        studentService.addStudent(student);
    }
    
    /**
     * 전체 학생 조회
     */
    private void showAllStudents() {
        System.out.println("\n=== 전체 학생 조회 ===");
        
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("등록된 학생이 없습니다.");
            return;
        }
        
        System.out.println("총 " + students.size() + "명");
        System.out.println("----------------------------------------");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
    
    /**
     * 학번으로 학생 조회
     */
    private void findStudent() {
        System.out.println("\n=== 학생 조회 (학번) ===");
        
        System.out.print("학번: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = studentService.findStudent(studentId);
        
        if (student == null) {
            System.out.println("학생을 찾을 수 없습니다. (학번: " + studentId + ")");
        } else {
            System.out.println("학생 정보:");
            System.out.println(student);
        }
    }
    
    /**
     * 학생 정보 수정
     */
    private void updateStudent() {
        System.out.println("\n=== 학생 수정 ===");
        
        System.out.print("수정할 학번: ");
        String studentId = scanner.nextLine().trim();
        
        Student existingStudent = studentService.findStudent(studentId);
        if (existingStudent == null) {
            System.out.println("학생을 찾을 수 없습니다. (학번: " + studentId + ")");
            return;
        }
        
        System.out.println("현재 정보: " + existingStudent);
        System.out.println("\n새로운 정보를 입력하세요 (엔터 시 기존 값 유지):");
        
        System.out.print("이름 [" + existingStudent.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = existingStudent.getName();
        }
        
        System.out.print("나이 [" + existingStudent.getAge() + "]: ");
        String ageInput = scanner.nextLine().trim();
        int age;
        if (ageInput.isEmpty()) {
            age = existingStudent.getAge();
        } else {
            age = getIntFromString(ageInput);
            if (age < 0) {
                return;
            }
        }
        
        System.out.print("전공 [" + existingStudent.getMajor() + "]: ");
        String major = scanner.nextLine().trim();
        if (major.isEmpty()) {
            major = existingStudent.getMajor();
        }
        
        studentService.updateStudent(studentId, name, age, major);
    }
    
    /**
     * 학생 삭제
     */
    private void deleteStudent() {
        System.out.println("\n=== 학생 삭제 ===");
        
        System.out.print("삭제할 학번: ");
        String studentId = scanner.nextLine().trim();
        
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("학생을 찾을 수 없습니다. (학번: " + studentId + ")");
            return;
        }
        
        System.out.println("삭제할 학생 정보: " + student);
        System.out.print("정말 삭제하시겠습니까? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("y") || confirm.equals("yes")) {
            studentService.deleteStudent(studentId);
        } else {
            System.out.println("삭제가 취소되었습니다.");
        }
    }
    
    /**
     * 파일 저장
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
     * 파일 불러오기
     */
    private void loadFromFile() {
        System.out.println("\n=== 파일 불러오기 ===");
        
        List<Student> loadedStudents = studentRepository.loadFromFile();
        
        if (loadedStudents.isEmpty()) {
            System.out.println("불러올 학생이 없습니다.");
            return;
        }
        
        System.out.print("기존 데이터를 덮어쓰시겠습니까? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("y") || confirm.equals("yes")) {
            // 기존 데이터 초기화 후 불러온 데이터로 교체
            studentService.clear();
            for (Student student : loadedStudents) {
                studentService.addStudent(student);
            }
            System.out.println("파일 불러오기가 완료되었습니다.");
        } else {
            // 기존 데이터에 추가
            int addedCount = 0;
            for (Student student : loadedStudents) {
                if (studentService.addStudent(student)) {
                    addedCount++;
                }
            }
            System.out.println(addedCount + "명의 학생이 추가되었습니다.");
        }
    }
    
    /**
     * 정수 입력 받기
     */
    private int getIntInput() {
        try {
            int value = scanner.nextInt();
            scanner.nextLine();  // 버퍼 비우기
            return value;
        } catch (Exception e) {
            scanner.nextLine();  // 버퍼 비우기
            System.out.println("오류: 숫자를 입력해주세요.");
            return -1;
        }
    }
    
    /**
     * 문자열을 정수로 변환
     */
    private int getIntFromString(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자를 입력해주세요.");
            return -1;
        }
    }
}

