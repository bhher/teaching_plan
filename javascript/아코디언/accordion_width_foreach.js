// forEach를 사용한 아코디언 메뉴 (Width 방식)

document.addEventListener('DOMContentLoaded', function(){
    var ddList = document.querySelectorAll('dd');
    var dtList = document.querySelectorAll('dl dt');
    var dtListSpan = document.querySelectorAll('dl dt span');
    
    // 1. 첫 번째를 제외한 모든 dd를 닫기
    ddList.forEach(function(dd, index) {
        if (index !== 0) {
            dd.style.width = '0px';
        }
    });

    // 2. 첫 번째 dt의 span에 selected 클래스 추가
    dtListSpan[0].classList.add('selected');

    // 3. 각 dt에 클릭 이벤트 리스너 추가
    dtList.forEach(function(dt) {
        dt.addEventListener('click', function(){
            var nextDD = this.nextElementSibling;

            // 4. 클릭한 dd가 닫혀있으면 모든 dd 닫기
            if(nextDD.style.width == '0px' || nextDD.style.width === ''){
                ddList.forEach(function(dd) {
                    dd.style.width = '0px';
                });
            }
            
            // 5. 클릭한 dd 열기
            nextDD.style.width = "695px";

            // 6. 모든 span에서 selected 제거
            dtListSpan.forEach(function(span) {
                span.classList.remove('selected');
            });
            
            // 7. 클릭한 dt의 span에 selected 추가
            this.querySelector('span').classList.add('selected');
        });
    });
});
