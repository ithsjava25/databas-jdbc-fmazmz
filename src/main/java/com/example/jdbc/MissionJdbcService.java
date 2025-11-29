package com.example.jdbc;

import com.example.domain.dto.MoonMissionDTO;
import com.example.domain.dto.MoonMissionDTOMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionJdbcService {

    private final MissionJdbcRepository missionJdbcRepository;
    private final MoonMissionDTOMapper moonMissionDTOMapper;

    public MissionJdbcService(MissionJdbcRepository missionJdbcRepository, MoonMissionDTOMapper moonMissionDTOMapper) {
        this.missionJdbcRepository = missionJdbcRepository;
        this.moonMissionDTOMapper = moonMissionDTOMapper;
    }


    public List<MoonMissionDTO> getAllMissions() {
        return missionJdbcRepository.findAll()
                .stream().map(moonMissionDTOMapper)
                .toList();
    }


}
