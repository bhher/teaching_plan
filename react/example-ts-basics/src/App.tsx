import { useState } from "react";
import { Card } from "./components/Card";
import { Counter } from "./components/Counter";
import { DemoButton } from "./components/DemoButton";
import { Layout } from "./components/Layout";
import { ProfileForm } from "./components/ProfileForm";
import {
  demoAge,
  demoIsStudent,
  demoName,
  demoScores,
  demoTuple,
  formatId,
} from "./lib/basicTypes";
import { add, greet, multiply, repeat } from "./lib/math";

export default function App() {
  const [unionDemo, setUnionDemo] = useState<"string" | "number">("string");

  const idLabel =
    unionDemo === "string" ? formatId("abc-1") : formatId(42);

  return (
    <Layout>
      <h1>React + TypeScript 핵심 5가지 (예제)</h1>
      <p className="muted">
        상세 설명: 저장소의 <code>React-TypeScript-Vite-시작-핵심개념.md</code>
      </p>

      <section>
        <Card title="1. 타입 선언" />
        <pre className="code-block">
          {JSON.stringify(
            {
              name: demoName,
              age: demoAge,
              isStudent: demoIsStudent,
              scores: demoScores,
              tuple: demoTuple,
              unionIdDisplay: idLabel,
            },
            null,
            2
          )}
        </pre>
        <div className="row">
          <span className="muted">유니온 토글:</span>
          <DemoButton
            label={unionDemo === "string" ? "숫자 id로" : "문자열 id로"}
            onClick={() => setUnionDemo(unionDemo === "string" ? "number" : "string")}
          />
        </div>
      </section>

      <section>
        <Card title="2. 함수 타입" />
        <pre className="code-block">
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
        <Card title="3. 객체 타입 (interface User)" />
        <p className="muted">
          <code>src/types/user.ts</code>의 <code>User</code>를 폼 제출 후 상태로 사용합니다.
        </p>
        <ProfileForm />
      </section>

      <section>
        <Card title="4. React Props (Card, Layout, DemoButton)" />
        <p>
          이 페이지의 소제목은 모두 <code>Card</code>의 <code>title: string</code> props입니다.
        </p>
        <div className="row">
          <DemoButton label="콘솔에 찍기" onClick={() => console.log("clicked")} />
        </div>
      </section>

      <section>
        <Card title="5. useState 타입" />
        <Counter />
      </section>
    </Layout>
  );
}
