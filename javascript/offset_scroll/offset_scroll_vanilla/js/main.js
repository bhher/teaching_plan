// 순수 JavaScript 버전 (jQuery 제거)

document.addEventListener('DOMContentLoaded', function(){
    
    // 쿠키 관련 함수들
    function setCookie(name, value, days) {
        var expires = "";
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + (value || "") + expires + "; path=/";
    }

    function getCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for(var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    }

    // 요소 선택
    var articles = document.querySelectorAll('section > article');
    var navLis = document.querySelectorAll('nav ul li');
    var floatdivLis = document.querySelectorAll('#floatdiv ul li');
    var floatdiv = document.getElementById('floatdiv');
    var nav = document.querySelector('nav');
    var sTop = document.getElementById('sTop');
    var popup = document.getElementById('popup');
    var noticeWrap = document.getElementById('notice_wrap');
    var expiresChk = document.getElementById('expiresChk');
    var closeBtn = document.querySelector('.closeBtn');

    // 각 article의 offset().top 값 저장
    var articleOffsets = [];
    articles.forEach(function(article){
        articleOffsets.push(article.getBoundingClientRect().top + window.pageYOffset);
    });

    // floatdiv의 초기 top 위치 저장
    var dTop = floatdiv.getBoundingClientRect().top + window.pageYOffset - window.pageYOffset;

    // 부드러운 스크롤 애니메이션 함수
    function smoothScrollTo(targetTop, duration) {
        duration = duration || 1300;
        var startPosition = window.pageYOffset;
        var distance = targetTop - startPosition;
        var startTime = null;

        function step(timestamp) {
            if (!startTime) startTime = timestamp;
            var progress = timestamp - startTime;
            var percentage = Math.min(progress / duration, 1);
            
            // easing function (easeInOutCubic)
            percentage = percentage < 0.5 
                ? 4 * percentage * percentage * percentage 
                : 1 - Math.pow(-2 * percentage + 2, 3) / 2;
            
            window.scrollTo(0, startPosition + distance * percentage);
            
            if (progress < duration) {
                window.requestAnimationFrame(step);
            }
        }
        
        window.requestAnimationFrame(step);
    }

    // 스크롤 이벤트
    var isScrolling = false;
    window.addEventListener('scroll', function(){
        if (isScrolling) return; // 애니메이션 중이면 무시
        
        var sct = window.pageYOffset || document.documentElement.scrollTop;
        
        // 스크롤 위치 표시
        sTop.textContent = Math.round(sct);
        
        // floatdiv 위치 조정 (애니메이션)
        var currentTop = floatdiv.getBoundingClientRect().top + window.pageYOffset;
        var targetTop = dTop + sct;
        
        // requestAnimationFrame으로 부드러운 이동
        function updateFloatDiv() {
            var current = floatdiv.getBoundingClientRect().top + window.pageYOffset;
            var diff = targetTop - current;
            if (Math.abs(diff) > 1) {
                floatdiv.style.top = (current - window.pageYOffset + diff * 0.1) + 'px';
                requestAnimationFrame(updateFloatDiv);
            }
        }
        updateFloatDiv();

        // nav에 'on' 클래스 추가/제거
        if(sct > 0){
            nav.classList.add('on');
        } else {
            nav.classList.remove('on');
        }

        // 현재 스크롤 위치에 따라 네비게이션 활성화
        for(var i = 0; i < articles.length; i++){
            // article의 현재 위치 재계산 (스크롤에 따라 변함)
            var articleTop = articles[i].getBoundingClientRect().top + window.pageYOffset;
            
            if(sct >= articleTop - 100){ // 약간의 여유 공간
                // 모든 on 클래스 제거
                navLis.forEach(function(li){
                    li.classList.remove('on');
                });
                floatdivLis.forEach(function(li){
                    li.classList.remove('on');
                });
                
                // 현재 섹션에 on 클래스 추가
                navLis[i].classList.add('on');
                floatdivLis[i].classList.add('on');
            }
        }
    });

    // floatdiv 클릭 이벤트
    floatdivLis.forEach(function(li, index){
        li.addEventListener('click', function(e){
            e.preventDefault();
            
            // article의 현재 위치 계산
            var articleTop = articles[index].getBoundingClientRect().top + window.pageYOffset;
            
            isScrolling = true;
            smoothScrollTo(articleTop, 1300);
            
            // 애니메이션 완료 후 플래그 해제
            setTimeout(function(){
                isScrolling = false;
            }, 1300);
            
            // 네비게이션 활성화
            navLis.forEach(function(navLi){
                navLi.classList.remove('on');
            });
            navLis[index].classList.add('on');
            
            floatdivLis.forEach(function(floatLi){
                floatLi.classList.remove('on');
            });
            floatdivLis[index].classList.add('on');
            
            return false;
        });
    });

    // nav 클릭 이벤트
    navLis.forEach(function(li, index){
        li.addEventListener('click', function(e){
            e.preventDefault();
            
            // article의 현재 위치 계산
            var articleTop = articles[index].getBoundingClientRect().top + window.pageYOffset;
            
            isScrolling = true;
            smoothScrollTo(articleTop, 1300);
            
            // 애니메이션 완료 후 플래그 해제
            setTimeout(function(){
                isScrolling = false;
            }, 1300);
            
            // 네비게이션 활성화
            navLis.forEach(function(navLi){
                navLi.classList.remove('on');
            });
            navLis[index].classList.add('on');
            
            floatdivLis.forEach(function(floatLi){
                floatLi.classList.remove('on');
            });
            floatdivLis[index].classList.add('on');
            
            return false;
        });
    });

    // 드래그 기능 (순수 JavaScript)
    function makeDraggable(element) {
        var isDragging = false;
        var currentX;
        var currentY;
        var initialX;
        var initialY;
        var xOffset = 0;
        var yOffset = 0;

        element.addEventListener('mousedown', dragStart);
        document.addEventListener('mousemove', drag);
        document.addEventListener('mouseup', dragEnd);

        function dragStart(e) {
            if (e.type === "touchstart") {
                initialX = e.touches[0].clientX - xOffset;
                initialY = e.touches[0].clientY - yOffset;
            } else {
                initialX = e.clientX - xOffset;
                initialY = e.clientY - yOffset;
            }

            if (e.target === element || element.contains(e.target)) {
                isDragging = true;
            }
        }

        function drag(e) {
            if (isDragging) {
                e.preventDefault();
                
                if (e.type === "touchmove") {
                    currentX = e.touches[0].clientX - initialX;
                    currentY = e.touches[0].clientY - initialY;
                } else {
                    currentX = e.clientX - initialX;
                    currentY = e.clientY - initialY;
                }

                xOffset = currentX;
                yOffset = currentY;

                element.style.transform = "translate(" + currentX + "px, " + currentY + "px)";
            }
        }

        function dragEnd(e) {
            initialX = currentX;
            initialY = currentY;
            isDragging = false;
        }
    }

    // 팝업 드래그 가능하게 만들기
    makeDraggable(popup);
    makeDraggable(noticeWrap);

    // 팝업 표시/숨김 함수
    function fadeOut(element, duration) {
        duration = duration || 200;
        var startOpacity = parseFloat(window.getComputedStyle(element).opacity) || 1;
        var startTime = null;

        function step(timestamp) {
            if (!startTime) startTime = timestamp;
            var progress = timestamp - startTime;
            var percentage = Math.min(progress / duration, 1);
            var opacity = startOpacity * (1 - percentage);
            
            element.style.opacity = opacity;
            
            if (progress < duration) {
                window.requestAnimationFrame(step);
            } else {
                element.style.display = 'none';
                element.style.opacity = '';
            }
        }
        
        element.style.opacity = startOpacity;
        element.style.display = 'block';
        window.requestAnimationFrame(step);
    }

    function fadeIn(element, duration) {
        duration = duration || 200;
        element.style.display = 'block';
        element.style.opacity = '0';
        
        var startTime = null;
        function step(timestamp) {
            if (!startTime) startTime = timestamp;
            var progress = timestamp - startTime;
            var percentage = Math.min(progress / duration, 1);
            
            element.style.opacity = percentage;
            
            if (progress < duration) {
                window.requestAnimationFrame(step);
            } else {
                element.style.opacity = '';
            }
        }
        
        window.requestAnimationFrame(step);
    }

    // 팝업 표시 (쿠키 확인)
    if(getCookie('pop') != 'no'){
        popup.style.display = 'block';
        fadeIn(popup, 200);
    }

    // 팝업 닫기 버튼들
    var popupAreas = document.querySelectorAll('#popup area');
    if(popupAreas.length > 0){
        // 첫 번째 area (창닫기)
        popupAreas[0].addEventListener('click', function(e){
            e.preventDefault();
            fadeOut(popup, 200);
            return false;
        });

        // 두 번째 area (하루동안 안보기)
        popupAreas[1].addEventListener('click', function(e){
            e.preventDefault();
            setCookie('pop', 'no', 1); // 1일
            fadeOut(popup, 200);
            return false;
        });
    }

    // 공지사항 팝업
    if(getCookie('popup') == 'none'){
        noticeWrap.style.display = 'none';
    }

    // 닫기 버튼 클릭
    if(closeBtn){
        closeBtn.addEventListener('click', function(){
            if(expiresChk && expiresChk.checked){
                setCookie('popup', 'none', 3); // 3일
            }
            fadeOut(noticeWrap, 200);
        });
    }

    // 리사이즈 시 article 위치 재계산
    window.addEventListener('resize', function(){
        articleOffsets = [];
        articles.forEach(function(article){
            articleOffsets.push(article.getBoundingClientRect().top + window.pageYOffset);
        });
        dTop = floatdiv.getBoundingClientRect().top + window.pageYOffset - window.pageYOffset;
    });

});
