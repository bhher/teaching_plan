import { sumByCategory } from "../lib/sumByCategory";
import type { Transaction } from "../types/transaction";

export interface CategoryTotalsProps {
  transactions: Transaction[];
}

function formatWon(n: number): string {
  return `${n.toLocaleString("ko-KR")}원`;
}

export function CategoryTotals({ transactions }: CategoryTotalsProps) {
  const totals = sumByCategory(transactions);
  const entries = Object.entries(totals).sort(([a], [b]) => a.localeCompare(b, "ko"));

  if (entries.length === 0) {
    return null;
  }

  const grand = entries.reduce((s, [, v]) => s + v, 0);

  return (
    <section className="totals">
      <h2>카테고리별 합계</h2>
      <ul className="totals-list">
        {entries.map(([category, amount]) => (
          <li key={category}>
            <span>{category}</span>
            <span className="num">{formatWon(amount)}</span>
          </li>
        ))}
      </ul>
      <p className="totals-grand">
        <span>전체</span>
        <span className="num">{formatWon(grand)}</span>
      </p>
    </section>
  );
}
