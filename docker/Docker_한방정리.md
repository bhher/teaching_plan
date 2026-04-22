# 도커 한방 정리

## 1️⃣ 도커란?

👉 “내 컴퓨터에서 되는데요?” 문제를 해결하는 기술  
**앱 + 실행환경(JDK, Node, 라이브러리 등)** 을 한 번에 묶어서 배포해, **어디서 실행하든 같은 환경**을 보장합니다.

**예시**
- 로컬: 잘 됨 ✅
- AWS: 안 됨 ❌  
→ 도커 사용: 로컬/서버 모두 동일하게 실행 ✅

---

## 2️⃣ 핵심 개념 4가지 (이것만 알면 70% 끝)

### 📦 1. 이미지 (Image)

👉 실행 파일 + 환경을 묶어놓은 **“설계도”**
- 예: `Java + Spring Boot + jar` 포함
- 한 번 만들어두면 계속 재사용 가능

### 📦 2. 컨테이너 (Container)

👉 이미지를 실행한 **실제 프로그램(실행 단위)**

비유
- 이미지 = 클래스
- 컨테이너 = 객체

특징
- VM보다 훨씬 가볍고 빠름
- 생성/삭제가 매우 빠름

### 📦 3. Dockerfile

👉 이미지를 만드는 **레시피(설계서)**

예시 (Spring Boot)

```dockerfile
FROM openjdk:17
COPY build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

의미
- `openjdk:17` 기반으로 실행 환경 준비
- jar 파일 복사
- 컨테이너 시작 시 jar 실행

### 📦 4. Docker Hub

👉 이미지 저장소 (GitHub 같은 느낌)
- 공식 이미지가 많음: MySQL, Redis, Nginx 등
- 직접 만든 이미지도 올려서 공유/배포 가능

---

## 3️⃣ 도커 vs 기존 방식

| 구분 | 기존 서버 배포 | 도커 |
| --- | --- | --- |
| 환경 설정 | 직접 설치 | 이미지에 자동 포함 |
| 서버마다 차이 | 있음 | 없음 |
| 배포 속도 | 느림 | 빠름 |
| 유지보수 | 어려움 | 쉬움 |

---

## 4️⃣ 도커 구조 이해 (중요)

```
[Docker Engine]
    ├── Image      (설계도)
    ├── Container  (실행)
    └── Volume     (데이터 저장)
```

- **Image**: 실행 환경 + 앱 묶음
- **Container**: 이미지 실행 결과(프로세스)
- **Volume**: 컨테이너가 삭제돼도 남겨야 하는 데이터(DB 데이터 등)

---

## 5️⃣ 실제 사용 흐름 (Spring Boot 기준)

### ① 프로젝트 빌드

```bash
./gradlew build
```

### ② Dockerfile 작성

```dockerfile
FROM openjdk:17
COPY build/libs/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### ③ 이미지 생성

```bash
docker build -t my-app .
```

### ④ 컨테이너 실행

```bash
docker run -d -p 8080:8080 my-app
```

👉 끝. 서버에 Java 설치/환경 세팅 없이도 실행됩니다.

---

## 6️⃣ 도커 쓰는 이유 (실무 핵심)

- **환경 통일**: 개발/테스트/운영 환경을 동일하게 유지
- **빠른 배포**: 서버를 새로 만들어도 이미지로 바로 실행
- **확장성**: 컨테이너를 여러 개 띄워 트래픽 처리(스케일 아웃)
- **MSA 필수 기반**: 마이크로서비스에서 사실상 표준

---

## 7️⃣ 도커 + AWS 조합 (중요)

권장 방향
- **Spring Boot → Docker → AWS (EC2 / ECS / EKS)**

### ① EC2 + Docker (입문)
- EC2에 Docker 설치
- `docker run`으로 컨테이너 실행

👉 지금 단계에서 가장 적당한 루트

### ② Kubernetes (EKS) (나중 단계)
- 컨테이너 자동 배치/확장/복구까지 관리

---

## 8️⃣ 도커 컴포즈 (중요⭐)

👉 여러 컨테이너를 **한 번에 실행/관리** (예: App + DB + Redis)

예시 (`docker-compose.yml`)

```yaml
version: "3"
services:
  app:
    build: .
    ports:
      - "8080:8080"

  db:
    image: mysql
```

실행

```bash
docker-compose up
```

---

## 🔥 너한테 중요한 포인트 (현실 기준)

너는 지금
- Spring Boot ✔️
- AWS 배포 ✔️
- Docker 기본 ✔️

이제 해야 할 것 (우선순위)
- **1순위**: Spring Boot를 Docker 이미지로 만들기
- **2순위**: AWS EC2에서 Docker로 실행하기
- **3순위**: Docker Compose로 DB(MySQL/Redis)까지 같이 구성하기

