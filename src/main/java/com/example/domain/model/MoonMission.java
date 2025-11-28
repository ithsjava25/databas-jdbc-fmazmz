package com.example.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "moon_mission")
public class MoonMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Short id;

    @Column(name = "spacecraft")
    private String spacecraft;

    @Column(name = "launch_date")
    private LocalDate launchDate;

    @Column(name = "carrier_rocket")
    private String carrierRocket;

    @Column(name = "operator")
    private String operator;

    @Column(name = "mission_type")
    private String missionType;

    @Column(name = "outcome")
    private String outcome;

    public MoonMission() {}

    public MoonMission(String spacecraft, LocalDate launchDate, String carrierRocket, String operator, String missionType, String outcome) {
        this.spacecraft = spacecraft;
        this.launchDate = launchDate;
        this.carrierRocket = carrierRocket;
        this.operator = operator;
        this.missionType = missionType;
        this.outcome = outcome;
    }

    public Short getId() {
        return id;
    }

    public String getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(String spacecraft) {
        this.spacecraft = spacecraft;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public String getCarrierRocket() {
        return carrierRocket;
    }

    public void setCarrierRocket(String carrierRocket) {
        this.carrierRocket = carrierRocket;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMissionType() {
        return missionType;
    }

    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "MoonMission{" +
                "id=" + id +
                ", spacecraft='" + spacecraft + '\'' +
                ", launchDate=" + launchDate +
                ", carrierRocket='" + carrierRocket + '\'' +
                ", operator='" + operator + '\'' +
                ", missionType='" + missionType + '\'' +
                ", outcome='" + outcome + '\'' +
                '}';
    }
}