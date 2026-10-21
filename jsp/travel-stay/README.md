# travel-stay — 여행지 소개 · 숙소 추천 (JSP)

중급 수업용. **CRUD · JOIN · 검색 · 관리자 · 파일 업로드 · 찜 · 리뷰**

**상세 정리:** [travel-stay-상세정리.md](./travel-stay-상세정리.md)  
**ERD 연관관계:** [travel-stay-ERD-연관관계.md](./travel-stay-ERD-연관관계.md)

## 기능

| 구분 | 내용 |
|------|------|
| 사용자 | 지역·키워드 검색, 상세, 주변 숙소, 찜, 리뷰·평점 |
| 관리자 (`role=ADMIN`) | 여행지·숙소 등록/수정/삭제, 이미지 URL 또는 COS 업로드 |
| DB | `destination` ← `stay` JOIN, `wishlist`, `review` |

## 실행

1. MySQL에서 `db_setup.sql` 실행 (`travel_stay`)
2. Eclipse Dynamic Web Project + `src` / `WebContent`
3. `WEB-INF/lib`에 추가  
   - `mysql-connector-j`  
   - **`cos.jar`** (MultipartRequest 파일 업로드)
4. `DBConnection.java` 비밀번호 확인
5. Tomcat → `http://localhost:8080/travel-stay/`

### 계정

- 관리자: `admin` / `1234`
- 사용자: `hong` / `1234`, `kim` / `1234`

> cos.jar 없이 관리자 등록을 쓰려면 이미지 **URL만** 넣고,  
> MultipartRequest JSP는 cos 추가 후 사용하세요. (URL만으로도 샘플 데이터는 동작)

## 학습 포인트

```text
검색 조건 → DestinationDAO.search(region, keyword)
JOIN      → 여행지 + 숙소 수 + 평균 평점
세션 role → ADMIN만 /admin/*
업로드    → MultipartRequest → WebContent/uploads
찜·리뷰   → wishlist / review 테이블
```

## 폴더

```text
travel-stay/
├── db_setup.sql
├── README.md
├── src/ util · dto · dao
└── WebContent/
    ├── index.jsp, include/
    ├── member/          가입·로그인
    ├── destination/     목록·상세
    ├── wishlist/, review/
    ├── admin/           여행지·숙소 관리
    └── uploads/
```

## 흐름

1. 사용자: 목록 검색 → 상세(숙소 JOIN) → 로그인 후 찜·리뷰  
2. 관리자: 로그인 → 관리자 메뉴 → 여행지/숙소 CRUD (+ 이미지)
