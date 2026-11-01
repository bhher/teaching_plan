# VSCode HTML · CSS · JavaScript 개발 가이드

웹 프론트엔드(HTML, CSS, JavaScript) 학습·실습에 필요한 **VSCode 설치, 필수 프로그램, 확장 프로그램, 단축키, 설정**을 한곳에 정리한 문서입니다.

---

## 목차

1. [VSCode 설치](#1-vscode-설치)
2. [필수·권장 프로그램 (Dependency)](#2-필수권장-프로그램-dependency)
3. [확장 프로그램 (Extensions)](#3-확장-프로그램-extensions)
4. [추천 설정 (settings.json)](#4-추천-설정-settingsjson)
5. [단축키 모음](#5-단축키-모음)
6. [Emmet 빠른 작성법](#6-emmet-빠른-작성법)
7. [HTML · CSS · JavaScript 실습 흐름](#7-html--css--javascript-실습-흐름)
8. [브라우저 개발자 도구](#8-브라우저-개발자-도구)
9. [자주 쓰는 명령 팔레트](#9-자주-쓰는-명령-팔레트)
10. [문제 해결](#10-문제-해결)
11. [학습 체크리스트](#11-학습-체크리스트)

---

## 1. VSCode 설치

### 1.1 다운로드

- 공식 사이트: https://code.visualstudio.com/
- Windows: **Download for Windows** (64-bit 권장)

### 1.2 Windows 설치 시 체크 항목

| 옵션 | 설명 |
|------|------|
| Add to PATH | 터미널에서 `code` 명령 사용 |
| Create a desktop icon | 바탕화면 바로가기 |
| Register Code as an editor... | HTML/CSS/JS 파일 더블클릭 시 VSCode로 열기 |

### 1.3 한국어 적용 (선택)

1. `Ctrl+Shift+X` → **Korean Language Pack** 검색 후 설치
2. `Ctrl+Shift+P` → `Configure Display Language` → `ko` 선택
3. VSCode 재시작

---

## 2. 필수·권장 프로그램 (Dependency)

웹 개발에 자주 함께 쓰는 프로그램입니다.

| 프로그램 | 필수 여부 | 용도 | 다운로드 |
|----------|-----------|------|----------|
| **Chrome** 또는 **Edge** | 필수 | 페이지 확인, 개발자 도구 | chrome.google.com / microsoft.com/edge |
| **Git** | 권장 | 버전 관리, GitHub 연동 | https://git-scm.com/download/win |
| **Node.js (LTS)** | 권장 | npm, 빌드 도구, 서버 실행 | https://nodejs.org/ |
| **Live Server** | VSCode 확장 | HTML 실시간 미리보기 | VSCode 마켓플레이스 |

### 2.1 Git 설치 확인

```bash
git --version
```

### 2.2 Node.js 설치 확인

```bash
node --version
npm --version
```

### 2.3 VSCode에서 폴더 열기

```bash
# 터미널에서 프로젝트 폴더를 VSCode로 열기
code .
```

---

## 3. 확장 프로그램 (Extensions)

`Ctrl+Shift+X` 로 확장 탭을 열고 검색·설치합니다.

### 3.1 필수 확장

| 확장 이름 | 확장 ID | 용도 |
|-----------|---------|------|
| **Live Server** | `ritwickdey.LiveServer` | HTML 저장 시 브라우저 자동 새로고침 |
| **Prettier** | `esbenp.prettier-vscode` | HTML/CSS/JS 코드 자동 정렬 |
| **HTML CSS Support** | `ecmel.vscode-html-css` | class/id 자동 완성 |

### 3.2 HTML/CSS 추천

| 확장 이름 | 용도 |
|-----------|------|
| **Auto Rename Tag** | 여는 태그 수정 시 닫는 태그도 같이 변경 |
| **Color Highlight** | `#ff0000`, `rgb()` 등 색상 미리보기 |
| **Path Intellisense** | `src`, `href` 경로 자동 완성 |
| **CSS Peek** | class 이름에서 CSS 정의로 바로 이동 |

### 3.3 JavaScript 추천

| 확장 이름 | 용도 |
|-----------|------|
| **ESLint** | JS 문법·스타일 검사 |
| **JavaScript (ES6) code snippets** | `clg`, `fn` 등 코드 스니펫 |
| **Error Lens** | 오류·경고를 코드 줄 옆에 바로 표시 |

### 3.4 공통 추천

| 확장 이름 | 용도 |
|-----------|------|
| **GitLens** | Git 변경 이력, 작성자 표시 |
| **Korean Language Pack** | VSCode 한국어 UI |

### 3.5 Live Server 사용법

1. `index.html` 파일 열기
2. 우클릭 → **Open with Live Server**
3. 또는 하단 상태바 **Go Live** 클릭
4. 기본 주소: `http://127.0.0.1:5500`

---

## 4. 추천 설정 (settings.json)

`Ctrl+Shift+P` → `Preferences: Open User Settings (JSON)` 입력 후 아래 내용을 참고합니다.

```json
{
  // 자동 저장
  "files.autoSave": "afterDelay",
  "files.autoSaveDelay": 1000,
  "files.encoding": "utf8",
  "files.eol": "\n",

  // 저장 시 자동 포맷
  "editor.formatOnSave": true,
  "editor.defaultFormatter": "esbenp.prettier-vscode",

  // 들여쓰기 (웹 표준: 스페이스 2칸)
  "editor.tabSize": 2,
  "editor.insertSpaces": true,
  "editor.detectIndentation": false,

  // 편집 편의
  "editor.wordWrap": "on",
  "editor.bracketPairColorization.enabled": true,
  "editor.guides.bracketPairs": true,
  "editor.linkedEditing": true,
  "editor.fontSize": 14,

  // HTML
  "html.format.indentInnerHtml": true,
  "html.format.wrapLineLength": 120,
  "emmet.triggerExpansionOnTab": true,

  // CSS
  "css.validate": true,
  "css.lint.unknownProperties": "warning",

  // JavaScript
  "javascript.updateImportsOnFileMove.enabled": "always",
  "javascript.suggest.autoImports": true,

  // Prettier 언어별 적용
  "[html]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[css]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[javascript]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },

  // Live Server
  "liveServer.settings.donotShowInfoMsg": true
}
```

### 4.1 프로젝트별 설정 (.vscode/settings.json)

팀 프로젝트나 수업용 폴더에만 적용할 설정 예시:

```json
{
  "editor.tabSize": 2,
  "editor.formatOnSave": true,
  "liveServer.settings.port": 5500
}
```

---

## 5. 단축키 모음

> Windows 기준입니다. macOS는 `Ctrl` → `Cmd`, `Alt` → `Option` 으로 대체하면 됩니다.

### 5.1 파일·편집 기본

| 기능 | Windows |
|------|---------|
| 새 파일 | `Ctrl+N` |
| 파일 열기 | `Ctrl+O` |
| 폴더 열기 | `Ctrl+K` `Ctrl+O` |
| 저장 | `Ctrl+S` |
| 모두 저장 | `Ctrl+K` `S` |
| 파일 닫기 | `Ctrl+W` |
| 되돌리기 / 다시 실행 | `Ctrl+Z` / `Ctrl+Y` |
| 찾기 / 바꾸기 | `Ctrl+F` / `Ctrl+H` |
| 프로젝트 전체 검색 | `Ctrl+Shift+F` |
| 전체 선택 | `Ctrl+A` |

### 5.2 코드 편집

| 기능 | Windows |
|------|---------|
| 줄 복사 (위/아래) | `Shift+Alt+↑` / `Shift+Alt+↓` |
| 줄 이동 (위/아래) | `Alt+↑` / `Alt+↓` |
| 줄 삭제 | `Ctrl+Shift+K` |
| 한 줄 주석 토글 | `Ctrl+/` |
| 블록 주석 토글 | `Shift+Alt+A` |
| 들여쓰기 / 내어쓰기 | `Ctrl+]` / `Ctrl+[` |
| 전체 포맷팅 | `Shift+Alt+F` |
| 선택 영역 포맷팅 | `Ctrl+K` `Ctrl+F` |

### 5.3 탐색·검색

| 기능 | Windows |
|------|---------|
| 명령 팔레트 | `Ctrl+Shift+P` |
| 파일 빠른 열기 | `Ctrl+P` |
| 현재 파일 심볼 검색 | `Ctrl+Shift+O` |
| 정의로 이동 | `F12` |
| 정의 미리보기 | `Alt+F12` |
| 참조 찾기 | `Shift+F12` |
| 줄 번호로 이동 | `Ctrl+G` |
| 뒤로 / 앞으로 | `Alt+←` / `Alt+→` |

### 5.4 패널·뷰

| 기능 | Windows |
|------|---------|
| 사이드바 토글 | `Ctrl+B` |
| 탐색기 | `Ctrl+Shift+E` |
| 검색 | `Ctrl+Shift+F` |
| 소스 제어 (Git) | `Ctrl+Shift+G` |
| 확장 프로그램 | `Ctrl+Shift+X` |
| 터미널 열기/닫기 | `` Ctrl+` `` |
| 문제 패널 (오류 목록) | `Ctrl+Shift+M` |
| 설정 열기 | `Ctrl+,` |
| 단축키 목록 보기 | `Ctrl+K` `Ctrl+S` |

### 5.5 멀티 커서

| 기능 | Windows |
|------|---------|
| 커서 추가 | `Alt+Click` |
| 위/아래 커서 추가 | `Ctrl+Alt+↑` / `Ctrl+Alt+↓` |
| 같은 단어 다음 선택 | `Ctrl+D` |
| 같은 단어 모두 선택 | `Ctrl+Shift+L` |
| 모든 일치 항목에 커서 | `Ctrl+F2` |

### 5.6 HTML · CSS · JavaScript 특화

| 기능 | Windows |
|------|---------|
| 태그 이름 변경 (연동 편집) | `F2` |
| 태그로 이동 | `Ctrl+Shift+O` (HTML 파일) |
| Emmet 확장 | `Tab` 또는 `Ctrl+E` |
| HTML5 기본 뼈대 | `!` 입력 후 `Tab` |
| 선택 영역을 태그로 감싸기 | `Ctrl+Shift+P` → `Emmet: Wrap with Abbreviation` |
| JS 함수 정의로 이동 | `F12` |
| JS 디버깅 시작 | `F5` |
| 중단점 설정/해제 | `F9` |
| 디버깅 중 한 줄 실행 | `F10` |
| 디버깅 중 함수 안으로 | `F11` |

---

## 6. Emmet 빠른 작성법

VSCode에 기본 내장되어 있습니다. 약어 입력 후 `Tab` 으로 확장합니다.

### 6.1 HTML

| 입력 | 결과 |
|------|------|
| `!` | HTML5 기본 구조 |
| `div.container` | `<div class="container"></div>` |
| `ul>li*3` | `<li>` 3개 목록 |
| `a[href="#"]{홈}` | 링크 + 텍스트 |
| `div.card>img+h3+p` | 카드 구조 |

**네비게이션 예시**

```
nav>ul>li*4>a[href="#"]{메뉴$}
```

### 6.2 CSS

| 입력 | 결과 |
|------|------|
| `m10` | `margin: 10px;` |
| `p20` | `padding: 20px;` |
| `w100p` | `width: 100%;` |
| `df` | `display: flex;` |
| `jcc` | `justify-content: center;` |
| `aic` | `align-items: center;` |
| `posr` | `position: relative;` |
| `fz16` | `font-size: 16px;` |

---

## 7. HTML · CSS · JavaScript 실습 흐름

### 7.1 기본 프로젝트 구조

```
my-web-project/
├── index.html
├── css/
│   └── style.css
├── js/
│   └── main.js
└── images/
    └── logo.png
```

### 7.2 HTML에서 CSS·JS 연결

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>내 첫 웹페이지</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <h1>안녕하세요</h1>
  <script src="js/main.js"></script>
</body>
</html>
```

### 7.3 실행 방법

| 방법 | 설명 |
|------|------|
| **Live Server** | HTML/CSS/JS 변경 시 자동 새로고침 (권장) |
| **브라우저 직접 열기** | `index.html` 더블클릭 (일부 JS 기능 제한 가능) |
| **Node.js 서버** | `npx serve .` 등으로 로컬 서버 실행 |

### 7.4 JavaScript 디버깅 (F5)

1. `index.html` 또는 `main.js` 에 중단점(`F9`) 설정
2. `F5` → **Chrome** 또는 **Edge** 선택
3. 변수 값, 호출 스택 확인

`.vscode/launch.json` 예시:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "chrome",
      "request": "launch",
      "name": "Chrome에서 열기",
      "file": "${workspaceFolder}/index.html"
    }
  ]
}
```

### 7.5 콘솔 확인

- 브라우저: `F12` → **Console** 탭
- JS 코드: `console.log('메시지')`

---

## 8. 브라우저 개발자 도구

| 기능 | Chrome / Edge |
|------|---------------|
| 개발자 도구 열기 | `F12` 또는 `Ctrl+Shift+I` |
| 요소 검사 | `Ctrl+Shift+C` |
| 반응형 모드 | `Ctrl+Shift+M` |
| 콘솔 | `Ctrl+Shift+J` |
| 새로고침 (캐시 무시) | `Ctrl+Shift+R` |

### 자주 쓰는 탭

| 탭 | 용도 |
|----|------|
| **Elements** | HTML 구조, CSS 스타일 확인·수정 |
| **Console** | `console.log`, 오류 메시지 확인 |
| **Network** | 이미지·CSS·JS 로딩 상태 확인 |
| **Sources** | JS 파일 디버깅 |

---

## 9. 자주 쓰는 명령 팔레트

`Ctrl+Shift+P` 입력 후 사용합니다.

| 명령 | 용도 |
|------|------|
| `Format Document` | 현재 파일 포맷팅 |
| `Change Language Mode` | HTML/CSS/JavaScript 모드 전환 |
| `Change File Encoding` | UTF-8 인코딩 변경 |
| `Emmet: Expand Abbreviation` | Emmet 수동 확장 |
| `Live Server: Open with Live Server` | Live Server 실행 |
| `Preferences: Open User Settings (JSON)` | 설정 JSON 열기 |
| `Developer: Reload Window` | VSCode 창 새로고침 |

---

## 10. 문제 해결

### 10.1 한글이 깨질 때

1. HTML에 `<meta charset="UTF-8">` 있는지 확인
2. `Ctrl+Shift+P` → `Change File Encoding` → **UTF-8**
3. settings.json에 `"files.encoding": "utf8"` 설정

### 10.2 Emmet이 안 될 때

1. 파일 확장자가 `.html`, `.css` 인지 확인
2. `"emmet.triggerExpansionOnTab": true` 설정
3. `Ctrl+Shift+P` → `Emmet: Expand Abbreviation` 수동 실행

### 10.3 CSS/JS가 적용 안 될 때

1. `<link>`, `<script>` 경로가 맞는지 확인 (`css/style.css`, `js/main.js`)
2. 대소문자·폴더명 오타 확인
3. 브라우저 `Ctrl+Shift+R` 로 강력 새로고침

### 10.4 Live Server 포트 충돌

settings.json에 포트 변경:

```json
{
  "liveServer.settings.port": 5501
}
```

### 10.5 Prettier가 동작하지 않을 때

1. Prettier 확장 설치 확인
2. `"editor.formatOnSave": true` 확인
3. `"editor.defaultFormatter": "esbenp.prettier-vscode"` 확인

---

## 11. 학습 체크리스트

- [ ] VSCode 설치 완료
- [ ] Chrome(또는 Edge) 설치 완료
- [ ] Live Server, Prettier, HTML CSS Support 설치
- [ ] `settings.json` 기본 설정 적용
- [ ] `Ctrl+S`, `Ctrl+P`, `Ctrl+Shift+P`, `Shift+Alt+F` 단축키 익힘
- [ ] Emmet으로 `!`, `div.container`, `ul>li*3` 사용 가능
- [ ] Live Server로 `index.html` 실행 성공
- [ ] 브라우저 F12로 Console 확인 가능
- [ ] Git 설치 및 `git --version` 확인 (선택)

---

## 참고 자료

| 자료 | 링크 |
|------|------|
| VSCode 공식 문서 | https://code.visualstudio.com/docs |
| Emmet 공식 문서 | https://emmet.io/cheat-sheet/ |
| MDN HTML 가이드 | https://developer.mozilla.org/ko/docs/Web/HTML |
| MDN CSS 가이드 | https://developer.mozilla.org/ko/docs/Web/CSS |
| MDN JavaScript 가이드 | https://developer.mozilla.org/ko/docs/Web/JavaScript |
| VSCode 단축키 PDF | VSCode 메뉴 → Help → Keyboard Shortcuts Reference |

---

## 관련 교안 (이 저장소)

- [html/html-0회차-개발환경-설정.md](./html/html-0회차-개발환경-설정.md) — HTML 수업용 상세 환경 설정
- [javascript/2장_자바스크립트_실행_환경.md](./javascript/2장_자바스크립트_실행_환경.md) — JS 실행 방법
- [VSCode-C-C++-설치가이드.md](./VSCode-C-C++-설치가이드.md) — C/C++ 환경 설정
