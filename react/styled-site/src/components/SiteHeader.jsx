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
