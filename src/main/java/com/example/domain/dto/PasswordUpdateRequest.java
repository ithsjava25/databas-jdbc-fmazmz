package com.example.domain.dto;

public record PasswordUpdateRequest(
        Short userId,
        String password
) {
}
