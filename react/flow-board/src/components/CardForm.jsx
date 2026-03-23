import { useState, useRef, useEffect } from 'react';

function CardForm({ onSubmit, onCancel, placeholder }) {
  const [text, setText] = useState('');
  const textareaRef = useRef(null);

  useEffect(() => {
    textareaRef.current?.focus();
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(text);
  };

  return (
    <form className="card-form" onSubmit={handleSubmit}>
      <textarea
        ref={textareaRef}
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder={placeholder}
        rows={2}
        onKeyDown={(e) => {
          if (e.key === 'Escape') onCancel();
        }}
      />
      <div className="card-form-actions">
        <button type="submit" className="btn-save" disabled={!text.trim()}>
          추가
        </button>
        <button type="button" className="btn-cancel" onClick={onCancel}>
          취소
        </button>
      </div>
    </form>
  );
}

export default CardForm;
