export type CardTone = 'a' | 'b' | 'c';

export type CardItem = {
  id: number;
  title: string;
  desc: string;
  tag: string;
  price: number;
  tone: CardTone;
};

type CardListProps = {
  cards: CardItem[];
  onSelect: (card: CardItem) => void;
};

/** TSX — CardList.jsx를 타입으로 변환한 정답 예시 */
export default function CardList({ cards, onSelect }: CardListProps) {
  return (
    <div className="card-grid">
      {cards.map((card) => (
        <article key={card.id} className="card">
          <div className={`card-thumb tone-${card.tone}`} />
          <div className="card-body">
            <span className="card-tag">{card.tag}</span>
            <h3>{card.title}</h3>
            <p>{card.desc}</p>
            <p className="card-price">{card.price.toLocaleString()}원</p>
            <button type="button" className="btn" onClick={() => onSelect(card)}>
              선택
            </button>
          </div>
        </article>
      ))}
    </div>
  );
}
