// api-server/server.js
const express = require('express');
const cors = require('cors');

const app = express();
const PORT = 3001;

app.use(cors()); // 개발: 모든 출처 허용 (운영에서는 origin 제한 권장)
app.use(express.json()); // JSON body 파싱

// 임시 저장소 (서버 재시작 시 초기화됨)
let items = [
  { id: 1, title: '첫 글', done: false },
  { id: 2, title: '둘째 글', done: true },
];
let nextId = 3;

// 목록
app.get('/api/items', (req, res) => {
  res.json(items);
});

// 단건
app.get('/api/items/:id', (req, res) => {
  const id = Number(req.params.id);
  const found = items.find((x) => x.id === id);
  if (!found) return res.status(404).json({ message: '없음' });
  res.json(found);
});

// 추가
app.post('/api/items', (req, res) => {
  const { title, done } = req.body;
  const row = { id: nextId++, title: title ?? '', done: Boolean(done) };
  items.push(row);
  res.status(201).json(row);
});

// 수정
app.put('/api/items/:id', (req, res) => {
  const id = Number(req.params.id);
  const idx = items.findIndex((x) => x.id === id);
  if (idx === -1) return res.status(404).json({ message: '없음' });
  const { title, done } = req.body;
  items[idx] = {
    ...items[idx],
    ...(title !== undefined && { title }),
    ...(done !== undefined && { done: Boolean(done) }),
  };
  res.json(items[idx]);
});

// 삭제
app.delete('/api/items/:id', (req, res) => {
  const id = Number(req.params.id);
  const before = items.length;
  items = items.filter((x) => x.id !== id);
  if (items.length === before) return res.status(404).json({ message: '없음' });
  res.status(204).send();
});

app.listen(PORT, () => {
  console.log(`API 서버 http://localhost:${PORT}`);
});
