/** CDN Bootstrap — className + 사진 */
export default function PhotoCard({ card }) {
  return (
    <div className="card h-100 shadow-sm border-0">
      <img src={card.img} className="card-img-top" alt={card.title} />
      <div className="card-body">
        <h5 className="card-title">{card.title}</h5>
        <p className="card-text text-muted small">{card.text}</p>
        <a href="#" className={`btn btn-sm ${card.btn}`}>
          자세히
        </a>
      </div>
    </div>
  )
}
