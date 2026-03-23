# App.jsx

## 위치
`flow-board/src/App.jsx`

## 역할
전역 **상태 관리**와 **카드 CRUD 로직**을 담당하는 최상위 컴포넌트입니다. KanbanBoard에 데이터와 핸들러를 props로 전달합니다.

## 상태(State)

| 상태 | 타입 | 설명 |
|------|------|------|
| `columns` | 배열 | 컬럼 정보 (id, title, color). 변경 없음 |
| `cards` | 배열 | 모든 카드 데이터. 추가/이동/수정/삭제 시 업데이트 |

## 데이터 구조

### column
```js
{ id: 'ideas', title: '아이디어', color: '#7c3aed' }
```

### card
```js
{ id: '1', text: '작업 내용', columnId: 'ideas', createdAt: 1234567890 }
```

## 핸들러 함수

| 함수 | 역할 |
|------|------|
| `addCard(columnId, text)` | 새 카드 생성. `crypto.randomUUID()`로 고유 ID 부여 |
| `moveCard(cardId, targetColumnId)` | 카드 `columnId` 변경 (드래그 이동) |
| `deleteCard(cardId)` | 카드 삭제 |
| `updateCard(cardId, newText)` | 카드 텍스트 수정 |

## 컴포넌트 트리

```
App
└── header (로고, 태그라인)
└── KanbanBoard
    ├── columns (props)
    ├── cards (props)
    ├── onAddCard, onMoveCard, onDeleteCard, onUpdateCard
```

## 상태 끌어올리기 (Lifting State Up)
- `cards`는 여러 컴포넌트(Column, Card)에서 사용되므로 App에 두고 props로 내려보냄
- 카드 변경은 반드시 App의 핸들러를 통해 수행 → 단일 진실 공급원(Single Source of Truth)
