package com.example.spring_contract.service;

import com.example.spring_contract.model.Client;
import com.example.spring_contract.model.Manager;
import com.example.spring_contract.repository.ManagerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    @NonNull
    private ManagerRepository repository;

    public void save(String name, Optional<Integer> salary) {
        int id = repository.findMaxId() + 1;
        Manager manager = new Manager(id, name, 0, salary.orElseThrow());
        repository.save(manager);
    }

    public List<Manager> find(String id, Optional<Integer> beginSalary, Optional<Integer> endSalary,
                              Optional<Integer> beginEarned, Optional<Integer> endEarned) {
        List<Manager> list;
        if (id != null && !"".equals(id)) {
            try {
                list = repository.findManagerById(Integer.parseInt(id));
            } catch (Exception e) {
                list = repository.findManagerByName(id);
            }
        } else {
            list = repository.findAll();
        }
        List<Manager> salary = new ArrayList<>();
        if (beginSalary.isPresent() && endSalary.isPresent()) {
            salary = repository.findManagerBySalaryBetween(beginSalary.orElseThrow(), endSalary.orElseThrow());
        } else if (beginSalary.isPresent()) {
            salary = repository.findManagerBySalaryBegin(beginSalary.orElseThrow());
        } else if (endSalary.isPresent()) {
            salary = repository.findManagerBySalaryEnd(endSalary.orElseThrow());
        }
        List<Manager> earned = new ArrayList<>();
        if (beginEarned.isPresent() && endEarned.isPresent()) {
            earned = repository.findManagerByEarnedBetween(beginEarned.orElseThrow(), endEarned.orElseThrow());
        } else if (beginEarned.isPresent()) {
            earned = repository.findManagerByEarnedBegin(beginEarned.orElseThrow());
        } else if (endEarned.isPresent()) {
            earned = repository.findManagerByEarnedEnd(endEarned.orElseThrow());
        }
        List<Manager> copy = new ArrayList<>(list);
        if (!salary.isEmpty() && !earned.isEmpty()) {
//            List<Manager> copyS=new ArrayList<>(salary);
//            List<Manager> copyE=new ArrayList<>(earned);
//            if(copyE.size()>copyS.size()){
//                copyE.removeAll(copyS);
//                list.removeAll(copyE);
//            }else {
//                copyS.removeAll(copyE);
//                list.removeAll(copyS);
//            }
            List<Manager> tr = new ArrayList<>();
            for (var l1 :
                    list) {
                for (var l2 :
                        salary) {
                    for (var l3 :
                            earned) {
                        if (l1 == l2 && l2 == l3) {
                            tr.add(l1);
                        }
                    }
                }
            }
            return tr;
        } else if (!salary.isEmpty()) {
            copy.removeAll(salary);
            list.removeAll(copy);
        } else if (!earned.isEmpty()) {
            copy.removeAll(earned);
            list.removeAll(copy);
        }


        return list;
    }
}
