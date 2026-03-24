export default function FlashCard({ card, onFlip, onMarkKnown, onMarkUnknown }) {
  return (
    <article className="flash-card">
      <button
        type="button"
        className={`card-inner ${card.isFlipped ? 'flipped' : ''}`}
        onClick={onFlip}
      >
        <span className="card-face card-front">{card.term}</span>
        <span className="card-face card-back">{card.description}</span>
      </button>

      <div className="card-actions">
        <button type="button" className="known" onClick={onMarkKnown}>
          아는 단어
        </button>
        <button type="button" className="unknown" onClick={onMarkUnknown}>
          모르는 단어
        </button>
      </div>
    </article>
  );
}
