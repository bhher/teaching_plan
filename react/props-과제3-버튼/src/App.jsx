import Button from './Button.jsx'

function App() {
  return (
    <div>
      <h1 style={{ marginTop: 0 }}>과제 3 — 버튼 컴포넌트</h1>
      <p style={{ color: '#555', marginBottom: '1.5rem' }}>
        text, color, size, onClick 을 Props로 받아 다양한 스타일 버튼 만들기
      </p>

      <section style={{ marginBottom: '2rem' }}>
        <h2 style={{ fontSize: '1.1rem' }}>색상 (color)</h2>
        <div style={{ display: 'flex', flexWrap: 'wrap', gap: '0.75rem' }}>
          <Button text="Blue" color="blue" onClick={() => alert('blue')} />
          <Button text="Green" color="green" onClick={() => alert('green')} />
          <Button text="Red" color="red" onClick={() => alert('red')} />
          <Button text="Gray" color="gray" onClick={() => alert('gray')} />
          <Button text="Orange" color="orange" onClick={() => alert('orange')} />
        </div>
      </section>

      <section style={{ marginBottom: '2rem' }}>
        <h2 style={{ fontSize: '1.1rem' }}>크기 (size)</h2>
        <div style={{ display: 'flex', flexWrap: 'wrap', alignItems: 'center', gap: '0.75rem' }}>
          <Button text="Small" color="blue" size="small" onClick={() => alert('small')} />
          <Button text="Medium" color="blue" size="medium" onClick={() => alert('medium')} />
          <Button
            text="클릭하세요"
            color="blue"
            size="large"
            onClick={() => alert('클릭!')}
          />
        </div>
      </section>

      <section>
        <h2 style={{ fontSize: '1.1rem' }}>조합 예시</h2>
        <div style={{ display: 'flex', flexWrap: 'wrap', gap: '0.75rem' }}>
          <Button text="저장" color="green" size="medium" onClick={() => alert('저장됨')} />
          <Button text="삭제" color="red" size="medium" onClick={() => alert('삭제됨')} />
          <Button text="취소" color="gray" size="small" onClick={() => alert('취소')} />
        </div>
      </section>
    </div>
  )
}

export default App
