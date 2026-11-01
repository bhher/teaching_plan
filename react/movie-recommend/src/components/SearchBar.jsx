export default function SearchBar({ value, onChange, onSubmit }) {
  return (
    <form className="search-bar" onSubmit={onSubmit}>
      <input
        type="search"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        placeholder="영화 제목 검색"
        aria-label="영화 검색"
      />
      <button type="submit">검색</button>
    </form>
  );
}
