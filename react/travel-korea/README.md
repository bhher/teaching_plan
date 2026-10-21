# 국내 여행지 추천 사이트 (초급~중급)

한국관광공사 TourAPI로 지역별 관광지·축제·음식점을 보여주는 React 예제입니다.

## React 학습 포인트

- 카드 UI (`PlaceCard`)
- 지역 · 유형 필터
- 페이지네이션
- 확장: 카카오맵 링크, 여행 일정 localStorage 저장

## API

[한국관광공사 TourAPI](https://www.data.go.kr/) — `KorService1`

발급 키를 `.env`에 넣습니다. (디코딩된 서비스키 권장)

```env
VITE_TOUR_API_KEY=발급받은_서비스키
```

브라우저 CORS를 피하기 위해 Vite 개발 서버 프록시(`/tour-api`)를 사용합니다.

키가 없으면 **데모 데이터**로 UI를 확인할 수 있습니다.

## 실행

```bash
cd react/travel-korea
npm install
npm run dev
```

## 페이지

| 경로 | 설명 |
|------|------|
| `/` | 목록 · 필터 · 페이지네이션 |
| `/place/:id` | 상세 · 지도 · 일정 저장 |
| `/plans` | 내 여행 일정 |
