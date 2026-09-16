# jQuery 필수 체크리스트

풀스택(HTML/CSS → JavaScript → **jQuery** → Ajax → Spring Boot) 기준.  
학생이 **직접 할 수 있어야 하는** 항목입니다.

**통합 교안:** [JavaScript-jQuery-한방정리.md](./JavaScript-jQuery-한방정리.md)  
**상세:** [jQuery-한방정리.md](./jQuery-한방정리.md)  
**문제:** [jQuery-한방정리-문제.md](./jQuery-한방정리-문제.md)

---

## 목차

1. [시작 · $()](#-1단계--시작--)
2. [선택자](#-2단계--선택자-)
3. [내용 · CSS · 값](#-3단계--내용--css--값-)
4. [클래스 · 속성](#-4단계--클래스--속성-)
5. [이벤트 · this](#-5단계--이벤트--this-)
6. [탐색](#-6단계--탐색-)
7. [추가 · 삭제 · 효과](#-7단계--추가--삭제--효과-)
8. [Ajax](#-8단계--ajax-)
9. [종합 실습](#-9단계--종합-실습-)
10. [우선순위](#우선순위-정리)

---

## 🟢 1단계 — 시작 · $() ⭐⭐⭐

- [ ] CDN으로 jQuery를 연결할 수 있다
- [ ] jQuery가 **라이브러리**임을 설명한다 (언어 아님)
- [ ] `$(function(){ ... })` = DOM 준비 후 실행
- [ ] `$()` 가 선택 + jQuery 객체 반환임을 안다

---

## 🟢 2단계 — 선택자 ⭐⭐⭐

- [ ] `$("#id")` / `$(".class")` / `$("tag")`
- [ ] 자손·자식 선택 (`$("ul li")`, `$("ul > li")`)
- [ ] 여러 요소가 선택되면 **집합**으로 동작함을 안다

---

## 🟢 3단계 — 내용 · CSS · 값 ⭐⭐⭐

- [ ] `.text()` / `.html()` 구분해서 사용
- [ ] `.val()` 로 input 읽기·쓰기
- [ ] `.css("color", "red")` 또는 객체로 여러 속성

---

## 🟢 4단계 — 클래스 · 속성 ⭐⭐⭐

- [ ] `.addClass` / `.removeClass` / `.toggleClass`
- [ ] `.hasClass("on")`
- [ ] `.attr("href")` / `.attr("src", "...")`
- [ ] 체크박스는 `.prop("checked")` 를 안다

---

## 🟡 5단계 — 이벤트 · this ⭐⭐⭐

- [ ] `.on("click", function(){})` 작성
- [ ] `e.preventDefault()` 사용
- [ ] 이벤트 안에서 **`$(this)`** = 클릭한 그 요소
- [ ] `click` / `keyup` / `submit` / `scroll` 중 3개 이상

---

## 🟡 6단계 — 탐색 ⭐⭐⭐

- [ ] `.parent()` / `.children()`
- [ ] `.find(".item")`
- [ ] `.siblings()` / `.next()` / `.prev()`
- [ ] 탭·아코디언에서 탐색을 조합한다

---

## 🟡 7단계 — 추가 · 삭제 · 효과 ⭐⭐

- [ ] `.append()` / `.prepend()`
- [ ] `.remove()` / `.empty()`
- [ ] `.hide()` / `.show()` / `.toggle()`
- [ ] `.slideToggle()` (아코디언)
- [ ] `.fadeIn()` / `.fadeOut()` (알면 가점)
- [ ] `.each(function(i){})` 로 반복

---

## 🟠 8단계 — Ajax ⭐⭐

- [ ] `$.ajax({ url, type, success, error })` 기본형 작성
- [ ] GET으로 JSON 받아 화면에 출력
- [ ] POST로 데이터 전송 (한 번)
- [ ] JS `fetch` 와 역할이 같음을 설명한다

---

## 🔴 9단계 — 종합 실습 ⭐⭐⭐

**2개 이상** 혼자 구현:

- [ ] 탭 메뉴 (버튼 on + 패널 전환)
- [ ] 아코디언 (`slideToggle` + siblings)
- [ ] 전체선택 체크박스
- [ ] TOP 버튼 (`scrollTop`)
- [ ] 동적 테이블/목록 append
- [ ] 폼 유효성 (val + preventDefault)

**예제 참고:** [jQuery/examples/](./jQuery/examples/)

---

## 우선순위 정리

| 우선 | 내용 |
|------|------|
| 반드시 | CDN, $(), text/html/val/css, on+$(this), addClass, parent/find, hide/show/slideToggle |
| 든든히 | each, append/remove, scrollTop, $.ajax |
| 간단히 | animate, 복잡한 필터, 플러그인 내부 |

### JS와 헷갈리지 않기

| 질문 | 답 |
|------|-----|
| jQuery 없으면? | **JavaScript로 같은 일 가능** |
| 언제 jQuery? | 빠른 UI, 기존 프로젝트, 수업 패턴 |
| 서버 통신 본질 | **HTTP + JSON** (ajax든 fetch든) |

### 자가 진단 (8문항)

1. [ ] `$(function(){})`  
2. [ ] `$("#")` `$(".")`  
3. [ ] `.text` `.val` `.css`  
4. [ ] `.on("click")` + `$(this)`  
5. [ ] `.addClass` / `.toggleClass`  
6. [ ] `.parent` 또는 `.find`  
7. [ ] `.slideToggle` 또는 `.hide`  
8. [ ] 탭 또는 아코디언 1개  

**6개 이상 → Ajax·실무 UI 가능**  
**4~5 → 이벤트·탐색 복습**  
**3 이하 → 1~5단계 재학습**
