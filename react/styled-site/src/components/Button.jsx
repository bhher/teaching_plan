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
