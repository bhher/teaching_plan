import { useState, useCallback, useEffect, memo } from 'react';

function Card({ card, onDelete, onUpdate, onDragStart, onDragEnd }) {
  const [isEditing, setIsEditing] = useState(false);
  const [editText, setEditText] = useState(card.text);

  useEffect(() => {
    setEditText(card.text);
  }, [card.text]);

  const handleSave = useCallback(() => {
    const trimmed = editText.trim();
    if (trimmed && trimmed !== card.text) {
      onUpdate(card.id, trimmed);
    } else if (!trimmed) {
      setEditText(card.text);
    }
    setIsEditing(false);
  }, [editText, card.id, card.text, onUpdate]);

  const handleKeyDown = useCallback(
    (e) => {
      if (e.key === 'Enter') {
        e.preventDefault();
        handleSave();
      }
      if (e.key === 'Escape') {
        setEditText(card.text);
        setIsEditing(false);
      }
    },
    [handleSave, card.text]
  );

  const handleDoubleClick = useCallback(() => {
    setIsEditing(true);
  }, []);

  const handleDelete = useCallback(() => {
    onDelete(card.id);
  }, [onDelete, card.id]);

  const handleDragStart = useCallback(
    (e) => {
      onDragStart(e, card);
    },
    [onDragStart, card]
  );

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
      onDragStart={handleDragStart}
      onDragEnd={onDragEnd}
    >
      <p className="card-text" onDoubleClick={handleDoubleClick}>
        {card.text}
      </p>
      <button
        className="card-delete"
        onClick={handleDelete}
        aria-label="삭제"
      >
        ×
      </button>
    </div>
  );
}

export default memo(Card);
