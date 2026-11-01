$(function(){

    $('.lnb > li').click(function(){
        var i = $(this).index();
        var nowLeft = $('.section').eq(i).offset().left;
        $('html, body').stop().animate({'scrollLeft':nowLeft},1300);
        return false;
    });

});