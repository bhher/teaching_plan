import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * 영어 단어 API 사용 예제
 * Java 11+ HttpClient를 사용하여 무료 영어 단어 API를 호출합니다
 */
public class WordAPIExample {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) {
        System.out.println("=== 영어 단어 API 예제 ===\n");
        
        // 예제 1: 무료 영어 단어 API (Free Dictionary API)
        System.out.println("1. Free Dictionary API 사용:");
        getWordDefinition("hello");
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // 예제 2: 랜덤 단어 가져오기
        System.out.println("2. 랜덤 단어 가져오기:");
        getRandomWord();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // 예제 3: 단어 검색 (여러 단어)
        System.out.println("3. 여러 단어 검색:");
        String[] words = {"java", "programming", "computer"};
        for (String word : words) {
            getWordDefinition(word);
            System.out.println();
        }
    }
    
    /**
     * 단어의 정의를 가져오는 메서드
     * Free Dictionary API 사용: https://api.dictionaryapi.dev/api/v2/entries/en/{word}
     */
    public static void getWordDefinition(String word) {
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
                System.out.println("단어: " + word);
                System.out.println("응답: " + jsonResponse);
                // 실제로는 JSON 파싱 라이브러리(Jackson, Gson 등)를 사용하여 파싱합니다
            } else {
                System.out.println("단어: " + word);
                System.out.println("오류: HTTP " + response.statusCode());
            }
            
        } catch (Exception e) {
            System.out.println("단어: " + word);
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 랜덤 단어 가져오기
     * Random Word API 사용: https://random-word-api.herokuapp.com/word
     */
    public static void getRandomWord() {
        try {
            String apiUrl = "https://random-word-api.herokuapp.com/word";
            
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl))
                    .timeout(Duration.ofSeconds(10))
                    .build();
            
            HttpResponse<String> response = httpClient.send(request, 
                    HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                System.out.println("랜덤 단어 응답: " + jsonResponse);
                // JSON 형식: ["word"]
            } else {
                System.out.println("오류: HTTP " + response.statusCode());
            }
            
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
