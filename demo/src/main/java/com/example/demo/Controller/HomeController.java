package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@Controller
public class HomeController {
    @GetMapping("/")
    public String welcome() {
        return "index";
    }
}
