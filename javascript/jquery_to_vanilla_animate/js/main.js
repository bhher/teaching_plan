// jQuery animate()를 Vanilla JS로 변환

document.addEventListener('DOMContentLoaded', function() {
    const box1 = document.getElementById('box1');
    
    // jQuery 코드:
    // $('#box1').animate({
    //     left:"500px",
    //     width:"200px",
    //     height:"200px",
    //     opacity:0.5
    // },2000).animate({
    //     left:"0",
    //     width:"100px",
    //     height:"100px",
    //     opacity:1
    // },1000);
    
    // 첫 번째 애니메이션
    animate(box1, {
        left: '500px',
        width: '200px',
        height: '200px',
        opacity: 0.5
    }, 2000).then(function() {
        // 두 번째 애니메이션 (체이닝)
        return animate(box1, {
            left: '0',
            width: '100px',
            height: '100px',
            opacity: 1
        }, 1000);
    });
});

/**
 * animate 함수 - jQuery의 animate() 메서드를 구현
 * @param {HTMLElement} element - 애니메이션할 요소
 * @param {Object} properties - 애니메이션할 속성들
 * @param {Number} duration - 애니메이션 지속 시간 (밀리초)
 * @param {Function} easing - 이징 함수 (선택사항)
 * @returns {Promise} - 애니메이션 완료 시 resolve되는 Promise
 */
function animate(element, properties, duration, easing) {
    return new Promise(function(resolve) {
        const startTime = performance.now();
        const startValues = {};
        const endValues = {};
        
        // easing 함수 (기본값: easeInOutQuad)
        const ease = easing || easeInOutQuad;
        
        // 시작값과 끝값 저장
        for (let prop in properties) {
            const computedStyle = window.getComputedStyle(element);
            
            // 시작값 가져오기
            if (prop === 'opacity') {
                startValues[prop] = parseFloat(computedStyle.opacity) || 1;
            } else {
                // px 단위 속성
                const value = computedStyle[prop] || '0px';
                startValues[prop] = parseFloat(value) || 0;
            }
            
            // 끝값 저장
            const endValue = properties[prop];
            if (typeof endValue === 'string') {
                endValues[prop] = parseFloat(endValue);
            } else {
                endValues[prop] = endValue;
            }
        }
        
        // 애니메이션 루프
        function animationFrame(currentTime) {
            const elapsed = currentTime - startTime;
            const progress = Math.min(elapsed / duration, 1); // 0~1 사이 값
            const easedProgress = ease(progress);
            
            // 각 속성 애니메이션
            for (let prop in properties) {
                const start = startValues[prop];
                const end = endValues[prop];
                const current = start + (end - start) * easedProgress;
                
                if (prop === 'opacity') {
                    element.style.opacity = current;
                } else {
                    element.style[prop] = current + 'px';
                }
            }
            
            // 애니메이션 완료 확인
            if (progress < 1) {
                requestAnimationFrame(animationFrame);
            } else {
                resolve(); // Promise resolve
            }
        }
        
        // 애니메이션 시작
        requestAnimationFrame(animationFrame);
    });
}

/**
 * Easing 함수들
 */

// Linear (선형)
function linear(t) {
    return t;
}

// Ease In Quad
function easeInQuad(t) {
    return t * t;
}

// Ease Out Quad
function easeOutQuad(t) {
    return t * (2 - t);
}

// Ease In Out Quad (기본값)
function easeInOutQuad(t) {
    return t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t;
}

// Ease In Cubic
function easeInCubic(t) {
    return t * t * t;
}

// Ease Out Cubic
function easeOutCubic(t) {
    return (--t) * t * t + 1;
}

// Ease In Out Cubic
function easeInOutCubic(t) {
    return t < 0.5 ? 4 * t * t * t : (t - 1) * (2 * t - 2) * (2 * t - 2) + 1;
}
