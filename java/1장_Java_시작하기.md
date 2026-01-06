# 1장. Java 시작하기

## Java란 무엇인가

Java는 1995년 썬 마이크로시스템즈(Sun Microsystems)에서 개발한 객체지향 프로그래밍 언어입니다. 현재는 오라클(Oracle)이 관리하고 있으며, 웹 애플리케이션, 모바일 앱(Android), 엔터프라이즈 소프트웨어 등 다양한 분야에서 널리 사용되고 있습니다.

### Java의 주요 용도
- 웹 애플리케이션 개발
- 안드로이드 앱 개발
- 서버 사이드 개발
- 데스크톱 애플리케이션
- 임베디드 시스템

---

## Java의 특징

### 1. 플랫폼 독립성 (Platform Independence)

Java의 가장 큰 특징 중 하나는 **"Write Once, Run Anywhere"** (한 번 작성하면 어디서든 실행)입니다.

- Java 코드는 **바이트코드(Bytecode)**로 컴파일됩니다
- 바이트코드는 JVM(Java Virtual Machine)이 설치된 어떤 운영체제에서도 실행 가능합니다
- Windows, Linux, macOS 등 다양한 플랫폼에서 동일한 코드를 실행할 수 있습니다

```
Java 소스코드(.java) 
    ↓ 컴파일
바이트코드(.class) 
    ↓ JVM
실행 (어떤 OS에서든!)
```

### 2. 객체지향 프로그래밍 (Object-Oriented Programming, OOP)

Java는 순수 객체지향 언어로, 다음과 같은 OOP 개념을 지원합니다:

- **캡슐화 (Encapsulation)**: 데이터와 메서드를 하나로 묶어 관리
- **상속 (Inheritance)**: 기존 클래스를 확장하여 새로운 클래스 생성
- **다형성 (Polymorphism)**: 하나의 인터페이스로 여러 형태 구현
- **추상화 (Abstraction)**: 복잡한 것을 단순하게 표현

### 3. 기타 주요 특징

- **자동 메모리 관리**: 가비지 컬렉션(Garbage Collection)으로 메모리 자동 관리
- **멀티스레딩 지원**: 동시에 여러 작업을 처리할 수 있음
- **풍부한 라이브러리**: 다양한 기능을 제공하는 표준 라이브러리
- **안정성**: 컴파일 시점에 많은 오류를 발견할 수 있음

---

## JDK / JRE / JVM 이해

Java 개발과 실행에 필요한 구성 요소들을 이해하는 것이 중요합니다.

### JVM (Java Virtual Machine)
- **역할**: 바이트코드를 실행하는 가상 머신
- **기능**: 바이트코드를 각 운영체제에 맞는 기계어로 변환하여 실행
- **플랫폼별로 다른 JVM**: Windows용 JVM, Linux용 JVM, macOS용 JVM 등

### JRE (Java Runtime Environment)
- **구성**: JVM + Java 클래스 라이브러리 + 기타 파일
- **역할**: Java 프로그램을 **실행**하기 위한 환경
- **사용 대상**: Java 프로그램을 실행만 하는 사용자

### JDK (Java Development Kit)
- **구성**: JRE + 개발 도구 (컴파일러, 디버거 등)
- **역할**: Java 프로그램을 **개발**하기 위한 환경
- **포함 도구**:
  - `javac`: Java 컴파일러 (소스코드를 바이트코드로 변환)
  - `java`: Java 실행기
  - `jar`: Java 아카이브 도구
  - 기타 개발 도구들

### 관계도
```
JDK = JRE + 개발 도구
JRE = JVM + 클래스 라이브러리
```

**개발자라면 JDK가 필요합니다!**

---

## 개발 환경 구축

### 1. JDK 설치

#### Windows
1. Oracle 공식 사이트에서 JDK 다운로드
   - https://www.oracle.com/java/technologies/downloads/
   - 최신 LTS 버전 권장 (예: JDK 17, JDK 21)
2. 설치 파일 실행 후 기본 설정으로 설치
3. 환경 변수 설정 (PATH에 JDK bin 폴더 추가)

#### 설치 확인
명령 프롬프트(cmd) 또는 PowerShell에서 확인:
```bash
java -version
javac -version
```

### 2. VS Code 설정

#### 필수 확장 프로그램 설치
1. **Extension Pack for Java** (Microsoft)
   - Java 개발에 필요한 모든 확장을 포함
   - 자동으로 다음 확장들을 설치:
     - Language Support for Java
     - Debugger for Java
     - Test Runner for Java
     - Maven for Java
     - Project Manager for Java
     - Visual Studio IntelliCode

#### VS Code에서 Java 설정
1. VS Code 실행
2. `Ctrl + Shift + X`로 확장 프로그램 탭 열기
3. "Extension Pack for Java" 검색 후 설치
4. 설치 후 자동으로 Java 프로젝트 인식

### 3. VS Code Java 단축키

#### 코드 편집 관련
- `Ctrl + Space`: 자동 완성 (IntelliSense)
- `Ctrl + Shift + Space`: 매개변수 힌트 표시
- `Alt + Shift + F`: 코드 포맷팅 (전체 파일)
- `Ctrl + K, Ctrl + F`: 선택 영역 포맷팅
- `Ctrl + /`: 주석 토글 (한 줄)
- `Shift + Alt + A`: 블록 주석 토글
- `Ctrl + D`: 같은 단어 선택 (여러 개 선택 가능)
- `Alt + ↑/↓`: 현재 줄 위/아래로 이동
- `Shift + Alt + ↑/↓`: 현재 줄 복사하여 위/아래에 붙여넣기
- `Ctrl + Shift + K`: 현재 줄 삭제
- `Ctrl + Enter`: 아래에 새 줄 삽입
- `Ctrl + Shift + Enter`: 위에 새 줄 삽입

#### 코드 탐색 및 이동
- `Ctrl + P`: 파일 빠른 열기
- `Ctrl + Shift + O`: 현재 파일의 심볼(클래스, 메서드 등) 검색
- `Ctrl + T`: 전체 프로젝트의 심볼 검색
- `F12`: 정의로 이동 (Go to Definition)
- `Alt + F12`: 정의 피킹 (Peek Definition) - 새 창에서 확인
- `Shift + F12`: 참조 찾기 (Find All References)
- `Ctrl + -`: 이전 위치로 이동
- `Ctrl + Shift + -`: 다음 위치로 이동
- `Ctrl + B`: 사이드바 토글

#### 리팩토링 관련
- `F2`: 심볼 이름 바꾸기 (Rename Symbol)
- `Ctrl + Shift + R`: 리팩토링 메뉴 열기
- `Alt + Shift + R`: 선택한 심볼 이름 바꾸기
- `Ctrl + .`: 빠른 수정 (Quick Fix) - 오류 수정 제안

#### 실행 및 디버깅
- `F5`: 디버깅 시작/계속
- `F9`: 중단점 토글
- `F10`: Step Over (다음 줄로)
- `F11`: Step Into (함수 안으로)
- `Shift + F11`: Step Out (함수 밖으로)
- `Ctrl + Shift + F5`: 디버깅 재시작
- `Shift + F5`: 디버깅 중지
- `Ctrl + F5`: 디버깅 없이 실행

#### Java 특화 기능
- `Ctrl + Shift + P` → "Java: Clean Java Language Server Workspace": Java 언어 서버 재시작
- `Ctrl + Shift + P` → "Java: Rebuild Projects": 프로젝트 재빌드
- `Ctrl + Shift + P` → "Java: Show Build Job Status": 빌드 상태 확인
- `Alt + Shift + S`: Source 액션 (Getter/Setter 생성 등)
- `Ctrl + 1`: 빠른 수정 제안 표시

#### 기타 유용한 단축키
- `Ctrl + `` (백틱)`: 터미널 토글
- `Ctrl + Shift + `` (백틱)`: 새 터미널 생성
- `Ctrl + K, Ctrl + S`: 키보드 단축키 설정 열기
- `Ctrl + ,`: 설정 열기
- `Ctrl + K, Ctrl + W`: 모든 탭 닫기
- `Ctrl + Tab`: 열린 파일 간 전환
- `Ctrl + W`: 현재 탭 닫기
- `Ctrl + Shift + T`: 최근에 닫은 파일 다시 열기

---

## 첫 Java 프로그램 (Hello World)

### 프로그램 작성

프로젝트 폴더를 만들고 `HelloWorld.java` 파일을 생성합니다.

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### 코드 설명

- `public class HelloWorld`: HelloWorld라는 이름의 공개 클래스 선언
  - 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- `public static void main(String[] args)`: 프로그램의 시작점
  - `public`: 어디서든 접근 가능
  - `static`: 클래스 레벨에서 실행 (객체 생성 없이)
  - `void`: 반환값 없음
  - `main`: 메서드 이름 (프로그램 진입점)
  - `String[] args`: 명령줄 인자를 받는 배열
- `System.out.println()`: 콘솔에 출력하는 메서드

### 프로그램 실행 방법

#### 방법 1: 명령줄에서 실행
```bash
# 1. 컴파일 (바이트코드 생성)
javac HelloWorld.java

# 2. 실행
java HelloWorld
```

#### 방법 2: VS Code에서 실행
1. `HelloWorld.java` 파일 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 디버그 모드로 실행

### 실행 결과
```
Hello, World!
```

---

## 연습 문제

1. "안녕하세요, Java!"를 출력하는 프로그램을 작성하세요.
2. 여러 줄의 메시지를 출력하는 프로그램을 작성하세요.
3. 이름을 변수에 저장하고 "안녕하세요, [이름]님!"을 출력하는 프로그램을 작성하세요.

---

## 다음 장 예고

다음 장에서는 변수, 데이터 타입, 연산자 등 Java의 기본 문법을 학습합니다.

