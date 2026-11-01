import CounterJsx from './examples/Counter.jsx';
import CounterTsx from './examples/Counter.tsx';
import UserCardJsx from './examples/UserCard.jsx';
import UserCardTsx from './examples/UserCard.tsx';

const sampleUser = {
  name: '홍길동',
  email: 'hong@example.com',
  age: 25,
};

export default function App() {
  return (
    <div className="app">
      <header className="hero">
        <p className="eyebrow">React 수업</p>
        <h1>
          JSX <span>vs</span> TSX
        </h1>
        <p className="lead">
          화면 결과는 같고, <strong>타입 검사</strong> 여부가 다릅니다. 왼쪽은{' '}
          <code>.jsx</code>, 오른쪽은 <code>.tsx</code>입니다.
        </p>
      </header>

      <section className="compare">
        <h2>1. Counter — useState</h2>
        <div className="cols">
          <article className="panel jsx">
            <h3>.jsx</h3>
            <pre className="code">{`function Counter({ label }) {
  const [count, setCount] = useState(0);
}`}</pre>
            <CounterJsx label="JSX Counter" />
          </article>
          <article className="panel tsx">
            <h3>.tsx</h3>
            <pre className="code">{`type CounterProps = { label: string };

function Counter({ label }: CounterProps) {
  const [count, setCount] = useState<number>(0);
}`}</pre>
            <CounterTsx label="TSX Counter" />
          </article>
        </div>
      </section>

      <section className="compare">
        <h2>2. UserCard — props 객체</h2>
        <div className="cols">
          <article className="panel jsx">
            <h3>.jsx</h3>
            <pre className="code">{`function UserCard({ user }) {
  return <p>{user.name}</p>;
}
// age에 문자열을 넣어도 실행 전까지 모름`}</pre>
            <UserCardJsx user={sampleUser} />
          </article>
          <article className="panel tsx">
            <h3>.tsx</h3>
            <pre className="code">{`type User = { name: string; email: string; age: number };

function UserCard({ user }: { user: User }) { ... }
// age: "25" 이면 빨간줄 / 빌드 실패`}</pre>
            <UserCardTsx user={sampleUser} />
          </article>
        </div>
      </section>

      <section className="summary">
        <h2>한눈에 비교</h2>
        <table>
          <thead>
            <tr>
              <th></th>
              <th>JSX</th>
              <th>TSX</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확장자</td>
              <td>
                <code>.jsx</code> / <code>.js</code>
              </td>
              <td>
                <code>.tsx</code> / <code>.ts</code>
              </td>
            </tr>
            <tr>
              <td>타입</td>
              <td>없음 (또는 PropTypes)</td>
              <td>TypeScript 타입</td>
            </tr>
            <tr>
              <td>오류 발견</td>
              <td>실행 시 · 테스트</td>
              <td>작성 중 · 빌드 시</td>
            </tr>
            <tr>
              <td>학습</td>
              <td>문법 부담 적음</td>
              <td>타입 문법 추가 학습</td>
            </tr>
            <tr>
              <td>화면 결과</td>
              <td colSpan={2}>동일 (브라우저에는 JS로 변환되어 실행)</td>
            </tr>
          </tbody>
        </table>
      </section>
    </div>
  );
}
