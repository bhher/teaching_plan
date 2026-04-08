# 실습 문제 3 — Echo (JSON 그대로 반환)

## 문제

`POST /api/echo` 로 **JSON 본문**을 보내면, 그대로 **다시 돌려주는(echo)** API를 만드시오.

**요청 예시 (JSON):**

```json
{ "message": "hello" }
```

**응답 예시 (JSON):**

```json
{ "message": "hello" }
```

**조건:**

- `@RequestBody` 사용
- DTO 클래스는 별도 파일로 두어도 되고, 한 파일에 두어도 됨 (입문 단계)

---

## 정답

### DTO

```java
package com.example.demo.dto;

public class EchoRequest {
    private String message;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
```

### 컨트롤러

```java
package com.example.demo.controller;

import com.example.demo.dto.EchoRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class EchoController {

    @PostMapping("/api/echo")
    public EchoRequest echo(@RequestBody EchoRequest body) {
        return body;
    }
}
```

**Postman 설정:** Method **POST**, URL `http://localhost:8080/api/echo`, Headers에 `Content-Type: application/json`, Body raw JSON 위 요청 예시.

**확인:** 응답 본문이 요청과 동일한 JSON.
