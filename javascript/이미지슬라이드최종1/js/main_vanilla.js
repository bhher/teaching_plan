

document.addEventListener('DOMContentLoaded', () => {
    const imgs = document.querySelector('.imgs');
    const items = document.querySelectorAll('.imgs li');
    const pager = document.querySelectorAll('.pager li');
    const prevBtn = document.querySelector('.prev');
    const nextBtn = document.querySelector('.next');
    const wrap = document.getElementById('wrap');
    
    const count = items.length; // 실제 이미지 개수 (5개)
    let i = 1; // 현재 인덱스 (1번부터 시작)
    let timer;

    // 1. 무한 루프를 위한 앞뒤 복제본 생성 및 추가
    const firstClone = items[0].cloneNode(true);
    const lastClone = items[count - 1].cloneNode(true);
    
    imgs.appendChild(firstClone); // 마지막 뒤에 1번 복제본 추가
    imgs.insertBefore(lastClone, items[0]); // 1번 앞에 마지막 복제본 추가

    // 2. 초기 위치 설정 (실제 1번 이미지가 보이도록)
    imgs.style.marginLeft = '-100%';

    // 3. 슬라이드 이동 핵심 함수
    function move(index, speed = 0.6) {
        // 애니메이션 적용
        imgs.style.transition = speed > 0 ? `margin-left ${speed}s ease` : 'none';
        imgs.style.marginLeft = `${-index * 100}%`;
        
        // 페이저(점) 상태 업데이트 (나머지 연산자를 이용해 복제본 구간에서도 정확한 인덱스 표시)
        let pagerIdx = (index - 1 + count) % count;
        pager.forEach((p, idx) => {
            p.classList.toggle('on', idx === pagerIdx);
        });
        
        i = index;
    }

    // 4. 무한 루프 '순간 점프' 로직 (가장 중요)
    imgs.addEventListener('transitionend', () => {
        // 마지막 복제본(0번)에 도달하면 실제 마지막(5번) 위치로 순간 이동
        if (i === 0) {
            move(count, 0); 
        } 
        // 첫 번째 복제본(6번)에 도달하면 실제 첫 번째(1번) 위치로 순간 이동
        else if (i === count + 1) {
            move(1, 0);
        }
    });

    // 5. 버튼 및 페이저 클릭 이벤트 바인딩
    nextBtn.onclick = () => move(i + 1);
    prevBtn.onclick = () => move(i - 1);

    pager.forEach((p, idx) => {
        p.onclick = () => move(idx + 1);
    });

    // 6. 자동 재생 기능
    const startTimer = () => {
        timer = setInterval(() => move(i + 1), 3000);
    };

    const stopTimer = () => {
        clearInterval(timer);
    };

    // 마우스 오버 시 일시 정지
    wrap.onmouseenter = stopTimer;
    wrap.onmouseleave = startTimer;

    // 실행 시작
    startTimer();
});