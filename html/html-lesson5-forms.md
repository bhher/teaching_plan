## HTML 5차시 – 폼(Form) 태그 기초 (회원가입 폼 만들기)

### 0. 학습 목표

- HTML **`<form>` 태그의 역할**을 이해한다.
- 다양한 `input` 타입(text, password, email, checkbox, radio)을 사용할 수 있다.
- `label`과 `for`를 올바르게 연결할 수 있다.
- `button`, `placeholder`, `required` 속성을 활용해 기본적인 **회원가입 폼**을 만들 수 있다.

---

### 1. 폼(Form)이란?

#### 1) 개념

- **폼(form)**: 사용자가 입력한 데이터를 **서버로 보내기 위한 영역**  
  (로그인 창, 회원가입, 검색창, 댓글 작성 등 대부분 폼으로 되어 있음)

```html
<form>
  <!-- 여기에 여러 input, label, button 등이 들어감 -->
</form>
```

- 실제 서버 연동은 나중에 하고,  
  지금은 **입력 양식을 올바르게 배치하는 것**에 초점을 맞춘다.

---

### 2. `<input>` 기본 – type별 특징

#### 2.1 공통 구조

```html
<input type="text" />
```

- `type` 에 따라 입력 방식과 모양이 달라진다.

#### 2.2 자주 쓰는 `input type` 정리 표

| 타입(type)   | 용도                         | 예시                            | 비고                          |
|-------------|------------------------------|---------------------------------|-------------------------------|
| `text`      | 일반 텍스트 입력             | 이름, 닉네임, 제목             | 가장 기본                     |
| `password`  | 비밀번호 입력                 | 비밀번호                        | 입력 문자가 ●로 가려짐       |
| `email`     | 이메일 주소 입력             | 이메일                          | 형식 검사(브라우저 기본)     |
| `checkbox`  | 체크박스 (다중 선택 가능)    | 약관 동의, 관심사 선택         | 여러 개 선택 가능            |
| `radio`     | 라디오 버튼 (단일 선택)      | 성별 선택, 결제 수단 선택      | 같은 name 그룹 중 1개만 선택 |
| `number`    | 숫자 입력                     | 나이, 수량                      | (실무에서 자주 사용)         |
| `tel`       | 전화번호 입력                 | 전화번호                        | 모바일에서 숫자 키패드 유리  |
| `date`      | 날짜 선택                     | 생년월일                        | 달력 UI 표시                  |

> **실무에서 자주 쓰는 타입**:  
> `text`, `password`, `email`, `number`, `tel`, `checkbox`, `radio`, `date`

---

### 3. `label` 태그 – 입력 설명과 연결

#### 3.1 기본 사용법

- `label` 은 입력창에 대한 **설명(라벨)** 을 표시하는 태그
- `for` 속성으로 `input` 의 `id`와 연결

```html
<label for="username">아이디</label>
<input id="username" type="text" />
```

- 라벨을 클릭해도 입력창에 포커스가 들어감 → 사용성이 좋아짐

#### 3.2 중첩 사용도 가능

```html
<label>
  아이디
  <input type="text" />
</label>
```

> 권장: `for` + `id` 방식에 익숙해지면 나중에 CSS/JS로 조작할 때도 편리함.

---

### 4. 버튼 – `<button>`

```html
<button type="submit">회원가입</button>
<button type="reset">초기화</button>
<button type="button">그냥 버튼</button>
```

- `type="submit"`: 폼을 전송할 때 사용 (기본값)
- `type="reset"`: 폼 내용을 초기화
- `type="button"`: 특별한 동작 없음 (JavaScript와 함께 사용할 때)

> 지금 단계에서는 주로 **`type="submit"`** 만 사용해도 충분.

---

### 5. `placeholder` / `required` 속성

#### 5.1 `placeholder` – 안내 문구

- 입력하기 전에 **옅은 회색 글씨**로 힌트를 보여줌

```html
<input type="text" placeholder="아이디를 입력하세요" />
```

#### 5.2 `required` – 필수 입력

- 폼 전송 시 해당 칸이 비어 있으면 **브라우저가 경고**를 띄움

```html
<input type="email" placeholder="이메일" required />
```

> HTML5의 기본 검사를 활용하는 것만으로도  
> 상당 부분의 입력 오류를 막을 수 있다.

---

### 6. 실습 – 회원가입 폼 만들기

#### 6.1 목표

- 다음 항목을 포함하는 **회원가입 폼**을 만든다.
  - 아이디 (text)
  - 비밀번호 (password)
  - 이메일 (email)
  - 성별 (radio)
  - 관심 분야 (checkbox 여러 개)
  - 제출 버튼

#### 6.2 HTML 구조 예시

> 실제 수업에서는 아래 예시를 완성본으로 보여주고,  
> 학생들에겐 빈 틀 또는 일부만 주고 스스로 채워보게 합니다.

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>회원가입 폼</title>
  </head>
  <body>
    <h1>회원가입</h1>

    <form>
      <!-- 아이디 -->
      <div>
        <label for="username">아이디</label><br />
        <input
          type="text"
          id="username"
          name="username"
          placeholder="아이디를 입력하세요"
          required
        />
      </div>

      <!-- 비밀번호 -->
      <div>
        <label for="password">비밀번호</label><br />
        <input
          type="password"
          id="password"
          name="password"
          placeholder="비밀번호를 입력하세요"
          required
        />
      </div>

      <!-- 이메일 -->
      <div>
        <label for="email">이메일</label><br />
        <input
          type="email"
          id="email"
          name="email"
          placeholder="example@example.com"
          required
        />
      </div>

      <!-- 성별 (radio) -->
      <div>
        <span>성별</span><br />
        <label>
          <input type="radio" name="gender" value="male" /> 남성
        </label>
        <label>
          <input type="radio" name="gender" value="female" /> 여성
        </label>
        <label>
          <input type="radio" name="gender" value="other" /> 기타
        </label>
      </div>

      <!-- 관심 분야 (checkbox) -->
      <div>
        <span>관심 분야</span><br />
        <label>
          <input type="checkbox" name="interest" value="frontend" /> 프론트엔드
        </label>
        <label>
          <input type="checkbox" name="interest" value="backend" /> 백엔드
        </label>
        <label>
          <input type="checkbox" name="interest" value="design" /> 디자인
        </label>
      </div>

      <!-- 제출 버튼 -->
      <div>
        <button type="submit">회원가입</button>
      </div>
    </form>
  </body>
</html>
```

> 포인트:  
> - `name` 속성은 서버로 전송될 때의 **필드 이름**  
> - 지금은 서버 연동을 하지 않지만, 나중에 중요해짐

---

### 7. 추가 설명 – `checkbox` vs `radio`

#### 7.1 `checkbox`

- 여러 개를 동시에 선택할 수 있음
- 예:

```html
<label><input type="checkbox" name="interest" value="html" /> HTML</label>
<label><input type="checkbox" name="interest" value="css" /> CSS</label>
<label><input type="checkbox" name="interest" value="js" /> JavaScript</label>
```

#### 7.2 `radio`

- 같은 `name` 그룹 안에서는 **딱 하나만 선택 가능**

```html
<label><input type="radio" name="gender" value="male" /> 남성</label>
<label><input type="radio" name="gender" value="female" /> 여성</label>
<label><input type="radio" name="gender" value="other" /> 기타</label>
```

> 자주 하는 실수:  
> - `radio`의 `name`을 서로 다르게 주면 **여러 개가 동시에 선택**되어 버림  
> → 같은 그룹은 반드시 같은 `name` 사용!

---

### 8. 정리 & 심화 방향

- `<form>`은 **입력값을 모으는 틀**
- `input`은 다양한 타입으로 사용:
  - `text`, `password`, `email`, `checkbox`, `radio` (실무에서 매우 자주 사용)
- `label`은 폼의 **사용성을 높여주는 필수 요소**
- `placeholder`, `required`로 사용자에게 **힌트와 기본 검증** 제공
- 실습: 회원가입 폼을 만들면서 실제 서비스에서 자주 보는 화면을 직접 구현해 봄

> 다음 차시 예고:  
> “다음 시간에는 지금 만든 폼과 레이아웃에 **CSS를 입혀서**  
> 좀 더 보기 좋은 회원가입 페이지로 꾸며 보겠습니다.”



