import ProfileCard from './ProfileCard.jsx'

function App() {
  return (
    <div>
      <h1 style={{ marginTop: 0 }}>과제 1 — 프로필 카드</h1>
      <p style={{ color: '#555', marginBottom: '1.5rem' }}>
        name, age, job, image 를 Props로 받아 여러 장 표시
      </p>

      <div
        style={{
          display: 'flex',
          flexWrap: 'wrap',
          gap: '1.25rem',
        }}
      >
        <ProfileCard
          name="홍길동"
          age={25}
          job="프론트엔드 개발자"
          image="https://i.pravatar.cc/300?img=12"
        />
        <ProfileCard
          name="김영희"
          age={28}
          job="백엔드 개발자"
          image="https://i.pravatar.cc/300?img=5"
        />
        <ProfileCard
          name="이철수"
          age={31}
          job="UI/UX 디자이너"
          image="https://i.pravatar.cc/300?img=33"
        />
      </div>
    </div>
  )
}

export default App
