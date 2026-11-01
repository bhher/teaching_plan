import { useEffect, useState } from 'react';
import { fetchPlaces, hasApiKey } from '../api/tour';
import Filters from '../components/Filters';
import PlaceCard from '../components/PlaceCard';
import Pagination from '../components/Pagination';

export default function Home() {
  const [areaCode, setAreaCode] = useState('1');
  const [contentTypeId, setContentTypeId] = useState('');
  const [keyword, setKeyword] = useState('');
  const [appliedKeyword, setAppliedKeyword] = useState('');
  const [page, setPage] = useState(1);
  const [list, setList] = useState([]);
  const [totalCount, setTotalCount] = useState(0);
  const [pageSize, setPageSize] = useState(12);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    let cancelled = false;
    async function load() {
      setLoading(true);
      setError('');
      try {
        const data = await fetchPlaces({
          areaCode,
          contentTypeId,
          pageNo: page,
          keyword: appliedKeyword,
        });
        if (!cancelled) {
          setList(data.list);
          setTotalCount(data.totalCount);
          setPageSize(data.numOfRows || 12);
        }
      } catch (e) {
        if (!cancelled) {
          setError(e.message || '목록을 불러오지 못했습니다.');
          setList([]);
        }
      } finally {
        if (!cancelled) setLoading(false);
      }
    }
    load();
    return () => {
      cancelled = true;
    };
  }, [areaCode, contentTypeId, page, appliedKeyword]);

  return (
    <div>
      <header className="page-head">
        <h1>국내 여행지 추천</h1>
        <p>
          지역별 관광지 · 축제 · 음식점
          {!hasApiKey() && <span className="badge">데모 모드</span>}
        </p>
      </header>

      <Filters
        areaCode={areaCode}
        contentTypeId={contentTypeId}
        keyword={keyword}
        onArea={(code) => {
          setAreaCode(code);
          setPage(1);
        }}
        onType={(code) => {
          setContentTypeId(code);
          setPage(1);
        }}
        onKeywordChange={setKeyword}
        onSearch={() => {
          setAppliedKeyword(keyword.trim());
          setPage(1);
        }}
      />

      {error && <p className="error">{error}</p>}
      {loading && <p className="muted">불러오는 중…</p>}
      {!loading && !error && list.length === 0 && (
        <p className="muted">결과가 없습니다. 필터를 바꿔 보세요.</p>
      )}

      <div className="grid">
        {list.map((place) => (
          <PlaceCard key={place.contentid} place={place} />
        ))}
      </div>

      <Pagination
        page={page}
        totalCount={totalCount}
        pageSize={pageSize}
        onChange={setPage}
      />
    </div>
  );
}
