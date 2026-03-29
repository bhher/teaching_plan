import { useState } from "react";
import { CategoryTotals } from "./components/CategoryTotals";
import { TransactionForm } from "./components/TransactionForm";
import { TransactionTable } from "./components/TransactionTable";
import type { Transaction, TransactionInput } from "./types/transaction";

function nextId(list: Transaction[]): number {
  if (list.length === 0) return 1;
  return Math.max(...list.map((t) => t.id)) + 1;
}

export default function App() {
  const [transactions, setTransactions] = useState<Transaction[]>([
    { id: 1, date: "2026-03-01", amount: 12000, category: "식비" },
    { id: 2, date: "2026-03-02", amount: 2500, category: "교통" },
    { id: 3, date: "2026-03-02", amount: 15000, category: "식비" },
  ]);
  const [editing, setEditing] = useState<Transaction | null>(null);

  const handleCreate = (input: TransactionInput) => {
    setTransactions((prev) => [...prev, { ...input, id: nextId(prev) }]);
  };

  const handleUpdate = (updated: Transaction) => {
    setTransactions((prev) => prev.map((t) => (t.id === updated.id ? updated : t)));
    setEditing(null);
  };

  const handleDelete = (id: number) => {
    setTransactions((prev) => prev.filter((t) => t.id !== id));
    setEditing((e) => (e?.id === id ? null : e));
  };

  const handleEdit = (id: number) => {
    const found = transactions.find((t) => t.id === id);
    setEditing(found ?? null);
  };

  return (
    <div className="app">
      <header className="header">
        <h1>간편 가계부 · 지출 추적 (TypeScript)</h1>
        <p className="lead">
          <code>Transaction</code> 모델 · <code>useState&lt;Transaction[]&gt;</code> · 카테고리 합산 함수 ·
          컴포넌트 Props 타입으로 CRUD를 연습합니다.
        </p>
      </header>

      <main className="main">
        <section className="panel">
          <h2>{editing ? "지출 수정" : "지출 추가"}</h2>
          <TransactionForm
            editing={editing}
            onCreate={handleCreate}
            onUpdate={handleUpdate}
            onCancelEdit={() => setEditing(null)}
          />
        </section>

        <CategoryTotals transactions={transactions} />

        <section className="panel">
          <h2>내역</h2>
          <TransactionTable
            transactions={transactions}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
        </section>
      </main>
    </div>
  );
}
