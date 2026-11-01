import { useState, type ChangeEvent } from 'react';

export default function NameInput() {
  const [name, setName] = useState<string>('');
  const onChange = (e: ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value);
  };
  return (
    <input
      className="input"
      value={name}
      placeholder="이름 (tsx)"
      onChange={onChange}
    />
  );
}
