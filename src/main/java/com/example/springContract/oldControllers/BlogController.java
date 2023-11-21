package com.example.springContract.oldControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BlogController {


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


}
