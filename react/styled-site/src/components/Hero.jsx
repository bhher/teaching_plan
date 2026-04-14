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
