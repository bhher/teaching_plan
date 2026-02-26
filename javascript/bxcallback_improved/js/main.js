$(function(){
  // 슬라이더 초기화
  var slider = $('.slider').bxSlider({
    auto: true,
    controls: false,
    autoHover: true,
    infiniteLoop: true,
    speed: 500,
    pause: 3000,
    
    // 초기화 완료 콜백
    onSliderLoad: function() {
      var currentIndex = slider.getCurrentSlide();
      updatePageIndicator();
      updateTextAnimation(currentIndex);
    },
    
    // 슬라이드 변경 전 콜백
    onSlideBefore: function() {
      updatePageIndicator();
    },
    
    // 슬라이드 변경 후 콜백
    onSlideAfter: function($slideElement, oldIndex, newIndex) {
      updateTextAnimation(newIndex);
    }
  });

  // 페이지 인디케이터 업데이트 함수
  function updatePageIndicator() {
    var currentIndex = slider.getCurrentSlide();
    var itemWidth = $('#page ul li').width();
    
    // 활성 클래스 업데이트
    $('#page ul li').removeClass('on');
    $('#page ul li').eq(currentIndex).addClass('on');
    
    // 포커스 인디케이터 이동
    var leftPosition = currentIndex * itemWidth;
    $('#focus').stop().animate({
      left: leftPosition
    }, 600);
  }

  // 텍스트 애니메이션 업데이트 함수
  function updateTextAnimation(currentIndex) {
    // 모든 텍스트에서 'on' 클래스 제거
    $('.slider li h1').removeClass('on');
    
    // 현재 슬라이드의 텍스트에 'on' 클래스 추가
    $('.slider li').eq(currentIndex).find('h1').addClass('on');
  }

  // 이전 버튼 클릭
  $('.left_btn').click(function(e) {
    e.preventDefault();
    slider.goToPrevSlide();
    return false;
  });

  // 다음 버튼 클릭
  $('.right_btn').click(function(e) {
    e.preventDefault();
    slider.goToNextSlide();
    return false;
  });

  // 페이지네이션 클릭
  $('#page ul li').click(function(e) {
    e.preventDefault();
    var targetIndex = $(this).index();
    slider.goToSlide(targetIndex);
    return false;
  });
  
  // 초기화 후 텍스트 애니메이션 실행 (이중 보장)
  setTimeout(function() {
    var currentIndex = slider.getCurrentSlide();
    updateTextAnimation(currentIndex);
  }, 100);
});
