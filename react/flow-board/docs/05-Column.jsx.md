# Column.jsx

## 위치
`flow-board/src/components/Column.jsx`

## 역할
**컬럼 하나**를 렌더링합니다. 헤더(제목, 카드 개수), 카드 목록, 카드 추가 버튼/폼을 포함합니다.

## Props

| prop | 타입 | 설명 |
|------|------|------|
| `column` | 객체 | `{ id, title, color }` |
| `cards` | 배열 | 해당 컬럼에 속한 카드만 (이미 필터된 상태) |
| `onAddCard` | 함수 | (columnId, text) |
| `onDeleteCard` | 함수 | (cardId) |
| `onUpdateCard` | 함수 | (cardId, newText) |
| `onDragStart` | 함수 | 드래그 시작 시 KanbanBoard에서 전달 |
| `onDragEnd` | 함수 | 드래그 종료 시 |
| `isDragging` | boolean | 이 컬럼에서 카드가 드래그 중인지 |

## 상태(State)

| 상태 | 용도 |
|------|------|
| `isAdding` | true일 때 CardForm 표시, false일 때 "+ 카드 추가" 버튼 표시 |

## 핵심 로직

### handleAdd
```js
const handleAdd = (text) => {
  onAddCard(column.id, text);
  setIsAdding(false);  // 폼 닫기
};
```

### 조건부 렌더링
```jsx
{isAdding ? (
  <CardForm onSubmit={handleAdd} onCancel={() => setIsAdding(false)} />
) : (
  <button onClick={() => setIsAdding(true)}>+ 카드 추가</button>
)}
```

## CSS 변수
- `style={{ '--column-accent': column.color }}` 로 컬럼별 색상 전달
- `index.css`의 `.column-header { border-bottom: 2px solid var(--column-accent); }`에서 사용

## isDragging
- KanbanBoard에서 `draggedCard?.columnId === column.id`일 때 true
- `.column-cards.dragging .card { opacity: 0.6 }` 으로 출발 컬럼 카드들 흐리게 표시
