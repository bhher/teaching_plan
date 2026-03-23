# vite.config.js

## 위치
`flow-board/vite.config.js`

## 역할
**Vite 빌드 도구**의 설정 파일입니다. React 변환을 위한 플러그인을 지정합니다.

## 코드

```js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
});
```

## 설명

### defineConfig
- Vite 설정 객체를 반환하는 헬퍼
- 타입 힌트와 자동완성을 위해 사용 (선택 사항)

### plugins: [react()]
- `@vitejs/plugin-react` : JSX를 일반 JavaScript로 변환
- React Fast Refresh (HMR) 지원 → 코드 수정 시 페이지 새로고침 없이 반영
- Babel 대신 esbuild 사용으로 빌드 속도 향상

## 기본 동작 (설정 없이)
- `index.html`이 프로젝트 루트에 있음을 전제
- `src/` 내 파일을 ES 모듈로 처리
- `public/` 폴더는 정적 파일로 그대로 복사 (flow.svg 등)
