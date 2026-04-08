# `GET /api/posts` — 게시글 목록 API 설명 (`index.js` 109~162행)

`server/index.js`에서 **게시글 목록**을 반환하는 부분입니다.

---

## 1. SELECT 컬럼 정의 (재사용용)

```js
const POST_SELECT_LIST = `...`;
const POST_SELECT_DETAIL = `...`;
```

**의미:** SQL에서 가져올 컬럼들을 미리 정의해 둔 것.

예:

- `p.id`
- `p.title`
- `m.name AS memberName`
- `c.name_en AS categoryName`

**왜 이렇게 하냐?**

- 코드 중복 방지
- 유지보수 편함

| 상수 | 용도 |
|------|------|
| **LIST** | 목록용 |
| **DETAIL** | 상세 페이지용 (`nationality` 등 추가됨) |

---

## 2. API 엔드포인트

```js
app.get('/api/posts', async (req, res) => {
```

**GET** 요청으로 **게시글 목록**을 가져오는 API.

---

## 3. 요청 파라미터 처리

```js
const categoryId = Number(req.query.categoryId) || 0;
const page = Math.max(1, Number(req.query.page) || 1);
const pageSize = Math.min(50, Math.max(1, Number(req.query.pageSize) || 10));
```

| 값 | 의미 |
|----|------|
| `categoryId` | 카테고리 필터 (0이면 전체) |
| `page` | 현재 페이지 |
| `pageSize` | 한 페이지 글 개수 |

**안전하게 처리:**

- 최소값 보장
- 최대값 제한 (`pageSize` 최대 50)

---

## 4. 페이징 계산

```js
const start = (page - 1) * pageSize;
```

**SQL `LIMIT`용 시작 위치.**

예:

- `page = 1` → `start = 0`
- `page = 2`, `pageSize = 10` → `start = 10`

---

## 5. SQL 분기 (핵심)

### 카테고리가 선택된 경우 (`categoryId > 0`)

```sql
WHERE p.category_id = ?
```

→ **특정 카테고리만** 조회.

### 전체 조회 (`else`)

→ `WHERE` 없음 → **전체 글**.

---

## 6. JOIN 구조 (중요)

```sql
FROM post p
JOIN member m ON p.member_id = m.id
JOIN category c ON p.category_id = c.id
```

**의미:** 글 + 작성자 + 카테고리를 **한 번에** 가져옴.

| 테이블 | 역할 |
|--------|------|
| `post` | 글 |
| `member` | 작성자 |
| `category` | 카테고리 |

---

## 7. 실제 데이터 조회

```js
const [list] = await pool.query(listSql, params);
```

→ **글 목록** 가져오기.

---

## 8. 전체 개수 조회 (페이징용)

```js
const [countRows] = ...
```

→ **총 글 개수** (필터 적용 후 기준).

---

## 9. 총 페이지 계산

```js
const totalPages = Math.max(1, Math.ceil(total / pageSize));
```

예: 글 23개 / 페이지당 10개 → **3페이지**.

---

## 10. 응답 반환

```js
res.json({
  posts: list,
  total,
  page,
  pageSize,
  totalPages,
  categoryId,
});
```

클라이언트(React)가 받는 데이터 예:

```json
{
  "posts": [],
  "total": 100,
  "page": 1,
  "pageSize": 10,
  "totalPages": 10,
  "categoryId": 0
}
```

---

## 핵심 정리

이 코드는:

- **카테고리 필터** 가능
- **페이지네이션** 지원
- **JOIN**으로 글·작성자·카테고리를 합쳐서 가져옴
- **목록 + 전체 개수**를 같이 반환

관련 코드: `react/kculture-platform1/server/index.js` (109~162행)
