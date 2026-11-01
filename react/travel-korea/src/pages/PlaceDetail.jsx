import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { fetchDetail } from '../api/tour';
import { addPlan, loadPlans, removePlan } from '../utils/plans';

export default function PlaceDetail() {
  const { id } = useParams();
  const [place, setPlace] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [saved, setSaved] = useState(false);

  useEffect(() => {
    let cancelled = false;
    async function load() {
      setLoading(true);
      setError('');
      try {
        const data = await fetchDetail(id);
        if (!cancelled) {
          setPlace(data);
          setSaved(loadPlans().some((p) => String(p.contentid) === String(data.contentid)));
        }
      } catch (e) {
        if (!cancelled) setError(e.message || '상세를 불러오지 못했습니다.');
      } finally {
        if (!cancelled) setLoading(false);
      }
    }
    load();
    return () => {
      cancelled = true;
    };
  }, [id]);

  if (loading) return <p className="muted">불러오는 중…</p>;
  if (error) return <p className="error">{error}</p>;
  if (!place) return null;

  const img = place.firstimage || place.firstimage2;
  const mapUrl =
    place.mapx && place.mapy
      ? `https://map.kakao.com/link/map/${encodeURIComponent(place.title)},${place.mapy},${place.mapx}`
      : null;

  function togglePlan() {
    if (saved) {
      removePlan(place.contentid);
      setSaved(false);
    } else {
      addPlan(place);
      setSaved(true);
    }
  }

  return (
    <div className="detail">
      <Link to="/" className="back">
        ← 목록
      </Link>
      {img && <img className="hero-img" src={img} alt={place.title} />}
      <h1>{place.title}</h1>
      <p className="addr">{place.addr1 || '주소 정보 없음'}</p>
      <p className="overview">
        {place.overview
          ? place.overview.replace(/<[^>]+>/g, '')
          : '상세 설명이 없습니다.'}
      </p>
      <div className="actions">
        <button type="button" onClick={togglePlan}>
          {saved ? '일정에서 제거' : '여행 일정에 저장'}
        </button>
        {mapUrl && (
          <a className="map-link" href={mapUrl} target="_blank" rel="noreferrer">
            지도에서 보기
          </a>
        )}
      </div>
    </div>
  );
}
