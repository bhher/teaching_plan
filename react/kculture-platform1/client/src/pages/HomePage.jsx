import { useEffect, useState } from 'react';
import { Link, useSearchParams } from 'react-router-dom';
import { api } from '../api/client';

export default function HomePage() {
  const [searchParams, setSearchParams] = useSearchParams();
  const categoryId = Number(searchParams.get('categoryId')) || 0;
  const page = Math.max(1, Number(searchParams.get('page')) || 1);

  const [categories, setCategories] = useState([]);
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    api.getCategories().then(setCategories).catch((e) => setError(e.message));
  }, []);

  useEffect(() => {
    setError(null);
    api
      .getPosts({ categoryId: categoryId || undefined, page })
      .then(setData)
      .catch((e) => setError(e.message));
  }, [categoryId, page]);

  const setCategory = (id) => {
    const next = new URLSearchParams(searchParams);
    if (id) next.set('categoryId', String(id));
    else next.delete('categoryId');
    next.set('page', '1');
    setSearchParams(next);
  };

  const goPage = (p) => {
    const next = new URLSearchParams(searchParams);
    next.set('page', String(p));
    setSearchParams(next);
  };

  if (error && !data) {
    return <p className="error">{error}</p>;
  }

  return (
    <div>
      <h1>게시글 목록</h1>
      <p className="hint">JSP의 list.do → <code>/</code> · 카테고리·페이지는 쿼리스트링</p>

      <div className="filters">
        <button
          type="button"
          className={categoryId === 0 ? 'active' : ''}
          onClick={() => setCategory(0)}
        >
          전체
        </button>
        {categories.map((c) => (
          <button
            key={c.id}
            type="button"
            className={categoryId === c.id ? 'active' : ''}
            onClick={() => setCategory(c.id)}
          >
            {c.icon} {c.nameEn}
          </button>
        ))}
      </div>

      {error && <p className="error">{error}</p>}

      {!data ? (
        <p>불러오는 중…</p>
      ) : (
        <>
          <p className="meta">
            총 {data.total}건 · {data.page}/{data.totalPages} 페이지
          </p>
          <ul className="post-list">
            {data.posts.length === 0 ? (
              <li>글이 없습니다.</li>
            ) : (
              data.posts.map((p) => (
                <li key={p.id} className="post-row">
                  {p.imageFilename ? (
                    <Link to={`/posts/${p.id}`} className="thumb-link">
                      <img src={`/uploads/${p.imageFilename}`} alt="" className="list-thumb" />
                    </Link>
                  ) : null}
                  <div className="post-row-text">
                    <Link to={`/posts/${p.id}`}>
                      <span className="cat">{p.categoryIcon}</span> {p.title}
                    </Link>
                    <span className="sub">
                      {p.memberName} · 조회 {p.viewCount}
                    </span>
                  </div>
                </li>
              ))
            )}
          </ul>
          <div className="pager">
            <button type="button" disabled={page <= 1} onClick={() => goPage(page - 1)}>
              이전
            </button>
            <span>
              {page} / {data.totalPages}
            </span>
            <button
              type="button"
              disabled={page >= data.totalPages}
              onClick={() => goPage(page + 1)}
            >
              다음
            </button>
          </div>
        </>
      )}
    </div>
  );
}
