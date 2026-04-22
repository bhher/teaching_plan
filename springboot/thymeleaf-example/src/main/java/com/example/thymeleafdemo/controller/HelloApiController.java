package com.example.thymeleafdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * HTML(Thymeleaf)과 같은 프로젝트에서 REST API를 함께 둘 수 있음을 보이는 최소 예제.
 */
@RestController
@RequestMapping("/api")
public class HelloApiController {

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", "Hello from REST — /api/hello");
    }
}
