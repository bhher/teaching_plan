# 0회차 — 개발 환경 설정 가이드

## 학습 목표
- VSCode를 설치하고 기본 설정을 완료할 수 있다
- HTML 개발에 필요한 확장 프로그램을 설치할 수 있다
- VSCode의 유용한 단축키를 활용할 수 있다
- Emmet을 사용하여 빠르게 HTML/CSS 코드를 작성할 수 있다

---

## 1. VSCode 설치

### 1.1 VSCode란?

Visual Studio Code (VSCode)는 Microsoft에서 개발한 무료 코드 에디터입니다.

**주요 특징:**
- 무료 오픈소스
- 크로스 플랫폼 (Windows, macOS, Linux)
- 확장 프로그램 지원
- Git 통합
- 디버깅 기능
- IntelliSense (자동 완성)

### 1.2 설치 방법

#### Windows

1. **다운로드**
   - 공식 웹사이트: https://code.visualstudio.com/
   - "Download for Windows" 클릭
   - 시스템에 맞는 버전 선택 (64-bit 권장)

2. **설치**
   - 다운로드한 설치 파일 실행
   - 설치 옵션 선택:
     - ✅ "Add to PATH" 체크 (명령줄에서 사용 가능)
     - ✅ "Create desktop icon" 체크 (바로가기 생성)
     - ✅ "Register Code as an editor for supported file types" 체크
   - "Install" 클릭

3. **실행**
   - 설치 완료 후 VSCode 실행
   - 첫 실행 시 테마 선택 (나중에 변경 가능)

#### macOS

1. **다운로드**
   - 공식 웹사이트: https://code.visualstudio.com/
   - "Download for Mac" 클릭

2. **설치**
   - 다운로드한 `.zip` 파일 압축 해제
   - `Visual Studio Code.app`을 Applications 폴더로 이동

3. **실행**
   - Applications 폴더에서 VSCode 실행
   - 보안 경고가 나오면 "시스템 환경설정 > 보안 및 개인 정보 보호"에서 허용

#### Linux

1. **다운로드**
   - 공식 웹사이트: https://code.visualstudio.com/
   - "Download for Linux" 클릭
   - 배포판에 맞는 패키지 선택 (.deb 또는 .rpm)

2. **설치**
   ```bash
   # Debian/Ubuntu (.deb)
   sudo dpkg -i code_*.deb
   sudo apt-get install -f
   
   # Red Hat/CentOS (.rpm)
   sudo rpm -ivh code-*.rpm
   ```

---

## 2. HTML 개발에 필요한 확장 프로그램

### 2.1 필수 확장 프로그램

#### 1. Live Server
**설명:** HTML 파일을 브라우저에서 실시간으로 미리보기할 수 있게 해주는 확장 프로그램

**설치 방법:**
1. VSCode 왼쪽 사이드바에서 확장 프로그램 아이콘 클릭 (또는 `Ctrl+Shift+X`)
2. 검색창에 "Live Server" 입력
3. "Live Server" (Ritwick Dey) 선택
4. "Install" 클릭

**사용 방법:**
- HTML 파일을 열고 우클릭 → "Open with Live Server"
- 또는 하단 상태바의 "Go Live" 클릭
- 자동으로 브라우저가 열리고 파일 변경 시 자동 새로고침

#### 2. Prettier - Code formatter
**설명:** 코드를 자동으로 포맷팅해주는 확장 프로그램

**설치 방법:**
1. 확장 프로그램에서 "Prettier - Code formatter" 검색
2. "Prettier - Code formatter" (Prettier) 설치

**사용 방법:**
- `Shift+Alt+F` (Windows/Linux) 또는 `Shift+Option+F` (Mac): 전체 파일 포맷팅
- 저장 시 자동 포맷팅 설정 가능

#### 3. HTML CSS Support
**설명:** HTML에서 CSS 클래스와 ID 자동 완성 지원

**설치 방법:**
1. 확장 프로그램에서 "HTML CSS Support" 검색
2. "HTML CSS Support" (ecmel) 설치

### 2.2 추천 확장 프로그램

#### 4. Auto Rename Tag
**설명:** HTML 태그의 여는 태그를 수정하면 닫는 태그도 자동으로 변경

**설치 방법:**
1. 확장 프로그램에서 "Auto Rename Tag" 검색
2. "Auto Rename Tag" (Jun Han) 설치

#### 5. Color Highlight
**설명:** CSS 색상 코드를 실제 색상으로 표시

**설치 방법:**
1. 확장 프로그램에서 "Color Highlight" 검색
2. "Color Highlight" (Sergii Naumov) 설치

#### 6. Bracket Pair Colorizer 2 (또는 기본 기능 사용)
**설명:** 괄호를 색상으로 구분하여 표시 (VSCode 최신 버전에는 기본 기능 포함)

#### 7. Path Intellisense
**설명:** 파일 경로 자동 완성

**설치 방법:**
1. 확장 프로그램에서 "Path Intellisense" 검색
2. "Path Intellisense" (Christian Kohler) 설치

#### 8. GitLens
**설명:** Git 기능 강화 (커밋 히스토리, 작성자 정보 등)

**설치 방법:**
1. 확장 프로그램에서 "GitLens" 검색
2. "GitLens — Git supercharged" (GitKraken) 설치

#### 9. Korean Language Pack
**설명:** VSCode를 한국어로 사용

**설치 방법:**
1. 확장 프로그램에서 "Korean Language Pack" 검색
2. "Korean Language Pack for Visual Studio Code" (Microsoft) 설치
3. `Ctrl+Shift+P` → "Configure Display Language" → "ko" 선택

---

## 3. VSCode 기본 설정

### 3.1 설정 파일 열기

**방법 1: 메뉴**
- `File` → `Preferences` → `Settings` (Windows/Linux)
- `Code` → `Preferences` → `Settings` (macOS)

**방법 2: 단축키**
- `Ctrl+,` (Windows/Linux)
- `Cmd+,` (macOS)

**방법 3: 명령 팔레트**
- `Ctrl+Shift+P` (Windows/Linux) 또는 `Cmd+Shift+P` (macOS)
- "Preferences: Open Settings" 입력

### 3.2 추천 설정

#### 자동 저장
```json
{
  "files.autoSave": "afterDelay",
  "files.autoSaveDelay": 1000
}
```

#### 저장 시 포맷팅 (Prettier)
```json
{
  "editor.formatOnSave": true,
  "editor.defaultFormatter": "esbenp.prettier-vscode"
}
```

#### 들여쓰기
```json
{
  "editor.tabSize": 2,
  "editor.insertSpaces": true,
  "editor.detectIndentation": false
}
```

#### 폰트 설정
```json
{
  "editor.fontFamily": "'Fira Code', 'Consolas', 'Courier New', monospace",
  "editor.fontLigatures": true,
  "editor.fontSize": 14
}
```

#### HTML/CSS 설정
```json
{
  "html.format.indentInnerHtml": true,
  "html.format.wrapLineLength": 120,
  "css.validate": true,
  "css.lint.unknownProperties": "warning"
}
```

### 3.3 설정 파일 직접 편집

**settings.json 파일 열기:**
1. `Ctrl+Shift+P` (또는 `Cmd+Shift+P`)
2. "Preferences: Open User Settings (JSON)" 입력

**전체 설정 예시:**
```json
{
  // 자동 저장
  "files.autoSave": "afterDelay",
  "files.autoSaveDelay": 1000,
  
  // 포맷팅
  "editor.formatOnSave": true,
  "editor.defaultFormatter": "esbenp.prettier-vscode",
  
  // 들여쓰기
  "editor.tabSize": 2,
  "editor.insertSpaces": true,
  "editor.detectIndentation": false,
  
  // 폰트
  "editor.fontSize": 14,
  "editor.fontFamily": "'Fira Code', 'Consolas', monospace",
  "editor.fontLigatures": true,
  
  // HTML/CSS
  "html.format.indentInnerHtml": true,
  "html.format.wrapLineLength": 120,
  "css.validate": true,
  
  // 파일 인코딩
  "files.encoding": "utf8",
  
  // 줄 끝 문자
  "files.eol": "\n"
}
```

---

## 4. VSCode 유용한 단축키

### 4.1 기본 편집 단축키

| 기능 | Windows/Linux | macOS |
|------|---------------|-------|
| 파일 열기 | `Ctrl+O` | `Cmd+O` |
| 새 파일 | `Ctrl+N` | `Cmd+N` |
| 파일 저장 | `Ctrl+S` | `Cmd+S` |
| 모두 저장 | `Ctrl+K S` | `Cmd+K S` |
| 파일 닫기 | `Ctrl+W` | `Cmd+W` |
| 되돌리기 | `Ctrl+Z` | `Cmd+Z` |
| 다시 실행 | `Ctrl+Y` | `Cmd+Shift+Z` |
| 잘라내기 | `Ctrl+X` | `Cmd+X` |
| 복사 | `Ctrl+C` | `Cmd+C` |
| 붙여넣기 | `Ctrl+V` | `Cmd+V` |
| 전체 선택 | `Ctrl+A` | `Cmd+A` |
| 찾기 | `Ctrl+F` | `Cmd+F` |
| 바꾸기 | `Ctrl+H` | `Cmd+Option+F` |
| 파일에서 찾기 | `Ctrl+Shift+F` | `Cmd+Shift+F` |

### 4.2 코드 편집 단축키

| 기능 | Windows/Linux | macOS |
|------|---------------|-------|
| 줄 복사 | `Shift+Alt+↓` / `Shift+Alt+↑` | `Shift+Option+↓` / `Shift+Option+↑` |
| 줄 이동 | `Alt+↓` / `Alt+↑` | `Option+↓` / `Option+↑` |
| 줄 삭제 | `Ctrl+Shift+K` | `Cmd+Shift+K` |
| 주석 토글 | `Ctrl+/` | `Cmd+/` |
| 블록 주석 | `Shift+Alt+A` | `Shift+Option+A` |
| 들여쓰기 | `Ctrl+]` | `Cmd+]` |
| 내어쓰기 | `Ctrl+[` | `Cmd+[` |
| 코드 포맷팅 | `Shift+Alt+F` | `Shift+Option+F` |
| 선택 영역 포맷팅 | `Ctrl+K Ctrl+F` | `Cmd+K Cmd+F` |

### 4.3 탐색 단축키

| 기능 | Windows/Linux | macOS |
|------|---------------|-------|
| 명령 팔레트 | `Ctrl+Shift+P` | `Cmd+Shift+P` |
| 파일 검색 | `Ctrl+P` | `Cmd+P` |
| 심볼 검색 | `Ctrl+Shift+O` | `Cmd+Shift+O` |
| 정의로 이동 | `F12` | `F12` |
| 참조 찾기 | `Shift+F12` | `Shift+F12` |
| 뒤로 가기 | `Ctrl+Alt+-` | `Ctrl+-` |
| 앞으로 가기 | `Ctrl+Shift+-` | `Ctrl+Shift+-` |
| 줄 번호로 이동 | `Ctrl+G` | `Cmd+G` |

### 4.4 뷰/패널 단축키

| 기능 | Windows/Linux | macOS |
|------|---------------|-------|
| 사이드바 토글 | `Ctrl+B` | `Cmd+B` |
| 터미널 열기 | `` Ctrl+` `` | `` Cmd+` `` |
| 문제 패널 | `Ctrl+Shift+M` | `Cmd+Shift+M` |
| 출력 패널 | `Ctrl+Shift+U` | `Cmd+Shift+U` |
| 확장 프로그램 | `Ctrl+Shift+X` | `Cmd+Shift+X` |
| 탐색기 | `Ctrl+Shift+E` | `Cmd+Shift+E` |
| 검색 | `Ctrl+Shift+F` | `Cmd+Shift+F` |
| 소스 제어 | `Ctrl+Shift+G` | `Cmd+Shift+G` |

### 4.5 멀티 커서 편집

| 기능 | Windows/Linux | macOS |
|------|---------------|-------|
| 커서 추가 | `Alt+Click` | `Option+Click` |
| 위/아래 커서 | `Ctrl+Alt+↑` / `Ctrl+Alt+↓` | `Option+Shift+↑` / `Option+Shift+↓` |
| 같은 단어 모두 선택 | `Ctrl+Shift+L` | `Cmd+Shift+L` |
| 다음 일치 항목 선택 | `Ctrl+D` | `Cmd+D` |
| 모든 일치 항목 선택 | `Ctrl+Shift+L` | `Cmd+Shift+L` |

### 4.6 HTML/CSS 특화 단축키

| 기능 | Windows/Linux | macOS |
|------|---------------|-------|
| 태그 감싸기 | `Alt+Shift+W` | `Option+Shift+W` |
| 태그 이름 변경 | `F2` | `F2` |
| 태그로 이동 | `Ctrl+Shift+O` | `Cmd+Shift+O` |
| 태그 닫기 | `Ctrl+.` | `Cmd+.` |

---

## 5. Emmet 사용법

### 5.1 Emmet이란?

Emmet은 HTML과 CSS를 빠르게 작성할 수 있게 해주는 도구입니다. VSCode에는 기본적으로 포함되어 있습니다.

### 5.2 기본 사용법

**활성화:**
- HTML/CSS 파일에서 자동으로 활성화
- `Tab` 키로 확장

### 5.3 HTML Emmet

#### 태그 생성
```
div → <div></div>
p → <p></p>
h1 → <h1></h1>
```

#### 클래스와 ID
```
div.container → <div class="container"></div>
div#header → <div id="header"></div>
div.container#header → <div class="container" id="header"></div>
```

#### 중첩 구조
```
div>p → <div><p></p></div>
ul>li → <ul><li></li></ul>
div>p>span → <div><p><span></span></p></div>
```

#### 형제 요소
```
div+p → <div></div><p></p>
h1+h2 → <h1></h1><h2></h2>
```

#### 곱하기 (반복)
```
ul>li*3 → 
<ul>
  <li></li>
  <li></li>
  <li></li>
</ul>
```

#### 그룹화
```
div>(header>nav)+(main>section)+footer → 
<div>
  <header><nav></nav></header>
  <main><section></section></main>
  <footer></footer>
</div>
```

#### 속성 추가
```
a[href="#"] → <a href="#"></a>
img[src="photo.jpg" alt="사진"] → <img src="photo.jpg" alt="사진">
input[type="text" placeholder="입력"] → <input type="text" placeholder="입력">
```

#### 텍스트 추가
```
p{안녕하세요} → <p>안녕하세요</p>
a[href="#"]{링크} → <a href="#">링크</a>
```

#### 번호 증가 ($)
```
ul>li.item$*3 → 
<ul>
  <li class="item1"></li>
  <li class="item2"></li>
  <li class="item3"></li>
</ul>
```

#### 번호 증가 (0으로 채우기)
```
ul>li.item$$*3 → 
<ul>
  <li class="item01"></li>
  <li class="item02"></li>
  <li class="item03"></li>
</ul>
```

#### 역순 번호 증가
```
ul>li.item$@-*3 → 
<ul>
  <li class="item3"></li>
  <li class="item2"></li>
  <li class="item1"></li>
</ul>
```

#### 시작 번호 지정
```
ul>li.item$@3*3 → 
<ul>
  <li class="item3"></li>
  <li class="item4"></li>
  <li class="item5"></li>
</ul>
```

### 5.4 실전 예제

#### HTML 기본 구조
```
! → HTML5 기본 구조 생성
```

#### 네비게이션 메뉴
```
nav>ul>li*5>a[href="#"]{메뉴$} → 
<nav>
  <ul>
    <li><a href="#">메뉴1</a></li>
    <li><a href="#">메뉴2</a></li>
    <li><a href="#">메뉴3</a></li>
    <li><a href="#">메뉴4</a></li>
    <li><a href="#">메뉴5</a></li>
  </ul>
</nav>
```

#### 폼 요소
```
form>label[for="name"]{이름}+input[type="text" id="name"]+label[for="email"]{이메일}+input[type="email" id="email"]+button[type="submit"]{제출} → 
<form>
  <label for="name">이름</label>
  <input type="text" id="name">
  <label for="email">이메일</label>
  <input type="email" id="email">
  <button type="submit">제출</button>
</form>
```

#### 카드 레이아웃
```
div.card*3>img[src="photo$.jpg"]+h3{제목$}+p{내용$} → 
<div class="card">
  <img src="photo1.jpg">
  <h3>제목1</h3>
  <p>내용1</p>
</div>
<div class="card">
  <img src="photo2.jpg">
  <h3>제목2</h3>
  <p>내용2</p>
</div>
<div class="card">
  <img src="photo3.jpg">
  <h3>제목3</h3>
  <p>내용3</p>
</div>
```

### 5.5 CSS Emmet

#### 속성 단축
```
m10 → margin: 10px;
p20 → padding: 20px;
w100 → width: 100px;
h50 → height: 50px;
```

#### 방향 지정
```
mt10 → margin-top: 10px;
mr20 → margin-right: 20px;
mb30 → margin-bottom: 30px;
ml40 → margin-left: 40px;
```

#### 여러 값
```
m10-20 → margin: 10px 20px;
p10-20-30 → padding: 10px 20px 30px;
m10-20-30-40 → margin: 10px 20px 30px 40px;
```

#### 단위 지정
```
w100p → width: 100%;
h50vh → height: 50vh;
m10rem → margin: 10rem;
```

#### 색상
```
c#f00 → color: #f00;
bg#fff → background: #fff;
bdc#333 → border-color: #333;
```

#### 폰트
```
fz16 → font-size: 16px;
fw700 → font-weight: 700;
ffArial → font-family: Arial;
```

#### 위치
```
posa → position: absolute;
posr → position: relative;
posf → position: fixed;
```

#### Flexbox
```
df → display: flex;
fdc → flex-direction: column;
jcc → justify-content: center;
aic → align-items: center;
```

### 5.6 Emmet 팁

1. **자동 완성**: Emmet 약어를 입력하고 `Tab` 키를 누르면 확장됩니다.
2. **여러 줄**: `Ctrl+Shift+P` → "Emmet: Expand Abbreviation"로 수동 확장 가능
3. **설정**: `Ctrl+,` → "emmet" 검색하여 설정 변경 가능

---

## 6. 추가 도구 및 프로그램

### 6.1 브라우저

#### Chrome / Edge
- **개발자 도구**: `F12` 또는 `Ctrl+Shift+I`
- **요소 검사**: `Ctrl+Shift+C`
- **반응형 디자인 모드**: `Ctrl+Shift+M`

#### Firefox
- **개발자 도구**: `F12`
- **요소 검사**: `Ctrl+Shift+C`

### 6.2 Git (선택사항)

**설치:**
- Windows: https://git-scm.com/download/win
- macOS: `brew install git` 또는 Xcode Command Line Tools
- Linux: `sudo apt-get install git` (Ubuntu/Debian)

**VSCode에서 사용:**
- GitLens 확장 프로그램 설치
- 소스 제어 패널 (`Ctrl+Shift+G`)에서 Git 기능 사용

### 6.3 Node.js (선택사항)

**설치:**
- 공식 웹사이트: https://nodejs.org/
- LTS 버전 권장

**용도:**
- 패키지 관리 (npm)
- 빌드 도구
- 서버 개발

---

## 7. 첫 프로젝트 만들기

### 7.1 프로젝트 폴더 생성

1. **폴더 만들기**
   - 원하는 위치에 새 폴더 생성 (예: `my-first-website`)

2. **VSCode에서 열기**
   - `File` → `Open Folder` (또는 `Ctrl+K Ctrl+O`)
   - 생성한 폴더 선택

### 7.2 첫 HTML 파일 만들기

1. **파일 생성**
   - `Ctrl+N` (새 파일)
   - `Ctrl+S` (저장)
   - 파일명: `index.html`

2. **기본 구조 작성**
   ```
   ! + Tab
   ```
   또는 직접 입력:
   ```html
   <!DOCTYPE html>
   <html lang="ko">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>My First Website</title>
   </head>
   <body>
       <h1>안녕하세요!</h1>
   </body>
   </html>
   ```

3. **Live Server로 실행**
   - 파일 우클릭 → "Open with Live Server"
   - 또는 하단 상태바의 "Go Live" 클릭

---

## 8. 문제 해결

### 8.1 Emmet이 작동하지 않을 때

**해결 방법:**
1. 파일 확장자가 `.html` 또는 `.css`인지 확인
2. `Ctrl+,` → "emmet" 검색 → "Emmet: Trigger Expansion On Tab" 체크
3. `Ctrl+Shift+P` → "Emmet: Expand Abbreviation"로 수동 확장

### 8.2 Live Server가 작동하지 않을 때

**해결 방법:**
1. 확장 프로그램이 설치되어 있는지 확인
2. HTML 파일이 열려있는지 확인
3. 포트가 사용 중일 수 있음 → 설정에서 포트 변경

### 8.3 한글이 깨질 때

**해결 방법:**
1. 파일 인코딩 확인: `Ctrl+Shift+P` → "Change File Encoding" → "UTF-8"
2. HTML 파일에 `<meta charset="UTF-8">` 포함 확인
3. VSCode 설정에서 `"files.encoding": "utf8"` 확인

---

## 9. 학습 체크리스트

- [ ] VSCode 설치 완료
- [ ] 필수 확장 프로그램 설치 (Live Server, Prettier)
- [ ] 기본 설정 완료 (자동 저장, 포맷팅)
- [ ] 주요 단축키 숙지
- [ ] Emmet 기본 사용법 익히기
- [ ] 첫 HTML 파일 생성 및 실행

---

## 10. 추가 자료

- **VSCode 공식 문서**: https://code.visualstudio.com/docs
- **Emmet 공식 문서**: https://emmet.io/
- **VSCode 단축키 참조**: `Ctrl+K Ctrl+S` (또는 `Help` → `Keyboard Shortcuts Reference`)
- **확장 프로그램 마켓플레이스**: https://marketplace.visualstudio.com/

---

## 다음 단계

환경 설정이 완료되었습니다! 이제 HTML 기초 학습을 시작할 수 있습니다.

1. **1회차**: HTML 기초 구조 및 시맨틱 마크업
2. **2회차**: 문서 구성 요소와 콘텐츠 마크업 심화
3. **3회차**: CSS 기초와 적용방법

행운을 빕니다! 🚀


