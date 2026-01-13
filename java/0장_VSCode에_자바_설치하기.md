---
title: "0장. VSCode에 자바 설치하기"
description: "Java 개발을 위한 개발 환경 구축 - JDK, VSCode, Java 확장 프로그램 설치 가이드"
category: "설치 및 환경 설정"
level: "초급"
tags:
  - #Java
  - #JDK
  - #VSCode
  - #VisualStudioCode
  - #개발환경
  - #설치
  - #환경설정
  - #초보자
  - #튜토리얼
  - #Windows
keywords:
  - Java 설치
  - VSCode Java 설정
  - Extension Pack for Java
  - Java 개발 환경
  - JDK 설치 가이드
---

# 0장. VSCode에 자바 설치하기

이 장에서는 Java 개발을 위한 개발 환경을 구축하는 방법을 단계별로 설명합니다. Visual Studio Code(VSCode)를 사용하여 Java 개발 환경을 설정하는 방법을 학습합니다.

---

## 사전 준비사항

Java 개발을 시작하기 전에 다음 사항을 확인하세요:

- Windows 운영체제 (Windows 10 이상 권장)&nbsp;

- 인터넷 연결&nbsp;

- 관리자 권한 (설치 시 필요할 수 있음)&nbsp;

---

## 1단계: JDK 설치

### JDK란?

**JDK (Java Development Kit)**는 Java 프로그램을 개발하고 실행하기 위한 필수 도구입니다. JDK에는 다음이 포함됩니다:

- **컴파일러 (javac)**: Java 소스코드를 바이트코드로 변환&nbsp;

- **실행기 (java)**: 바이트코드를 실행&nbsp;

- **라이브러리**: Java 표준 라이브러리&nbsp;

- **개발 도구**: 디버거, 문서 생성기 등&nbsp;

### JDK 다운로드 및 설치

#### 방법 1: Oracle JDK 설치 (권장)

1. **Oracle 공식 사이트 접속**&nbsp;
   - https://www.oracle.com/java/technologies/downloads/&nbsp;
   - 또는 https://www.oracle.com/kr/java/technologies/downloads/ (한국어)&nbsp;

2. **JDK 버전 선택**&nbsp;
   - **LTS (Long Term Support) 버전 권장**: JDK 17 또는 JDK 21&nbsp;
   - LTS 버전은 장기간 지원되므로 안정적입니다&nbsp;
   - 최신 버전도 사용 가능하지만, 학습 목적이라면 LTS 버전을 권장합니다&nbsp;

3. **Windows용 JDK 다운로드**&nbsp;
   - Windows x64 Installer 선택&nbsp;
   - `.exe` 파일 다운로드&nbsp;

4. **JDK 설치**&nbsp;
   - 다운로드한 `.exe` 파일 실행&nbsp;
   - 설치 마법사 따라하기&nbsp;
   - **설치 경로 확인**: 기본 경로는 `C:\Program Files\Java\jdk-XX` (XX는 버전 번호)&nbsp;
   - 설치 완료&nbsp;

#### 방법 2: OpenJDK 설치 (무료, 오픈소스)

1. **Adoptium (Eclipse Temurin) 접속**&nbsp;
   - https://adoptium.net/&nbsp;

2. **JDK 다운로드**&nbsp;
   - 버전 선택 (JDK 17 또는 JDK 21 권장)&nbsp;
   - 운영체제: Windows&nbsp;
   - 아키텍처: x64&nbsp;
   - 패키지 타입: JDK&nbsp;
   - 다운로드&nbsp;

3. **설치**&nbsp;
   - 다운로드한 `.msi` 파일 실행&nbsp;
   - 설치 마법사 따라하기&nbsp;

### 환경 변수 설정

JDK 설치 후 환경 변수를 설정해야 명령줄에서 Java를 사용할 수 있습니다.

#### 자동 설정 (권장)

- 최신 JDK 설치 프로그램은 대부분 자동으로 환경 변수를 설정합니다&nbsp;

- 설치 후 명령 프롬프트를 **새로 열어서** 확인하세요&nbsp;

#### 수동 설정 (필요한 경우)

1. **시스템 속성 열기**&nbsp;
   - `Win + R` 키 누르기&nbsp;
   - `sysdm.cpl` 입력 후 Enter&nbsp;

2. **환경 변수 설정**&nbsp;
   - "고급" 탭 클릭&nbsp;
   - "환경 변수" 버튼 클릭&nbsp;
   - "시스템 변수"에서 `Path` 선택 후 "편집" 클릭&nbsp;
   - "새로 만들기" 클릭&nbsp;
   - JDK의 `bin` 폴더 경로 추가:&nbsp;
     ```
     C:\Program Files\Java\jdk-17\bin
     ```
     (버전에 따라 경로가 다를 수 있음)
   - "확인" 클릭하여 모든 창 닫기&nbsp;

### 설치 확인

명령 프롬프트(cmd) 또는 PowerShell을 **새로 열고** 다음 명령어 실행:

```bash
java -version
```

**예상 출력:**
```
java version "17.0.9" 2023-10-17 LTS
Java(TM) SE Runtime Environment (build 17.0.9+11-LTS-201)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.9+11-LTS-201, mixed mode, sharing)
```

```bash
javac -version
```

**예상 출력:**
```
javac 17.0.9
```

**⚠️ 주의사항:**

- 명령어를 실행했을 때 "명령을 찾을 수 없습니다" 또는 "is not recognized" 오류가 나오면:&nbsp;
  1. 환경 변수가 제대로 설정되지 않았을 수 있습니다&nbsp;
  2. 명령 프롬프트를 **새로 열어서** 다시 시도하세요&nbsp;
  3. JDK가 실제로 설치되었는지 확인하세요&nbsp;

---

## 2단계: Visual Studio Code 설치

### VSCode란?

**Visual Studio Code (VSCode)**는 Microsoft에서 개발한 무료 코드 에디터입니다. Java 개발을 포함한 다양한 프로그래밍 언어를 지원하며, 확장 프로그램을 통해 기능을 확장할 수 있습니다.

### VSCode 다운로드 및 설치

1. **VSCode 공식 사이트 접속**&nbsp;
   - https://code.visualstudio.com/&nbsp;

2. **다운로드**&nbsp;
   - "Download for Windows" 클릭&nbsp;
   - 시스템에 맞는 버전 자동 선택 (64-bit 권장)&nbsp;

3. **설치**&nbsp;
   - 다운로드한 `.exe` 파일 실행&nbsp;
   - 설치 마법사 따라하기&nbsp;
   - **추가 작업 선택** (선택 사항):&nbsp;
     - ✅ "PATH에 추가" 체크 (명령줄에서 `code` 명령어 사용 가능)&nbsp;
     - ✅ "파일 탐색기에서 'Code로 열기' 작업 추가" 체크&nbsp;

4. **VSCode 실행**&nbsp;
   - 설치 완료 후 VSCode 실행&nbsp;

---

## 3단계: Java 확장 프로그램 설치

VSCode에서 Java 개발을 하려면 Java 확장 프로그램이 필요합니다.

### Extension Pack for Java 설치

1. **확장 프로그램 탭 열기**&nbsp;
   - `Ctrl + Shift + X` 키 누르기&nbsp;
   - 또는 왼쪽 사이드바에서 확장 프로그램 아이콘 클릭&nbsp;

2. **Java 확장 프로그램 검색**&nbsp;
   - 검색창에 "Extension Pack for Java" 입력&nbsp;
   - **Microsoft**에서 제공하는 "Extension Pack for Java" 선택&nbsp;

3. **설치**&nbsp;
   - "설치" 버튼 클릭&nbsp;
   - 이 확장 팩은 다음 확장 프로그램들을 자동으로 설치합니다:&nbsp;
     - **Language Support for Java**: Java 언어 지원&nbsp;
     - **Debugger for Java**: Java 디버거&nbsp;
     - **Test Runner for Java**: Java 테스트 실행기&nbsp;
     - **Maven for Java**: Maven 프로젝트 지원&nbsp;
     - **Project Manager for Java**: Java 프로젝트 관리&nbsp;
     - **Visual Studio IntelliCode**: AI 기반 코드 완성&nbsp;

4. **설치 완료 확인**&nbsp;
   - 설치가 완료되면 "설치됨"으로 표시됩니다&nbsp;
   - VSCode를 재시작할 필요는 없지만, 권장됩니다&nbsp;

### 추가 유용한 확장 프로그램 (선택 사항)

- **Java Extension Pack** (이미 설치됨)&nbsp;

- **Code Runner**: 코드를 빠르게 실행&nbsp;

- **GitLens**: Git 기능 강화&nbsp;

- **Korean Language Pack**: VSCode 한국어 지원&nbsp;

---

## 4단계: Java 설정 확인

### VSCode에서 Java 버전 확인

1. **명령 팔레트 열기**&nbsp;
   - `Ctrl + Shift + P` 키 누르기&nbsp;

2. **Java 버전 확인**&nbsp;
   - "Java: Configure Java Runtime" 입력 후 선택&nbsp;
   - 또는 "Java: Show Runtime" 입력 후 선택&nbsp;
   - 설치된 JDK 버전이 표시됩니다&nbsp;

### Java 설정 파일 확인

VSCode는 Java 프로젝트를 자동으로 인식합니다. 첫 번째 Java 파일을 열면 자동으로 설정이 완료됩니다.

---

## 5단계: 첫 Java 프로그램 테스트

### 프로젝트 폴더 생성

1. **작업 폴더 생성**&nbsp;
   ```
   예: C:\Users\사용자명\Documents\JavaProjects
   ```

2. **VSCode에서 폴더 열기**&nbsp;
   - `File` → `Open Folder` (또는 `Ctrl + K, Ctrl + O`)&nbsp;
   - 생성한 폴더 선택&nbsp;

### HelloWorld 프로그램 작성

1. **새 파일 생성**&nbsp;
   - `File` → `New File` (또는 `Ctrl + N`)&nbsp;
   - 파일 이름: `HelloWorld.java`&nbsp;

2. **코드 작성**
   ```java
   public class HelloWorld {
       public static void main(String[] args) {
           System.out.println("Hello, World!");
           System.out.println("Java 설치가 완료되었습니다!");
       }
   }
   ```

3. **파일 저장**&nbsp;
   - `Ctrl + S` 또는 `File` → `Save`&nbsp;

### 프로그램 실행

#### 방법 1: VSCode에서 직접 실행 (가장 간단)

1. **Run 버튼 클릭**&nbsp;
   - `main` 메서드 위에 나타나는 "Run" 또는 "▶ Run" 버튼 클릭&nbsp;
   - 또는 `F5` 키를 눌러 디버그 모드로 실행&nbsp;

2. **터미널에서 결과 확인**&nbsp;
   - 하단 터미널에 실행 결과가 표시됩니다:&nbsp;
     ```
     Hello, World!
     Java 설치가 완료되었습니다!
     ```

#### 방법 2: 명령줄에서 실행

1. **터미널 열기**&nbsp;
   - `Ctrl + `` (백틱) 또는 `Terminal` → `New Terminal`&nbsp;

2. **컴파일 및 실행**
   ```bash
   javac HelloWorld.java
   java HelloWorld
   ```

3. **결과 확인**
   ```
   Hello, World!
   Java 설치가 완료되었습니다!
   ```

---

## 문제 해결 (Troubleshooting)

### 문제 1: "Java를 찾을 수 없습니다"

**해결 방법:**

1. JDK가 제대로 설치되었는지 확인

2. 환경 변수 `Path`에 JDK의 `bin` 폴더가 추가되었는지 확인

3. 명령 프롬프트를 **새로 열어서** 다시 시도

4. VSCode를 재시작

### 문제 2: VSCode에서 Java 확장 프로그램이 작동하지 않음

**해결 방법:**

1. 확장 프로그램이 제대로 설치되었는지 확인

2. VSCode를 재시작

3. "Java: Configure Java Runtime" 명령으로 JDK 경로 확인

4. 확장 프로그램을 제거 후 다시 설치

### 문제 3: 컴파일 오류 발생

**해결 방법:**

1. 파일 이름과 클래스 이름이 일치하는지 확인&nbsp;
   - 파일: `HelloWorld.java` → 클래스: `public class HelloWorld`&nbsp;

2. 대소문자 구분 확인 (Java는 대소문자를 구분합니다)

3. 코드 문법 오류 확인 (세미콜론, 중괄호 등)

### 문제 4: 여러 JDK 버전이 설치된 경우

**해결 방법:**

1. VSCode에서 사용할 JDK 버전 선택:&nbsp;
   - `Ctrl + Shift + P` → "Java: Configure Java Runtime"&nbsp;
   - 원하는 JDK 버전 선택&nbsp;

2. 또는 `settings.json`에서 직접 설정:
   ```json
   {
       "java.configuration.runtimes": [
           {
               "name": "JavaSE-17",
               "path": "C:\\Program Files\\Java\\jdk-17"
           }
       ]
   }
   ```

---

## 설치 체크리스트

설치가 완료되었는지 다음 항목을 확인하세요:

- [ ] JDK 설치 완료 (`java -version` 명령어로 확인)&nbsp;
- [ ] 컴파일러 설치 확인 (`javac -version` 명령어로 확인)&nbsp;
- [ ] VSCode 설치 완료&nbsp;
- [ ] Extension Pack for Java 설치 완료&nbsp;
- [ ] HelloWorld 프로그램 실행 성공&nbsp;
- [ ] 터미널에서 Java 명령어 사용 가능&nbsp;

---

## 다음 단계

설치가 완료되었다면 다음 장으로 진행하세요:

- **1장. Java 시작하기**: Java의 기본 개념과 특징을 학습합니다.

---

## 참고 자료

- **Oracle Java 공식 사이트**: https://www.oracle.com/java/&nbsp;

- **OpenJDK (Adoptium)**: https://adoptium.net/&nbsp;

- **VSCode 공식 사이트**: https://code.visualstudio.com/&nbsp;

- **VSCode Java 확장 프로그램**: https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack&nbsp;

---

## 요약

이 장에서는 다음을 학습했습니다:

1. ✅ **JDK 설치**: Java 개발에 필수적인 JDK를 설치하는 방법&nbsp;
2. ✅ **VSCode 설치**: 무료이면서 강력한 코드 에디터 설치&nbsp;
3. ✅ **Java 확장 프로그램 설치**: VSCode에서 Java 개발을 위한 확장 프로그램 설치&nbsp;
4. ✅ **환경 설정 확인**: 설치가 제대로 되었는지 확인하는 방법&nbsp;
5. ✅ **첫 프로그램 실행**: HelloWorld 프로그램을 작성하고 실행하여 환경 구축 완료 확인&nbsp;

이제 Java 개발을 시작할 준비가 완료되었습니다! 🎉

