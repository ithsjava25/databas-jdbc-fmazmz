package com.example.domain.dto;

import org.jetbrains.annotations.NotNull;

public record PasswordUpdateRequest(
        @NotNull Integer userId,
        @NotNull String password
) {
}
