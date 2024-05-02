package com.example.demo.Repository;
import com.example.demo.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

    @Query("SELECT t.teamID,t.teamName FROM Team t WHERE t.leagueId = :leagueId")
    List<Team> findByLeagueLeagueID(@Param("leagueId") int leagueId);

    List<Team> findByTeamName(String leagueName);

    @Modifying
    @Query(value = "INSERT INTO team (team_name, league_id) VALUES (:teamName, :leagueId)", nativeQuery = true)
    void insertTeam(@Param("teamName") String teamName, @Param("leagueId") int leagueId);


//    @Query("SELECT l.teamID FROM Team l WHERE l.teamName = :teamName")
//    Optional<Integer> findTeamIdByTeamName(@Param("leagueName") String teamName);

}
