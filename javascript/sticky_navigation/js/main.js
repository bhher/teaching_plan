$(function(){
  /**
   * 스크롤 시 네비게이션 고정 기능
   * 
   * 작동 원리:
   * 1. 사용자가 페이지를 스크롤할 때마다 $(window).scroll() 이벤트가 발생
   * 2. 현재 스크롤 위치를 확인
   * 3. 특정 위치(예: 100px) 이상 스크롤하면 .active 클래스 추가
   * 4. .active 클래스가 추가되면 CSS에서 그림자 효과와 스타일 변경 적용
   */

  // 네비게이션 요소를 변수에 저장 (성능 최적화)
  var $navbar = $('#navbar');
  
  // 스크롤 위치 임계값 설정 (이 위치 이상 스크롤하면 네비게이션 활성화)
  var scrollThreshold = 100;

  /**
   * 스크롤 이벤트 핸들러
   * 
   * $(window).scroll() - 브라우저 창이 스크롤될 때마다 실행되는 이벤트
   * 
   * 작동 과정:
   * 1. $(window).scrollTop() - 현재 스크롤 위치를 픽셀 단위로 가져옴
   * 2. scrollThreshold와 비교
   * 3. 조건에 따라 .active 클래스 추가/제거
   */
  $(window).scroll(function() {
    // 현재 스크롤 위치 가져오기
    var scrollPosition = $(window).scrollTop();
    
    // 스크롤 위치가 임계값보다 크면
    if (scrollPosition > scrollThreshold) {
      // .active 클래스 추가
      $navbar.addClass('active');
    } else {
      // 스크롤 위치가 임계값보다 작으면 .active 클래스 제거
      $navbar.removeClass('active');
    }
  });

  /**
   * 모바일 메뉴 토글 기능
   * 
   * 작동 원리:
   * 1. 햄버거 메뉴 버튼 클릭 시
   * 2. .nav-menu와 .nav-toggle에 .active 클래스 토글
   * 3. CSS에서 .active 클래스에 따라 메뉴 표시/숨김
   */
  $('#navToggle').click(function() {
    // 메뉴와 토글 버튼에 active 클래스 토글
    $('.nav-menu').toggleClass('active');
    $(this).toggleClass('active');
  });

  /**
   * 부드러운 스크롤 기능
   * 
   * 네비게이션 메뉴 클릭 시 해당 섹션으로 부드럽게 이동
   */
  $('.nav-menu a').click(function(e) {
    // 기본 링크 동작 방지
    e.preventDefault();
    
    // 클릭한 링크의 href 속성에서 섹션 ID 가져오기
    var targetId = $(this).attr('href');
    
    // 해당 섹션의 위치 계산
    var targetPosition = $(targetId).offset().top - 70; // 네비게이션 높이만큼 빼기
    
    // 부드럽게 스크롤
    $('html, body').animate({
      scrollTop: targetPosition
    }, 800); // 800ms 동안 애니메이션
    
    // 모바일에서 메뉴 클릭 시 메뉴 닫기
    if ($(window).width() <= 768) {
      $('.nav-menu').removeClass('active');
      $('#navToggle').removeClass('active');
    }
  });

  /**
   * 페이지 로드 시 초기 스크롤 위치 확인
   * 
   * 페이지를 새로고침하거나 직접 URL로 접근했을 때
   * 스크롤 위치에 따라 네비게이션 상태를 설정
   */
  var initialScrollPosition = $(window).scrollTop();
  if (initialScrollPosition > scrollThreshold) {
    $navbar.addClass('active');
  }
});
