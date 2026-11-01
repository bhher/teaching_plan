import { useCallback, useEffect, useState } from 'react';
import {
  fetchWeatherBundle,
  hasApiKey,
  recommendClothes,
  searchCities,
} from './api/weather';
import { loadFavorites, saveFavorites } from './utils/favorites';
import SearchForm from './components/SearchForm';
import CurrentWeather from './components/CurrentWeather';
import HourlyForecast from './components/HourlyForecast';
import DailyForecast from './components/DailyForecast';
import Favorites from './components/Favorites';

const DEFAULT = { lat: 37.5665, lon: 126.978, label: 'Seoul, KR' };

export default function App() {
  const [query, setQuery] = useState('Seoul');
  const [suggestions, setSuggestions] = useState([]);
  const [weather, setWeather] = useState(null);
  const [place, setPlace] = useState(DEFAULT);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [favorites, setFavorites] = useState(() => loadFavorites());

  const loadWeather = useCallback(async (target) => {
    setLoading(true);
    setError('');
    try {
      const data = await fetchWeatherBundle(target.lat, target.lon, target.label);
      setWeather(data);
      setPlace(target);
    } catch (e) {
      setError(e.message || '오류가 발생했습니다.');
      setWeather(null);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    loadWeather(DEFAULT);
  }, [loadWeather]);

  async function handleSearch(e) {
    e.preventDefault();
    if (!query.trim()) return;
    setLoading(true);
    setError('');
    try {
      const cities = await searchCities(query.trim());
      setSuggestions(cities);
      if (cities.length === 0) {
        setError('검색 결과가 없습니다.');
        return;
      }
      await loadWeather(cities[0]);
    } catch (err) {
      setError(err.message || '검색 실패');
    } finally {
      setLoading(false);
    }
  }

  function toggleFavorite() {
    const exists = favorites.some(
      (f) => f.lat === place.lat && f.lon === place.lon
    );
    let next;
    if (exists) {
      next = favorites.filter((f) => !(f.lat === place.lat && f.lon === place.lon));
    } else {
      next = [...favorites, place];
    }
    setFavorites(next);
    saveFavorites(next);
  }

  function removeFavorite(f) {
    const next = favorites.filter((x) => !(x.lat === f.lat && x.lon === f.lon));
    setFavorites(next);
    saveFavorites(next);
  }

  const isFav = favorites.some((f) => f.lat === place.lat && f.lon === place.lon);
  const clothes = weather
    ? recommendClothes(weather.current.temp, weather.current.main)
    : [];

  return (
    <div className="app">
      <header className="hero">
        <p className="brand">Weather Site</p>
        <h1>날씨 정보</h1>
        <p className="lead">
          현재 날씨 · 시간별 · 주간 예보 · 도시 검색
          {!hasApiKey() && (
            <span className="badge">데모 모드 (API 키 없음)</span>
          )}
        </p>
        <SearchForm
          query={query}
          onChange={setQuery}
          onSubmit={handleSearch}
          loading={loading}
        />
        {suggestions.length > 1 && (
          <ul className="suggestions">
            {suggestions.map((c) => (
              <li key={`${c.lat}-${c.lon}`}>
                <button
                  type="button"
                  onClick={() => {
                    setQuery(c.name);
                    loadWeather(c);
                  }}
                >
                  {c.label}
                </button>
              </li>
            ))}
          </ul>
        )}
      </header>

      {error && <p className="error" role="alert">{error}</p>}
      {loading && !weather && <p className="loading">날씨를 불러오는 중…</p>}

      {weather && (
        <>
          <div className="toolbar">
            <button type="button" className="fav-btn" onClick={toggleFavorite}>
              {isFav ? '★ 즐겨찾기됨' : '☆ 즐겨찾기 추가'}
            </button>
          </div>
          <CurrentWeather data={weather} clothes={clothes} />
          <HourlyForecast items={weather.hourly} />
          <DailyForecast items={weather.daily} />
        </>
      )}

      <Favorites
        list={favorites}
        onSelect={(f) => {
          setQuery(f.name || f.label);
          loadWeather(f);
        }}
        onRemove={removeFavorite}
      />

      <footer className="foot">
        API: OpenWeatherMap · React: useState, useEffect, 로딩·에러 처리
      </footer>
    </div>
  );
}
