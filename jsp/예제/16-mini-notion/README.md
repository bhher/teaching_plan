# 📒 미니 노션 - 계층형 게시판

@WebServlet("*.do") 기반 JSP 계층형 게시판 (Re-level, Re-step 알고리즘)

## 주요 기능

| URL | 기능 |
|-----|------|
| `list.do` | 전체 노트 목록 보기 (계층형 정렬) |
| `write.do` | 새 노트 작성 |
| `write.do?parentId=N` | N번 노트에 답글 작성 |
| `writeProcess.do` | 작성 처리 (POST) |
| `update.do?id=N` | N번 노트 수정 |
| `updateProcess.do` | 수정 처리 (POST) |
| `delete.do?id=N` | N번 노트 휴지통 이동 (Soft Delete) |
| `trash.do` | 휴지통 목록 |

## 계층형 구조 (Re-level, Re-step)

- **ref**: 원글(그룹) ID. 같은 원글에 달린 답글들은 같은 ref
- **re_level**: 들여쓰기 단계 (0=원글, 1=1단계 답글, 2=2단계 답글...)
- **re_step**: 같은 ref 내 정렬 순서

정렬: `ORDER BY ref DESC, re_step ASC`

## DB 설정

```bash
mysql -u root -p < schema.sql
```

또는 MySQL에서 `schema.sql` 내용 실행

## 프로젝트 구조

```
16-mini-notion/
├── schema.sql              # DB 스키마
├── src/notion/
│   ├── FrontController.java   # @WebServlet("*.do")
│   ├── NoteDAO.java           # 계층형 CRUD
│   ├── NoteDTO.java
│   └── DBConnection.java
└── WebContent/
    ├── index.jsp
    ├── list.jsp
    ├── write.jsp
    ├── update.jsp
    ├── trash.jsp
    └── WEB-INF/web.xml
```

## Eclipse 설정

1. Dynamic Web Project 생성
2. `src/notion/` 아래 Java 파일 배치
3. `WebContent/` 아래 JSP 파일 배치
4. MySQL Connector JAR을 `WEB-INF/lib/`에 추가
5. Tomcat 10+ (Jakarta EE 9+) 사용
