# Movie Explorer (TMDB + React Router)

## TMDB API 키

1. [themoviedb.org](https://www.themoviedb.org/) 가입 후 [API 설정](https://www.themoviedb.org/settings/api)에서 **API Key (v3 auth)** 발급  
2. 프로젝트 루트에 `.env` 생성:

```env
VITE_TMDB_API_KEY=발급받은_키
```

## 실행

```bash
npm install
npm run dev
```

## 기능

- 인기 영화 목록(카드: 포스터·제목·평점) · `/movie/popular`
- 상세(줄거리·평점·출연진) · `/movie/:id`
- 제목 검색(디바운스) · `/search/movie`
- 장르 필터(액션/로맨스/코미디) · `/discover/movie`
- 즐겨찾기 · `localStorage` (`movie_explorer_favorites`)
