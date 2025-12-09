/**
 * 간단한 서버 예제
 * 클라이언트의 연결을 받아 메시지를 주고받는 서버
 */
import java.net.*;
import java.io.*;

public class SimpleServer {
    public static void main(String[] args) {
        System.out.println("=== 간단한 서버 예제 ===\n");
        
        int port = 8080;
        
        try {
            // 서버 소켓 생성
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("서버 시작: 포트 " + port + "에서 대기 중...");
            
            // 클라이언트 연결 대기
            System.out.println("클라이언트 연결 대기 중...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트 연결됨: " + 
                clientSocket.getInetAddress().getHostAddress());
            
            // 입력 스트림 (클라이언트로부터 데이터 수신)
            BufferedReader br = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            
            // 출력 스트림 (클라이언트에게 데이터 전송)
            PrintWriter pw = new PrintWriter(
                clientSocket.getOutputStream(), true);
            
            // 클라이언트로부터 메시지 수신
            String clientMessage = br.readLine();
            System.out.println("클라이언트 메시지: " + clientMessage);
            
            // 클라이언트에게 응답 전송
            String response = "서버 응답: 메시지를 받았습니다. (" + clientMessage + ")";
            pw.println(response);
            System.out.println("서버 응답 전송: " + response);
            
            // 여러 메시지 주고받기
            System.out.println("\n--- 여러 메시지 주고받기 ---");
            pw.println("서버 준비 완료. 메시지를 보내주세요. (종료: quit)");
            
            String message;
            while ((message = br.readLine()) != null) {
                System.out.println("받은 메시지: " + message);
                
                if (message.equalsIgnoreCase("quit")) {
                    pw.println("서버 종료");
                    break;
                }
                
                // 에코 응답
                pw.println("에코: " + message);
            }
            
            // 연결 종료
            System.out.println("\n연결 종료");
            clientSocket.close();
            serverSocket.close();
            System.out.println("서버 종료");
            
        } catch (IOException e) {
            System.out.println("서버 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

