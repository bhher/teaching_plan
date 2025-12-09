# React에서 사용할 수 있는 지도 및 교통 API 가이드

## 📍 지도 API

### 1. 카카오맵 API (추천 ⭐)

**특징:**
- 한국에서 가장 널리 사용되는 지도 API
- 한글 주소 검색 지원
- 상세한 한국어 문서 제공
- 무료 사용량 제공 (일일 300,000건)

**설치:**
```bash
npm install react-kakao-maps-sdk
```

**기본 사용법:**
```jsx
import { Map, MapMarker } from 'react-kakao-maps-sdk';

function KakaoMap() {
  return (
    <Map
      center={{ lat: 37.5665, lng: 126.9780 }} // 서울시청
      style={{ width: '100%', height: '400px' }}
      level={3}
    >
      <MapMarker position={{ lat: 37.5665, lng: 126.9780 }}>
        <div style={{ padding: '5px', color: '#000' }}>
          서울시청
        </div>
      </MapMarker>
    </Map>
  );
}
```

**API 키 발급:**
- [카카오 개발자 센터](https://developers.kakao.com/)
- 애플리케이션 등록 → 플랫폼 설정 → JavaScript 키 발급

**주요 기능:**
- 지도 표시 및 마커
- 주소 검색 (카카오 로컬 API)
- 경로 안내
- 장소 검색

---

### 2. 네이버 지도 API

**특징:**
- 네이버 지도 서비스 기반
- 한국 지역 상세 정보
- 무료 사용량 제공

**설치:**
```bash
npm install react-naver-maps
```

**기본 사용법:**
```jsx
import { NaverMap, Marker } from 'react-naver-maps';

function NaverMapComponent() {
  return (
    <NaverMap
      mapDivId="react-naver-map"
      style={{ width: '100%', height: '400px' }}
      defaultCenter={{ lat: 37.5665, lng: 126.9780 }}
      defaultZoom={13}
    >
      <Marker
        position={{ lat: 37.5665, lng: 126.9780 }}
        title="서울시청"
      />
    </NaverMap>
  );
}
```

**API 키 발급:**
- [네이버 클라우드 플랫폼](https://www.ncloud.com/)
- AI·NAVER → AI·NAVER Services → Maps

---

### 3. 구글 맵 API

**특징:**
- 전 세계적으로 가장 널리 사용
- 다양한 기능 제공
- 유료 (월 $200 크레딧 무료)

**설치:**
```bash
npm install @react-google-maps/api
```

**기본 사용법:**
```jsx
import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';

const containerStyle = {
  width: '100%',
  height: '400px'
};

const center = {
  lat: 37.5665,
  lng: 126.9780
};

function GoogleMapComponent() {
  return (
    <LoadScript googleMapsApiKey="YOUR_API_KEY">
      <GoogleMap
        mapContainerStyle={containerStyle}
        center={center}
        zoom={10}
      >
        <Marker position={center} />
      </GoogleMap>
    </LoadScript>
  );
}
```

**API 키 발급:**
- [Google Cloud Platform](https://console.cloud.google.com/)
- APIs & Services → Credentials → Create Credentials

---

### 4. Leaflet (오픈소스)

**특징:**
- 완전 무료 오픈소스
- 가볍고 커스터마이징 용이
- OpenStreetMap 기반

**설치:**
```bash
npm install react-leaflet leaflet
```

**기본 사용법:**
```jsx
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';

function LeafletMap() {
  return (
    <MapContainer
      center={[37.5665, 126.9780]}
      zoom={13}
      style={{ height: '400px', width: '100%' }}
    >
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; OpenStreetMap contributors'
      />
      <Marker position={[37.5665, 126.9780]}>
        <Popup>서울시청</Popup>
      </Marker>
    </MapContainer>
  );
}
```

---

## 🚌 버스 시간표 및 대중교통 API

### 1. 서울시 버스 정보 API (서울 열린데이터광장)

**특징:**
- 서울시 버스 실시간 도착 정보
- 버스 노선 정보
- 정류장 정보
- 무료

**API 엔드포인트:**
```
http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute
```

**기본 사용법:**
```jsx
import axios from 'axios';

const API_KEY = 'YOUR_API_KEY';
const BASE_URL = 'http://ws.bus.go.kr/api/rest';

// 버스 도착 정보 조회
async function getBusArrivalInfo(stationId, routeId) {
  try {
    const response = await axios.get(
      `${BASE_URL}/arrive/getArrInfoByRoute`,
      {
        params: {
          serviceKey: API_KEY,
          stId: stationId,
          busRouteId: routeId,
        }
      }
    );
    return response.data;
  } catch (error) {
    console.error('버스 정보 조회 실패:', error);
  }
}

// React 컴포넌트에서 사용
function BusArrivalInfo({ stationId, routeId }) {
  const [arrivalInfo, setArrivalInfo] = useState(null);

  useEffect(() => {
    getBusArrivalInfo(stationId, routeId)
      .then(data => setArrivalInfo(data));
  }, [stationId, routeId]);

  return (
    <div>
      {arrivalInfo && (
        <div>
          <p>남은 시간: {arrivalInfo.arrmsg1}</p>
          <p>다음 버스: {arrivalInfo.arrmsg2}</p>
        </div>
      )}
    </div>
  );
}
```

**API 키 발급:**
- [서울 열린데이터광장](https://data.seoul.go.kr/)
- 회원가입 → 인증키 신청

**주요 API:**
- `getArrInfoByRoute`: 노선별 도착 정보
- `getRouteInfo`: 노선 정보
- `getStationByName`: 정류장 검색
- `getBusPosByRouteid`: 버스 위치 정보

---

### 2. 공공데이터포털 대중교통 API

**특징:**
- 전국 대중교통 정보
- 버스, 지하철 통합 정보
- 다양한 데이터 제공

**주요 API:**
- 국토교통부_대중교통 정보 조회 서비스
- 한국교통공사_지하철역 정보
- 각 지자체별 대중교통 정보

**API 키 발급:**
- [공공데이터포털](https://www.data.go.kr/)
- 회원가입 → 원하는 API 신청

**기본 사용법:**
```jsx
import axios from 'axios';

const API_KEY = 'YOUR_API_KEY';
const BASE_URL = 'http://apis.data.go.kr';

// 대중교통 경로 검색
async function searchPublicTransportRoute(startX, startY, endX, endY) {
  try {
    const response = await axios.get(
      `${BASE_URL}/1613000/SubwayInfoService/getSubwayRouteInfo`,
      {
        params: {
          serviceKey: API_KEY,
          startX,
          startY,
          endX,
          endY,
        }
      }
    );
    return response.data;
  } catch (error) {
    console.error('경로 검색 실패:', error);
  }
}
```

---

### 3. 카카오 대중교통 API

**특징:**
- 카카오맵과 통합 가능
- 경로 검색 및 안내
- 실시간 정보 제공

**기본 사용법:**
```jsx
// 카카오맵 JavaScript SDK 사용
window.kakao.maps.load(() => {
  const directions = new window.kakao.maps.Directions();
  
  directions.search({
    origin: { x: 126.9780, y: 37.5665 }, // 출발지
    destination: { x: 127.0276, y: 37.4979 }, // 도착지
    waypoints: [], // 경유지
    priority: 'TIME', // 최단시간 또는 최단거리
    transport: 'PUBLIC', // 대중교통
  }, (result, status) => {
    if (status === window.kakao.maps.services.Status.OK) {
      console.log('경로 검색 결과:', result);
    }
  });
});
```

**API 키 발급:**
- [카카오 개발자 센터](https://developers.kakao.com/)
- REST API 키 발급

---

## 🚇 지하철 API

### 1. 서울시 지하철 실시간 도착 정보

**API 엔드포인트:**
```
http://swopenAPI.seoul.go.kr/api/subway/{인증키}/json/realtimeStationArrival/0/5/{지하철역명}
```

**기본 사용법:**
```jsx
async function getSubwayArrivalInfo(stationName) {
  try {
    const response = await axios.get(
      `http://swopenAPI.seoul.go.kr/api/subway/${API_KEY}/json/realtimeStationArrival/0/5/${stationName}`
    );
    return response.data;
  } catch (error) {
    console.error('지하철 정보 조회 실패:', error);
  }
}
```

---

## 🚗 교통 정보 API

### 1. 한국도로공사 고속도로 교통정보

**특징:**
- 고속도로 실시간 교통 정보
- 구간별 소요 시간
- 사고 및 공사 정보

**API 키 발급:**
- [한국도로공사 Open API](http://data.ex.co.kr/)

---

### 2. 서울시 실시간 교통량 정보

**API 키 발급:**
- [서울 열린데이터광장](https://data.seoul.go.kr/)

---

## 📦 추천 라이브러리

### 1. Axios (HTTP 클라이언트)
```bash
npm install axios
```

### 2. React Query (데이터 페칭)
```bash
npm install @tanstack/react-query
```

**사용 예제:**
```jsx
import { useQuery } from '@tanstack/react-query';

function BusInfo({ stationId }) {
  const { data, isLoading, error } = useQuery({
    queryKey: ['busArrival', stationId],
    queryFn: () => getBusArrivalInfo(stationId),
    refetchInterval: 30000, // 30초마다 갱신
  });

  if (isLoading) return <div>로딩 중...</div>;
  if (error) return <div>에러 발생</div>;

  return (
    <div>
      {data?.map(bus => (
        <div key={bus.busId}>
          <p>{bus.routeName}: {bus.arrivalTime}분 후 도착</p>
        </div>
      ))}
    </div>
  );
}
```

---

## 💡 실전 활용 예제

### 지도 + 버스 정보 통합 예제

```jsx
import { useState, useEffect } from 'react';
import { Map, MapMarker } from 'react-kakao-maps-sdk';
import axios from 'axios';

function MapWithBusInfo() {
  const [position, setPosition] = useState({
    lat: 37.5665,
    lng: 126.9780
  });
  const [busStations, setBusStations] = useState([]);
  const [selectedStation, setSelectedStation] = useState(null);

  // 주변 버스 정류장 검색
  useEffect(() => {
    searchNearbyBusStations(position.lat, position.lng);
  }, [position]);

  const searchNearbyBusStations = async (lat, lng) => {
    try {
      // 카카오 로컬 API로 주변 정류장 검색
      const response = await axios.get(
        `https://dapi.kakao.com/v2/local/search/keyword.json`,
        {
          params: {
            query: '버스정류장',
            x: lng,
            y: lat,
            radius: 500, // 500m 반경
          },
          headers: {
            Authorization: `KakaoAK ${KAKAO_REST_API_KEY}`
          }
        }
      );
      setBusStations(response.data.documents);
    } catch (error) {
      console.error('정류장 검색 실패:', error);
    }
  };

  const getBusArrivalInfo = async (stationId) => {
    try {
      const response = await axios.get(
        'http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute',
        {
          params: {
            serviceKey: SEOUL_BUS_API_KEY,
            stId: stationId,
          }
        }
      );
      return response.data;
    } catch (error) {
      console.error('버스 정보 조회 실패:', error);
    }
  };

  return (
    <div>
      <Map
        center={position}
        style={{ width: '100%', height: '400px' }}
        level={3}
        onClick={(_, mouseEvent) => {
          setPosition({
            lat: mouseEvent.latLng.getLat(),
            lng: mouseEvent.latLng.getLng(),
          });
        }}
      >
        {busStations.map((station, index) => (
          <MapMarker
            key={index}
            position={{ lat: station.y, lng: station.x }}
            onClick={() => setSelectedStation(station)}
          >
            <div style={{ padding: '5px' }}>
              {station.place_name}
            </div>
          </MapMarker>
        ))}
      </Map>

      {selectedStation && (
        <div>
          <h3>{selectedStation.place_name}</h3>
          <BusArrivalList stationId={selectedStation.id} />
        </div>
      )}
    </div>
  );
}
```

---

## 🔐 API 키 관리 방법

### 환경 변수 사용 (.env)

```env
REACT_APP_KAKAO_MAP_KEY=your_kakao_map_key
REACT_APP_SEOUL_BUS_API_KEY=your_seoul_bus_api_key
REACT_APP_PUBLIC_DATA_API_KEY=your_public_data_api_key
```

```jsx
const KAKAO_MAP_KEY = process.env.REACT_APP_KAKAO_MAP_KEY;
const SEOUL_BUS_API_KEY = process.env.REACT_APP_SEOUL_BUS_API_KEY;
```

**주의사항:**
- `.env` 파일은 `.gitignore`에 추가
- API 키는 절대 공개 저장소에 커밋하지 않기
- 프로덕션에서는 서버 사이드에서 API 호출 권장

---

## 📚 참고 자료

### 공식 문서
- [카카오맵 API 문서](https://apis.map.kakao.com/)
- [서울 열린데이터광장](https://data.seoul.go.kr/)
- [공공데이터포털](https://www.data.go.kr/)
- [네이버 클라우드 플랫폼](https://www.ncloud.com/)
- [Google Maps Platform](https://developers.google.com/maps)

### 유용한 라이브러리
- `react-kakao-maps-sdk`: 카카오맵 React 통합
- `@react-google-maps/api`: 구글맵 React 통합
- `react-leaflet`: Leaflet React 통합
- `axios`: HTTP 클라이언트
- `@tanstack/react-query`: 데이터 페칭 및 캐싱

---

## 🎯 추천 조합

### 한국 서비스 개발 시
1. **지도**: 카카오맵 API (한글 주소 검색 우수)
2. **버스 정보**: 서울 열린데이터광장 API
3. **데이터 페칭**: React Query
4. **HTTP 클라이언트**: Axios

### 글로벌 서비스 개발 시
1. **지도**: Google Maps API
2. **대중교통**: 각 지역별 공공 API
3. **데이터 페칭**: React Query
4. **HTTP 클라이언트**: Axios

---

## ⚠️ 주의사항

1. **API 사용량 제한**: 각 API마다 일일/월간 호출 제한이 있으므로 확인 필요
2. **CORS 문제**: 브라우저에서 직접 호출 시 CORS 에러 발생 가능 → 프록시 서버 사용 고려
3. **API 키 보안**: 클라이언트에 노출되는 API 키는 제한적으로 사용
4. **에러 처리**: 네트워크 오류, API 오류 등에 대한 적절한 처리 필요
5. **로딩 상태**: 사용자 경험을 위한 로딩 인디케이터 제공

---

## 📝 라이선스 및 비용

| API | 무료 사용량 | 유료 시작 |
|-----|------------|----------|
| 카카오맵 | 일 300,000건 | 초과 시 과금 |
| 네이버 지도 | 제한적 무료 | 유료 플랜 |
| 구글맵 | 월 $200 크레딧 | 초과 시 과금 |
| 서울 버스 API | 무료 | - |
| 공공데이터포털 | 무료 | - |

---

이 가이드를 참고하여 React 애플리케이션에 지도와 교통 정보 기능을 구현해보세요!



