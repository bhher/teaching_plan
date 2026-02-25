// 슬라이드 효과 슬라이더 - Vanilla JS

document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('slider');
    const slides = slider.querySelectorAll('.slide');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const indicators = document.querySelectorAll('.indicator');
    
    let currentIndex = 0;
    const totalSlides = slides.length;
    const slideWidth = 100; // 100%
    
    // 슬라이드 표시 함수
    function showSlide(index) {
        // 인덱스 범위 체크
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
    
    // 이전 버튼
    prevBtn.addEventListener('click', function() {
        showSlide(currentIndex - 1);
    });
    
    // 다음 버튼
    nextBtn.addEventListener('click', function() {
        showSlide(currentIndex + 1);
    });
    
    // 인디케이터 클릭
    indicators.forEach(function(indicator, index) {
        indicator.addEventListener('click', function() {
            showSlide(index);
        });
    });
    
    // 키보드 네비게이션
    document.addEventListener('keydown', function(e) {
        if (e.key === 'ArrowLeft') {
            showSlide(currentIndex - 1);
        } else if (e.key === 'ArrowRight') {
            showSlide(currentIndex + 1);
        }
    });
    
    // 초기 슬라이드 표시
    showSlide(0);
});
