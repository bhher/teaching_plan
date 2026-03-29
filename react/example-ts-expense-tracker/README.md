# 간편 가계부 / 지출 추적기 (입문 · 타입 안전성)

`Transaction` 모델, `useState<Transaction[]>`, 카테고리 합산(`sumByCategory`), 컴포넌트 **Props** 타입으로 CRUD를 연습합니다.

## 실행

```bash
cd example-ts-expense-tracker
npm install
npm run dev
```

## 구조

| 파일 | 연습 포인트 |
|------|-------------|
| `src/types/transaction.ts` | `interface Transaction`, `TransactionInput` |
| `src/lib/sumByCategory.ts` | `Transaction[]` 인자와 반환 타입 |
| `src/App.tsx` | `useState<Transaction[]>` 상태 끌어올리기 |
| `src/components/*.tsx` | `TransactionFormProps`, `TransactionTableProps`, `CategoryTotalsProps` |

Props에 맞지 않는 값을 넘기면 편집기에서 바로 타입 에러가 납니다.
