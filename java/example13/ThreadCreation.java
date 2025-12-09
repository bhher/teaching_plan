/**
 * 스레드 생성 방법을 설명하는 예제
 * Thread 상속, Runnable 구현, 람다 표현식을 보여줍니다
 */
// 방법 1: Thread 클래스 상속
class MyThread extends Thread {
    private String name;
    
    public MyThread(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + ": " + i);
            try {
                Thread.sleep(500);  // 0.5초 대기
            } catch (InterruptedException e) {
                System.out.println(name + " 인터럽트됨");
            }
        }
        System.out.println(name + " 종료");
    }
}

// 방법 2: Runnable 인터페이스 구현
class MyRunnable implements Runnable {
    private String name;
    
    public MyRunnable(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(name + " 인터럽트됨");
            }
        }
        System.out.println(name + " 종료");
    }
}

public class ThreadCreation {
    public static void main(String[] args) {
        System.out.println("=== 스레드 생성 방법 ===\n");
        
        // 방법 1: Thread 클래스 상속
        System.out.println("--- 방법 1: Thread 클래스 상속 ---");
        MyThread thread1 = new MyThread("Thread-1");
        thread1.start();  // 새 스레드에서 실행
        
        // 방법 2: Runnable 인터페이스 구현
        System.out.println("\n--- 방법 2: Runnable 인터페이스 구현 ---");
        Thread thread2 = new Thread(new MyRunnable("Runnable-1"));
        thread2.start();
        
        // 방법 3: 익명 클래스
        System.out.println("\n--- 방법 3: 익명 클래스 ---");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Anonymous: " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Anonymous 종료");
            }
        });
        thread3.start();
        
        // 방법 4: 람다 표현식 (Java 8+)
        System.out.println("\n--- 방법 4: 람다 표현식 ---");
        Thread thread4 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Lambda 종료");
        });
        thread4.start();
        
        // 메인 스레드도 작업 수행
        System.out.println("\n--- 메인 스레드 작업 ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // 모든 스레드가 종료될 때까지 대기
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== start() vs run() ===");
        System.out.println("start(): 새 스레드를 생성하고 run() 실행");
        System.out.println("run(): 단순히 메서드 호출 (새 스레드 생성 안 됨)");
        
        System.out.println("\n--- start() 사용 (권장) ---");
        Thread t1 = new Thread(() -> System.out.println("새 스레드에서 실행"));
        t1.start();
        
        System.out.println("\n--- run() 사용 (비권장) ---");
        Thread t2 = new Thread(() -> System.out.println("현재 스레드에서 실행"));
        t2.run();  // 새 스레드 생성 안 됨
        
        System.out.println("\n=== 스레드 상태 ===");
        Thread testThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("생성 후: " + testThread.getState());  // NEW
        testThread.start();
        System.out.println("start() 후: " + testThread.getState());  // RUNNABLE
        
        try {
            testThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("종료 후: " + testThread.getState());  // TERMINATED
    }
}

