# 13장 예제 파일 실행 방법

## 명령줄에서 실행하기

### 1. ProcessThread.java
```bash
javac ProcessThread.java
java ProcessThread
```

### 2. ThreadCreation.java
```bash
javac ThreadCreation.java
java ThreadCreation
```

### 3. SynchronizationExample.java
```bash
javac SynchronizationExample.java
java SynchronizationExample
```

### 4. ThreadControl.java
```bash
javac ThreadControl.java
java ThreadControl
```

### 5. SimpleServer.java
```bash
javac SimpleServer.java
java SimpleServer
```

### 6. SimpleClient.java
```bash
javac SimpleClient.java
java SimpleClient
```

### 7. MultiThreadServer.java
```bash
javac MultiThreadServer.java
java MultiThreadServer
```

### 8. NetworkBasics.java
```bash
javac NetworkBasics.java
java NetworkBasics
```

### 9. ThreadNetworkComprehensive.java
```bash
javac ThreadNetworkComprehensive.java
java ThreadNetworkComprehensive
```

## 네트워크 예제 실행 방법

### 서버와 클라이언트 실행

1. **터미널 1**: 서버 실행
```bash
javac SimpleServer.java
java SimpleServer
```

2. **터미널 2**: 클라이언트 실행
```bash
javac SimpleClient.java
java SimpleClient
```

### 멀티스레드 서버 테스트

1. **터미널 1**: 멀티스레드 서버 실행
```bash
javac MultiThreadServer.java
java MultiThreadServer
```

2. **터미널 2, 3, 4...**: 여러 클라이언트 실행
```bash
javac SimpleClient.java
java SimpleClient
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 주의사항

- 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- Java는 대소문자를 구분합니다
- 각 파일은 하나의 public 클래스를 포함해야 합니다
- 네트워크 예제는 서버를 먼저 실행한 후 클라이언트를 실행해야 합니다
- 포트가 이미 사용 중이면 다른 포트를 사용하세요
- 네트워크 예제는 예외 처리가 필수입니다

## 학습 포인트

- **프로세스와 스레드**: 프로세스는 독립적인 메모리, 스레드는 공유 메모리
- **스레드 생성**: Thread 상속, Runnable 구현, 람다 표현식
- **동기화**: synchronized로 공유 자원 안전하게 접근
- **네트워크**: Socket을 통한 클라이언트-서버 통신
- **멀티스레드 서버**: 여러 클라이언트를 동시에 처리

## 주요 개념

### 스레드
- `start()`: 새 스레드 시작
- `run()`: 스레드가 실행할 코드
- `sleep()`: 스레드 대기
- `join()`: 스레드 종료 대기
- `synchronized`: 동기화 키워드

### 네트워크
- `ServerSocket`: 서버 소켓 생성
- `Socket`: 클라이언트 소켓 생성
- `accept()`: 클라이언트 연결 대기
- `getInputStream()`: 입력 스트림
- `getOutputStream()`: 출력 스트림

## 네트워크 통신 순서

1. 서버: ServerSocket 생성 및 대기
2. 클라이언트: Socket으로 서버에 연결
3. 서버: accept()로 연결 수락
4. 양방향 통신: 데이터 송수신
5. 연결 종료: 소켓 close()

