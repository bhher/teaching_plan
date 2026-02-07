# Green 복지재단 웹사이트 프로젝트 설명서

## 🌐 사이트 방문

**실제 사이트**: [https://bhher.github.io/webdesign/green/index.html](https://bhher.github.io/webdesign/green/index.html)

## 📋 프로젝트 개요

Green 복지재단 홈페이지는 웹디자인기능사 실기 시험 예상문제로 제작된 웹사이트입니다. 사회복지법인 Green복지재단의 공식 홈페이지로, 재단 소개, 후원 안내, 자료실, 스토리 등의 정보를 제공합니다.

## 🏗️ 프로젝트 구조

```
제출용/
├── index.html          # 메인 HTML 파일
├── css/
│   └── style.css      # 스타일시트
├── js/
│   ├── jquery-1.12.3.js  # jQuery 라이브러리
│   └── script.js      # JavaScript 파일
└── images/            # 이미지 파일들
    ├── logo.png       # 메인 로고
    ├── logo_gray.png  # 푸터용 회색 로고
    ├── slide1.jpg     # 슬라이드 이미지 1
    ├── slide2.jpg     # 슬라이드 이미지 2
    ├── slide3.JPG     # 슬라이드 이미지 3
    ├── g1.png, g2.png, g3.png  # 갤러리 이미지
    ├── b1.png, b2.png, b3.png  # 배너 이미지
    └── f1.png, f2.png, f3.png  # 패밀리 사이트 이미지
```

## 📐 페이지 레이아웃

### 1. Header (헤더)
- **로고 영역**: Green 복지재단 로고
- **GNB (Global Navigation Bar)**: 메인 네비게이션 메뉴
  - **재단소개** (드롭다운)
    - 설립취지
    - 연혁
    - 찾아오시는 길
  - **후원하기** (드롭다운)
    - 국내후원
    - 국외후원
    - 맞춤후원
  - **자료실** (드롭다운)
    - 서식자료실
    - 사진자료실
    - 후원양식
  - **스토리** (드롭다운)
    - 웹진
    - 보고서
    - 나의 후원

### 2. Image Slide (이미지 슬라이드)
3개의 슬라이드 이미지와 텍스트:
1. **슬라이드 1**: "밀알복지재단이 함께하는 장애인식개선 프로젝트 '우리동네 피터팬'"
2. **슬라이드 2**: "한 아이의 든든한 후원자 되어주기 '너는 우리의 사랑'"
3. **슬라이드 3**: "2019 새해다짐 소액기부 '천천히 천 원부터'"

### 3. Contents (콘텐츠 영역)

#### 3.1 Notice (공지사항)
- 제목: "공지사항"
- 테이블 형태로 공지사항 목록 표시
- 주요 공지사항:
  - 발달장애인 사회적 자립지원사업 '아트블룸(Artbloom)' (2019.05.21)
  - 미얀마 성장발달보고서 및 사업장소식 발송 안내 (2019.04.20)
  - 네팔 성장발달보고서 및 사업장소식 발송 안내 (2019.03.23)
  - KB국민카드, 밀알복지재단에 장애청소년 후원금 1억 5천... (2019.02.25)

#### 3.2 Gallery (갤러리)
- 제목: "갤러리"
- 3개의 이미지 썸네일 (g1.png, g2.png, g3.png)
- 각 이미지는 링크로 연결

#### 3.3 Banner (배너)
- 3개의 배너 이미지 (b1.png, b2.png, b3.png)
- 수직 배치

### 4. Footer (푸터)
- **로고**: 회색 로고 이미지 (logo_gray.png)
- **저작권 정보**:
  - 사회복지법인 Green복지재단 대표자 홍정길
  - 사업자등록번호: 213-82-04651
  - 주소: (우)06349 서울특별시 강남구 밤고개로 1길 34 (수서동)
  - 대표전화: 02-3411-4664
  - 후원자전용번호: 1600-0966 (평일 AM 9:00 ~ PM 6:00)
  - COPYRIGHTⓒ 2019 GREEN ALL RIGHTS RESERVED.
- **패밀리 사이트**: 3개의 관련 사이트 링크
  - kizM
  - 사랑의 열매
  - 해피빈

### 5. Popup (팝업)
- **제목**: "강남세움발달장애인평생교육센터 2020년 신규교육생 모집"
- **내용**: 밀알복지재단 강남세움발달장애인평생교육센터에서 성인발달장애인 대상의 2020년 신규교육생을 모집하는 안내
- **닫기 버튼**: 팝업을 닫을 수 있는 버튼

## 🛠️ 기술 스택

- **HTML5**: 웹 페이지 구조
- **CSS3**: 스타일링
- **JavaScript**: 인터랙티브 기능
- **jQuery 1.12.3**: DOM 조작 및 이벤트 처리

## 📝 주요 기능

1. **드롭다운 메뉴**: GNB의 각 메뉴 항목에 서브메뉴가 드롭다운으로 표시
2. **이미지 슬라이드**: 메인 비주얼 영역의 이미지 슬라이드 기능
3. **팝업 모달**: 페이지 로드 시 공지사항 팝업 표시
4. **반응형 레이아웃**: 다양한 화면 크기에 대응하는 레이아웃

## 🎨 디자인 특징

- 깔끔하고 전문적인 복지재단 웹사이트 디자인
- 사용자 친화적인 네비게이션 구조
- 명확한 정보 계층 구조
- 접근성을 고려한 레이아웃

## 📌 참고사항

- 웹디자인기능사 실기 시험 예상문제로 제작됨
- 모든 링크는 현재 "#"으로 설정되어 있음 (실제 구현 시 실제 URL로 변경 필요)
- 이미지 파일들은 모두 `images` 폴더에 위치
- jQuery 라이브러리는 로컬 파일로 포함되어 있음

## 🔧 개발 환경

- HTML5
- CSS3
- JavaScript (ES5)
- jQuery 1.12.3

---

## 📘 CSS 상세 설명

### 1. 초기화 및 기본 설정

```css
@charset "UTF-8";
*{margin:0; padding:0;}
ul,li{list-style:none;}
img{display:block;}
a{text-decoration:none; color:#333;}
.cf:after{content:''; display:block; clear:both;}
```

- **`@charset "UTF-8"`**: CSS 파일의 문자 인코딩을 UTF-8로 설정
- **`*{margin:0; padding:0;}`**: 모든 요소의 기본 마진과 패딩을 0으로 초기화 (CSS Reset)
- **`ul,li{list-style:none;}`**: 리스트의 기본 스타일(불릿) 제거
- **`img{display:block;}`**: 이미지를 블록 요소로 설정하여 하단 여백 제거
- **`a{text-decoration:none; color:#333;}`**: 링크의 밑줄 제거 및 색상 설정
- **`.cf:after`**: Clearfix 클래스 - float 해제를 위한 가상 요소 사용

### 2. 전체 레이아웃

```css
body{font-family:"나눔고딕","Nanum Gothic", sans-serif; color:#333;}
#wrap{width:1200px; margin:0 auto;}
```

- **`body`**: 기본 폰트를 나눔고딕으로 설정, 텍스트 색상은 #333 (진한 회색)
- **`#wrap`**: 전체 컨테이너 너비 1200px, 중앙 정렬 (`margin:0 auto`)

### 3. Header (헤더) 스타일

```css
.header{width:100%; height:100px; position:relative;}
.header .logo{position:absolute; left:0; top:0; width:250px; height:100px; 
              background:url(../images/logo.png) no-repeat center;}
.header .gnb{position:absolute; left:450px; top:50px; width:700px; height:auto;}
```

- **`.header`**: 헤더 영역 높이 100px, 상대 위치 지정 (자식 요소의 absolute 기준점)
- **`.logo`**: 절대 위치로 좌측 상단에 배치, 배경 이미지로 로고 표시
- **`.gnb`**: 네비게이션 메뉴를 절대 위치로 배치 (left:450px, top:50px)

#### GNB 메뉴 스타일

```css
.header .gnb>li{float:left; width:175px; font-weight:bold; font-size:24px; 
                text-align:center; line-height:50px;}
.header .gnb>li:hover{background-color:#3cb149;}
.header .gnb>li:hover>a{color:#fff;}
```

- **메인 메뉴**: 각 항목 너비 175px, 가로 배치(float), 호버 시 배경색 변경 (#3cb149 - 초록색)
- **호버 효과**: 메뉴 항목에 마우스 오버 시 배경색이 초록색으로 변경되고 텍스트가 흰색으로 변경

#### 드롭다운 서브메뉴

```css
.header .gnb>li>ul{position:absolute; top:100%; z-index:2; width:175px; 
                   display:none; font-weight:normal;}
.header .gnb>li>ul>li:hover{background-color:#1d8844;}
.header .gnb>li>ul>li:hover>a{color:#fff;}
.header .fulldown{position:absolute; left:0; top:100%; width:100%; height:175px; 
                  background-color:#3cb149; z-index:1; display:none;}
```

- **서브메뉴**: 기본적으로 숨김(`display:none`), 절대 위치로 메인 메뉴 하단에 배치
- **`.fulldown`**: 드롭다운 메뉴 전체를 덮는 배경 영역 (초록색 배경)
- **z-index**: 서브메뉴(z-index:2)가 배경(z-index:1) 위에 표시되도록 설정
- **호버 색상**: 서브메뉴 호버 시 더 진한 초록색(#1d8844)으로 변경

### 4. Image Slide (이미지 슬라이드) 스타일

```css
.img_slide{width:100%; height:300px; background-color:#434748; overflow:hidden;}
.img_slide ul{position:relative; left:0; top:0; width:100%; height:100%; 
              background:no-repeat center /cover;}
.img_slide ul li{position:absolute; width:100%; height:100%; left:100%; top:0;}
.img_slide ul li:nth-child(1){background:url(../images/slide1.jpg); left:0;}
.img_slide ul li:nth-child(2){background:url(../images/slide2.jpg); color:#fff;}
.img_slide ul li:nth-child(3){background:url(../images/slide3.jpg); left:200%;}
```

- **슬라이드 컨테이너**: 높이 300px, 배경색 #434748 (어두운 회색)
- **슬라이드 아이템**: 모든 슬라이드를 절대 위치로 겹쳐 배치
  - 첫 번째 슬라이드: `left:0` (화면에 표시)
  - 두 번째 슬라이드: `left:100%` (오른쪽에 숨김)
  - 세 번째 슬라이드: `left:200%` (더 오른쪽에 숨김)
- **배경 이미지**: 각 슬라이드에 배경 이미지 설정, `cover`로 전체 영역 채움

#### 슬라이드 텍스트 스타일

```css
.img_slide ul li p{position:absolute; top:50%; transform:translateY(-50%); 
                   left:30px; font-size:25px; line-height:40px;}
.img_slide ul li p span{font-weight:bold; color:#1d8844;}
.img_slide ul li:nth-child(2) p span{color:#fff;}
```

- **텍스트 위치**: 수직 중앙 정렬 (`top:50%`, `transform:translateY(-50%)`)
- **텍스트 스타일**: 폰트 크기 25px, 줄 간격 40px
- **강조 텍스트**: `<span>` 태그는 진한 초록색(#1d8844), 두 번째 슬라이드는 흰색

### 5. Contents (콘텐츠 영역) 스타일

```css
.contents{width:100%; height:200px; position:relative;}
```

#### 공지사항 영역

```css
.contents .notice{position:absolute; left:0; top:0; width:33.33%; height:100%;}
.contents .notice h1{position:absolute; font-size:16px; background-color:#3cb149; 
                     padding:10px 20px; border-radius:5px 5px 0 0; 
                     left:10px; top:5px;}
.contents .notice table{position:absolute; font-size:0.75em; left:0; top:35px; 
                        line-height:30px; width:390px; height:160px; 
                        background-color:#1d8844; padding:5px; color:#fff;}
.contents .notice table tr:hover{text-decoration:underline;}
```

- **위치**: 왼쪽에 배치, 너비 33.33% (3등분)
- **제목**: 초록색 배경(#3cb149), 상단 모서리만 둥글게 처리
- **테이블**: 진한 초록색 배경(#1d8844), 흰색 텍스트, 호버 시 밑줄 표시

#### 갤러리 영역

```css
.contents .gallery{position:absolute; left:400px; top:0; width:33.33%; height:100%;}
.contents .gallery h1{position:absolute; font-size:16px; background-color:#3cb149; 
                      padding:10px 20px; border-radius:5px 5px 0 0; 
                      left:10px; top:5px;}
.contents .gallery ul{position:absolute; left:0; top:35px; width:400px; 
                      height:160px; background-color:#434748;}
.contents .gallery ul li{float:left; margin-top:5px; margin-right:10px;}
.contents .gallery ul li:nth-child(1){margin-left:10px;}
```

- **위치**: 중앙에 배치 (left:400px)
- **이미지 리스트**: 어두운 회색 배경(#434748), float로 가로 배치
- **마진**: 첫 번째 이미지만 왼쪽 마진 10px 적용

#### 배너 영역

```css
.contents .banner{position:absolute; left:800px; top:0; width:33.33%; height:100%;}
.contents .banner img{margin-left: 20px; margin-top:5px}
```

- **위치**: 오른쪽에 배치 (left:800px)
- **이미지**: 각 배너 이미지에 왼쪽 마진 20px, 상단 마진 5px 적용

### 6. Footer (푸터) 스타일

```css
.footer{position:relative; width:100%; height:100px; background-color:#434748;}
.footer .f_logo{position:absolute; width:300px; height:100px;}
.footer .f_logo img{position:absolute; left:30px; top:30px;}
.footer .f_copy{position:absolute; width:600px; height:100px; left:300px; top:0;}
.footer .f_copy p{position:absolute; top:10px; left:10px; color:#fff; 
                  font-size:0.8em; line-height:20px;}
.footer .f_family{position:absolute; left:900px; top:0; width:300px; height:100%;}
.footer .f_family img{float:left; margin:34px 10px;}
```

- **푸터 배경**: 어두운 회색(#434748), 높이 100px
- **로고**: 왼쪽에 배치, 이미지 위치 조정
- **저작권 정보**: 중앙에 배치, 흰색 텍스트, 작은 폰트 크기(0.8em)
- **패밀리 사이트**: 오른쪽에 배치, float로 가로 배치

### 7. Popup (팝업) 스타일

```css
#pop{position:fixed; left:0; top:0; width:100%; height:100%; 
     background-color:rgba(0,0,0,0.3); display:none;}
#pop .pop_up{position:absolute; left:50%; top:50%; 
             transform:translate(-50%, -50%); 
             background-color:#fff; width:650px; height:300px;}
#pop .pop_up .title{position:absolute; font-size:20px; left:30px; top:30px;}
#pop .pop_up .word{position:absolute; left:30px; top:100px; width:600px; 
                   word-break:keep-all; font-size:1em; line-height:1.5em;}
#pop .pop_up button{position:absolute; right:30px; bottom:20px; 
                    padding:5px 25px; border:0;}
```

- **팝업 배경**: 전체 화면을 덮는 반투명 검은색 배경 (`rgba(0,0,0,0.3)`)
- **팝업 박스**: 화면 중앙에 배치 (`left:50%`, `top:50%`, `transform:translate(-50%, -50%)`)
- **내용 영역**: 제목과 본문을 절대 위치로 배치
- **단어 줄바꿈**: `word-break:keep-all`로 단어 단위 줄바꿈 유지

---

## 📗 jQuery 상세 설명

### 1. 문서 준비 이벤트

```javascript
$(function(){
    // 모든 코드가 여기에 작성됨
});
```

- **`$(function(){})`**: jQuery의 문서 준비 이벤트
- DOM이 완전히 로드된 후에 실행되도록 보장
- `$(document).ready(function(){})`의 축약형

### 2. 드롭다운 메뉴 기능

```javascript
$(".gnb>li").mouseover(function(){
    $(".gnb>li>ul").stop().slideDown();
    $(".fulldown").stop().slideDown();
});
$(".gnb>li, .fulldown").mouseout(function(){
    $(".gnb>li>ul").stop().slideUp();
    $(".fulldown").stop().slideUp();
});
```

#### 동작 원리:
1. **`mouseover` 이벤트**: 메인 메뉴 항목에 마우스를 올리면
   - `$(".gnb>li>ul").stop().slideDown()`: 모든 서브메뉴를 아래로 슬라이드하여 표시
   - `$(".fulldown").stop().slideDown()`: 배경 영역도 아래로 슬라이드하여 표시
   - `.stop()`: 진행 중인 애니메이션을 중지하고 새 애니메이션 시작

2. **`mouseout` 이벤트**: 메인 메뉴나 배경 영역에서 마우스가 벗어나면
   - `slideUp()`: 서브메뉴와 배경을 위로 슬라이드하여 숨김

#### jQuery 메서드 설명:
- **`.stop()`**: 현재 실행 중인 애니메이션을 즉시 중지
- **`.slideDown()`**: 요소를 아래로 슬라이드하며 표시 (기본 속도: 400ms)
- **`.slideUp()`**: 요소를 위로 슬라이드하며 숨김

### 3. 이미지 슬라이드 기능

```javascript
var i = 0;

setInterval(function(){
    i++;
    if(i==3){i=0;}
    $(".img_slide ul li").eq(i).css("left","100%").animate({"left":0},600);
    $(".img_slide ul li").eq(i-1).css("left",0).animate({"left":"-100%"},600);
},3000);
```

#### 동작 원리:
1. **변수 초기화**: `var i = 0` - 현재 슬라이드 인덱스
2. **`setInterval`**: 3초(3000ms)마다 함수 실행
3. **인덱스 증가**: `i++`로 다음 슬라이드로 이동
4. **인덱스 리셋**: `i==3`이면 `i=0`으로 초기화 (0, 1, 2 반복)
5. **슬라이드 애니메이션**:
   - 현재 슬라이드(`eq(i)`): 오른쪽(100%)에서 왼쪽(0)으로 이동
   - 이전 슬라이드(`eq(i-1)`): 현재 위치(0)에서 왼쪽(-100%)으로 이동

#### jQuery 메서드 설명:
- **`.eq(index)`**: 선택된 요소 중 특정 인덱스의 요소 선택 (0부터 시작)
- **`.css(property, value)`**: CSS 속성 설정
- **`.animate(properties, duration)`**: CSS 속성을 애니메이션으로 변경
  - `{"left":0}`: left 속성을 0으로 변경
  - `600`: 애니메이션 지속 시간 600ms

#### 슬라이드 동작 흐름:
```
초기 상태: [슬라이드1(0)] [슬라이드2(100%)] [슬라이드3(200%)]
1초 후:    [슬라이드1(-100%)] [슬라이드2(0)] [슬라이드3(100%)]
2초 후:    [슬라이드1(100%)] [슬라이드2(-100%)] [슬라이드3(0)]
3초 후:    [슬라이드1(0)] [슬라이드2(100%)] [슬라이드3(200%)] (반복)
```

### 4. 팝업 모달 기능

```javascript
$(".contents .notice table tr:nth-child(1)").click(function(){
    $("#pop").stop().show();
});
$("#pop .pop_up button").click(function(){
    $("#pop").stop().hide();
});
```

#### 동작 원리:
1. **팝업 열기**: 공지사항 첫 번째 행을 클릭하면
   - `$("#pop").stop().show()`: 팝업 요소를 표시
   - `.show()`: `display:none`을 `display:block`으로 변경

2. **팝업 닫기**: 팝업 내 닫기 버튼을 클릭하면
   - `$("#pop").stop().hide()`: 팝업 요소를 숨김
   - `.hide()`: `display:block`을 `display:none`으로 변경

#### jQuery 메서드 설명:
- **`.click(function(){})`**: 클릭 이벤트 핸들러 등록
- **`.show()`**: 요소를 표시 (기본: fadeIn 효과)
- **`.hide()`**: 요소를 숨김 (기본: fadeOut 효과)
- **`.nth-child(n)`**: n번째 자식 요소 선택

---

## 🎯 CSS & jQuery 핵심 포인트

### CSS 핵심 기술:
1. **Position 속성**: `relative`, `absolute`, `fixed`를 활용한 레이아웃
2. **Float**: 가로 배치를 위한 float 사용
3. **Transform**: 중앙 정렬을 위한 `translate` 활용
4. **가상 선택자**: `:hover`, `:nth-child()` 등
5. **z-index**: 요소의 겹침 순서 제어

### jQuery 핵심 기술:
1. **이벤트 처리**: `mouseover`, `mouseout`, `click`
2. **애니메이션**: `slideDown()`, `slideUp()`, `animate()`
3. **요소 선택**: 클래스, ID, 자식 선택자 활용
4. **DOM 조작**: `show()`, `hide()`, `css()`
5. **타이머**: `setInterval()`을 활용한 자동 슬라이드

### 성능 최적화:
- `.stop()` 메서드로 애니메이션 중복 실행 방지
- 이벤트 위임을 통한 효율적인 이벤트 처리
- CSS3 transform을 활용한 하드웨어 가속

---

**작성일**: 2024년  
**프로젝트명**: Green 복지재단 홈페이지  
**용도**: 웹디자인기능사 실기 시험 예상문제
