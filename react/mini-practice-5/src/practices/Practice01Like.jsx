import { useState } from 'react';

/** ① 좋아요 버튼 — useState, 이벤트, 조건부 렌더링 */
export default function Practice01Like() {
  const [liked, setLiked] = useState(false);
  const [count, setCount] = useState(12);

  const handleLike = () => {
    if (liked) {
      setCount(count - 1);
    } else {
      setCount(count + 1);
    }
    setLiked(!liked);
  };

  return (
    <div className="panel">
      <h2>① ❤️ 좋아요 버튼</h2>
      <p className="goal">
        학습: useState · 클릭 이벤트 · 조건부 렌더링 · 삼항 연산자
      </p>

      <h3 style={{ marginBottom: 12 }}>React 공부하기</h3>
      <p style={{ marginBottom: 16, fontSize: '1.1rem' }}>
        ❤️ 좋아요 {count}개
      </p>

      <button type="button" className="btn" onClick={handleLike}>
        {liked ? '🤍 좋아요 취소' : '❤️ 좋아요'}
      </button>

      <div className="hint">
        실습: ① 클릭 시 증가 ② 다시 클릭 시 감소 ③ 상태에 따라 버튼 글자 변경
      </div>
    </div>
  );
}
