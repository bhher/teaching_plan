$(function(){
  var pop_prev = $('.slide_but p .prev');
  var pop_next = $('.slide_but p .next');
  var pop_pause = $('.slide_but p .pause');
  //초기 세팅
  var popCurrent = 0;
  var popIndex =0;
  var total = $('.pop_slide li').length //갯수 - 11 
  pop_next.click(function(){
    popIndex = popCurrent + 1;
    if(popIndex == total) popIndex = 0;
    var pop_cu = $('.pop_slide li').eq(popCurrent);//현재사진
    var pop_ne = $('.pop_slide li').eq(popIndex);//다음에 올사진

    pop_cu.css('left',0).stop().animate({'left':'-100%'});
    pop_ne.css('left','100%').stop().animate({'left':'0'});
    popCurrent = popIndex;
    //current = i;
    $('.slide_but p strong').text(popIndex+1);
    return false;
  });
  pop_prev.click(function(){
    popIndex = popCurrent - 1;
    if(popIndex == -1) popIndex = total -1;
    var pop_cu = $('.pop_slide li').eq(popCurrent);//현재사진
    var pop_ne = $('.pop_slide li').eq(popIndex);//다음에 올사진

    pop_cu.css('left',0).stop().animate({'left':'100%'});
    pop_ne.css('left','-100%').stop().animate({'left':'0'});
    popCurrent = popIndex;

    $('.slide_but p strong').text(popIndex+1);
    return false;
  });

  var id;
  popAuto();

  function popAuto(){
    id =  setInterval(function(){
      pop_next.trigger('click');
    },3000);
  }

  //일시정지, 플레이버튼 설정
  pop_pause.click(function(){
    if($(this).hasClass('on')==false){
      $(this).addClass('on');
      clearInterval(id);
    }else{
      $(this).removeClass('on');
       popAuto();
    }

  });

});