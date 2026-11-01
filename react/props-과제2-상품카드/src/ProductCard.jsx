function formatPrice(price) {
  return price.toLocaleString('ko-KR') + '원'
}

function ProductCard({ name, price, description, image, discount }) {
  const hasDiscount = discount != null && discount > 0
  const finalPrice = hasDiscount
    ? Math.round(price * (1 - discount / 100))
    : price

  return (
    <div
      style={{
        width: 260,
        background: '#fff',
        borderRadius: 12,
        overflow: 'hidden',
        boxShadow: '0 4px 16px rgba(0,0,0,0.08)',
        position: 'relative',
      }}
    >
      {hasDiscount && (
        <span
          style={{
            position: 'absolute',
            top: 12,
            left: 12,
            background: '#e11d48',
            color: '#fff',
            fontSize: '0.8rem',
            fontWeight: 700,
            padding: '0.25rem 0.5rem',
            borderRadius: 6,
          }}
        >
          {discount}% OFF
        </span>
      )}

      <img
        src={image}
        alt={name}
        style={{ width: '100%', height: 160, objectFit: 'cover' }}
      />

      <div style={{ padding: '1rem 1.1rem 1.25rem' }}>
        <h3 style={{ margin: '0 0 0.4rem', fontSize: '1.1rem' }}>{name}</h3>
        <p style={{ margin: '0 0 0.75rem', color: '#666', fontSize: '0.88rem', lineHeight: 1.4 }}>
          {description}
        </p>

        {hasDiscount ? (
          <div>
            <span
              style={{
                textDecoration: 'line-through',
                color: '#999',
                fontSize: '0.85rem',
                marginRight: 8,
              }}
            >
              {formatPrice(price)}
            </span>
            <strong style={{ color: '#e11d48', fontSize: '1.1rem' }}>
              {formatPrice(finalPrice)}
            </strong>
          </div>
        ) : (
          <strong style={{ fontSize: '1.1rem' }}>{formatPrice(price)}</strong>
        )}
      </div>
    </div>
  )
}

export default ProductCard
