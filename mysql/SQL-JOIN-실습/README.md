# SQL JOIN 실습

SQL JOIN을 학습하기 위한 실습 파일입니다.

## 파일 구조

```
SQL-JOIN-실습/
├── create_table.sql      # 테이블 생성 및 더미 데이터
├── join_examples.sql     # JOIN 예제 모음
├── join_examples-해설.md # 각 예제에 대한 상세 해설 (추천!)
└── README.md            # 이 파일
```

## 사용 방법

### 1. 테이블 생성 및 데이터 삽입

```bash
mysql -u 사용자명 -p < create_table.sql
```

또는 MySQL Workbench에서:
1. `create_table.sql` 파일 열기
2. 전체 실행 (Ctrl+Shift+Enter)

### 2. JOIN 예제 실행

```bash
mysql -u 사용자명 -p < join_examples.sql
```

또는 MySQL Workbench에서:
1. `join_examples.sql` 파일 열기
2. 원하는 예제만 선택하여 실행

## 테이블 구조

### student 테이블 (학생)
- student_id: 학생 ID (PK, AUTO_INCREMENT)
- student_no: 학번 (UNIQUE)
- name: 이름
- major: 전공
- grade: 학년

### grade 테이블 (성적)
- grade_id: 성적 ID (PK, AUTO_INCREMENT)
- student_no: 학번 (FK)
- subject: 과목명
- score: 점수

### department 테이블 (부서)
- dept_id: 부서 ID (PK, AUTO_INCREMENT)
- dept_name: 부서명
- location: 위치

### employee 테이블 (직원 - SELF JOIN 예제용)
- emp_id: 직원 ID (PK, AUTO_INCREMENT)
- emp_name: 직원 이름
- manager_id: 상사 ID (FK, 자기 참조)

## 포함된 JOIN 예제

1. **INNER JOIN**
   - 기본 INNER JOIN
   - 조건이 있는 INNER JOIN
   - 3개 테이블 JOIN

2. **LEFT JOIN**
   - 기본 LEFT JOIN
   - 성적이 없는 학생 찾기
   - 집계 함수와 함께 사용

3. **RIGHT JOIN**
   - 기본 RIGHT JOIN
   - 학생 정보가 없는 성적 찾기

4. **FULL OUTER JOIN**
   - UNION을 사용한 구현

5. **CROSS JOIN**
   - 모든 조합 생성

6. **SELF JOIN**
   - 직원과 상사 관계
   - 같은 전공 학생 찾기

7. **실전 예제**
   - 학생별 총점과 평균
   - 전공별 평균 점수
   - 복잡한 JOIN (3개 이상 테이블)
   - 조건이 있는 JOIN

## 학습 순서

1. `create_table.sql` 실행하여 테이블 생성
2. `join_examples-해설.md` 파일을 읽으며 개념 이해
3. `join_examples.sql`의 예제를 하나씩 실행하며 결과 확인
4. 해설 문서와 비교하며 각 JOIN의 차이점 이해
5. 직접 쿼리 작성 연습

## 참고 자료

- `join_examples-해설.md` - **각 예제에 대한 상세한 해설** (추천!)
- `SQL-JOIN-완전정복.md` - JOIN 전체 개념과 설명
