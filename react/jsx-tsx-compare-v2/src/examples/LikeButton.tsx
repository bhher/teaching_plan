import { useState } from 'react';

type LikeButtonProps = { label: string };

export default function LikeButton({ label }: LikeButtonProps) {
  const [liked, setLiked] = useState<boolean>(false);
  return (
    <button type="button" className="btn" onClick={() => setLiked(!liked)}>
      {liked ? '🤍 취소' : `❤️ ${label}`}
    </button>
  );
}
