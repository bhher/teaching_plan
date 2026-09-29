import { Card, Button } from 'react-bootstrap'

export default function ServiceCard({ card }) {
  return (
    <Card className="h-100 shadow-sm border-0">
      <div className={`card-thumb bg-${card.bg}`}>
        <i className={`bi ${card.icon}`} />
      </div>
      <Card.Body>
        <Card.Title as="h5">{card.title}</Card.Title>
        <Card.Text className="text-muted small">{card.text}</Card.Text>
        <Button variant={card.btn} size="sm">
          자세히
        </Button>
      </Card.Body>
    </Card>
  )
}
