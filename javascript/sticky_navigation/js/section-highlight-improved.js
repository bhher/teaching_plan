/**
 * 섹션별 메뉴 하이라이트 기능 (each문 사용)
 * 
 * 개선 전: 반복되는 if문 (5개)
 * 개선 후: each문으로 간결하게 처리
 */

$(function(){
  var sct = $(window).scrollTop();
  
  /**
   * 개선 전 코드 (반복)
   */
  // if(sct >= $('.container > div').eq(0).offset().top){
  //   $('nav ul li').removeClass('on');
  //   $('nav ul li').eq(0).addClass('on');
  // }
  // if(sct >= $('.container > div').eq(1).offset().top){
  //   $('nav ul li').removeClass('on');
  //   $('nav ul li').eq(1).addClass('on');
  // }
  // ... 반복 ...

  /**
   * 개선 후 코드 (each문 사용)
   * 
   * 작동 원리:
   * 1. .container > div 요소들을 each로 순회
   * 2. 각 섹션의 offset().top과 현재 스크롤 위치 비교
   * 3. 조건에 맞는 섹션의 인덱스에 해당하는 메뉴 항목에 'on' 클래스 추가
   */
  
  // 방법 1: 기본 each문 사용
  $('.container > div').each(function(index){
    if(sct >= $(this).offset().top){
      $('nav ul li').removeClass('on');
      $('nav ul li').eq(index).addClass('on');
    }
  });

  /**
   * 방법 2: 역순으로 체크 (더 정확한 방법)
   * 
   * 마지막 섹션부터 체크하여 가장 아래에 있는 섹션을 활성화
   * 여러 섹션이 동시에 조건을 만족할 때 가장 아래 섹션이 활성화됨
   */
  var activeIndex = -1;
  $('.container > div').each(function(index){
    if(sct >= $(this).offset().top - 100){ // 네비게이션 높이만큼 여유 공간
      activeIndex = index;
    }
  });
  
  if(activeIndex >= 0){
    $('nav ul li').removeClass('on');
    $('nav ul li').eq(activeIndex).addClass('on');
  }

  /**
   * 방법 3: 스크롤 이벤트와 함께 사용 (실제 사용 예시)
   */
  $(window).scroll(function(){
    var currentScroll = $(window).scrollTop();
    var activeIndex = -1;
    
    $('.container > div').each(function(index){
      var sectionTop = $(this).offset().top;
      var sectionHeight = $(this).outerHeight();
      
      // 현재 스크롤 위치가 섹션 범위 내에 있는지 확인
      if(currentScroll >= sectionTop - 100 && currentScroll < sectionTop + sectionHeight - 100){
        activeIndex = index;
      }
    });
    
    // 활성화된 섹션이 있으면 메뉴 업데이트
    if(activeIndex >= 0){
      $('nav ul li').removeClass('on');
      $('nav ul li').eq(activeIndex).addClass('on');
    }
  });
});
