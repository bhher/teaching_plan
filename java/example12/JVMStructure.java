/**
 * JVM 구조를 설명하는 예제
 * JVM의 주요 구성 요소를 보여줍니다
 */
public class JVMStructure {
    public static void main(String[] args) {
        System.out.println("=== JVM 구조 ===\n");
        
        System.out.println("--- JVM의 주요 구성 요소 ---");
        System.out.println("1. Class Loader (클래스 로더)");
        System.out.println("   - 클래스 파일(.class)을 메모리에 로드");
        System.out.println("   - 클래스 검증 및 준비");
        System.out.println();
        
        System.out.println("2. Runtime Data Area (런타임 데이터 영역)");
        System.out.println("   - Method Area: 클래스 정보, 정적 변수");
        System.out.println("   - Heap: 객체 저장");
        System.out.println("   - Stack: 메서드 호출, 지역 변수");
        System.out.println("   - PC Register: 현재 실행 중인 명령어 주소");
        System.out.println("   - Native Method Stack: 네이티브 메서드");
        System.out.println();
        
        System.out.println("3. Execution Engine (실행 엔진)");
        System.out.println("   - Interpreter: 바이트코드를 한 줄씩 실행");
        System.out.println("   - JIT Compiler: 자주 사용되는 코드를 기계어로 컴파일");
        System.out.println("   - Garbage Collector: 사용하지 않는 객체 제거");
        System.out.println();
        
        System.out.println("4. Native Method Interface");
        System.out.println("   - C/C++ 등 네이티브 코드와의 인터페이스");
        System.out.println();
        
        // JVM 정보 출력
        System.out.println("--- JVM 정보 ---");
        Runtime runtime = Runtime.getRuntime();
        System.out.println("프로세서 수: " + runtime.availableProcessors());
        System.out.println("전체 메모리: " + runtime.totalMemory() / 1024 / 1024 + " MB");
        System.out.println("최대 메모리: " + runtime.maxMemory() / 1024 / 1024 + " MB");
        System.out.println("사용 가능한 메모리: " + runtime.freeMemory() / 1024 / 1024 + " MB");
        
        System.out.println("\n--- JVM의 역할 ---");
        System.out.println("1. 바이트코드 실행: .class 파일을 실행");
        System.out.println("2. 메모리 관리: 메모리 할당 및 가비지 컬렉션");
        System.out.println("3. 플랫폼 독립성: 운영체제에 맞게 변환");
        System.out.println("4. 보안 관리: Java 프로그램의 안전한 실행");
    }
}

