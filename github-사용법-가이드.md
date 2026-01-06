# GitHub 사용법 가이드

## 목차
1. [GitHub란?](#github란)
2. [시작하기](#시작하기)
3. [Git 기본 개념](#git-기본-개념)
4. [기본 사용법](#기본-사용법)
5. [원격 저장소 관리](#원격-저장소-관리)
6. [브랜치(Branch) 관리](#브랜치branch-관리)
7. [협업 워크플로우](#협업-워크플로우)
8. [Pull Request](#pull-request)
9. [GitHub 기능](#github-기능)
10. [일반적인 시나리오](#일반적인-시나리오)
11. [자주 묻는 질문](#자주-묻는-질문)

---

## GitHub란?

**GitHub**는 Git 기반의 코드 저장 및 협업 플랫폼입니다.

### 주요 기능
- 📦 **코드 저장소**: 프로젝트 코드를 클라우드에 저장
- 👥 **협업**: 여러 개발자가 함께 작업
- 🔄 **버전 관리**: Git을 통한 코드 변경 이력 추적
- 📊 **이슈 관리**: 버그 리포트, 기능 요청 등
- 🔀 **Pull Request**: 코드 리뷰 및 병합
- 📝 **Wiki & README**: 프로젝트 문서화
- 🌐 **GitHub Pages**: 무료 웹 호스팅
- ⚙️ **GitHub Actions**: CI/CD 자동화

### Git vs GitHub
- **Git**: 로컬에서 버전 관리를 하는 도구
- **GitHub**: Git 저장소를 클라우드에 호스팅하는 서비스

---

## 시작하기

### 1. GitHub 계정 생성
1. [github.com](https://github.com) 접속
2. "Sign up" 클릭
3. 이메일, 비밀번호, 사용자명 입력
4. 이메일 인증 완료

### 2. Git 설치 확인
터미널/명령 프롬프트에서 확인:
```bash
git --version
```

#### Git 설치가 안 되어 있다면:
- **Windows**: [git-scm.com](https://git-scm.com)에서 다운로드
- **Mac**: `brew install git` 또는 Xcode Command Line Tools 설치
- **Linux**: `sudo apt-get install git` (Ubuntu/Debian)

### 3. Git 초기 설정
처음 한 번만 설정:
```bash
# 사용자 이름 설정
git config --global user.name "Your Name"

# 이메일 설정 (GitHub 계정 이메일과 동일하게)
git config --global user.email "your.email@example.com"

# 기본 브랜치 이름을 main으로 설정
git config --global init.defaultBranch main

# 설정 확인
git config --list
```

### 4. SSH 키 설정 (선택사항, 권장)
SSH 키를 사용하면 매번 비밀번호를 입력하지 않아도 됩니다.

#### SSH 키 생성
```bash
ssh-keygen -t ed25519 -C "your.email@example.com"
# Enter를 눌러 기본 경로 사용, 비밀번호 설정 (선택사항)
```

#### SSH 키 복사
```bash
# Windows (Git Bash)
cat ~/.ssh/id_ed25519.pub

# Mac/Linux
cat ~/.ssh/id_ed25519.pub
```

#### GitHub에 SSH 키 등록
1. GitHub → Settings → SSH and GPG keys
2. "New SSH key" 클릭
3. Title 입력 (예: "My Laptop")
4. Key에 복사한 SSH 키 붙여넣기
5. "Add SSH key" 클릭

#### SSH 연결 테스트
```bash
ssh -T git@github.com
# "Hi [username]! You've successfully authenticated..." 메시지가 나오면 성공
```

---

## Git 기본 개념

### 저장소(Repository)
- **로컬 저장소**: 내 컴퓨터에 있는 Git 프로젝트
- **원격 저장소**: GitHub 등 클라우드에 있는 Git 프로젝트

### 주요 개념

#### 1. Working Directory (작업 디렉토리)
- 실제 파일이 있는 디렉토리
- 코드를 작성하고 수정하는 곳

#### 2. Staging Area (스테이징 영역)
- 커밋하기 전에 변경사항을 준비하는 영역
- `git add`로 파일을 추가

#### 3. Repository (저장소)
- 커밋된 변경사항이 저장되는 곳
- `.git` 폴더에 저장

### Git 상태

```
Working Directory → (git add) → Staging Area → (git commit) → Repository
     (수정)                              (준비)                    (저장)
```

### 주요 용어
- **Commit**: 변경사항을 저장소에 저장하는 것
- **Branch**: 독립적인 작업 공간
- **Merge**: 브랜치를 병합하는 것
- **Push**: 로컬 저장소의 변경사항을 원격 저장소에 업로드
- **Pull**: 원격 저장소의 변경사항을 로컬 저장소로 다운로드
- **Clone**: 원격 저장소를 로컬로 복사

---

## 기본 사용법

### 1. 새 저장소 시작하기

#### 로컬에서 시작
```bash
# 새 폴더 생성
mkdir my-project
cd my-project

# Git 초기화
git init

# 파일 생성 및 추가
echo "# My Project" > README.md
git add README.md
git commit -m "Initial commit"
```

#### GitHub에서 시작
1. GitHub → "New repository" 클릭
2. Repository name 입력
3. Public 또는 Private 선택
4. "Add a README file" 선택 (선택사항)
5. "Create repository" 클릭

### 2. 기존 저장소 클론하기
```bash
# HTTPS 사용
git clone https://github.com/username/repository-name.git

# SSH 사용 (SSH 키 설정 후)
git clone git@github.com:username/repository-name.git

# 특정 폴더명으로 클론
git clone https://github.com/username/repository-name.git my-folder
```

### 3. 기본 Git 명령어

#### 상태 확인
```bash
# 현재 상태 확인
git status

# 변경사항 상세 보기
git diff

# 커밋 히스토리 보기
git log

# 간단한 히스토리 보기
git log --oneline

# 그래프로 히스토리 보기
git log --oneline --graph
```

#### 파일 추가 및 커밋
```bash
# 특정 파일 추가
git add filename.txt

# 모든 변경사항 추가
git add .

# 특정 확장자 파일만 추가
git add *.js

# 커밋 (변경사항 저장)
git commit -m "커밋 메시지"

# 메시지와 함께 바로 커밋 (파일이 이미 추가된 경우)
git commit -am "커밋 메시지"
```

#### 커밋 메시지 작성 가이드
```
타입(범위): 간단한 제목 (50자 이내)

상세 설명 (선택사항, 72자마다 줄바꿈)
- 무엇을 변경했는지
- 왜 변경했는지
- 어떻게 변경했는지

예시:
feat: 사용자 로그인 기능 추가

- 이메일과 비밀번호로 로그인 가능
- 세션 관리 기능 포함
```

**커밋 타입:**
- `feat`: 새로운 기능
- `fix`: 버그 수정
- `docs`: 문서 수정
- `style`: 코드 포맷팅 (로직 변경 없음)
- `refactor`: 코드 리팩토링
- `test`: 테스트 추가/수정
- `chore`: 빌드 프로세스, 도구 설정 등

### 4. 변경사항 되돌리기

#### 파일 수정 취소 (아직 add 안 한 경우)
```bash
# 특정 파일만 되돌리기
git checkout -- filename.txt

# 또는 (최신 Git 버전)
git restore filename.txt

# 모든 파일 되돌리기
git restore .
```

#### Staging Area에서 제거 (add는 했지만 commit 안 한 경우)
```bash
# 특정 파일 제거
git reset HEAD filename.txt

# 또는
git restore --staged filename.txt

# 모든 파일 제거
git reset HEAD
```

#### 커밋 되돌리기
```bash
# 마지막 커밋 취소 (변경사항은 유지)
git reset --soft HEAD~1

# 마지막 커밋 취소 (변경사항도 제거)
git reset --hard HEAD~1

# 특정 커밋으로 되돌리기
git reset --hard <commit-hash>
```

> ⚠️ **주의**: `--hard` 옵션은 변경사항을 영구적으로 삭제합니다. 신중하게 사용하세요!

---

## 원격 저장소 관리

### 원격 저장소 추가
```bash
# 원격 저장소 확인
git remote -v

# 원격 저장소 추가
git remote add origin https://github.com/username/repository-name.git

# SSH로 추가
git remote add origin git@github.com:username/repository-name.git
```

### Push (업로드)
```bash
# 처음 push할 때
git push -u origin main

# 그 이후
git push

# 다른 브랜치 push
git push origin branch-name

# 모든 브랜치 push
git push --all origin
```

### Pull (다운로드)
```bash
# 원격 저장소의 변경사항 가져오기 및 병합
git pull

# 특정 브랜치 pull
git pull origin branch-name

# fetch만 하고 병합은 하지 않기
git fetch
git merge origin/main
```

### Fetch vs Pull
- **Fetch**: 원격 저장소의 변경사항을 가져오기만 하고 병합하지 않음
- **Pull**: Fetch + Merge (가져온 후 자동으로 병합)

### 원격 저장소 정보 수정
```bash
# 원격 저장소 URL 변경
git remote set-url origin new-url

# 원격 저장소 삭제
git remote remove origin

# 원격 저장소 이름 변경
git remote rename old-name new-name
```

---

## 브랜치(Branch) 관리

브랜치는 독립적인 작업 공간입니다. 여러 기능을 동시에 개발할 때 유용합니다.

### 브랜치 기본 명령어

#### 브랜치 목록 보기
```bash
# 로컬 브랜치
git branch

# 원격 브랜치 포함
git branch -a

# 원격 브랜치만
git branch -r
```

#### 브랜치 생성 및 전환
```bash
# 새 브랜치 생성
git branch branch-name

# 브랜치 생성하고 바로 전환
git checkout -b branch-name

# 또는 (최신 Git 버전)
git switch -c branch-name

# 브랜치 전환
git checkout branch-name
# 또는
git switch branch-name

# 이전 브랜치로 돌아가기
git checkout -
```

#### 브랜치 병합 (Merge)
```bash
# main 브랜치로 전환
git checkout main

# feature 브랜치를 main에 병합
git merge feature-branch

# 병합 커밋 메시지 작성
git merge feature-branch -m "Merge feature-branch into main"
```

#### 브랜치 삭제
```bash
# 로컬 브랜치 삭제
git branch -d branch-name

# 강제 삭제 (병합 안 된 브랜치도 삭제)
git branch -D branch-name

# 원격 브랜치 삭제
git push origin --delete branch-name
```

### 브랜치 전략

#### 1. Main/Master 브랜치
- 프로덕션용 안정적인 코드
- 항상 배포 가능한 상태 유지

#### 2. Feature 브랜치
- 새로운 기능 개발
- 예: `feature/user-login`, `feature/payment`

#### 3. Bugfix 브랜치
- 버그 수정
- 예: `bugfix/login-error`

#### 4. Hotfix 브랜치
- 긴급 버그 수정
- main에서 직접 분기

#### 5. Develop 브랜치 (선택사항)
- 개발 브랜치
- 여러 feature 브랜치를 통합하는 곳

### 브랜치 워크플로우 예시
```bash
# 1. main에서 새 기능 브랜치 생성
git checkout main
git pull origin main
git checkout -b feature/new-feature

# 2. 기능 개발 및 커밋
# ... 코드 작성 ...
git add .
git commit -m "feat: 새로운 기능 추가"

# 3. 원격 저장소에 push
git push -u origin feature/new-feature

# 4. Pull Request 생성 (GitHub에서)

# 5. 리뷰 후 병합되면 로컬 정리
git checkout main
git pull origin main
git branch -d feature/new-feature
```

---

## 협업 워크플로우

### 1. Fork & Pull Request 워크플로우
오픈소스 프로젝트에 기여할 때 사용:

1. **Fork**: 원본 저장소를 내 계정으로 복사
2. **Clone**: Fork한 저장소를 로컬로 가져오기
3. **Branch**: 새 기능 브랜치 생성
4. **작업**: 코드 작성 및 커밋
5. **Push**: Fork한 저장소에 push
6. **Pull Request**: 원본 저장소에 PR 생성

### 2. Shared Repository 워크플로우
팀 프로젝트에서 사용:

1. **Clone**: 팀 저장소를 로컬로 가져오기
2. **Branch**: 개인 브랜치 또는 feature 브랜치 생성
3. **작업**: 코드 작성 및 커밋
4. **Push**: 원격 저장소에 push
5. **Pull Request**: main 브랜치에 PR 생성
6. **Review**: 코드 리뷰 후 병합

### 3. 충돌(Conflict) 해결

#### 충돌 발생 시
```bash
git pull
# CONFLICT 메시지가 나타남
```

#### 충돌 해결 방법
1. 충돌이 발생한 파일 열기
2. 충돌 마커 확인:
   ```
   <<<<<<< HEAD
   현재 브랜치의 코드
   =======
   가져오려는 브랜치의 코드
   >>>>>>> branch-name
   ```
3. 원하는 코드로 수정 (충돌 마커 제거)
4. 파일 저장
5. 해결된 파일 추가 및 커밋:
   ```bash
   git add conflicted-file.txt
   git commit -m "Resolve merge conflict"
   ```

#### 충돌 방지 팁
- 작업 전 항상 `git pull`로 최신 상태 유지
- 작은 단위로 자주 커밋 및 push
- 팀원과 작업 영역 분리

---

## Pull Request

Pull Request(PR)는 코드 변경사항을 검토하고 병합하기 위한 요청입니다.

### PR 생성하기

1. **브랜치에 push**
   ```bash
   git push -u origin feature-branch
   ```

2. **GitHub에서 PR 생성**
   - 저장소 페이지에서 "Compare & pull request" 버튼 클릭
   - 또는 Pull requests 탭 → "New pull request"

3. **PR 정보 작성**
   - 제목: 변경사항을 명확하게 설명
   - 설명: 무엇을, 왜, 어떻게 변경했는지 설명
   - 리뷰어 지정
   - 라벨 추가 (선택사항)

4. **PR 템플릿 예시**
   ```markdown
   ## 변경사항
   - 사용자 로그인 기능 추가
   - 세션 관리 기능 구현
   
   ## 관련 이슈
   Closes #123
   
   ## 체크리스트
   - [ ] 테스트 코드 작성
   - [ ] 문서 업데이트
   - [ ] 코드 리뷰 요청
   ```

### PR 리뷰 프로세스

1. **코드 리뷰**
   - 리뷰어가 코드 확인
   - 댓글로 피드백 제공
   - Approve 또는 Request changes

2. **리뷰 피드백 반영**
   ```bash
   # 변경사항 수정
   git add .
   git commit -m "Apply review feedback"
   git push
   ```

3. **병합 (Merge)**
   - 모든 리뷰 완료 후
   - 충돌이 없으면 "Merge pull request" 클릭
   - 병합 방법 선택:
     - **Create a merge commit**: 병합 커밋 생성
     - **Squash and merge**: 모든 커밋을 하나로 합침
     - **Rebase and merge**: 커밋 히스토리를 선형으로 유지

### PR 병합 후 정리
```bash
# main 브랜치로 전환
git checkout main

# 최신 변경사항 가져오기
git pull origin main

# 병합된 브랜치 삭제
git branch -d feature-branch

# 원격 브랜치 삭제 (선택사항)
git push origin --delete feature-branch
```

---

## GitHub 기능

### 1. Issues (이슈 관리)

#### 이슈 생성
- 저장소 → Issues 탭 → "New issue"
- 버그 리포트, 기능 요청, 질문 등

#### 이슈 라벨
- `bug`: 버그
- `enhancement`: 기능 개선
- `documentation`: 문서
- `question`: 질문
- `help wanted`: 도움 요청

#### 이슈와 커밋 연결
커밋 메시지에 이슈 번호 포함:
```bash
git commit -m "Fix login bug #123"
# 자동으로 이슈 #123과 연결됨
```

### 2. README.md
프로젝트의 첫인상, 사용법 설명 문서

```markdown
# 프로젝트 이름

프로젝트에 대한 간단한 설명

## 설치 방법

\`\`\`bash
npm install
\`\`\`

## 사용 방법

\`\`\`bash
npm start
\`\`\`

## 기여하기

Pull Request를 환영합니다!
```

### 3. GitHub Pages
무료 웹 호스팅 서비스

#### 설정 방법
1. Settings → Pages
2. Source 선택 (보통 `main` 브랜치의 `/docs` 또는 `/root`)
3. 저장 후 `https://username.github.io/repository-name` 접속

### 4. GitHub Actions
CI/CD 자동화

#### 기본 예시 (.github/workflows/ci.yml)
```yaml
name: CI

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Setup Node.js
        uses: actions/setup-node@v2
        with:
          node-version: '16'
      - name: Install dependencies
        run: npm install
      - name: Run tests
        run: npm test
```

### 5. Releases
프로젝트 버전 릴리스

1. Releases → "Create a new release"
2. Tag 버전 입력 (예: `v1.0.0`)
3. 제목 및 설명 작성
4. 파일 첨부 (선택사항)
5. "Publish release" 클릭

### 6. Wiki
프로젝트 문서화

- 저장소 → Wiki 탭
- 마크다운으로 문서 작성
- 협업 편집 가능

### 7. Discussions
커뮤니티 토론 공간

- 질문과 답변
- 아이디어 공유
- 일반적인 토론

---

## 일반적인 시나리오

### 시나리오 1: 새 프로젝트 시작하기
```bash
# 1. 로컬에서 초기화
mkdir my-project
cd my-project
git init
echo "# My Project" > README.md
git add README.md
git commit -m "Initial commit"

# 2. GitHub에서 새 저장소 생성 (웹에서)

# 3. 원격 저장소 연결 및 push
git remote add origin https://github.com/username/my-project.git
git push -u origin main
```

### 시나리오 2: 기존 프로젝트에 합류하기
```bash
# 1. 저장소 클론
git clone https://github.com/username/project.git
cd project

# 2. 최신 상태로 업데이트
git pull origin main

# 3. 새 브랜치에서 작업
git checkout -b feature/my-feature
```

### 시나리오 3: 실수로 잘못 커밋했을 때
```bash
# 마지막 커밋 메시지 수정 (아직 push 안 한 경우)
git commit --amend -m "새로운 커밋 메시지"

# 파일 추가/제거 후 커밋 수정
git add forgotten-file.txt
git commit --amend --no-edit

# 이미 push한 경우 (주의해서 사용)
git commit --amend -m "새로운 커밋 메시지"
git push --force  # ⚠️ 협업 중이면 위험할 수 있음
```

### 시나리오 4: 다른 사람의 변경사항 가져오기
```bash
# 방법 1: Pull (가져오기 + 병합)
git pull origin main

# 방법 2: Fetch + Merge (더 안전)
git fetch origin
git merge origin/main

# 방법 3: Fetch + Rebase (깔끔한 히스토리)
git fetch origin
git rebase origin/main
```

### 시나리오 5: 특정 파일만 가져오기
```bash
# 다른 브랜치의 파일 가져오기
git checkout other-branch -- path/to/file.txt
git commit -m "Add file from other branch"
```

### 시나리오 6: 커밋 히스토리 수정하기
```bash
# 최근 3개 커밋 메시지 대화형으로 수정
git rebase -i HEAD~3

# 에디터에서:
# pick → reword (메시지 변경)
# pick → edit (커밋 수정)
# pick → squash (이전 커밋과 합치기)
# pick → drop (커밋 삭제)
```

### 시나리오 7: 임시로 작업 저장하기 (Stash)
```bash
# 현재 변경사항 임시 저장
git stash

# 또는 메시지와 함께
git stash save "작업 중인 내용"

# 저장된 작업 목록 보기
git stash list

# 저장된 작업 복원
git stash pop

# 특정 stash 복원
git stash apply stash@{0}

# stash 삭제
git stash drop stash@{0}

# 모든 stash 삭제
git stash clear
```

---

## 자주 묻는 질문

### Q: `git pull`과 `git fetch`의 차이는?
A: 
- `git fetch`: 원격 저장소의 변경사항을 가져오기만 함 (병합 안 함)
- `git pull`: `git fetch` + `git merge` (가져온 후 자동 병합)

### Q: 커밋을 push한 후 수정하고 싶어요.
A: 
- 아직 PR이 생성되지 않았다면 `git commit --amend` 후 `git push --force`
- 이미 PR이 있다면 새로운 커밋으로 수정하는 것을 권장

### Q: 실수로 중요한 파일을 삭제했어요.
A:
```bash
# 파일 복원
git checkout HEAD -- deleted-file.txt

# 또는 특정 커밋에서 복원
git checkout <commit-hash> -- deleted-file.txt
```

### Q: `.gitignore` 파일은 어떻게 사용하나요?
A: Git이 추적하지 않을 파일/폴더 목록

```bash
# .gitignore 파일 생성
echo "node_modules/" > .gitignore
echo "*.log" >> .gitignore
echo ".env" >> .gitignore

git add .gitignore
git commit -m "Add .gitignore"
```

### Q: 여러 계정을 사용하고 싶어요.
A:
```bash
# 전역 설정 (기본)
git config --global user.name "Name"
git config --global user.email "email@example.com"

# 특정 저장소만 다른 설정
cd specific-project
git config user.name "Other Name"
git config user.email "other@example.com"
```

### Q: 원격 저장소를 여러 개 연결할 수 있나요?
A: 네, 가능합니다.
```bash
git remote add upstream https://github.com/original/repo.git
git remote add fork https://github.com/myusername/repo.git

# 특정 원격 저장소에 push
git push upstream main
git push fork main
```

### Q: 큰 파일을 실수로 커밋했어요.
A:
```bash
# Git LFS 사용 (권장)
git lfs track "*.psd"
git add .gitattributes
git add file.psd
git commit -m "Add large file with LFS"

# 이미 커밋한 경우 Git LFS로 마이그레이션 필요
```

### Q: 협업 중 충돌을 최소화하려면?
A:
1. 작은 단위로 자주 커밋 및 push
2. 작업 전 항상 `git pull`
3. 팀원과 작업 영역 분리
4. 브랜치 전략 사용

---

## 유용한 Git 명령어 모음

### 기본
```bash
git init                    # 저장소 초기화
git clone <url>            # 저장소 복제
git status                 # 상태 확인
git add <file>             # 파일 추가
git commit -m "message"    # 커밋
git push                   # 업로드
git pull                   # 다운로드
```

### 브랜치
```bash
git branch                 # 브랜치 목록
git branch <name>          # 브랜치 생성
git checkout <name>        # 브랜치 전환
git checkout -b <name>     # 브랜치 생성 및 전환
git merge <branch>         # 브랜치 병합
git branch -d <name>       # 브랜치 삭제
```

### 히스토리
```bash
git log                    # 커밋 히스토리
git log --oneline          # 간단한 히스토리
git log --graph            # 그래프로 보기
git show <commit>          # 특정 커밋 상세
```

### 되돌리기
```bash
git restore <file>         # 파일 되돌리기
git restore --staged <file> # 스테이징 제거
git reset HEAD~1           # 커밋 취소
git revert <commit>        # 커밋 되돌리기 (새 커밋 생성)
```

### 기타
```bash
git stash                  # 작업 임시 저장
git stash pop              # 저장된 작업 복원
git remote -v              # 원격 저장소 확인
git diff                   # 변경사항 비교
git tag <name>             # 태그 생성
```

---

## 추가 자료

- **공식 문서**: [git-scm.com](https://git-scm.com/doc)
- **GitHub 가이드**: [guides.github.com](https://guides.github.com)
- **Git 시각화**: [learngitbranching.js.org](https://learngitbranching.js.org)
- **Git 커밋 메시지 가이드**: [Conventional Commits](https://www.conventionalcommits.org)
- **GitHub CLI**: [cli.github.com](https://cli.github.com) (명령줄에서 GitHub 사용)

---

**작성일**: 2024  
**버전**: 1.0

*이 가이드는 Git과 GitHub의 기본적인 사용법을 다룹니다. 더 자세한 내용은 공식 문서를 참고하세요.*







