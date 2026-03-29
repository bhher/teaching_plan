interface DemoButtonProps {
  label: string;
  onClick: () => void;
}

export function DemoButton({ label, onClick }: DemoButtonProps) {
  return (
    <button type="button" onClick={onClick}>
      {label}
    </button>
  );
}
