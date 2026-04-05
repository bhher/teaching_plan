export default function SearchBar({ value, onChange, onSearchClick }) {
  return (
    <form
      className="search-bar"
      onSubmit={(e) => {
        e.preventDefault();
        onSearchClick?.();
      }}
    >
      <input
        type="search"
        placeholder="영화 제목 검색… (입력 후 약 0.4초 뒤 자동 검색)"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className="search-bar__input"
      />
      <button type="submit" className="search-bar__btn">
        검색
      </button>
    </form>
  );
}
