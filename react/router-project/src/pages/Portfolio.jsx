import Card from '../components/Card';

const works = [
  {
    id: 1,
    title: '감정 다이어리',
    desc: '일기 작성 · 감정 선택 UI 연습 프로젝트',
    tag: 'React',
    color: 'c1',
  },
  {
    id: 2,
    title: 'Todo 앱',
    desc: '추가 · 완료 · 삭제와 필터 실습',
    tag: 'State',
    color: 'c2',
  },
  {
    id: 3,
    title: 'Tailwind 예제',
    desc: '유틸리티 클래스로 버튼·카드·폼 구성',
    tag: 'CSS',
    color: 'c3',
  },
  {
    id: 4,
    title: 'Router Basic',
    desc: 'Link · useParams · useNavigate 기초',
    tag: 'Router',
    color: 'c4',
  },
  {
    id: 5,
    title: '검색 필터',
    desc: 'useMemo로 상품/도서 필터링',
    tag: 'Hooks',
    color: 'c1',
  },
  {
    id: 6,
    title: '미니 랜딩',
    desc: '히어로 · CTA · 반응형 레이아웃',
    tag: 'UI',
    color: 'c2',
  },
];

export default function Portfolio() {
  return (
    <div className="page">
      <p className="eyebrow">Portfolio</p>
      <h1>프로젝트 카드</h1>
      <p className="lead">map으로 Card 컴포넌트를 반복 렌더링합니다.</p>

      <div className="card-grid">
        {works.map((item) => (
          <Card key={item.id} item={item} />
        ))}
      </div>
    </div>
  );
}
