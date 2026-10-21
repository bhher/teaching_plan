export default function DailyForecast({ items }) {
  if (!items?.length) return null;
  return (
    <section className="panel">
      <h3>주간 예보</h3>
      <ul className="daily-list">
        {items.map((d) => (
          <li key={d.date}>
            <span className="day">{d.date}</span>
            <img
              src={`https://openweathermap.org/img/wn/${d.icon}.png`}
              alt={d.description}
              width={36}
              height={36}
            />
            <span className="desc">{d.description}</span>
            <span className="range">
              {d.min}° / {d.max}°
            </span>
          </li>
        ))}
      </ul>
    </section>
  );
}
