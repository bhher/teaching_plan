import { Link } from 'react-router-dom';
import { CONTENT_TYPES } from '../api/tour';

function typeName(code) {
  return CONTENT_TYPES.find((t) => t.code === String(code))?.name || '여행';
}

export default function PlaceCard({ place }) {
  const img = place.firstimage || place.firstimage2;
  return (
    <article className="place-card">
      <Link to={`/place/${place.contentid}`}>
        {img ? (
          <img src={img} alt={place.title} loading="lazy" />
        ) : (
          <div className="no-img">사진 없음</div>
        )}
        <div className="body">
          <span className="type">{typeName(place.contenttypeid)}</span>
          <h3>{place.title}</h3>
          <p>{place.addr1 || '주소 정보 없음'}</p>
        </div>
      </Link>
    </article>
  );
}
