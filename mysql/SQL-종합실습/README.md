# SQL 종합 실습 (20문제)

SQL의 핵심 기능을 종합적으로 학습하기 위한 실습 문제입니다.

## 파일 구조

```
SQL-종합실습/
├── create_table.sql              # 테이블 생성 및 더미 데이터
├── 문제별_정답.sql                # 모든 문제의 정답 SQL
├── README.md                     # 이 파일
└── (상위 폴더)
    ├── SQL-종합실습-20문제.md     # 문제 파일
    └── SQL-종합실습-20문제-정답-해설.md  # 정답 및 해설
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

1. `SQL-종합실습-20문제.md` 파일에서 문제 확인
2. 직접 SQL 작성하여 실행
3. `문제별_정답.sql` 또는 `SQL-종합실습-20문제-정답-해설.md`에서 정답 확인

## 포함된 문제 유형

### 1. 데이터베이스/테이블 생성 (문제 1-3)
- 데이터베이스 생성 (UTF-8 설정)
- 테이블 생성 (제약조건 포함)
- 외래키 제약조건

### 2. 데이터 삽입 (문제 4-5)
- INSERT 문
- 여러 행 한 번에 삽입

### 3. 데이터 수정 (문제 6-8)
- UPDATE 문
- 조건부 수정
- 계산식 사용

### 4. 데이터 삭제 (문제 9-10)
- DELETE 문
- 조건부 삭제
- 외래키 제약조건 주의

### 5. 집계함수 (문제 11-16)
- COUNT, AVG, MAX, MIN
- GROUP BY
- HAVING

### 6. JOIN (문제 17-20)
- INNER JOIN
- LEFT JOIN
- 조건부 JOIN
- 매칭되지 않는 데이터 찾기

## 테이블 구조

### employee 테이블 (직원)
- emp_id: 직원 ID (PK, AUTO_INCREMENT)
- emp_no: 사원번호 (UNIQUE, NOT NULL)
- name: 이름 (NOT NULL)
- department: 부서
- position: 직책
- salary: 급여
- hire_date: 입사일

### project 테이블 (프로젝트)
- project_id: 프로젝트 ID (PK, AUTO_INCREMENT)
- project_name: 프로젝트명 (NOT NULL)
- emp_no: 담당 사원번호 (FK → employee.emp_no)
- start_date: 시작일
- end_date: 종료일
- status: 상태 (DEFAULT '진행중')

## 학습 목표

1. 데이터베이스 및 테이블 생성
2. 제약조건 이해 및 활용
3. 데이터 삽입/수정/삭제
4. 집계함수 활용
5. JOIN을 통한 테이블 연결
6. 복잡한 쿼리 작성

## 참고 자료

- 문제: `SQL-종합실습-20문제.md`
- 해설: `SQL-종합실습-20문제-정답-해설.md`

## 총점

**150점** (문제당 5-10점)
