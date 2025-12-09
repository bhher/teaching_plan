/**
 * 파일 복사 예제
 * 바이트 스트림과 문자 스트림을 사용한 파일 복사
 */
import java.io.*;

public class FileCopyExample {
    // 바이트 스트림을 사용한 파일 복사
    public static void copyFileByteStream(String source, String destination) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            int totalBytes = 0;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;
            }
            
            System.out.println("파일 복사 완료 (바이트 스트림)");
            System.out.println("복사된 크기: " + totalBytes + " bytes");
        } catch (IOException e) {
            System.out.println("파일 복사 오류: " + e.getMessage());
        }
    }
    
    // 문자 스트림을 사용한 파일 복사
    public static void copyFileCharacterStream(String source, String destination) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(source));
             BufferedWriter bw = new BufferedWriter(
                new FileWriter(destination))) {
            
            String line;
            int lineCount = 0;
            
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                lineCount++;
            }
            
            System.out.println("파일 복사 완료 (문자 스트림)");
            System.out.println("복사된 줄 수: " + lineCount);
        } catch (IOException e) {
            System.out.println("파일 복사 오류: " + e.getMessage());
        }
    }
    
    // 파일 존재 확인
    public static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists() && file.isFile();
    }
    
    public static void main(String[] args) {
        System.out.println("=== 파일 복사 예제 ===\n");
        
        // 테스트 파일 생성
        String sourceFile = "source.txt";
        String destFile1 = "destination1.txt";
        String destFile2 = "destination2.txt";
        
        // 소스 파일 생성
        System.out.println("--- 소스 파일 생성 ---");
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(sourceFile))) {
            bw.write("첫 번째 줄\n");
            bw.write("두 번째 줄\n");
            bw.write("세 번째 줄\n");
            bw.write("안녕하세요! Java 파일 복사 예제입니다.\n");
            System.out.println("소스 파일 생성 완료: " + sourceFile);
        } catch (IOException e) {
            System.out.println("파일 생성 오류: " + e.getMessage());
            return;
        }
        
        // 파일 존재 확인
        if (!fileExists(sourceFile)) {
            System.out.println("소스 파일이 존재하지 않습니다.");
            return;
        }
        
        // 바이트 스트림으로 복사
        System.out.println("\n--- 바이트 스트림으로 복사 ---");
        copyFileByteStream(sourceFile, destFile1);
        
        // 복사 결과 확인
        System.out.println("\n복사된 파일 내용 (바이트 스트림):");
        try (BufferedReader br = new BufferedReader(
                new FileReader(destFile1))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 문자 스트림으로 복사
        System.out.println("\n--- 문자 스트림으로 복사 ---");
        copyFileCharacterStream(sourceFile, destFile2);
        
        // 복사 결과 확인
        System.out.println("\n복사된 파일 내용 (문자 스트림):");
        try (BufferedReader br = new BufferedReader(
                new FileReader(destFile2))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 파일 크기 비교
        System.out.println("\n--- 파일 크기 비교 ---");
        File source = new File(sourceFile);
        File dest1 = new File(destFile1);
        File dest2 = new File(destFile2);
        
        System.out.println("소스 파일 크기: " + source.length() + " bytes");
        System.out.println("복사본1 크기: " + dest1.length() + " bytes");
        System.out.println("복사본2 크기: " + dest2.length() + " bytes");
        
        System.out.println("\n=== 파일 복사 방법 비교 ===");
        System.out.println("바이트 스트림:");
        System.out.println("- 바이너리 데이터 복사에 적합");
        System.out.println("- 원본과 동일한 크기");
        System.out.println("\n문자 스트림:");
        System.out.println("- 텍스트 파일 복사에 적합");
        System.out.println("- 줄 단위로 처리 가능");
    }
}

