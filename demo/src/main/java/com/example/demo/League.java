package com.example.demo;


//                                                    builder design pattern
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "League")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leagueID;

    @Column(name = "leagueName", nullable = false, length = 255)
    private String leagueName;

    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    // Getters and Setters
    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
