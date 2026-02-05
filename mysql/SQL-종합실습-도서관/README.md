# SQL 종합 실습 - 도서관 시스템 (20문제)

도서관 시스템을 기반으로 한 SQL 종합 실습 문제입니다.

## 파일 구조

```
SQL-종합실습-도서관/
├── create_table.sql              # 테이블 생성 및 더미 데이터
├── 문제별_정답.sql                # 모든 문제의 정답 SQL
└── README.md                     # 이 파일
```

## 사용 방법

### 1. 테이블 생성 및 데이터 삽입

```bash
mysql -u 사용자명 -p < create_table.sql
```

또는 MySQL Workbench에서:
1. `create_table.sql` 파일 열기
2. 전체 실행 (Ctrl+Shift+Enter)

### 2. 문제 풀이

1. `SQL-종합실습-20문제-도서관.md` 파일에서 문제 확인
2. 직접 SQL 작성하여 실행
3. `문제별_정답.sql` 또는 `SQL-종합실습-20문제-도서관-정답-해설.md`에서 정답 확인

## 포함된 문제 유형

### 1. 데이터베이스/테이블 생성 (문제 1-4)
- 데이터베이스 생성 (UTF-8 설정)
- 회원 테이블 생성
- 도서 테이블 생성
- 대출 테이블 생성 (외래키 2개)

### 2. 데이터 삽입 (문제 5-7)
- 회원 데이터 삽입
- 도서 데이터 삽입
- 대출 데이터 삽입

### 3. 데이터 수정 (문제 8-10)
- 회원 상태 변경
- 도서 재고 업데이트 (계산식 사용)
- 대출 반납 처리 (여러 컬럼 동시 수정)

### 4. 데이터 삭제 (문제 11-12)
- 조건부 삭제
- 특정 레코드 삭제

### 5. 집계함수 (문제 13-17)
- COUNT
- GROUP BY
- HAVING
- JOIN과 집계함수 조합

### 6. JOIN (문제 18-20)
- INNER JOIN (3개 테이블)
- LEFT JOIN
- 조건부 JOIN
- 매칭되지 않는 데이터 찾기

## 테이블 구조

### member 테이블 (회원)
- member_id: 회원 ID (PK, AUTO_INCREMENT)
- member_no: 회원번호 (UNIQUE, NOT NULL)
- name: 이름 (NOT NULL)
- phone: 전화번호 (UNIQUE)
- email: 이메일
- join_date: 가입일 (DEFAULT CURRENT_DATE)
- status: 상태 (DEFAULT '활성')

### book 테이블 (도서)
- book_id: 도서 ID (PK, AUTO_INCREMENT)
- isbn: ISBN (UNIQUE, NOT NULL)
- title: 제목 (NOT NULL)
- author: 저자
- publisher: 출판사
- category: 카테고리
- total_copies: 전체 권수 (DEFAULT 1)
- available_copies: 대출 가능 권수 (DEFAULT 1)

### loan 테이블 (대출)
- loan_id: 대출 ID (PK, AUTO_INCREMENT)
- member_no: 회원번호 (FK → member.member_no)
- isbn: ISBN (FK → book.isbn)
- loan_date: 대출일 (DEFAULT CURRENT_DATE)
- return_date: 반납일
- due_date: 반납 예정일 (NOT NULL)
- status: 상태 (DEFAULT '대출중')

## 학습 목표

1. 3개 테이블 구조 이해
2. 외래키 제약조건 활용 (2개)
3. UNIQUE 제약조건 활용
4. DEFAULT 값 활용
5. 재고 관리 개념 이해
6. 대출/반납 처리 로직 이해
7. 3개 테이블 JOIN
8. 복잡한 쿼리 작성

## 기존 문제와의 차이점

### 변별력 요소

1. **도메인**: 도서관 시스템 (기존: 회사 시스템)
2. **테이블 수**: 3개 테이블 (기존: 2개 테이블)
3. **제약조건**: UNIQUE 추가, DEFAULT 다양화
4. **비즈니스 로직**: 재고 관리, 대출/반납 처리
5. **JOIN 복잡도**: 3개 테이블 JOIN

## 참고 자료

- 문제: `SQL-종합실습-20문제-도서관.md`
- 해설: `SQL-종합실습-20문제-도서관-정답-해설.md`

## 총점

**100점** (문제당 5점)
