/** JSX — 카드 목록 (타입 없음). 학생에게 먼저 주고 버전 */
export default function CardList({ cards, onSelect }) {
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
