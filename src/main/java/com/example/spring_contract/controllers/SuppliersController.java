package com.example.spring_contract.controllers;

import com.example.spring_contract.model.FinishedProduct;
import com.example.spring_contract.model.Materials;
import com.example.spring_contract.model.Organization;
import com.example.spring_contract.repository.FinishProductRepository;
import com.example.spring_contract.repository.MaterialsRepository;
import com.example.spring_contract.repository.OrganizationRepository;
import com.example.spring_contract.service.MaterialsService;
import com.example.spring_contract.service.OrganizationService;
import com.example.spring_contract.service.SuppliersService;
import com.example.spring_contract.model.Suppliers;
import com.example.spring_contract.repository.SuppliersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SuppliersController {
    @NonNull
    private SuppliersRepository suppliersRepository;
    @NonNull
    private SuppliersService service;
    @NonNull
    private FinishProductRepository finishProductRepository;
    @NonNull
    private OrganizationRepository organizationRepository;
    @NonNull
    private MaterialsRepository materialsRepository;
    @NonNull
    private MaterialsService materialsService;
    @NonNull
    private OrganizationService organizationService;

    @GetMapping("/suppliers")
    public String suppliers(Model model, String id,String org, String mat) {
        List<Suppliers> suppliers;
        if(!Objects.equals(id, "") &&id!=null && !Objects.equals(org, "") && org!=null){
            suppliers=service.getByIdAndOrg(id,org);
        } else if (!Objects.equals(org, "") && org!=null) {
            suppliers=service.getByOrgOrAddress(org);
        }else if (!Objects.equals(id, "") && id != null) {
            suppliers = service.getByIdOrName(id);
        } else if (!Objects.equals(mat, "") && mat != null) {
            suppliers = service.getByMat(mat);
        } else {
            suppliers = service.getAllSup();
        }
        model.addAttribute("suppliers", suppliers);
        return "suppliers/suppliers";
    }

    @GetMapping("/suppliers/{id}")
    public String suppliersId(Model model, @PathVariable int id) {
        Optional<Suppliers> suppliers = suppliersRepository.findById(id);

        ArrayList<Suppliers> suppliersArrayList = new ArrayList<>();
        suppliers.ifPresent(suppliersArrayList::add);
        model.addAttribute("suppliers", suppliersArrayList);

        return "suppliers/detailsSuppliers";
    }

    @PostMapping("/suppliers/{id}/remove")
    public String removeSuppliers(Model model, @PathVariable int id) {
        List<FinishedProduct> finishedProducts = finishProductRepository.findAllBySup(id);
        finishProductRepository.deleteAll(finishedProducts);
        Suppliers suppliers = suppliersRepository.findById(id).orElseThrow();
        suppliersRepository.delete(suppliers);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/{id}/change")
    public String changeSuppliers(Model model, @PathVariable int id) {
        model.addAttribute("organization", organizationRepository.findAll());
        model.addAttribute("suppliers", suppliersRepository.findByIds(id));
        return "suppliers/changeSuppliers";
    }

    @PostMapping("/suppliers/{id}/change")
    public String postChangeSuppliers(Model model, @PathVariable int id, @RequestParam String name, @RequestParam Organization org) {
        Suppliers suppliers = suppliersRepository.findById(id).orElseThrow();
        suppliers.setName(name);
        suppliers.setOrganization(org);
        suppliersRepository.save(suppliers);
        return "redirect:/suppliers";
    }
    @GetMapping("/suppliers/add")
    public String addSuppliers(Model model){
        model.addAttribute("orgs",organizationRepository.findAll());
        return "suppliers/addSuppliers";
    }
    @PostMapping("/suppliers/add")
    public String postAddSuppliers(Model model,@RequestParam String name,@RequestParam Organization org){
        service.save(name,org);
        return "redirect:/suppliers";
    }



//MATERIALS



    @GetMapping("/suppliers/{id}/addMaterials")
    public String MaterialsSuppliers(Model model, @PathVariable int id) {
        model.addAttribute("materials", suppliersRepository.findById(id).orElseThrow().getMaterials());
        model.addAttribute("id", id);
        return "suppliers/addMaterialsSuppliers";
    }

    @PostMapping("/suppliers/{id}/addMaterials/{idMat}/remove")
    public String postDeleteMaterialsSuppliers(Model model, @PathVariable int id, @PathVariable int idMat) {
        List<FinishedProduct> finishedProducts=finishProductRepository.findAllByMat(idMat);
        finishProductRepository.deleteAll(finishedProducts);
        Materials materials = materialsRepository.findById(idMat).orElseThrow();
        materialsRepository.delete(materials);
        return "redirect:/suppliers/{id}/addMaterials";
    }

    @GetMapping("/suppliers/{id}/addMaterials/{idMat}/change")
    public String changeMaterialsSuppliers(Model model, @PathVariable int id, @PathVariable int idMat) {
        model.addAttribute("id", id);
        model.addAttribute("materials", materialsRepository.findById(idMat).orElseThrow());
        return "suppliers/changeMaterialsSupplier";
    }

    @PostMapping("/suppliers/{id}/addMaterials/{idMat}/change")
    public String postChangeMaterialsSuppliers(Model model, @PathVariable int id, @PathVariable int idMat, @RequestParam String name,
                                               @RequestParam Optional<Integer> price, @RequestParam Optional<Integer> quantity) {
        Materials materials=materialsRepository.findById(idMat).orElseThrow();
        materials.setName(name);
        int pr;
        if(price.isPresent() && price.orElseThrow()>=0){
            pr= price.orElseThrow();
        }else {
            pr=1;
        }
        int q;
        if(quantity.isPresent()&&quantity.orElseThrow()>0){
            q=quantity.orElseThrow();
        }else{
            q=1;
        }
        materials.setPrice(pr);
        materials.setQuantity(q);
        materialsRepository.save(materials);
        return "redirect:/suppliers/{id}/addMaterials";
    }
    @GetMapping("/suppliers/{id}/addMaterials/add")
    public String addMaterials(Model model,@PathVariable int id){

        return "suppliers/addMaterials";
    }
    @PostMapping("/suppliers/{id}/addMaterials/add")
    public String postAddMaterials(Model model,@PathVariable int id,@RequestParam String name,
                                   @RequestParam Optional<Integer> price,@RequestParam Optional<Integer> quantity){
        int idMat=materialsRepository.findMaxId()+1;
        int p;
        int q;
        if(price.isPresent()&&price.orElseThrow()<=0){
            p=1;
        }else {
            p=price.orElseThrow();
        }
        if(quantity.orElseThrow()<=0){
            q=1;
        }else {
            q=quantity.orElseThrow();
        }
        Materials material=new Materials(idMat,name,q, p,suppliersRepository.findById(id).orElseThrow());
        materialsRepository.save(material);
        return "redirect:/suppliers/{id}/addMaterials";
    }
    //Organization
    @GetMapping("/suppliers/organization")
    public String organization(Model model,String org,String sup){
        List<Organization> list;
        if(!Objects.equals(org, "") && org!=null && !Objects.equals(sup, "") && sup!=null){
            list=organizationService.getByOrgAndSup(org,sup);
        } else if (!Objects.equals(org, "") && org!=null) {
            list=organizationService.getByOrgOrAddressOrId(org);
        } else if (!Objects.equals(sup, "") && sup!=null) {
            list=organizationRepository.findByManager(sup);
        } else {
            list=organizationRepository.findAll();
        }



        model.addAttribute("orgs",list);
        return "suppliers/organization";
    }
    @PostMapping("/suppliers/organization/{id}/remove")
    public String removeOrganization(Model model,@PathVariable int id){
        List<FinishedProduct> finishedProducts=finishProductRepository.findAllByOrg(id);
        finishProductRepository.deleteAll(finishedProducts);
        Organization organization=organizationRepository.findById(id).orElseThrow();
        organizationRepository.delete(organization);
        return "redirect:/suppliers/organization";
    }
    @GetMapping("/suppliers/organization/{id}/change")
    public String changeOrganization(Model model,@PathVariable int id){
        model.addAttribute("orgs",organizationRepository.findById(id).orElseThrow());
        return "suppliers/ChangeOrganization";
    }
    @PostMapping("/suppliers/organization/{id}/change")
    public String postChangeOrganization(Model model,@PathVariable int id,@RequestParam String name,@RequestParam String address){
        Organization organization=organizationRepository.findById(id).orElseThrow();
        organization.setName(name);
        organization.setAddres(address);
        organizationRepository.save(organization);
        return "redirect:/suppliers/organization";
    }
    @GetMapping("/suppliers/organization/add")
    public String addOrganization(Model model){
        return "suppliers/addOrganization";
    }
    @PostMapping("/suppliers/organization/add")
    public String PostAddOrganization(Model model,@RequestParam String name,@RequestParam String address){
        organizationService.save(name,address);
        return "redirect:/suppliers/organization";
    }

}
