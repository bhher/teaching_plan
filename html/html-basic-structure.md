## HTML 기본 문서 구조

### 1. DOCTYPE 선언
- `<!DOCTYPE html>`은 문서가 HTML5 규격임을 알리는 선언으로, 브라우저가 표준 모드로 렌더링하도록 한다.

### 2. `<html>` 요소
- 문서의 루트 요소이며 `lang` 속성으로 문서의 기본 언어를 지정한다.
- 예: `<html lang="ko">`.

### 3. `<head>` 영역
- 메타데이터, 스타일, 스크립트, 제목 등을 포함한다.
- 주요 하위 요소
  - `<meta charset="UTF-8">`: 문자 인코딩 설정.
  - `<meta name="viewport" content="width=device-width, initial-scale=1.0">`: 모바일을 포함한 반응형 표시 설정.
  - `<title>`: 브라우저 탭에 표시될 문서 제목.
  - `<link rel="stylesheet" href="...">`: 외부 스타일시트 연결.
  - `<script src="...">`: 외부 스크립트 로드(가능하면 `defer` 사용).

### 4. `<body>` 영역
- 실제로 화면에 렌더링되는 콘텐츠를 포함한다.
- 텍스트, 이미지, 폼, 컴포넌트 등의 요소들이 위치한다.

### 5. 전체 예시

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>문서 제목</title>
    <link rel="stylesheet" href="styles.css" />
    <script src="main.js" defer></script>
  </head>
  <body>
    <header>헤더 영역</header>
    <main>
      <h1>문서 제목</h1>
      <p>본문 내용...</p>
    </main>
    <footer>푸터 영역</footer>
  </body>
</html>
```

### 6. 추가 팁
- 시맨틱 태그(`<header>`, `<main>`, `<section>`, `<footer>` 등)를 사용하면 구조가 명확해지고 접근성이 향상된다.
- 문서마다 고유한 `<title>`과 적절한 메타 태그를 정의하면 검색엔진 최적화(SEO)에 도움이 된다.
- CSS·JS 파일은 가능한 한 압축/번들링하여 네트워크 비용을 줄이자.





