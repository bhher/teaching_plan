/**
 * 스레드와 네트워크 종합 예제
 * 멀티스레드 서버와 여러 클라이언트를 시뮬레이션합니다
 */
import java.net.*;
import java.io.*;

public class ThreadNetworkComprehensive {
    // 동기화된 카운터 (여러 스레드가 안전하게 접근)
    static class ThreadSafeCounter {
        private int count = 0;
        
        public synchronized void increment() {
            count++;
        }
        
        public synchronized int getCount() {
            return count;
        }
    }
    
    // 서버 클래스
    static class Server {
        private ServerSocket serverSocket;
        private ThreadSafeCounter clientCounter;
        private int port;
        
        public Server(int port) {
            this.port = port;
            this.clientCounter = new ThreadSafeCounter();
        }
        
        public void start() {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("서버 시작: 포트 " + port);
                System.out.println("여러 클라이언트를 동시에 처리합니다.\n");
                
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    clientCounter.increment();
                    int clientId = clientCounter.getCount();
                    
                    System.out.println("클라이언트 #" + clientId + " 연결: " + 
                        clientSocket.getInetAddress().getHostAddress());
                    
                    // 각 클라이언트마다 새 스레드 생성
                    Thread clientThread = new Thread(
                        new ClientHandler(clientSocket, clientId));
                    clientThread.start();
                }
                
            } catch (IOException e) {
                System.out.println("서버 오류: " + e.getMessage());
            }
        }
        
        // 클라이언트 처리 클래스
        class ClientHandler implements Runnable {
            private Socket socket;
            private int clientId;
            
            public ClientHandler(Socket socket, int clientId) {
                this.socket = socket;
                this.clientId = clientId;
            }
            
            @Override
            public void run() {
                try {
                    BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                    PrintWriter pw = new PrintWriter(
                        socket.getOutputStream(), true);
                    
                    pw.println("클라이언트 #" + clientId + "에 연결되었습니다.");
                    pw.println("메시지를 보내주세요. (종료: quit)");
                    
                    String message;
                    while ((message = br.readLine()) != null) {
                        System.out.println("[클라이언트 #" + clientId + "] " + message);
                        
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
    
    // 클라이언트 시뮬레이터
    static class ClientSimulator implements Runnable {
        private String name;
        private int port;
        
        public ClientSimulator(String name, int port) {
            this.name = name;
            this.port = port;
        }
        
        @Override
        public void run() {
            try {
                Socket socket = new Socket("localhost", port);
                
                BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(
                    socket.getOutputStream(), true);
                
                // 서버 환영 메시지 읽기
                String welcome = br.readLine();
                System.out.println("[" + name + "] " + welcome);
                
                String ready = br.readLine();
                System.out.println("[" + name + "] " + ready);
                
                // 메시지 전송
                String[] messages = {
                    "안녕하세요!",
                    "반갑습니다.",
                    "테스트 메시지입니다."
                };
                
                for (String msg : messages) {
                    pw.println("[" + name + "] " + msg);
                    Thread.sleep(1000);
                    
                    String response = br.readLine();
                    System.out.println("[" + name + "] 서버 응답: " + response);
                }
                
                // 종료
                pw.println("quit");
                socket.close();
                System.out.println("[" + name + "] 연결 종료");
                
            } catch (Exception e) {
                System.out.println("[" + name + "] 오류: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== 스레드와 네트워크 종합 예제 ===\n");
        
        int port = 8080;
        
        // 서버를 별도 스레드에서 실행
        Thread serverThread = new Thread(() -> {
            Server server = new Server(port);
            server.start();
        });
        serverThread.setDaemon(true);  // 데몬 스레드로 설정
        serverThread.start();
        
        // 서버가 시작될 시간 제공
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 여러 클라이언트 시뮬레이터 실행
        System.out.println("--- 여러 클라이언트 연결 시뮬레이션 ---\n");
        
        Thread[] clients = new Thread[3];
        for (int i = 0; i < 3; i++) {
            final int clientNum = i + 1;
            clients[i] = new Thread(new ClientSimulator(
                "클라이언트" + clientNum, port));
            clients[i].start();
            
            // 약간의 지연
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // 모든 클라이언트 종료 대기
        for (Thread client : clients) {
            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("\n=== 종합 예제 완료 ===");
        System.out.println("이 예제는 다음을 보여줍니다:");
        System.out.println("1. 멀티스레드 서버: 여러 클라이언트 동시 처리");
        System.out.println("2. 동기화: ThreadSafeCounter로 안전한 카운터");
        System.out.println("3. 네트워크 통신: Socket을 통한 데이터 송수신");
        System.out.println("4. 스레드 관리: 각 클라이언트마다 별도 스레드");
    }
}

