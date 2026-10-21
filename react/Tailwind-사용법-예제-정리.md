# Tailwind CSS 사용법 · 자주 쓰는 클래스 · 예제

유틸리티 클래스만으로 UI를 빠르게 만드는 CSS 프레임워크입니다.  
(CSS를 새로 쓰기보다 `class`에 작은 스타일 조각을 조합합니다.)

## React 실습 프로젝트

→ **`tailwind-examples/`** (Vite + React, 탭 10개)

```bash
cd react/tailwind-examples
npm install
npm run dev
```

| 탭 | 내용 |
|----|------|
| 01~02 | 기초 · 버튼/뱃지 |
| 03~05 | 카드 · 네비 · 폼 |
| 06~08 | 히어로 · Flex/Grid · 반응형 |
| 09~10 | 랜딩 · 가격표/모달 |

---

## 1. 왜 쓰나?

| 비교 | 일반 CSS | Tailwind |
|------|----------|----------|
| 스타일 위치 | `.btn { ... }` 별도 파일 | HTML/JSX의 `class`에 직접 |
| 이름 고민 | `.card-title-blue` 등 | 거의 없음 (`text-xl font-bold`) |
| 결과물 | 사용 안 해도 CSS 남을 수 있음 | 쓴 클래스만 빌드에 포함 |

```html
<!-- 일반 CSS -->
<button class="btn-primary">저장</button>

<!-- Tailwind -->
<button class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">
  저장
</button>
```

---

## 2. 설치 · 시작

### 방법 A — CDN (실습·데모용, 가장 간단)

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <script src="https://cdn.tailwindcss.com"></script>
  <title>Tailwind CDN</title>
</head>
<body class="bg-slate-100 p-8">
  <h1 class="text-3xl font-bold text-slate-800">Hello Tailwind</h1>
</body>
</html>
```

> CDN은 프로토타입·수업에 좋고, **배포용 프로덕션은 Vite 설치 방식**을 권장합니다.

### 방법 B — Vite + Tailwind v4 (권장)

```bash
npm install tailwindcss @tailwindcss/vite
```

`vite.config.js`:

```js
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

export default defineConfig({
  plugins: [react(), tailwindcss()],
})
```

`src/index.css`:

```css
@import "tailwindcss";
```

`main.jsx`에서 CSS import:

```js
import './index.css'
```

### 방법 C — Tailwind v3 (구버전 프로젝트)

```bash
npm install -D tailwindcss@3 postcss autoprefixer
npx tailwindcss init -p
```

`tailwind.config.js`에 `content` 경로 지정, CSS에:

```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

---

## 3. 핵심 개념

### 3.1 유틸리티 = 한 가지 스타일

| 클래스 | 의미 (대략) |
|--------|-------------|
| `p-4` | padding: 1rem |
| `mt-2` | margin-top: 0.5rem |
| `text-center` | text-align: center |
| `flex` | display: flex |
| `bg-blue-500` | 배경색 blue-500 |

### 3.2 숫자 스케일 (간격)

`0` · `0.5` · `1` · `2` · `3` · `4` · `5` · `6` · `8` · `10` · `12` · `16` · `20` · `24` …  
대략 **1 = 0.25rem (4px)** → `p-4` = 16px, `gap-2` = 8px

### 3.3 색상 스케일

`slate` · `gray` · `zinc` · `red` · `orange` · `amber` · `yellow` · `green` · `emerald` · `teal` · `cyan` · `blue` · `indigo` · `violet` · `purple` · `pink` …

숫자: `50`(아주 연함) → `500`(기본) → `900`(진함) → `950`

```
bg-blue-500   text-red-600   border-slate-200
```

### 3.4 상태 · 반응형 (앞에 붙임)

| 접두사 | 의미 |
|--------|------|
| `hover:` | 마우스 오버 |
| `focus:` | 포커스 |
| `active:` | 클릭 중 |
| `disabled:` | 비활성 |
| `sm:` `md:` `lg:` `xl:` | 화면 너비 이상일 때 |

```html
<button class="bg-blue-500 hover:bg-blue-600 focus:ring-2 disabled:opacity-50">
  버튼
</button>

<div class="w-full md:w-1/2 lg:w-1/3">반응형 너비</div>
```

모바일 우선: 기본 = 작은 화면, `md:`부터 큰 화면용.

---

## 4. 자주 쓰는 클래스 치트시트

### 레이아웃

| 클래스 | 설명 |
|--------|------|
| `block` `inline-block` `hidden` | display |
| `flex` `inline-flex` | Flexbox |
| `grid` | Grid |
| `flex-row` `flex-col` | 방향 |
| `flex-wrap` | 줄바꿈 |
| `items-center` `items-start` `items-end` | 교차축 정렬 |
| `justify-center` `justify-between` `justify-around` | 주축 정렬 |
| `gap-2` `gap-4` | flex/grid 간격 |
| `grid-cols-2` `grid-cols-3` `grid-cols-4` | 열 개수 |
| `col-span-2` | 열 합치기 |
| `container` `mx-auto` | 가운데 고정폭 컨테이너 |

### 크기 · 간격

| 클래스 | 설명 |
|--------|------|
| `w-full` `w-1/2` `w-64` `w-screen` | 너비 |
| `h-full` `h-screen` `h-12` `min-h-screen` | 높이 |
| `max-w-md` `max-w-lg` `max-w-4xl` `max-w-7xl` | 최대 너비 |
| `p-4` `px-6` `py-2` `pt-8` `pb-4` | padding |
| `m-4` `mx-auto` `my-8` `mt-2` `mb-4` `-mt-2` | margin |
| `space-y-4` `space-x-2` | 자식 사이 간격 |

### 타이포그래피

| 클래스 | 설명 |
|--------|------|
| `text-sm` `text-base` `text-lg` `text-xl` `text-2xl` `text-3xl` | 글자 크기 |
| `font-normal` `font-medium` `font-semibold` `font-bold` | 굵기 |
| `text-left` `text-center` `text-right` | 정렬 |
| `leading-tight` `leading-relaxed` | 줄간격 |
| `tracking-wide` | 자간 |
| `truncate` | 한 줄 + 말줄임 |
| `underline` `line-through` `no-underline` | 장식 |
| `uppercase` `lowercase` `capitalize` | 대소문자 |

### 배경 · 테두리 · 그림자

| 클래스 | 설명 |
|--------|------|
| `bg-white` `bg-slate-100` `bg-blue-500` | 배경색 |
| `bg-gradient-to-r from-blue-500 to-cyan-400` | 그라데이션 |
| `border` `border-2` `border-0` | 테두리 두께 |
| `border-slate-200` `border-b` `border-t` | 색·방향 |
| `rounded` `rounded-md` `rounded-lg` `rounded-xl` `rounded-full` | 둥글기 |
| `shadow` `shadow-sm` `shadow-md` `shadow-lg` | 그림자 |
| `opacity-50` `opacity-100` | 투명도 |

### 위치 · 오버플로

| 클래스 | 설명 |
|--------|------|
| `relative` `absolute` `fixed` `sticky` | position |
| `top-0` `right-0` `inset-0` | 위치값 |
| `z-10` `z-50` | z-index |
| `overflow-hidden` `overflow-auto` `overflow-x-auto` | overflow |
| `object-cover` `object-contain` | img 맞춤 |

### 기타 실무

| 클래스 | 설명 |
|--------|------|
| `cursor-pointer` | 포인터 |
| `select-none` | 텍스트 선택 막기 |
| `transition` `duration-200` `ease-in-out` | 애니메이션 기초 |
| `scale-105` `hover:scale-105` | 확대 |
| `sr-only` | 스크린리더만 보이게 |
| `ring-2` `ring-blue-500` | 포커스 링 |
| `divide-y` `divide-slate-200` | 자식 사이 구분선 |

---

## 5. 실전 예제

### 5.1 버튼

```html
<!-- Primary -->
<button class="px-4 py-2 bg-blue-600 text-white text-sm font-medium rounded-lg
               hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-400">
  저장
</button>

<!-- Outline -->
<button class="px-4 py-2 border border-slate-300 text-slate-700 text-sm rounded-lg
               hover:bg-slate-50">
  취소
</button>

<!-- Danger -->
<button class="px-4 py-2 bg-red-500 text-white text-sm rounded-lg hover:bg-red-600">
  삭제
</button>
```

### 5.2 카드

```html
<article class="max-w-sm bg-white rounded-xl shadow-md overflow-hidden">
  <img class="w-full h-48 object-cover" src="pic.jpg" alt="썸네일" />
  <div class="p-5">
    <h2 class="text-xl font-bold text-slate-800">카드 제목</h2>
    <p class="mt-2 text-slate-600 text-sm leading-relaxed">
      카드 설명 텍스트입니다.
    </p>
    <a href="#" class="inline-block mt-4 text-blue-600 font-medium hover:underline">
      더보기 →
    </a>
  </div>
</article>
```

### 5.3 Flex 헤더 (네비)

```html
<header class="flex items-center justify-between px-6 py-4 bg-white shadow-sm">
  <div class="text-xl font-bold text-slate-800">Brand</div>
  <nav class="hidden md:flex gap-6 text-sm text-slate-600">
    <a class="hover:text-blue-600" href="#">홈</a>
    <a class="hover:text-blue-600" href="#">소개</a>
    <a class="hover:text-blue-600" href="#">문의</a>
  </nav>
  <button class="md:hidden p-2 rounded hover:bg-slate-100">☰</button>
</header>
```

### 5.4 Grid 카드 목록

```html
<section class="max-w-6xl mx-auto p-6">
  <h1 class="text-2xl font-bold mb-6">상품 목록</h1>
  <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
    <div class="bg-white p-4 rounded-lg shadow">카드 1</div>
    <div class="bg-white p-4 rounded-lg shadow">카드 2</div>
    <div class="bg-white p-4 rounded-lg shadow">카드 3</div>
  </div>
</section>
```

### 5.5 입력 폼

```html
<form class="max-w-md space-y-4 p-6 bg-white rounded-xl shadow">
  <div>
    <label class="block text-sm font-medium text-slate-700 mb-1">이메일</label>
    <input
      type="email"
      class="w-full px-3 py-2 border border-slate-300 rounded-lg
             focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
      placeholder="you@example.com"
    />
  </div>
  <button type="submit"
    class="w-full py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
    로그인
  </button>
</form>
```

### 5.6 뱃지 · 알림

```html
<span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium
             bg-green-100 text-green-800">
  활성
</span>

<div class="p-4 rounded-lg bg-amber-50 border border-amber-200 text-amber-800 text-sm">
  주의: 저장하지 않은 변경사항이 있습니다.
</div>
```

### 5.7 가운데 정렬 히어로

```html
<section class="min-h-screen flex flex-col items-center justify-center
                bg-gradient-to-br from-slate-900 to-slate-700 text-white text-center px-4">
  <h1 class="text-4xl md:text-6xl font-bold">안녕하세요</h1>
  <p class="mt-4 text-lg text-slate-300 max-w-xl">짧은 소개 문구를 넣습니다.</p>
  <a href="#start"
     class="mt-8 px-6 py-3 bg-white text-slate-900 font-semibold rounded-lg
            hover:bg-slate-100 transition">
    시작하기
  </a>
</section>
```

### 5.8 React(JSX)에서 쓰기

```jsx
function Card({ title, desc }) {
  return (
    <div className="p-4 bg-white rounded-lg shadow hover:shadow-md transition">
      <h3 className="text-lg font-semibold text-slate-800">{title}</h3>
      <p className="mt-1 text-sm text-slate-600">{desc}</p>
    </div>
  )
}
```

조건부 클래스:

```jsx
<button
  className={`px-4 py-2 rounded-lg text-white ${
    active ? 'bg-blue-600' : 'bg-slate-400'
  }`}
>
  토글
</button>
```

여러 조건을 자주 쓰면 `clsx` / `cn` 헬퍼를 쓰는 경우가 많습니다.

---

## 6. Bootstrap vs Tailwind (수업용)

| | Bootstrap | Tailwind |
|--|-----------|----------|
| 방식 | 컴포넌트 클래스 (`btn btn-primary`) | 유틸리티 조합 (`px-4 bg-blue-600 …`) |
| 커스터마이즈 | 테마·오버라이드 | 클래스 조합이 곧 디자인 |
| 학습 | 컴포넌트 이름 암기 | 유틸리티 패턴 암기 |
| 결과 | 비슷한 느낌이 나기 쉬움 | 디자인 자유도 높음 |

이 레포의 Bootstrap 예제(`boot/`)와 비교해 보면 차이가 잘 보입니다.  
CDN Tailwind 사용 예: `react/modern-vision/`, `react/modern-vision-jsx/`

---

## 7. 수업 팁 · 주의

1. **먼저 레이아웃** (`flex` / `grid` / `gap`) → 그다음 색·폰트·둥글기  
2. **`mx-auto` + `max-w-*`** 로 페이지 폭 잡기  
3. **반응형은 모바일 기본** 후 `md:`, `lg:` 추가  
4. 클래스만 잔뜩 넣기보다 **반복되는 패턴은 컴포넌트로** 빼기 (React)  
5. CDN은 편하지만, 실무 프로젝트는 **Vite 플러그인 설치** 권장  
6. 공식 문서 검색이 가장 빠름: [https://tailwindcss.com/docs](https://tailwindcss.com/docs)

---

## 8. 한 장 요약 (자주 쓰는 조합)

```
레이아웃   flex items-center justify-between gap-4
카드       bg-white rounded-xl shadow p-6
버튼       px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700
입력       w-full border border-slate-300 rounded-lg px-3 py-2 focus:ring-2
제목       text-2xl font-bold text-slate-800
본문       text-sm text-slate-600 leading-relaxed
그리드     grid grid-cols-1 md:grid-cols-3 gap-6
가운데     flex items-center justify-center min-h-screen
숨김(모바일) hidden md:block
```

### 체크리스트

- [ ] CDN 또는 Vite로 Tailwind 적용
- [ ] `flex` / `grid` / `gap`으로 배치
- [ ] `p-` `m-` `text-` `bg-` `rounded-` `shadow` 사용
- [ ] `hover:` `md:` 접두사 사용
- [ ] 버튼 · 카드 · 폼 중 하나 직접 만들어 보기
