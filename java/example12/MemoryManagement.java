/**
 * 메모리 관리 실습 예제
 * 메모리 사용량 확인, 객체 생성, GC 이해를 종합적으로 보여줍니다
 */
public class MemoryManagement {
    // 정적 변수: Method Area
    static int objectCount = 0;
    
    // 인스턴스 변수: Heap (객체 생성 시)
    private String name;
    private int id;
    
    public MemoryManagement(String name, int id) {
        this.name = name;
        this.id = id;
        objectCount++;
    }
    
    // 메모리 정보 출력
    public static void printMemoryInfo(String label) {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();
        
        System.out.println("--- " + label + " ---");
        System.out.println("전체 메모리: " + totalMemory / 1024 / 1024 + " MB");
        System.out.println("사용 중인 메모리: " + usedMemory / 1024 / 1024 + " MB");
        System.out.println("사용 가능한 메모리: " + freeMemory / 1024 / 1024 + " MB");
        System.out.println("최대 메모리: " + maxMemory / 1024 / 1024 + " MB");
        System.out.println("메모리 사용률: " + 
            String.format("%.2f", (double) usedMemory / totalMemory * 100) + "%");
    }
    
    public static void main(String[] args) {
        System.out.println("=== 메모리 관리 실습 예제 ===\n");
        
        // 초기 메모리 상태
        printMemoryInfo("초기 상태");
        
        // 객체 생성
        System.out.println("\n--- 객체 생성 ---");
        MemoryManagement[] objects = new MemoryManagement[1000];
        for (int i = 0; i < 1000; i++) {
            objects[i] = new MemoryManagement("Object" + i, i);
        }
        System.out.println("생성된 객체 수: " + objectCount);
        
        printMemoryInfo("객체 생성 후");
        
        // 참조 해제
        System.out.println("\n--- 참조 해제 ---");
        objects = null;
        System.out.println("objects 배열 참조 해제");
        System.out.println("객체들은 가비지가 되었지만 아직 메모리에 존재");
        
        printMemoryInfo("참조 해제 후 (GC 전)");
        
        // GC 실행 (요청)
        System.out.println("\n--- GC 실행 요청 ---");
        System.out.println("System.gc() 호출 (GC 실행을 요청할 뿐, 보장하지 않음)");
        System.gc();
        
        // GC 실행 시간 제공
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        printMemoryInfo("GC 실행 후");
        
        // 메모리 영역 설명
        System.out.println("\n=== 메모리 영역별 저장 내용 ===");
        System.out.println("1. Stack (스택):");
        System.out.println("   - 지역 변수 (main 메서드의 objects 배열 참조)");
        System.out.println("   - 메서드 호출 정보");
        System.out.println("   - 메서드 매개변수");
        
        System.out.println("\n2. Heap (힙):");
        System.out.println("   - 객체 인스턴스 (MemoryManagement 객체들)");
        System.out.println("   - 배열 데이터");
        System.out.println("   - 인스턴스 변수 (name, id)");
        
        System.out.println("\n3. Method Area (메서드 영역):");
        System.out.println("   - 클래스 정보 (MemoryManagement 클래스)");
        System.out.println("   - 정적 변수 (objectCount)");
        System.out.println("   - 메서드 코드 (printMemoryInfo, main 등)");
        
        // 객체 생성과 메모리 관계
        System.out.println("\n=== 객체 생성과 메모리 ===");
        System.out.println("MemoryManagement obj = new MemoryManagement(\"Test\", 1);");
        System.out.println("\n메모리 할당 과정:");
        System.out.println("1. 힙에 MemoryManagement 객체 생성");
        System.out.println("2. 객체 내부에 name, id 저장 (힙)");
        System.out.println("3. 스택의 obj 변수에 힙 주소 저장 (참조)");
        System.out.println("4. Method Area의 objectCount 증가");
        
        System.out.println("\n메모리 상태:");
        System.out.println("Stack          Heap");
        System.out.println("┌─────┐       ┌──────────────────┐");
        System.out.println("│ obj │ ───→  │ MemoryManagement │");
        System.out.println("└─────┘       │ name: \"Test\"     │");
        System.out.println("              │ id: 1            │");
        System.out.println("              └──────────────────┘");
        
        System.out.println("\n=== 메모리 관리 팁 ===");
        System.out.println("1. 불필요한 객체 생성 최소화");
        System.out.println("2. 큰 객체는 신중하게 사용");
        System.out.println("3. 사용하지 않는 참조는 null로 설정");
        System.out.println("4. 컬렉션 크기 관리");
        System.out.println("5. 메모리 사용량 모니터링");
    }
}

