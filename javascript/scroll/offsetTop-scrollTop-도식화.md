# offsetTop · scrollTop 도식화

관련 예제: [scroll1.html](./scroll1.html) · [offsettop.html](./offsettop.html)  
상세: [offset_scroll/offset_scroll_vanilla/offset-scroll-완전정복.md](../offset_scroll/offset_scroll_vanilla/offset-scroll-완전정복.md)

---

## 한 줄 요약

| 이름 | 의미 | 비유 |
|------|------|------|
| **scrollTop** | 지금 **얼마나 스크롤했는지** (위에서 내려온 거리) | 책갈피를 몇 cm 넘겼나 |
| **offsetTop** / **offset().top** | 요소가 **문서 맨 위에서** 얼마나 떨어져 있는지 | 그 박스가 몇 페이지째에 있나 |

```javascript
$(window).scroll(function () {
    var sct = $(window).scrollTop();   // 스크롤한 양
    var boxTop = $('.box1').offset().top;  // 박스1의 문서 기준 위치

    if (sct > 500) { /* 애니메이션 */ }
});
```

---

## 1. 전체 페이지 구조

```
┌─ document (전체 문서, height: 3000px) ─────────────────────┐
│  y=0   ← 문서 맨 위 (시작점)                                │
│  ┌─ viewport (보이는 화면) ─────────────┐                  │
│  │  헤더, 고정 요소 .sTop               │  ← 처음엔 여기만 보임
│  └──────────────────────────────────────┘                  │
│                                                             │
│         ┌ box1 ────┐  ← offset().top ≈ 300px               │
│         │  박스1   │                                        │
│         └──────────┘                                        │
│                                                             │
│         ┌ box2 ────┐  ← offset().top ≈ 1200px              │
│         │  박스2   │                                        │
│         └──────────┘                                        │
│                    ...                                      │
│         ┌ box4 ────┐  ← offset().top ≈ 2800px              │
│         │  박스4   │                                        │
│         └──────────┘                                        │
│  y=3000  문서 맨 아래                                        │
└─────────────────────────────────────────────────────────────┘
```

---

## 2. scrollTop — 스크롤한 양

마우스 휠로 **아래로 내린 만큼** scrollTop이 커집니다.

### 스크롤 전 (scrollTop = 0)

```
y=0 ─── viewport 상단 ─────────────────
      │  보이는 화면          │
      │                       │
y=? ──└───────────────────────┘
      box1 (아직 아래에 있음)
```

### 스크롤 후 (scrollTop = 500)

```
y=0
      ·························  (위로 올라가서 안 보임)
y=500 ─ viewport 상단 ──────────  ← scrollTop = 500
      │  보이는 화면          │
      │  box1 근처            │
y=? ──└───────────────────────┘
```

```text
scrollTop = 0     → 맨 위
scrollTop = 500   → 500px 내려온 상태
scrollTop = 2000  → 2000px 내려온 상태
```

| API | 설명 |
|-----|------|
| jQuery | `$(window).scrollTop()` |
| Vanilla | `window.scrollY` 또는 `document.documentElement.scrollTop` |

---

## 3. offset().top — 요소의 문서 기준 위치

**스크롤과 관계없이** 박스가 문서에서 **고정된 y좌표**입니다.  
(페이지 레이아웃이 바뀌지 않는 한 값이 일정)

```
문서 맨 위 (y=0)
    │
    │  ← offset().top = 300
    ▼
┌─────────┐
│  box1   │
└─────────┘
```

```javascript
$('.box1').offset().top;  // 예: 300
$('.box2').offset().top;  // 예: 1200
```

| API | 기준 |
|-----|------|
| jQuery `offset().top` | **document** (문서 전체) |
| DOM `element.offsetTop` | **offsetParent** (가장 가까운 positioned 조상) |
| `getBoundingClientRect().top` | **viewport** (지금 보이는 화면) — 스크롤하면 변함 |

> 수업에서는 **`$(el).offset().top`** 과 **`$(window).scrollTop()`** 비교를 많이 씁니다.

---

## 4. 둘을 같이 보면 (scroll1.html)

```
문서 y축
  0 ─────────────────────────────
      [ viewport — scrollTop=0 ]
 300 ─ box1  ← offset().top = 300
      ...
 500 ─ scrollTop이 500이 되면
      → if (sct > 10)  box1 애니메이션 (이미 지남)
      → if (sct > 500) box2 애니메이션 시작
 1200 ─ box2
 1800 ─ box3
 2000 ─ if (sct >= 2000) box4 .on
 2800 ─ box4
```

### 비교 조건의 의미

```javascript
if (sct > 500) {
    $('.box2').animate({ left: '50%' }, 800);
}
```

```text
scrollTop(스크롤한 양)  >  500
        ↓
"화면을 500px 이상 내렸을 때" box2 실행
```

```javascript
if (sct >= 1200) {
    $(".box3").animate({ right: '50%' }, 800);
}
```

```text
"스크롤이 box3 위치(1200px) 근처까지 왔을 때" 실행
(정확히는 offset().top과 비교해도 됨)
```

---

## 5. 관계 도식 (한 장)

```
        문서 맨 위 (0)
              │
              │  offset().top = 300
              ▼
           [ box1 ]
              │
              │  ←── scrollTop = 300 이면
              │      box1이 화면 맨 위에 닿는 쯤
              ▼
    ┌─────────────────┐
    │    viewport     │  scrollTop = 현재 스크롤 위치
    │   (보이는 창)    │
    └─────────────────┘
              │
              ▼  스크롤 더 내리면 scrollTop 증가
```

**기억하기**

```text
offset().top  →  박스가 문서 어디에 박혀 있나 (고정 좌표)
scrollTop     →  사용자가 얼마나 내렸나 (변하는 값)

스크롤 이벤트에서:
  scrollTop 을 읽어서
  offset().top 과 비교하거나
  특정 숫자(500, 1200…)와 비교해 애니메이션 실행
```

---

## 6. jQuery vs Vanilla

| 목적 | jQuery | Vanilla |
|------|--------|---------|
| 스크롤 양 | `$(window).scrollTop()` | `window.scrollY` |
| 스크롤 이벤트 | `$(window).scroll(fn)` | `window.addEventListener('scroll', fn)` |
| 요소 문서 위치 | `$(el).offset().top` | `el.getBoundingClientRect().top + window.scrollY` |
| 요소 DOM offsetTop | — | `el.offsetTop` (부모 기준 주의) |

---

## 7. 자주 하는 실수

| 실수 | 설명 |
|------|------|
| `offset().top`과 `scrollTop` 혼동 | 하나는 **박스 위치**, 하나는 **스크롤 양** |
| `offsetTop` = `offset().top` | **다름**. offsetTop은 부모 기준, offset().top은 문서 기준 |
| 스크롤 안 해도 offset().top은 변함? | 레이아웃·리사이즈 시에만 변함. 스크롤만으로는 **안 변함** |
| `getBoundingClientRect().top` | 스크롤하면 **변함** (화면 기준이라서) |

---

## 8. 연습 체크

- [ ] scrollTop = 0일 때 화면 맨 위를 본다  
- [ ] box1의 offset().top이 300이면, 문서 시작에서 300px 아래에 있다  
- [ ] `sct > 500`은 "500px 이상 스크롤했을 때"  
- [ ] scroll1.html에서 `.sTop`에 `sct`를 출력해 스크롤 값 확인해 보기  

---

## 9. scroll1.html 핵심만

```javascript
$(window).scroll(function () {
    var sct = $(window).scrollTop();  // ① 스크롤 양
    $('.sTop').text(sct);             // 화면에 숫자 표시

    if (sct > 10)   $('.box1').animate(...);
    if (sct > 500)  $('.box2').animate(...);
    if (sct >= 1200) $('.box3').animate(...);
    if (sct >= 2000) $('.box4').addClass('on');
});
```

```text
① scrollTop 읽기
② 숫자 비교로 "스크롤이 이 지점을 지났는지" 판단
③ CSS/animate 실행
```
