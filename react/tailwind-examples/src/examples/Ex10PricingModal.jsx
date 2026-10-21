import { useState } from 'react';

const plans = [
  {
    name: 'Basic',
    price: '0',
    features: ['예제 10개', 'CDN 실습', '커뮤니티'],
    highlight: false,
  },
  {
    name: 'Pro',
    price: '19',
    features: ['모든 Basic', 'Vite 템플릿', '우선 답변'],
    highlight: true,
  },
  {
    name: 'Team',
    price: '49',
    features: ['모든 Pro', '팀 좌석 5', '커스텀 테마'],
    highlight: false,
  },
];

export default function Ex10PricingModal() {
  const [open, setOpen] = useState(false);
  const [selected, setSelected] = useState(null);

  const openModal = (plan) => {
    setSelected(plan);
    setOpen(true);
  };

  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">10 · 가격표 · 모달</h2>
        <p className="mt-1 text-slate-600">useState 모달 · fixed inset-0 · z-50</p>
      </div>

      <div className="grid gap-6 md:grid-cols-3">
        {plans.map((plan) => (
          <div
            key={plan.name}
            className={`flex flex-col rounded-xl bg-white p-6 shadow-sm ${
              plan.highlight ? 'ring-2 ring-teal-500' : ''
            }`}
          >
            {plan.highlight && (
              <span className="mb-2 w-fit rounded-full bg-teal-100 px-2 py-0.5 text-xs font-medium text-teal-800">
                추천
              </span>
            )}
            <h3 className="text-lg font-bold">{plan.name}</h3>
            <p className="mt-2">
              <span className="text-3xl font-bold">${plan.price}</span>
              <span className="text-slate-500">/월</span>
            </p>
            <ul className="mt-4 flex-1 space-y-2 text-sm text-slate-600">
              {plan.features.map((f) => (
                <li key={f}>✓ {f}</li>
              ))}
            </ul>
            <button
              type="button"
              onClick={() => openModal(plan)}
              className={`mt-6 w-full rounded-lg py-2 text-sm font-medium ${
                plan.highlight
                  ? 'bg-teal-600 text-white hover:bg-teal-700'
                  : 'bg-slate-100 text-slate-800 hover:bg-slate-200'
              }`}
            >
              선택하기
            </button>
          </div>
        ))}
      </div>

      {open && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
          <button
            type="button"
            className="absolute inset-0 bg-slate-900/50"
            aria-label="닫기"
            onClick={() => setOpen(false)}
          />
          <div className="relative w-full max-w-md rounded-2xl bg-white p-6 shadow-xl">
            <h3 className="text-xl font-bold text-slate-900">{selected?.name} 플랜</h3>
            <p className="mt-2 text-slate-600">
              월 ${selected?.price} — 확인을 누르면 모달이 닫힙니다. (데모)
            </p>
            <div className="mt-6 flex justify-end gap-2">
              <button
                type="button"
                onClick={() => setOpen(false)}
                className="rounded-lg border border-slate-300 px-4 py-2 text-sm hover:bg-slate-50"
              >
                취소
              </button>
              <button
                type="button"
                onClick={() => setOpen(false)}
                className="rounded-lg bg-teal-600 px-4 py-2 text-sm font-medium text-white hover:bg-teal-700"
              >
                확인
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
