import { Card, Button } from 'react-bootstrap'

/** 사진 포함 카드 1장 */
export default function PhotoCard({ card }) {
  return (
    <Card className="h-100 shadow-sm border-0">
      <Card.Img variant="top" src={card.img} alt={card.title} />
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
