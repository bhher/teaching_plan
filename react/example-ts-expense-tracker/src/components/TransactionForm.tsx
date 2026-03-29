import { useEffect, useState, type FormEvent } from "react";
import { DEFAULT_CATEGORIES } from "../constants/categories";
import type { Transaction, TransactionInput } from "../types/transaction";

export interface TransactionFormProps {
  editing: Transaction | null;
  onCreate: (input: TransactionInput) => void;
  onUpdate: (transaction: Transaction) => void;
  onCancelEdit: () => void;
}

export function TransactionForm({
  editing,
  onCreate,
  onUpdate,
  onCancelEdit,
}: TransactionFormProps) {
  const [date, setDate] = useState("");
  const [amount, setAmount] = useState("");
  const [category, setCategory] = useState<string>(DEFAULT_CATEGORIES[0]);

  useEffect(() => {
    if (editing) {
      setDate(editing.date);
      setAmount(String(editing.amount));
      setCategory(editing.category);
    } else {
      setDate(new Date().toISOString().slice(0, 10));
      setAmount("");
      setCategory(DEFAULT_CATEGORIES[0]);
    }
  }, [editing]);

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    const n = Number(amount);
    if (!date || !category.trim() || Number.isNaN(n) || n <= 0) {
      alert("날짜·금액(양수)·카테고리를 확인하세요.");
      return;
    }

    const payload: TransactionInput = { date, amount: n, category: category.trim() };

    if (editing) {
      onUpdate({ ...payload, id: editing.id });
    } else {
      onCreate(payload);
    }
  };

  return (
    <form className="form" onSubmit={handleSubmit}>
      <div className="form-row">
        <label>
          날짜
          <input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
        </label>
        <label>
          금액
          <input
            type="number"
            min={1}
            step={1}
            placeholder="원"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />
        </label>
        <label>
          카테고리
          <select value={category} onChange={(e) => setCategory(e.target.value)}>
            {DEFAULT_CATEGORIES.map((c) => (
              <option key={c} value={c}>
                {c}
              </option>
            ))}
          </select>
        </label>
      </div>
      <div className="form-actions">
        <button type="submit">{editing ? "수정 저장" : "추가"}</button>
        {editing ? (
          <button type="button" className="ghost" onClick={onCancelEdit}>
            수정 취소
          </button>
        ) : null}
      </div>
    </form>
  );
}
