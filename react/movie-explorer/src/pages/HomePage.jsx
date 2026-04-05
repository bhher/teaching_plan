import { useEffect, useState } from 'react';
import { fetchByGenre, fetchPopular, searchMovies } from '../api/tmdb';
import GenreFilter from '../components/GenreFilter';
import MovieCard from '../components/MovieCard';
import SearchBar from '../components/SearchBar';
import { useDebouncedValue } from '../hooks/useDebouncedValue';

export default function HomePage() {
  const [searchInput, setSearchInput] = useState('');
  const debouncedSearch = useDebouncedValue(searchInput, 400);
  const [genreId, setGenreId] = useState(null);
  const [page, setPage] = useState(1);
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const query = debouncedSearch.trim();

  useEffect(() => {
    setPage(1);
  }, [query, genreId]);

  useEffect(() => {
    let cancelled = false;
    setLoading(true);
    setError(null);

    const run = async () => {
      try {
        setData(null);
        let res;
        if (query) {
          res = await searchMovies(query, page);
        } else if (genreId != null) {
          res = await fetchByGenre(genreId, page);
        } else {
          res = await fetchPopular(page);
        }
        if (!cancelled) setData(res);
      } catch (e) {
        if (!cancelled) {
          setError(e.message);
          setData(null);
        }
      } finally {
        if (!cancelled) setLoading(false);
      }
    };

    run();
    return () => {
      cancelled = true;
    };
  }, [query, genreId, page]);

  const handleGenreChange = (id) => {
    setGenreId(id);
    setSearchInput('');
  };

  const list = data?.results ?? [];
  const totalPages = data?.total_pages ?? 1;

  return (
    <main className="page home">
      <section className="hero">
        <h1>인기 영화</h1>
        <p className="hero__sub">TMDB 인기 목록 · 장르 필터 · 제목 검색</p>
      </section>

      <SearchBar
        value={searchInput}
        onChange={setSearchInput}
        onSearchClick={() => {}}
      />

      <GenreFilter
        value={genreId}
        onChange={handleGenreChange}
      />

      {query && (
        <p className="hint">
          검색 중: <strong>{query}</strong> (장르 필터는 검색 시 적용되지 않습니다)
        </p>
      )}

      {error && <p className="error">{error}</p>}

      {loading && !data ? (
        <p className="loading">불러오는 중…</p>
      ) : (
        <>
          <ul className="movie-grid">
            {list.length === 0 ? (
              <li className="empty">결과가 없습니다.</li>
            ) : (
              list.map((movie) => (
                <li key={movie.id}>
                  <MovieCard movie={movie} />
                </li>
              ))
            )}
          </ul>

          {totalPages > 1 && (
            <div className="pager">
              <button type="button" disabled={page <= 1} onClick={() => setPage((p) => p - 1)}>
                이전
              </button>
              <span>
                {page} / {totalPages}
              </span>
              <button
                type="button"
                disabled={page >= totalPages}
                onClick={() => setPage((p) => p + 1)}
              >
                다음
              </button>
            </div>
          )}
        </>
      )}
    </main>
  );
}
