# 🇰🇷 K-Culture Platform

**다각화하는 K-컬쳐 외국인 관광객 커뮤니티(정보) 플랫폼**

외국인 관광객을 위한 K-컬쳐 정보 공유 및 커뮤니티 미니 프로젝트

## 주요 기능

### K-컬쳐 카테고리 (다각화)
| 카테고리 | 설명 |
|----------|------|
| 🍜 K-Food | 한식 맛집, 음식 정보 |
| 🎵 K-Pop | 케이팝 콘서트, 팬미팅 |
| 📺 K-Drama | 드라마 촬영지, 스팟 |
| 🎬 K-Movie | 한국영화, 영화관 |
| 💄 K-Beauty | K뷰티, 화장품 |
| 👗 K-Fashion | 패션, 스트리트 패션 |
| ✈️ Travel | 여행 코스, 추천 장소 |
| 🏯 Traditional | 전통문화 체험 |
| 🎉 Festivals | 축제, 이벤트 |
| 🏨 Accommodation | 숙소, 호텔 |
| 🛍️ Shopping | 쇼핑, 명동/홍대 |
| 🌃 Nightlife | 나이트라이프, 클럽 |
| 🎮 K-Gaming | 게임, e스포츠 |
| 💡 Tips | 여행 팁 & 정보 |

### 커뮤니티 기능
- **회원가입/로그인** - 국적, 선호 언어 입력
- **게시글 작성** - 카테고리별 정보 공유
- **댓글** - 관광객 간 소통
- **조회수** - 인기 글 확인

## URL (*.do)

| URL | 기능 |
|-----|------|
| list.do | 전체/카테고리별 목록 |
| view.do?id=N | 게시글 상세 + 댓글 |
| write.do | 글쓰기 (로그인 필요) |
| edit.do?id=N | 수정 |
| delete.do?id=N | 삭제 |
| commentProcess.do | 댓글 등록 |
| login.do / join.do | 로그인/회원가입 |

## DB 설정

**신규 설치**
```bash
mysql -u root -p < schema.sql
```

**기존 DB에 카테고리만 추가**
```bash
mysql -u root -p < schema_add_categories.sql
```

**테스트 계정**
- Email: `tourist@test.com` / Password: `1234`
- Email: `visitor@test.com` / Password: `1234`

## 프로젝트 구조

```
17-Kculture-Platform/
├── schema.sql
├── src/kculture/
│   ├── FrontController.java   # @WebServlet("*.do")
│   ├── MemberDAO.java, MemberDTO.java
│   ├── CategoryDAO.java, CategoryDTO.java
│   ├── PostDAO.java, PostDTO.java
│   ├── CommentDAO.java, CommentDTO.java
│   └── DBConnection.java
└── WebContent/
    ├── index.jsp, list.jsp, view.jsp
    ├── write.jsp, edit.jsp
    ├── login.jsp, join.jsp
    └── WEB-INF/web.xml
```

## 실행

1. MySQL에서 `schema.sql` 실행
2. Eclipse Dynamic Web Project로 import
3. MySQL Connector JAR → WEB-INF/lib
4. Tomcat 10+ 실행
5. `http://localhost:8080/프로젝트명/list.do`
