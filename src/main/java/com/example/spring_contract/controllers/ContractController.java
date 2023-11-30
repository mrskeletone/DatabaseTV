package com.example.spring_contract.controllers;

import com.example.spring_contract.model.Client;
import com.example.spring_contract.model.FinishedProduct;
import com.example.spring_contract.model.Manager;
import com.example.spring_contract.model.Sell;
import com.example.spring_contract.repository.ClientRepository;
import com.example.spring_contract.repository.FinishProductRepository;
import com.example.spring_contract.repository.ManagerRepository;
import com.example.spring_contract.repository.SellRepository;
import com.example.spring_contract.service.ContractService;
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
public class ContractController {
    @NonNull
    private SellRepository sellRepository;
    @NonNull
    private ContractService service;
    @NonNull
    private ClientRepository clientRepository;
    @NonNull
    private ManagerRepository managerRepository;
    @NonNull
    private FinishProductRepository finishProductRepository;

    @GetMapping("/mainContract")
    public String contract(Model model, Optional<Integer> id, String product, String cl,
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
        return "contract/mainContract";
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

    @GetMapping("/mainContract/{id}")
    public String detail(Model model, @PathVariable int id) {
        if (checkExist(model, id)) return "redirect:/mainContract";
        return "contract/detailsContract";
    }
    @PostMapping("/mainContract/{id}/remove")
    public String removeProduct(Model model,@PathVariable int id){
        Sell sell=sellRepository.findById(id).orElseThrow();
        sellRepository.delete(sell);
        return "redirect:/mainContract";
    }
    @GetMapping("/mainContract/{id}/change")
    public String changeContract(Model model,@PathVariable int id){
        model.addAttribute("contract",sellRepository.findById(id).orElseThrow());
        model.addAttribute("client",clientRepository.findAll());
        model.addAttribute("manager",managerRepository.findAll());
        model.addAttribute("product",finishProductRepository.findAll());

        return "contract/changeContract";
    }
    @PostMapping("/mainContract/{id}/change")
    public String postChangeContract(Model model, @PathVariable int id, @RequestParam Client clients, @RequestParam Manager managers,
                                     @RequestParam FinishedProduct products, @RequestParam Optional<Integer> price, @RequestParam Optional<Integer> quantity,
                                     @RequestParam LocalDate date){
        Sell sell=sellRepository.findById(id).orElseThrow();
        sell.setDateSell(date);
        sell.setPrice(price.orElseThrow());
        sell.setClient(clients);
        sell.setQuantity(quantity.orElseThrow());
        sell.setManager(managers);
        sell.setFinishedProduct(products);
        sellRepository.save(sell);
        return "redirect:/mainContract/{id}";
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
