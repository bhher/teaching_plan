# jQuery 한방 정리 — 정답

문제: [jQuery-한방정리-문제.md](./jQuery-한방정리-문제.md)

---

# Part 1. 기본 · 선택자 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 1 | ② | jQuery = DOM 조작 **라이브러리** (JavaScript 위에서 동작) |
| 2 | ② | `$(function(){})` = DOM 준비 후 실행 (= `$(document).ready()`) |
| 3 | ② | `#` = id 선택자 |
| 4 | ② | `>` = **직계 자식** 선택 |
| 5 | ② | `$()` → **jQuery 객체** 반환 (DOM과 다름) |

---

# Part 2. 내용 · CSS · 클래스 · 속성 · form 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 6 | ② | `.text()` → HTML 태그를 **문자**로 표시 |
| 7 | ② | `.html()` → 태그 **적용** |
| 8 | ③ | input 값 → `.val()` |
| 9 | ③ | `toggleClass` = 토글 (있↔없) |
| 10 | ② | 속성 변경 → `.attr("src", "...")` |

### 메서드 구분표

| 메서드 | 용도 |
|--------|------|
| `.text()` | 텍스트 |
| `.html()` | HTML |
| `.val()` | input 값 |
| `.attr()` | HTML 속성 |
| `.css()` | CSS |

---

# Part 3. 이벤트 · this 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 11 | ② | `.on()` 권장 (동적 요소·여러 이벤트에 유리) |
| 12 | ② | `e.preventDefault()` → 기본 동작(제출·이동) 차단 |
| 13 | ② | `this` = DOM, `$(this)` = jQuery 객체 |
| 14 | ② | form **submit** 이벤트 |

---

# Part 4. each · 탐색 · DOM 추가/삭제 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 15 | ① | `.each()` → jQuery 순회 |
| 16 | ② | `.find()` → **자손** 중 검색 |
| 17 | ② | `append` = **안쪽 맨 뒤** 추가 |
| 18 | ② | `remove` = 요소 삭제 / `empty` = 내부만 비움 |

---

# Part 5. 효과 · 스크롤 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 19 | ② | 아코디언 → `slideToggle` 등 |
| 20 | ② | `$(window).scrollTop()` = 스크롤 양 |
| 21 | ② | TOP 버튼 → `animate({ scrollTop: 0 })` |

---

# Part 6. form · 필터 · UI/플러그인 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 22 | ② | `:checked` / `.prop("checked")` |
| 23 | ② | `:eq(2)` = **0부터** → 3번째 |
| 24 | ② | `draggable()` = **jQuery UI** |
| 25 | ② | `$.cookie()` = **플러그인** (본체 아님) |

### jQuery 구분

| API | 정체 |
|-----|------|
| `$()`, `.on()`, `$.ajax()` | jQuery **본체** |
| `.draggable()` | jQuery **UI** |
| `$.cookie()` | **플러그인** |

---

# Part 7. Ajax · Spring Boot 정답

| 문제 | 정답 | 해설 |
|------|------|------|
| 26 | ① | GET → `$.get()` / `$.ajax({ type:"GET" })` |
| 27 | ① | POST JSON → type, contentType, JSON.stringify |
| 28 | ② | 브라우저 → $.ajax → Controller → … → DB |

---

# Part 8. 빈칸 정답

| 문제 | 정답 |
|------|------|
| 29 | `$` |
| 30 | `on` |

---

# Part 9. 코드 작성 정답 (31~38)

## 31번. 텍스트 변경

```javascript
$(function () {
    $("#btn").on("click", function () {
        $("#title").text("안녕 jQuery");
    });
});
```

---

## 32번. mouseover / mouseout

```javascript
$(function () {
    $("#box").on("mouseenter", function () {
        $(this).css("color", "red");
    }).on("mouseleave", function () {
        $(this).css("color", "black");
    });
});
```

---

## 33번. slideToggle

```javascript
$(function () {
    $("#toggleBtn").on("click", function () {
        $("#panel").slideToggle(300);
    });
});
```

---

## 34번. li 클릭 → alert

```javascript
$(function () {
    $("ul li").on("click", function () {
        alert($(this).text());
    });
});
```

---

## 35번. form submit

```javascript
$(function () {
    $("#loginForm").on("submit", function (e) {
        e.preventDefault();
        var userid = $("#userid").val();
        console.log(userid);
    });
});
```

---

## 36번. 전체 선택 체크박스

```javascript
$(function () {
    $("#all").on("click", function () {
        $("input[name=hobby]").prop("checked", this.checked);
    });

    $("input[name=hobby]").on("change", function () {
        var total = $("input[name=hobby]").length;
        var checked = $("input[name=hobby]:checked").length;
        $("#all").prop("checked", total === checked);
    });
});
```

---

## 37번. scrollTop + TOP 버튼

```javascript
$(function () {
    $("#topBtn").hide();

    $(window).on("scroll", function () {
        if ($(window).scrollTop() >= 300) {
            $("#topBtn").fadeIn();
        } else {
            $("#topBtn").fadeOut();
        }
    });

    $("#topBtn").on("click", function () {
        $("html, body").animate({ scrollTop: 0 }, 500);
    });
});
```

---

## 38번. getJSON + append

```javascript
$(function () {
    $.getJSON("https://jsonplaceholder.typicode.com/posts?_limit=3", function (data) {
        $.each(data, function (index, post) {
            $("#list").append("<li>" + post.title + "</li>");
        });
    });
});
```

또는:

```javascript
$(function () {
    $.ajax({
        url: "https://jsonplaceholder.typicode.com/posts?_limit=3",
        type: "GET",
        dataType: "json",
        success: function (data) {
            data.forEach(function (post) {
                $("#list").append($("<li>").text(post.title));
            });
        },
        error: function () {
            alert("데이터를 불러올 수 없습니다.");
        }
    });
});
```

---

## JavaScript vs jQuery 대응표

| 작업 | JavaScript | jQuery |
|------|------------|--------|
| 선택 | `querySelector("#id")` | `$("#id")` |
| 텍스트 | `.textContent` | `.text()` |
| HTML | `.innerHTML` | `.html()` |
| 클래스 | `.classList.add()` | `.addClass()` |
| 이벤트 | `.addEventListener()` | `.on()` |
| 숨기기 | `style.display = "none"` | `.hide()` |
| Ajax | `fetch()` | `$.ajax()` / `$.getJSON()` |

---

## 핵심 정리

| 영역 | 필수 |
|------|------|
| 기본 | CDN, `$(function(){})`, `$()` |
| 조작 | text/html/val/css/attr, toggleClass |
| 이벤트 | `.on()`, preventDefault, `$(this)` |
| DOM | each, find, append, remove/empty |
| 효과 | hide/show, slideToggle, scrollTop |
| 서버 | $.ajax, $.getJSON → Spring Boot |

---

## 참고

- [jQuery-한방정리.md](./jQuery-한방정리.md)
- [jQuery/실습정답.md](./jQuery/실습정답.md)
- [JavaScript-한방정리.md](./JavaScript-한방정리.md)
