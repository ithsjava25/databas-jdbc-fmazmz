package com.example.domain.repository;

import com.example.domain.model.MoonMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionRepository extends JpaRepository<MoonMission,Short> {
    @Query("select count(m) from MoonMission m where extract(year from m.launchDate) = :year")
    long countMissionsByYear(int year);
}
