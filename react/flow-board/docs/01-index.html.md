# index.html

## 위치
`flow-board/index.html`

## 역할
React 앱의 **HTML 루트 문서**. 브라우저가 처음 로드하는 파일입니다.

## 주요 요소

### 1. 메타 태그
- `charset="UTF-8"` : 한글 등 멀티바이트 문자 지원
- `viewport` : 반응형 화면 설정

### 2. 리소스 로딩
- **파비콘**: `/flow.svg` (public 폴더에 위치)
- **폰트**: Google Fonts에서 Noto Sans KR, JetBrains Mono 로드
  - `preconnect`로 DNS/연결 사전 설정 → 로딩 속도 향상

### 3. React 마운트 지점
```html
<div id="root"></div>
```
- React가 렌더링하는 DOM의 루트 요소

### 4. 스크립트
```html
<script type="module" src="/src/main.jsx"></script>
```
- `type="module"` : ES 모듈로 로드 (import/export 사용)
- Vite가 이 경로를 진입점으로 해석, 의존성 번들링

## Vite와의 관계
- 개발 서버(`npm run dev`) 실행 시 Vite가 이 HTML을 기준으로 앱을 서빙
- `src/main.jsx`가 JSX로 작성돼 있어 Vite의 React 플러그인이 변환 처리
