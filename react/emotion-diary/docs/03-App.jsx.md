# App.jsx

## 위치
`emotion-diary/src/App.jsx`

## 역할
전역 **상태 관리**와 **localStorage 동기화**, 일기 CRUD 로직을 담당하는 최상위 컴포넌트입니다.

## 상태(State)

| 상태 | 타입 | 설명 |
|------|------|------|
| `entries` | 배열 | 전체 일기 목록. 추가/삭제 시 업데이트 |

## localStorage

| 키 | 용도 |
|----|------|
| `emotion-diary-entries` | 일기 데이터 JSON 저장 |

### 로드/저장 흐름

- **초기 로드** : `useState(loadEntries)` 로 localStorage에서 파싱
- **저장** : `useEffect`로 `entries` 변경 시마다 `saveEntries` 호출
- **try/catch** : JSON 파싱 실패 시 빈 배열 반환

## 핸들러 함수

| 함수 | 역할 |
|------|------|
| `addEntry(data)` | 새 일기 생성. `crypto.randomUUID()`로 고유 ID 부여, `Date.now()`로 생성 시각 기록 |
| `deleteEntry(id)` | 일기 삭제 |

## 데이터 구조 (entry)

```js
{
  id: 'uuid-string',
  emotion: 'happy',      // constants.js의 id
  content: '일기 내용',
  createdAt: 1234567890  // timestamp
}
```

## 컴포넌트 트리

```
App
└── header (로고, 태그라인)
└── main
    ├── write-section → DiaryForm
    │   └── EmotionPicker, textarea, submit button
    └── history-section → DiaryList
        └── DiaryEntry (각 일기)
```

## 상태 끌어올리기 (Lifting State Up)
- `entries`는 여러 컴포넌트에서 사용되므로 App에 두고 props로 내려보냄
- 일기 변경은 반드시 App의 핸들러를 통해 수행 → 단일 진실 공급원
