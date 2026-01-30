package a0127.lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 람다 표현식 예제 모음
 */
public class LambdaExamples {
    
    public static void main(String[] args) {
        System.out.println("=== 람다 표현식 예제 ===\n");
        
        // 1. 기본 예제
        basicExamples();
        
        // 2. 컬렉션 예제
        collectionExamples();
        
        // 3. 학생 관리 예제
        studentManagementExample();
        
        // 4. 계산기 예제
        calculatorExample();
        
        // 5. 필터링 및 변환 예제
        filterTransformExample();
    }
    
    // 1. 기본 예제
    public static void basicExamples() {
        System.out.println("1. 기본 예제");
        System.out.println("-------------------");
        
        // 정수 두 개를 더하는 람다
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("덧셈: " + add.apply(10, 20));  // 30
        
        // 문자열 길이 반환
        Function<String, Integer> getLength = s -> s.length();
        System.out.println("문자열 길이: " + getLength.apply("Java"));  // 4
        
        // 짝수 판별
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("4는 짝수? " + isEven.test(4));  // true
        System.out.println("5는 짝수? " + isEven.test(5));  // false
        
        // 출력
        Consumer<String> print = s -> System.out.println("출력: " + s);
        print.accept("Hello Lambda");
        
        // 공급자 (매개변수 없음)
        Supplier<String> greeting = () -> "Hello World";
        System.out.println(greeting.get());
        
        System.out.println();
    }
    
    // 2. 컬렉션 예제
    public static void collectionExamples() {
        System.out.println("2. 컬렉션 예제");
        System.out.println("-------------------");
        
        List<String> names = Arrays.asList("김철수", "이영희", "박민수", "최지영");
        
        // 정렬 (기존 방식)
        List<String> sorted1 = new ArrayList<>(names);
        Collections.sort(sorted1, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        System.out.println("기존 방식 정렬: " + sorted1);
        
        // 정렬 (람다 방식)
        List<String> sorted2 = new ArrayList<>(names);
        sorted2.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("람다 방식 정렬: " + sorted2);
        
        // Stream API 사용
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 짝수만 필터링
        List<Integer> evens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("짝수: " + evens);  // [2, 4, 6, 8, 10]
        
        // 각 숫자에 2 곱하기
        List<Integer> doubled = numbers.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("2배: " + doubled);
        
        // 합계 구하기
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("합계: " + sum);  // 55
        
        System.out.println();
    }
    
    // 3. 학생 관리 예제
    public static void studentManagementExample() {
        System.out.println("3. 학생 관리 예제");
        System.out.println("-------------------");
        
        List<Student> students = Arrays.asList(
            new Student("김철수", 85),
            new Student("이영희", 92),
            new Student("박민수", 78),
            new Student("최지영", 95),
            new Student("정수진", 88)
        );
        
        // 점수로 정렬 (내림차순)
        students.sort((s1, s2) -> s2.getScore() - s1.getScore());
        System.out.println("점수 내림차순:");
        students.forEach(s -> System.out.println("  " + s));
        
        // 90점 이상 학생만 필터링
        List<Student> topStudents = students.stream()
            .filter(s -> s.getScore() >= 90)
            .collect(Collectors.toList());
        System.out.println("\n90점 이상 학생:");
        topStudents.forEach(s -> System.out.println("  " + s));
        
        // 학생 이름만 추출
        List<String> names = students.stream()
            .map(Student::getName)  // 메서드 참조 사용
            .collect(Collectors.toList());
        System.out.println("\n학생 이름 목록: " + names);
        
        // 평균 점수 계산
        double average = students.stream()
            .mapToInt(Student::getScore)
            .average()
            .orElse(0.0);
        System.out.println("\n평균 점수: " + average);
        
        System.out.println();
    }
    
    // 4. 계산기 예제
    public static void calculatorExample() {
        System.out.println("4. 계산기 예제");
        System.out.println("-------------------");
        
        // 사칙연산을 람다로 표현
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> divide = (a, b) -> b != 0 ? a / b : 0;
        
        int x = 20;
        int y = 5;
        
        System.out.println("x = " + x + ", y = " + y);
        System.out.println("덧셈: " + add.apply(x, y));      // 25
        System.out.println("뺄셈: " + subtract.apply(x, y)); // 15
        System.out.println("곱셈: " + multiply.apply(x, y)); // 100
        System.out.println("나눗셈: " + divide.apply(x, y)); // 4
        
        // 계산 실행
        System.out.println("\n계산 함수 사용:");
        calculate(10, 3, add);
        calculate(10, 3, multiply);
        
        System.out.println();
    }
    
    // 계산 실행 메서드
    static void calculate(int a, int b, BiFunction<Integer, Integer, Integer> operation) {
        int result = operation.apply(a, b);
        System.out.println("  " + a + "와 " + b + "의 연산 결과: " + result);
    }
    
    // 5. 필터링 및 변환 예제
    public static void filterTransformExample() {
        System.out.println("5. 필터링 및 변환 예제");
        System.out.println("-------------------");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 짝수만 필터링
        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> evens = numbers.stream()
            .filter(isEven)
            .collect(Collectors.toList());
        System.out.println("짝수: " + evens);  // [2, 4, 6, 8, 10]
        
        // 5보다 큰 수만 필터링
        Predicate<Integer> greaterThan5 = n -> n > 5;
        List<Integer> largeNumbers = numbers.stream()
            .filter(greaterThan5)
            .collect(Collectors.toList());
        System.out.println("5보다 큰 수: " + largeNumbers);  // [6, 7, 8, 9, 10]
        
        // 각 숫자를 제곱으로 변환
        Function<Integer, Integer> square = n -> n * n;
        List<Integer> squares = numbers.stream()
            .map(square)
            .collect(Collectors.toList());
        System.out.println("제곱: " + squares);
        
        // 조건을 조합 (짝수이면서 5보다 큰 수)
        Predicate<Integer> isEvenAndGreaterThan5 = isEven.and(greaterThan5);
        List<Integer> result = numbers.stream()
            .filter(isEvenAndGreaterThan5)
            .collect(Collectors.toList());
        System.out.println("짝수이면서 5보다 큰 수: " + result);  // [6, 8, 10]
        
        System.out.println();
    }
}

// Student 클래스
class Student {
    private String name;
    private int score;
    
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    @Override
    public String toString() {
        return name + ": " + score + "점";
    }
}
