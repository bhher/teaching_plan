# example-js-todo

`example-ts-todo`와 **동일한 Todo 앱**을 **JavaScript(.jsx)** 로 옮긴 프로젝트입니다.

## TS 버전과 차이

- `.tsx` → `.jsx`, 타입/`types` 폴더 없음
- `vite.config.ts` → `vite.config.js`, `build` 시 `tsc` 없음
- **추가 파일**: `src/components/TodoPageHeader.jsx` — 제목·설명 영역만 분리 (props: `title`, `children`)

## 실행

```bash
cd react/example-js-todo
npm install
npm run dev
```

## 대응

| TS | JS |
|----|-----|
| `example-ts-todo` | `example-js-todo` |
