# 01. Comparable — 기본 정렬

클래스 **안**에 정렬 기준을 두고 `Collections.sort(list)` 한 줄로 정렬합니다.

| 파일 | 역할 |
|------|------|
| `Student.java` | `Comparable` 구현 (`compareTo` = 점수 오름차순) |
| `ComparableDemo.java` | 실행 (main) |

```powershell
javac *.java
java ComparableDemo
```

```java
public int compareTo(Student o) {
    return this.score - o.score;  // 음수=앞, 0=같음, 양수=뒤
}
Collections.sort(list);  // compareTo 사용
```
