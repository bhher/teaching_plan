const BASE = 'https://api.themoviedb.org/3';
const IMG = 'https://image.tmdb.org/t/p';

function getKey() {
  const key = import.meta.env.VITE_TMDB_API_KEY;
  if (!key) {
    throw new Error('VITE_TMDB_API_KEY가 설정되지 않았습니다. .env 파일을 확인하세요.');
  }
  return key;
}

async function get(path, params = {}) {
  const key = getKey();
  const q = new URLSearchParams({ api_key: key, language: 'ko-KR', ...params });
  const res = await fetch(`${BASE}${path}?${q}`);
  if (!res.ok) {
    const err = await res.text();
    throw new Error(`TMDB ${res.status}: ${err}`);
  }
  return res.json();
}

export function posterUrl(path, size = 'w342') {
  if (!path) return null;
  return `${IMG}/${size}${path}`;
}

export function backdropUrl(path) {
  if (!path) return null;
  return `${IMG}/w1280${path}`;
}

/** 인기 영화 */
export function fetchPopular(page = 1) {
  return get('/movie/popular', { page: String(page) });
}

/** 장르로 필터 (discover) */
export function fetchByGenre(genreId, page = 1) {
  return get('/discover/movie', {
    with_genres: String(genreId),
    sort_by: 'popularity.desc',
    page: String(page),
  });
}

/** 제목 검색 */
export function searchMovies(query, page = 1) {
  if (!query?.trim()) return Promise.resolve({ results: [], total_results: 0 });
  return get('/search/movie', { query: query.trim(), page: String(page) });
}

/** 상세 + 출연 (credits) */
export function fetchMovieDetail(id) {
  return get(`/movie/${id}`, { append_to_response: 'credits' });
}
