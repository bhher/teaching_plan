# 7장 예제 파일 실행 방법

## 명령줄에서 실행하기

### 1. ClassAndObject.java
```bash
javac ClassAndObject.java
java ClassAndObject
```

### 2. ConstructorExample.java
```bash
javac ConstructorExample.java
java ConstructorExample
```

### 3. ThisKeyword.java
```bash
javac ThisKeyword.java
java ThisKeyword
```

### 4. AccessModifier.java
```bash
javac AccessModifier.java
java AccessModifier
```

### 5. Encapsulation.java
```bash
javac Encapsulation.java
java Encapsulation
```

### 6. BankAccount.java
```bash
javac BankAccount.java
java BankAccount
```

### 7. Car.java
```bash
javac Car.java
java Car
```

### 8. OOPComprehensive.java
```bash
javac OOPComprehensive.java
java OOPComprehensive
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 주의사항

- 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- Java는 대소문자를 구분합니다
- 각 파일은 하나의 public 클래스를 포함해야 합니다
- 생성자는 클래스명과 동일한 이름을 가집니다
- this는 현재 객체를 가리키는 참조 변수입니다
- private 필드는 같은 클래스 내에서만 접근 가능합니다

## 학습 포인트

- **클래스와 객체**: 클래스는 설계도, 객체는 실제 인스턴스
- **객체 생성**: `new` 키워드로 객체 생성
- **생성자**: 객체 초기화를 담당하는 특별한 메서드
- **this 키워드**: 현재 객체를 가리키는 참조 변수
- **접근제어자**: public, private, default, protected
- **캡슐화**: private 필드와 getter/setter를 통한 데이터 보호

## 객체지향 프로그래밍 원칙

1. **캡슐화**: 데이터와 메서드를 하나로 묶어 관리
2. **정보 은닉**: private 필드로 외부 접근 제한
3. **유효성 검사**: setter에서 데이터 검증
4. **코드 재사용**: 클래스를 재사용하여 여러 객체 생성

