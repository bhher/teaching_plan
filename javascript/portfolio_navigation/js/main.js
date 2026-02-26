/**
 * Main Application Script
 * 메인 애플리케이션 로직
 */

(function() {
  'use strict';

  // ============================================
  // Mobile Menu Toggle
  // ============================================
  const navbarToggle = document.getElementById('navbarToggle');
  const navbarMenu = document.getElementById('navbarMenu');

  if (navbarToggle && navbarMenu) {
    navbarToggle.addEventListener('click', function() {
      navbarToggle.classList.toggle('active');
      navbarMenu.classList.toggle('active');
    });

    // 메뉴 링크 클릭 시 모바일 메뉴 닫기
    const navbarLinks = navbarMenu.querySelectorAll('.navbar-link');
    navbarLinks.forEach(function(link) {
      link.addEventListener('click', function() {
        if (window.innerWidth <= 768) {
          navbarToggle.classList.remove('active');
          navbarMenu.classList.remove('active');
        }
      });
    });
  }

  // ============================================
  // Smooth Scroll
  // ============================================
  const smoothScrollLinks = document.querySelectorAll('a[href^="#"]');

  smoothScrollLinks.forEach(function(link) {
    link.addEventListener('click', function(e) {
      const href = this.getAttribute('href');
      
      // #만 있는 경우는 처리하지 않음
      if (href === '#') return;

      const target = document.querySelector(href);
      
      if (target) {
        e.preventDefault();
        
        const navbarHeight = 70; // 네비게이션 높이
        const targetPosition = target.offsetTop - navbarHeight;

        window.scrollTo({
          top: targetPosition,
          behavior: 'smooth'
        });
      }
    });
  });

  // ============================================
  // Active Menu Item Highlight
  // ============================================
  const sections = document.querySelectorAll('.section');
  const navbarLinks = document.querySelectorAll('.navbar-link');

  function highlightActiveSection() {
    const scrollPosition = window.pageYOffset + 100;

    sections.forEach(function(section, index) {
      const sectionTop = section.offsetTop;
      const sectionHeight = section.offsetHeight;
      const sectionId = section.getAttribute('id');

      if (scrollPosition >= sectionTop && scrollPosition < sectionTop + sectionHeight) {
        navbarLinks.forEach(function(link) {
          link.classList.remove('active');
          if (link.getAttribute('href') === '#' + sectionId) {
            link.classList.add('active');
          }
        });
      }
    });
  }

  // 스크롤 시 활성 섹션 하이라이트 (throttle 적용)
  let ticking = false;
  window.addEventListener('scroll', function() {
    if (!ticking) {
      window.requestAnimationFrame(function() {
        highlightActiveSection();
        ticking = false;
      });
      ticking = true;
    }
  }, { passive: true });

  // 초기 실행
  highlightActiveSection();

})();
