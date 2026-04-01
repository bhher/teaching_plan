import DemoCard from './components/DemoCard/DemoCard';
import ModuleDemo from './components/ModuleDemo/ModuleDemo';
import './App.scss';

export default function App() {
  return (
    <div className="app">
      <h1 className="title">SCSS + React 예제</h1>
      <p className="lead">
        <code>App.scss</code> — 변수·중첩·믹스인 / <code>DemoCard.scss</code> — 컴포넌트 분리 /{' '}
        <code>*.module.scss</code> — CSS Module
      </p>

      <div className="grid">
        <DemoCard />
        <ModuleDemo />
      </div>
    </div>
  );
}
