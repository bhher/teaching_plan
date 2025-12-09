## 6차시 – 상태 공유 & Lifting State Up

### 0. 학습 목표

- React의 **단방향 데이터 흐름**을 설명할 수 있다.
- 형제 컴포넌트 간 데이터 공유를 위해 **상태를 상위로 끌어올리는(Lifting State Up)** 패턴을 사용할 수 있다.
- 검색바 + 리스트 구조를 설계·구현할 수 있다.

---

### 1. 이론 (슬라이드 흐름)

#### 1) 단방향 데이터 흐름

- React의 기본 철학:
  - 데이터는 **위에서 아래로(부모 → 자식)** 흐른다.
  - 자식이 부모에게 무언가를 알려야 할 때는, **부모가 넘겨준 콜백 함수를 호출**한다.

도식:

```txt
App(state)
 ├─ SearchBar (props: searchText, onChange)
 └─ FilteredList (props: items, searchText)
```

#### 2) 형제 컴포넌트 간 데이터 공유 문제

- SearchBar → FilteredList로 직접 전달할 수 없음
- 해결 방법:
  - 상태를 형제의 공통 부모(App)로 올린 후,
  - App이 각각의 자식에게 props로 나눠줌

이 과정을 **Lifting State Up**이라고 부른다.

---

### 2. 실습 – 검색바 + 필터 리스트

#### 2.1 실습 목표

- 상단 검색창에서 입력한 텍스트에 따라,
  - 하단 리스트가 실시간으로 필터링되도록 구현

#### 2.2 상태 설계

- App 컴포넌트:

```jsx
const [searchText, setSearchText] = useState('');
const [items] = useState(['React', 'JavaScript', 'TypeScript', 'HTML', 'CSS']);
```

- `SearchBar`:
  - props: `searchText`, `onChange`
- `FilteredList`:
  - props: `items`, `searchText`

---

### 3. 코드 스켈레톤

#### 3.1 `SearchBar.jsx`

```jsx
// src/SearchBar.jsx
function SearchBar({ searchText, onChange }) {
  return (
    <div>
      <input
        value={searchText}
        onChange={(e) => onChange(e.target.value)}
        placeholder="검색어를 입력하세요"
      />
    </div>
  );
}

export default SearchBar;
```

#### 3.2 `FilteredList.jsx`

```jsx
// src/FilteredList.jsx
function FilteredList({ items, searchText }) {
  const filtered = items.filter((item) =>
    item.toLowerCase().includes(searchText.toLowerCase())
  );

  if (filtered.length === 0) {
    return <p>검색 결과가 없습니다.</p>;
  }

  return (
    <ul>
      {filtered.map((item) => (
        <li key={item}>{item}</li>
      ))}
    </ul>
  );
}

export default FilteredList;
```

#### 3.3 `App.jsx`

```jsx
// src/App.jsx
import { useState } from 'react';
import SearchBar from './SearchBar.jsx';
import FilteredList from './FilteredList.jsx';

const initialItems = ['React', 'JavaScript', 'TypeScript', 'HTML', 'CSS'];

function App() {
  const [searchText, setSearchText] = useState('');

  return (
    <div>
      <h1>6차시: 상태 끌어올리기 (Lifting State Up)</h1>
      <SearchBar searchText={searchText} onChange={setSearchText} />
      <FilteredList items={initialItems} searchText={searchText} />
    </div>
  );
}

export default App;
```

---

### 4. 과제

1. **카테고리 필터 추가**
   - 상단에 `<select>` 추가:
     - “전체”, “Frontend”, “Backend” 등 카테고리
   - `items` 배열을 `{ name, category }` 형태로 바꾼 뒤,
     - 검색어 + 카테고리 조건을 모두 만족하는 항목만 필터링

2. **필터 요약 표시 컴포넌트**
   - 예: `<FilterSummary />` 컴포넌트 생성
   - 예시 문구:
     - 검색어가 없을 때: “현재 검색어: (없음)”
     - 검색어가 있을 때: “현재 검색어: React / 결과 3개”
   - `searchText`와 `filtered.length`를 props로 받아서 표시

> 다음 차시 예고:  
> “다음 시간에는 여러 페이지를 가진 것처럼 보이는 SPA를 만들기 위해  
> **React Router**를 사용해 `/`, `/todos`, `/about`와 같은 라우팅을 설정해 봅니다.”




