# jQuery 실습 — dl/dt/dd 아코디언 (문제)

`dl` · `dt` · `dd` 구조로 **스텝(아코디언)** UI를 만드세요.  
아래 요구사항만 보고 구현한 뒤, 정답과 비교하세요.

정답: [아코디언-dl-정답.md](./아코디언-dl-정답.md)

---

## 완성 모습 (동작)

1. 처음에는 **Step.1**만 열려 있고, Step.2 · Step.3 내용은 숨겨져 있다.  
2. 닫혀 있는 `dt`를 **클릭**하면  
   - 다른 `dd`는 천천히 접히고 (`slideUp`)  
   - 클릭한 제목 바로 아래 `dd`만 천천히 펼쳐진다 (`slideDown`)  
3. 이미 열려 있는 `dt`를 다시 클릭해도 **아무 일도 안 한다** (열린 것은 그대로).  
4. 마우스 올리면 `dt`에 `over` 클래스, 나가면 `over` 제거.  
5. 현재 열린 스텝의 `dt`에는 `selected` 클래스가 붙어 있다.

---

## HTML 구조 (그대로 사용)

```html
<dl>
  <dt>Step.1</dt>
  <dd><p>…내용…</p></dd>

  <dt>Step.2</dt>
  <dd><p>…내용…</p></dd>

  <dt>Step.3</dt>
  <dd><p>…내용…</p></dd>
</dl>
```

> `dt`와 `dd`가 **번갈아** 나와야 합니다. (`dt` 다음이 그 스텝의 `dd`)

---

## CSS 요구사항

- `body` 배경: `#252422`
- `dl` 너비 `800px`, 가운데 정렬 (`margin: 50px auto 0`)
- `dt`
  - 높이·줄간격 `35px`, 흰 글씨, 굵게, `text-indent: 3em`
  - 기본 배경 이미지: `a1.jpg` (왼쪽 아이콘 느낌)
- `dt.over` → 배경 `a2.jpg`, `cursor: pointer`
- `dt.selected` → 배경 `a3.jpg`, `cursor: pointer`
- `dd` 높이 `300px`, 배경 `#d4d0c8`
- `dd p` → `padding: 20px`, `text-indent: 1em`

이미지 `a1.jpg` / `a2.jpg` / `a3.jpg` 가 없으면, 색만 다르게 해도 됩니다.

예:

```css
dt { background-color: #555; }
dt.over { background-color: #777; }
dt.selected { background-color: #1d4ed8; }
```

---

## jQuery 요구사항 (핵심)

jQuery CDN(또는 로컬)을 연결한 뒤, 다음을 구현하세요.

### 1) 초기 상태

- 모든 `dd`를 숨긴다.  
- **첫 번째** `dd`만 보이게 한다.  
- **첫 번째** `dt`에 `selected` 클래스를 준다.

### 2) `dt` 클릭

- 클릭한 `dt`의 **다음 형제** `dd`가 지금 숨겨져 있을 때만 동작한다.  
  - 힌트: `$(this).next('dd').css('display') === 'none'`  
- 조건이 맞으면  
  1. 모든 `dd`를 `slideUp('slow')`  
  2. 클릭한 `dt`의 다음 `dd`만 `slideDown('slow')`  
  3. 모든 `dt`에서 `selected` 제거  
  4. 클릭한 `dt`에만 `selected` 추가  

### 3) 마우스 오버 / 아웃

- `mouseover` → 해당 `dt`에 `over` 추가  
- `mouseout` → 해당 `dt`에서 `over` 제거  

---

## 사용하면 좋은 메서드

| 메서드 | 용도 |
|--------|------|
| `$('dd').hide()` / `.show()` | 초기 표시 |
| `.eq(0)` | 첫 번째 |
| `.addClass` / `.removeClass` | selected, over |
| `.click` 또는 `.on('click')` | 클릭 |
| `.next('dd')` | 제목 다음의 내용 |
| `.css('display')` | 열려 있는지 확인 |
| `.slideUp` / `.slideDown` | 접기·펼치기 |
| `.mouseover` / `.mouseout` | 호버 |

---

## 체크리스트

- [ ] 처음엔 Step.1만 열려 있다  
- [ ] 다른 Step 클릭 시 이전 내용은 접히고 새 내용만 펼쳐진다  
- [ ] 이미 열린 Step을 다시 눌러도 안 닫힌다 (요구대로)  
- [ ] 호버 시 `over`, 선택 시 `selected`  
- [ ] HTML / CSS / jQuery가 한 파일(또는 분리)로 동작한다  

---

## 생각해 보기 (서술)

1. `$(this).next('dd')`에서 `this`는 무엇을 가리키는가? `$(this)`와 차이는?  
2. 왜 “이미 열린 dt를 다시 클릭해도 아무 일도 안 하게” `display === 'none'` 조건을 썼을까?  
3. `mouseover`/`mouseout` 대신 `mouseenter`/`mouseleave`를 쓰면 어떤 점이 달라질 수 있는가?
