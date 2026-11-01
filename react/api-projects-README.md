# API 활용 React 프로젝트 3종

수업용 초급~중급 미니 프로젝트입니다. API 키가 없어도 **데모 모드**로 UI를 확인할 수 있습니다.

| # | 폴더 | 난이도 | API | 학습 포인트 |
|---|------|--------|-----|-------------|
| 1 | [weather-site](./weather-site) | 초급 | OpenWeather | useEffect, useState, 검색, 로딩·에러, 즐겨찾기·옷차림 |
| 2 | [movie-recommend](./movie-recommend) | 초급~중급 | TMDB | map, 컴포넌트 분리, 검색·필터, 상세, 좋아요·찜 |
| 3 | [travel-korea](./travel-korea) | 초급~중급 | TourAPI | 카드 UI, 지역 필터, 페이지네이션, 일정·지도 |

## 공통 실행

```bash
cd react/<프로젝트폴더>
npm install
cp .env.example .env   # 키 입력 (선택)
npm run dev
```

## API 키 발급

1. **OpenWeather** — https://openweathermap.org/api  
2. **TMDB** — https://www.themoviedb.org/settings/api  
3. **TourAPI** — https://www.data.go.kr/ (한국관광공사 KorService1)
