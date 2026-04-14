# Styled Site (styled-components) 설명

이 문서는 `react/styled-site` 프로젝트가 **`styled-components`만**으로 (CSS 파일 없이) 랜딩 페이지 형태의 사이트 UI를 만드는 흐름을 정리한 것입니다. 아래에 **사이트 전체 코딩 내용(주요 파일 전체 코드)** 도 함께 포함했습니다.

---

## 1. 개요

- **핵심 라이브러리**: `styled-components`
  - `styled.div\`...\`` 같은 방식으로 **컴포넌트와 스타일을 같은 파일**에서 관리합니다.
  - `ThemeProvider`로 **theme(색/토큰)** 을 전역 주입합니다.
  - `createGlobalStyle`로 **전역 스타일**(배경/기본 폰트/리셋)을 한 번에 적용합니다.
- **사이트 구성**: 헤더(앵커 네비) → 히어로 → 기능 카드 → 요금 카드 → FAQ/푸터
- **데이터 분리**: 화면에 뿌릴 텍스트/플랜은 `src/data/content.js`에 모아두고, UI는 컴포넌트가 담당합니다.

---

## 2. 폴더 구조

```
styled-site/
├── index.html
├── package.json
├── vite.config.js
├── Styled-Site-설명.md
└── src/
    ├── main.jsx
    ├── App.jsx
    ├── data/
    │   └── content.js
    ├── styles/
    │   ├── GlobalStyle.js
    │   ├── theme.js
    │   └── tokens.js
    └── components/
        ├── Button.jsx
        ├── Card.jsx
        ├── SiteHeader.jsx
        ├── Hero.jsx
        ├── FeatureGrid.jsx
        ├── Pricing.jsx
        └── SiteFooter.jsx
```

---

## 3. 렌더링/스타일 흐름

### 3.1 `main.jsx`에서 전역 스타일 + 테마 주입

- `ThemeProvider theme={theme}`: 모든 styled 컴포넌트에서 `({ theme }) => ...`로 접근 가능
- `<GlobalStyle />`: `createGlobalStyle`로 만든 전역 CSS를 주입

### 3.2 `App.jsx`에서 섹션 컴포넌트를 조립

- `SiteHeader`, `Hero`, `FeatureGrid`, `Pricing`, `SiteFooter`를 순서대로 렌더링
- `FeatureGrid`에는 `features`, `Pricing`에는 `plans`를 props로 전달 (데이터/뷰 분리)

### 3.3 `tokens → theme → 컴포넌트` 의존 방향

```
tokens.js (radius/space/shadow)
  → theme.js (colors/font + tokens)
    → ThemeProvider
      → 모든 styled 컴포넌트에서 theme 사용
```

---

## 4. 컴포넌트별 역할 요약

- **`Button.jsx`**: `$variant`로 `primary` / `ghost` 변형 제공, `:focus-visible` 링 적용
- **`Card.jsx`**: 카드 골격(`Card`, `CardBody`, `CardTitle`, `CardDesc`, `Pill`) 재사용
- **`SiteHeader.jsx`**: 상단 고정(sticky) + 섹션 앵커 링크(`#features`, `#pricing`, `#faq`)
- **`Hero.jsx`**: 사이트 소개 + CTA 버튼 + 코드 스니펫 패널
- **`FeatureGrid.jsx`**: `features` 배열을 카드 그리드로 렌더
- **`Pricing.jsx`**: `plans` 배열을 요금 카드로 렌더 + 데모 버튼(`alert`)
- **`SiteFooter.jsx`**: FAQ와 간단 메모 + 연도 표시

---

## 5. 실행 방법

프로젝트 루트(`styled-site`)에서:

```bash
npm install
npm run dev
```

---

## 6. 전체 코드 (사이트 코딩 내용)

아래 코드는 프로젝트의 주요 파일을 그대로 옮긴 것입니다.

### `package.json`

```json
{
  "name": "styled-site",
  "private": true,
  "version": "1.0.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview"
  },
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "styled-components": "^6.1.19"
  },
  "devDependencies": {
    "@vitejs/plugin-react": "^4.2.1",
    "vite": "^5.0.0"
  }
}
```

### `vite.config.js`

```js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
});
```

### `index.html`

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Styled Site — styled-components</title>
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.jsx"></script>
  </body>
</html>
```

### `src/main.jsx`

```jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import { ThemeProvider } from 'styled-components';
import App from './App';
import { GlobalStyle } from './styles/GlobalStyle';
import { theme } from './styles/theme';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <App />
    </ThemeProvider>
  </React.StrictMode>
);
```

### `src/styles/tokens.js`

```js
export const tokens = {
  radius: {
    sm: '10px',
    md: '14px',
    lg: '18px',
  },
  shadow: {
    sm: '0 10px 30px rgba(0,0,0,0.25)',
    ring: '0 0 0 4px rgba(106, 166, 255, 0.25)',
  },
  space: {
    1: '4px',
    2: '8px',
    3: '12px',
    4: '16px',
    5: '20px',
    6: '24px',
    8: '32px',
    10: '40px',
    12: '48px',
    16: '64px',
  },
};
```

### `src/styles/theme.js`

```js
import { tokens } from './tokens';

export const theme = {
  tokens,
  colors: {
    bg: '#0b1220',
    surface: '#121b2e',
    surface2: '#17223a',
    border: '#2a3650',
    text: '#eaf0ff',
    muted: '#a7b3cc',
    primary: '#6aa6ff',
    primary2: '#8a7dff',
    ok: '#62d9a6',
    warn: '#ffd37a',
  },
  font: {
    body: \"system-ui, 'Segoe UI', sans-serif\",
    mono: \"ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, 'Liberation Mono', 'Courier New', monospace\",
  },
};
```

### `src/styles/GlobalStyle.js`

```js
import { createGlobalStyle } from 'styled-components';

export const GlobalStyle = createGlobalStyle`
  :root {
    color-scheme: dark;
  }

  *, *::before, *::after {
    box-sizing: border-box;
  }

  html, body {
    height: 100%;
  }

  body {
    margin: 0;
    font-family: ${({ theme }) => theme.font.body};
    background: radial-gradient(1200px 700px at 20% 10%, rgba(106, 166, 255, 0.20), transparent 60%),
      radial-gradient(1100px 650px at 85% 25%, rgba(138, 125, 255, 0.20), transparent 55%),
      ${({ theme }) => theme.colors.bg};
    color: ${({ theme }) => theme.colors.text};
  }

  a {
    color: inherit;
  }

  ::selection {
    background: rgba(106, 166, 255, 0.25);
  }

  code {
    font-family: ${({ theme }) => theme.font.mono};
    font-size: 0.95em;
  }

  button, input, textarea {
    font: inherit;
  }
`;
```

### `src/data/content.js`

```js
export const features = [
  {
    title: '컴포넌트 단위 스타일',
    desc: 'CSS 파일을 찾지 않아도, 컴포넌트 옆에서 스타일을 함께 관리합니다.',
    tag: 'styled()',
  },
  {
    title: 'ThemeProvider',
    desc: '색상·여백·라운드 같은 토큰을 theme으로 공유해 일관성을 유지합니다.',
    tag: 'theme',
  },
  {
    title: 'GlobalStyle',
    desc: 'reset/배경/기본 폰트 같은 전역 규칙을 한 번에 적용합니다.',
    tag: 'createGlobalStyle',
  },
];

export const plans = [
  {
    name: 'Starter',
    price: '무료',
    highlights: ['기본 버튼/카드', '테마 토큰', '반응형 레이아웃'],
    accent: 'primary',
  },
  {
    name: 'Pro',
    price: '₩9,900',
    highlights: ['섹션 컴포넌트', '그라데이션 배경', '포커스 링/접근성'],
    accent: 'primary2',
  },
  {
    name: 'Team',
    price: '₩29,900',
    highlights: ['재사용 설계', '데이터 분리', 'UI 확장하기 쉬움'],
    accent: 'ok',
  },
];
```

### `src/App.jsx`

```jsx
import styled from 'styled-components';
import SiteHeader from './components/SiteHeader';
import Hero from './components/Hero';
import FeatureGrid from './components/FeatureGrid';
import Pricing from './components/Pricing';
import SiteFooter from './components/SiteFooter';
import { features, plans } from './data/content';

const Shell = styled.div`
  min-height: 100vh;
`;

const SectionSpacer = styled.div`
  height: ${({ theme }) => theme.tokens.space[12]};
`;

export default function App() {
  return (
    <Shell>
      <SiteHeader />
      <Hero />
      <FeatureGrid items={features} />
      <SectionSpacer />
      <Pricing plans={plans} />
      <SiteFooter />
    </Shell>
  );
}
```

### `src/components/Button.jsx`

```jsx
import styled, { css } from 'styled-components';

const variants = {
  primary: css`
    background: ${({ theme }) => theme.colors.primary};
    color: #06101f;
  `,
  ghost: css`
    background: transparent;
    color: ${({ theme }) => theme.colors.text};
    border: 1px solid ${({ theme }) => theme.colors.border};
  `,
};

export const Button = styled.button`
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  padding: 10px 14px;
  border-radius: ${({ theme }) => theme.tokens.radius.md};
  border: 1px solid transparent;
  cursor: pointer;
  font-weight: 700;
  letter-spacing: -0.01em;
  transition: transform 120ms ease, background 120ms ease, border-color 120ms ease, opacity 120ms ease;

  ${({ $variant }) => variants[$variant ?? 'primary']}

  &:hover {
    transform: translateY(-1px);
    opacity: 0.96;
  }

  &:active {
    transform: translateY(0px);
  }

  &:focus-visible {
    outline: none;
    box-shadow: ${({ theme }) => theme.tokens.shadow.ring};
  }

  &:disabled {
    cursor: not-allowed;
    opacity: 0.6;
  }
`;
```

### `src/components/Card.jsx`

```jsx
import styled from 'styled-components';

export const Card = styled.div`
  border: 1px solid ${({ theme }) => theme.colors.border};
  background: ${({ theme }) => theme.colors.surface};
  border-radius: ${({ theme }) => theme.tokens.radius.lg};
  box-shadow: ${({ theme }) => theme.tokens.shadow.sm};
`;

export const CardBody = styled.div`
  padding: ${({ theme }) => theme.tokens.space[6]};
`;

export const CardTitle = styled.h3`
  margin: 0 0 ${({ theme }) => theme.tokens.space[2]} 0;
  font-size: 1.05rem;
  letter-spacing: -0.02em;
`;

export const CardDesc = styled.p`
  margin: 0;
  color: ${({ theme }) => theme.colors.muted};
  line-height: 1.6;
`;

export const Pill = styled.span`
  display: inline-flex;
  align-items: center;
  padding: 6px 10px;
  border-radius: 999px;
  font-size: 0.85rem;
  border: 1px solid ${({ theme }) => theme.colors.border};
  background: ${({ theme }) => theme.colors.surface2};
  color: ${({ theme }) => theme.colors.muted};
`;
```

### `src/components/SiteHeader.jsx`

```jsx
import styled from 'styled-components';
import { Button } from './Button';

const Bar = styled.header`
  position: sticky;
  top: 0;
  z-index: 10;
  backdrop-filter: blur(10px);
  background: rgba(18, 27, 46, 0.7);
  border-bottom: 1px solid ${({ theme }) => theme.colors.border};
`;

const Inner = styled.div`
  max-width: 980px;
  margin: 0 auto;
  padding: 14px 18px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
`;

const Brand = styled.a`
  text-decoration: none;
  display: inline-flex;
  align-items: center;
  gap: 10px;
  font-weight: 900;
  letter-spacing: -0.03em;
`;

const Dot = styled.span`
  width: 12px;
  height: 12px;
  border-radius: 999px;
  background: linear-gradient(135deg, ${({ theme }) => theme.colors.primary}, ${({ theme }) => theme.colors.primary2});
  box-shadow: 0 0 0 6px rgba(106, 166, 255, 0.12);
`;

const Nav = styled.nav`
  display: flex;
  gap: 8px;
  align-items: center;
`;

const NavLink = styled.a`
  color: ${({ theme }) => theme.colors.muted};
  text-decoration: none;
  padding: 8px 10px;
  border-radius: ${({ theme }) => theme.tokens.radius.md};
  border: 1px solid transparent;

  &:hover {
    color: ${({ theme }) => theme.colors.text};
    background: rgba(255, 255, 255, 0.04);
    border-color: rgba(255, 255, 255, 0.06);
  }

  &:focus-visible {
    outline: none;
    box-shadow: ${({ theme }) => theme.tokens.shadow.ring};
  }
`;

export default function SiteHeader() {
  return (
    <Bar>
      <Inner>
        <Brand href="#top" aria-label="Styled Site 홈">
          <Dot />
          Styled Site
        </Brand>

        <Nav aria-label="섹션 메뉴">
          <NavLink href="#features">기능</NavLink>
          <NavLink href="#pricing">요금</NavLink>
          <NavLink href="#faq">FAQ</NavLink>
          <Button as="a" href="#pricing" $variant="primary">
            시작하기
          </Button>
        </Nav>
      </Inner>
    </Bar>
  );
}
```

### `src/components/Hero.jsx`

```jsx
import styled from 'styled-components';
import { Button } from './Button';
import { Pill } from './Card';

const Wrap = styled.section`
  max-width: 980px;
  margin: 0 auto;
  padding: 54px 18px 18px;
`;

const Grid = styled.div`
  display: grid;
  gap: 18px;
  align-items: center;
  grid-template-columns: 1.25fr 0.75fr;

  @media (max-width: 860px) {
    grid-template-columns: 1fr;
  }
`;

const Title = styled.h1`
  margin: 0;
  font-size: clamp(2rem, 4vw, 3.1rem);
  letter-spacing: -0.05em;
  line-height: 1.05;
`;

const Sub = styled.p`
  margin: 14px 0 0;
  color: ${({ theme }) => theme.colors.muted};
  font-size: 1.05rem;
  line-height: 1.6;
`;

const Row = styled.div`
  margin-top: 18px;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  align-items: center;
`;

const Panel = styled.div`
  border: 1px solid ${({ theme }) => theme.colors.border};
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.06), rgba(255, 255, 255, 0.02));
  border-radius: ${({ theme }) => theme.tokens.radius.lg};
  padding: 18px;
  min-height: 220px;
  position: relative;
  overflow: hidden;
`;

const Glow = styled.div`
  position: absolute;
  inset: -60px -60px auto auto;
  width: 260px;
  height: 260px;
  border-radius: 50%;
  background: radial-gradient(circle, rgba(98, 217, 166, 0.25), transparent 55%);
  filter: blur(2px);
`;

const Code = styled.pre`
  margin: 0;
  font-family: ${({ theme }) => theme.font.mono};
  font-size: 0.9rem;
  line-height: 1.5;
  color: rgba(234, 240, 255, 0.92);
  white-space: pre-wrap;
`;

export default function Hero() {
  return (
    <Wrap id="top">
      <Grid>
        <div>
          <Pill>styled-components · ThemeProvider · GlobalStyle</Pill>
          <Title>컴포넌트에 스타일을 “붙여서” 만드는 작은 랜딩 페이지</Title>
          <Sub>
            이 예제는 <code>styled-components</code>만 사용합니다. 버튼/카드 같은 재사용 UI를 만들고,
            theme 토큰으로 색·여백·라운드를 통일해 봅니다.
          </Sub>
          <Row>
            <Button as="a" href="#features" $variant="primary">
              기능 보기
            </Button>
            <Button as="a" href="#pricing" $variant="ghost">
              요금 보기
            </Button>
          </Row>
        </div>

        <Panel aria-label="코드 스니펫">
          <Glow />
          <Code>{`import styled from 'styled-components';

const Title = styled.h1\`
  color: \${({ theme }) => theme.colors.text};
\`;
`}</Code>
        </Panel>
      </Grid>
    </Wrap>
  );
}
```

### `src/components/FeatureGrid.jsx`

```jsx
import styled from 'styled-components';
import { Card, CardBody, CardDesc, CardTitle, Pill } from './Card';

const Section = styled.section`
  max-width: 980px;
  margin: 0 auto;
  padding: 18px 18px 0;
`;

const Head = styled.div`
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 12px;
`;

const H2 = styled.h2`
  margin: 0;
  font-size: 1.4rem;
  letter-spacing: -0.03em;
`;

const P = styled.p`
  margin: 6px 0 0;
  color: ${({ theme }) => theme.colors.muted};
`;

const Grid = styled.div`
  margin-top: 14px;
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 14px;

  @media (max-width: 860px) {
    grid-template-columns: 1fr;
  }
`;

const CardTop = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 10px;
  margin-bottom: 10px;
`;

export default function FeatureGrid({ items }) {
  return (
    <Section id="features">
      <Head>
        <div>
          <H2>기능</H2>
          <P>데이터(`content.js`)로 섹션을 채우고, 카드 컴포넌트를 재사용합니다.</P>
        </div>
      </Head>

      <Grid>
        {items.map((f) => (
          <Card key={f.title}>
            <CardBody>
              <CardTop>
                <CardTitle>{f.title}</CardTitle>
                <Pill>{f.tag}</Pill>
              </CardTop>
              <CardDesc>{f.desc}</CardDesc>
            </CardBody>
          </Card>
        ))}
      </Grid>
    </Section>
  );
}
```

### `src/components/Pricing.jsx`

```jsx
import styled from 'styled-components';
import { Card, CardBody, CardDesc, CardTitle, Pill } from './Card';
import { Button } from './Button';

const Section = styled.section`
  max-width: 980px;
  margin: 0 auto;
  padding: 22px 18px 0;
`;

const Head = styled.div`
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 12px;
`;

const H2 = styled.h2`
  margin: 0;
  font-size: 1.4rem;
  letter-spacing: -0.03em;
`;

const P = styled.p`
  margin: 6px 0 0;
  color: ${({ theme }) => theme.colors.muted};
`;

const Grid = styled.div`
  margin-top: 14px;
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 14px;

  @media (max-width: 860px) {
    grid-template-columns: 1fr;
  }
`;

const Price = styled.div`
  margin-top: 10px;
  font-size: 1.6rem;
  font-weight: 900;
  letter-spacing: -0.04em;
`;

const List = styled.ul`
  margin: 12px 0 0;
  padding-left: 18px;
  color: ${({ theme }) => theme.colors.muted};
  line-height: 1.7;
`;

const Accent = styled.span`
  color: ${({ theme, $accent }) => theme.colors[$accent] ?? theme.colors.primary};
`;

const Actions = styled.div`
  margin-top: 16px;
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
`;

export default function Pricing({ plans }) {
  return (
    <Section id="pricing">
      <Head>
        <div>
          <H2>요금</H2>
          <P>
            실제 결제는 없고, <code>styled-components</code>로 카드 레이아웃을 꾸미는 예시입니다.
          </P>
        </div>
      </Head>

      <Grid>
        {plans.map((plan) => (
          <Card key={plan.name} aria-label={`${plan.name} 플랜`}>
            <CardBody>
              <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: 10 }}>
                <CardTitle>{plan.name}</CardTitle>
                <Pill>추천</Pill>
              </div>
              <Price>
                <Accent $accent={plan.accent}>{plan.price}</Accent>
              </Price>
              <CardDesc>수업용 예제라서 단순한 문구만 넣었습니다.</CardDesc>
              <List>
                {plan.highlights.map((h) => (
                  <li key={h}>{h}</li>
                ))}
              </List>
              <Actions>
                <Button type="button" onClick={() => alert(`데모: ${plan.name} 선택`)}>
                  선택하기
                </Button>
                <Button as="a" href="#faq" $variant="ghost">
                  자세히
                </Button>
              </Actions>
            </CardBody>
          </Card>
        ))}
      </Grid>
    </Section>
  );
}
```

### `src/components/SiteFooter.jsx`

```jsx
import styled from 'styled-components';

const Wrap = styled.footer`
  max-width: 980px;
  margin: 0 auto;
  padding: 28px 18px 48px;
  color: ${({ theme }) => theme.colors.muted};
`;

const Hr = styled.div`
  height: 1px;
  background: ${({ theme }) => theme.colors.border};
  margin: 18px 0;
`;

const Grid = styled.div`
  display: grid;
  grid-template-columns: 1.2fr 0.8fr;
  gap: 14px;

  @media (max-width: 860px) {
    grid-template-columns: 1fr;
  }
`;

const H3 = styled.h3`
  margin: 0 0 8px;
  color: ${({ theme }) => theme.colors.text};
  font-size: 1.1rem;
  letter-spacing: -0.03em;
`;

const FAQ = styled.dl`
  margin: 0;
`;

const Q = styled.dt`
  margin-top: 10px;
  font-weight: 800;
  color: ${({ theme }) => theme.colors.text};
`;

const A = styled.dd`
  margin: 6px 0 0;
  line-height: 1.6;
`;

export default function SiteFooter() {
  return (
    <Wrap id="faq">
      <Hr />
      <Grid>
        <div>
          <H3>FAQ</H3>
          <FAQ>
            <Q>왜 styled-components를 쓰나요?</Q>
            <A>컴포넌트와 스타일을 한 파일에 묶어 재사용 단위를 깔끔하게 만들 수 있습니다.</A>
            <Q>theme은 어디서 쓰나요?</Q>
            <A>
              모든 styled 컴포넌트에서 <code>{'${({ theme }) => ...}'}</code> 형태로 색/여백을 참조합니다.
            </A>
          </FAQ>
        </div>
        <div>
          <H3>메모</H3>
          <p>
            이 프로젝트는 Vite + React 기반입니다. <code>src/styles</code>에 theme과 GlobalStyle이,
            <code>src/components</code>에 UI 컴포넌트가 있습니다.
          </p>
        </div>
      </Grid>
      <p style={{ marginTop: 18, fontSize: '0.9rem' }}>© {new Date().getFullYear()} Styled Site (demo)</p>
    </Wrap>
  );
}
```

