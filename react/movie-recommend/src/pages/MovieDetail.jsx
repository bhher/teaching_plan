import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import {
  fetchMovieDetail,
  fetchRecommendations,
  posterUrl,
} from '../api/tmdb';
import {
  getLikes,
  getWishlist,
  toggleLike,
  toggleWish,
} from '../utils/storage';
import MovieCard from '../components/MovieCard';

export default function MovieDetail() {
  const { id } = useParams();
  const [movie, setMovie] = useState(null);
  const [recs, setRecs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [likes, setLikes] = useState(() => getLikes());
  const [wishlist, setWishlist] = useState(() => getWishlist());

  useEffect(() => {
    let cancelled = false;
    async function load() {
      setLoading(true);
      setError('');
      try {
        const [detail, recommend] = await Promise.all([
          fetchMovieDetail(id),
          fetchRecommendations(id),
        ]);
        if (!cancelled) {
          setMovie(detail);
          setRecs(recommend.results || []);
        }
      } catch (e) {
        if (!cancelled) setError(e.message || '상세 정보를 불러오지 못했습니다.');
      } finally {
        if (!cancelled) setLoading(false);
      }
    }
    load();
    return () => {
      cancelled = true;
    };
  }, [id]);

  if (loading) return <p className="muted">불러오는 중…</p>;
  if (error) return <p className="error">{error}</p>;
  if (!movie) return null;

  const src = posterUrl(movie.poster_path);
  const liked = likes.includes(movie.id);
  const wished = wishlist.some((w) => w.id === movie.id);

  return (
    <div className="detail">
      <Link to="/" className="back">
        ← 목록
      </Link>
      <div className="detail-grid">
        {src ? (
          <img className="detail-poster" src={src} alt={movie.title} />
        ) : (
          <div className="no-poster tall">No Image</div>
        )}
        <div>
          <h1>{movie.title}</h1>
          {movie.tagline && <p className="tagline">{movie.tagline}</p>}
          <p className="rating">★ {movie.vote_average?.toFixed?.(1) ?? movie.vote_average}</p>
          <p className="genres">
            {(movie.genres || []).map((g) => g.name).join(' · ') || '장르 정보 없음'}
          </p>
          <p className="overview">{movie.overview || '줄거리가 없습니다.'}</p>
          <div className="card-actions">
            <button type="button" onClick={() => setLikes(toggleLike(movie.id))}>
              {liked ? '♥ 좋아요' : '♡ 좋아요'}
            </button>
            <button type="button" onClick={() => setWishlist(toggleWish(movie))}>
              {wished ? '★ 찜됨' : '☆ 찜'}
            </button>
          </div>
        </div>
      </div>

      {recs.length > 0 && (
        <section className="recs">
          <h2>추천 영화</h2>
          <div className="grid">
            {recs.slice(0, 6).map((m) => (
              <MovieCard
                key={m.id}
                movie={m}
                liked={likes.includes(m.id)}
                wished={wishlist.some((w) => w.id === m.id)}
                onLike={(mid) => setLikes(toggleLike(mid))}
                onWish={(mm) => setWishlist(toggleWish(mm))}
              />
            ))}
          </div>
        </section>
      )}
    </div>
  );
}
