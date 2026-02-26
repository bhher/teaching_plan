/**
 * 스크롤 애니메이션 웹사이트
 * 
 * 주요 기능:
 * 1. 스크롤 시 헤더와 네비게이션 고정
 * 2. 스크롤 위치에 따른 섹션별 메뉴 하이라이트
 * 3. 스크롤 위치에 따른 요소 애니메이션
 * 4. 부드러운 스크롤 이동
 */

$(function(){
    'use strict';

    // ============================================
    // Configuration (설정)
    // ============================================
    const CONFIG = {
        scrollThreshold: 10,           // 헤더 고정되는 스크롤 위치
        animationOffset: 100,         // 애니메이션 시작 오프셋
        throttleDelay: 50             // 스크롤 이벤트 throttle 지연 시간
    };

    // ============================================
    // DOM Elements (DOM 요소 캐싱)
    // ============================================
    const $window = $(window);
    const $headerTop = $('.header-top');
    const $headerMiddle = $('.header-middle');
    const $navbar = $('.navbar');
    const $sections = $('.container > .section');
    const $navbarItems = $('.navbar-item');
    const $scrollPosition = $('#scrollPosition');
    const $productLeft = $('.product-left');
    const $productRight = $('.product-right');
    const $skillCards = $('.skill-card');

    // ============================================
    // Utility Functions (유틸리티 함수)
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

    // ============================================
    // Scroll Handler (스크롤 핸들러)
    // ============================================

    /**
     * 메인 스크롤 이벤트 핸들러
     * 모든 스크롤 관련 기능을 처리
     */
    function handleScroll() {
        const scrollTop = $window.scrollTop();

        // 스크롤 위치 표시 업데이트
        $scrollPosition.text(Math.floor(scrollTop));

        // 헤더 및 네비게이션 고정 처리
        handleHeaderFixed(scrollTop);

        // 섹션별 메뉴 하이라이트
        handleMenuHighlight(scrollTop);

        // 요소 애니메이션 처리
        handleElementAnimations(scrollTop);
    }

    /**
     * 헤더 및 네비게이션 고정 처리
     * 
     * @param {number} scrollTop - 현재 스크롤 위치
     */
    function handleHeaderFixed(scrollTop) {
        if (scrollTop > CONFIG.scrollThreshold) {
            $headerTop.addClass('fixed');
            $headerMiddle.addClass('fixed');
            $navbar.addClass('fixed');
            $('.section-hero').addClass('fixed');
        } else {
            $headerTop.removeClass('fixed');
            $headerMiddle.removeClass('fixed');
            $navbar.removeClass('fixed');
            $('.section-hero').removeClass('fixed');
        }
    }

    /**
     * 섹션별 메뉴 하이라이트 처리
     * each문을 사용하여 간결하게 구현
     * 
     * @param {number} scrollTop - 현재 스크롤 위치
     */
    function handleMenuHighlight(scrollTop) {
        let activeIndex = -1;

        // each문으로 각 섹션을 순회하며 활성화된 섹션 찾기
        $sections.each(function(index) {
            const $section = $(this);
            const sectionTop = $section.offset().top;
            const sectionHeight = $section.outerHeight();
            const offset = CONFIG.animationOffset;

            // 현재 스크롤 위치가 섹션 범위 내에 있는지 확인
            if (scrollTop >= sectionTop - offset && 
                scrollTop < sectionTop + sectionHeight - offset) {
                activeIndex = index;
            }
        });

        // 활성화된 섹션이 있으면 메뉴 업데이트
        if (activeIndex >= 0) {
            $navbarItems.removeClass('on');
            $navbarItems.eq(activeIndex).addClass('on');
        }
    }

    /**
     * 요소 애니메이션 처리
     * 스크롤 위치에 따라 요소들을 애니메이션
     * 
     * @param {number} scrollTop - 현재 스크롤 위치
     */
    function handleElementAnimations(scrollTop) {
        // 제품 소개 섹션의 좌우 요소 애니메이션
        const section2Top = $('.section-product').offset().top;
        const section2Height = $('.section-product').outerHeight();
        const section2Bottom = section2Top + section2Height;

        // 왼쪽 요소 애니메이션 (섹션 진입 시)
        if (scrollTop >= section2Top - 300 && scrollTop < section2Bottom) {
            $productLeft.addClass('on');
        } else {
            $productLeft.removeClass('on');
        }

        // 오른쪽 요소 애니메이션 (약간 늦게 시작)
        if (scrollTop >= section2Top + 200 && scrollTop < section2Bottom) {
            $productRight.addClass('on');
        } else {
            $productRight.removeClass('on');
        }

        // 기술 스택 카드 순차 애니메이션
        const section4Top = $('.section-skills').offset().top;
        
        if (scrollTop >= section4Top - 300) {
            // 각 카드를 순차적으로 활성화
            $skillCards.each(function(index) {
                const $card = $(this);
                setTimeout(function() {
                    $card.addClass('active');
                }, index * 200); // 200ms 간격으로 순차 애니메이션
            });
        }
    }

    // ============================================
    // Event Listeners (이벤트 리스너)
    // ============================================

    /**
     * 스크롤 이벤트 리스너 등록
     * throttle을 적용하여 성능 최적화
     */
    const throttledHandleScroll = throttle(handleScroll, CONFIG.throttleDelay);
    $window.on('scroll', throttledHandleScroll);

    /**
     * 네비게이션 메뉴 클릭 이벤트
     * 해당 섹션으로 부드럽게 스크롤 이동
     */
    $navbarItems.on('click', function(e) {
        e.preventDefault();
        
        const index = $(this).index();
        const $targetSection = $sections.eq(index);
        
        if ($targetSection.length) {
            const targetOffset = $targetSection.offset().top - 60; // 헤더 높이 고려
            
            $('html, body').stop().animate({
                scrollTop: targetOffset
            }, 1000, 'swing');
        }
        
        return false;
    });

    // ============================================
    // Initialization (초기화)
    // ============================================

    /**
     * 페이지 로드 시 초기 상태 설정
     */
    function init() {
        // 초기 스크롤 위치 확인
        const initialScrollTop = $window.scrollTop();
        
        if (initialScrollTop > CONFIG.scrollThreshold) {
            handleHeaderFixed(initialScrollTop);
        }
        
        // 초기 메뉴 하이라이트 설정
        handleMenuHighlight(initialScrollTop);
    }

    // 초기화 실행
    init();
});
