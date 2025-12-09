/**
 * 네트워크 기초를 설명하는 예제
 * Socket 개념과 네트워크 통신 과정을 보여줍니다
 */
import java.net.*;

public class NetworkBasics {
    public static void main(String[] args) {
        System.out.println("=== 네트워크 기초 ===\n");
        
        System.out.println("--- Socket이란? ---");
        System.out.println("소켓(Socket)은 네트워크를 통해 데이터를 주고받기 위한");
        System.out.println("통신의 끝점(endpoint)입니다.\n");
        
        System.out.println("--- 네트워크 통신 모델 ---");
        System.out.println("클라이언트-서버 모델:");
        System.out.println("┌─────────────┐         ┌─────────────┐");
        System.out.println("│  클라이언트   │ ────→   │   서버       │");
        System.out.println("│  (Client)    │         │  (Server)    │");
        System.out.println("│             │ ←────   │             │");
        System.out.println("└─────────────┘         └─────────────┘\n");
        
        System.out.println("--- 포트(Port)란? ---");
        System.out.println("포트는 네트워크 통신에서 특정 프로세스를 식별하는 번호입니다.");
        System.out.println("범위: 0 ~ 65535");
        System.out.println("잘 알려진 포트: 0 ~ 1023");
        System.out.println("  - HTTP: 80");
        System.out.println("  - HTTPS: 443");
        System.out.println("  - FTP: 21");
        System.out.println("  - SSH: 22");
        System.out.println("사용자 포트: 1024 ~ 65535\n");
        
        System.out.println("--- 네트워크 통신 과정 ---");
        System.out.println("1. 서버: ServerSocket 생성 및 대기");
        System.out.println("2. 클라이언트: Socket으로 서버에 연결");
        System.out.println("3. 서버: 연결 수락 (accept)");
        System.out.println("4. 양방향 통신: 데이터 송수신");
        System.out.println("5. 연결 종료: 소켓 닫기\n");
        
        System.out.println("--- ServerSocket 사용법 ---");
        System.out.println("ServerSocket serverSocket = new ServerSocket(8080);");
        System.out.println("Socket clientSocket = serverSocket.accept();");
        System.out.println("// 클라이언트 처리");
        System.out.println("clientSocket.close();");
        System.out.println("serverSocket.close();\n");
        
        System.out.println("--- Socket 사용법 (클라이언트) ---");
        System.out.println("Socket socket = new Socket(\"localhost\", 8080);");
        System.out.println("// 데이터 송수신");
        System.out.println("socket.close();\n");
        
        System.out.println("--- 데이터 송수신 ---");
        System.out.println("출력 스트림 (전송):");
        System.out.println("  OutputStream os = socket.getOutputStream();");
        System.out.println("  PrintWriter pw = new PrintWriter(os, true);");
        System.out.println("  pw.println(\"메시지\");");
        System.out.println("\n입력 스트림 (수신):");
        System.out.println("  InputStream is = socket.getInputStream();");
        System.out.println("  BufferedReader br = new BufferedReader(");
        System.out.println("      new InputStreamReader(is));");
        System.out.println("  String message = br.readLine();\n");
        
        // 로컬 호스트 정보
        try {
            System.out.println("--- 로컬 호스트 정보 ---");
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("호스트 이름: " + localhost.getHostName());
            System.out.println("IP 주소: " + localhost.getHostAddress());
            
            System.out.println("\n--- 루프백 주소 ---");
            InetAddress loopback = InetAddress.getLoopbackAddress();
            System.out.println("루프백 주소: " + loopback.getHostAddress());
            System.out.println("(localhost 또는 127.0.0.1)");
            
        } catch (UnknownHostException e) {
            System.out.println("호스트 정보를 가져올 수 없습니다.");
        }
        
        System.out.println("\n=== 네트워크 프로그래밍 주의사항 ===");
        System.out.println("1. 예외 처리: IOException 반드시 처리");
        System.out.println("2. 리소스 관리: 소켓은 반드시 close()");
        System.out.println("3. 포트 충돌: 이미 사용 중인 포트 사용 불가");
        System.out.println("4. 타임아웃: 연결 타임아웃 설정 권장");
        System.out.println("5. 멀티스레드: 여러 클라이언트 처리 시 스레드 사용");
    }
}

