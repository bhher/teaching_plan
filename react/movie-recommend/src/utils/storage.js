const LIKES = 'movie_recommend_likes';
const WISH = 'movie_recommend_wishlist';
const USER = 'movie_recommend_user';

function read(key) {
  try {
    return JSON.parse(localStorage.getItem(key)) || [];
  } catch {
    return [];
  }
}

function write(key, value) {
  localStorage.setItem(key, JSON.stringify(value));
}

export function getUser() {
  return localStorage.getItem(USER) || '';
}

export function setUser(name) {
  if (!name) localStorage.removeItem(USER);
  else localStorage.setItem(USER, name.trim());
}

function scopedKey(base) {
  const user = getUser() || 'guest';
  return `${base}_${user}`;
}

export function getLikes() {
  return read(scopedKey(LIKES));
}

export function toggleLike(id) {
  const list = getLikes();
  const next = list.includes(id) ? list.filter((x) => x !== id) : [...list, id];
  write(scopedKey(LIKES), next);
  return next;
}

export function getWishlist() {
  return read(scopedKey(WISH));
}

export function toggleWish(movie) {
  const list = getWishlist();
  const exists = list.some((m) => m.id === movie.id);
  const next = exists
    ? list.filter((m) => m.id !== movie.id)
    : [...list, { id: movie.id, title: movie.title, poster_path: movie.poster_path, vote_average: movie.vote_average }];
  write(scopedKey(WISH), next);
  return next;
}
