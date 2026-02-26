// 자동 재생 슬라이더 - Vanilla JS

document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('slider');
    const slides = slider.querySelectorAll('.slide');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const playPauseBtn = document.getElementById('playPauseBtn');
    const indicators = document.querySelectorAll('.indicator');
    const sliderWrapper = document.querySelector('.slider-wrapper');
    
    let currentIndex = 0;
    const totalSlides = slides.length;
    const slideWidth = 100; // 100%
    let autoPlayInterval = null;
    let isAutoPlay = true;
    const autoPlayDelay = 3000; // 3초
    
    // 슬라이드 표시 함수
    function showSlide(index) {
        // 인덱스 범위 체크 (무한 루프)
        if (index < 0) {
            currentIndex = totalSlides - 1;
        } else if (index >= totalSlides) {
            currentIndex = 0;
        } else {
            currentIndex = index;
        }
        
        // transform으로 슬라이드 이동
        const translateX = -currentIndex * slideWidth;
        slider.style.transform = `translateX(${translateX}%)`;
        
        // 인디케이터 업데이트
        indicators.forEach(function(indicator, idx) {
            indicator.classList.toggle('active', idx === currentIndex);
        });
    }
    
    // 자동 재생 시작
    function startAutoPlay() {
        if (autoPlayInterval) {
            clearInterval(autoPlayInterval);
        }
        
        autoPlayInterval = setInterval(function() {
            showSlide(currentIndex + 1);
        }, autoPlayDelay);
        
        isAutoPlay = true;
        playPauseBtn.textContent = '⏸';
    }
    
    // 자동 재생 정지
    function stopAutoPlay() {
        if (autoPlayInterval) {
            clearInterval(autoPlayInterval);
            autoPlayInterval = null;
        }
        isAutoPlay = false;
        playPauseBtn.textContent = '▶';
    }
    
    // 자동 재생 토글
    function toggleAutoPlay() {
        if (isAutoPlay) {
            stopAutoPlay();
        } else {
            startAutoPlay();
        }
    }
    
    // 이전 버튼
    prevBtn.addEventListener('click', function() {
        stopAutoPlay();
        showSlide(currentIndex - 1);
        startAutoPlay();
    });
    
    // 다음 버튼
    nextBtn.addEventListener('click', function() {
        stopAutoPlay();
        showSlide(currentIndex + 1);
        startAutoPlay();
    });
    
    // 재생/일시정지 버튼
    playPauseBtn.addEventListener('click', function() {
        toggleAutoPlay();
    });
    
    // 인디케이터 클릭
    indicators.forEach(function(indicator, index) {
        indicator.addEventListener('click', function() {
            stopAutoPlay();
            showSlide(index);
            startAutoPlay();
        });
    });
    
    // 마우스 호버 시 일시 정지
    sliderWrapper.addEventListener('mouseenter', function() {
        if (isAutoPlay) {
            stopAutoPlay();
        }
    });
    
    sliderWrapper.addEventListener('mouseleave', function() {
        if (isAutoPlay) {
            startAutoPlay();
        }
    });
    
    // 키보드 네비게이션
    document.addEventListener('keydown', function(e) {
        if (e.key === 'ArrowLeft') {
            stopAutoPlay();
            showSlide(currentIndex - 1);
            startAutoPlay();
        } else if (e.key === 'ArrowRight') {
            stopAutoPlay();
            showSlide(currentIndex + 1);
            startAutoPlay();
        } else if (e.key === ' ') {
            e.preventDefault();
            toggleAutoPlay();
        }
    });
    
    // 초기 슬라이드 표시 및 자동 재생 시작
    showSlide(0);
    startAutoPlay();
});
