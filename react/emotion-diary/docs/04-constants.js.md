# constants.js

## 위치
`emotion-diary/src/constants.js`

## 역할
**감정 목록**을 정의합니다. EmotionPicker, DiaryEntry에서 공통으로 사용합니다.

## EMOTIONS 배열 구조

| 필드 | 타입 | 설명 |
|------|------|------|
| `id` | 문자열 | 고유 식별자 (entry.emotion에 저장) |
| `label` | 문자열 | 화면에 표시할 한글 이름 |
| `emoji` | 문자열 | 감정 이모지 |
| `color` | 문자열 | HEX 색상 (선택 시 강조, 일기 카드 배지) |

## 정의된 감정

| id | label | emoji | color |
|----|-------|-------|-------|
| happy | 행복 | 😊 | #fcd34d |
| sad | 슬픔 | 😢 | #93c5fd |
| angry | 화남 | 😤 | #f87171 |
| calm | 평온 | 😌 | #86efac |
| anxious | 불안 | 😰 | #c4b5fd |
| excited | 설렘 | 🥰 | #f9a8d4 |
| tired | 지침 | 😮‍💨 | #a8a29e |
| grateful | 감사 | 🙏 | #fde68a |

## 사용처

- **EmotionPicker** : 버튼 렌더링, 선택 시 `onChange(emotion.id)` 전달
- **DiaryEntry** : `EMOTIONS.find(e => e.id === entry.emotion)` 로 이모지·색상 조회
