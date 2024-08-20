package org.example.exam.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    /*
    @GetMapping("/login")
    public String handleLogin() {
        return "user-login";
    }
    */

    @GetMapping("/home")
    public String showHome() {return "index";}
}
