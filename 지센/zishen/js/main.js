// 순수 JavaScript 버전 (jQuery 제거)

document.addEventListener('DOMContentLoaded', function(){

	// window resize 이벤트
	window.addEventListener('resize', function(){
		var w = window.innerWidth;
		
		if(w <= 850){
			// 모바일 사이즈
		} else {
			var mobileNav = document.querySelector('.mobile_nav');
			if(mobileNav && mobileNav.classList.contains('active')){
				mobileNav.classList.remove('active');
				document.querySelector('.transparency').classList.remove('active');
				document.querySelector('.container').classList.remove('active');
				
				// 모든 서브메뉴 닫기
				var subMenus = document.querySelectorAll('.mobile_nav .sub');
				subMenus.forEach(function(sub){
					sub.style.display = 'none';
				});
			}
		}
	});
	
	// 초기 resize 이벤트 발생
	window.dispatchEvent(new Event('resize'));

	// PC 네비게이션 hover 효과
	var navUl = document.querySelector('.nav ul');
	if(navUl){
		navUl.addEventListener('mouseenter', function(){
			this.classList.add('over');
		});
		navUl.addEventListener('mouseleave', function(){
			this.classList.remove('over');
		});
	}

	// 모바일 탭 버튼 클릭
	var mobileTab = document.querySelector('.mobile_tab');
	if(mobileTab){
		mobileTab.addEventListener('click', function(e){
			e.preventDefault();
			document.querySelector('.mobile_nav').classList.add('active');
			document.querySelector('.transparency').classList.add('active');
			document.querySelector('.container').classList.add('active');
		});
	}

	// 투명 배경 클릭 시 닫기
	var transparency = document.querySelector('.transparency');
	if(transparency){
		transparency.addEventListener('click', function(e){
			e.preventDefault();
			document.querySelector('.mobile_nav').classList.remove('active');
			this.classList.remove('active');
			document.querySelector('.container').classList.remove('active');
			
			// 모든 서브메뉴 닫기
			var subMenus = document.querySelectorAll('.mobile_nav .sub');
			subMenus.forEach(function(sub){
				sub.style.display = 'none';
			});
			return false;
		});
	}

	// slideUp 함수 (jQuery slideUp 대체)
	function slideUp(element, duration) {
		duration = duration || 300;
		element.style.display = 'block';
		element.style.height = element.scrollHeight + 'px';
		element.style.overflow = 'hidden';
		element.style.transition = 'height ' + duration + 'ms ease';
		
		// 리플로우 강제
		element.offsetHeight;
		
		requestAnimationFrame(function(){
			element.style.height = '0px';
		});
		
		setTimeout(function(){
			element.style.display = 'none';
			element.style.height = '';
			element.style.overflow = '';
			element.style.transition = '';
		}, duration);
	}

	// slideDown 함수 (jQuery slideDown 대체)
	function slideDown(element, duration) {
		duration = duration || 300;
		element.style.display = 'block';
		element.style.height = '0px';
		element.style.overflow = 'hidden';
		element.style.transition = 'height ' + duration + 'ms ease';
		
		// 리플로우 강제
		element.offsetHeight;
		
		var height = element.scrollHeight;
		requestAnimationFrame(function(){
			element.style.height = height + 'px';
		});
		
		setTimeout(function(){
			element.style.height = '';
			element.style.overflow = '';
			element.style.transition = '';
		}, duration);
	}

	// 모바일 네비게이션 메뉴 클릭
	var mobileNavLinks = document.querySelectorAll('.mobile_nav > ul > li > a');
	mobileNavLinks.forEach(function(link){
		link.addEventListener('click', function(e){
			e.preventDefault();
			var subMenu = this.nextElementSibling;
			var display = window.getComputedStyle(subMenu).display;
			
			if(display === 'none'){
				// 모든 서브메뉴 닫기
				var allSubMenus = document.querySelectorAll('.mobile_nav .sub');
				allSubMenus.forEach(function(sub){
					if(sub !== subMenu){
						slideUp(sub, 300);
					}
				});
				// 현재 서브메뉴 열기
				slideDown(subMenu, 300);
			} else {
				// 현재 서브메뉴 닫기
				slideUp(subMenu, 300);
			}
			return false;
		});
	});
	
	// Item View Details Button Animation
	var btnViews = document.querySelectorAll('.btn-view');
	btnViews.forEach(function(btn){
		btn.addEventListener('click', function(e){
			e.preventDefault();
			var item = this.closest('.item');
			var itemName = item.querySelector('.item-name').textContent;
			alert('View Details: ' + itemName);
			// 실제 구현 시 상세 페이지로 이동하거나 모달 팝업을 띄울 수 있습니다.
		});
	});
	
	// Smooth Scroll Animation for Internal Links
	var anchorLinks = document.querySelectorAll('a[href^="#"]');
	anchorLinks.forEach(function(link){
		link.addEventListener('click', function(e){
			var href = this.getAttribute('href');
			if(href && href !== '#'){
				var target = document.querySelector(href);
				if(target){
					e.preventDefault();
					var targetTop = target.getBoundingClientRect().top + window.pageYOffset - 100;
					
					// 부드러운 스크롤 애니메이션
					var startPosition = window.pageYOffset;
					var distance = targetTop - startPosition;
					var duration = 800;
					var start = null;
					
					function step(timestamp){
						if(!start) start = timestamp;
						var progress = timestamp - start;
						var percentage = Math.min(progress / duration, 1);
						
						// easing function (easeInOutQuad)
						percentage = percentage < 0.5 
							? 2 * percentage * percentage 
							: -1 + (4 - 2 * percentage) * percentage;
						
						window.scrollTo(0, startPosition + distance * percentage);
						
						if(progress < duration){
							window.requestAnimationFrame(step);
						}
					}
					
					window.requestAnimationFrame(step);
				}
			}
		});
	});
	
	// Items Fade-in Animation on Scroll
	function checkItemsVisible() {
		var items = document.querySelectorAll('.item');
		items.forEach(function(item){
			var rect = item.getBoundingClientRect();
			var elementTop = rect.top + window.pageYOffset;
			var elementBottom = elementTop + item.offsetHeight;
			var viewportTop = window.pageYOffset;
			var viewportBottom = viewportTop + window.innerHeight;
			
			if(elementBottom > viewportTop && elementTop < viewportBottom){
				item.classList.add('visible');
			}
		});
	}
	
	// 초기 아이템 스타일 설정 (CSS로 처리하되, transition delay는 JavaScript로)
	var items = document.querySelectorAll('.item');
	items.forEach(function(item, index){
		item.style.transitionDelay = (index * 0.1) + 's';
		item.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
	});
	
	// 스크롤 및 리사이즈 이벤트
	window.addEventListener('scroll', checkItemsVisible);
	window.addEventListener('resize', checkItemsVisible);
	
	// 초기 체크
	checkItemsVisible();
	
});
