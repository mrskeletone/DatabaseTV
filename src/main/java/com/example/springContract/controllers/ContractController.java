package com.example.springContract.controllers;

import com.example.springContract.model.FinishedProduct;
import com.example.springContract.model.Sell;
import com.example.springContract.repository.SellRepository;
import com.example.springContract.service.ContractService;
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
public class ContractController {
    @NonNull
    private SellRepository sellRepository;
    @Autowired
    private ContractService service;

    @GetMapping("/mainContract")
    public String blog(Model model, Optional<Integer> id, String product, String cl,
                       String man, Optional<Integer> begin, Optional<Integer> end) {
        List<Sell> list = new ArrayList<>();
        if (!Objects.equals(product, "") && !Objects.equals(cl, "") && !Objects.equals(man, "")
                && product != null && cl != null && man != null) {
            List<Sell> productList = service.getByProduct(product);
            List<Sell> clientList = service.getByClient(cl);
            List<Sell> managerList = service.getByManager(man);

            for (var pr :
                    productList) {
                for (var client :
                        clientList) {
                    for (var manager :
                            managerList) {
                        if (pr == manager && client == manager) {
                            list.add(pr);
                        }
                    }
                }
            }
        } else if (!Objects.equals(product, "") && !Objects.equals(cl, "") && product != null && cl != null) {
            List<Sell> productList = service.getByProduct(product);
            List<Sell> clientList = service.getByClient(cl);
            for (var pr :
                    productList) {
                for (var client :
                        clientList) {
                    if (pr == client) {
                        list.add(pr);
                    }
                }
            }

        } else if (!Objects.equals(product, "") && !Objects.equals(man, "") && product!=null && man!=null) {
            List<Sell> productList = service.getByProduct(product);
            List<Sell> managerList = service.getByManager(man);
            for (var pr :
                    productList) {
                for (var manager :
                        managerList) {
                    if (pr == manager) {
                        list.add(pr);
                    }
                }
            }
        } else if (!Objects.equals(cl, "") && !Objects.equals(man, "") && cl!=null&&man!=null) {
            List<Sell> clientList = service.getByClient(cl);
            List<Sell> managerList = service.getByManager(man);
            for (var pr :
                    clientList) {
                for (var manager :
                        managerList) {
                    if (pr == manager) {
                        list.add(pr);
                    }
                }
            }
        } else if (id.isPresent()) {
            list=service.getById(id.get());
        } else if (!Objects.equals(product, "") && product!=null) {
            list=service.getByProduct(product);
        } else if (!Objects.equals(cl, "") && cl!=null) {
            list=service.getByClient(cl);
        } else if (!Objects.equals(man, "") &&man!=null) {
            list=service.getByManager(man);
        } else {
            list = service.getAll();
        }
        List<Sell> priceList;
        if(end.isPresent()&& begin.isPresent()){
            priceList=service.getByPrice(begin.get(),end.get());
            checkPriceOnList(list, priceList);
        }else if(begin.isPresent()){
            priceList=service.getByBeginPrice(begin.get());
            checkPriceOnList(list, priceList);
        }

        model.addAttribute("sell", list);
        return "mainContract";
    }
    //Сравнивает листы и убирает все ненужные
    private void checkPriceOnList(List<Sell> list, List<Sell> priceList) {
        boolean flag;
        for (int i = 0; i < list.size(); i++) {
            flag=false;
            for (var price :
                    priceList) {
                if(list.get(i)==price){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                list.remove(i);
                i--;
            }
        }
    }

    @GetMapping("/mainContract/{IDSell}")
    public String detail(Model model, @PathVariable int IDSell) {
        if (checkExist(model, IDSell)) return "redirect:/mainContract";
        return "detailsContract";
    }
    @PostMapping("/mainContract/{IDSell}/remove")
    public String removeProduct(Model model,@PathVariable int IDSell){
        Sell sell=sellRepository.findById(IDSell).get();
        sellRepository.delete(sell);
        return "redirect:/mainContract";
    }


    private boolean checkExist(Model model, @PathVariable int login) {
        if (!sellRepository.existsById(login)) {
            return true;
        }
        Optional<Sell> sells = sellRepository.findById(login);
        ArrayList<Sell> res = new ArrayList<>();
        sells.ifPresent(res::add);
        model.addAttribute("sell", res);
        return false;
    }
}
