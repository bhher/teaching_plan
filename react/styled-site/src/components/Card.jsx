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
