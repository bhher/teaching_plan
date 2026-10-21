import { useState } from 'react';

export default function Ex05Form() {
  const [form, setForm] = useState({ email: '', password: '' });
  const [msg, setMsg] = useState('');

  const onChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const onSubmit = (e) => {
    e.preventDefault();
    setMsg(`제출: ${form.email || '(이메일 없음)'}`);
  };

  const inputClass =
    'w-full rounded-lg border border-slate-300 px-3 py-2 focus:border-transparent focus:outline-none focus:ring-2 focus:ring-teal-500';

  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-2xl font-bold text-slate-900">05 · 입력 폼</h2>
        <p className="mt-1 text-slate-600">focus:ring · controlled input</p>
      </div>

      <form
        onSubmit={onSubmit}
        className="max-w-md space-y-4 rounded-xl bg-white p-6 shadow-sm"
      >
        <div>
          <label className="mb-1 block text-sm font-medium text-slate-700" htmlFor="email">
            이메일
          </label>
          <input
            id="email"
            name="email"
            type="email"
            value={form.email}
            onChange={onChange}
            placeholder="you@example.com"
            className={inputClass}
          />
        </div>
        <div>
          <label className="mb-1 block text-sm font-medium text-slate-700" htmlFor="password">
            비밀번호
          </label>
          <input
            id="password"
            name="password"
            type="password"
            value={form.password}
            onChange={onChange}
            className={inputClass}
          />
        </div>
        <button
          type="submit"
          className="w-full rounded-lg bg-teal-600 py-2 font-medium text-white hover:bg-teal-700"
        >
          로그인
        </button>
        {msg && (
          <p className="rounded-lg bg-teal-50 p-3 text-sm text-teal-800">{msg}</p>
        )}
      </form>
    </div>
  );
}
