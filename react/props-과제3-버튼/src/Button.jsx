const colorMap = {
  blue: { background: '#2563eb', color: '#fff' },
  green: { background: '#16a34a', color: '#fff' },
  red: { background: '#dc2626', color: '#fff' },
  gray: { background: '#6b7280', color: '#fff' },
  orange: { background: '#ea580c', color: '#fff' },
}

const sizeMap = {
  small: { padding: '0.35rem 0.75rem', fontSize: '0.85rem' },
  medium: { padding: '0.55rem 1.1rem', fontSize: '1rem' },
  large: { padding: '0.75rem 1.5rem', fontSize: '1.15rem' },
}

function Button({
  text,
  color = 'blue',
  size = 'medium',
  onClick,
}) {
  const colorStyle = colorMap[color] || colorMap.blue
  const sizeStyle = sizeMap[size] || sizeMap.medium

  return (
    <button
      type="button"
      onClick={onClick}
      style={{
        ...colorStyle,
        ...sizeStyle,
        border: 'none',
        borderRadius: 8,
        cursor: 'pointer',
        fontWeight: 600,
        fontFamily: 'inherit',
      }}
    >
      {text}
    </button>
  )
}

export default Button
