/**
 * 예제: DB(member 테이블)로 로그인만 하는 최소 서버
 * 사용법: 이름을 index.js로 바꿔 쓰거나, node index-login-db-minimal.example.js 로 실행
 * 필요: env.js, db.js, .env, MySQL에 kculture_platform 스키마 + member 데이터
 */
import './env.js';
import express from 'express';
import cors from 'cors';
import session from 'express-session';
import { pool } from './db.js';

const PORT = Number(process.env.PORT, 10) || 3002;
const CLIENT_ORIGIN = process.env.CLIENT_ORIGIN || 'http://localhost:5173';
const SESSION_SECRET = process.env.SESSION_SECRET || 'dev-secret-change-me';

const app = express();

app.use(
  cors({
    origin: CLIENT_ORIGIN,
    credentials: true,
  })
);
app.use(express.json());
app.use(
  session({
    name: 'kculture1.sid',
    secret: SESSION_SECRET,
    resave: false,
    saveUninitialized: false,
    cookie: {
      httpOnly: true,
      maxAge: 7 * 24 * 60 * 60 * 1000,
      sameSite: 'lax',
      secure: process.env.NODE_ENV === 'production',
    },
  })
);

function mapMemberRow(row) {
  if (!row) return null;
  return {
    id: row.id,
    email: row.email,
    name: row.name,
    nationality: row.nationality,
    language: row.language,
  };
}

app.get('/', (req, res) => res.send('OK'));

app.get('/api/auth/me', async (req, res) => {
  try {
    if (!req.session.memberId) {
      res.json({ member: null });
      return;
    }
    const [rows] = await pool.query(
      'SELECT id, email, name, nationality, language FROM member WHERE id = ?',
      [req.session.memberId]
    );
    res.json({ member: mapMemberRow(rows[0]) });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

app.post('/api/auth/login', async (req, res) => {
  try {
    const { email, password } = req.body;
    if (!email || !password) {
      res.status(400).json({ error: 'email and password are required.' });
      return;
    }
    const [rows] = await pool.query('SELECT * FROM member WHERE email = ? AND password = ?', [
      email,
      password,
    ]);
    const row = rows[0];
    if (!row) {
      res.status(401).json({ error: 'Invalid email or password.' });
      return;
    }
    req.session.memberId = row.id;
    res.json({ member: mapMemberRow(row) });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

app.post('/api/auth/logout', (req, res) => {
  req.session.destroy((err) => {
    if (err) {
      res.status(500).json({ error: 'Could not log out.' });
      return;
    }
    res.clearCookie('kculture1.sid', { path: '/' });
    res.json({ ok: true });
  });
});

app.listen(PORT, () => {
  console.log(`Login API (DB) http://localhost:${PORT}`);
});
