package com.example.spring_contract.service;

import com.example.spring_contract.model.Manufacture;
import com.example.spring_contract.repository.ManufactureRepository;
import com.example.spring_contract.repository.MasterRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManufactureAndMasterService {
    @NonNull
    private ManufactureRepository manufactureRepository;
    @NonNull
    private MasterRepository masterRepository;

    public void saveMan(Manufacture manufacture){
        //Доделать
    }
}
