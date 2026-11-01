import { useState } from 'react';

export default function Contact() {
  const [form, setForm] = useState({ name: '', email: '', message: '' });
  const [sent, setSent] = useState(false);

  const onChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const onSubmit = (e) => {
    e.preventDefault();
    setSent(true);
  };

  return (
    <div className="page narrow">
      <p className="eyebrow">Contact</p>
      <h1>문의하기</h1>
      <p className="lead">데모용 폼입니다. 제출 시 아래 확인 메시지만 표시됩니다.</p>

      {sent ? (
        <div className="success">
          <p>
            <strong>{form.name || '손님'}</strong>님, 메시지가 접수되었습니다. (데모)
          </p>
          <button type="button" className="btn btn-ghost" onClick={() => setSent(false)}>
            다시 작성
          </button>
        </div>
      ) : (
        <form className="form" onSubmit={onSubmit}>
          <label>
            이름
            <input name="name" value={form.name} onChange={onChange} required />
          </label>
          <label>
            이메일
            <input
              type="email"
              name="email"
              value={form.email}
              onChange={onChange}
              required
            />
          </label>
          <label>
            메시지
            <textarea
              name="message"
              rows={5}
              value={form.message}
              onChange={onChange}
              required
            />
          </label>
          <button type="submit" className="btn">
            보내기
          </button>
        </form>
      )}
    </div>
  );
}
