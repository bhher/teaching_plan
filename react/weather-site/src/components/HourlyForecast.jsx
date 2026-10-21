function formatHour(timeStr) {
  const part = timeStr.slice(11, 16);
  return part || timeStr;
}

export default function HourlyForecast({ items }) {
  if (!items?.length) return null;
  return (
    <section className="panel">
      <h3>시간별 예보</h3>
      <div className="scroll-row">
        {items.map((item) => (
          <div className="chip" key={item.time}>
            <span className="chip-time">{formatHour(item.time)}</span>
            <img
              src={`https://openweathermap.org/img/wn/${item.icon}.png`}
              alt={item.description}
              width={40}
              height={40}
            />
            <strong>{item.temp}°</strong>
          </div>
        ))}
      </div>
    </section>
  );
}
