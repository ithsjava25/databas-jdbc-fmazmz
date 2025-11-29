package com.example.domain.dto;

public record PasswordUpdateRequest(
        Integer userId,
        String password
) {
}
