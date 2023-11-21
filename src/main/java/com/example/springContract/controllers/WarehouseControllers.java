package com.example.springContract.controllers;

import com.example.springContract.model.FinishedProduct;
import com.example.springContract.repository.WarehouseRepository;
import com.example.springContract.service.WarehouseService;
import com.example.springContract.model.TV_warehouse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class WarehouseControllers {
    @NonNull
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseService service;

//    @GetMapping("/warehouse")
    @RequestMapping(path = {"/warehouse"})
    public String warehouse(Model model,  String id, String name, String product) {
        if (!Objects.equals(id, "") && Objects.equals(name, "")&& Objects.equals(product, "")) {
            List<TV_warehouse> list = service.getBysId(id);
            model.addAttribute("warehouse", list);

        } else if (!Objects.equals(name, "") && Objects.equals(product, "")) {
            List<TV_warehouse> list = service.getByName(name);
            model.addAttribute("warehouse", list);
        } else if (!Objects.equals(product, "") && product!=null) {
            List<TV_warehouse> list = service.getByProduct(product);
            model.addAttribute("warehouse", list);
        } else {
            List<TV_warehouse> list = service.getAllWarehouse();
            model.addAttribute("warehouse", list);
        }
        return "warehouse";
    }

    @GetMapping("/warehouse/{id}")
    public String warehouseId(Model model, @PathVariable int id) {
        Optional<TV_warehouse> tv_warehouse = warehouseRepository.findById(id);
        ArrayList<TV_warehouse> tvWarehouses1 = new ArrayList<>();
        tv_warehouse.ifPresent(tvWarehouses1::add);
        model.addAttribute("warehouse", tvWarehouses1);
        return "detailsWarehouse";
    }
    @PostMapping("/warehouse/{id}/remove")
    public String removeProduct(Model model,@PathVariable int id){
        TV_warehouse warehouse=warehouseRepository.findById(id).get();
        warehouseRepository.delete(warehouse);
        return "redirect:/warehouse";
    }
}
