import { useState, useEffect } from 'react'

/**
 * useEffect + scroll 간단 예제
 *
 * 1) 컴포넌트가 화면에 나타나면 scroll 이벤트를 등록
 * 2) scrollY > 50 이면 헤더 스타일 변경
 * 3) Cleanup에서 removeEventListener (메모리 누수 방지)
 */
function App() {
  const [isScrolled, setIsScrolled] = useState(false)
  const [scrollY, setScrollY] = useState(0)

  useEffect(() => {
    const handleScroll = () => {
      const y = window.scrollY
      setScrollY(Math.round(y))
      setIsScrolled(y > 50)
    }

    window.addEventListener('scroll', handleScroll)
    console.log('✅ scroll 이벤트 등록')

    // Cleanup: 컴포넌트가 사라질 때 리스너 제거
    return () => {
      window.removeEventListener('scroll', handleScroll)
      console.log('🧹 scroll 이벤트 제거')
    }
  }, []) // 처음 한 번만 등록

  return (
    <div>
      <header className={`header ${isScrolled ? 'scrolled' : 'top'}`}>
        <div className="logo">SCROLL DEMO</div>
        <nav>
          <a href="#s1">소개</a>
          <a href="#s2">내용</a>
          <a href="#s3">정리</a>
        </nav>
      </header>

      <section className="hero">
        <h1>아래로 스크롤 해보세요</h1>
        <p>
          50px 이상 내리면 헤더가 투명 → 흰 배경으로 바뀝니다.
          <br />
          (useEffect로 scroll 이벤트를 등록했습니다)
        </p>
      </section>

      <div className="content">
        <section id="s1">
          <h2>1. 무엇을 하나요?</h2>
          <p>
            화면이 처음 그려진 뒤 <code>useEffect</code>가 scroll 리스너를 붙입니다.
            스크롤할 때마다 <code>window.scrollY</code>를 읽고 state를 바꿉니다.
          </p>
        </section>

        <section id="s2">
          <h2>2. 왜 Cleanup이 필요한가?</h2>
          <p>
            컴포넌트가 없어져도 리스너가 남으면 메모리 누수가 납니다.
            <code>return () =&gt; removeEventListener(...)</code> 로 꼭 지웁니다.
          </p>
        </section>

        <section id="s3">
          <h2>3. 핵심 코드</h2>
          <pre className="code-hint">{`useEffect(() => {
  const handleScroll = () => {
    setIsScrolled(window.scrollY > 50);
  };
  window.addEventListener("scroll", handleScroll);
  return () => window.removeEventListener("scroll", handleScroll);
}, []);`}</pre>
        </section>

        <section>
          <h2>더미 텍스트 (스크롤용)</h2>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod
            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
            veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
            commodo consequat.
          </p>
          <p style={{ marginTop: '1rem' }}>
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
            dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
            proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </p>
          <p style={{ marginTop: '1rem' }}>
            스크롤을 더 내려도 헤더는 고정(fixed)되어 있고, scrolled 스타일이
            유지됩니다. 맨 위로 올리면 다시 투명 헤더로 돌아갑니다.
          </p>
        </section>
      </div>

      <div className="scroll-info">scrollY: {scrollY}px · {isScrolled ? 'scrolled' : 'top'}</div>
    </div>
  )
}

export default App
