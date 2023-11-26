package com.example.spring_contract.controllers;

import com.example.spring_contract.model.Storekeeper;
import com.example.spring_contract.repository.StorekeeperRepository;
import com.example.spring_contract.repository.WarehouseRepository;
import com.example.spring_contract.service.WarehouseService;
import com.example.spring_contract.model.TV_warehouse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class WarehouseControllers {
    @NonNull
    private WarehouseRepository warehouseRepository;

    @NonNull
    private WarehouseService service;
    @NonNull
    private StorekeeperRepository storekeeperRepository;

    //    @GetMapping("/warehouse")
    @RequestMapping(path = {"/warehouse"})
    public String warehouse(Model model, String id, String name, String product) {
        if (!Objects.equals(id, "") && Objects.equals(name, "") && Objects.equals(product, "")) {
            List<TV_warehouse> list = service.getBysId(id);
            model.addAttribute("warehouse", list);

        } else if (!Objects.equals(name, "") && Objects.equals(product, "")) {
            List<TV_warehouse> list = service.getByName(name);
            model.addAttribute("warehouse", list);
        } else if (!Objects.equals(product, "") && product != null) {
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
    public String removeProduct(Model model, @PathVariable int id) {
        TV_warehouse warehouse = warehouseRepository.findById(id).orElseThrow();
        warehouseRepository.delete(warehouse);
        return "redirect:/warehouse";
    }

    @GetMapping("/warehouse/{id}/change")
    public String warehouseChange(Model model, @PathVariable int id) {
        Optional<TV_warehouse> warehouse = warehouseRepository.findById(id);
        List<TV_warehouse> list = new ArrayList<>();
        warehouse.ifPresent(list::add);
        List<Storekeeper> storekeeperList = storekeeperRepository.findLessWarehouse();
        storekeeperList.add(list.get(0).getStorekeeper());
        model.addAttribute("warehouse", list);
        model.addAttribute("storekeeper", storekeeperList);
        return "changeWarehouse";
    }

    @PostMapping("/warehouse/{id}/change")
    public String warehouseChangePost(Model model, @PathVariable int id, @RequestParam Storekeeper storekeeper) {
        Optional<TV_warehouse> warehouse = warehouseRepository.findById(id);
        TV_warehouse tvWarehouse = warehouse.orElseThrow();
        tvWarehouse.setStorekeeper(storekeeper);
        warehouseRepository.save(tvWarehouse);
        return "redirect:/warehouse";
    }

    @GetMapping("/warehouse/add")
    public String warehouseAdd(Model model) {
        model.addAttribute("storekeeper", storekeeperRepository.findLessWarehouse());
        return "warehouseAdd";
    }

    @PostMapping("/warehouse/add")
    public String warehouseAddPost(Model model, @RequestParam Optional<Integer> id, @RequestParam Storekeeper storekeeper) {

        TV_warehouse warehouse = new TV_warehouse(id.orElseThrow(), null, storekeeper);
        service.save(warehouse);
        return "redirect:/warehouse";
    }

    @GetMapping("/warehouse/add/storekeeper")
    public String warehouseAddStorekeeper(Model model) {
        return "warehouseAddStorekeeper";
    }

    @PostMapping("/warehouse/add/storekeeper")
    public String warehouseAddPostStorekeeper(Model model, @RequestParam Optional<Integer> id, @RequestParam String name,
                                              @RequestParam Optional<Integer> salary) {
        Storekeeper storekeeper = new Storekeeper(id.orElseThrow(), name, salary.orElseThrow());
        service.saveStorekeeper(storekeeper);
        return "redirect:/storekeeper";
    }

}
