/**
 * 간단한 클라이언트 예제
 * 서버에 연결하여 메시지를 주고받는 클라이언트
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) {
        System.out.println("=== 간단한 클라이언트 예제 ===\n");
        
        String serverAddress = "localhost";
        int port = 8080;
        
        try {
            // 서버에 연결
            System.out.println("서버에 연결 중: " + serverAddress + ":" + port);
            Socket socket = new Socket(serverAddress, port);
            System.out.println("서버에 연결됨");
            
            // 입력 스트림 (서버로부터 데이터 수신)
            BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            
            // 출력 스트림 (서버에게 데이터 전송)
            PrintWriter pw = new PrintWriter(
                socket.getOutputStream(), true);
            
            // 서버로 메시지 전송
            String message = "Hello, Server!";
            pw.println(message);
            System.out.println("서버로 전송: " + message);
            
            // 서버 응답 수신
            String response = br.readLine();
            System.out.println("서버 응답: " + response);
            
            // 여러 메시지 주고받기
            System.out.println("\n--- 여러 메시지 주고받기 ---");
            Scanner scanner = new Scanner(System.in);
            
            // 서버의 초기 메시지 읽기
            String serverMessage = br.readLine();
            System.out.println("서버: " + serverMessage);
            
            while (true) {
                System.out.print("메시지 입력 (quit로 종료): ");
                String input = scanner.nextLine();
                
                // 서버로 전송
                pw.println(input);
                
                if (input.equalsIgnoreCase("quit")) {
                    break;
                }
                
                // 서버 응답 수신
                String serverResponse = br.readLine();
                System.out.println("서버: " + serverResponse);
            }
            
            // 연결 종료
            System.out.println("\n연결 종료");
            socket.close();
            scanner.close();
            System.out.println("클라이언트 종료");
            
        } catch (ConnectException e) {
            System.out.println("서버에 연결할 수 없습니다.");
            System.out.println("서버가 실행 중인지 확인하세요.");
        } catch (IOException e) {
            System.out.println("클라이언트 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

