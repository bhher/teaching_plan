# 🎥 YouTube iframe 반응형 삽입 가이드

## 📋 개요

YouTube 동영상을 웹페이지에 반응형으로 삽입하는 방법을 학습합니다. 모바일, 태블릿, 데스크톱 등 모든 기기에서 올바른 비율로 동영상이 표시되도록 합니다.

---

## 🎯 1. YouTube 동영상 URL 가져오기

### 1.1 공유 링크 복사

1. YouTube 동영상 페이지 접속
2. **공유** 버튼 클릭
3. **퍼가기** 탭 클릭
4. **iframe** 코드 복사

### 1.2 YouTube URL 형식

**일반 URL:**
```
https://www.youtube.com/watch?v=VIDEO_ID
```

**짧은 URL:**
```
https://youtu.be/VIDEO_ID
```

**임베드용 URL:**
```
https://www.youtube.com/embed/VIDEO_ID
```

**VIDEO_ID 예시:**
- `dQw4w9WgXcQ` (Rick Astley - Never Gonna Give You Up)
- `jNQXAC9IVRw` (Me at the zoo)

---

## 📐 2. 기본 iframe 코드

### 2.1 기본 구조

```html
<iframe 
    width="560" 
    height="315" 
    src="https://www.youtube.com/embed/VIDEO_ID" 
    frameborder="0" 
    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
    allowfullscreen>
</iframe>
```

### 2.2 문제점

- **고정 크기:** `width="560" height="315"`로 고정되어 있음
- **반응형 아님:** 작은 화면에서 잘리거나 넘침
- **비율 깨짐:** 화면 크기에 따라 비율이 맞지 않음

---

## 🎨 3. 반응형 구현 방법

### 3.1 방법 1: CSS aspect-ratio (권장)

**최신 브라우저에서 지원하는 방법입니다.**

```html
<style>
.video-container {
    position: relative;
    width: 100%;
    aspect-ratio: 16 / 9; /* YouTube 기본 비율 */
    max-width: 800px; /* 최대 너비 설정 (선택사항) */
    margin: 0 auto; /* 중앙 정렬 */
}

.video-container iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
</style>

<div class="video-container">
    <iframe 
        src="https://www.youtube.com/embed/VIDEO_ID" 
        frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen>
    </iframe>
</div>
```

**장점:**
- 코드가 간단함
- 최신 브라우저에서 완벽하게 작동
- CSS만으로 해결 가능

**단점:**
- 구형 브라우저 미지원 (IE 등)

### 3.2 방법 2: Padding-bottom 트릭 (호환성 높음)

**모든 브라우저에서 작동하는 전통적인 방법입니다.**

```html
<style>
.video-wrapper {
    position: relative;
    padding-bottom: 56.25%; /* 16:9 비율 (9/16 = 0.5625 = 56.25%) */
    height: 0;
    overflow: hidden;
    max-width: 100%;
}

.video-wrapper iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
</style>

<div class="video-wrapper">
    <iframe 
        src="https://www.youtube.com/embed/VIDEO_ID" 
        frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen>
    </iframe>
</div>
```

**작동 원리:**
- `padding-bottom: 56.25%`는 부모 요소의 너비의 56.25%를 의미
- 16:9 비율 = 9 ÷ 16 = 0.5625 = 56.25%
- iframe을 absolute로 배치하여 컨테이너를 채움

**다른 비율:**
- 4:3 비율: `padding-bottom: 75%` (3/4 = 0.75)
- 21:9 비율: `padding-bottom: 42.857%` (9/21 ≈ 0.42857)

---

## 🎯 4. YouTube URL 파라미터

### 4.1 주요 파라미터

**기본 URL:**
```
https://www.youtube.com/embed/VIDEO_ID
```

**파라미터 추가:**
```
https://www.youtube.com/embed/VIDEO_ID?파라미터1=값1&파라미터2=값2
```

### 4.2 자주 사용하는 파라미터

| 파라미터 | 설명 | 예시 |
|----------|------|------|
| `autoplay=1` | 자동 재생 | `?autoplay=1` |
| `loop=1` | 반복 재생 | `?loop=1` |
| `controls=0` | 컨트롤 숨김 | `?controls=0` |
| `mute=1` | 음소거 | `?mute=1` |
| `start=30` | 시작 시간(초) | `?start=30` |
| `end=60` | 종료 시간(초) | `?end=60` |
| `rel=0` | 관련 동영상 숨김 | `?rel=0` |
| `modestbranding=1` | YouTube 로고 최소화 | `?modestbranding=1` |

### 4.3 파라미터 조합 예시

**자동 재생 + 음소거 + 시작 시간:**
```
https://www.youtube.com/embed/VIDEO_ID?autoplay=1&mute=1&start=30
```

**반복 재생 + 관련 동영상 숨김:**
```
https://www.youtube.com/embed/VIDEO_ID?loop=1&rel=0
```

---

## 💻 5. JSP에서 사용하기

### 5.1 기본 예제

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YouTube 반응형 예제</title>
    <style>
        .video-container {
            position: relative;
            width: 100%;
            padding-bottom: 56.25%; /* 16:9 비율 */
            height: 0;
            overflow: hidden;
            max-width: 800px;
            margin: 20px auto;
        }
        
        .video-container iframe {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <h1>YouTube 동영상</h1>
    
    <div class="video-container">
        <iframe 
            src="https://www.youtube.com/embed/dQw4w9WgXcQ" 
            frameborder="0" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen>
        </iframe>
    </div>
</body>
</html>
```

### 5.2 동적 비디오 ID 사용

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String videoId = request.getParameter("id");
    if (videoId == null || videoId.isEmpty()) {
        videoId = "dQw4w9WgXcQ"; // 기본값
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YouTube 동영상</title>
    <style>
        .video-container {
            position: relative;
            width: 100%;
            padding-bottom: 56.25%;
            height: 0;
            overflow: hidden;
            max-width: 800px;
            margin: 20px auto;
        }
        
        .video-container iframe {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <h1>YouTube 동영상</h1>
    
    <div class="video-container">
        <iframe 
            src="https://www.youtube.com/embed/<%= videoId %>" 
            frameborder="0" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen>
        </iframe>
    </div>
</body>
</html>
```

---

## 🎨 6. 다양한 레이아웃 예제

### 6.1 여러 동영상 나란히 배치

```html
<style>
.video-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
    margin: 20px;
}

.video-item {
    position: relative;
    width: 100%;
    padding-bottom: 56.25%;
    height: 0;
    overflow: hidden;
}

.video-item iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}
</style>

<div class="video-grid">
    <div class="video-item">
        <iframe src="https://www.youtube.com/embed/VIDEO_ID_1" allowfullscreen></iframe>
    </div>
    <div class="video-item">
        <iframe src="https://www.youtube.com/embed/VIDEO_ID_2" allowfullscreen></iframe>
    </div>
    <div class="video-item">
        <iframe src="https://www.youtube.com/embed/VIDEO_ID_3" allowfullscreen></iframe>
    </div>
</div>
```

### 6.2 카드 형태로 배치

```html
<style>
.video-card {
    background: white;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    overflow: hidden;
    margin: 20px;
    max-width: 500px;
}

.video-card .video-wrapper {
    position: relative;
    width: 100%;
    padding-bottom: 56.25%;
    height: 0;
    overflow: hidden;
}

.video-card .video-wrapper iframe {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}

.video-card .video-info {
    padding: 15px;
}

.video-card h3 {
    margin: 0 0 10px 0;
    color: #333;
}

.video-card p {
    margin: 0;
    color: #666;
    font-size: 14px;
}
</style>

<div class="video-card">
    <div class="video-wrapper">
        <iframe src="https://www.youtube.com/embed/VIDEO_ID" allowfullscreen></iframe>
    </div>
    <div class="video-info">
        <h3>동영상 제목</h3>
        <p>동영상 설명...</p>
    </div>
</div>
```

---

## 📱 7. 모바일 최적화

### 7.1 Viewport 설정

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

### 7.2 모바일 전용 스타일

```css
@media (max-width: 768px) {
    .video-container {
        margin: 10px;
        max-width: 100%;
    }
}
```

---

## ✅ 8. 체크리스트

- [ ] YouTube 동영상 URL에서 VIDEO_ID 추출 방법 이해
- [ ] 기본 iframe 코드 구조 이해
- [ ] 반응형 CSS (aspect-ratio 또는 padding-bottom) 구현
- [ ] YouTube URL 파라미터 사용법 이해
- [ ] JSP에서 동적으로 비디오 ID 사용
- [ ] 모바일에서 테스트 완료
- [ ] 다양한 화면 크기에서 테스트 완료

---

## 💡 실전 팁

### 팁 1: 비디오 ID 추출
- URL에서 `v=` 뒤의 값이 비디오 ID
- `https://www.youtube.com/watch?v=dQw4w9WgXcQ` → `dQw4w9WgXcQ`

### 팁 2: 성능 최적화
- `loading="lazy"` 속성 추가하여 지연 로딩 가능
- 여러 동영상이 있을 때 유용

### 팁 3: 접근성
- `title` 속성 추가하여 스크린 리더 지원
- 키보드 네비게이션 고려

### 팁 4: 보안
- `allow` 속성으로 필요한 기능만 허용
- `allowfullscreen`은 필요할 때만 사용

---

## 🔗 참고 링크

- **YouTube 임베드:** https://support.google.com/youtube/answer/171780
- **YouTube API:** https://developers.google.com/youtube
- **CSS aspect-ratio:** https://developer.mozilla.org/en-US/docs/Web/CSS/aspect-ratio

---

**이제 반응형 YouTube 동영상을 웹페이지에 멋지게 삽입할 수 있습니다! 🎬**
