# Bootstrap 원페이지 포트폴리오 (SEOJIN)

Bootstrap **5.3.8**으로 만든 **한 페이지 포트폴리오** 예제입니다.  
스크롤만으로 Home → Slide → About → Skills → Work → Contact를 모두 볼 수 있습니다.

수업에서 자주 쓰는 컴포넌트 **Navbar · Carousel · Tabs · Accordion**을 한 화면에 모아 두었습니다.

---

## 실행 방법

1. `index.html`을 Chrome / Edge 등 브라우저에서 엽니다.
2. **설치·빌드 없음** — CDN으로 Bootstrap CSS/JS를 불러옵니다.
3. 인터넷이 필요합니다. (CDN · Google Fonts · Unsplash 이미지)

```
boot/onepage-portfolio/index.html
```

`boot/index.html` 예제 목록의 **One Page** 카드에서도 열 수 있습니다.

---

## 폴더 구조

```
onepage-portfolio/
├── index.html      # 페이지 전체 마크업 (섹션·컴포넌트)
├── css/style.css   # 색상·히어로·탭/아코디언 커스텀
├── js/main.js      # 스크롤 시 nav 배경, 모바일 메뉴 닫기, 폼 데모
└── README.md       # 이 파일
```

| 파일 | 역할 |
|------|------|
| `index.html` | Navbar, Carousel, Tabs, Accordion, Form 등 **HTML 구조** |
| `css/style.css` | 브랜드 색, 히어로, 슬라이드 높이, 탭·아코디언 스타일 |
| `js/main.js` | `is-scrolled` 클래스, collapse 닫기, submit 알림 |

---

## 화면 구성 (위에서 아래)

| 순서 | 섹션 ID | 내용 | Bootstrap |
|------|---------|------|-----------|
| 1 | `#home` | 브랜드 SEOJIN · 헤드라인 · CTA | Hero (커스텀) |
| 2 | `#slide` | 이미지 슬라이드 3장 | **Carousel** |
| 3 | `#about` | 소개 / 경력 / 학력 | **Tabs** |
| 4 | `#skills` | Markup · Bootstrap · JS/React | **Accordion** |
| 5 | `#work` | 프로젝트 3개 | Grid (`col-md-4`) |
| 6 | `#contact` | 문의 폼 | Form · Button |
| — | — | 하단 저작권 | Footer |

상단 **Navbar**는 `fixed-top`이라 스크롤해도 따라옵니다.  
메뉴를 누르면 해당 섹션으로 부드럽게 이동합니다 (`scroll-behavior: smooth`).

---

## 포함 컴포넌트 상세

### 1. Navbar (네비게이션)

```html
<nav class="navbar navbar-expand-lg site-nav fixed-top" id="mainNav">
```

| 클래스 / 속성 | 의미 |
|---------------|------|
| `navbar-expand-lg` | lg 이상에서 가로 메뉴, 미만에서 햄버거 |
| `fixed-top` | 화면 상단 고정 |
| `data-bs-toggle="collapse"` | 모바일 메뉴 열기/닫기 |
| `data-bs-spy="scroll"` (body) | 스크롤 위치에 따라 메뉴 active |

`js/main.js`에서 스크롤이 내려가면 `is-scrolled`를 붙여 **배경이 진해지도록** 처리합니다.

---

### 2. Carousel (슬라이드)

위치: `#slide` · id=`pfCarousel`

| 요소 | 역할 |
|------|------|
| `carousel slide` + `data-bs-ride="carousel"` | 자동 슬라이드 |
| `carousel-indicators` | 하단 점(인디케이터) |
| `carousel-item active` | 첫 장 (active 필수) |
| `carousel-control-prev / next` | 이전·다음 버튼 |

슬라이드 내용은 이미지 URL + 하단 캡션(Web UI / Bootstrap / JavaScript)입니다.  
이미지는 `css/style.css`의 `.slide-a`, `.slide-b`, `.slide-c`에서 바꿉니다.

---

### 3. Tabs (탭 메뉴)

위치: `#about`

```html
<ul class="nav nav-tabs" id="aboutTab">
  <button data-bs-toggle="tab" data-bs-target="#pane-me">소개</button>
  ...
</ul>
<div class="tab-content">
  <div class="tab-pane fade show active" id="pane-me">...</div>
  ...
</div>
```

| 탭 | 패널 ID | 내용 |
|----|---------|------|
| 소개 | `#pane-me` | 자기소개 |
| 경력 | `#pane-career` | 경력 목록 |
| 학력 | `#pane-edu` | 학력·과정 |

`data-bs-toggle="tab"` + `data-bs-target`으로 버튼과 패널을 연결합니다.  
처음 보이는 패널에는 `show active`가 있어야 합니다.

---

### 4. Accordion (아코디언)

위치: `#skills` · id=`skillsAccordion`

| 항목 | 내용 |
|------|------|
| Markup & Style | HTML, CSS, Grid, Bootstrap Utility |
| Bootstrap Components | Navbar, Carousel, Tabs, Modal 등 |
| JavaScript & React | DOM, Props, State, useEffect |

한 칸만 열리게 하려면 각 collapse에 `data-bs-parent="#skillsAccordion"`을 둡니다.  
첫 칸은 `collapse show`, 나머지는 `collapsed` + `collapse`만 둡니다.

---

### 5. Grid · Form (Work / Contact)

- **Work:** `row` + `col-md-4` → 데스크톱 3열, 모바일 1열  
- **Contact:** `form-control`, `form-label`, `btn`  
- 전송은 데모용(`alert`)이며 실제 서버 전송은 없습니다.

---

## CDN · 사용 라이브러리

| 구분 | 주소 / 내용 |
|------|-------------|
| Bootstrap CSS | `bootstrap@5.3.8/dist/css/bootstrap.min.css` |
| Bootstrap JS | `bootstrap.bundle.min.js` (Popper 포함) |
| Icons | Bootstrap Icons 1.11 |
| Fonts | Syne (제목) · Outfit (본문) |

---

## 커스터마이징 가이드

### 이름 · 문구 바꾸기

`index.html`에서 검색·수정:

- `SEOJIN` → 본인 영문 브랜드  
- About 탭 문구, Skills 아코디언 내용  
- Work 프로젝트 제목·설명  
- Contact는 그대로 두거나 action URL만 연결

### 색상 바꾸기

`css/style.css` 상단 변수:

```css
--ink: #0d1f24;       /* 글자·다크 배경 */
--accent: #0d9488;    /* 버튼·강조 */
--paper: #e9eef1;     /* 페이지 배경 */
```

### 슬라이드 이미지 바꾸기

`.slide-a`, `.slide-b`, `.slide-c` 및 `.work-1` ~ `.work-3`의 `url(...)`을 교체합니다.

---

## 수업용 학습 포인트

1. **원페이지** = 여러 `section` + `id` + nav `href="#..."`  
2. **Navbar** 반응형 = `navbar-expand-lg` + `collapse`  
3. **Carousel** = `carousel-item` + `active` + controls  
4. **Tabs** = `nav-tabs` ↔ `tab-pane` 짝 맞추기  
5. **Accordion** = `data-bs-parent`로 하나만 열기  
6. JS는 최소한만 — 동작의 핵심은 Bootstrap `data-bs-*` 속성

---

## 관련 예제

| 경로 | 설명 |
|------|------|
| [../portfolio/](../portfolio/) | HAEUN 포트폴리오 (히어로·Work 중심) |
| [../examples/05-navbar-modal-carousel.html](../examples/05-navbar-modal-carousel.html) | Nav · Modal · Carousel만 |
| [../Bootstrap-핵심정리.md](../Bootstrap-핵심정리.md) | Bootstrap 이론 정리 |

---

## 공식 문서

- [Navbar](https://getbootstrap.com/docs/5.3/components/navbar/)  
- [Carousel](https://getbootstrap.com/docs/5.3/components/carousel/)  
- [Navs and tabs](https://getbootstrap.com/docs/5.3/components/navs-tabs/)  
- [Accordion](https://getbootstrap.com/docs/5.3/components/accordion/)  
