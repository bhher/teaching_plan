# crudboard — 최소 CRUD

컬럼: **id · title · content** 만 사용합니다.

**자세한 수업 정리:** [crudBoard-상세정리.md](./crudBoard-상세정리.md)  
**문제·답:** [crudBoard-문제답.md](./crudBoard-문제답.md)

## SQL

`db_setup.sql` 실행 → DB **`crudboard`**

```sql
CREATE TABLE board (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  content TEXT NOT NULL
);
```

## 파일

| 기능 | JSP |
|------|-----|
| 목록 | list.jsp |
| 등록 | write.jsp → writeProcess.jsp |
| 상세 | view.jsp |
| 수정 | update.jsp → updateProcess.jsp |
| 삭제 | deleteProcess.jsp |

## 실행

1. MySQL에 `db_setup.sql` 실행  
2. `DBConnection.java` 비밀번호 수정  
3. Eclipse에 src + WebContent 복사, mysql-connector 추가  
4. `list.jsp` 실행  

`crud-board` 보다 단순합니다 (작성자·비밀번호·조회수 없음).
