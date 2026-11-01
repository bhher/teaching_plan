# jQuery 한방 정리 — 문제

교안: [jQuery-한방정리.md](./jQuery-한방정리.md)  
정답: [jQuery-한방정리-정답.md](./jQuery-한방정리-정답.md)

---

# Part 1. 기본 · 선택자

## 1번

jQuery에 대한 설명으로 **올바른** 것은?

① JavaScript를 대체하는 프로그래밍 언어이다  
② DOM 조작을 쉽게 해 주는 **라이브러리**이다  
③ CSS 전용 도구이다  
④ Spring Boot 전용 프레임워크이다  

---

## 2번

`$(function(){ ... });` 의 의미로 **올바른** 것은?

① 페이지를 새로고침한다  
② DOM이 준비되면 안의 코드를 실행한다  
③ 클릭할 때마다 실행한다  
④ Ajax 요청 후 실행한다  

---

## 3번

`$("#title")` 이 선택하는 것은?

① class가 title인 모든 요소  
② id가 title인 요소  
③ `<title>` 태그  
④ name이 title인 input  

---

## 4번

`$("#box > p")` 의 의미는?

① `#box`의 모든 자손 `p`  
② `#box`의 **바로 아래 자식** `p`  
③ `#box`와 `p` 중 하나  
④ 형제 `p`  

---

## 5번

jQuery `$(".item")` 이 반환하는 것은?

① DOM 요소 하나  
② **jQuery 객체**  
③ 문자열  
④ boolean  

---

# Part 2. 내용 · CSS · 클래스 · 속성 · form

## 6번

`$("#title").text("<strong>Hello</strong>");` 실행 시 화면에 보이는 것은?

① 굵은 글씨 Hello  
② `<strong>Hello</strong>` (태그가 문자로 표시)  
③ 아무것도 안 보임  
④ 오류 발생  

---

## 7번

HTML 태그를 **적용**해서 내용을 넣을 때 사용하는 메서드는?

① `.text()`  
② `.html()`  
③ `.val()`  
④ `.attr()`  

---

## 8번

input 입력값을 가져올 때 사용하는 메서드는?

① `.text()`  
② `.html()`  
③ `.val()`  
④ `.css()`  

---

## 9번

`$("#menu").toggleClass("active");` 의 의미는?

① active 클래스를 무조건 추가한다  
② active 클래스를 무조건 제거한다  
③ **있으면 제거, 없으면 추가**한다  
④ active 클래스 존재 여부만 확인한다  

---

## 10번

이미지 경로를 바꿀 때 **올바른** 코드는?

① `$("#img").css("src", "a.jpg")`  
② `$("#img").attr("src", "a.jpg")`  
③ `$("#img").val("a.jpg")`  
④ `$("#img").html("a.jpg")`  

---

# Part 3. 이벤트 · this

## 11번

현대 jQuery에서 이벤트 등록 **권장** 방법은?

① `$("#btn").click(function(){})` 만 사용  
② `$("#btn").on("click", function(){})`  
③ `onclick="..."` HTML 속성만 사용  
④ `$.event("click")`  

---

## 12번

폼 제출 시 페이지 새로고침을 막으려면?

① `e.stopPropagation()`  
② `e.preventDefault()`  
③ `return true`  
④ `$.ajax()`  

---

## 13번

```javascript
$("li").on("click", function () {
    alert($(this).text());
});
```

클릭 시 **올바른** 설명은?

① `this`는 jQuery 객체이다  
② `$(this)`는 클릭된 li의 jQuery 객체이다  
③ `this.text()`로 텍스트를 가져온다  
④ 모든 li의 텍스트가 동시에 출력된다  

---

## 14번

`submit` 이벤트가 발생하는 대상은?

① `<button>` 만  
② `<form>`  
③ `<input type="text">`  
④ `<a>`  

---

# Part 4. each · 탐색 · DOM 추가/삭제

## 15번

여러 `li` 요소를 순회할 때 사용하는 jQuery 메서드는?

① `.each()`  
② `.every()`  
③ `.map()`  
④ `.find()`  

---

## 16번

`$(".box").find("p")` 의 의미는?

① `.box`의 부모 `p`  
② `.box` **안쪽**의 `p` (자손)  
③ `.box`의 형제 `p`  
④ `.box` 바로 다음 `p`  

---

## 17번

`$("#list").append("<li>사과</li>")` 의 의미는?

① `#list` **앞에** li 추가  
② `#list` **안쪽 맨 뒤**에 li 추가  
③ `#list` 자체를 삭제  
④ `#list` 내용만 비움  

---

## 18번

`remove()` 와 `empty()` 차이로 **올바른** 것은?

① 둘 다 요소 자체를 삭제한다  
② `remove()`는 요소 삭제, `empty()`는 **내부만** 비움  
③ `empty()`만 요소를 삭제한다  
④ 차이 없음  

---

# Part 5. 효과 · 스크롤

## 19번

아코디언 메뉴에서 펼치기/접기에 **많이** 쓰는 메서드는?

① `fadeIn()` / `fadeOut()`  
② `slideDown()` / `slideUp()` / `slideToggle()`  
③ `width()` / `height()`  
④ `offset()`  

---

## 20번

현재 스크롤 위치(스크롤 양)를 가져오는 코드는?

① `$(window).scroll()`  
② `$(window).scrollTop()`  
③ `$("#box").offset()`  
④ `$(document).height()`  

---

## 21번

TOP 버튼 클릭 시 맨 위로 부드럽게 이동하는 **핵심** 코드는?

```javascript
$("html, body").animate({ scrollTop: 0 }, 500);
```

① `hide()`  
② `animate({ scrollTop: 0 })`  
③ `fadeIn()`  
④ `css("top", 0)`  

---

# Part 6. form · 필터 · UI/플러그인

## 22번

체크박스가 선택되었는지 확인하는 방법으로 **올바른** 것은?

① `$("#agree").attr("checked")` 만 가능  
② `$("#agree").is(":checked")` 또는 `.prop("checked")`  
③ `$("#agree").val()`  
④ `$("#agree").hasClass("checked")`  

---

## 23번

`$("li:eq(2)")` 가 선택하는 것은?

① 2번째 li (1부터)  
② **3번째** li (0부터)  
③ 마지막 li  
④ 짝수 번째 li  

---

## 24번

`$("#box").draggable()` 은?

① jQuery **본체** 기능이다  
② **jQuery UI** 기능이다  
③ CSS만으로 동작한다  
④ Vanilla JS 전용이다  

---

## 25번

`$.cookie("popup", "none")` 은?

① jQuery 본체 기능이다  
② **별도 쿠키 플러그인** 기능이다  
③ Spring Boot 기능이다  
④ HTML5 localStorage이다  

---

# Part 7. Ajax · Spring Boot

## 26번

jQuery에서 GET 요청을 보낼 때 사용하는 **대표** 메서드는?

① `$.get()` 또는 `$.ajax()`  
② `$.post()` 만  
③ `fetch()` (jQuery 전용)  
④ `$.loadCSS()`  

---

## 27번

POST로 JSON을 보낼 때 **필수**에 가까운 설정은?

① `type: "POST"`, `contentType: "application/json"`, `data: JSON.stringify(...)`  
② `type: "GET"`, `data: html`  
③ `url: null`  
④ `success` 없이도 반드시 동작  

---

## 28번

jQuery Ajax → Spring Boot 흐름으로 **올바른** 것은?

① Controller → $.ajax → DB  
② $.ajax → Controller → Service → Repository → DB  
③ DB → jQuery → HTML  
④ Spring Boot → jQuery → CDN  

---

# Part 8. JavaScript vs jQuery (빈칸·대응)

## 29번 (빈칸)

Vanilla JS `document.querySelector("#id")` ↔ jQuery `________("#id")`

---

## 30번 (빈칸)

Vanilla JS `.addEventListener("click", fn)` ↔ jQuery `$("#btn").________("click", fn)`

---

# Part 9. 코드 작성 (31~38)

## 31번

`#btn` 클릭 시 `#title`의 텍스트를 `"안녕 jQuery"`로 바꾸세요.  
(`$(function(){})` + `.on()` 사용)

---

## 32번

`#box`에 마우스를 올리면 글자색 `red`, 나가면 `black`으로 바꾸세요.  
(`mouseover` / `mouseout` 또는 `mouseenter` / `mouseleave`)

---

## 33번

`#toggleBtn` 클릭 시 `#panel`을 `slideToggle(300)` 하세요.

---

## 34번

`ul` 안의 각 `li`를 클릭하면 **그 항목의 텍스트**를 `alert`로 출력하세요.  
(`$(this)` 사용)

---

## 35번

`#loginForm` submit 시:
1. `e.preventDefault()`
2. `#userid` 값을 읽어 `console.log` 출력

---

## 36번

`#all` 체크박스 클릭 시 `input[name=hobby]` 전체를 같은 checked 상태로 맞추세요.

---

## 37번

`$(window).scroll()` 이벤트에서 `scrollTop()`이 **300 이상**이면 `#topBtn`을 `fadeIn`, 아니면 `fadeOut` 하세요.

---

## 38번

`$.getJSON("https://jsonplaceholder.typicode.com/posts?_limit=3", function(data){})` 로  
받은 데이터의 `title`을 `#list`(`<ul>`)에 `<li>`로 `append` 하세요.

---

# 제출 체크

| Part | 번호 | 유형 |
|------|------|------|
| 1 | 1~5 | 객관식 |
| 2 | 6~10 | 객관식 |
| 3 | 11~14 | 객관식 |
| 4 | 15~18 | 객관식 |
| 5 | 19~21 | 객관식 |
| 6 | 22~25 | 객관식 |
| 7 | 26~28 | 객관식 |
| 8 | 29~30 | 빈칸 |
| 9 | 31~38 | 코드 작성 |

---

## 참고

- 실습 8종: [jQuery/실습문제.md](./jQuery/실습문제.md)
- 예제 HTML: [jQuery/examples/](./jQuery/examples/)
