import { useState } from 'react';
import Ex01Basics from './examples/Ex01Basics';
import Ex02Buttons from './examples/Ex02Buttons';
import Ex03Cards from './examples/Ex03Cards';
import Ex04Nav from './examples/Ex04Nav';
import Ex05Form from './examples/Ex05Form';
import Ex06Hero from './examples/Ex06Hero';
import Ex07FlexGrid from './examples/Ex07FlexGrid';
import Ex08Responsive from './examples/Ex08Responsive';
import Ex09Landing from './examples/Ex09Landing';
import Ex10PricingModal from './examples/Ex10PricingModal';

const tabs = [
  { id: 1, label: '01 시작', Component: Ex01Basics },
  { id: 2, label: '02 버튼', Component: Ex02Buttons },
  { id: 3, label: '03 카드', Component: Ex03Cards },
  { id: 4, label: '04 네비', Component: Ex04Nav },
  { id: 5, label: '05 폼', Component: Ex05Form },
  { id: 6, label: '06 히어로', Component: Ex06Hero },
  { id: 7, label: '07 Flex·Grid', Component: Ex07FlexGrid },
  { id: 8, label: '08 반응형', Component: Ex08Responsive },
  { id: 9, label: '09 랜딩', Component: Ex09Landing },
  { id: 10, label: '10 가격·모달', Component: Ex10PricingModal },
];

function App() {
  const [tab, setTab] = useState(1);
  const active = tabs.find((t) => t.id === tab);
  const Active = active.Component;

  return (
    <div className="min-h-screen">
      <header className="sticky top-0 z-40 border-b border-slate-200 bg-white/90 backdrop-blur">
        <div className="mx-auto max-w-6xl px-4 py-3">
          <h1 className="text-lg font-bold text-slate-900">
            Tailwind <span className="text-teal-700">React</span> 예제
          </h1>
          <p className="text-xs text-slate-500">CDN Tailwind · className으로 유틸리티 조합</p>
          <nav className="mt-3 flex flex-wrap gap-2">
            {tabs.map((t) => (
              <button
                key={t.id}
                type="button"
                onClick={() => setTab(t.id)}
                className={`rounded-lg px-3 py-1.5 text-sm font-medium transition ${
                  tab === t.id
                    ? 'bg-teal-600 text-white'
                    : 'bg-slate-100 text-slate-700 hover:bg-slate-200'
                }`}
              >
                {t.label}
              </button>
            ))}
          </nav>
        </div>
      </header>

      <main className="mx-auto max-w-6xl px-4 py-8">
        <Active />
      </main>
    </div>
  );
}

export default App;
