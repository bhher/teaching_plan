# Git 단계별 가이드

## 학습 목표
- Git의 개념과 필요성을 이해할 수 있다
- Git 설치 및 기본 설정을 할 수 있다
- 기본 Git 명령어를 사용할 수 있다
- 브랜치를 생성하고 관리할 수 있다
- 원격 저장소(GitHub)와 연동할 수 있다
- 협업 워크플로우를 이해하고 적용할 수 있다

---

## 1단계: Git이란?

### 1.1 Git의 개념

**Git**은 분산 버전 관리 시스템(Distributed Version Control System)입니다.

**버전 관리란?**
- 파일의 변경 이력을 추적하고 관리하는 것
- 언제든지 이전 버전으로 되돌릴 수 있음
- 여러 사람이 동시에 작업할 수 있음

**Git의 장점:**
- ✅ 변경 이력 추적
- ✅ 협업 용이
- ✅ 백업 및 복구
- ✅ 브랜치를 통한 병렬 개발
- ✅ 오프라인 작업 가능

### 1.2 Git vs 다른 도구

**로컬 버전 관리:**
- 파일 복사본 저장 (예: `project_v1`, `project_v2`)
- ❌ 비효율적, 실수 가능성 높음

**중앙 집중식 버전 관리 (CVS, SVN):**
- 중앙 서버에 모든 버전 저장
- ❌ 서버 장애 시 전체 작업 중단

**분산 버전 관리 (Git):**
- 각 개발자 컴퓨터에 전체 저장소 복사
- ✅ 서버 장애 시에도 작업 가능
- ✅ 오프라인 작업 가능
- ✅ 빠른 속도

### 1.3 Git의 작동 원리

```
작업 디렉토리 (Working Directory)
    ↓
스테이징 영역 (Staging Area)
    ↓
로컬 저장소 (Local Repository)
    ↓
원격 저장소 (Remote Repository)
```

**3가지 영역:**
1. **Working Directory**: 실제 파일이 있는 곳
2. **Staging Area**: 커밋할 파일을 준비하는 곳
3. **Repository**: 커밋된 파일들이 저장되는 곳

---

## 2단계: Git 설치 및 설정

### 2.1 Git 설치

#### Windows
1. https://git-scm.com/download/win 접속
2. 다운로드 후 설치
3. 설치 중 기본 옵션 유지 (Next 클릭)

#### 설치 확인
```bash
git --version
# 예: git version 2.40.0
```

### 2.2 Git 기본 설정

#### 사용자 정보 설정 (필수)
```bash
# 전역 설정 (모든 프로젝트에 적용)
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# 설정 확인
git config --global user.name
git config --global user.email
```

#### 추가 설정 (선택)
```bash
# 기본 에디터 설정 (VS Code 사용 시)
git config --global core.editor "code --wait"

# 기본 브랜치 이름을 main으로 설정
git config --global init.defaultBranch main

# 줄 끝 문자 처리 (Windows)
git config --global core.autocrlf true

# 색상 출력 활성화
git config --global color.ui auto
```

#### 설정 확인
```bash
# 모든 설정 확인
git config --list

# 특정 설정 확인
git config user.name
```

---

## 3단계: Git 기본 명령어

### 3.1 저장소 초기화

#### 새 프로젝트 시작
```bash
# 프로젝트 폴더로 이동
cd my-project

# Git 저장소 초기화
git init

# .git 폴더가 생성됨 (숨김 폴더)
```

#### 기존 저장소 복제
```bash
# GitHub에서 저장소 복제
git clone https://github.com/username/repository.git

# 특정 폴더명으로 복제
git clone https://github.com/username/repository.git my-folder
```

### 3.2 상태 확인

#### 현재 상태 확인
```bash
# 파일 상태 확인
git status

# 간단한 상태 확인
git status -s
```

**상태 표시:**
- `??`: 추적되지 않는 새 파일
- `A`: 추가된 파일 (스테이징됨)
- `M`: 수정된 파일
- `D`: 삭제된 파일

#### 변경사항 확인
```bash
# 수정된 내용 확인
git diff

# 스테이징된 내용 확인
git diff --staged
```

### 3.3 파일 추가 및 커밋

#### 기본 워크플로우
```bash
# 1. 파일을 스테이징 영역에 추가
git add filename.txt

# 2. 모든 파일 추가
git add .

# 3. 특정 확장자만 추가
git add *.html

# 4. 커밋 (변경사항 저장)
git commit -m "커밋 메시지"

# 5. add와 commit 한 번에 (주의: 새 파일은 안 됨)
git commit -am "커밋 메시지"
```

#### 커밋 메시지 작성 규칙
```
타입: 간단한 설명

상세 설명 (선택사항)
```

**타입 예시:**
- `feat`: 새로운 기능 추가
- `fix`: 버그 수정
- `docs`: 문서 수정
- `style`: 코드 포맷팅
- `refactor`: 리팩토링
- `test`: 테스트 추가
- `chore`: 빌드/설정 변경

**예시:**
```bash
git commit -m "feat: Add user login functionality"
git commit -m "fix: Resolve mobile menu display issue"
git commit -m "docs: Update README with installation guide"
```

### 3.4 커밋 이력 확인

#### 커밋 로그 보기
```bash
# 전체 로그
git log

# 간단한 로그
git log --oneline

# 그래프로 보기
git log --oneline --graph

# 최근 5개만 보기
git log -5

# 특정 파일의 이력
git log filename.txt
```

#### 커밋 상세 정보
```bash
# 특정 커밋의 상세 정보
git show <commit-hash>

# 예시
git show abc1234
```

### 3.5 파일 삭제 및 이름 변경

#### 파일 삭제
```bash
# 파일 삭제 후 Git에 반영
git rm filename.txt
git commit -m "Remove filename.txt"

# 또는 파일만 삭제하고 Git에는 나중에 반영
rm filename.txt
git add filename.txt
git commit -m "Remove filename.txt"
```

#### 파일 이름 변경
```bash
# 파일 이름 변경
git mv old-name.txt new-name.txt
git commit -m "Rename file"
```

---

## 4단계: 되돌리기 및 수정

### 4.1 스테이징 취소

```bash
# 스테이징 영역에서 제거 (파일은 유지)
git reset HEAD filename.txt

# 모든 파일 스테이징 취소
git reset HEAD
```

### 4.2 파일 변경사항 취소

```bash
# 작업 디렉토리의 변경사항 취소 (주의!)
git checkout -- filename.txt

# 또는 (Git 2.23+)
git restore filename.txt

# 모든 변경사항 취소
git restore .
```

⚠️ **주의**: 이 명령어는 되돌릴 수 없습니다!

### 4.3 커밋 수정

#### 마지막 커밋 메시지 수정
```bash
git commit --amend -m "새로운 메시지"
```

#### 마지막 커밋에 파일 추가
```bash
git add forgotten-file.txt
git commit --amend --no-edit
```

#### 마지막 커밋 취소 (파일은 유지)
```bash
# 커밋만 취소, 파일은 스테이징 영역에 유지
git reset --soft HEAD~1

# 커밋 취소, 파일은 작업 디렉토리에 유지
git reset --mixed HEAD~1
# 또는
git reset HEAD~1

# 커밋과 파일 변경 모두 취소 (주의!)
git reset --hard HEAD~1
```

### 4.4 특정 커밋으로 되돌리기

```bash
# 커밋 해시 확인
git log --oneline

# 특정 커밋으로 되돌리기
git reset --hard <commit-hash>

# 예시
git reset --hard abc1234
```

---

## 5단계: .gitignore 파일

### 5.1 .gitignore란?

Git이 추적하지 않을 파일/폴더를 지정하는 파일입니다.

### 5.2 .gitignore 생성

프로젝트 루트에 `.gitignore` 파일 생성:

```bash
# .gitignore 파일 생성
touch .gitignore
```

### 5.3 .gitignore 작성 예시

```gitignore
# 의존성
node_modules/
package-lock.json
yarn.lock

# 빌드 결과
dist/
build/
*.min.js
*.min.css

# 환경 변수
.env
.env.local
.env.production

# 운영체제
.DS_Store
Thumbs.db
desktop.ini

# 에디터
.vscode/
.idea/
*.swp
*.swo
*~

# 로그
*.log
logs/

# 임시 파일
*.tmp
*.temp
.cache/

# 특정 파일
config.json
secrets.txt
```

### 5.4 .gitignore 패턴

- `폴더명/`: 폴더 전체 무시
- `*.확장자`: 특정 확장자 무시
- `!예외파일`: 예외 처리
- `#`: 주석

**예시:**
```gitignore
# 모든 .log 파일 무시
*.log

# 하지만 important.log는 추적
!important.log

# node_modules 폴더 무시
node_modules/

# build 폴더는 무시하지만 build/docs는 추적
build/*
!build/docs/
```

---

## 6단계: 브랜치 (Branch)

### 6.1 브랜치란?

브랜치는 독립적인 작업 공간입니다. 메인 코드에 영향을 주지 않고 새로운 기능을 개발하거나 실험할 수 있습니다.

**브랜치의 장점:**
- ✅ 안전한 실험 공간
- ✅ 병렬 개발 가능
- ✅ 기능별로 독립적 작업

### 6.2 브랜치 확인 및 생성

#### 브랜치 목록 확인
```bash
# 로컬 브랜치 목록
git branch

# 원격 브랜치 포함
git branch -a

# 현재 브랜치 확인
git branch
# 또는
git status
```

#### 브랜치 생성
```bash
# 브랜치 생성 (전환하지 않음)
git branch feature-login

# 브랜치 생성 및 전환
git checkout -b feature-login

# 또는 (Git 2.23+)
git switch -c feature-login
```

#### 브랜치 전환
```bash
# 브랜치 전환
git checkout feature-login

# 또는 (Git 2.23+)
git switch feature-login

# 이전 브랜치로 돌아가기
git checkout -
```

### 6.3 브랜치 병합 (Merge)

#### 기본 병합
```bash
# 1. 메인 브랜치로 전환
git checkout main

# 2. 최신 코드 가져오기
git pull origin main

# 3. 기능 브랜치 병합
git merge feature-login

# 4. 병합 후 브랜치 삭제 (선택)
git branch -d feature-login
```

#### 병합 충돌 해결

충돌이 발생하면:

```bash
# 1. 충돌 파일 확인
git status

# 2. 파일 열어서 충돌 해결
# <<<<<<< HEAD
# 현재 브랜치 코드
# =======
# 병합할 브랜치 코드
# >>>>>>> feature-login

# 3. 충돌 해결 후
git add resolved-file.txt
git commit -m "Merge feature-login"
```

### 6.4 브랜치 삭제

```bash
# 로컬 브랜치 삭제
git branch -d feature-login

# 강제 삭제 (병합되지 않은 브랜치)
git branch -D feature-login

# 원격 브랜치 삭제
git push origin --delete feature-login
```

### 6.5 브랜치 전략

#### Git Flow (인기 있는 전략)

```
main (master)
  └── develop
       ├── feature/login
       ├── feature/header
       └── fix/mobile-menu
```

**브랜치 종류:**
- `main`: 프로덕션 코드 (항상 안정적)
- `develop`: 개발 브랜치
- `feature/*`: 새로운 기능
- `fix/*`: 버그 수정
- `hotfix/*`: 긴급 수정

**예시:**
```bash
# 기능 개발
git checkout -b feature/user-profile
# 작업 후
git checkout develop
git merge feature/user-profile

# 버그 수정
git checkout -b fix/button-style
# 작업 후
git checkout develop
git merge fix/button-style
```

---

## 7단계: 원격 저장소 (GitHub)

### 7.1 GitHub란?

GitHub는 Git 저장소를 호스팅하는 웹 서비스입니다.

**GitHub의 기능:**
- 원격 저장소 호스팅
- 협업 도구 (Pull Request, Issue)
- 코드 리뷰
- 프로젝트 관리

### 7.2 원격 저장소 연결

#### GitHub 저장소 생성
1. GitHub.com 접속
2. New Repository 클릭
3. 저장소 이름 입력
4. Create repository 클릭

#### 로컬 저장소와 연결
```bash
# 원격 저장소 추가
git remote add origin https://github.com/username/repository.git

# 원격 저장소 확인
git remote -v

# 원격 저장소 이름 변경
git remote rename origin github

# 원격 저장소 제거
git remote remove origin
```

### 7.3 Push & Pull

#### Push (로컬 → 원격)
```bash
# 첫 푸시
git push -u origin main

# 이후 푸시
git push

# 특정 브랜치 푸시
git push origin feature-login

# 모든 브랜치 푸시
git push --all origin
```

#### Pull (원격 → 로컬)
```bash
# 원격 저장소의 변경사항 가져오기
git pull origin main

# 또는
git pull
```

#### Fetch (가져오기만, 병합 안 함)
```bash
# 원격 변경사항만 가져오기
git fetch origin

# 가져온 후 병합
git merge origin/main
```

### 7.4 Clone vs Fork

#### Clone (복제)
```bash
# 다른 사람의 저장소를 내 컴퓨터에 복사
git clone https://github.com/username/repository.git
```

#### Fork (포크)
- GitHub에서 다른 사람의 저장소를 내 계정으로 복사
- 원본 저장소와 독립적으로 관리
- Pull Request를 통해 원본에 기여 가능

---

## 8단계: 협업 워크플로우

### 8.1 기본 협업 흐름

```
1. 원격 저장소에서 최신 코드 가져오기
   git pull origin main

2. 새 브랜치 생성
   git checkout -b feature/new-feature

3. 작업 및 커밋
   git add .
   git commit -m "feat: Add new feature"

4. 원격에 푸시
   git push origin feature/new-feature

5. Pull Request 생성 (GitHub에서)

6. 코드 리뷰 후 병합

7. 로컬에서 최신 코드 가져오기
   git checkout main
   git pull origin main
```

### 8.2 Pull Request (PR)

**Pull Request란?**
- 내 브랜치의 변경사항을 메인 브랜치에 병합 요청
- 코드 리뷰를 받을 수 있음
- 협업의 핵심 도구

**PR 생성 과정:**
1. 기능 브랜치에서 작업 완료
2. GitHub에서 "New Pull Request" 클릭
3. 변경사항 설명 작성
4. 리뷰어 지정
5. PR 생성
6. 리뷰 후 병합 또는 수정 요청

### 8.3 충돌 해결

#### Pull 시 충돌
```bash
# 1. 최신 코드 가져오기
git pull origin main

# 2. 충돌 발생 시
# CONFLICT 메시지 확인

# 3. 충돌 파일 수정
# <<<<<<< HEAD
# =======
# >>>>>>>

# 4. 충돌 해결 후
git add .
git commit -m "Resolve merge conflict"

# 5. 다시 푸시
git push
```

### 8.4 협업 시 주의사항

✅ **해야 할 것:**
- 작업 전 항상 `git pull`로 최신 코드 가져오기
- 작은 단위로 자주 커밋하기
- 명확한 커밋 메시지 작성
- 브랜치 전략 일관성 유지

❌ **하지 말아야 할 것:**
- `main` 브랜치에 직접 커밋하지 않기
- 다른 사람의 작업 중인 브랜치에 강제 푸시하지 않기
- 큰 파일을 저장소에 올리지 않기
- `.gitignore`에 포함된 파일 커밋하지 않기

---

## 9단계: 실전 예제

### 9.1 새 프로젝트 시작하기

```bash
# 1. 프로젝트 폴더 생성
mkdir my-project
cd my-project

# 2. Git 초기화
git init

# 3. .gitignore 생성
echo "node_modules/" > .gitignore
echo ".env" >> .gitignore

# 4. 파일 생성 및 커밋
echo "# My Project" > README.md
git add .
git commit -m "Initial commit"

# 5. GitHub에 저장소 생성 후 연결
git remote add origin https://github.com/username/my-project.git
git branch -M main
git push -u origin main
```

### 9.2 기능 개발하기

```bash
# 1. 새 기능 브랜치 생성
git checkout -b feature/header

# 2. 파일 생성/수정
# header.html 작성
# styles.css 수정

# 3. 변경사항 커밋
git add .
git commit -m "feat: Add responsive header component"

# 4. 원격에 푸시
git push origin feature/header

# 5. GitHub에서 Pull Request 생성
# 6. 리뷰 후 병합되면

# 7. 메인 브랜치로 돌아가서 최신 코드 가져오기
git checkout main
git pull origin main

# 8. 기능 브랜치 삭제
git branch -d feature/header
```

### 9.3 버그 수정하기

```bash
# 1. 버그 수정 브랜치 생성
git checkout -b fix/mobile-menu

# 2. 버그 수정
# mobile-menu.css 수정

# 3. 커밋
git add .
git commit -m "fix: Resolve mobile menu display issue"

# 4. 푸시 및 PR
git push origin fix/mobile-menu
```

### 9.4 이전 버전으로 되돌리기

```bash
# 1. 커밋 이력 확인
git log --oneline

# 2. 특정 커밋으로 되돌리기
git reset --hard <commit-hash>

# 3. 강제 푸시 (주의!)
git push -f origin main
```

---

## 10단계: 고급 기능

### 10.1 Stash (임시 저장)

```bash
# 현재 작업 임시 저장
git stash

# 메시지와 함께 저장
git stash save "작업 중인 내용"

# 저장된 목록 확인
git stash list

# 가장 최근 stash 적용
git stash pop

# 특정 stash 적용
git stash apply stash@{0}

# stash 삭제
git stash drop stash@{0}

# 모든 stash 삭제
git stash clear
```

### 10.2 Tag (태그)

```bash
# 태그 생성
git tag v1.0.0

# 메시지와 함께 태그 생성
git tag -a v1.0.0 -m "Version 1.0.0 release"

# 태그 목록 확인
git tag

# 태그 푸시
git push origin v1.0.0

# 모든 태그 푸시
git push origin --tags
```

### 10.3 Cherry-pick

```bash
# 특정 커밋만 가져오기
git cherry-pick <commit-hash>

# 여러 커밋 가져오기
git cherry-pick <commit1> <commit2>
```

### 10.4 Rebase

```bash
# 현재 브랜치를 main 위에 재배치
git checkout feature-login
git rebase main

# 충돌 발생 시
# 1. 충돌 해결
# 2. git add .
# 3. git rebase --continue

# rebase 취소
git rebase --abort
```

---

## 11단계: 문제 해결

### 11.1 자주 발생하는 문제

#### 문제 1: 커밋 메시지 잘못 입력
```bash
# 해결: 커밋 메시지 수정
git commit --amend -m "올바른 메시지"
```

#### 문제 2: 잘못된 파일 커밋
```bash
# 해결: 마지막 커밋 취소
git reset --soft HEAD~1
# 파일 수정 후 다시 커밋
```

#### 문제 3: 원격과 로컬 불일치
```bash
# 해결: 원격 코드 가져오기
git pull origin main

# 충돌 발생 시 해결 후
git add .
git commit -m "Resolve conflict"
git push
```

#### 문제 4: 실수로 삭제한 파일 복구
```bash
# 해결: 커밋 이력에서 복구
git checkout HEAD -- filename.txt
```

### 11.2 유용한 명령어

```bash
# 현재 상태 한눈에 보기
git status
git log --oneline --graph --all

# 변경사항 요약
git diff --stat

# 누가 언제 무엇을 수정했는지
git blame filename.txt

# 파일 이력 추적
git log --follow filename.txt
```

---

## 12단계: 실습 과제

### 과제 1: 기본 워크플로우
1. 새 폴더에 Git 저장소 초기화
2. README.md 파일 생성 및 커밋
3. index.html 파일 추가 및 커밋
4. 커밋 이력 확인

### 과제 2: 브랜치 실습
1. `feature/about` 브랜치 생성
2. about.html 파일 생성
3. 커밋 후 main 브랜치에 병합
4. 브랜치 삭제

### 과제 3: GitHub 연동
1. GitHub에 새 저장소 생성
2. 로컬 저장소와 연결
3. 코드 푸시
4. GitHub에서 확인

### 과제 4: 협업 시뮬레이션
1. 두 개의 브랜치에서 동시에 작업
2. 충돌 발생시키기
3. 충돌 해결하기
4. 병합하기

---

## 요약

### 필수 명령어 체크리스트

```bash
# 초기 설정
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# 기본 워크플로우
git init
git add .
git commit -m "메시지"
git status
git log

# 브랜치
git branch
git checkout -b feature-name
git merge feature-name

# 원격 저장소
git remote add origin <url>
git push -u origin main
git pull origin main
git clone <url>
```

### 워크플로우 요약

```
1. git pull (최신 코드 가져오기)
2. git checkout -b feature-name (브랜치 생성)
3. 작업 및 수정
4. git add . (스테이징)
5. git commit -m "메시지" (커밋)
6. git push origin feature-name (푸시)
7. Pull Request 생성
8. 리뷰 후 병합
9. git checkout main (메인으로 전환)
10. git pull (최신 코드 가져오기)
```

---

## 추가 학습 자료

- **공식 문서**: https://git-scm.com/doc
- **GitHub 가이드**: https://guides.github.com
- **시각적 학습**: https://learngitbranching.js.org
- **Git Cheat Sheet**: https://education.github.com/git-cheat-sheet-education.pdf

---

## 마무리

Git은 처음에는 어려워 보일 수 있지만, 자주 사용하다 보면 자연스럽게 익숙해집니다. 

**핵심은:**
- 작은 단위로 자주 커밋하기
- 명확한 커밋 메시지 작성하기
- 브랜치를 적극 활용하기
- 협업 시 소통하기

꾸준한 연습으로 Git을 마스터하세요! 🚀


