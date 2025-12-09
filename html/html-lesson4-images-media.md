## HTML 4차시 – 이미지 & 미디어 태그

### 0. 학습 목표

- 이미지를 표시하는 **`<img>` 태그**를 사용할 수 있다.
- `src`, `alt` 속성의 역할을 이해하고 올바르게 작성할 수 있다.
- **경로(상대/절대)** 개념을 이해한다.
- `video`, `audio` 태그를 이용해 간단한 미디어를 재생할 수 있다는 것을 안다.
- 이미지를 포함한 **프로필 카드 페이지**를 직접 만들어 본다.

---

### 1. 이미지 태그 – `<img>`

#### 1) 기본 형태

```html
<img src="이미지_주소" alt="이미지 설명" />
```

- `<img>` : **이미지**를 화면에 표시하는 태그
- `src` (source) : 이미지 파일의 **위치(주소)**
- `alt` (alternative text) : 이미지가 로딩되지 않거나, 시각장애인용 리더기가 읽을 때 사용하는 **대체 텍스트**

예시:

```html
<img src="profile.jpg" alt="홍길동의 프로필 사진" />
```

#### 2) `alt` 속성이 중요한 이유

- 이미지가 깨졌을 때, 대체 텍스트가 대신 표시된다.
- 시각장애인용 스크린 리더(screen reader)가 `alt` 내용을 읽어 준다.
- 검색엔진(구글 등)도 `alt`를 참고해 이미지를 이해한다.

> 실무 팁:  
> 내용이 중요한 이미지일수록 **구체적인 alt**를 작성하는 습관을 들이자.  
> 예: `alt="회사 로고"` / `alt="React 로고"` / `alt="홍길동이 웃고 있는 모습"`

---

### 2. 경로(path) 개념 – 상대 경로 / 절대 경로

#### 2.1 상대 경로 (relative path)

- **현재 파일을 기준으로** 한 위치
- 같은 폴더 안이라면 파일 이름만 적으면 된다.

예시 상황:

```txt
project/
  index.html
  images/
    profile.jpg
```

`index.html`에서 `profile.jpg`를 불러오려면:

```html
<img src="images/profile.jpg" alt="프로필 사진" />
```

- `images/` : index.html이 있는 폴더 기준으로, 그 안의 `images` 폴더
- `../` : 한 단계 상위 폴더를 의미

예:

```txt
project/
  pages/
    intro.html
  images/
    profile.jpg
```

`intro.html`에서 이미지를 불러오려면:

```html
<img src="../images/profile.jpg" alt="프로필 사진" />
```

#### 2.2 절대 경로 (absolute path)

- **웹 사이트의 전체 주소를 포함**하는 방식

```html
<img src="https://example.com/images/profile.jpg" alt="프로필 사진" />
```

또는 로컬 컴퓨터의 전체 경로(C:\... )를 사용할 수도 있지만,  
실제 웹 서비스에서는 **상대 경로 또는 도메인이 포함된 절대 URL**을 주로 사용한다.

> 수업 포인트:  
> - “상대 경로는 지금 파일 위치를 기준으로, 절대 경로는 어디서 보든 똑같은 주소”  
> - 상대 경로 실습을 위해 작은 폴더 구조를 손으로 그려보게 하면 이해가 빨라진다.

---

### 3. 비디오 & 오디오 태그 – `<video>`, `<audio>` (기초 소개)

#### 3.1 `<video>` 태그

```html
<video src="movie.mp4" controls width="400"></video>
```

- `controls` : 재생/일시정지/볼륨 조절 등의 기본 컨트롤 표시
- `width`/`height` : 표시 크기
- `autoplay`, `loop`, `muted` 등의 속성도 존재 (추후 심화)

**여러 소스 형식 제공 예시**:

```html
<video controls width="400">
  <source src="movie.mp4" type="video/mp4" />
  <source src="movie.webm" type="video/webm" />
  브라우저가 video 태그를 지원하지 않습니다.
</video>
```

#### 3.2 `<audio>` 태그

```html
<audio src="music.mp3" controls></audio>
```

- 역시 `controls` 로 재생 UI 표시
- `autoplay`, `loop` 등 속성 사용 가능

> 입문 단계에서는 “video와 audio 태그로 **브라우저에서 직접 재생할 수 있다**” 정도만 이해해도 충분합니다.

---

### 4. 실습 – 프로필 카드 만들기

#### 4.1 목표

- 프로필 사진(또는 임시 이미지 URL)을 포함한  
  **프로필 카드 HTML 페이지**를 작성한다.

#### 4.2 기본 요구사항

- 파일 이름 예: `profile.html`
- 포함할 내용:
  - 프로필 이미지: `<img>`
  - 이름: `<h1>` 또는 `<h2>`
  - 간단한 소개: `<p>`
  - 취미, 관심사: 목록(`<ul><li>`)
  - (선택) 좋아하는 음악/영상 링크를 `video` 또는 `audio`로 삽입(실제 파일이 없으면 URL이나 주석으로 대체 가능)

#### 4.3 예시 코드 (구조 예시)

```html
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <title>홍길동 프로필</title>
  </head>
  <body>
    <h1>홍길동 프로필 카드</h1>

    <!-- 프로필 이미지 -->
    <img src="images/profile.jpg" alt="홍길동의 프로필 사진" width="200" />

    <!-- 이름 & 소개 -->
    <h2>홍길동</h2>
    <p>안녕하세요. 저는 코딩을 처음 배우고 있는 홍길동입니다.</p>

    <!-- 취미 목록 -->
    <h3>취미</h3>
    <ul>
      <li>독서</li>
      <li>산책</li>
      <li>음악 감상</li>
    </ul>

    <!-- (선택) 좋아하는 음악/영상 -->
    <h3>좋아하는 음악 (예시)</h3>
    <!-- 실제 mp3 파일이 있다면 src 경로를 수정 -->
    <audio src="music.mp3" controls></audio>
  </body>
</html>
```

> 실제 수업에서는 위 예시를 **완성본 예시**로 보여주고,  
> 학생들에게는 이미지 파일 이름이나 텍스트를 각자 스타일대로 작성하게 합니다.

---

### 5. 실습 진행 팁 (강사용)

1. **이미지 파일 준비**
   - 수업 전에 간단한 이미지 파일(`profile.jpg`)을 제공하거나,  
     인터넷에 있는 임시 이미지 URL(예: `https://via.placeholder.com/200`)을 사용하도록 안내

2. **폴더 구조 연습**
   - 직접 화이트보드에 다음과 같은 구조를 그려주고:

   ```txt
   project/
     profile.html
     images/
       profile.jpg
   ```

   - `profile.html`에서 `profile.jpg`로 가는 상대 경로를 함께 맞춰본다.

3. **경로 오류 디버깅**
   - 이미지가 안 나올 경우:
     - 파일 이름/확장자 확인 (`.jpg`, `.png` 등)
     - 대소문자 확인
     - 경로(`images/profile.jpg`, `../images/profile.jpg` 등) 확인

---

### 6. 마무리 정리

- `<img>` 태그로 이미지를 표시할 수 있다.
  - `src` : 이미지 파일의 위치
  - `alt` : 이미지 설명 (접근성 + 에러 대비)
- 경로:
  - **상대 경로**: 지금 HTML 파일 위치를 기준으로
  - **절대 경로**: 전체 URL을 포함한 주소
- `video`, `audio` 태그를 이용하면 브라우저에서 직접 미디어를 재생할 수 있다.
- 실습: 프로필 카드 페이지를 통해  
  텍스트 + 이미지 + (선택 미디어)를 함께 써보며 HTML 태그 사용에 익숙해진다.

> 다음 차시 예고:  
> “다음 시간에는 지금 만든 프로필 카드를 더 예쁘게 꾸미기 위해,  
> **CSS를 살짝 맛보기**로 입혀 보면서 스타일을 바꿔 보겠습니다.”



