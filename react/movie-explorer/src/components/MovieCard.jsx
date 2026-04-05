import { Link } from 'react-router-dom';
import { posterUrl } from '../api/tmdb';
import { useFavorites } from '../context/FavoritesContext';

export default function MovieCard({ movie }) {
  const { isFavorite, toggleFavorite } = useFavorites();
  const fav = isFavorite(movie.id);
  const poster = posterUrl(movie.poster_path, 'w342');

  return (
    <article className="movie-card">
      <Link to={`/movie/${movie.id}`} className="movie-card__link">
        <div className="movie-card__poster-wrap">
          {poster ? (
            <img src={poster} alt="" className="movie-card__poster" loading="lazy" />
          ) : (
            <div className="movie-card__placeholder">No poster</div>
          )}
          <span className="movie-card__score">★ {movie.vote_average?.toFixed(1) ?? '-'}</span>
        </div>
        <h2 className="movie-card__title">{movie.title}</h2>
      </Link>
      <button
        type="button"
        className={`fav-btn ${fav ? 'fav-btn--on' : ''}`}
        aria-label={fav ? '즐겨찾기 해제' : '즐겨찾기'}
        onClick={(e) => {
          e.preventDefault();
          e.stopPropagation();
          toggleFavorite(movie);
        }}
      >
        {fav ? '♥' : '♡'}
      </button>
    </article>
  );
}
