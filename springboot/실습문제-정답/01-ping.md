# 실습 문제 1 — Ping

## 문제

`GET /api/ping` 으로 요청하면 **문자열** `pong` 을 응답하는 REST API를 만드시오.

**조건:**

- 컨트롤러 클래스 이름은 자유 (`PingController` 등)
- URL은 정확히 **GET `/api/ping`**

**힌트:** `@RestController`, `@GetMapping`

---

## 정답

```java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/api/ping")
    public String ping() {
        return "pong";
    }
}
```

**확인:** 브라우저에서 `http://localhost:8080/api/ping` 접속 → 화면에 `pong`
