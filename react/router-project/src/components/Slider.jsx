import { useState, useEffect } from 'react';

const slides = [
  {
    id: 1,
    title: '웹으로 이야기를 만듭니다',
    text: 'React Router로 페이지를 연결한 미니 포트폴리오',
    tone: 'tone-a',
  },
  {
    id: 2,
    title: '카드로 작품을 보여줍니다',
    text: 'Portfolio 메뉴에서 프로젝트 카드를 확인하세요',
    tone: 'tone-b',
  },
  {
    id: 3,
    title: '연락은 Contact로',
    text: '간단한 문의 폼까지 한 프로젝트에 담았습니다',
    tone: 'tone-c',
  },
];

export default function Slider() {
  const [index, setIndex] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setIndex((prev) => (prev + 1) % slides.length);
    }, 4000);
    return () => clearInterval(timer);
  }, []);

  const go = (dir) => {
    setIndex((prev) => (prev + dir + slides.length) % slides.length);
  };

  const current = slides[index];

  return (
    <section className={`slider ${current.tone}`}>
      <div className="slider-body">
        <p className="slider-kicker">Slide {index + 1} / {slides.length}</p>
        <h2>{current.title}</h2>
        <p className="slider-text">{current.text}</p>
      </div>
      <div className="slider-controls">
        <button type="button" onClick={() => go(-1)} aria-label="이전">
          ‹
        </button>
        <div className="dots">
          {slides.map((s, i) => (
            <button
              key={s.id}
              type="button"
              className={i === index ? 'dot active' : 'dot'}
              onClick={() => setIndex(i)}
              aria-label={`${i + 1}번 슬라이드`}
            />
          ))}
        </div>
        <button type="button" onClick={() => go(1)} aria-label="다음">
          ›
        </button>
      </div>
    </section>
  );
}
