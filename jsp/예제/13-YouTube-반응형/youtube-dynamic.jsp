<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 동영상 정보 배열
    String[][] videos = {
        {"dQw4w9WgXcQ", "동영상 제목 1", "이것은 첫 번째 동영상입니다."},
        {"jNQXAC9IVRw", "동영상 제목 2", "이것은 두 번째 동영상입니다."},
        {"dQw4w9WgXcQ", "동영상 제목 3", "이것은 세 번째 동영상입니다."}
    };
    
    // 선택된 동영상 ID (파라미터에서 가져오기)
    String selectedVideoId = request.getParameter("id");
    if (selectedVideoId == null || selectedVideoId.isEmpty()) {
        selectedVideoId = videos[0][0]; // 기본값
    }
    
    // 선택된 동영상 정보 찾기
    String selectedTitle = "";
    String selectedDescription = "";
    for (String[] video : videos) {
        if (video[0].equals(selectedVideoId)) {
            selectedTitle = video[1];
            selectedDescription = video[2];
            break;
        }
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YouTube 동적 삽입 예제</title>
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
            padding: 20px;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
        }
        
        h1 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
        }
        
        .main-video {
            margin-bottom: 40px;
        }
        
        .video-container {
            position: relative;
            width: 100%;
            padding-bottom: 56.25%; /* 16:9 비율 */
            height: 0;
            overflow: hidden;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            margin-bottom: 20px;
        }
        
        .video-container iframe {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            border: none;
        }
        
        .video-info {
            padding: 20px;
            background: #f5f5f5;
            border-radius: 10px;
        }
        
        .video-info h2 {
            color: #333;
            margin-bottom: 10px;
        }
        
        .video-info p {
            color: #666;
            line-height: 1.6;
        }
        
        .video-list {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        
        .video-thumbnail {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
            cursor: pointer;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        
        .video-thumbnail:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.3);
        }
        
        .thumbnail-wrapper {
            position: relative;
            width: 100%;
            padding-bottom: 56.25%;
            height: 0;
            overflow: hidden;
            background: #000;
        }
        
        .thumbnail-wrapper img {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        
        .thumbnail-info {
            padding: 15px;
        }
        
        .thumbnail-info h3 {
            color: #333;
            font-size: 14px;
            margin-bottom: 5px;
        }
        
        .thumbnail-info p {
            color: #999;
            font-size: 12px;
        }
        
        .code-example {
            background: #282c34;
            color: #abb2bf;
            padding: 20px;
            border-radius: 10px;
            margin-top: 30px;
            overflow-x: auto;
        }
        
        .code-example pre {
            margin: 0;
            font-family: 'Courier New', monospace;
            font-size: 12px;
            line-height: 1.6;
        }
        
        .note {
            background: #e3f2fd;
            padding: 15px;
            border-radius: 10px;
            margin-top: 20px;
            border-left: 4px solid #2196f3;
        }
        
        .note strong {
            color: #1976d2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎬 YouTube 동적 삽입 예제</h1>
        
        <div class="main-video">
            <div class="video-container">
                <iframe 
                    src="https://www.youtube.com/embed/<%= selectedVideoId %>" 
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                    allowfullscreen
                    title="<%= selectedTitle %>">
                </iframe>
            </div>
            
            <div class="video-info">
                <h2><%= selectedTitle %></h2>
                <p><%= selectedDescription %></p>
            </div>
        </div>
        
        <h3 style="margin-bottom: 20px; color: #333;">다른 동영상 보기</h3>
        
        <div class="video-list">
            <% for (String[] video : videos) { %>
                <div class="video-thumbnail" onclick="changeVideo('<%= video[0] %>')">
                    <div class="thumbnail-wrapper">
                        <img src="https://img.youtube.com/vi/<%= video[0] %>/maxresdefault.jpg" 
                             alt="<%= video[1] %>"
                             onerror="this.src='https://img.youtube.com/vi/<%= video[0] %>/hqdefault.jpg'">
                    </div>
                    <div class="thumbnail-info">
                        <h3><%= video[1] %></h3>
                        <p><%= video[2] %></p>
                    </div>
                </div>
            <% } %>
        </div>
        
        <div class="code-example">
            <pre>&lt;%
    // 동영상 ID를 파라미터에서 가져오기
    String videoId = request.getParameter("id");
    if (videoId == null || videoId.isEmpty()) {
        videoId = "dQw4w9WgXcQ"; // 기본값
    }
%&gt;

&lt;div class="video-container"&gt;
    &lt;iframe 
        src="https://www.youtube.com/embed/&lt;%= videoId %&gt;" 
        allowfullscreen&gt;
    &lt;/iframe&gt;
&lt;/div&gt;</pre>
        </div>
        
        <div class="note">
            <strong>💡 설명:</strong><br>
            - JSP에서 파라미터로 동영상 ID를 받아 동적으로 iframe src를 생성합니다.<br>
            - 썸네일 이미지는 YouTube의 자동 생성 썸네일을 사용합니다.<br>
            - 썸네일 클릭 시 해당 동영상으로 변경됩니다.
        </div>
    </div>
    
    <script>
        function changeVideo(videoId) {
            window.location.href = 'youtube-dynamic.jsp?id=' + videoId;
        }
    </script>
</body>
</html>
