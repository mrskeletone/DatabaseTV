package com.example.spring_contract.controllers;

import com.example.spring_contract.model.Client;
import com.example.spring_contract.model.FinishedProduct;
import com.example.spring_contract.model.Manager;
import com.example.spring_contract.model.Sell;
import com.example.spring_contract.repository.ClientRepository;
import com.example.spring_contract.repository.FinishProductRepository;
import com.example.spring_contract.repository.ManagerRepository;
import com.example.spring_contract.repository.SellRepository;
import com.example.spring_contract.service.ClientService;
import com.example.spring_contract.service.ContractService;
import com.example.spring_contract.service.ManagerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    @NonNull
    private ClientService clientService;
    @NonNull
    private ManagerService managerService;

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

        } else if (!Objects.equals(product, "") && !Objects.equals(man, "") && product != null && man != null) {
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
        } else if (!Objects.equals(cl, "") && !Objects.equals(man, "") && cl != null && man != null) {
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
            list = service.getById(id.get());
        } else if (!Objects.equals(product, "") && product != null) {
            list = service.getByProduct(product);
        } else if (!Objects.equals(cl, "") && cl != null) {
            list = service.getByClient(cl);
        } else if (!Objects.equals(man, "") && man != null) {
            list = service.getByManager(man);
        } else {
            list = service.getAll();
        }
        List<Sell> priceList;
        if (end.isPresent() && begin.isPresent()) {
            priceList = service.getByPrice(begin.get(), end.get());
            checkPriceOnList(list, priceList);
        } else if (begin.isPresent()) {
            priceList = service.getByBeginPrice(begin.get());
            checkPriceOnList(list, priceList);
        }

        model.addAttribute("sell", list);
        return "contract/mainContract";
    }

    //Сравнивает листы и убирает все ненужные
    private void checkPriceOnList(List<Sell> list, List<Sell> priceList) {
        boolean flag;
        for (int i = 0; i < list.size(); i++) {
            flag = false;
            for (var price :
                    priceList) {
                if (list.get(i) == price) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
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
    public String removeProduct(Model model, @PathVariable int id) {
        Sell sell = sellRepository.findById(id).orElseThrow();
        sellRepository.delete(sell);
        return "redirect:/mainContract";
    }

    @GetMapping("/mainContract/{id}/change")
    public String changeContract(Model model, @PathVariable int id) {
        model.addAttribute("contract", sellRepository.findById(id).orElseThrow());
        model.addAttribute("client", clientRepository.findAll());
        model.addAttribute("manager", managerRepository.findAll());
        model.addAttribute("product", finishProductRepository.findAll());

        return "contract/changeContract";
    }

    @PostMapping("/mainContract/{id}/change")
    public String postChangeContract(Model model, @PathVariable int id, @RequestParam Client clients, @RequestParam Manager managers,
                                     @RequestParam FinishedProduct products, @RequestParam Optional<Integer> price, @RequestParam Optional<Integer> quantity,
                                     @RequestParam LocalDate date) {
        Sell sell = sellRepository.findById(id).orElseThrow();
        sell.setDateSell(date);
        Manager managerOld = managerRepository.findById(sell.getManager().getId()).orElseThrow();
        Manager managerNew = managerRepository.findById(managers.getId()).orElseThrow();
        if (price.orElseThrow() >= 0) {
            sell.setPrice(price.orElseThrow());

        }
        sell.setClient(clients);
        if (quantity.orElseThrow() >= 0) {
            sell.setQuantity(quantity.orElseThrow());
        }
        if (managerNew != managerOld) {
            managerOld.setEarned(managerOld.getEarned() - price.orElseThrow());
            managerNew.setEarned(managerNew.getEarned() + price.orElseThrow());
            sell.setManager(managers);
        }
        sell.setFinishedProduct(products);
        sellRepository.save(sell);
        return "redirect:/mainContract/{id}";
    }

    @GetMapping("/mainContract/add")
    public String addContract(Model model) {
        model.addAttribute("managers", managerRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("products", finishProductRepository.findAll());
        return "contract/addContract";
    }

    @PostMapping("/mainContract/add")
    public String postAddContract(Model model, @RequestParam LocalDate date, @RequestParam Manager manager,
                                  @RequestParam Client client, @RequestParam FinishedProduct product,
                                  @RequestParam Optional<Integer> price, @RequestParam Optional<Integer> quantity) {
        service.save(client, manager, product, price, quantity, date);
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

    //Client
    @GetMapping("/mainContract/client")
    public String client(Model model, String id, String org, String address) {
        List<Client> list = clientService.find(id, org, address);
        model.addAttribute("client", list);
        return "contract/client";
    }

    @PostMapping("/mainContract/client/{id}/remove")
    public String removeClient(Model model, @PathVariable int id) {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/mainContract/client";
    }

    @GetMapping("/mainContract/client/{id}/change")
    public String changeClient(Model model, @PathVariable int id) {
        Client client = clientRepository.findById(id).orElseThrow();
        model.addAttribute("client", client);
        return "contract/changeClient";
    }

    @PostMapping("/mainContract/client/{id}/change")
    public String postChangeClient(Model model, @PathVariable int id, @RequestParam String name, @RequestParam String org, @RequestParam String address) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setName(name);
        client.setOrganization(org);
        client.setAddres(address);
        clientRepository.save(client);
        return "redirect:/mainContract/client";
    }

    @GetMapping("/mainContract/client/add")
    public String addClient(Model model) {

        return "contract/addClient";
    }

    @PostMapping("/mainContract/client/add")
    public String postAddClient(Model model, @RequestParam String name, @RequestParam String org,
                                @RequestParam String address) {
        clientService.saveClient(name, org, address);
        return "redirect:/mainContract/client";
    }

    //Manager
    @GetMapping("/mainContract/manager")
    public String manager(Model model, String id, Optional<Integer> beginSalary, Optional<Integer> endSalary,
                          Optional<Integer> beginEarned, Optional<Integer> endEarned) {
        List<Manager> list = managerService.find(id, beginSalary, endSalary, beginEarned, endEarned);
        model.addAttribute("manager", list);
        return "contract/manager";
    }

    @PostMapping("/mainContract/manager/{id}/remove")
    public String removeManager(Model model, @PathVariable int id) {
        Manager manager = managerRepository.findById(id).orElseThrow();
        managerRepository.delete(manager);
        return "redirect:/mainContract/manager";
    }

    @GetMapping("/mainContract/manager/{id}/change")
    public String changeManager(Model model, @PathVariable int id) {
        Manager manager = managerRepository.findById(id).orElseThrow();
        model.addAttribute("managers", manager);
        return "contract/changeManager";
    }

    @PostMapping("/mainContract/manager/{id}/change")
    public String postChangeManager(Model model, @PathVariable int id, @RequestParam String name, @RequestParam Optional<Integer> salary) {
        Manager manager = managerRepository.findById(id).orElseThrow();
        manager.setName(name);
        if (salary.orElseThrow() > 0)
            manager.setSalary(salary.orElseThrow());
        managerRepository.save(manager);
        return "redirect:/mainContract/manager";
    }

    @GetMapping("/mainContract/manager/add")
    public String addManager(Model model) {

        return "contract/addManager";
    }

    @PostMapping("/mainContract/manager/add")
    public String postAddManager(Model model, @RequestParam String name, @RequestParam Optional<Integer> salary) {
        managerService.save(name, salary);
        return "redirect:/mainContract/manager";
    }
}
