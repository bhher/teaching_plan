// 이미지 슬라이더 - 슬라이드 효과 (순수 JavaScript) - 무한 루프 버전

document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('slider');
    const slides = slider.querySelectorAll('li');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const stopBtn = document.getElementById('stopBtn');
    const currentSlideSpan = document.getElementById('currentSlide');
    const totalSlidesSpan = document.getElementById('totalSlides');
    
    const slideCount = slides.length; // 실제 슬라이드 개수 (7개)
    let currentIndex = 1; // 현재 인덱스 (1번부터 시작, 0번은 복제본)
    let autoPlayInterval = null;
    let isAutoPlay = true;
    const slideWidth = 960; // article 너비와 동일
    
    // 1. 무한 루프를 위한 앞뒤 복제본 생성 및 추가
    const firstClone = slides[0].cloneNode(true);
    const lastClone = slides[slideCount - 1].cloneNode(true);
    
    slider.appendChild(firstClone); // 마지막 뒤에 첫 번째 복제본 추가
    slider.insertBefore(lastClone, slides[0]); // 첫 번째 앞에 마지막 복제본 추가
    
    // 복제 후 모든 슬라이드 다시 선택 (복제본 포함)
    const allSlides = slider.querySelectorAll('li');
    
    // 전체 슬라이드 개수 설정 (실제 개수만)
    totalSlidesSpan.textContent = slideCount;
    
    // 2. 초기 위치 설정 (실제 첫 번째 슬라이드가 보이도록)
    // 인덱스 1 = 실제 첫 번째 슬라이드 (0번은 복제본)
    showSlide(1, false);
    
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
    
    // 3. 무한 루프 '순간 점프' 로직 (transitionend 이벤트)
    slider.addEventListener('transitionend', function() {
        // 마지막 복제본(0번)에 도달하면 실제 마지막(slideCount번) 위치로 순간 이동
        if (currentIndex === 0) {
            currentIndex = slideCount; // currentIndex 업데이트
            showSlide(slideCount, false); // 애니메이션 없이 점프
        }
        // 첫 번째 복제본(slideCount + 1번)에 도달하면 실제 첫 번째(1번) 위치로 순간 이동
        else if (currentIndex === slideCount + 1) {
            currentIndex = 1; // currentIndex 업데이트
            showSlide(1, false); // 애니메이션 없이 점프
        }
    });
    
    // 이전 슬라이드로 이동
    function goToPrevSlide() {
        currentIndex--;
        showSlide(currentIndex, true);
    }
    
    // 다음 슬라이드로 이동
    function goToNextSlide() {
        currentIndex++;
        showSlide(currentIndex, true);
    }
    
    // 특정 슬라이드 표시 (슬라이드 효과)
    function showSlide(index, withTransition = true) {
        // currentIndex 업데이트
        currentIndex = index;
        
        // transform을 사용하여 슬라이드 이동
        const translateX = -index * slideWidth;
        
        // transition 제어
        if (withTransition) {
            slider.style.transition = 'transform 0.5s ease-in-out';
        } else {
            slider.style.transition = 'none'; // 애니메이션 없이 즉시 이동
        }
        
        slider.style.transform = `translateX(${translateX}px)`;
        
        // 현재 슬라이드 번호 업데이트 (복제본 제외)
        // 인덱스를 실제 슬라이드 번호로 변환
        let displayIndex;
        if (index === 0) {
            displayIndex = slideCount; // 마지막 복제본 = 실제 마지막
        } else if (index === slideCount + 1) {
            displayIndex = 1; // 첫 번째 복제본 = 실제 첫 번째
        } else {
            displayIndex = index; // 실제 슬라이드
        }
        currentSlideSpan.textContent = displayIndex;
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
    
    // 마우스 호버 시 자동 재생 일시 정지
    slider.parentElement.addEventListener('mouseenter', function() {
        if (isAutoPlay) {
            clearInterval(autoPlayInterval);
        }
    });
    
    slider.parentElement.addEventListener('mouseleave', function() {
        if (isAutoPlay) {
            startAutoPlay();
        }
    });
});
