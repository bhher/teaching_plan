# Modern Vision (JSX)

`modern-vision`(TSX)을 **JSX 전용**으로 옮긴 수업용 프로젝트입니다.

## 실행

```bash
cd modern-vision-jsx
npm install
npm run dev
```

## 원본과의 차이

| | modern-vision | modern-vision-jsx |
|--|---------------|-------------------|
| 언어 | TypeScript (.tsx) | **JavaScript (.jsx)** |
| 타입 | `types.ts`, `React.FC` | 없음 |
| 진입점 | `index.tsx` | `src/main.jsx` |

## useEffect 포인트

| 파일 | 내용 |
|------|------|
| `src/components/Header.jsx` | **scroll** 이벤트 + Cleanup |
| `src/components/HeroSlider.jsx` | **setInterval** 자동 슬라이드 + Cleanup |

## 구조

```
modern-vision-jsx/
├── index.html
├── package.json
├── vite.config.js
└── src/
    ├── main.jsx
    ├── App.jsx
    ├── constants.js
    └── components/
        ├── Header.jsx      ← useEffect + scroll
        ├── HeroSlider.jsx  ← useEffect + timer
        ├── CardSection.jsx
        └── Footer.jsx
```
