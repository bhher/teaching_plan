// 이미지 슬라이더 - 순수 JavaScript 구현

document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('slider');
    const slides = slider.querySelectorAll('li');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const stopBtn = document.getElementById('stopBtn');
    const currentSlideSpan = document.getElementById('currentSlide');
    const totalSlidesSpan = document.getElementById('totalSlides');
    
    let currentIndex = 0;
    let autoPlayInterval = null;
    let isAutoPlay = true;
    
    // 전체 슬라이드 개수 설정
    totalSlidesSpan.textContent = slides.length;
    
    // 초기 슬라이드 표시
    showSlide(currentIndex);
    
    // 자동 재생 시작
    startAutoPlay();
    
    // 이전 버튼 클릭
    prevBtn.addEventListener('click', function(e) {
        e.preventDefault();
        goToPrevSlide();
    });
    
    // 다음 버튼 클릭
    nextBtn.addEventListener('click', function(e) {
        e.preventDefault();
        goToNextSlide();
    });
    
    // 정지/재생 버튼 클릭
    stopBtn.addEventListener('click', function(e) {
        e.preventDefault();
        toggleAutoPlay();
    });
    
    // 이전 슬라이드로 이동
    function goToPrevSlide() {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = slides.length - 1;
        }
        showSlide(currentIndex);
    }
    
    // 다음 슬라이드로 이동
    function goToNextSlide() {
        currentIndex++;
        if (currentIndex >= slides.length) {
            currentIndex = 0;
        }
        showSlide(currentIndex);
    }
    
    // 특정 슬라이드 표시
    function showSlide(index) {
        // 모든 슬라이드 숨기기
        slides.forEach(function(slide) {
            slide.classList.remove('active');
        });
        
        // 현재 슬라이드 표시
        slides[index].classList.add('active');
        
        // 현재 슬라이드 번호 업데이트
        currentSlideSpan.textContent = index + 1;
    }
    
    // 자동 재생 시작
    function startAutoPlay() {
        if (autoPlayInterval) {
            clearInterval(autoPlayInterval);
        }
        
        autoPlayInterval = setInterval(function() {
            goToNextSlide();
        }, 3000); // 3초마다 자동으로 다음 슬라이드로 이동
        
        isAutoPlay = true;
        stopBtn.classList.remove('on');
    }
    
    // 자동 재생 정지
    function stopAutoPlay() {
        if (autoPlayInterval) {
            clearInterval(autoPlayInterval);
            autoPlayInterval = null;
        }
        isAutoPlay = false;
        stopBtn.classList.add('on');
    }
    
    // 자동 재생 토글
    function toggleAutoPlay() {
        if (isAutoPlay) {
            stopAutoPlay();
        } else {
            startAutoPlay();
        }
    }
    
    // 마우스 호버 시 자동 재생 일시 정지 (선택사항)
    slider.addEventListener('mouseenter', function() {
        if (isAutoPlay) {
            clearInterval(autoPlayInterval);
        }
    });
    
    slider.addEventListener('mouseleave', function() {
        if (isAutoPlay) {
            startAutoPlay();
        }
    });
});
