export default function CompareCard({
  title,
  subtitle,
  points,
  loading,
  error,
  result,
  onGet,
  onPost,
}) {
  return (
    <article className="card">
      <header>
        <h2>{title}</h2>
        <p>{subtitle}</p>
      </header>

      <ul className="points">
        {points.map((p) => (
          <li key={p}>{p}</li>
        ))}
      </ul>

      <div className="actions">
        <button type="button" onClick={onGet} disabled={loading}>
          GET
        </button>
        <button type="button" className="secondary" onClick={onPost} disabled={loading}>
          POST
        </button>
      </div>

      {loading && <p className="status loading">요청 중…</p>}
      {error && <p className="status error">{error}</p>}
      {result && (
        <pre className="result">{JSON.stringify(result, null, 2)}</pre>
      )}
    </article>
  );
}
