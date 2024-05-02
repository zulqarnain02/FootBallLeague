package com.example.demo.Controller;

import com.example.demo.League;
import com.example.demo.Repository.LeagueRepository;
import com.example.demo.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@Controller
public class LeagueController {
    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;
    @GetMapping("/league")
    public String league(Model model) {
        model.addAttribute("leagues", leagueRepository.findAll());
        return "league";
    }
    @GetMapping("/addleague")
    public String addleague(){
        return "addLeague";
    }


    @PostMapping("/addleague")
    public String createLeague(@RequestParam String leagueName, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate, RedirectAttributes redirectAttributes) {
        League newLeague = new League();

        // *******************implemented builder design pattern****************
        newLeague.setLeagueName(leagueName);
        newLeague.setStartDate(startDate);
        newLeague.setEndDate(endDate);
        leagueRepository.save(newLeague);
        redirectAttributes.addFlashAttribute("message", "League added successfully!");
        return "redirect:/league"; // Redirect back to the league page or a confirmation page
    }

    @PostMapping("/removeleague")
    public String removeLeague(@RequestParam String leagueName, RedirectAttributes redirectAttributes, Model model) {
        System.out.println("league name" +leagueName);
        List<League> leagues = leagueRepository.findByLeagueName(leagueName);
        System.out.println("all leagues " + leagues);
        if (leagues.isEmpty()) {
            model.addAttribute("leagues", leagueRepository.findAll()); // Make sure to add this to update the table
            return "league"; // Stay on the current page and show error
        } else {
            leagueRepository.deleteAll(leagues);
            return "redirect:/league"; // If successful, redirect back to the league page
        }
    }

    @GetMapping("/league/details")
    @ResponseBody
    public ResponseEntity<League> getLeagueDetails(@RequestParam String leagueName) {
        List<League> leagues = leagueRepository.findByLeagueName(leagueName);
        if (leagues.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            // Assuming league names are unique and just getting the first one.
            return ResponseEntity.ok(leagues.get(0));
        }
    }

    @PostMapping("/league/extend")
    @ResponseBody // Make sure you are returning a response body
    public ResponseEntity<?> extendLeagueEndDate(@RequestParam String leagueName,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date extendedDate) {
        List<League> leagues = leagueRepository.findByLeagueName(leagueName);
        if (leagues.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("League not found");
        } else {
            League league = leagues.get(0); // Assuming league names are unique

            // *******************implemented builder design pattern****************
            league.setEndDate(extendedDate);
            leagueRepository.save(league);
            return ResponseEntity.ok("League extended successfully");
        }
    }
}
