# VSCode Java 터미널 한글 깨짐 해결 방법

---

## 문제 상황

VSCode 터미널에서 Java 프로그램을 실행할 때 한글이 깨져서 출력되는 현상

**예시:**
```
입력: 홍길동
출력: ��ȣ�� (깨진 문자)
```

---

## 원인

Windows에서 VSCode 터미널의 기본 인코딩이 **MS949(CP949)**인데, Java는 기본적으로 **UTF-8**을 사용하기 때문에 발생하는 문제입니다.

---

## 해결 방법

### 방법 1: VSCode 설정 파일 수정 (권장)

#### 1-1. settings.json 파일 열기

1. `Ctrl + Shift + P` (또는 `F1`)
2. "Preferences: Open User Settings (JSON)" 입력 후 선택

#### 1-2. 다음 설정 추가

```json
{
    // 터미널 인코딩 설정
    "terminal.integrated.defaultProfile.windows": "PowerShell",
    "terminal.integrated.profiles.windows": {
        "PowerShell": {
            "source": "PowerShell",
            "args": ["-NoExit", "-Command", "chcp 65001"]
        }
    },
    
    // Java 컴파일/실행 시 인코딩 설정
    "java.compile.nullAnalysis.mode": "automatic",
    "java.configuration.runtimes": [],
    
    // 파일 인코딩 설정
    "files.encoding": "utf8",
    "files.autoGuessEncoding": true
}
```

#### 1-3. VSCode 재시작

설정 변경 후 VSCode를 완전히 종료하고 다시 실행합니다.

---

### 방법 2: 터미널에서 직접 인코딩 변경

#### 2-1. PowerShell 터미널에서

```powershell
# UTF-8로 변경
chcp 65001

# Java 프로그램 실행
javac Test.java
java Test
```

#### 2-2. 명령 프롬프트(CMD)에서

```cmd
chcp 65001
javac Test.java
java Test
```

**주의:** 터미널을 닫으면 설정이 초기화됩니다.

---

### 방법 3: Java 컴파일/실행 시 인코딩 옵션 지정

#### 3-1. 컴파일 시 인코딩 지정

```bash
javac -encoding UTF-8 Test.java
```

#### 3-2. 실행 시 인코딩 지정

```bash
java -Dfile.encoding=UTF-8 Test
```

#### 3-3. 한 번에 실행

```bash
javac -encoding UTF-8 Test.java && java -Dfile.encoding=UTF-8 Test
```

---

### 방법 4: tasks.json 파일 설정 (자동화)

#### 4-1. tasks.json 파일 생성

1. 프로젝트 폴더에 `.vscode` 폴더 생성 (없는 경우)
2. `.vscode/tasks.json` 파일 생성

#### 4-2. tasks.json 내용

```json
{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "javac: compile",
            "type": "shell",
            "command": "javac",
            "args": [
                "-encoding",
                "UTF-8",
                "${file}"
            ],
            "group": {
                "kind": "build",
                "isDefault": true
            },
            "problemMatcher": ["$tsc"]
        },
        {
            "label": "java: run",
            "type": "shell",
            "command": "java",
            "args": [
                "-Dfile.encoding=UTF-8",
                "-cp",
                "${fileDirname}",
                "${fileBasenameNoExtension}"
            ],
            "group": "test",
            "dependsOn": "javac: compile",
            "problemMatcher": []
        }
    ]
}
```

#### 4-3. 사용 방법

1. Java 파일 열기
2. `Ctrl + Shift + P`
3. "Tasks: Run Task" 선택
4. "java: run" 선택

---

### 방법 5: launch.json 파일 설정 (디버깅)

#### 5-1. launch.json 파일 생성

`.vscode/launch.json` 파일 생성

#### 5-2. launch.json 내용

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Java: 현재 파일 실행",
            "request": "launch",
            "mainClass": "${file}",
            "projectName": "your-project-name",
            "vmArgs": "-Dfile.encoding=UTF-8",
            "encoding": "UTF-8"
        }
    ]
}
```

---

### 방법 6: VSCode Java Extension 설정

#### 6-1. Java Extension Pack 설치 확인

확장 프로그램에서 "Extension Pack for Java" 설치 확인

#### 6-2. Java 설정 확인

1. `Ctrl + ,` (설정 열기)
2. "java" 검색
3. "Java: Compile" 관련 설정 확인

---

## 테스트 코드

다음 코드로 한글 출력이 정상적으로 되는지 확인하세요:

```java
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("이름 입력: ");
        String name = sc.nextLine();
        
        System.out.println("입력한 이름: " + name);
        System.out.println("한글 테스트: 안녕하세요");
        
        sc.close();
    }
}
```

**정상 출력 예시:**
```
이름 입력: 홍길동
입력한 이름: 홍길동
한글 테스트: 안녕하세요
```

---

## 추가 팁

### 팁 1: 터미널 프로필 설정

VSCode 설정에서 기본 터미널을 UTF-8로 설정:

```json
{
    "terminal.integrated.defaultProfile.windows": "PowerShell",
    "terminal.integrated.profiles.windows": {
        "PowerShell": {
            "source": "PowerShell",
            "args": ["-NoExit", "-Command", "[Console]::OutputEncoding = [System.Text.Encoding]::UTF8; chcp 65001"]
        }
    }
}
```

### 팁 2: 환경 변수 설정

시스템 환경 변수에 추가:

1. 시스템 속성 → 고급 → 환경 변수
2. 시스템 변수에 추가:
   - 변수명: `JAVA_TOOL_OPTIONS`
   - 변수값: `-Dfile.encoding=UTF-8`

### 팁 3: PowerShell 프로필 설정

PowerShell 프로필 파일에 추가:

```powershell
# 프로필 파일 위치 확인
$PROFILE

# 프로필 파일에 추가
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
chcp 65001 | Out-Null
```

---

## 문제 해결 체크리스트

- [ ] VSCode settings.json에 UTF-8 인코딩 설정 추가
- [ ] 터미널에서 `chcp 65001` 실행
- [ ] Java 컴파일 시 `-encoding UTF-8` 옵션 사용
- [ ] Java 실행 시 `-Dfile.encoding=UTF-8` 옵션 사용
- [ ] VSCode 재시작
- [ ] 테스트 코드로 확인

---

## 자주 발생하는 오류

### 오류 1: "인코딩을 인식할 수 없습니다"

**해결:**
```bash
javac -encoding UTF-8 파일명.java
```

### 오류 2: 터미널에서 한글이 여전히 깨짐

**해결:**
1. 터미널 종료 후 재시작
2. `chcp 65001` 실행
3. VSCode 재시작

### 오류 3: 파일 저장 시 인코딩 경고

**해결:**
1. 파일 열기
2. 우측 하단 인코딩 표시 클릭
3. "Save with Encoding" 선택
4. "UTF-8" 선택

---

## 참고 자료

- [VSCode 공식 문서 - 터미널 설정](https://code.visualstudio.com/docs/terminal/profiles)
- [Java 인코딩 가이드](https://docs.oracle.com/javase/tutorial/i18n/text/index.html)

---

**작성일:** 2024년  
**버전:** 1.0
