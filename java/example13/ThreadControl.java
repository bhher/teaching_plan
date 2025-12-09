/**
 * 스레드 제어를 설명하는 예제
 * sleep, join, interrupt 등의 메서드를 보여줍니다
 */
public class ThreadControl {
    public static void main(String[] args) {
        System.out.println("=== 스레드 제어 예제 ===\n");
        
        // 예제 1: sleep() - 스레드 대기
        System.out.println("--- 예제 1: sleep() - 스레드 대기 ---");
        Thread sleepThread = new Thread(() -> {
            System.out.println("스레드 시작");
            try {
                System.out.println("3초 대기...");
                Thread.sleep(3000);  // 3초 대기
                System.out.println("대기 완료");
            } catch (InterruptedException e) {
                System.out.println("인터럽트됨");
            }
        });
        sleepThread.start();
        
        try {
            sleepThread.join();  // 스레드 종료 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 예제 2: join() - 스레드 종료 대기
        System.out.println("\n--- 예제 2: join() - 스레드 종료 대기 ---");
        Thread workerThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("작업 중: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("작업 완료");
        });
        
        System.out.println("작업 스레드 시작");
        workerThread.start();
        
        try {
            System.out.println("작업 스레드 종료 대기...");
            workerThread.join();  // 작업 스레드가 종료될 때까지 대기
            System.out.println("메인 스레드 계속 실행");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 예제 3: interrupt() - 스레드 인터럽트
        System.out.println("\n--- 예제 3: interrupt() - 스레드 인터럽트 ---");
        Thread interruptThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("작업: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("인터럽트 받음: 작업 중단");
            }
        });
        
        interruptThread.start();
        
        // 3초 후 인터럽트
        try {
            Thread.sleep(3000);
            System.out.println("스레드 인터럽트 전송");
            interruptThread.interrupt();
            interruptThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 예제 4: isAlive() - 스레드 생존 확인
        System.out.println("\n--- 예제 4: isAlive() - 스레드 생존 확인 ---");
        Thread aliveThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("시작 전: " + aliveThread.isAlive());  // false
        aliveThread.start();
        System.out.println("시작 후: " + aliveThread.isAlive());  // true
        
        try {
            aliveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("종료 후: " + aliveThread.isAlive());  // false
        
        // 예제 5: 우선순위
        System.out.println("\n--- 예제 5: 스레드 우선순위 ---");
        Thread lowPriority = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("낮은 우선순위: " + i);
            }
        });
        
        Thread highPriority = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("높은 우선순위: " + i);
            }
        });
        
        lowPriority.setPriority(Thread.MIN_PRIORITY);  // 1
        highPriority.setPriority(Thread.MAX_PRIORITY);  // 10
        
        System.out.println("낮은 우선순위: " + lowPriority.getPriority());
        System.out.println("높은 우선순위: " + highPriority.getPriority());
        
        highPriority.start();
        lowPriority.start();
        
        try {
            highPriority.join();
            lowPriority.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== 스레드 제어 메서드 요약 ===");
        System.out.println("start(): 스레드 시작");
        System.out.println("sleep(ms): 지정된 시간만큼 대기");
        System.out.println("join(): 스레드가 종료될 때까지 대기");
        System.out.println("interrupt(): 스레드에 인터럽트 신호 전송");
        System.out.println("isAlive(): 스레드가 살아있는지 확인");
        System.out.println("setPriority(): 스레드 우선순위 설정");
    }
}

