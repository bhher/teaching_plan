/**
 * Navigation Module
 * 스크롤 기반 네비게이션 제어 모듈
 * 
 * 기능:
 * 1. 스크롤 시 네비게이션 sticky 처리
 * 2. 스크롤 방향 감지 (아래/위)
 * 3. 배경색 변경 (투명 → 흰색)
 * 4. 성능 최적화 (throttle 사용)
 */

(function() {
  'use strict';

  // ============================================
  // Configuration
  // ============================================
  const CONFIG = {
    scrollThreshold: 100,        // 활성화되는 스크롤 위치 (px)
    throttleDelay: 100,         // 스크롤 이벤트 throttle 지연 시간 (ms)
    navbarSelector: '#navbar',  // 네비게이션 선택자
    activeClass: 'active',       // 활성화 클래스명
    hiddenClass: 'hidden'       // 숨김 클래스명
  };

  // ============================================
  // State Management
  // ============================================
  const state = {
    lastScrollTop: 0,           // 이전 스크롤 위치
    isScrollingDown: false,      // 스크롤 방향 (아래: true, 위: false)
    isNavbarActive: false,       // 네비게이션 활성화 상태
    isNavbarHidden: false        // 네비게이션 숨김 상태
  };

  // ============================================
  // DOM Elements
  // ============================================
  const navbar = document.querySelector(CONFIG.navbarSelector);

  if (!navbar) {
    console.error('Navigation element not found');
    return;
  }

  // ============================================
  // Utility Functions
  // ============================================

  /**
   * Throttle 함수
   * 성능 최적화를 위해 함수 실행 빈도 제한
   * 
   * @param {Function} func - 실행할 함수
   * @param {number} delay - 지연 시간 (ms)
   * @returns {Function} - throttle된 함수
   */
  function throttle(func, delay) {
    let timeoutId;
    let lastExecTime = 0;

    return function(...args) {
      const currentTime = Date.now();

      if (currentTime - lastExecTime > delay) {
        func.apply(this, args);
        lastExecTime = currentTime;
      } else {
        clearTimeout(timeoutId);
        timeoutId = setTimeout(() => {
          func.apply(this, args);
          lastExecTime = Date.now();
        }, delay - (currentTime - lastExecTime));
      }
    };
  }

  /**
   * 현재 스크롤 위치 가져오기
   * 
   * @returns {number} - 스크롤 위치 (px)
   */
  function getScrollTop() {
    return window.pageYOffset || document.documentElement.scrollTop || 0;
  }

  // ============================================
  // Navigation Control Functions
  // ============================================

  /**
   * 네비게이션 활성화/비활성화
   * 스크롤 위치에 따라 배경색 변경
   * 
   * @param {boolean} activate - 활성화 여부
   */
  function toggleNavbarActive(activate) {
    if (activate === state.isNavbarActive) return;

    state.isNavbarActive = activate;

    if (activate) {
      navbar.classList.add(CONFIG.activeClass);
    } else {
      navbar.classList.remove(CONFIG.activeClass);
    }
  }

  /**
   * 네비게이션 표시/숨김
   * 스크롤 방향에 따라 네비게이션 표시/숨김 처리
   * 
   * @param {boolean} hide - 숨김 여부
   */
  function toggleNavbarVisibility(hide) {
    if (hide === state.isNavbarHidden) return;

    state.isNavbarHidden = hide;

    if (hide) {
      navbar.classList.add(CONFIG.hiddenClass);
    } else {
      navbar.classList.remove(CONFIG.hiddenClass);
    }
  }

  /**
   * 스크롤 방향 감지
   * 
   * @param {number} currentScrollTop - 현재 스크롤 위치
   * @returns {boolean} - 아래로 스크롤 중이면 true
   */
  function detectScrollDirection(currentScrollTop) {
    const scrollingDown = currentScrollTop > state.lastScrollTop;
    state.lastScrollTop = currentScrollTop;
    return scrollingDown;
  }

  /**
   * 스크롤 이벤트 핸들러
   * 메인 로직 처리
   */
  function handleScroll() {
    const currentScrollTop = getScrollTop();
    const scrollingDown = detectScrollDirection(currentScrollTop);

    // 스크롤 위치에 따른 활성화 처리
    const shouldActivate = currentScrollTop > CONFIG.scrollThreshold;
    toggleNavbarActive(shouldActivate);

    // 스크롤 방향에 따른 표시/숨김 처리
    // 맨 위에서는 항상 표시
    if (currentScrollTop < CONFIG.scrollThreshold) {
      toggleNavbarVisibility(false);
    } else {
      // 아래로 스크롤 중이고, 네비게이션이 숨겨지지 않은 상태일 때만 숨김
      if (scrollingDown && !state.isNavbarHidden) {
        toggleNavbarVisibility(true);
      }
      // 위로 스크롤 중이면 표시
      else if (!scrollingDown && state.isNavbarHidden) {
        toggleNavbarVisibility(false);
      }
    }
  }

  // ============================================
  // Event Listeners
  // ============================================

  /**
   * 초기화 함수
   */
  function init() {
    // 초기 스크롤 위치 설정
    state.lastScrollTop = getScrollTop();

    // 초기 상태 확인
    if (state.lastScrollTop > CONFIG.scrollThreshold) {
      toggleNavbarActive(true);
    }

    // 스크롤 이벤트 리스너 등록 (throttle 적용)
    const throttledHandleScroll = throttle(handleScroll, CONFIG.throttleDelay);
    window.addEventListener('scroll', throttledHandleScroll, { passive: true });

    // 페이지 로드 시 한 번 실행
    handleScroll();
  }

  // ============================================
  // Public API
  // ============================================
  const Navigation = {
    init: init,
    getState: function() {
      return Object.assign({}, state);
    }
  };

  // DOM이 로드되면 초기화
  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', init);
  } else {
    init();
  }

  // 전역 객체로 export (필요시)
  window.Navigation = Navigation;

})();
