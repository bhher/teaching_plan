# 카운터 - React 버전

## 실행 방법
```bash
npm install
npm run dev
```

## 핵심 코드
```jsx
const [count, setCount] = useState(0);
const increase = () => setCount(count + 1);
const decrease = () => setCount(count - 1);
```
