# VSCode C/C++ 세팅 폴더 영상 정리

> 원본: [(2024.10.6) Windows에 Visual Studio Code를 사용한 C/C++ 개발환경 만들기](https://www.youtube.com/watch?v=Fbg6DCqMrYs)  
> 채널: 멈춤보단 천천히라도 (webnautes)  
> 검색 키워드: `vscode c 세팅 폴더`

이 영상의 핵심은 **파일을 하나만 여는 게 아니라, 폴더를 연 뒤 `.vscode`에 설정을 저장하는 것**입니다.  
그래야 `F5`를 누를 때마다 C++를 고르지 않고, 컴파일·실행이 반복됩니다.

---

## 한 줄 요약

| 단계 | 하는 일 | 결과 |
|------|---------|------|
| 1 | MinGW를 `C:\mingw64`에 두고 PATH 등록 | `gcc`, `g++` 명령 가능 |
| 2 | VS Code 설치 + 한글 UI | 편집기 준비 |
| 3 | **폴더 열기** 후 하위 폴더에 `.c` / `.cpp` 작성 | 작업 공간(워크스페이스) 생김 |
| 4 | C/C++ 확장 + `c_cpp_properties.json` | 자동완성(IntelliSense) |
| 5 | `tasks.json` + 단축키 | 저장+컴파일, 실행 |
| 6 | `F5`로 디버깅 | 브레이크포인트, 변수 확인 |

---

## 왜 폴더를 열어야 하나

VS Code의 C/C++ 설정은 **지금 연 폴더** 안에만 저장됩니다.

```
프로젝트폴더/          ← 파일 > 폴더 열기로 이 폴더를 연다
├── .vscode/           ← 세팅이 여기 생김
│   ├── c_cpp_properties.json   ← 컴파일러 경로, 자동완성
│   └── tasks.json              ← 컴파일 / 실행 작업
├── helloworld/
│   └── hello.c
├── helloworldcpp/
│   └── hello.cpp
├── testc/
│   └── test.c
└── testdcpp/          (영상: testcpp)
    └── test.cpp
```

- `.c` 파일만 열면 `.vscode`가 생기지 않거나, 매번 디버거를 다시 고르게 됩니다.
- 폴더를 연 뒤에 `F5` → **C++** 을 한 번 고르면, 이후에는 그 설정이 `.vscode`에 남습니다.
- 경로에 **한글·공백**이 있으면 컴파일이 실패하는 경우가 많습니다. 영문 폴더명을 권장합니다.

---

## 1. MinGW 컴파일러 설치

영상은 **WinLibs MinGW-w64** 압축본을 사용합니다.

1. 브라우저에서 MinGW-w64 다운로드 (파일 이름에 `x86_64`, `win32`, `seh`가 들어간 것)
2. 압축을 푼 뒤 **`mingw64` 폴더를 `C:\`로 복사**
3. 최종 위치는 아래와 같아야 합니다.

```text
C:\mingw64\bin\gcc.exe
C:\mingw64\bin\g++.exe
C:\mingw64\bin\gdb.exe
```

### PATH 등록

1. `Win + R` → `sysdm.cpl` → 확인
2. **고급** 탭 → **환경 변수**
3. **시스템 변수**의 `Path` → 편집 → 새로 만들기
4. 아래 경로 추가

```text
C:\mingw64\bin
```

5. 확인으로 창을 모두 닫기
6. **새로** 명령 프롬프트를 연 뒤 확인

```bat
gcc --version
g++ --version
gdb --version
```

이미 **MSYS2**를 쓰는 PC라면 경로가 다를 수 있습니다.

| 설치 방식 | PATH 예시 |
|-----------|-----------|
| 영상 (WinLibs) | `C:\mingw64\bin` |
| MSYS2 ucrt64 | `C:\msys64\ucrt64\bin` |
| MSYS2 mingw64 | `C:\msys64\mingw64\bin` |

`g++ --version`이 나오면 어느 쪽이든 됩니다. 두 개를 동시에 PATH에 넣으면 헷갈리니 **하나만** 씁니다.

---

## 2. VS Code 설치와 한글 UI

1. Google에서 `Visual Studio Code` 검색
2. **System Installer x64** 다운로드 후 설치
3. 한글 UI (선택)

`Ctrl + Shift + P` → `display` 입력 → **Configure Display Language** → **한국어** → Restart

---

## 3. 세팅 폴더 만들기 (영상 핵심)

1. 왼쪽 탐색기 아이콘 클릭
2. **폴더 열기**
3. 왼쪽 위 **새 폴더** → 예: `CppProject` (영문 권장)
4. 그 폴더를 선택한 뒤 **폴더 선택**
5. **예, 작성자를 신뢰합니다** 클릭

소스 파일은 **프로젝트 바로 아래가 아니라 하위 폴더**에 둡니다. (영상 방식)

### C 예제

1. 열린 폴더 이름 옆 **새 폴더** → `helloworld`
2. **새 파일** → `hello.c`

```c
#include <stdio.h>

int main(void) {
    printf("Hello World!\n");
    return 0;
}
```

오른쪽 아래에 **C/C++ 확장 설치** 안내가 뜨면 **설치**를 누릅니다. (Microsoft C/C++)

### C++ 예제

1. 탐색기 빈 곳 클릭 → **새 폴더** → `helloworldcpp`
2. **새 파일** → `hello.cpp`

```cpp
#include <iostream>
using namespace std;

int main() {
    cout << "Hello World!" << endl;
    return 0;
}
```

`Ctrl + S`로 저장합니다.

---

## 4. `.vscode` 자동완성 설정 (`c_cpp_properties.json`)

1. `Ctrl + Shift + P`
2. `C/C++` 입력 → **C/C++: 구성 선택**(Select a Configuration)
   - 목록에 없으면 VS Code를 **재시작**한 뒤 다시 검색
3. **구성 편집**
4. 컴파일러: **g++**
5. IntelliSense 모드: **windows-gcc-x64**

탐색기에 `.vscode/c_cpp_properties.json`이 생깁니다. 설정은 자동 저장됩니다.

예시 (경로는 PC에 맞게 수정):

```json
{
    "configurations": [
        {
            "name": "Win32",
            "includePath": [
                "${workspaceFolder}/**"
            ],
            "defines": [
                "_DEBUG",
                "UNICODE",
                "_UNICODE"
            ],
            "compilerPath": "C:/mingw64/bin/g++.exe",
            "cStandard": "c17",
            "cppStandard": "c++17",
            "intelliSenseMode": "windows-gcc-x64"
        }
    ],
    "version": 4
}
```

---

## 5. 컴파일·실행 작업 (`tasks.json`)

영상은 메뉴에서 빈 템플릿을 만든 뒤, 블로그 내용을 붙여 넣습니다.

1. 메뉴 **터미널** → **작업 구성...** (Configure Tasks)
2. **템플릿에서 tasks.json 파일 만들기**
3. **Others** 선택
4. 생성된 `.vscode/tasks.json`을 아래처럼 바꿉니다.

```json
{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "Save and Compile for C",
            "type": "shell",
            "command": "gcc",
            "args": [
                "${file}",
                "-g",
                "-o",
                "${fileDirname}\\${fileBasenameNoExtension}.exe"
            ],
            "problemMatcher": ["$gcc"],
            "presentation": {
                "reveal": "always",
                "panel": "new"
            }
        },
        {
            "label": "Save and Compile for C++",
            "type": "shell",
            "command": "g++",
            "args": [
                "${file}",
                "-g",
                "-o",
                "${fileDirname}\\${fileBasenameNoExtension}.exe"
            ],
            "problemMatcher": ["$gcc"],
            "presentation": {
                "reveal": "always",
                "panel": "new"
            }
        },
        {
            "label": "execute",
            "type": "shell",
            "command": "${fileDirname}\\${fileBasenameNoExtension}.exe",
            "problemMatcher": [],
            "presentation": {
                "reveal": "always",
                "panel": "new"
            }
        }
    ]
}
```

의미:

- `Save and Compile for C` → 지금 연 `.c`를 `gcc`로 컴파일 → 같은 폴더에 `.exe`
- `Save and Compile for C++` → `.cpp`를 `g++`로 컴파일
- `execute` → 방금 만든 `.exe` 실행 (출력은 터미널)

---

## 6. 단축키와 터미널

### 단축키 (`keybindings.json`)

1. **파일** → **기본 설정** → **바로 가기 키**
2. 오른쪽 위 **JSON으로 열기** 아이콘 클릭
3. 아래를 추가합니다.

```json
[
    {
        "key": "ctrl+alt+c",
        "command": "workbench.action.tasks.runTask"
    },
    {
        "key": "ctrl+alt+r",
        "command": "workbench.action.tasks.runTask",
        "args": "execute"
    }
]
```

- `Ctrl + Alt + C` : 작업 목록이 뜸 → C면 **Save and Compile for C**, C++면 **Save and Compile for C++**
- `Ctrl + Alt + R` : **execute** (실행)

### 터미널을 Command Prompt로

`Ctrl + Shift + P` → **터미널: 기본 프로필 선택** → **Command Prompt**

PowerShell보다 `gcc`/`g++` 호출이 덜 꼬입니다.

### 컴파일 확인

1. `hello.c`를 연 상태
2. `Ctrl + Alt + C` → **Save and Compile for C**
3. 탐색기에 `hello.exe`가 보이면 성공
4. `Ctrl + Alt + R` → 터미널에 `Hello World!`

C++도 같습니다. `hello.cpp`를 연 뒤 **Save and Compile for C++** → `Ctrl + Alt + R`.

---

## 7. F5 디버깅 (C++를 한 번 고르는 이유)

영상도 **처음 F5**에서 디버거를 고릅니다.

1. `testcpp` 폴더와 `test.cpp` 생성 (브레이크포인트를 걸 예제)
2. 줄 번호 왼쪽을 클릭해 **빨간 점**(브레이크포인트) 추가
3. `F5`
4. 목록에서 **C++ (GDB/LLDB)** 선택
5. 컴파일러가 있는 **g++.exe** 선택 (경로가 `mingw64` 또는 `msys64`인 항목)

이후:

- 빨간 점에서 실행이 멈춤
- `F11` : 한 줄씩 실행 (Step Into)
- 왼쪽 **변수** 창에서 지역 변수 확인
- 전역 변수는 Watch(조사식)에 이름을 직접 추가

C 파일(`test.c`)도 같은 방법입니다. `F5` → **C++** → 이번에는 **gcc.exe**를 고를 수 있습니다.  
(C/C++ 확장이 같은 디버거를 쓰기 때문에 메뉴 이름이 C++입니다.)

`F5`를 한 번 성공하면 `.vscode/launch.json`이 생기고, **다음부터는 F5만 누르면** 됩니다.

---

## 수업에서 막히는 지점과 영상 연결

| 증상 | 원인 | 영상에서 하는 해결 |
|------|------|-------------------|
| `exe`는 생기는데 실행이 안 됨 | 빌드만 함, 또는 Code Runner가 이미 실행 중 | `tasks.json`의 **execute** 또는 `Ctrl + Alt + R` |
| `F5`마다 C++를 고름 | 폴더를 안 열었거나 `launch.json` 없음 | **폴더 열기** 후 F5 한 번으로 설정 저장 |
| `Ctrl + Alt + N`이 안 됨 | Code Runner 단축키. 영상은 Code Runner를 안 씀 | 영상 방식은 `Ctrl + Alt + C` / `Ctrl + Alt + R` |
| `gcc`를 찾을 수 없음 | PATH 미등록, 또는 VS Code를 PATH 등록 전에 실행 | PATH 추가 후 **VS Code 재시작** |
| 구성 선택이 안 보임 | C/C++ 확장이 아직 준비 안 됨 | VS Code 재시작 후 `Ctrl + Shift + P` |

---

## 체크리스트

- [ ] `C:\mingw64\bin`(또는 MSYS2 경로)이 PATH에 있다
- [ ] 새 cmd에서 `gcc --version`이 된다
- [ ] VS Code에서 **파일 하나가 아니라 폴더**를 열었다
- [ ] `.vscode/c_cpp_properties.json`이 있다
- [ ] `.vscode/tasks.json`에 컴파일·execute가 있다
- [ ] `hello.c` 컴파일 후 `hello.exe`가 같은 폴더에 생긴다
- [ ] `F5` 한 번으로 `.vscode/launch.json`이 생겼다

영상에서 건너뛴 세부 JSON은 채널 블로그를 보면 됩니다. 위 내용은 자막 기준으로 따라 할 수 있게 재구성한 수업 노트입니다.
