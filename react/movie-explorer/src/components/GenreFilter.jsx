const GENRES = [
  { id: null, label: '전체' },
  { id: 28, label: '액션' },
  { id: 10749, label: '로맨스' },
  { id: 35, label: '코미디' },
];

export default function GenreFilter({ value, onChange }) {
  return (
    <div className="genre-filter">
      <span className="genre-filter__label">장르</span>
      <div className="genre-filter__buttons">
        {GENRES.map((g) => (
          <button
            key={g.label}
            type="button"
            className={`genre-filter__btn ${value === g.id ? 'genre-filter__btn--active' : ''}`}
            onClick={() => onChange(g.id)}
          >
            {g.label}
          </button>
        ))}
      </div>
    </div>
  );
}
