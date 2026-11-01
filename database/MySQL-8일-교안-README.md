# MySQL 1~8일차 수업 교안

비전공자·입문자용. 강사가 화면에 띄워 그대로 설명할 수 있는 구성입니다.

**이후 연결:** JDBC → MyBatis → JPA → Spring Boot + MySQL

---

## 차시표

| 차시 | 파일 | 주제 | 핵심 |
|------|------|------|------|
| 1일차 | [MySQL-1일차.md](./MySQL-1일차.md) | 설치와 DB 기초 | Workbench, DB/테이블, CREATE, INSERT, SELECT |
| 2일차 | [MySQL-2일차.md](./MySQL-2일차.md) | SELECT 기본 | 컬럼, 별칭, DISTINCT, 산술 |
| 3일차 | [MySQL-3일차.md](./MySQL-3일차.md) | 조건·정렬 | WHERE, LIKE, IN, BETWEEN, ORDER BY, LIMIT |
| 4일차 | [MySQL-4일차.md](./MySQL-4일차.md) | 함수·집계 | 문자열/숫자/날짜, COUNT~MIN |
| 5일차 | [MySQL-5일차.md](./MySQL-5일차.md) | GROUP BY | GROUP BY, HAVING |
| 6일차 | [MySQL-6일차.md](./MySQL-6일차.md) | JOIN | INNER, LEFT, 여러 테이블 |
| 7일차 | [MySQL-7일차.md](./MySQL-7일차.md) | 변경·서브쿼리 | INSERT, UPDATE, DELETE, 서브쿼리 |
| 8일차 | [MySQL-8일차.md](./MySQL-8일차.md) | 설계·종합 | PK/FK, 쇼핑몰 미니 프로젝트 |

## 수업 진행 비율 (약 150분)

| 구간 | 시간 |
|------|------|
| 개념 설명 | 20분 |
| 강사 시연 | 30분 |
| 학생 따라하기 | 30분 |
| 실습문제 | 40분 |
| 문제 풀이 | 20분 |
| 복습·질문 | 10분 |

수준이 낮을수록 **설명보다 직접 입력 시간**을 늘리세요.

## 데이터 이어가기 (추천)

6→7→8일차는 **같은 쇼핑몰 데이터**로 이어가면 SQL을 단편적으로 외우지 않습니다.

```
6일차 JOIN용 member / order
        ↓
7일차 product CRUD + 서브쿼리
        ↓
8일차 member + product + orders 종합
```

## 학생에게 최종 강조할 5가지

1. SQL은 **영어 문장처럼** 읽는다  
2. **SELECT / FROM / WHERE** 를 먼저  
3. GROUP BY = **묶어서 계산**  
4. JOIN = **테이블 연결** (`ON ...`)  
5. UPDATE·DELETE 는 **WHERE 먼저**
