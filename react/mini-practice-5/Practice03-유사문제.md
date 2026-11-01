# 미니 실습 ③ 유사 문제 — 검색 · 필터 · useMemo

**참고 예제:** `mini-practice-5/src/practices/Practice03Search.jsx`  
**핵심:** `useState` + `filter` + `useMemo` (의존성이 바뀔 때만 재계산)

---

## 예제에서 배운 패턴

```jsx
const [keyword, setKeyword] = useState('');
const [category, setCategory] = useState('전체');

const filtered = useMemo(() => {
  return list.filter((item) => {
    const keywordMatch = item.name.includes(keyword);
    const categoryMatch = category === '전체' || item.category === category;
    return keywordMatch && categoryMatch;
  });
}, [keyword, category]);
```

| 개념 | 역할 |
|------|------|
| useState | 검색어 · 카테고리 상태 |
| filter | 조건에 맞는 항목만 남김 |
| useMemo | keyword/category 변경 시에만 필터 재실행 |

---

## 문제 A. 도서 검색 ⭐⭐

**데이터**

```js
const books = [
  { id: 1, title: '자바의 정석', author: '남궁성', genre: 'IT' },
  { id: 2, title: '토비의 스프링', author: '이일민', genre: 'IT' },
  { id: 3, title: '데미안', author: '헤르만 헤세', genre: '소설' },
  { id: 4, title: '어린 왕자', author: '생텍쥐페리', genre: '소설' },
  { id: 5, title: '클린 코드', author: '로버트 마틴', genre: 'IT' },
];
```

**요구사항**

1. 제목 검색 input (`keyword`) — `title.includes(keyword)`  
2. 장르 select: `전체` / `IT` / `소설`  
3. `useMemo`로 필터 결과 계산, 의존성 `[keyword, genre]`  
4. 결과 없으면 `"검색 결과가 없습니다."`  
5. 콘솔에 `📚 도서 필터링` 로그 (의존성 바뀔 때만)

**완성 모습 예시**

```text
[ 자바          ]   장르 [ IT ▼ ]

자바의 정석 — 남궁성
클린 코드 — 로버트 마틴
```

---

## 문제 B. 학생 성적 필터 ⭐⭐

**데이터**

```js
const students = [
  { id: 1, name: '김민수', className: 'A반', score: 92 },
  { id: 2, name: '이서연', className: 'B반', score: 78 },
  { id: 3, name: '박지훈', className: 'A반', score: 85 },
  { id: 4, name: '최유진', className: 'B반', score: 95 },
  { id: 5, name: '정하늘', className: 'A반', score: 60 },
];
```

**요구사항**

1. 이름 검색 (`name.includes`)  
2. 반 선택: `전체` / `A반` / `B반`  
3. **추가 조건:** 합격만 보기 체크박스 (`score >= 70`)  
4. `useMemo` 의존성: `[keyword, className, onlyPass]`  
5. 목록에 이름 · 반 · 점수 표시  

**힌트**

```js
const passMatch = !onlyPass || student.score >= 70;
```

---

## 문제 C. 할 일 검색 · 상태 필터 ⭐⭐

**데이터**

```js
const todos = [
  { id: 1, text: 'React 복습', status: '완료' },
  { id: 2, text: 'JSP 기초 문제', status: '진행중' },
  { id: 3, text: 'Router 예제', status: '대기' },
  { id: 4, text: 'useMemo 정리', status: '완료' },
  { id: 5, text: '포트폴리오', status: '진행중' },
];
```

**요구사항**

1. 할 일 텍스트 검색  
2. 상태 select: `전체` / `대기` / `진행중` / `완료`  
3. `useMemo` + `filter`  
4. 완료 개수를 **별도 useMemo**로 계산  

```js
const doneCount = useMemo(
  () => filtered.filter((t) => t.status === '완료').length,
  [filtered]
);
```

---

## 문제 D. 메뉴판 가격 필터 ⭐⭐⭐

**데이터**

```js
const menus = [
  { id: 1, name: '아메리카노', type: '음료', price: 4500 },
  { id: 2, name: '카페라떼', type: '음료', price: 5000 },
  { id: 3, name: '치즈케이크', type: '디저트', price: 6500 },
  { id: 4, name: '크로와상', type: '디저트', price: 4000 },
  { id: 5, name: '바닐라라떼', type: '음료', price: 5500 },
];
```

**요구사항**

1. 메뉴명 검색  
2. 종류: `전체` / `음료` / `디저트`  
3. 최대 가격 입력 (숫자) — `price <= maxPrice`  
   - 입력이 비어 있으면 가격 조건 무시  
4. `useMemo` 의존성: `[keyword, type, maxPrice]`  
5. 가격은 `toLocaleString()` + `원`  

**완성 예:** 검색 `라떼`, 종류 `음료`, 최대 `5200` → 카페라떼만

---

## 문제 E. (응용) 대소문자 · 공백 무시 검색 ⭐⭐⭐

Practice03Search를 복사한 뒤 개선하세요.

**요구사항**

1. 검색 시 양쪽 공백 제거: `keyword.trim()`  
2. 대소문자 무시 (영문 상품명 추가 후 테스트)  

```js
const keywordMatch = product.name
  .toLowerCase()
  .includes(keyword.trim().toLowerCase());
```

3. 필터 결과 **건수** 표시: `검색 결과 N개`  
4. `useMemo` 안에서 `console.log`로 재계산 여부 확인  

---

## 학생용 체크리스트 (공통)

- [ ] 검색어·필터 값은 `useState`로 관리한다  
- [ ] 목록은 `filter`로 걸러낸다  
- [ ] 필터 결과는 `useMemo(..., [의존성])` 안에 둔다  
- [ ] 의존성에 쓰는 state만 넣는다 (불필요한 state X)  
- [ ] 결과 0건일 때 안내 문구를 띄운다  
- [ ] F12 콘솔로 “의존성 바뀔 때만 로그”를 확인한다  

---

## 채점 / 수업 포인트

| 문제 | 핵심 추가점 |
|------|-------------|
| A 도서 | 예제와 거의 동일 (데이터만 변경) |
| B 학생 | 체크박스 조건 추가 |
| C 할 일 | 필터 결과로 두 번째 useMemo |
| D 메뉴 | 숫자 비교 · 빈 값 처리 |
| E 개선 | trim · toLowerCase · 건수 |

**숙제 추천:** A 또는 B 하나 완성 후, 여유 있으면 D  
**복습 추천:** 예제 ③ 실행 → E로 살짝 개선
