/**
 * 문자 스트림을 설명하는 예제
 * FileReader, FileWriter, BufferedReader, BufferedWriter의 사용법을 보여줍니다
 */
import java.io.*;

public class CharacterStreamExample {
    public static void main(String[] args) {
        System.out.println("=== 문자 스트림 예제 ===\n");
        
        String filename = "text_data.txt";
        
        // 예제 1: FileWriter로 파일 쓰기
        System.out.println("--- 예제 1: FileWriter로 파일 쓰기 ---");
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("안녕하세요!\n");
            fw.write("Java 파일 입출력 예제입니다.\n");
            fw.write("문자 스트림을 사용합니다.\n");
            System.out.println("파일 쓰기 완료: " + filename);
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }
        
        // 예제 2: FileReader로 파일 읽기 (문자 단위)
        System.out.println("\n--- 예제 2: FileReader로 파일 읽기 (문자 단위) ---");
        try (FileReader fr = new FileReader(filename)) {
            int data;
            System.out.print("읽은 내용: ");
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 예제 3: BufferedReader로 파일 읽기 (줄 단위)
        System.out.println("\n--- 예제 3: BufferedReader로 파일 읽기 (줄 단위) ---");
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            String line;
            int lineNumber = 1;
            System.out.println("줄 단위로 읽기:");
            while ((line = br.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 예제 4: BufferedWriter로 파일 쓰기
        System.out.println("\n--- 예제 4: BufferedWriter로 파일 쓰기 ---");
        String bufferedFile = "buffered_text.txt";
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(bufferedFile))) {
            bw.write("첫 번째 줄");
            bw.newLine();  // 줄바꿈
            bw.write("두 번째 줄");
            bw.newLine();
            bw.write("세 번째 줄");
            System.out.println("버퍼를 사용한 파일 쓰기 완료");
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }
        
        // 예제 5: 파일 추가 모드
        System.out.println("\n--- 예제 5: 파일 추가 모드 ---");
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write("\n추가된 텍스트");
            System.out.println("파일 추가 완료");
        } catch (IOException e) {
            System.out.println("파일 추가 오류: " + e.getMessage());
        }
        
        // 예제 6: 파일 복사 (문자 스트림)
        System.out.println("\n--- 예제 6: 파일 복사 (문자 스트림) ---");
        String sourceFile = filename;
        String destFile = "copied_text_data.txt";
        
        try (BufferedReader br = new BufferedReader(
                new FileReader(sourceFile));
             BufferedWriter bw = new BufferedWriter(
                new FileWriter(destFile))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("파일 복사 완료: " + sourceFile + " → " + destFile);
        } catch (IOException e) {
            System.out.println("파일 복사 오류: " + e.getMessage());
        }
        
        // 예제 7: 여러 줄 텍스트 쓰기
        System.out.println("\n--- 예제 7: 여러 줄 텍스트 쓰기 ---");
        String multiLineFile = "multiline.txt";
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(multiLineFile))) {
            String[] lines = {
                "첫 번째 줄",
                "두 번째 줄",
                "세 번째 줄",
                "네 번째 줄"
            };
            
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("여러 줄 텍스트 쓰기 완료");
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }
        
        System.out.println("\n=== 문자 스트림의 특징 ===");
        System.out.println("1. 문자 단위로 데이터 처리");
        System.out.println("2. 텍스트 파일 처리에 최적화");
        System.out.println("3. 인코딩을 자동으로 처리");
        System.out.println("4. BufferedReader/BufferedWriter로 성능 향상");
    }
}

