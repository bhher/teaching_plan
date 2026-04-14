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
