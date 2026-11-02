import { layout, bindLayoutEvents } from '../ui/layout.js';

export function renderHome(root) {
  root.innerHTML = layout(
    `
    <section class="landing-hero">
      <div class="landing-hero-copy">
        <span class="hero-badge">✨ 네이버 블로그 아이디어 메이트</span>
        <h1>막막한 첫 문장을<br /><em>반짝이는 콘텐츠</em>로</h1>
        <p class="hero-lead">
          키워드만 알려주세요. 독자가 클릭하고 끝까지 읽는 제목과 글 구성을
          AI가 함께 설계합니다.
        </p>
        <div class="hero-cta">
          <a class="btn btn-gold" href="#/ideas">아이디어 만들기 →</a>
          <a class="btn btn-outline-light" href="#/community">다른 작가 둘러보기</a>
        </div>
        <ul class="hero-checks">
          <li>✓ 제목 5개 추천</li>
          <li>✓ 완성형 글 구성</li>
          <li>✓ 저장·복사 한 번에</li>
        </ul>
      </div>
      <div class="landing-hero-visual" aria-hidden="true">
        <div class="idea-card-mock">
          <p class="mock-label">오늘의 글감</p>
          <p class="mock-title">좋아하는 일을 꾸준히 기록하는 법</p>
          <div class="mock-lines">
            <span></span><span></span><span></span><span class="short"></span>
          </div>
          <span class="mock-pill">💡 아이디어 발견!</span>
        </div>
        <span class="mock-spark">✦</span>
      </div>
    </section>

    <section class="process-strip">
      <div class="process-steps">
        <div class="process-step">
          <span class="step-num">01</span>
          <div>
            <strong>키워드 입력</strong>
            <p>최대 10개까지</p>
          </div>
        </div>
        <div class="process-step">
          <span class="step-num">02</span>
          <div>
            <strong>제목 선택</strong>
            <p>서로 다른 5가지</p>
          </div>
        </div>
        <div class="process-step">
          <span class="step-num">03</span>
          <div>
            <strong>구성 완성</strong>
            <p>검색 의도부터 해시태그까지</p>
          </div>
        </div>
      </div>
      <a class="btn btn-deep" href="#/ideas">지금 시작하기</a>
    </section>

    <section class="section-block">
      <header class="section-head">
        <h2>아이디어가 글이 되는 가장 쉬운 과정</h2>
        <p>복잡한 프롬프트는 몰라도 괜찮아요. 당신의 생각을 담는 데만 집중하세요.</p>
      </header>
      <div class="grid-3">
        <article class="card feature-card">
          <span class="feature-icon" aria-hidden="true">01</span>
          <h3>내 주제에 딱 맞게</h3>
          <p>독자층과 글 유형, 말투까지 세밀하게 조절해요.</p>
        </article>
        <article class="card feature-card">
          <span class="feature-icon" aria-hidden="true">02</span>
          <h3>클릭하고 싶은 제목</h3>
          <p>검색 의도는 살리고 식상함은 덜어낸 제목 5개를 제안해요.</p>
        </article>
        <article class="card feature-card">
          <span class="feature-icon" aria-hidden="true">03</span>
          <h3>빈틈없는 글 구성</h3>
          <p>도입부터 소제목, 예시, 마무리와 이미지 아이디어까지 챙겨요.</p>
        </article>
      </div>
    </section>

    <section class="community-banner">
      <div class="community-banner-inner">
        <div>
          <h2>혼자 고민하지 말고, 함께 영감을 나눠요.</h2>
          <p>다른 창작자의 질문과 경험을 읽고 나만의 노하우도 공유해 보세요.</p>
          <p class="topic-tags">AI 활용 · 국내여행 · 기록 습관</p>
        </div>
        <a class="btn btn-grad" href="#/community">커뮤니티 둘러보기</a>
      </div>
    </section>
  `,
    { active: 'home', fullBleed: true }
  );
  bindLayoutEvents(root);
}
