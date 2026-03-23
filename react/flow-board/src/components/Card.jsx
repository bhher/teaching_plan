import { useState } from 'react';

function Card({ card, onDelete, onUpdate, onDragStart, onDragEnd }) {
  const [isEditing, setIsEditing] = useState(false);
  const [editText, setEditText] = useState(card.text);

  const handleSave = () => {
    const trimmed = editText.trim();
    if (trimmed && trimmed !== card.text) {
      onUpdate(card.id, trimmed);
    } else if (!trimmed) {
      setEditText(card.text);
    }
    setIsEditing(false);
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      e.preventDefault();
      handleSave();
    }
    if (e.key === 'Escape') {
      setEditText(card.text);
      setIsEditing(false);
    }
  };

  if (isEditing) {
    return (
      <div className="card card-editing">
        <textarea
          value={editText}
          onChange={(e) => setEditText(e.target.value)}
          onBlur={handleSave}
          onKeyDown={handleKeyDown}
          autoFocus
          rows={3}
        />
      </div>
    );
  }

  return (
    <div
      className="card"
      draggable
      onDragStart={(e) => onDragStart(e, card)}
      onDragEnd={onDragEnd}
    >
      <p className="card-text" onDoubleClick={() => setIsEditing(true)}>
        {card.text}
      </p>
      <button
        className="card-delete"
        onClick={() => onDelete(card.id)}
        aria-label="삭제"
      >
        ×
      </button>
    </div>
  );
}

export default Card;
