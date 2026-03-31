package com.example.student.controller;

import com.example.student.dto.LoginRequest;
import com.example.student.dto.SignupRequest;
import com.example.student.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request) {
        service.register(request);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {

        if (service.login(request)) {
            return "redirect:/home";
        }

        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
