package com.example.springContract.controllers;

import com.example.springContract.model.FinishedProduct;
import com.example.springContract.repository.FinishProductRepository;
import com.example.springContract.service.SuppliersService;
import com.example.springContract.model.Suppliers;
import com.example.springContract.repository.SuppliersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SuppliersController {
    @NonNull
    private SuppliersRepository suppliersRepository;
    @Autowired
    private SuppliersService service;
    @Autowired
    private FinishProductRepository finishProductRepository;
    @GetMapping("/suppliers")
    public String suppliers(Model model, String id,String mat) {
        List<Suppliers> suppliers;
        if (!Objects.equals(id, "") && id != null) {
            suppliers=service.getByIdOrOrg(id);
        } else if (!Objects.equals(mat, "") && mat!=null) {
            suppliers=service.getByMat(mat);
        } else {
            suppliers = service.getAllSup();
        }
        model.addAttribute("suppliers", suppliers);
        return "suppliers";
    }

    @GetMapping("/suppliers/{id}")
    public String suppliersId(Model model, @PathVariable int id) {
        Optional<Suppliers> suppliers = suppliersRepository.findById(id);

        ArrayList<Suppliers> suppliersArrayList = new ArrayList<>();
        suppliers.ifPresent(suppliersArrayList::add);
        model.addAttribute("suppliers", suppliersArrayList);

        return "detailsSuppliers";
    }
    @PostMapping("/suppliers/{id}/remove")
    public String removeProduct(Model model,@PathVariable int id){
        List<FinishedProduct> finishedProducts=finishProductRepository.findAllBySup(id);
        finishProductRepository.deleteAll(finishedProducts);
        Suppliers suppliers=suppliersRepository.findById(id).get();
        suppliersRepository.delete(suppliers);

        return "redirect:/suppliers";
    }
}
