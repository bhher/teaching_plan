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
