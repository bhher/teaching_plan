<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.*, java.io.*, org.json.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // API 설정
    String apiKey = "cd63accc133fc76e1f94a3f270442688";
    String cityId = request.getParameter("cityId");
    if (cityId == null || cityId.isEmpty()) {
        cityId = "1897007"; // 기본값: 하남
    }
    
    String cityName = request.getParameter("cityName");
    if (cityName == null || cityName.isEmpty()) {
        cityName = "하남";
    }
    
    String urlStr = "http://api.openweathermap.org/data/2.5/weather?id=" 
                    + cityId + "&appid=" + apiKey + "&units=metric";
    
    // 날씨 정보 변수
    String errorMessage = "";
    double temp = 0;
    double tempMin = 0;
    double tempMax = 0;
    double feelsLike = 0;
    int humidity = 0;
    String description = "";
    String icon = "";
    String mainWeather = "";
    
    try {
        // API 호출
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        
        // 응답 읽기
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "UTF-8"));
        
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        
        // JSON 파싱
        JSONObject json = new JSONObject(response.toString());
        
        // main 객체에서 온도 정보 추출
        JSONObject main = json.getJSONObject("main");
        temp = main.getDouble("temp");
        tempMin = main.getDouble("temp_min");
        tempMax = main.getDouble("temp_max");
        feelsLike = main.getDouble("feels_like");
        humidity = main.getInt("humidity");
        
        // weather 배열에서 날씨 정보 추출
        JSONArray weatherArray = json.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        mainWeather = weather.getString("main");
        description = weather.getString("description");
        icon = weather.getString("icon");
        
        // request에 저장
        request.setAttribute("temp", temp);
        request.setAttribute("tempMin", tempMin);
        request.setAttribute("tempMax", tempMax);
        request.setAttribute("feelsLike", feelsLike);
        request.setAttribute("humidity", humidity);
        request.setAttribute("description", description);
        request.setAttribute("icon", icon);
        request.setAttribute("mainWeather", mainWeather);
        request.setAttribute("cityName", cityName);
        
    } catch (Exception e) {
        errorMessage = "날씨 정보를 가져오는 중 오류가 발생했습니다: " + e.getMessage();
        request.setAttribute("errorMessage", errorMessage);
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>날씨 정보 - OpenWeatherMap API</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Malgun Gothic', sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            background: white;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
            padding: 40px;
            max-width: 600px;
            width: 100%;
        }
        
        h1 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
            font-size: 2em;
        }
        
        .city-selector {
            margin-bottom: 30px;
            text-align: center;
        }
        
        .city-selector select {
            padding: 10px 20px;
            font-size: 16px;
            border: 2px solid #667eea;
            border-radius: 10px;
            background: white;
            cursor: pointer;
            outline: none;
        }
        
        .city-selector select:hover {
            border-color: #764ba2;
        }
        
        .weather-info {
            text-align: center;
            padding: 30px;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            border-radius: 15px;
            margin-bottom: 20px;
        }
        
        .city-name {
            font-size: 1.5em;
            color: #333;
            margin-bottom: 20px;
            font-weight: bold;
        }
        
        .weather-icon {
            width: 120px;
            height: 120px;
            margin: 20px auto;
            display: block;
        }
        
        .temperature {
            font-size: 4em;
            font-weight: bold;
            color: #333;
            margin: 20px 0;
        }
        
        .description {
            font-size: 1.5em;
            color: #666;
            margin-bottom: 20px;
            text-transform: capitalize;
        }
        
        .details {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 15px;
            margin-top: 30px;
        }
        
        .detail-item {
            background: white;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .detail-label {
            font-size: 0.9em;
            color: #666;
            margin-bottom: 5px;
        }
        
        .detail-value {
            font-size: 1.3em;
            font-weight: bold;
            color: #333;
        }
        
        .error {
            background: #fee;
            color: #c33;
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }
        
        .note {
            background: #e3f2fd;
            padding: 15px;
            border-radius: 10px;
            margin-top: 20px;
            font-size: 0.9em;
            color: #1976d2;
        }
        
        @media (max-width: 600px) {
            .details {
                grid-template-columns: 1fr;
            }
            
            .temperature {
                font-size: 3em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🌤️ 날씨 정보</h1>
        
        <div class="city-selector">
            <select id="citySelect" onchange="changeCity()">
                <option value="1897007" ${cityName == '하남' ? 'selected' : ''}>하남</option>
                <option value="1835848" ${cityName == '서울' ? 'selected' : ''}>서울</option>
            </select>
        </div>
        
        <c:if test="${not empty errorMessage}">
            <div class="error">
                <p>${errorMessage}</p>
                <p style="margin-top: 10px; font-size: 0.9em;">
                    API 키와 도시 코드를 확인해주세요.<br>
                    org.json 라이브러리가 필요합니다.
                </p>
            </div>
        </c:if>
        
        <c:if test="${empty errorMessage}">
            <div class="weather-info">
                <div class="city-name">${cityName}</div>
                
                <img src="https://openweathermap.org/img/wn/${icon}@2x.png" 
                     alt="${description}" 
                     class="weather-icon">
                
                <div class="temperature">${temp}°C</div>
                
                <div class="description">${description}</div>
                
                <div class="details">
                    <div class="detail-item">
                        <div class="detail-label">체감 온도</div>
                        <div class="detail-value">${feelsLike}°C</div>
                    </div>
                    
                    <div class="detail-item">
                        <div class="detail-label">최저 온도</div>
                        <div class="detail-value">${tempMin}°C</div>
                    </div>
                    
                    <div class="detail-item">
                        <div class="detail-label">최고 온도</div>
                        <div class="detail-value">${tempMax}°C</div>
                    </div>
                    
                    <div class="detail-item">
                        <div class="detail-label">습도</div>
                        <div class="detail-value">${humidity}%</div>
                    </div>
                </div>
            </div>
        </c:if>
        
        <div class="note">
            <strong>📌 사용 방법:</strong><br>
            1. org.json 라이브러리를 WEB-INF/lib/에 추가<br>
            2. API 키를 본인의 키로 변경<br>
            3. 도시 코드를 변경하여 다른 지역 날씨 확인
        </div>
    </div>
    
    <script>
        function changeCity() {
            var select = document.getElementById('citySelect');
            var cityId = select.value;
            var cityName = select.options[select.selectedIndex].text;
            window.location.href = 'weather-example-with-json.jsp?cityId=' + cityId + '&cityName=' + encodeURIComponent(cityName);
        }
    </script>
</body>
</html>
