$(function(){
  // ============================================
  // 1단계: BxSlider 초기화 (20점)
  // ============================================
  var slider = $('.bxslider').bxSlider({
    mode: 'horizontal',
    speed: 500,
    auto: true,
    autoHover: true,
    pause: 3000,
    controls: false,
    pager: false,
    onSlideAfter: function($slideElement, oldIndex, newIndex){
      // 2단계: 슬라이드 번호 업데이트
      $('#current-slide').text(newIndex + 1);
    }
  });

  // ============================================
  // 2단계: 슬라이드 번호 표시 (20점)
  // ============================================
  var totalSlides = slider.getSlideCount();
  $('#total-slides').text(totalSlides);

  // ============================================
  // 3단계: 이전/다음 버튼 구현 (30점)
  // ============================================
  // 이전 버튼
  $('.prev').click(function(e){
    e.preventDefault();
    slider.goToPrevSlide();
    return false;
  });

  // 다음 버튼
  $('.next').click(function(e){
    e.preventDefault();
    slider.goToNextSlide();
    return false;
  });

  // ============================================
  // 4단계: 일시정지/재생 버튼 구현 (30점)
  // ============================================
  var isPaused = false;
  $('.pause').click(function(e){
    e.preventDefault();
    if(!isPaused){
      slider.stopAuto();
      $(this).addClass('on');
      isPaused = true;
    } else {
      slider.startAuto();
      $(this).removeClass('on');
      isPaused = false;
    }
    return false;
  });
});
