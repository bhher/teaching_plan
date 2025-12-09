/**
 * 프로세스와 스레드 개념을 설명하는 예제
 * 프로세스와 스레드의 차이를 보여줍니다
 */
public class ProcessThread {
    public static void main(String[] args) {
        System.out.println("=== 프로세스와 스레드 ===\n");
        
        System.out.println("--- 프로세스 (Process) ---");
        System.out.println("프로세스는 실행 중인 프로그램입니다.");
        System.out.println("특징:");
        System.out.println("1. 독립적인 메모리 공간");
        System.out.println("2. 프로세스 간 통신 어려움");
        System.out.println("3. 컨텍스트 스위칭 비용 큼");
        System.out.println("4. 독립 실행");
        System.out.println();
        
        System.out.println("--- 스레드 (Thread) ---");
        System.out.println("스레드는 프로세스 내에서 실행되는 실행 단위입니다.");
        System.out.println("특징:");
        System.out.println("1. 공유 메모리 (같은 프로세스 내)");
        System.out.println("2. 경량 (프로세스보다 가벼움)");
        System.out.println("3. 동시 실행 가능");
        System.out.println("4. 의존성 (한 스레드 오류가 전체에 영향)");
        System.out.println();
        
        System.out.println("--- 프로세스 vs 스레드 비교 ---");
        System.out.println("┌─────────────┬─────────────┐");
        System.out.println("│   프로세스   │    스레드    │");
        System.out.println("├─────────────┼─────────────┤");
        System.out.println("│ 독립 메모리  │ 공유 메모리  │");
        System.out.println("│ 생성 비용 높음│ 생성 비용 낮음│");
        System.out.println("│ 통신 어려움  │ 통신 쉬움    │");
        System.out.println("│ 독립성 높음  │ 독립성 낮음  │");
        System.out.println("└─────────────┴─────────────┘");
        System.out.println();
        
        System.out.println("--- 멀티스레딩의 장점 ---");
        System.out.println("1. 응답성 향상: UI 스레드와 작업 스레드 분리");
        System.out.println("2. 자원 활용: 멀티코어 CPU 활용");
        System.out.println("3. 효율성: I/O 대기 시간 활용");
        System.out.println("4. 동시성: 여러 작업 동시 처리");
        System.out.println();
        
        // 현재 스레드 정보
        System.out.println("--- 현재 스레드 정보 ---");
        Thread currentThread = Thread.currentThread();
        System.out.println("스레드 이름: " + currentThread.getName());
        System.out.println("스레드 ID: " + currentThread.getId());
        System.out.println("스레드 우선순위: " + currentThread.getPriority());
        System.out.println("스레드 상태: " + currentThread.getState());
        System.out.println("활성 스레드 수: " + Thread.activeCount());
    }
}

