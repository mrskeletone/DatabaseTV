package com.example.springblog2.controllers;

import com.example.springblog2.model.LoginPassword;
import com.example.springblog2.model.Sell;
import com.example.springblog2.repository.LoginPasswordRepository;
import com.example.springblog2.repository.SellRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BlogController {
    @NonNull
    private LoginPasswordRepository loginPasswordRepository;
    @NonNull
    private SellRepository sellRepository;
    @GetMapping("/blog")
    public String blog(Model model){
//        Iterable<LoginPassword> loginPasswords=loginPasswordRepository.findAll();
        Iterable<Sell> sells=sellRepository.findAll();
        model.addAttribute("sell",sells);
        return "blog-main";
    }
//    @GetMapping("/blog/register")
//    public String register(Model model){
//
//        return "register";
//    }
//    @PostMapping("/blog/register")
//    public String registerPost(@RequestParam String loginR,@RequestParam String passwordR, Model model){
//        if(loginPasswordRepository.findById(loginR).isEmpty()){
//        LoginPassword loginPassword=new LoginPassword(loginR,passwordR);
//        loginPasswordRepository.save(loginPassword);}
//        return "redirect:/";
//    }
    @GetMapping("/blog/{IDSell}")
    public String detail(Model model, @PathVariable int IDSell){
        if (checkExist(model, IDSell)) return "redirect:/blog";
        return "detailsLP";
    }
//    @GetMapping("/blog/{login}/edit")
//    public String blockEdit(Model model, @PathVariable String login){
//        if (checkExist(model, login)) return "redirect:/blog";
//        return "editLP";
//    }
//    @PostMapping("/blog/{login}/edit")
//    public String editPost(@RequestParam String loginR, @RequestParam String passwordR, Model model, @PathVariable String login){
//        LoginPassword loginPassword=loginPasswordRepository.findById(login).orElseThrow();
//        loginPassword.setLogin(loginR);
//        loginPassword.setPassword(passwordR);
//        loginPasswordRepository.save(loginPassword);
//        return "redirect:/blog";
//    }
//    @PostMapping("/blog/{login}/remove")
//    public String delete( Model model, @PathVariable String login){
//        LoginPassword loginPassword=loginPasswordRepository.findById(login).orElseThrow();
//        loginPasswordRepository.delete(loginPassword);
//        return "redirect:/blog";
//    }

    private boolean checkExist(Model model, @PathVariable int login) {
        if(!sellRepository.existsById(login)){
            return true;
        }
        Optional<Sell> sells=sellRepository.findById(login);
        ArrayList<Sell> res=new ArrayList<>();
        sells.ifPresent(res::add);
        model.addAttribute("sell",res);
        return false;
    }
}
