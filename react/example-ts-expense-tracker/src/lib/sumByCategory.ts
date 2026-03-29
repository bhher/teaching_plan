import type { Transaction } from "../types/transaction";

/**
 * 카테고리별 금액 합산.
 * `Transaction[]`만 넘기면 키가 카테고리, 값이 합계인 객체가 됩니다.
 */
export function sumByCategory(transactions: Transaction[]): Record<string, number> {
  return transactions.reduce<Record<string, number>>((acc, t) => {
    const key = t.category;
    acc[key] = (acc[key] ?? 0) + t.amount;
    return acc;
  }, {});
}
