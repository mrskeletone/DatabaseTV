package com.example.spring_contract.controllers;

import com.example.spring_contract.model.Storekeeper;
import com.example.spring_contract.repository.StorekeeperRepository;
import com.example.spring_contract.service.StorekeeperService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class StorekeeperController {
    @NonNull
    private StorekeeperRepository repository;
    @NonNull
    private StorekeeperService service;

    @GetMapping("/storekeeper")
    public String storekeeper(Model model, String id, Optional<Integer> begin, Optional<Integer> end) {
        List<Storekeeper> list = new ArrayList<>();
        if (!Objects.equals(id, "") && id!=null) {
            list = service.findByIdOrName(id);
        } else {
            list = repository.findAll();
        }
        if (begin.isPresent() || end.isPresent()) {
            List<Storekeeper> between = service.findBetweenSalary(begin, end);
            if (between != null) {
                List<Storekeeper> med = new ArrayList<>(list);
                med.removeAll(between);
                list.removeAll(med);
            }
        }

        model.addAttribute("storekeeper", list);
        return "storekeeper";
    }

    @PostMapping("/storekeeper/{id}/remove")
    public String storekeeperRemove(Model model, @PathVariable int id) {
        repository.delete(repository.findById(id).orElseThrow());
        return "redirect:/storekeeper";
    }

}
