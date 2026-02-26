# each문으로 코드 개선하기

## 📋 개요

반복되는 if문을 `each()` 메서드를 사용하여 간결하고 유지보수하기 쉬운 코드로 개선합니다.

---

## 🔄 개선 전/후 비교

### ❌ 개선 전 (반복 코드)

```javascript
if(sct >= $('.container > div').eq(0).offset().top){
  $('nav ul li').removeClass('on');
  $('nav ul li').eq(0).addClass('on');
}
if(sct >= $('.container > div').eq(1).offset().top){
  $('nav ul li').removeClass('on');
  $('nav ul li').eq(1).addClass('on');
}
if(sct >= $('.container > div').eq(2).offset().top){
  $('nav ul li').removeClass('on');
  $('nav ul li').eq(2).addClass('on');
}
if(sct >= $('.container > div').eq(3).offset().top){
  $('nav ul li').removeClass('on');
  $('nav ul li').eq(3).addClass('on');
}
if(sct >= $('.container > div').eq(4).offset().top){
  $('nav ul li').removeClass('on');
  $('nav ul li').eq(4).addClass('on');
}
```

**문제점:**
- 코드 중복이 많음
- 섹션이 추가되면 코드를 계속 추가해야 함
- 유지보수가 어려움

---

### ✅ 개선 후 (each문 사용)

#### 방법 1: 기본 each문

```javascript
$('.container > div').each(function(index){
  if(sct >= $(this).offset().top){
    $('nav ul li').removeClass('on');
    $('nav ul li').eq(index).addClass('on');
  }
});
```

**장점:**
- 코드가 간결해짐
- 섹션이 추가되어도 코드 수정 불필요
- 가독성 향상

**주의사항:**
- 여러 섹션이 동시에 조건을 만족할 수 있음
- 마지막에 체크된 섹션이 활성화됨

---

#### 방법 2: 역순 체크 (권장)

```javascript
var activeIndex = -1;
$('.container > div').each(function(index){
  if(sct >= $(this).offset().top - 100){
    activeIndex = index;
  }
});

if(activeIndex >= 0){
  $('nav ul li').removeClass('on');
  $('nav ul li').eq(activeIndex).addClass('on');
}
```

**장점:**
- 가장 아래에 있는 섹션을 정확히 활성화
- DOM 조작을 한 번만 수행 (성능 향상)
- 더 정확한 섹션 감지

---

#### 방법 3: 범위 체크 (가장 정확)

```javascript
$(window).scroll(function(){
  var currentScroll = $(window).scrollTop();
  var activeIndex = -1;
  
  $('.container > div').each(function(index){
    var sectionTop = $(this).offset().top;
    var sectionHeight = $(this).outerHeight();
    
    // 현재 스크롤 위치가 섹션 범위 내에 있는지 확인
    if(currentScroll >= sectionTop - 100 && 
       currentScroll < sectionTop + sectionHeight - 100){
      activeIndex = index;
    }
  });
  
  if(activeIndex >= 0){
    $('nav ul li').removeClass('on');
    $('nav ul li').eq(activeIndex).addClass('on');
  }
});
```

**장점:**
- 섹션의 시작과 끝을 모두 고려
- 가장 정확한 섹션 감지
- 스크롤 이벤트와 통합

---

## 📚 each() 메서드 설명

### 기본 문법

```javascript
$(selector).each(function(index, element){
  // index: 현재 요소의 인덱스 (0부터 시작)
  // element: 현재 DOM 요소
  // this: 현재 jQuery 객체
});
```

### 사용 예시

```javascript
// 1. 인덱스 사용
$('.item').each(function(index){
  console.log('인덱스:', index);
  console.log('요소:', this);
});

// 2. 요소 직접 사용
$('.item').each(function(index, element){
  console.log('인덱스:', index);
  console.log('DOM 요소:', element);
  console.log('jQuery 객체:', $(element));
});

// 3. this 사용
$('.item').each(function(index){
  $(this).addClass('active');  // this는 현재 jQuery 객체
});
```

---

## 🎯 실전 예제

### 예제 1: 섹션별 메뉴 하이라이트

```javascript
$(window).scroll(function(){
  var scrollTop = $(window).scrollTop();
  var activeIndex = -1;
  
  $('.container > div').each(function(index){
    var sectionTop = $(this).offset().top;
    
    if(scrollTop >= sectionTop - 100){
      activeIndex = index;
    }
  });
  
  if(activeIndex >= 0){
    $('nav ul li').removeClass('on');
    $('nav ul li').eq(activeIndex).addClass('on');
  }
});
```

### 예제 2: 성능 최적화 버전

```javascript
// DOM 요소 캐싱
var $sections = $('.container > div');
var $menuItems = $('nav ul li');

$(window).scroll(function(){
  var scrollTop = $(window).scrollTop();
  var activeIndex = -1;
  
  $sections.each(function(index){
    if(scrollTop >= $(this).offset().top - 100){
      activeIndex = index;
    }
  });
  
  if(activeIndex >= 0){
    $menuItems.removeClass('on');
    $menuItems.eq(activeIndex).addClass('on');
  }
});
```

### 예제 3: Throttle 적용 버전

```javascript
function throttle(func, delay){
  var timeoutId;
  var lastExecTime = 0;
  
  return function(){
    var currentTime = Date.now();
    
    if(currentTime - lastExecTime > delay){
      func.apply(this, arguments);
      lastExecTime = currentTime;
    } else {
      clearTimeout(timeoutId);
      timeoutId = setTimeout(function(){
        func.apply(this, arguments);
        lastExecTime = Date.now();
      }, delay - (currentTime - lastExecTime));
    }
  };
}

var handleScroll = throttle(function(){
  var scrollTop = $(window).scrollTop();
  var activeIndex = -1;
  
  $('.container > div').each(function(index){
    if(scrollTop >= $(this).offset().top - 100){
      activeIndex = index;
    }
  });
  
  if(activeIndex >= 0){
    $('nav ul li').removeClass('on');
    $('nav ul li').eq(activeIndex).addClass('on');
  }
}, 100);

$(window).scroll(handleScroll);
```

---

## 🔍 코드 분석

### each() 동작 과정

```javascript
$('.container > div').each(function(index){
  // index = 0: 첫 번째 div
  // index = 1: 두 번째 div
  // index = 2: 세 번째 div
  // ...
});
```

### 인덱스 매칭

```javascript
// 섹션 인덱스와 메뉴 인덱스가 일치해야 함
$('.container > div').eq(0)  ↔  $('nav ul li').eq(0)
$('.container > div').eq(1)  ↔  $('nav ul li').eq(1)
$('.container > div').eq(2)  ↔  $('nav ul li').eq(2)
```

---

## 💡 개선 효과

| 항목 | 개선 전 | 개선 후 |
|------|---------|---------|
| **코드 라인 수** | 30줄 | 5-10줄 |
| **유지보수성** | 낮음 | 높음 |
| **확장성** | 낮음 | 높음 |
| **가독성** | 낮음 | 높음 |
| **성능** | 동일 | 동일/개선 |

---

## ⚠️ 주의사항

### 1. 인덱스 일치 확인
```javascript
// 섹션과 메뉴 항목의 개수가 일치해야 함
// .container > div 개수 === nav ul li 개수
```

### 2. DOM 로드 확인
```javascript
$(function(){
  // DOM이 로드된 후 실행
  $('.container > div').each(function(index){
    // ...
  });
});
```

### 3. 성능 고려
```javascript
// 매번 DOM 조작하지 않도록 주의
// 변수에 캐싱하여 사용
var $sections = $('.container > div');
var $menuItems = $('nav ul li');
```

---

## 🚀 추가 개선 아이디어

### 1. 역순 체크로 정확도 향상
```javascript
var activeIndex = -1;
$('.container > div').each(function(index){
  if(scrollTop >= $(this).offset().top - 100){
    activeIndex = index;  // 마지막에 조건을 만족한 인덱스 저장
  }
});
```

### 2. 범위 체크로 더 정확한 감지
```javascript
$('.container > div').each(function(index){
  var top = $(this).offset().top;
  var bottom = top + $(this).outerHeight();
  
  if(scrollTop >= top - 100 && scrollTop < bottom - 100){
    activeIndex = index;
  }
});
```

### 3. break 사용 (jQuery each에서는 return false)
```javascript
var activeIndex = -1;
$('.container > div').each(function(index){
  if(scrollTop < $(this).offset().top - 100){
    return false;  // 반복 중단
  }
  activeIndex = index;
});
```

---

## 📝 최종 권장 코드

```javascript
$(function(){
  var $sections = $('.container > div');
  var $menuItems = $('nav ul li');
  
  $(window).scroll(function(){
    var scrollTop = $(window).scrollTop();
    var activeIndex = -1;
    
    $sections.each(function(index){
      if(scrollTop >= $(this).offset().top - 100){
        activeIndex = index;
      }
    });
    
    if(activeIndex >= 0){
      $menuItems.removeClass('on');
      $menuItems.eq(activeIndex).addClass('on');
    }
  });
});
```

---

## 마무리

`each()` 메서드를 사용하면 반복 코드를 간결하고 유지보수하기 쉬운 코드로 개선할 수 있습니다.

**핵심 포인트:**
- ✅ 코드 중복 제거
- ✅ 확장성 향상
- ✅ 가독성 향상
- ✅ 유지보수 용이
