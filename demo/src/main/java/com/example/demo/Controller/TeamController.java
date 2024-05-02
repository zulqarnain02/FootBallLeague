package com.example.demo.Controller;

import com.example.demo.Repository.LeagueRepository;
import com.example.demo.Team;
import com.example.demo.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@Controller
public class TeamController {
    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;


    @GetMapping("/team")
    public String team(){
        return "team";
    }

    @PostMapping("/enterLeague")
    @ResponseBody
    // This annotation indicates that the return value should be written directly to the HTTP response body
    public String enterLeague(@RequestParam String leagueName) {
        System.out.println("Received league name: " + leagueName);
        // Find the league by name
        Optional<Integer> leagueIdOptional = leagueRepository.findLeagueIdByLeagueName(leagueName);
        if (leagueIdOptional.isPresent()) {
            // If league ID exists, return it
            Integer leagueId = leagueIdOptional.get();
            System.out.println("League ID found: " + leagueId);
            return leagueId.toString();
        } else {
            // If no league ID is found, return an error message
            return "League ID not found";
        }
    }

//    @PostMapping("/enterTeam")
//    @ResponseBody // This annotation indicates that the return value should be written directly to the HTTP response body
//    public String teamLeague(@RequestParam String teamName) {
//        System.out.println("Received league name: " + teamName);
//        // Find the league by name
//        Optional<Integer> leagueIdOptional = teamRepository.findTeamIdByTeamName(teamName);
//        if (leagueIdOptional.isPresent()) {
//            // If league ID exists, return it
//            Integer teamId = leagueIdOptional.get();
//            System.out.println("team ID found: " + teamId);
//            return teamId.toString();
//        } else {
//            return "team ID not found";
//        }
//    }


    @GetMapping("/getTeamsByLeague")
    @ResponseBody
    public ResponseEntity<List<Team>> getTeamsByLeague(@RequestParam int leagueId) {
        // Retrieve teams from the database based on the provided leagueId
        System.out.println("leagueID "+leagueId);
        List<Team> teams = teamRepository.findByLeagueLeagueID(leagueId);
        System.out.println("teams "+teams);
        if (teams.isEmpty()) {
            // If no teams are found, return a 404 Not Found status
            return ResponseEntity.ok(teams);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("League not found")
        } else {
            // If teams are found, return them with a 200 OK status
            return ResponseEntity.ok(teams);
        }
    }


    //     Mapping to handle adding a new team
    @PostMapping("/addTeam")
    @Transactional
    public ResponseEntity<String> addTeam(@RequestParam String teamName, @RequestParam int leagueId) {
        System.out.println("team name " +teamName);
        System.out.println("leagueId " +leagueId);
        teamRepository.insertTeam(teamName,leagueId);
        return ResponseEntity.ok("Team added successfully");
    }

    @PostMapping("/removeteam")
    public String removeteam(@RequestParam String teamName, RedirectAttributes redirectAttributes, Model model) {
        System.out.println("league name" +teamName);
        List<Team> teams = teamRepository.findByTeamName(teamName);
        System.out.println("all leagues " + teams);
        if (teams.isEmpty()) {
            model.addAttribute("leagues", leagueRepository.findAll()); // Make sure to add this to update the table
            return "team"; // Stay on the current page and show error
        } else {
            teamRepository.deleteAll(teams);
            return "redirect:/team"; // If successful, redirect back to the league page
        }
    }
}
