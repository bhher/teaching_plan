import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * 영어 단어 API 사용 예제 (JSON 파싱 포함)
 * 간단한 JSON 파싱을 포함한 완전한 예제
 */
public class WordAPIWithJSON {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) {
        System.out.println("=== 영어 단어 API (JSON 파싱) ===\n");
        
        // 단어 정의 가져오기
        getWordInfo("hello");
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        getWordInfo("java");
    }
    
    /**
     * 단어 정보를 가져와서 간단히 파싱하여 출력
     */
    public static void getWordInfo(String word) {
        try {
            String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
            
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl))
                    .timeout(Duration.ofSeconds(10))
                    .build();
            
            HttpResponse<String> response = httpClient.send(request, 
                    HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                
                System.out.println("단어: " + word.toUpperCase());
                System.out.println("=".repeat(30));
                
                // 간단한 문자열 파싱 (실제로는 JSON 라이브러리 사용 권장)
                parseSimpleJSON(jsonResponse);
                
            } else {
                System.out.println("단어를 찾을 수 없습니다: " + word);
                System.out.println("HTTP 상태 코드: " + response.statusCode());
            }
            
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 간단한 JSON 파싱 (문자열 메서드 사용)
     * 실제 프로젝트에서는 Jackson, Gson 등의 라이브러리 사용 권장
     */
    private static void parseSimpleJSON(String json) {
        // JSON에서 단어 추출
        if (json.contains("\"word\"")) {
            int wordStart = json.indexOf("\"word\"") + 8;
            int wordEnd = json.indexOf("\"", wordStart);
            if (wordEnd > wordStart) {
                String word = json.substring(wordStart, wordEnd);
                System.out.println("단어: " + word);
            }
        }
        
        // 의미(meanings) 추출 시도
        if (json.contains("\"meanings\"")) {
            System.out.println("\n의미:");
            // meanings 배열에서 간단히 추출
            int meaningsStart = json.indexOf("\"meanings\"");
            if (meaningsStart > 0) {
                String meaningsSection = json.substring(meaningsStart, 
                        Math.min(meaningsStart + 500, json.length()));
                
                // definitions 찾기
                if (meaningsSection.contains("\"definition\"")) {
                    int defStart = meaningsSection.indexOf("\"definition\"") + 14;
                    int defEnd = meaningsSection.indexOf("\"", defStart);
                    if (defEnd > defStart) {
                        String definition = meaningsSection.substring(defStart, defEnd);
                        System.out.println("  정의: " + definition);
                    }
                }
            }
        }
        
        System.out.println("\n전체 JSON 응답:");
        System.out.println(json);
    }
}
