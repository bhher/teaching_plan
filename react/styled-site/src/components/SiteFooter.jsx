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
