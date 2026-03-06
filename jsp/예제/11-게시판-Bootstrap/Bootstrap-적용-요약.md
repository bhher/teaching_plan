# 🎨 Bootstrap 적용 요약

## 📌 주요 변경 사항

### 1. CDN 추가
모든 JSP 파일에 다음을 추가:

```html
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

<!-- Bootstrap JS (</body> 앞) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
```

### 2. 주요 Bootstrap 클래스

#### 레이아웃
- `container`: 중앙 정렬, 최대 너비 제한
- `card`: 카드 컴포넌트
- `card-header`, `card-body`: 카드 내부 구조

#### 버튼
- `btn btn-primary`: 주요 버튼
- `btn btn-secondary`: 취소 버튼
- `btn btn-outline-*`: 아웃라인 버튼

#### 테이블
- `table table-hover`: 호버 효과
- `table-responsive`: 반응형 테이블
- `table-light`: 밝은 헤더

#### 유틸리티
- `d-flex`: Flexbox 레이아웃
- `justify-content-between`: 양쪽 정렬
- `gap-2`: 요소 간 간격
- `shadow`: 그림자 효과
- `rounded`: 둥근 모서리

### 3. 아이콘 사용

```html
<i class="bi bi-[아이콘명]"></i>
```

주요 아이콘:
- `bi-clipboard-check`: 게시판
- `bi-pencil-square`: 글쓰기
- `bi-eye`: 보기
- `bi-trash`: 삭제
- `bi-person-circle`: 작성자
- `bi-calendar3`: 날짜

### 4. 색상 테마

- **Primary** (파란색): 주요 액션
- **Secondary** (회색): 취소/보조 액션
- **Warning** (노란색): 수정
- **Danger** (빨간색): 삭제
- **Info** (하늘색): 정보 표시

---

## 🎯 디자인 특징

1. **그라데이션 헤더**: 게시판 목록 페이지
2. **카드 레이아웃**: 모든 페이지를 카드로 감싸서 깔끔하게
3. **아이콘 통합**: 텍스트와 함께 아이콘으로 시각적 구분
4. **반응형**: 모바일에서도 잘 보이도록 설계
5. **호버 효과**: 테이블 행에 마우스 오버 시 배경색 변경

---

**Bootstrap을 활용하여 전문적이고 현대적인 UI를 구현했습니다! 🎨**
