/**
 * StudentRepository 클래스
 * 학생 데이터를 파일에 저장하고 불러오는 저장소 클래스
 */
package repository;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static final String FILE_PATH = "data/students.txt";
    
    /**
     * 학생 목록을 파일에 저장
     */
    public boolean saveToFile(List<Student> students) {
        if (students == null) {
            System.out.println("오류: 저장할 학생 목록이 없습니다.");
            return false;
        }
        
        // 디렉토리 생성
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                bw.write(student.toFileString());
                bw.newLine();
            }
            System.out.println("파일 저장 완료: " + FILE_PATH + " (" + students.size() + "명)");
            return true;
            
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 파일에서 학생 목록 불러오기
     */
    public List<Student> loadFromFile() {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + FILE_PATH);
            return students;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                Student student = Student.fromFileString(line);
                if (student != null) {
                    students.add(student);
                    count++;
                }
            }
            
            System.out.println("파일 불러오기 완료: " + FILE_PATH + " (" + count + "명)");
            
        } catch (IOException e) {
            System.out.println("파일 불러오기 오류: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("파일 형식 오류: 숫자 변환 실패");
        }
        
        return students;
    }
    
    /**
     * 파일 존재 여부 확인
     */
    public boolean fileExists() {
        return new File(FILE_PATH).exists();
    }
    
    /**
     * 파일 삭제
     */
    public boolean deleteFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("파일 삭제 완료: " + FILE_PATH);
            }
            return deleted;
        }
        return false;
    }
}

