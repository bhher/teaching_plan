import Slider from 'react-slick';
import { Link } from 'react-router-dom';
import { Container } from 'react-bootstrap';

const slides = [
  {
    title: '빠르고 안정적인 웹',
    subtitle: 'React와 Bootstrap으로 구성하는 모던 UI',
    image: 'https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=1600&q=80',
    cta: '서비스 보기',
    to: '/services',
  },
  {
    title: '슬릭(Slick) 슬라이더',
    subtitle: '메인 배너에 자동 재생 · 화살표 · 도트 인디케이터',
    image: 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=1600&q=80',
    cta: '문의하기',
    to: '/contact',
  },
  {
    title: '카드 & 이미지',
    subtitle: 'Unsplash 이미지로 카드 레이아웃을 구성했습니다',
    image: 'https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=1600&q=80',
    cta: '서비스 보기',
    to: '/services',
  },
];

export default function HeroSlider() {
  const settings = {
    dots: true,
    infinite: true,
    speed: 600,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 5000,
    pauseOnHover: true,
    arrows: true,
  };

  return (
    <div className="hero-slider mb-0">
      <Slider {...settings}>
        {slides.map((s) => (
          <div key={s.title}>
            <div
              className="hero-slide text-white"
              style={{ backgroundImage: `url(${s.image})` }}
            >
              <Container>
                <div className="py-5" style={{ maxWidth: '560px' }}>
                  <h1 className="display-5 fw-bold mb-3">{s.title}</h1>
                  <p className="lead opacity-90 mb-4">{s.subtitle}</p>
                  <Link to={s.to} className="btn btn-primary btn-lg px-4">
                    {s.cta}
                    <i className="bi bi-arrow-right ms-2" />
                  </Link>
                </div>
              </Container>
            </div>
          </div>
        ))}
      </Slider>
    </div>
  );
}
