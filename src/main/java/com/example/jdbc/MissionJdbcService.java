package com.example.jdbc;

import com.example.domain.dto.MoonMissionDTO;
import com.example.domain.dto.MoonMissionDTOMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionJdbcService {

    private final MissionJdbcRepository repo;
    private final MoonMissionDTOMapper DTOmapper;

    public MissionJdbcService(MissionJdbcRepository missionJdbcRepository, MoonMissionDTOMapper moonMissionDTOMapper) {
        this.repo = missionJdbcRepository;
        this.DTOmapper = moonMissionDTOMapper;
    }


    public List<MoonMissionDTO> getAllMissions() {
        return repo.findAll()
                .stream().map(DTOmapper)
                .toList();
    }


}
