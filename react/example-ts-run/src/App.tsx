import { useState, type FormEvent, type ReactNode } from "react";

// --- 1. 타입 선언 ---
const demoName: string = "홍길동";
const demoAge: number = 20;
const demoScores: number[] = [90, 85, 88];
const demoTuple: [string, number] = ["점수", 100];

function formatId(id: string | number): string {
  return typeof id === "number" ? `#${id}` : id;
}

// --- 2. 함수 타입 ---
function add(a: number, b: number): number {
  return a + b;
}

const multiply = (a: number, b: number): number => a * b;

function greet(name: string, title?: string): string {
  return title ? `${title} ${name}님` : `${name}님`;
}

function repeat(text: string, count: number = 2): string {
  return text.repeat(count);
}

// --- 3. 객체 타입 ---
interface User {
  id: number;
  name: string;
  email?: string;
}

interface FormState {
  email: string;
  password: string;
}

// --- 4. Props ---
function Card({ title }: { title: string }) {
  return <h2>{title}</h2>;
}

function Shell({ children }: { children: ReactNode }) {
  return <div>{children}</div>;
}

function ActionButton({ label, onClick }: { label: string; onClick: () => void }) {
  return (
    <button type="button" onClick={onClick}>
      {label}
    </button>
  );
}

// --- 5. 폼 + useState (User | null, FormState) ---
function MiniForm() {
  const [form, setForm] = useState<FormState>({ email: "", password: "" });
  const [user, setUser] = useState<User | null>(null);

  const onSubmit = (e: FormEvent) => {
    e.preventDefault();
    setUser({ id: 1, name: "데모 사용자", email: form.email || undefined });
  };

  return (
    <div>
      {user ? (
        <p>
          <strong>{user.name}</strong>
          {user.email ? ` · ${user.email}` : ""}
        </p>
      ) : (
        <p className="muted">이메일 입력 후 제출</p>
      )}
      <form onSubmit={onSubmit} className="row" style={{ flexDirection: "column", alignItems: "stretch" }}>
        <input
          type="email"
          placeholder="email"
          value={form.email}
          onChange={(e) => setForm((p) => ({ ...p, email: e.target.value }))}
        />
        <input
          type="password"
          placeholder="password"
          value={form.password}
          onChange={(e) => setForm((p) => ({ ...p, password: e.target.value }))}
        />
        <button type="submit">제출</button>
      </form>
    </div>
  );
}

function Counter() {
  const [count, setCount] = useState<number>(0);
  return (
    <div className="row">
      <span>count = {count}</span>
      <button type="button" className="secondary" onClick={() => setCount((c) => c + 1)}>
        +1
      </button>
    </div>
  );
}

export default function App() {
  const [unionMode, setUnionMode] = useState<"string" | "number">("string");
  const idShown = unionMode === "string" ? formatId("abc-1") : formatId(42);

  return (
    <Shell>
      <h1>TypeScript 예제 (한 파일)</h1>

      <section>
        <Card title="1. 타입 선언" />
        <pre>
          {JSON.stringify(
            {
              name: demoName,
              age: demoAge,
              scores: demoScores,
              tuple: demoTuple,
              unionId: idShown,
            },
            null,
            2
          )}
        </pre>
        <div className="row">
          <ActionButton
            label={unionMode === "string" ? "숫자 id" : "문자열 id"}
            onClick={() => setUnionMode(unionMode === "string" ? "number" : "string")}
          />
        </div>
      </section>

      <section>
        <Card title="2. 함수 타입" />
        <pre>
          {[
            `add(2, 3) = ${add(2, 3)}`,
            `multiply(4, 5) = ${multiply(4, 5)}`,
            `greet("영희") = ${greet("영희")}`,
            `greet("철수", "선생님") = ${greet("철수", "선생님")}`,
            `repeat("하") = ${repeat("하")}`,
          ].join("\n")}
        </pre>
      </section>

      <section>
        <Card title="3~5. interface · Props · useState" />
        <MiniForm />
        <div className="row" style={{ marginTop: "0.75rem" }}>
          <ActionButton label="콘솔" onClick={() => console.log("ok")} />
        </div>
        <Counter />
      </section>
    </Shell>
  );
}
