# index.css

## 위치
`flow-board/src/index.css`

## 역할
앱의 **전역 스타일**을 정의합니다. CSS 변수로 테마 색상을 관리하고, 각 컴포넌트별 클래스를 스타일링합니다.

## CSS 변수 (:root)

| 변수 | 용도 |
|------|------|
| `--bg-primary` | 페이지 배경 (#0f0f12) |
| `--bg-secondary` | 컬럼 배경 |
| `--bg-card` | 카드 배경 |
| `--bg-card-hover` | 카드 호버 시 |
| `--border` | 테두리 색상 |
| `--text-primary` | 기본 텍스트 |
| `--text-secondary` | 부제/보조 텍스트 |
| `--text-muted` | 더 약한 텍스트 |
| `--accent` | 강조 색 (버튼, 포커스) |
| `--accent-hover` | 강조 버튼 호버 |
| `--danger` | 삭제 등 위험 액션 |
| `--radius` | 기본 border-radius |
| `--shadow` | 그림자 |

## 섹션별 스타일

### 1. Reset & Body
- `* { box-sizing: border-box }` : 패딩을 width에 포함
- body에 다크 테마 배경, 폰트 적용

### 2. App & Header
- `.logo` : 그라데이션 텍스트 (background-clip: text)
- `.tagline` : 부제목 스타일

### 3. Board
- `display: grid` + `repeat(auto-fit, minmax(260px, 1fr))` : 반응형 그리드
- 화면이 좁아지면 컬럼이 아래로 줄바꿈

### 4. Column
- `--column-accent` : 각 컬럼별 헤더 색 (Column에서 inline style로 주입)
- `.column-cards.dragging` : 드래그 출발 컬럼의 카드 opacity 감소

### 5. Card
- `cursor: grab` / `grabbing` : 드래그 가능 UI 힌트
- `.card-delete` : 기본 opacity 0, 호버 시 1 (카드에 마우스 올렸을 때만 표시)

### 6. CardForm
- 점선 테두리로 "추가 영역" 시각화
- textarea focus 시 accent 색 아웃라인

### 7. Add Button
- 점선 버튼, 호버 시 accent 색 강조
