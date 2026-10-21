const cards = [
  {
    tag: '여행',
    tagClass: 'text-teal-700',
    title: '제주 동쪽 코스',
    desc: '성산일출봉과 해변을 따라가는 하루 일정입니다.',
    grad: 'from-teal-500 to-teal-700',
    link: 'text-teal-700',
  },
  {
    tag: '개발',
    tagClass: 'text-sky-700',
    title: 'Tailwind 치트시트',
    desc: '자주 쓰는 유틸리티만 모아 둔 수업용 요약입니다.',
    grad: 'from-sky-500 to-sky-800',
    link: 'text-sky-700',
  },
  {
    tag: '디자인',
    tagClass: 'text-amber-700',
    title: '카드 UI 패턴',
    desc: '이미지 · 제목 · 본문 · CTA 한 세트로 구성합니다.',
    grad: 'from-amber-400 to-orange-600',
    link: 'text-amber-700',
  },
];

export default function Ex03Cards() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">03 · 카드 · Grid</h2>
        <p className="mt-1 text-slate-600">
          grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 · map 렌더링
        </p>
      </div>

      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        {cards.map((c) => (
          <article
            key={c.title}
            className="overflow-hidden rounded-xl bg-white shadow-sm transition hover:shadow-md"
          >
            <div className={`h-40 bg-gradient-to-br ${c.grad}`} />
            <div className="p-5">
              <span className={`text-xs font-medium ${c.tagClass}`}>{c.tag}</span>
              <h3 className="mt-1 text-lg font-bold text-slate-900">{c.title}</h3>
              <p className="mt-2 text-sm leading-relaxed text-slate-600">{c.desc}</p>
              <a href="#/" className={`mt-4 inline-block text-sm font-medium hover:underline ${c.link}`}>
                더보기 →
              </a>
            </div>
          </article>
        ))}

        <article className="flex flex-col overflow-hidden rounded-xl bg-white shadow-sm sm:col-span-2 sm:flex-row lg:col-span-3">
          <div className="h-32 shrink-0 bg-gradient-to-br from-slate-700 to-slate-900 sm:h-auto sm:w-48" />
          <div className="flex-1 p-5">
            <h3 className="text-lg font-bold">가로형 카드 (flex)</h3>
            <p className="mt-2 text-sm text-slate-600">
              sm:flex-row · col-span으로 그리드 폭을 넓힙니다.
            </p>
            <div className="mt-3 flex gap-2">
              <span className="rounded-full bg-slate-100 px-2 py-0.5 text-xs text-slate-600">flex</span>
              <span className="rounded-full bg-slate-100 px-2 py-0.5 text-xs text-slate-600">col-span</span>
            </div>
          </div>
        </article>
      </div>
    </div>
  );
}
