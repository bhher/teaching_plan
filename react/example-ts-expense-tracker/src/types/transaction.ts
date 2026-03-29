/** 지출(거래) 한 건 — 데이터 모델 */
export interface Transaction {
  id: number;
  date: string;
  amount: number;
  category: string;
}

/** 생성 시에는 id 없이 넘김 (부모가 id 부여) */
export type TransactionInput = Omit<Transaction, "id">;
