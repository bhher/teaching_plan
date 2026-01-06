# 2회차 — 문서 구성 요소와 콘텐츠 마크업 심화

## 학습 목표
- 다양한 목록 태그를 적절히 사용할 수 있다
- 테이블을 올바르게 구성하고 접근성을 고려할 수 있다
- 폼 요소를 사용하여 사용자 입력을 받을 수 있다
- 미디어 요소와 임베드 요소를 활용할 수 있다
- 마이크로데이터와 ARIA를 이해하고 기본적으로 사용할 수 있다

---

## 1. 목록 태그 (ul, ol, dl)

### 1.1 순서 없는 목록 (`<ul>`, `<li>`)
항목의 순서가 중요하지 않은 목록에 사용합니다.

```html
<ul>
  <li>항목 1</li>
  <li>항목 2</li>
  <li>항목 3</li>
</ul>
```

**사용 예시:**
- 네비게이션 메뉴
- 기능 목록
- 체크리스트

### 1.2 순서 있는 목록 (`<ol>`, `<li>`)
항목의 순서가 중요한 목록에 사용합니다.

```html
<ol>
  <li>첫 번째 단계</li>
  <li>두 번째 단계</li>
  <li>세 번째 단계</li>
</ol>
```

**속성:**
- `type`: 번호 스타일 (1, a, A, i, I)
- `start`: 시작 번호
- `reversed`: 역순 번호

```html
<ol type="a" start="3">
  <li>c 항목</li>
  <li>d 항목</li>
</ol>
```

**사용 예시:**
- 순서가 있는 단계별 가이드
- 순위 목록
- 레시피 단계

### 1.3 중첩 목록
목록 안에 목록을 넣을 수 있습니다.

```html
<ul>
  <li>메뉴 1
    <ul>
      <li>하위 메뉴 1-1</li>
      <li>하위 메뉴 1-2</li>
    </ul>
  </li>
  <li>메뉴 2</li>
</ul>
```

### 1.4 정의 목록 (`<dl>`, `<dt>`, `<dd>`)
용어와 정의를 표현하는 목록입니다.

```html
<dl>
  <dt>HTML</dt>
  <dd>HyperText Markup Language의 약자로, 웹 페이지의 구조를 만드는 마크업 언어입니다.</dd>
  
  <dt>CSS</dt>
  <dd>Cascading Style Sheets의 약자로, 웹 페이지의 스타일을 정의하는 언어입니다.</dd>
</dl>
```

**구조:**
- `<dl>`: 정의 목록 컨테이너
- `<dt>`: 정의할 용어 (Definition Term)
- `<dd>`: 용어에 대한 설명 (Definition Description)

**사용 예시:**
- 용어 사전
- FAQ (질문과 답변)
- 메타데이터 표현

---

## 2. 테이블 (table)

### 2.1 기본 테이블 구조

```html
<table>
  <tr>
    <th>제목 1</th>
    <th>제목 2</th>
    <th>제목 3</th>
  </tr>
  <tr>
    <td>데이터 1</td>
    <td>데이터 2</td>
    <td>데이터 3</td>
  </tr>
</table>
```

**태그 설명:**
- `<table>`: 테이블 컨테이너
- `<tr>`: 테이블 행 (Table Row)
- `<th>`: 테이블 헤더 셀 (Table Header) - 굵게 표시, 중앙 정렬
- `<td>`: 테이블 데이터 셀 (Table Data)

### 2.2 테이블 섹션

```html
<table>
  <thead>
    <tr>
      <th>이름</th>
      <th>나이</th>
      <th>직업</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>홍길동</td>
      <td>25</td>
      <td>개발자</td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="3">총 1명</td>
    </tr>
  </tfoot>
</table>
```

**섹션 태그:**
- `<thead>`: 테이블 헤더 영역
- `<tbody>`: 테이블 본문 영역
- `<tfoot>`: 테이블 푸터 영역

### 2.3 셀 병합

#### `colspan`: 열 병합 (가로로 합치기)
```html
<tr>
  <td colspan="2">2개 열을 합침</td>
  <td>일반 셀</td>
</tr>
```

#### `rowspan`: 행 병합 (세로로 합치기)
```html
<tr>
  <td rowspan="2">2개 행을 합침</td>
  <td>일반 셀</td>
</tr>
<tr>
  <td>일반 셀</td>
</tr>
```

### 2.4 테이블 캡션 (`<caption>`)

```html
<table>
  <caption>2024년 월별 매출 현황</caption>
  <thead>
    <!-- 테이블 내용 -->
  </thead>
</table>
```

**주의사항:**
- `<caption>`은 `<table>` 바로 다음에 위치해야 함
- 테이블의 제목이나 설명을 제공

### 2.5 테이블 접근성

#### `scope` 속성
헤더 셀이 어떤 셀에 대한 헤더인지 명시합니다.

```html
<table>
  <tr>
    <th scope="col">이름</th>
    <th scope="col">나이</th>
    <th scope="row">홍길동</th>
    <td>25</td>
  </tr>
</table>
```

- `scope="col"`: 열 헤더
- `scope="row"`: 행 헤더

#### `headers` 속성
복잡한 테이블에서 셀이 어떤 헤더에 속하는지 명시합니다.

```html
<th id="name">이름</th>
<td headers="name">홍길동</td>
```

#### 접근성 체크리스트
- [ ] `<th>` 태그 사용 (헤더 셀)
- [ ] `<caption>` 사용 (테이블 제목)
- [ ] `scope` 속성 사용 (간단한 테이블)
- [ ] `headers` 속성 사용 (복잡한 테이블)
- [ ] 테이블을 레이아웃 용도로 사용하지 않기

---

## 3. 폼 요소 (Form Elements)

### 3.1 폼 기본 구조

```html
<form action="/submit" method="POST">
  <!-- 폼 요소들 -->
  <button type="submit">제출</button>
</form>
```

**속성:**
- `action`: 폼 제출 시 데이터를 보낼 URL
- `method`: HTTP 메서드 (GET, POST)
- `enctype`: 인코딩 타입 (파일 업로드 시 `multipart/form-data`)

### 3.2 텍스트 입력 (`<input>`)

#### 텍스트 입력
```html
<input type="text" name="username" placeholder="사용자 이름">
```

#### 비밀번호
```html
<input type="password" name="password" placeholder="비밀번호">
```

#### 이메일
```html
<input type="email" name="email" placeholder="이메일 주소">
```

#### 숫자
```html
<input type="number" name="age" min="1" max="100" step="1">
```

#### 날짜/시간
```html
<input type="date" name="birthday">
<input type="time" name="appointment">
<input type="datetime-local" name="event">
```

#### URL
```html
<input type="url" name="website" placeholder="https://example.com">
```

#### 전화번호
```html
<input type="tel" name="phone" placeholder="010-1234-5678">
```

#### 검색
```html
<input type="search" name="query" placeholder="검색어 입력">
```

### 3.3 체크박스와 라디오 버튼

#### 체크박스 (다중 선택)
```html
<input type="checkbox" name="hobby" value="reading" id="hobby1">
<label for="hobby1">독서</label>

<input type="checkbox" name="hobby" value="sports" id="hobby2">
<label for="hobby2">운동</label>
```

#### 라디오 버튼 (단일 선택)
```html
<input type="radio" name="gender" value="male" id="male">
<label for="male">남성</label>

<input type="radio" name="gender" value="female" id="female">
<label for="female">여성</label>
```

**주의사항:**
- 같은 `name` 값을 가진 라디오 버튼은 그룹으로 묶임
- 체크박스는 여러 개 선택 가능, 라디오는 하나만 선택 가능

### 3.4 선택 박스 (`<select>`, `<option>`)

```html
<select name="country">
  <option value="">국가를 선택하세요</option>
  <option value="kr">한국</option>
  <option value="us">미국</option>
  <option value="jp">일본</option>
</select>
```

#### 다중 선택
```html
<select name="languages" multiple>
  <option value="html">HTML</option>
  <option value="css">CSS</option>
  <option value="js">JavaScript</option>
</select>
```

#### 옵션 그룹
```html
<select name="food">
  <optgroup label="한식">
    <option value="kimchi">김치</option>
    <option value="bulgogi">불고기</option>
  </optgroup>
  <optgroup label="양식">
    <option value="pasta">파스타</option>
    <option value="pizza">피자</option>
  </optgroup>
</select>
```

### 3.5 텍스트 영역 (`<textarea>`)

```html
<textarea name="message" rows="5" cols="50" placeholder="메시지를 입력하세요"></textarea>
```

**속성:**
- `rows`: 행 수
- `cols`: 열 수 (CSS로 크기 조절 권장)
- `placeholder`: 힌트 텍스트

### 3.6 레이블 (`<label>`)

레이블은 폼 요소와 연결하여 접근성을 향상시킵니다.

#### 방법 1: `for` 속성 사용
```html
<label for="username">사용자 이름</label>
<input type="text" id="username" name="username">
```

#### 방법 2: 레이블로 감싸기
```html
<label>
  사용자 이름
  <input type="text" name="username">
</label>
```

**장점:**
- 레이블 클릭 시 입력 필드에 포커스 이동
- 스크린 리더가 레이블을 읽어줌
- 접근성 향상

### 3.7 버튼 (`<button>`)

```html
<!-- 제출 버튼 -->
<button type="submit">제출</button>

<!-- 리셋 버튼 -->
<button type="reset">초기화</button>

<!-- 일반 버튼 -->
<button type="button">클릭</button>
```

**주의사항:**
- `<form>` 안에서 `type`을 생략하면 기본값은 `submit`
- `<input type="button">` 대신 `<button>` 사용 권장

### 3.8 폼 유효성 검사 (Validation)

#### 필수 입력 (`required`)
```html
<input type="text" name="username" required>
```

#### 패턴 검사 (`pattern`)
```html
<input type="text" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" 
       placeholder="010-1234-5678">
```

#### 최소/최대 길이
```html
<input type="text" name="username" minlength="3" maxlength="20">
```

#### 최소/최대 값 (숫자)
```html
<input type="number" name="age" min="1" max="120">
```

#### 커스텀 유효성 메시지
```html
<input type="email" name="email" required 
       oninvalid="this.setCustomValidity('이메일을 입력해주세요')"
       oninput="this.setCustomValidity('')">
```

### 3.9 폼 접근성 체크리스트
- [ ] 모든 입력 필드에 `<label>` 연결
- [ ] `required` 필드에 명확한 표시
- [ ] 오류 메시지가 명확하고 이해하기 쉬운가?
- [ ] 키보드만으로 모든 기능 사용 가능한가?
- [ ] 포커스 순서가 논리적인가?

---

## 4. 미디어 요소

### 4.1 이미지 (이미 학습함)
```html
<img src="image.jpg" alt="이미지 설명">
```

### 4.2 오디오 (`<audio>`)

```html
<audio controls>
  <source src="audio.mp3" type="audio/mpeg">
  <source src="audio.ogg" type="audio/ogg">
  브라우저가 오디오 태그를 지원하지 않습니다.
</audio>
```

**속성:**
- `controls`: 재생 컨트롤 표시
- `autoplay`: 자동 재생 (주의: 사용자 경험에 좋지 않음)
- `loop`: 반복 재생
- `muted`: 음소거
- `preload`: 미리 로드할지 여부 (none, metadata, auto)

**주의사항:**
- 여러 `<source>` 태그로 브라우저 호환성 확보
- 자동 재생은 사용자 경험을 해칠 수 있음

### 4.3 비디오 (`<video>`)

```html
<video controls width="640" height="360">
  <source src="video.mp4" type="video/mp4">
  <source src="video.webm" type="video/webm">
  브라우저가 비디오 태그를 지원하지 않습니다.
</video>
```

**속성:**
- `controls`: 재생 컨트롤 표시
- `width`, `height`: 비디오 크기
- `poster`: 썸네일 이미지
- `autoplay`, `loop`, `muted`, `preload`: 오디오와 동일

```html
<video controls poster="thumbnail.jpg">
  <source src="video.mp4" type="video/mp4">
</video>
```

### 4.4 미디어 접근성
- [ ] 비디오에 자막 제공 (`<track>` 태그)
- [ ] 오디오에 대체 텍스트나 전사본 제공
- [ ] 자동 재생하지 않기
- [ ] 컨트롤 제공

---

## 5. 임베드 요소 (iframe)

### 5.1 iframe 기본 사용법

```html
<iframe src="https://www.example.com" width="800" height="600"></iframe>
```

**속성:**
- `src`: 임베드할 페이지 URL
- `width`, `height`: 크기
- `title`: 접근성을 위한 제목 (필수)
- `sandbox`: 보안 제한 설정

### 5.2 YouTube 비디오 임베드

```html
<iframe 
  width="560" 
  height="315" 
  src="https://www.youtube.com/embed/VIDEO_ID" 
  title="YouTube video player" 
  frameborder="0" 
  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
  allowfullscreen>
</iframe>
```

### 5.3 Google Maps 임베드

```html
<iframe 
  src="https://www.google.com/maps/embed?pb=..." 
  width="600" 
  height="450" 
  style="border:0;" 
  allowfullscreen="" 
  loading="lazy" 
  title="지도 위치">
</iframe>
```

### 5.4 iframe 접근성
- [ ] `title` 속성 필수
- [ ] 대체 콘텐츠 제공
- [ ] 키보드 접근 가능한지 확인

---

## 6. 마이크로데이터 (Microdata)

마이크로데이터는 HTML 요소에 구조화된 데이터를 추가하는 방법입니다.

### 6.1 기본 개념

```html
<div itemscope itemtype="https://schema.org/Person">
  <h1 itemprop="name">홍길동</h1>
  <p itemprop="jobTitle">웹 개발자</p>
  <p itemprop="email">hong@example.com</p>
</div>
```

**속성:**
- `itemscope`: 마이크로데이터 항목의 시작
- `itemtype`: 데이터 타입 (Schema.org URL)
- `itemprop`: 속성 이름

### 6.2 Schema.org 타입

#### Person (사람)
```html
<div itemscope itemtype="https://schema.org/Person">
  <span itemprop="name">홍길동</span>
  <span itemprop="jobTitle">개발자</span>
  <a itemprop="url" href="https://example.com">웹사이트</a>
</div>
```

#### Organization (조직)
```html
<div itemscope itemtype="https://schema.org/Organization">
  <h1 itemprop="name">회사명</h1>
  <p itemprop="address">서울시 강남구</p>
</div>
```

#### Article (기사)
```html
<article itemscope itemtype="https://schema.org/Article">
  <h1 itemprop="headline">기사 제목</h1>
  <p itemprop="author" itemscope itemtype="https://schema.org/Person">
    <span itemprop="name">작성자명</span>
  </p>
  <time itemprop="datePublished" datetime="2024-12-10">2024년 12월 10일</time>
</article>
```

### 6.3 마이크로데이터의 장점
- 검색 엔진이 콘텐츠를 더 잘 이해
- 리치 스니펫(검색 결과에 추가 정보 표시) 가능
- 구조화된 데이터 제공

---

## 7. ARIA (Accessible Rich Internet Applications)

ARIA는 접근성을 향상시키기 위한 속성들입니다.

### 7.1 기본 개념

ARIA는 스크린 리더와 같은 보조 기술이 웹 콘텐츠를 더 잘 이해할 수 있도록 돕습니다.

### 7.2 주요 ARIA 속성

#### `role`
요소의 역할을 명시합니다.

```html
<div role="button" tabindex="0">클릭 가능한 버튼</div>
<div role="alert">중요한 알림</div>
<nav role="navigation">네비게이션</nav>
```

#### `aria-label`
요소에 접근 가능한 이름을 제공합니다.

```html
<button aria-label="닫기">×</button>
<img src="icon.png" aria-label="홈으로 이동">
```

#### `aria-labelledby`
다른 요소의 ID를 참조하여 레이블을 지정합니다.

```html
<h2 id="section-title">섹션 제목</h2>
<section aria-labelledby="section-title">
  <!-- 섹션 내용 -->
</section>
```

#### `aria-describedby`
요소에 대한 추가 설명을 제공합니다.

```html
<input type="password" aria-describedby="password-help">
<p id="password-help">비밀번호는 8자 이상이어야 합니다.</p>
```

#### `aria-hidden`
요소를 스크린 리더에서 숨깁니다.

```html
<span aria-hidden="true">※</span>
<span class="sr-only">필수 항목</span>
```

#### `aria-required`
필수 입력 필드를 표시합니다.

```html
<input type="text" aria-required="true">
```

#### `aria-invalid`
유효하지 않은 입력을 표시합니다.

```html
<input type="email" aria-invalid="true" aria-describedby="email-error">
<span id="email-error">올바른 이메일 형식이 아닙니다.</span>
```

### 7.3 ARIA 라이브 영역

동적으로 변경되는 콘텐츠를 스크린 리더에 알립니다.

```html
<div aria-live="polite" aria-atomic="true">
  <!-- 동적으로 변경되는 내용 -->
</div>
```

**`aria-live` 값:**
- `off`: 알림하지 않음
- `polite`: 현재 작업이 끝난 후 알림
- `assertive`: 즉시 알림

### 7.4 ARIA 사용 가이드라인
1. **시맨틱 HTML을 먼저 사용**: ARIA는 보완용
2. **불필요한 ARIA 사용 지양**: 시맨틱 태그로 충분하면 ARIA 불필요
3. **역할 중복 피하기**: `<button>`에 `role="button"` 불필요
4. **테스트**: 실제 스크린 리더로 테스트

---

## 8. 실습 체크리스트

### 목록
- [ ] ul, ol, dl을 적절히 사용
- [ ] 중첩 목록 구조 이해

### 테이블
- [ ] thead, tbody, tfoot 사용
- [ ] th 태그와 scope 속성 사용
- [ ] caption 사용
- [ ] 접근성 고려

### 폼
- [ ] 다양한 input 타입 사용
- [ ] label과 input 연결
- [ ] 유효성 검사 속성 사용
- [ ] 접근성 고려

### 미디어
- [ ] audio, video 태그 사용
- [ ] iframe 사용
- [ ] 접근성 고려 (title, 대체 콘텐츠)

### 마이크로데이터/ARIA
- [ ] 기본적인 마이크로데이터 구조 이해
- [ ] 기본적인 ARIA 속성 사용

---

## 다음 단계
- CSS 기초 스타일링
- 반응형 웹 디자인
- JavaScript 기초
- 웹 접근성 심화


