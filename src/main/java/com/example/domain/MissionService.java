package com.example.domain;

import com.example.domain.dto.MoonMissionDTO;
import com.example.domain.dto.MoonMissionDTOMapper;
import com.example.domain.model.MoonMission;
import com.example.domain.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final MoonMissionDTOMapper moonMissionDTOMapper;

    @Autowired
    public MissionService(MissionRepository missionRepository,  MoonMissionDTOMapper moonMissionDTOMapper) {
        this.missionRepository = missionRepository;
        this.moonMissionDTOMapper = moonMissionDTOMapper;
    }

    public List<String> getAllMissionNames() {
        return missionRepository.findAll().stream()
                .map(MoonMission::getSpacecraft)
                .toList();
    }

    public Optional<MoonMissionDTO> getMissionById(Integer id) {
        return missionRepository.findById(id).map(moonMissionDTOMapper);
    }

    public long totalMissionsByYear(int year) {
        return missionRepository.countMissionsByYear(year);
    }



}
