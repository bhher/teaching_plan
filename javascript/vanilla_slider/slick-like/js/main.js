// Slick-like 슬라이더 - Vanilla JS (jQuery 없이 구현)

document.addEventListener('DOMContentLoaded', function() {
    const slider = document.getElementById('slider');
    const slides = slider.querySelectorAll('.slide');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const dotsContainer = document.getElementById('dots');
    
    let currentIndex = 0;
    let slidesToShow = getSlidesToShow(); // 화면 크기에 따라 변경
    let isDragging = false;
    let startX = 0;
    let currentX = 0;
    let translateX = 0;
    
    // 화면 크기에 따라 표시할 슬라이드 개수 결정
    function getSlidesToShow() {
        if (window.innerWidth >= 1024) {
            return 3; // 데스크톱: 3개
        } else if (window.innerWidth >= 768) {
            return 2; // 태블릿: 2개
        } else {
            return 1; // 모바일: 1개
        }
    }
    
    // 총 슬라이드 그룹 수 계산
    function getTotalGroups() {
        return Math.max(1, slides.length - slidesToShow + 1);
    }
    
    // 슬라이드 이동 함수
    function goToSlide(index) {
        const totalGroups = getTotalGroups();
        
        if (index < 0) {
            currentIndex = totalGroups - 1;
        } else if (index >= totalGroups) {
            currentIndex = 0;
        } else {
            currentIndex = index;
        }
        
        updateSlider();
        updateDots();
        updateButtons();
    }
    
    // 슬라이더 업데이트
    function updateSlider() {
        const slideWidth = 100 / slidesToShow;
        translateX = -currentIndex * slideWidth;
        slider.style.transform = `translateX(${translateX}%)`;
    }
    
    // Dots 생성 및 업데이트
    function createDots() {
        dotsContainer.innerHTML = '';
        const totalGroups = getTotalGroups();
        
        for (let i = 0; i < totalGroups; i++) {
            const dot = document.createElement('button');
            dot.className = 'dot';
            if (i === currentIndex) {
                dot.classList.add('active');
            }
            dot.addEventListener('click', function() {
                goToSlide(i);
            });
            dotsContainer.appendChild(dot);
        }
    }
    
    // Dots 업데이트
    function updateDots() {
        const dots = dotsContainer.querySelectorAll('.dot');
        dots.forEach(function(dot, index) {
            dot.classList.toggle('active', index === currentIndex);
        });
    }
    
    // 버튼 상태 업데이트 (무한 루프이므로 항상 활성화)
    function updateButtons() {
        // 무한 루프이므로 버튼은 항상 활성화
        prevBtn.disabled = false;
        nextBtn.disabled = false;
    }
    
    // 이전 버튼
    prevBtn.addEventListener('click', function() {
        goToSlide(currentIndex - 1);
    });
    
    // 다음 버튼
    nextBtn.addEventListener('click', function() {
        goToSlide(currentIndex + 1);
    });
    
    // 터치/마우스 드래그 시작
    slider.addEventListener('mousedown', function(e) {
        isDragging = true;
        startX = e.clientX;
        slider.style.cursor = 'grabbing';
        slider.style.transition = 'none';
    });
    
    slider.addEventListener('touchstart', function(e) {
        isDragging = true;
        startX = e.touches[0].clientX;
        slider.style.transition = 'none';
    });
    
    // 드래그 중
    slider.addEventListener('mousemove', function(e) {
        if (!isDragging) return;
        
        e.preventDefault();
        currentX = e.clientX;
        const diff = currentX - startX;
        const slideWidth = slider.offsetWidth / slidesToShow;
        const currentTranslate = translateX * slider.offsetWidth / 100;
        
        slider.style.transform = `translateX(${currentTranslate + diff}px)`;
    });
    
    slider.addEventListener('touchmove', function(e) {
        if (!isDragging) return;
        
        e.preventDefault();
        currentX = e.touches[0].clientX;
        const diff = currentX - startX;
        const slideWidth = slider.offsetWidth / slidesToShow;
        const currentTranslate = translateX * slider.offsetWidth / 100;
        
        slider.style.transform = `translateX(${currentTranslate + diff}px)`;
    });
    
    // 드래그 종료
    slider.addEventListener('mouseup', function(e) {
        if (!isDragging) return;
        
        isDragging = false;
        slider.style.cursor = 'grab';
        slider.style.transition = 'transform 0.5s ease';
        
        const diff = currentX - startX;
        const slideWidth = slider.offsetWidth / slidesToShow;
        const threshold = slideWidth / 3; // 1/3 이상 드래그하면 이동
        
        if (Math.abs(diff) > threshold) {
            if (diff > 0) {
                goToSlide(currentIndex - 1);
            } else {
                goToSlide(currentIndex + 1);
            }
        } else {
            updateSlider(); // 원래 위치로 복귀
        }
    });
    
    slider.addEventListener('touchend', function(e) {
        if (!isDragging) return;
        
        isDragging = false;
        slider.style.transition = 'transform 0.5s ease';
        
        const diff = currentX - startX;
        const slideWidth = slider.offsetWidth / slidesToShow;
        const threshold = slideWidth / 3;
        
        if (Math.abs(diff) > threshold) {
            if (diff > 0) {
                goToSlide(currentIndex - 1);
            } else {
                goToSlide(currentIndex + 1);
            }
        } else {
            updateSlider();
        }
    });
    
    // 마우스가 슬라이더 밖으로 나갔을 때
    slider.addEventListener('mouseleave', function() {
        if (isDragging) {
            isDragging = false;
            slider.style.cursor = 'grab';
            slider.style.transition = 'transform 0.5s ease';
            updateSlider();
        }
    });
    
    // 키보드 네비게이션
    document.addEventListener('keydown', function(e) {
        if (e.key === 'ArrowLeft') {
            goToSlide(currentIndex - 1);
        } else if (e.key === 'ArrowRight') {
            goToSlide(currentIndex + 1);
        }
    });
    
    // 리사이즈 이벤트 처리
    let resizeTimer;
    window.addEventListener('resize', function() {
        clearTimeout(resizeTimer);
        resizeTimer = setTimeout(function() {
            const newSlidesToShow = getSlidesToShow();
            if (newSlidesToShow !== slidesToShow) {
                slidesToShow = newSlidesToShow;
                createDots();
                goToSlide(0);
            }
        }, 250);
    });
    
    // 초기화
    createDots();
    updateSlider();
    updateButtons();
    
    // 커서 스타일
    slider.style.cursor = 'grab';
});
