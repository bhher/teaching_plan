# Java String 완전 정복

## 목차

1. [String이란?](#string이란)
2. [String 생성 방법](#string-생성-방법)
3. [String의 불변성 (Immutability)](#string의-불변성-immutability)
4. [String 비교 (== vs equals)](#string-비교--vs-equals)
5. [String 주요 메서드](#string-주요-메서드)
6. [String 연결 (Concatenation)](#string-연결-concatenation)
7. [StringBuffer와 StringBuilder](#stringbuffer와-stringbuilder)
8. [실전 예제](#실전-예제)
9. [주의사항 및 모범 사례](#주의사항-및-모범-사례)

---

## String이란?

### 정의

`String`은 Java에서 **문자열을 다루는 클래스**입니다. Java에서 가장 많이 사용하는 참조 자료형 중 하나입니다.

### 특징

1. **참조 자료형**: 기본 자료형이 아닌 클래스
2. **불변성 (Immutable)**: 한 번 생성되면 변경할 수 없음
3. **문자열 풀 (String Pool)**: 동일한 문자열 리터럴은 재사용
4. **final 클래스**: 상속 불가능

### 기본 사용법

```java
String name = "홍길동";
String message = "안녕하세요";
System.out.println(name + "님, " + message);
```

---

## String 생성 방법

### 방법 1: 문자열 리터럴 (가장 일반적)

```java
String str1 = "Hello";
String str2 = "Hello";  // str1과 같은 객체 참조
```

**특징:**
- 문자열 풀(String Pool)에 저장
- 동일한 문자열은 재사용
- 메모리 효율적

### 방법 2: new 연산자 사용

```java
String str3 = new String("Hello");
String str4 = new String("Hello");  // str3와 다른 객체
```

**특징:**
- 힙 메모리에 새 객체 생성
- 동일한 문자열이라도 다른 객체
- 메모리 사용량 증가

### 방법 3: char 배열로부터 생성

```java
char[] chars = {'H', 'e', 'l', 'l', 'o'};
String str5 = new String(chars);
```

### 방법 4: 다른 String으로부터 생성

```java
String original = "Hello";
String copy = new String(original);
```

### 방법 5: StringBuilder/StringBuffer로부터

```java
StringBuilder sb = new StringBuilder("Hello");
String str6 = sb.toString();
```

### 비교 예제

```java
String str1 = "Hello";
String str2 = "Hello";
String str3 = new String("Hello");
String str4 = new String("Hello");

System.out.println(str1 == str2);  // true (같은 객체)
System.out.println(str1 == str3);   // false (다른 객체)
System.out.println(str3 == str4);   // false (다른 객체)
```

---

## String의 불변성 (Immutability)

### 불변성이란?

String 객체는 **한 번 생성되면 그 내용을 변경할 수 없습니다**. 메서드를 호출해도 원본은 그대로이고, 새로운 String 객체가 생성됩니다.

### 예제

```java
String original = "Hello";
System.out.println("원본: " + original);

String modified = original.toUpperCase();  // 새로운 String 객체 생성
System.out.println("변경 후: " + modified);  // "HELLO"
System.out.println("원본은 여전히: " + original);  // "Hello" (변경되지 않음)
```

### 불변성의 장점

1. **안전성**: 멀티스레드 환경에서 안전
2. **캐싱**: 문자열 풀에서 재사용 가능
3. **해시코드**: 해시코드가 변하지 않아 HashMap 등에서 안전

### 불변성의 단점

1. **성능**: 문자열 변경 시마다 새 객체 생성
2. **메모리**: 많은 문자열 조작 시 메모리 사용량 증가

**해결책**: `StringBuilder` 또는 `StringBuffer` 사용

---

## String 비교 (== vs equals)

### == 연산자 (참조 비교)

```java
String str1 = "Hello";
String str2 = "Hello";
String str3 = new String("Hello");

System.out.println(str1 == str2);  // true (같은 객체 참조)
System.out.println(str1 == str3);  // false (다른 객체)
```

**설명:**
- `==`는 **객체의 참조(주소)**를 비교
- 같은 객체를 가리키면 `true`, 다르면 `false`

### equals() 메서드 (내용 비교)

```java
String str1 = "Hello";
String str2 = "Hello";
String str3 = new String("Hello");

System.out.println(str1.equals(str2));  // true (내용이 같음)
System.out.println(str1.equals(str3));   // true (내용이 같음)
```

**설명:**
- `equals()`는 **문자열의 내용**을 비교
- 내용이 같으면 `true`, 다르면 `false`

### equalsIgnoreCase() 메서드

```java
String str1 = "Hello";
String str2 = "HELLO";

System.out.println(str1.equals(str2));           // false
System.out.println(str1.equalsIgnoreCase(str2)); // true (대소문자 무시)
```

### ⚠️ 중요: 문자열 비교는 항상 equals() 사용!

```java
// ❌ 잘못된 방법
if (str1 == str2) {  // 참조 비교 - 예상과 다를 수 있음
    // ...
}

// ✅ 올바른 방법
if (str1.equals(str2)) {  // 내용 비교 - 권장
    // ...
}
```

---

## String 주요 메서드

### 1. 길이와 빈 문자열 확인

#### length() - 문자열 길이

```java
String str = "Hello";
int len = str.length();  // 5
```

#### isEmpty() - 빈 문자열 확인

```java
String empty = "";
String notEmpty = "Hello";

System.out.println(empty.isEmpty());      // true
System.out.println(notEmpty.isEmpty());  // false
```

#### isBlank() - 공백만 있는지 확인 (Java 11+)

```java
String blank = "   ";
String notBlank = "Hello";

System.out.println(blank.isBlank());      // true
System.out.println(notBlank.isBlank());   // false
```

### 2. 문자열 검색

#### charAt() - 특정 위치의 문자

```java
String str = "Hello";
char ch = str.charAt(0);  // 'H'
char ch2 = str.charAt(4); // 'o'
```

**주의**: 인덱스 범위를 벗어나면 `StringIndexOutOfBoundsException` 발생

#### indexOf() - 문자열이나 문자의 첫 번째 위치

```java
String str = "Hello World";

int index1 = str.indexOf('o');        // 4
int index2 = str.indexOf("World");    // 6
int index3 = str.indexOf('x');        // -1 (없음)
int index4 = str.indexOf('o', 5);     // 7 (5번 인덱스부터 검색)
```

#### lastIndexOf() - 마지막 위치

```java
String str = "Hello World";
int index = str.lastIndexOf('o');  // 7
```

#### contains() - 포함 여부

```java
String str = "Hello World";
boolean result1 = str.contains("Hello");  // true
boolean result2 = str.contains("Java");    // false
```

#### startsWith() / endsWith() - 시작/끝 확인

```java
String str = "Hello World";
boolean starts = str.startsWith("Hello");  // true
boolean ends = str.endsWith("World");      // true
```

### 3. 문자열 추출

#### substring() - 부분 문자열 추출

```java
String str = "Hello World";

String sub1 = str.substring(0, 5);   // "Hello" (0부터 5 전까지)
String sub2 = str.substring(6);       // "World" (6부터 끝까지)
```

**주의**: `substring(start, end)`에서 `end`는 포함되지 않음

#### split() - 문자열 분리

```java
String date = "2024-01-15";
String[] parts = date.split("-");  // ["2024", "01", "15"]

String text = "사과,바나나,오렌지";
String[] fruits = text.split(",");  // ["사과", "바나나", "오렌지"]
```

#### trim() - 앞뒤 공백 제거

```java
String str = "  Hello World  ";
String trimmed = str.trim();  // "Hello World"
```

#### strip() - 앞뒤 공백 제거 (Java 11+, 유니코드 공백도 제거)

```java
String str = "  Hello World  ";
String stripped = str.strip();  // "Hello World"
```

### 4. 문자열 변환

#### toUpperCase() / toLowerCase() - 대소문자 변환

```java
String str = "Hello World";
String upper = str.toUpperCase();  // "HELLO WORLD"
String lower = str.toLowerCase();  // "hello world"
```

#### replace() - 문자열 치환

```java
String str = "Hello World";
String replaced = str.replace("World", "Java");  // "Hello Java"
String replaced2 = str.replace('o', 'O');        // "HellO WOrld"
```

#### replaceAll() / replaceFirst() - 정규표현식 치환

```java
String str = "Hello123World456";
String replaced = str.replaceAll("\\d", "");  // "HelloWorld" (숫자 제거)
```

### 5. 문자열 변환 (다른 타입으로)

#### toCharArray() - char 배열로 변환

```java
String str = "Hello";
char[] chars = str.toCharArray();  // ['H', 'e', 'l', 'l', 'o']
```

#### valueOf() - 다른 타입을 String으로 변환

```java
int num = 123;
String str = String.valueOf(num);  // "123"

double d = 3.14;
String str2 = String.valueOf(d);   // "3.14"

boolean b = true;
String str3 = String.valueOf(b);   // "true"
```

### 6. 문자열 비교

#### compareTo() - 사전식 비교

```java
String str1 = "apple";
String str2 = "banana";

int result = str1.compareTo(str2);  // 음수 (str1이 앞섬)
int result2 = str2.compareTo(str1); // 양수 (str2가 뒤섬)
int result3 = str1.compareTo("apple"); // 0 (같음)
```

#### compareToIgnoreCase() - 대소문자 무시 비교

```java
String str1 = "Apple";
String str2 = "apple";
int result = str1.compareToIgnoreCase(str2);  // 0 (같음)
```

---

## String 연결 (Concatenation)

### + 연산자 사용

```java
String firstName = "홍";
String lastName = "길동";
String fullName = firstName + " " + lastName;  // "홍 길동"
```

### += 연산자 사용

```java
String message = "Hello";
message += " World";  // "Hello World"
```

**주의**: 내부적으로 새로운 String 객체가 생성됨 (성능 이슈 가능)

### concat() 메서드

```java
String str1 = "Hello";
String str2 = str1.concat(" World");  // "Hello World"
```

### 여러 문자열 연결

```java
String result = "안녕하세요, " + firstName + lastName + "님!";
```

### StringBuilder 사용 (권장 - 많은 연결 시)

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" ");
sb.append("World");
String result = sb.toString();  // "Hello World"
```

---

## StringBuffer와 StringBuilder

### 왜 필요한가?

String은 불변성이므로 많은 문자열 조작 시 성능 문제가 발생합니다. 이때 `StringBuffer`나 `StringBuilder`를 사용합니다.

### StringBuffer vs StringBuilder

| 특징 | StringBuffer | StringBuilder |
|------|-------------|---------------|
| 동기화 | 지원 (Thread-safe) | 미지원 (빠름) |
| 성능 | 느림 | 빠름 |
| 사용 시기 | 멀티스레드 환경 | 단일스레드 환경 |

### StringBuffer 사용 예제

```java
StringBuffer sb = new StringBuffer();
sb.append("Hello");
sb.append(" ");
sb.append("World");
String result = sb.toString();  // "Hello World"
```

### StringBuilder 사용 예제

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" ");
sb.append("World");
String result = sb.toString();  // "Hello World"
```

### 주요 메서드

```java
StringBuilder sb = new StringBuilder("Hello");

sb.append(" World");        // "Hello World"
sb.insert(5, ",");          // "Hello, World"
sb.delete(5, 6);           // "Hello World"
sb.replace(0, 5, "Hi");     // "Hi World"
sb.reverse();               // "dlroW iH"
sb.length();                // 길이
sb.capacity();              // 용량
```

### 언제 사용하나?

**String 사용:**
- 문자열이 자주 변경되지 않을 때
- 간단한 문자열 조작

**StringBuilder/StringBuffer 사용:**
- 반복문에서 문자열을 여러 번 연결할 때
- 많은 문자열 조작이 필요할 때

**예제:**
```java
// ❌ 비효율적 (매번 새 객체 생성)
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i;  // 매번 새 String 객체 생성
}

// ✅ 효율적
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);  // 같은 객체에 추가
}
String result = sb.toString();
```

---

## 실전 예제

### 예제 1: 기본 String 사용

```java
public class StringExample1 {
    public static void main(String[] args) {
        // String 생성
        String str1 = "Hello";
        String str2 = new String("World");
        
        // 문자열 연결
        String result = str1 + " " + str2;
        System.out.println(result);  // "Hello World"
        
        // 길이 확인
        System.out.println("길이: " + result.length());  // 11
        
        // 대소문자 변환
        System.out.println(result.toUpperCase());  // "HELLO WORLD"
        System.out.println(result.toLowerCase());  // "hello world"
    }
}
```

### 예제 2: 문자열 검색 및 추출

```java
public class StringExample2 {
    public static void main(String[] args) {
        String text = "Hello World Java";
        
        // 검색
        System.out.println("'o'의 위치: " + text.indexOf('o'));  // 4
        System.out.println("'World' 포함: " + text.contains("World"));  // true
        
        // 추출
        System.out.println("0~5: " + text.substring(0, 5));  // "Hello"
        System.out.println("6부터: " + text.substring(6));    // "World Java"
        
        // 분리
        String[] words = text.split(" ");
        for (String word : words) {
            System.out.println(word);
        }
        // 출력:
        // Hello
        // World
        // Java
    }
}
```

### 예제 3: 문자열 비교

```java
public class StringExample3 {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        
        // 참조 비교
        System.out.println("str1 == str2: " + (str1 == str2));      // true
        System.out.println("str1 == str3: " + (str1 == str3));      // false
        
        // 내용 비교
        System.out.println("str1.equals(str2): " + str1.equals(str2));  // true
        System.out.println("str1.equals(str3): " + str1.equals(str3));    // true
    }
}
```

### 예제 4: StringBuilder 사용

```java
public class StringExample4 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        
        // 문자열 추가
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        
        System.out.println(sb.toString());  // "Hello World"
        
        // 삽입
        sb.insert(5, ",");
        System.out.println(sb.toString());  // "Hello, World"
        
        // 삭제
        sb.delete(5, 6);
        System.out.println(sb.toString());  // "Hello World"
        
        // 치환
        sb.replace(0, 5, "Hi");
        System.out.println(sb.toString());  // "Hi World"
    }
}
```

### 예제 5: 실용적인 문자열 처리

```java
public class StringExample5 {
    public static void main(String[] args) {
        // 이메일 검증
        String email = "user@example.com";
        boolean isValid = email.contains("@") && email.contains(".");
        System.out.println("이메일 유효: " + isValid);
        
        // 파일 확장자 추출
        String filename = "document.pdf";
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        System.out.println("확장자: " + extension);  // "pdf"
        
        // 공백 제거
        String text = "  Hello World  ";
        System.out.println("원본: [" + text + "]");
        System.out.println("trim: [" + text.trim() + "]");
        
        // 문자열 반복 (Java 11+)
        String repeated = "Hello ".repeat(3);
        System.out.println(repeated);  // "Hello Hello Hello "
    }
}
```

---

## 주의사항 및 모범 사례

### 1. null 체크

```java
// ❌ 위험
String str = null;
int len = str.length();  // NullPointerException 발생!

// ✅ 안전
String str = null;
if (str != null) {
    int len = str.length();
}

// ✅ 더 안전 (Java 7+)
if (str != null && !str.isEmpty()) {
    // 처리
}
```

### 2. 문자열 비교는 equals() 사용

```java
// ❌ 잘못된 방법
if (str1 == str2) {  // 참조 비교
    // ...
}

// ✅ 올바른 방법
if (str1 != null && str1.equals(str2)) {  // 내용 비교
    // ...
}

// ✅ 더 간단 (Java 7+)
if (Objects.equals(str1, str2)) {  // null 안전
    // ...
}
```

### 3. 많은 문자열 연결 시 StringBuilder 사용

```java
// ❌ 비효율적
String result = "";
for (int i = 0; i < 1000; i++) {
    result += i;  // 매번 새 객체 생성
}

// ✅ 효율적
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);
}
String result = sb.toString();
```

### 4. 문자열 풀 활용

```java
// ✅ 권장 (문자열 풀 사용)
String str1 = "Hello";
String str2 = "Hello";  // 같은 객체 재사용

// ❌ 비권장 (새 객체 생성)
String str3 = new String("Hello");
String str4 = new String("Hello");  // 다른 객체
```

### 5. 인덱스 범위 확인

```java
String str = "Hello";

// ❌ 위험
char ch = str.charAt(10);  // StringIndexOutOfBoundsException

// ✅ 안전
if (str.length() > 10) {
    char ch = str.charAt(10);
}
```

### 6. 정규표현식 사용 시 주의

```java
String text = "Hello.World";

// ❌ 잘못된 사용
String[] parts = text.split(".");  // 모든 문자로 분리됨

// ✅ 올바른 사용
String[] parts = text.split("\\.");  // 점(.)으로 분리
```

---

## 체크리스트

### 기본 개념
- [ ] String이 참조 자료형임을 이해
- [ ] String의 불변성을 이해
- [ ] 문자열 풀의 개념 이해

### 생성 방법
- [ ] 문자열 리터럴로 생성
- [ ] new 연산자로 생성
- [ ] char 배열로부터 생성

### 비교
- [ ] == 연산자와 equals()의 차이 이해
- [ ] equals() 메서드 사용법
- [ ] equalsIgnoreCase() 사용법

### 주요 메서드
- [ ] length(), isEmpty(), isBlank()
- [ ] charAt(), indexOf(), lastIndexOf()
- [ ] substring(), split(), trim()
- [ ] toUpperCase(), toLowerCase()
- [ ] replace(), replaceAll()

### 문자열 연결
- [ ] + 연산자 사용
- [ ] concat() 메서드 사용
- [ ] StringBuilder/StringBuffer 사용

### 실전 활용
- [ ] null 체크 방법
- [ ] 많은 문자열 연결 시 StringBuilder 사용
- [ ] 인덱스 범위 확인

---

## 요약

### 핵심 포인트

1. **String은 불변 객체**: 한 번 생성되면 변경 불가
2. **문자열 비교는 equals()**: == 대신 equals() 사용
3. **문자열 풀 활용**: 리터럴 방식이 메모리 효율적
4. **많은 연결 시 StringBuilder**: 성능 향상
5. **null 체크 필수**: NullPointerException 방지

### 자주 사용하는 메서드

| 메서드 | 설명 | 예제 |
|--------|------|------|
| `length()` | 길이 | `str.length()` |
| `charAt()` | 문자 추출 | `str.charAt(0)` |
| `indexOf()` | 위치 찾기 | `str.indexOf("Hello")` |
| `substring()` | 부분 문자열 | `str.substring(0, 5)` |
| `split()` | 문자열 분리 | `str.split(",")` |
| `trim()` | 공백 제거 | `str.trim()` |
| `toUpperCase()` | 대문자 변환 | `str.toUpperCase()` |
| `toLowerCase()` | 소문자 변환 | `str.toLowerCase()` |
| `replace()` | 문자열 치환 | `str.replace("old", "new")` |
| `equals()` | 내용 비교 | `str1.equals(str2)` |

---

**작성일:** 2026-01-30  
**범위:** Java String 클래스 완전 정복
