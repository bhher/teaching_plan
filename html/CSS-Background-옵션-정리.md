# CSS Background 옵션 정리와 예제

배경(배경색, 배경 이미지)을 꾸밀 때 쓰는 `background` 관련 속성을 정리한 문서입니다.

---

## 목차

1. [한눈에 보기](#1-한눈에-보기)
2. [background-color](#2-background-color)
3. [background-image](#3-background-image)
4. [background-repeat](#4-background-repeat)
5. [background-position](#5-background-position)
6. [background-size](#6-background-size)
7. [background-attachment](#7-background-attachment)
8. [background-origin / clip](#8-background-origin--clip)
9. [단축 속성 background](#9-단축-속성-background)
10. [실전 예제](#10-실전-예제)
11. [자주 쓰는 조합](#11-자주-쓰는-조합)

---

## 1. 한눈에 보기

| 속성 | 설명 | 자주 쓰는 값 |
|------|------|--------------|
| `background-color` | 배경색 | `#fff`, `red`, `rgba(0,0,0,0.5)` |
| `background-image` | 배경 이미지 | `url("이미지경로")` |
| `background-repeat` | 이미지 반복 | `no-repeat`, `repeat`, `repeat-x` |
| `background-position` | 이미지 위치 | `center`, `top right`, `50% 50%` |
| `background-size` | 이미지 크기 | `cover`, `contain`, `100% 100%` |
| `background-attachment` | 스크롤 고정 | `scroll`, `fixed` |
| `background-origin` | 이미지 시작 기준 | `padding-box`, `border-box` |
| `background-clip` | 배경이 채워지는 범위 | `border-box`, `content-box` |
| `background` | 위 속성들을 한 줄로 작성 | 단축 속성 |

---

## 2. background-color

배경색을 지정합니다.

### 사용 가능한 값

| 표기법 | 예시 |
|--------|------|
| 색 이름 | `red`, `blue`, `transparent` |
| HEX | `#ff0000`, `#f00` |
| RGB | `rgb(255, 0, 0)` |
| RGBA | `rgba(255, 0, 0, 0.5)` (투명도 포함) |
| HSL | `hsl(0, 100%, 50%)` |

### 예제

```css
.box {
  background-color: #3498db;
}

.overlay {
  background-color: rgba(0, 0, 0, 0.5); /* 반투명 검정 */
}
```

```html
<div class="box">파란 배경</div>
```

---

## 3. background-image

배경에 이미지를 넣습니다.

### 예제

```css
.hero {
  background-image: url("images/bg.jpg");
  width: 100%;
  height: 400px;
}
```

### 여러 이미지 (레이어)

```css
.multi {
  background-image: url("logo.png"), url("bg.jpg");
  /* 앞에 적은 이미지가 위(앞)에 표시됨 */
}
```

### 그라데이션도 이미지처럼 사용

```css
.gradient {
  background-image: linear-gradient(to right, #3498db, #9b59b6);
}
```

---

## 4. background-repeat

배경 이미지의 반복 방식을 정합니다.

| 값 | 설명 |
|----|------|
| `repeat` | 가로·세로 모두 반복 (기본값) |
| `repeat-x` | 가로만 반복 |
| `repeat-y` | 세로만 반복 |
| `no-repeat` | 반복하지 않음 |
| `space` | 간격 두고 반복 |
| `round` | 잘리지 않게 맞춰 반복 |

### 예제

```css
.pattern {
  background-image: url("pattern.png");
  background-repeat: repeat; /* 타일처럼 반복 */
}

.banner {
  background-image: url("banner.jpg");
  background-repeat: no-repeat; /* 한 장만 */
}
```

---

## 5. background-position

배경 이미지가 어디에 위치할지 정합니다.

| 값 | 설명 |
|----|------|
| `left`, `center`, `right` | 가로 위치 |
| `top`, `center`, `bottom` | 세로 위치 |
| `50% 50%` | 가로 50%, 세로 50% (정중앙) |
| `20px 40px` | 왼쪽에서 20px, 위에서 40px |

### 예제

```css
.center-bg {
  background-image: url("photo.jpg");
  background-repeat: no-repeat;
  background-position: center center; /* 가운데 */
}

.top-right {
  background-image: url("icon.png");
  background-repeat: no-repeat;
  background-position: top right; /* 오른쪽 위 */
}
```

---

## 6. background-size

배경 이미지의 크기를 정합니다. **가장 자주 쓰는 속성**입니다.

| 값 | 설명 |
|----|------|
| `auto` | 원본 크기 (기본값) |
| `cover` | 영역을 **꽉 채움** (잘릴 수 있음) |
| `contain` | 이미지가 **잘리지 않게** 맞춤 (여백 생길 수 있음) |
| `100% 100%` | 가로·세로를 영역에 맞춤 (비율 깨질 수 있음) |
| `300px 200px` | 가로 300px, 세로 200px |

### cover vs contain

```
cover   → 빈 공간 없이 채움 (이미지 일부 잘릴 수 있음)
contain → 이미지 전체 보임 (위아래/양옆에 여백 가능)
```

### 예제

```css
.cover-box {
  background-image: url("photo.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: 300px;
}

.contain-box {
  background-image: url("logo.png");
  background-size: contain;
  background-position: center;
  background-repeat: no-repeat;
  height: 300px;
}
```

---

## 7. background-attachment

스크롤할 때 배경이 같이 움직일지, 고정할지 정합니다.

| 값 | 설명 |
|----|------|
| `scroll` | 콘텐츠와 같이 스크롤 (기본값) |
| `fixed` | 화면에 고정 (패럴랙스 효과) |
| `local` | 요소 내부 스크롤에 따라 움직임 |

### 예제

```css
.fixed-bg {
  background-image: url("bg.jpg");
  background-attachment: fixed;
  background-size: cover;
  background-position: center;
}
```

---

## 8. background-origin / clip

### background-origin

배경 이미지가 **어디서부터** 시작할지 정합니다.

| 값 | 설명 |
|----|------|
| `padding-box` | 패딩 영역부터 (기본값) |
| `border-box` | 테두리부터 |
| `content-box` | 콘텐츠 영역부터 |

### background-clip

배경이 **어디까지** 채워질지 정합니다.

| 값 | 설명 |
|----|------|
| `border-box` | 테두리까지 채움 (기본값) |
| `padding-box` | 패딩까지만 |
| `content-box` | 콘텐츠 영역만 |
| `text` | 글자 모양으로만 배경 표시 (특수 효과) |

### 예제: 글자에 그라데이션

```css
.gradient-text {
  background-image: linear-gradient(to right, #e74c3c, #3498db);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  font-size: 40px;
  font-weight: bold;
}
```

---

## 9. 단축 속성 background

여러 속성을 **한 줄**로 작성할 수 있습니다.

### 작성 순서 (권장)

```
background: [color] [image] [repeat] [attachment] [position] / [size];
```

> `size` 를 쓸 때는 `position` 뒤에 `/` 를 넣습니다.

### 예제

```css
/* 개별 작성 */
.box1 {
  background-color: #222;
  background-image: url("bg.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
}

/* 단축 작성 (같은 결과) */
.box2 {
  background: #222 url("bg.jpg") no-repeat fixed center / cover;
}
```

### 색만 / 이미지만

```css
.color-only {
  background: #f5f5f5;
}

.image-only {
  background: url("pattern.png") repeat;
}
```

---

## 10. 실전 예제

### 예제 1. 전체 화면 히어로 배경

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>히어로 배경</title>
  <style>
    .hero {
      height: 100vh;
      background: url("images/hero.jpg") no-repeat center / cover;
      display: flex;
      justify-content: center;
      align-items: center;
      color: #fff;
      text-shadow: 0 2px 8px rgba(0, 0, 0, 0.5);
    }
  </style>
</head>
<body>
  <section class="hero">
    <h1>환영합니다</h1>
  </section>
</body>
</html>
```

### 예제 2. 반투명 오버레이 + 배경 이미지

```css
.banner {
  position: relative;
  height: 400px;
  background: url("images/city.jpg") no-repeat center / cover;
}

.banner::before {
  content: "";
  position: absolute;
  inset: 0;
  background-color: rgba(0, 0, 0, 0.4); /* 어두운 막 */
}

.banner h2 {
  position: relative; /* 텍스트가 막 위로 올라오게 */
  color: #fff;
  text-align: center;
  padding-top: 160px;
}
```

```html
<div class="banner">
  <h2>도시 여행</h2>
</div>
```

### 예제 3. 카드에 그라데이션 배경

```css
.card {
  width: 280px;
  padding: 24px;
  border-radius: 12px;
  color: #fff;
  background: linear-gradient(135deg, #667eea, #764ba2);
}
```

```html
<div class="card">
  <h3>프로 플랜</h3>
  <p>월 9,900원</p>
</div>
```

### 예제 4. 반복 패턴 배경

```css
.page {
  background-color: #fafafa;
  background-image: url("images/dot.png");
  background-repeat: repeat;
}
```

### 예제 5. 고정 배경 (스크롤해도 배경 고정)

```css
.parallax {
  min-height: 500px;
  background: url("images/mountain.jpg") no-repeat center / cover fixed;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
}
```

### 예제 6. 여러 배경 레이어

```css
.layered {
  height: 300px;
  background-image:
    linear-gradient(rgba(0, 0, 0, 0.45), rgba(0, 0, 0, 0.45)),
    url("images/photo.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}
```

> 위 그라데이션이 어두운 막을 만들고, 아래 이미지가 사진 배경이 됩니다.

---

## 11. 자주 쓰는 조합

### 풀스크린 이미지 배경 (가장 추천)

```css
.full-bg {
  background: url("bg.jpg") no-repeat center / cover;
}
```

### 가운데 로고 + 단색 배경

```css
.logo-area {
  background-color: #111;
  background-image: url("logo.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: 120px;
}
```

### 그라데이션만

```css
.grad {
  background: linear-gradient(to bottom, #1abc9c, #3498db);
}
```

---

## 기억하면 좋은 포인트

1. **이미지만** 넣으면 보통 `no-repeat` + `center` + `cover` 를 함께 씁니다.
2. **`cover`**: 영역을 꽉 채움 / **`contain`**: 이미지 전체가 보임
3. 단축 속성에서 **`size`는 `position` 뒤 `/` 다음에** 씁니다.
4. 글자 위 사진이 잘 안 보이면 **반투명 검정 오버레이**를 올립니다.
5. 배경색과 배경 이미지를 같이 쓰면, 이미지 로딩 전에도 색이 보입니다.

```css
/* 안전한 기본 패턴 */
.safe-bg {
  background-color: #333; /* 이미지 없을 때 대비 */
  background-image: url("bg.jpg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
}
```

---

## 관련 교안

- [html-3회차-CSS기초-적용방법.md](./html-3회차-CSS기초-적용방법.md)
- [html-css-lesson4-font-text.md](./html-css-lesson4-font-text.md)
- [ex03/README.md](./ex03/README.md) — colors / backgrounds 실습
