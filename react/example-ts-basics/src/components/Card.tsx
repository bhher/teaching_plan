/**
 * 가이드 4번: React Props 타입
 */
interface CardProps {
  title: string;
}

export function Card({ title }: CardProps) {
  return <h2>{title}</h2>;
}
