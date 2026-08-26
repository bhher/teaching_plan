# jQuery · JavaScript 선택자 · 탐색 · 조작 문제

제공된 예제(기본 선택자 / 탐색·html 조작)를 바탕으로 한 문제입니다.

정답: [jQuery-JavaScript-선택자-탐색-조작-정답.md](./jQuery-JavaScript-선택자-탐색-조작-정답.md)

---

# Part 1. 기본 선택자 · CSS (예제 1)

## 1번

jQuery를 CDN으로 연결했는데 콘솔에 `$ is not defined` 가 뜬다. 원인은?

① HTML에 `$` 변수가 없어서  
② **jQuery 스크립트 경로/주소가 잘못되었거나, 내 코드보다 아래에 로드됨**  
③ CSS 파일이 없어서  
④ `$(function(){})` 문법이 틀려서만

---

## 2번

```javascript
$(function(){ ... });
```

위 코드의 의미로 옳은 것은?

① 페이지를 새로고침한다  
② **문서(DOM)가 준비되면** 안의 코드를 실행한다  
③ 클릭할 때마다 실행한다  
④ CSS만 로드한 뒤 실행한다

---

## 3번

Vanilla JS에서 `$(function(){})` 와 가장 비슷한 것은?

① `window.onload` 만 가능  
② `document.addEventListener('DOMContentLoaded', function(){ ... })`  
③ `document.querySelector('ready')`  
④ `setTimeout(..., 0)` 만

---

## 4번

모든 요소 글자 크기를 13px로 바꾸는 jQuery는?

① `$('all').css('font-size','13px')`  
② `$('*').css('font-size','13px')`  
③ `$('body').fontSize(13)`  
④ `$('#*').css('fontSize','13px')`

---

## 5번

`id="firstTitle"` 만 초록색으로 — jQuery / JS 짝이 맞는 것은?

① `$('#firstTitle')` / `document.getElementById('firstTitle')`  
② `$('.firstTitle')` / `document.querySelector('#firstTitle')` 만 가능  
③ `$('firstTitle')` / `getElementsByClassName('firstTitle')`  
④ `$('#firstTitle')` / `querySelector('.firstTitle')`

---

## 6번

클래스 `nextTitle` **여러 개**에 파란색 — JS로 올바른 것은?

① `document.querySelector('.nextTitle').style.color = 'blue'` 만으로 전부 적용  
② `document.querySelectorAll('.nextTitle').forEach(el => el.style.color = 'blue')`  
③ `document.getElementById('nextTitle')`  
④ `$('.nextTitle')` 만 되고 JS는 불가

---

## 7번

`.obj3 > li` 의미는?

① `.obj3`의 **모든 자손** `li`  
② `.obj3`의 **바로 아래 자식** `li`  
③ `obj3`라는 id의 li  
④ 형제 li 전부

---

## 8번

체이닝에 대한 설명으로 옳은 것은?

① CSS만 가능하다  
② **하나의 선택자에 여러 `.css()` 등을 이어 붙이는 기법**  
③ 반드시 `forEach`가 필요하다  
④ jQuery에서만 되고 의미는 없다

```javascript
$('#wrap h3').css('font-size','20px').css('background-color','yellow');
```

---

## 9번

`.obj5`의 **부모**에 빨간 점선 테두리 — jQuery / JS?

① `.parent()` / `.parentNode`  
② `.children()` / `.childNodes`  
③ `.next()` / `.nextElementSibling`  
④ `.prev()` / `.previousElementSibling`

---

## 10번

`.obj4`의 **다음 형제** 글자색 orange — jQuery?

① `$('.obj4').prev()`  
② `$('.obj4').next()`  
③ `$('.obj4').parent()`  
④ `$('.obj4').siblings()`

JS 대응: `nextElementSibling`

---

## 11번

`.obj5`의 **이전 형제** 오른쪽 정렬 — jQuery?

① `.next()`  
② `.prev()`  
③ `.parent()`  
④ `.find()`

---

## 12번

`.obj6`을 **제외한** 형제 `li`만 가운데 정렬 — jQuery?

① `$('.obj6').children('li')`  
② `$('.obj6').siblings('li')`  
③ `$('.obj6').parent()`  
④ `$('.obj6').nextAll()`

---

## 13번

`type="text"` 인 입력창 선택 — jQuery에서 둘 다 되는 것은?

① `$('input[type=text]')` 와 `$(':text')`  
② `$('#text')` 만  
③ `$('text')` 만  
④ `$('.text')` 만

---

## 14번 (빈칸)

jQuery에서 CSS 속성을 줄 때:

```javascript
$('h1').________('color', 'red');
```

JS에서는:

```javascript
document.querySelector('h1').________.color = 'red';
```

---

# Part 2. 탐색 선택자 (예제 2 — list1)

`#list1` 에 li가 7개(인덱스 0~6)일 때.

## 15번

`$("#list1 li:odd")` 가 선택하는 것은? (jQuery 기준, **첫 li 인덱스 0**)

① 1,3,5번째 화면 항목 (인덱스 0,2,4)  
② **홀수 인덱스** 1,3,5 …  
③ 짝수 인덱스만  
④ 마지막만

---

## 16번

`:odd` / `:even` 과 CSS `nth-child` 차이에 대한 설명으로 알맞은 것은?

① 완전히 같다  
② jQuery `:even/:odd`는 **0부터**, `nth-child`는 **1부터** 세는 점이 달라 헷갈리기 쉽다  
③ `nth-child`만 0부터  
④ 둘 다 문자열만 센다

---

## 17번

첫 번째 / 마지막 li — jQuery?

① `:first` / `:last`  
② `:eq(0)` / `:eq(-1)` 만 가능  
③ `:odd` / `:even`  
④ `:lt(0)` / `:gt(0)`

---

## 18번

`$(" #list1 li").eq(1)` 의미는?

① 화면에 보이는 1번째(사람이 세는 1)  
② **인덱스 1** (두 번째 li)  
③ id가 1인 li  
④ 모든 li

---

## 19번

`$(" #list1 li:lt(2)")` 가 고르는 인덱스?

① 2만  
② **0, 1** (2보다 작은)  
③ 2, 3, 4…  
④ 없음

`lt` = less than, `gt` = greater than

---

## 20번

`$(" #list1 li:gt(2)")` 가 고르는 인덱스?

① 0,1,2  
② **3, 4, 5…** (2보다 큰)  
③ 2만  
④ 전체

---

# Part 3. 내용·필터 · HTML 조작 (예제 2 — list2, list3)

## 21번

`$(" #list2 li:contains('리스트11')")` 의미는?

① class가 리스트11인 li  
② **텍스트에 '리스트11'이 포함된** li  
③ 11번째 li만  
④ span이 있는 li

---

## 22번

`$(" #list2 li:has('span')")` 의미는?

① span 글자색만 변경  
② **자식(자손)으로 `span`을 가진** li  
③ contains와 같다  
④ a 태그만

---

## 23번

```javascript
$("#list2 li").find("a").css("background-color", "red");
```

의미로 옳은 것은?

① list2의 li와 형제 a  
② **각 li 안에서 `a`를 찾아** 배경색  
③ list2 직계 자식 a만 (find 아님)  
④ body의 모든 a

---

## 24번

`.html()` 과 `.text()` 차이로 옳은 것은?

① 같다  
② **`.html()`은 HTML 태그 해석**, `.text()`는 글자만  
③ `.text()`만 태그 삽입 가능  
④ `.html()`은 읽기만 가능

```javascript
$(".theTitle").html("<a href='#'>요소 객체 조작</a>");
$(".obj1").text("리스트_2");
```

---

## 25번

`$("#list3").append("<li>list_6</li>")` 는?

① 맨 **앞**에 추가  
② 맨 **뒤**에 추가  
③ 교체  
④ 삭제

`prepend` = 맨 앞

---

## 26번

```javascript
$("<li>insertBefore</li>").insertBefore($('#list3 li').eq(4));
```

의미는?

① eq(4) **다음**에 삽입  
② eq(4) **이전**에 삽입  
③ list3 맨 끝  
④ eq(4)를 삭제

`insertAfter` = 기준 요소 **다음**

---

## 27번

```javascript
$('.obj1').clone().appendTo("#list3");
```

의미는?

① obj1을 잘라 list3로 이동  
② **obj1을 복사**해 list3 끝에 붙임  
③ obj1 삭제  
④ list3를 복사

---

## 28번

```javascript
var k = $('#list3 li').eq(0).attr('class');
$('#list3 li').eq(0).attr('class', 'obj2');
```

JS 대응으로 가까운 것은?

① `getAttribute` / `setAttribute`  
② `querySelector` / `remove`  
③ `innerHTML` / `outerHTML`  
④ `classList`만 가능하고 attr은 없음

---

## 29번

`.addClass("obj3")` 의 특징은?

① 기존 class를 **지우고** obj3만 남긴다  
② 기존 class에 **obj3를 추가**한다  
③ id를 바꾼다  
④ CSS 파일만 수정한다

---

## 30번 (빈칸)

| jQuery | JavaScript (유사) |
|--------|-------------------|
| `.parent()` | `________` |
| `.next()` | `________` |
| `.prev()` | `________` |
| `.append()` | `________` (또는 `insertAdjacentHTML`) |
| `.attr('class')` | `________` |

---

# Part 4. 코드 작성

## 31번

jQuery로 `#list1`의 **짝수 인덱스** li 배경을 `gray`, **홀수 인덱스**를 `yellow`로 만드세요.

---

## 32번

Vanilla JS로 `.obj3 > li` 글자를 bold, `.obj3 > .theObj1` 글자색 purple로 만드세요.  
(`DOMContentLoaded` 사용)

---

## 33번

jQuery로 `#list3` 맨 앞에 `list_1`, 맨 뒤에 `list_6` li를 추가하세요.

---

## 34번

jQuery ↔ JS 변환: 아래 jQuery를 Vanilla JS로 바꾸세요.

```javascript
$('.obj5').parent().css('border', 'dashed 2px #f00');
$('.obj4').next().css('color', 'orange');
```

---

## 참고 HTML 구조 (요약)

- 예제1: `#wrap`, `#firstTitle`, `.nextTitle`, `.obj3`~`.obj6`, `input[type=text]`
- 예제2: `#list1` 탐색, `#list2` contains/has/find, `#list3` append/prepend/attr
