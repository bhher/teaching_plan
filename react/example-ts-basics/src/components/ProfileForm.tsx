import { useState, type FormEvent } from "react";
import type { User } from "../types/user";

interface FormState {
  email: string;
  password: string;
}

export function ProfileForm() {
  const [form, setForm] = useState<FormState>({
    email: "",
    password: "",
  });
  const [user, setUser] = useState<User | null>(null);

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    setUser({
      id: 1,
      name: "데모 사용자",
      email: form.email || undefined,
    });
  };

  return (
    <div>
      {user ? (
        <p>
          로그인 상태: <strong>{user.name}</strong>
          {user.email ? ` (${user.email})` : ""}
        </p>
      ) : (
        <p className="muted">아래에서 이메일을 넣고 제출해 보세요.</p>
      )}
      <form onSubmit={handleSubmit} className="row" style={{ flexDirection: "column", alignItems: "stretch" }}>
        <input
          type="email"
          placeholder="email"
          value={form.email}
          onChange={(e) => setForm((prev) => ({ ...prev, email: e.target.value }))}
        />
        <input
          type="password"
          placeholder="password"
          value={form.password}
          onChange={(e) => setForm((prev) => ({ ...prev, password: e.target.value }))}
        />
        <button type="submit">제출 (User | null 데모)</button>
      </form>
    </div>
  );
}
