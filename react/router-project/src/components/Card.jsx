import { Link } from 'react-router-dom';

export default function Card({ item }) {
  return (
    <article className="card">
      <div className={`card-thumb ${item.color}`} />
      <div className="card-body">
        <span className="card-tag">{item.tag}</span>
        <h3>{item.title}</h3>
        <p>{item.desc}</p>
        {item.to && (
          <Link className="card-link" to={item.to}>
            자세히 →
          </Link>
        )}
      </div>
    </article>
  );
}
