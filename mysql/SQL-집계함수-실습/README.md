# SQL 집계함수 실습

## 파일 구조

```
SQL-집계함수-실습/
├── create_table.sql          # 테이블 생성 및 더미 데이터
├── 문제별_정답.sql            # 모든 문제의 정답 SQL
└── README.md                 # 프로젝트 설명
```

## 실행 방법

### 1. 테이블 생성 및 데이터 삽입

```bash
mysql -u 사용자명 -p < create_table.sql
```

또는 MySQL Workbench에서:
1. `create_table.sql` 파일 열기
2. 전체 실행 (Ctrl+Shift+Enter)

### 2. 문제 풀이

- `SQL-집계함수-실습문제.md` 파일에서 문제 확인
- 직접 SQL 작성하여 실행
- `문제별_정답.sql`에서 정답 확인

## 테이블 구조

**student 테이블:**
- student_id: 학생 번호 (PK, AUTO_INCREMENT)
- name: 학생 이름
- department: 학과
- grade: 학년
- korean: 국어 점수
- english: 영어 점수
- math: 수학 점수
- science: 과학 점수
- total_score: 총점
- average: 평균

## 데이터 개요

- 총 학생 수: 20명
- 학과: 컴퓨터공학(10명), 전자공학(6명), 기계공학(4명)
- 학년: 1학년(7명), 2학년(6명), 3학년(7명)

## 학습 목표

1. COUNT() - 개수 집계
2. SUM() - 합계 계산
3. AVG() - 평균 계산
4. MAX() - 최대값
5. MIN() - 최소값
6. GROUP BY - 그룹화
7. HAVING - 그룹 조건

## 참고 자료

- 문제: `SQL-집계함수-실습문제.md`
- 해설: `SQL-집계함수-실습문제-정답-해설.md`
