/** JSX — user 형태를 문서/관례로만 맞춤 */
export default function UserCard({ user }) {
  return (
    <div className="demo-box">
      <p className="demo-label">UserCard (.jsx)</p>
      <p>
        <strong>{user.name}</strong>
      </p>
      <p className="muted">{user.email}</p>
      <p className="muted">나이 {user.age}</p>
    </div>
  );
}
