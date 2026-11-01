export default function Ex01Basics() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">01 · 시작하기</h2>
        <p className="mt-1 text-slate-600">간격 · 색 · 텍스트 · 둥글기 (className)</p>
      </div>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-4 text-lg font-bold">텍스트 크기 · 굵기</h3>
        <p className="text-sm">text-sm</p>
        <p className="text-base">text-base</p>
        <p className="text-lg">text-lg</p>
        <p className="text-xl font-medium">text-xl font-medium</p>
        <p className="text-2xl font-bold">text-2xl font-bold</p>
        <p className="text-3xl font-bold text-teal-700">text-3xl font-bold text-teal-700</p>
      </section>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-4 text-lg font-bold">배경 · 글자 색</h3>
        <div className="flex flex-wrap gap-2">
          <span className="rounded bg-slate-200 px-3 py-2 text-slate-800">bg-slate-200</span>
          <span className="rounded bg-teal-500 px-3 py-2 text-white">bg-teal-500</span>
          <span className="rounded bg-sky-600 px-3 py-2 text-white">bg-sky-600</span>
          <span className="rounded bg-amber-400 px-3 py-2 text-slate-900">bg-amber-400</span>
          <span className="rounded bg-rose-500 px-3 py-2 text-white">bg-rose-500</span>
        </div>
      </section>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-4 text-lg font-bold">Padding · Margin</h3>
        <div className="space-y-2">
          <div className="border border-teal-300 bg-teal-100 p-2">p-2</div>
          <div className="border border-teal-300 bg-teal-100 p-4">p-4</div>
          <div className="border border-teal-300 bg-teal-100 px-8 py-3">px-8 py-3</div>
          <div className="mt-6 border border-sky-300 bg-sky-100 p-4">mt-6 + p-4</div>
        </div>
      </section>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-4 text-lg font-bold">둥글기 · 그림자</h3>
        <div className="flex flex-wrap items-end gap-4">
          <div className="h-16 w-16 rounded-none bg-teal-500" />
          <div className="h-16 w-16 rounded bg-teal-500" />
          <div className="h-16 w-16 rounded-lg bg-teal-500" />
          <div className="h-16 w-16 rounded-xl bg-teal-500 shadow-md" />
          <div className="h-16 w-16 rounded-full bg-teal-500 shadow-lg" />
        </div>
      </section>
    </div>
  );
}
