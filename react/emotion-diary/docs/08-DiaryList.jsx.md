# DiaryList.jsx

## 위치
`emotion-diary/src/components/DiaryList.jsx`

## 역할
**일기 목록**을 렌더링합니다. 빈 상태일 때 안내 메시지, 있을 때 DiaryEntry 목록을 보여줍니다.

## Props

| prop | 타입 | 설명 |
|------|------|------|
| `entries` | 배열 | 전체 일기 데이터 |
| `onDelete` | 함수 | (entryId) — DiaryEntry의 삭제 버튼에서 호출 |

## 조건부 렌더링

### entries.length === 0

- `diary-empty` 영역 표시
- "아직 작성한 일기가 없어요", "첫 번째 감정을 기록해보세요" 안내

### entries.length > 0

- `entries.slice().sort((a, b) => b.createdAt - a.createdAt)` 로 최신순 정렬
- `slice()` : 원본 배열을 변경하지 않고 복사 후 정렬
- 각 entry에 대해 `DiaryEntry` 렌더, `key={entry.id}`

## 정렬

- `b.createdAt - a.createdAt` : 숫자가 큰 것(최신)이 먼저
