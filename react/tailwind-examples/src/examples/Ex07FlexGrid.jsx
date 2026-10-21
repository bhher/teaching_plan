export default function Ex07FlexGrid() {
  return (
    <div className="space-y-8">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">07 · Flex · Grid 비교</h2>
        <p className="mt-1 text-slate-600">같은 3칸을 flex와 grid로 배치</p>
      </div>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-3 font-bold">Flex · gap-4</h3>
        <div className="flex flex-wrap gap-4">
          <div className="flex-1 rounded-lg bg-teal-100 p-4 text-center min-w-[120px]">A</div>
          <div className="flex-1 rounded-lg bg-teal-200 p-4 text-center min-w-[120px]">B</div>
          <div className="flex-1 rounded-lg bg-teal-300 p-4 text-center min-w-[120px]">C</div>
        </div>
        <p className="mt-3 font-mono text-xs text-slate-500">flex flex-wrap gap-4 · flex-1</p>
      </section>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-3 font-bold">Flex · justify-between · items-center</h3>
        <div className="flex items-center justify-between rounded-lg bg-slate-50 p-4">
          <span className="font-medium">왼쪽</span>
          <span className="rounded-full bg-teal-600 px-3 py-1 text-xs text-white">가운데 뱃지</span>
          <button type="button" className="rounded bg-slate-800 px-3 py-1.5 text-sm text-white">
            오른쪽
          </button>
        </div>
      </section>

      <section className="rounded-xl bg-white p-6 shadow-sm">
        <h3 className="mb-3 font-bold">Grid · grid-cols-3</h3>
        <div className="grid grid-cols-3 gap-4">
          <div className="rounded-lg bg-sky-100 p-4 text-center">1</div>
          <div className="rounded-lg bg-sky-200 p-4 text-center">2</div>
          <div className="rounded-lg bg-sky-300 p-4 text-center">3</div>
          <div className="col-span-2 rounded-lg bg-sky-400 p-4 text-center text-white">col-span-2</div>
          <div className="rounded-lg bg-sky-500 p-4 text-center text-white">5</div>
        </div>
        <p className="mt-3 font-mono text-xs text-slate-500">grid grid-cols-3 gap-4 · col-span-2</p>
      </section>
    </div>
  );
}
