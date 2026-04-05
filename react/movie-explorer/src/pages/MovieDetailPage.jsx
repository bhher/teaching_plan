import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { backdropUrl, fetchMovieDetail, posterUrl } from '../api/tmdb';
import { useFavorites } from '../context/FavoritesContext';

export default function MovieDetailPage() {
  const { id } = useParams();
  const { isFavorite, toggleFavorite } = useFavorites();
  const [movie, setMovie] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    let cancelled = false;
    setMovie(null);
    setError(null);
    fetchMovieDetail(id)
      .then((m) => {
        if (!cancelled) setMovie(m);
      })
      .catch((e) => {
        if (!cancelled) setError(e.message);
      });
    return () => {
      cancelled = true;
    };
  }, [id]);

  if (error) {
    return (
      <main className="page detail">
        <p className="error">{error}</p>
        <Link to="/">← 목록</Link>
      </main>
    );
  }

  if (!movie) {
    return (
      <main className="page detail">
        <p className="loading">불러오는 중…</p>
      </main>
    );
  }

  const cast = movie.credits?.cast?.slice(0, 14) ?? [];
  const poster = posterUrl(movie.poster_path, 'w500');
  const backdrop = backdropUrl(movie.backdrop_path);
  const fav = isFavorite(movie.id);

  const cardMovie = {
    id: movie.id,
    title: movie.title,
    poster_path: movie.poster_path,
    vote_average: movie.vote_average,
  };

  return (
    <main className="page detail">
      <div
        className="detail__backdrop"
        style={
          backdrop
            ? { backgroundImage: `linear-gradient(rgba(10,12,20,0.92), rgba(10,12,20,0.97)), url(${backdrop})` }
            : undefined
        }
      />
      <div className="detail__inner">
        <Link to="/" className="back-link">
          ← 목록으로
        </Link>

        <div className="detail__head">
          {poster ? (
            <img src={poster} alt="" className="detail__poster" />
          ) : (
            <div className="detail__poster detail__poster--empty">No poster</div>
          )}
          <div className="detail__meta">
            <h1>{movie.title}</h1>
            {movie.tagline && <p className="detail__tagline">{movie.tagline}</p>}
            <p className="detail__score">평점 ★ {movie.vote_average?.toFixed(1)} / 10</p>
            <p className="detail__date">{movie.release_date}</p>
            <button
              type="button"
              className={`fav-btn fav-btn--large ${fav ? 'fav-btn--on' : ''}`}
              onClick={() => toggleFavorite(cardMovie)}
            >
              {fav ? '♥ 즐겨찾기 해제' : '♡ 즐겨찾기'}
            </button>
          </div>
        </div>

        <section className="detail__section">
          <h2>줄거리</h2>
          <p className="detail__overview">{movie.overview || '줄거리 정보가 없습니다.'}</p>
        </section>

        <section className="detail__section">
          <h2>출연진</h2>
          {cast.length === 0 ? (
            <p>출연 정보가 없습니다.</p>
          ) : (
            <ul className="cast-list">
              {cast.map((c) => (
                <li key={c.credit_id} className="cast-item">
                  {c.profile_path ? (
                    <img
                      src={posterUrl(c.profile_path, 'w185')}
                      alt=""
                      className="cast-item__img"
                    />
                  ) : (
                    <div className="cast-item__img cast-item__img--empty" />
                  )}
                  <div>
                    <strong>{c.name}</strong>
                    <p className="cast-item__char">{c.character}</p>
                  </div>
                </li>
              ))}
            </ul>
          )}
        </section>
      </div>
    </main>
  );
}
