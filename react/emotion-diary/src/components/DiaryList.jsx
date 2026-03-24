import DiaryEntry from './DiaryEntry';

function DiaryList({ entries, onDelete }) {
  if (entries.length === 0) {
    return (
      <div className="diary-empty">
        <p className="empty-emoji">📔</p>
        <p>아직 작성한 일기가 없어요</p>
        <p>첫 번째 감정을 기록해보세요</p>
      </div>
    );
  }

  return (
    <div className="diary-list">
      {entries
        .slice()
        .sort((a, b) => b.createdAt - a.createdAt)
        .map((entry) => (
          <DiaryEntry key={entry.id} entry={entry} onDelete={onDelete} />
        ))}
    </div>
  );
}

export default DiaryList;
