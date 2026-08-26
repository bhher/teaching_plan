package a0320.hak1;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        // 더미 데이터 2명
        students.add(new Student("홍길동", "2025001"));
        students.add(new Student("김영희", "2025002"));
        students.get(0).setGrade(85);
        students.get(1).setGrade(90);

        boolean condition = true;
        while (condition) {
            System.out.println("1. 학생 등록");
            System.out.println("2. 성적 입력");
            System.out.println("3. 성적 출력");
            System.out.println("4. 점수 수정");
            System.out.println("5. 학생 삭제");
            System.out.println("6. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    System.out.print("이름 입력: ");
                    String name = scanner.nextLine();
                    System.out.print("학번 입력: ");
                    String studentId = scanner.nextLine();
                    students.add(new Student(name, studentId));
                    System.out.println("학생 등록 완료!");
                    break;

                case 2:
                    System.out.println("==== 성적 입력 ====");
                    System.out.print("학번 입력: ");
                    String id = scanner.nextLine();
                    Student foundStudent = findStudentById(students, id);
                    if (foundStudent != null) {
                        System.out.print("성적 입력: ");
                        int grade = scanner.nextInt();
                        scanner.nextLine(); // 버퍼 비우기
                        foundStudent.setGrade(grade);
                        System.out.println("성적 입력 완료");
                    } else {
                        System.out.println("해당 학번을 가진 학생이 없습니다.");
                    }
                    break;

                case 3:
                    System.out.println("=== 성적 출력 ===");
                    if (students.isEmpty()) {
                        System.out.println("등록된 학생이 없습니다.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 4: // 점수 수정
                    System.out.println("==== 점수 수정 ====");
                    System.out.print("학번 입력: ");
                    String updateId = scanner.nextLine();
                    Student updateStudent = findStudentById(students, updateId);
                    if (updateStudent != null) {
                        System.out.println("현재 성적: " + updateStudent.getGrade());
                        System.out.print("새 성적 입력: ");
                        int newGrade = scanner.nextInt();
                        scanner.nextLine();
                        updateStudent.setGrade(newGrade);
                        System.out.println("점수 수정 완료!");
                    } else {
                        System.out.println("해당 학번을 가진 학생이 없습니다.");
                    }
                    break;

                case 5: // 학생 삭제
                    System.out.println("==== 학생 삭제 ====");
                    System.out.print("삭제할 학번 입력: ");
                    String deleteId = scanner.nextLine();
                    Student deleteStudent = findStudentById(students, deleteId);
                    if (deleteStudent != null) {
                        students.remove(deleteStudent);
                        System.out.println("학생 삭제 완료!");
                    } else {
                        System.out.println("해당 학번을 가진 학생이 없습니다.");
                    }
                    break;

                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    condition = false;
                    break;

                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
                    break;
            }
        }
        scanner.close();
    }

    private static Student findStudentById(ArrayList<Student> students, String id) {
        for (Student s : students) {
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}

class Student {
    private String name;
    private String studentId;
    private int grade;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "이름 : " + name + ", 학번 : " + studentId + ", 성적 : " + grade;
    }
}
