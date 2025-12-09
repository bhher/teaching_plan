# 8장 예제 파일 실행 방법

## 명령줄에서 실행하기

### 1. EncapsulationExample.java
```bash
javac EncapsulationExample.java
java EncapsulationExample
```

### 2. InheritanceExample.java
```bash
javac InheritanceExample.java
java InheritanceExample
```

### 3. PolymorphismExample.java
```bash
javac PolymorphismExample.java
java PolymorphismExample
```

### 4. AbstractClassExample.java
```bash
javac AbstractClassExample.java
java AbstractClassExample
```

### 5. InterfaceExample.java
```bash
javac InterfaceExample.java
java InterfaceExample
```

### 6. ShapeExample.java
```bash
javac ShapeExample.java
java ShapeExample
```

### 7. OOPConceptsComprehensive.java
```bash
javac OOPConceptsComprehensive.java
java OOPConceptsComprehensive
```

## VS Code에서 실행하기

1. 각 `.java` 파일을 열기
2. `main` 메서드 위에 나타나는 "Run" 버튼 클릭
3. 또는 `F5` 키를 눌러 실행

## 주의사항

- 클래스 이름과 파일 이름이 반드시 일치해야 합니다
- Java는 대소문자를 구분합니다
- 각 파일은 하나의 public 클래스를 포함해야 합니다
- 추상 클래스는 직접 객체를 생성할 수 없습니다
- 인터페이스는 직접 객체를 생성할 수 없습니다
- 추상 메서드를 포함한 클래스는 반드시 abstract로 선언해야 합니다
- 인터페이스를 구현하는 클래스는 모든 추상 메서드를 구현해야 합니다

## 학습 포인트

- **캡슐화**: private 필드와 getter/setter를 통한 데이터 보호
- **상속**: extends 키워드로 부모 클래스의 속성과 메서드 상속
- **다형성**: 하나의 타입으로 여러 객체를 처리하는 능력
- **추상 클래스**: abstract 키워드로 완전하지 않은 클래스 정의
- **인터페이스**: interface 키워드로 구현해야 할 메서드 목록 정의

## 객체지향 프로그래밍의 4대 핵심 개념

1. **캡슐화 (Encapsulation)**: 데이터와 메서드를 하나로 묶어 관리
2. **상속 (Inheritance)**: 기존 클래스를 확장하여 새로운 클래스 생성
3. **다형성 (Polymorphism)**: 하나의 인터페이스로 여러 형태 구현
4. **추상화 (Abstraction)**: 복잡한 것을 단순하게 표현

## 주요 키워드

- `extends`: 클래스 상속
- `implements`: 인터페이스 구현
- `abstract`: 추상 클래스/메서드
- `interface`: 인터페이스
- `super`: 부모 클래스 참조
- `@Override`: 메서드 오버라이딩 표시
- `instanceof`: 타입 확인

