package com.example.spring_contract.controllers;

import com.example.spring_contract.model.Manufacture;
import com.example.spring_contract.model.Master;
import com.example.spring_contract.repository.ManufactureRepository;
import com.example.spring_contract.repository.MasterRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ManufactureAndMasterController {
    @NonNull
    private ManufactureRepository manufactureRepository;
    @NonNull
    private MasterRepository masterRepository;

    @GetMapping("/manufacture")
    public String manufacture(Model model){
        model.addAttribute("man",manufactureRepository.findAll());
        return "manufacture";
    }
    @PostMapping("/manufacture/{id}/remove")
    public String deleteManufacture(Model model,@PathVariable int id){
        Manufacture manufacture=manufactureRepository.findById(id).orElseThrow();
        manufactureRepository.delete(manufacture);
        return "redirect:/manufacture";
    }
    @GetMapping("/manufacture/add")
    public String addManufacture(Model model){
        return "addManufacture";
    }
    @PostMapping("/manufacture/add")
    public String postAddManufacture(Model model, @RequestParam Optional<Integer> idMan,@RequestParam Master master ){
        Manufacture manufacture=new Manufacture(idMan.orElseThrow(),master);
        //переделать с сервисом
        manufactureRepository.save(manufacture);
        return "redirect:/manufacture";
    }
    @GetMapping("/manufacture/master")
    public String master(Model model){
        model.addAttribute("mas",masterRepository.findAll());
        return "master";
    }
    @PostMapping("/manufacture/master/{id}/remove")
    public String deleteMaster(Model model,@PathVariable int id){
        Master master=masterRepository.findById(id).orElseThrow();
        masterRepository.delete(master);
        return "redirect:/manufacture/master";
    }
    @GetMapping("/manufacture/master/add" )
    public String addMaster(Model model){

        return "addMaster";
    }
    @PostMapping("/manufacture/master/add")
    public String postAddMaster(Model model,@RequestParam Optional<Integer> id,@RequestParam String name,
                                @RequestParam Optional<Integer> salary ,@RequestParam String special){
        Master master=new Master(id.orElseThrow(),name,salary.orElseThrow(),special);
        //Переделать с сервисом
        masterRepository.save(master);
        return "redirect:/manufacture/master";
    }
}
