/**
 * 바이트 스트림을 설명하는 예제
 * FileInputStream과 FileOutputStream의 사용법을 보여줍니다
 */
import java.io.*;

public class ByteStreamExample {
    public static void main(String[] args) {
        System.out.println("=== 바이트 스트림 예제 ===\n");
        
        String filename = "byte_data.dat";
        
        // 예제 1: FileOutputStream으로 파일 쓰기
        System.out.println("--- 예제 1: FileOutputStream으로 파일 쓰기 ---");
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            String text = "Hello, World!\n안녕하세요!";
            byte[] bytes = text.getBytes();
            fos.write(bytes);
            System.out.println("파일 쓰기 완료: " + filename);
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }
        
        // 예제 2: FileInputStream으로 파일 읽기 (바이트 단위)
        System.out.println("\n--- 예제 2: FileInputStream으로 파일 읽기 (바이트 단위) ---");
        try (FileInputStream fis = new FileInputStream(filename)) {
            int data;
            System.out.print("읽은 내용: ");
            while ((data = fis.read()) != -1) {
                System.out.print((char) data);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 예제 3: FileInputStream으로 파일 읽기 (바이트 배열)
        System.out.println("\n--- 예제 3: FileInputStream으로 파일 읽기 (바이트 배열) ---");
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            if (bytesRead != -1) {
                String content = new String(buffer, 0, bytesRead);
                System.out.println("읽은 내용: " + content);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 예제 4: 파일 추가 모드
        System.out.println("\n--- 예제 4: 파일 추가 모드 ---");
        try (FileOutputStream fos = new FileOutputStream(filename, true)) {
            String appendText = "\n추가된 텍스트";
            fos.write(appendText.getBytes());
            System.out.println("파일 추가 완료");
        } catch (IOException e) {
            System.out.println("파일 추가 오류: " + e.getMessage());
        }
        
        // 예제 5: BufferedInputStream/BufferedOutputStream
        System.out.println("\n--- 예제 5: BufferedInputStream/BufferedOutputStream ---");
        String bufferedFile = "buffered_data.dat";
        
        // 버퍼를 사용한 파일 쓰기
        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(bufferedFile))) {
            String text = "버퍼를 사용한 파일 입출력 예제입니다.";
            bos.write(text.getBytes());
            System.out.println("버퍼를 사용한 파일 쓰기 완료");
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }
        
        // 버퍼를 사용한 파일 읽기
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(bufferedFile))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            System.out.print("읽은 내용: ");
            while ((bytesRead = bis.read(buffer)) != -1) {
                System.out.write(buffer, 0, bytesRead);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 예제 6: 파일 복사 (바이트 스트림)
        System.out.println("\n--- 예제 6: 파일 복사 (바이트 스트림) ---");
        String sourceFile = filename;
        String destFile = "copied_byte_data.dat";
        
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("파일 복사 완료: " + sourceFile + " → " + destFile);
        } catch (IOException e) {
            System.out.println("파일 복사 오류: " + e.getMessage());
        }
        
        System.out.println("\n=== 바이트 스트림의 특징 ===");
        System.out.println("1. 바이트 단위로 데이터 처리");
        System.out.println("2. 바이너리 데이터 처리에 적합");
        System.out.println("3. 이미지, 동영상, 실행 파일 등에 사용");
    }
}

