package com.example.domain.dto;

import java.time.LocalDate;

public record MoonMissionDTO(
        String spacecraft,
        LocalDate launchDate,
        String carrierRocket,
        String operator,
        String missionType,
        String outcome
) {
}
