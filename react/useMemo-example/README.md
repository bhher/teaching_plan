# useMemo & useCallback 예제

## 실행 방법

```bash
npm install
npm run dev
```

## 예제 구성

| 탭 | Hook | 설명 |
|----|------|------|
| useMemo 1 | useMemo | count * 2 캐시 |
| useMemo 2 | useMemo | 팩토리얼 (name 입력 시 재계산 안 함) |
| useCallback 1 | useCallback | 이벤트 핸들러 고정 |
| useCallback 2 | useCallback + memo | 자식 리렌더 방지 |

콘솔(F12)에서 로그를 확인하세요.
