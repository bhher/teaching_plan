/**
 * 학생 정보 파일 관리 예제
 * 파일에 학생 정보를 저장하고 읽는 실전 예제
 */
import java.io.*;
import java.util.ArrayList;

public class StudentFileManager {
    // 학생 정보를 파일에 저장
    public static void saveStudents(ArrayList<String> students, String filename) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(filename))) {
            for (String student : students) {
                bw.write(student);
                bw.newLine();
            }
            System.out.println("학생 정보 저장 완료: " + filename);
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }
    
    // 파일에서 학생 정보 읽기
    public static ArrayList<String> loadStudents(String filename) {
        ArrayList<String> students = new ArrayList<>();
        
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + filename);
            return students;
        }
        
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {  // 빈 줄 제외
                    students.add(line);
                }
            }
            System.out.println("학생 정보 읽기 완료: " + students.size() + "명");
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        return students;
    }
    
    // 학생 정보 추가
    public static void addStudent(String student, String filename) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(filename, true))) {  // append 모드
            bw.write(student);
            bw.newLine();
            System.out.println("학생 정보 추가 완료: " + student);
        } catch (IOException e) {
            System.out.println("파일 추가 오류: " + e.getMessage());
        }
    }
    
    // 학생 정보 출력
    public static void printStudents(ArrayList<String> students) {
        if (students.isEmpty()) {
            System.out.println("학생 정보가 없습니다.");
            return;
        }
        
        System.out.println("\n=== 학생 목록 ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 학생 정보 파일 관리 시스템 ===\n");
        
        String filename = "students.txt";
        
        // 초기 학생 정보
        ArrayList<String> students = new ArrayList<>();
        students.add("홍길동,20,컴퓨터공학,85");
        students.add("김철수,21,전자공학,90");
        students.add("이영희,20,컴퓨터공학,88");
        
        System.out.println("--- 초기 학생 정보 ---");
        printStudents(students);
        
        // 파일에 저장
        System.out.println("\n--- 파일에 저장 ---");
        saveStudents(students, filename);
        
        // 파일에서 읽기
        System.out.println("\n--- 파일에서 읽기 ---");
        ArrayList<String> loadedStudents = loadStudents(filename);
        printStudents(loadedStudents);
        
        // 학생 정보 추가
        System.out.println("\n--- 학생 정보 추가 ---");
        addStudent("박민수,22,기계공학,92", filename);
        
        // 다시 읽기
        System.out.println("\n--- 추가 후 파일 읽기 ---");
        loadedStudents = loadStudents(filename);
        printStudents(loadedStudents);
        
        // 파일 정보 확인
        System.out.println("\n--- 파일 정보 ---");
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("파일명: " + file.getName());
            System.out.println("파일 크기: " + file.length() + " bytes");
            System.out.println("절대 경로: " + file.getAbsolutePath());
        }
    }
}

