$(function(){

	$(window).resize(function(){
		var w  = $(this).width();
		
		if(w <= 850){
			
		}else{
			if($('.mobile_nav').hasClass('active')){
				$('.mobile_nav').removeClass('active');
				$('.transparency').removeClass('active');
				$('.container').removeClass('active');
				$('.mobile_nav .sub').css('display','none');
		
			}

		}
		
	});
	$(window).trigger("resize");




	$(".nav ul").hover(function(){
		$(this).addClass("over");
	},function(){
		$(this).removeClass("over");
	});

	$('.mobile_tab').click(function(){
		$('.mobile_nav').addClass('active');
		$('.transparency').addClass('active');
		$('.container').addClass('active');
		
	});
	$('.transparency').click(function(){
		$('.mobile_nav').removeClass('active');
		$('.transparency').removeClass('active');
		$('.container').removeClass('active');
		$('.mobile_nav .sub').css('display','none');
		return false;
	});
	$(".mobile_nav > ul > li > a").click(function(e){
		var k = $(this).next(".sub").css("display");
			if( k == "none"){
				$(".mobile_nav .sub").slideUp(300);
				$(this).next(".sub").slideDown(300);
			}else{
				$(this).next(".sub").slideUp(300);
			}
		return false;
	});
	
	// Item View Details Button Animation
	$('.btn-view').click(function(e){
		e.preventDefault();
		var itemName = $(this).closest('.item').find('.item-name').text();
		alert('View Details: ' + itemName);
		// 실제 구현 시 상세 페이지로 이동하거나 모달 팝업을 띄울 수 있습니다.
	});
	
	// Smooth Scroll Animation for Internal Links
	$('a[href^="#"]').on('click', function(e) {
		var target = $(this.getAttribute('href'));
		if(target.length) {
			e.preventDefault();
			$('html, body').stop().animate({
				scrollTop: target.offset().top - 100
			}, 800);
		}
	});
	
	// ============================================
	// 스크롤 시 아이템 페이드인 애니메이션 (간단 버전)
	// ============================================
	
	// 1. 초기 상태 설정: 모든 아이템을 숨김 상태로
	$('.item').css({
		'opacity': '0',
		'transform': 'translateY(30px)',
		'transition': 'opacity 0.6s ease, transform 0.6s ease'
	});
	
	// 2. 각 아이템에 순차적 딜레이 추가 (선택사항)
	$('.item').each(function(index) {
		$(this).css('transition-delay', (index * 0.1) + 's');
	});
	
	// 3. 스크롤 이벤트 핸들러 (하나만 사용)
	$(window).on('scroll resize', function() {
		$('.item').each(function() {
			// 요소의 위치 정보
			var elementTop = $(this).offset().top;
			var elementBottom = elementTop + $(this).outerHeight();
			
			// 뷰포트의 위치 정보
			var viewportTop = $(window).scrollTop();
			var viewportBottom = viewportTop + $(window).height();
			
			// 요소가 뷰포트 안에 보이는지 확인
			if (elementBottom > viewportTop && elementTop < viewportBottom) {
				// 보이면 페이드인 효과 적용
				$(this).css({
					'opacity': '1',
					'transform': 'translateY(0)'
				});
			}
		});
	});
	
	// 4. 페이지 로드 시 한 번 실행 (초기 화면에 보이는 아이템 처리)
	$(window).trigger('scroll');

});
