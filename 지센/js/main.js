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
	
	// Items Fade-in Animation on Scroll
	function checkItemsVisible() {
		$('.item').each(function() {
			var elementTop = $(this).offset().top;
			var elementBottom = elementTop + $(this).outerHeight();
			var viewportTop = $(window).scrollTop();
			var viewportBottom = viewportTop + $(window).height();
			
			if (elementBottom > viewportTop && elementTop < viewportBottom) {
				$(this).addClass('visible');
			}
		});
	}
	
	// Add CSS class for fade-in effect
	$('.item').css({
		'opacity': '0',
		'transform': 'translateY(30px)',
		'transition': 'opacity 0.6s ease, transform 0.6s ease'
	});
	
	// Add visible class styling
	$(window).on('scroll resize', checkItemsVisible);
	$(window).trigger('scroll');
	
	// Apply visible class styles dynamically
	$(document).on('DOMNodeInserted', '.item.visible', function() {
		$(this).css({
			'opacity': '1',
			'transform': 'translateY(0)'
		});
	});
	
	// Alternative: use CSS classes instead
	$('.item').each(function(index) {
		$(this).css('transition-delay', (index * 0.1) + 's');
	});
	
	$(window).on('scroll resize', function() {
		$('.item').each(function() {
			var elementTop = $(this).offset().top;
			var elementBottom = elementTop + $(this).outerHeight();
			var viewportTop = $(window).scrollTop();
			var viewportBottom = viewportTop + $(window).height();
			
			if (elementBottom > viewportTop && elementTop < viewportBottom) {
				$(this).css({
					'opacity': '1',
					'transform': 'translateY(0)'
				});
			}
		});
	});
	
});