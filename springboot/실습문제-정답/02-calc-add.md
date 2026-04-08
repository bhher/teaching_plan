# 실습 문제 2 — 덧셈 JSON

## 문제

`GET /api/calc/add?a=3&b=5` 로 요청하면 **두 수의 합**을 **문자열이 아닌 숫자(JSON)** 로 응답하시오.

**예시 응답 (JSON):**

```json
{ "result": 8 }
```

**조건:**

- `a`, `b` 쿼리 파라미터 사용 (`@RequestParam`)
- 둘 중 하나라도 없으면 **400** 이 나오게 하면 좋음 (선택, 입문자는 생략 가능)

---

## 정답 (최소)

### DTO

```java
package com.example.demo.dto;

public class AddResult {
    private final int result;

    public AddResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
```

### 컨트롤러

```java
package com.example.demo.controller;

import com.example.demo.dto.AddResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

    @GetMapping("/api/calc/add")
    public AddResult add(
            @RequestParam int a,
            @RequestParam int b) {
        return new AddResult(a + b);
    }
}
```

**확인:** `http://localhost:8080/api/calc/add?a=3&b=5` → `{"result":8}`

---

## 정답 (선택) — 파라미터 없을 때 400

```java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@GetMapping("/api/calc/add")
public AddResult add(
        @RequestParam(required = false) Integer a,
        @RequestParam(required = false) Integer b) {
    if (a == null || b == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "a와 b가 필요합니다");
    }
    return new AddResult(a + b);
}
```
