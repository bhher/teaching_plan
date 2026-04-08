# 챕터 3. 핵심 코드 — 컨트롤러와 REST API

**대상:** 비전공 초보  
**이 챕터 목표:** GET/POST로 데이터를 주고받는 **최소 REST API** 를 직접 작성할 수 있게 하기

---

## 1. 개념 설명

### 1-1. REST API란?

**비유:** 식당 **메뉴판(URL)** 과 **주문 방식(GET/POST)** 이 정해져 있고, **접시(JSON)** 로 응답이 나온다.

- **GET** → **가져와 줘** (조회). 서버 데이터를 **바꾸지 않는** 경우가 많음  
- **POST** → **새로 등록해 줘** (생성). **본문(body)** 에 내용을 넣어 보냄  

**왜 JSON?**  
→ 브라우저·앱·다른 서버가 **같은 형식**으로 읽기 쉽기 때문입니다. (키-값 구조)

### 1-2. 컨트롤러의 역할

**컨트롤러** = “**URL + HTTP 메서드**가 들어오면, **그에 맞는 자바 메서드**를 실행한다”고 **스프링에 등록**하는 클래스.

**왜 컨트롤러에만 두지 않고 나중에 Service로 나누나?**  
→ **화면 요청 처리**와 **업무 규칙**을 나누면, 테스트·수정이 쉬워집니다. (챕터 3에서는 **컨트롤러만** 먼저 익힙니다.)

---

## 2. 프로젝트 구조 (이 챕터에서 추가할 것)

```
com.example.demo
├── DemoApplication.java
└── controller
    ├── HelloController.java
    └── UserController.java      ← 예제
```

---

## 3. 핵심 코드 작성

### 3-1. GET — 문자열 반환 (복습)

```java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello";
    }
}
```

**왜 `/api/hello` 를 쓰나?**  
→ 나중에 **프론트(React 등)** 와 구분하기 쉽게 `/api` 로 묶는 팀이 많습니다. (규칙일 뿐 필수는 아님)

---

### 3-2. GET — 경로에 숫자 받기 (Path Variable)

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/users/{id}")
    public String getUser(@PathVariable Long id) {
        return "요청한 사용자 id = " + id;
    }
}
```

**실행:** `GET http://localhost:8080/api/users/5` → 응답 `"요청한 사용자 id = 5"`

**왜 `@PathVariable`?**  
→ URL **경로 안에 있는 값**을 메서드 **파라미터**로 바꿔줍니다.

---

### 3-3. GET — 쿼리 문자열 (?name=kim)

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/greet")
    public String greet(@RequestParam(defaultValue = "guest") String name) {
        return "안녕, " + name;
    }
}
```

**실행:** `GET http://localhost:8080/api/greet?name=Kim` → `안녕, Kim`  
`name` 없으면 → `안녕, guest`

**왜 `@RequestParam`?**  
→ `?key=value` 형태를 **파라미터**로 받습니다.

---

### 3-4. JSON 객체로 응답 (실무에서 가장 많음)

**DTO 클래스** (데이터만 담는 상자):

```java
package com.example.demo.dto;

public class UserResponse {
    private Long id;
    private String name;

    public UserResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}
```

**컨트롤러:**

```java
import com.example.demo.dto.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/users/me")
    public UserResponse me() {
        return new UserResponse(1L, "홍길동");
    }
}
```

**응답 예시 (브라우저/Postman):**

```json
{"id":1,"name":"홍길동"}
```

**왜 getter가 있으면 JSON이 되나?**  
→ Spring Boot 기본 Jackson이 **getter** 를 보고 JSON 필드를 만듭니다. (필드가 직접 `public`이 아니어도 됨)

---

### 3-5. POST — JSON 받기 (Request Body)

```java
package com.example.demo.dto;

public class UserCreateRequest {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
```

```java
import com.example.demo.dto.UserCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/api/users")
    public String create(@RequestBody UserCreateRequest body) {
        return "등록: " + body.getName() + ", 나이 " + body.getAge();
    }
}
```

**Postman 설정:** Method **POST**, URL `http://localhost:8080/api/users`, Body **raw JSON**:

```json
{ "name": "Lee", "age": 20 }
```

**왜 `@RequestBody`?**  
→ HTTP **본문**에 들어온 JSON을 **자바 객체**로 바꿔줍니다.

---

## 4. 실행 흐름 (요약)

1. 클라이언트가 `GET /api/users/me` 또는 `POST /api/users` 로 요청  
2. 내장 톰캣이 스프링에 전달  
3. **매핑 테이블**에서 `@GetMapping` / `@PostMapping` 일치하는 메서드 검색  
4. 파라미터(`@PathVariable`, `@RequestBody` 등) **바인딩**  
5. 반환값이 **문자열**이면 텍스트, **객체**면 **JSON**으로 직렬화  

---

## 5. 실무 팁

- **URL 설계:** 복수형 `/api/users` , 리소스 중심으로 맞추기  
- **상태 코드:** 나중에 `@ResponseStatus` 또는 `ResponseEntity`로 **201 Created** 등 세밀하게 조정  
- **검증:** `@Valid` + `@NotNull` 등으로 **입력 검사** (별도 학습)

---

## 6. 이 챕터 정리

| 애너테이션 | 역할 |
|------------|------|
| `@RestController` | REST용 컨트롤러 |
| `@GetMapping` / `@PostMapping` | URL·메서드 매핑 |
| `@PathVariable` | URL 경로 변수 |
| `@RequestParam` | `?key=value` |
| `@RequestBody` | JSON → 객체 |

다음 챕터: 위 과정이 **스프링 내부에서 어떤 순서로** 도는지 그림으로 정리합니다.
