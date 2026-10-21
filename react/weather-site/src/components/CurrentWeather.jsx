export default function CurrentWeather({ data, clothes }) {
  if (!data) return null;
  const { current, city } = data;
  const iconUrl = `https://openweathermap.org/img/wn/${current.icon}@2x.png`;

  return (
    <section className="panel current">
      <div className="current-main">
        <img src={iconUrl} alt={current.description} width={80} height={80} />
        <div>
          <h2>{city}</h2>
          <p className="temp">{current.temp}°</p>
          <p className="desc">{current.description}</p>
        </div>
      </div>
      <ul className="meta">
        <li>체감 {current.feelsLike}°</li>
        <li>습도 {current.humidity}%</li>
        <li>바람 {current.wind} m/s</li>
      </ul>
      {clothes?.length > 0 && (
        <div className="clothes">
          <h3>오늘 옷차림 추천</h3>
          <ul>
            {clothes.map((c) => (
              <li key={c}>{c}</li>
            ))}
          </ul>
        </div>
      )}
    </section>
  );
}
