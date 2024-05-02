package com.example.demo.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.League;
import org.springframework.data.repository.query.Param;

public interface LeagueRepository extends CrudRepository<League,Integer>{
    List<League> findByLeagueName(String leagueName);
    void deleteByLeagueName(String leagueName);

    @Query("SELECT l.leagueID FROM League l WHERE l.leagueID = :leagueId")
    Optional<Integer> findById(@Param("leagueId") int leagueId);

    @Query("SELECT l.leagueID FROM League l WHERE l.leagueName = :leagueName")
    Optional<Integer> findLeagueIdByLeagueName(@Param("leagueName") String leagueName);
}
