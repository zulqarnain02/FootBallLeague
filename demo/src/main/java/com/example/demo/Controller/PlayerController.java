package com.example.demo.Controller;

import com.example.demo.Repository.LeagueRepository;
import com.example.demo.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@Controller
public class PlayerController {
    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;



    @GetMapping("/players")
    public String players(){
        return "player";
    }

    @GetMapping("/addplayers")
    public String addplayers(){
        return "addPlayer";
    }




//    @PostMapping("/addPlayer")
//    @Transactional
//    public ResponseEntity<String> addPlayer(@RequestParam String firstName,@RequestParam String lastName,@RequestParam int age,@RequestParam int phoneNo, @RequestParam int LeagueId,@RequestParam int teamId) {
//        System.out.println("leagueId " +LeagueId);
//        System.out.println("teamId "+teamId);
//        PlayerRepository.insertPlayer(firstName,lastName,age,phoneNo,LeagueId,teamId);
//        return ResponseEntity.ok("Team added successfully");
//    }

//    @PostMapping("/removePlayer")
//    public String removePlayer(@RequestParam String firstName, RedirectAttributes redirectAttributes, Model model) {
//        System.out.println("league name" +firstName);
//        List<Player> players = playerRepository.findByfirstName(firstName);
//        System.out.println("all leagues " + players);
//        if (players.isEmpty()) {
//            model.addAttribute("leagues", leagueRepository.findAll()); // Make sure to add this to update the table
//            return "team"; // Stay on the current page and show error
//        } else {
//            playerRepository.deleteAll(players);
//            return "redirect:/team"; // If successful, redirect back to the league page
//        }
//    }
}
