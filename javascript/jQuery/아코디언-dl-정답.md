# jQuery 실습 — dl/dt/dd 아코디언 (정답)

문제: [아코디언-dl-문제.md](./아코디언-dl-문제.md)

실행 예제: [examples/09-아코디언-dl.html](./examples/09-아코디언-dl.html)

---

## 핵심 로직

```javascript
$("dd").hide();
$("dd").eq(0).show();
$("dt").eq(0).addClass("selected");

$("dl dt").click(function () {
  // 닫혀 있을 때만 전환 (이미 열린 dt 재클릭 무시)
  if ($(this).next("dd").css("display") === "none") {
    $("dd").slideUp("slow");
    $(this).next().slideDown("slow");
    $("dl dt").removeClass("selected");
    $(this).addClass("selected");
  }
});

$("dl dt").mouseover(function () {
  $(this).addClass("over");
});
$("dl dt").mouseout(function () {
  $(this).removeClass("over");
});
```

---

## 서술 답

1. **`this`** = 클릭된 `dt`의 **DOM 객체**. **`$(this)`** = 그 DOM을 감싼 **jQuery 객체**라서 `.next()`, `.addClass()` 등을 쓸 수 있다.  
2. 열린 패널을 다시 누르면 `display`가 `none`이 아니므로 if에 안 들어가 **닫히지 않는다**. (한 칸은 항상 열린 채 유지하는 UI)  
3. `mouseover`/`mouseout`은 자식으로 들어갈 때도 발생할 수 있음. `mouseenter`/`mouseleave`는 요소 경계 기준이라 호버 UI에 더 안정적인 경우가 많음.

---

## 전체 코드

```html
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Step 아코디언</title>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
  <style>
    * { margin: 0; padding: 0; border: 0; }
    body { background-color: #252422; }
    dl { width: 800px; margin: 50px auto 0; max-width: 95%; }
    dt {
      line-height: 35px;
      font-size: large;
      text-indent: 3em;
      font-weight: bold;
      color: #fff;
      height: 35px;
      background-color: #555;
      /* 이미지가 있으면: background: url(a1.jpg) no-repeat 0 0; */
    }
    dt.over {
      background-color: #777;
      cursor: pointer;
      /* background: url(a2.jpg) no-repeat 0 0; */
    }
    dt.selected {
      background-color: #1d4ed8;
      cursor: pointer;
      /* background: url(a3.jpg) no-repeat 0 0; */
    }
    dd {
      margin: 0;
      height: 300px;
      background-color: #d4d0c8;
      overflow: auto;
    }
    dd p {
      margin: 0;
      text-indent: 1em;
      padding: 20px;
    }
  </style>
</head>
<body>
  <dl>
    <dt>Step.1</dt>
    <dd>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis neque urna, molestie id cursus non,
        bibendum nec magna. Mauris consequat faucibus ante sollicitudin ullamcorper.</p>
    </dd>
    <dt>Step.2</dt>
    <dd>
      <p>Integer rhoncus felis mauris. Donec massa nisi, posuere vel posuere vel, suscipit sed magna.</p>
    </dd>
    <dt>Step.3</dt>
    <dd>
      <p>Integer pretium porttitor lectus at tempor. Donec nec cursus elit. Maecenas ornare malesuada urna.</p>
    </dd>
  </dl>

  <script>
    $("dd").hide();
    $("dd").eq(0).show();
    $("dt").eq(0).addClass("selected");

    $("dl dt").click(function () {
      if ($(this).next("dd").css("display") === "none") {
        $("dd").slideUp("slow");
        $(this).next().slideDown("slow");
        $("dl dt").removeClass("selected");
        $(this).addClass("selected");
      }
    });

    $("dl dt").mouseover(function () {
      $(this).addClass("over");
    });
    $("dl dt").mouseout(function () {
      $(this).removeClass("over");
    });
  </script>
</body>
</html>
```
