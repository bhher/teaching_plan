import java.util.*;

/**
 * 컬렉션 프레임워크 종합 예제
 * List, Set, Map을 모두 활용한 실전 예제
 */
public class CollectionComprehensive {
    public static void main(String[] args) {
        System.out.println("=== 컬렉션 프레임워크 종합 예제 ===\n");
        
        // 예제 1: 학생 점수 관리 (ArrayList)
        System.out.println("--- 예제 1: 학생 점수 관리 ---");
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(85);
        scores.add(90);
        scores.add(78);
        scores.add(92);
        scores.add(88);
        
        System.out.println("점수: " + scores);
        
        // 평균 계산
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = (double) sum / scores.size();
        System.out.println("평균: " + String.format("%.2f", average));
        
        // 최댓값, 최솟값
        int max = Collections.max(scores);
        int min = Collections.min(scores);
        System.out.println("최댓값: " + max);
        System.out.println("최솟값: " + min);
        
        // 예제 2: 중복 제거 (HashSet)
        System.out.println("\n--- 예제 2: 중복 제거 ---");
        ArrayList<String> list = new ArrayList<>();
        list.add("사과");
        list.add("바나나");
        list.add("사과");
        list.add("오렌지");
        list.add("바나나");
        list.add("포도");
        
        System.out.println("원본 리스트: " + list);
        
        HashSet<String> uniqueSet = new HashSet<>(list);
        System.out.println("중복 제거: " + uniqueSet);
        
        // 예제 3: 단어 빈도 계산 (HashMap)
        System.out.println("\n--- 예제 3: 단어 빈도 계산 ---");
        String[] words = {"사과", "바나나", "사과", "오렌지", "바나나", "사과", "포도", "오렌지"};
        
        HashMap<String, Integer> frequency = new HashMap<>();
        
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        
        System.out.println("단어 빈도:");
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "번");
        }
        
        // 예제 4: 학생 정보 관리 (종합)
        System.out.println("\n--- 예제 4: 학생 정보 관리 ---");
        
        // 학생 이름 리스트
        ArrayList<String> students = new ArrayList<>();
        students.add("홍길동");
        students.add("김철수");
        students.add("이영희");
        students.add("박민수");
        
        // 학생 점수 맵
        HashMap<String, Integer> studentScores = new HashMap<>();
        studentScores.put("홍길동", 85);
        studentScores.put("김철수", 90);
        studentScores.put("이영희", 88);
        studentScores.put("박민수", 92);
        
        // 학생 정보 출력
        System.out.println("학생 정보:");
        for (String name : students) {
            int score = studentScores.get(name);
            String grade = getGrade(score);
            System.out.println(name + ": " + score + "점 (" + grade + "등급)");
        }
        
        // 평균 점수 계산
        int totalScore = 0;
        for (int score : studentScores.values()) {
            totalScore += score;
        }
        double avgScore = (double) totalScore / studentScores.size();
        System.out.println("\n평균 점수: " + String.format("%.2f", avgScore));
        
        // 예제 5: 정렬
        System.out.println("\n--- 예제 5: 정렬 ---");
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(9);
        numbers.add(3);
        
        System.out.println("정렬 전: " + numbers);
        Collections.sort(numbers);
        System.out.println("정렬 후: " + numbers);
        
        // 역순 정렬
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("역순 정렬: " + numbers);
        
        // 예제 6: 리스트 섞기
        System.out.println("\n--- 예제 6: 리스트 섞기 ---");
        ArrayList<String> cards = new ArrayList<>();
        cards.add("A");
        cards.add("2");
        cards.add("3");
        cards.add("4");
        cards.add("5");
        
        System.out.println("섞기 전: " + cards);
        Collections.shuffle(cards);
        System.out.println("섞기 후: " + cards);
        
        // 예제 7: 최빈값 찾기
        System.out.println("\n--- 예제 7: 최빈값 찾기 ---");
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(2);
        data.add(3);
        data.add(3);
        data.add(3);
        data.add(4);
        
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (Integer num : data) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        int maxCount = 0;
        int mode = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        
        System.out.println("데이터: " + data);
        System.out.println("최빈값: " + mode + " (출현 횟수: " + maxCount + ")");
        
        System.out.println("\n=== 컬렉션 프레임워크의 장점 ===");
        System.out.println("1. 동적 크기: 필요에 따라 크기 자동 조정");
        System.out.println("2. 다양한 자료구조: List, Set, Map 제공");
        System.out.println("3. 편리한 메서드: 추가, 삭제, 검색 등");
        System.out.println("4. 타입 안정성: 제네릭으로 타입 보장");
    }
    
    // 등급 계산 메서드
    private static String getGrade(int score) {
        if (score >= 90) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }
}

