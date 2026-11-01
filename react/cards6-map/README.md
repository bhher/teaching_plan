# 카드 6장 (사진) — CDN + map

Bootstrap을 **CDN**으로 연결하고, React `map`으로 카드 6장을 그립니다.

## 비교

| 폴더 | 방식 |
|------|------|
| `boot/examples/08-카드6장.html` | HTML Bootstrap |
| **이 폴더** `cards6-map` | React + **CDN** + className |
| `cards6-bootstrap` | React + **npm** react-bootstrap |

## CDN (index.html)

```html
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" />
```

## 실행

```bash
cd react/cards6-map
npm install
npm run dev
```

`npm`으로는 **react / react-dom**만 설치합니다. Bootstrap CSS는 CDN입니다.
