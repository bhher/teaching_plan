# index.css

## 위치
`emotion-diary/src/index.css`

## 역할
감정일기장 앱의 **전역 스타일**입니다. 일기장 느낌의 따뜻한 테마를 적용합니다.

## CSS 변수 (:root)

| 변수 | 색상 | 용도 |
|------|------|------|
| `--bg-page` | #faf7f2 | 페이지 배경 (크림톤) |
| `--bg-card` | #fffef9 | 카드 배경 |
| `--bg-card-hover` | #fff9f0 | 호버 시 배경 |
| `--accent` | #c9a87c | 버튼, 강조 (베이지/골드) |
| `--accent-dark` | #a08050 | 버튼 호버, 제목 |
| `--text` | #3d3629 | 본문 글자 |
| `--text-muted` | #7d7265 | 부가 텍스트 |
| `--border` | #e8e0d5 | 테두리 |
| `--shadow` | rgba(61,54,41,0.06) | 그림자 |
| `--shadow-lg` | rgba(61,54,41,0.1) | 진한 그림자 |

## 폰트

- **본문** : Gowun Batang
- **헤더 타이틀** : Nanum Pen Script (손글씨체)

## 섹션별 스타일

### .app
- max-width: 720px, 중앙 정렬
- padding: 2rem 1.5rem 4rem

### .header
- 중앙 정렬, 로고 + 태그라인

### .diary-form
- 카드 스타일, 2px border, 12px radius
- EmotionPicker + textarea + submit 버튼

### .emotion-btn
- border-radius: 999px (필 모양)
- `selected` 시 `--emotion-color`와 `color-mix()` 활용

### .diary-entry
- 카드 스타일, 호버 시 border·shadow 강조
- entry-header: 감정 배지, 날짜, 삭제 버튼
- entry-content: `white-space: pre-wrap` 줄바꿈 유지

### .diary-empty
- 점선 테두리, 빈 상태 안내 영역

## color-mix (선택 감정 버튼)

```css
.emotion-btn.selected {
  background: color-mix(in srgb, var(--emotion-color) 15%, white);
}
```

- 감정별 색상을 15% 비율로 섞어 은은한 배경 적용
- Chrome 111+, Firefox 113+, Safari 16.2+ 지원
