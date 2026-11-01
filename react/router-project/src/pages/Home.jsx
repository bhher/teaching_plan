import { Link } from 'react-router-dom';
import Slider from '../components/Slider';
import Card from '../components/Card';

const highlights = [
  {
    id: 1,
    title: 'About',
    desc: '팀과 작업 방식을 소개합니다.',
    tag: '소개',
    color: 'c1',
    to: '/about',
  },
  {
    id: 2,
    title: 'Portfolio',
    desc: '웹 · UI 프로젝트 카드 모음입니다.',
    tag: '작품',
    color: 'c2',
    to: '/portfolio',
  },
  {
    id: 3,
    title: 'Contact',
    desc: '문의와 협업 요청을 받습니다.',
    tag: '연락',
    color: 'c3',
    to: '/contact',
  },
];

export default function Home() {
  return (
    <div className="page">
      <Slider />

      <section className="section">
        <div className="section-head">
          <h2>바로가기 카드</h2>
          <p>Link로 다른 라우트로 이동합니다.</p>
        </div>
        <div className="card-grid">
          {highlights.map((item) => (
            <Card key={item.id} item={item} />
          ))}
        </div>
      </section>

      <section className="cta">
        <h2>포트폴리오를 먼저 보시겠어요?</h2>
        <Link className="btn" to="/portfolio">
          Portfolio 보기
        </Link>
      </section>
    </div>
  );
}
