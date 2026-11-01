import { useNavigate } from 'react-router-dom';

function Login() {
  const navigate = useNavigate();

  const handleLogin = () => {
    // 실제로는 로그인 API 호출 후
    alert('로그인 성공 (데모)');
    navigate('/');
  };

  return (
    <div className="page">
      <h1>로그인</h1>
      <p>버튼을 누르면 useNavigate로 홈(/)으로 이동합니다.</p>
      <button type="button" className="btn" onClick={handleLogin}>
        로그인
      </button>
      <div className="hint">실습 4: navigate(&quot;/&quot;) — 코드로 페이지 이동</div>
    </div>
  );
}

export default Login;
