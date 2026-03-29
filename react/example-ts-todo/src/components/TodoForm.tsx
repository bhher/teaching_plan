import { useEffect, useState, type FormEvent } from "react";
import type { Todo } from "../types/todo";

export interface TodoFormProps {
  editing: Todo | null;
  onCreate: (text: string) => void;
  onUpdate: (todo: Todo) => void;
  onCancelEdit: () => void;
}

export function TodoForm({ editing, onCreate, onUpdate, onCancelEdit }: TodoFormProps) {
  const [text, setText] = useState("");

  useEffect(() => {
    setText(editing ? editing.text : "");
  }, [editing]);

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    const trimmed = text.trim();
    if (!trimmed) return;

    if (editing) {
      onUpdate({ ...editing, text: trimmed });
    } else {
      onCreate(trimmed);
      setText("");
    }
  };

  return (
    <form className="todo-form" onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder={editing ? "할 일 수정…" : "할 일을 입력하세요"}
        value={text}
        onChange={(e) => setText(e.target.value)}
        autoComplete="off"
      />
      <button type="submit">{editing ? "수정 저장" : "추가"}</button>
      {editing ? (
        <button type="button" className="ghost" onClick={onCancelEdit}>
          취소
        </button>
      ) : null}
    </form>
  );
}
