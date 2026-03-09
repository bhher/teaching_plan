<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.*, java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // API 설정
    String apiKey = "cd63accc133fc76e1f94a3f270442688";
    String cityId = "1897007"; // 하남
    String urlStr = "http://api.openweathermap.org/data/2.5/weather?id=" 
                    + cityId + "&appid=" + apiKey + "&units=metric";
    
    String jsonResponse = "";
    String errorMessage = "";
    
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
        
        jsonResponse = response.toString();
        request.setAttribute("jsonResponse", jsonResponse);
        
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
        }
        
        .weather-info {
            text-align: center;
            padding: 30px;
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            border-radius: 15px;
            margin-bottom: 20px;
        }
        
        .weather-icon {
            width: 120px;
            height: 120px;
            margin: 20px auto;
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
        
        .json-viewer {
            background: #282c34;
            color: #abb2bf;
            padding: 20px;
            border-radius: 10px;
            overflow-x: auto;
            font-family: 'Courier New', monospace;
            font-size: 12px;
            white-space: pre-wrap;
            word-wrap: break-word;
            max-height: 400px;
            overflow-y: auto;
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
    </style>
</head>
<body>
    <div class="container">
        <h1>🌤️ 날씨 정보</h1>
        
        <div class="city-selector">
            <select id="citySelect" onchange="changeCity()">
                <option value="1897007" selected>하남</option>
                <option value="1835848">서울</option>
            </select>
        </div>
        
        <c:if test="${not empty errorMessage}">
            <div class="error">
                <p>${errorMessage}</p>
                <p style="margin-top: 10px; font-size: 0.9em;">
                    API 키와 도시 코드를 확인해주세요.
                </p>
            </div>
        </c:if>
        
        <c:if test="${empty errorMessage}">
            <div class="weather-info">
                <p style="font-size: 1.2em; margin-bottom: 20px;">
                    <strong>하남</strong> 날씨
                </p>
                
                <div class="note">
                    <strong>참고:</strong> 실제 날씨 정보를 표시하려면 JSON 파싱 라이브러리(org.json)가 필요합니다.<br>
                    현재는 API 응답 JSON만 표시합니다.
                </div>
                
                <div class="json-viewer" id="jsonViewer">
                    ${jsonResponse}
                </div>
            </div>
        </c:if>
        
        <div class="note">
            <strong>사용 방법:</strong><br>
            1. org.json 라이브러리를 WEB-INF/lib/에 추가<br>
            2. JSON 파싱 코드 추가하여 날씨 정보 추출<br>
            3. 온도, 날씨 설명, 아이콘 등을 화면에 표시
        </div>
    </div>
    
    <script>
        function changeCity() {
            var cityId = document.getElementById('citySelect').value;
            var cityName = document.getElementById('citySelect').options[document.getElementById('citySelect').selectedIndex].text;
            window.location.href = 'weather-example.jsp?cityId=' + cityId + '&cityName=' + encodeURIComponent(cityName);
        }
    </script>
</body>
</html>
