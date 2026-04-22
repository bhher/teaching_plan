# start.spring.io — 의존성 선택·다운로드·설치 가이드 (Gradle)

**대상:** 비전공 초보, IntelliJ 사용  
**목표:** **Gradle** 프로젝트로 Spring Initializr에서 받아 **IntelliJ에서 열고 의존성을 설치**하기

**Maven 버전:** [Spring-Initializr-의존성-선택-가이드.md](./Spring-Initializr-의존성-선택-가이드.md)

---

## 1. Gradle이 뭔가요?

**비유:** Maven이 **XML 설계도(`pom.xml`)** 라면, Gradle은 **스크립트(`build.gradle`)** 로 같은 일을 하는 **빌드 도구**입니다.

**왜 Maven 말고 Gradle?**  
→ 회사·오픈소스에서 **Gradle**을 쓰는 팀이 많고, **빌드 속도·유연성**에서 선호하는 경우가 있습니다. **의존성 목록(스타터)** 은 Initializr에서 **Maven과 거의 동일**하게 고릅니다.

---

## 2. start.spring.io 에서 Gradle 고르기

1. **https://start.spring.io** 접속  
2. **Project** 에서 **Gradle** 선택  

| 하위 옵션 | 설명 |
|-----------|------|
| **Gradle - Groovy** | `build.gradle` — 전통적, 예제 많음 |
| **Gradle - Kotlin** | `build.gradle.kts` — Kotlin 문법, 최근 프로젝트에서 자주 봄 |

입문이면 **Gradle - Groovy** 로 통일해도 되고, 팀이 **Kotlin DSL** 이면 그쪽을 고릅니다.

3. 나머지는 Maven 가이드와 동일합니다.

| 항목 | 추천 |
|------|------|
| **Language** | Java |
| **Spring Boot** | 3.x (Stable) |
| **Packaging** | Jar |
| **Java** | 17 또는 21 |

4. **ADD DEPENDENCIES** 로 Spring Web 등 필요한 것 선택 (의존성 **이름·역할**은 Maven 가이드 **3절·4절**과 같음)

5. **GENERATE** → ZIP 다운로드

---

## 3. 받은 ZIP 안에 뭐가 있나 (Maven과 다른 점)

```
demo/
├── build.gradle          ← 또는 build.gradle.kts (의존성·빌드 스크립트)
├── settings.gradle       ← 프로젝트 이름·포함 모듈
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
├── gradlew               ← Linux/Mac 실행 스크립트
├── gradlew.bat           ← Windows 실행 스크립트
└── src/main/java/...
```

- **`gradlew` / `gradlew.bat`:** **Gradle Wrapper** — PC에 Gradle을 따로 설치하지 않아도 **프로젝트가 정한 버전**으로 빌드합니다.  
- **`pom.xml` 은 없음** — 대신 **`build.gradle`** 을 봅니다.

---

## 4. 다운로드 후 “설치” (= IntelliJ에서 열기)

**의존성은 `build.gradle`(또는 `.kts`)의 `dependencies { }` 에 적혀 있고, Gradle이 저장소에서 받아옵니다.**

### 4-1. 압축 풀기

1. ZIP을 원하는 폴더에 압축 해제  
2. **`build.gradle` 또는 `build.gradle.kts` 가 있는 폴더**가 프로젝트 루트입니다.

### 4-2. IntelliJ에서 열기

1. **File → Open**  
2. 위 **루트 폴더** 선택 → **OK**  
3. **“Open as Gradle Project”** / **Trust Project** 등이 나오면 진행  
4. 오른쪽 **Gradle** 탭이 보이거나, 상단에 **코끼리(Gradle) 아이콘** 이 보이면 **Sync** 가 돌아가며 의존성을 내려받습니다.  
5. 안 돌아가면: **Gradle 탭 → 새로고침(Reload)** 또는 **File → Reload Gradle Project**

### 4-3. 잘 열렸는지 확인

- `src/main/java/.../DemoApplication.java` 존재  
- **Run** 실행 시 `Started ...` 로그  
- **External Libraries** 에 `spring-boot-starter-*` 가 잡혀 있음  

---

## 5. 의존성을 나중에 더 넣기

### 5-1. Groovy DSL (`build.gradle`)

`dependencies { }` 블록 안에 한 줄 추가합니다.

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'
}
```

- **`implementation`:** 컴파일·실행에 필요한 라이브러리 (가장 많이 씀)  
- **`runtimeOnly`:** 실행할 때만 필요 (DB 드라이버 등)  
- Spring Boot 플러그인을 쓰면 **버전 생략**(BOM으로 맞춤)이 가능한 경우가 많음  

추가 후 IntelliJ에서 **Gradle Reload**.

### 5-2. Kotlin DSL (`build.gradle.kts`)

```kotlin
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
}
```

---

## 6. 터미널에서만 확인하고 싶을 때 (선택)

프로젝트 루트에서:

**Windows**

```text
gradlew.bat build
```

**Mac / Linux**

```text
./gradlew build
```

→ 의존성 다운로드 + 컴파일까지 한 번에 검증할 수 있습니다.

---

## 7. 자주 나는 상황 (Gradle)

| 상황 | 할 일 |
|------|--------|
| **Gradle JVM** 에러 | File → Settings → Build → Gradle → **Gradle JVM** 을 JDK 17+ 로 |
| **Sync 실패** | 인터넷·방화벽, 회사 **프록시** 설정 확인 |
| **의존성 안 받아짐** | Gradle 탭 **Reload**, 또는 **Invalidate Caches / Restart** (최후) |
| **포트 8080 사용 중** | `application.properties`에 `server.port=8081` |

---

## 8. Maven vs Gradle 한눈에

| | Maven | Gradle |
|---|--------|--------|
| 설정 파일 | `pom.xml` | `build.gradle` / `build.gradle.kts` |
| 의존성 예 | `<dependency>...</dependency>` | `implementation '...'` |
| 실행/빌드 | `mvnw.cmd package` | `gradlew.bat build` |
| IntelliJ | Maven Reload | Gradle Sync |

**Initializr에서 고르는 스타터(Spring Web, JPA 등)는 동일**하고, **프로젝트 종류만 Maven / Gradle** 으로 나뉩니다.

---

## 9. 한 줄 정리

1. start.spring.io 에서 **Project: Gradle** (Groovy 또는 Kotlin DSL)  
2. **ADD DEPENDENCIES** 로 스타터 선택 → **GENERATE**  
3. ZIP 풀고 **`build.gradle` 있는 폴더**를 IntelliJ **Open**  
4. **Gradle Sync** 로 의존성 설치  
5. 더 필요하면 **`dependencies { }`에 `implementation` 추가** 후 Reload  

---

*교안: `02-프로젝트-구조와-IntelliJ.md`, Maven 가이드와 짝으로 사용하세요.*
