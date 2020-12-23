package com.geongo.insurancedomain.services;

import com.geongo.insurancedomain.entity.CarInsurancePolicy;
import com.geongo.insurancedomain.repos.CarInsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class CarInsurancePolicyService {

    @Autowired
    private CarInsurancePolicyRepository carInsurancePolicyRepository;

    public boolean saveCarInsurancePolicy(CarInsurancePolicy carInsurancePolicy){

        carInsurancePolicyRepository.save(carInsurancePolicy);

        return true;
    }
}
