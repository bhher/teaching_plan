# Thymeleaf 예제 (교안용)

**Spring Web + Thymeleaf** 만으로 동작하는 최소 프로젝트입니다. DB 없이 메모리에 샘플 글 2개를 둡니다.

## 포함된 것

| URL | 설명 |
|-----|------|
| `http://localhost:8080/` | 홈 — `th:text`, `th:href` |
| `http://localhost:8080/posts` | 목록 — `th:each`, 상세 링크 |
| `http://localhost:8080/posts/1` | 상세 — PathVariable + `Model` |
| `http://localhost:8080/api/hello` | JSON — `@RestController` 공존 예시 |

정적 파일: `src/main/resources/static/css/style.css` → `th:href="@{/css/style.css}"`

## 실행 방법

1. JDK 17 이상 설치  
2. 이 폴더(`thymeleaf-example`)에서:

```bash
mvn spring-boot:run
```

3. 브라우저에서 `http://localhost:8080/` 접속  

IntelliJ에서는 **pom.xml** 을 열고 Maven으로 임포트한 뒤, `ThymeleafDemoApplication` 을 실행해도 됩니다.

## 문서

- [../Thymeleaf-사용법.md](../Thymeleaf-사용법.md)
