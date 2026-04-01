import './DemoCard.scss';

/** 일반 SCSS: 컴포넌트 옆에 같은 이름의 .scss */
export default function DemoCard() {
  return (
    <section className="demo-card">
      <h2 className="demo-card__heading">1) 일반 SCSS (DemoCard.scss)</h2>
      <p className="demo-card__text">중첩·호버는 이 파일 안에서만 적용됩니다.</p>
    </section>
  );
}
