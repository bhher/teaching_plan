import { useState } from "react";

/**
 * 가이드 5번: useState 타입
 */
export function Counter() {
  const [count, setCount] = useState<number>(0);

  return (
    <div className="row">
      <span>count = {count}</span>
      <button type="button" className="secondary" onClick={() => setCount((c) => c + 1)}>
        +1
      </button>
    </div>
  );
}
