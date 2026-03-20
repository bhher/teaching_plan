import { useState, useMemo } from 'react';

function factorial(n) {
  console.log('✅ [useMemo] 팩토리얼 계산:', n);
  if (n <= 1) return 1;
  return n * factorial(n - 1);
}

function Example2() {
  const [count, setCount] = useState(5);
  const [name, setName] = useState('');

  const result = useMemo(() => factorial(count), [count]);
  //const result = factorial(count);
  return (
    <div className="example-box">
      <h3>예제 2: 비용 큰 연산 (팩토리얼)</h3>
      <p>name 입력 시 리렌더링되지만, result는 count가 바뀔 때만 재계산.</p>
      <p><strong>팩토리얼({count}) = {result}</strong></p>
      <div>
        <input
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="이름 입력 (리렌더링 발생)"
        />
        <button className="btn btn-primary" onClick={() => setCount(count + 1)}>숫자 +1</button>
      </div>
      <div className="log">💡 name 입력해도 "팩토리얼 계산" 안 나옴. 숫자+1 할 때만!</div>
    </div>
  );
}

export default Example2;
