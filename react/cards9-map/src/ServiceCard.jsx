/** 원본: Bootstrap className + map */
export default function ServiceCard({ card }) {
  return (
    <div className="card h-100 shadow-sm border-0">
      <div className={`card-thumb ${card.bg}`}>
        <i className={`bi ${card.icon}`} />
      </div>
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
