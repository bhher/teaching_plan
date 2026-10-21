export default function SearchForm({ query, onChange, onSubmit, loading }) {
  return (
    <form className="search-form" onSubmit={onSubmit}>
      <input
        type="search"
        value={query}
        onChange={(e) => onChange(e.target.value)}
        placeholder="도시명 검색 (예: Seoul, Busan)"
        aria-label="도시 검색"
      />
      <button type="submit" disabled={loading || !query.trim()}>
        {loading ? '검색 중…' : '검색'}
      </button>
    </form>
  );
}
