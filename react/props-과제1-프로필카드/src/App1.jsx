import ProfileCard from './ProfileCard.jsx'

const profiles = [
  {
    id: 1,
    name: '홍길동',
    age: 25,
    job: '프론트엔드 개발자',
    image: 'https://i.pravatar.cc/300?img=12',
  },
  {
    id: 2,
    name: '김영희',
    age: 28,
    job: '백엔드 개발자',
    image: 'https://i.pravatar.cc/300?img=5',
  },
  {
    id: 3,
    name: '이철수',
    age: 31,
    job: 'UI/UX 디자이너',
    image: 'https://i.pravatar.cc/300?img=33',
  },
]

function App1() {
  return (
    <div>
      <h1 style={{ marginTop: 0 }}>과제 1 — 프로필 카드</h1>
      <p style={{ color: '#555', marginBottom: '1.5rem' }}>
        name, age, job, image 를 Props로 받아 map으로 여러 장 표시
      </p>

      <div
        style={{
          display: 'flex',
          flexWrap: 'wrap',
          gap: '1.25rem',
        }}
      >
        {profiles.map((profile) => (
          <ProfileCard
            key={profile.id}
            name={profile.name}
            age={profile.age}
            job={profile.job}
            image={profile.image}
          />
        ))}
      </div>
    </div>
  )
}

export default App1
