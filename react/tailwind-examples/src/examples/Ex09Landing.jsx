const features = [
  { title: '빠른 UI', desc: '유틸리티 조합으로 프로토타입 속도↑' },
  { title: '일관된 간격', desc: 'p-4 · gap-6 같은 스케일' },
  { title: '반응형 기본', desc: 'md: lg: 접두사만 붙이면 끝' },
];

export default function Ex09Landing() {
  return (
    <div className="-mx-4 overflow-hidden rounded-2xl bg-white shadow-sm sm:mx-0">
      {/* Hero */}
      <section className="bg-gradient-to-br from-slate-900 via-slate-800 to-teal-900 px-6 py-16 text-center text-white md:py-24">
        <h2 className="text-3xl font-bold md:text-5xl">미니 랜딩페이지</h2>
        <p className="mx-auto mt-4 max-w-lg text-slate-300">
          히어로 + 기능 3칸 + CTA를 한 컴포넌트에 모았습니다.
        </p>
        <div className="mt-8 flex flex-wrap justify-center gap-3">
          <a
            href="#/"
            className="rounded-lg bg-teal-500 px-5 py-2.5 font-medium text-white hover:bg-teal-400"
          >
            무료로 시작
          </a>
          <a
            href="#/"
            className="rounded-lg border border-white/30 px-5 py-2.5 font-medium text-white hover:bg-white/10"
          >
            문서 보기
          </a>
        </div>
      </section>

      {/* Features */}
      <section className="grid gap-6 px-6 py-12 sm:grid-cols-3">
        {features.map((f) => (
          <div key={f.title} className="rounded-xl border border-slate-100 p-5">
            <h3 className="font-bold text-slate-900">{f.title}</h3>
            <p className="mt-2 text-sm text-slate-600">{f.desc}</p>
          </div>
        ))}
      </section>

      {/* CTA strip */}
      <section className="flex flex-col items-center justify-between gap-4 bg-teal-50 px-6 py-8 sm:flex-row">
        <p className="font-medium text-teal-900">수업용 예제를 바로 실행해 보세요</p>
        <a
          href="#/"
          className="rounded-lg bg-teal-700 px-4 py-2 text-sm font-medium text-white hover:bg-teal-800"
        >
          npm run dev
        </a>
      </section>
    </div>
  );
}
