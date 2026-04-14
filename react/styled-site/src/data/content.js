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
