export default function Ex06Hero() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">06 · 히어로</h2>
        <p className="mt-1 text-slate-600">min-h · flex 가운데 · gradient · 반응형 글자 크기</p>
      </div>

      <section className="flex min-h-[70vh] flex-col items-center justify-center rounded-2xl bg-gradient-to-br from-slate-900 to-slate-700 px-4 text-center text-white">
        <p className="text-sm font-medium tracking-wide text-teal-300">TAILWIND × REACT</p>
        <h3 className="mt-3 text-4xl font-bold md:text-6xl">안녕하세요</h3>
        <p className="mt-4 max-w-xl text-lg text-slate-300">
          짧은 소개 문구를 넣습니다. text-4xl md:text-6xl 로 화면 크기에 따라 커집니다.
        </p>
        <a
          href="#/"
          className="mt-8 rounded-lg bg-white px-6 py-3 font-semibold text-slate-900 transition hover:bg-slate-100"
        >
          시작하기
        </a>
      </section>
    </div>
  );
}
