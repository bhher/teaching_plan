# 02. Comparator — 정렬 기준 바꾸기

클래스 **밖**에서 기준을 정합니다. `Student`에 `Comparable`이 **없어도** 됩니다.

| 파일 | 역할 |
|------|------|
| `Student.java` | 데이터만 (Comparable 없음) |
| `ComparatorDemo.java` | 이름/나이/점수 등 여러 기준으로 정렬 |

```powershell
javac *.java
java ComparatorDemo
```

```java
Collections.sort(list, (a, b) -> a.age - b.age);       // 나이 오름차순
Collections.sort(list, (a, b) -> b.score - a.score);  // 점수 내림차순
```

연습 문제: [../02-Comparator-문제/](../02-Comparator-문제/) · 정답: [../02-Comparator-정답/](../02-Comparator-정답/)