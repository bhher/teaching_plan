import { AREAS, CONTENT_TYPES } from '../api/tour';

export default function Filters({
  areaCode,
  contentTypeId,
  keyword,
  onArea,
  onType,
  onKeywordChange,
  onSearch,
}) {
  return (
    <div className="filters">
      <form
        onSubmit={(e) => {
          e.preventDefault();
          onSearch();
        }}
        className="search-row"
      >
        <input
          type="search"
          value={keyword}
          onChange={(e) => onKeywordChange(e.target.value)}
          placeholder="키워드 검색 (예: 해변, 축제)"
        />
        <button type="submit">검색</button>
      </form>

      <div className="chips">
        <span className="label">지역</span>
        {AREAS.map((a) => (
          <button
            key={a.code || 'all'}
            type="button"
            className={areaCode === a.code ? 'active' : ''}
            onClick={() => onArea(a.code)}
          >
            {a.name}
          </button>
        ))}
      </div>

      <div className="chips">
        <span className="label">유형</span>
        {CONTENT_TYPES.map((t) => (
          <button
            key={t.code || 'all-type'}
            type="button"
            className={contentTypeId === t.code ? 'active' : ''}
            onClick={() => onType(t.code)}
          >
            {t.name}
          </button>
        ))}
      </div>
    </div>
  );
}
