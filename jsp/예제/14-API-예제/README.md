# JavaScript/jQuery API 예제 모음

다양한 무료 API를 활용한 JavaScript와 jQuery 예제 모음입니다.

## 📋 목차

1. [날씨 API - jQuery](#1-날씨-api---jquery)
2. [랜덤 사용자 API](#2-랜덤-사용자-api)
3. [인용구 API](#3-인용구-api)
4. [이미지 API - Unsplash](#4-이미지-api---unsplash)
5. [통화 환율 API](#5-통화-환율-api)
6. [세계 시간 API](#6-세계-시간-api)
7. [뉴스 API - jQuery](#7-뉴스-api---jquery)
8. [스크롤 차트 애니메이션](#8-스크롤-차트-애니메이션)

---

## 1. 날씨 API - jQuery

**파일명:** `01-날씨API-jQuery.html`

### 설명
jQuery의 `$.ajax()`를 사용하여 OpenWeatherMap API에서 날씨 정보를 가져오는 예제입니다.

### 주요 기능
- jQuery AJAX를 사용한 API 호출
- 도시별 날씨 정보 표시
- 날씨 아이콘 및 상세 정보 표시

### 핵심 코드
```javascript
$.ajax({
    url: 'https://api.openweathermap.org/data/2.5/weather',
    method: 'GET',
    data: {
        id: cityId,
        appid: apiKey,
        units: 'metric'
    },
    success: function(data) {
        // 성공 처리
    },
    error: function(xhr, status, error) {
        // 에러 처리
    }
});
```

### 사용 시나리오
- jQuery를 사용하는 프로젝트
- 간단한 AJAX 호출 예제 학습
- 날씨 정보 표시 기능 구현

---

## 2. 랜덤 사용자 API

**파일명:** `02-랜덤사용자API.html`

### 설명
RandomUser.me API를 사용하여 랜덤 사용자 정보를 가져오는 예제입니다. API 키가 필요 없습니다.

### 주요 기능
- 랜덤 사용자 프로필 생성
- 사용자 수 선택 (1명, 5명, 10명)
- 프로필 사진, 이름, 이메일, 전화번호, 주소 표시

### 핵심 코드
```javascript
fetch('https://randomuser.me/api/?results=' + count + '&nat=us,gb,kr')
    .then(response => response.json())
    .then(data => {
        data.results.forEach(function(user) {
            // 사용자 정보 표시
        });
    });
```

### 사용 시나리오
- 테스트 데이터가 필요한 경우
- 사용자 프로필 UI 개발
- 더미 데이터 생성

### API 정보
- **URL:** https://randomuser.me/api/
- **API 키:** 불필요
- **무료 사용량:** 제한 없음

---

## 3. 인용구 API

**파일명:** `03-인용구API.html`

### 설명
Quotable API를 사용하여 랜덤 인용구를 가져오는 예제입니다. API 키가 필요 없습니다.

### 주요 기능
- 랜덤 인용구 표시
- 태그별 인용구 검색
- 인용구, 작가, 태그 정보 표시

### 핵심 코드
```javascript
fetch('https://api.quotable.io/random')
    .then(response => response.json())
    .then(data => {
        // 인용구 표시
    });
```

### 사용 시나리오
- 인용구 표시 기능
- 영감을 주는 메시지 표시
- 랜덤 콘텐츠 생성

### API 정보
- **URL:** https://api.quotable.io/
- **API 키:** 불필요
- **무료 사용량:** 제한 없음

---

## 4. 이미지 API - Unsplash

**파일명:** `04-이미지API-Unsplash.html`

### 설명
Unsplash API를 사용하여 고품질 이미지를 검색하고 표시하는 예제입니다.

### 주요 기능
- 키워드로 이미지 검색
- 이미지 그리드 레이아웃
- 이미지 호버 효과
- 원본 이미지 링크

### 핵심 코드
```javascript
fetch('https://api.unsplash.com/search/photos?query=' + query + '&client_id=' + accessKey)
    .then(response => response.json())
    .then(data => {
        data.results.forEach(function(photo) {
            // 이미지 표시
        });
    });
```

### 사용 시나리오
- 이미지 갤러리 구현
- 배경 이미지 선택 기능
- 이미지 검색 기능

### API 정보
- **URL:** https://unsplash.com/developers
- **API 키:** 필요 (무료 발급 가능)
- **무료 사용량:** 시간당 50회

---

## 5. 통화 환율 API

**파일명:** `05-통화환율API.html`

### 설명
ExchangeRate-API를 사용하여 실시간 환율 정보를 가져오는 예제입니다. API 키가 필요 없습니다.

### 주요 기능
- 실시간 환율 정보
- 통화 변환 계산기
- 주요 통화 환율 목록

### 핵심 코드
```javascript
fetch('https://api.exchangerate-api.com/v4/latest/' + baseCurrency)
    .then(response => response.json())
    .then(data => {
        exchangeRates = data.rates;
        // 환율 표시
    });
```

### 사용 시나리오
- 환율 변환 계산기
- 다국가 쇼핑몰 가격 표시
- 금융 앱 개발

### API 정보
- **URL:** https://www.exchangerate-api.com/
- **API 키:** 불필요 (무료 플랜)
- **무료 사용량:** 월 1,500회

---

## 6. 세계 시간 API

**파일명:** `06-시간API-WorldTime.html`

### 설명
WorldTimeAPI를 사용하여 여러 도시의 현재 시간을 실시간으로 표시하는 예제입니다.

### 주요 기능
- 여러 도시의 시간 동시 표시
- 실시간 시간 업데이트
- 날짜 및 요일 표시
- 타임존 정보 표시

### 핵심 코드
```javascript
fetch('https://worldtimeapi.org/api/timezone/' + timezone)
    .then(response => response.json())
    .then(data => {
        var date = new Date(data.datetime);
        // 시간 표시
    });
```

### 사용 시나리오
- 세계 시계 앱
- 다국가 서비스의 시간 표시
- 회의 일정 관리 시스템

### API 정보
- **URL:** https://worldtimeapi.org/
- **API 키:** 불필요
- **무료 사용량:** 제한 없음

---

## 7. 뉴스 API - jQuery

**파일명:** `07-뉴스API-jQuery.html`

### 설명
NewsAPI를 사용하여 최신 뉴스 헤드라인을 가져오는 jQuery 예제입니다.

### 주요 기능
- 카테고리별 뉴스 조회 (일반, 기술, 비즈니스, 스포츠, 엔터테인먼트)
- 키워드 검색
- 뉴스 제목, 설명, 출처, 날짜 표시
- 원본 기사 링크

### 핵심 코드
```javascript
$.ajax({
    url: 'https://newsapi.org/v2/top-headlines?category=' + category + '&country=kr&apiKey=' + apiKey,
    method: 'GET',
    success: function(data) {
        data.articles.forEach(function(article) {
            // 뉴스 표시
        });
    },
    error: function(xhr, status, error) {
        // 에러 처리
    }
});
```

### 사용 시나리오
- 뉴스 앱 개발
- 뉴스 피드 구현
- 콘텐츠 집계 서비스

### API 정보
- **URL:** https://newsapi.org/
- **API 키:** 필요 (무료 발급 가능)
- **무료 사용량:** 일일 100회
- **주의:** 개발 환경(localhost)에서만 작동, 배포 시 서버 사이드 호출 필요

---

## 8. 스크롤 차트 애니메이션

**파일명:** `08-스크롤-차트-애니메이션.html`

### 설명
사용자가 페이지를 스크롤할 때 차트가 화면에 나타나면서 애니메이션 효과를 보여주는 예제입니다. Chart.js 라이브러리와 Intersection Observer API를 사용합니다.

### 주요 기능
- 스크롤 감지: Intersection Observer API로 차트가 화면에 나타날 때 감지
- 페이드인 애니메이션: 차트 섹션이 나타날 때 부드러운 페이드인 효과
- 막대 그래프: 범주형 데이터 비교
- 원형 그래프: 비율 및 구성 요소 시각화
- 선 그래프: 시간에 따른 추이 분석

### 핵심 코드
```javascript
// Intersection Observer 설정
var observer = new IntersectionObserver(function(entries) {
    entries.forEach(function(entry) {
        if (entry.isIntersecting) {
            entry.target.classList.add('visible');
            createBarChart();  // 차트 생성
        }
    });
}, { threshold: 0.3 });

// Chart.js로 차트 생성
var barChart = new Chart(ctx, {
    type: 'bar',
    data: { /* 데이터 */ },
    options: {
        animation: { duration: 2000 }
    }
});
```

### 사용 시나리오
- 대시보드 및 데이터 시각화
- 스크롤 기반 인터랙티브 웹사이트
- 데이터 분석 결과 표시
- 보고서 및 프레젠테이션

### 기술 스택
- **Chart.js**: 차트 라이브러리
- **Intersection Observer API**: 스크롤 감지
- **CSS Transitions**: 부드러운 애니메이션

### 상세 설명
자세한 설명은 `08-스크롤-차트-애니메이션-설명.md` 파일을 참고하세요.

---

## 🚀 시작하기

### 1. 파일 실행
각 HTML 파일을 브라우저에서 직접 열거나 웹 서버를 통해 실행하세요.

### 2. API 키 설정 (필요한 경우)
- **Unsplash API:** `04-이미지API-Unsplash.html` 파일의 `accessKey` 변수에 본인의 키를 입력하세요.
- **날씨 API:** `01-날씨API-jQuery.html` 파일의 `apiKey` 변수에 본인의 키를 입력하세요.
- **뉴스 API:** `07-뉴스API-jQuery.html` 파일의 `apiKey` 변수에 본인의 키를 입력하세요.

### 3. CORS 문제 해결
일부 API는 브라우저에서 직접 호출 시 CORS 문제가 발생할 수 있습니다.

**해결 방법:**
1. 웹 서버를 통해 실행 (권장)
2. CORS 프록시 서버 사용
3. 브라우저 확장 프로그램 사용 (개발용)

---

## 📚 API 키 발급 방법

### OpenWeatherMap
1. https://openweathermap.org/api 접속
2. Sign Up으로 계정 생성
3. API Keys에서 키 발급

### Unsplash
1. https://unsplash.com/developers 접속
2. New Application으로 앱 등록
3. Access Key 발급

### NewsAPI
1. https://newsapi.org/ 접속
2. Sign Up으로 계정 생성
3. API 키 발급
4. **주의:** 개발 환경(localhost)에서만 작동하며, 실제 배포 시에는 서버 사이드에서 API를 호출해야 합니다.

---

## 💡 학습 포인트

### jQuery vs 순수 JavaScript
- **jQuery:** 간단한 문법, 크로스 브라우저 호환성
- **순수 JavaScript:** 최신 표준, 외부 라이브러리 불필요

### Fetch API
- Promise 기반 비동기 처리
- `.then()` / `.catch()` 체이닝
- `async/await` 문법 지원

### 에러 처리
- try-catch 블록
- Promise의 catch 메서드
- 사용자 친화적인 에러 메시지

---

## ⚠️ 주의사항

1. **API 사용량 제한**: 각 API마다 사용량 제한이 있으므로 확인 필요
2. **API 키 보안**: 클라이언트에 노출되는 API 키는 사용량 제한 설정 권장
3. **CORS 문제**: 브라우저 보안 정책으로 인한 CORS 에러 발생 가능
4. **네트워크 오류**: 인터넷 연결이 필요하며, 일부 API는 HTTPS 필수

---

## 📝 라이선스

이 예제들은 교육 목적으로 제공되며, 각 API의 이용약관을 준수해야 합니다.
