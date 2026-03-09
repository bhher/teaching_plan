# 🌤️ OpenWeatherMap.org 사용법 - 단계별 가이드

## 📌 목차
1. [회원가입 및 API 키 발급](#1-회원가입-및-api-키-발급)
2. [도시 코드 찾기](#2-도시-코드-찾기)
3. [API 호출 방법](#3-api-호출-방법)
4. [온도 단위 설정](#4-온도-단위-설정)
5. [날씨 아이콘 사용](#5-날씨-아이콘-사용)
6. [유용한 도구](#6-유용한-도구)

---

## 1. 회원가입 및 API 키 발급

### 1단계: 사이트 접속
- **사이트 주소:** http://openweathermap.org/

### 2단계: 회원가입
1. 우측 상단 **Sign in** 버튼 클릭
2. **Create an Account** 또는 **Sign Up** 클릭
3. 이메일, 비밀번호 등 정보 입력하여 회원가입 완료

### 3단계: API 키 복사
1. 로그인 후 상단 메뉴에서 **API keys** 탭 클릭
2. 생성된 API 키를 복사
   - 예시: `cd63accc133fc76e1f94a3f270442688`

**⚠️ 중요:** API 키는 개인 정보이므로 절대 공개하지 마세요!

---

## 2. 도시 코드 찾기

### 방법: 검색 기능 사용

1. **메인 화면에서 검색**
   - 메인 화면의 **돋보기(검색창)** 클릭
   - 또는 **"weather in your city"** placeholder가 있는 검색창 찾기

2. **도시명 입력**
   - 예: `Hanam` (하남)
   - 예: `Seoul` (서울)

3. **검색 결과 클릭**
   - 검색된 도시를 클릭하면 날씨 정보 페이지로 이동

4. **도시 코드 확인**
   - 브라우저 주소표시줄 확인
   - 형식: `https://openweathermap.org/city/1897007`
   - **오른쪽 숫자가 도시 코드입니다!**

### 주요 도시 코드
| 도시 | 코드 |
|------|------|
| 하남 | `1897007` |
| 서울 | `1835848` |

---

## 3. API 호출 방법

### 3.1 기본 URL 구조

```
http://api.openweathermap.org/data/2.5/{API종류}?id={도시코드}&appid={API키}
```

### 3.2 Forecast API (5일 예보)

**용도:** 5일간의 날씨 예보 (3시간 간격)

**URL:**
```
http://api.openweathermap.org/data/2.5/forecast?id={도시코드}&appid={API키}
```

**예시:**
```
http://api.openweathermap.org/data/2.5/forecast?id=1897007&appid=cd63accc133fc76e1f94a3f270442688
```

**응답 데이터:**
- 5일간의 날씨 예보
- 온도, 습도, 날씨 설명, 아이콘 등

### 3.3 Weather API (현재 날씨)

**용도:** 현재 날씨 정보

**URL:**
```
http://api.openweathermap.org/data/2.5/weather?id={도시코드}&appid={API키}
```

**예시:**
```
http://api.openweathermap.org/data/2.5/weather?id=1897007&appid=cd63accc133fc76e1f94a3f270442688
```

**응답 데이터:**
- 현재 날씨 정보
- 온도, 습도, 날씨 설명, 아이콘 등

### 3.4 API 테스트 방법

**브라우저 주소창에 직접 입력:**
1. 위 예시 URL을 복사
2. 브라우저 주소창에 붙여넣기
3. Enter 키 누르기
4. JSON 데이터 확인

---

## 4. 온도 단위 설정

### 4.1 기본 단위 (Kelvin - 켈빈)

**기본값:** 온도는 Kelvin 단위로 반환됩니다.

**예시 응답:**
```json
{
  "main": {
    "temp": 279.23
  }
}
```

### 4.2 섭씨(Celsius)로 변경

**파라미터 추가:** `&units=metric`

**예시:**
```
http://api.openweathermap.org/data/2.5/forecast?id=1897007&appid=cd63accc133fc76e1f94a3f270442688&units=metric
```

**변경 후 응답:**
```json
{
  "main": {
    "temp": 6.08  // 섭씨 온도
  }
}
```

### 4.3 화씨(Fahrenheit)로 변경

**파라미터 추가:** `&units=imperial`

**예시:**
```
http://api.openweathermap.org/data/2.5/forecast?id=1897007&appid=cd63accc133fc76e1f94a3f270442688&units=imperial
```

### 4.4 온도 단위 비교표

| 단위 | 파라미터 | 예시 온도 | 설명 |
|------|----------|-----------|------|
| Kelvin | (기본값) | 279.23 | 절대 온도 |
| Celsius | `&units=metric` | 6.08 | 섭씨 (한국에서 주로 사용) |
| Fahrenheit | `&units=imperial` | 42.94 | 화씨 (미국에서 사용) |

**💡 팁:** 한국에서는 보통 `&units=metric`을 사용합니다!

---

## 5. 날씨 아이콘 사용

### 5.1 아이콘 코드 확인

**공식 문서:** https://openweathermap.org/weather-conditions

**아이콘 코드 형식:**
- 예: `10d` (light rain, 낮)
- 예: `01n` (clear sky, 밤)

**코드 구조:**
- 앞 2자리: 날씨 코드 (01, 02, 03, 04, 09, 10, 11, 13, 50)
- 마지막 1자리: 시간대
  - `d` = 낮 (day)
  - `n` = 밤 (night)

### 5.2 아이콘 URL 생성

**기본 형식:**
```
https://openweathermap.org/img/wn/{아이콘코드}@2x.png
```

**예시:**
```
https://openweathermap.org/img/wn/10d@2x.png
```

**HTML에서 사용:**
```html
<img src="https://openweathermap.org/img/wn/10d@2x.png" alt="날씨 아이콘">
```

### 5.3 API 응답에서 아이콘 가져오기

**JSON 응답 예시:**
```json
{
  "weather": [
    {
      "id": 500,
      "main": "Rain",
      "description": "light rain",
      "icon": "10d"
    }
  ]
}
```

**JSP에서 사용:**
```jsp
<img src="https://openweathermap.org/img/wn/${weather.icon}@2x.png" 
     alt="${weather.description}">
```

---

## 6. 유용한 도구

### 6.1 JSON Viewer

**브라우저 확장 프로그램:**
- Chrome 또는 Edge에서 "JSON Viewer" 검색하여 설치
- JSON 데이터를 보기 좋게 포맷팅해줍니다

**온라인 도구:**
- **사이트:** https://tools.arantius.com/tabifier
- **용도:** HTML, CSS, JSON 코드를 예쁘게 정리해주는 사이트
- **사용법:** 코드를 붙여넣고 포맷팅 버튼 클릭

### 6.2 API 가이드 참고

**공식 가이드 확인:**
1. 메인 메뉴에서 **Guide** 클릭
2. 하단에 **"How to start"** 클릭
3. **"Example on how to make an API call using your API key"** 섹션 확인

**API 파라미터 확인:**
1. 메뉴에서 **API** 클릭
2. **Parameters of API response** 선택
3. **JSON** 탭 선택
4. **Ctrl + F**로 **"Units of measurement"** 검색
5. 온도 단위 설정 방법 확인

---

## 📝 실전 예제

### 완성된 API URL 예시

**하남 현재 날씨 (섭씨):**
```
http://api.openweathermap.org/data/2.5/weather?id=1897007&appid=cd63accc133fc76e1f94a3f270442688&units=metric
```

**서울 5일 예보 (섭씨):**
```
http://api.openweathermap.org/data/2.5/forecast?id=1835848&appid=cd63accc133fc76e1f94a3f270442688&units=metric
```

### JSON 데이터 구조 이해

**배열 예시:**
```javascript
var array = [1, 2, 3, 4];
array[0] = 1;  // 첫 번째 요소

var weatherArray = [
  {temp: 6.08, temp_min: 4.5},
  {temp: 7.2, temp_min: 5.0}
];
weatherArray[0].temp;  // 6.08
```

**Forecast API 응답 구조:**
```json
{
  "list": [
    {
      "main": {
        "temp": 6.08,
        "temp_min": 4.5,
        "temp_max": 7.2
      },
      "weather": [
        {
          "main": "Rain",
          "description": "light rain",
          "icon": "10d"
        }
      ],
      "dt_txt": "2024-01-15 12:00:00"
    }
  ]
}
```

---

## ✅ 체크리스트

다음 단계를 따라하세요:

- [ ] OpenWeatherMap.org 회원가입 완료
- [ ] API 키 발급 및 복사 완료
- [ ] 원하는 도시 코드 확인 완료 (하남: 1897007, 서울: 1835848)
- [ ] 브라우저에서 Forecast API 테스트 완료
- [ ] 브라우저에서 Weather API 테스트 완료
- [ ] `&units=metric` 파라미터 추가하여 섭씨 온도 확인 완료
- [ ] 날씨 아이콘 URL 생성 방법 이해 완료
- [ ] JSON Viewer 도구 설치 완료

---

## 💡 실전 팁

### 팁 1: API 키 보안
- ❌ JSP 파일에 API 키를 직접 하드코딩하지 마세요
- ✅ `web.xml`의 초기화 파라미터나 별도 설정 파일 사용 권장

### 팁 2: 에러 처리
- API 호출 실패 시 예외 처리 추가
- 네트워크 오류, API 키 오류 등 처리

### 팁 3: 온도 단위
- 한국에서는 `&units=metric` (섭씨) 사용 권장
- URL 끝에 `&units=metric` 추가하기

### 팁 4: JSON 라이브러리
- JSP에서 JSON 파싱을 위해 `org.json` 라이브러리 필요
- Maven 또는 수동으로 `WEB-INF/lib/` 폴더에 추가

---

## 🔗 빠른 참고 링크

- **공식 사이트:** http://openweathermap.org/
- **API 문서:** https://openweathermap.org/api
- **날씨 아이콘:** https://openweathermap.org/weather-conditions
- **JSON 포맷터:** https://tools.arantius.com/tabifier

---

## 📌 요약

1. **회원가입** → API 키 발급
2. **도시 검색** → 도시 코드 확인
3. **API URL 생성** → `http://api.openweathermap.org/data/2.5/weather?id={도시코드}&appid={API키}&units=metric`
4. **브라우저에서 테스트** → JSON 데이터 확인
5. **아이콘 사용** → `https://openweathermap.org/img/wn/{아이콘코드}@2x.png`

**이제 OpenWeatherMap API를 활용하여 날씨 애플리케이션을 만들어보세요! 🌈**
