import { EMOTIONS } from '../constants';

function DiaryEntry({ entry, onDelete }) {
  const emotionData = EMOTIONS.find((e) => e.id === entry.emotion);

  const formatDate = (timestamp) => {
    const d = new Date(timestamp);
    const now = new Date();
    const isToday = d.toDateString() === now.toDateString();
    return isToday
      ? `오늘 ${d.toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' })}`
      : d.toLocaleDateString('ko-KR', {
          month: 'long',
          day: 'numeric',
          hour: '2-digit',
          minute: '2-digit',
        });
  };

  return (
    <article className="diary-entry">
      <div className="entry-header">
        <span
          className="entry-emotion"
          style={{ backgroundColor: emotionData?.color || '#e5e7eb' }}
          title={emotionData?.label}
        >
          {emotionData?.emoji || '📝'}
        </span>
        <span className="entry-date">{formatDate(entry.createdAt)}</span>
        <button
          type="button"
          className="entry-delete"
          onClick={() => onDelete(entry.id)}
          title="삭제"
        >
          ×
        </button>
      </div>
      <p className="entry-content">{entry.content}</p>
    </article>
  );
}

export default DiaryEntry;
