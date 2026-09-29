import { useState, useEffect } from 'react'

/**
 * Ex01 — 의존성 배열 [] → 처음 한 번만 실행
 */
function Ex01() {
  const [count, setCount] = useState(0)

  useEffect(() => {
    console.log('✅ [Ex01] [] → 처음 한 번만 실행')
  }, [])

  return (
    <>
      <h3>Ex01: 의존성 배열 <code>[]</code> — 처음 한 번만</h3>
      <pre className="code-block">{`useEffect(() => {
  console.log("실행!");
}, []);  // ← 빈 배열`}</pre>

      <p>
        <strong>흐름:</strong> App 실행 → 렌더링 → useEffect 실행 → 끝
      </p>
      <p>
        +1로 count를 바꿔도 <strong>콘솔에 Effect가 다시 안 찍힙니다.</strong>
      </p>

      <div className="count-display">{count}</div>
      <button className="btn" type="button" onClick={() => setCount(count + 1)}>
        +1 (Effect는 안 돌아감)
      </button>

      <div className="console-hint">
        💡 F12 → Console · 이 탭을 다시 고르면 마운트되어 1회 다시 실행
      </div>
    </>
  )
}

export default Ex01
