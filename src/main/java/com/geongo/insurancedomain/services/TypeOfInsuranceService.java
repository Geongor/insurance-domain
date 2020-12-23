package com.geongo.insurancedomain.services;

import com.geongo.insurancedomain.entity.TypeOfInsurance;
import com.geongo.insurancedomain.repos.TypeOfInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfInsuranceService {

    @Autowired
    private TypeOfInsuranceRepository typeRepository;

    public boolean saveType(TypeOfInsurance type){

        typeRepository.save(type);

        return true;
    }

    public List<TypeOfInsurance> getProperties(){

        return (List<TypeOfInsurance>) typeRepository.findAll();
    }

    public List<TypeOfInsurance> getTypes(String insurance){

        return typeRepository.findByInsurance(insurance);
    }
}
