package com.example.jdbc;

import com.example.domain.dto.MoonMissionDTO;
import com.example.domain.model.MoonMission;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MissionJdbcRepository {

    public List<MoonMission> findAll() {
        String sql = "select * from moon_mission";
        List<MoonMission> missions = new ArrayList<>();

        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(sql)) {
            ResultSet results =  statement.executeQuery();
            while (results.next()) {
                missions.add(new MoonMission(
                        results.getInt("mission_id"),
                        results.getString("spacecraft"),
                        results.getDate("launch_date").toLocalDate(),
                        results.getString("carrier_rocket"),
                        results.getString("operator"),
                        results.getString("mission_type"),
                        results.getString("outcome")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        return missions;
    }
}
