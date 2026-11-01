import { useState } from 'react';
import { getWithAjax, postWithAjax } from './api/withAjax';
import { getWithAxios, postWithAxios } from './api/withAxios';
import { getWithFetch, postWithFetch } from './api/withFetch';
import CompareCard from './components/CompareCard';
import CompareTable from './components/CompareTable';

const INITIAL = {
  fetch: { loading: false, error: '', result: null },
  axios: { loading: false, error: '', result: null },
  ajax: { loading: false, error: '', result: null },
};

export default function App() {
  const [state, setState] = useState(INITIAL);

  async function run(key, fn) {
    setState((s) => ({
      ...s,
      [key]: { loading: true, error: '', result: null },
    }));
    try {
      const result = await fn();
      setState((s) => ({
        ...s,
        [key]: { loading: false, error: '', result },
      }));
    } catch (e) {
      setState((s) => ({
        ...s,
        [key]: {
          loading: false,
          error: e.message || '요청 실패',
          result: null,
        },
      }));
    }
  }

  return (
    <div className="app">
      <header className="hero">
        <p className="eyebrow">HTTP 클라이언트 비교</p>
        <h1>fetch · axios · ajax</h1>
        <p className="lead">
          같은 API(<code>jsonplaceholder.typicode.com/posts</code>)를 세 방식으로
          호출해 보세요. 코드는 <code>src/api/</code>에 나란히 있습니다.
        </p>
      </header>

      <div className="grid">
        <CompareCard
          title="fetch"
          subtitle="브라우저 내장 Fetch API"
          points={[
            '설치 불필요',
            'res.json() 직접 호출',
            '4xx/5xx는 직접 if (!res.ok) 처리',
          ]}
          {...state.fetch}
          onGet={() => run('fetch', getWithFetch)}
          onPost={() => run('fetch', postWithFetch)}
        />

        <CompareCard
          title="axios"
          subtitle="npm 라이브러리"
          points={[
            'data가 이미 JSON',
            '에러 시 자동 throw',
            'params / 인터셉터 편리',
          ]}
          {...state.axios}
          onGet={() => run('axios', getWithAxios)}
          onPost={() => run('axios', postWithAxios)}
        />

        <CompareCard
          title="ajax (XHR)"
          subtitle="XMLHttpRequest (고전 AJAX)"
          points={[
            '콜백 기반 원형',
            'Promise로 감싸서 사용 가능',
            'jQuery $.ajax의 기반',
          ]}
          {...state.ajax}
          onGet={() => run('ajax', getWithAjax)}
          onPost={() => run('ajax', postWithAjax)}
        />
      </div>

      <CompareTable />

      <section className="code-hint">
        <h2>코드 위치</h2>
        <ul>
          <li>
            <code>src/api/withFetch.js</code> — fetch GET/POST
          </li>
          <li>
            <code>src/api/withAxios.js</code> — axios GET/POST
          </li>
          <li>
            <code>src/api/withAjax.js</code> — XMLHttpRequest GET/POST
          </li>
        </ul>
      </section>
    </div>
  );
}
