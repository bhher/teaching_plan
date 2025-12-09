# 11장 예제 파일 실행 방법

## 명령줄에서 실행하기

### 1. StreamConcept.java
```bash
javac StreamConcept.java
java StreamConcept
```

### 2. ByteStreamExample.java
```bash
javac ByteStreamExample.java
java ByteStreamExample
```

### 3. CharacterStreamExample.java
```bash
javac CharacterStreamExample.java
java CharacterStreamExample
```

### 4. FileIOExample.java
```bash
javac FileIOExample.java
java FileIOExample
```

### 5. StudentFileManager.java
```bash
javac StudentFileManager.java
java StudentFileManager
```

### 6. FileCopyExample.java
```bash
javac FileCopyExample.java
java FileCopyExample
```

### 7. IOComprehensive.java
```bash
javac IOComprehensive.java
java IOComprehensive
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 주의사항

- 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- Java는 대소문자를 구분합니다
- 각 파일은 하나의 public 클래스를 포함해야 합니다
- 파일 입출력은 `IOException`이 발생할 수 있으므로 반드시 예외 처리가 필요합니다
- 스트림은 반드시 `close()`를 호출하거나 try-with-resources를 사용해야 합니다
- 파일을 읽기 전에 존재하는지 확인하는 것이 좋습니다

## 학습 포인트

- **스트림 개념**: 데이터가 흐르는 통로
- **바이트 스트림**: 바이트 단위로 데이터 처리 (InputStream/OutputStream)
- **문자 스트림**: 문자 단위로 데이터 처리 (Reader/Writer)
- **파일 입출력**: 파일에 데이터 저장 및 읽기
- **리소스 관리**: try-with-resources로 자동 리소스 정리

## 주요 클래스

### 바이트 스트림
- `FileInputStream`: 파일에서 바이트 읽기
- `FileOutputStream`: 파일에 바이트 쓰기
- `BufferedInputStream`: 버퍼를 사용한 바이트 읽기
- `BufferedOutputStream`: 버퍼를 사용한 바이트 쓰기

### 문자 스트림
- `FileReader`: 파일에서 문자 읽기
- `FileWriter`: 파일에 문자 쓰기
- `BufferedReader`: 버퍼를 사용한 문자 읽기 (줄 단위)
- `BufferedWriter`: 버퍼를 사용한 문자 쓰기

### 파일 관리
- `File`: 파일과 디렉토리 정보 관리

## 파일 입출력 패턴

1. **파일 쓰기**: FileWriter 또는 BufferedWriter 사용
2. **파일 읽기**: FileReader 또는 BufferedReader 사용
3. **리소스 관리**: try-with-resources 사용
4. **예외 처리**: IOException 처리 필수
5. **파일 확인**: File.exists()로 존재 여부 확인

## 바이트 vs 문자 스트림

- **바이트 스트림**: 바이너리 데이터 (이미지, 동영상 등)
- **문자 스트림**: 텍스트 데이터 (텍스트 파일)

