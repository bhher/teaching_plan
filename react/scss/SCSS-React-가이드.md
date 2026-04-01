# SCSS + React 가이드

## 1. SCSS란?

**CSS 확장 문법**입니다.

| 특징 | 설명 |
|------|------|
| 변수 | 색·간격 등을 한곳에서 관리 |
| 중첩 | 선택자를 계층처럼 작성 |
| 재사용 | 믹스인, `@use`로 분리 |

컴파일하면 일반 **CSS**로 변환되어 브라우저에 전달됩니다.

---

## 2. 설치 (React / Vite)

```bash
npm install sass
```

- **Dart Sass** (`sass` 패키지) 하나면 됩니다.
- 예전에 쓰던 **`node-sass`는 더 이상 권장하지 않습니다.**

`devDependencies`에 두는 경우가 많습니다.

```bash
npm install -D sass
```

---

## 3. 기본 사용 방법

### ① 파일 만들기

예: `App.scss`

### ② JS/JSX에서 import

```jsx
import './App.scss';
```

### ③ 작성 예 (중첩)

```scss
.app {
  background: #f5f5f5;

  .title {
    color: blue;
  }
}
```

일반 CSS와 달리 **중첩**이 가능합니다.

---

## 4. React에서 적용

```jsx
function App() {
  return (
    <div className="app">
      <h1 className="title">Hello</h1>
    </div>
  );
}
```

`className`은 그대로 두고, 스타일만 `.scss` 파일에 작성합니다.

---

## 5. 핵심 기능

### ① 변수

```scss
$main-color: blue;

.title {
  color: $main-color;
}
```

### ② 중첩 (자주 사용)

```scss
.card {
  padding: 10px;

  .title {
    font-size: 20px;
  }

  &:hover {
    background: gray;
  }
}
```

- **`&`** : 현재 선택자 (여기서는 `.card:hover`와 같음)

### ③ 믹스인 (재사용)

```scss
@mixin flex-center {
  display: flex;
  justify-content: center;
  align-items: center;
}

.box {
  @include flex-center;
}
```

### ④ 파일 분리 (실무)

**`src/styles/_variables.scss`**

```scss
$primary: #2563eb;
```

**`App.scss`** (최신 Sass는 `@use` 권장)

```scss
@use './styles/variables' as vars;

.title {
  color: vars.$primary;
}
```

또는 `as *`로 네임스페이스 없이 쓸 수 있습니다.

```scss
@use './styles/variables' as *;

.title {
  color: $primary;
}
```

---

## 6. 컴포넌트별 SCSS (추천)

```
components/
  TodoList.jsx
  TodoList.scss
```

```jsx
import './TodoList.scss';
```

컴포넌트와 스타일을 같이 두면 수정이 편합니다.

---

## 7. CSS Module + SCSS (충돌 방지)

**파일명**

```
Todo.module.scss
```

**JSX**

```jsx
import styles from './Todo.module.scss';

export default function Todo() {
  return <div className={styles.title}>제목</div>;
}
```

**Todo.module.scss**

```scss
.title {
  color: red;
}
```

| 효과 |
|------|
| 빌드 시 클래스명이 **고유하게** 바뀜 |
| 다른 컴포넌트의 `.title`과 **이름 충돌 없음** |

실무에서 **CSS Module + SCSS** 조합을 많이 씁니다.

---

## 8. 방식 비교

| 방식 | 특징 |
|------|------|
| 일반 `.scss` | import만 하면 됨, 간단 |
| **CSS Module** (`.module.scss`) | 클래스 충돌 방지, **추천** |
| styled-components 등 | JS 안에서 스타일 정의 |

---

## 9. 추천 (실무 기준)

- **Vite + React + `sass`**
- 스타일은 **`*.module.scss`** 로 컴포넌트 단위 분리
- 공통 변수·믹스인은 `styles/_variables.scss` 등으로 `@use`

---

## 10. 이 폴더의 예제

`scss/example-react-scss/` — 위 패턴을 최소 코드로 재현한 Vite 프로젝트입니다.

| 파일 | 설명 |
|------|------|
| `src/styles/_variables.scss` | 공통 변수 |
| `src/App.scss` | `@use`, 중첩, `@mixin` |
| `src/components/DemoCard/DemoCard.scss` | 컴포넌트 옆 일반 SCSS |
| `src/components/ModuleDemo/ModuleDemo.module.scss` | CSS Module |

```bash
cd react/scss/example-react-scss
npm install
npm run dev
```
