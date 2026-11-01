export default function GenreFilter({ genres, selected, onChange }) {
  return (
    <div className="genre-filter">
      <button
        type="button"
        className={!selected ? 'active' : ''}
        onClick={() => onChange('')}
      >
        전체
      </button>
      {genres.map((g) => (
        <button
          key={g.id}
          type="button"
          className={String(selected) === String(g.id) ? 'active' : ''}
          onClick={() => onChange(String(g.id))}
        >
          {g.name}
        </button>
      ))}
    </div>
  );
}
