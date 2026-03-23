# Card.jsx

## 위치
`flow-board/src/components/Card.jsx`

## 역할
**개별 카드**를 렌더링합니다. 드래그 가능, 더블클릭 수정, 삭제 버튼을 지원합니다.

## Props

| prop | 타입 | 설명 |
|------|------|------|
| `card` | 객체 | `{ id, text, columnId, createdAt }` |
| `onDelete` | 함수 | (cardId) |
| `onUpdate` | 함수 | (cardId, newText) |
| `onDragStart` | 함수 | (e, card) |
| `onDragEnd` | 함수 | (e) |

## 상태(State)

| 상태 | 용도 |
|------|------|
| `isEditing` | true면 수정 모드(textarea), false면 읽기 모드 |
| `editText` | 수정 중인 텍스트. card.text와 동기화 |

## 모드 전환

### 읽기 모드 → 수정 모드
- 카드 텍스트(`<p>`)를 **더블클릭** → `setIsEditing(true)`

### 수정 모드 → 읽기 모드
- **Enter** : `handleSave()` 호출 → 변경 시 `onUpdate` 실행 후 `setIsEditing(false)`
- **Esc** : 원래 텍스트로 복원, `setIsEditing(false)`
- **blur** (포커스 잃음) : `handleSave()` 호출

## handleSave 로직

```js
const trimmed = editText.trim();
if (trimmed && trimmed !== card.text) {
  onUpdate(card.id, trimmed);  // 변경 사항이 있을 때만
} else if (!trimmed) {
  setEditText(card.text);      // 빈 값이면 원래대로
}
setIsEditing(false);
```

## 드래그 속성
- `draggable` : HTML 속성, 이 요소를 드래그 가능하게 함
- `onDragStart`에서 `e.target.style.opacity = '0.5'` 로 드래그 중 시각적 피드백 (KanbanBoard의 onDragEnd에서 복원)

## 삭제 버튼
- 카드에 호버 시 우측 상단 × 버튼 표시 (CSS `.card:hover .card-delete { opacity: 1 }`)
