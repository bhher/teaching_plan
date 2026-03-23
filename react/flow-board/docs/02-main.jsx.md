# main.jsx

## 위치
`flow-board/src/main.jsx`

## 역할
React 앱의 **진입점(Entry Point)**. DOM에 React 트리를 마운트합니다.

## 코드 설명

```jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
```

### 1. import
- `React` : JSX 변환에 필요 (React 17+에서는 불필요할 수 있으나 명시적으로 유지)
- `ReactDOM.createRoot` : React 18의 새로운 렌더 API
- `App` : 최상위 컴포넌트
- `index.css` : 전역 스타일

### 2. createRoot
- `document.getElementById('root')` : index.html의 `<div id="root">`를 선택
- `createRoot()` : React 18 방식. 이전 `ReactDOM.render()`를 대체

### 3. StrictMode
- 개발 모드에서 잠재적 문제를 경고
- 중복 렌더링, deprecated API 사용 등 검사
- 프로덕션 빌드에는 영향 없음
