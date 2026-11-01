import { useState } from 'react';

/** JSX — 타입 선언 없음. props 검증은 런타임/문서에 의존 */
export default function Counter({ label }) {
  const [count, setCount] = useState(0);

  const onPlus = () => setCount(count + 1);

  return (
    <div className="demo-box">
      <p className="demo-label">{label}</p>
      <p className="demo-value">{count}</p>
      <button type="button" onClick={onPlus}>
        +1
      </button>
    </div>
  );
}
