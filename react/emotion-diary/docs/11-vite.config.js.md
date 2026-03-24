# vite.config.js

## 위치
`emotion-diary/vite.config.js`

## 역할
**Vite 빌드 설정** 파일입니다.

## 코드

```js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
});
```

### 플러그인

- `@vitejs/plugin-react` : JSX 변환, Fast Refresh(핫 리로드) 지원
- 별도 경로·프록시 설정 없음 (기본값 사용)
