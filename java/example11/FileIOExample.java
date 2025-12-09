/**
 * 파일 입출력 기본 예제
 * 파일 쓰기, 읽기, 정보 확인을 보여줍니다
 */
import java.io.*;
import java.util.Date;

public class FileIOExample {
    public static void main(String[] args) {
        System.out.println("=== 파일 입출력 기본 예제 ===\n");
        
        String filename = "example.txt";
        
        // 예제 1: 파일 쓰기
        System.out.println("--- 예제 1: 파일 쓰기 ---");
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("안녕하세요!\n");
            fw.write("Java 파일 입출력 예제입니다.\n");
            fw.write("오늘 날짜: " + new Date() + "\n");
            System.out.println("파일 쓰기 완료: " + filename);
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }
        
        // 예제 2: 파일 읽기
        System.out.println("\n--- 예제 2: 파일 읽기 ---");
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            String line;
            System.out.println("파일 내용:");
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 예제 3: 파일 정보 확인
        System.out.println("\n--- 예제 3: 파일 정보 확인 ---");
        File file = new File(filename);
        
        if (file.exists()) {
            System.out.println("파일명: " + file.getName());
            System.out.println("절대 경로: " + file.getAbsolutePath());
            System.out.println("파일 크기: " + file.length() + " bytes");
            System.out.println("읽기 가능: " + file.canRead());
            System.out.println("쓰기 가능: " + file.canWrite());
            System.out.println("파일인가: " + file.isFile());
            System.out.println("디렉토리인가: " + file.isDirectory());
            System.out.println("마지막 수정: " + new Date(file.lastModified()));
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
        
        // 예제 4: 파일 존재 확인 후 읽기
        System.out.println("\n--- 예제 4: 파일 존재 확인 후 읽기 ---");
        File checkFile = new File(filename);
        if (checkFile.exists() && checkFile.isFile()) {
            try (BufferedReader br = new BufferedReader(
                    new FileReader(checkFile))) {
                System.out.println("파일을 읽을 수 있습니다.");
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("  " + line);
                }
            } catch (IOException e) {
                System.out.println("파일 읽기 오류: " + e.getMessage());
            }
        } else {
            System.out.println("파일이 존재하지 않거나 파일이 아닙니다.");
        }
        
        // 예제 5: 파일에 추가
        System.out.println("\n--- 예제 5: 파일에 추가 ---");
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write("\n추가된 내용입니다.");
            System.out.println("파일 추가 완료");
        } catch (IOException e) {
            System.out.println("파일 추가 오류: " + e.getMessage());
        }
        
        // 추가 후 내용 확인
        System.out.println("\n추가 후 파일 내용:");
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        System.out.println("\n=== 파일 입출력 주의사항 ===");
        System.out.println("1. 리소스 관리: try-with-resources 사용 권장");
        System.out.println("2. 예외 처리: IOException 반드시 처리");
        System.out.println("3. 파일 존재 확인: 읽기 전에 존재 여부 확인");
        System.out.println("4. 경로 구분자: File.separator 사용 권장");
    }
}

