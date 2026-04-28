package com.example.restcontrollerexample.api.dto;

import java.time.Instant;

public record EchoResponse(String message, int number, Instant serverTime) {
}

