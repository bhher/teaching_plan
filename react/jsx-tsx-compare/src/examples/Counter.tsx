import { useState } from 'react';

/** TSX — props · state 타입을 컴파일 단계에서 검사 */
type CounterProps = {
  label: string;
};

export default function Counter({ label }: CounterProps) {
  const [count, setCount] = useState<number>(0);

  const onPlus = (): void => {
    setCount(count + 1);
  };

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
