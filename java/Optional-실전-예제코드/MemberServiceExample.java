package optional;

import java.util.Optional;

/**
 * Optional 실전 예제: MemberService 개선 버전
 * 
 * 기존 방식과 Optional 방식을 비교하여 보여주는 예제
 */
class MemberService {
    
    // ❌ 기존 방식: null 반환 (위험)
    public String findNameById_Old(int id) {
        if(id == 1){
            return "홍길동";
        }
        return null;  // null 반환
    }
    
    // ✅ Optional 방식: 안전한 반환
    public Optional<String> findNameById(int id) {
        if(id == 1){
            return Optional.of("홍길동");
        }
        return Optional.empty();
    }
}

public class MemberServiceExample {
    public static void main(String[] args) {
        MemberService service = new MemberService();
        
        System.out.println("=== Optional 실전 예제: MemberService ===\n");
        
        // ============================================
        // 예제 1: 기본 사용 (ifPresent)
        // ============================================
        System.out.println("1. 기본 사용 (ifPresent):");
        System.out.println("-------------------");
        
        Optional<String> name = service.findNameById(2);
        
        // 값이 있을 때만 실행 (안전함)
        name.ifPresent(n -> System.out.println("  이름 길이: " + n.length()));
        
        System.out.println("  프로그램 정상 종료");
        System.out.println();
        
        // ============================================
        // 예제 2: 기본값 제공 (orElse)
        // ============================================
        System.out.println("2. 기본값 제공 (orElse):");
        System.out.println("-------------------");
        
        String result = service.findNameById(2)
                .orElse("이름없음");
        
        System.out.println("  결과: " + result);
        System.out.println("  길이: " + result.length());
        System.out.println();
        
        // ============================================
        // 예제 3: 예외 던지기 (orElseThrow)
        // ============================================
        System.out.println("3. 예외 던지기 (orElseThrow):");
        System.out.println("-------------------");
        
        try {
            String name2 = service.findNameById(2)
                    .orElseThrow(() -> new RuntimeException("회원이 없습니다"));
            
            System.out.println("  이름: " + name2);
        } catch (RuntimeException e) {
            System.out.println("  오류: " + e.getMessage());
        }
        System.out.println();
        
        // ============================================
        // 예제 4: 값 변환 (map)
        // ============================================
        System.out.println("4. 값 변환 (map):");
        System.out.println("-------------------");
        
        Optional<Integer> length = service.findNameById(1)
                .map(String::length);
        
        length.ifPresent(len -> System.out.println("  길이: " + len));
        System.out.println();
        
        // ============================================
        // 예제 5: 조건부 실행 (ifPresentOrElse)
        // ============================================
        System.out.println("5. 조건부 실행 (ifPresentOrElse):");
        System.out.println("-------------------");
        
        System.out.println("  ID 1 조회:");
        service.findNameById(1)
                .ifPresentOrElse(
                    n -> System.out.println("    찾음: " + n),
                    () -> System.out.println("    없음")
                );
        
        System.out.println("  ID 2 조회:");
        service.findNameById(2)
                .ifPresentOrElse(
                    n -> System.out.println("    찾음: " + n),
                    () -> System.out.println("    없음")
                );
        System.out.println();
        
        // ============================================
        // 예제 6: 필터링 (filter)
        // ============================================
        System.out.println("6. 필터링 (filter):");
        System.out.println("-------------------");
        
        System.out.println("  길이가 3 이상인 이름:");
        service.findNameById(1)
                .filter(n -> n.length() >= 3)
                .ifPresent(n -> System.out.println("    " + n));
        
        System.out.println("  길이가 10 이상인 이름:");
        service.findNameById(1)
                .filter(n -> n.length() >= 10)
                .ifPresent(n -> System.out.println("    " + n));
        // 출력 없음
        System.out.println();
        
        // ============================================
        // 비교: 기존 방식 vs Optional 방식
        // ============================================
        System.out.println("7. 기존 방식 vs Optional 방식 비교:");
        System.out.println("-------------------");
        
        // 기존 방식 (위험)
        System.out.println("  기존 방식 (null 반환):");
        String oldName = service.findNameById_Old(2);
        if(oldName != null) {
            System.out.println("    이름: " + oldName);
        } else {
            System.out.println("    이름이 없습니다");
        }
        
        // Optional 방식 (안전)
        System.out.println("  Optional 방식:");
        service.findNameById(2)
                .ifPresentOrElse(
                    n -> System.out.println("    이름: " + n),
                    () -> System.out.println("    이름이 없습니다")
                );
        
        System.out.println("\n프로그램 정상 종료");
    }
}
