// 페이드 슬라이더 - Vanilla JS

document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('slider');
    const slides = slider.querySelectorAll('.slide');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const indicators = document.querySelectorAll('.indicator');
    
    let currentIndex = 0;
    const totalSlides = slides.length;
    let isTransitioning = false; // 애니메이션 중복 방지
    
    // 슬라이드 표시 함수
    function showSlide(index) {
        if (isTransitioning) return; // 애니메이션 중이면 무시
        
        // 인덱스 범위 체크
        if (index < 0) {
            currentIndex = totalSlides - 1;
        } else if (index >= totalSlides) {
            currentIndex = 0;
        } else {
            currentIndex = index;
        }
        
        isTransitioning = true;
        
        // 모든 슬라이드에서 active 제거
        slides.forEach(function(slide) {
            slide.classList.remove('active');
        });
        
        // 현재 슬라이드에 active 추가
        slides[currentIndex].classList.add('active');
        
        // 인디케이터 업데이트
        indicators.forEach(function(indicator, idx) {
            indicator.classList.toggle('active', idx === currentIndex);
        });
        
        // 애니메이션 완료 후 플래그 해제
        setTimeout(function() {
            isTransitioning = false;
        }, 800); // CSS transition 시간과 동일
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
