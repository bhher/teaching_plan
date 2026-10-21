export type CategoryFilter = 'all' | 'IT' | '생활';
export type Product = { id: number; name: string; category: 'IT' | '생활' };

type Props = { items: Product[]; filter: CategoryFilter };

export default function ProductFilter({ items, filter }: Props) {
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
