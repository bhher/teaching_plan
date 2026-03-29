/**
 * 가이드 1번: 타입 선언 — 화면에 넣을 데모 값
 */
export const demoName: string = "홍길동";
export const demoAge: number = 20;
export const demoIsStudent: boolean = true;
export const demoScores: number[] = [90, 85, 88];
export const demoTuple: [string, number] = ["점수", 100];

/** 유니온: 문자열 또는 숫자 id */
export function formatId(id: string | number): string {
  return typeof id === "number" ? `#${id}` : id;
}
