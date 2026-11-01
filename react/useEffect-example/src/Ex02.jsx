import { useState, useEffect } from 'react'

/**
 * Ex02 — 의존성 배열 [count] → 처음 + count 변경 시 실행
 */
function Ex02() {
  const [count, setCount] = useState(0)

  useEffect(() => {
    console.log('✅ [Ex02] [count] → count 변경됨:', count)
    document.title = `Ex02 카운트: ${count}`
  }, [count])

  return (
    <>
      <h3>Ex02: 의존성 배열 <code>[count]</code> — 값 변경 시</h3>
      <pre className="code-block">{`useEffect(() => {
  console.log("count:", count);
}, [count]);  // ← count가 바뀔 때`}</pre>

      <p>
        <strong>한 줄:</strong> count가 변경되면 이 코드를 실행해라
      </p>
      <p>처음에도 한 번 실행되고, +1 할 때마다 콘솔 + 탭 제목이 바뀝니다.</p>

      <div className="count-display">{count}</div>
      <button className="btn" type="button" onClick={() => setCount(count + 1)}>
        +1
      </button>

      <div className="console-hint">💡 콘솔 + 브라우저 탭 제목 확인</div>
    </>
  )
}

export default Ex02
