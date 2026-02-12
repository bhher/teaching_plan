$(function(){
    let visual = $('#brandVisual > ul > li');
    let button = $('#buttonList > li');
    let current = 0;
    //현재 인덱스 번호 저장용
    let id;
    let i = 0;

    function moveBy(){
        if(current == i)return;
        //현재 인덱스 번호와 클릭한 인덱스 번호가 같으면 함수 종료
        let cu = visual.eq(current);
        //현재 사진
        let ne = visual.eq(i);
        //슬라이드 되서 들어올 사진

        cu.css('left', '0').stop().animate({'left':'-100%'},500);
        ne.css('left','100%').stop().animate({'left':'0'},500);
        current = i;
        //현재 인덱스 번호에 클릭한 인덱스 번호 저장
    }

    button.click(function(){
        i = $(this).index();
        button.removeClass('on');
        button.eq(i).addClass('on');
        moveBy();
        return false;
    })

    function timer(){
        id = setInterval(function(){
            let n = current + 1;
            console.log(n);
            if(n == visual.length){n = 0;}
            button.eq(n).trigger('click');
            //trigger('이벤트명') : 해당 이벤트 강제 발생
            //3초마다 다음 버튼 클릭 이벤트 강제 발생
        },3000)
    }
    timer();
})