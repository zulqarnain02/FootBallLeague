package com.example.demo;

//                                                    builder design pattern
import jakarta.persistence.*;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamID;

    @Column(name = "leagueId", nullable = false,columnDefinition = "INT DEFAULT 0")
    private int leagueId;

    @Column(name = "teamName", nullable = false, length = 255)
    private String teamName;

    // Getters and Setters
    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }
}
