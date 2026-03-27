# Props vs useContext 비교

`example-props`와 `example-context` 두 예제 프로젝트를 기준으로, **같은 UI(테마 + 카운트)**를 어떻게 나누어 구현했는지 정리합니다.

---

## 1. 한눈에 비교

| 구분 | Props (`example-props`) | useContext (`example-context`) |
|------|-------------------------|--------------------------------|
| **상태 위치** | 보통 최상위 부모 `App`의 `useState` | `AppProvider` 안에서 `useState` |
| **자식에게 전달** | 부모 → 자식으로 **props**로 명시적으로 전달 | **Provider**가 값을 제공, 자식은 **`useApp()`** 등으로 구독 |
| **중간 컴포넌트** | 중간에 끼인 컴포넌트가 **쓰지 않아도** props를 넘겨야 할 수 있음 (**props drilling**) | Provider **아래**라면 깊이와 관계없이 **직접** context에서 읽기 가능 |
| **의존성** | “이 컴포넌트는 부모가 어떤 props를 주는지”가 타입/문서로 드러남 | “`useApp()`을 쓰려면 Provider 안에 있어야 함” 규칙이 필요 |
| **적합한 경우** | 부모–자식 1~2단, 범위가 좁을 때 | 여러 단계·많은 컴포넌트가 같은 값을 쓸 때 |

---

## 2. 데이터 흐름 (개념)

### Props

```
App (state: theme, count)
  │ props
  ├─► Toolbar (theme, handlers, count)
  └─► ContentPanel (theme, count)
```

- 화살표는 **부모가 직접 넘겨준 props**만 따라갑니다.

### Context

```
AppProvider (state: theme, count)
  │
  ├─► Toolbar  ── useApp() ─┐
  └─► ContentPanel ─ useApp() ┘── 같은 Context 값 공유
```

- **같은 Provider** 아래에서는 중간에 “전달용” 컴포넌트 없이도 읽을 수 있습니다.

---

## 3. 코드 구조 차이

### Props 예제 (`react/example-props`)

- `src/App.jsx` — `useState`로 `theme`, `count` 관리 후 자식에 props 전달
- `src/components/Toolbar.jsx` — `(theme, onToggleTheme, count, onIncrement)` 등 **props 인자**
- `src/components/ContentPanel.jsx` — `(theme, count)` **props**

### Context 예제 (`react/example-context`)

- `src/context/AppContext.jsx` — `createContext`, **`AppProvider`**, **`useApp`**
- `src/App.jsx` — 최상위를 `<AppProvider>`로 감싸고, 내부에서 `useApp()`으로 테마만 쓰는 `AppShell` 등 구성
- `src/components/Toolbar.jsx` / `ContentPanel.jsx` — **props 없음**, `useApp()`만 사용

---

## 4. 언제 무엇을 쓰면 좋은가

| 상황 | 권장 |
|------|------|
| 부모 한 곳에서만 자식 몇 개로만 내려보내면 될 때 | **Props**가 단순하고 읽기 쉬움 |
| 깊은 트리 여러 곳에서 동일한 “앱 설정 / 로그인 사용자 / 테마” 등 | **Context** (또는 이후 **전역 상태 라이브러리**) 검토 |
| 재사용 가능한 **UI 조각** (버튼, 입력) | 대개 **props**만 받는 **순수에 가까운** 컴포넌트로 두기 쉬움 |

Context를 쓰면 편하지만, **Provider 범위**와 **커스텀 훅(`useApp`) 규칙**을 팀에서 통일하는 것이 좋습니다.

---

## 5. 실행 방법

```bash
# Props 예제
cd react/example-props
npm install
npm run dev
```

```bash
# Context 예제
cd react/example-context
npm install
npm run dev
```

두 프로젝트 모두 **동일한 화면 동작**(테마 전환, 카운트 증가)을 합니다. 코드만 열어 비교해 보시면 됩니다.

---

## 6. 요약

- **Props**: “위에서 아래로 명시적으로 전달” — 범위가 작을 때 가장 직관적입니다.
- **useContext**: “Provider가 한 번 제공하면, 아래에서는 훅으로 구독” — **props drilling**을 줄이고 싶을 때 유용합니다.

둘 중 하나만 옳은 것은 아니며, **범위·팀 규모·재사용**에 맞게 선택하면 됩니다.
