import { useState, useCallback, memo } from 'react';
import Card from './Card';
import CardForm from './CardForm';

function Column({ column, cards, onAddCard, onDeleteCard, onUpdateCard, onDragStart, onDragEnd, isDragging }) {
  const [isAdding, setIsAdding] = useState(false);

  const handleAdd = useCallback(
    (text) => {
      onAddCard(column.id, text);
      setIsAdding(false);
    },
    [onAddCard, column.id]
  );

  const handleCancelForm = useCallback(() => {
    setIsAdding(false);
  }, []);

  const handleOpenAdd = useCallback(() => {
    setIsAdding(true);
  }, []);

  return (
    <section className="column" style={{ '--column-accent': column.color }}>
      <div className="column-header">
        <h2 className="column-title">{column.title}</h2>
        <span className="column-count">{cards.length}</span>
      </div>
      <div className={`column-cards ${isDragging ? 'dragging' : ''}`}>
        {cards.map((card) => (
          <Card
            key={card.id}
            card={card}
            onDelete={onDeleteCard}
            onUpdate={onUpdateCard}
            onDragStart={onDragStart}
            onDragEnd={onDragEnd}
          />
        ))}
      </div>
      {isAdding ? (
        <CardForm
          onSubmit={handleAdd}
          onCancel={handleCancelForm}
          placeholder="작업 내용 입력..."
        />
      ) : (
        <button
          className="add-btn"
          onClick={handleOpenAdd}
          aria-label="카드 추가"
        >
          + 카드 추가
        </button>
      )}
    </section>
  );
}

export default memo(Column);
