import { useState, useCallback, useMemo } from 'react';
import Column from './Column';

function KanbanBoard({ columns, cards, onAddCard, onMoveCard, onDeleteCard, onUpdateCard }) {
  const [draggedCard, setDraggedCard] = useState(null);
  const [dragOverColumn, setDragOverColumn] = useState(null);

  const columnCardMap = useMemo(() => {
    const map = {};
    columns.forEach((col) => {
      map[col.id] = [];
    });
    cards.forEach((card) => {
      if (map[card.columnId]) {
        map[card.columnId].push(card);
      }
    });
    return map;
  }, [cards, columns]);

  const handleDragStart = useCallback((e, card) => {
    setDraggedCard(card);
    e.dataTransfer.effectAllowed = 'move';
    e.dataTransfer.setData('text/plain', card.id);
    e.target.style.opacity = '0.5';
  }, []);

  const handleDragEnd = useCallback((e) => {
    e.target.style.opacity = '1';
    setDraggedCard(null);
    setDragOverColumn(null);
  }, []);

  const handleDragOver = useCallback((e, columnId) => {
    e.preventDefault();
    e.dataTransfer.dropEffect = 'move';
    setDragOverColumn(columnId);
  }, []);

  const handleDragLeave = useCallback(() => {
    setDragOverColumn(null);
  }, []);

  const handleDrop = useCallback(
    (e, targetColumnId) => {
      e.preventDefault();
      const cardId = e.dataTransfer.getData('text/plain');
      if (cardId && draggedCard && draggedCard.columnId !== targetColumnId) {
        onMoveCard(cardId, targetColumnId);
      }
      setDraggedCard(null);
      setDragOverColumn(null);
    },
    [draggedCard, onMoveCard]
  );

  return (
    <main className="board">
      {columns.map((column) => (
        <div
          key={column.id}
          className={`column-wrapper ${dragOverColumn === column.id ? 'drag-over' : ''}`}
          onDragOver={(e) => handleDragOver(e, column.id)}
          onDragLeave={handleDragLeave}
          onDrop={(e) => handleDrop(e, column.id)}
        >
          <Column
            column={column}
            cards={columnCardMap[column.id] || []}
            onAddCard={onAddCard}
            onDeleteCard={onDeleteCard}
            onUpdateCard={onUpdateCard}
            onDragStart={handleDragStart}
            onDragEnd={handleDragEnd}
            isDragging={draggedCard?.columnId === column.id}
          />
        </div>
      ))}
    </main>
  );
}

export default KanbanBoard;
