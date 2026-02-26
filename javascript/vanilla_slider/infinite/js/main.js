// 무한 루프 슬라이더 - Vanilla JS

document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('slider');
    const slides = slider.querySelectorAll('.slide');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const currentSlideSpan = document.getElementById('currentSlide');
    const totalSlidesSpan = document.getElementById('totalSlides');
    
    const slideCount = slides.length;
    let currentIndex = 1; // 실제 첫 번째 슬라이드 (0번은 복제본)
    const slideWidth = 100; // 100%
    
    // 전체 슬라이드 개수 설정
    totalSlidesSpan.textContent = slideCount;
    
    // 1. 무한 루프를 위한 앞뒤 복제본 생성
    const firstClone = slides[0].cloneNode(true);
    const lastClone = slides[slideCount - 1].cloneNode(true);
    
    slider.appendChild(firstClone); // 마지막에 첫 번째 복제본 추가
    slider.insertBefore(lastClone, slides[0]); // 첫 번째 앞에 마지막 복제본 추가
    
    // 2. 초기 위치 설정 (실제 첫 번째 슬라이드)
    showSlide(1, false);
    
    // 3. transitionend 이벤트로 무한 루프 구현
    slider.addEventListener('transitionend', function() {
        // 마지막 복제본(0번)에 도달하면 실제 마지막으로 점프
        if (currentIndex === 0) {
            currentIndex = slideCount;
            showSlide(slideCount, false);
        }
        // 첫 번째 복제본(slideCount+1번)에 도달하면 실제 첫 번째로 점프
        else if (currentIndex === slideCount + 1) {
            currentIndex = 1;
            showSlide(1, false);
        }
    });
    
    // 이전 버튼
    prevBtn.addEventListener('click', function() {
        currentIndex--;
        showSlide(currentIndex, true);
    });
    
    // 다음 버튼
    nextBtn.addEventListener('click', function() {
        currentIndex++;
        showSlide(currentIndex, true);
    });
    
    // 슬라이드 표시 함수
    function showSlide(index, withTransition = true) {
        currentIndex = index;
        
        const translateX = -index * slideWidth;
        
        if (withTransition) {
            slider.style.transition = 'transform 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
        } else {
            slider.style.transition = 'none';
        }
        
        slider.style.transform = `translateX(${translateX}%)`;
        
        // 실제 슬라이드 번호 표시
        let displayIndex;
        if (index === 0) {
            displayIndex = slideCount;
        } else if (index === slideCount + 1) {
            displayIndex = 1;
        } else {
            displayIndex = index;
        }
        currentSlideSpan.textContent = displayIndex;
    }
    
    // 키보드 네비게이션
    document.addEventListener('keydown', function(e) {
        if (e.key === 'ArrowLeft') {
            currentIndex--;
            showSlide(currentIndex, true);
        } else if (e.key === 'ArrowRight') {
            currentIndex++;
            showSlide(currentIndex, true);
        }
    });
});
