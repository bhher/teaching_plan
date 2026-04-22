# Thymeleaf 사용법 (Spring Boot 서버 사이드 렌더링)

**대상:** 챕터 3(`@RestController` + JSON)을 읽은 뒤, **브라우저에 HTML 페이지**를 서버에서 만들어 보내고 싶을 때  
**목표:** Thymeleaf 의존성 추가, 템플릿 위치, `@Controller`·`Model`·`th:*` 속성으로 **최소 화면**을 직접 만들 수 있게 하기

**선행:** [03-핵심-코드-컨트롤러와-REST.md](./03-핵심-코드-컨트롤러와-REST.md) — 여기서는 **JSON API** 를 다룹니다. Thymeleaf는 **HTML 응답** 이라는 점만 다릅니다.

**의존성 추가:** [Spring-Initializr-의존성-선택-가이드.md](./Spring-Initializr-의존성-선택-가이드.md) 의 **Thymeleaf** 절 참고 (`start.spring.io` 에서 **Thymeleaf** 검색 후 추가)

**실행 예제 (전체 프로젝트):** [thymeleaf-example/](./thymeleaf-example/README.md) — 홈·목록·상세 HTML, `/api/hello` JSON, `static/css` 연결까지 포함한 **Maven 예제**입니다. (`mvn spring-boot:run` → `http://localhost:8080/`)

---

## 1. Thymeleaf가 뭔가요?

**비유:** 레스토랑 **주방(서버)** 에서 **완성된 접시(HTML)** 를 만들어 **손님(브라우저)** 에게 내보내는 방식입니다.

| 구분 | `@RestController` + JSON (챕터 3) | Thymeleaf + `@Controller` |
|------|-----------------------------------|-----------------------------|
| 응답 형태 | 주로 **JSON** 문자열 | **HTML** 문서 |
| 누가 화면을 그리나 | **프론트(React 등)** 가 JSON 받아서 그림 | **서버**가 HTML 조각을 채워서 보냄 |
| 언제 쓰나 | 앱·SPA·다른 서버와 연동 | 관리자 페이지, 간단한 폼·게시판 **서버 렌더링** |

Thymeleaf는 **HTML 파일 안에** `th:text` 같은 속성을 넣어, 서버 데이터를 **안전하게** 끼워 넣는 **템플릿 엔진**입니다. (순수 HTML로 열어도 대략 읽히게 설계된 편이라, 교육용으로 다루기 좋습니다.)

---

## 2. 프로젝트에 넣는 위치

Spring Boot는 기본적으로 아래를 봅니다.

```
src/main/resources/
├── templates/          ← Thymeleaf HTML (.html)
└── static/             ← CSS, JS, 이미지 (URL로 직접 접근)
```

- **템플릿:** `templates/` 아래 `hello.html` 이면, 컨트롤러에서 반환 이름은 보통 **`hello`** (확장자 제외)  
- **정적 파일:** `static/style.css` → 브라우저에서는 `http://localhost:8080/style.css`

---

## 3. 컨트롤러: `@Controller` 와 뷰 이름

JSON이 아니라 **HTML 페이지**를 줄 때는 **`@RestController`가 아니라 `@Controller`** 를 씁니다.  
메서드가 **`String` 을 반환**하면 그 문자열이 **템플릿 파일 이름**으로 해석됩니다.

```java
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "홈");
        return "home";   // → src/main/resources/templates/home.html
    }
}
```

**정리**

- **`@Controller`** + `return "home"` → `templates/home.html` 렌더링  
- 화면에 넘길 값은 **`Model`** 에 `addAttribute("이름", 값)` 으로 넣습니다.

---

## 4. 템플릿 HTML 최소 예제

`src/main/resources/templates/home.html` 예시입니다.

```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title th:text="${title}">제목</title>
</head>
<body>
    <h1 th:text="'안녕하세요, ' + ${title}">표시 영역</h1>
</body>
</html>
```

- **`xmlns:th="http://www.thymeleaf.org"`**  
  → IDE가 `th:*` 속성을 인식하게 하는 선언(권장).  
- **`th:text="${title}"`**  
  → 컨트롤러에서 넣은 `model.addAttribute("title", ...)` 의 **`title`** 값으로 태그 **안쪽 텍스트**를 바꿉니다.  
- 태그 사이의 `제목`, `표시 영역` 은 **서버 없이 HTML만 열었을 때** 보이는 **플레이스홀더** 용도로 두는 경우가 많습니다.

---

## 5. 자주 쓰는 Thymeleaf 속성

| 속성 | 역할 | 예시 |
|------|------|------|
| `th:text` | 이스케이프된 텍스트 (HTML 태그를 문자로 표시) | `th:text="${msg}"` |
| `th:utext` | HTML 조각을 넣을 때 (신뢰할 수 있는 내용만 — XSS 주의) | `th:utext="${html}"` |
| `th:each` | 리스트/컬렉션 반복 | `th:each="item : ${items}"` |
| `th:if` / `th:unless` | 조건 표시 | `th:if="${user != null}"` |
| `th:href` | 링크 URL (`@{}` 로 컨텍스트 경로 처리에 유리) | `th:href="@{/posts/{id}(id=${p.id})}"` |
| `th:action` | 폼 전송 URL | `th:action="@{/login}"` |
| `th:object` + `th:field` | 폼과 객체 필드 바인딩 | 폼 처리 챕터에서 확장 |

### 5-1. 반복 (`th:each`)

```html
<ul>
    <li th:each="name : ${names}" th:text="${name}">이름</li>
</ul>
```

컨트롤러에서 `model.addAttribute("names", List.of("Kim", "Lee"));` 를 넘기면 두 줄이 렌더링됩니다.

### 5-2. 링크 (`th:href` 와 `@{}`)

```html
<a th:href="@{/posts}">글 목록</a>
<a th:href="@{/posts/{id}(id=${postId})}">상세</a>
```

`@{}` 는 애플리케이션 **컨텍스트 경로**가 붙는 배포 환경에서도 링크가 깨지지 않게 도와줍니다.

---

## 6. 레이아웃·조각 (입문만)

큰 페이지를 나눌 때 **`th:replace` / `th:insert`** 로 **다른 HTML 조각**을 끼워 넣을 수 있습니다.  
(팀·프로젝트에 따라 **layout Dialect** 플러그인을 추가해 쓰기도 합니다. 처음에는 한 파일에 다 넣고, 익숙해지면 분리하면 됩니다.)

---

## 7. REST와 같이 쓰는 패턴

한 프로젝트 안에 다음이 **동시에** 있을 수 있습니다.

- **`@Controller`** — `/`, `/posts` 같은 **화면**  
- **`@RestController`** — `/api/...` **JSON API**

URL 규칙만 팀에서 정해 두면 됩니다. (예: 화면은 `/posts`, API는 `/api/posts`)

---

## 8. 설정 (선택)

`src/main/resources/application.properties` 예시입니다.

```properties
# 템플릿 캐시 — 개발 중에는 false 로 두면 HTML 수정이 바로 반영되는 경우가 많음
spring.thymeleaf.cache=false
```

운영 배포 시에는 보통 **`cache=true`** 로 두고 성능을 맞춥니다.

---

## 9. 정리 체크리스트

1. **Initializr** 또는 `pom.xml` / `build.gradle` 에 **Thymeleaf** 의존성이 있다.  
2. HTML은 **`src/main/resources/templates/`** 에 둔다.  
3. 화면용 컨트롤러는 **`@Controller`**, 데이터는 **`Model`** 로 넘긴다.  
4. 템플릿에서는 **`th:text`**, **`th:each`**, **`th:if`**, **`th:href="@{...}"`** 부터 익힌다.

다음 단계로는 **폼 전송(POST)**, **Spring Data JPA와 목록/상세 페이지**, 필요 시 **Spring Security** 와 함께 배우면 **게시판 SSR** 흐름이 이어집니다. ([주제-템플릿.md](./주제-템플릿.md) 의 게시판 CRUD 한 줄 예시 참고)
