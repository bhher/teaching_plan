/**
 * Heap 영역을 설명하는 예제
 * 객체 생성과 힙 메모리 사용을 보여줍니다
 */
public class HeapExample {
    public static void main(String[] args) {
        System.out.println("=== Heap 영역 예제 ===\n");
        
        // 예제 1: 객체 생성 (힙에 저장)
        System.out.println("--- 예제 1: 객체 생성 ---");
        String str1 = new String("Hello");
        String str2 = new String("World");
        
        System.out.println("str1: " + str1 + " (힙에 객체 생성)");
        System.out.println("str2: " + str2 + " (힙에 객체 생성)");
        
        // 메모리 상태 설명
        System.out.println("\n메모리 상태:");
        System.out.println("Stack          Heap");
        System.out.println("┌─────┐       ┌──────────┐");
        System.out.println("│str1 │ ───→  │ \"Hello\"  │");
        System.out.println("├─────┤       ├──────────┤");
        System.out.println("│str2 │ ───→  │ \"World\"  │");
        System.out.println("└─────┘       └──────────┘");
        
        // 예제 2: 배열 생성 (힙에 저장)
        System.out.println("\n--- 예제 2: 배열 생성 ---");
        int[] numbers = new int[5];
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        
        System.out.println("배열: " + java.util.Arrays.toString(numbers));
        System.out.println("배열도 힙에 저장됨");
        
        // 예제 3: 객체 참조 공유
        System.out.println("\n--- 예제 3: 객체 참조 공유 ---");
        String str3 = str1;  // 같은 객체를 참조
        System.out.println("str1: " + str1);
        System.out.println("str3: " + str3);
        System.out.println("str1 == str3: " + (str1 == str3));  // 같은 객체 참조
        
        System.out.println("\n메모리 상태:");
        System.out.println("Stack          Heap");
        System.out.println("┌─────┐       ┌──────────┐");
        System.out.println("│str1 │ ────→ │ \"Hello\"  │");
        System.out.println("│str3 │ ────→ │          │");
        System.out.println("└─────┘       └──────────┘");
        System.out.println("(같은 객체를 참조)");
        
        // 예제 4: 객체 변경
        System.out.println("\n--- 예제 4: 객체 변경 ---");
        str1 = new String("Changed");
        System.out.println("str1: " + str1);
        System.out.println("str3: " + str3);
        System.out.println("str1 == str3: " + (str1 == str3));  // 다른 객체 참조
        
        // 예제 5: 인스턴스 변수 (힙에 저장)
        System.out.println("\n--- 예제 5: 인스턴스 변수 ---");
        Student student = new Student("홍길동", 20);
        System.out.println("학생: " + student.getName() + ", " + student.getAge());
        System.out.println("인스턴스 변수는 힙의 객체 안에 저장됨");
        
        // 예제 6: 힙 메모리 사용량 확인
        System.out.println("\n--- 예제 6: 힙 메모리 사용량 ---");
        Runtime runtime = Runtime.getRuntime();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        
        // 많은 객체 생성
        System.out.println("객체 생성 전 메모리: " + beforeMemory / 1024 + " KB");
        
        String[] objects = new String[10000];
        for (int i = 0; i < 10000; i++) {
            objects[i] = new String("Object " + i);
        }
        
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("객체 생성 후 메모리: " + afterMemory / 1024 + " KB");
        System.out.println("사용된 메모리: " + (afterMemory - beforeMemory) / 1024 + " KB");
        
        System.out.println("\n=== Heap의 특징 ===");
        System.out.println("1. 객체와 배열이 저장되는 영역");
        System.out.println("2. 모든 스레드가 공유");
        System.out.println("3. 동적으로 메모리 할당");
        System.out.println("4. 가비지 컬렉션으로 자동 정리");
        System.out.println("5. 참조로 접근 (스택에 참조 저장)");
    }
    
    // 내부 클래스
    static class Student {
        private String name;
        private int age;
        
        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
    }
}

