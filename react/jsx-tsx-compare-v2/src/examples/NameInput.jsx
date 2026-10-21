import { useState } from 'react';

export default function NameInput() {
  const [name, setName] = useState('');
  return (
    <input
      className="input"
      value={name}
      placeholder="이름 (jsx)"
      onChange={(e) => setName(e.target.value)}
    />
  );
}
