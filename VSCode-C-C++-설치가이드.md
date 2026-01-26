# VSCode C/C++ 개발 환경 설치 가이드

## 📋 목차
1. [VSCode 설치](#1-vscode-설치)
2. [C/C++ 확장 설치](#2-cc-확장-설치)
3. [컴파일러 설치](#3-컴파일러-설치)
4. [환경 변수 설정](#4-환경-변수-설정)
5. [VSCode 설정 파일 구성](#5-vscode-설정-파일-구성)
6. [테스트 및 확인](#6-테스트-및-확인)

---

## 1. VSCode 설치

### 1.1 VSCode 다운로드
1. [Visual Studio Code 공식 사이트](https://code.visualstudio.com/) 접속
2. "Download for Windows" 클릭하여 다운로드
3. 설치 프로그램 실행 후 기본 설정으로 설치

### 1.2 설치 확인
- VSCode 실행 후 정상적으로 열리는지 확인

---

## 2. C/C++ 확장 설치

### 2.1 확장 설치 방법
1. VSCode 실행
2. 왼쪽 사이드바에서 **Extensions** 아이콘 클릭 (또는 `Ctrl+Shift+X`)
3. 검색창에 `C/C++` 입력
4. **Microsoft의 "C/C++"** 확장 선택
   - 확장 ID: `ms-vscode.cpptools`
   - 작성자: Microsoft
5. **Install** 버튼 클릭

### 2.2 추가 확장 (선택사항)
- **C/C++ Extension Pack**: C/C++ 관련 확장 모음
  - C/C++ (Microsoft)
  - C/C++ Themes
  - CMake Tools
  - CMake

### 2.3 설치 확인
- 확장 설치 후 VSCode 재시작 또는 Reload 권장
- `.c` 또는 `.cpp` 파일을 열면 C/C++ 관련 기능이 활성화됨

---

## 3. 컴파일러 설치

### 방법 A: MSYS2 사용 (권장)

#### 3.1 MSYS2 설치
1. [MSYS2 공식 사이트](https://www.msys2.org/) 접속
2. Windows용 설치 프로그램 다운로드
3. 설치 프로그램 실행
   - 기본 설치 경로: `C:\msys64`

#### 3.2 패키지 업데이트
1. MSYS2 터미널 실행 (시작 메뉴에서 "MSYS2 MSYS" 검색)
2. 다음 명령어 실행:
```bash
pacman -Syu
```
- 업데이트 완료 후 터미널 재시작 요청 시 재시작

#### 3.3 GCC 컴파일러 및 디버거 설치
MSYS2 터미널에서 다음 명령어 실행:
```bash
pacman -S mingw-w64-x86_64-gcc
pacman -S mingw-w64-x86_64-g++
pacman -S mingw-w64-x86_64-gdb
```

또는 한 번에 설치:
```bash
pacman -S mingw-w64-x86_64-toolchain
```

#### 3.4 설치 경로 확인
- 설치 경로: `C:\msys64\mingw64\bin`
- 이 경로를 다음 단계에서 PATH에 추가해야 함

---

### 방법 B: 독립형 MinGW-w64 설치

#### 3.1 MinGW-w64 다운로드
1. [MinGW-w64 공식 사이트](https://www.mingw-w64.org/) 또는
2. [WinLibs](https://winlibs.com/)에서 사전 빌드된 버전 다운로드

#### 3.2 설치
1. 다운로드한 파일 압축 해제
2. 원하는 위치에 폴더 생성 (예: `C:\mingw64`)
3. 압축 해제한 내용을 해당 폴더에 복사

#### 3.3 설치 경로 확인
- `bin` 폴더 경로 확인 (예: `C:\mingw64\bin`)

---

## 4. 환경 변수 설정

### 4.1 PATH 환경 변수 추가 (Windows)

#### 방법 1: 시스템 속성 사용
1. **시작 메뉴** → **"환경 변수"** 검색 → **"시스템 환경 변수 편집"** 클릭
2. **"환경 변수"** 버튼 클릭
3. **"사용자 변수"** 또는 **"시스템 변수"**에서 **Path** 선택
4. **"편집"** 버튼 클릭
5. **"새로 만들기"** 클릭
6. MinGW의 `bin` 폴더 경로 입력:
   - MSYS2 사용 시: `C:\msys64\mingw64\bin`
   - 독립형 설치 시: `C:\mingw64\bin` (설치한 경로)
7. **"확인"** 클릭하여 모든 창 닫기

#### 방법 2: PowerShell 사용 (관리자 권한)
```powershell
[Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\msys64\mingw64\bin", "User")
```

### 4.2 설치 확인
새로운 PowerShell 또는 CMD 창을 열고 다음 명령어 실행:

```bash
gcc --version
g++ --version
gdb --version
```

정상적으로 버전 정보가 출력되면 설치 성공!

**예시 출력:**
```
gcc (x86_64-posix-seh-rev0, Built by MinGW-W64 project) 13.2.0
Copyright (C) 2023 Free Software Foundation, Inc.
```

---

## 5. VSCode 설정 파일 구성

### 5.1 작업 폴더 생성
1. 원하는 위치에 프로젝트 폴더 생성 (예: `C:\cpp-projects\hello`)
2. VSCode에서 해당 폴더 열기: **File → Open Folder**

### 5.2 tasks.json 생성 (빌드 설정)

#### 자동 생성 방법:
1. `.cpp` 파일 생성 (예: `hello.cpp`)
2. `Ctrl+Shift+P` → **"Tasks: Configure Default Build Task"** 입력
3. **"C/C++: g++.exe build active file"** 선택
4. `.vscode/tasks.json` 파일이 자동 생성됨

#### 수동 생성 방법:
1. 프로젝트 폴더에 `.vscode` 폴더 생성
2. `.vscode/tasks.json` 파일 생성 후 다음 내용 입력:

```json
{
    "version": "2.0.0",
    "tasks": [
        {
            "type": "cppbuild",
            "label": "C/C++: g++.exe build active file",
            "command": "C:\\msys64\\mingw64\\bin\\g++.exe",
            "args": [
                "-fdiagnostics-color=always",
                "-g",
                "${file}",
                "-o",
                "${fileDirname}\\${fileBasenameNoExtension}.exe"
            ],
            "options": {
                "cwd": "${fileDirname}"
            },
            "problemMatcher": [
                "$gcc"
            ],
            "group": {
                "kind": "build",
                "isDefault": true
            },
            "detail": "컴파일러: C:\\msys64\\mingw64\\bin\\g++.exe"
        }
    ]
}
```

**경로 수정 필요:**
- `command` 경로를 실제 설치한 경로로 변경
- MSYS2: `C:\\msys64\\mingw64\\bin\\g++.exe`
- 독립형: `C:\\mingw64\\bin\\g++.exe`

---

### 5.3 launch.json 생성 (디버깅 설정)

#### 자동 생성 방법:
1. 왼쪽 사이드바에서 **Run and Debug** 아이콘 클릭 (또는 `Ctrl+Shift+D`)
2. **"create a launch.json file"** 클릭
3. **"C/C++: g++.exe build and debug active file"** 선택
4. `.vscode/launch.json` 파일이 자동 생성됨

#### 수동 생성 방법:
`.vscode/launch.json` 파일 생성 후 다음 내용 입력:

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": "C/C++: g++.exe build and debug active file",
            "type": "cppdbg",
            "request": "launch",
            "program": "${fileDirname}\\${fileBasenameNoExtension}.exe",
            "args": [],
            "stopAtEntry": false,
            "cwd": "${fileDirname}",
            "environment": [],
            "externalConsole": false,
            "MIMode": "gdb",
            "miDebuggerPath": "C:\\msys64\\mingw64\\bin\\gdb.exe",
            "setupCommands": [
                {
                    "description": "Enable pretty-printing for gdb",
                    "text": "-enable-pretty-printing",
                    "ignoreFailures": true
                }
            ],
            "preLaunchTask": "C/C++: g++.exe build active file"
        }
    ]
}
```

**경로 수정 필요:**
- `miDebuggerPath`를 실제 gdb.exe 경로로 변경

---

### 5.4 c_cpp_properties.json 생성 (IntelliSense 설정)

#### 자동 생성 방법:
1. `.cpp` 파일 열기
2. 전구 아이콘(💡) 클릭 → **"Update includePath"** 선택
3. `.vscode/c_cpp_properties.json` 파일이 자동 생성됨

#### 수동 생성 방법:
`.vscode/c_cpp_properties.json` 파일 생성 후 다음 내용 입력:

```json
{
    "configurations": [
        {
            "name": "Win32",
            "includePath": [
                "${workspaceFolder}/**",
                "C:/msys64/mingw64/include/**",
                "C:/msys64/mingw64/include/c++/13.2.0/**"
            ],
            "defines": [
                "_DEBUG",
                "UNICODE",
                "_UNICODE"
            ],
            "windowsSdkVersion": "10.0.22621.0",
            "compilerPath": "C:/msys64/mingw64/bin/g++.exe",
            "cStandard": "c17",
            "cppStandard": "c++17",
            "intelliSenseMode": "windows-gcc-x64"
        }
    ],
    "version": 4
}
```

**경로 수정 필요:**
- `compilerPath`: 실제 g++.exe 경로
- `includePath`: 실제 include 폴더 경로

---

## 6. 테스트 및 확인

### 6.1 테스트 파일 생성
프로젝트 폴더에 `hello.cpp` 파일 생성:

```cpp
#include <iostream>
using namespace std;

int main() {
    cout << "Hello, VSCode with C++!" << endl;
    cout << "C/C++ 환경 설정이 완료되었습니다!" << endl;
    return 0;
}
```

### 6.2 빌드 및 실행

#### 방법 1: 단축키 사용
1. `hello.cpp` 파일 열기
2. `Ctrl+Shift+B` → 빌드 실행
3. 터미널에서 실행:
```bash
.\hello.exe
```

#### 방법 2: 터미널에서 직접 실행
```bash
g++ hello.cpp -o hello.exe
.\hello.exe
```

#### 방법 3: 디버깅 모드 실행
1. `F5` 키 누르기 또는 **Run and Debug**에서 실행
2. 자동으로 빌드 후 디버깅 시작

### 6.3 예상 출력
```
Hello, VSCode with C++!
C/C++ 환경 설정이 완료되었습니다!
```

---

## 7. 문제 해결

### 문제 1: "g++ is not recognized"
**해결 방법:**
- 환경 변수 PATH가 제대로 설정되었는지 확인
- 새로운 터미널 창 열기 (기존 창은 PATH 변경 사항이 반영되지 않음)
- VSCode 재시작

### 문제 2: IntelliSense가 작동하지 않음
**해결 방법:**
- `c_cpp_properties.json` 파일의 경로 확인
- C/C++ 확장이 설치되어 있는지 확인
- VSCode 재시작

### 문제 3: 빌드 오류 발생
**해결 방법:**
- `tasks.json`의 `command` 경로 확인
- 컴파일러가 정상 설치되었는지 확인 (`g++ --version`)
- 파일 경로에 한글이나 공백이 없는지 확인

### 문제 4: 디버깅이 작동하지 않음
**해결 방법:**
- `launch.json`의 `miDebuggerPath` 경로 확인
- GDB가 설치되어 있는지 확인 (`gdb --version`)
- `preLaunchTask`가 올바른지 확인

---

## 8. 추가 팁

### 8.1 코드 포맷팅
- `Ctrl+Shift+P` → **"Format Document"**
- 또는 저장 시 자동 포맷팅 설정:
  ```json
  "editor.formatOnSave": true
  ```

### 8.2 코드 스니펫
- `Ctrl+Shift+P` → **"Preferences: Configure User Snippets"**
- `cpp.json` 선택하여 C++ 스니펫 추가

### 8.3 여러 파일 컴파일
`tasks.json` 수정:
```json
"args": [
    "-fdiagnostics-color=always",
    "-g",
    "${workspaceFolder}/*.cpp",
    "-o",
    "${workspaceFolder}\\${workspaceFolderBasename}.exe"
]
```

---

## 9. 요약 체크리스트

- [ ] VSCode 설치 완료
- [ ] C/C++ 확장 설치 완료
- [ ] MinGW-w64 컴파일러 설치 완료
- [ ] 환경 변수 PATH 설정 완료
- [ ] `g++ --version` 명령어로 확인 완료
- [ ] `tasks.json` 파일 생성 완료
- [ ] `launch.json` 파일 생성 완료
- [ ] `c_cpp_properties.json` 파일 생성 완료
- [ ] 테스트 파일 빌드 및 실행 성공

---

## 10. 참고 자료

- [VSCode 공식 문서 - C/C++](https://code.visualstudio.com/docs/languages/cpp)
- [MSYS2 공식 사이트](https://www.msys2.org/)
- [MinGW-w64 공식 사이트](https://www.mingw-w64.org/)

---

**설치 완료 후 즐거운 코딩 되세요! 🎉**
