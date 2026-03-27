# example-props

**Props**로 부모(`App`) → 자식(`Toolbar`, `ContentPanel`)에 `theme`, `count`, 핸들러를 넘깁니다.

## 실행

```bash
cd react/example-props
npm install
npm run dev
```

## 구조

```
App.jsx          ← useState로 theme, count 보유
  ├── Toolbar.jsx    ← props: theme, onToggleTheme, count, onIncrement
  └── ContentPanel.jsx ← props: theme, count
```

깊이가 깊어지면 중간 컴포넌트가 “전달만” 하는 **props drilling**이 됩니다.
