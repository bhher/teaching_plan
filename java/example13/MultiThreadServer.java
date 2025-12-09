/**
 * 멀티스레드 서버 예제
 * 여러 클라이언트를 동시에 처리할 수 있는 서버
 */
import java.net.*;
import java.io.*;

public class MultiThreadServer {
    public static void main(String[] args) {
        System.out.println("=== 멀티스레드 서버 예제 ===\n");
        
        int port = 8080;
        
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("서버 시작: 포트 " + port);
            System.out.println("여러 클라이언트를 동시에 처리할 수 있습니다.\n");
            
            int clientCount = 0;
            
            while (true) {
                // 클라이언트 연결 대기
                Socket clientSocket = serverSocket.accept();
                clientCount++;
                
                System.out.println("클라이언트 #" + clientCount + " 연결: " + 
                    clientSocket.getInetAddress().getHostAddress());
                
                // 각 클라이언트마다 새 스레드 생성
                Thread clientThread = new Thread(new ClientHandler(clientSocket, clientCount));
                clientThread.start();
            }
            
        } catch (IOException e) {
            System.out.println("서버 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // 클라이언트 처리 클래스
    static class ClientHandler implements Runnable {
        private Socket socket;
        private int clientId;
        
        public ClientHandler(Socket socket, int clientId) {
            this.socket = socket;
            this.clientId = clientId;
        }
        
        @Override
        public void run() {
            try {
                // 입력 스트림
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                
                // 출력 스트림
                PrintWriter pw = new PrintWriter(
                    socket.getOutputStream(), true);
                
                // 환영 메시지
                pw.println("클라이언트 #" + clientId + "에 연결되었습니다.");
                pw.println("메시지를 보내주세요. (종료: quit)");
                
                String message;
                while ((message = br.readLine()) != null) {
                    System.out.println("클라이언트 #" + clientId + " 메시지: " + message);
                    
                    if (message.equalsIgnoreCase("quit")) {
                        pw.println("연결 종료");
                        break;
                    }
                    
                    // 에코 응답
                    pw.println("에코 (#" + clientId + "): " + message);
                }
                
                System.out.println("클라이언트 #" + clientId + " 연결 종료");
                socket.close();
                
            } catch (IOException e) {
                System.out.println("클라이언트 #" + clientId + " 처리 오류: " + e.getMessage());
            }
        }
    }
}

