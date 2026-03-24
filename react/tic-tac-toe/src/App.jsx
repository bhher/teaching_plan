import { useMemo, useState } from 'react';
import FlashCard from './components/FlashCard.jsx';

const TERMS = [
  { id: 1, term: '요구사항 확인', description: '실기에서 주어진 업무 시나리오를 기능/비기능 요구사항으로 분리해 명세화하는 단계입니다.' },
  { id: 2, term: '기능 요구사항', description: '시스템이 반드시 수행해야 하는 기능(조회, 등록, 수정, 삭제 등)을 정의합니다.' },
  { id: 3, term: '비기능 요구사항', description: '성능, 보안, 가용성, 확장성 등 품질 속성과 제약 사항을 정의합니다.' },
  { id: 4, term: '유스케이스(Use Case)', description: '사용자 관점에서 시스템과의 상호작용 시나리오를 기술하는 모델입니다.' },
  { id: 5, term: 'UML 다이어그램', description: '시스템 구조/행위를 시각화하기 위한 표준 모델링 표기법입니다.' },
  { id: 6, term: '클래스 다이어그램', description: '클래스, 속성, 메서드, 클래스 간 관계를 정적으로 표현합니다.' },
  { id: 7, term: '시퀀스 다이어그램', description: '객체 간 메시지 흐름을 시간 순서로 표현해 처리 절차를 분석합니다.' },
  { id: 8, term: 'ERD', description: '엔터티, 속성, 관계를 도식화해 데이터베이스 구조를 설계합니다.' },
  { id: 9, term: '식별자(Identifier)', description: '엔터티 내 각 인스턴스를 유일하게 구분하는 속성입니다.' },
  { id: 10, term: '기본키(PK)', description: '테이블의 각 행을 유일하게 식별하는 키로 NULL을 허용하지 않습니다.' },
  { id: 11, term: '외래키(FK)', description: '다른 테이블의 기본키를 참조해 테이블 간 관계를 연결합니다.' },
  { id: 12, term: '정규화', description: '데이터 중복을 최소화하고 이상 현상을 줄이도록 테이블을 분해하는 과정입니다.' },
  { id: 13, term: '반정규화', description: '조회 성능 향상을 위해 의도적으로 중복을 허용해 테이블을 통합/확장하는 기법입니다.' },
  { id: 14, term: '트랜잭션', description: '논리적으로 하나의 작업 단위를 의미하며 전부 성공 또는 전부 실패해야 합니다.' },
  { id: 15, term: 'ACID', description: '원자성, 일관성, 고립성, 지속성으로 트랜잭션의 핵심 성질을 의미합니다.' },
  { id: 16, term: '인덱스(Index)', description: '검색 속도를 높이기 위한 자료구조이며 과도하면 쓰기 성능이 저하될 수 있습니다.' },
  { id: 17, term: '조인(Join)', description: '공통 컬럼을 기준으로 여러 테이블 데이터를 결합하는 SQL 연산입니다.' },
  { id: 18, term: '뷰(View)', description: '하나 이상의 테이블을 기반으로 만든 가상 테이블로 보안/단순화에 활용됩니다.' },
  { id: 19, term: '저장 프로시저', description: 'DB에 저장된 절차형 SQL 묶음으로 반복 로직을 재사용할 수 있습니다.' },
  { id: 20, term: '트리거(Trigger)', description: 'INSERT/UPDATE/DELETE 이벤트 발생 시 자동 실행되는 데이터베이스 객체입니다.' },
  { id: 21, term: 'REST API', description: 'URI로 자원을 식별하고 HTTP 메서드(GET/POST/PUT/DELETE)로 행위를 표현합니다.' },
  { id: 22, term: 'HTTP 상태코드', description: '요청 처리 결과를 숫자로 표현하며 200, 201, 400, 404, 500 등이 자주 사용됩니다.' },
  { id: 23, term: 'JSON', description: '키-값 기반 경량 데이터 교환 형식으로 API 응답/요청 본문에 널리 사용됩니다.' },
  { id: 24, term: '세션(Session)', description: '서버 측에 사용자 인증 상태를 저장해 요청 간 상태를 유지하는 방식입니다.' },
  { id: 25, term: 'JWT', description: '토큰 기반 인증 방식으로 Header.Payload.Signature 구조를 가집니다.' },
  { id: 26, term: '입력값 검증', description: '클라이언트와 서버 모두에서 유효성 검사를 수행해 오류와 보안 취약점을 줄입니다.' },
  { id: 27, term: '예외 처리', description: '런타임 오류를 포착해 서비스 중단을 방지하고 사용자 친화적인 메시지를 제공합니다.' },
  { id: 28, term: '단위 테스트', description: '함수/모듈 단위로 동작을 검증해 결함을 조기에 발견하는 테스트입니다.' },
  { id: 29, term: '통합 테스트', description: '모듈 간 연동을 검증해 인터페이스 문제와 데이터 흐름 오류를 확인합니다.' },
  { id: 30, term: '회귀 테스트', description: '기능 변경 후 기존 기능이 정상 동작하는지 재검증하는 테스트입니다.' },
  { id: 31, term: '형상 관리', description: '소스/문서 변경 이력을 체계적으로 관리해 추적성과 협업 품질을 높입니다.' },
  { id: 32, term: '브랜치 전략', description: 'Git에서 기능 개발, 배포, 긴급 수정 흐름을 정의하는 협업 규칙입니다.' },
  { id: 33, term: 'CI/CD', description: '코드 통합, 빌드, 테스트, 배포를 자동화해 릴리스 속도와 안정성을 높입니다.' },
  { id: 34, term: '로그 모니터링', description: '운영 로그를 수집/분석해 장애 징후를 조기에 탐지하고 원인을 추적합니다.' },
  { id: 35, term: '성능 튜닝', description: '병목 구간 분석 후 쿼리, 캐시, 코드, 인프라를 개선해 응답 시간을 최적화합니다.' },
  { id: 36, term: '취약점 점검', description: 'SQL Injection, XSS, CSRF 등 보안 취약점을 사전에 식별하고 대응합니다.' },
];

function shuffleArray(list) {
  const copied = [...list];
  for (let i = copied.length - 1; i > 0; i -= 1) {
    const randomIndex = Math.floor(Math.random() * (i + 1));
    [copied[i], copied[randomIndex]] = [copied[randomIndex], copied[i]];
  }
  return copied;
}

export default function App() {
  const [cards, setCards] = useState(() =>
    TERMS.map((item) => ({ ...item, isFlipped: false, known: null }))
  );
  const [onlyUnknown, setOnlyUnknown] = useState(false);

  const visibleCards = useMemo(() => {
    if (!onlyUnknown) {
      return cards;
    }
    return cards.filter((card) => card.known === false);
  }, [cards, onlyUnknown]);

  const knownCount = cards.filter((card) => card.known === true).length;
  const unknownCount = cards.filter((card) => card.known === false).length;

  const toggleCard = (id) => {
    setCards((prev) =>
      prev.map((card) =>
        card.id === id ? { ...card, isFlipped: !card.isFlipped } : card
      )
    );
  };

  const markCard = (id, known) => {
    setCards((prev) =>
      prev.map((card) =>
        card.id === id ? { ...card, known, isFlipped: false } : card
      )
    );
  };

  const shuffleCards = () => {
    setCards((prev) => shuffleArray(prev).map((card) => ({ ...card, isFlipped: false })));
  };

  const resetProgress = () => {
    setCards(TERMS.map((item) => ({ ...item, isFlipped: false, known: null })));
    setOnlyUnknown(false);
  };

  return (
    <main className="app">
      <h1>정보처리기사 용어 암기</h1>
      <p className="guide">
        카드를 클릭하면 설명이 뒤집혀 보여요. 학습 후 아는/모르는 단어를 선택해보세요.
      </p>

      <section className="toolbar">
        <button type="button" onClick={shuffleCards}>
          카드 섞기
        </button>
        <button
          type="button"
          className={onlyUnknown ? 'active' : ''}
          onClick={() => setOnlyUnknown((prev) => !prev)}
        >
          {onlyUnknown ? '전체 보기' : '모르는 단어만 보기'}
        </button>
        <button type="button" onClick={resetProgress}>
          학습 초기화
        </button>
      </section>

      <p className="status">
        전체 {cards.length}개 / 아는 단어 {knownCount}개 / 모르는 단어 {unknownCount}개
      </p>

      <section className="card-list">
        {visibleCards.length === 0 ? (
          <p className="empty">모르는 단어가 없습니다. 잘하고 있어요!</p>
        ) : (
          visibleCards.map((card) => (
            <FlashCard
              key={card.id}
              card={card}
              onFlip={() => toggleCard(card.id)}
              onMarkKnown={() => markCard(card.id, true)}
              onMarkUnknown={() => markCard(card.id, false)}
            />
          ))
        )}
      </section>
    </main>
  );
}
