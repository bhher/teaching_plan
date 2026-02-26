// jQuery 이벤트 핸들러를 Vanilla JS로 변환

document.addEventListener('DOMContentLoaded', function() {
    const btn1 = document.getElementById('btn1');
    const btn2 = document.getElementById('btn2');
    const btn3 = document.getElementById('btn3');
    const btn4 = document.getElementById('btn4');
    const textZone = document.getElementById('textZone');
    const listWrap = document.getElementById('listWrap');
    const list1 = document.querySelector('.list1');
    const hoverLink = document.querySelector('.hover');
    
    // 1. click 이벤트
    // jQuery: $("#btn1").click(function () { $("#textZone").css("color", "blue") });
    if (btn1) {
        btn1.addEventListener('click', function() {
            textZone.style.color = 'blue';
        });
    }
    
    // 2. mouseover 이벤트
    // jQuery: $("#btn2").mouseover(function () { $("#textZone").css("background-color", "yellow") });
    if (btn2) {
        btn2.addEventListener('mouseover', function() {
            textZone.style.backgroundColor = 'yellow';
        });
    }
    
    // 3. focus 이벤트
    // jQuery: $("#btn2").focus(function () { $("#textZone").css("background-color", "yellow") });
    if (btn2) {
        btn2.addEventListener('focus', function() {
            textZone.style.backgroundColor = 'yellow';
        });
    }
    
    // 4. 여러 이벤트 등록 (on 메서드)
    // jQuery: $("#btn3").on('mouseover focus', function () { ... });
    // 한 가지 이상 이벤트 등록 시 on 메서드 사용 (예전엔 bind 메서드 사용)
    if (btn3) {
        btn3.addEventListener('mouseover', function() {
            textZone.style.color = 'green';
            textZone.style.fontWeight = 'bold';
        });
        
        btn3.addEventListener('focus', function() {
            textZone.style.color = 'green';
            textZone.style.fontWeight = 'bold';
        });
    }
    
    // 5. mouseenter 이벤트
    // jQuery: $("#listWrap").mouseenter(function () { $(".list1").css("display", "block"); });
    // id="listWrap"에 마우스가 올라가 있으면 class="list1"을 블록 요소로 변경
    // mouseover와 비슷하지만 자식 요소로 이동해도 이벤트가 발생하지 않음
    if (listWrap && list1) {
        listWrap.addEventListener('mouseenter', function() {
            list1.style.display = 'block';
        });
    }
    
    // 6. mouseleave 이벤트
    // jQuery: $("#listWrap").mouseleave(function () { $(".list1").css("display", "none"); });
    // id="listWrap"에서 마우스가 벗어나면 class="list1"을 숨김
    // mouseout과 비슷하지만 자식 요소로 이동해도 이벤트가 발생하지 않음
    if (listWrap && list1) {
        listWrap.addEventListener('mouseleave', function() {
            list1.style.display = 'none';
        });
    }
    
    // 7. hover 메서드 (두 개의 함수)
    // jQuery: $('.hover').hover(function(){ $(this).css("color","aqua"); }, function(){ $(this).css("color","red"); });
    // 첫 번째 함수: mouseenter 시 실행
    // 두 번째 함수: mouseleave 시 실행
    if (hoverLink) {
        hoverLink.addEventListener('mouseenter', function() {
            this.style.color = 'aqua';
        });
        
        hoverLink.addEventListener('mouseleave', function() {
            this.style.color = 'red';
        });
    }
    
    // 8. toggle 기능 (추가 구현)
    // jQuery의 toggle은 제거되었지만, 클릭할 때마다 토글하는 기능 구현
    if (btn4) {
        let toggleState = false;
        btn4.addEventListener('click', function() {
            toggleState = !toggleState;
            if (toggleState) {
                textZone.style.backgroundColor = 'lightblue';
                textZone.style.color = 'darkblue';
            } else {
                textZone.style.backgroundColor = '';
                textZone.style.color = '';
            }
        });
    }
});
