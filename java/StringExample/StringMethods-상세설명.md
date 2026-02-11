# StringMethods.java 상세 설명

## 파일 개요

**파일명:** `StringMethods.java`

**목적:** String 클래스의 주요 메서드들을 실제 예제와 함께 설명하는 파일

**학습 내용:**
- 길이 및 빈 문자열 확인 메서드
- 문자열 검색 메서드
- 문자열 추출 메서드
- 문자열 변환 메서드
- 문자열 비교 메서드
- 타입 변환 메서드

---

## 코드 섹션별 상세 설명

### 1. 길이와 빈 문자열 확인

```java
System.out.println("=== 길이와 빈 문자열 확인 ===");
String str = "Hello World";
System.out.println("문자열: \"" + str + "\"");
System.out.println("길이: " + str.length());
System.out.println("빈 문자열인가? " + str.isEmpty());

String empty = "";
System.out.println("\"" + empty + "\"는 빈 문자열인가? " + empty.isEmpty());
```

#### 설명

**`length()` 메서드**
- **기능**: 문자열의 길이를 반환
- **반환 타입**: `int`
- **예제**: `"Hello World".length()` → `11`
- **주의**: 공백도 길이에 포함됨

**`isEmpty()` 메서드**
- **기능**: 문자열이 비어있는지 확인
- **반환 타입**: `boolean`
- **조건**: `length() == 0`일 때 `true`
- **예제**: 
  - `"".isEmpty()` → `true`
  - `"Hello".isEmpty()` → `false`
  - `"   ".isEmpty()` → `false` (공백은 길이가 있음)

**실행 결과:**
```
=== 길이와 빈 문자열 확인 ===
문자열: "Hello World"
길이: 11
빈 문자열인가? false
""는 빈 문자열인가? true
```

---

### 2. 문자열 검색

```java
System.out.println("\n=== 문자열 검색 ===");
String text = "Hello World";

// charAt(): 특정 위치의 문자
System.out.println("charAt(0): " + text.charAt(0));  // 'H'
System.out.println("charAt(6): " + text.charAt(6));   // 'W'

// indexOf(): 문자열이나 문자의 첫 번째 위치
System.out.println("indexOf('o'): " + text.indexOf('o'));        // 4
System.out.println("indexOf(\"World\"): " + text.indexOf("World"));  // 6
System.out.println("indexOf('x'): " + text.indexOf('x'));      // -1 (없음)

// lastIndexOf(): 마지막 위치
System.out.println("lastIndexOf('o'): " + text.lastIndexOf('o'));  // 7

// contains(): 포함 여부
System.out.println("contains(\"Hello\"): " + text.contains("Hello"));  // true
System.out.println("contains(\"Java\"): " + text.contains("Java"));    // false

// startsWith() / endsWith()
System.out.println("startsWith(\"Hello\"): " + text.startsWith("Hello"));  // true
System.out.println("endsWith(\"World\"): " + text.endsWith("World"));      // true
```

#### 설명

**`charAt(int index)` 메서드**
- **기능**: 지정된 인덱스 위치의 문자를 반환
- **매개변수**: `index` (0부터 시작)
- **반환 타입**: `char`
- **주의**: 
  - 인덱스 범위를 벗어나면 `StringIndexOutOfBoundsException` 발생
  - `0 <= index < length()` 범위 내에서만 사용 가능
- **예제**: 
  - `"Hello".charAt(0)` → `'H'`
  - `"Hello".charAt(4)` → `'o'`

**`indexOf()` 메서드**
- **기능**: 문자열이나 문자의 첫 번째 위치를 찾음
- **오버로딩**:
  - `indexOf(char ch)`: 문자 검색
  - `indexOf(String str)`: 문자열 검색
  - `indexOf(char ch, int fromIndex)`: 특정 위치부터 검색
- **반환 타입**: `int`
- **반환값**: 
  - 찾으면: 인덱스 위치 (0부터 시작)
  - 못 찾으면: `-1`
- **예제**: 
  - `"Hello".indexOf('l')` → `2` (첫 번째 'l'의 위치)
  - `"Hello".indexOf("lo")` → `3`
  - `"Hello".indexOf('x')` → `-1`

**`lastIndexOf()` 메서드**
- **기능**: 문자열이나 문자의 마지막 위치를 찾음
- **반환 타입**: `int`
- **예제**: 
  - `"Hello".lastIndexOf('l')` → `3` (마지막 'l'의 위치)
  - `"Hello World".lastIndexOf('o')` → `7`

**`contains(CharSequence s)` 메서드**
- **기능**: 문자열이 특정 문자열을 포함하는지 확인
- **매개변수**: `CharSequence` (String도 CharSequence의 구현체)
- **반환 타입**: `boolean`
- **예제**: 
  - `"Hello World".contains("Hello")` → `true`
  - `"Hello World".contains("Java")` → `false`

**`startsWith(String prefix)` 메서드**
- **기능**: 문자열이 특정 문자열로 시작하는지 확인
- **반환 타입**: `boolean`
- **오버로딩**: `startsWith(String prefix, int offset)` - 특정 위치부터 확인
- **예제**: 
  - `"Hello World".startsWith("Hello")` → `true`
  - `"Hello World".startsWith("World")` → `false`

**`endsWith(String suffix)` 메서드**
- **기능**: 문자열이 특정 문자열로 끝나는지 확인
- **반환 타입**: `boolean`
- **예제**: 
  - `"Hello World".endsWith("World")` → `true`
  - `"Hello World".endsWith("Hello")` → `false`

**실행 결과:**
```
=== 문자열 검색 ===
charAt(0): H
charAt(6): W
indexOf('o'): 4
indexOf("World"): 6
indexOf('x'): -1
lastIndexOf('o'): 7
contains("Hello"): true
contains("Java"): false
startsWith("Hello"): true
endsWith("World"): true
```

---

### 3. 문자열 추출

```java
System.out.println("\n=== 문자열 추출 ===");
String str2 = "Hello World";

// substring(): 부분 문자열 추출
String sub1 = str2.substring(0, 5);   // "Hello" (0부터 5 전까지)
String sub2 = str2.substring(6);       // "World" (6부터 끝까지)
System.out.println("substring(0, 5): " + sub1);
System.out.println("substring(6): " + sub2);

// split(): 문자열 분리
String date = "2024-01-15";
String[] dateParts = date.split("-");
System.out.println("날짜 분리: " + dateParts[0] + "년 " + dateParts[1] + "월 " + dateParts[2] + "일");

String fruits = "사과,바나나,오렌지";
String[] fruitArray = fruits.split(",");
System.out.print("과일 목록: ");
for (String fruit : fruitArray) {
    System.out.print(fruit + " ");
}
System.out.println();

// trim(): 앞뒤 공백 제거
String spaced = "  Hello World  ";
System.out.println("원본: [" + spaced + "]");
System.out.println("trim: [" + spaced.trim() + "]");
```

#### 설명

**`substring()` 메서드**
- **기능**: 문자열의 일부를 추출
- **오버로딩**:
  - `substring(int beginIndex)`: 시작 인덱스부터 끝까지
  - `substring(int beginIndex, int endIndex)`: 시작 인덱스부터 끝 인덱스 전까지
- **반환 타입**: `String`
- **주의**: 
  - `endIndex`는 포함되지 않음 (exclusive)
  - `beginIndex`는 포함됨 (inclusive)
  - 인덱스 범위를 벗어나면 `StringIndexOutOfBoundsException` 발생
- **예제**: 
  - `"Hello World".substring(0, 5)` → `"Hello"` (0,1,2,3,4 인덱스)
  - `"Hello World".substring(6)` → `"World"` (6부터 끝까지)

**`split(String regex)` 메서드**
- **기능**: 정규표현식을 기준으로 문자열을 분리하여 배열로 반환
- **매개변수**: `regex` (정규표현식)
- **반환 타입**: `String[]`
- **주의**: 
  - 정규표현식 특수문자는 이스케이프 필요 (예: `.` → `\\.`)
  - 빈 문자열도 배열 요소로 포함될 수 있음
- **예제**: 
  - `"2024-01-15".split("-")` → `["2024", "01", "15"]`
  - `"사과,바나나,오렌지".split(",")` → `["사과", "바나나", "오렌지"]`
  - `"a.b.c".split("\\.")` → `["a", "b", "c"]` (점은 이스케이프 필요)

**`trim()` 메서드**
- **기능**: 문자열 앞뒤의 공백(whitespace)을 제거
- **반환 타입**: `String`
- **주의**: 
  - 중간 공백은 제거하지 않음
  - 유니코드 공백도 제거 (Java 11+에서는 `strip()` 권장)
- **예제**: 
  - `"  Hello World  ".trim()` → `"Hello World"`
  - `"  Hello  World  ".trim()` → `"Hello  World"` (중간 공백 유지)

**실행 결과:**
```
=== 문자열 추출 ===
substring(0, 5): Hello
substring(6): World
날짜 분리: 2024년 01월 15일
과일 목록: 사과 바나나 오렌지 
원본: [  Hello World  ]
trim: [Hello World]
```

---

### 4. 문자열 변환

```java
System.out.println("\n=== 문자열 변환 ===");
String str3 = "Hello World";

// toUpperCase() / toLowerCase()
System.out.println("toUpperCase(): " + str3.toUpperCase());  // "HELLO WORLD"
System.out.println("toLowerCase(): " + str3.toLowerCase());  // "hello world"

// replace(): 문자열 치환
String replaced = str3.replace("World", "Java");
System.out.println("replace(\"World\", \"Java\"): " + replaced);  // "Hello Java"

String replaced2 = str3.replace('o', 'O');
System.out.println("replace('o', 'O'): " + replaced2);  // "HellO WOrld"
```

#### 설명

**`toUpperCase()` 메서드**
- **기능**: 문자열의 모든 문자를 대문자로 변환
- **반환 타입**: `String`
- **주의**: 
  - 원본 문자열은 변경되지 않음 (불변성)
  - 새로운 String 객체 반환
- **예제**: 
  - `"Hello".toUpperCase()` → `"HELLO"`
  - `"Hello World".toUpperCase()` → `"HELLO WORLD"`

**`toLowerCase()` 메서드**
- **기능**: 문자열의 모든 문자를 소문자로 변환
- **반환 타입**: `String`
- **주의**: 원본 문자열은 변경되지 않음
- **예제**: 
  - `"HELLO".toLowerCase()` → `"hello"`
  - `"Hello World".toLowerCase()` → `"hello world"`

**`replace()` 메서드**
- **기능**: 문자열이나 문자를 다른 문자열이나 문자로 치환
- **오버로딩**:
  - `replace(char oldChar, char newChar)`: 문자 치환
  - `replace(CharSequence target, CharSequence replacement)`: 문자열 치환
- **반환 타입**: `String`
- **주의**: 
  - 모든 일치 항목을 치환 (전역 치환)
  - 원본 문자열은 변경되지 않음
  - 정규표현식 사용 불가 (정규표현식은 `replaceAll()` 사용)
- **예제**: 
  - `"Hello World".replace("World", "Java")` → `"Hello Java"`
  - `"Hello".replace('l', 'L')` → `"HeLLo"` (모든 'l'이 'L'로 변경)

**실행 결과:**
```
=== 문자열 변환 ===
toUpperCase(): HELLO WORLD
toLowerCase(): hello world
replace("World", "Java"): Hello Java
replace('o', 'O'): HellO WOrld
```

---

### 5. 문자열 비교

```java
System.out.println("\n=== 문자열 비교 ===");
String str4 = "apple";
String str5 = "banana";
String str6 = "Apple";

// compareTo(): 사전식 비교
System.out.println("compareTo(\"banana\"): " + str4.compareTo(str5));  // 음수
System.out.println("compareTo(\"apple\"): " + str4.compareTo("apple"));  // 0

// compareToIgnoreCase(): 대소문자 무시 비교
System.out.println("compareToIgnoreCase(\"Apple\"): " + str4.compareToIgnoreCase(str6));  // 0

// equalsIgnoreCase(): 대소문자 무시 비교
System.out.println("equalsIgnoreCase(\"Apple\"): " + str4.equalsIgnoreCase(str6));  // true
```

#### 설명

**`compareTo(String anotherString)` 메서드**
- **기능**: 사전식(lexicographic) 순서로 두 문자열을 비교
- **반환 타입**: `int`
- **반환값**:
  - 음수: 현재 문자열이 비교 문자열보다 앞섬
  - 0: 두 문자열이 같음
  - 양수: 현재 문자열이 비교 문자열보다 뒤섬
- **비교 기준**: 
  - 유니코드 값으로 비교
  - 대소문자 구분
- **예제**: 
  - `"apple".compareTo("banana")` → 음수 (a < b)
  - `"apple".compareTo("apple")` → 0 (같음)
  - `"banana".compareTo("apple")` → 양수 (b > a)
  - `"Apple".compareTo("apple")` → 음수 (대문자 A < 소문자 a)

**`compareToIgnoreCase(String str)` 메서드**
- **기능**: 대소문자를 무시하고 사전식 순서로 비교
- **반환 타입**: `int`
- **반환값**: `compareTo()`와 동일 (음수, 0, 양수)
- **예제**: 
  - `"apple".compareToIgnoreCase("Apple")` → 0 (대소문자 무시하면 같음)

**`equalsIgnoreCase(String anotherString)` 메서드**
- **기능**: 대소문자를 무시하고 내용이 같은지 확인
- **반환 타입**: `boolean`
- **예제**: 
  - `"Hello".equalsIgnoreCase("hello")` → `true`
  - `"Hello".equalsIgnoreCase("HELLO")` → `true`
  - `"Hello".equalsIgnoreCase("World")` → `false`

**비교 메서드 정리:**

| 메서드 | 용도 | 반환 타입 | 대소문자 구분 |
|--------|------|-----------|--------------|
| `equals()` | 내용이 같은지 확인 | `boolean` | 구분 |
| `equalsIgnoreCase()` | 내용이 같은지 확인 | `boolean` | 무시 |
| `compareTo()` | 사전식 순서 비교 | `int` | 구분 |
| `compareToIgnoreCase()` | 사전식 순서 비교 | `int` | 무시 |

**실행 결과:**
```
=== 문자열 비교 ===
compareTo("banana"): -1
compareTo("apple"): 0
compareToIgnoreCase("Apple"): 0
equalsIgnoreCase("Apple"): true
```

---

### 6. 다른 타입으로 변환

```java
System.out.println("\n=== 다른 타입으로 변환 ===");
// valueOf(): 다른 타입을 String으로
int num = 123;
String numStr = String.valueOf(num);
System.out.println("int 123 → String: " + numStr);

double d = 3.14;
String dStr = String.valueOf(d);
System.out.println("double 3.14 → String: " + dStr);

boolean b = true;
String bStr = String.valueOf(b);
System.out.println("boolean true → String: " + bStr);
```

#### 설명

**`String.valueOf()` 메서드**
- **기능**: 다양한 타입의 값을 String으로 변환
- **오버로딩**:
  - `valueOf(boolean b)`
  - `valueOf(char c)`
  - `valueOf(char[] data)`
  - `valueOf(double d)`
  - `valueOf(float f)`
  - `valueOf(int i)`
  - `valueOf(long l)`
  - `valueOf(Object obj)`
- **반환 타입**: `String`
- **주의**: 
  - `null`을 전달하면 `"null"` 문자열 반환
  - `Object`의 경우 `toString()` 메서드 호출
- **예제**: 
  - `String.valueOf(123)` → `"123"`
  - `String.valueOf(3.14)` → `"3.14"`
  - `String.valueOf(true)` → `"true"`
  - `String.valueOf(null)` → `"null"`

**다른 변환 방법:**
```java
// valueOf() 대신 사용 가능
int num = 123;
String str1 = String.valueOf(num);  // 방법 1
String str2 = Integer.toString(num); // 방법 2
String str3 = "" + num;              // 방법 3 (문자열 연결)
```

**실행 결과:**
```
=== 다른 타입으로 변환 ===
int 123 → String: 123
double 3.14 → String: 3.14
boolean true → String: true
```

---

## 전체 실행 결과

```
=== 길이와 빈 문자열 확인 ===
문자열: "Hello World"
길이: 11
빈 문자열인가? false
""는 빈 문자열인가? true

=== 문자열 검색 ===
charAt(0): H
charAt(6): W
indexOf('o'): 4
indexOf("World"): 6
indexOf('x'): -1
lastIndexOf('o'): 7
contains("Hello"): true
contains("Java"): false
startsWith("Hello"): true
endsWith("World"): true

=== 문자열 추출 ===
substring(0, 5): Hello
substring(6): World
날짜 분리: 2024년 01월 15일
과일 목록: 사과 바나나 오렌지 
원본: [  Hello World  ]
trim: [Hello World]

=== 문자열 변환 ===
toUpperCase(): HELLO WORLD
toLowerCase(): hello world
replace("World", "Java"): Hello Java
replace('o', 'O'): HellO WOrld

=== 문자열 비교 ===
compareTo("banana"): -1
compareTo("apple"): 0
compareToIgnoreCase("Apple"): 0
equalsIgnoreCase("Apple"): true

=== 다른 타입으로 변환 ===
int 123 → String: 123
double 3.14 → String: 3.14
boolean true → String: true
```

---

## 학습 포인트 요약

### 1. 메서드 분류

**길이 및 확인:**
- `length()`: 길이 반환
- `isEmpty()`: 빈 문자열 확인

**검색:**
- `charAt()`: 특정 위치 문자
- `indexOf()` / `lastIndexOf()`: 위치 찾기
- `contains()`: 포함 여부
- `startsWith()` / `endsWith()`: 시작/끝 확인

**추출:**
- `substring()`: 부분 문자열
- `split()`: 문자열 분리
- `trim()`: 공백 제거

**변환:**
- `toUpperCase()` / `toLowerCase()`: 대소문자 변환
- `replace()`: 문자열 치환

**비교:**
- `compareTo()` / `compareToIgnoreCase()`: 사전식 비교
- `equalsIgnoreCase()`: 대소문자 무시 비교

**타입 변환:**
- `valueOf()`: 다른 타입을 String으로

### 2. 주의사항

1. **인덱스 범위**: `charAt()`, `substring()` 사용 시 인덱스 범위 확인
2. **불변성**: 모든 메서드는 원본을 변경하지 않고 새 객체 반환
3. **정규표현식**: `split()` 사용 시 특수문자 이스케이프 필요
4. **null 체크**: `null` 문자열에 메서드 호출 시 `NullPointerException` 발생

### 3. 실전 활용 팁

- **문자열 검증**: `isEmpty()`, `contains()`, `startsWith()`, `endsWith()`
- **데이터 파싱**: `split()`, `substring()` 조합
- **데이터 정제**: `trim()`, `replace()` 사용
- **대소문자 처리**: `toUpperCase()`, `toLowerCase()`, `equalsIgnoreCase()`
- **타입 변환**: `valueOf()` 또는 `toString()` 사용

---

**작성일:** 2026-01-30  
**파일:** StringMethods.java 상세 설명
