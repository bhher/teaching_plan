# 🗺️ 카카오맵 API 사용 가이드

## 📋 개요

카카오맵 JavaScript API를 사용하여 웹페이지에 지도를 삽입하고 다양한 기능을 구현하는 방법을 학습합니다.

**공식 사이트:** https://apis.map.kakao.com/

---

## 🔑 1. 카카오맵 API 키 발급

### 1.1 카카오 개발자 계정 생성

1. **카카오 개발자 사이트 접속**
   - https://developers.kakao.com/ 접속

2. **회원가입**
   - 카카오 계정으로 로그인
   - 개발자 등록 진행

### 1.2 애플리케이션 등록

1. **내 애플리케이션** 메뉴 클릭
2. **애플리케이션 추가하기** 클릭
3. 앱 이름, 사업자명 등 입력하여 생성

### 1.3 JavaScript 키 발급

1. 생성한 애플리케이션 선택
2. **앱 키** 탭 클릭
3. **JavaScript 키** 복사
   - 예시: `YOUR_JAVASCRIPT_KEY`

**⚠️ 중요:** JavaScript 키는 공개되어도 되지만, 도메인 제한을 설정하는 것을 권장합니다.

### 1.4 플랫폼 설정

1. **플랫폼** 탭 클릭
2. **Web 플랫폼 등록** 클릭
3. 사이트 도메인 등록 (예: `http://localhost:8080`)

---

## 📐 2. 기본 지도 생성

### 2.1 HTML 구조

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>카카오맵</title>
</head>
<body>
    <!-- 지도를 표시할 div -->
    <div id="map" style="width:100%;height:400px;"></div>
    
    <!-- 카카오맵 API 스크립트 -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=YOUR_JAVASCRIPT_KEY"></script>
    <script>
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울시청
            level: 3 // 지도 확대 레벨
        };
        
        var map = new kakao.maps.Map(container, options);
    </script>
</body>
</html>
```

### 2.2 주요 옵션

| 옵션 | 설명 | 예시 |
|------|------|------|
| `center` | 지도 중심 좌표 | `new kakao.maps.LatLng(위도, 경도)` |
| `level` | 지도 확대 레벨 (1~14) | `3` (숫자가 작을수록 확대) |
| `draggable` | 지도 드래그 가능 여부 | `true` (기본값) |
| `scrollwheel` | 마우스 휠로 확대/축소 | `true` (기본값) |
| `disableDoubleClick` | 더블클릭 확대 비활성화 | `false` (기본값) |
| `disableDoubleClickZoom` | 더블클릭 확대 비활성화 | `false` (기본값) |

---

## 📍 3. 마커 표시

### 3.1 기본 마커

```javascript
// 마커 위치
var markerPosition = new kakao.maps.LatLng(37.5665, 126.9780);

// 마커 생성
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커를 지도에 표시
marker.setMap(map);
```

### 3.2 커스텀 마커 이미지

```javascript
// 커스텀 마커 이미지
var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png';
var imageSize = new kakao.maps.Size(50, 50);
var imageOption = {offset: new kakao.maps.Point(25, 50)};

var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: markerImage
});

marker.setMap(map);
```

### 3.3 여러 개의 마커

```javascript
var positions = [
    {title: '위치1', latlng: new kakao.maps.LatLng(37.5665, 126.9780)},
    {title: '위치2', latlng: new kakao.maps.LatLng(37.5651, 126.9895)},
    {title: '위치3', latlng: new kakao.maps.LatLng(37.5700, 126.9769)}
];

positions.forEach(function(pos) {
    var marker = new kakao.maps.Marker({
        position: pos.latlng
    });
    marker.setMap(map);
});
```

---

## 💬 4. 인포윈도우 (정보창)

### 4.1 기본 인포윈도우

```javascript
// 인포윈도우 내용
var iwContent = '<div style="padding:5px;">서울시청</div>';
var iwPosition = new kakao.maps.LatLng(37.5665, 126.9780);

// 인포윈도우 생성
var infowindow = new kakao.maps.InfoWindow({
    position: iwPosition,
    content: iwContent
});

// 인포윈도우 표시
infowindow.open(map, marker);
```

### 4.2 커스텀 인포윈도우

```javascript
var iwContent = `
    <div style="padding:15px; min-width:200px;">
        <h3 style="margin:0 0 10px 0;">서울시청</h3>
        <p style="margin:0;">서울특별시 중구 세종대로 110</p>
        <a href="https://www.seoul.go.kr" target="_blank" style="color:blue;">홈페이지</a>
    </div>
`;

var infowindow = new kakao.maps.InfoWindow({
    content: iwContent
});

// 마커 클릭 시 인포윈도우 표시
kakao.maps.event.addListener(marker, 'click', function() {
    infowindow.open(map, marker);
});
```

---

## 🎨 5. 지도 타입 변경

### 5.1 지도 타입 컨트롤 추가

```javascript
// 지도 타입 컨트롤 생성
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도 우측 상단에 추가
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
```

### 5.2 지도 타입 변경

```javascript
// 지도 타입 변경 (ROADMAP, SKYVIEW, HYBRID)
map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP); // 일반 지도
map.setMapTypeId(kakao.maps.MapTypeId.SKYVIEW); // 스카이뷰
map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);  // 하이브리드
```

---

## 🔍 6. 지도 컨트롤

### 6.1 줌 컨트롤

```javascript
// 줌 컨트롤 생성
var zoomControl = new kakao.maps.ZoomControl();

// 지도 우측 하단에 추가
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
```

### 6.2 컨트롤 위치

| 위치 | 설명 |
|------|------|
| `TOPLEFT` | 좌측 상단 |
| `TOPRIGHT` | 우측 상단 |
| `BOTTOMLEFT` | 좌측 하단 |
| `BOTTOMRIGHT` | 우측 하단 |
| `LEFT` | 좌측 중앙 |
| `RIGHT` | 우측 중앙 |
| `TOP` | 상단 중앙 |
| `BOTTOM` | 하단 중앙 |

---

## 📊 7. 마커 클러스터링

### 7.1 클러스터러 사용

```javascript
// 마커 배열
var markers = [];

// 여러 위치에 마커 생성
var positions = [
    new kakao.maps.LatLng(37.5665, 126.9780),
    new kakao.maps.LatLng(37.5651, 126.9895),
    new kakao.maps.LatLng(37.5700, 126.9769)
];

positions.forEach(function(position) {
    var marker = new kakao.maps.Marker({
        position: position
    });
    markers.push(marker);
});

// 마커 클러스터러 생성
var clusterer = new kakao.maps.MarkerClusterer({
    map: map,
    markers: markers,
    gridSize: 60,
    averageCenter: true,
    minLevel: 6
});
```

---

## 🎯 8. 주소 검색 (Geocoding)

### 8.1 주소 → 좌표 변환

```javascript
var geocoder = new kakao.maps.services.Geocoder();

geocoder.addressSearch('서울특별시 중구 세종대로 110', function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        
        // 지도 중심 이동
        map.setCenter(coords);
        
        // 마커 표시
        var marker = new kakao.maps.Marker({
            position: coords
        });
        marker.setMap(map);
    }
});
```

### 8.2 좌표 → 주소 변환 (Reverse Geocoding)

```javascript
var geocoder = new kakao.maps.services.Geocoder();

geocoder.coord2Address(126.9780, 37.5665, function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var address = result[0].address.address_name;
        console.log('주소:', address);
    }
});
```

---

## 📱 9. 반응형 지도

### 9.1 CSS 설정

```css
#map {
    width: 100%;
    height: 400px;
}

@media (max-width: 768px) {
    #map {
        height: 300px;
    }
}
```

### 9.2 지도 크기 조정

```javascript
// 창 크기 변경 시 지도 크기 조정
window.addEventListener('resize', function() {
    map.relayout();
});
```

---

## 🎨 10. 지도 스타일 커스터마이징

### 10.1 지도 색상 변경

```javascript
// 지도 스타일 설정
var mapStyle = [
    {
        featureType: 'all',
        elementType: 'all',
        stylers: [
            { hue: '#ff0000' },      // 색상
            { saturation: -50 },     // 채도
            { lightness: 50 }        // 명도
        ]
    }
];

map.addOverlayMapTypeId(kakao.maps.MapTypeId.ROADMAP);
```

---

## ✅ 체크리스트

- [ ] 카카오 개발자 계정 생성 완료
- [ ] 애플리케이션 등록 완료
- [ ] JavaScript 키 발급 완료
- [ ] 플랫폼(도메인) 설정 완료
- [ ] 기본 지도 생성 테스트 완료
- [ ] 마커 표시 테스트 완료
- [ ] 인포윈도우 표시 테스트 완료
- [ ] 주소 검색 기능 테스트 완료

---

## 💡 실전 팁

### 팁 1: API 키 보안
- JavaScript 키는 공개되어도 되지만, 도메인 제한 설정 권장
- 서버에서 사용하는 REST API 키는 절대 공개하지 마세요

### 팁 2: 성능 최적화
- 많은 마커가 있을 때는 클러스터러 사용 권장
- 지도 로드 후 마커 추가하기

### 팁 3: 좌표계
- 카카오맵은 WGS84 좌표계 사용
- 위도(latitude), 경도(longitude) 순서 주의

### 팁 4: 이벤트 처리
- 마커 클릭, 지도 클릭 등 다양한 이벤트 활용
- `kakao.maps.event.addListener()` 사용

---

## 🔗 참고 링크

- **카카오맵 API 문서:** https://apis.map.kakao.com/
- **카카오 개발자 콘솔:** https://developers.kakao.com/
- **카카오맵 예제:** https://apis.map.kakao.com/web/sample/

---

**카카오맵 API를 활용하여 멋진 지도 애플리케이션을 만들어보세요! 🗺️**
