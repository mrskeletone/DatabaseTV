package com.example.spring_contract.service;

import com.example.spring_contract.model.Manufacture;
import com.example.spring_contract.model.Master;
import com.example.spring_contract.repository.ManufactureRepository;
import com.example.spring_contract.repository.MasterRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManufactureAndMasterService {
    @NonNull
    private ManufactureRepository manufactureRepository;
    @NonNull
    private MasterRepository masterRepository;

    public void saveManufacture(Manufacture manufacture) {
        int i = manufactureRepository.check(manufacture.getId_Manufucture());
        if (i == 0 && manufacture.getId_Manufucture() > 0) {
            manufactureRepository.save(manufacture);
        } else {
            i = manufactureRepository.findMax()+1;
            manufacture.setId_Manufucture(i);
            manufactureRepository.save(manufacture);
        }
    }

    public void saveMaster(Master master) {
        int i = masterRepository.check(master.getId_master());
        if (i == 0 && master.getId_master() > 0) {
            masterRepository.save(master);
        } else {
            i = masterRepository.findMax()+1;
            master.setId_master(i);
            masterRepository.save(master);
        }
    }

    public List<Manufacture> findByIdOrMasterOrProduct(String id) {
        List<Manufacture> list;
        if(id!=null&& !id.isEmpty()) {
            try {
                list = manufactureRepository.findByIds(Integer.parseInt(id));
                return list;
            } catch (Exception ignored) {
                List<Manufacture> listProduct = manufactureRepository.findByProduct(id);
                list = manufactureRepository.findByMaster(id);
                list.addAll(listProduct);
                return list;
            }
        }else {
            list =manufactureRepository.findAll();
            return list;
        }
    }
    public List<Master> findByIdOrMaster(String id) {
        List<Master> list;
        if(id!=null&& !id.isEmpty()) {
            try {
                list = masterRepository.findById_master(Integer.parseInt(id));
                return list;
            } catch (Exception ignored) {
                List<Master> listSpec=masterRepository.findBySpec(id);
                list = masterRepository.findByName(id);
                list.addAll(listSpec);
                return list;
            }
        }else {
            list =masterRepository.findAll();
            return list;
        }
    }
    public List<Master> findBySalary(Optional<Integer> begin,Optional<Integer> end,List<Master> list){
        List<Master> listSalary=new ArrayList<>();
        if(begin.isPresent()&& end.isPresent()){
             listSalary=masterRepository.findBySalary(begin.orElseThrow(),end.orElseThrow());
        } else if (begin.isPresent()) {
            listSalary=masterRepository.findByBeginSalary(begin.orElseThrow());
        } else if (end.isPresent()) {
            listSalary=masterRepository.findBySalary(0,end.orElseThrow());
        }
        if(!listSalary.isEmpty()){
            List<Master> copy=new ArrayList<>(list);
            copy.removeAll(listSalary);
            list.removeAll(copy);
        }
        return list;
    }
}
