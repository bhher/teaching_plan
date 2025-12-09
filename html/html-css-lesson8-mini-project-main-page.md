## HTML + CSS 8차시 – 미니 프로젝트: 메인 페이지 만들기

### 0. 학습 목표

- HTML 시맨틱 태그(`header`, `nav`, `main`, `section`, `article`, `aside`, `footer`)를 실제 페이지 구조에 적용한다.
- Flexbox를 활용해 **레이아웃(헤더, 메뉴, 메인/사이드, 카드 목록 등)** 을 구성한다.
- HTML + CSS만으로 **회사 / 병원 / 포트폴리오** 중 하나의 메인 페이지를 기획하고 구현한다.
- 폴더 구조를 스스로 설계하고, 화면 구성을 말과 그림(텍스트 설명)으로 설명할 수 있다.

---

### 1. 프로젝트 개요

#### 주제 선택 (학생이 1개 선택)

- **회사 메인 페이지**
  - 예: IT 스타트업, 카페, 학원 등
- **병원 메인 페이지**
  - 예: 동네 내과, 치과, 한의원 등
- **포트폴리오 메인 페이지**
  - 예: 개인 개발자/디자이너 포트폴리오

공통 조건:
- 시맨틱 태그 필수 사용
- Flexbox 필수 사용
- HTML + CSS만 사용 (JavaScript 없음)

---

### 2. 요구사항 정리

#### 2.1 필수 시맨틱 구조

페이지에 아래 구조를 반드시 포함:

- `header`
  - 로고(텍스트 OK), 상단 네비게이션 메뉴
- `nav`
  - 주요 메뉴 링크: 예) 회사 소개, 서비스, 공지사항, 문의하기 등
- `main`
  - 메인 콘텐츠 영역 전체
  - 안에 여러 `section` / `article` 로 나눔
- `section`
  - 히어로 영역(큰 배너), 서비스/진료과목/프로젝트 소개 등
- `article` (선택)
  - 개별 카드(서비스 카드, 프로젝트 카드 등)를 감쌀 때 사용
- `aside` (선택)
  - 공지사항, 빠른 링크, 연락처 등 보조 정보
- `footer`
  - 회사/병원 정보, 저작권, 연락처, SNS 링크 등

#### 2.2 Flexbox 사용 위치 (예시)

- 헤더에서 **로고 + 메뉴**를 좌우로 나누어 배치
- 메인 영역에서 **본문 + 사이드(aside)** 2열 레이아웃
- 서비스 카드/프로젝트 카드 목록을 가로로 정렬

예:

```css
header .inner {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.main-layout {
  display: flex;
  gap: 24px;
}

.card-list {
  display: flex;
  gap: 16px;
}
```

---

### 3. 폴더 구조

프로젝트 폴더 예시:

```txt
mini-project/
  index.html          # 메인 페이지
  css/
    style.css         # 전체 스타일
  images/             # 로고, 배너, 아이콘(선택)
    logo.png
    hero.jpg
```

설명:
- **`index.html`**
  - 메인 페이지(회사/병원/포트폴리오) 한 장.
- **`css/style.css`**
  - 전체 레이아웃, 색상, 폰트, 카드 스타일 등 모든 CSS.
- **`images` 폴더**
  - 로고나 배너 이미지는 실제가 아니어도 됨(임시 이미지 사용 가능).

학생에게 안내:
- 실제 수업에서는 VS Code에서 `mini-project` 폴더를 열고 작업.
- 이미지가 없을 경우, 배경색과 단색 블록만으로 디자인해도 충분하다.

---

### 4. HTML 기본 구조 스켈레톤

> 실제 내용(텍스트, 메뉴 이름, 섹션 제목 등)은  
> 학생이 선택한 주제(회사/병원/포트폴리오)에 맞게 수정합니다.

#### 4.1 `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0"
    />
    <title>미니 프로젝트 메인 페이지</title>
    <link rel="stylesheet" href="css/style.css" />
  </head>
  <body>
    <header class="site-header">
      <div class="inner header-inner">
        <h1 class="logo">My Company</h1>
        <nav class="main-nav">
          <ul>
            <li><a href="#">회사 소개</a></li>
            <li><a href="#">서비스</a></li>
            <li><a href="#">공지사항</a></li>
            <li><a href="#">문의하기</a></li>
          </ul>
        </nav>
      </div>
    </header>

    <main class="site-main">
      <!-- 히어로 / 메인 배너 영역 -->
      <section class="hero-section">
        <div class="inner hero-inner">
          <div class="hero-text">
            <h2>당신의 비즈니스를 돕는 파트너</h2>
            <p>
              간단한 소개 문구를 여기에 작성합니다. 회사/병원/포트폴리오
              콘셉트에 맞게 자유롭게 수정하세요.
            </p>
            <a href="#" class="hero-button">자세히 보기</a>
          </div>
          <div class="hero-image">
            <!-- 이미지 또는 색 블록 -->
          </div>
        </div>
      </section>

      <!-- 메인 레이아웃: 본문 + 사이드 -->
      <section class="main-layout inner">
        <section class="main-content">
          <h2>서비스 / 진료과목 / 프로젝트</h2>
          <div class="card-list">
            <article class="card">
              <h3 class="card-title">카드 1</h3>
              <p class="card-text">
                서비스/과목/프로젝트에 대한 간단 설명.
              </p>
            </article>

            <article class="card">
              <h3 class="card-title">카드 2</h3>
              <p class="card-text">
                서비스/과목/프로젝트에 대한 간단 설명.
              </p>
            </article>

            <article class="card">
              <h3 class="card-title">카드 3</h3>
              <p class="card-text">
                서비스/과목/프로젝트에 대한 간단 설명.
              </p>
            </article>
          </div>
        </section>

        <aside class="sidebar">
          <section class="notice">
            <h2>공지사항</h2>
            <ul>
              <li><a href="#">공지사항 1</a></li>
              <li><a href="#">공지사항 2</a></li>
              <li><a href="#">공지사항 3</a></li>
            </ul>
          </section>

          <section class="contact">
            <h2>문의 / 연락처</h2>
            <p>전화: 02-123-4567</p>
            <p>이메일: info@example.com</p>
          </section>
        </aside>
      </section>
    </main>

    <footer class="site-footer">
      <div class="inner footer-inner">
        <small>© 2025 My Company. All rights reserved.</small>
      </div>
    </footer>
  </body>
</html>
```

---

### 5. CSS 기본 구조 스켈레톤

> Flexbox와 시맨틱 구조를 보여주기 위한 최소 스타일입니다.  
> 색상, 간격, 폰트 등은 수업 중 또는 과제로 자유롭게 변경합니다.

#### 5.1 `css/style.css`

```css
*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  margin: 0;
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
    sans-serif;
  background-color: #f5f5f5;
}

.inner {
  max-width: 1100px;
  margin: 0 auto;
  padding: 0 16px;
}

/* 헤더 */
.site-header {
  background-color: #283593;
  color: #ffffff;
}

.header-inner {
  display: flex; /* Flexbox: 로고 + 메뉴 좌우 배치 */
  justify-content: space-between;
  align-items: center;
  padding: 16px 0;
}

.logo {
  margin: 0;
  font-size: 24px;
}

.main-nav ul {
  list-style: none;
  display: flex; /* Flexbox: 메뉴 가로 정렬 */
  gap: 16px;
  margin: 0;
  padding: 0;
}

.main-nav a {
  color: #ffffff;
  text-decoration: none;
  font-size: 14px;
}

.main-nav a:hover {
  text-decoration: underline;
}

/* 히어로 섹션 */
.hero-section {
  background-color: #3949ab;
  color: #ffffff;
  padding: 40px 0;
}

.hero-inner {
  display: flex; /* Flexbox: 텍스트 + 이미지 영역 */
  gap: 24px;
  align-items: center;
}

.hero-text {
  flex: 1;
}

.hero-text h2 {
  margin-top: 0;
  margin-bottom: 12px;
  font-size: 28px;
}

.hero-text p {
  margin-top: 0;
  margin-bottom: 16px;
}

.hero-button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #ffca28;
  color: #000;
  text-decoration: none;
  border-radius: 4px;
  font-weight: 600;
}

.hero-image {
  flex: 1;
  min-height: 150px;
  background-color: rgba(255, 255, 255, 0.1);
  border-radius: 8px;
}

/* 메인 레이아웃 */
.site-main {
  padding: 32px 0;
}

.main-layout {
  display: flex; /* Flexbox: 메인 콘텐츠 + 사이드 */
  gap: 24px;
}

.main-content {
  flex: 2;
}

.sidebar {
  flex: 1;
}

/* 카드 리스트 – 서비스/프로젝트 카드 */
.card-list {
  display: flex; /* Flexbox: 카드 가로 정렬 */
  gap: 16px;
  margin-top: 16px;
}

.card {
  flex: 1;
  background-color: #ffffff;
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.06);
}

.card-title {
  margin-top: 0;
  margin-bottom: 8px;
  font-size: 18px;
}

.card-text {
  margin: 0;
  font-size: 14px;
  color: #555555;
}

/* 사이드 영역 */
.sidebar section {
  background-color: #ffffff;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.06);
}

.sidebar h2 {
  margin-top: 0;
  font-size: 16px;
}

.notice ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.notice li + li {
  margin-top: 8px;
}

.notice a {
  text-decoration: none;
  color: #333333;
  font-size: 14px;
}

.notice a:hover {
  text-decoration: underline;
}

/* 푸터 */
.site-footer {
  background-color: #1a237e;
  color: #ffffff;
  padding: 16px 0;
  font-size: 12px;
}

.footer-inner {
  text-align: center;
}
```

---

### 6. 완성 화면 설명 (텍스트로 그려보기)

학생들에게 다음과 같이 “완성 화면”을 말이나 그림으로 설명하게 할 수 있습니다.

- **상단 헤더 영역**
  - 파란색 배경 위에 왼쪽에는 사이트 로고(텍스트),
  - 오른쪽에는 가로로 나열된 메뉴 4개(회사 소개 / 서비스 / 공지사항 / 문의하기).
  - 헤더 안의 요소들은 Flexbox로 좌우 배치, 가운데 정렬.

- **히어로(메인 배너) 영역**
  - 진한 파란색 배경의 큰 영역.
  - 왼쪽에는 굵은 제목과 간단한 설명, “자세히 보기” 버튼.
  - 오른쪽에는 이미지 박스(또는 배경 박스).
  - 두 영역은 Flexbox로 좌우 배치, 화면이 작아지면 세로로 쌓이도록 변경 가능(심화).

- **메인 콘텐츠 + 사이드 영역**
  - 가운데 정렬된 메인 레이아웃 안에, 왼쪽에는 서비스/프로젝트 카드 3개가 가로로 정렬.
  - 오른쪽에는 공지사항 리스트와 연락처 박스가 세로로 배치된 사이드바.
  - 메인과 사이드 전체는 Flexbox로 2열 구성, 카드 목록도 Flexbox로 정렬.

- **푸터 영역**
  - 화면 가장 아래에 어두운 파란색 배경의 얇은 영역.
  - 가운데에 저작권 문구가 작게 표시.

학생들이 말로 설명할 수 있도록 유도:
- “지금 만든 페이지를 A4용지에 연필로 대략 그려 본다면 어떻게 생겼나요?”  
- “모바일 화면에서는 어떤 영역이 위에서부터 어떤 순서로 보이게 할 건가요?”

---

### 7. 과제 정리

1. **주제 확정 & 텍스트 수정**
   - 회사/병원/포트폴리오 중 1개를 선택하고,
   - 로고 텍스트, 메뉴 이름, 섹션 제목, 카드 내용, 공지사항 텍스트를  
     선택한 주제에 맞게 모두 수정한다.

2. **색상/폰트 커스터마이징**
   - 브랜드 컬러(또는 병원/포트폴리오에 어울리는 색상)를 정해  
     헤더/버튼/카드 등에 적용해 본다.

3. **반응형(선택 심화)**
   - `@media` 를 사용해 화면이 좁아졌을 때:
     - `hero-inner` 와 `.main-layout` 이 세로로 쌓이도록 변경해 보기.
   - 모바일 화면에서 메뉴가 줄바꿈되더라도 레이아웃이 깨지지 않는 디자인을 고민해 보기.



