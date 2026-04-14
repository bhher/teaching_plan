import { useState } from 'react';

export default function Contact() {
  const [sent, setSent] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    setSent(true);
  };

  return (
    <article className="page">
      <h1>문의</h1>
      <p>아래는 라우팅 연습용 폼입니다. 제출해도 서버로 전송되지 않습니다.</p>

      {sent ? (
        <p className="notice notice--ok" role="status">
          데모: 제출된 것처럼 표시만 했습니다.
        </p>
      ) : null}

      <form className="contact-form" onSubmit={handleSubmit}>
        <label className="field">
          <span>이름</span>
          <input name="name" type="text" required autoComplete="name" placeholder="홍길동" />
        </label>
        <label className="field">
          <span>메시지</span>
          <textarea name="message" rows={4} required placeholder="내용을 입력하세요" />
        </label>
        <button type="submit" className="btn-primary">
          보내기 (데모)
        </button>
      </form>
    </article>
  );
}
