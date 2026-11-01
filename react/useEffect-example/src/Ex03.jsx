import { useState, useEffect } from 'react'

/**
 * Ex03 — 의존성 배열 없음 → 렌더링될 때마다 실행
 */
function Ex03() {
  const [count, setCount] = useState(0)
  const [text, setText] = useState('')

  useEffect(() => {
    console.log('⚠️ [Ex03] 의존성 배열 없음 → 렌더링마다 실행', {
      count,
      text,
    })
  }) // ← 두 번째 인자 없음

  return (
    <>
      <h3>Ex03: 의존성 배열 <strong>없음</strong> — 매 렌더링</h3>
      <pre className="code-block">{`useEffect(() => {
  console.log("실행");
});  // ← [] 도 [count] 도 없음`}</pre>

      <p>
        State가 <strong>무엇이든</strong> 바뀌어 다시 그리면 Effect가 실행됩니다.
      </p>
      <p style={{ color: '#c62828' }}>
        배열을 빼먹으면 API가 계속 호출되는 등 문제가 날 수 있습니다.
      </p>

      <div className="count-display">{count}</div>
      <button className="btn" type="button" onClick={() => setCount(count + 1)}>
        count +1
      </button>

      <div style={{ marginTop: 16 }}>
        <input
          value={text}
          onChange={(e) => setText(e.target.value)}
          placeholder="글자 입력 (text 변경)"
          style={{ padding: '8px 12px', width: '100%', maxWidth: 280 }}
        />
      </div>

      <div className="console-hint">
        💡 count 버튼·입력 둘 다 콘솔에 로그가 계속 쌓이는지 확인
      </div>
    </>
  )
}

export default Ex03
