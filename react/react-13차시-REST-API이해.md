# 13차시. REST API 이해

## 학습 목표
- REST API의 개념을 이해할 수 있다
- HTTP 메서드의 역할을 이해할 수 있다
- JSON 구조를 이해할 수 있다
- API 엔드포인트를 이해할 수 있다

---

## 1. API란?

### 1.1 API의 정의

**API (Application Programming Interface)**는 애플리케이션 간에 데이터를 주고받기 위한 인터페이스입니다.

**비유:**
- 레스토랑의 메뉴판
- 주문서를 통해 요청
- 요리사가 음식을 준비하여 제공

**웹 개발에서:**
- 프론트엔드(클라이언트)가 백엔드(서버)에 데이터 요청
- 백엔드가 데이터를 제공

### 1.2 API가 필요한 이유

**전통적인 방식 (문제):**
- 프론트엔드와 백엔드가 강하게 결합
- 데이터 변경 시 전체 수정 필요

**API 사용 (해결):**
- 프론트엔드와 백엔드 분리
- 독립적인 개발 가능
- 다양한 클라이언트 지원 (웹, 모바일 등)

---

## 2. REST API란?

### 2.1 REST의 정의

**REST (Representational State Transfer)**는 웹 서비스를 설계하기 위한 아키텍처 스타일입니다.

**REST의 특징:**
- ✅ **자원 기반**: URL로 자원을 표현
- ✅ **HTTP 메서드**: GET, POST, PUT, DELETE 사용
- ✅ **Stateless**: 각 요청이 독립적
- ✅ **표준화**: 일관된 인터페이스

### 2.2 RESTful API

**RESTful API**는 REST 원칙을 따르는 API입니다.

**예시:**
```
GET    /api/posts          # 게시글 목록 조회
GET    /api/posts/1        # 게시글 상세 조회
POST   /api/posts          # 게시글 생성
PUT    /api/posts/1        # 게시글 수정
DELETE /api/posts/1        # 게시글 삭제
```

---

## 3. HTTP 메서드

### 3.1 주요 HTTP 메서드

| 메서드 | 의미 | 용도 | 예시 |
|--------|------|------|------|
| GET | 조회 | 데이터 가져오기 | 게시글 목록 |
| POST | 생성 | 새 데이터 추가 | 게시글 작성 |
| PUT | 수정 | 전체 데이터 수정 | 게시글 수정 |
| PATCH | 부분 수정 | 일부 데이터 수정 | 게시글 제목만 수정 |
| DELETE | 삭제 | 데이터 삭제 | 게시글 삭제 |

### 3.2 HTTP 메서드 예시

**GET - 조회:**
```
GET /api/posts
→ 게시글 목록 반환

GET /api/posts/1
→ ID가 1인 게시글 반환
```

**POST - 생성:**
```
POST /api/posts
Body: { title: "제목", content: "내용" }
→ 새 게시글 생성
```

**PUT - 수정:**
```
PUT /api/posts/1
Body: { title: "수정된 제목", content: "수정된 내용" }
→ 게시글 전체 수정
```

**DELETE - 삭제:**
```
DELETE /api/posts/1
→ ID가 1인 게시글 삭제
```

---

## 4. JSON 구조

### 4.1 JSON이란?

**JSON (JavaScript Object Notation)**은 데이터를 표현하는 형식입니다.

**특징:**
- ✅ 가볍고 읽기 쉬움
- ✅ JavaScript와 호환
- ✅ 대부분의 언어에서 지원

### 4.2 JSON 구조

**기본 구조:**
```json
{
  "key": "value",
  "number": 123,
  "boolean": true,
  "array": [1, 2, 3],
  "object": {
    "nested": "value"
  }
}
```

**예시:**
```json
{
  "id": 1,
  "title": "React 배우기",
  "author": "홍길동",
  "content": "React는 정말 재미있습니다!",
  "createdAt": "2024-01-15",
  "tags": ["react", "javascript", "frontend"]
}
```

**배열:**
```json
[
  {
    "id": 1,
    "title": "첫 번째 게시글"
  },
  {
    "id": 2,
    "title": "두 번째 게시글"
  }
]
```

### 4.3 JavaScript에서 JSON 사용

**JSON 문자열 → 객체:**
```javascript
const jsonString = '{"name": "홍길동", "age": 25}';
const obj = JSON.parse(jsonString);
console.log(obj.name); // "홍길동"
```

**객체 → JSON 문자열:**
```javascript
const obj = { name: "홍길동", age: 25 };
const jsonString = JSON.stringify(obj);
console.log(jsonString); // '{"name":"홍길동","age":25}'
```

---

## 5. API 엔드포인트

### 5.1 엔드포인트란?

**엔드포인트**는 API의 특정 URL입니다.

**구조:**
```
https://api.example.com/v1/posts
│      │              │  │  │
│      │              │  │  └─ 리소스 (게시글)
│      │              │  └─ 버전
│      │              └─ 도메인
│      └─ 프로토콜
```

### 5.2 RESTful 엔드포인트 설계

**좋은 예:**
```
GET    /api/posts              # 게시글 목록
GET    /api/posts/1            # 게시글 상세
POST   /api/posts              # 게시글 생성
PUT    /api/posts/1            # 게시글 수정
DELETE /api/posts/1            # 게시글 삭제

GET    /api/posts/1/comments   # 댓글 목록
POST   /api/posts/1/comments   # 댓글 생성
```

**나쁜 예:**
```
GET /api/getPosts              # get 불필요
POST /api/createPost           # create 불필요
GET /api/posts/delete/1        # DELETE 메서드 사용해야 함
```

### 5.3 HTTP 상태 코드

**주요 상태 코드:**
- `200 OK`: 성공
- `201 Created`: 생성 성공
- `400 Bad Request`: 잘못된 요청
- `401 Unauthorized`: 인증 필요
- `404 Not Found`: 리소스 없음
- `500 Internal Server Error`: 서버 오류

---

## 6. API 문서 예시

### 6.1 게시글 API 문서

**게시글 목록 조회:**
```
GET /api/posts

Response:
[
  {
    "id": 1,
    "title": "제목",
    "content": "내용",
    "author": "작성자",
    "createdAt": "2024-01-15"
  }
]
```

**게시글 생성:**
```
POST /api/posts
Content-Type: application/json

Request Body:
{
  "title": "제목",
  "content": "내용",
  "author": "작성자"
}

Response:
{
  "id": 1,
  "title": "제목",
  "content": "내용",
  "author": "작성자",
  "createdAt": "2024-01-15"
}
```

---

## 7. 실습: API 이해하기

### 실습 1: JSON 파싱

**요구사항:**
- JSON 문자열을 객체로 변환
- 객체를 JSON 문자열로 변환

**코드:**
```javascript
// JSON 문자열 → 객체
const jsonString = '{"name":"홍길동","age":25,"city":"서울"}';
const person = JSON.parse(jsonString);
console.log(person.name); // "홍길동"

// 객체 → JSON 문자열
const personObj = {
  name: "홍길동",
  age: 25,
  city: "서울"
};
const jsonStr = JSON.stringify(personObj);
console.log(jsonStr); // '{"name":"홍길동","age":25,"city":"서울"}'
```

### 실습 2: API 엔드포인트 분석

**요구사항:**
- 주어진 API 엔드포인트 분석
- HTTP 메서드와 용도 파악

**예시:**
```
GET    /api/users
→ 사용자 목록 조회

POST   /api/users
→ 새 사용자 생성

GET    /api/users/1
→ ID가 1인 사용자 조회

PUT    /api/users/1
→ ID가 1인 사용자 수정

DELETE /api/users/1
→ ID가 1인 사용자 삭제
```

---

## 8. 다음 차시 예고

다음 차시에서는 **fetch / axios**를 배웁니다:
- fetch API 사용법
- axios 라이브러리 사용법
- GET / POST 요청
- 비동기 처리 (async/await)
- 게시글 목록 API 연동

---

## 요약

### 핵심 개념

1. **API**: 애플리케이션 간 데이터 교환 인터페이스
2. **REST**: 웹 서비스 설계 원칙
3. **HTTP 메서드**: GET, POST, PUT, DELETE
4. **JSON**: 데이터 표현 형식

### 주요 HTTP 메서드

- **GET**: 데이터 조회
- **POST**: 데이터 생성
- **PUT**: 데이터 수정
- **DELETE**: 데이터 삭제

### JSON 사용법

```javascript
// 파싱
JSON.parse(jsonString)

// 문자열화
JSON.stringify(object)
```

### 체크리스트

- [ ] API의 개념 이해
- [ ] REST API 개념 이해
- [ ] HTTP 메서드 이해
- [ ] JSON 구조 이해
- [ ] API 엔드포인트 이해

---

**다음 차시에서 만나요! 🚀**

