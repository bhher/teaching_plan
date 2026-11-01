import { useEffect, useState } from 'react';
import {
  discoverByGenre,
  fetchGenres,
  fetchPopular,
  hasApiKey,
  searchMovies,
} from '../api/tmdb';
import {
  getLikes,
  getWishlist,
  toggleLike,
  toggleWish,
} from '../utils/storage';
import SearchBar from '../components/SearchBar';
import GenreFilter from '../components/GenreFilter';
import MovieCard from '../components/MovieCard';

export default function Home() {
  const [query, setQuery] = useState('');
  const [submittedQuery, setSubmittedQuery] = useState('');
  const [genre, setGenre] = useState('');
  const [genres, setGenres] = useState([]);
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [likes, setLikes] = useState(() => getLikes());
  const [wishlist, setWishlist] = useState(() => getWishlist());

  useEffect(() => {
    fetchGenres()
      .then(setGenres)
      .catch(() => setGenres([]));
  }, []);

  useEffect(() => {
    let cancelled = false;
    async function load() {
      setLoading(true);
      setError('');
      try {
        let data;
        if (submittedQuery.trim()) {
          data = await searchMovies(submittedQuery.trim());
        } else if (genre) {
          data = await discoverByGenre(genre);
        } else {
          data = await fetchPopular();
        }
        if (!cancelled) setMovies(data.results || []);
      } catch (e) {
        if (!cancelled) {
          setError(e.message || '목록을 불러오지 못했습니다.');
          setMovies([]);
        }
      } finally {
        if (!cancelled) setLoading(false);
      }
    }
    load();
    return () => {
      cancelled = true;
    };
  }, [submittedQuery, genre]);

  function handleSearch(e) {
    e.preventDefault();
    setGenre('');
    setSubmittedQuery(query.trim());
  }

  return (
    <div>
      <header className="page-head">
        <h1>영화 검색 · 추천</h1>
        <p>
          포스터 · 줄거리 · 평점 · 장르 필터
          {!hasApiKey() && <span className="badge">데모 모드</span>}
        </p>
        <SearchBar value={query} onChange={setQuery} onSubmit={handleSearch} />
        <GenreFilter
          genres={genres}
          selected={genre}
          onChange={(id) => {
            setGenre(id);
            setQuery('');
            setSubmittedQuery('');
          }}
        />
      </header>

      {error && <p className="error">{error}</p>}
      {loading && <p className="muted">불러오는 중…</p>}
      {!loading && !error && movies.length === 0 && (
        <p className="muted">결과가 없습니다.</p>
      )}

      <div className="grid">
        {movies.map((movie) => (
          <MovieCard
            key={movie.id}
            movie={movie}
            liked={likes.includes(movie.id)}
            wished={wishlist.some((w) => w.id === movie.id)}
            onLike={(id) => setLikes(toggleLike(id))}
            onWish={(m) => setWishlist(toggleWish(m))}
          />
        ))}
      </div>
    </div>
  );
}
