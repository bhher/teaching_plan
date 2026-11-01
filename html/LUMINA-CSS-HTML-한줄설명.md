# LUMINA 반응형 웹사이트 — CSS / HTML 한 줄 설명

파일: [responsive-lumina.html](./responsive-lumina.html)

---

## 목차

1. [기본 설정 & 전역 스타일](#1-기본-설정--전역-스타일)
2. [HEADER / NAV](#2-header--nav-헤더-및-네비게이션)
3. [HERO](#3-hero-히어로-섹션)
4. [SECTION](#4-section-일반-섹션)
5. [CARDS](#5-cards-카드-그리드)
6. [ABOUT](#6-about-어바웃-섹션)
7. [FOOTER](#7-footer-푸터)
8. [MEDIA QUERIES](#8-media-queries-반응형)
9. [HTML — 카드 영역](#9-html--카드-영역)

---

## 1. 기본 설정 & 전역 스타일

| 코드 | 설명 |
|------|------|
| `* { margin: 0; padding: 0; box-sizing: border-box; }` | 모든 요소의 기본 여백을 없애고, 테두리와 패딩을 크기 계산에 포함시킵니다. |
| `html { scroll-behavior: smooth; }` | 화면 내 링크 이동 시 부드럽게 스크롤되도록 설정합니다. |
| `body { font-family: "DM Sans", sans-serif; background: var(--bg); color: var(--ink); line-height: 1.6; }` | 전체 기본 글꼴, 배경색, 글자색, 줄 간격을 설정합니다. |
| `a { color: inherit; text-decoration: none; }` | 링크의 기본 밑줄을 없애고 부모 요소의 글자색을 상속받게 합니다. |
| `img { display: block; width: 100%; height: 100%; object-fit: cover; }` | 이미지를 블록 요소로 만들고 영역에 꽉 차게 비율을 유지하며 채웁니다. |

---

## 2. HEADER / NAV (헤더 및 네비게이션)

| 코드 | 설명 |
|------|------|
| `.site-header { position: sticky; top: 0; z-index: 100; background: rgba(243, 239, 230, 0.92); backdrop-filter: blur(10px); border-bottom: 1px solid var(--line); }` | 상단 고정 헤더에 반투명 배경과 흐림(Blur) 효과를 적용합니다. |
| `.nav-bar { max-width: var(--max); margin: 0 auto; padding: 14px 20px; display: flex; flex-wrap: wrap; align-items: center; gap: 8px 16px; }` | 네비게이션 바 내부 요소를 유연한 플렉스박스로 배치하고 간격을 줍니다. |
| `.logo { font-family: "Instrument Serif", serif; font-size: 1.75rem; letter-spacing: 0.02em; }` | 로고의 독특한 세리프 글꼴과 크기, 자간을 지정합니다. |
| `.logo span { color: var(--accent); }` | 로고 안의 특정 강조 텍스트 색상을 변경합니다. |
| `.menu-toggle { margin-left: auto; border: 1px solid var(--ink); background: transparent; color: var(--ink); padding: 8px 12px; font-size: 0.85rem; font-family: inherit; cursor: pointer; letter-spacing: 0.06em; }` | 모바일용 메뉴 열기 버튼을 우측 끝으로 밀어내고 테두리 스타일을 지정합니다. |
| `.nav-menu { display: none; list-style: none; flex-direction: column; gap: 0; flex-basis: 100%; width: 100%; margin-top: 8px; padding-top: 8px; border-top: 1px solid var(--line); }` | 모바일에서 기본적으로 메뉴를 숨기고, 펼칠 때 로고 아래 세로로 정렬합니다. |
| `.nav-menu.open { display: flex; }` | 메뉴 토글이 활성화(open)되었을 때 메뉴를 화면에 보여줍니다. |
| `.nav-menu a { display: block; padding: 14px 4px; border-bottom: 1px solid var(--line); font-size: 0.95rem; font-weight: 500; }` | 모바일 메뉴 링크의 간격과 구분선을 설정합니다. |
| `.nav-menu li:last-child a { border-bottom: none; }` | 모바일 메뉴 마지막 항목의 구분선을 제거합니다. |
| `.nav-menu a:hover { color: var(--accent); }` | 메뉴에 마우스를 올렸을 때 강조 색상으로 변경됩니다. |

### 포인트

- `flex-wrap` + `flex-basis: 100%` → MENU 클릭 시 메뉴가 **LUMINA 아래**로 펼쳐짐
- `margin-left: auto` → MENU 버튼을 오른쪽 끝으로 배치

---

## 3. HERO (히어로 섹션)

| 코드 | 설명 |
|------|------|
| `.hero { min-height: 72vh; display: flex; flex-direction: column; justify-content: flex-end; padding: 40px 20px 48px; background: linear-gradient(...), url(...) center / cover no-repeat; color: #f7f3ea; }` | 메인 히어로에 어두운 그라데이션 배경 이미지와 하단 정렬 레이아웃을 적용합니다. |
| `.hero-inner { max-width: var(--max); margin: 0 auto; width: 100%; }` | 히어로 콘텐츠의 최대 너비를 제한하고 가운데 정렬합니다. |
| `.hero h1 { font-family: "Instrument Serif", serif; font-size: clamp(2.6rem, 10vw, 5rem); font-weight: 400; line-height: 1.05; margin-bottom: 16px; }` | 반응형 크기를 가지는 커다란 메인 제목을 설정합니다. |
| `.hero p { max-width: 34rem; font-size: 1.05rem; opacity: 0.9; margin-bottom: 28px; }` | 히어로 서브 텍스트의 최대 너비와 투명도를 지정합니다. |
| `.btn { display: inline-block; padding: 12px 22px; background: var(--accent); color: #fff; font-weight: 700; font-size: 0.9rem; letter-spacing: 0.04em; border: none; cursor: pointer; transition: transform 0.2s, background 0.2s; }` | 기본 버튼의 색상, 패딩, 마우스 오버 애니메이션을 정의합니다. |
| `.btn:hover { background: #a84b1d; transform: translateY(-2px); }` | 버튼에 마우스를 올리면 색상이 진해지고 위로 살짝 떠오릅니다. |
| `.btn-ghost { background: transparent; border: 1px solid rgba(255, 255, 255, 0.7); color: #fff; margin-left: 10px; }` | 테두리만 있는 투명한 스타일의 보조 버튼을 만듭니다. |
| `.btn-ghost:hover { background: rgba(255, 255, 255, 0.12); }` | 보조 버튼에 마우스를 올리면 배경에 은은한 하이라이트를 줍니다. |

### 포인트

- `clamp(최소, 선호, 최대)` → 화면 크기에 따라 글자 크기가 자연스럽게 변함
- `justify-content: flex-end` → 히어로 텍스트를 아래쪽에 배치

---

## 4. SECTION (일반 섹션)

| 코드 | 설명 |
|------|------|
| `.section { max-width: var(--max); margin: 0 auto; padding: 56px 20px; }` | 페이지 내 공통 섹션의 최대 너비와 상하 패딩을 지정합니다. |
| `.section-head { margin-bottom: 28px; }` | 섹션 제목 영역 하단에 여백을 줍니다. |
| `.section-head .label { display: inline-block; color: var(--accent); font-size: 0.78rem; font-weight: 700; letter-spacing: 0.12em; text-transform: uppercase; margin-bottom: 8px; }` | 섹션 상단의 작은 소제목(라벨) 스타일을 꾸밉니다. |
| `.section-head h2 { font-family: "Instrument Serif", serif; font-size: clamp(1.9rem, 4vw, 2.6rem); font-weight: 400; line-height: 1.15; }` | 반응형 크기를 가지는 섹션의 메인 제목을 설정합니다. |
| `.section-head p { margin-top: 10px; color: var(--muted); max-width: 36rem; }` | 섹션 설명 글의 색상과 최대 너비를 제한합니다. |

---

## 5. CARDS (카드 그리드)

| 코드 | 설명 |
|------|------|
| `.card-grid { display: grid; grid-template-columns: 1fr; gap: var(--gap); }` | 모바일에서 카드들을 1열 그리드로 배치합니다. |
| `.card { background: var(--card); border: 1px solid var(--line); overflow: hidden; transition: transform 0.25s, box-shadow 0.25s; }` | 카드의 배경, 테두리, 부드러운 전환 효과를 설정합니다. |
| `.card:hover { transform: translateY(-4px); box-shadow: 0 12px 28px rgba(36, 48, 31, 0.1); }` | 카드에 마우스를 올리면 위로 떠오르며 그림자가 깊어집니다. |
| `.card-thumb { height: 180px; background: var(--dark); }` | 카드 내부 이미지 영역의 높이와 기본 배경색을 지정합니다. |
| `.card-body { padding: 20px; }` | 카드 내부 텍스트 영역의 여백을 줍니다. |
| `.card-tags { display: flex; flex-wrap: wrap; gap: 8px; margin-bottom: 10px; }` | 카드 안의 태그들을 가로로 나열합니다. |
| `.card-tags span { font-size: 0.72rem; letter-spacing: 0.06em; text-transform: uppercase; color: var(--muted); }` | 태그 텍스트를 작고 세련된 대문자 스타일로 꾸밉니다. |
| `.card h3 { font-size: 1.2rem; margin-bottom: 8px; }` | 카드 제목의 글자 크기와 하단 여백을 설정합니다. |
| `.card p { font-size: 0.92rem; color: var(--muted); }` | 카드 설명 텍스트의 크기와 색상을 지정합니다. |

### 열 수 변화 (미디어쿼리)

| 화면 | 열 수 |
|------|------|
| 모바일 (기본) | 1열 (`1fr`) |
| 768px 이상 | 2열 (`repeat(2, 1fr)`) |
| 1024px 이상 | 3열 (`repeat(3, 1fr)`) |

---

## 6. ABOUT (어바웃 섹션)

| 코드 | 설명 |
|------|------|
| `.about-box { display: flex; flex-direction: column; gap: 20px; background: var(--dark); color: #f3efe6; padding: 32px 24px; }` | 어바웃 박스를 어두운 배경의 세로 배치 플렉스박스로 만듭니다. |
| `.about-box h2 { font-family: "Instrument Serif", serif; font-size: 2rem; font-weight: 400; }` | 어바웃 섹션 제목의 글꼴과 크기를 설정합니다. |
| `.about-box p { opacity: 0.85; }` | 어바웃 설명 문구의 투명도를 조절합니다. |
| `.about-stats { display: grid; grid-template-columns: repeat(2, 1fr); gap: 16px; margin-top: 8px; }` | 통계 수치들을 2열 그리드로 정렬합니다. |
| `.stat strong { display: block; font-family: "Instrument Serif", serif; font-size: 2rem; color: #e8a078; }` | 통계 수치 강조 글자의 크기와 색상을 지정합니다. |
| `.stat span { font-size: 0.85rem; opacity: 0.75; }` | 통계 설명 라벨의 크기와 투명도를 지정합니다. |

---

## 7. FOOTER (푸터)

| 코드 | 설명 |
|------|------|
| `.site-footer { border-top: 1px solid var(--line); padding: 36px 20px; }` | 하단 푸터 영역의 위쪽 테두리와 패딩을 설정합니다. |
| `.footer-inner { max-width: var(--max); margin: 0 auto; display: flex; flex-direction: column; gap: 20px; }` | 푸터 내부 요소를 세로 방향 플렉스박스로 정렬합니다. |
| `.footer-nav { display: flex; flex-wrap: wrap; gap: 14px 22px; list-style: none; }` | 푸터 메뉴 목록을 가로로 정렬합니다. |
| `.footer-nav a { font-size: 0.9rem; color: var(--muted); }` | 푸터 링크의 글자 크기와 색상을 지정합니다. |
| `.footer-nav a:hover { color: var(--accent); }` | 푸터 링크에 마우스를 올리면 강조색으로 바뀝니다. |
| `.copy { font-size: 0.82rem; color: var(--muted); }` | 저작권 문구의 글자 크기와 색상을 지정합니다. |

---

## 8. MEDIA QUERIES (반응형)

| 코드 | 설명 |
|------|------|
| `@media (min-width: 768px) { ... }` | 화면 너비 768px 이상(태블릿 이상)일 때 적용할 스타일을 정의합니다. |
| `@media (min-width: 1024px) { ... }` | 화면 너비 1024px 이상(데스크톱 이상)일 때 적용할 스타일을 정의합니다. |

### 768px 이상에서 바뀌는 것

- 햄버거(MENU) 숨김 → 가로 메뉴 표시
- 카드 2열
- 어바웃 / 푸터 가로 배치

### 1024px 이상에서 바뀌는 것

- 카드 3열

---

## 9. HTML — 카드 영역

| 코드 | 설명 |
|------|------|
| `<div class="card-grid">` | 세 개의 카드 컴포넌트를 그리드 형태로 감싸는 컨테이너입니다. |
| `<article class="card">` | 독립적인 콘텐츠 조각인 개별 카드를 정의합니다. |
| `<div class="card-thumb">` | 카드의 상단 이미지 썸네일을 담는 영역입니다. |
| `<img src="..." alt="...">` | 카드의 시각적 대표 이미지를 불러옵니다. |
| `<div class="card-body">` | 카드의 텍스트와 태그 콘텐츠가 들어가는 본문 영역입니다. |
| `<div class="card-tags">` | 프로젝트 종류를 나타내는 태그들을 묶어주는 영역입니다. |
| `<span>Web</span>` | 카드의 분류를 나타내는 개별 태그 텍스트입니다. |
| `<h3>Atelier Gallery</h3>` | 카드의 메인 제목입니다. |
| `<p>전시 공간을 위한 미니멀 반응형 사이트...</p>` | 프로젝트에 대한 간략한 설명을 담은 단락입니다. |

### HTML 구조 한눈에 보기

```html
<div class="card-grid">
  <article class="card">
    <div class="card-thumb">
      <img src="..." alt="...">
    </div>
    <div class="card-body">
      <div class="card-tags">
        <span>Web</span>
        <span>Brand</span>
      </div>
      <h3>Atelier Gallery</h3>
      <p>전시 공간을 위한 미니멀 반응형 사이트...</p>
    </div>
  </article>
  <!-- 카드 반복 -->
</div>
```

---

## 관련 파일

- 완성 HTML: [responsive-lumina.html](./responsive-lumina.html)
- 반응형 해설: [반응형-웹-종합예제-해설.md](./반응형-웹-종합예제-해설.md)
