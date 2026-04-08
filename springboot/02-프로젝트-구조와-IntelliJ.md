# 챕터 2. 프로젝트 구조와 IntelliJ

**대상:** 비전공 초보  
**이 챕터 목표:** 폴더 이름을 보면 “여기에 뭐 넣는지” 말할 수 있게 하기

---

## 1. 개념 설명

### 1-1. 비유: 아파트 단지

- **`src/main/java`** = **사람이 사는 동(소스 코드)**  
- **`src/main/resources`** = **공용 시설(설정 파일, HTML, 이미지)**  
- **`src/test/java`** = **검사·시험용(단위 테스트)**  
- **`pom.xml` (Maven)** 또는 **`build.gradle` (Gradle)** = **건물 설계도(의존성·빌드 방법)**

**왜 이렇게 나누나?**  
→ 컴파일되는 **자바 코드**와, 빌드만 되고 **클래스가 아닌 자원**을 구분하기 위해서입니다. (표준 관례)

---

## 2. 프로젝트 구조 설명 (Maven 기준)

```
demo/
├── pom.xml                          ← 라이브러리 목록·빌드 플러그인
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       ├── DemoApplication.java
│   │   │       └── controller/
│   │   │           └── HelloController.java
│   │   └── resources/
│   │       ├── application.properties   ← 포트, 서버 설정
│   │       └── static/                  ← 정적 파일 (CSS, JS) — 선택
│   │       └── templates/               ← Thymeleaf HTML — 선택
│   └── test/
│       └── java/
│           └── .../DemoApplicationTests.java
└── target/                          ← 빌드 결과 (컴파일 산출물) — 자동 생성
```

### 2-1. 패키지 `com.example.demo` 는 왜 길어?

**도메인을 거꾸로** 쓴 것입니다. (`com.example` 회사의 `demo` 프로젝트)  
→ 전 세계에서 **클래스 이름이 겹치지 않게** 하기 위한 관례입니다.

**왜 중요한가?**  
나중에 라이브러리를 합칠 때 **패키지가 겹치면** 컴파일 에러가 납니다.

### 2-2. `pom.xml` 에서 자주 보는 것

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <!-- 버전: Spring Boot가 여러 라이브러리 버전을 맞춰 줌 -->
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

**왜 `spring-boot-starter-web`?**  
→ 웹 요청(REST), JSON, **내장 톰캣**까지 한 번에 쓸 수 있게 묶어 둔 **스타터 패키지**입니다.

---

## 3. IntelliJ에서 프로젝트 만들기 (단계)

### 3-1. IntelliJ에서 새 프로젝트

1. **File → New → Project**  
2. 왼쪽에서 **Spring Initializr** 선택  
3. **Language:** Java, **Type:** Maven, **JDK:** 17+  
4. **Next → Dependencies:** `Spring Web` 체크  
5. **Finish** → Gradle을 쓰면 **Gradle** 선택 가능 (회사마다 다름)

### 3-2. 화면에서 쓰는 곳

| 메뉴/창 | 용도 |
|---------|------|
| **Project** (왼쪽 트리) | 파일 탐색 |
| **Run** (초록 버튼) | `DemoApplication` 실행 |
| **Run** 탭 (하단) | 로그, 에러 메시지 |
| **Build → Build Project** | 컴파일 확인 |

**왜 IntelliJ?**  
→ 자동완성, **의존성 다운로드**, 디버깅, Spring 연동이 **교육·실무 모두** 편합니다.

---

## 4. 실행 흐름 (이 챕터 관점)

1. IntelliJ가 **Maven**으로 `pom.xml` 을 읽음  
2. **의존성**(`spring-boot-starter-web` 등)이 로컬 저장소에 내려받아짐  
3. **Run** 실행 시 `java` 컴파일 → `target/classes` 에 클래스 파일 생성  
4. `SpringApplication.run` 이 **내장 톰캣** 기동  

---

## 5. 실무 팁 (구조)

- **Controller** 는 `controller` 패키지에 모으기 (팀 규칙과 통일)  
- **비밀번호·API 키** 는 `application.properties` 에 **직접 커밋하지 말고**, 환경 변수나 `.env` (도구 사용 시) 등으로 분리  
- **`target/`** 은 Git에 **올리지 않음** (`.gitignore`에 포함)

---

## 6. 이 챕터 정리

| 경로 | 역할 |
|------|------|
| `src/main/java` | 자바 소스 |
| `src/main/resources` | 설정·정적 리소스 |
| `pom.xml` / `build.gradle` | 의존성·빌드 |
| `DemoApplication.java` | 앱 시작 |

다음 챕터: **URL 한 줄로 요청을 받고 JSON을 돌려주는 코드**를 작성합니다.
