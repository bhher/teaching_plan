# 학생 관리 시스템

콘솔 기반 학생 관리 시스템입니다. 객체지향 프로그래밍 원칙을 적용하여 구현했습니다.

## 프로젝트 구조

```
example15/
├── model/
│   └── Student.java          # 학생 도메인 모델
├── service/
│   └── StudentService.java   # 비즈니스 로직
├── repository/
│   └── StudentRepository.java # 데이터 저장/불러오기
├── controller/
│   └── StudentController.java # 사용자 인터페이스 제어
├── Main.java                 # 진입점
└── README.md                 # 프로젝트 설명
```

## 기능

- 학생 등록 (학번, 이름, 나이, 전공)
- 학생 조회 (전체 조회, 학번으로 조회)
- 학생 정보 수정
- 학생 삭제
- 파일 저장/불러오기

## 실행 방법

### 컴파일

```bash
javac -d . model/*.java service/*.java repository/*.java controller/*.java Main.java
```

### 실행

```bash
java Main
```

### VS Code에서 실행

1. `Main.java` 파일을 엽니다
2. `main` 메서드 위의 "Run" 버튼을 클릭하거나 `F5` 키를 누릅니다

## 사용 방법

### 메뉴

```
=== 메뉴 ===
1. 학생 등록
2. 학생 조회 (전체)
3. 학생 조회 (학번)
4. 학생 수정
5. 학생 삭제
6. 파일 저장
7. 파일 불러오기
0. 종료
```

### 예제

1. **학생 등록**
   - 메뉴에서 1 선택
   - 학번, 이름, 나이, 전공 입력

2. **학생 조회**
   - 메뉴에서 2 선택 (전체 조회)
   - 메뉴에서 3 선택 (학번으로 조회)

3. **학생 수정**
   - 메뉴에서 4 선택
   - 수정할 학번 입력
   - 새로운 정보 입력 (엔터 시 기존 값 유지)

4. **학생 삭제**
   - 메뉴에서 5 선택
   - 삭제할 학번 입력
   - 확인 (y/n)

5. **파일 저장**
   - 메뉴에서 6 선택
   - `data/students.txt` 파일에 저장

6. **파일 불러오기**
   - 메뉴에서 7 선택
   - `data/students.txt` 파일에서 불러오기

## 파일 형식

학생 정보는 CSV 형식으로 저장됩니다:

```
학번,이름,나이,전공
2024001,홍길동,20,컴퓨터공학
2024002,김철수,21,전자공학
```

## 클래스 설명

### Student (model)
- 학생 정보를 담는 도메인 모델
- 학번, 이름, 나이, 전공 필드
- 파일 저장/불러오기 메서드 포함

### StudentService (service)
- 학생 관리 비즈니스 로직 처리
- CRUD 기능 제공
- 학번 중복 확인 등 검증 로직 포함

### StudentRepository (repository)
- 파일에 데이터 저장/불러오기
- `data/students.txt` 파일 사용

### StudentController (controller)
- 사용자 입력 처리
- 메뉴 표시 및 선택 처리
- 서비스와 저장소를 연결

## 객체지향 설계 원칙

1. **단일 책임 원칙 (SRP)**
   - 각 클래스는 하나의 책임만 가짐

2. **캡슐화**
   - 필드는 private으로 선언
   - getter/setter로 접근

3. **의존성 주입**
   - Controller가 Service와 Repository를 주입받음

4. **계층 분리**
   - Model, Service, Repository, Controller 계층 분리

## 개선 사항

- [ ] 입력 검증 강화
- [ ] 예외 처리 개선
- [ ] 로깅 추가
- [ ] 데이터베이스 연동
- [ ] GUI 추가
- [ ] 단위 테스트 작성

## 라이선스

이 프로젝트는 교육 목적으로 만들어졌습니다.

