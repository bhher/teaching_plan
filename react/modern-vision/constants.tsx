
import { SlideItem, CardItem } from './types';

export const SLIDES: SlideItem[] = [
  {
    id: 1,
    title: "새로운 감각의 디자인",
    subtitle: "당신의 비즈니스를 혁신하는 프리미엄 솔루션",
    imageUrl: "https://picsum.photos/id/20/1920/1080",
    accentColor: "bg-blue-600"
  },
  {
    id: 2,
    title: "미래를 향한 테크놀로지",
    subtitle: "최첨단 기술로 구현하는 최상의 사용자 경험",
    imageUrl: "https://picsum.photos/id/42/1920/1080",
    accentColor: "bg-purple-600"
  },
  {
    id: 3,
    title: "창의적인 협업의 가치",
    subtitle: "팀의 잠재력을 깨우는 스마트 워크플레이스",
    imageUrl: "https://picsum.photos/id/60/1920/1080",
    accentColor: "bg-emerald-600"
  }
];

export const CARDS: CardItem[] = [
  {
    id: 1,
    category: "브랜딩",
    title: "미니멀리즘 로고 디자인",
    description: "브랜드의 본질을 담아내는 간결하고 강력한 시각적 언어",
    imageUrl: "https://picsum.photos/id/101/600/400",
    price: "₩450,000"
  },
  {
    id: 2,
    category: "웹 개발",
    title: "반응형 이커머스 솔루션",
    description: "모든 기기에서 완벽하게 작동하는 현대적인 온라인 스토어",
    imageUrl: "https://picsum.photos/id/102/600/400",
    price: "₩1,200,000"
  },
  {
    id: 3,
    category: "마케팅",
    title: "데이터 기반 콘텐츠 전략",
    description: "사용자의 행동 패턴을 분석하여 최적화된 마케팅 성과 도출",
    imageUrl: "https://picsum.photos/id/103/600/400",
    price: "₩300,000"
  },
  {
    id: 4,
    category: "UI/UX",
    title: "사용자 중심 모바일 앱",
    description: "사용자의 니즈를 정확히 파악하여 직관적인 경험 제공",
    imageUrl: "https://picsum.photos/id/104/600/400",
    price: "₩850,000"
  }
];
