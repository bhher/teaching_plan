export default function Ex02Buttons() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">02 · 버튼 · 뱃지 · 알림</h2>
        <p className="mt-1 text-slate-600">hover: · focus:ring · disabled</p>
      </div>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-4 text-lg font-bold">버튼</h3>
        <div className="flex flex-wrap gap-3">
          <button
            type="button"
            className="rounded-lg bg-teal-600 px-4 py-2 text-sm font-medium text-white transition hover:bg-teal-700 focus:outline-none focus:ring-2 focus:ring-teal-400 focus:ring-offset-2"
          >
            Primary
          </button>
          <button
            type="button"
            className="rounded-lg bg-sky-600 px-4 py-2 text-sm font-medium text-white transition hover:bg-sky-700"
          >
            Secondary
          </button>
          <button
            type="button"
            className="rounded-lg border border-slate-300 px-4 py-2 text-sm font-medium text-slate-700 transition hover:bg-slate-50"
          >
            Outline
          </button>
          <button
            type="button"
            className="rounded-lg bg-rose-500 px-4 py-2 text-sm font-medium text-white transition hover:bg-rose-600"
          >
            Danger
          </button>
          <button
            type="button"
            disabled
            className="cursor-not-allowed rounded-lg bg-slate-200 px-4 py-2 text-sm font-medium text-slate-400"
          >
            Disabled
          </button>
          <button
            type="button"
            className="rounded-full bg-teal-600 px-6 py-3 text-base font-semibold text-white shadow-md transition hover:bg-teal-700"
          >
            Pill Large
          </button>
        </div>
        <p className="mt-4 font-mono text-xs text-slate-500">
          className=&quot;px-4 py-2 bg-teal-600 text-white rounded-lg hover:bg-teal-700&quot;
        </p>
      </section>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-4 text-lg font-bold">뱃지</h3>
        <div className="flex flex-wrap gap-2">
          <span className="inline-flex items-center rounded-full bg-emerald-100 px-2.5 py-0.5 text-xs font-medium text-emerald-800">
            활성
          </span>
          <span className="inline-flex items-center rounded-full bg-amber-100 px-2.5 py-0.5 text-xs font-medium text-amber-800">
            대기
          </span>
          <span className="inline-flex items-center rounded-full bg-rose-100 px-2.5 py-0.5 text-xs font-medium text-rose-800">
            오류
          </span>
          <span className="inline-flex items-center rounded-full bg-sky-100 px-2.5 py-0.5 text-xs font-medium text-sky-800">
            정보
          </span>
          <span className="inline-flex items-center rounded-full bg-slate-800 px-2.5 py-0.5 text-xs font-medium text-white">
            NEW
          </span>
        </div>
      </section>

      <section className="space-y-3">
        <h3 className="text-lg font-bold">알림</h3>
        <div className="rounded-lg border border-emerald-200 bg-emerald-50 p-4 text-sm text-emerald-800">
          저장되었습니다.
        </div>
        <div className="rounded-lg border border-amber-200 bg-amber-50 p-4 text-sm text-amber-800">
          주의: 저장하지 않은 변경사항이 있습니다.
        </div>
        <div className="rounded-lg border border-rose-200 bg-rose-50 p-4 text-sm text-rose-800">
          오류: 이메일 형식을 확인해 주세요.
        </div>
      </section>
    </div>
  );
}
