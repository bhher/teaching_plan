import { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function LoginPage() {
  const navigate = useNavigate();
  const location = useLocation();
  const { login } = useAuth();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);

  const from = location.state?.from?.pathname || '/';

  const submit = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      await login(email, password);
      navigate(from, { replace: true });
    } catch (err) {
      setError(err.data?.error || err.message);
    }
  };

  return (
    <div>
      <h1>로그인</h1>
      <p className="hint">세션 쿠키(httpOnly) · <code>fetch(..., credentials: &apos;include&apos;)</code></p>
      <form onSubmit={submit} className="form">
        <label>
          이메일
          <input
            type="email"
            autoComplete="username"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </label>
        <label>
          비밀번호
          <input
            type="password"
            autoComplete="current-password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">로그인</button>
      </form>
      <p>
        테스트: tourist@test.com / 1234 · <Link to="/join">회원가입</Link>
      </p>
    </div>
  );
}
