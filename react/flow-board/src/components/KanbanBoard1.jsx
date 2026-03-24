import { useState, useCallback, useMemo } from 'react';
import Column from './Column';

function KanbanBoard1({ columns, cards, onMoveCard, onAddCard, onDeleteCard, onUpdateCard }) {
  const [draggedCard, setDraggedCard] = useState(null);
  const [dragOverColumn, setDragOverColumn] = useState(null);

  // 🔥 카드 그룹핑 최적화
  const columnCardMap = useMemo(() => {
    const map = {};
    columns.forEach(col => {
      map[col.id] = [];
    });

    cards.forEach(card => {
      if (map[card.columnId]) {
        map[card.columnId].push(card);
      }
    });

    return map;
  }, [cards, columns]);

  // 🔥 useCallback 적용
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
    setDragOverColumn(columnId);
  }, []);

  const handleDrop = useCallback((e, targetColumnId) => {
    e.preventDefault();
    const cardId = e.dataTransfer.getData('text/plain');

    if (cardId && draggedCard?.columnId !== targetColumnId) {
      onMoveCard(cardId, targetColumnId);
    }

    setDraggedCard(null);
    setDragOverColumn(null);
  }, [draggedCard, onMoveCard]);

  return (
    <div className="kanban-board">
      {columns.map((column) => (
        <div
          key={column.id}
          onDragOver={(e) => handleDragOver(e, column.id)}
          onDrop={(e) => handleDrop(e, column.id)}
          className={dragOverColumn === column.id ? 'drag-over' : ''}
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
    </div>
  );
}

export default KanbanBoard1;
