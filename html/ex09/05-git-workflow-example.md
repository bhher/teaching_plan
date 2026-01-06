# Git 워크플로우 가이드

이 문서는 Git을 사용한 버전 관리와 협업 워크플로우를 설명합니다.

## 1. Git 기본 설정

### 초기 설정
```bash
# 사용자 정보 설정
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# 기본 에디터 설정
git config --global core.editor "code --wait"

# 기본 브랜치 이름 설정
git config --global init.defaultBranch main
```

### 저장소 초기화
```bash
# 새 저장소 초기화
git init

# 원격 저장소 연결
git remote add origin https://github.com/user/repo.git
```

## 2. 기본 워크플로우

### 첫 커밋
```bash
# 파일 추가
git add .

# 커밋
git commit -m "Initial commit"

# 원격 저장소에 푸시
git push -u origin main
```

### 일반적인 작업 흐름
```bash
# 1. 작업 시작 전 최신 코드 가져오기
git pull origin main

# 2. 파일 수정

# 3. 변경사항 확인
git status
git diff

# 4. 변경사항 스테이징
git add .

# 5. 커밋
git commit -m "feat: Add new feature"

# 6. 원격 저장소에 푸시
git push origin main
```

## 3. 브랜치 전략

### 브랜치 종류

#### main (또는 master)
- 프로덕션 코드
- 항상 안정적인 상태 유지
- 직접 커밋 금지 (Pull Request 통해서만)

#### develop
- 개발 브랜치
- 기능 개발 완료 후 병합

#### feature/*
- 새로운 기능 개발
- 예: `feature/header-component`, `feature/user-login`

#### fix/*
- 버그 수정
- 예: `fix/button-hover`, `fix/mobile-menu`

#### hotfix/*
- 긴급 수정
- 예: `hotfix/security-patch`

### 브랜치 생성 및 전환
```bash
# 브랜치 생성 및 전환
git checkout -b feature/new-feature

# 또는 (Git 2.23+)
git switch -c feature/new-feature

# 브랜치 전환
git checkout main
git switch main

# 브랜치 목록 확인
git branch

# 원격 브랜치 포함 목록
git branch -a
```

### 브랜치 병합
```bash
# main 브랜치로 전환
git checkout main

# feature 브랜치 병합
git merge feature/new-feature

# 병합 후 브랜치 삭제
git branch -d feature/new-feature
```

## 4. 커밋 규칙

### 커밋 메시지 형식
```
타입(범위): 간단한 설명

상세 설명 (선택사항)

- 변경사항 1
- 변경사항 2
```

### 타입 종류
- `feat`: 새로운 기능 추가
- `fix`: 버그 수정
- `docs`: 문서 수정
- `style`: 코드 포맷팅 (기능 변경 없음)
- `refactor`: 리팩토링
- `test`: 테스트 추가/수정
- `chore`: 빌드/설정 변경
- `perf`: 성능 개선
- `ci`: CI/CD 설정 변경

### 커밋 예시
```bash
# 기능 추가
git commit -m "feat(header): Add responsive navigation menu"

# 버그 수정
git commit -m "fix(button): Fix hover state color issue"

# 문서 수정
git commit -m "docs: Update README with installation guide"

# 스타일 수정
git commit -m "style: Format code with Prettier"

# 리팩토링
git commit -m "refactor(css): Extract common styles to variables"
```

## 5. 협업 워크플로우

### 1. 프로젝트 클론
```bash
git clone https://github.com/user/repo.git
cd repo
```

### 2. 기능 브랜치 생성
```bash
git checkout -b feature/my-feature
```

### 3. 개발 및 커밋
```bash
# 작업
# ...

# 커밋
git add .
git commit -m "feat: Add my feature"
```

### 4. 원격 저장소에 푸시
```bash
git push origin feature/my-feature
```

### 5. Pull Request 생성
- GitHub/GitLab에서 Pull Request 생성
- 코드 리뷰 요청
- 리뷰 피드백 반영

### 6. 병합 후 정리
```bash
# main 브랜치로 전환
git checkout main

# 최신 코드 가져오기
git pull origin main

# 로컬 브랜치 삭제
git branch -d feature/my-feature
```

## 6. 충돌 해결

### 충돌 발생 시
```bash
# 충돌 발생
git merge feature/other-feature
# Auto-merging file.txt
# CONFLICT (content): Merge conflict in file.txt

# 충돌 파일 확인
git status

# 충돌 해결 (에디터에서)
# <<<<<<< HEAD
# 현재 브랜치 내용
# =======
# 병합할 브랜치 내용
# >>>>>>> feature/other-feature

# 해결 후
git add file.txt
git commit -m "fix: Resolve merge conflict"
```

## 7. 유용한 Git 명령어

### 상태 확인
```bash
# 현재 상태
git status

# 변경사항 확인
git diff

# 커밋 히스토리
git log
git log --oneline
git log --graph --oneline --all
```

### 변경사항 되돌리기
```bash
# 파일 되돌리기 (스테이징 전)
git checkout -- file.txt

# 스테이징 취소
git reset HEAD file.txt

# 마지막 커밋 수정
git commit --amend
```

### 원격 저장소 관리
```bash
# 원격 저장소 확인
git remote -v

# 원격 저장소 추가
git remote add origin https://github.com/user/repo.git

# 원격 저장소에서 가져오기
git fetch origin

# 원격 저장소와 동기화
git pull origin main

# 원격 저장소에 푸시
git push origin main
```

## 8. .gitignore 설정

### 기본 .gitignore
```
# 의존성
node_modules/
package-lock.json

# 빌드 결과
dist/
build/

# 운영체제
.DS_Store
Thumbs.db

# 에디터
.vscode/
.idea/
*.swp
```

## 9. 실전 워크플로우 예시

### 시나리오: 새로운 기능 추가

```bash
# 1. 최신 코드 가져오기
git checkout main
git pull origin main

# 2. 기능 브랜치 생성
git checkout -b feature/header-component

# 3. 개발
# ... 파일 수정 ...

# 4. 커밋
git add .
git commit -m "feat(header): Add responsive navigation menu"

# 5. 원격 저장소에 푸시
git push origin feature/header-component

# 6. Pull Request 생성 (GitHub/GitLab)

# 7. 코드 리뷰 후 병합

# 8. 로컬 정리
git checkout main
git pull origin main
git branch -d feature/header-component
```

### 시나리오: 버그 수정

```bash
# 1. 버그 수정 브랜치 생성
git checkout -b fix/button-hover

# 2. 버그 수정
# ... 파일 수정 ...

# 3. 커밋
git add .
git commit -m "fix(button): Fix hover state color"

# 4. 푸시 및 Pull Request
git push origin fix/button-hover
```

## 10. Git 체크리스트

### 커밋 전 확인
- [ ] 코드가 정상 작동하는가?
- [ ] 불필요한 파일이 포함되지 않았는가?
- [ ] 커밋 메시지가 명확한가?
- [ ] 관련된 변경사항만 포함되었는가?

### Pull Request 전 확인
- [ ] 모든 테스트가 통과하는가?
- [ ] 코드 리뷰를 받았는가?
- [ ] 문서가 업데이트되었는가?
- [ ] 충돌이 없는가?


