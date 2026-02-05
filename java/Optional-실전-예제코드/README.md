# Optional 실전 예제 코드

Optional을 사용한 실전 개선 예제 코드입니다.

## 파일 구조

```
Optional-실전-예제코드/
├── MemberServiceExample.java  # MemberService 개선 예제
└── README.md                  # 이 파일
```

## 컴파일 및 실행

### 1. 컴파일

```bash
javac -d . optional/*.java
```

### 2. 실행

```bash
java optional.MemberServiceExample
```

## 포함된 예제

### MemberServiceExample.java

**주요 내용:**

1. **기본 사용 (ifPresent)**
   - 값이 있을 때만 실행
   - 안전한 null 처리

2. **기본값 제공 (orElse)**
   - 값이 없을 때 기본값 사용
   - 항상 값이 보장됨

3. **예외 던지기 (orElseThrow)**
   - 값이 없을 때 커스텀 예외 발생
   - 명시적인 오류 처리

4. **값 변환 (map)**
   - Optional의 값을 다른 타입으로 변환
   - 체이닝 가능

5. **조건부 실행 (ifPresentOrElse)**
   - 값이 있을 때와 없을 때 각각 처리
   - if-else 대체

6. **필터링 (filter)**
   - 조건을 만족하는 값만 처리
   - 함수형 프로그래밍 스타일

7. **기존 방식 vs Optional 방식 비교**
   - null 반환 방식과 Optional 방식 비교
   - 코드 가독성 및 안전성 비교

## 실행 결과 예시

```
=== Optional 실전 예제: MemberService ===

1. 기본 사용 (ifPresent):
-------------------
  프로그램 정상 종료

2. 기본값 제공 (orElse):
-------------------
  결과: 이름없음
  길이: 3

3. 예외 던지기 (orElseThrow):
-------------------
  오류: 회원이 없습니다

4. 값 변환 (map):
-------------------
  길이: 3

5. 조건부 실행 (ifPresentOrElse):
-------------------
  ID 1 조회:
    찾음: 홍길동
  ID 2 조회:
    없음

6. 필터링 (filter):
-------------------
  길이가 3 이상인 이름:
    홍길동
  길이가 10 이상인 이름:

7. 기존 방식 vs Optional 방식 비교:
-------------------
  기존 방식 (null 반환):
    이름이 없습니다
  Optional 방식:
    이름이 없습니다

프로그램 정상 종료
```

## 학습 포인트

1. **안전성**: NullPointerException 방지
2. **간결성**: null 체크 코드 불필요
3. **명확성**: 메서드 시그니처만 봐도 null 가능성 파악
4. **함수형**: 체이닝으로 깔끔한 코드 작성

## 참고 자료

- `Optional-실전-개선가이드.md` - 상세한 설명과 예제
- `Java-Optional-완전정복.md` - Optional 완전 정복 가이드
