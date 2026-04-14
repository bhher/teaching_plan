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
