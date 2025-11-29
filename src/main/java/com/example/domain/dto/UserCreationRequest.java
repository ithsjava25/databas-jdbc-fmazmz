package com.example.domain.dto;

public record UserCreationRequest(
        String firstName,
        String lastName,
        String ssn,
        String password
) {
}
