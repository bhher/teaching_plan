import { useState } from 'react';
import { Link } from 'react-router-dom';
import { loadPlans, removePlan } from '../utils/plans';

export default function Plans() {
  const [plans, setPlans] = useState(() => loadPlans());

  return (
    <div>
      <header className="page-head">
        <h1>내 여행 일정</h1>
        <p>localStorage에 저장됩니다. 상세 페이지에서 장소를 추가하세요.</p>
      </header>

      {plans.length === 0 ? (
        <p className="muted">저장된 일정이 없습니다.</p>
      ) : (
        <ul className="plan-list">
          {plans.map((p, idx) => (
            <li key={p.contentid}>
              <span className="order">{idx + 1}</span>
              {p.firstimage ? (
                <img src={p.firstimage} alt="" />
              ) : (
                <div className="thumb-empty" />
              )}
              <div>
                <Link to={`/place/${p.contentid}`}>
                  <strong>{p.title}</strong>
                </Link>
                <p>{p.addr1}</p>
              </div>
              <button
                type="button"
                className="ghost"
                onClick={() => setPlans(removePlan(p.contentid))}
              >
                삭제
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
