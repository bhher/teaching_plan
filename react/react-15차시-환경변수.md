# 15차시. 환경 변수

## 학습 목표
- .env 파일의 개념을 이해할 수 있다
- 환경 변수를 설정하고 사용할 수 있다
- API URL을 환경 변수로 분리할 수 있다
- 개발 환경과 프로덕션 환경을 구분할 수 있다
- 배포 환경 대비 설정을 할 수 있다

---

## 1. 환경 변수란?

### 1.1 환경 변수의 정의

**환경 변수**는 애플리케이션의 설정값을 외부에서 관리하는 방법입니다.

**사용 목적:**
- ✅ API URL 관리
- ✅ API 키 관리
- ✅ 데이터베이스 연결 정보
- ✅ 환경별 설정 분리

**장점:**
- ✅ 보안: 민감한 정보를 코드에서 분리
- ✅ 유연성: 환경별로 다른 설정 사용
- ✅ 관리 용이: 설정 변경 시 코드 수정 불필요

### 1.2 환경 변수가 필요한 이유

**하드코딩의 문제:**
```javascript
// ❌ 나쁜 예
const API_URL = 'https://api.example.com';
const API_KEY = 'secret-key-12345';
```

**문제점:**
- ❌ 코드에 민감한 정보 노출
- ❌ 환경별로 다른 설정 사용 어려움
- ❌ Git에 커밋되면 보안 위험

**환경 변수 사용:**
```javascript
// ✅ 좋은 예
const API_URL = import.meta.env.VITE_API_URL;
const API_KEY = import.meta.env.VITE_API_KEY;
```

---

## 2. .env 파일

### 2.1 .env 파일이란?

**.env 파일**은 환경 변수를 저장하는 파일입니다.

**위치:**
- 프로젝트 루트 디렉토리
- `.env` (기본)
- `.env.local` (로컬 환경)
- `.env.development` (개발 환경)
- `.env.production` (프로덕션 환경)

### 2.2 .env 파일 생성

**프로젝트 루트에 `.env` 파일 생성:**
```env
VITE_API_URL=http://localhost:3000/api
VITE_API_KEY=your-api-key-here
VITE_APP_NAME=My React App
```

**주의사항:**
- ✅ `VITE_` 접두사 필수 (Vite 사용 시)
- ✅ 공백 없이 작성
- ✅ 따옴표 불필요 (값에 공백이 있으면 따옴표 사용)

### 2.3 .gitignore에 추가

**.gitignore 파일:**
```gitignore
# 환경 변수 파일
.env
.env.local
.env.development.local
.env.production.local
```

**이유:**
- 민감한 정보가 Git에 커밋되지 않도록

---

## 3. Vite에서 환경 변수 사용

### 3.1 환경 변수 접근

**Vite에서는 `import.meta.env` 사용:**
```javascript
const apiUrl = import.meta.env.VITE_API_URL;
const apiKey = import.meta.env.VITE_API_KEY;
```

**주의:**
- `VITE_` 접두사가 있는 변수만 접근 가능
- 보안을 위해 클라이언트에 노출되지 않아야 하는 변수는 서버에서만 사용

### 3.2 환경 변수 타입

**TypeScript 사용 시:**
```typescript
// vite-env.d.ts
/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_API_URL: string;
  readonly VITE_API_KEY: string;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
```

---

## 4. 환경별 설정

### 4.1 환경 파일 종류

**기본 파일:**
- `.env`: 모든 환경에서 로드
- `.env.local`: 로컬 환경 (Git에 커밋하지 않음)
- `.env.development`: 개발 환경
- `.env.production`: 프로덕션 환경

**우선순위:**
1. `.env.local` (가장 높음)
2. `.env.development` 또는 `.env.production`
3. `.env` (가장 낮음)

### 4.2 환경별 설정 예시

**.env.development:**
```env
VITE_API_URL=http://localhost:3000/api
VITE_DEBUG=true
```

**.env.production:**
```env
VITE_API_URL=https://api.example.com/api
VITE_DEBUG=false
```

**사용:**
```javascript
const apiUrl = import.meta.env.VITE_API_URL;
const isDebug = import.meta.env.VITE_DEBUG === 'true';
```

---

## 5. 실습: API URL 분리

### 실습 1: 기본 환경 변수 설정

**요구사항:**
- .env 파일 생성
- API URL 환경 변수로 설정
- 코드에서 사용

**1단계: .env 파일 생성**
```env
VITE_API_URL=https://jsonplaceholder.typicode.com
VITE_APP_NAME=My React App
```

**2단계: 코드에서 사용**
```jsx
// api/config.js
const API_URL = import.meta.env.VITE_API_URL;
const APP_NAME = import.meta.env.VITE_APP_NAME;

export { API_URL, APP_NAME };
```

**3단계: API 호출에서 사용**
```jsx
import { API_URL } from './api/config';
import axios from 'axios';

async function getPosts() {
  const response = await axios.get(`${API_URL}/posts`);
  return response.data;
}
```

### 실습 2: 환경별 설정

**요구사항:**
- 개발 환경과 프로덕션 환경 분리
- 각각 다른 API URL 사용

**.env.development:**
```env
VITE_API_URL=http://localhost:3000/api
VITE_ENV=development
```

**.env.production:**
```env
VITE_API_URL=https://api.example.com/api
VITE_ENV=production
```

**코드:**
```jsx
// api/config.js
const API_URL = import.meta.env.VITE_API_URL;
const ENV = import.meta.env.VITE_ENV || 'development';

console.log(`현재 환경: ${ENV}`);
console.log(`API URL: ${API_URL}`);

export { API_URL, ENV };
```

### 실습 3: 환경 변수 유틸리티

**요구사항:**
- 환경 변수 검증
- 기본값 설정
- 타입 변환

**utils/env.js:**
```javascript
// 환경 변수 가져오기
export const getEnv = (key, defaultValue = '') => {
  const value = import.meta.env[key];
  return value || defaultValue;
};

// 필수 환경 변수 검증
export const requireEnv = (key) => {
  const value = import.meta.env[key];
  if (!value) {
    throw new Error(`환경 변수 ${key}가 설정되지 않았습니다`);
  }
  return value;
};

// API 설정
export const API_CONFIG = {
  URL: requireEnv('VITE_API_URL'),
  TIMEOUT: parseInt(getEnv('VITE_API_TIMEOUT', '5000'), 10),
};

// 앱 설정
export const APP_CONFIG = {
  NAME: getEnv('VITE_APP_NAME', 'React App'),
  VERSION: getEnv('VITE_APP_VERSION', '1.0.0'),
  DEBUG: getEnv('VITE_DEBUG', 'false') === 'true',
};
```

**사용:**
```jsx
import { API_CONFIG, APP_CONFIG } from './utils/env';

console.log(APP_CONFIG.NAME);
console.log(API_CONFIG.URL);
```

---

## 6. 배포 환경 대비 설정

### 6.1 배포 전 체크리스트

**환경 변수 확인:**
- [ ] 프로덕션 API URL 설정
- [ ] API 키 설정 (보안)
- [ ] 디버그 모드 끄기
- [ ] 불필요한 로그 제거

### 6.2 환경 변수 검증

**시작 시 검증:**
```javascript
// utils/env.js
export const validateEnv = () => {
  const requiredVars = ['VITE_API_URL'];
  
  requiredVars.forEach(key => {
    if (!import.meta.env[key]) {
      console.error(`필수 환경 변수 ${key}가 설정되지 않았습니다`);
    }
  });
};

// main.jsx에서 호출
import { validateEnv } from './utils/env';
validateEnv();
```

### 6.3 빌드 시 환경 변수

**빌드 명령어:**
```bash
# 개발 환경 빌드
npm run build

# 프로덕션 환경 빌드 (기본)
npm run build

# 환경 변수 지정
VITE_API_URL=https://api.example.com npm run build
```

---

## 7. 실습 과제

### 과제 1: 환경 변수 설정

**요구사항:**
- .env 파일 생성
- API URL, API 키 설정
- 코드에서 사용

### 과제 2: 환경별 설정

**요구사항:**
- 개발/프로덕션 환경 분리
- 각각 다른 설정 사용

---

## 8. 자주 발생하는 오류

### 오류 1: `undefined` 반환

**원인:** `VITE_` 접두사 누락 또는 변수명 오타

**해결:**
```env
# ❌
API_URL=http://localhost:3000

# ✅
VITE_API_URL=http://localhost:3000
```

### 오류 2: 환경 변수 변경이 반영되지 않음

**원인:** 개발 서버 재시작 필요

**해결:**
```bash
# 개발 서버 재시작
npm run dev
```

---

## 9. 다음 차시 예고

다음 차시에서는 **로딩 & 에러 처리**를 배웁니다:
- 로딩 상태 관리
- 에러 화면 처리
- 로딩 스피너 구현

---

## 요약

### 핵심 개념

1. **환경 변수**: 외부에서 관리하는 설정값
2. **.env 파일**: 환경 변수 저장 파일
3. **VITE_ 접두사**: Vite에서 필수
4. **환경별 설정**: 개발/프로덕션 분리

### 필수 문법

```javascript
// 환경 변수 접근
const value = import.meta.env.VITE_VARIABLE_NAME;

// .env 파일
VITE_API_URL=https://api.example.com
```

### 체크리스트

- [ ] .env 파일 개념 이해
- [ ] 환경 변수 설정 가능
- [ ] API URL 분리 완료
- [ ] 환경별 설정 이해
- [ ] 배포 환경 대비 설정 완료

---

**다음 차시에서 만나요! 🚀**

