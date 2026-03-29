import type { Transaction } from "../types/transaction";

export interface TransactionTableProps {
  transactions: Transaction[];
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}

function formatWon(n: number): string {
  return `${n.toLocaleString("ko-KR")}원`;
}

export function TransactionTable({ transactions, onEdit, onDelete }: TransactionTableProps) {
  if (transactions.length === 0) {
    return <p className="muted">내역이 없습니다. 위 폼에서 추가해 보세요.</p>;
  }

  const sorted = [...transactions].sort((a, b) => b.date.localeCompare(a.date) || b.id - a.id);

  return (
    <div className="table-wrap">
      <table className="data-table">
        <thead>
          <tr>
            <th>날짜</th>
            <th>카테고리</th>
            <th className="num">금액</th>
            <th />
          </tr>
        </thead>
        <tbody>
          {sorted.map((t) => (
            <tr key={t.id}>
              <td>{t.date}</td>
              <td>{t.category}</td>
              <td className="num">{formatWon(t.amount)}</td>
              <td className="actions">
                <button type="button" className="small" onClick={() => onEdit(t.id)}>
                  수정
                </button>
                <button type="button" className="small danger" onClick={() => onDelete(t.id)}>
                  삭제
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
