# start.spring.io — 의존성 선택·다운로드·설치 가이드 (Maven)

**대상:** 비전공 초보, IntelliJ 사용  
**목표:** 브라우저에서 **Spring Initializr**로 프로젝트를 만들고, **의존성(Dependency)** 을 고른 뒤 압축을 받아 **IntelliJ에서 열기**까지 할 수 있게 하기

**Gradle 버전:** [Spring-Initializr-의존성-선택-가이드-Gradle.md](./Spring-Initializr-의존성-선택-가이드-Gradle.md)

---

## 1. start.spring.io 가 뭔가요?

**비유:** 식당에서 **세트 메뉴**를 고르는 사이트입니다.  
**Project**, **Language**, **Spring Boot 버전**, 그리고 **필요한 기능(의존성)** 을 체크하면, **Maven/Gradle 설정이 이미 들어 있는** 프로젝트 ZIP을 받을 수 있습니다.

**왜 쓰나요?**  
→ `pom.xml` / `build.gradle` 에 라이브러리 이름·버전을 **직접 타이핑**하지 않아도 되고, **Spring Boot와 맞는 조합**이 자동으로 잡힙니다.

---

## 2. 접속과 기본 선택

1. 브라우저에서 **https://start.spring.io** 접속  
2. 보통 아래처럼 맞춥니다 (교육·실무 입문 기준).

| 항목 | 추천 (예시) | 설명 |
|------|-------------|------|
| **Project** | **Maven** | 국내 교육·문서에서 많이 씀 (Gradle도 가능) |
| **Language** | **Java** | |
| **Spring Boot** | **3.x (Stable)** | LTS에 가까운 안정 버전 선택 |
| **Project Metadata** | Group `com.example`, Artifact `demo` | 패키지 경로에 쓰임. 팀 규칙에 맞게 변경 |
| **Packaging** | **Jar** | 실행 파일이 JAR 하나로 나옴 (일반적) |
| **Java** | **17** 또는 **21** | Spring Boot 3는 **최소 Java 17** |

3. 오른쪽 **ADD DEPENDENCIES** (또는 **Dependencies** 섹션)를 눌러 **아래 3절**에서 필요한 것을 고릅니다.

4. 맨 아래 **GENERATE** 를 누르면 **`demo.zip`** 같은 파일이 **다운로드**됩니다.

---

## 3. 자주 고르는 의존성 (Dependency) 설명

검색창에 **영문 키워드**를 넣으면 항목이 나옵니다. 이름이 조금 다를 수 있으니 **공식 스타터 이름** 기준으로 정리했습니다.

### 3-1. Spring Web

- **검색:** `web` → **Spring Web**
- **역할:** REST API, **내장 톰캣**, JSON 입출력  
- **언제:** “URL로 요청 받고 응답하는 서버”를 만들 때 **거의 필수**

### 3-2. Spring Data JPA

- **검색:** `jpa` → **Spring Data JPA**
- **역할:** DB 테이블을 **자바 엔티티**로 매핑, **Repository** 로 CRUD  
- **언제:** **MySQL, PostgreSQL, H2** 등 RDB에 저장할 때  
- **참고:** DB 드라이버는 **별도**로 추가하는 경우가 많음 (아래 3-3)

### 3-3. MySQL Driver / H2 Database

- **MySQL Driver:** MySQL 서버에 연결할 때  
- **H2 Database:** **파일/메모리 DB** — 설치 없이 **로컬 실습**에 편함  

→ JPA와 같이 고르는 경우가 많습니다.

### 3-4. Thymeleaf

- **검색:** `thymeleaf`  
- **역할:** 서버에서 **HTML** 을 만들어 브라우저에 보낼 때 (SSR)  
- **언제:** **게시판 화면을 JSP처럼** 서버에서 렌더링할 때  

### 3-5. Spring Security

- **검색:** `security` → **Spring Security**  
- **역할:** 로그인·권한·CSRF 등 **보안**  
- **언제:** **로그인/로그아웃**을 스프링 방식으로 할 때 (설정 학습량이 있음)

### 3-6. Validation

- **검색:** `validation`  
- **역할:** `@NotBlank`, `@Email` 등 **입력 검증**  
- **언제:** API 요청 **DTO** 검사할 때  

### 3-7. Lombok (선택)

- **검색:** `lombok`  
- **역할:** `@Getter`, `@Builder` 등으로 **보일러플레이트** 감소  
- **주의:** IntelliJ에 **Lombok 플러그인** 활성화 필요한 경우가 있음  

### 3-8. Spring Boot DevTools (선택)

- **검색:** `devtools`  
- **역할:** 코드 수정 시 **빠른 재시작** 등 개발 편의  
- **언제:** 로컬 개발할 때만 (운영 배포에는 보통 제외)

---

## 4. 조합 예시 (주제별로 이렇게 고르면 됨)

| 하고 싶은 것 | 최소로 자주 고르는 것 |
|--------------|------------------------|
| REST API만 (챕터 1~3 실습) | **Spring Web** |
| DB 저장 + REST | **Spring Web**, **Spring Data JPA**, **H2** 또는 **MySQL Driver** |
| 서버 렌더링 게시판 | **Spring Web**, **Thymeleaf**, **Spring Data JPA**, **H2** |
| 로그인까지 | 위 + **Spring Security**, **Validation** (순서는 강의 계획에 맞게) |

---

## 5. 다운로드 후 “설치” (= IntelliJ에서 열기)

**의존성은 ZIP 안의 `pom.xml`(Maven) 또는 `build.gradle`(Gradle)에 이미 적혀 있습니다.**  
IntelliJ가 그 파일을 읽고 **Maven/Gradle이 라이브러리를 인터넷에서 받아옵니다** — 이게 곧 **의존성 설치**입니다.

### 5-1. 압축 풀기

1. 다운로드한 **`demo.zip`** 을 원하는 폴더에 **압축 해제**  
2. **`pom.xml` 이 있는 폴더**가 프로젝트 루트입니다.  
   (예: `C:\work\demo\pom.xml`)

### 5-2. IntelliJ에서 열기

1. IntelliJ 실행 → **Open** (또는 File → Open)  
2. **`pom.xml` 이 있는 폴더**를 선택 → **OK**  
3. **“Trust Project”** 같은 메시지가 나오면 신뢰할 프로젝트면 **Trust**  
4. **Maven 프로젝트로 열겠냐**는 뜻이 나오면 **Open as Project** / **Import Maven Project** 선택  
5. 오른쪽 아래(또는 Maven 탭)에서 **의존성 다운로드**가 자동으로 진행됩니다.  
   - 처음에는 **인덱싱·다운로드**에 시간이 걸릴 수 있음  

### 5-3. 잘 열렸는지 확인

- 왼쪽 **Project** 트리에 `src/main/java`, `pom.xml` 보임  
- 하단 **Build** 탭에 치명적 에러 없음  
- `DemoApplication.java` 에서 **Run** → 콘솔에 `Started ...` 로그  

---

## 6. 의존성을 나중에 더 넣고 싶을 때

Initializr에서 빼먹었어도 됩니다.

### Maven (`pom.xml`)

`<dependencies>` 안에 `<dependency>` 블록을 **추가**하고, IntelliJ에서 **Maven 새로고침**(Reload) 하면 됩니다.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**Spring Boot Parent** 를 쓰는 경우 **버전 번호를 생략**해도 맞는 버전이 잡힙니다.

### IntelliJ 안에서 (선택)

- **pom.xml** 열기 → `dependency` 입력 시 **자동완성**  
- 또는 **File → Project Structure → Libraries** (고급, 입문은 `pom.xml` 편집이 일반적)

---

## 7. 자주 나는 상황

| 상황 | 할 일 |
|------|--------|
| **빨간 줄**이 `pom.xml`에만 있음 | Maven **Reload Project**, 인터넷 연결 확인 |
| **JDK 버전** 에러 | Project Structure에서 **JDK 17+** 로 맞추기 |
| **포트 8080 사용 중** | `application.properties`에 `server.port=8081` |

---

## 8. 한 줄 정리

1. **https://start.spring.io** 에서 **Project / Java / Boot 버전** 고르고  
2. **ADD DEPENDENCIES** 로 **Spring Web** 등 필요한 것 선택  
3. **GENERATE** 로 ZIP 받아 압축 풀고  
4. IntelliJ **Open** → **Maven이 의존성 자동 설치**  
5. 더 필요하면 **`pom.xml`에 dependency 추가** 후 Reload  

---

*교안 폴더: `02-프로젝트-구조와-IntelliJ.md` 와 함께 보세요. **Gradle**은 [Spring-Initializr-의존성-선택-가이드-Gradle.md](./Spring-Initializr-의존성-선택-가이드-Gradle.md).*
