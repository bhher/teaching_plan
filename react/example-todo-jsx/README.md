# Todo (JavaScript + JSX)

`example-ts-todo`와 **같은 UI·같은 Props 이름**(`editing`, `onCreate`, `onUpdate`, `todos`, `onToggle` …)으로 동작하지만, **TypeScript 없이** `.jsx` / `.js` 만 사용합니다.

## 실행

```bash
cd example-todo-jsx
npm install
npm run dev
```

## 비교

| 항목 | `example-ts-todo` | `example-todo-jsx` (여기) |
|------|-------------------|---------------------------|
| 확장자 | `.tsx`, `.ts` | `.jsx`, `.js` |
| 타입 | `interface`, `useState<T>` 등 | 없음 (`todoQueries.js`에 JSDoc만 선택) |
| 빌드 | `tsc` + Vite | Vite만 |

상세 이론은 `example-ts-todo/Todo-TypeScript-상세설명.md`를 참고하면 됩니다.
