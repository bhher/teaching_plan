# HashMap을 활용한 영화 평점 관리 예제

---

## 목차

1. [기본 예제: 영화 평점 출력](#1-기본-예제-영화-평점-출력)
2. [영화 평점 조회](#2-영화-평점-조회)
3. [영화 추가 및 평점 수정](#3-영화-추가-및-평점-수정)
4. [평점대별 영화 찾기](#4-평점대별-영화-찾기)
5. [종합 예제: 영화 추천 시스템](#5-종합-예제-영화-추천-시스템)

---

## 1. 기본 예제: 영화 평점 출력

### 1-1. 문제 설명

영화명과 평점을 HashMap에 저장하고, 모든 영화를 출력하는 프로그램을 작성하세요.

### 1-2. 코드

```java
import java.util.HashMap;
import java.util.Map;

public class MovieRating {
    public static void main(String[] args) {
        // HashMap 생성 (영화명을 키, 평점을 값으로 저장)
        HashMap<String, Double> movies = new HashMap<>();
        
        // 영화 정보 추가
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        movies.put("겨울왕국", 8.7);
        movies.put("토이스토리", 9.1);
        
        // 모든 영화 출력
        System.out.println("=== 영화 평점 ===");
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            String movieName = entry.getKey();
            double rating = entry.getValue();
            System.out.println(movieName + ": " + rating + "점");
        }
    }
}
```

### 1-3. 실행 결과

```
=== 영화 평점 ===
토이스토리: 9.1점
겨울왕국: 8.7점
기생충: 9.0점
인터스텔라: 9.2점
어벤져스: 8.5점
```

---

## 2. 영화 평점 조회

### 2-1. 문제 설명

사용자가 입력한 영화의 평점을 조회하는 프로그램을 작성하세요.

### 2-2. 코드

```java
import java.util.HashMap;
import java.util.Scanner;

public class MovieRatingLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 영화 목록 초기화
        HashMap<String, Double> movies = new HashMap<>();
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        movies.put("겨울왕국", 8.7);
        movies.put("토이스토리", 9.1);
        movies.put("라라랜드", 8.8);
        
        // 영화 평점 조회
        System.out.print("조회할 영화명을 입력하세요: ");
        String movieName = scanner.nextLine();
        
        if (movies.containsKey(movieName)) {
            double rating = movies.get(movieName);
            System.out.println(movieName + "의 평점은 " + rating + "점입니다.");
        } else {
            System.out.println("해당 영화가 없습니다.");
        }
        
        scanner.close();
    }
}
```

### 2-3. 실행 결과

```
조회할 영화명을 입력하세요: 기생충
기생충의 평점은 9.0점입니다.
```

---

## 3. 영화 추가 및 평점 수정

### 3-1. 문제 설명

영화를 추가하고, 기존 영화의 평점을 수정하는 프로그램을 작성하세요.

### 3-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieRatingManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 영화 목록 초기화
        HashMap<String, Double> movies = new HashMap<>();
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        
        System.out.println("=== 초기 영화 목록 ===");
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
        }
        
        // 새 영화 추가
        System.out.print("\n추가할 영화명: ");
        String newMovie = scanner.nextLine();
        System.out.print("평점: ");
        double newRating = scanner.nextDouble();
        scanner.nextLine(); // 버퍼 비우기
        
        movies.put(newMovie, newRating);
        System.out.println(newMovie + " 영화가 추가되었습니다.");
        
        // 기존 영화 평점 수정
        System.out.print("\n수정할 영화명: ");
        String updateMovie = scanner.nextLine();
        
        if (movies.containsKey(updateMovie)) {
            System.out.print("새 평점: ");
            double updateRating = scanner.nextDouble();
            movies.put(updateMovie, updateRating);
            System.out.println(updateMovie + "의 평점이 " + updateRating + "점으로 변경되었습니다.");
        } else {
            System.out.println("해당 영화가 없습니다.");
        }
        
        // 최종 영화 목록 출력
        System.out.println("\n=== 최종 영화 목록 ===");
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
        }
        
        scanner.close();
    }
}
```

### 3-3. 실행 결과

```
=== 초기 영화 목록 ===
기생충: 9.0점
인터스텔라: 9.2점
어벤져스: 8.5점

추가할 영화명: 라라랜드
평점: 8.8
라라랜드 영화가 추가되었습니다.

수정할 영화명: 어벤져스
새 평점: 8.7
어벤져스의 평점이 8.7점으로 변경되었습니다.

=== 최종 영화 목록 ===
기생충: 9.0점
라라랜드: 8.8점
인터스텔라: 9.2점
어벤져스: 8.7점
```

---

## 4. 평점대별 영화 찾기

### 4-1. 문제 설명

특정 평점 이상의 영화를 찾아 출력하는 프로그램을 작성하세요.

### 4-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieRatingFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 영화 목록 초기화
        HashMap<String, Double> movies = new HashMap<>();
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        movies.put("겨울왕국", 8.7);
        movies.put("토이스토리", 9.1);
        movies.put("라라랜드", 8.8);
        movies.put("매트릭스", 8.9);
        
        System.out.print("최소 평점을 입력하세요: ");
        double minRating = scanner.nextDouble();
        
        System.out.println("\n=== " + minRating + "점 이상 영화 ===");
        boolean found = false;
        
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            if (entry.getValue() >= minRating) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("해당 평점대의 영화가 없습니다.");
        }
        
        scanner.close();
    }
}
```

### 4-3. 실행 결과

```
최소 평점을 입력하세요: 9.0

=== 9.0점 이상 영화 ===
토이스토리: 9.1점
기생충: 9.0점
인터스텔라: 9.2점
```

---

## 5. 종합 예제: 영화 추천 시스템

### 5-1. 문제 설명

영화 목록을 관리하고 사용자의 평점을 받아 추천 영화를 찾는 프로그램을 작성하세요.

### 5-2. 코드

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieRecommendationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 영화 목록 초기화
        HashMap<String, Double> movies = new HashMap<>();
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        movies.put("겨울왕국", 8.7);
        movies.put("토이스토리", 9.1);
        movies.put("라라랜드", 8.8);
        movies.put("매트릭스", 8.9);
        
        // 사용자 평점 저장
        HashMap<String, Double> userRatings = new HashMap<>();
        
        System.out.println("=== 영화 평점 입력 ===");
        System.out.println("영화를 보고 평점을 입력하세요 (종료: '종료')\n");
        
        while (true) {
            // 영화 목록 출력
            System.out.println("=== 영화 목록 ===");
            for (Map.Entry<String, Double> entry : movies.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
            }
            
            System.out.println("\n평점을 입력할 영화명을 입력하세요 (종료: '종료'): ");
            String movieName = scanner.nextLine();
            
            if (movieName.equals("종료")) {
                break;
            }
            
            // 영화 존재 확인
            if (!movies.containsKey(movieName)) {
                System.out.println("해당 영화가 없습니다. 다시 입력해주세요.");
                continue;
            }
            
            // 사용자 평점 입력
            System.out.print("평점을 입력하세요 (0.0 ~ 10.0): ");
            double userRating = scanner.nextDouble();
            scanner.nextLine(); // 버퍼 비우기
            
            if (userRating < 0.0 || userRating > 10.0) {
                System.out.println("평점은 0.0부터 10.0 사이여야 합니다.");
                continue;
            }
            
            userRatings.put(movieName, userRating);
            System.out.println(movieName + "에 " + userRating + "점을 주셨습니다.");
        }
        
        // 입력한 평점 출력
        if (!userRatings.isEmpty()) {
            System.out.println("\n=== 입력한 평점 ===");
            for (Map.Entry<String, Double> entry : userRatings.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
            }
            
            // 평균 평점 계산
            double sum = 0;
            for (double rating : userRatings.values()) {
                sum += rating;
            }
            double average = sum / userRatings.size();
            System.out.println("\n평균 평점: " + average + "점");
            
            // 높은 평점 영화 추천
            System.out.println("\n=== 추천 영화 (9.0점 이상) ===");
            boolean found = false;
            for (Map.Entry<String, Double> entry : movies.entrySet()) {
                if (entry.getValue() >= 9.0 && !userRatings.containsKey(entry.getKey())) {
                    System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
                    found = true;
                }
            }
            
            if (!found) {
                System.out.println("추천할 영화가 없습니다.");
            }
        } else {
            System.out.println("\n입력한 평점이 없습니다.");
        }
        
        scanner.close();
    }
}
```

### 5-3. 실행 결과

```
=== 영화 평점 입력 ===
영화를 보고 평점을 입력하세요 (종료: '종료')

=== 영화 목록 ===
토이스토리: 9.1점
겨울왕국: 8.7점
기생충: 9.0점
인터스텔라: 9.2점
어벤져스: 8.5점
라라랜드: 8.8점
매트릭스: 8.9점

평점을 입력할 영화명을 입력하세요 (종료: '종료'): 
어벤져스
평점을 입력하세요 (0.0 ~ 10.0): 8.5
어벤져스에 8.5점을 주셨습니다.

=== 영화 목록 ===
토이스토리: 9.1점
겨울왕국: 8.7점
기생충: 9.0점
인터스텔라: 9.2점
어벤져스: 8.5점
라라랜드: 8.8점
매트릭스: 8.9점

평점을 입력할 영화명을 입력하세요 (종료: '종료'): 
기생충
평점을 입력하세요 (0.0 ~ 10.0): 9.2
기생충에 9.2점을 주셨습니다.

=== 영화 목록 ===
토이스토리: 9.1점
겨울왕국: 8.7점
기생충: 9.0점
인터스텔라: 9.2점
어벤져스: 8.5점
라라랜드: 8.8점
매트릭스: 8.9점

평점을 입력할 영화명을 입력하세요 (종료: '종료'): 
종료

=== 입력한 평점 ===
어벤져스: 8.5점
기생충: 9.2점

평균 평점: 8.85점

=== 추천 영화 (9.0점 이상) ===
토이스토리: 9.1점
인터스텔라: 9.2점
```

---

## 추가 예제: 평균 평점 계산

### 코드

```java
import java.util.HashMap;
import java.util.Map;

public class MovieAverageRating {
    public static void main(String[] args) {
        // 영화 목록 초기화
        HashMap<String, Double> movies = new HashMap<>();
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        movies.put("겨울왕국", 8.7);
        movies.put("토이스토리", 9.1);
        
        // 평균 평점 계산
        double sum = 0;
        int count = movies.size();
        
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            sum += entry.getValue();
        }
        
        double average = sum / count;
        
        System.out.println("=== 영화 평점 ===");
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
        }
        
        System.out.println("\n평균 평점: " + average + "점");
    }
}
```

### 실행 결과

```
=== 영화 평점 ===
토이스토리: 9.1점
겨울왕국: 8.7점
기생충: 9.0점
인터스텔라: 9.2점
어벤져스: 8.5점

평균 평점: 8.9점
```

---

## 추가 예제: 가장 높은/낮은 평점 영화 찾기

### 코드

```java
import java.util.HashMap;
import java.util.Map;

public class MovieRatingRange {
    public static void main(String[] args) {
        // 영화 목록 초기화
        HashMap<String, Double> movies = new HashMap<>();
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        movies.put("겨울왕국", 8.7);
        movies.put("토이스토리", 9.1);
        movies.put("라라랜드", 8.8);
        
        // 가장 높은 평점 영화 찾기
        String topMovie = null;
        double maxRating = Double.MIN_VALUE;
        
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            if (entry.getValue() > maxRating) {
                maxRating = entry.getValue();
                topMovie = entry.getKey();
            }
        }
        
        // 가장 낮은 평점 영화 찾기
        String lowMovie = null;
        double minRating = Double.MAX_VALUE;
        
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            if (entry.getValue() < minRating) {
                minRating = entry.getValue();
                lowMovie = entry.getKey();
            }
        }
        
        System.out.println("=== 영화 평점 ===");
        for (Map.Entry<String, Double> entry : movies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "점");
        }
        
        System.out.println("\n가장 높은 평점 영화: " + topMovie + " (" + maxRating + "점)");
        System.out.println("가장 낮은 평점 영화: " + lowMovie + " (" + minRating + "점)");
    }
}
```

### 실행 결과

```
=== 영화 평점 ===
토이스토리: 9.1점
겨울왕국: 8.7점
기생충: 9.0점
인터스텔라: 9.2점
어벤져스: 8.5점
라라랜드: 8.8점

가장 높은 평점 영화: 인터스텔라 (9.2점)
가장 낮은 평점 영화: 어벤져스 (8.5점)
```

---

## 전체 요약

### HashMap을 활용한 영화 평점 관리 패턴

```java
// 1. 영화 목록 초기화
HashMap<String, Double> movies = new HashMap<>();
movies.put("영화명", 평점);

// 2. 영화 조회
if (movies.containsKey("영화명")) {
    double rating = movies.get("영화명");
}

// 3. 영화 순회
for (Map.Entry<String, Double> entry : movies.entrySet()) {
    String name = entry.getKey();
    double rating = entry.getValue();
}

// 4. 평점 수정
movies.put("영화명", 새평점);

// 5. 영화 삭제
movies.remove("영화명");
```

### 핵심 포인트

1. **키-값 쌍**: 영화명(키)과 평점(값)을 쌍으로 저장
2. **entrySet()**: 모든 영화를 효율적으로 순회
3. **containsKey()**: 영화 존재 여부 확인
4. **get()**: 특정 영화의 평점 조회
5. **put()**: 영화 추가 또는 평점 수정

### 활용 예시

- ✅ 영화 평점 관리 시스템
- ✅ 영화 추천 시스템
- ✅ 평점 조회 시스템
- ✅ 통계 분석 (평균, 최댓값, 최솟값)
- ✅ 필터링 (평점대별 영화 찾기)

---

## 연습 문제

1. **영화 삭제 기능 추가**: 사용자가 입력한 영화를 삭제하는 기능을 추가하세요.

2. **장르별 관리**: 액션/로맨스/코미디 등 장르를 구분하여 관리하는 프로그램을 작성하세요.

3. **인기 영화 추적**: 조회 횟수를 추적하여 인기 영화를 찾는 프로그램을 작성하세요.

4. **평점 통계**: 평점 분포를 분석하는 프로그램을 작성하세요 (예: 9점 이상, 8-9점, 8점 미만).
