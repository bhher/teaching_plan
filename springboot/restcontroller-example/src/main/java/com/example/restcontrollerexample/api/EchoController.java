package com.example.restcontrollerexample.api;

import com.example.restcontrollerexample.api.dto.EchoRequest;
import com.example.restcontrollerexample.api.dto.EchoResponse;
import java.time.Instant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EchoController {

    @PostMapping("/echo")
    public EchoResponse echo(@RequestBody EchoRequest req) {
        return new EchoResponse(req.message(), req.number(), Instant.now());
    }
}

