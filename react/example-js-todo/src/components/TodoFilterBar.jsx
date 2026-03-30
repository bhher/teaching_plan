const FILTERS = [
  { value: 'all', label: '전체' },
  { value: 'active', label: '할 일' },
  { value: 'completed', label: '완료' },
];

export function TodoFilterBar({ filter, onChange, activeCount, completedCount }) {
  return (
    <div className="filter-bar">
      {FILTERS.map(({ value, label }) => (
        <button
          key={value}
          type="button"
          className={filter === value ? 'filter active' : 'filter'}
          onClick={() => onChange(value)}
        >
          {label}
          {value === 'active' ? ` (${activeCount})` : null}
          {value === 'completed' ? ` (${completedCount})` : null}
        </button>
      ))}
    </div>
  );
}
