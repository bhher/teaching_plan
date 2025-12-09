/**
 * 동기화를 설명하는 예제
 * 동기화 없이와 동기화를 사용한 경우를 비교합니다
 */
// 동기화 없는 카운터 (문제 발생 가능)
class UnsafeCounter {
    private int count = 0;
    
    public void increment() {
        count++;  // 여러 스레드가 동시에 실행하면 문제 발생
    }
    
    public int getCount() {
        return count;
    }
}

// 동기화된 카운터
class SafeCounter {
    private int count = 0;
    
    // synchronized 메서드
    public synchronized void increment() {
        count++;  // 한 번에 하나의 스레드만 실행
    }
    
    public synchronized int getCount() {
        return count;
    }
}

// synchronized 블록 사용
class BlockCounter {
    private int count = 0;
    private Object lock = new Object();
    
    public void increment() {
        synchronized (lock) {
            count++;
        }
    }
    
    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}

public class SynchronizationExample {
    public static void main(String[] args) {
        System.out.println("=== 동기화 예제 ===\n");
        
        // 예제 1: 동기화 없는 카운터 (문제 발생 가능)
        System.out.println("--- 예제 1: 동기화 없는 카운터 ---");
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        
        Thread[] unsafeThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            unsafeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    unsafeCounter.increment();
                }
            });
            unsafeThreads[i].start();
        }
        
        // 모든 스레드 종료 대기
        for (Thread t : unsafeThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("예상 값: 5000");
        System.out.println("실제 값: " + unsafeCounter.getCount());
        System.out.println("(동기화 없이 여러 스레드가 동시에 수정하면 데이터 손실 발생 가능)");
        
        // 예제 2: 동기화된 카운터
        System.out.println("\n--- 예제 2: 동기화된 카운터 (synchronized 메서드) ---");
        SafeCounter safeCounter = new SafeCounter();
        
        Thread[] safeThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            safeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    safeCounter.increment();
                }
            });
            safeThreads[i].start();
        }
        
        // 모든 스레드 종료 대기
        for (Thread t : safeThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("예상 값: 5000");
        System.out.println("실제 값: " + safeCounter.getCount());
        System.out.println("(동기화로 인해 정확한 값 보장)");
        
        // 예제 3: synchronized 블록
        System.out.println("\n--- 예제 3: synchronized 블록 ---");
        BlockCounter blockCounter = new BlockCounter();
        
        Thread[] blockThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            blockThreads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    blockCounter.increment();
                }
            });
            blockThreads[i].start();
        }
        
        // 모든 스레드 종료 대기
        for (Thread t : blockThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("예상 값: 5000");
        System.out.println("실제 값: " + blockCounter.getCount());
        
        // 예제 4: 동기화의 필요성 시연
        System.out.println("\n--- 예제 4: 동기화의 필요성 ---");
        System.out.println("여러 스레드가 공유 자원에 동시에 접근하면:");
        System.out.println("1. 데이터 불일치 발생 가능");
        System.out.println("2. 경쟁 조건 (Race Condition) 발생");
        System.out.println("3. 예상치 못한 결과 발생");
        System.out.println("\n동기화를 사용하면:");
        System.out.println("1. 한 번에 하나의 스레드만 실행");
        System.out.println("2. 데이터 일관성 보장");
        System.out.println("3. 안전한 공유 자원 접근");
        
        System.out.println("\n=== 동기화의 특징 ===");
        System.out.println("장점:");
        System.out.println("1. 데이터 일관성 보장");
        System.out.println("2. 경쟁 조건 방지");
        System.out.println("3. 안전한 멀티스레드 프로그래밍");
        System.out.println("\n단점:");
        System.out.println("1. 성능 저하 (스레드 대기)");
        System.out.println("2. 데드락 가능성");
        System.out.println("3. 복잡성 증가");
    }
}

