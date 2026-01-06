# Git 설치 및 초기 설정 가이드

## 학습 목표
- Git을 설치하고 기본 설정을 완료할 수 있다
- GitHub에 가입하고 저장소를 생성할 수 있다
- 로컬 저장소를 초기화하고 GitHub에 연결할 수 있다
- 기본적인 Git 워크플로우를 사용할 수 있다
- 다른 컴퓨터에서 저장소를 클론할 수 있다

---

## 1단계: Git 설치하기

### 1.1 Git이란?

**Git**은 VCS(Version Control System, 버전 관리 시스템)의 일종으로, 프로그램의 버전 관리를 위한 도구입니다.

**GitHub**는 Git으로 관리하는 프로젝트들을 온라인 공간에 공유해서 프로젝트 구성원들이 함께 소프트웨어를 만들어 갈 수 있도록 코드 공유 및 협업 서비스를 제공합니다.

```
Git (로컬 버전 관리)
    ↓
GitHub (온라인 저장소)
    ↓
협업 및 코드 공유
```

### 1.2 Git 다운로드

1. **크롬 브라우저에서 "깃 다운로드" 검색**
2. **공식 사이트 접속**: https://git-scm.com/downloads
3. **운영체제에 맞는 버전 다운로드** (Windows, Mac, Linux)

### 1.3 Git 설치

1. **다운로드한 Git 설치 파일 더블클릭**
2. **설치 마법사 실행**
3. **"다음(Next)" 버튼을 클릭하여 기본 설정으로 설치 진행**
4. **설치 완료 후 확인**

#### 설치 확인 방법

터미널 또는 Git Bash에서:
```bash
git --version
```

설치가 완료되었다면 버전 정보가 표시됩니다:
```
git version 2.40.0
```

---

## 2단계: 환경 설정

### 2.1 필요한 프로그램

- ✅ **VSCode** (Visual Studio Code) - 코드 에디터
- ✅ **Git** - 버전 관리 시스템

### 2.2 VSCode에서 작업 폴더 생성

1. **D 드라이브에 `test5` 폴더 생성**
   ```
   D:\test5
   ```

2. **VSCode 실행**

3. **File → Open Folder** 클릭

4. **`test5` 폴더 선택**

### 2.3 VSCode 터미널 열기

**방법 1: 단축키 사용**
- `Ctrl + `` (백틱 키)
  - 백틱(`)은 ESC 키 아래에 있는 키입니다

**방법 2: 메뉴 사용**
- **Terminal → New Terminal** 클릭

### 2.4 VSCode 기본 터미널을 Git Bash로 설정

1. **VSCode 터미널 열기** (`Ctrl + ``)

2. **터미널 우측 상단의 `+` 옆 화살표 클릭**

3. **"Select Default Profile" 선택**

4. **"Git Bash" 선택**

또는 설정에서:
1. **File → Preferences → Settings** (또는 `Ctrl + ,`)
2. **검색창에 "terminal.integrated.defaultProfile.windows" 입력**
3. **"Git Bash" 선택**

---

## 3단계: GitHub 가입 및 저장소 생성

### 3.1 GitHub 가입

1. **브라우저에서 "GitHub" 검색**

2. **GitHub 공식 사이트 접속**: https://github.com

3. **"Sign up" 클릭하여 계정 생성**
   - 사용자명, 이메일, 비밀번호 입력
   - 이메일 인증 완료

### 3.2 GitHub 저장소 생성

1. **GitHub 로그인 후 좌측 상단의 "New" 또는 "+" 버튼 클릭**

2. **"New repository" 선택**

3. **저장소 정보 입력**
   - **Repository name**: `test5` (원하는 이름)
   - **Description**: 설명 (선택사항)
   - **Public/Private**: 공개 여부 선택
   - **Initialize this repository with a README**: 체크하지 않기 (이미 로컬에 파일이 있으므로)

4. **"Create repository" 클릭**

5. **저장소 URL 복사**
   ```
   https://github.com/사용자명/test5.git
   ```
   예: `https://github.com/bhher/test5.git`

---

## 4단계: Git 초기 설정

### 4.1 사용자 정보 설정 (필수)

VSCode 터미널(Git Bash)에서 실행:

```bash
# 사용자 이름 설정
git config --global user.name "username"

# 사용자 이메일 설정
git config --global user.email "username@gmail.com"
```

**예시:**
```bash
git config --global user.name "bhher30"
git config --global user.email "bhher30@gmail.com"
```

#### 설정 확인

```bash
# 사용자 이름 확인
git config --global user.name

# 사용자 이메일 확인
git config --global user.email
```

### 4.2 기본 브랜치명 설정

```bash
# 기본 브랜치를 main으로 설정
git config --global init.defaultBranch main

# 설정 확인
git config --global init.defaultBranch
```

**설정 확인:**
```
main
```

---

## 5단계: 로컬 저장소 초기화 및 GitHub 연결

### 5.1 저장소 초기화

VSCode 터미널에서 `test5` 폴더로 이동한 후:

```bash
# README.md 파일 생성
echo "# test5" >> README.md

# Git 저장소 초기화
git init
```

**결과:**
```
Initialized empty Git repository in D:/test5/.git/
```

### 5.2 첫 번째 커밋

```bash
# README.md 파일을 스테이징 영역에 추가
git add README.md

# 첫 번째 커밋 생성
git commit -m "first commit"
```

### 5.3 브랜치 이름 확인 및 변경

```bash
# 현재 브랜치 확인
git branch

# 브랜치가 master로 되어 있다면 main으로 변경
git branch -m master main
```

또는:
```bash
git branch -M main
```

### 5.4 GitHub 원격 저장소 연결

```bash
# 원격 저장소 추가 (origin이라는 이름으로)
git remote add origin https://github.com/사용자명/test5.git
```

**예시:**
```bash
git remote add origin https://github.com/bhher/test5.git
```

#### 원격 저장소 확인

```bash
git remote -v
```

**결과:**
```
origin  https://github.com/bhher/test5.git (fetch)
origin  https://github.com/bhher/test5.git (push)
```

### 5.5 GitHub에 코드 푸시

```bash
# main 브랜치를 GitHub에 푸시
git push -u origin main
```

**`-u` 옵션의 의미:**
- `upstream`을 설정하여 이후 `git push`만으로도 푸시 가능

**결과:**
```
Enumerating objects: 3, done.
Counting objects: 100% (3/3), done.
Writing objects: 100% (3/3), done.
To https://github.com/bhher/test5.git
 * [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

### 5.6 전체 과정 요약

```bash
# 1. README.md 생성
echo "# test5" >> README.md

# 2. Git 초기화
git init

# 3. 파일 추가
git add README.md

# 4. 첫 커밋
git commit -m "first commit"

# 5. 브랜치 이름을 main으로 설정
git branch -M main

# 6. 원격 저장소 연결
git remote add origin https://github.com/사용자명/test5.git

# 7. GitHub에 푸시
git push -u origin main
```

---

## 6단계: 일상적인 작업 흐름

### 6.1 두 번째 커밋부터

첫 번째 푸시 이후부터는 다음 명령어만 반복하면 됩니다:

```bash
# 1. 변경된 파일들을 스테이징 영역에 추가
git add .

# 2. 커밋 생성
git commit -m "v2"

# 3. GitHub에 푸시
git push
```

**워크플로우 다이어그램:**
```
파일 수정
    ↓
git add .
    ↓
git commit -m "메시지"
    ↓
git push
    ↓
GitHub에 반영
```

### 6.2 커밋 메시지 작성 팁

**좋은 커밋 메시지 예시:**
```bash
git commit -m "feat: Add user login functionality"
git commit -m "fix: Resolve mobile menu display issue"
git commit -m "style: Update button colors"
git commit -m "docs: Update README"
```

**나쁜 커밋 메시지 예시:**
```bash
git commit -m "수정"
git commit -m "asdf"
git commit -m "."
```

---

## 7단계: 다른 컴퓨터에서 저장소 받기 (Clone)

### 7.1 상황 설명

집 컴퓨터에서 GitHub에 올린 프로젝트를 받아서 작업하고 싶을 때 사용합니다.

### 7.2 사전 준비

1. **Git 설치** (설치 방법은 1단계 참고)

2. **Git 사용자 정보 설정**
   ```bash
   git config --global user.name "bhher30"
   git config --global user.email "bhher30@gmail.com"
   ```

### 7.3 GitHub에서 저장소 URL 복사

1. **GitHub 저장소 페이지 접속**
2. **초록색 "Code" 버튼 클릭**
3. **HTTPS URL 복사**
   ```
   https://github.com/bhher/test10.git
   ```

### 7.4 저장소 클론 (복사)

원하는 위치에서 Git Bash 또는 터미널 실행:

```bash
# 저장소 클론
git clone https://github.com/bhher/test10.git
```

**결과:**
```
Cloning into 'test10'...
remote: Enumerating objects: 5, done.
remote: Counting objects: 100% (5/5), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 5 (delta 0), reused 0 (delta 0), pack-reused 0
Receiving objects: 100% (5/5), done.
```

### 7.5 VSCode에서 폴더 열기

1. **VSCode 실행**

2. **File → Open Folder** 클릭

3. **클론된 `test10` 폴더 선택**

4. **작업 시작!**

### 7.6 클론 후 작업 흐름

```bash
# 1. 최신 코드 가져오기 (다른 사람이 수정했을 수 있으므로)
git pull

# 2. 파일 수정

# 3. 변경사항 커밋
git add .
git commit -m "Update files"

# 4. GitHub에 푸시
git push
```

---

## 8단계: VSCode에서 숨김 파일 보기

### 8.1 문제 상황

VSCode에서 `.git` 폴더나 다른 숨김 파일들이 보이지 않는 경우가 있습니다.

### 8.2 해결 방법

1. **VSCode 설정 화면 진입**
   - **File → Preferences → Settings** (또는 `Ctrl + ,`)

2. **"Exclude" 검색**
   - 설정 검색창에 `exclude` 입력

3. **Files: Exclude 설정 수정**
   - **"Files: Exclude"** 섹션 찾기
   - 표시하고 싶은 파일/폴더의 패턴을 제거하거나 수정

**예시:**
```
기본 설정:
**/.git          ← 이 패턴을 제거하면 .git 폴더가 보임
**/.DS_Store     ← 이 패턴을 제거하면 .DS_Store 파일이 보임
```

4. **설정 저장 후 확인**
   - VSCode를 다시 열거나 파일 탐색기에서 확인

### 8.3 빠른 설정 방법

**settings.json 파일 직접 수정:**

1. **`Ctrl + Shift + P`** 눌러서 명령 팔레트 열기

2. **"Preferences: Open Settings (JSON)" 입력 후 선택**

3. **다음 내용 추가 또는 수정:**
   ```json
   {
     "files.exclude": {
       "**/.git": false,  // .git 폴더 표시
       "**/.DS_Store": false  // .DS_Store 파일 표시
     }
   }
   ```

---

## 9단계: 자주 사용하는 Git 명령어 정리

### 9.1 상태 확인

```bash
# 현재 상태 확인
git status

# 커밋 이력 확인
git log

# 간단한 커밋 이력
git log --oneline
```

### 9.2 파일 관리

```bash
# 모든 변경사항 추가
git add .

# 특정 파일만 추가
git add filename.txt

# 스테이징 취소
git reset HEAD filename.txt
```

### 9.3 커밋 관리

```bash
# 커밋 생성
git commit -m "메시지"

# 마지막 커밋 메시지 수정
git commit --amend -m "새로운 메시지"
```

### 9.4 원격 저장소

```bash
# 원격 저장소 확인
git remote -v

# 원격 저장소에서 가져오기
git pull

# 원격 저장소에 푸시
git push
```

---

## 10단계: 문제 해결

### 10.1 자주 발생하는 오류

#### 오류 1: "fatal: not a git repository"

**원인:** Git 저장소가 초기화되지 않음

**해결:**
```bash
git init
```

#### 오류 2: "Please tell me who you are"

**원인:** 사용자 정보가 설정되지 않음

**해결:**
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

#### 오류 3: "failed to push some refs"

**원인:** 원격 저장소에 로컬에 없는 커밋이 있음

**해결:**
```bash
# 먼저 원격 저장소의 변경사항 가져오기
git pull origin main

# 충돌 해결 후 다시 푸시
git push
```

#### 오류 4: "remote origin already exists"

**원인:** 이미 원격 저장소가 연결되어 있음

**해결:**
```bash
# 기존 원격 저장소 제거
git remote remove origin

# 새로 추가
git remote add origin https://github.com/사용자명/저장소명.git
```

---

## 11단계: 실습 체크리스트

### ✅ 초기 설정 체크리스트

- [ ] Git 설치 완료
- [ ] VSCode 설치 완료
- [ ] GitHub 계정 생성 완료
- [ ] Git 사용자 정보 설정 완료
- [ ] 기본 브랜치명 main으로 설정 완료

### ✅ 첫 번째 저장소 체크리스트

- [ ] 로컬 폴더 생성
- [ ] `git init` 실행
- [ ] README.md 파일 생성
- [ ] 첫 번째 커밋 생성
- [ ] GitHub에 저장소 생성
- [ ] 원격 저장소 연결
- [ ] 첫 번째 푸시 완료

### ✅ 일상 작업 체크리스트

- [ ] 파일 수정
- [ ] `git add .` 실행
- [ ] `git commit -m "메시지"` 실행
- [ ] `git push` 실행
- [ ] GitHub에서 확인

---

## 12단계: 다음 단계 학습

이 가이드를 완료했다면 다음 내용을 학습해보세요:

1. **브랜치 사용하기**
   - `git branch`
   - `git checkout -b feature-name`
   - `git merge`

2. **충돌 해결하기**
   - `git pull` 시 충돌 발생
   - 충돌 파일 수정
   - 병합 완료

3. **.gitignore 파일 사용하기**
   - 추적하지 않을 파일 지정
   - `node_modules/`, `.env` 등

4. **더 자세한 Git 가이드**
   - `Git_단계별_가이드.md` 파일 참고

---

## 요약

### 필수 명령어

```bash
# 초기 설정
git config --global user.name "사용자명"
git config --global user.email "이메일"
git config --global init.defaultBranch main

# 저장소 초기화
git init
git add .
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/사용자명/저장소명.git
git push -u origin main

# 일상 작업
git add .
git commit -m "메시지"
git push

# 다른 컴퓨터에서 받기
git clone https://github.com/사용자명/저장소명.git
```

### 워크플로우 다이어그램

```
[로컬 컴퓨터]
    │
    ├─ 파일 생성/수정
    │
    ├─ git add .
    │  (스테이징)
    │
    ├─ git commit -m "메시지"
    │  (로컬 저장소에 저장)
    │
    └─ git push
       (GitHub에 업로드)
          │
          ▼
    [GitHub 저장소]
          │
          ▼
    [다른 컴퓨터]
    git clone
    (저장소 복사)
```

---

## 마무리

이 가이드를 따라하시면 Git과 GitHub의 기본 사용법을 익힐 수 있습니다.

**핵심 포인트:**
- ✅ Git은 버전 관리 도구
- ✅ GitHub는 온라인 저장소
- ✅ `add` → `commit` → `push` 순서 기억하기
- ✅ 다른 컴퓨터에서는 `git clone` 사용

꾸준히 사용하다 보면 자연스럽게 익숙해집니다! 🚀

---

## 참고 자료

- **Git 공식 사이트**: https://git-scm.com
- **GitHub 공식 사이트**: https://github.com
- **Git 다운로드**: https://git-scm.com/downloads
- **VSCode 다운로드**: https://code.visualstudio.com


