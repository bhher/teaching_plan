/**
 * 가이드 2번: 함수 타입
 */
export function add(a: number, b: number): number {
  return a + b;
}

export const multiply = (a: number, b: number): number => a * b;

export function greet(name: string, title?: string): string {
  return title ? `${title} ${name}님` : `${name}님`;
}

export function repeat(text: string, count: number = 2): string {
  return text.repeat(count);
}
