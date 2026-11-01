import { useState } from 'react';

const products = [
  { id: 1, name: '노트북', price: 1200000, quantity: 1 },
  { id: 2, name: '마우스', price: 30000, quantity: 1 },
  { id: 3, name: '키보드', price: 80000, quantity: 1 },
];

/** ② 장바구니 — useState, map, 불변성 업데이트 */
export default function Practice02Cart() {
  const [cart, setCart] = useState(products);

  const increase = (id) => {
    setCart(
      cart.map((item) =>
        item.id === id ? { ...item, quantity: item.quantity + 1 } : item
      )
    );
  };

  const decrease = (id) => {
    setCart(
      cart.map((item) =>
        item.id === id
          ? { ...item, quantity: Math.max(1, item.quantity - 1) }
          : item
      )
    );
  };

  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  return (
    <div className="panel">
      <h2>② 🛒 간단한 장바구니</h2>
      <p className="goal">학습: useState · map · 불변성 ({`{ ...item, quantity }`}) · 총액</p>

      <h3 style={{ marginBottom: 8 }}>상품 목록</h3>

      {cart.map((item) => (
        <div className="list-item" key={item.id}>
          <div>
            <strong>{item.name}</strong>
            <div className="price">{item.price.toLocaleString()}원</div>
          </div>
          <div className="row" style={{ marginBottom: 0 }}>
            <button type="button" className="btn btn-ghost btn-sm" onClick={() => decrease(item.id)}>
              -
            </button>
            <span style={{ minWidth: 24, textAlign: 'center' }}>{item.quantity}</span>
            <button type="button" className="btn btn-sm" onClick={() => increase(item.id)}>
              +
            </button>
          </div>
        </div>
      ))}

      <div className="total">총 금액 : {total.toLocaleString()}원</div>

      <div className="hint">
        핵심: <code>{`{ ...item, quantity: item.quantity + 1 }`}</code> — 기존 객체를 직접 수정하지
        않고 새 객체로 상태를 변경합니다.
      </div>
    </div>
  );
}
