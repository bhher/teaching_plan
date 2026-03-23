# KanbanBoard.jsx

## 위치
`flow-board/src/components/KanbanBoard.jsx`

## 역할
**드래그 앤 드롭** 로직을 관리하고, 컬럼들을 가로로 배치하는 보드 레이아웃 컴포넌트입니다.

## Props

| prop | 타입 | 설명 |
|------|------|------|
| `columns` | 배열 | 컬럼 목록 |
| `cards` | 배열 | 전체 카드 목록 |
| `onAddCard` | 함수 | 카드 추가 콜백 |
| `onMoveCard` | 함수 | 카드 이동 콜백 |
| `onDeleteCard` | 함수 | 카드 삭제 콜백 |
| `onUpdateCard` | 함수 | 카드 수정 콜백 |

## 상태(State)

| 상태 | 용도 |
|------|------|
| `draggedCard` | 현재 드래그 중인 카드 객체. 이동/드롭 판단에 사용 |
| `dragOverColumn` | 마우스가 올라간 컬럼 ID. 시각적 피드백용 |

## 드래그 이벤트 흐름

```
1. onDragStart (Card에서 발생)
   → cardId를 dataTransfer에 저장
   → draggedCard 상태 설정
   → 카드 opacity 0.5

2. onDragOver (column-wrapper에서)
   → e.preventDefault()로 드롭 허용
   → dragOverColumn 업데이트 (드롭 가능 영역 강조)

3. onDrop (column-wrapper에서)
   → dataTransfer에서 cardId 조회
   → onMoveCard(cardId, targetColumnId) 호출
   → draggedCard, dragOverColumn 초기화

4. onDragEnd (Card에서 발생)
   → opacity 복원
   → draggedCard, dragOverColumn 초기화
```

## HTML5 Drag and Drop API
- `e.dataTransfer.setData()` / `getData()` : 드래그 중인 데이터 전달
- `e.dataTransfer.effectAllowed = 'move'` : 이동 동작임을 명시
- `e.preventDefault()` in `onDragOver` : 기본적으로 드롭이 막혀 있으므로 필수

## column-wrapper의 역할
- 각 Column을 감싸는 div
- `onDragOver`, `onDragLeave`, `onDrop`을 여기서 처리 (Column 내부 카드 영역보다 넓은 드롭 영역 확보)
- `drag-over` 클래스로 보라색 테두리 시각적 피드백
