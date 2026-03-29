# example-ts-basics

`React-TypeScript-Vite-시작-핵심개념.md` 가이드의 **핵심 5가지**를 한 화면에서 돌려볼 수 있는 Vite + React + TypeScript 예제입니다.

## 실행

```bash
cd example-ts-basics
npm install
npm run dev
```

## 파일 구조 (개념별)

| 개념 | 주요 파일 |
|------|-----------|
| 타입 선언 | `src/lib/basicTypes.ts`, `App.tsx` (유니온 토글) |
| 함수 타입 | `src/lib/math.ts` |
| interface | `src/types/user.ts`, `ProfileForm.tsx` |
| Props | `src/components/Card.tsx`, `Layout.tsx`, `DemoButton.tsx` |
| useState | `src/components/Counter.tsx`, `ProfileForm.tsx` |

## 빌드

```bash
npm run build
```

타입 검사는 `tsc --noEmit` 후 Vite 빌드가 실행됩니다.
