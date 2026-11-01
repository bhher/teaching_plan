# 영화 검색 및 추천 사이트 (초급~중급)

TMDB API로 영화 포스터, 줄거리, 평점, 장르 검색을 구현한 React 예제입니다.

## React 학습 포인트

- `map()`으로 카드 목록 렌더링
- 컴포넌트 분리 (`MovieCard`, `SearchBar`, `GenreFilter`)
- 검색 · 장르 필터링
- 상세 페이지 (`react-router-dom`)
- 확장: 좋아요 · 찜 목록 · 닉네임별 localStorage 저장

## API

[TMDB](https://www.themoviedb.org/settings/api) — API Key (v3 auth)

## 설정

```env
VITE_TMDB_API_KEY=발급받은_키
```

키가 없으면 데모 영화 데이터로 UI를 확인할 수 있습니다.

## 실행

```bash
cd react/movie-recommend
npm install
npm run dev
```

## 페이지

| 경로 | 설명 |
|------|------|
| `/` | 인기/검색/장르 목록 |
| `/movie/:id` | 상세 + 추천 영화 |
| `/wishlist` | 찜 목록 · 로그인(닉네임) |
