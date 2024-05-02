package com.example.demo.Repository;
import com.example.demo.Player;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    // Define custom methods if needed
//    @Modifying
//    @Query(value = "INSERT INTO player (first_name,last_name,age,phone_no, league_id,team_id) VALUES (:firstName,:lastName,:age,:phoneNo, :leagueId, :teamId)", nativeQuery = true)
//    void insertPlayer(@Param("firstName") String firstName,@Param("lastName") String teamName, @Param("age") int age,@Param("phoneNo") int phoneNo, @Param("leagueId") int leagueId, @Param("teamId") int teamId);

//    List<Player> findByfirstName(String firstName);
}

