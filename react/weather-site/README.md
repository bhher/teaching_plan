# 날씨 정보 사이트 (초급)

현재 날씨, 시간별 예보, 주간 예보, 도시 검색을 구현한 React 예제입니다.

## React 학습 포인트

- `useState` — 검색어, 날씨 데이터, 로딩·에러
- `useEffect` — 첫 진입 시 서울 날씨 로드
- 검색 폼 · 로딩/에러 UI
- 확장: `localStorage` 즐겨찾기, 기온·날씨별 옷차림 추천

## API

[OpenWeatherMap](https://openweathermap.org/api) (Current + 5 Day / 3 Hour Forecast + Geocoding)

> 기상청 API는 인증·격자 변환이 복잡해서 수업용으로는 OpenWeather를 기본으로 둡니다.

## 설정

1. OpenWeather 가입 후 API Key 발급
2. 프로젝트 루트 `.env` 생성:

```env
VITE_OPENWEATHER_API_KEY=발급받은_키
```

키가 없으면 **데모 데이터**로 UI를 확인할 수 있습니다.

## 실행

```bash
cd react/weather-site
npm install
npm run dev
```

## 구조

```
src/
  api/weather.js          # fetch + mock + 옷차림 추천
  components/
    SearchForm.jsx
    CurrentWeather.jsx
    HourlyForecast.jsx
    DailyForecast.jsx
    Favorites.jsx
  utils/favorites.js
  App.jsx
```
