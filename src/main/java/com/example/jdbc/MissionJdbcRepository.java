package com.example.jdbc;

import com.example.domain.model.MoonMission;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MissionJdbcRepository {

    private final DataSource dataSource;

    public MissionJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<MoonMission> findAll() {
        String sql = "SELECT * FROM moon_mission";

        List<MoonMission> missions = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                missions.add(new MoonMission(
                        rs.getInt("mission_id"),
                        rs.getString("spacecraft"),
                        rs.getDate("launch_date").toLocalDate(),
                        rs.getString("carrier_rocket"),
                        rs.getString("operator"),
                        rs.getString("mission_type"),
                        rs.getString("outcome")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch missions", e);
        }

        return missions;
    }
}
