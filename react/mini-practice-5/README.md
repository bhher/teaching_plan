# React 미니 실습 5개

| 실습 | 핵심 개념 | 난이도 |
|------|-----------|--------|
| ① 좋아요 버튼 | useState, 이벤트 | ⭐ |
| ② 카운터 + 장바구니 | useState, map | ⭐⭐ |
| ③ 검색 필터 | useState, filter, useMemo | ⭐⭐ |
| ④ 할 일 관리 | useState, filter, map, useMemo | ⭐⭐⭐ |
| ⑤ 상품 목록 최적화 | memo, useCallback, useMemo | ⭐⭐⭐ |

## 실행

```bash
cd react/mini-practice-5
npm install
npm run dev
```

## 파일 구조

```
src/
  App.jsx                 # 탭 전환
  practices/
    Practice01Like.jsx    # 좋아요
    Practice02Cart.jsx    # 장바구니
    Practice03Search.jsx  # 검색 필터
    Practice04Todo.jsx    # Todo
    Practice05Optimize.jsx # memo + useCallback + useMemo
```

## 수업 순서

```
① 좋아요 → ② 장바구니 → ③ 검색 필터 → ④ Todo → ⑤ 최적화
```

복습만 짧게 할 때: **① → ③ → ⑤**

```
useState → 상태 변경 → useMemo → 컴포넌트 분리 → memo → useCallback
```

## 개념 연결

| Hook / API | 역할 |
|------------|------|
| **useMemo** | 값을 기억 |
| **useCallback** | 함수를 기억 |
| **memo** | 컴포넌트 렌더링을 줄임 |

⑤번은 콘솔(F12)에서 `ProductItem 렌더링` 로그를 확인하세요.  
「부모만 리렌더」를 눌러도 자식 로그가 안 나오면 최적화 성공입니다.
