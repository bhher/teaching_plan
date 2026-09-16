# 가로 스크롤 예제

```
horizontal-scroll/
├── index.html
├── css/style.css
├── js/main.js
└── README.md
```

브라우저에서 `index.html` 열기

## 핵심 CSS

```css
.scroll-x {
  display: flex;
  gap: 10px;
  overflow-x: auto;
  scroll-snap-type: x mandatory;
  scrollbar-width: thin;
}

.item {
  flex: 0 0 120px;       /* 줄어들지 않음 */
  scroll-snap-align: start;
}
```

## 스크롤바 꾸미기 (Chrome)

```css
.scroll-x::-webkit-scrollbar { height: 4px; }
.scroll-x::-webkit-scrollbar-thumb {
  background: #cbd5e1;
  border-radius: 99px;
}
```

## 클릭 시 가운데로 이동

```javascript
el.scrollIntoView({ behavior: "smooth", inline: "center" });
```

## 반응형에서 가로 스크롤 **막기** (참고)

```css
img { max-width: 100%; }
.container { width: 100%; max-width: 1200px; }
```

→ [html/ex07/03-fluid-layout-media.html](../../html/ex07/03-fluid-layout-media.html)
