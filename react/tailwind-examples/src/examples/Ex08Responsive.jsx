export default function Ex08Responsive() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">08 · 반응형</h2>
        <p className="mt-1 text-slate-600">
          모바일 우선 · sm: · md: · lg: — 창 크기를 바꿔 보세요
        </p>
      </div>

      <div className="rounded-xl bg-teal-600 p-6 text-center text-white md:bg-sky-600 lg:bg-slate-800">
        <p className="text-sm opacity-80">배경색이 브레이크포인트마다 바뀝니다</p>
        <p className="mt-2 text-xl font-bold md:hidden">기본 (~ md 미만) · teal</p>
        <p className="mt-2 hidden text-xl font-bold md:block lg:hidden">md: · sky</p>
        <p className="mt-2 hidden text-xl font-bold lg:block">lg: · slate</p>
      </div>

      <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-4">
        {['A', 'B', 'C', 'D'].map((label) => (
          <div
            key={label}
            className="rounded-xl bg-white p-6 text-center shadow-sm"
          >
            <span className="text-2xl font-bold text-teal-700">{label}</span>
            <p className="mt-2 text-xs text-slate-500">1열 → sm:2열 → lg:4열</p>
          </div>
        ))}
      </div>

      <p className="rounded-lg bg-white p-4 text-sm text-slate-600 shadow-sm">
        <span className="font-mono text-teal-700">hidden md:block</span> ·{' '}
        <span className="font-mono text-teal-700">md:hidden</span> ·{' '}
        <span className="font-mono text-teal-700">w-full md:w-1/2</span>
      </p>
    </div>
  );
}
