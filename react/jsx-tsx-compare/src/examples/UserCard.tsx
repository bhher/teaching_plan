export type User = {
  name: string;
  email: string;
  age: number;
};

type UserCardProps = {
  user: User;
};

/** TSX — User 타입이 틀리면 에디터/빌드에서 바로 오류 */
export default function UserCard({ user }: UserCardProps) {
  return (
    <div className="demo-box">
      <p className="demo-label">UserCard (.tsx)</p>
      <p>
        <strong>{user.name}</strong>
      </p>
      <p className="muted">{user.email}</p>
      <p className="muted">나이 {user.age}</p>
    </div>
  );
}
