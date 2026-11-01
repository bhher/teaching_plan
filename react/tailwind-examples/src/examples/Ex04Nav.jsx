import { useState } from 'react';

export default function Ex04Nav() {
  const [open, setOpen] = useState(false);

  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">04 · 네비게이션</h2>
        <p className="mt-1 text-slate-600">flex · justify-between · md:hidden 모바일 메뉴</p>
      </div>

      <header className="overflow-hidden rounded-xl bg-white shadow-sm">
        <div className="flex items-center justify-between px-6 py-4">
          <div className="text-xl font-bold text-slate-900">Brand</div>
          <nav className="hidden gap-6 text-sm text-slate-600 md:flex">
            <a className="hover:text-teal-700" href="#/">
              홈
            </a>
            <a className="hover:text-teal-700" href="#/">
              소개
            </a>
            <a className="hover:text-teal-700" href="#/">
              문의
            </a>
          </nav>
          <button
            type="button"
            className="rounded p-2 hover:bg-slate-100 md:hidden"
            onClick={() => setOpen((v) => !v)}
            aria-label="메뉴"
          >
            {open ? '✕' : '☰'}
          </button>
        </div>
        {open && (
          <nav className="flex flex-col gap-2 border-t border-slate-100 px-6 py-3 text-sm md:hidden">
            <a className="py-1 hover:text-teal-700" href="#/">
              홈
            </a>
            <a className="py-1 hover:text-teal-700" href="#/">
              소개
            </a>
            <a className="py-1 hover:text-teal-700" href="#/">
              문의
            </a>
          </nav>
        )}
      </header>

      <p className="text-sm text-slate-500">
        창 너비를 줄이면 햄버거가 보입니다. useState로 메뉴 열고 닫기.
      </p>
    </div>
  );
}
