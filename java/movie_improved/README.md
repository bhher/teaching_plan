# 영화 예매 시스템 - 개선된 버전

## 개선 사항

### 1. 데이터 영속성 (Data Persistence)
- ✅ 영화 목록 파일 저장/로드 (`movies.txt`)
- ✅ 사용자 정보 파일 저장/로드 (`users.txt`)
- ✅ 예약 정보 파일 저장/로드 (`reservations.txt`)
- ✅ 설정 정보 파일 저장/로드 (`settings.txt`)
- ✅ 프로그램 종료 후에도 데이터 유지

### 2. 예외 처리 강화
- ✅ 모든 입력에 대한 검증 추가
- ✅ 구체적인 오류 메시지 제공
- ✅ NumberFormatException 처리
- ✅ IllegalArgumentException 처리
- ✅ IOException 처리

### 3. 코드 중복 제거
- ✅ `cancelAllReservationsByMovie()` 공통 메서드 생성
- ✅ 영화 삭제와 수정에서 공통 메서드 사용
- ✅ 입력 검증 로직 통합

### 4. 사용자 인증 개선
- ✅ User 클래스에 비밀번호 필드 추가
- ✅ 비밀번호 확인 메서드 추가
- ✅ 향후 확장 가능한 구조

## 파일 구조

```
movie_improved/
├── MovieReservationMain.java  (메인 클래스)
├── Movie.java                 (영화 정보 클래스)
├── ReservationManager.java    (예약 관리 클래스 - 개선)
├── Theater.java              (상영관 좌석 관리 클래스 - 개선)
├── Ticket.java               (티켓 출력 클래스 - 개선)
├── User.java                 (사용자 정보 클래스 - 개선)
├── DataManager.java          (데이터 영속성 관리 클래스 - 신규)
└── README.md                 (이 파일)
```

## 데이터 파일 구조

### movies.txt
```
제목/상영시간/가격/총좌석수|좌석상태1,좌석상태2,...
Inception/18:00/12000/50|1,2,3,X,5,6,7,8,9,10,...
```

### users.txt
```
이름|비밀번호|총결제금액|예매번호카운터
홍길동||12000|3
COUNTER|5
```

### reservations.txt
```
사용자이름|예매번호1,예매번호2|영화1,영화2|좌석1,좌석2
홍길동|1,2|Inception,Interstellar|5,10
```

### settings.txt
```
DISCOUNT_RATE=10
```

## 컴파일 및 실행

```bash
# 컴파일
javac movie_improved/*.java

# 실행
java movie_improved.MovieReservationMain
```

## 주요 변경 사항

### Movie 클래스
- `toFileString()` 메서드 추가
- Setter 메서드 추가
- 입력 검증 추가

### Theater 클래스
- `getTotalSeats()` 메서드 추가
- `toFileString()` 메서드 추가
- `loadFromFileString()` 메서드 추가
- 생성자에 입력 검증 추가

### User 클래스
- `password` 필드 추가
- `checkPassword()` 메서드 추가
- `setPassword()` 메서드 추가
- `toFileString()` 메서드 추가
- `reservationsToFileString()` 메서드 추가
- 정적 메서드로 카운터 관리

### ReservationManager 클래스
- `cancelAllReservationsByMovie()` 공통 메서드 추가
- 모든 입력 검증 강화
- 예외 처리 개선
- Getter 메서드 추가 (DataManager에서 사용)

### Ticket 클래스
- 파일 경로 상수화
- 예외 처리 강화
- 상세한 로그 메시지

### DataManager 클래스 (신규)
- 데이터 저장/로드 기능
- 파일 기반 영속성 제공
- 자동 데이터 백업

## 사용 방법

1. 프로그램 실행 시 자동으로 저장된 데이터 로드
2. 사용자 메뉴 종료 시 자동 저장
3. 운영자 메뉴에서 수동 저장 가능
4. 프로그램 종료 시 자동 저장

## 개선 효과

- **데이터 보존**: 프로그램 재시작 후에도 데이터 유지
- **안정성 향상**: 강화된 예외 처리로 오류 방지
- **코드 품질**: 중복 제거로 유지보수성 향상
- **확장성**: 향후 기능 추가 용이
