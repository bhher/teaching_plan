/**
 * Garbage Collection을 설명하는 예제
 * 객체가 가비지가 되는 조건과 GC 동작을 보여줍니다
 */
public class GarbageCollectionExample {
    public static void main(String[] args) {
        System.out.println("=== Garbage Collection 예제 ===\n");
        
        Runtime runtime = Runtime.getRuntime();
        
        // 초기 메모리 상태
        System.out.println("--- 초기 메모리 상태 ---");
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("사용 중인 메모리: " + initialMemory / 1024 + " KB");
        
        // 예제 1: 객체 생성
        System.out.println("\n--- 예제 1: 객체 생성 ---");
        String obj1 = new String("Object 1");
        String obj2 = new String("Object 2");
        String obj3 = new String("Object 3");
        
        System.out.println("obj1: " + obj1);
        System.out.println("obj2: " + obj2);
        System.out.println("obj3: " + obj3);
        
        long afterCreation = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("객체 생성 후 메모리: " + afterCreation / 1024 + " KB");
        
        // 예제 2: 참조 변경 (가비지 생성)
        System.out.println("\n--- 예제 2: 참조 변경 (가비지 생성) ---");
        System.out.println("obj1 = obj2 실행");
        obj1 = obj2;  // "Object 1"은 가비지가 됨
        
        System.out.println("obj1: " + obj1);
        System.out.println("obj2: " + obj2);
        System.out.println("\"Object 1\"은 이제 가비지 (참조 없음)");
        
        // 예제 3: null 할당 (가비지 생성)
        System.out.println("\n--- 예제 3: null 할당 (가비지 생성) ---");
        System.out.println("obj3 = null 실행");
        obj3 = null;  // "Object 3"은 가비지가 됨
        
        System.out.println("obj3: " + obj3);
        System.out.println("\"Object 3\"은 이제 가비지 (null 참조)");
        
        // 예제 4: 지역 변수 (메서드 종료 시 가비지)
        System.out.println("\n--- 예제 4: 지역 변수 (메서드 종료 시 가비지) ---");
        createLocalObject();
        
        // 예제 5: 많은 객체 생성
        System.out.println("\n--- 예제 5: 많은 객체 생성 ---");
        String[] objects = new String[10000];
        for (int i = 0; i < 10000; i++) {
            objects[i] = new String("Temp Object " + i);
        }
        
        long afterManyObjects = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("많은 객체 생성 후 메모리: " + afterManyObjects / 1024 + " KB");
        
        // 참조 해제
        System.out.println("\n참조 해제 (objects = null)");
        objects = null;  // 배열과 모든 요소가 가비지가 됨
        
        // GC 강제 실행 (실제로는 권장하지 않음, 예제용)
        System.out.println("\n--- GC 실행 (System.gc()) ---");
        System.out.println("주의: System.gc()는 GC 실행을 요청할 뿐, 보장하지 않음");
        System.gc();
        
        // 잠시 대기 (GC가 실행될 시간 제공)
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long afterGC = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("GC 후 메모리: " + afterGC / 1024 + " KB");
        System.out.println("해제된 메모리: " + (afterManyObjects - afterGC) / 1024 + " KB");
        
        System.out.println("\n=== Garbage Collection의 특징 ===");
        System.out.println("1. 자동 메모리 관리: 프로그래머가 직접 메모리 해제 불필요");
        System.out.println("2. 참조가 없는 객체를 자동으로 제거");
        System.out.println("3. Stop-the-World: GC 실행 중 일시 정지");
        System.out.println("4. 힙 영역을 Young/Old Generation으로 분리");
        System.out.println("5. Minor GC: Young Generation에서 발생");
        System.out.println("6. Major GC: Old Generation에서 발생");
        
        System.out.println("\n=== 객체가 가비지가 되는 조건 ===");
        System.out.println("1. 참조가 null인 경우");
        System.out.println("2. 참조가 다른 객체를 가리키는 경우");
        System.out.println("3. 지역 변수가 메서드 종료 후");
        System.out.println("4. 순환 참조가 있지만 외부 참조가 없는 경우");
    }
    
    public static void createLocalObject() {
        String localObj = new String("Local Object");
        System.out.println("지역 객체 생성: " + localObj);
        System.out.println("메서드 종료 시 가비지가 됨");
    }
}

