# HTML 폼(Form) 태그 정리

사용자가 입력한 데이터를 서버로 보내기 위한 폼 관련 태그를 한눈에 정리한 문서입니다.

---

## 태그 한눈에 보기

| 태그 | 설명 |
|------|------|
| `<form>` | 입력한 데이터를 서버로 전송 |
| `<fieldset>` | 관련 입력 항목을 하나의 그룹으로 묶음 |
| `<legend>` | 그룹의 제목 |
| `<label>` | 입력창의 설명 |
| `<input type="text">` | 문자 입력 |
| `<input type="password">` | 비밀번호 입력 |
| `<input type="email">` | 이메일 입력 |
| `<input type="tel">` | 전화번호 입력 |
| `<input type="date">` | 날짜 선택 |
| `<input type="radio">` | 하나만 선택 |
| `<input type="checkbox">` | 여러 개 선택 |
| `<select>` | 목록에서 선택 |
| `<datalist>` | 추천 목록 제공 (직접 입력도 가능) |
| `<input type="file">` | 파일 선택 |
| `<textarea>` | 여러 줄 입력 |
| `<input type="submit">` | 전송 버튼 |
| `<input type="reset">` | 입력 내용 초기화 |

---

## 역할별 분류

### 1. 폼 구조

| 태그 | 역할 |
|------|------|
| `<form>` | 폼 전체 영역. 입력 데이터를 서버로 전송 |
| `<fieldset>` | 관련 입력 항목을 하나의 그룹으로 묶음 |
| `<legend>` | `<fieldset>` 그룹의 제목 |
| `<label>` | 입력창의 설명 (클릭 시 해당 입력창 포커스) |

### 2. 한 줄 입력 (`<input>`)

| 태그 | 역할 |
|------|------|
| `type="text"` | 일반 문자 입력 |
| `type="password"` | 비밀번호 입력 (가려서 표시) |
| `type="email"` | 이메일 입력 |
| `type="tel"` | 전화번호 입력 |
| `type="date"` | 날짜 선택 |
| `type="file"` | 파일 선택 |

### 3. 선택형 입력

| 태그 | 역할 |
|------|------|
| `type="radio"` | 여러 항목 중 **하나만** 선택 |
| `type="checkbox"` | 여러 항목 중 **여러 개** 선택 |
| `<select>` | 드롭다운 목록에서 선택 |
| `<datalist>` | 추천 목록 제공 (직접 입력도 가능) |

### 4. 여러 줄 입력

| 태그 | 역할 |
|------|------|
| `<textarea>` | 긴 글, 여러 줄 입력 |

### 5. 버튼

| 태그 | 역할 |
|------|------|
| `type="submit"` | 폼 데이터 전송 |
| `type="reset"` | 입력 내용 초기화 |

---

## 간단 사용 예시

```html
<form action="/submit" method="post">
  <fieldset>
    <legend>회원 정보</legend>

    <label for="name">이름</label>
    <input type="text" id="name" name="name">

    <label for="email">이메일</label>
    <input type="email" id="email" name="email">

    <label for="pw">비밀번호</label>
    <input type="password" id="pw" name="password">

    <label for="tel">전화번호</label>
    <input type="tel" id="tel" name="tel">

    <label for="birth">생년월일</label>
    <input type="date" id="birth" name="birth">

    <p>성별</p>
    <label><input type="radio" name="gender" value="male"> 남</label>
    <label><input type="radio" name="gender" value="female"> 여</label>

    <p>관심사</p>
    <label><input type="checkbox" name="hobby" value="html"> HTML</label>
    <label><input type="checkbox" name="hobby" value="css"> CSS</label>

    <label for="city">지역</label>
    <select id="city" name="city">
      <option value="seoul">서울</option>
      <option value="busan">부산</option>
    </select>

    <label for="fruit">좋아하는 과일</label>
    <input type="text" id="fruit" list="fruit-list">
    <datalist id="fruit-list">
      <option value="사과">
      <option value="바나나">
      <option value="포도">
    </datalist>

    <label for="file">첨부파일</label>
    <input type="file" id="file" name="file">

    <label for="memo">메모</label>
    <textarea id="memo" name="memo" rows="4"></textarea>

    <input type="submit" value="전송">
    <input type="reset" value="초기화">
  </fieldset>
</form>
```

---

## 기억하면 좋은 포인트

1. **`<label for="아이디">`** 와 **`<input id="아이디">`** 를 같은 값으로 연결한다.
2. **radio** 는 `name` 이 같아야 **하나만** 선택된다.
3. **checkbox** 는 `name` 이 같아도 **여러 개** 선택 가능하다.
4. **`<datalist>`** 는 `<input list="아이디">` 와 연결해서 쓴다.
5. **`submit`** 은 전송, **`reset`** 은 초기화이다.

---

## 관련 교안

- [html-lesson5-forms.md](./html-lesson5-forms.md) — 폼 태그 기초 (회원가입 폼)
- [html-form-examples.html](./html-form-examples.html) — 폼 예제 HTML
