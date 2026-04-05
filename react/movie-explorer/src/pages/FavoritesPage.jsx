import { Link } from 'react-router-dom';
import MovieCard from '../components/MovieCard';
import { useFavorites } from '../context/FavoritesContext';

export default function FavoritesPage() {
  const { favorites } = useFavorites();

  return (
    <main className="page favorites">
      <h1>즐겨찾기</h1>
      <p className="hint">localStorage에 저장됩니다. 브라우저를 바꾸면 목록이 비어 있습니다.</p>

      {favorites.length === 0 ? (
        <p className="empty-msg">
          아직 없습니다. 카드의 ♡를 눌러 추가해 보세요.{' '}
          <Link to="/">인기 영화 보기</Link>
        </p>
      ) : (
        <ul className="movie-grid">
          {favorites.map((movie) => (
            <li key={movie.id}>
              <MovieCard movie={movie} />
            </li>
          ))}
        </ul>
      )}
    </main>
  );
}
