# example-react-scss — SCSS 중심 설명

Vite + React에서 **Sass(SCSS 문법)** 를 어떻게 나누고, **일반 SCSS**와 **CSS Module + SCSS**를 함께 쓰는지 보여 주는 최소 예제입니다.

## 의존성

- **`sass`** 패키지 하나로 SCSS 컴파일이 됩니다. (`node-sass` 아님)

## SCSS 파일 구조

```
src/
├── main.jsx
├── App.jsx
├── App.scss
├── styles/
│   ├── _variables.scss   ← 공통 변수 (부분 파일)
│   └── global.scss       ← 전역 리셋·body
└── components/
    ├── DemoCard/
    │   ├── DemoCard.jsx
    │   └── DemoCard.scss          ← 일반 SCSS (전역 클래스)
    └── ModuleDemo/
        ├── ModuleDemo.jsx
        └── ModuleDemo.module.scss ← CSS Module (클래스 해시)
```

## 1. 전역 스타일 — `global.scss` + `_variables.scss`

**`main.jsx`** 에서 한 번만 불러와 앱 전체에 적용합니다.

```js
import './styles/global.scss';
```

- **`_variables.scss`**: 파일명 앞 `_` 는 Sass에서 “부분 파일(partial)” 관례입니다. 직접 컴파일 대상이 아니라 **`@use`로만** 가져다 씁니다.
- **`global.scss`**: `@use './variables' as *;` 로 변수를 쓰고, `body`, `code` 등 **전역** 규칙만 둡니다.

여기서 쓰는 SCSS 개념:

- **`@use`**: 최신 Sass 방식으로 다른 파일의 변수·믹스인을 가져옵니다. (`as *` 는 네임스페이스 없이 `$primary` 처럼 바로 쓰기 위함.)

## 2. 루트 레이아웃 — `App.scss`

**`App.jsx`** 에서 `import './App.scss';` 로 연결합니다.

| SCSS 요소 | 이 예제에서 |
|-----------|-------------|
| **`@use './styles/variables' as *;`** | `$primary`, `$muted` 등 공통 색·값 재사용 |
| **`@mixin flex-center`** | 재사용 가능한 스타일 묶음 (이 파일에는 믹스인 정의만 있고, 필요 시 `@include` 로 확장) |
| **중첩** | `.app` 안에 `.title`, `.lead`, `.grid` 를 넣어 구조가 HTML과 비슷하게 읽힘 |

클래스 이름은 일반 문자열: `className="app"`, `className="title"` — **전역**으로 등록되므로 다른 파일에서 같은 클래스명을 쓰면 충돌할 수 있습니다.

## 3. 컴포넌트 옆 일반 SCSS — `DemoCard.scss`

**패턴**: `DemoCard.jsx` 옆에 `DemoCard.scss` 를 두고 `import './DemoCard.scss';` 만 합니다.

| SCSS 요소 | 이 예제에서 |
|-----------|-------------|
| **`@use '../../styles/variables' as *;`** | 깊은 폴더에서도 동일한 `_variables.scss` 참조 |
| **`&`** | `.demo-card:hover` → `&:hover { ... }` |
| **BEM 스타일 `&__heading`** | `.demo-card__heading` 으로 펼쳐짐 — 블록 안에서 요소명을 정리하기 좋음 |

일반 SCSS는 **빌드 후에도 클래스 문자열이 그대로**이므로, 이름 규칙(BEM 등)으로 충돌을 줄이는 방식입니다.

## 4. CSS Module + SCSS — `ModuleDemo.module.scss`

**파일명**: `*.module.scss` → Vite가 **CSS Module**로 처리합니다.

**`ModuleDemo.jsx`**:

```js
import styles from './ModuleDemo.module.scss';
// ...
<section className={styles.wrapper}>
```

| 특징 | 설명 |
|------|------|
| **클래스 충돌 방지** | `.title` 이라도 빌드 시 `ModuleDemo_title__해시` 처럼 파일마다 고유해짐 |
| **SCSS 문법 그대로** | 변수, 중첩, `@use` 모두 `.module.scss` 안에서 사용 가능 |

**`vite.config.js`** 의 `css.modules.localsConvention: 'camelCase'` 는 `class-name` 같은 케이스를 JS에서 `styles.className` 으로 다루기 쉽게 해 주는 설정입니다. (이 예제의 클래스는 단일 단어라 필수는 아님.)

## 5. 정리 — 이 예제가 보여 주는 것

| 파일 | 역할 | SCSS 포인트 |
|------|------|-------------|
| `_variables.scss` | 공통 `$변수` | `@use` 로 여러 SCSS에서 공유 |
| `global.scss` | 전역 한 번 | body 배경 등 |
| `App.scss` | 페이지 루트 | 중첩, `@mixin`, `@use` |
| `DemoCard.scss` | 일반 컴포넌트 SCSS | `&`, `&__element`, `@use` |
| `ModuleDemo.module.scss` | 모듈 + SCSS | 전역 오염 없이 클래스 스코프 |

실무에서는 **레이아웃·전역 토큰은 `global` / `_variables`**, **화면 단위는 일반 SCSS 또는 Module**, **재사용 컴포넌트는 `*.module.scss` 로 충돌 방지** 조합을 많이 씁니다.

## 실행

```bash
cd react/scss/example-react-scss
npm install
npm run dev
```

더 넓은 개념(설치 방법, `@use` vs 옛 `@import` 등)은 상위 폴더의 **`SCSS-React-가이드.md`** 를 참고하면 됩니다.
