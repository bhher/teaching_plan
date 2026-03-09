<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YouTube 반응형 - 기본 예제</title>
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
            margin-bottom: 10px;
            text-align: center;
        }
        
        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
        }
        
        /* 방법 1: aspect-ratio 사용 (최신 브라우저) */
        .video-container-modern {
            position: relative;
            width: 100%;
            aspect-ratio: 16 / 9;
            max-width: 800px;
            margin: 30px auto;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        
        .video-container-modern iframe {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            border: none;
        }
        
        /* 방법 2: padding-bottom 트릭 (호환성 높음) */
        .video-container-compat {
            position: relative;
            width: 100%;
            padding-bottom: 56.25%; /* 16:9 비율 */
            height: 0;
            overflow: hidden;
            max-width: 800px;
            margin: 30px auto;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
        
        .video-container-compat iframe {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            border: none;
        }
        
        .method-title {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px;
            border-radius: 10px;
            margin: 40px 0 20px 0;
            text-align: center;
            font-size: 1.2em;
        }
        
        .code-example {
            background: #f5f5f5;
            padding: 20px;
            border-radius: 10px;
            margin: 20px 0;
            overflow-x: auto;
        }
        
        .code-example pre {
            margin: 0;
            font-family: 'Courier New', monospace;
            font-size: 14px;
            line-height: 1.6;
        }
        
        .note {
            background: #e3f2fd;
            padding: 15px;
            border-radius: 10px;
            margin: 20px 0;
            border-left: 4px solid #2196f3;
        }
        
        .note strong {
            color: #1976d2;
        }
        
        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }
            
            h1 {
                font-size: 1.5em;
            }
            
            .video-container-modern,
            .video-container-compat {
                margin: 20px 0;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎥 YouTube 반응형 삽입 예제</h1>
        <p class="subtitle">두 가지 방법으로 반응형 YouTube 동영상을 삽입하는 방법을 보여줍니다.</p>
        
        <div class="method-title">방법 1: CSS aspect-ratio 사용 (최신 브라우저)</div>
        
        <div class="video-container-modern">
            <iframe 
                src="https://www.youtube.com/embed/dQw4w9WgXcQ" 
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                allowfullscreen
                title="YouTube 동영상">
            </iframe>
        </div>
        
        <div class="code-example">
            <pre>&lt;style&gt;
.video-container-modern {
    position: relative;
    width: 100%;
    aspect-ratio: 16 / 9;
    max-width: 800px;
    margin: 30px auto;
}

.video-container-modern iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
&lt;/style&gt;

&lt;div class="video-container-modern"&gt;
    &lt;iframe src="https://www.youtube.com/embed/VIDEO_ID" allowfullscreen&gt;&lt;/iframe&gt;
&lt;/div&gt;</pre>
        </div>
        
        <div class="note">
            <strong>💡 장점:</strong> 코드가 간단하고 최신 브라우저에서 완벽하게 작동합니다.<br>
            <strong>⚠️ 단점:</strong> 구형 브라우저(IE 등)에서는 지원되지 않습니다.
        </div>
        
        <div class="method-title">방법 2: Padding-bottom 트릭 (모든 브라우저 호환)</div>
        
        <div class="video-container-compat">
            <iframe 
                src="https://www.youtube.com/embed/jNQXAC9IVRw" 
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                allowfullscreen
                title="YouTube 동영상">
            </iframe>
        </div>
        
        <div class="code-example">
            <pre>&lt;style&gt;
.video-container-compat {
    position: relative;
    width: 100%;
    padding-bottom: 56.25%; /* 16:9 비율 (9/16 = 0.5625) */
    height: 0;
    overflow: hidden;
    max-width: 800px;
    margin: 30px auto;
}

.video-container-compat iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
&lt;/style&gt;

&lt;div class="video-container-compat"&gt;
    &lt;iframe src="https://www.youtube.com/embed/VIDEO_ID" allowfullscreen&gt;&lt;/iframe&gt;
&lt;/div&gt;</pre>
        </div>
        
        <div class="note">
            <strong>💡 장점:</strong> 모든 브라우저에서 작동하며 호환성이 높습니다.<br>
            <strong>📐 작동 원리:</strong> padding-bottom: 56.25%는 부모 요소 너비의 56.25%를 의미하며, 16:9 비율을 만듭니다.
        </div>
        
        <div class="note">
            <strong>📱 테스트 방법:</strong><br>
            1. 브라우저 창 크기를 조절해보세요<br>
            2. 개발자 도구(F12)에서 모바일 뷰로 전환해보세요<br>
            3. 동영상이 항상 올바른 비율을 유지하는지 확인하세요
        </div>
    </div>
</body>
</html>
