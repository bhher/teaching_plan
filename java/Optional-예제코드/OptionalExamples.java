package optional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Java Optional 완전 정복 예제 코드
 */
public class OptionalExamples {
    
    public static void main(String[] args) {
        System.out.println("=== Optional 예제 모음 ===\n");
        
        example1_BasicUsage();
        example2_Chaining();
        example3_WithStream();
        example4_NestedOptional();
        example5_ScoreCalculation();
        example6_ConditionalProcessing();
    }
    
    // 예제 1: 기본 사용법
    public static void example1_BasicUsage() {
        System.out.println("1. 기본 사용법");
        System.out.println("-------------------");
        
        // Optional.of() - null이 아닌 값
        Optional<String> opt1 = Optional.of("Hello");
        System.out.println("Optional.of(\"Hello\"): " + opt1.get());
        
        // Optional.ofNullable() - null일 수 있는 값
        String name = null;
        Optional<String> opt2 = Optional.ofNullable(name);
        System.out.println("Optional.ofNullable(null): " + opt2.isPresent());
        
        // Optional.empty() - 빈 Optional
        Optional<String> opt3 = Optional.empty();
        System.out.println("Optional.empty().isPresent(): " + opt3.isPresent());
        
        // orElse() - 기본값 제공
        String result = opt2.orElse("기본값");
        System.out.println("null Optional.orElse(\"기본값\"): " + result);
        
        System.out.println();
    }
    
    // 예제 2: 체이닝
    public static void example2_Chaining() {
        System.out.println("2. 체이닝을 통한 데이터 처리");
        System.out.println("-------------------");
        
        String input = "  hello  ";
        String result = Optional.ofNullable(input)
            .map(String::trim)           // 공백 제거
            .map(String::toUpperCase)     // 대문자 변환
            .filter(s -> s.length() > 0)  // 길이 체크
            .orElse("기본값");
        
        System.out.println("입력: \"" + input + "\"");
        System.out.println("결과: \"" + result + "\"");
        
        // null 처리
        String result2 = Optional.ofNullable(null)
            .map(String::trim)
            .orElse("null 처리됨");
        System.out.println("null 처리 결과: " + result2);
        
        System.out.println();
    }
    
    // 예제 3: Stream과 함께 사용
    public static void example3_WithStream() {
        System.out.println("3. Stream과 함께 사용");
        System.out.println("-------------------");
        
        List<String> names = Arrays.asList("김철수", null, "이영희", "", "박민수", null);
        
        // null 제거하고 필터링
        List<String> validNames = names.stream()
            .filter(Objects::nonNull)       // null 제거
            .filter(s -> !s.isEmpty())      // 빈 문자열 제거
            .collect(Collectors.toList());
        
        System.out.println("원본: " + names);
        System.out.println("필터링 후: " + validNames);
        
        System.out.println();
    }
    
    // 예제 4: 중첩 Optional 처리
    public static void example4_NestedOptional() {
        System.out.println("4. 중첩 Optional 처리");
        System.out.println("-------------------");
        
        // flatMap 사용 예제
        Optional<String> opt = Optional.of("hello");
        
        // map() 사용 시 중첩됨
        Optional<Optional<String>> nested = opt.map(s -> Optional.of(s.toUpperCase()));
        System.out.println("map() 결과 (중첩): " + nested);
        
        // flatMap() 사용 시 펼쳐짐
        Optional<String> flat = opt.flatMap(s -> Optional.of(s.toUpperCase()));
        System.out.println("flatMap() 결과: " + flat.get());
        
        System.out.println();
    }
    
    // 예제 5: 점수 계산 시스템
    public static void example5_ScoreCalculation() {
        System.out.println("5. 점수 계산 시스템");
        System.out.println("-------------------");
        
        List<Student> students = Arrays.asList(
            new Student("김철수", 85),
            new Student("이영희", null),
            new Student("박민수", 92),
            new Student("최지영", null)
        );
        
        // 평균 점수 계산
        double average = students.stream()
            .map(Student::getScore)
            .filter(Optional::isPresent)
            .mapToInt(opt -> opt.get())
            .average()
            .orElse(0.0);
        
        System.out.println("평균 점수: " + average);
        
        // 점수가 있는 학생만 출력
        System.out.println("\n점수가 있는 학생:");
        students.stream()
            .filter(s -> s.getScore().isPresent())
            .forEach(s -> System.out.println("  " + s.getName() + ": " + s.getScore().get()));
        
        // 점수가 없는 학생 찾기
        System.out.println("\n점수가 없는 학생:");
        students.stream()
            .filter(s -> s.getScore().isEmpty())
            .forEach(s -> System.out.println("  " + s.getName()));
        
        System.out.println();
    }
    
    // 예제 6: 조건부 처리
    public static void example6_ConditionalProcessing() {
        System.out.println("6. 조건부 처리");
        System.out.println("-------------------");
        
        Optional<String> longName = Optional.of("John");
        Optional<String> shortName = Optional.of("Jo");
        
        // 길이가 3보다 크면 처리
        System.out.println("긴 이름 처리:");
        longName.filter(n -> n.length() > 3)
            .ifPresent(n -> System.out.println("  긴 이름: " + n));
        
        // 짧은 이름 처리
        System.out.println("\n짧은 이름 처리:");
        shortName.filter(n -> n.length() > 3)
            .ifPresentOrElse(
                n -> System.out.println("  긴 이름: " + n),
                () -> System.out.println("  이름이 너무 짧습니다")
            );
        
        System.out.println();
    }
}

// Student 클래스
class Student {
    private String name;
    private Optional<Integer> score;
    
    public Student(String name, Integer score) {
        this.name = name;
        this.score = Optional.ofNullable(score);
    }
    
    public String getName() {
        return name;
    }
    
    public Optional<Integer> getScore() {
        return score;
    }
}
