// jQuery Effect를 Vanilla JS로 변환

document.addEventListener('DOMContentLoaded', function() {
    const btn1 = document.getElementById('btn1');
    const btn2 = document.getElementById('btn2');
    const btn3 = document.getElementById('btn3');
    const btn4 = document.getElementById('btn4');
    const btn5 = document.getElementById('btn5');
    const btn6 = document.getElementById('btn6');
    const btn7 = document.getElementById('btn7');
    const btn8 = document.getElementById('btn8');
    const btn9 = document.getElementById('btn9');
    const btn10 = document.getElementById('btn10');
    const btn11 = document.getElementById('btn11');
    
    const box1 = document.querySelector('.box1');
    const box2 = document.querySelector('.box2');
    const box3 = document.getElementById('box3');
    
    // 시간 변환 함수 (jQuery의 fast, normal, slow를 밀리초로 변환)
    function parseDuration(duration) {
        if (typeof duration === 'string') {
            switch(duration) {
                case 'fast': return 200;
                case 'normal': return 400;
                case 'slow': return 600;
                default: return 400;
            }
        }
        return duration || 400;
    }
    
    // 1. hide 효과
    // jQuery: $(".box1").hide("slow");
    if (btn1 && box1) {
        btn1.addEventListener('click', function() {
            hide(box1, 'slow');
        });
    }
    
    // 2. show 효과
    // jQuery: $(".box1").show(1000);
    if (btn2 && box1) {
        btn2.addEventListener('click', function() {
            show(box1, 1000);
        });
    }
    
    // 3. toggle 효과
    // jQuery: $(".box2").toggle(500);
    if (btn3 && box2) {
        btn3.addEventListener('click', function() {
            toggle(box2, 500);
        });
    }
    
    // 4. slideUp 효과
    // jQuery: $(this).parent().next().slideUp(500);
    if (btn4) {
        btn4.addEventListener('click', function() {
            const target = this.parentElement.nextElementSibling;
            if (target) {
                slideUp(target, 500);
            }
        });
    }
    
    // 5. slideDown 효과
    // jQuery: $(this).parent().next().slideDown("fast");
    if (btn5) {
        btn5.addEventListener('click', function() {
            const target = this.parentElement.nextElementSibling;
            if (target) {
                slideDown(target, 'fast');
            }
        });
    }
    
    // 6. slideToggle 효과
    // jQuery: $(this).parent().next().slideToggle("fast");
    if (btn6) {
        btn6.addEventListener('click', function() {
            const target = this.parentElement.nextElementSibling;
            if (target) {
                slideToggle(target, 'fast');
            }
        });
    }
    
    // 7. fadeOut 효과
    // jQuery: $(this).parent().next().fadeOut(1000);
    if (btn7) {
        btn7.addEventListener('click', function() {
            const target = this.parentElement.nextElementSibling;
            if (target) {
                fadeOut(target, 1000);
            }
        });
    }
    
    // 8. fadeIn 효과
    // jQuery: $(this).parent().next().fadeIn('slow');
    if (btn8) {
        btn8.addEventListener('click', function() {
            const target = this.parentElement.nextElementSibling;
            if (target) {
                fadeIn(target, 'slow');
            }
        });
    }
    
    // 9. fadeToggle 효과
    // jQuery: $(this).parent().next().fadeToggle("normal");
    if (btn9) {
        btn9.addEventListener('click', function() {
            const target = this.parentElement.nextElementSibling;
            if (target) {
                fadeToggle(target, 'normal');
            }
        });
    }
    
    // 10. fadeTo(0.3) 효과
    // jQuery: $(this).parent().next().fadeTo("slow", 0.3);
    if (btn10) {
        btn10.addEventListener('click', function() {
            const target = this.parentElement.nextElementSibling;
            if (target) {
                fadeTo(target, 'slow', 0.3);
            }
        });
    }
    
    // 11. fadeTo(1) 효과
    // jQuery: $(this).parent().next().fadeTo("slow", 1);
    if (btn11) {
        btn11.addEventListener('click', function() {
            const target = this.parentElement.nextElementSibling;
            if (target) {
                fadeTo(target, 'slow', 1);
            }
        });
    }
    
    // ============================================
    // 효과 함수 구현
    // ============================================
    
    // hide 함수
    function hide(element, duration) {
        const dur = parseDuration(duration);
        element.style.transition = `opacity ${dur}ms ease`;
        element.style.opacity = '0';
        
        setTimeout(function() {
            element.style.display = 'none';
        }, dur);
    }
    
    // show 함수
    function show(element, duration) {
        const dur = parseDuration(duration);
        element.style.display = 'block';
        element.style.opacity = '0';
        
        // 다음 프레임에서 opacity 애니메이션 시작
        requestAnimationFrame(function() {
            element.style.transition = `opacity ${dur}ms ease`;
            element.style.opacity = '1';
        });
    }
    
    // toggle 함수
    function toggle(element, duration) {
        const isHidden = element.style.display === 'none' || 
                        window.getComputedStyle(element).display === 'none';
        
        if (isHidden) {
            show(element, duration);
        } else {
            hide(element, duration);
        }
    }
    
    // slideUp 함수
    function slideUp(element, duration) {
        const dur = parseDuration(duration);
        const height = element.scrollHeight;
        
        element.style.height = height + 'px';
        element.style.overflow = 'hidden';
        element.style.transition = `height ${dur}ms ease`;
        
        // 다음 프레임에서 높이 애니메이션 시작
        requestAnimationFrame(function() {
            element.style.height = '0px';
        });
        
        setTimeout(function() {
            element.style.display = 'none';
            element.style.height = '';
        }, dur);
    }
    
    // slideDown 함수
    function slideDown(element, duration) {
        const dur = parseDuration(duration);
        const height = element.scrollHeight;
        
        element.style.display = 'block';
        element.style.height = '0px';
        element.style.overflow = 'hidden';
        element.style.transition = `height ${dur}ms ease`;
        
        // 다음 프레임에서 높이 애니메이션 시작
        requestAnimationFrame(function() {
            element.style.height = height + 'px';
        });
        
        setTimeout(function() {
            element.style.height = '';
            element.style.overflow = '';
        }, dur);
    }
    
    // slideToggle 함수
    function slideToggle(element, duration) {
        const isHidden = element.style.display === 'none' || 
                        window.getComputedStyle(element).display === 'none';
        
        if (isHidden) {
            slideDown(element, duration);
        } else {
            slideUp(element, duration);
        }
    }
    
    // fadeOut 함수
    function fadeOut(element, duration) {
        const dur = parseDuration(duration);
        element.style.transition = `opacity ${dur}ms ease`;
        element.style.opacity = '0';
        
        setTimeout(function() {
            element.style.display = 'none';
        }, dur);
    }
    
    // fadeIn 함수
    function fadeIn(element, duration) {
        const dur = parseDuration(duration);
        element.style.display = 'block';
        element.style.opacity = '0';
        
        // 다음 프레임에서 opacity 애니메이션 시작
        requestAnimationFrame(function() {
            element.style.transition = `opacity ${dur}ms ease`;
            element.style.opacity = '1';
        });
    }
    
    // fadeToggle 함수
    function fadeToggle(element, duration) {
        const isHidden = element.style.display === 'none' || 
                        window.getComputedStyle(element).display === 'none';
        
        if (isHidden) {
            fadeIn(element, duration);
        } else {
            fadeOut(element, duration);
        }
    }
    
    // fadeTo 함수
    function fadeTo(element, duration, opacity) {
        const dur = parseDuration(duration);
        element.style.display = 'block';
        element.style.transition = `opacity ${dur}ms ease`;
        element.style.opacity = opacity;
        
        // opacity가 0이면 완료 후 display: none
        if (opacity === 0) {
            setTimeout(function() {
                element.style.display = 'none';
            }, dur);
        }
    }
});
