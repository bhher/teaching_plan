# EmotionPicker.jsx

## 위치
`emotion-diary/src/components/EmotionPicker.jsx`

## 역할
**감정 선택 버튼**을 렌더링합니다. 사용자가 오늘의 감정을 하나 선택할 수 있게 합니다.

## Props

| prop | 타입 | 설명 |
|------|------|------|
| `value` | 문자열 | 현재 선택된 감정 id (비어 있으면 미선택) |
| `onChange` | 함수 | (emotionId) — 감정 선택 시 호출 |

## 렌더링

- `constants.js`의 `EMOTIONS` 배열을 순회하여 각 감정별 버튼 생성
- 선택된 버튼에 `selected` 클래스 적용 → border·background 색상 변경
- `style={{ '--emotion-color': emotion.color }}` : CSS 변수로 선택 시 해당 감정 색 사용

## CSS 변수

```css
.emotion-btn.selected {
  border-color: var(--emotion-color);
  background: color-mix(in srgb, var(--emotion-color) 15%, white);
}
```

- 각 버튼이 `--emotion-color`를 인라인으로 설정 → 선택 시 감정별 고유 색 적용

## 버튼 구성

- `emotion-emoji` : 이모지
- `emotion-text` : 한글 라벨 (행복, 슬픔 등)
- `title` : 호버 시 툴팁
