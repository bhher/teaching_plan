# useEffect + Scroll 간단 예제

**상세 설명:** [useEffect-scroll-simple-설명.md](./useEffect-scroll-simple-설명.md)

`modern-vision-jsx`보다 **스크롤만** 담은 최소 예제입니다.

## 실행

```bash
cd useEffect-scroll-simple
npm install
npm run dev
```

아래로 스크롤 → 헤더가 바뀌는지 확인  
F12 콘솔 → 등록/Cleanup 로그 확인

## 핵심 한 줄

> `addEventListener`로 등록한 이벤트는 컴포넌트가 사라질 때 `removeEventListener`로 정리한다.

## 파일

- `src/App.jsx` — scrollY + isScrolled + useEffect
- `src/index.css` — `.header.top` / `.scrolled`
- `useEffect-scroll-simple-설명.md` — handleScroll부터 Cleanup까지 수업용 해설
