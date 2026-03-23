# CardForm.jsx

## 위치
`flow-board/src/components/CardForm.jsx`

## 역할
**새 카드 추가용 폼**입니다. textarea와 추가/취소 버튼을 제공합니다.

## Props

| prop | 타입 | 설명 |
|------|------|------|
| `onSubmit` | 함수 | (text) — 추가 버튼 클릭 시 호출 |
| `onCancel` | 함수 | 취소 버튼 클릭 또는 Esc 입력 시 |
| `placeholder` | 문자열 | textarea placeholder |

## 상태(State)

| 상태 | 용도 |
|------|------|
| `text` | textarea의 현재 값 (제어 컴포넌트) |

## Ref

| ref | 용도 |
|-----|------|
| `textareaRef` | textarea DOM 요소 참조. 마운트 시 `focus()` 호출 |

## useEffect — 자동 포커스

```js
useEffect(() => {
  textareaRef.current?.focus();
}, []);
```

- **의존성 배열 `[]`** : 마운트 시 1회만 실행
- **이유** : 폼이 나타나자마자 사용자가 바로 타이핑할 수 있도록
- **DOM 타이밍** : 렌더 직후에는 ref가 아직 연결되지 않을 수 있음. useEffect는 DOM 커밋 후 실행되므로 `textareaRef.current`가 설정된 상태

## 폼 전송

- **추가 버튼** : `type="submit"` → `handleSubmit` → `onSubmit(text)` 호출
- `text.trim()`이 비어 있으면 `disabled`로 버튼 비활성화
- 제출 후 Column의 `handleAdd`가 `setIsAdding(false)`로 폼을 닫음

## 키보드 단축키

- **Esc** : `onCancel()` 호출, 입력 취소
