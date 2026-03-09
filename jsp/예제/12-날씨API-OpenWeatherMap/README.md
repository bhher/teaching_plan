# 🌤️ OpenWeatherMap API 사용 가이드

## 📋 개요

OpenWeatherMap은 무료 날씨 API를 제공하는 서비스입니다. JSP 프로젝트에서 날씨 정보를 가져와서 활용할 수 있습니다.

**공식 사이트:** http://openweathermap.org/

---

## 🔑 1. API 키 발급

### 1.1 회원가입

1. http://openweathermap.org/ 접속
2. 우측 상단 **Sign in** 클릭
3. **Create an Account** 또는 **Sign Up** 클릭하여 회원가입

### 1.2 API 키 복사

1. 로그인 후 상단 메뉴에서 **API keys** 탭 클릭
2. 생성된 API 키 복사
   - 예시: `cd63accc133fc76e1f94a3f270442688`

**⚠️ 주의:** API 키는 개인 정보이므로 공개하지 마세요!

---

## 🏙️ 2. 도시 코드 찾기

### 2.1 도시 검색 방법

1. 메인 화면의 **돋보기(검색창)** 클릭
2. **"weather in your city"** placeholder 부분에 도시명 입력
   - 예: `Hanam` (하남)
   - 예: `Seoul` (서울)
3. 검색된 도시 클릭
4. 날씨 정보 페이지에서 **주소표시줄** 확인

### 2.2 도시 코드 확인

**주소 형식:**
```
https://openweathermap.org/city/1897007
```

**오른쪽 숫자가 도시 코드입니다.**

**주요 도시 코드:**
- 하남: `1897007`
- 서울: `1835848`

---

## 🌐 3. API 엔드포인트

### 3.1 기본 URL 구조

```
http://api.openweathermap.org/data/2.5/{endpoint}?id={city_id}&appid={API_key}
```

**파라미터:**
- `{endpoint}`: API 종류 (forecast, weather 등)
- `{city_id}`: 도시 코드
- `{API_key}`: 발급받은 API 키

### 3.2 Forecast API (예보)

**5일간의 날씨 예보를 제공합니다.**

**URL:**
```
http://api.openweathermap.org/data/2.5/forecast?id={city_id}&appid={API_key}
```

**예시:**
```
http://api.openweathermap.org/data/2.5/forecast?id=1897007&appid=cd63accc133fc76e1f94a3f270442688
```

**응답 데이터:**
- 5일간의 날씨 예보 (3시간 간격)
- 온도, 습도, 날씨 설명, 아이콘 등

### 3.3 Weather API (현재 날씨)

**현재 날씨 정보를 제공합니다.**

**URL:**
```
http://api.openweathermap.org/data/2.5/weather?id={city_id}&appid={API_key}
```

**예시:**
```
http://api.openweathermap.org/data/2.5/weather?id=1897007&appid=cd63accc133fc76e1f94a3f270442688
```

**응답 데이터:**
- 현재 날씨 정보
- 온도, 습도, 날씨 설명, 아이콘 등

---

## 🌡️ 4. 온도 단위 설정

### 4.1 기본 단위 (Kelvin)

기본적으로 온도는 **Kelvin (켈빈)** 단위로 반환됩니다.

**예시:**
```json
{
  "main": {
    "temp": 279.23
  }
}
```

### 4.2 섭씨(Celsius)로 변경

**파라미터 추가:** `&units=metric`

**예시:**
```
http://api.openweathermap.org/data/2.5/forecast?id=1897007&appid=cd63accc133fc76e1f94a3f270442688&units=metric
```

**응답:**
```json
{
  "main": {
    "temp": 6.08  // 섭씨 온도
  }
}
```

### 4.3 화씨(Fahrenheit)로 변경

**파라미터 추가:** `&units=imperial`

**예시:**
```
http://api.openweathermap.org/data/2.5/forecast?id=1897007&appid=cd63accc133fc76e1f94a3f270442688&units=imperial
```

### 4.4 온도 단위 비교

| 단위 | 파라미터 | 예시 온도 |
|------|----------|-----------|
| Kelvin | (기본값) | 279.23 |
| Celsius | `&units=metric` | 6.08 |
| Fahrenheit | `&units=imperial` | 42.94 |

---

## 🎨 5. 날씨 아이콘 사용

### 5.1 아이콘 코드 확인

**공식 문서:** https://openweathermap.org/weather-conditions

**아이콘 코드 형식:**
- 예: `10d` (light rain, 낮)
- 예: `01n` (clear sky, 밤)

**코드 구조:**
- 앞 2자리: 날씨 코드 (01, 02, 03, 04, 09, 10, 11, 13, 50)
- 마지막 1자리: 시간대 (`d` = 낮, `n` = 밤)

### 5.2 아이콘 URL 생성

**기본 형식:**
```
https://openweathermap.org/img/wn/{icon_code}@2x.png
```

**예시:**
```
https://openweathermap.org/img/wn/10d@2x.png
```

**사용 예시:**
```html
<img src="https://openweathermap.org/img/wn/10d@2x.png" alt="날씨 아이콘">
```

### 5.3 API 응답에서 아이콘 코드 가져오기

**JSON 응답 예시:**
```json
{
  "weather": [
    {
      "id": 500,
      "main": "Rain",
      "description": "light rain",
      "icon": "10d"
    }
  ]
}
```

**JSP에서 사용:**
```jsp
<img src="https://openweathermap.org/img/wn/${weather.icon}@2x.png" 
     alt="${weather.description}">
```

---

## 🛠️ 6. 유용한 도구

### 6.1 JSON Viewer

**브라우저 확장 프로그램:**
- JSON Viewer 설치 시 JSON 데이터를 보기 좋게 볼 수 있음

**온라인 도구:**
- https://tools.arantius.com/tabifier
- HTML, CSS, JSON 코드를 정리해주는 사이트

### 6.2 API 테스트

브라우저 주소창에 직접 URL을 입력하여 API 응답을 확인할 수 있습니다.

**예시:**
```
http://api.openweathermap.org/data/2.5/weather?id=1897007&appid=cd63accc133fc76e1f94a3f270442688&units=metric
```

---

## 📝 7. JSP에서 사용하기

### 7.1 JSON 데이터 구조 이해

**Forecast API 응답 예시:**
```json
{
  "list": [
    {
      "main": {
        "temp": 6.08,
        "temp_min": 4.5,
        "temp_max": 7.2
      },
      "weather": [
        {
          "main": "Rain",
          "description": "light rain",
          "icon": "10d"
        }
      ],
      "dt_txt": "2024-01-15 12:00:00"
    }
  ]
}
```

**배열 구조:**
```javascript
var array = [1, 2, 3, 4];
array[0] = 1;

var weatherArray = [
  {temp: 6.08, temp_min: 4.5},
  {temp: 7.2, temp_min: 5.0}
];
```

### 7.2 JSP 예제 코드

**기본 구조:**
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.*, java.io.*, org.json.*" %>

<%
    String apiKey = "cd63accc133fc76e1f94a3f270442688";
    String cityId = "1897007"; // 하남
    String urlStr = "http://api.openweathermap.org/data/2.5/weather?id=" 
                    + cityId + "&appid=" + apiKey + "&units=metric";
    
    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream(), "UTF-8"));
    
    StringBuilder response = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        response.append(line);
    }
    reader.close();
    
    // JSON 파싱 (JSON 라이브러리 필요)
    JSONObject json = new JSONObject(response.toString());
    JSONObject main = json.getJSONObject("main");
    double temp = main.getDouble("temp");
    
    JSONArray weatherArray = json.getJSONArray("weather");
    JSONObject weather = weatherArray.getJSONObject(0);
    String description = weather.getString("description");
    String icon = weather.getString("icon");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>날씨 정보</title>
</head>
<body>
    <h1>하남 날씨</h1>
    <p>온도: <%= temp %>°C</p>
    <p>날씨: <%= description %></p>
    <img src="https://openweathermap.org/img/wn/<%= icon %>@2x.png" 
         alt="<%= description %>">
</body>
</html>
```

---

## 📚 8. API 가이드 참고

### 8.1 공식 가이드

1. 메인 메뉴에서 **Guide** 클릭
2. 하단에 **"How to start"** 클릭
3. **"Example on how to make an API call using your API key"** 섹션 확인

### 8.2 API 파라미터 확인

1. 메뉴에서 **API** 클릭
2. **Parameters of API response** 선택
3. **JSON** 탭 선택
4. **Units of measurement** 섹션 확인 (Ctrl + F로 검색)

---

## ✅ 체크리스트

- [ ] OpenWeatherMap 회원가입 완료
- [ ] API 키 발급 및 복사 완료
- [ ] 원하는 도시 코드 확인 완료
- [ ] Forecast API 테스트 완료
- [ ] Weather API 테스트 완료
- [ ] 온도 단위 설정 (metric) 확인 완료
- [ ] 날씨 아이콘 URL 생성 방법 이해 완료
- [ ] JSON Viewer 도구 설치 완료

---

## 💡 실전 팁

### 팁 1: API 키 보안
- API 키를 JSP 파일에 직접 하드코딩하지 마세요
- `web.xml`의 초기화 파라미터나 별도 설정 파일 사용 권장

### 팁 2: 에러 처리
- API 호출 실패 시 예외 처리 추가
- 네트워크 오류, API 키 오류 등 처리

### 팁 3: 캐싱
- 날씨 정보는 자주 변경되지 않으므로 캐싱 활용 권장
- 서버 부하 감소 및 응답 속도 향상

### 팁 4: JSON 라이브러리
- JSON 파싱을 위해 `org.json` 라이브러리 필요
- Maven 또는 수동으로 `WEB-INF/lib/`에 추가

---

## 🔗 참고 링크

- **공식 사이트:** http://openweathermap.org/
- **API 문서:** https://openweathermap.org/api
- **날씨 아이콘:** https://openweathermap.org/weather-conditions
- **도시 코드 검색:** 메인 화면 검색 기능 활용

---

**OpenWeatherMap API를 활용하여 멋진 날씨 애플리케이션을 만들어보세요! 🌈**
