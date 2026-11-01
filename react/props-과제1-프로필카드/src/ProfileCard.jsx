function ProfileCard({ name, age, job, image }) {
  return (
    <div
      style={{
        width: 240,
        background: '#fff',
        borderRadius: 12,
        overflow: 'hidden',
        boxShadow: '0 4px 16px rgba(0,0,0,0.08)',
        textAlign: 'center',
      }}
    >
      <img
        src={image}
        alt={name}
        style={{ width: '100%', height: 180, objectFit: 'cover' }}
      />
      <div style={{ padding: '1rem 1.25rem 1.25rem' }}>
        <h3 style={{ margin: '0 0 0.4rem', fontSize: '1.15rem' }}>{name}</h3>
        <p style={{ margin: '0 0 0.25rem', color: '#666', fontSize: '0.9rem' }}>
          {age}세
        </p>
        <p style={{ margin: 0, color: '#2563eb', fontWeight: 600, fontSize: '0.9rem' }}>
          {job}
        </p>
      </div>
    </div>
  )
}

export default ProfileCard
