import { useEffect, useState } from 'react';
import MovieCard from '../components/MovieCard';
import {
  getLikes,
  getUser,
  getWishlist,
  setUser,
  toggleLike,
  toggleWish,
} from '../utils/storage';

export default function Wishlist() {
  const [user, setUserState] = useState(() => getUser());
  const [nameInput, setNameInput] = useState(user);
  const [likes, setLikes] = useState(() => getLikes());
  const [wishlist, setWishlist] = useState(() => getWishlist());

  useEffect(() => {
    setLikes(getLikes());
    setWishlist(getWishlist());
  }, [user]);

  function handleLogin(e) {
    e.preventDefault();
    setUser(nameInput);
    setUserState(nameInput.trim());
  }

  function handleLogout() {
    setUser('');
    setUserState('');
    setNameInput('');
  }

  return (
    <div>
      <header className="page-head">
        <h1>찜 목록</h1>
        <p>로그인 이름별로 localStorage에 저장됩니다.</p>
        <form className="login-form" onSubmit={handleLogin}>
          <input
            value={nameInput}
            onChange={(e) => setNameInput(e.target.value)}
            placeholder="닉네임 (예: student1)"
          />
          <button type="submit">로그인 저장</button>
          {user && (
            <button type="button" className="ghost" onClick={handleLogout}>
              로그아웃
            </button>
          )}
        </form>
        <p className="muted">현재: {user || 'guest'}</p>
      </header>

      {wishlist.length === 0 ? (
        <p className="muted">찜한 영화가 없습니다.</p>
      ) : (
        <div className="grid">
          {wishlist.map((movie) => (
            <MovieCard
              key={movie.id}
              movie={movie}
              liked={likes.includes(movie.id)}
              wished
              onLike={(id) => setLikes(toggleLike(id))}
              onWish={(m) => setWishlist(toggleWish(m))}
            />
          ))}
        </div>
      )}
    </div>
  );
}
