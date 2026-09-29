import { useState } from 'react'
import Example1 from './Example1'
import Example2 from './Example2'
import Example3 from './Example3'
import Example4 from './Example4'
import Ex01 from './Ex01'
import Ex02 from './Ex02'
import Ex03 from './Ex03'
import ExTimer from './ExTimer'

function App() {
  const [activeTab, setActiveTab] = useState(1)

  const tabs = [
    { id: 1, label: '예제1: 마운트 1회' },
    { id: 2, label: '예제2: 값 변경 시' },
    { id: 3, label: '예제3: Cleanup' },
    { id: 4, label: '예제4: 통합' },
    { id: 'ex01', label: 'Ex01: [] 한 번만' },
    { id: 'ex02', label: 'Ex02: [count]' },
    { id: 'ex03', label: 'Ex03: 배열 없음' },
    { id: 'timer', label: 'ExTimer: setInterval' },
  ]

  return (
    <div className="app">
      <h1>🔧 useEffect 실전 예제</h1>
      <p style={{ marginBottom: 20, color: '#666' }}>
        예제1~4 · Ex01~03 · ExTimer · 콘솔(F12) 확인
      </p>

      <div className="tabs">
        {tabs.map((tab) => (
          <button
            key={tab.id}
            type="button"
            className={`tab ${activeTab === tab.id ? 'active' : ''}`}
            onClick={() => setActiveTab(tab.id)}
          >
            {tab.label}
          </button>
        ))}
      </div>

      <div className="example-box">
        {activeTab === 1 && <Example1 />}
        {activeTab === 2 && <Example2 />}
        {activeTab === 3 && <Example3 />}
        {activeTab === 4 && <Example4 />}
        {activeTab === 'ex01' && <Ex01 />}
        {activeTab === 'ex02' && <Ex02 />}
        {activeTab === 'ex03' && <Ex03 />}
        {activeTab === 'timer' && <ExTimer />}
      </div>
    </div>
  )
}

export default App
