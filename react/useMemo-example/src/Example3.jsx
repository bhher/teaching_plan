import { useState, useCallback } from 'react';

function Example3() {
  const [count, setCount] = useState(0);

  const handleIncrease = useCallback(() => {
    setCount((c) => c + 1);
  }, []);

  const handleDecrease = useCallback(() => {
    setCount((c) => c - 1);
  }, []);

  return (
    <div className="example-box">
      <h3>예제 1: useCallback - 이벤트 핸들러</h3>
      <p>함수형 업데이트 setCount(c =&gt; c + 1) 사용 → 의존성 [] 가능</p>
      <p><strong>count: {count}</strong></p>
      <button className="btn btn-primary" onClick={handleIncrease}>+1</button>
      <button className="btn btn-primary" onClick={handleDecrease}>-1</button>
      <div className="log">💡 handleIncrease, handleDecrease는 항상 같은 참조</div>
    </div>
  );
}

export default Example3;
