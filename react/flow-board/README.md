# Flow - 칸반 작업판

Vite + React로 만든 **칸반 스타일 작업 관리 앱**입니다.  
일반 Todo 앱(체크리스트)과 달리, **드래그 앤 드롭**으로 작업 흐름을 시각적으로 관리합니다.

---

## 실행 방법

```bash
cd react/flow-board
npm install
npm run dev
```

브라우저에서 `http://localhost:5173` 접속

---

## 주요 기능

| 기능 | 설명 |
|------|------|
| **카드 추가** | 각 컬럼의 "+ 카드 추가" 버튼으로 새 작업 카드 추가 |
| **드래그 이동** | 카드를 드래그해서 다른 컬럼으로 이동 (진행 상태 변경) |
| **카드 수정** | 카드 텍스트 더블클릭으로 수정, Enter로 저장 / Esc로 취소 |
| **카드 삭제** | 카드에 마우스를 올리면 나타나는 × 버튼으로 삭제 |

---

## 컬럼 구조

| 컬럼 | 용도 |
|------|------|
| 아이디어 | 아직 구체화되지 않은 아이디어/제안 |
| 오늘 | 오늘 할 작업 목록 |
| 진행중 | 현재 진행 중인 작업 |
| 완료 | 완료된 작업 |

---

## 파일별 상세 설명

각 파일의 역할과 코드 설명은 `docs/` 폴더를 참고하세요.

| 파일 | 설명 문서 |
|------|----------|
| index.html | [01-index.html.md](docs/01-index.html.md) |
| main.jsx | [02-main.jsx.md](docs/02-main.jsx.md) |
| App.jsx | [03-App.jsx.md](docs/03-App.jsx.md) |
| KanbanBoard.jsx | [04-KanbanBoard.jsx.md](docs/04-KanbanBoard.jsx.md) |
| Column.jsx | [05-Column.jsx.md](docs/05-Column.jsx.md) |
| Card.jsx | [06-Card.jsx.md](docs/06-Card.jsx.md) |
| CardForm.jsx | [07-CardForm.jsx.md](docs/07-CardForm.jsx.md) |
| index.css | [08-index.css.md](docs/08-index.css.md) |
| vite.config.js | [09-vite.config.js.md](docs/09-vite.config.js.md) |
| package.json | [10-package.json.md](docs/10-package.json.md) |

---

## 프로젝트 구조

```
flow-board/
├── index.html
├── package.json
├── vite.config.js
├── README.md
├── docs/                 # 파일별 상세 설명
│   ├── 01-index.html.md
│   ├── 02-main.jsx.md
│   └── ...
├── public/
│   └── flow.svg          # 파비콘
└── src/
    ├── main.jsx          # 진입점
    ├── App.jsx            # 상태 관리, 컬럼/카드 데이터
    ├── index.css          # 전역 스타일
    └── components/
        ├── KanbanBoard.jsx  # 드래그 로직, 보드 레이아웃
        ├── Column.jsx       # 컬럼 UI, 카드 목록
        ├── Card.jsx         # 개별 카드, 수정/삭제
        └── CardForm.jsx     # 카드 추가 폼
```

---

## 컴포넌트별 역할

### App.jsx
- **상태**: `columns`, `cards`
- **역할**: 최상위 상태 관리, 카드 CRUD 핸들러 제공
- **데이터 구조**:
  - `column`: `{ id, title, color }`
  - `card`: `{ id, text, columnId, createdAt }`

### KanbanBoard.jsx
- **상태**: `draggedCard`, `dragOverColumn`
- **역할**: HTML5 Drag & Drop API로 카드 이동 처리
- **이벤트**: `onDragStart`, `onDragOver`, `onDrop`, `onDragEnd`

### Column.jsx
- **상태**: `isAdding` (카드 추가 폼 표시 여부)
- **역할**: 컬럼 헤더, 카드 목록, "+ 카드 추가" 버튼

### Card.jsx
- **상태**: `isEditing`, `editText`
- **역할**: 카드 표시, 더블클릭 수정, 삭제 버튼
- **키보드**: Enter(저장), Esc(취소)

### CardForm.jsx
- **역할**: 새 카드 입력 폼
- **기능**: `useEffect`로 마운트 시 textarea 자동 포커스

---

## 사용한 React 개념

| 개념 | 사용 위치 |
|------|----------|
| `useState` | App, KanbanBoard, Column, Card, CardForm |
| `useEffect` | CardForm (마운트 시 포커스) |
| `useRef` | CardForm (textarea 참조) |
| 상태 끌어올리기 | App → KanbanBoard → Column → Card |
| 제어 컴포넌트 | CardForm (textarea), Card (수정 모드) |
| 조건부 렌더링 | `{isAdding ? <CardForm /> : <button />}` |

---

## 드래그 앤 드롭 흐름

1. `onDragStart`: 카드 ID를 `dataTransfer`에 저장, 드래그 중인 카드 상태 설정
2. `onDragOver`: 드롭 가능 영역 표시 (컬럼에 `drag-over` 클래스)
3. `onDrop`: `dataTransfer`에서 ID 확인 후 `onMoveCard` 호출
4. `onDragEnd`: 드래그 관련 상태 초기화

---

## 빌드 및 배포

```bash
npm run build    # dist/ 폴더에 산출물 생성
npm run preview  # 빌드 결과 미리보기
```
