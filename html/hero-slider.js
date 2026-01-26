// Hero Slider JavaScript
(function() {
    'use strict';

    // DOM 요소 선택
    const slider = document.getElementById('heroSlider');
    const slides = slider.querySelectorAll('.slide');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const indicators = slider.querySelectorAll('.indicator');

    // 현재 슬라이드 인덱스
    let currentSlide = 0;
    let autoSlideTimer = null;

    // 슬라이드 총 개수
    const totalSlides = slides.length;

    // 슬라이드 변경 함수
    function goToSlide(index) {
        // 인덱스 범위 확인
        if (index < 0) {
            index = totalSlides - 1;
        } else if (index >= totalSlides) {
            index = 0;
        }

        // 이전 슬라이드에서 active 클래스 제거
        slides[currentSlide].classList.remove('active');
        indicators[currentSlide].classList.remove('active');

        // 새 슬라이드에 active 클래스 추가
        currentSlide = index;
        slides[currentSlide].classList.add('active');
        indicators[currentSlide].classList.add('active');
    }

    // 다음 슬라이드로 이동
    function nextSlide() {
        goToSlide(currentSlide + 1);
    }

    // 이전 슬라이드로 이동
    function prevSlide() {
        goToSlide(currentSlide - 1);
    }

    // 자동 슬라이드 시작
    function startAutoSlide() {
        autoSlideTimer = setInterval(nextSlide, 6000);
    }

    // 자동 슬라이드 중지
    function stopAutoSlide() {
        if (autoSlideTimer) {
            clearInterval(autoSlideTimer);
            autoSlideTimer = null;
        }
    }

    // 이벤트 리스너 등록
    prevBtn.addEventListener('click', function() {
        prevSlide();
        stopAutoSlide();
        startAutoSlide();
    });

    nextBtn.addEventListener('click', function() {
        nextSlide();
        stopAutoSlide();
        startAutoSlide();
    });

    // 인디케이터 클릭 이벤트
    indicators.forEach((indicator, index) => {
        indicator.addEventListener('click', function() {
            goToSlide(index);
            stopAutoSlide();
            startAutoSlide();
        });
    });

    // 마우스 호버 시 자동 슬라이드 일시 정지
    slider.addEventListener('mouseenter', stopAutoSlide);
    slider.addEventListener('mouseleave', startAutoSlide);

    // 키보드 네비게이션 (선택사항)
    document.addEventListener('keydown', function(e) {
        if (e.key === 'ArrowLeft') {
            prevSlide();
            stopAutoSlide();
            startAutoSlide();
        } else if (e.key === 'ArrowRight') {
            nextSlide();
            stopAutoSlide();
            startAutoSlide();
        }
    });

    // 초기화: 첫 번째 슬라이드 표시 및 자동 슬라이드 시작
    function init() {
        goToSlide(0);
        startAutoSlide();
    }

    // 페이지 로드 완료 시 초기화
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', init);
    } else {
        init();
    }

    // 터치 이벤트 지원 (모바일)
    let touchStartX = 0;
    let touchEndX = 0;

    slider.addEventListener('touchstart', function(e) {
        touchStartX = e.changedTouches[0].screenX;
    }, { passive: true });

    slider.addEventListener('touchend', function(e) {
        touchEndX = e.changedTouches[0].screenX;
        handleSwipe();
    }, { passive: true });

    function handleSwipe() {
        const swipeThreshold = 50;
        const diff = touchStartX - touchEndX;

        if (Math.abs(diff) > swipeThreshold) {
            if (diff > 0) {
                // 왼쪽으로 스와이프 (다음 슬라이드)
                nextSlide();
            } else {
                // 오른쪽으로 스와이프 (이전 슬라이드)
                prevSlide();
            }
            stopAutoSlide();
            startAutoSlide();
        }
    }
})();
