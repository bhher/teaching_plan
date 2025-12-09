/**
 * 입출력 종합 예제
 * 다양한 파일 입출력 기능을 종합적으로 보여줍니다
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class IOComprehensive {
    // 로그 파일에 기록
    public static void writeLog(String message, String logFile) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(logFile, true))) {
            String timestamp = java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            bw.write("[" + timestamp + "] " + message);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("로그 기록 오류: " + e.getMessage());
        }
    }
    
    // 파일에서 특정 단어 검색
    public static ArrayList<String> searchInFile(String filename, String keyword) {
        ArrayList<String> results = new ArrayList<>();
        
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + filename);
            return results;
        }
        
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (line.contains(keyword)) {
                    results.add("줄 " + lineNumber + ": " + line);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        return results;
    }
    
    // 파일 통계 정보
    public static void printFileStats(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + filename);
            return;
        }
        
        System.out.println("=== 파일 통계 ===");
        System.out.println("파일명: " + file.getName());
        System.out.println("크기: " + file.length() + " bytes");
        System.out.println("절대 경로: " + file.getAbsolutePath());
        
        // 줄 수와 단어 수 계산
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            int lineCount = 0;
            int wordCount = 0;
            String line;
            
            while ((line = br.readLine()) != null) {
                lineCount++;
                if (!line.trim().isEmpty()) {
                    String[] words = line.trim().split("\\s+");
                    wordCount += words.length;
                }
            }
            
            System.out.println("줄 수: " + lineCount);
            System.out.println("단어 수: " + wordCount);
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }
    
    // 여러 파일 내용 합치기
    public static void mergeFiles(String[] sourceFiles, String destinationFile) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(destinationFile))) {
            
            for (String sourceFile : sourceFiles) {
                File file = new File(sourceFile);
                if (!file.exists()) {
                    System.out.println("파일이 존재하지 않습니다: " + sourceFile);
                    continue;
                }
                
                try (BufferedReader br = new BufferedReader(
                        new FileReader(sourceFile))) {
                    bw.write("=== " + file.getName() + " ===\n");
                    String line;
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                        bw.newLine();
                    }
                    bw.newLine();
                }
            }
            
            System.out.println("파일 합치기 완료: " + destinationFile);
        } catch (IOException e) {
            System.out.println("파일 합치기 오류: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 입출력 종합 예제 ===\n");
        
        // 예제 1: 로그 파일 작성
        System.out.println("--- 예제 1: 로그 파일 작성 ---");
        String logFile = "app.log";
        writeLog("프로그램 시작", logFile);
        writeLog("사용자 로그인", logFile);
        writeLog("데이터 처리 완료", logFile);
        writeLog("프로그램 종료", logFile);
        
        // 로그 파일 읽기
        System.out.println("\n로그 파일 내용:");
        try (BufferedReader br = new BufferedReader(
                new FileReader(logFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        // 예제 2: 파일 생성 및 검색
        System.out.println("\n--- 예제 2: 파일 생성 및 검색 ---");
        String searchFile = "search_test.txt";
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(searchFile))) {
            bw.write("Java는 객체지향 프로그래밍 언어입니다.\n");
            bw.write("Java는 플랫폼 독립적입니다.\n");
            bw.write("Java는 강력한 언어입니다.\n");
            System.out.println("테스트 파일 생성 완료");
        } catch (IOException e) {
            System.out.println("파일 생성 오류: " + e.getMessage());
        }
        
        // 파일에서 검색
        System.out.println("\n'Java' 검색 결과:");
        ArrayList<String> results = searchInFile(searchFile, "Java");
        for (String result : results) {
            System.out.println("  " + result);
        }
        
        // 예제 3: 파일 통계
        System.out.println("\n--- 예제 3: 파일 통계 ---");
        printFileStats(searchFile);
        
        // 예제 4: 여러 파일 합치기
        System.out.println("\n--- 예제 4: 여러 파일 합치기 ---");
        String file1 = "file1.txt";
        String file2 = "file2.txt";
        String mergedFile = "merged.txt";
        
        // 테스트 파일 생성
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(file1))) {
            bw.write("파일 1의 내용\n");
            bw.write("첫 번째 줄\n");
            bw.write("두 번째 줄\n");
        } catch (IOException e) {
            System.out.println("파일 생성 오류: " + e.getMessage());
        }
        
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(file2))) {
            bw.write("파일 2의 내용\n");
            bw.write("세 번째 줄\n");
            bw.write("네 번째 줄\n");
        } catch (IOException e) {
            System.out.println("파일 생성 오류: " + e.getMessage());
        }
        
        // 파일 합치기
        String[] sourceFiles = {file1, file2};
        mergeFiles(sourceFiles, mergedFile);
        
        // 합쳐진 파일 읽기
        System.out.println("\n합쳐진 파일 내용:");
        try (BufferedReader br = new BufferedReader(
                new FileReader(mergedFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
        
        System.out.println("\n=== 입출력의 중요성 ===");
        System.out.println("1. 데이터 영구 저장");
        System.out.println("2. 프로그램 간 데이터 공유");
        System.out.println("3. 로그 기록 및 분석");
        System.out.println("4. 설정 파일 관리");
    }
}

