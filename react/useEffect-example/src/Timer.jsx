import { useState, useEffect } from 'react'

/**
 * setInterval + Cleanup
 * - 보이기: 1초마다 count 증가
 * - 숨기기: clearInterval (안 지우면 백그라운드에서 계속 돌아감)
 */
function Timer() {
  const [count, setCount] = useState(0)

  useEffect(() => {
    console.log('✅ 타이머 시작')

    const timer = setInterval(() => {
      console.log('1초마다 실행')
      setCount((prev) => prev + 1)
    }, 1000)

    return () => {
      console.log('🧹 타이머 정리')
      clearInterval(timer)
    }
  }, [])

  return (
    <div
      style={{
        padding: '20px',
        marginTop: '20px',
        backgroundColor: '#e8f5e9',
        borderRadius: '10px',
      }}
    >
      <h2>⏰ 타이머</h2>
      <p>{count}초 경과</p>
    </div>
  )
}

export default Timer
