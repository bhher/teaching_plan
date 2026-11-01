import { Link } from 'react-router-dom';
import { posterUrl } from '../api/tmdb';

export default function MovieCard({ movie, liked, wished, onLike, onWish }) {
  const src = posterUrl(movie.poster_path);
  return (
    <article className="movie-card">
      <Link to={`/movie/${movie.id}`} className="poster-link">
        {src ? (
          <img src={src} alt={movie.title} loading="lazy" />
        ) : (
          <div className="no-poster">No Image</div>
        )}
      </Link>
      <div className="card-body">
        <h3>
          <Link to={`/movie/${movie.id}`}>{movie.title}</Link>
        </h3>
        <p className="rating">★ {movie.vote_average?.toFixed?.(1) ?? movie.vote_average}</p>
        <div className="card-actions">
          <button type="button" onClick={() => onLike(movie.id)} aria-pressed={liked}>
            {liked ? '♥ 좋아요' : '♡ 좋아요'}
          </button>
          <button type="button" onClick={() => onWish(movie)} aria-pressed={wished}>
            {wished ? '★ 찜됨' : '☆ 찜'}
          </button>
        </div>
      </div>
    </article>
  );
}
