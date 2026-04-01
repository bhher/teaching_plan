import './env.js';
import express from 'express';
import cors from 'cors';
import session from 'express-session';
import { pool } from './db.js';

const PORT = Number(process.env.PORT, 10) || 3001;
const CLIENT_ORIGIN = process.env.CLIENT_ORIGIN || 'http://localhost:5173';
const SESSION_SECRET = process.env.SESSION_SECRET || 'kculture-dev-secret-change-in-production';

const app = express();

app.set('trust proxy', 1);

app.use(
  cors({
    origin: CLIENT_ORIGIN,
    credentials: true,
  })
);
app.use(express.json());
app.use(
  session({
    name: 'kculture.sid',
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

function requireAuth(req, res, next) {
  if (!req.session.memberId) {
    res.status(401).json({ error: 'Login required.' });
    return;
  }
  next();
}

/** ---------- categories ---------- */
app.get('/api/categories', async (_req, res) => {
  try {
    const [rows] = await pool.query(
      'SELECT id, code, name_en AS nameEn, name_ko AS nameKo, icon, sort_order AS sortOrder FROM category ORDER BY sort_order'
    );
    res.json(rows);
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

/** ---------- posts list ---------- */
app.get('/api/posts', async (req, res) => {
  try {
    const categoryId = Number(req.query.categoryId) || 0;
    const page = Math.max(1, Number(req.query.page) || 1);
    const pageSize = Math.min(50, Math.max(1, Number(req.query.pageSize) || 10));
    const start = (page - 1) * pageSize;

    let listSql;
    let countSql;
    const params = [];
    if (categoryId > 0) {
      listSql = `SELECT p.id, p.category_id AS categoryId, p.member_id AS memberId, p.title, p.content,
        p.view_count AS viewCount, p.created_at AS createdAt, p.updated_at AS updatedAt,
        m.name AS memberName, c.name_en AS categoryName, c.icon AS categoryIcon
        FROM post p JOIN member m ON p.member_id = m.id JOIN category c ON p.category_id = c.id
        WHERE p.category_id = ? ORDER BY p.created_at DESC LIMIT ?, ?`;
      params.push(categoryId, start, pageSize);
      countSql = 'SELECT COUNT(*) AS cnt FROM post WHERE category_id = ?';
    } else {
      listSql = `SELECT p.id, p.category_id AS categoryId, p.member_id AS memberId, p.title, p.content,
        p.view_count AS viewCount, p.created_at AS createdAt, p.updated_at AS updatedAt,
        m.name AS memberName, c.name_en AS categoryName, c.icon AS categoryIcon
        FROM post p JOIN member m ON p.member_id = m.id JOIN category c ON p.category_id = c.id
        ORDER BY p.created_at DESC LIMIT ?, ?`;
      params.push(start, pageSize);
      countSql = 'SELECT COUNT(*) AS cnt FROM post';
    }

    const [list] = await pool.query(listSql, params);
    const [countRows] =
      categoryId > 0
        ? await pool.query(countSql, [categoryId])
        : await pool.query(countSql);
    const total = countRows[0]?.cnt ?? 0;
    const totalPages = Math.max(1, Math.ceil(total / pageSize));

    res.json({
      posts: list,
      total,
      page,
      pageSize,
      totalPages,
      categoryId,
    });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

async function loadPostRow(id) {
  const [rows] = await pool.query(
    `SELECT p.id, p.category_id AS categoryId, p.member_id AS memberId, p.title, p.content,
      p.view_count AS viewCount, p.created_at AS createdAt, p.updated_at AS updatedAt,
      m.name AS memberName, m.nationality, c.name_en AS categoryName, c.icon AS categoryIcon
      FROM post p JOIN member m ON p.member_id = m.id JOIN category c ON p.category_id = c.id WHERE p.id = ?`,
    [id]
  );
  return rows[0] ?? null;
}

/** 편집 폼용: 조회수 증가 없음 */
app.get('/api/posts/:id/edit', requireAuth, async (req, res) => {
  try {
    const id = Number(req.params.id);
    if (!id) {
      res.status(404).json({ error: 'Not found.' });
      return;
    }
    const post = await loadPostRow(id);
    if (!post) {
      res.status(404).json({ error: 'Not found.' });
      return;
    }
    if (post.memberId !== req.session.memberId) {
      res.status(403).json({ error: 'Forbidden.' });
      return;
    }
    res.json({ post });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

/** ---------- post detail (+ view++) ---------- */
app.get('/api/posts/:id', async (req, res) => {
  try {
    const id = Number(req.params.id);
    if (!id) {
      res.status(404).json({ error: 'Not found.' });
      return;
    }
    const post = await loadPostRow(id);
    if (!post) {
      res.status(404).json({ error: 'Not found.' });
      return;
    }
    await pool.query('UPDATE post SET view_count = view_count + 1 WHERE id = ?', [id]);
    post.viewCount = (post.viewCount ?? 0) + 1;

    const [comments] = await pool.query(
      `SELECT c.id, c.post_id AS postId, c.member_id AS memberId, c.content, c.created_at AS createdAt,
        m.name AS memberName, m.nationality
        FROM comment c JOIN member m ON c.member_id = m.id WHERE c.post_id = ? ORDER BY c.created_at`,
      [id]
    );

    res.json({ post, comments });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

app.post('/api/posts', requireAuth, async (req, res) => {
  try {
    const { categoryId, title, content } = req.body;
    const cid = Number(categoryId);
    if (!cid || !title || String(title).trim() === '') {
      res.status(400).json({ error: 'categoryId and title are required.' });
      return;
    }
    const [result] = await pool.query(
      'INSERT INTO post (category_id, member_id, title, content) VALUES (?, ?, ?, ?)',
      [cid, req.session.memberId, String(title).trim(), content != null ? String(content) : '']
    );
    res.status(201).json({ id: result.insertId });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

app.patch('/api/posts/:id', requireAuth, async (req, res) => {
  try {
    const id = Number(req.params.id);
    const { title, content } = req.body;
    if (!id) {
      res.status(400).json({ error: 'Invalid id.' });
      return;
    }
    const [r] = await pool.query(
      'UPDATE post SET title = ?, content = ? WHERE id = ? AND member_id = ?',
      [String(title ?? '').trim(), content != null ? String(content) : '', id, req.session.memberId]
    );
    if (r.affectedRows === 0) {
      res.status(403).json({ error: 'Forbidden or not found.' });
      return;
    }
    res.json({ ok: true });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

app.delete('/api/posts/:id', requireAuth, async (req, res) => {
  try {
    const id = Number(req.params.id);
    const [r] = await pool.query('DELETE FROM post WHERE id = ? AND member_id = ?', [
      id,
      req.session.memberId,
    ]);
    if (r.affectedRows === 0) {
      res.status(403).json({ error: 'Forbidden or not found.' });
      return;
    }
    res.json({ ok: true });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

/** ---------- comments ---------- */
app.post('/api/posts/:id/comments', requireAuth, async (req, res) => {
  try {
    const postId = Number(req.params.id);
    const { content } = req.body;
    if (!postId || !content || String(content).trim() === '') {
      res.status(400).json({ error: 'content is required.' });
      return;
    }
    const [result] = await pool.query(
      'INSERT INTO comment (post_id, member_id, content) VALUES (?, ?, ?)',
      [postId, req.session.memberId, String(content).trim()]
    );
    res.status(201).json({ id: result.insertId });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

/** ---------- auth ---------- */
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
    res.clearCookie('kculture.sid', { path: '/' });
    res.json({ ok: true });
  });
});

app.post('/api/auth/join', async (req, res) => {
  try {
    const { email, password, name, nationality, language } = req.body;
    if (!email || !password || !name) {
      res.status(400).json({ error: 'email, password, and name are required.' });
      return;
    }
    const [dup] = await pool.query('SELECT id FROM member WHERE email = ?', [email]);
    if (dup.length > 0) {
      res.status(409).json({ error: 'Email already exists.' });
      return;
    }
    const [result] = await pool.query(
      'INSERT INTO member (email, password, name, nationality, language) VALUES (?, ?, ?, ?, ?)',
      [email, password, name, nationality || null, language || 'en']
    );
    req.session.memberId = result.insertId;
    const [rows] = await pool.query(
      'SELECT id, email, name, nationality, language FROM member WHERE id = ?',
      [result.insertId]
    );
    res.status(201).json({ member: mapMemberRow(rows[0]) });
  } catch (e) {
    console.error(e);
    res.status(500).json({ error: 'Database error.' });
  }
});

app.listen(PORT, () => {
  console.log(`K-Culture API http://localhost:${PORT}`);
});
