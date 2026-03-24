# DiaryEntry.jsx

## 위치
`emotion-diary/src/components/DiaryEntry.jsx`

## 역할
**일기 한 건**을 카드 형태로 렌더링합니다. 감정 배지, 날짜, 내용, 삭제 버튼을 표시합니다.

## Props

| prop | 타입 | 설명 |
|------|------|------|
| `entry` | 객체 | `{ id, emotion, content, createdAt }` |
| `onDelete` | 함수 | (entryId) — 삭제 버튼 클릭 시 호출 |

## formatDate 로직

```js
const formatDate = (timestamp) => {
  const d = new Date(timestamp);
  const now = new Date();
  const isToday = d.toDateString() === now.toDateString();
  return isToday
    ? `오늘 ${d.toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' })}`
    : d.toLocaleDateString('ko-KR', {
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      });
};
```

- **오늘** : "오늘 14:30"
- **과거** : "3월 24일 14:30"

## 렌더링 구성

| 요소 | 설명 |
|------|------|
| `entry-emotion` | 감정 이모지가 들어간 원형 배지. `emotionData?.color` 배경색 |
| `entry-date` | formatDate 결과 |
| `entry-delete` | × 삭제 버튼. 호버 시 빨간색 |
| `entry-content` | 일기 본문. `white-space: pre-wrap` 로 줄바꿈 유지 |

## emotionData 조회

- `EMOTIONS.find((e) => e.id === entry.emotion)` 로 id → 이모지, 색상, 라벨 매핑
- 해당 감정이 없으면 기본 이모지 📝, 기본색 사용
