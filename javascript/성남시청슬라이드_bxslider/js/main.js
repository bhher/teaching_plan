$(function(){
  var slider = $('.bxslider').bxSlider({
    mode: 'horizontal',
    speed: 500,
    auto: true,
    autoHover: true,
    pause: 3000,
    controls: false,
    pager: false,
    onSlideAfter: function($slideElement, oldIndex, newIndex){
      // 슬라이드 변경 시 현재 슬라이드 번호 업데이트
      $('#current-slide').text(newIndex + 1);
    }
  });

  var totalSlides = slider.getSlideCount();
  $('#total-slides').text(totalSlides);

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

  // 일시정지/재생 버튼
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
