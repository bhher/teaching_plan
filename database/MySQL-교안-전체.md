# MySQL 교안 전체 목차

**대상:** DB 입문 ~ 중급 (웹 백엔드·정보처리기사 SQL 기초 포함)  
**환경:** MySQL 8.x + MySQL Workbench (또는 CLI)

---

## 학습 로드맵

```
1-2주          3-4주            5-6주           7-8주
개념·설치  →  조회·집계   →   JOIN·제약   →  설계·프로젝트
CRUD           GROUP BY        서브쿼리        뷰·인덱스
```

---

## 1–2주차 — 시작과 CRUD

1. 데이터베이스·RDBMS·SQL이란?
2. MySQL 설치·접속·Workbench
3. DATABASE 생성 / 삭제 / 사용
4. 자료형, CREATE TABLE
5. INSERT / SELECT / UPDATE / DELETE
6. 실습: 학생 테이블 CRUD

→ [MySQL-교안-1-2주차.md](./MySQL-교안-1-2주차.md)

---

## 3–4주차 — 조건·함수·집계

1. WHERE, 비교·논리·LIKE·IN·BETWEEN·NULL
2. ORDER BY, LIMIT
3. 문자열·숫자·날짜 함수
4. COUNT, SUM, AVG, MAX, MIN
5. GROUP BY, HAVING
6. 실습: 성적·주문 집계

→ [MySQL-교안-3-4주차.md](./MySQL-교안-3-4주차.md)

---

## 5–6주차 — JOIN·서브쿼리·제약

1. INNER / LEFT / RIGHT JOIN
2. 셀프 조인 개념
3. 서브쿼리 (WHERE, FROM, SELECT)
4. PRIMARY KEY, FOREIGN KEY, UNIQUE, NOT NULL, DEFAULT, CHECK
5. ALTER TABLE
6. 실습: 회원·주문·상품

→ [MySQL-교안-5-6주차.md](./MySQL-교안-5-6주차.md)

---

## 7–8주차 — 심화·프로젝트

1. VIEW
2. INDEX
3. 사용자·권한 (GRANT / REVOKE)
4. 트랜잭션 (COMMIT / ROLLBACK) 개념
5. ERD·정규화 입문
6. 미니 프로젝트: 도서 대출 / 쇼핑몰 중 택1

→ [MySQL-교안-7-8주차.md](./MySQL-교안-7-8주차.md)

---

## SQL 언어 분류 (전체 공통)

| 분류 | 이름 | 명령어 예 |
|------|------|-----------|
| DDL | 정의 | CREATE, ALTER, DROP |
| DML | 조작 | INSERT, UPDATE, DELETE |
| DQL | 조회 | SELECT |
| DCL | 제어 | GRANT, REVOKE |
| TCL | 트랜잭션 | COMMIT, ROLLBACK |

---

## 추가 학습 (기존 저장소)

- [MySQL-기초-교안](../mysql/MySQL-기초-교안.md)
- [MySQL-중급-교안](../mysql/MySQL-중급-교안.md)
- [SQL-JOIN-실습](../mysql/SQL-JOIN-실습/)
- [SQL-종합실습-20문제](../mysql/SQL-종합실습-20문제.md)
- [SQLD-시험준비](../mysql/SQLD-시험준비-교안.md)
