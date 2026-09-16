# PC / Mobile Nav + Slick Slider

지센(ZISHEN)과 비슷한 **PC GNB · 모바일 햄버거 메뉴** 아래에  
**Slick Carousel** 메인 슬라이드를 붙인 수업용 예제입니다.

## 실행

`index.html`을 브라우저로 열면 됩니다. (CDN: jQuery, Slick)

```
지센/pc-mobile-nav-slick/
├── index.html
├── css/style.css
├── js/main.js
└── README.md
```

## 화면 구성

```
┌─────────────────────────────┐
│ Header (로고 · Login)        │
├─────────────────────────────┤
│ PC .nav  (hover → 서브 펼침) │  ← >850px
│ 또는 햄버거 + .mobile_nav    │  ← ≤850px
├─────────────────────────────┤
│ Slick 메인 슬라이드          │
│ (자동재생 · dots · 이전/다음) │
├─────────────────────────────┤
│ Intro 안내 문구              │
└─────────────────────────────┘
```

## PC 메뉴 (지센과 동일 패턴)

1. `.nav > ul` 기본 높이 `54px` + `overflow: hidden` → 서브 숨김  
2. hover 시 `.over` → 높이 `180px` → 서브 노출  

```javascript
$(".nav > ul").hover(
  function () { $(this).addClass("over"); },
  function () { $(this).removeClass("over"); }
);
```

## 모바일 메뉴 (≤850px)

| 요소 | 역할 |
|------|------|
| `.mobile_tab` | 헤더 햄버거 — 메뉴 **열기** |
| `.mobile_close` | 사이드 메뉴 **상단 X** — 메뉴 **닫기** |
| `.mobile_nav` | 오른쪽 사이드 메뉴 (`right: -260px` → `0`) |
| `.transparency` | 반투명 배경, 클릭 시 닫기 |

1depth 클릭 → 아코디언(`slideDown` / `slideUp`)

## Slick 옵션

```javascript
$(".main-slider").slick({
  infinite: true,
  autoplay: true,
  autoplaySpeed: 3500,
  fade: true,      // PC: 페이드
  arrows: false,
  dots: true
});
```

- 커스텀 컨트롤: 이전 / 다음 / 일시정지·재생 / `1 / 4` 페이저  
- 768px 이하: `fade: false` (밀기 전환)

## 지센 원본과 비교

| | 지센 `index.html` | 이 예제 |
|--|-------------------|---------|
| PC 서브 | `.over` 높이 확장 | 동일 |
| 모바일 전환 | 850px | 동일 |
| 네비 아래 | `.hero` 단일 이미지 | **Slick 슬라이더** |
| BEST/NEW/Footer | 있음 | 없음 (네비+슬라이드 집중) |

원본 설명: [../지센-반응형-메뉴-본문-설명.md](../지센-반응형-메뉴-본문-설명.md)

## 수업 팁

1. 창 너비를 850px 기준으로 줄였다 보며 PC ↔ 모바일 전환 확인  
2. PC에서 GNB에 마우스를 올려 서브 패널이 내려오는지 확인  
3. 슬라이드 자동재생 / 일시정지 / dots 동작 확인  
4. 이미지 URL은 `picsum.photos` CDN — 오프라인이면 `images/` 로컬 이미지로 교체
