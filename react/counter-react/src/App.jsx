import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);

  const increase = () => setCount(count + 1);
  const decrease = () => setCount(count - 1);
  const reset = () => setCount(0);

  return (
    <div className="counter-box">
      <h1>🔢 카운터 (React)</h1>
      <div className="count">{count}</div>
      <div className="buttons">
        <button className="btn btn-decrease" onClick={decrease}>− 감소</button>
        <button className="btn btn-reset" onClick={reset}>초기화</button>
        <button className="btn btn-increase" onClick={increase}>+ 증가</button>
      </div>
    </div>
  );
}

export default Counter;
