## 10차시 – 종합 프로젝트 & 리팩터링

### 0. 학습 목표

- 지금까지 배운 내용을 하나의 **SPA 프로젝트**에 통합한다.
- 컴포넌트 구조, 폴더 구조, 상태 설계를 스스로 결정할 수 있다.
- 간단한 리팩터링 포인트를 찾아 개선해 본다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 프로젝트 목표 정리

- 규모: **소규모 대시보드형 SPA**
  - 홈 대시보드
  - ToDo 관리 페이지
  - 게시글 리스트/상세 페이지
  - (선택) 테마, 로그인 Context 적용

#### 2) 폴더 구조 예시

```txt
src/
  api/
    posts.js
  components/
    Layout.jsx
    Header.jsx
    Footer.jsx
  contexts/
    ThemeContext.jsx
  hooks/
    useFetch.js
  pages/
    HomePage.jsx
    TodoPage.jsx
    PostsPage.jsx
    PostDetailPage.jsx
  App.jsx
  main.jsx
```

> 강의 멘트 예시  
> “이 구조는 정답이 아닙니다. 프로젝트가 커질수록  
> **기능별**, **도메인별**로 나누는 등 다양한 구조를 시도해 볼 수 있습니다.”

#### 3) 리팩터링 관점

- 중복된 코드 제거:
  - 비슷한 버튼, 카드 → 공통 컴포넌트로 묶기
- 상태 최소화:
  - 꼭 필요한 컴포넌트에만 state 두기
- 명확한 이름:
  - 컴포넌트/함수/변수 이름에서 “역할”이 보이도록

---

### 2. 종합 프로젝트 – 예시 요구사항

#### 2.1 페이지 구성

1. **홈 페이지 (`/`)**
   - 오늘 해야 할 일 개수
   - 최근 게시글 3개 제목
   - 간단한 인사말

2. **ToDo 페이지 (`/todos`)**
   - 5차시~6차시에서 만든 ToDo 기능:
     - 추가/삭제/완료 토글
     - (선택) 검색/필터

3. **게시글 페이지 (`/posts`)**
   - 게시글 리스트 (8차시 API 연동)
   - 각 글 제목 클릭 시 상세 페이지(`/posts/:id`)로 이동

4. **게시글 상세 페이지 (`/posts/:id`)**
   - 선택한 게시글의 제목 + 내용(body) 표시

#### 2.2 공통 레이아웃

- 헤더:
  - 사이트 제목
  - 네비게이션 링크: 홈 / ToDo / Posts
  - (선택) 테마 전환 버튼
- 푸터:
  - 간단한 카피 문구

---

### 3. 코드 스켈레톤 (일부 예시)

#### 3.1 `Layout.jsx`

```jsx
// src/components/Layout.jsx
import { Link } from 'react-router-dom';

function Layout({ children }) {
  return (
    <div>
      <header
        style={{
          padding: '10px 20px',
          background: '#667eea',
          color: 'white',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
        }}
      >
        <div>React 종합 프로젝트</div>
        <nav>
          <Link to="/" style={{ color: 'white', marginRight: 12 }}>홈</Link>
          <Link to="/todos" style={{ color: 'white', marginRight: 12 }}>할 일</Link>
          <Link to="/posts" style={{ color: 'white' }}>게시글</Link>
        </nav>
      </header>

      <main style={{ padding: '20px' }}>{children}</main>

      <footer style={{ padding: '10px 20px', background: '#f5f5f5', marginTop: '40px' }}>
        © 2025 React Course
      </footer>
    </div>
  );
}

export default Layout;
```

#### 3.2 `App.jsx`

```jsx
// src/App.jsx
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout.jsx';
import HomePage from './pages/HomePage.jsx';
import TodoPage from './pages/TodoPage.jsx';
import PostsPage from './pages/PostsPage.jsx';
import PostDetailPage from './pages/PostDetailPage.jsx';

function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/todos" element={<TodoPage />} />
        <Route path="/posts" element={<PostsPage />} />
        <Route path="/posts/:id" element={<PostDetailPage />} />
      </Routes>
    </Layout>
  );
}

export default App;
```

#### 3.3 `HomePage.jsx` (예시)

```jsx
// src/pages/HomePage.jsx
function HomePage() {
  return (
    <div>
      <h1>환영합니다 👋</h1>
      <p>이 프로젝트는 React 18 입문~중급 과정을 마무리하는 종합 예제입니다.</p>
      <ul>
        <li>할 일 페이지에서 오늘 할 일을 관리해 보세요.</li>
        <li>게시글 페이지에서 외부 API 데이터를 확인해 보세요.</li>
      </ul>
    </div>
  );
}

export default HomePage;
```

나머지 `TodoPage`, `PostsPage`, `PostDetailPage`는 이전 차시에서 만든 내용들을 조합해서 구성.

---

### 4. 수업 진행 아이디어

1. **초반 10~15분**
   - 전체 요구사항 다시 정리
   - 각자 또는 팀별로 어떤 주제로 만들지 짧게 기획 시간

2. **중간 60분**
   - 코드 작성 시간
   - 강사는 순회하며 질문/구조 설계 도움

3. **마지막 20~30분**
   - 2~3팀(또는 몇 명)의 코드를 화면에 띄워 간단 코드 리뷰
   - “잘한 점” + “개선하면 좋을 점”을 중심으로 피드백

---

### 5. 최종 과제 (개인 또는 팀)

**자유 주제 SPA 만들기**

- 예시 주제:
  - 영화 목록 뷰어
  - 책/도서 목록 관리
  - 강의/수업 관리 대시보드
  - 간단한 블로그

**필수 조건(예시)**:

- 페이지 2개 이상 (React Router 사용)
- `useState`, `useEffect` 사용
- Custom Hook 1개 이상
- Context 1개 이상 또는 상위 상태 공유(Lifting State Up) 사용

**README에 포함할 내용:**

- 프로젝트 소개(한 문단)
- 사용한 기술 스택 (React 18, Vite, react-router-dom 등)
- 폴더 구조 설명
- “이번 과정에서 내가 배운 점” 3가지
- “다음에 개선하고 싶은 점” 2가지

---

### 6. 마무리 멘트 예시

> “오늘 만든 프로젝트가 완벽할 필요는 없습니다.  
> 중요한 건, 여러분이 **React의 전체 흐름을 한 번 다 경험했다**는 점입니다.  
> 이제는 공식 문서와 다양한 예제를 참고하면서, 스스로 기능을 추가하고 수정해 보는 연습을 계속해 보세요.”



