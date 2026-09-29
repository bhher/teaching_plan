# Bootstrap 5 핵심 클래스 — 문제

**정리:** [Bootstrap-5-핵심클래스-정리.md](./Bootstrap-5-핵심클래스-정리.md)  
**정답:** [Bootstrap-5-핵심클래스-정답.md](./Bootstrap-5-핵심클래스-정답.md)  
**예제:** [index.html](./index.html) · [examples/](./examples/)

> 정리 문서(1~23)와 **같은 개념**으로 출제했습니다.  
> HTML 작성 문제는 CDN이 연결된 빈 HTML에 붙여 넣어 확인하세요.

---

# Part A. 객관식 · 단답

### 1번
페이지 콘텐츠의 **고정 폭(반응형 최대 너비)** 을 잡는 클래스는?

① `container-fluid`  
② **`container`**  
③ `row`  
④ `col-12`

### 2번
Bootstrap Grid의 기본 칸 수는?

① 10  ② **12**  ③ 16  ④ 100

### 3번
`col-4`의 너비로 가장 적절한 것은?

① 25%  ② **약 33.3%**  ③ 50%  ④ 100%

### 4번
아래 클래스의 의미로 옳은 것은?

```html
<div class="col-12 col-md-6 col-lg-4">
```

① 모바일 4칸, PC 12칸  
② **모바일 12칸, 태블릿 6칸, PC 4칸**  
③ 항상 4칸  
④ `md`에서만 표시

### 5번
`col-md-` 가 적용되는 최소 너비는?

① 576px  ② **768px**  ③ 992px  ④ 1200px

### 6번
`mb-3`의 의미는?

① margin 전체  
② **margin-bottom**  
③ padding-bottom  
④ margin-start

### 7번
좌우 margin을 자동으로 주어 **가운데** 놓는 클래스는?

① `mx-3`  ② **`mx-auto`**  ③ `my-auto`  ④ `ms-auto`

### 8번
요소를 **숨기는** Display 클래스는?

① `d-block`  ② `d-flex`  ③ **`d-none`**  ④ `d-inline`

### 9번
로고와 메뉴를 **양끝**에 두려면?

① `justify-content-center`  
② **`justify-content-between`**  
③ `align-items-center`  
④ `text-center`

### 10번
가로·세로 **모두 가운데** 정렬에 가까운 조합은?

① `d-block text-center`  
② **`d-flex justify-content-center align-items-center`**  
③ `mx-auto my-auto`  
④ `row col-12`

### 11번
`fs-1`과 `fs-6` 중 **더 큰** 글자는?

① `fs-6`  ② **`fs-1`**  ③ 같다  ④ 상황에 따라 다름

### 12번
버튼의 기본 조합으로 올바른 것은?

① `button button-primary`  
② **`btn btn-primary`**  
③ `btn-primary`만  
④ `primary-btn`

### 13번
Card의 일반적인 구조로 맞는 것은?

① `card` → `card-title` → `card-body`  
② **`card` → `card-body` → `card-title` / `card-text`**  
③ `card-body`만  
④ `card-img`만

### 14번
`<input type="text">`에 주로 붙이는 클래스는?

① `form-label`  ② **`form-control`**  ③ `form-select`  ④ `form-check`

### 15번
탭 내용 영역을 감싸는 클래스는?

① `nav-tabs`  ② `nav-link`  ③ **`tab-content`**  ④ `accordion`

### 16번 (단답)
`m` / `p` / `t` / `b` / `s` / `e` / `x` / `y` 각각의 뜻을 쓰시오.

### 17번 (단답)
반응형 이미지에 쓰는 클래스는?

### 18번 (단답)
줄무늬 + 마우스 오버 테이블을 만드는 클래스 조합을 쓰시오.

### 19번 (단답)
모달을 열 때 버튼에 필요한 `data-bs-` 속성 **두 가지**를 쓰시오.

### 20번 (단답)
수업에서 꼭 익히라는 **10개** 중 Grid·버튼·폼에 해당하는 것을 각각 하나씩 쓰시오.

---

# Part B. 맞는 것 고르기 / 틀린 것 찾기

### 21번
다음에 **틀린** 설명은?

① `container-fluid`는 화면 전체 너비이다.  
② `col-6`은 대략 절반이다.  
③ **`WHERE`처럼 `mt`는 padding-top이다.**  
④ `d-flex`는 Flexbox를 켠다.

### 22번
다음에 **옳은** 것은?

① Accordion 핵심은 `data-bs-toggle="tab"` 이다.  
② **Navbar 햄버거 버튼은 `navbar-toggler`이다.**  
③ Badge는 `btn-badge`만 쓴다.  
④ `w-50`은 높이 50%이다.

### 23번
Alert로 **오류** 메시지를 보여줄 때 적절한 클래스는?

① `alert alert-success`  
② **`alert alert-danger`**  
③ `badge bg-danger`만  
④ `text-danger`만 (alert 없이)

### 24번
아래 HTML에서 **빠진 것**으로 가장 중요한 것은?

```html
<ul class="nav nav-tabs">
  <li class="nav-item">
    <button class="nav-link active">소개</button>
  </li>
</ul>
<div class="tab-content">
  <div class="tab-pane fade show active" id="pane-me">내용</div>
</div>
```

① `card`  
② **`data-bs-toggle="tab"` 과 `data-bs-target`**  
③ `container`  
④ `btn-primary`

---

# Part C. HTML 작성

### 25번
`container` 안에 제목 `h2.text-center` 로 「Bootstrap 연습」을 넣는 코드를 쓰시오.

### 26번
한 줄에 `col-6` 두 칸(왼쪽/오른쪽 문구)을 배치하시오. (`row` 포함)

### 27번
상품 카드 한 개를 작성하시오.  
조건: `card` · `card-body` · `card-title`(노트북) · `card-text` · `btn btn-primary`(구매)

### 28번
이름 입력 폼 한 줄을 작성하시오.  
(`form-label`, `form-control`, placeholder 포함)

### 29번
성공 알림: 「저장되었습니다.」를 Alert로 작성하시오.

### 30번
아래 조건을 **한 덩어리**로 작성하시오. (조합 문제)

- `container` → `row` → `col-12 col-md-6 col-lg-4`
- 안에 `card shadow mb-3`
- `card-body text-center`
- 제목 `fw-bold` 「상품명」
- 설명 `text-secondary`
- 버튼 `btn btn-primary` 「구매하기」

### 31번
`d-flex justify-content-between align-items-center` 로  
왼쪽 「상품 목록」, 오른쪽 `btn btn-primary btn-sm` 「등록」을 배치하시오.

### 32번
Navbar 최소 구조만 작성하시오.  
(`navbar navbar-expand-lg`, `navbar-brand`, `navbar-nav`에 링크 2개)

---

# Part D. 실습 확인 (브라우저)

아래를 예제에서 **직접 확인**하고, 사용한 핵심 클래스를 적으시오.

### 33번
[examples/02-grid.html](./examples/02-grid.html) 을 열고  
창 크기를 줄였을 때 `col-12 col-md-6 col-lg-4` 배치가 어떻게 바뀌는지 한 줄로 쓰시오.

### 34번
[examples/06-쇼핑몰.html](./examples/06-쇼핑몰.html) 또는 [onepage-portfolio/](./onepage-portfolio/) 에서  
**Tab** 또는 **Accordion**에 쓰인 클래스/속성 3개를 적으시오.

### 35번 (서술)
「Bootstrap은 암기보다 조합」이라는 말의 뜻을,  
`container → row → col → card → btn` 흐름으로 3~4문장 설명하시오.

---

## 채점

| Part | 문항 | 배점 |
|------|------|------|
| A | 1~20 | 40 |
| B | 21~24 | 10 |
| C | 25~32 | 40 |
| D | 33~35 | 10 |
| **합** | | **100** |

**통과:** 70점 + **25 · 27 · 30번** 작성 성공
