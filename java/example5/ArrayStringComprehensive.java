/**
 * 배열과 문자열 종합 예제
 * 배열과 문자열을 함께 사용하는 실전 예제를 보여줍니다
 */
public class ArrayStringComprehensive {
    public static void main(String[] args) {
        System.out.println("=== 배열과 문자열 종합 예제 ===\n");
        
        // 1. 이름 배열과 길이 출력
        System.out.println("--- 1. 이름 배열과 길이 ---");
        String[] names = {"홍길동", "김철수", "이영희", "박민수", "최지영"};
        
        for (String name : names) {
            System.out.println(name + "의 길이: " + name.length());
        }
        
        // 2. 점수 배열과 등급 판정
        System.out.println("\n--- 2. 점수 배열과 등급 판정 ---");
        int[] scores = {95, 87, 78, 65, 92};
        String[] students = {"홍길동", "김철수", "이영희", "박민수", "최지영"};
        
        for (int i = 0; i < scores.length; i++) {
            String grade;
            if (scores[i] >= 90) {
                grade = "A";
            } else if (scores[i] >= 80) {
                grade = "B";
            } else if (scores[i] >= 70) {
                grade = "C";
            } else {
                grade = "F";
            }
            System.out.println(students[i] + ": " + scores[i] + "점 → " + grade + "등급");
        }
        
        // 3. 문자열 배열 검색
        System.out.println("\n--- 3. 문자열 배열에서 검색 ---");
        String[] fruits = {"사과", "바나나", "오렌지", "사과", "포도", "사과"};
        String target = "사과";
        int count = 0;
        
        for (String fruit : fruits) {
            if (fruit.equals(target)) {
                count++;
            }
        }
        System.out.println("\"" + target + "\"의 개수: " + count);
        
        // 4. 문자열 배열 정렬 (간단한 버블 정렬)
        System.out.println("\n--- 4. 문자열 배열 정렬 ---");
        String[] words = {"banana", "apple", "cherry", "date"};
        System.out.println("정렬 전: " + java.util.Arrays.toString(words));
        
        // 간단한 정렬 (사전식 순서)
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].compareTo(words[j]) > 0) {
                    String temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
        System.out.println("정렬 후: " + java.util.Arrays.toString(words));
        
        // 5. 2차원 배열과 문자열
        System.out.println("\n--- 5. 학생 정보 2차원 배열 ---");
        String[][] studentInfo = {
            {"홍길동", "20", "컴퓨터공학"},
            {"김철수", "21", "전자공학"},
            {"이영희", "19", "컴퓨터공학"}
        };
        
        for (String[] info : studentInfo) {
            System.out.println("이름: " + info[0] + ", 나이: " + info[1] + ", 전공: " + info[2]);
        }
        
        // 6. 문자열 분리와 배열
        System.out.println("\n--- 6. 문자열 분리와 배열 ---");
        String data = "홍길동,20,서울|김철수,21,부산|이영희,19,대구";
        String[] records = data.split("\\|");  // |는 특수문자이므로 \\ 필요
        
        for (String record : records) {
            String[] fields = record.split(",");
            System.out.println("이름: " + fields[0] + ", 나이: " + fields[1] + ", 지역: " + fields[2]);
        }
        
        // 7. 문자열 배열을 하나의 문자열로 합치기
        System.out.println("\n--- 7. 문자열 배열 합치기 ---");
        String[] parts = {"안녕", "하세요", "Java", "프로그래밍"};
        
        // 방법 1: 반복문 사용
        String result1 = "";
        for (int i = 0; i < parts.length; i++) {
            result1 += parts[i];
            if (i < parts.length - 1) {
                result1 += " ";
            }
        }
        System.out.println("방법 1: " + result1);
        
        // 방법 2: StringBuilder 사용 (권장)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
            if (i < parts.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println("방법 2: " + sb.toString());
        
        // 8. 문자열 배열에서 특정 문자로 시작하는 항목 찾기
        System.out.println("\n--- 8. 특정 문자로 시작하는 항목 찾기 ---");
        String[] cities = {"서울", "부산", "대구", "인천", "서울시"};
        String prefix = "서울";
        
        System.out.print("\"" + prefix + "\"로 시작하는 도시: ");
        for (String city : cities) {
            if (city.startsWith(prefix)) {
                System.out.print(city + " ");
            }
        }
        System.out.println();
        
        // 9. 문자열 배열의 각 요소 처리
        System.out.println("\n--- 9. 문자열 배열 요소 처리 ---");
        String[] texts = {"hello", "WORLD", "Java", "PROGRAMMING"};
        
        System.out.println("원본:");
        for (String text : texts) {
            System.out.print(text + " ");
        }
        System.out.println();
        
        System.out.println("대문자로 변환:");
        for (String text : texts) {
            System.out.print(text.toUpperCase() + " ");
        }
        System.out.println();
        
        System.out.println("소문자로 변환:");
        for (String text : texts) {
            System.out.print(text.toLowerCase() + " ");
        }
        System.out.println();
        
        System.out.println("\n=== 예제 완료 ===");
    }
}

