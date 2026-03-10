# 카카오맵 예제 모음

카카오맵 API를 활용한 6가지 실전 예제 모음입니다.

## 📋 목차

1. [기본 맵 예제](#1-기본-맵-예제)
2. [마커 예제](#2-마커-예제)
3. [인포윈도우 예제](#3-인포윈도우-예제)
4. [커스텀 마커 예제](#4-커스텀-마커-예제)
5. [클러스터 예제](#5-클러스터-예제)
6. [주소 검색 예제](#6-주소-검색-예제)

---

## 1. 기본 맵 예제

**파일명:** `kakao-map-basic.html`

### 설명
카카오맵을 웹페이지에 삽입하는 가장 기본적인 예제입니다. 지도를 표시하고 클릭 이벤트를 통해 좌표를 확인할 수 있습니다.

### 주요 기능
- 지도 표시
- 지도 클릭 시 좌표 표시
- 지도 확대/축소 레벨 조절

### 핵심 코드
```javascript
var container = document.getElementById('map');
var options = {
    center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울시청
    level: 3 // 지도 확대 레벨 (1~14)
};
var map = new kakao.maps.Map(container, options);
```

### 사용 시나리오
- 간단한 지도 표시가 필요한 경우
- 지도 API를 처음 사용하는 학습자용
- 기본 지도 기능 테스트

---

## 2. 마커 예제

**파일명:** `kakao-map-marker.html`

### 설명
여러 위치에 마커를 표시하고 동적으로 추가/제거하는 방법을 보여주는 예제입니다.

### 주요 기능
- 여러 위치에 마커 표시
- 마커 동적 추가/제거
- 모든 마커를 한 화면에 보기
- 마커 목록 표시

### 핵심 코드
```javascript
// 마커 생성
var marker = new kakao.maps.Marker({
    position: new kakao.maps.LatLng(37.5665, 126.9780),
    title: '서울시청'
});
marker.setMap(map);

// 마커 제거
marker.setMap(null);

// 모든 마커 보기
var bounds = new kakao.maps.LatLngBounds();
markers.forEach(function(marker) {
    bounds.extend(marker.getPosition());
});
map.setBounds(bounds);
```

### 사용 시나리오
- 여러 지점을 표시해야 하는 경우
- 매장 위치, 관광지 안내 등
- 마커를 동적으로 관리해야 하는 경우

---

## 3. 인포윈도우 예제

**파일명:** `kakao-map-infowindow.html`

### 설명
마커를 클릭하면 정보창(인포윈도우)을 표시하는 예제입니다. 위치에 대한 상세 정보를 사용자에게 제공합니다.

### 주요 기능
- 마커 클릭 시 정보창 표시
- 여러 인포윈도우 관리
- 리스트 클릭으로 지도 이동 및 정보창 표시
- 인포윈도우 자동 닫기

### 핵심 코드
```javascript
// 인포윈도우 생성
var infowindow = new kakao.maps.InfoWindow({
    content: '<div>정보 내용</div>'
});

// 인포윈도우 열기
infowindow.open(map, marker);

// 인포윈도우 닫기
infowindow.close();

// 마커 클릭 이벤트
kakao.maps.event.addListener(marker, 'click', function() {
    infowindow.open(map, marker);
});
```

### 사용 시나리오
- 위치에 대한 상세 정보를 제공해야 하는 경우
- 매장 정보, 이벤트 안내 등
- 사용자 인터랙션이 필요한 경우

---

## 4. 커스텀 마커 예제

**파일명:** `kakao-map-custom-marker.html`

### 설명
기본 마커 대신 사용자 정의 이미지나 Canvas로 만든 커스텀 마커를 사용하는 예제입니다.

### 주요 기능
- 기본 마커, 빨간 마커, 파란 마커, 커스텀 마커 전환
- Canvas를 이용한 마커 이미지 생성
- 다양한 색상의 마커 지원

### 핵심 코드
```javascript
// Canvas로 마커 이미지 생성
function createMarkerCanvas(color) {
    var canvas = document.createElement('canvas');
    canvas.width = 50;
    canvas.height = 50;
    var ctx = canvas.getContext('2d');
    
    // 마커 모양 그리기
    ctx.beginPath();
    ctx.moveTo(25, 0);
    ctx.lineTo(45, 45);
    ctx.lineTo(25, 50);
    ctx.lineTo(5, 45);
    ctx.closePath();
    ctx.fillStyle = color;
    ctx.fill();
    
    return canvas.toDataURL();
}

// 커스텀 마커 사용
var markerImage = new kakao.maps.MarkerImage(
    createMarkerCanvas('#3B82F6'),
    new kakao.maps.Size(50, 50),
    {offset: new kakao.maps.Point(25, 50)}
);

var marker = new kakao.maps.Marker({
    position: coords,
    image: markerImage
});
```

### 사용 시나리오
- 브랜드 아이덴티티를 반영한 마커가 필요한 경우
- 카테고리별로 다른 색상의 마커를 사용하는 경우
- 특별한 디자인의 마커가 필요한 경우

---

## 5. 클러스터 예제

**파일명:** `kakao-map-cluster.html`

### 설명
많은 마커를 효율적으로 표시하기 위해 클러스터링 기능을 사용하는 예제입니다. 마커가 많을 때 자동으로 그룹화하여 성능을 향상시킵니다.

### 주요 기능
- 마커 클러스터링
- 동적 마커 추가/제거
- 클러스터 통계 표시
- 지도 확대 시 클러스터 분리

### 핵심 코드
```javascript
// 클러스터러 초기화
var clusterer = new kakao.maps.MarkerClusterer({
    map: map,
    averageCenter: true,
    minLevel: 4,
    gridSize: 60
});

// 마커 추가
clusterer.addMarkers(markers);

// 모든 마커 제거
clusterer.clear();
```

### 사용 시나리오
- 많은 마커(100개 이상)를 표시해야 하는 경우
- 성능 최적화가 필요한 경우
- 지도 확대/축소에 따라 마커를 동적으로 표시해야 하는 경우

---

## 6. 주소 검색 예제

**파일명:** `kakao-map-geocoding.html`

### 설명
주소를 입력하면 해당 위치를 지도에 표시하는 주소 검색(Geocoding) 예제입니다. 카카오맵의 주소 검색 API를 활용합니다.

### 주요 기능
- 주소 검색
- 검색 결과 목록 표시
- 빠른 검색 버튼
- 검색 결과 클릭 시 지도 이동
- 마커 자동 표시

### 핵심 코드
```javascript
// Geocoder 초기화
var geocoder = new kakao.maps.services.Geocoder();

// 주소 검색
geocoder.addressSearch(address, function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        
        // 마커 생성
        var marker = new kakao.maps.Marker({
            position: coords
        });
        marker.setMap(map);
        
        // 지도 중심 이동
        map.setCenter(coords);
    }
});
```

### 사용 시나리오
- 주소로 위치를 찾아야 하는 경우
- 사용자 입력 주소를 지도에 표시하는 경우
- 주소 검색 기능이 필요한 웹사이트

---

## 🚀 시작하기

### 1. API 키 발급
1. [카카오 개발자 센터](https://developers.kakao.com/) 접속
2. 애플리케이션 등록
3. JavaScript 키 발급
4. 플랫폼 설정에서 Web 플랫폼 추가 및 도메인 등록

### 2. API 키 설정
각 HTML 파일의 스크립트 태그에서 API 키를 변경하세요:
```html
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=YOUR_JAVASCRIPT_KEY"></script>
```

### 3. 파일 실행
HTML 파일을 브라우저에서 직접 열거나 웹 서버를 통해 실행하세요.

---

## 📚 추가 학습 자료

- [카카오맵 API 공식 문서](https://apis.map.kakao.com/)
- [카카오 개발자 센터](https://developers.kakao.com/)
- [카카오맵 JavaScript API 가이드](https://apis.map.kakao.com/web/guide/)

---

## ⚠️ 주의사항

1. **API 키 보안**: API 키는 클라이언트에 노출되므로, 사용량 제한을 설정하는 것을 권장합니다.
2. **도메인 설정**: 카카오 개발자 센터에서 사용할 도메인을 등록해야 합니다.
3. **사용량 제한**: 무료 사용량은 일일 300,000건입니다.
4. **CORS 문제**: 로컬 파일(`file://`)에서 실행 시 일부 기능이 제한될 수 있습니다.

---

## 📝 라이선스

이 예제들은 교육 목적으로 제공되며, 카카오맵 API의 이용약관을 준수해야 합니다.
