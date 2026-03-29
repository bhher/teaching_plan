# example-props · example-context 예제 설명서

React에서 **상태를 자식 컴포넌트에 넘기는 두 가지 방식**을 같은 UI(테마 전환 + 카운터)로 비교할 수 있도록 만든 교육용 예제입니다.

| 프로젝트 | 핵심 아이디어 |
|----------|----------------|
| `example-props` | 부모(`App`)가 `useState`로 상태를 들고, **props**로 자식에 전달 |
| `example-context` | `AppProvider`가 상태를 들고, 자식은 **`useContext` 커스텀 훅(`useApp`)**으로 구독 |

---

## 1. 공통으로 하는 일

두 프로젝트 모두 화면에서 다음을 수행합니다.

- **테마**: `light` / `dark` 전환 → 루트에 `className="app light"` 또는 `app dark` 적용
- **카운트**: 버튼으로 숫자 +1
- **구성**: `Toolbar`(버튼 영역) + `ContentPanel`(테마·카운트 표시)

스타일(`index.css`)도 거의 동일하며, 배지 색만 props 예제는 파란 계열, context 예제는 초록 계열로 구분되어 있습니다.

---

## 2. example-props

### 2.1 목적

- **Props**로 데이터와 함수(이벤트 핸들러)를 자식에게 넘기는 패턴을 익힙니다.
- 상태는 **항상 `App.jsx` 한곳**에 두고, 자식은 **읽기 전용으로 props만** 받는 형태입니다.
- 컴포넌트 트리가 깊어지면 같은 props를 중간 컴포넌트들이 그대로 전달만 해야 하는 **props drilling** 문제가 생길 수 있다는 점을 연상할 수 있습니다. (이 예제는 깊이가 얕아 drilling이 두 단계 수준입니다.)

### 2.2 폴더 구조

```
example-props/
├── index.html
├── package.json
├── vite.config.js
└── src/
    ├── main.jsx          # React 루트 마운트
    ├── index.css         # 테마·패널·버튼 스타일
    ├── App.jsx           # 상태 + 자식에 props 전달
    └── components/
        ├── Toolbar.jsx   # theme, count, 핸들러를 props로 수신
        └── ContentPanel.jsx
```

### 2.3 데이터 흐름

1. `App`에서 `useState`로 `theme`, `count` 관리
2. `toggleTheme`, `onIncrement` 등 핸들러를 `App`에서 정의
3. `Toolbar`에 `theme`, `onToggleTheme`, `count`, `onIncrement`를 **props로 전달**
4. `ContentPanel`에 `theme`, `count`를 **props로 전달**

자식 컴포넌트는 **props 인자만** 받아 UI를 그립니다.

### 2.4 파일별 역할

#### `src/main.jsx`

- `createRoot`로 `#root`에 `App`을 렌더링합니다.
- `StrictMode`로 개발 시 잠재적 문제를 한 번 더 검사합니다.

#### `src/App.jsx`

- `theme`, `count` 상태와 `toggleTheme` 등 로직의 **단일 소스**입니다.
- `<Toolbar ... />`, `<ContentPanel ... />`에 필요한 값·함수를 모두 넘깁니다.
- 최상위 `div`에 `className={\`app ${theme}\`}`로 테마 클래스를 붙입니다.

#### `src/components/Toolbar.jsx`

- 구조 분해로 `{ theme, onToggleTheme, count, onIncrement }`를 받습니다.
- 테마 전환 버튼, 카운트 +1 버튼을 렌더링하고 `onClick`에 부모가 넘긴 함수를 연결합니다.

#### `src/components/ContentPanel.jsx`

- `{ theme, count }`만 받아 표시용으로 사용합니다. (버튼 없음)

#### `src/index.css`

- `.app.light`, `.app.dark`로 배경·글자색 전환
- `.panel`, `.btn-toggle`, `.counter` 등 레이아웃·버튼 스타일

### 2.5 실행 방법

```bash
cd example-props
npm install
npm run dev
```

---

## 3. example-context

### 3.1 목적

- **Context API**로 “앱 전역에 가까운” 상태를 제공하고, 자식은 **커스텀 훅 `useApp()`**으로 읽습니다.
- `Toolbar`, `ContentPanel`은 **props 없이** 동일한 `theme`, `count`, `toggleTheme`, `increment`에 접근합니다.
- 트리가 깊어질 때 props를 중간에 계속 넘기지 않아도 되는 패턴을 보여 줍니다.

### 3.2 폴더 구조

```
example-context/
├── index.html
├── package.json
├── vite.config.js
└── src/
    ├── main.jsx
    ├── index.css
    ├── App.jsx                 # AppProvider로 감싸고 AppShell 렌더
    ├── context/
    │   └── AppContext.jsx      # Context, Provider, useApp
    └── components/
        ├── Toolbar.jsx         # useApp()만 사용
        └── ContentPanel.jsx
```

### 3.3 데이터 흐름

1. `App` 최상단에서 `<AppProvider>`로 하위 전체를 감쌉니다.
2. `AppProvider` 내부에서 `useState`로 `theme`, `count` 관리
3. `toggleTheme`, `increment`를 객체로 묶어 `AppContext.Provider`의 **`value`**로 전달
4. `AppShell`(또는 자식)은 `useApp()`으로 `theme`을 읽어 `div` 클래스에 반영
5. `Toolbar`, `ContentPanel`은 각각 `useApp()`으로 필요한 필드만 구독

### 3.4 파일별 역할

#### `src/context/AppContext.jsx`

- `createContext(null)`로 컨텍스트 생성
- **`AppProvider`**: 실제 상태와 함수를 가지고 `Provider`로 `children`을 감쌉니다.
- **`useMemo`**: `value` 객체 `{ theme, count, toggleTheme, increment }`를 메모이제이션합니다.  
  - `theme`, `count`가 바뀔 때만 새 객체를 만들어, 불필요한 하위 리렌더를 줄이는 습관을 보여 줍니다.
- **`useApp`**: `useContext(AppContext)`를 감싼 커스텀 훅입니다.
  - Provider 밖에서 호출하면 에러를 던져, 사용 위치를 명확히 합니다.

#### `src/App.jsx`

- 기본 export `App`은 `<AppProvider><AppShell /></AppProvider>`만 반환합니다.
- `AppShell`은 `useApp()`으로 `theme`을 읽고, `Toolbar` / `ContentPanel`에는 **props를 넘기지 않습니다.**

#### `src/components/Toolbar.jsx` · `ContentPanel.jsx`

- `import { useApp } from '../context/AppContext'`
- `const { ... } = useApp()`으로 값·함수를 가져와 UI에 연결합니다.

#### `src/index.css`

- props 예제와 동일한 구조이며, 배지 색 등만 살짝 다릅니다.

### 3.5 실행 방법

```bash
cd example-context
npm install
npm run dev
```

---

## 4. 두 예제 비교 정리

| 구분 | example-props | example-context |
|------|----------------|-----------------|
| 상태 위치 | `App.jsx` | `AppContext.jsx`의 `AppProvider` |
| 자식에 데이터 전달 | props | Context `value` + `useApp()` |
| Toolbar / ContentPanel 시그니처 | props 인자 있음 | props 없음 (훅으로 접근) |
| 중간 컴포넌트가 많아질 때 | 중간에서 props 전달 누적 가능 | Provider 아래면 깊이와 무관하게 구독 가능 |
| 적합한 경우 | 소규모, 부모-자식 관계가 단순할 때 | 여러 화면·깊은 트리에서 동일 상태 공유가 잦을 때 |

**주의**: Context는 “모든 전역 상태”에 무조건 쓰기보다, **정말 여러 곳에서 같은 데이터가 필요할 때** 선택하는 편이 유지보수에 유리합니다. 작은 범위에서는 props가 더 명시적일 수 있습니다.

---

## 5. 학습 체크리스트

- [ ] `example-props`에서 `App` → `Toolbar`로 넘어가는 props 이름과 타입(값 vs 함수)을 짝지어 설명할 수 있다.
- [ ] `example-context`에서 `Provider`의 `value`가 바뀌면 구독 컴포넌트가 리렌더된다는 것을 이해한다.
- [ ] `useMemo`로 `value`를 감싼 이유(참조 동일성과 리렌더)를 말로 설명할 수 있다.
- [ ] `useApp`을 Provider 밖에서 쓰면 왜 에러가 나는지 안다.
- [ ] 언제 props를 쓰고 언제 Context를 쓸지 한 문장으로 구분할 수 있다.

---

## 6. 기술 스택

두 프로젝트 공통:

- React 18
- Vite 5
- `@vitejs/plugin-react`

문서 기준 경로: `teaching_plan/react/example-props`, `teaching_plan/react/example-context`
