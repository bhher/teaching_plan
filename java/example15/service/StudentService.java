/**
 * StudentService 클래스
 * 학생 관리 비즈니스 로직을 처리하는 서비스 클래스
 */
package service;

import model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students;
    
    public StudentService() {
        this.students = new ArrayList<>();
    }
    
    /**
     * 학생 목록 초기화 (파일에서 불러온 데이터로)
     */
    public StudentService(List<Student> students) {
        this.students = students != null ? new ArrayList<>(students) : new ArrayList<>();
    }
    
    /**
     * CREATE: 학생 등록
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            System.out.println("오류: 학생 정보가 없습니다.");
            return false;
        }
        
        // 학번 중복 확인
        if (findStudent(student.getStudentId()) != null) {
            System.out.println("오류: 이미 존재하는 학번입니다. (학번: " + student.getStudentId() + ")");
            return false;
        }
        
        students.add(student);
        System.out.println("학생이 등록되었습니다: " + student.getName());
        return true;
    }
    
    /**
     * READ: 모든 학생 조회
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);  // 방어적 복사
    }
    
    /**
     * READ: 학번으로 학생 조회
     */
    public Student findStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return null;
        }
        
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * UPDATE: 학생 정보 수정
     */
    public boolean updateStudent(String studentId, String name, int age, String major) {
        Student student = findStudent(studentId);
        
        if (student == null) {
            System.out.println("오류: 학생을 찾을 수 없습니다. (학번: " + studentId + ")");
            return false;
        }
        
        student.setName(name);
        student.setAge(age);
        student.setMajor(major);
        System.out.println("학생 정보가 수정되었습니다: " + student.getName());
        return true;
    }
    
    /**
     * DELETE: 학생 삭제
     */
    public boolean deleteStudent(String studentId) {
        Student student = findStudent(studentId);
        
        if (student == null) {
            System.out.println("오류: 학생을 찾을 수 없습니다. (학번: " + studentId + ")");
            return false;
        }
        
        students.remove(student);
        System.out.println("학생이 삭제되었습니다: " + student.getName());
        return true;
    }
    
    /**
     * 학생 수 조회
     */
    public int getStudentCount() {
        return students.size();
    }
    
    /**
     * 학생 목록이 비어있는지 확인
     */
    public boolean isEmpty() {
        return students.isEmpty();
    }
    
    /**
     * 학생 목록 초기화
     */
    public void clear() {
        students.clear();
        System.out.println("모든 학생 정보가 삭제되었습니다.");
    }
}

