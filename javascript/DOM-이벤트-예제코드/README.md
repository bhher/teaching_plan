# JavaScript DOM과 이벤트 예제 코드

DOM 조작과 이벤트 처리를 학습하기 위한 실전 예제 코드입니다.

## 파일 구조

```
DOM-이벤트-예제코드/
├── example1_click_counter.html      # 클릭 카운터
├── example2_todo_list.html         # 할 일 목록
├── example3_form_validation.html   # 폼 유효성 검사
├── example4_modal.html             # 모달 창
├── example5_tab_menu.html         # 탭 메뉴
└── README.md                       # 이 파일
```

## 사용 방법

1. 각 HTML 파일을 웹 브라우저에서 열기
2. 브라우저의 개발자 도구(F12)를 사용하여 소스 코드 확인
3. 코드를 수정해보며 학습

## 포함된 예제

### 1. 클릭 카운터 (example1_click_counter.html)
**학습 내용:**
- DOM 요소 선택 (`getElementById`)
- 이벤트 리스너 추가 (`addEventListener`)
- 텍스트 내용 변경 (`textContent`)

**주요 기능:**
- 버튼 클릭 시 카운터 증가
- 리셋 버튼으로 초기화

---

### 2. 할 일 목록 (example2_todo_list.html)
**학습 내용:**
- 요소 생성 (`createElement`)
- 요소 추가 (`appendChild`)
- 요소 삭제 (`remove`)
- 키보드 이벤트 (`keypress`)

**주요 기능:**
- 할 일 추가
- Enter 키로 추가
- 할 일 삭제
- 빈 목록 메시지 표시

---

### 3. 폼 유효성 검사 (example3_form_validation.html)
**학습 내용:**
- 폼 이벤트 (`blur`, `submit`)
- 정규표현식 사용
- 동적 스타일 변경
- `preventDefault()` 사용

**주요 기능:**
- 이름 유효성 검사 (2자 이상)
- 이메일 형식 검사
- 비밀번호 길이 검사
- 실시간 피드백

---

### 4. 모달 창 (example4_modal.html)
**학습 내용:**
- 클래스 토글 (`classList.add/remove`)
- 키보드 이벤트 (`keydown`)
- 이벤트 전파 이해
- CSS 애니메이션

**주요 기능:**
- 모달 열기/닫기
- 배경 클릭 시 닫기
- ESC 키로 닫기
- 애니메이션 효과

---

### 5. 탭 메뉴 (example5_tab_menu.html)
**학습 내용:**
- `querySelectorAll` 사용
- `data-*` 속성 활용
- 여러 요소에 이벤트 추가
- CSS 클래스 토글

**주요 기능:**
- 탭 전환
- 콘텐츠 표시/숨김
- 애니메이션 효과

## 학습 순서

1. `example1_click_counter.html` - 기본 이벤트 이해
2. `example2_todo_list.html` - DOM 조작 기본
3. `example3_form_validation.html` - 폼 이벤트와 유효성 검사
4. `example4_modal.html` - 이벤트 전파와 키보드 이벤트
5. `example5_tab_menu.html` - 복잡한 DOM 조작

## 참고 자료

- `JavaScript-DOM과-이벤트-완전정복.md` - 상세한 설명과 예제
