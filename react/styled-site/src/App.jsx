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
