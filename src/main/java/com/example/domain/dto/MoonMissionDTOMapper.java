package com.example.domain.dto;

import com.example.domain.model.MoonMission;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MoonMissionDTOMapper implements Function<MoonMission, MoonMissionDTO> {
    @Override
    public MoonMissionDTO apply(MoonMission moonMission) {
        return new  MoonMissionDTO(
                moonMission.getSpacecraft(),
                moonMission.getLaunchDate(),
                moonMission.getCarrierRocket(),
                moonMission.getOperator(),
                moonMission.getMissionType(),
                moonMission.getOutcome()
        );
    }
}
