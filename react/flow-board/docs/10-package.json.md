# package.json

## 위치
`flow-board/package.json`

## 역할
**프로젝트 메타데이터**와 **의존성, 스크립트**를 정의합니다.

## 필드 설명

### scripts

| 스크립트 | 명령 | 설명 |
|----------|------|------|
| `dev` | `vite` | 개발 서버 실행 (기본 http://localhost:5173) |
| `build` | `vite build` | 프로덕션 빌드, `dist/` 폴더에 산출물 생성 |
| `preview` | `vite preview` | 빌드 결과물 로컬에서 미리보기 |

### dependencies (실행에 필요한 패키지)

| 패키지 | 용도 |
|--------|------|
| `react` | React 라이브러리 |
| `react-dom` | React를 DOM에 렌더링하는 패키지 |

### devDependencies (개발 시에만 필요한 패키지)

| 패키지 | 용도 |
|--------|------|
| `@vitejs/plugin-react` | Vite용 React 플러그인 (JSX 변환) |
| `vite` | 빌드 도구 |

### 기타

- `"type": "module"` : `.js` 파일을 ES 모듈로 해석
- `"private": true` : npm에 패키지 게시 방지
