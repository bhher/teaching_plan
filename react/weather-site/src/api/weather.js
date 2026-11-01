const API_KEY = import.meta.env.VITE_OPENWEATHER_API_KEY;
const BASE = 'https://api.openweathermap.org';

/** 키가 없으면 mock 모드 */
export function hasApiKey() {
  return Boolean(API_KEY && API_KEY.trim());
}

export async function searchCities(query) {
  if (!hasApiKey()) {
    return mockCities.filter((c) =>
      c.name.toLowerCase().includes(query.toLowerCase())
    );
  }
  const url = `${BASE}/geo/1.0/direct?q=${encodeURIComponent(query)}&limit=5&appid=${API_KEY}`;
  const res = await fetch(url);
  if (!res.ok) throw new Error('도시 검색에 실패했습니다.');
  const data = await res.json();
  return data.map((c) => ({
    name: c.name,
    country: c.country,
    state: c.state || '',
    lat: c.lat,
    lon: c.lon,
    label: [c.name, c.state, c.country].filter(Boolean).join(', '),
  }));
}

export async function fetchWeatherBundle(lat, lon, label) {
  if (!hasApiKey()) {
    return getMockBundle(label || 'Seoul');
  }

  const [currentRes, forecastRes] = await Promise.all([
    fetch(
      `${BASE}/data/2.5/weather?lat=${lat}&lon=${lon}&units=metric&lang=kr&appid=${API_KEY}`
    ),
    fetch(
      `${BASE}/data/2.5/forecast?lat=${lat}&lon=${lon}&units=metric&lang=kr&appid=${API_KEY}`
    ),
  ]);

  if (!currentRes.ok || !forecastRes.ok) {
    throw new Error('날씨 정보를 불러오지 못했습니다. API 키를 확인하세요.');
  }

  const current = await currentRes.json();
  const forecast = await forecastRes.json();

  return {
    city: label || current.name,
    current: {
      temp: Math.round(current.main.temp),
      feelsLike: Math.round(current.main.feels_like),
      humidity: current.main.humidity,
      wind: current.wind.speed,
      description: current.weather[0]?.description || '',
      icon: current.weather[0]?.icon || '01d',
      main: current.weather[0]?.main || 'Clear',
    },
    hourly: forecast.list.slice(0, 8).map((item) => ({
      time: item.dt_txt,
      temp: Math.round(item.main.temp),
      icon: item.weather[0]?.icon || '01d',
      description: item.weather[0]?.description || '',
    })),
    daily: groupDaily(forecast.list),
  };
}

function groupDaily(list) {
  const byDay = {};
  list.forEach((item) => {
    const day = item.dt_txt.slice(0, 10);
    if (!byDay[day]) byDay[day] = [];
    byDay[day].push(item);
  });
  return Object.keys(byDay)
    .slice(0, 5)
    .map((day) => {
      const items = byDay[day];
      const temps = items.map((i) => i.main.temp);
      const mid = items[Math.floor(items.length / 2)];
      return {
        date: day,
        min: Math.round(Math.min(...temps)),
        max: Math.round(Math.max(...temps)),
        icon: mid.weather[0]?.icon || '01d',
        description: mid.weather[0]?.description || '',
      };
    });
}

/** 기온·날씨 상태 기반 옷차림 추천 */
export function recommendClothes(temp, main) {
  const tips = [];
  if (temp <= 5) tips.push('패딩·코트', '목도리·장갑', '기모 바지');
  else if (temp <= 12) tips.push('두꺼운 자켓', '니트', '청바지');
  else if (temp <= 18) tips.push('가디건·얇은 자켓', '긴팔', '면바지');
  else if (temp <= 24) tips.push('긴팔·반팔', '얇은 겉옷', '편한 바지');
  else tips.push('반팔·민소매', '반라지', '모자·선크림');

  const m = (main || '').toLowerCase();
  if (m.includes('rain') || m.includes('drizzle')) tips.push('우산·방수 자켓');
  if (m.includes('snow')) tips.push('방한화·미끄럼 방지');
  if (m.includes('clear') && temp >= 22) tips.push('선글라스');

  return tips;
}

const mockCities = [
  { name: 'Seoul', country: 'KR', state: '', lat: 37.5665, lon: 126.978, label: 'Seoul, KR' },
  { name: 'Busan', country: 'KR', state: '', lat: 35.1796, lon: 129.0756, label: 'Busan, KR' },
  { name: 'Incheon', country: 'KR', state: '', lat: 37.4563, lon: 126.7052, label: 'Incheon, KR' },
  { name: 'Tokyo', country: 'JP', state: '', lat: 35.6762, lon: 139.6503, label: 'Tokyo, JP' },
];

function getMockBundle(label) {
  const now = new Date();
  const hourly = Array.from({ length: 8 }, (_, i) => {
    const t = new Date(now.getTime() + i * 3 * 3600 * 1000);
    return {
      time: t.toISOString().slice(0, 16).replace('T', ' '),
      temp: 18 + (i % 3) - 1,
      icon: i % 3 === 0 ? '02d' : '01d',
      description: i % 3 === 0 ? '구름조금' : '맑음',
    };
  });
  const daily = Array.from({ length: 5 }, (_, i) => {
    const d = new Date(now.getTime() + i * 86400000);
    return {
      date: d.toISOString().slice(0, 10),
      min: 12 + i,
      max: 20 + i,
      icon: i === 2 ? '10d' : '01d',
      description: i === 2 ? '가벼운 비' : '맑음',
    };
  });
  return {
    city: label,
    current: {
      temp: 19,
      feelsLike: 18,
      humidity: 55,
      wind: 2.4,
      description: '맑음 (데모 데이터)',
      icon: '01d',
      main: 'Clear',
    },
    hourly,
    daily,
  };
}
