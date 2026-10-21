import { useState } from 'react';
import LikeJsx from './examples/LikeButton.jsx';
import LikeTsx from './examples/LikeButton.tsx';
import FilterJsx from './examples/ProductFilter.jsx';
import FilterTsx from './examples/ProductFilter.tsx';
import NameJsx from './examples/NameInput.jsx';
import NameTsx from './examples/NameInput.tsx';
import CardListJsx from './examples/CardList.jsx';
import CardListTsx from './examples/CardList.tsx';
import type { CategoryFilter, Product } from './examples/ProductFilter.tsx';
import type { CardItem } from './examples/CardList.tsx';

const items: Product[] = [
  { id: 1, name: '노트북', category: 'IT' },
  { id: 2, name: '텀블러', category: '생활' },
  { id: 3, name: '키보드', category: 'IT' },
];

const cards: CardItem[] = [
  {
    id: 1,
    title: '무선 마우스',
    desc: '조용한 클릭, 장시간 배터리',
    tag: '주변기기',
    price: 25000,
    tone: 'a',
  },
  {
    id: 2,
    title: '노트북 파우치',
    desc: '13~15인치 호환',
    tag: '생활',
    price: 18000,
    tone: 'b',
  },
  {
    id: 3,
    title: 'USB-C 허브',
    desc: 'HDMI · USB3 포트',
    tag: 'IT',
    price: 32000,
    tone: 'c',
  },
];

export default function App() {
  const [filter, setFilter] = useState<CategoryFilter>('all');
  const [selected, setSelected] = useState<string>('');

  const onSelect = (card: CardItem) => {
    setSelected(card.title);
  };

  return (
    <div className="app">
      <h1>
        JSX <span>vs</span> TSX <small>v2</small>
      </h1>
      <p className="lead">좋아요 · 필터 · input · 카드 (JSX→TSX 변환 연습)</p>

      <section>
        <h2>1. LikeButton</h2>
        <div className="cols">
          <div className="panel">
            <h3>.jsx</h3>
            <LikeJsx label="좋아요" />
          </div>
          <div className="panel">
            <h3>.tsx</h3>
            <LikeTsx label="좋아요" />
          </div>
        </div>
      </section>

      <section>
        <h2>2. ProductFilter — 유니온</h2>
        <div className="row">
          {(['all', 'IT', '생활'] as CategoryFilter[]).map((f) => (
            <button
              key={f}
              type="button"
              className={filter === f ? 'btn on' : 'btn ghost'}
              onClick={() => setFilter(f)}
            >
              {f}
            </button>
          ))}
        </div>
        <div className="cols">
          <div className="panel">
            <h3>.jsx</h3>
            <FilterJsx items={items} filter={filter} />
          </div>
          <div className="panel">
            <h3>.tsx</h3>
            <FilterTsx items={items} filter={filter} />
          </div>
        </div>
      </section>

      <section>
        <h2>3. NameInput — ChangeEvent</h2>
        <div className="cols">
          <div className="panel">
            <h3>.jsx</h3>
            <NameJsx />
          </div>
          <div className="panel">
            <h3>.tsx</h3>
            <NameTsx />
          </div>
        </div>
      </section>

      <section>
        <h2>4. CardList — JSX → TSX 변환 대상</h2>
        {selected && <p className="picked">선택: {selected}</p>}
        <div className="cols">
          <div className="panel">
            <h3>.jsx (문제)</h3>
            <CardListJsx cards={cards} onSelect={onSelect} />
          </div>
          <div className="panel">
            <h3>.tsx (정답 예시)</h3>
            <CardListTsx cards={cards} onSelect={onSelect} />
          </div>
        </div>
      </section>
    </div>
  );
}
