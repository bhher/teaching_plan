$(function(){
	// $(".m_menu li ul").css('display','none')
    $('.sub').hide();

    // $('.sub').eq(0).show;
    // $('.m_menu li ul').eq(0).show();
    //처음 메뉴만 보이게 설정

    $('.m_menu li a').click(function(){
        let kkk = $(this).next('.sub').css('display');
        //클릭한 a 다음에 .sub의 diplay 상태 확인
        if(kkk == 'none'){
            $('.sub').slideUp();
            //모든 서브메뉴 닫기
            $(this).next('.sub').slideDown();
            //클릭한 a 다음요소인 .sub 열기
        } else {
            $('.sub').slideUp();
            //모든 서브메뉴 닫기
        }

        return false;
        //a태그 기능 막기
    })
})