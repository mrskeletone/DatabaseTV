package com.example.springContract.controllers;

import com.example.springContract.model.*;
import com.example.springContract.repository.*;
import com.example.springContract.service.FinishProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FinishProductController {
    @NonNull
    private FinishProductRepository finishProductRepository;

    @Autowired
    private FinishProductService service;

    @NonNull
    private MaterialsRepository materialsRepository;
    @NonNull
    private ManufactureRepository manufactureRepository;
    @NonNull
    private WarehouseRepository warehouseRepository;
    @NonNull
    private GroupMaterialsRepository groupMaterialsRepository;


    @GetMapping("/finishProduct")
    public String finishProduct(Model model, String id, Optional<Integer> man, String mat, LocalDate begin, LocalDate end) {
        List<FinishedProduct> list = new ArrayList<>();
        if (!Objects.equals(mat, "") && mat != null && man.isPresent()) {
            List<FinishedProduct> materials = service.getByMaterials(mat);
            List<FinishedProduct> manufacture = service.getByMan(man.get());
            for (FinishedProduct material : materials) {
                for (FinishedProduct finishedProduct : manufacture) {
                    if (material == finishedProduct) {
                        list.add(material);
                    }
                }
            }
        } else if (!Objects.equals(id, "") && id != null) {
            list = service.getByNameOrId(id);
        } else if (man.isPresent()) {
            int i = man.get();
            list = service.getByMan(i);
        } else if (!Objects.equals(mat, "") && mat != null) {
            list = service.getByMaterials(mat);
        } else {
            list = service.getAllProduct();
        }

        List<FinishedProduct> time = service.getByDate(begin, end);
        boolean flag = false;
        int size = list.size();
        if (!time.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                flag = false;
                for (FinishedProduct finishedProduct : time) {
                    if (list.get(i) == finishedProduct) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    list.remove(i);
                    i--;
                }
            }
        }else {
            if(begin!=null){
                list.clear();
            }
        }

        model.addAttribute("finishProduct", list);

        return "finishProduct";
    }

    @GetMapping("/finishProduct/add")
    public String finisProductAdd(Model model){
        List<Materials> materials=materialsRepository.findAll();
        model.addAttribute("materials",materials);
        return "product-add";
    }
    @PostMapping("/finishProduct/add")
    public String finishProductAddPost(Model model, @RequestParam Optional<Integer> id,@RequestParam String nameProduct,
                                       @RequestParam Optional<Integer> manufacture,@RequestParam Optional<Integer>warehouse ,
                                       @RequestParam Optional<Integer>quantity , @RequestParam LocalDate date,
                                       @RequestParam("materials")Materials[] materials){
        Manufacture manufactureById=manufactureRepository.findById(manufacture.get()).get();
        TV_warehouse warehouseById=warehouseRepository.findById(warehouse.get()).get();
        FinishedProduct finishedProduct=new FinishedProduct(id.get(),quantity.get(),nameProduct,date,manufactureById,warehouseById);
        service.save(finishedProduct);
        for (Materials material : materials) {
            GroupMaterials groupMaterials = new GroupMaterials(finishedProduct, material);
            groupMaterialsRepository.save(groupMaterials);
        }
        return "redirect:/finishProduct";
    }
    @GetMapping("/finishProduct/{id}")
    public String finishProductId(Model model, @PathVariable int id) {
        Optional<FinishedProduct> finishedProduct = finishProductRepository.findById(id);
        ArrayList<FinishedProduct> finishedProducts = new ArrayList<>();
        finishedProduct.ifPresent(finishedProducts::add);
        model.addAttribute("finishProduct", finishedProducts);
        return "detailsFinishProduct";
    }
    @PostMapping("/finishProduct/{id}/remove")
    public String removeProduct(Model model,@PathVariable int id){
        FinishedProduct finishedProduct=finishProductRepository.findById(id).orElseThrow();
        finishProductRepository.delete(finishedProduct);
        return "redirect:/finishProduct";
    }
}
