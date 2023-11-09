package com.example.springblog2.controllers;

import com.example.springblog2.model.LoginPassword;
import com.example.springblog2.repository.LoginPasswordRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

//@Controller
//@RequiredArgsConstructor
public class MainController {
//    @NonNull
//    private LoginPasswordRepository loginPasswordRepository;
//
//    @GetMapping("/")
//    public String greeting( Model model) {
//        model.addAttribute("title", "Главная страница");
//        return "home";
//    }
//@PostMapping("/")
//    public String sigPost(@RequestParam String login,@RequestParam String password,Model model){
//    Optional<LoginPassword> loginPassword=loginPasswordRepository.findById(login);
//
//
//    if(loginPassword.isPresent() && loginPassword.get().getPassword().equals(password))
//        return "redirect:/blog";
//    else return "redirect:/";
//}

}
