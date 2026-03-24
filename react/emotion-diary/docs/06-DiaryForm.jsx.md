# DiaryForm.jsx

## 위치
`emotion-diary/src/components/DiaryForm.jsx`

## 역할
**일기 작성 폼**입니다. 감정 선택 + 텍스트 입력 + 저장 버튼을 포함합니다.

## Props

| prop | 타입 | 설명 |
|------|------|------|
| `onSubmit` | 함수 | ({ emotion, content }) — 저장 버튼 클릭 시 호출 |

## 상태(State)

| 상태 | 용도 |
|------|------|
| `emotion` | 선택된 감정 id |
| `content` | textarea 내용 (제어 컴포넌트) |

## 핵심 로직

### handleSubmit

```js
const handleSubmit = (e) => {
  e.preventDefault();
  if (!emotion || !content.trim()) return;
  onSubmit({ emotion, content: content.trim() });
  setEmotion('');
  setContent('');
};
```

- **유효성** : 감정 미선택이거나 내용이 비어 있으면 제출 방지
- **전달** : `{ emotion, content }` 객체로 App의 `addEntry`에 전달
- **초기화** : 제출 후 폼 비우기

## submit 버튼 disabled 조건

- `!emotion` : 감정 미선택
- `!content.trim()` : 내용 없음 또는 공백만

## 컴포넌트 구성

1. **EmotionPicker** — 감정 선택
2. **textarea** — 일기 내용 (placeholder: "오늘 하루는 어땠나요? 마음을 적어보세요...")
3. **submit 버튼** — "일기 저장하기"
