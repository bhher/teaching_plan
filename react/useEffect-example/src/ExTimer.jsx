import { useState } from 'react'
import Timer from './Timer'

/**
 * Cleanup 전용 화면
 * show === false 이면 Timer 언마운트 → clearInterval
 */
function ExTimer() {
  const [show, setShow] = useState(true)

  return (
    <div>
      <h3>ExTimer: setInterval + Cleanup</h3>
      <p>
        타이머를 숨기면 컴포넌트가 언마운트되고, Cleanup에서{' '}
        <code>clearInterval</code>이 실행됩니다.
      </p>

      <button
        type="button"
        className="btn"
        onClick={() => setShow(!show)}
      >
        {show ? '타이머 숨기기' : '타이머 보이기'}
      </button>

      {show && <Timer />}

      <div className="console-hint">
        💡 F12 → 숨기기 후 &quot;1초마다 실행&quot; 로그가 멈춰야 정상 (Cleanup 동작)
      </div>
    </div>
  )
}

export default ExTimer
