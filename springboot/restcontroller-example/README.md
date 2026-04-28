# RestController 예제 (교안용)

`@RestController` 로 **JSON API** 를 만드는 가장 단순한 Spring Boot 예제입니다.

## 실행 방법

1. JDK 17 이상 설치
2. 이 폴더(`restcontroller-example`)에서:

```bash
mvn spring-boot:run
```

3. 테스트

- `GET http://localhost:8080/api/health`
- `GET http://localhost:8080/api/hello?name=kim`
- `GET http://localhost:8080/api/users/1`
- `POST http://localhost:8080/api/echo`

예시 요청(JSON):

```json
{
  "message": "hi",
  "number": 123
}
```

예시 응답(JSON):

```json
{
  "message": "hi",
  "number": 123,
  "serverTime": "2026-04-27T06:00:00Z"
}
```

## 핵심 포인트(시험/면접용)

- **`@RestController`** = `@Controller` + `@ResponseBody` (리턴값을 뷰가 아니라 **응답 바디(JSON)** 로 보냄)
- **`@RequestMapping`** 으로 공통 URL prefix 설정 가능
- **`@GetMapping` / `@PostMapping`**
- **`@RequestParam`**: 쿼리스트링
- **`@PathVariable`**: URL 경로 변수
- **`@RequestBody`**: 요청 바디(JSON) → 자바 객체(record)로 매핑

