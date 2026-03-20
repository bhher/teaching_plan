import { useState, useCallback, memo } from 'react';

const Child = memo(({ onClick, label }) => {
  console.log('Child 렌더:', label);
  return <button className="btn btn-primary" onClick={onClick}>{label}</button>;
});

function Example4() {
  const [count, setCount] = useState(0);

  const handleClick = useCallback(() => {
    setCount((c) => c + 1);
  }, []);

  return (
    <div className="example-box">
      <h3>예제 2: useCallback + React.memo</h3>
      <p>Parent 리렌더 시에도 Child는 handleClick 참조가 같아서 리렌더 안 함</p>
      <p><strong>count: {count}</strong></p>
      <Child onClick={handleClick} label="클릭" />
      <div className="log">💡 콘솔에서 "Child 렌더"는 count 변경 시에만 출력 (useCallback 없으면 매번 출력)</div>
    </div>
  );
}

export default Example4;
