/**
 * 스트림 개념을 설명하는 예제
 * 스트림의 기본 개념과 특징을 보여줍니다
 */
import java.io.*;

public class StreamConcept {
    public static void main(String[] args) {
        System.out.println("=== 스트림 개념 ===\n");
        
        System.out.println("--- 스트림이란? ---");
        System.out.println("스트림은 데이터가 흐르는 통로입니다.");
        System.out.println("Java에서는 모든 입출력이 스트림을 통해 이루어집니다.\n");
        
        System.out.println("--- 스트림의 특징 ---");
        System.out.println("1. 단방향: 입력 스트림과 출력 스트림이 분리됨");
        System.out.println("2. 순차적: 데이터가 순서대로 처리됨");
        System.out.println("3. 연속적: 데이터가 연속적으로 흐름\n");
        
        System.out.println("--- 스트림의 종류 ---");
        System.out.println("1. 바이트 스트림 (Byte Stream)");
        System.out.println("   - 단위: 바이트(byte)");
        System.out.println("   - 용도: 이미지, 동영상, 실행 파일 등 바이너리 데이터");
        System.out.println("   - 클래스: InputStream, OutputStream 계열\n");
        
        System.out.println("2. 문자 스트림 (Character Stream)");
        System.out.println("   - 단위: 문자(char)");
        System.out.println("   - 용도: 텍스트 파일 처리");
        System.out.println("   - 클래스: Reader, Writer 계열\n");
        
        System.out.println("--- 표준 입출력 스트림 ---");
        System.out.println("System.in: 표준 입력 스트림 (키보드)");
        System.out.println("System.out: 표준 출력 스트림 (모니터)");
        System.out.println("System.err: 표준 오류 스트림 (모니터)\n");
        
        // 표준 입출력 예제
        System.out.println("--- 표준 입출력 예제 ---");
        System.out.println("표준 출력: Hello, World!");
        System.err.println("표준 오류: 오류 메시지");
        
        System.out.println("\n=== 스트림 계층 구조 ===");
        System.out.println("InputStream (추상 클래스)");
        System.out.println("├── FileInputStream");
        System.out.println("├── BufferedInputStream");
        System.out.println("└── ObjectInputStream");
        System.out.println("\nOutputStream (추상 클래스)");
        System.out.println("├── FileOutputStream");
        System.out.println("├── BufferedOutputStream");
        System.out.println("└── ObjectOutputStream");
        System.out.println("\nReader (추상 클래스)");
        System.out.println("├── FileReader");
        System.out.println("├── BufferedReader");
        System.out.println("└── InputStreamReader");
        System.out.println("\nWriter (추상 클래스)");
        System.out.println("├── FileWriter");
        System.out.println("├── BufferedWriter");
        System.out.println("└── OutputStreamWriter");
    }
}

