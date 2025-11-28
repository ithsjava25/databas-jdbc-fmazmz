package com.example.domain;

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

    @Autowired
    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public List<String> getAllMissionNames() {
        return missionRepository.findAll().stream()
                .map(MoonMission::getSpacecraft)
                .toList();
    }

    public Optional<MoonMission> getMissionById(Short id) {
        return missionRepository.findById(id);
    }

    public long totalMissionsByYear(int year) {
        return missionRepository.countMissionsByYear(year);
    }



}
