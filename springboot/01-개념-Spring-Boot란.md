# 챕터 1. Spring Boot가 뭔가요? (개념)

**대상:** 비전공 초보  
**이 챕터 목표:** “왜 Spring Boot를 배우는지” 말할 수 있게 하기

---

## 1. 개념 설명

### 1-1. 비유: 식당과 주방

- **손님(클라이언트)** 이 “메뉴 주세요”라고 **주문(HTTP 요청)** 을 합니다.
- **웹 서버 + 애플리케이션** 은 **주방**입니다. 주문을 받고, 요리하고, 접시에 담아 **응답**합니다.
- **Spring** 은 “주방 운영 규칙과 도구를 한곳에 모아 둔 **프레임워크**”입니다. (반복되는 일을 정해진 방식으로 처리)

**Spring Boot** 는 그 Spring 위에 **“자주 쓰는 설정을 대부분 자동으로 해 주는 키트”** 입니다.  
→ 예전에는 `web.xml` 같은 설정 파일을 길게 썼는데, Boot는 **최소 설정으로 서버를 띄우게** 해 줍니다.

### 1-2. 용어 한 줄

| 용어 | 한 줄 |
|------|--------|
| **Spring** | 자바로 엔터프라이즈(회사 업무) 앱을 만들기 위한 **도구 모음** |
| **Spring Boot** | Spring을 **빠르게 시작**하고 **실행 가능한 JAR**로 묶기 쉽게 한 것 |
| **내장 톰캣(Embedded Tomcat)** | 별도 톰캣 설치 없이, 프로그램 안에 **작은 웹 서버**가 같이 들어 있음 |

### 1-3. 왜 사용하는가?

1. **취업/실무:** 국내 자바 백엔드 채용에서 Spring 사용 비중이 매우 큽니다.
2. **생산성:** 설정보다 **비즈니스 로직(기능)** 에 시간을 쓰게 해 줍니다.
3. **표준에 가까운 구조:** Controller / Service / Repository 같은 **역할 분리** 패턴이 잘 정착되어 있습니다.

---

## 2. 프로젝트 구조 (미리보기)

이 챕터에서는 **전체 구조를 외울 필요 없습니다.**  
다만 “이런 레이어가 있다” 정도만 보면 됩니다.

```
src/main/java/.../DemoApplication.java   ← 프로그램 시작점 (@SpringBootApplication)
src/main/java/.../HelloController.java     ← URL 들어오면 응답하는 곳 (나중에 상세)
src/main/resources/application.properties    ← 포트, DB 주소 같은 설정
```

---

## 3. 핵심 코드 (최소 예제)

Spring Initializr로 **Spring Web** 만 넣고 만든 프로젝트라고 가정합니다.

### 3-1. 시작 클래스

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

**왜 `@SpringBootApplication`?**  
→ “이 클래스가 **스프링 부트 앱의 진입점**이고, 자동 설정을 켠다”는 표시입니다.

### 3-2. “Hello”를 돌려주는 컨트롤러 (맛보기)

```java
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
```

**왜 `@RestController`?**  
→ 이 클래스의 메서드 반환값을 **HTTP 응답 본문**으로 보낸다는 뜻입니다. (HTML 페이지가 아니라 **문자열/JSON** 등)

**왜 `@GetMapping("/hello")`?**  
→ 브라우저로 `http://localhost:8080/hello` 에 **GET 요청**이 오면 `hello()` 메서드가 실행됩니다.

---

## 4. 실행 흐름 (개념 단계)

1. `main` → `SpringApplication.run` 이 **스프링 컨테이너**를 띄움  
2. **내장 톰캣**이 **8080 포트**(기본)에서 요청을 기다림  
3. 사용자가 `/hello` 로 GET 요청  
4. 스프링이 **매핑**을 찾아 `HelloController.hello()` 실행  
5. 반환 문자열이 **응답 본문**으로 브라우저에 표시  

(자세한 내부는 챕터 4에서 다시 정리합니다.)

---

## 5. IntelliJ에서 실행 (실습 순서)

1. IntelliJ에서 프로젝트 **Open** (Maven/Gradle 루트 폴더)
2. `DemoApplication.java` 열기
3. `main` 옆 **초록 삼각형(Run)** 클릭  
4. 콘솔에 `Started DemoApplication` 비슷한 로그가 나오면 성공  
5. 브라우저에서 `http://localhost:8080/hello` 접속  

**포트가 안 열리면:** 다른 프로그램이 8080을 쓰는지 확인하거나, `application.properties`에 `server.port=8081` 등으로 변경합니다.

---

## 6. 이 챕터 정리

| 질문 | 답 |
|------|----|
| Spring Boot는 뭐야? | 자바로 **웹 API·서버**를 쉽게 만드는 **프레임워크 + 자동 설정** |
| 왜 쓰? | 실무 표준에 가깝고, **설정 시간을 줄여** 기능 개발에 집중 |
| 처음 본 코드는? | `@SpringBootApplication` + `@RestController` + `@GetMapping` |

다음 챕터: **프로젝트 폴더가 각각 무슨 일을 하는지**, IntelliJ 화면 기준으로 설명합니다.
