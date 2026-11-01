export default function ProductFilter({ items, filter }) {
  const list =
    filter === 'all' ? items : items.filter((p) => p.category === filter);
  return (
    <ul className="list">
      {list.map((p) => (
        <li key={p.id}>
          {p.name} <span className="tag">{p.category}</span>
        </li>
      ))}
    </ul>
  );
}
