# jQuery 학습 — 전체 목록 · 수업 가이드

K-Digital 풀스택용 jQuery 커리큘럼입니다.  
기존 자료와 함께 쓰세요.

| 기존 자료 | 용도 |
|-----------|------|
| [jQuery-JavaScript-선택자-탐색-조작-문제.md](../jQuery-JavaScript-선택자-탐색-조작-문제.md) | 선택자·탐색 객관식 |
| [jquery_to_vanilla/](../jquery_to_vanilla/) | jQuery → Vanilla 대응 |
| [jquery_to_vanilla_event/](../jquery_to_vanilla_event/) | 이벤트 |
| [jquery_to_vanilla_effect/](../jquery_to_vanilla_effect/) | Effect |
| [jquery_to_vanilla_animate/](../jquery_to_vanilla_animate/) | animate |

---

## 폴더 구성

```
javascript/jQuery/
├── README.md
├── 01-기본-선택자-조작.md
├── 02-이벤트-탐색-DOM.md
├── 03-효과-폼-AJAX.md
├── 오늘수업-이벤트-효과-메뉴.md  ← 이벤트·Effect·animate·메뉴 당일 정리
├── 아코디언-dl-문제.md / 아코디언-dl-정답.md
├── 실습문제.md / 실습정답.md
└── examples/
```

당일 수업 예제 모음: [오늘수업-이벤트-효과-메뉴.md](./오늘수업-이벤트-효과-메뉴.md)  
당일 문제·정답: [오늘수업-문제.md](./오늘수업-문제.md) · [오늘수업-정답.md](./오늘수업-정답.md)
---

## 전체 학습 목록 (31 + 실습)

### Part A. 기본 (1~9) ⭐~⭐⭐⭐

| # | 주제 | 중요도 | 핵심 |
|---|------|--------|------|
| 1 | jQuery 기본 개념 | ⭐ | CDN, `$()`, `$(function(){})` |
| 2 | 선택자 | ⭐⭐⭐ | `#` `.` 자식/속성/`eq` |
| 3 | jQuery 객체 vs DOM | ⭐⭐⭐ | `$(".box")` vs `querySelector` |
| 4 | 내용 변경 | ⭐⭐ | `html` `text` `val` |
| 5 | 속성 | ⭐⭐⭐ | `attr` `removeAttr` |
| 6 | CSS 조작 | ⭐⭐⭐ | `css()` |
| 7 | 클래스 조작 | ⭐⭐⭐ | `add/remove/toggle/hasClass` |
| 8 | 크기 | ⭐⭐ | `width` `inner` `outer` |
| 9 | 위치 | ⭐⭐ | `offset` `position` |

→ [01-기본-선택자-조작.md](./01-기본-선택자-조작.md)

### Part B. 이벤트 · DOM (10~19) ⭐⭐⭐~⭐⭐⭐⭐⭐

| # | 주제 | 중요도 | 핵심 |
|---|------|--------|------|
| 10 | 이벤트 | ⭐⭐⭐⭐⭐ | click, mouse, key, form |
| 11 | `on()` | ⭐⭐⭐⭐⭐ | 실무 표준 |
| 12 | 이벤트 객체 | ⭐⭐⭐ | `preventDefault` `stopPropagation` |
| 13 | 이벤트 위임 | ⭐⭐⭐⭐⭐ | `$("#list").on("click","li",…)` |
| 14 | `this` / `$(this)` | ⭐⭐⭐⭐⭐ | DOM vs jQuery |
| 15 | DOM 탐색 | ⭐⭐⭐⭐⭐ | parent children find siblings |
| 16 | 요소 추가 | ⭐⭐⭐⭐ | append prepend before after |
| 17 | 요소 삭제 | ⭐⭐⭐ | remove vs empty |
| 18 | 복사 | ⭐⭐ | clone |
| 19 | 반복 | ⭐⭐⭐⭐ | `$.each` / `.each()` |

→ [02-이벤트-탐색-DOM.md](./02-이벤트-탐색-DOM.md)

### Part C. 효과 · 폼 · AJAX (20~31)

| # | 주제 | 중요도 | 핵심 |
|---|------|--------|------|
| 20 | Effect | ⭐⭐⭐⭐ | show hide toggle |
| 21 | Fade | ⭐⭐⭐ | fadeIn/Out/Toggle |
| 22 | Slide | ⭐⭐⭐⭐ | 아코디언 |
| 23 | animate | ⭐⭐⭐ | vs CSS transition |
| 24 | 체이닝 | ⭐⭐⭐⭐ | `$().css().slideUp()` |
| 25 | Form | ⭐⭐⭐⭐⭐ | val, :checked, :selected |
| 26 | 체크박스 | ⭐⭐⭐⭐ | 전체 선택 |
| 27 | attr vs prop | ⭐⭐⭐⭐ | checked, disabled |
| 28 | AJAX | ⭐⭐⭐⭐⭐ | `$.ajax` |
| 29 | 단축 메서드 | ⭐⭐⭐ | `$.get` `$.post` `$.getJSON` |
| 30 | JSON + AJAX | ⭐⭐⭐⭐⭐ | Spring 연동 개념 |
| 31 | AJAX POST | ⭐⭐⭐⭐⭐ | `JSON.stringify` |

→ [03-효과-폼-AJAX.md](./03-효과-폼-AJAX.md)

### Part D. 실습 프로젝트

| # | 실습 | 사용 API | 파일 |
|---|------|----------|------|
| 1 | 탭 메뉴 | 클래스·이벤트 | `examples/01-탭메뉴.html` |
| 2 | 아코디언 | slideToggle | `02-아코디언.html` |
| 3 | 이미지 갤러리 | attr | `03-이미지갤러리.html` |
| 4 | 롤링 배너 | animate | `04-롤링배너.html` |
| 5 | 회원가입 유효성 | val, 이벤트 | `05-회원가입유효성.html` |
| 6 | 동적 테이블 | append | `06-동적테이블.html` |
| 7 | 전체 선택 체크박스 | prop | `07-전체선택체크박스.html` |
| 8 | AJAX 게시판 | $.ajax + JSON | `08-AJAX게시판.html` |
| 9 | dl/dt/dd 아코디언 | slide + selected/over | [아코디언-dl-문제.md](./아코디언-dl-문제.md) |

→ [실습문제.md](./실습문제.md) · [실습정답.md](./실습정답.md)  
→ dl 아코디언: [아코디언-dl-문제.md](./아코디언-dl-문제.md) · [아코디언-dl-정답.md](./아코디언-dl-정답.md)

---

## 추천 수업 순서 (25단계)

1. jQuery란? → CDN / `$()`  
2. 선택자  
3. `html` / `text` / `val`  
4. `attr` / `prop`  
5. `css`  
6. `addClass` / `removeClass` / `toggleClass`  
7. click / mouse / keyboard  
8. `on()`  
9. `this` / `$(this)`  
10. parent / children / siblings / find  
11. append / prepend / before / after  
12. remove / empty  
13. `each()`  
14. show / hide / toggle  
15. fade  
16. slide  
17. animate  
18. 메서드 체이닝  
19. Form / checkbox / select  
20. AJAX  
21. JSON  
22. GET / POST  
23. Spring Boot REST 연동 개념  
24. AJAX 게시판 실습  
25. (선택) Vanilla로 같은 기능 다시 쓰기  

---

## CDN (수업용)

```html
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
$(function () {
  console.log("jQuery 준비됨", $.fn.jquery);
});
</script>
```

> 스크립트는 **jQuery CDN 아래**에 두세요. (`$ is not defined` 방지)
