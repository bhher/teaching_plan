// 썸네일 슬라이더 - Vanilla JS

document.addEventListener('DOMContentLoaded', function() {
    const mainSlider = document.getElementById('mainSlider');
    const mainSlides = mainSlider.querySelectorAll('.slide');
    const thumbnailSlider = document.getElementById('thumbnailSlider');
    const thumbnails = thumbnailSlider.querySelectorAll('.thumbnail');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    
    let currentIndex = 0;
    const totalSlides = mainSlides.length;
    
    // 메인 슬라이드 표시 함수
    function showMainSlide(index) {
        // 인덱스 범위 체크
        if (index < 0) {
            currentIndex = totalSlides - 1;
        } else if (index >= totalSlides) {
            currentIndex = 0;
        } else {
            currentIndex = index;
        }
        
        // 모든 슬라이드에서 active 제거
        mainSlides.forEach(function(slide) {
            slide.classList.remove('active');
        });
        
        // 현재 슬라이드에 active 추가
        mainSlides[currentIndex].classList.add('active');
        
        // 썸네일 업데이트
        updateThumbnails();
    }
    
    // 썸네일 업데이트 함수
    function updateThumbnails() {
        thumbnails.forEach(function(thumbnail, idx) {
            thumbnail.classList.toggle('active', idx === currentIndex);
        });
        
        // 활성 썸네일이 보이도록 스크롤
        const activeThumbnail = thumbnails[currentIndex];
        if (activeThumbnail) {
            activeThumbnail.scrollIntoView({
                behavior: 'smooth',
                block: 'nearest',
                inline: 'center'
            });
        }
    }
    
    // 이전 버튼
    prevBtn.addEventListener('click', function() {
        showMainSlide(currentIndex - 1);
    });
    
    // 다음 버튼
    nextBtn.addEventListener('click', function() {
        showMainSlide(currentIndex + 1);
    });
    
    // 썸네일 클릭
    thumbnails.forEach(function(thumbnail, index) {
        thumbnail.addEventListener('click', function() {
            showMainSlide(index);
        });
    });
    
    // 키보드 네비게이션
    document.addEventListener('keydown', function(e) {
        if (e.key === 'ArrowLeft') {
            showMainSlide(currentIndex - 1);
        } else if (e.key === 'ArrowRight') {
            showMainSlide(currentIndex + 1);
        }
    });
    
    // 초기 슬라이드 표시
    showMainSlide(0);
});
