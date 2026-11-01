const API_KEY = import.meta.env.VITE_TMDB_API_KEY;
const BASE = 'https://api.themoviedb.org/3';
const IMG = 'https://image.tmdb.org/t/p/w500';

export function hasApiKey() {
  return Boolean(API_KEY && API_KEY.trim());
}

export function posterUrl(path) {
  if (!path) return null;
  if (path.startsWith('http')) return path;
  return `${IMG}${path}`;
}

async function tmdb(path, params = {}) {
  const qs = new URLSearchParams({
    api_key: API_KEY,
    language: 'ko-KR',
    ...params,
  });
  const res = await fetch(`${BASE}${path}?${qs}`);
  if (!res.ok) throw new Error('TMDB 요청 실패. API 키를 확인하세요.');
  return res.json();
}

export async function fetchPopular() {
  if (!hasApiKey()) return { results: MOCK_MOVIES, total_pages: 1 };
  return tmdb('/movie/popular', { page: '1' });
}

export async function searchMovies(query, page = 1) {
  if (!hasApiKey()) {
    const q = query.toLowerCase();
    const results = MOCK_MOVIES.filter(
      (m) =>
        m.title.toLowerCase().includes(q) ||
        (m.overview || '').toLowerCase().includes(q)
    );
    return { results, total_pages: 1 };
  }
  return tmdb('/search/movie', { query, page: String(page) });
}

export async function discoverByGenre(genreId, page = 1) {
  if (!hasApiKey()) {
    const results = genreId
      ? MOCK_MOVIES.filter((m) => m.genre_ids.includes(Number(genreId)))
      : MOCK_MOVIES;
    return { results, total_pages: 1 };
  }
  return tmdb('/discover/movie', {
    with_genres: String(genreId),
    sort_by: 'popularity.desc',
    page: String(page),
  });
}

export async function fetchGenres() {
  if (!hasApiKey()) return MOCK_GENRES;
  const data = await tmdb('/genre/movie/list');
  return data.genres;
}

export async function fetchMovieDetail(id) {
  if (!hasApiKey()) {
    const m = MOCK_MOVIES.find((x) => String(x.id) === String(id));
    if (!m) throw new Error('영화를 찾을 수 없습니다.');
    return {
      ...m,
      genres: MOCK_GENRES.filter((g) => m.genre_ids.includes(g.id)),
      runtime: 120,
      tagline: '데모 상세 화면',
    };
  }
  return tmdb(`/movie/${id}`);
}

export async function fetchRecommendations(id) {
  if (!hasApiKey()) {
    return { results: MOCK_MOVIES.filter((m) => String(m.id) !== String(id)).slice(0, 4) };
  }
  return tmdb(`/movie/${id}/recommendations`);
}

const MOCK_GENRES = [
  { id: 28, name: '액션' },
  { id: 35, name: '코미디' },
  { id: 18, name: '드라마' },
  { id: 10749, name: '로맨스' },
  { id: 878, name: 'SF' },
];

const MOCK_MOVIES = [
  {
    id: 1,
    title: '인셉션',
    overview: '꿈속에서 아이디어를 훔치는 특수 요원들의 이야기.',
    vote_average: 8.4,
    poster_path: 'https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg',
    genre_ids: [28, 878, 18],
    release_date: '2010-07-16',
  },
  {
    id: 2,
    title: '기생충',
    overview: '반지하 가족과 부유층 가족의 만남이 불러온 예측 불가의 사건.',
    vote_average: 8.5,
    poster_path: 'https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg',
    genre_ids: [18, 35],
    release_date: '2019-05-30',
  },
  {
    id: 3,
    title: '어바웃 타임',
    overview: '시간을 되돌릴 수 있는 능력을 가진 남자의 사랑 이야기.',
    vote_average: 7.9,
    poster_path: 'https://image.tmdb.org/t/p/w500/zH41zkCAmjGv0INb3yv3ZQGZ5Fd.jpg',
    genre_ids: [10749, 35, 18],
    release_date: '2013-09-04',
  },
  {
    id: 4,
    title: '매드맥스: 분노의 도로',
    overview: '황무지에서 펼쳐지는 추격전과 생존.',
    vote_average: 8.1,
    poster_path: 'https://image.tmdb.org/t/p/w500/hA2ple9q4qnwxp3hKVNhroelsns.jpg',
    genre_ids: [28, 878],
    release_date: '2015-05-15',
  },
  {
    id: 5,
    title: '라라랜드',
    overview: '배우와 재즈 뮤지션의 꿈과 사랑.',
    vote_average: 8.0,
    poster_path: 'https://image.tmdb.org/t/p/w500/uDO8zWDmhW7vulalEUJuByLbKJM.jpg',
    genre_ids: [10749, 35],
    release_date: '2016-12-09',
  },
  {
    id: 6,
    title: '인터스텔라',
    overview: '인류의 생존을 위해 우주로 떠나는 탐험대.',
    vote_average: 8.6,
    poster_path: 'https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg',
    genre_ids: [878, 18],
    release_date: '2014-11-07',
  },
];
