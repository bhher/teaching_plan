# QueryDSL 설정: 교재(최소 구성) vs 본 프로젝트 `pom.xml`

대상 프로젝트: Spring Boot **3.1.1**, Java **17**  
비교 기준: 제공해 주신 QueryDSL 의존성·컴파일 플러그인·설정 예시

---

## 1. 의존성(dependencies)

| 항목 | 교재 최소 구성 | 본 프로젝트 `pom.xml` | 비고 |
|------|----------------|----------------------|------|
| `querydsl-jpa` 5.0.0 + `classifier=jakarta` | ✅ | ✅ **동일** | Spring Boot 3 / `jakarta.persistence`와 맞춤 |
| `querydsl-apt` 5.0.0 + `jakarta` + `provided` | ✅ | ✅ **동일** | Q타입 생성용 |
| `querydsl-core` | 없음 | ✅ **추가됨** | JPA 모듈이 transitive로 끌어오는 경우가 많아 생략 가능하지만, 명시해 두면 버전 정렬에 유리할 수 있음 |

**정리:** QueryDSL JPA/APT + Jakarta 분기는 교재와 **일치**합니다. `querydsl-core`만 프로젝트에 **더** 있습니다.

---

## 2. `classifier=jakarta`가 필요한 이유(교재 요지와 동일)

- QueryDSL 5.x는 **javax 기반** 아티팩트와 **jakarta 기반** 아티팩트를 **같은 좌표**로 나누어 배포합니다.
- Spring Boot 3은 `javax.persistence.*`가 아니라 **`jakarta.persistence.*`** 를 사용합니다.
- 그래서 `querydsl-jpa`, `querydsl-apt`에는 **`jakarta` classifier**를 붙여, 엔티티·어노테이션 프로세서가 **Jakarta API**에 맞는 바이너리를 쓰게 합니다.  
  classifier를 빼면 javax 쪽과 섞이거나 Q클래스/컴파일 단계에서 맞지 않을 수 있습니다.

---

## 3. 빌드 플러그인: `maven-compiler-plugin`

| 항목 | 교재 최소 구성 | 본 프로젝트 | 비고 |
|------|----------------|------------|------|
| 플러그인 `<version>` (예: 3.11.0) | 명시 | **없음** | `spring-boot-starter-parent`가 관리하는 버전 사용 |
| `annotationProcessorPaths` → `querydsl-apt` (jakarta) | ✅ | ✅ **동일** | |
| `annotationProcessorPaths` → `jakarta.persistence-api` | ✅ (3.1.0) | **없음** | 명시하면 APT 클래스패스가 더 안정적일 수 있음 |
| `annotationProcessorPaths` → `jakarta.annotation-api` | ✅ (2.1.1) | **없음** | 위와 동일 |
| `build-helper-maven-plugin` (`add-source` → `generated-sources/annotations`) | 없음 | ✅ **있음** | Q소스 디렉터리를 컴파일 소스 루트에 **명시적으로 추가** |

**정리:**

- Q클래스 생성 경로는 교재와 같이 보통 **`target/generated-sources/annotations`** 입니다.
- 본 프로젝트는 **`build-helper-maven-plugin`** 으로 그 경로를 소스에 넣어 두어, IDE/빌드가 생성 소스를 더 잘 인식하도록 한 **추가 구성**입니다.
- 교재에 있는 `jakarta.persistence-api`, `jakarta.annotation-api`를 processor path에 넣는 것은 **권장 패턴**이고, 현재 pom은 **`querydsl-apt`만** processor path에 둔 **더 짧은 구성**입니다. 빌드가 잘 되면 유지해도 되고, Q생성/IDE 오류 시 교재처럼 두 path를 추가해 보는 것이 좋습니다.

---

## 4. Lombok과의 공존(교재 예시에는 없음)

본 프로젝트는 **`lombok`** 을 쓰고, `maven-compiler-plugin`의 `annotationProcessorPaths`에는 **QueryDSL만** 있습니다.

- Spring Boot 부모 POM이 Lombok을 컴파일 단계에서 처리하는 방식과 맞물려 **동작하는 경우가 많습니다.**
- 만약 “Lombok + QueryDSL” 같이 쓸 때 한쪽만 동작한다면, 공식 문서/팀 관례에 따라 **`lombok`을 `annotationProcessorPaths`에도 추가**하는 구성을 검토하면 됩니다.

---

## 5. `QueryDslConfig` vs 본 프로젝트 코드

| 항목 | 교재 예시 | 본 프로젝트 |
|------|-----------|------------|
| `JPAQueryFactory` Bean | `@Configuration` + `@Bean` | **별도 설정 클래스 없음** |
| 팩토리 생성 위치 | `EntityManager` 주입 후 Bean | `ItemRepositoryCustomImpl` 생성자에서 `new JPAQueryFactory(em)` |

```27:29:d:\sande\teaching_plan\shop-master\shop-master\src\main\java\com\shop\repository\ItemRepositoryCustomImpl.java
    public ItemRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
```

**정리:** 동작은 동일 계열입니다. Bean으로 빼면 다른 리포지토리/서비스에서 주입 재사용하기 쉽고, 지금처럼 구현체마다 생성자에서 만들면 **설정 클래스는 줄이고** 구현체에 국한됩니다.

---

## 6. IntelliJ / 생성 위치(교재와 동일 개념)

- 빌드 후 Q클래스: 보통 **`target/generated-sources/annotations`** (본 프로젝트도 `build-helper`로 이 경로를 소스에 추가).
- 생성이 안 보이면: **Settings → Build, Execution, Deployment → Compiler → Annotation Processors → Enable annotation processing** 확인.

---

## 7. 한눈에 요약

| 구분 | 교재 최소 예시 | 본 `shop` 프로젝트 |
|------|----------------|-------------------|
| QueryDSL Jakarta 의존성 | 기준 | **동일 + `querydsl-core` 명시** |
| Compiler APT | querydsl + jakarta API 2개 path | **querydsl-apt만** |
| 생성 소스 등록 | (교재 예시만으로는 생략 가능) | **`build-helper-maven-plugin`으로 add-source** |
| `JPAQueryFactory` | `QueryDslConfig` Bean | **`ItemRepositoryCustomImpl`에서 직접 생성** |

교재 예시를 그대로 반영하고 싶다면, **`annotationProcessorPaths`에 `jakarta.persistence-api`, `jakarta.annotation-api` 추가**와 **`maven-compiler-plugin` 버전 명시** 정도를 선택적으로 맞추면 됩니다. 반대로 본 프로젝트 스타일을 유지해도 Spring Boot 3 + QueryDSL 5 Jakarta 조합으로는 일반적으로 문제 없습니다.


| 현재 페이지 | start | end | 출력             |
| ------ | ----- | --- | -------------- |
| 1      | 1     | 5   | 1 2 3 4 5      |
| 3      | 1     | 5   | 1 2 3 4 5      |
| 6      | 6     | 10  | 6 7 8 9 10     |
| 11     | 11    | 15  | 11 12 13 14 15 |
