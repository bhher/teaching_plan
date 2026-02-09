# Java로 영어 단어 API 사용하기

## 목차

1. [개요](#개요)
2. [필요한 것](#필요한-것)
3. [무료 영어 단어 API](#무료-영어-단어-api)
4. [Java HttpClient 사용법](#java-httpclient-사용법)
5. [실전 예제](#실전-예제)
6. [JSON 파싱](#json-파싱)
7. [주의사항](#주의사항)

---

## 개요

Java에서 HTTP 요청을 통해 영어 단어 API를 호출하여 단어의 정의, 발음, 예문 등을 가져올 수 있습니다.

### 가능한 작업

- 단어 정의 조회
- 발음 정보 가져오기
- 예문 가져오기
- 랜덤 단어 생성
- 단어 검증

---

## 필요한 것

### 1. Java 버전

- **Java 11 이상**: `HttpClient` 클래스 내장
- **Java 8 이하**: 외부 라이브러리 필요 (Apache HttpClient, OkHttp 등)

### 2. JSON 파싱 라이브러리 (선택사항)

- **Jackson**: 가장 인기 있는 JSON 라이브러리
- **Gson**: Google의 JSON 라이브러리
- **org.json**: 간단한 JSON 처리

---

## 무료 영어 단어 API

### 1. Free Dictionary API

**URL**: `https://api.dictionaryapi.dev/api/v2/entries/en/{word}`

**특징:**
- 완전 무료
- API 키 불필요
- 단어 정의, 발음, 예문 제공
- HTTPS 지원

**예제:**
```
https://api.dictionaryapi.dev/api/v2/entries/en/hello
```

**응답 형식:**
```json
[
  {
    "word": "hello",
    "phonetic": "/həˈloʊ/",
    "meanings": [
      {
        "partOfSpeech": "noun",
        "definitions": [
          {
            "definition": "A greeting.",
            "example": "Hello, how are you?"
          }
        ]
      }
    ]
  }
]
```

### 2. Random Word API

**URL**: `https://random-word-api.herokuapp.com/word`

**특징:**
- 랜덤 단어 생성
- API 키 불필요
- 간단한 사용법

**예제:**
```
https://random-word-api.herokuapp.com/word
```

**응답 형식:**
```json
["word"]
```

### 3. WordsAPI (유료, 무료 플랜 있음)

**URL**: `https://wordsapiv1.p.rapidapi.com/words/{word}`

**특징:**
- 더 많은 정보 제공
- API 키 필요 (RapidAPI에서 발급)
- 무료 플랜: 월 2,500 요청

---

## Java HttpClient 사용법

### 기본 구조

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WordAPIExample {
    // HttpClient 생성 (재사용 가능)
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    public static void main(String[] args) {
        try {
            // API URL
            String apiUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/hello";
            
            // HttpRequest 생성
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl))
                    .timeout(Duration.ofSeconds(10))
                    .build();
            
            // 요청 전송 및 응답 받기
            HttpResponse<String> response = httpClient.send(request, 
                    HttpResponse.BodyHandlers.ofString());
            
            // 응답 처리
            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                System.out.println(jsonResponse);
            } else {
                System.out.println("오류: HTTP " + response.statusCode());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### 비동기 요청 (선택사항)

```java
// 동기 요청 (기본)
HttpResponse<String> response = httpClient.send(request, 
        HttpResponse.BodyHandlers.ofString());

// 비동기 요청
CompletableFuture<HttpResponse<String>> future = httpClient.sendAsync(request, 
        HttpResponse.BodyHandlers.ofString());
future.thenAccept(response -> {
    System.out.println(response.body());
});
```

---

## 실전 예제

### 예제 1: 단어 정의 가져오기

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WordDefinitionExample {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    public static void main(String[] args) {
        getWordDefinition("hello");
        getWordDefinition("java");
        getWordDefinition("programming");
    }
    
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
                System.out.println("단어: " + word);
                System.out.println("응답: " + response.body());
                System.out.println();
            } else {
                System.out.println("단어를 찾을 수 없습니다: " + word);
            }
            
        } catch (Exception e) {
            System.out.println("오류: " + e.getMessage());
        }
    }
}
```

### 예제 2: 랜덤 단어 가져오기

```java
public static void getRandomWord() {
    try {
        String apiUrl = "https://random-word-api.herokuapp.com/word";
        
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, 
                HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            String jsonResponse = response.body();
            // JSON 형식: ["word"]
            String word = jsonResponse.replaceAll("[\\[\\]\"]", "");
            System.out.println("랜덤 단어: " + word);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

### 예제 3: 여러 단어 일괄 조회

```java
public static void getMultipleWords(String[] words) {
    for (String word : words) {
        getWordDefinition(word);
        try {
            Thread.sleep(1000); // API 호출 간격 (1초)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

---

## JSON 파싱

### 방법 1: 문자열 메서드 사용 (간단하지만 제한적)

```java
private static void parseSimpleJSON(String json) {
    // 단어 추출
    if (json.contains("\"word\"")) {
        int start = json.indexOf("\"word\"") + 8;
        int end = json.indexOf("\"", start);
        String word = json.substring(start, end);
        System.out.println("단어: " + word);
    }
}
```

### 방법 2: Jackson 라이브러리 사용 (권장)

**의존성 추가 (Maven):**
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>
```

**사용 예제:**
```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

ObjectMapper mapper = new ObjectMapper();
JsonNode rootNode = mapper.readTree(jsonResponse);

String word = rootNode.get(0).get("word").asText();
String phonetic = rootNode.get(0).get("phonetic").asText();
```

### 방법 3: Gson 라이브러리 사용

**의존성 추가 (Maven):**
```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

**사용 예제:**
```java
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

JsonParser parser = new JsonParser();
JsonArray jsonArray = parser.parse(jsonResponse).getAsJsonArray();
JsonObject wordObject = jsonArray.get(0).getAsJsonObject();

String word = wordObject.get("word").getAsString();
```

---

## 주의사항

### 1. API 호출 제한

- 무료 API는 호출 횟수 제한이 있을 수 있음
- 너무 빠른 연속 호출 시 차단될 수 있음
- 적절한 딜레이 추가 권장

```java
Thread.sleep(1000); // 1초 대기
```

### 2. 예외 처리

```java
try {
    // API 호출
} catch (java.net.http.HttpTimeoutException e) {
    System.out.println("요청 시간 초과");
} catch (java.io.IOException e) {
    System.out.println("네트워크 오류");
} catch (Exception e) {
    System.out.println("예상치 못한 오류: " + e.getMessage());
}
```

### 3. 네트워크 연결 확인

```java
// 인터넷 연결 확인 후 API 호출
if (!isInternetAvailable()) {
    System.out.println("인터넷 연결이 필요합니다.");
    return;
}
```

### 4. 응답 크기 제한

```java
HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create(apiUrl))
        .timeout(Duration.ofSeconds(10))
        .header("Accept", "application/json")
        .build();
```

### 5. API 키 사용 시

```java
HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create(apiUrl))
        .header("X-RapidAPI-Key", "your-api-key")
        .header("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
        .build();
```

---

## 완전한 예제 코드

### WordAPIExample.java

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class WordAPIExample {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) {
        // 단어 정의 가져오기
        getWordDefinition("hello");
        
        // 랜덤 단어 가져오기
        getRandomWord();
    }
    
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
                System.out.println("단어: " + word);
                System.out.println("응답: " + response.body());
            } else {
                System.out.println("단어를 찾을 수 없습니다.");
            }
            
        } catch (Exception e) {
            System.out.println("오류: " + e.getMessage());
        }
    }
    
    public static void getRandomWord() {
        try {
            String apiUrl = "https://random-word-api.herokuapp.com/word";
            
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(apiUrl))
                    .build();
            
            HttpResponse<String> response = httpClient.send(request, 
                    HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                String word = jsonResponse.replaceAll("[\\[\\]\"]", "");
                System.out.println("랜덤 단어: " + word);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 실행 방법

### 컴파일 및 실행

```bash
javac WordAPIExample.java
java WordAPIExample
```

### 필요 조건

- Java 11 이상
- 인터넷 연결
- 방화벽에서 HTTPS 허용

---

## 요약

### 핵심 포인트

1. **Java 11+ HttpClient 사용**: 외부 라이브러리 없이 API 호출 가능
2. **무료 API 활용**: Free Dictionary API 등 무료 API 사용 가능
3. **JSON 파싱**: Jackson 또는 Gson 라이브러리 사용 권장
4. **예외 처리**: 네트워크 오류, 타임아웃 등 처리 필수
5. **API 제한**: 호출 횟수 제한 고려

### 추천 API

- **Free Dictionary API**: 무료, API 키 불필요, 단어 정의 제공
- **Random Word API**: 무료, 랜덤 단어 생성
- **WordsAPI**: 유료(무료 플랜 있음), 더 많은 정보 제공

---

**작성일:** 2026-01-30  
**범위:** Java로 영어 단어 API 사용하기
