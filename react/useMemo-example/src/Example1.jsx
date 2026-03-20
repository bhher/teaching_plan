import { useState, useMemo } from 'react';

function Example1() {
  const [count, setCount] = useState(0);

  const doubled = useMemo(() => {
    console.log('✅ [useMemo] 계산 실행 - count가 바뀔 때만');
    return count * 2;
  }, [count]);

  return (
    <div className="example-box">
      <h3>예제 1: 간단한 계산 캐시</h3>
      <p>useMemo로 count * 2 결과를 캐시. count가 바뀔 때만 다시 계산.</p>
      <p><strong>count: {count}</strong> → doubled: <strong>{doubled}</strong></p>
      <button className="btn btn-primary" onClick={() => setCount(count + 1)}>count +1</button>
      <div className="log">💡 count 변경 시에만 콘솔에 "계산 실행" 출력</div>
    </div>
  );
}

export default Example1;
