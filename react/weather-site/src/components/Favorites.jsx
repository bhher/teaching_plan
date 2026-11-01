export default function Favorites({ list, onSelect, onRemove }) {
  if (!list.length) return null;
  return (
    <section className="panel favorites">
      <h3>즐겨찾기 지역</h3>
      <ul>
        {list.map((f) => (
          <li key={`${f.lat}-${f.lon}`}>
            <button type="button" className="linkish" onClick={() => onSelect(f)}>
              {f.label}
            </button>
            <button
              type="button"
              className="remove"
              onClick={() => onRemove(f)}
              aria-label="즐겨찾기 삭제"
            >
              ×
            </button>
          </li>
        ))}
      </ul>
    </section>
  );
}
